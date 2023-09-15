package club.thornya.cmdutils;

import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.Arrays;

public class RegisterCommands {

    private static SimpleCommandMap scm;
    private SimplePluginManager spm;



    private void registerCommands(CommandsAdapter... commands) {
        Arrays.stream(commands).forEach(command -> scm.register("CMDUtils", command));//Register the plugin
    }

    private void registerCommand(String name) {
        scm.register("CMDUtils", new Commands(name));
    }

    private void setupSimpleCommandMap() {
        spm = (SimplePluginManager) CMDUtils.getInstance().getServer().getPluginManager();
        Field f = null;
        try {
            f = SimplePluginManager.class.getDeclaredField("commandMap");
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            scm = (SimpleCommandMap) f.get(spm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SimpleCommandMap getCommandMap() {
        return scm;
    }

}
