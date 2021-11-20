package me.extraplays.erankup.events;

import me.clip.placeholderapi.PlaceholderAPI;
import me.extraplays.erankup.eRankup;
import me.extraplays.erankup.rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();

        Rank rank = eRankup.getRankManager().getRank(p);

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Você esta no rank: " + rank.getPrefix()));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Proximo Rank: " + rank.getNextRank()));



    }



}
