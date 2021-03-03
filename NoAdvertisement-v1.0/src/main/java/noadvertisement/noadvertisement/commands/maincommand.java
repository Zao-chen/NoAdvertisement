package noadvertisement.noadvertisement.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class maincommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label, String[] args) {
        Plugin plugin = noadvertisement.noadvertisement.NoAdvertisement.getProvidingPlugin(noadvertisement.noadvertisement.NoAdvertisement.class);
        if(args.length==0)
        {
            sender.sendMessage(ChatColor.YELLOW+"[Noadvertisement] "+ChatColor.GREEN+"请使用/Noadvertisement help查看帮助");
        }
        else if(args.length==1)
        {
            String arg_to_string = args[0];
            if(arg_to_string.equals("help"))
            {
                sender.sendMessage(ChatColor.YELLOW+"[Noadvertisement]"+ChatColor.GREEN+" /Noadvertisement reload - 重载插件");
            }
            else if(arg_to_string.equals("reload"))
            {
                if(sender.hasPermission("noadvertisement.event.reload"))
                {
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.YELLOW+"[Noadvertisement]"+ChatColor.GREEN+" 插件重载成功"); }
                }
        }
        else if(args.length==3)
        {
            String arg_to_string = args[0];
            if(arg_to_string.equals("add"))
            {
                arg_to_string = args[1];
                if(arg_to_string.equals("w"))
                {
                    List<String> Whitelist = plugin.getConfig().getStringList("Whitelist");
                    arg_to_string = args[2];
                    Whitelist.add(args[2]);
                    Whitelist.add(args[2]+"/");
                    Whitelist.add("https://"+args[2]);
                    Whitelist.add("https://"+args[2]+"/");
                    plugin.getConfig().set("Whitelist", Whitelist);
                    plugin.saveConfig();
                    sender.sendMessage(ChatColor.YELLOW+"[Noadvertisement]"+ChatColor.GREEN+" 白名单追加成功");
                }
                else if(arg_to_string.equals("b"))
                {
                    List<String> Whitelist = plugin.getConfig().getStringList("Blacklist");
                    arg_to_string = args[2];
                    Whitelist.add(args[2]);
                    Whitelist.add(args[2]+"/");
                    Whitelist.add("https://"+args[2]);
                    Whitelist.add("https://"+args[2]+"/");
                    plugin.getConfig().set("Blacklist", Whitelist);
                    plugin.saveConfig();
                    sender.sendMessage(ChatColor.YELLOW+"[Noadvertisement]"+ChatColor.GREEN+" 黑名单追加成功");
                }
            }
        }
        return false;
    }
}
