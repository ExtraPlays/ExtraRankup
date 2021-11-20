package me.extraplays.erankup;

import me.extraplays.erankup.commands.RankupCommand;
import me.extraplays.erankup.config.ExtraConfig;
import me.extraplays.erankup.events.ClickEvent;
import me.extraplays.erankup.events.JoinEvent;
import me.extraplays.erankup.placeholders.PlaceHolders;
import me.extraplays.erankup.rank.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class eRankup extends JavaPlugin {

    public ExtraConfig ranks, players;
    private static RankManager rankManager;

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage("[eRankup] Plugin iniciado.");
        Bukkit.getConsoleSender().sendMessage("[eRankup] Checando dependencias...");
        Bukkit.getConsoleSender().sendMessage("[eRankup] Ok.");

        saveDefaultConfig();
        ranks = new ExtraConfig("ranks", this);
        players = new ExtraConfig("players", this);
        rankManager = new RankManager(this);

        rankManager.loadRanks();
        rankManager.loadPlayersRanks();
        HookPlaceHolderAPI();

        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);;
        Bukkit.getPluginManager().registerEvents(new ClickEvent(this), this);;
        Bukkit.getPluginCommand("rankup").setExecutor(new RankupCommand(this));

    }

    @Override
    public void onDisable() {
        rankManager.savePlayersRanks();
    }

    public void HookPlaceHolderAPI(){

        if (Bukkit.getPluginManager().getPlugin("PlaceHolderAPI") != null){
            this.getLogger().info("PlaceHoderAPI encontrado. Registrando placeholders!");
            new PlaceHolders().register();
        }

    }

    public static RankManager getRankManager() {
        return rankManager;
    }
}
