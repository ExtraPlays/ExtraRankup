package me.extraplays.erankup.inventory;

import me.extraplays.erankup.eRankup;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class MenuGui {

    private Inventory inventory;

    public MenuGui(String name, int size){
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    public void setItem(ItemStack item, int slot){
        inventory.setItem(slot, item);
    }

    public void open(Player p){
        p.openInventory(getInventory());
    }

    public Inventory getInventory() {
        return inventory;
    }
}
