package com.bytehawks.tournament.tournamentofpower.task;

import com.bytehawks.tournament.tournamentofpower.TournamentOfPower;
import org.bukkit.scheduler.BukkitRunnable;


public class RemoveBlockTask extends BukkitRunnable {

    private final TournamentOfPower plugin;

    public RemoveBlockTask(TournamentOfPower plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.getServer().broadcastMessage("Der Rand fällt");
        plugin.getLogger().info(plugin.getRemoveBlockService().getBlockRow() + "");
        plugin.getRemoveBlockService().setBlockRow(plugin.getRemoveBlockService().getBlockRow() + 1d);
        plugin.getRemoveBlockService().setBorderHalfSideLength(plugin.getWorld().getWorldBorder().getSize() / 2d);
        plugin.getRemoveBlockService().setWorldHeight(plugin.getWorld().getMaxHeight());
        plugin.getRemoveBlockService().removeNorthBlocks();
        plugin.getRemoveBlockService().removeEastBlocks();
        plugin.getRemoveBlockService().removeSouthBlocks();
        plugin.getRemoveBlockService().removeWestBlocks();

    }

}
