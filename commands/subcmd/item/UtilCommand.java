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

package net.momirealms.customfishing.commands.subcmd.item;

import net.momirealms.customfishing.CustomFishing;
import net.momirealms.customfishing.commands.AbstractSubCommand;
import net.momirealms.customfishing.manager.MessageManager;
import net.momirealms.customfishing.util.AdventureUtils;
import net.momirealms.customfishing.util.ItemStackUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UtilCommand extends AbstractSubCommand {

    public static final UtilCommand INSTANCE = new UtilCommand();

    public UtilCommand() {
        super("util");
        regSubCommand(GetCommand.INSTANCE);
        regSubCommand(GiveCommand.INSTANCE);
    }

    public static class GetCommand extends AbstractSubCommand {

        public static final GetCommand INSTANCE = new GetCommand();

        public GetCommand() {
            super("get");
        }

        @Override
        public boolean onCommand(CommandSender sender, List<String> args) {
            if (super.lackArgs(sender, 1, args.size())
                    || super.noConsoleExecute(sender)
                    || super.itemNotExist(sender, "util", args.get(0))
            ) return true;
            if (args.size() == 1){
                ItemStackUtils.givePlayerUtil((Player) sender, args.get(0), 0);
                super.getItemMsg(sender, args.get(0), 1);
            } else {
                if (Integer.parseInt(args.get(1)) < 1){
                    AdventureUtils.sendMessage(sender, MessageManager.prefix + MessageManager.wrongAmount);
                    return true;
                }
                ItemStackUtils.givePlayerUtil((Player) sender, args.get(0), Integer.parseInt(args.get(1)));
                super.getItemMsg(sender, args.get(0), Integer.parseInt(args.get(1)));
            }
            return true;
        }

        @Override
        public List<String> onTabComplete(CommandSender sender, List<String> args) {
            if (args.size() == 1) {
                return filterStartingWith(new ArrayList<>(CustomFishing.getInstance().getEffectManager().getUtilItems().keySet()), args.get(0));
            } else if (args.size() == 2) {
                return filterStartingWith(List.of("1", "2", "4", "8", "16", "32", "64"), args.get(1));
            }
            return null;
        }
    }

    public static class GiveCommand extends AbstractSubCommand {

        public static final GiveCommand INSTANCE = new GiveCommand();

        public GiveCommand() {
            super("give");
        }

        @Override
        public boolean onCommand(CommandSender sender, List<String> args) {
            if (super.lackArgs(sender, 2, args.size())
                    || playerNotOnline(sender, args.get(0))
                    || itemNotExist(sender, "util", args.get(1))
            ) return true;
            if (args.size() == 2){
                ItemStackUtils.givePlayerUtil(Bukkit.getPlayer(args.get(0)), args.get(1), 0);
                super.giveItemMsg(sender, args.get(0), args.get(1), 1);
            }
            else if (args.size() == 3) {
                if (Integer.parseInt(args.get(2)) < 1) {
                    AdventureUtils.sendMessage(sender, MessageManager.prefix + MessageManager.wrongAmount);
                    return true;
                }
                ItemStackUtils.givePlayerUtil(Bukkit.getPlayer(args.get(0)), args.get(1), Integer.parseInt(args.get(2)));
                super.giveItemMsg(sender, args.get(0), args.get(1), Integer.parseInt(args.get(2)));
            }
            return true;
        }

        @Override
        public List<String> onTabComplete(CommandSender sender, List<String> args) {
            if (args.size() == 1) {
                return filterStartingWith(online_players(), args.get(0));
            } else if (args.size() == 2) {
                return filterStartingWith(new ArrayList<>(CustomFishing.getInstance().getEffectManager().getUtilItems().keySet()), args.get(1));
            } else if (args.size() == 3) {
                return filterStartingWith(List.of("1", "2", "4", "8", "16", "32", "64"), args.get(2));
            }
            return null;
        }
    }
}