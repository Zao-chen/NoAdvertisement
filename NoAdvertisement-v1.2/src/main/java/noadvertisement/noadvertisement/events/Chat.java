package noadvertisement.noadvertisement.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.regex.*;

public class Chat implements Listener {
    @EventHandler
    public void ChatEvent(AsyncPlayerChatEvent event) {
        Plugin plugin = noadvertisement.noadvertisement.NoAdvertisement.getProvidingPlugin(noadvertisement.noadvertisement.NoAdvertisement.class);
        String message = event.getMessage();
        List<String> skip = plugin.getConfig().getStringList("skip-words");
        String test1 = message;
        if(plugin.getConfig().getBoolean("screen"))
        {
            for (int i = 0; i != skip.size(); i++)
            {
                test1 = test1.replace(skip.get(i), "");
            }
            List<String> screen = plugin.getConfig().getStringList("screen-words");
            for (int i = 0; i != screen.size(); i++)
            {
                test1 = test1.replace(screen.get(i), "**");
            }
            event.setMessage(test1);
        }
        if(message.equals(test1))
        {
            event.setMessage(test1);
        }
        else
        {
            if (plugin.getConfig().getBoolean("feedback"))
            {
                event.getPlayer().sendMessage(ChatColor.YELLOW + "[Noadvertisement]" + ChatColor.RED + plugin.getConfig().getString("feedback"));
            }
            if (plugin.getConfig().getBoolean("cancel"))
            {
                event.setCancelled(true);
            }
        }
        //——————————————————————————————————————
        if (message.contains("https:")) {
            String regex = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
            StringBuffer sb = new StringBuffer();
            Pattern p = Pattern.compile(regex);
            Matcher matcher = p.matcher(event.getMessage());
            //如果找到了我们正则里要的东西
            while (matcher.find()) {
                //保存到sb中，"\r\n"表示找到一个放一行，就是换行
                sb.append(matcher.group());
            }
            String testold = sb.toString();
            String test;
            test = testold.replace("//", "$$");
            test = test.replace("/", "亹");
            test = test.replace("$$", "//");
            sb = new StringBuffer();
            p = Pattern.compile(regex);
            matcher = p.matcher(test);
            //如果找到了我们正则里要的东西
            while (matcher.find()) {
                //保存到sb中，"\r\n"表示找到一个放一行，就是换行
                sb.append(matcher.group());
            }
            test = sb.toString();
            List<String> Whitelist = plugin.getConfig().getStringList("Whitelist");
            for (String Whitelist_to_string : Whitelist) {
                if (test.equals(Whitelist_to_string)) {
                    event.setMessage(ChatColor.GREEN + plugin.getConfig().getString("message-safe")+ ChatColor.WHITE + message);
                    return;
                }
            }
            List<String> Blacklist = plugin.getConfig().getStringList("Blacklist");
            for (String Blacklist_to_string : Blacklist) {
                if (test.equals(Blacklist_to_string)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.YELLOW + "[Noadvertisement]" + ChatColor.RED + plugin.getConfig().getString("message-dangersend"));
                    List<String> Data = plugin.getConfig().getStringList("Data");
                    Data.add(plugin.getConfig().getString("message-danger")+test+" —— "+event.getPlayer().getName());
                    plugin.getConfig().set("Data", Data);
                    plugin.saveConfig();
                    return;
                }
            }
            event.setMessage(ChatColor.YELLOW + plugin.getConfig().getString("message-unkonw") + ChatColor.WHITE + message);
            List<String> Data = plugin.getConfig().getStringList("Data");
            Data.add(plugin.getConfig().getString("message-unkonw")+test+" —— "+event.getPlayer().getName());
            plugin.getConfig().set("Data", Data);
            plugin.saveConfig();
        }
        else if(message.contains("http")||message.contains(".cn")||message.contains(".gg")||message.contains(".org")||message.contains(".com")||message.contains(".net")||message.contains("top")||message.contains(".xyz"))
        {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.YELLOW + "[Noadvertisement]" + ChatColor.RED + plugin.getConfig().getString("message-back"));
        }
    }
}