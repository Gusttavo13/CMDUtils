package club.thornya.cmdutils;

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
        getServer().getPluginManager().registerEvents(new ListenerCommands(), this);
        Bukkit.getConsoleSender().sendMessage("§a[CMDUtils] §fPlugin carregado com sucesso!");

    }

    public static CMDUtils getInstance() {
        return instance;
    }
}
