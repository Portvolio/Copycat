package wtf.violet.portvolio.copycat;

import org.bukkit.GameRule;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.violet.portvolio.copycat.listener.DeathListener;

public final class Copycat extends JavaPlugin {

    @Override
    public void onEnable()
    {
        final Server server = getServer();
        server.getPluginManager().registerEvents(new DeathListener(this), this);

        for (final World world : server.getWorlds())
        {
            world.setGameRule(GameRule.KEEP_INVENTORY, true);
        }
    }

}
