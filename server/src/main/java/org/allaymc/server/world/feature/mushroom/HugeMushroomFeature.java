package org.allaymc.server.world.feature.mushroom;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.allaymc.server.world.feature.AbstractWorldFeature;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Abstract base class for huge mushroom features.
 * Provides shared logic for brown and red huge mushroom generation.
 *
 * @author daoge_cmd
 */
public abstract class HugeMushroomFeature extends AbstractWorldFeature {

    protected final BlockType<?> capType;

    public HugeMushroomFeature(Identifier identifier, BlockType<?> capType) {
        super(identifier);
        this.capType = capType;
    }

    protected boolean canReplace(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockType == BlockTypes.AIR ||
               blockType.hasBlockTag(BlockTags.REPLACEABLE) ||
               blockType.hasBlockTag(BlockTags.LEAVES);
    }

    protected boolean canGrowOn(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockType.hasBlockTag(BlockTags.DIRT) ||
               blockType == BlockTypes.MYCELIUM ||
               blockType == BlockTypes.PODZOL;
    }

    protected int calculateHeight() {
        var random = ThreadLocalRandom.current();
        int height = 4 + random.nextInt(3); // 4-6
        if (random.nextInt(12) == 0) {
            height *= 2; // 1/12 chance to double
        }
        return height;
    }

    protected boolean canGenerate(WorldFeatureContext context, int x, int y, int z, int height) {
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        // Check trunk column
        for (int dy = 0; dy < height; dy++) {
            if (!canReplace(context.getBlockState(x, y + dy, z))) {
                return false;
            }
        }

        // Check cap area (radius 3) at the top
        int capRadius = getCapRadius();
        int capStartY = getCapStartY(height);
        for (int dy = capStartY; dy <= height; dy++) {
            for (int dx = -capRadius; dx <= capRadius; dx++) {
                for (int dz = -capRadius; dz <= capRadius; dz++) {
                    if (!canReplace(context.getBlockState(x + dx, y + dy, z + dz))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    protected void placeStem(WorldFeatureContext context, int x, int y, int z, int height) {
        var stemState = BlockTypes.MUSHROOM_STEM.getDefaultState()
                .setPropertyValue(BlockPropertyTypes.HUGE_MUSHROOM_BITS, 15); // ALL_STEM
        for (int dy = 0; dy < height; dy++) {
            if (canReplace(context.getBlockState(x, y + dy, z))) {
                context.setBlockState(x, y + dy, z, stemState);
            }
        }
    }

    protected BlockState createCapState(int hugeMushroomBits) {
        return capType.getDefaultState()
                .setPropertyValue(BlockPropertyTypes.HUGE_MUSHROOM_BITS, hugeMushroomBits);
    }

    protected abstract int getCapRadius();

    protected abstract int getCapStartY(int height);

    protected abstract void placeCap(WorldFeatureContext context, int x, int y, int z, int height);
}
