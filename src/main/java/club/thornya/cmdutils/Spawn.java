package club.thornya.cmdutils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (command instanceof Player){
            Bukkit.getConsoleSender().sendMessage("§cComando executado somente por jogadores!");
            return true;
        }

        if (command.getName().equalsIgnoreCase("spawn")) {
            Player p = (Player) sender;
            if (args.length == 0) {
                System.out.println("entrei aqui spawn");
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("survivalhub");
                p.sendPluginMessage(CMDUtils.getInstance(), "BungeeCord", out.toByteArray());
            }
            if(args.length == 1) {
                if (p.isOp() || p.hasPermission("cmdutils.spawn.others")) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        if (target.isOnline()) {
                            ByteArrayDataOutput out = ByteStreams.newDataOutput();
                            out.writeUTF("Connect");
                            out.writeUTF("survivalhub");
                            target.sendPluginMessage(CMDUtils.getInstance(), "BungeeCord", out.toByteArray());
                        } else {
                            p.sendMessage("§cEsse jogador está offline.");
                        }
                    } else {
                        p.sendMessage("§cEsse jogador está offline.");
                    }
                } else {
                    p.sendMessage("§cVocê não tem permissão para executar esse comando.");
                }
            }
        }

        return false;
    }
}
