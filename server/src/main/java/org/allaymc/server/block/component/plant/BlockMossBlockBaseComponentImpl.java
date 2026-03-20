package org.allaymc.server.block.component.plant;

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
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPPER_BLOCK_BIT;

/**
 * Block component for moss block and pale moss block.
 * Handles bone meal interaction to spread moss and grow vegetation.
 * <p>
 * The spread target block type is determined by {@code this.blockType},
 * while the vegetation distribution is configurable via constructor.
 *
 * @author daoge_cmd
 */
public class BlockMossBlockBaseComponentImpl extends BlockBaseComponentImpl {

    private static final int VERTICAL_RANGE = 5;
    private static final float EXTRA_EDGE_COLUMN_CHANCE = 0.75f;
    private static final float VEGETATION_CHANCE = 0.6f;

    private final List<VegetationEntry> vegetationEntries;
    private final int totalWeight;

    public BlockMossBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, List<VegetationEntry> vegetationEntries) {
        super(blockType);
        this.vegetationEntries = vegetationEntries;
        this.totalWeight = vegetationEntries.stream().mapToInt(VegetationEntry::weight).sum();
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
        int xRadius = random.nextInt(2, 4);
        int zRadius = random.nextInt(2, 4);

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
                    dimension.setBlockState(surfacePos.x(), surfacePos.y(), surfacePos.z(), blockType.getDefaultState());
                    convertedPositions.add(surfacePos);
                } else if (surfaceType == blockType) {
                    // Already the same moss type, still eligible for vegetation
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
        for (int dy = VERTICAL_RANGE; dy >= -VERTICAL_RANGE; dy--) {
            int y = originY + dy;
            var blockType = dimension.getBlockState(x, y, z).getBlockType();
            var aboveType = dimension.getBlockState(x, y + 1, z).getBlockType();

            if (blockType != BlockTypes.AIR &&
                !blockType.hasBlockTag(BlockTags.WATER) &&
                aboveType == BlockTypes.AIR) {
                return new Vector3i(x, y, z);
            }
        }
        return null;
    }

    /**
     * Place random vegetation at the given position using the weighted
     * vegetation entries configured for this moss block type.
     */
    private void placeVegetation(Dimension dimension, int x, int y, int z, ThreadLocalRandom random) {
        if (dimension.getBlockState(x, y, z).getBlockType() != BlockTypes.AIR) {
            return;
        }

        int roll = random.nextInt(totalWeight);
        int cumulative = 0;
        for (var entry : vegetationEntries) {
            cumulative += entry.weight();
            if (roll < cumulative) {
                var type = entry.blockType().get();
                if (entry.tallPlant()) {
                    placeTallPlant(dimension, x, y, z, type);
                } else {
                    dimension.setBlockState(x, y, z, type.getDefaultState());
                }
                return;
            }
        }
    }

    private void placeTallPlant(Dimension dimension, int x, int y, int z, BlockType<?> type) {
        if (dimension.getBlockState(x, y + 1, z).getBlockType() == BlockTypes.AIR) {
            var defaultState = type.getDefaultState();
            dimension.setBlockState(x, y, z, defaultState.setPropertyValue(UPPER_BLOCK_BIT, false));
            dimension.setBlockState(x, y + 1, z, defaultState.setPropertyValue(UPPER_BLOCK_BIT, true));
        } else {
            // Fallback to short grass if not enough space
            dimension.setBlockState(x, y, z, BlockTypes.SHORT_GRASS.getDefaultState());
        }
    }

    public record VegetationEntry(int weight, Supplier<BlockType<?>> blockType, boolean tallPlant) {}
}
