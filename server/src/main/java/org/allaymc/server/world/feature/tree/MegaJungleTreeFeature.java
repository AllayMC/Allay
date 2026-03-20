package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Mega jungle tree feature implementation.
 *
 * @author daoge_cmd
 */
public class MegaJungleTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:mega_jungle_tree");

    public MegaJungleTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.JUNGLE_LOG,
                BlockTypes.JUNGLE_LEAVES
        );
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight(10, 2, 19);

        for (int dx = 0; dx <= 1; dx++) {
            for (int dz = 0; dz <= 1; dz++) {
                if (!canGrowOn(context.getBlockState(x + dx, y - 1, z + dz))) {
                    return false;
                }
            }
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(
                context,
                height,
                x,
                y,
                z,
                (treeHeight, currentHeight) -> currentHeight < 1 ? 1 : 2,
                false
        );
        if (maxFreeTreeHeight < height) {
            return false;
        }

        for (int dx = 0; dx <= 1; dx++) {
            for (int dz = 0; dz <= 1; dz++) {
                placeDirtUnder(context, x + dx, y - 1, z + dz);
            }
        }

        var placedLogs = new ArrayList<Vector3i>();
        var attachments = new ArrayList<FoliageAttachment>();

        for (int dy = 0; dy < maxFreeTreeHeight; dy++) {
            placeLogIfValid(context, x, y + dy, z, placedLogs);
            if (dy < maxFreeTreeHeight - 1) {
                placeLogIfValid(context, x + 1, y + dy, z, placedLogs);
                placeLogIfValid(context, x + 1, y + dy, z + 1, placedLogs);
                placeLogIfValid(context, x, y + dy, z + 1, placedLogs);
            }
        }
        attachments.add(new FoliageAttachment(x, y + maxFreeTreeHeight, z, 0, true));

        for (int branchStart = maxFreeTreeHeight - 2 - random.nextInt(4);
             branchStart > maxFreeTreeHeight / 2;
             branchStart -= 2 + random.nextInt(4)) {
            float angle = random.nextFloat() * ((float) Math.PI * 2.0f);
            int branchX = 0;
            int branchZ = 0;
            for (int i = 0; i < 5; i++) {
                branchX = (int) (1.5f + Math.cos(angle) * i);
                branchZ = (int) (1.5f + Math.sin(angle) * i);
                placeLogIfValid(context, x + branchX, y + branchStart - 3 + i / 2, z + branchZ, placedLogs);
            }
            attachments.add(new FoliageAttachment(x + branchX, y + branchStart, z + branchZ, -2, false));
        }

        var placedLeaves = new ArrayList<Vector3i>();
        for (var attachment : attachments) {
            placeMegaJungleFoliage(context, attachment, 2, 2, 0, placedLeaves);
        }
        placeTrunkVines(context, placedLogs);
        placeLeafVines(context, placedLeaves, 0.25f);
        return true;
    }

    private void placeMegaJungleFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<Vector3i> placedLeaves
    ) {
        int foliageLayers = attachment.doubleTrunk() ? foliageHeight : 1 + ThreadLocalRandom.current().nextInt(2);
        for (int localY = offset; localY >= offset - foliageLayers; localY--) {
            int range = foliageRadius + attachment.radiusOffset() + 1 - localY;
            placeLeavesRow(
                    context,
                    attachment.x(),
                    attachment.y(),
                    attachment.z(),
                    range,
                    localY,
                    attachment.doubleTrunk(),
                    (ignored, coord, rowY, rowRange, large) ->
                            coord.localX() + coord.localZ() >= 7 ||
                            coord.localX() * coord.localX() + coord.localZ() * coord.localZ() > rowRange * rowRange,
                    placedLeaves
            );
        }
    }

}
