package club.thornya.cmdutils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands extends CommandsAdapter{
    public Commands(String name) {
        super(name);
    }

    @Override
    public void run(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("O console não pode rodar esse comando!");
            return;
        }
        Player player = (Player) commandSender;
    }
}
