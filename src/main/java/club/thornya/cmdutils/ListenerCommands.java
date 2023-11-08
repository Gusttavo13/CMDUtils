package club.thornya.cmdutils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Objects;

//
// * Project: CMDUtils
// * Author: Gusttavo13
// * Date: 15/09/2023
// * Time: 00:00
// * File: ListenerCommands.java
//

public class ListenerCommands implements Listener {
    private final ArrayList<String> commands = new ArrayList<>();

    public ListenerCommands() {
        this.commands.addAll(Objects.requireNonNull(CMDUtils.getInstance().config.getConfigurationSection("block-commands")).getKeys(false));
    }

    @EventHandler(priority = org.bukkit.event.EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage().split(" ")[0].replace("/", "");
        if(command.equalsIgnoreCase("spawn")) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("survivalhub");
            e.getPlayer().sendPluginMessage(CMDUtils.getInstance(), "BungeeCord", out.toByteArray());
        }

        if(!(e.getPlayer().isOp() || e.getPlayer().hasPermission("cmdutils.bypass"))) {
            if (commands.contains(command)) {
                CMDUtils.getInstance().config.getStringList("block-commands." + command + ".messages").forEach(s -> e.getPlayer().sendMessage(s.replace("&", "§")));
                if(CMDUtils.getInstance().config.getBoolean("discord.enable")) DiscordHook.sendWebhook(command, e.getPlayer());
                e.setCancelled(true);
            }
        }
    }
}
