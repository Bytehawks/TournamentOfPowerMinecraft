package com.bytehawks.tournament.tournamentofpower.listener;

import com.bytehawks.tournament.tournamentofpower.TournamentOfPower;
import org.bukkit.Location;
import org.bukkit.event.Listener;

public class FlowListener implements Listener {

    private TournamentOfPower plugin;

    boolean isInRemovedBlockArea(Location location) {
        return getPlugin().getRemovedBlocks().contains(location);
    }

    public TournamentOfPower getPlugin() {
        return plugin;
    }

    public void setPlugin(TournamentOfPower plugin) {
        this.plugin = plugin;
    }
}
