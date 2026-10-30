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
 * {@code BlockMossBlockBaseComponentImpl} implements bone-meal moss conversion and
 * configurable weighted vegetation. An interaction requires air above the clicked moss,
 * consumes bone meal, and samples a patch whose spread type is this component's block type.
 *
 * <p>
 * Patch generation samples horizontal radii, omits corners, and probabilistically includes
 * edges before finding the highest exposed nonwater surface near the origin height.
 * Replaceable surfaces become moss, and existing matching moss remains eligible for
 * vegetation. The component retains the caller's vegetation list but caches its total weight
 * at construction, so later list mutation can desynchronize selection. Tall plants write
 * separate halves and fall back to short grass when the upper position is occupied.
 *
 * @author daoge_cmd
 */
public class BlockMossBlockBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code VERTICAL_RANGE} bounds the exposed-surface search above and below the origin
     * height for each sampled patch column.
     */
    private static final int VERTICAL_RANGE = 5;
    /**
     * {@code EXTRA_EDGE_COLUMN_CHANCE} controls inclusion of noncorner edge columns after the
     * rectangular patch radii are sampled independently.
     */
    private static final float EXTRA_EDGE_COLUMN_CHANCE = 0.75f;
    /**
     * {@code VEGETATION_CHANCE} controls whether each converted or already-matching moss
     * surface receives a weighted vegetation placement attempt.
     */
    private static final float VEGETATION_CHANCE = 0.6f;

    /**
     * {@code vegetationEntries} retains the caller's weighted choices without copying. Callers
     * must keep the list and weights stable because {@code totalWeight} is computed only
     * during construction.
     */
    private final List<VegetationEntry> vegetationEntries;
    /**
     * {@code totalWeight} caches the constructor-time weight sum used as the random bound.
     * Vegetation selection requires this value to be positive and consistent with {@code
     * vegetationEntries}.
     */
    private final int totalWeight;

    /**
     * Creates moss behavior with caller-supplied weighted vegetation choices. The list remains
     * shared, and its total weight is cached immediately; callers must provide a stable list
     * with a positive usable weight sum.
     */
    public BlockMossBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, List<VegetationEntry> vegetationEntries) {
        super(blockType);
        this.vegetationEntries = vegetationEntries;
        this.totalWeight = vegetationEntries.stream().mapToInt(VegetationEntry::weight).sum();
    }

    /**
     * Handles bone meal after inherited interaction handling when air is present above the
     * clicked moss. The callback consumes the item and emits particles before patch
     * generation, so it reports success even when no surface is converted.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();

        if (dimension.getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(pos), SimpleParticle.BONE_MEAL);

        spreadMoss(dimension, pos);
        return true;
    }

    /**
     * Samples a moss patch and attempts vegetation on qualifying surfaces. Independent radii
     * of two or three define a rectangle with omitted corners; noncorner edge columns pass an
     * additional inclusion roll before their highest exposed surface is found.
     *
     * <p>
     * Replaceable surfaces receive this moss type, while existing matching moss is retained
     * for vegetation eligibility. The collected positions then receive independent vegetation
     * rolls. World-write results are not checked, so a conversion call can still lead to a
     * vegetation attempt even when its dimension write did not take effect.
     */
    private void spreadMoss(Dimension dimension, Vector3ic origin) {
        var random = ThreadLocalRandom.current();
        int xRadius = random.nextInt(2, 4);
        int zRadius = random.nextInt(2, 4);

        var convertedPositions = new ArrayList<Vector3i>();

        for (int dx = -xRadius; dx <= xRadius; dx++) {
            for (int dz = -zRadius; dz <= zRadius; dz++) {
                if (Math.abs(dx) == xRadius && Math.abs(dz) == zRadius) {
                    continue;
                }

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
                    convertedPositions.add(surfacePos);
                }
            }
        }

        for (var pos : convertedPositions) {
            if (random.nextFloat() < VEGETATION_CHANCE) {
                placeVegetation(dimension, pos.x(), pos.y() + 1, pos.z(), random);
            }
        }
    }

    /**
     * Returns the highest nonair, nonwater surface with air above within the vertical search
     * range. {@code null} means no exposed surface was found; solidity and moss-replaceable
     * tagging are not requirements of this search.
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
     * Selects one weighted vegetation entry for an air position. The cached positive total
     * supplies the random bound, and the first cumulative weight exceeding the roll wins; tall
     * selections delegate upper-space handling to {@code placeTallPlant}.
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

    /**
     * Writes lower and upper plant states when the position above is air. The caller
     * establishes the lower position's availability; an occupied upper position selects short
     * grass instead, and neither path verifies the dimension's write result.
     */
    private void placeTallPlant(Dimension dimension, int x, int y, int z, BlockType<?> type) {
        if (dimension.getBlockState(x, y + 1, z).getBlockType() == BlockTypes.AIR) {
            var defaultState = type.getDefaultState();
            dimension.setBlockState(x, y, z, defaultState.setPropertyValue(UPPER_BLOCK_BIT, false));
            dimension.setBlockState(x, y + 1, z, defaultState.setPropertyValue(UPPER_BLOCK_BIT, true));
        } else {
            // Preserve a vegetation result when the selected tall plant cannot fit;
            // short grass uses only the lower position already validated by the caller.
            dimension.setBlockState(x, y, z, BlockTypes.SHORT_GRASS.getDefaultState());
        }
    }

    /**
     * {@code VegetationEntry} represents a weighted plant choice for a moss patch.
     * @param weight relative selection weight
     * @param blockType deferred vegetation supplier
     * @param tallPlant two-block placement flag
     */
    public record VegetationEntry(int weight, Supplier<BlockType<?>> blockType, boolean tallPlant) {}
}
