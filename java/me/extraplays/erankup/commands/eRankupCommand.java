package me.extraplays.erankup.commands;

import me.extraplays.erankup.eRankup;
import me.extraplays.erankup.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class eRankupCommand implements CommandExecutor {

    private eRankup eRankup;
    public eRankupCommand(eRankup eRankup){
        this.eRankup = eRankup;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (cmd.getName().equalsIgnoreCase("erankup")){


            Player p = (Player) sender;

            if (args.length == 3){

                if (args[0].equalsIgnoreCase("set")){

                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                    if (target == null) {
                        p.sendMessage("Jogador " + args[1] + " não encontrado");
                        return true;
                    }

                    Rank rank = eRankup.getRankManager().getRankByName(args[2]);
                    if (rank == null) return true;

                    eRankup.getRankManager().setRank((Player) target, rank);


                }

            }

            if (args.length == 1){
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")){
                    // make the reload command
                }
            }




        }

        return false;
    }
}
