package com.bytehawks.tournament.tournamentofpower;

import com.bytehawks.tournament.tournamentofpower.command.StartCommand;
import com.bytehawks.tournament.tournamentofpower.listener.*;
import com.bytehawks.tournament.tournamentofpower.service.RemoveBlockService;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;


public final class TournamentOfPower extends JavaPlugin {
    private static TournamentOfPower instance;
    private static Logger log;
    public List<Location> removedBlocks;
    private World world;
    private boolean started = false;

    private RemoveBlockService removeBlockService;


    public TournamentOfPower() {
        instance = this;

    }

    public static TournamentOfPower getInstance() {
        return instance;
    }

    public static Logger getLog() {
        return log;
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    @Override
    public void onEnable() {
        log = Bukkit.getLogger();
        this.saveDefaultConfig();

        Optional<World> optionalWorld = this.getServer().getWorlds().stream().filter(w -> w.getEnvironment().equals(World.Environment.NORMAL)).findFirst();
        if (optionalWorld.isPresent()) {
            this.world = this.getServer().getWorld(optionalWorld.get().getName());
        } else {
            if (this.getServer().getWorlds().stream().findFirst().isPresent()) {
                this.world = this.getServer().getWorlds().stream().findFirst().get();
            }
        }

        Objects.requireNonNull(this.getServer().getPluginCommand("tournamentofpower")).setExecutor(new StartCommand(this));

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new WaterflowListener(this), this);
        pluginManager.registerEvents(new LavaflowListener(this), this);
        pluginManager.registerEvents(new PlayerJoinListener(this), this);
        pluginManager.registerEvents(new PlayerSpawnListener(this), this);
        pluginManager.registerEvents(new PlayerDeathListener(this), this);

        getWorld().getWorldBorder().setSize(601);
        getWorld().getWorldBorder().setCenter(0d, 0d);
        getWorld().setSpawnLocation(getWorld().getWorldBorder().getCenter());
        getWorld().setTime(1000);
        getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, Boolean.FALSE);
        getWorld().setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, Boolean.FALSE);
        this.removeBlockService = new RemoveBlockService(this);
        this.removedBlocks = new ArrayList<>();

    }

    public World getWorld() {
        return world;
    }

    public List<Location> getRemovedBlocks() {
        return removedBlocks;
    }

    public void setRemovedBlocks(List<Location> removedBlocks) {
        this.removedBlocks = removedBlocks;
    }

    public RemoveBlockService getRemoveBlockService() {
        return removeBlockService;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
