package me.extraplays.erankup.events;

import me.extraplays.erankup.eRankup;
import me.extraplays.erankup.rank.Rank;
import me.extraplays.erankup.rank.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClickEvent implements Listener {

    eRankup eRankup;
    public ClickEvent(eRankup eRankup){
        this.eRankup = eRankup;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){

        if (!e.getView().getTitle().equals(eRankup.getConfig().getString("menu.name").replace("&", "§"))) return;
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();

        if (e.isLeftClick()) {

            ItemStack clicked = e.getCurrentItem();

            RankManager manager = me.extraplays.erankup.eRankup.getRankManager();

            if (e.getCurrentItem() == null) return;

            if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(manager.getNextRank(p).getPrefix().replace("&", "§"))){
                p.sendMessage("Você não pode upar pra esse rank");
                p.closeInventory();
                e.setCancelled(true);
            }else {

                if (eRankup.econ.getBalance(p) >= manager.getNextRank(p).getCost()){

                    eRankup.econ.withdrawPlayer(p, manager.getNextRank(p).getCost());

                    manager.Rankup(p);
                    p.closeInventory();
                    p.sendMessage("Você foi para o Rank  " + manager.getRank(p).getPrefix().replace("&", "§"));
                }else {
                    p.sendMessage("Dinheiro insuficiente");
                    e.setCancelled(true);
                    p.closeInventory();
                }

                e.setCancelled(true);

            }

        }

        e.setCancelled(true);


    }
}
