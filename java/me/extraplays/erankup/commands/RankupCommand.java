package me.extraplays.erankup.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.extraplays.erankup.eRankup;
import me.extraplays.erankup.inventory.MenuGui;
import me.extraplays.erankup.rank.Rank;
import me.extraplays.erankup.rank.RankManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RankupCommand implements CommandExecutor {

    eRankup eRankup;
    public RankupCommand(eRankup eRankup){
        this.eRankup = eRankup;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        if (cmd.getName().equalsIgnoreCase("rankup")){

            Player p = (Player) sender;
            RankManager manager = eRankup.getRankManager();

            FileConfiguration config = eRankup.getConfig();
            FileConfiguration ranksConfig = eRankup.ranks.config();


            Rank actual = manager.getRank(p);
            Rank next = manager.getNextRank(p);

            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Rank Atual: " + PlaceholderAPI.setPlaceholders(p, "%eRankup_rank%")));
            if (next == null) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Proximo Rank: Nenhum"));
            }else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Proximo Rank: " + PlaceholderAPI.setPlaceholders(p, "%eRankup_next_rank%")));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Preço: " + PlaceholderAPI.setPlaceholders(p, "%eRankup_next_rank_cost%")));
            }

            MenuGui gui = new MenuGui(config.getString("menu.name").replace("&", "§"), (9 * config.getInt("menu.rows") ));

            for (String s : ranksConfig.getConfigurationSection("ranks").getKeys(false)){

                String path = "ranks." + s;

                ItemStack it = new ItemStack(Material.matchMaterial(ranksConfig.getString(path + ".material")), 1);
                ItemMeta meta = it.getItemMeta();
                meta.setDisplayName(ranksConfig.getString(path + ".tag").replace("&", "§"));

                List<String> lore = new ArrayList<>();

                for (String l : ranksConfig.getStringList(path + ".lore")){

                    String cost = String.valueOf(ranksConfig.getInt(path + ".cost"));

                    lore.add(
                            l.replace("@rank", ranksConfig.getString(path + ".tag"))
                            .replace("@cost", String.valueOf(cost))
                            .replace("&", "§")
                    );
                }

                meta.setLore(lore);


                it.setItemMeta(meta);

                gui.setItem(it, ranksConfig.getInt(path + ".slot"));

            }

            gui.open(p);

        }

        return false;
    }
}
