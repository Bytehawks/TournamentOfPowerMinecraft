package com.bytehawks.tournament.tournamentofpower.service;

import com.bytehawks.tournament.tournamentofpower.TournamentOfPower;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;


public class RemoveBlockService {

    private TournamentOfPower plugin;

    private double blockRow = 1d;
    private double borderHalfSideLength;
    private double worldHeight;
    private List<Location> locationList;

    private double x, y, z;
    private Location lowestNorthWestBlock1;
    private Location highestNorthEastBlock;
    private Location lowestNorthEastBlock;
    private Location highestSouthEastBlock1;
    private Location lowestSouthWestBlock;
    private Location highestSouthEastBlock2;
    private Location lowestNorthWestBlock2;
    private Location highestSouthWestBlock;

    private Material replaceBlock = Material.AIR;


    public RemoveBlockService(TournamentOfPower plugin) {
        this.plugin = plugin;

    }

    private void setLocationstoRemove() {
        locationList = plugin.getRemovedBlocks();
        Location borderCenter = plugin.getWorld().getWorldBorder().getCenter();

        lowestNorthWestBlock1 = new Location(plugin.getWorld(), borderCenter.getX() - borderHalfSideLength, 0, borderCenter.getZ() - borderHalfSideLength + blockRow);
        highestNorthEastBlock = new Location(plugin.getWorld(), borderCenter.getX() + borderHalfSideLength, worldHeight, borderCenter.getZ() - borderHalfSideLength + blockRow);

        lowestNorthEastBlock = new Location(plugin.getWorld(), borderCenter.getX() - borderHalfSideLength + blockRow, 0, borderCenter.getZ() + borderHalfSideLength);
        highestSouthEastBlock1 = new Location(plugin.getWorld(), borderCenter.getX() - borderHalfSideLength + blockRow, worldHeight, borderCenter.getZ() - borderHalfSideLength);

        lowestSouthWestBlock = new Location(plugin.getWorld(), borderCenter.getX() + borderHalfSideLength, 0, borderCenter.getZ() + borderHalfSideLength + blockRow);
        highestSouthEastBlock2 = new Location(plugin.getWorld(), borderCenter.getX() - borderHalfSideLength, worldHeight, borderCenter.getZ() + borderHalfSideLength + blockRow);

        lowestNorthWestBlock2 = new Location(plugin.getWorld(), borderCenter.getX() - borderHalfSideLength + blockRow, 0, borderCenter.getZ() + borderHalfSideLength);
        highestSouthWestBlock = new Location(plugin.getWorld(), borderCenter.getX() - borderHalfSideLength + blockRow, worldHeight, borderCenter.getZ() - borderHalfSideLength);


        lowestNorthWestBlock1.getChunk().load();
        highestNorthEastBlock.getChunk().load();
        lowestNorthEastBlock.getChunk().load();
        highestSouthEastBlock1.getChunk().load();
        lowestSouthWestBlock.getChunk().load();
        highestSouthEastBlock2.getChunk().load();
        lowestNorthWestBlock2.getChunk().load();
        highestSouthWestBlock.getChunk().load();

    }


    public void removeNorthBlocks() {
        plugin.getLogger().info("northBlocks");
        setLocationstoRemove();
        //Norden
        x = lowestNorthWestBlock1.getX();
        z = lowestNorthWestBlock1.getZ();
        while (x < highestNorthEastBlock.getX()) {
            y = lowestNorthWestBlock1.getY();
            while (y < worldHeight) {
                Location location = new Location(plugin.getWorld(), (int) x, (int) y, (int) z);
                if (!location.getBlock().getType().equals(Material.AIR)) {
                    location.getBlock().setType(replaceBlock, false);
                    locationList.add(location);
                }
                y++;
            }
            x++;
        }
        plugin.setRemovedBlocks(locationList);
    }

    public void removeWestBlocks() {
        plugin.getLogger().info("westBlocks");
        setLocationstoRemove();
        //Osten
        x = lowestNorthEastBlock.getX();
        z = lowestNorthEastBlock.getZ();
        while (z < highestSouthEastBlock1.getZ()) {
            y = lowestNorthEastBlock.getY();
            while (y < worldHeight) {
                Location location = new Location(plugin.getWorld(), (int) x, (int) y, (int) z);
                if (!location.getBlock().getType().equals(Material.AIR)) {
                    location.getBlock().setType(replaceBlock, false);
                    locationList.add(location);
                }
                y++;
            }
            z++;
        }
        plugin.setRemovedBlocks(locationList);
    }

    public void removeSouthBlocks() {
        plugin.getLogger().info("southBlocks");
        setLocationstoRemove();
        //Sued
        x = lowestSouthWestBlock.getX();
        z = lowestSouthWestBlock.getZ();
        while (x < highestSouthEastBlock2.getX()) {
            y = lowestSouthWestBlock.getY();
            while (y < worldHeight) {
                Location location = new Location(plugin.getWorld(), (int) x, (int) y, (int) z);
                if (!location.getBlock().getType().equals(Material.AIR)) {
                    location.getBlock().setType(replaceBlock, false);
                    locationList.add(location);
                }
                y++;
            }
            x++;
        }
        plugin.setRemovedBlocks(locationList);
    }

    public void removeEastBlocks() {
        plugin.getLogger().info("eastBlocks");
        setLocationstoRemove();
        //West
        x = lowestNorthWestBlock2.getX();
        z = lowestNorthWestBlock2.getZ();
        while (z < highestSouthWestBlock.getZ()) {
            y = lowestNorthWestBlock2.getY();
            while (y < worldHeight) {
                Location location = new Location(plugin.getWorld(), (int) x, (int) y, (int) z);
                if (!location.getBlock().getType().equals(Material.AIR)) {
                    location.getBlock().setType(replaceBlock, false);
                    locationList.add(location);
                }
                y++;
            }
            z++;
        }
        plugin.setRemovedBlocks(locationList);
    }

    public double getBlockRow() {
        return blockRow;
    }

    public void setBlockRow(double blockRow) {
        this.blockRow = blockRow;
    }

    public void setBorderHalfSideLength(double borderHalfSideLength) {
        this.borderHalfSideLength = borderHalfSideLength;
    }

    public void setWorldHeight(double worldHeight) {
        this.worldHeight = worldHeight;
    }
}
