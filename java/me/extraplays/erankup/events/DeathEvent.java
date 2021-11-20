package me.extraplays.erankup.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeaht(EntityDeathEvent event){

        switch (event.getEntityType()){
            case ZOMBIE:
            case PLAYER:
        }

    }

}
