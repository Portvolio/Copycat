package wtf.violet.portvolio.copycat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import wtf.violet.portvolio.copycat.Copycat;

public class DeathListener implements Listener
{

    private final Copycat cat;

    public DeathListener(Copycat cat)
    {
        this.cat = cat;
    }

    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event)
    {
        final Player victim = event.getEntity();
        final Player killer = victim.getKiller();

        if (killer != null)
        {
            final PlayerInventory inv = killer.getInventory();
            final PlayerInventory victimInv = victim.getInventory();

            inv.clear();

            final ItemStack[] contents = victimInv.getContents();

            for (int i = 0; i < contents.length; i++)
            {
                final ItemStack stack = contents[i];

                if (stack == null)
                {
                    continue;
                }

                inv.setItem(i, stack);
            }

            cat.getServer().getScheduler().runTaskLater(cat, () ->
            {
                victim.getInventory().clear();
            }, 1);
        }
    }

}
