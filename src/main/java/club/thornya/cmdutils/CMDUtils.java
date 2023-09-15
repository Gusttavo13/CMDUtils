package club.thornya.cmdutils;

import org.bukkit.plugin.java.JavaPlugin;

public final class CMDUtils extends JavaPlugin {

    private static CMDUtils instance;

    @Override
    public void onEnable() {
        instance = this;
        Config.setup("config.yml", this);
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
