/*
 *  Copyright (C) <2022> <XiaoMoMi>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.momirealms.customfishing.manager;

import net.momirealms.customfishing.CustomFishing;
import net.momirealms.customfishing.data.storage.DataStorageInterface;
import net.momirealms.customfishing.data.storage.FileStorageImpl;
import net.momirealms.customfishing.data.storage.MySQLStorageImpl;
import net.momirealms.customfishing.data.storage.StorageType;
import net.momirealms.customfishing.object.Function;
import net.momirealms.customfishing.util.AdventureUtils;
import net.momirealms.customfishing.util.ConfigUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DataManager extends Function {

    private DataStorageInterface dataStorageInterface;
    private final CustomFishing plugin;
    private StorageType storageType;
    private ScheduledFuture<?> timerSave;

    public DataManager(CustomFishing plugin) {
        this.plugin = plugin;
    }

    public DataStorageInterface getDataStorageInterface() {
        return dataStorageInterface;
    }

    private boolean loadStorageMode() {
        YamlConfiguration config = ConfigUtils.getConfig("database.yml");
        if (config.getString("data-storage-method","YAML").equalsIgnoreCase("YAML")) {
            if (storageType != StorageType.YAML) {
                this.dataStorageInterface = new FileStorageImpl(plugin);
                this.storageType = StorageType.YAML;
                return true;
            }
        } else {
            if (storageType != StorageType.SQL) {
                this.dataStorageInterface = new MySQLStorageImpl(plugin);
                this.storageType = StorageType.SQL;
                return true;
            }
        }
        return false;
    }

    @Override
    public void load() {
        if (loadStorageMode()) this.dataStorageInterface.initialize();
        this.timerSave = plugin.getScheduler().runTaskTimerAsync(() -> {
            //long time1 = System.currentTimeMillis();
            if (ConfigManager.enableFishingBag) {
                if (!ConfigManager.hideSaveInfo) AdventureUtils.consoleMessage("[CustomFishing] Saving fishing bag data...");
                plugin.getBagDataManager().saveBagDataForOnlinePlayers(false);
            }
            if (ConfigManager.enableStatistics) {
                if (!ConfigManager.hideSaveInfo) AdventureUtils.consoleMessage("[CustomFishing] Saving statistics data...");
                plugin.getStatisticsManager().saveStatisticsDataForOnlinePlayers(false);
            }
            //AdventureUtil.consoleMessage("[CustomFishing] Data saved for all online players. Took " + (System.currentTimeMillis() - time1) + " ms.");
            if (!ConfigManager.hideSaveInfo) AdventureUtils.consoleMessage("[CustomFishing] Data saved for all online players.");
        }, 20, 20, TimeUnit.MINUTES);
    }

    @Override
    public void unload() {
        if (timerSave != null && !timerSave.isCancelled()) {
            timerSave.cancel(false);
        }
        YamlConfiguration config = ConfigUtils.getConfig("database.yml");
        StorageType st = config.getString("data-storage-method","YAML").equalsIgnoreCase("YAML") ? StorageType.YAML : StorageType.SQL;
        if (this.dataStorageInterface != null && dataStorageInterface.getStorageType() != st) this.dataStorageInterface.disable();
    }

    @Override
    public void disable() {
        if (this.dataStorageInterface != null) {
            this.dataStorageInterface.disable();
        }
        if (timerSave != null && !timerSave.isCancelled()) {
            timerSave.cancel(false);
        }
    }
}
