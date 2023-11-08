package club.thornya.cmdutils.listeners;

import club.thornya.cmdutils.CMDUtils;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerRespawnEvent e) {

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("survivalhub");
        e.getPlayer().sendPluginMessage(CMDUtils.getInstance(), "BungeeCord", out.toByteArray());
    }

}
