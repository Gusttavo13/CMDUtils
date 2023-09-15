package club.thornya.cmdutils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class CMDUtils extends JavaPlugin {
    FileConfiguration config = getConfig();
    private static CMDUtils instance;

    @Override
    public void onEnable() {
        instance = this;
        config.options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(new ListenerCommands(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CMDUtils getInstance() {
        return instance;
    }
}
