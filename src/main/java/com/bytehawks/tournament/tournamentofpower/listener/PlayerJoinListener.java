package com.bytehawks.tournament.tournamentofpower.listener;

import com.bytehawks.tournament.tournamentofpower.TournamentOfPower;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class PlayerJoinListener implements Listener {

    private static Material material = Material.BEDROCK;
    private TournamentOfPower plugin;

    public PlayerJoinListener(TournamentOfPower plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Block block = player.getLocation().getBlock();
        Block westBlock = block.getRelative(BlockFace.WEST);
        Block westUpBlock = westBlock.getRelative(BlockFace.UP);
        Block northBlock = block.getRelative(BlockFace.NORTH);
        Block northUpBlock = northBlock.getRelative(BlockFace.UP);
        Block eastBlock = block.getRelative(BlockFace.EAST);
        Block eastUpBlock = eastBlock.getRelative(BlockFace.UP);
        Block southBlock = block.getRelative(BlockFace.SOUTH);
        Block southUpBlock = southBlock.getRelative(BlockFace.UP);
        Block downBlock = block.getRelative(BlockFace.DOWN);
        Block upBlock = block.getRelative(BlockFace.UP, 3);

        List<String> deadPlayers = plugin.getConfig().getStringList("isDead");

        if (!plugin.isStarted() && !deadPlayers.contains(player.getUniqueId().toString())) {
            westBlock.setType(material);
            westUpBlock.setType(material);
            northBlock.setType(material);
            northUpBlock.setType(material);
            eastBlock.setType(material);
            eastUpBlock.setType(material);
            southBlock.setType(material);
            southUpBlock.setType(material);
            downBlock.setType(material);
            upBlock.setType(material);
        }

        if (deadPlayers.contains(player.getUniqueId().toString())) {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }

}
