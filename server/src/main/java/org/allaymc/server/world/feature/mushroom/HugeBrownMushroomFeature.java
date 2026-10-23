package org.allaymc.server.world.feature.mushroom;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

/**
 * {@code HugeBrownMushroomFeature} places a brown mushroom with a flat
 * seven-by-seven cap whose four outer corners are omitted. Shared mushroom
 * checks validate the substrate, stem column, and complete cap square before
 * any feature writes begin.
 *
 * <p>
 * Cap metadata combines independent horizontal edge observations: west or east
 * changes the center value by one, while north or south changes it by three.
 * Interior positions retain the center value, and omitted outer corners are
 * never written by the cap loop. The clearance square still includes those
 * omitted corners, so an obstruction there can reject growth before placement.
 * After admission, each selected position is rechecked for replaceability and
 * skipped if blocked, without undoing earlier stem or cap writes.
 *
 * @author daoge_cmd
 */
public class HugeBrownMushroomFeature extends HugeMushroomFeature {

    /**
     * Registered brown huge-mushroom feature key. The runtime uses this
     * identifier when constructing the built-in placement feature.
     */
    public static final Identifier IDENTIFIER = new Identifier("minecraft:huge_brown_mushroom");

    /**
     * Creates and immediately registers the built-in brown huge-mushroom
     * feature. The constructor selects its fixed cap material, while shared
     * height sampling and substrate checks remain in the mushroom base.
     */
    public HugeBrownMushroomFeature() {
        super(IDENTIFIER, BlockTypes.BROWN_MUSHROOM_BLOCK);
    }

    /**
     * Returns the horizontal clearance radius of three for the brown cap.
     */
    @Override
    protected int getCapRadius() {
        return 3;
    }

    /**
     * Returns the stem height as the single brown cap layer.
     */
    @Override
    protected int getCapStartY(int height) {
        return height; // cap occupies the level above the stem
    }

    /**
     * Attempts brown mushroom placement after shared substrate and
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
     * Places the brown cap at the level above the stem using a seven-by-seven
     * square without outer corners. Each remaining position receives
     * directional metadata only when its current state remains replaceable.
     */
    @Override
    protected void placeCap(WorldFeatureContext context, int x, int y, int z, int height) {
        int capY = y + height;

        for (int dx = -3; dx <= 3; dx++) {
            for (int dz = -3; dz <= 3; dz++) {
                // Omitting the four outer corners rounds the flat brown cap outline.
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
     * Returns mushroom-face metadata by combining the position's X and Z edge
     * classifications. The cap loop calls this helper for coordinates within
     * radius three after omitting the four outer corners.
     *
     * <p>
     * The center value starts at five. West and east edges contribute negative
     * or positive one, while north and south contribute negative or positive
     * three, preserving both axes in one metadata index. Interior positions
     * contribute neither adjustment and retain the center value; the helper
     * itself performs no radius validation or block writes.
     */
    private int calculateCapBits(int dx, int dz) {
        // The cap combines two independent edge offsets into one mushroom
        // metadata value, preserving X and Z contributions through separate
        // increments before block-state construction.
        int bits = 5;

        if (dx == -3) bits--; // west edge
        if (dx == 3) bits++; // east edge
        if (dz == -3) bits -= 3; // north edge
        if (dz == 3) bits += 3; // south edge

        return bits;
    }
}
