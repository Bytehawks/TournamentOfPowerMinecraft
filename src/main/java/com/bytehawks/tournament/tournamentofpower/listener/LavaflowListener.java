package com.bytehawks.tournament.tournamentofpower.listener;

import com.bytehawks.tournament.tournamentofpower.TournamentOfPower;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockFromToEvent;

public class LavaflowListener extends FlowListener {


    public LavaflowListener(TournamentOfPower plugin) {
        setPlugin(plugin);
    }


    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        Material material = event.getBlock().getType();

        if (material.equals(Material.LAVA) && isInRemovedBlockArea(event.getBlock().getLocation())) {
            event.setCancelled(true);
        }
    }
}
