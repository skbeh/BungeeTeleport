package org.skbeh.bungeeteleport;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeTeleportPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getCommand("bt").setExecutor(new CommandBt(this));
    }

    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }
}

