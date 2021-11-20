package me.extraplays.erankup.placeholders;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.extraplays.erankup.eRankup;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceHolders extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "eRankup";
    }

    @Override
    public @NotNull String getAuthor() {
        return "ExtraPlays";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, @NotNull String params) {

        if (p == null) return "";

        if (params.equals("rank")){
            return eRankup.getRankManager().getRank(p).getPrefix();
        }

        if (params.equals("next_rank")){

            if (eRankup.getRankManager().getNextRank(p) == null) {
                return "Nenhum..";
            }

            return eRankup.getRankManager().getNextRank(p).getPrefix();
        }

        if (params.equals("next_rank_cost")){

            if (eRankup.getRankManager().getNextRank(p) == null) {
                return "";
            }

            return String.valueOf(eRankup.getRankManager().getNextRank(p).getCost());
        }

        return null;

    }
}
