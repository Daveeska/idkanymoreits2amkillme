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

package net.momirealms.customfishing.fishing.action;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.momirealms.customfishing.CustomFishing;
import net.momirealms.customfishing.util.AdventureUtils;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;

public record VanillaXPImpl(int amount, boolean mending) implements Action {

    @Override
    public void doOn(Player player, Player another) {
        if (CustomFishing.getInstance().getVersionHelper().isSpigot()) {
            if (mending) {
                player.getLocation().getWorld().spawn(player.getLocation(), ExperienceOrb.class, e -> e.setExperience(amount));
            } else {
                player.giveExp(amount);
                AdventureUtils.playerSound(player, Sound.Source.PLAYER, Key.key("minecraft:entity.experience_orb.pickup"), 1, 1);
            }
        } else {
            player.giveExp(amount, mending);
            AdventureUtils.playerSound(player, Sound.Source.PLAYER, Key.key("minecraft:entity.experience_orb.pickup"), 1, 1);
        }
    }
}
