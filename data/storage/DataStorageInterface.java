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

package net.momirealms.customfishing.data.storage;

import net.momirealms.customfishing.data.PlayerSellData;
import net.momirealms.customfishing.data.PlayerStatisticsData;
import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface DataStorageInterface {

    void initialize();
    void disable();
    Inventory loadBagData(UUID uuid, boolean force);
    void saveBagData(UUID uuid, Inventory inventory, boolean unlock);
    void saveBagData(Set<Map.Entry<UUID, Inventory>> set, boolean unlock);
    PlayerSellData loadSellData(UUID uuid, boolean force);
    void saveSellData(UUID uuid, PlayerSellData playerSellData, boolean unlock);
    void saveSellData(Set<Map.Entry<UUID, PlayerSellData>> set, boolean unlock);
    StorageType getStorageType();
    void saveStatistics(UUID uuid, PlayerStatisticsData statisticsData, boolean unlock);
    void saveStatistics(Set<Map.Entry<UUID, PlayerStatisticsData>> set, boolean unlock);
    PlayerStatisticsData loadStatistics(UUID uuid, boolean force);
}
