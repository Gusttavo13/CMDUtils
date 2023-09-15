package club.thornya.cmdutils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

public class ListenerCommands implements Listener {
    private final ArrayList<String> commands = new ArrayList<>();

    public ListenerCommands() {
        this.commands.addAll(Config.get().getConfigurationSection("block-commands").getKeys(false));
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if(!(e.getPlayer().isOp() || e.getPlayer().hasPermission("cmdutils.bypass"))) {
            String command = e.getMessage().split(" ")[0].replace("/", "");

            if (commands.contains(command)) {
                Config.get().getStringList("block-commands." + command + ".messages").forEach(s -> e.getPlayer().sendMessage(s));
                DiscordHook.sendWebhook(command, e.getPlayer());
                e.setCancelled(true);
            }
        }
    }
}
