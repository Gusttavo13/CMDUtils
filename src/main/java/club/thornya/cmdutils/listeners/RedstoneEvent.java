package club.thornya.cmdutils.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class RedstoneEvent implements Listener {
    @EventHandler
    public void RedstoneStrongholdChests (BlockRedstoneEvent e){
        Location ground = new Location(e.getBlock().getWorld(), -478, -32, 1647);
        Location pressure = new Location(e.getBlock().getWorld(), -478, -31, 1647);

        if(e.getBlock().getLocation().equals(pressure)) {
            Location block1 = new Location(e.getBlock().getWorld(), -475, -34, 1639);
            Location block2 = new Location(e.getBlock().getWorld(), -481, -34, 1639);

            if (!ground.getBlock().isBlockPowered()) {
                e.getBlock().getWorld().playSound(e.getBlock().getLocation(), "minecraft:block.stone_button.click_on", 1, 1);
                block1.getBlock().setType(Material.AIR);
                block2.getBlock().setType(Material.AIR);
                e.getBlock().getWorld().playSound(block1, "minecraft:block.piston.extend", 1, 1);
                e.getBlock().getWorld().playSound(block2, "minecraft:block.piston.extend", 1, 1);
            } else {
                e.getBlock().getWorld().playSound(block1, "minecraft:block.piston.contract", 1, 1);
                e.getBlock().getWorld().playSound(block2, "minecraft:block.piston.contract", 1, 1);
                block1.getBlock().setType(Material.STONE_BRICKS);
                block2.getBlock().setType(Material.STONE_BRICKS);
            }
        }
    }
}
