package com.bytehawks.tournament.tournamentofpower.listener;

import com.bytehawks.tournament.tournamentofpower.TournamentOfPower;
import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class PlayerSpawnListener implements Listener {

    private TournamentOfPower plugin;

    public PlayerSpawnListener(TournamentOfPower plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerPostRespawnEvent event) {
        Player player = event.getPlayer();
        List<String> deadPlayers = plugin.getConfig().getStringList("isDead");

        if (deadPlayers.contains(player.getUniqueId().toString())) {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }

}
