package noadvertisement.noadvertisement.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class OpJoin implements Listener {
    @EventHandler
    public void Playerjoin(PlayerJoinEvent event)
    {
        Plugin plugin = noadvertisement.noadvertisement.NoAdvertisement.getProvidingPlugin(noadvertisement.noadvertisement.NoAdvertisement.class);
        List<String> Data = plugin.getConfig().getStringList("Data");
        if(Data.size()==0) return;
        if(plugin.getConfig().getBoolean("op-push-message"))
        if(event.getPlayer().hasPermission("noadvertisement.event.get"))
        {
            event.getPlayer().sendMessage(ChatColor.YELLOW+"==获取到您不在的时候玩家输入了以下网址==");
            for (String command_ : Data)
            {
                event.getPlayer().sendMessage(command_);
            }
            event.getPlayer().sendMessage(ChatColor.YELLOW+"============================================");
        }
    }
}
