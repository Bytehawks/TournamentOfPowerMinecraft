package com.bytehawks.tournament.tournamentofpower.listener;

import com.bytehawks.tournament.tournamentofpower.TournamentOfPower;
import com.destroystokyo.paper.Title;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

public class PlayerDeathListener implements Listener {

    private TournamentOfPower plugin;

    public PlayerDeathListener(TournamentOfPower plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerdeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        List<String> deadPlayers = plugin.getConfig().getStringList("isDead");
        deadPlayers.add(player.getUniqueId().toString());
        System.out.println(deadPlayers.size());
        System.out.println(player.getUniqueId().toString());
        plugin.getConfig().set("isDead", deadPlayers);
        Title title = Title.builder().title(ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET + "ist ausgeschieden!").fadeIn(10).fadeOut(10).stay(20).build();
        plugin.getServer().getOnlinePlayers().forEach(p -> sendDeathMessageToAllOtherPlayers(p, title));
    }


    private void sendDeathMessageToAllOtherPlayers(Player player, Title title) {
        player.sendTitle(title);
        player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 100, 100);
        player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 50, 50);
        player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 75, 75);
    }

}
