package club.thornya.cmdutils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

public abstract class CommandsAdapter extends Command implements PluginIdentifiableCommand {

    CMDUtils cmdUtils = CMDUtils.getInstance();
    CommandSender sender;//So you can mess with this inside this class
    public CommandsAdapter(String name) {
        super(name);
    }
    @Override
    public Plugin getPlugin() {
        return cmdUtils;
    }

    public abstract void run(CommandSender sender, String commandLabel, String[] arguments);//Just simpler and allows 'return;' instead of 'return true/false;'

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] arguments) {
        this.sender = sender;
        run(sender, commandLabel, arguments);//actually run the command.
        return true;
    }
}
