package me.extraplays.erankup.config;

import me.extraplays.erankup.eRankup;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ExtraConfig {

    private File arq;
    public eRankup m;
    private String configName;
    private FileConfiguration fileconfig;

    public ExtraConfig(final String file, final eRankup m) {
        this.m = m;
        this.arq = new File(m.getDataFolder(), file + ".yml");
        this.fileconfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.arq);
        this.configName = file;
        if (m.getResource(this.arq.getName()) != null) {
            m.saveResource(this.arq.getName(), false);
        }
        else {
            try {
                this.arq.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public FileConfiguration config() {
        return this.fileconfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.arq);
    }

    public void save() {
        try {
            this.fileconfig.save(this.arq);
        }
        catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("[ExtraConfig] Nao foi Possivel Salvar a " + this.configName);
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        this.arq = new File(this.m.getDataFolder(), this.configName);
        try {
            this.fileconfig.load(this.arq);
        }
        catch (FileNotFoundException e) {
            System.out.println("[ExtraConfig] Config nao Encontrada");
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        catch (InvalidConfigurationException e3) {
            System.out.println("[ExtraConfig] Configuracao invalida");
            e3.printStackTrace();
        }
    }

}
