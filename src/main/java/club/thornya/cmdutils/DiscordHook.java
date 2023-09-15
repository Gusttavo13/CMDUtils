package club.thornya.cmdutils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class DiscordHook {

    public static void sendWebhook(String content, Player p) {
        try {
            URL url = new URL(Objects.requireNonNull(Config.get().getString("discord.url")));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\n" +
                    "  \"content\": null,\n" +
                    "  \"embeds\": [\n" +
                    "    {\n" +
                    "      \"description\": \"O jogador **$nickname$** digitou o comando **$command$**```c\\nIP: $IP$\\n```\",\n" +
                    "      \"color\": 16711680\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"attachments\": []\n" +
                    "}";
            jsonInputString = jsonInputString.replace("$nickname$", p.getName()).replace("$command$", content).replace("$IP$", p.getAddress().getAddress().getHostAddress());
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            con.getInputStream();
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("§cOcorrreu um erro ao enviar o webhook!");
        }
    }

}
