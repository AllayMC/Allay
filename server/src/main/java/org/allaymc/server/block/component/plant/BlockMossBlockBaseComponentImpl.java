package org.allaymc.server.block.component.plant;

import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPPER_BLOCK_BIT;

/**
 * Block component for moss block.
 * Handles bone meal interaction to spread moss and grow vegetation.
 *
 * @author daoge_cmd
 */
public class BlockMossBlockBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Maximum vertical scan range to find a surface block.
     */
    private static final int VERTICAL_RANGE = 5;

    /**
     * Chance for edge column to be included in moss patch.
     */
    private static final float EXTRA_EDGE_COLUMN_CHANCE = 0.75f;

    /**
     * Chance for vegetation to be placed on a converted block.
     */
    private static final float VEGETATION_CHANCE = 0.6f;

    public BlockMossBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();

        // Bonemeal only works if the block above is air
        if (dimension.getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(pos), SimpleParticle.BONE_MEAL);

        spreadMoss(dimension, pos);
        return true;
    }

    /**
     * Spread moss to nearby blocks and grow vegetation, mimicking the
     * vanilla MOSS_PATCH_BONEMEAL feature.
     */
    private void spreadMoss(Dimension dimension, Vector3ic origin) {
        var random = ThreadLocalRandom.current();
        // X and Z radii chosen independently, 50% chance of 2 or 3 each,
        // resulting in a cornerless 7×11×7 maximum volume
        int xRadius = random.nextInt(2, 4);
        int zRadius = random.nextInt(2, 4);

        // Collect positions that were successfully converted to moss
        var convertedPositions = new ArrayList<Vector3i>();

        for (int dx = -xRadius; dx <= xRadius; dx++) {
            for (int dz = -zRadius; dz <= zRadius; dz++) {
                // Skip corner blocks
                if (Math.abs(dx) == xRadius && Math.abs(dz) == zRadius) {
                    continue;
                }

                // Edge blocks have a 75% chance to be included
                if ((Math.abs(dx) == xRadius || Math.abs(dz) == zRadius) &&
                    random.nextFloat() > EXTRA_EDGE_COLUMN_CHANCE) {
                    continue;
                }

                var surfacePos = findSurface(dimension, origin.x() + dx, origin.y(), origin.z() + dz);
                if (surfacePos == null) {
                    continue;
                }

                var surfaceType = dimension.getBlockState(surfacePos.x(), surfacePos.y(), surfacePos.z()).getBlockType();

                if (surfaceType.hasBlockTag(BlockTags.MOSS_REPLACEABLE)) {
                    dimension.setBlockState(surfacePos.x(), surfacePos.y(), surfacePos.z(), BlockTypes.MOSS_BLOCK.getDefaultState());
                    convertedPositions.add(surfacePos);
                } else if (surfaceType == BlockTypes.MOSS_BLOCK) {
                    // Already moss, still eligible for vegetation
                    convertedPositions.add(surfacePos);
                }
            }
        }

        // Place vegetation on converted positions
        for (var pos : convertedPositions) {
            if (random.nextFloat() < VEGETATION_CHANCE) {
                placeVegetation(dimension, pos.x(), pos.y() + 1, pos.z(), random);
            }
        }
    }

    /**
     * Find the surface block at the given (x, z) column near the origin Y.
     * Scans vertically to find a solid block with air above.
     *
     * @return the position of the surface block, or null if none found
     */
    private Vector3i findSurface(Dimension dimension, int x, int originY, int z) {
        // Scan from top to bottom within vertical range
        for (int dy = VERTICAL_RANGE; dy >= -VERTICAL_RANGE; dy--) {
            int y = originY + dy;
            var blockType = dimension.getBlockState(x, y, z).getBlockType();
            var aboveType = dimension.getBlockState(x, y + 1, z).getBlockType();

            // Surface is a solid block with air/passable block above
            if (blockType != BlockTypes.AIR &&
                !blockType.hasBlockTag(BlockTags.WATER) &&
                aboveType == BlockTypes.AIR) {
                return new Vector3i(x, y, z);
            }
        }
        return null;
    }

    /**
     * Place random vegetation at the given position.
     * Weighted distribution matching vanilla:
     * - Short grass: 50/96 (~52%)
     * - Moss carpet: 25/96 (~26%)
     * - Tall grass: 10/96 (~10.4%)
     * - Azalea: 7/96 (~7.3%)
     * - Flowering azalea: 4/96 (~4.2%)
     */
    private void placeVegetation(Dimension dimension, int x, int y, int z, ThreadLocalRandom random) {
        // Check if space is available
        if (dimension.getBlockState(x, y, z).getBlockType() != BlockTypes.AIR) {
            return;
        }

        int roll = random.nextInt(96);
        if (roll < 50) {
            // Short grass (weight 50)
            dimension.setBlockState(x, y, z, BlockTypes.SHORT_GRASS.getDefaultState());
        } else if (roll < 75) {
            // Moss carpet (weight 25)
            dimension.setBlockState(x, y, z, BlockTypes.MOSS_CARPET.getDefaultState());
        } else if (roll < 85) {
            // Tall grass (weight 10) - double block plant
            if (dimension.getBlockState(x, y + 1, z).getBlockType() == BlockTypes.AIR) {
                var defaultState = BlockTypes.TALL_GRASS.getDefaultState();
                dimension.setBlockState(x, y, z, defaultState.setPropertyValue(UPPER_BLOCK_BIT, false));
                dimension.setBlockState(x, y + 1, z, defaultState.setPropertyValue(UPPER_BLOCK_BIT, true));
            } else {
                // Fallback to short grass if not enough space
                dimension.setBlockState(x, y, z, BlockTypes.SHORT_GRASS.getDefaultState());
            }
        } else if (roll < 92) {
            // Azalea (weight 7)
            dimension.setBlockState(x, y, z, BlockTypes.AZALEA.getDefaultState());
        } else {
            // Flowering azalea (weight 4)
            dimension.setBlockState(x, y, z, BlockTypes.FLOWERING_AZALEA.getDefaultState());
        }
    }
}
