package de.docbrumm.terracraft.world;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class WorldGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, ChunkGenerator.BiomeGrid biome) {
        if(x == 0) {
            // This is our Chunk region, if the to be generated chunk gets into this step, we want it to be only one block width.
            ChunkData data = createVanillaChunkData(world, x, z);
            for (int xCoord = 1; xCoord < 16; xCoord++) {
                for (int zCoord = 0; zCoord < 16; zCoord++) {
                    for (int yCoord = 0; yCoord < data.getMaxHeight(); yCoord++) {
                        data.setBlock(xCoord, yCoord, zCoord, Material.AIR);
                    }
                }
            }
            for (int zCoord = 0; zCoord < 16; zCoord++) {
                for (int yCoord = 0; yCoord < data.getMaxHeight(); yCoord++) {
                    data.setBlock(10, yCoord, zCoord, data.getBlockData(0, yCoord, zCoord));
                }
            }
            return data;
        }
        return createChunkData(world); // Here we want some lonely emptiness...
    }

    @Override
    public @NotNull List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        return List.of(new TreePopulation());
    }
}
