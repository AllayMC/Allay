package org.allaymc.server.world.feature.mushroom;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

/**
 * Huge brown mushroom feature.
 * Generates a mushroom with a flat 7x7 cap at the top.
 *
 * @author daoge_cmd
 */
public class HugeBrownMushroomFeature extends HugeMushroomFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:huge_brown_mushroom");

    public HugeBrownMushroomFeature() {
        super(IDENTIFIER, BlockTypes.BROWN_MUSHROOM_BLOCK);
    }

    @Override
    protected int getCapRadius() {
        return 3;
    }

    @Override
    protected int getCapStartY(int height) {
        return height; // Single layer at the top
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
        int capY = y + height;

        for (int dx = -3; dx <= 3; dx++) {
            for (int dz = -3; dz <= 3; dz++) {
                // Skip corners (where both x and z are at +-3)
                if (Math.abs(dx) == 3 && Math.abs(dz) == 3) {
                    continue;
                }

                int bits = calculateCapBits(dx, dz);
                if (canReplace(context.getBlockState(x + dx, capY, z + dz))) {
                    context.setBlockState(x + dx, capY, z + dz, createCapState(bits));
                }
            }
        }
    }

    /**
     * Calculate the HUGE_MUSHROOM_BITS metadata for a brown mushroom cap position.
     * <p>
     * Cap metadata values:
     * 1=NW, 2=N, 3=NE, 4=W, 5=CENTER, 6=E, 7=SW, 8=S, 9=SE, 14=ALL_OUTSIDE
     * <p>
     * For the flat brown cap, edge/corner positions get directional metadata,
     * interior positions get CENTER (5) with cap on top only.
     */
    private int calculateCapBits(int dx, int dz) {
        int bits = 5; // CENTER

        if (dx == -3) bits--; // west edge
        if (dx == 3) bits++; // east edge
        if (dz == -3) bits -= 3; // north edge
        if (dz == 3) bits += 3; // south edge

        return bits;
    }
}
