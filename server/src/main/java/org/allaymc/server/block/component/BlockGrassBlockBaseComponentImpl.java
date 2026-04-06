package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockSpreadEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class BlockGrassBlockBaseComponentImpl extends BlockBaseComponentImpl {

    public static final int MINIMUM_LIGHT_LEVEL_FOR_SPREADING = 9;
    private static final int BONEMEAL_ATTEMPTS = 128;
    private static final int BONEMEAL_HORIZONTAL_RANGE = 4;
    private static final int BONEMEAL_VERTICAL_RANGE = 2;
    private static final List<VegetationEntry> BONEMEAL_VEGETATION = List.of(
            new VegetationEntry(48, () -> BlockTypes.SHORT_GRASS, false),
            new VegetationEntry(24, () -> BlockTypes.FERN, false),
            new VegetationEntry(12, () -> BlockTypes.TALL_GRASS, true),
            new VegetationEntry(8, () -> BlockTypes.LARGE_FERN, true),
            new VegetationEntry(4, () -> BlockTypes.DANDELION, false),
            new VegetationEntry(4, () -> BlockTypes.POPPY, false)
    );
    private static final int BONEMEAL_VEGETATION_TOTAL_WEIGHT = BONEMEAL_VEGETATION.stream()
            .mapToInt(VegetationEntry::weight)
            .sum();

    public BlockGrassBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
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
        if (!dimension.isYInRange(pos.y() + 1)) {
            return false;
        }

        if (dimension.getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        if (!growVegetation(dimension, pos.x(), pos.y(), pos.z())) {
            return false;
        }

        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(pos), SimpleParticle.BONE_MEAL);
        return true;
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);
        var pos = block.getPosition();
        var dimension = block.getDimension();

        var upperBlockState = block.offsetPos(BlockFace.UP);
        // Grass dies and changes to dirt after a random time (when a random tick lands on the block)
        // if directly covered by any opaque block.
        // Transparent blocks can kill grass in a similar manner,
        // but only if they cause the light level above the grass block to be four or below (like water does),
        // and the surrounding area is not otherwise sufficiently lit up.
        if (upperBlockState.getBlockStateData().lightDampening() > 1) {
            var event = new BlockFadeEvent(block, BlockTypes.DIRT.getDefaultState());
            if (event.call()) {
                dimension.setBlockState(pos, event.getNewBlockState());
            }
            return;
        }

        // Grass can spread to nearby dirt blocks.
        // Grass spreading without player intervention depends heavily on the time of day.
        // For a dirt block to accept grass from a nearby grass block, the following requirements must be met:

        // The source block must have a light level of 9 or brighter directly above it.
        if (dimension.getLightEngine().getInternalLight(pos.x(), pos.y() + 1, pos.z()) >= MINIMUM_LIGHT_LEVEL_FOR_SPREADING) {
            // The dirt block receiving grass must be within a 3×5×3 range of the source block
            // where the source block is in the center of the second topmost layer of that range.
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int x = random.nextInt(pos.x() - 1, pos.x() + 1 + 1);
            int y = random.nextInt(pos.y() - 3, pos.y() + 1 + 1);
            int z = random.nextInt(pos.z() - 1, pos.z() + 1 + 1);
            var blockState = dimension.getBlockState(x, y, z);
            if (blockState.getBlockType() == BlockTypes.DIRT &&
                // The dirt block must have a light level of at least 4 above it.
                dimension.getLightEngine().getInternalLight(x, y + 1, z) >= 4 &&
                // Any block directly above the dirt block must not reduce light by 2 levels or more.
                dimension.getBlockState(x, y + 1, z).getBlockStateData().lightDampening() < 2) {
                var spreadBlock = new Block(BlockTypes.GRASS_BLOCK.getDefaultState(), new Position3i(x, y, z, dimension), 0);
                var event = new BlockSpreadEvent(block, spreadBlock);
                if (event.call()) {
                    dimension.setBlockState(x, y, z, event.getSpreadBlock().getBlockState());
                }
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.DIRT.createItemStack(1));
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    private boolean growVegetation(Dimension dimension, int originX, int originY, int originZ) {
        var random = ThreadLocalRandom.current();
        var grown = false;
        for (int i = 0; i < BONEMEAL_ATTEMPTS; i++) {
            int x = originX + random.nextInt(-BONEMEAL_HORIZONTAL_RANGE, BONEMEAL_HORIZONTAL_RANGE + 1);
            int z = originZ + random.nextInt(-BONEMEAL_HORIZONTAL_RANGE, BONEMEAL_HORIZONTAL_RANGE + 1);
            int y = findGrassSurfaceY(dimension, x, originY, z);
            if (y == Integer.MIN_VALUE) {
                continue;
            }

            grown |= placeVegetation(dimension, x, y + 1, z, random);
        }

        return grown;
    }

    private int findGrassSurfaceY(Dimension dimension, int x, int originY, int z) {
        for (int offsetY = BONEMEAL_VERTICAL_RANGE; offsetY >= -BONEMEAL_VERTICAL_RANGE; offsetY--) {
            int y = originY + offsetY;
            if (!dimension.isYInRange(y) || !dimension.isYInRange(y + 1)) {
                continue;
            }

            var surfaceType = dimension.getBlockState(x, y, z).getBlockType();
            var aboveType = dimension.getBlockState(x, y + 1, z).getBlockType();
            if (surfaceType == BlockTypes.GRASS_BLOCK && aboveType == BlockTypes.AIR) {
                return y;
            }
        }

        return Integer.MIN_VALUE;
    }

    private boolean placeVegetation(Dimension dimension, int x, int y, int z, ThreadLocalRandom random) {
        if (!dimension.isYInRange(y)) {
            return false;
        }

        if (dimension.getBlockState(x, y, z).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        int roll = random.nextInt(BONEMEAL_VEGETATION_TOTAL_WEIGHT);
        int cumulativeWeight = 0;
        for (var entry : BONEMEAL_VEGETATION) {
            cumulativeWeight += entry.weight();
            if (roll < cumulativeWeight) {
                var blockType = entry.blockType().get();
                if (entry.tallPlant()) {
                    if (!dimension.isYInRange(y + 1)) {
                        return false;
                    }

                    if (dimension.getBlockState(x, y + 1, z).getBlockType() != BlockTypes.AIR) {
                        return false;
                    }

                    var state = blockType.getDefaultState();
                    dimension.setBlockState(x, y, z, state.setPropertyValue(BlockPropertyTypes.UPPER_BLOCK_BIT, false));
                    dimension.setBlockState(x, y + 1, z, state.setPropertyValue(BlockPropertyTypes.UPPER_BLOCK_BIT, true));
                } else {
                    dimension.setBlockState(x, y, z, blockType.getDefaultState());
                }

                return true;
            }
        }

        return false;
    }

    private record VegetationEntry(int weight, Supplier<BlockType<?>> blockType, boolean tallPlant) {}
}
