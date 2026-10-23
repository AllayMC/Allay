package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.item.data.ItemId;
import org.joml.Vector3ic;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH;

/**
 * {@code BlockStemBaseComponentImpl} implements fruit-bearing crop stems with deferred fruit
 * and seed types. Stem placement keeps the supplied facing state instead of orienting it
 * toward the player, and lower support changes require farmland. Seed drops come from the
 * configured supplier.
 *
 * <p>
 * Natural growth shares the crop soil probability but adds stem maturity and fruit adjacency.
 * An immature stem proposes its next stage; a mature stem checks for existing neighboring
 * fruit before choosing one horizontal air target with valid soil. Accepted fruit growth turns
 * the stem toward that target before writing the fruit, and later neighbor changes reset a
 * direction whose connected fruit disappears.
 *
 * @author daoge_cmd
 */
public class BlockStemBaseComponentImpl extends BlockCropsBaseComponentImpl {
    /**
     * {@code fruitType} supplies the fruit block type when growth or connection checks need
     * it. The constructor retains the supplier and resolves it lazily rather than caching its
     * result.
     */
    protected final Supplier<BlockType<?>> fruitType;
    /**
     * {@code seedsType} supplies the seed item type during drop calculation. The constructor
     * retains the supplier, so each drop lookup resolves its current result.
     */
    protected final Supplier<ItemType<?>> seedsType;

    /**
     * Creates a stem with deferred fruit and seed type suppliers. The suppliers are retained
     * without evaluation or {@code null} checks; callbacks require their results to provide usable
     * block and item types.
     */
    public BlockStemBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<BlockType<?>> fruitTypeSupplier, Supplier<ItemType<?>> seedsTypeSupplier) {
        super(blockType);
        this.fruitType = fruitTypeSupplier;
        this.seedsType = seedsTypeSupplier;
    }

    /**
     * Creates a stem whose fruit and seeds resolve through the supplied identifiers.
     * Identifier-backed method references defer registry access until growth, neighbor checks,
     * or drops request those types.
     */
    public BlockStemBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId fruitId, ItemId seedsId) {
        this(blockType, fruitId::getBlockType, seedsId::getItemType);
    }

    /**
     * Returns the fruit block type resolved by the configured supplier.
     */
    protected BlockType<?> getFruitType() {
        return fruitType.get();
    }

    /**
     * Returns the seed item type resolved by the configured supplier.
     */
    protected ItemType<?> getSeedsType() {
        return seedsType.get();
    }

    /**
     * Breaks a stem after loss of lower farmland and otherwise verifies its encoded fruit
     * connection. Vertical facing values mean no connected fruit; a missing fruit in a
     * horizontal facing direction resets the stem to {@code DOWN}.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        if (face == BlockFace.DOWN && neighbor.getBlockType() != BlockTypes.FARMLAND) {
            block.breakBlock();
            return;
        }

        var stemFace = Objects.requireNonNull(BlockFace.fromIndex(block.getPropertyValue(FACING_DIRECTION)));
        if (stemFace == BlockFace.DOWN || stemFace == BlockFace.UP) {
            return;
        }

        var fruitBlock = block.offsetPos(stemFace);
        if (fruitBlock.getBlockType() != getFruitType()) {
            block.getDimension().setBlockState(block.getPosition(), block.getBlockState().setPropertyValue(FACING_DIRECTION, BlockFace.DOWN.ordinal()));
        }
    }

    /**
     * Attempts stem or fruit growth when the soil probability passes and internal light is at
     * least eight. Immature stems dispatch a one-stage growth proposal and then end the
     * update. Mature stems first reject growth when any adjacent horizontal fruit already
     * exists.
     *
     * <p>
     * The mature path samples one horizontal destination, requires air there and farmland,
     * dirt, or grass below it, then dispatches a fruit-growth event. Acceptance writes the
     * stem's facing before the event-selected fruit state. The writes are separate, and this
     * callback does not roll back a changed direction if fruit placement fails.
     */
    @Override
    public void onRandomUpdate(Block block) {
        if (ThreadLocalRandom.current().nextFloat() <= calculateGrowthChance(block) &&
            block.getDimension().getLightEngine().getInternalLight(block.getPosition()) >= 8) {
            var growth = block.getPropertyValue(GROWTH);
            if (growth < GROWTH.getMax()) {
                var newCrop = block.setPropertyValue(GROWTH, growth + 1);
                var event = new BlockGrowEvent(block, newCrop.getBlockState());
                if (event.call()) {
                    block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
                }
                return;
            }

            for (var face : BlockFace.getHorizontalBlockFaces()) {
                if (block.offsetPos(face).getBlockType() == getFruitType()) {
                    return;
                }
            }

            var face = BlockFace.getHorizontalBlockFaces()[ThreadLocalRandom.current().nextInt(4)];
            var fruitBlock = block.offsetPos(face);
            if (fruitBlock.getBlockType() == BlockTypes.AIR) {
                var downBlockType = fruitBlock.offsetPos(BlockFace.DOWN).getBlockType();
                if (downBlockType != BlockTypes.FARMLAND &&
                    downBlockType != BlockTypes.DIRT &&
                    downBlockType != BlockTypes.GRASS_BLOCK) {
                    return;
                }

                var event = new BlockGrowEvent(
                        new Block(BlockTypes.AIR.getDefaultState(), new Position3i(fruitBlock.getPosition(), block.getDimension())),
                        getFruitType().getDefaultState()
                );
                if (event.call()) {
                    // Record the fruit connection before writing the destination so later
                    // neighbor checks can interpret the stem direction; neither write has rollback.
                    block.getDimension().setBlockState(block.getPosition(), block.getBlockState().setPropertyValue(FACING_DIRECTION, face.ordinal()));
                    block.getDimension().setBlockState(fruitBlock.getPosition(), event.getNewBlockState());
                }
            }
        }
    }

    /**
     * Places the supplied stem state on farmland through an upper-face interaction. {@code
     * placementInfo} is required, and the write deliberately bypasses normal property
     * processors so a new unconnected stem retains its supplied facing value.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo.blockFace() != BlockFace.UP ||
            placementInfo.getClickedBlock().getBlockType() != BlockTypes.FARMLAND) {
            return false;
        }

        // Skip directional property processing so an unconnected stem keeps its downward
        // facing marker instead of acquiring a misleading connection toward the placing player.
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState, placementInfo
        );
    }

    /**
     * Returns one seed item resolved by this stem's configured supplier.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(getSeedsType().createItemStack(1));
    }
}
