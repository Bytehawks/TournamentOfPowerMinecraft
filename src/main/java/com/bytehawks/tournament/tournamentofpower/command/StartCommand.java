package com.bytehawks.tournament.tournamentofpower.command;

import com.bytehawks.tournament.tournamentofpower.TournamentOfPower;
import com.bytehawks.tournament.tournamentofpower.task.RemoveBlockTask;
import com.destroystokyo.paper.Title;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartCommand implements CommandExecutor {

    private static Material material = Material.AIR;
    private TournamentOfPower plugin;

    public StartCommand(TournamentOfPower plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length > 1) {
            sender.sendMessage(ChatColor.RED + "Zu viele Argumente!");
            return false;
        }
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Nicht genug Argumente!");
            return false;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Du musst ein Spieler sein!");
            return false;
        }
        if (args[0].equals("start")) {
            Player player = (Player) sender;
            if (player.hasPermission("tounamenetofpower.start")) {
                RemoveBlockTask removeBlockTask = new RemoveBlockTask(plugin);
                removeBlockTask.runTaskTimer(plugin, 0, 1000);
                Title message = Title.builder().title(ChatColor.DARK_RED + "Das Turnier beginnt!").stay(100).subtitle("Der Rand fällt, ab jetzt").fadeIn(100).fadeOut(100).build();
                plugin.getServer().getOnlinePlayers().forEach(p -> letPlayersFree(p, message));
                plugin.setStarted(true);
                plugin.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, Boolean.TRUE);
            } else {
                sender.sendMessage(ChatColor.RED + "Du hast die Berechtigung nicht!");
            }
            return true;
        }
        return false;
    }

    private void letPlayersFree(Player player, Title message) {

        player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 100, 100);
        player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 50, 50);
        player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 75, 75);
        player.sendTitle(message);

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

        if (!plugin.isStarted()) {
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


    }

}
