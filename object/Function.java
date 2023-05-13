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

package net.momirealms.customfishing.object;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public abstract class Function {

    public void load() {
        //empty
    }

    public void unload() {
        //empty
    }

    public void disable() {
        //empty
    }

    public void onQuit(Player player) {
        //empty
    }

    public void onJoin(Player player) {
        //empty
    }

    public void onInteract(PlayerInteractEvent event) {
        //empty
    }

    public void onBreakBlock(BlockBreakEvent event) {
        //empty
    }

    public void onConsumeItem(PlayerItemConsumeEvent event) {
        //empty
    }

    public void onDragInventory(InventoryDragEvent event) {
        //empty
    }
}
