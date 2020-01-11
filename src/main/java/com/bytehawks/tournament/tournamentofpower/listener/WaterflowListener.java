package com.bytehawks.tournament.tournamentofpower.listener;

import com.bytehawks.tournament.tournamentofpower.TournamentOfPower;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockFromToEvent;

public class WaterflowListener extends FlowListener {

    public WaterflowListener(TournamentOfPower plugin) {
        setPlugin(plugin);
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        Material material = event.getBlock().getType();
        if (material.equals(Material.WATER) && isInRemovedBlockArea(event.getBlock().getLocation())) {
            event.setCancelled(true);
        }
    }
}
