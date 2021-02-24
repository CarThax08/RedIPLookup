package com.github.carthax08.rediplookup;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LookupCommand implements CommandExecutor {
    Main plugin;
    public LookupCommand(Main main){
        plugin = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("ip.use")){
                if(args.length >= 1){
                    OfflinePlayer player2 = null;
                    for(Player player1 : Bukkit.getOnlinePlayers()){
                        if(player1.getName().equals(args[0])){
                            player2 = player1;
                        }
                    }
                    for(OfflinePlayer p : Bukkit.getOfflinePlayers()){
                        if(p.getName().equals(args[0])){
                            player2 = p;
                        }
                    }
                    if(player2 != null){
                        for(String string : plugin.getConfig().getStringList("ips")){
                            if(string.contains(player2.getName())){
                                String[] strings = string.split(":");
                                if(strings[0].equals(player2.getName())){
                                    player.sendMessage(ChatColor.GREEN + player2.getName() + "'s IP is " + strings[1]);
                                }else{
                                    player.sendMessage(ChatColor.GREEN + player2.getName() + "'s IP is " + strings[0]);
                                }
                            }
                        }
                    }
                }
            }else{
                player.sendMessage(ChatColor.RED + "You do not have the required permission to use that command!");
            }
        }
        return true;
    }
}
