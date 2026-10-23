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
 * {@code HugeMushroomFeature} supplies substrate, clearance, stem, and
 * cap-state rules shared by huge mushrooms. Concrete red and brown features
 * choose the cap radius, the first cap layer, and the actual cap geometry,
 * while this base retains the cap material and registers the feature.
 *
 * <p>
 * The clearance check first requires suitable soil and a replaceable stem
 * column, then checks a complete square volume across every potential cap
 * layer. That volume can include corners or interior cells the concrete cap
 * omits, making admission more conservative than the eventual shape. The
 * height sampler chooses four through six blocks with a one-in-twelve doubling
 * chance. Placement helpers recheck blocks and perform immediate context
 * writes without rollback.
 *
 * @author daoge_cmd
 */
public abstract class HugeMushroomFeature extends AbstractWorldFeature {

    /**
     * Mushroom cap block material.
     */
    protected final BlockType<?> capType;

    /**
     * Creates and registers a mushroom feature with the supplied cap material.
     * The material is retained after base registration and supplies default
     * cap states for the concrete red or brown geometry.
     */
    public HugeMushroomFeature(Identifier identifier, BlockType<?> capType) {
        super(identifier);
        this.capType = capType;
    }

    /**
     * Returns whether the block is air, replaceable, or tagged as leaves.
     */
    protected boolean canReplace(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockType == BlockTypes.AIR ||
               blockType.hasBlockTag(BlockTags.REPLACEABLE) ||
               blockType.hasBlockTag(BlockTags.LEAVES);
    }

    /**
     * Returns whether dirt-tagged soil, mycelium, or podzol supports this
     * mushroom.
     */
    protected boolean canGrowOn(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockType.hasBlockTag(BlockTags.DIRT) ||
               blockType == BlockTypes.MYCELIUM ||
               blockType == BlockTypes.PODZOL;
    }

    /**
     * Returns a random height from four through six, occasionally doubled to
     * eight, ten, or twelve. The independent doubling decision succeeds one
     * time in twelve and applies to the already sampled base height.
     */
    protected int calculateHeight() {
        var random = ThreadLocalRandom.current();
        int height = 4 + random.nextInt(3); // four through six base stem blocks
        if (random.nextInt(12) == 0) {
            // The rare doubled form uses the same cap geometry above a taller stem.
            height *= 2;
        }
        return height;
    }

    /**
     * Returns whether substrate, stem clearance, and the concrete cap volume
     * permit mushroom placement. The context supplies all reads, and
     * unavailable chunks can appear as air rather than proving stored space
     * exists.
     *
     * <p>
     * The stem scan stops below the cap-top level, while the cap scan includes
     * both its first and final layers. Every cell in each square cap layer
     * must be replaceable, even if the concrete cap omits that cell. A failed
     * check returns before any mutation, allowing callers to reject growth
     * without this helper changing terrain.
     */
    protected boolean canGenerate(WorldFeatureContext context, int x, int y, int z, int height) {
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        // Stem clearance stops below the top cap level; the cap-volume scan
        // below covers that level using the concrete feature's radius.
        for (int dy = 0; dy < height; dy++) {
            if (!canReplace(context.getBlockState(x, y + dy, z))) {
                return false;
            }
        }

        // Check the complete cap volume, including cells omitted by the final
        // shape, so every possible cap layer passes the same admission rule.
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

    /**
     * Places default mushroom-stem blocks with the all-stem metadata value
     * through the requested column. Each position is rechecked for
     * replaceability, and blocked positions are skipped without aborting
     * remaining stem writes.
     */
    protected void placeStem(WorldFeatureContext context, int x, int y, int z, int height) {
        // The stem fixes mushroom metadata at fifteen, while cap writers
        // compute their directional values separately from each horizontal
        // position; both paths use the same bounded property.
        var stemState = BlockTypes.MUSHROOM_STEM.getDefaultState()
                .setPropertyValue(BlockPropertyTypes.HUGE_MUSHROOM_BITS, 15); // all-stem face metadata
        for (int dy = 0; dy < height; dy++) {
            if (canReplace(context.getBlockState(x, y + dy, z))) {
                context.setBlockState(x, y + dy, z, stemState);
            }
        }
    }

    /**
     * Returns the configured cap material with the supplied mushroom-face
     * metadata. Property validation belongs to the block-state implementation,
     * and this helper does not translate or clamp the supplied bit value.
     */
    protected BlockState createCapState(int hugeMushroomBits) {
        return capType.getDefaultState()
                .setPropertyValue(BlockPropertyTypes.HUGE_MUSHROOM_BITS, hugeMushroomBits);
    }

    /**
     * Returns the square cap-clearance radius measured in horizontal blocks.
     */
    protected abstract int getCapRadius();

    /**
     * Returns the first relative cap layer checked during clearance
     * validation.
     */
    protected abstract int getCapStartY(int height);

    /**
     * Places this mushroom's cap using the supplied stem height and dimension
     * coordinates. Implementations choose the cap geometry and metadata, while
     * context availability and replacement rules still govern individual
     * writes.
     */
    protected abstract void placeCap(WorldFeatureContext context, int x, int y, int z, int height);
}
