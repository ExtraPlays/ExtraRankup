package me.extraplays.erankup.rank;

import me.extraplays.erankup.eRankup;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RankManager {

    private eRankup eRankup;
    private ArrayList<Rank> ranks = new ArrayList<>();
    private HashMap<UUID, Rank> playerRank = new HashMap<>();

    public RankManager(eRankup eRankup){
        this.eRankup = eRankup;
    }

    public void loadRanks(){
        FileConfiguration config = eRankup.ranks.config();
        for (String s : config.getConfigurationSection("ranks").getKeys(false)){
            Rank rank = new Rank();
            rank.setName(s);
            rank.setCost(config.getInt("ranks." + s + ".cost"));
            rank.setPrefix(config.getString("ranks." + s + ".tag"));
            rank.setNextRank(config.getString("ranks." + s + ".nextRank"));
            rank.setDefault(config.contains("ranks." + s + ".default"));
            ranks.add(rank);
        }
    }

    public void savePlayersRanks(){

        Bukkit.getConsoleSender().sendMessage("[eRankup] Salvando os Ranks");

        for (final Map.Entry<UUID, Rank> rank : playerRank.entrySet()) {
            eRankup.players.config().set("players." + rank.getKey(), rank.getValue().getName());
            eRankup.players.save();
        }
    }

    public void loadPlayersRanks(){
        FileConfiguration playersConfig = eRankup.players.config();
        FileConfiguration ranksConfig = eRankup.ranks.config();

        for (String s : playersConfig.getConfigurationSection("players").getKeys(false)){

            Rank ranks = new Rank();

            String rank = playersConfig.getString("players." + s);

            ranks.setName(rank);
            ranks.setPrefix(ranksConfig.getString("ranks." + rank + ".tag"));
            ranks.setNextRank(ranksConfig.getString("ranks." + rank + ".nextRank"));
            ranks.setCost(ranksConfig.getInt("ranks." + rank + ".cost"));
            ranks.setDefault(ranksConfig.contains("ranks." + rank + ".default"));

            playerRank.put(UUID.fromString(s), ranks);

        }
    }

    public void Rankup(Player p){
        Rank rank = getNextRank(p);
        playerRank.put(p.getUniqueId(), rank);

        if (eRankup.getConfig().getBoolean("effects.thunder")){
            p.getLocation().getWorld().strikeLightningEffect(p.getLocation());
        }

//        if (eRankup.getConfig().getBoolean("effects.bat")){
//            Entity bat = p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
//        }

    }

    public Rank getRank(Player p){
        Rank rank = playerRank.get(p.getUniqueId());
        if (rank == null){
            rank = getDefaultRank();
            playerRank.put(p.getUniqueId(), rank);
        }

        return rank;
    }

    public Rank getNextRank(Player p){

        Rank actualRank = playerRank.get(p.getUniqueId());

        for (Rank r : ranks){
            if (r.getName().equals(actualRank.getNextRank())) {
                return r;
            }
        }
        return null;
    }

    public Rank getDefaultRank(){
        for (Rank rank : ranks) {
            if (rank.isDefault()){
                return rank;
            }
        }
        return null;
    }




}
