package de.docbrumm.terracraft.world;

import org.bukkit.*;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TreePopulation extends BlockPopulator {

    @Override
    public void populate(@NotNull World world, @NotNull Random random, @NotNull Chunk chunk) {
        if (random.nextBoolean()) {
            int amount = random.nextInt(4)+1;  // Amount of trees
            for (int x = 0; x < 15; x++) {
                for (int z = 0; z < 15; z++) {
                    world.generateTree(new Location(world, x*16, world.getHighestBlockAt(x*16, z*16).getLocation().getY(), z*16), TreeType.TREE);
                }
            }
        }
    }
}
