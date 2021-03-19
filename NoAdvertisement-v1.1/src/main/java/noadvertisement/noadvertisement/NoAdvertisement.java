package noadvertisement.noadvertisement;

import noadvertisement.noadvertisement.commands.maincommand;
import noadvertisement.noadvertisement.events.Chat;
import noadvertisement.noadvertisement.events.OpJoin;
import org.bukkit.plugin.java.JavaPlugin;
import noadvertisement.noadvertisement.metrics.Metrics;

public final class NoAdvertisement extends JavaPlugin {
    @Override
    public void onEnable() {
        // All you have to do is adding the following two lines in your onEnable method.
        // You can find the plugin ids of your plugins on the page https://bstats.org/what-is-my-plugin-id
        int pluginId = 10483; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        // Optional: Add custom charts
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
        // Plugin startup logic
        this.saveDefaultConfig();
        getCommand("NoAdvertisement").setExecutor(new maincommand());
        getConfig().options().copyDefaults();
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new OpJoin(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
