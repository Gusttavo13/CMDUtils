package club.thornya.cmdutils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class Config {

    private static File file;
    private static FileConfiguration configuration;

    @SuppressWarnings("all")
    public static void setup(String path, CMDUtils main){
        file = new File(main.getDataFolder(), path);
        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                Bukkit.getConsoleSender().sendMessage("§c§lConfig do CMDUtils não criada.");
            }
        }
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return configuration;
    }

    public static void save() {
        try {
            configuration.save(file);
        }catch (IOException e){
            Bukkit.getConsoleSender().sendMessage("§c§lArquivo não salvo, ERRO");
        }
    }

    public static void defaultConfigs(){
        /*
        get().addDefault("texto", "texto");
        get().addDefault("messages.usage", List.of("&c&lLista de Comandos"));
        */
        get().options().copyDefaults(true);
        save();
    }
    public static void reload(){
        configuration = YamlConfiguration.loadConfiguration(file);
    }

}
