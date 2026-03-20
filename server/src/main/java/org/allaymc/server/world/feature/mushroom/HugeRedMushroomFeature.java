package org.allaymc.server.world.feature.mushroom;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

/**
 * Huge red mushroom feature.
 * Generates a mushroom with a dome-shaped cap:
 * - 3 ring layers (5x5 edges only, no corners/interior) from height-3 to height-1
 * - 1 solid top layer (3x3) at height
 * Total cap blocks: 3x12 + 9 = 45
 *
 * @author daoge_cmd
 */
public class HugeRedMushroomFeature extends HugeMushroomFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:huge_red_mushroom");

    public HugeRedMushroomFeature() {
        super(IDENTIFIER, BlockTypes.RED_MUSHROOM_BLOCK);
    }

    @Override
    protected int getCapRadius() {
        return 2;
    }

    @Override
    protected int getCapStartY(int height) {
        return height - 3;
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        int height = calculateHeight();

        if (!canGenerate(context, x, y, z, height)) {
            return false;
        }

        placeStem(context, x, y, z, height);
        placeCap(context, x, y, z, height);

        return true;
    }

    @Override
    protected void placeCap(WorldFeatureContext context, int x, int y, int z, int height) {
        // foliageRadius = 2, i2 = foliageRadius - 2 = 0
        for (int i = height - 3; i <= height; i++) {
            int capY = y + i;
            // Lower layers: radius 2 (5x5), top layer: radius 1 (3x3)
            int radius = i < height ? 2 : 1;

            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    boolean xEdge = dx == -radius || dx == radius;
                    boolean zEdge = dz == -radius || dz == radius;

                    // Lower layers: only place ring blocks (edge in exactly one axis)
                    // Top layer: place all blocks
                    if (i < height && xEdge == zEdge) {
                        continue;
                    }

                    // Calculate directional HUGE_MUSHROOM_BITS
                    // WEST if dx < 0, EAST if dx > 0, NORTH if dz < 0, SOUTH if dz > 0
                    int bits = 5;
                    if (dx < 0) bits--;
                    if (dx > 0) bits++;
                    if (dz < 0) bits -= 3;
                    if (dz > 0) bits += 3;

                    if (canReplace(context.getBlockState(x + dx, capY, z + dz))) {
                        context.setBlockState(x + dx, capY, z + dz, createCapState(bits));
                    }
                }
            }
        }
    }
}
