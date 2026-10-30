package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code SpruceTreeFeature} places a straight spruce trunk with descending
 * conical foliage rows. Ground and clearance checks precede writes, and the
 * foliage height, maximum radius, and vertical offset are sampled after the
 * trunk is placed.
 *
 * <p>
 * The row radius grows until it reaches a moving cap, then resets before
 * widening again; the cap itself grows only up to the selected foliage radius.
 * This state makes lower rows depend on earlier row decisions, rather than
 * computing each radius independently from height. The shared row helper omits
 * square corners and records accepted leaf positions. Context availability and
 * replacement checks still control individual writes without rollback.
 *
 * @author daoge_cmd
 */
public class SpruceTreeFeature extends TreeWorldFeature {

    /**
     * Built-in spruce tree registry key. Runtime feature construction uses
     * this identifier to register the corresponding placement implementation.
     */
    public static final Identifier IDENTIFIER = new Identifier("minecraft:spruce_tree");

    /**
     * Creates and immediately registers the built-in spruce tree feature. The
     * constructor fixes its log and leaf materials while shared clearance and
     * replacement behavior remains in the tree base.
     */
    public SpruceTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.SPRUCE_LOG,
                BlockTypes.SPRUCE_LEAVES
        );
    }

    /**
     * Attempts a spruce tree after ground and clearance checks, returning
     * {@code false} before writes when admission fails. The straight trunk is
     * placed before sampling foliage height, radius, and offset for the
     * descending cone.
     *
     * <p>
     * Foliage height retains a four-block minimum, while row radii are
     * selected by a stateful growth-and-reset sequence. That sequence is
     * passed to the shared row helper with square-corner omissions, so canopy
     * shape follows both sampled limits and earlier rows. Block replacement
     * and context availability still control individual leaves without making
     * the accepted tree update atomic.
     */
    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight(5, 2, 1);
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(context, height, x, y, z, (treeHeight, currentHeight) -> currentHeight < 2 ? 0 : 2, true);
        if (maxFreeTreeHeight < height) {
            return false;
        }

        placeDirtUnder(context, x, y - 1, z);

        for (int dy = 0; dy < maxFreeTreeHeight; dy++) {
            placeLog(context, x, y + dy, z);
        }

        // A four-block minimum keeps a short trunk's foliage sequence from
        // collapsing.
        int foliageHeight = Math.max(4, maxFreeTreeHeight - (1 + random.nextInt(2)));
        int foliageRadius = 2 + random.nextInt(2);
        int offset = random.nextInt(3);
        placeSpruceFoliage(
                context,
                new FoliageAttachment(x, y + maxFreeTreeHeight, z, 0, false),
                foliageHeight,
                foliageRadius,
                offset,
                new ArrayList<Vector3i>()
        );
        return true;
    }

    /**
     * Places descending spruce foliage rows using a growing and resetting
     * radius. The attachment and offset determine vertical placement, while
     * the supplied foliage radius caps the progressively increasing maximum.
     *
     * <p>
     * After a row reaches the current maximum, the next radius resets and the
     * maximum grows toward its cap. The first reset begins at zero and later
     * resets begin at one, making subsequent row widths depend on earlier
     * state. Square corners are omitted whenever radius is positive, and
     * accepted positions are appended through the shared row helper rather
     * than assumed to form a complete layer.
     */
    private void placeSpruceFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<Vector3i> placedLeaves
    ) {
        var random = ThreadLocalRandom.current();
        int range = random.nextInt(2);
        int maxRange = 1;
        int resetRange = 0;
        for (int localY = offset; localY >= -foliageHeight; localY--) {
            int currentRange = range;
            placeLeavesRow(
                    context,
                    attachment.x(),
                    attachment.y(),
                    attachment.z(),
                    currentRange,
                    localY,
                    attachment.doubleTrunk(),
                    (ignored, coord, rowY, rowRange, large) ->
                            coord.localX() == rowRange && coord.localZ() == rowRange && rowRange > 0,
                    placedLeaves
            );

            // Reset only after the current row reaches its cap, then grow the cap
            // toward the sampled limit so consecutive row widths stay coordinated.
            if (range >= maxRange) {
                range = resetRange;
                resetRange = 1;
                maxRange = Math.min(maxRange + 1, foliageRadius + attachment.radiusOffset());
            } else {
                range++;
            }
        }
    }
}
