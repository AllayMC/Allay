package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Azalea tree feature implementation.
 *
 * @author daoge_cmd
 */
public class AzaleaTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:azalea_tree");

    public AzaleaTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.OAK_LOG,
                BlockTypes.AZALEA_LEAVES,
                null,
                4, 6
        );
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight(4, 2, 0);
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(context, height, x, y, z, (treeHeight, currentHeight) -> currentHeight < 1 ? 0 : 1, false);
        if (maxFreeTreeHeight < height) {
            return false;
        }

        context.setBlockState(x, y - 1, z, BlockTypes.DIRT_WITH_ROOTS.getDefaultState());

        var attachments = new ArrayList<FoliageAttachment>();
        HorizontalDirection bendDirection = HorizontalDirection.values()[random.nextInt(HorizontalDirection.values().length)];
        int topIndex = maxFreeTreeHeight - 1;
        int currentX = x;
        int currentZ = z;

        for (int i = 0; i <= topIndex; i++) {
            if (i + 1 >= topIndex + random.nextInt(2)) {
                currentX += bendDirection.stepX();
                currentZ += bendDirection.stepZ();
            }

            placeLogIfValid(context, currentX, y + i, currentZ);
            if (i >= 3) {
                attachments.add(new FoliageAttachment(currentX, y + i, currentZ, 0, false));
            }
        }

        int bendLength = 1 + random.nextInt(2);
        int bendY = y + maxFreeTreeHeight;
        for (int i = 0; i <= bendLength; i++) {
            placeLogIfValid(context, currentX, bendY, currentZ);
            attachments.add(new FoliageAttachment(currentX, bendY, currentZ, 0, false));
            currentX += bendDirection.stepX();
            currentZ += bendDirection.stepZ();
        }

        var placedLeaves = new ArrayList<TreePos>();
        for (var attachment : attachments) {
            for (int i = 0; i < 50; i++) {
                int targetX = attachment.x() + random.nextInt(3) - random.nextInt(3);
                int targetY = attachment.y() + random.nextInt(2) - random.nextInt(2);
                int targetZ = attachment.z() + random.nextInt(3) - random.nextInt(3);
                BlockState leafState = random.nextInt(4) == 0 ?
                        BlockTypes.AZALEA_LEAVES_FLOWERED.getDefaultState() :
                        BlockTypes.AZALEA_LEAVES.getDefaultState();
                tryPlaceLeaves(context, targetX, targetY, targetZ, leafState, placedLeaves);
            }
        }
        return true;
    }
}
