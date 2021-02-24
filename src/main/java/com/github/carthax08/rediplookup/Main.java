package com.github.carthax08.rediplookup;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(!getConfig().getStringList("ips").contains(e.getPlayer().getAddress().getAddress().toString())){
            List<String> t = getConfig().getStringList("ips");
            t.add(e.getPlayer().getAddress().getAddress().toString());
            getConfig().set("ips", t + ":" + e.getPlayer().getName());
            saveConfig();
        }
    }
}
