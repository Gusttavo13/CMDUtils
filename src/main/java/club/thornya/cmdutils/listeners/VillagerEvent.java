package club.thornya.cmdutils.listeners;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class VillagerEvent implements Listener {

    @EventHandler
    public void onBreeding(EntityBreedEvent e){
        if (e.getEntity() instanceof Villager){
            e.setCancelled(true);
        }
    }

}
