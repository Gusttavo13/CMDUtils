package club.thornya.cmdutils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Objects;

public class ListenerCommands implements Listener {
    private final ArrayList<String> commands = new ArrayList<>();

    public ListenerCommands() {
        this.commands.addAll(Objects.requireNonNull(CMDUtils.getInstance().config.getConfigurationSection("block-commands")).getKeys(false));
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if(!(e.getPlayer().isOp() || e.getPlayer().hasPermission("cmdutils.bypass"))) {
            String command = e.getMessage().split(" ")[0].replace("/", "");

            if (commands.contains(command)) {
                CMDUtils.getInstance().config.getStringList("block-commands." + command + ".messages").forEach(s -> e.getPlayer().sendMessage(s.replace("&", "§")));
                if(CMDUtils.getInstance().config.getBoolean("discord.enable")) DiscordHook.sendWebhook(command, e.getPlayer());
                e.setCancelled(true);
            }
        }
    }
}
