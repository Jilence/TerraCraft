package de.docbrumm.terracraft.world;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import java.util.Random;

public class WorldGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, ChunkGenerator.BiomeGrid biome) {
        if(x == 1) {
            // This is our Chunk region, if the to be generated chunk gets into this step, we want it to be only one block width.
            ChunkData data = createVanillaChunkData(world, x, z);
            for (int xCoord = 1; xCoord < 15; xCoord++) {
                for (int zCoord = 1; zCoord < 15; zCoord++) {
                    for (int yCoord = 0; yCoord < data.getMaxHeight(); yCoord++) {
                        data.setBlock(xCoord, yCoord, zCoord, Material.AIR);
                    }
                }
            }
        }
        return createChunkData(world); // Here we want some lonely emptiness...
    }
}
