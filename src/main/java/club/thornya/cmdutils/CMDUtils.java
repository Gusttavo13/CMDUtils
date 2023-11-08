package club.thornya.cmdutils;

import club.thornya.cmdutils.listeners.DeathEvent;
import club.thornya.cmdutils.listeners.RedstoneEvent;
import club.thornya.cmdutils.listeners.VillagerEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

//
// * Project: CMDUtils
// * Author: Gusttavo13
// * Date: 15/09/2023
// * Time: 00:00
// * File: CMDUtils.java
//

public final class CMDUtils extends JavaPlugin {
    FileConfiguration config = getConfig();
    private static CMDUtils instance;

    @Override
    public void onEnable() {
        instance = this;
        config.options().copyDefaults(true);
        saveConfig();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new ListenerCommands(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new VillagerEvent(), this);
        getServer().getPluginManager().registerEvents(new RedstoneEvent(), this);
        getCommand("spawn").setExecutor(new Spawn());
        Bukkit.getConsoleSender().sendMessage("§a[CMDUtils] §fPlugin carregado com sucesso!");

    }

    public static CMDUtils getInstance() {
        return instance;
    }
}
