package org.allaymc.server.world.feature.mushroom;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

/**
 * {@code HugeRedMushroomFeature} places a red mushroom with three hollow cap
 * rings and a smaller filled top layer. Shared mushroom checks validate a
 * complete cap volume before writing, while the concrete cap assigns
 * directional metadata and rechecks replacement eligibility at each selected
 * position.
 *
 * <p>
 * Each lower ring has twelve eligible positions because both its interior and
 * four corners are omitted. The filled three-by-three top adds nine positions,
 * giving forty-five cap positions before later block checks. The clearance
 * volume includes cells omitted by this geometry, so an obstruction in a
 * cavity can reject admission even though the cap would not occupy that cell.
 * Stem placement precedes the cap and is not rolled back after later failures.
 *
 * @author daoge_cmd
 */
public class HugeRedMushroomFeature extends HugeMushroomFeature {

    /**
     * Registered red huge-mushroom feature key. The runtime uses this
     * identifier when constructing the built-in placement feature.
     */
    public static final Identifier IDENTIFIER = new Identifier("minecraft:huge_red_mushroom");

    /**
     * Creates and immediately registers the built-in red huge-mushroom
     * feature. The constructor selects its fixed cap material, while shared
     * height sampling and substrate checks remain in the mushroom base.
     */
    public HugeRedMushroomFeature() {
        super(IDENTIFIER, BlockTypes.RED_MUSHROOM_BLOCK);
    }

    /**
     * Returns the horizontal clearance radius of two for the red cap.
     */
    @Override
    protected int getCapRadius() {
        return 2;
    }

    /**
     * Returns the first red cap layer, three blocks below its top.
     */
    @Override
    protected int getCapStartY(int height) {
        return height - 3;
    }

    /**
     * Attempts red mushroom placement after shared substrate and
     * complete-volume checks. Rejection returns {@code false} before mutation;
     * accepted placement writes the stem before the cap and provides no
     * rollback for later exceptions.
     */
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

    /**
     * Places three radius-two cap rings and a filled radius-one top above the
     * stem. Lower layers omit both corners and interiors, leaving twelve
     * positions per ring; the top contributes nine positions.
     *
     * <p>
     * Horizontal offset signs select directional mushroom-face metadata
     * independently of replacement checks. The shared admission volume is
     * deliberately broader than these selected cells, so a successful precheck
     * does not imply every checked cell receives a cap block. Each selected
     * position is rechecked before writing, and any new obstruction is skipped
     * rather than aborting the cap.
     */
    @Override
    protected void placeCap(WorldFeatureContext context, int x, int y, int z, int height) {
        for (int i = height - 3; i <= height; i++) {
            int capY = y + i;
            // A smaller filled top closes the hollow lower cap rings.
            int radius = i < height ? 2 : 1;

            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    boolean xEdge = dx == -radius || dx == radius;
                    boolean zEdge = dz == -radius || dz == radius;

                    // Exactly one edge selects a lower ring side while excluding
                    // both corners and the hollow interior; the top is filled.
                    if (i < height && xEdge == zEdge) {
                        continue;
                    }

                    // Metadata combines horizontal offset signs so each cap block
                    // exposes the outward faces appropriate to its ring position.
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
