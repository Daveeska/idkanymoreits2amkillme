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

package net.momirealms.customfishing.commands.subcmd;


import net.momirealms.customfishing.CustomFishing;
import net.momirealms.customfishing.commands.AbstractSubCommand;
import net.momirealms.customfishing.manager.MessageManager;
import net.momirealms.customfishing.util.AdventureUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public final class ReloadCommand extends AbstractSubCommand {

    public static final ReloadCommand INSTANCE = new ReloadCommand();

    private ReloadCommand() {
        super("reload");
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        long time1 = System.currentTimeMillis();
        CustomFishing.getInstance().reload();
        AdventureUtils.sendMessage(sender, MessageManager.prefix + MessageManager.reload.replace("{time}", String.valueOf(System.currentTimeMillis() - time1)));
        return true;
    }
}
