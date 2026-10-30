package org.allaymc.server.block.component.bamboo;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.BambooLeafSize;
import org.allaymc.api.block.property.enums.BambooStalkThickness;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.BAMBOO_LEAF_SIZE;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.BAMBOO_STALK_THICKNESS;

/**
 * {@code BlockBambooSaplingBaseComponentImpl} implements the ground-level stage of a bamboo
 * column. Placement requires approved support and an empty liquid layer, while player
 * placement also requires the upper face. Random updates and bone meal propose a first bamboo
 * segment above the sapling, and an upper bamboo neighbor converts the sapling into a thin,
 * leafless stalk.
 *
 * @author daoge_cmd
 */
public class BlockBambooSaplingBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the bamboo sapling behavior component for the supplied block type.
     */
    public BlockBambooSaplingBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a bamboo sapling on valid dry support. A nonnull {@code placementInfo} must
     * identify the upper face; both player and programmatic placement reject a position that
     * already contains liquid.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }
        if (!canSupportBamboo(dimension, placeBlockPos)) {
            return false;
        }
        // Don't place in liquid
        var liquid = dimension.getLiquid(placeBlockPos).right();
        if (liquid != null) {
            return false;
        }
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the base neighbor hook, removes an unsupported sapling, and converts a
     * sapling below bamboo into a stalk. The conversion writes thin, leafless bamboo at the
     * sapling's current position without a growth event.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN) {
            if (!canSupportBamboo(block.getDimension(), block.getPosition())) {
                block.breakBlock();
                return;
            }
        }

        // If block above is bamboo, convert self to bamboo
        if (face == BlockFace.UP && neighbor.getBlockType() == BlockTypes.BAMBOO) {
            var bambooState = BlockTypes.BAMBOO.getDefaultState()
                    .setPropertyValue(BAMBOO_STALK_THICKNESS, BambooStalkThickness.THIN)
                    .setPropertyValue(BAMBOO_LEAF_SIZE, BambooLeafSize.NO_LEAVES);
            block.getDimension().setBlockState(block.getPosition(), bambooState);
        }
    }

    /**
     * Returns whether saplings receive random updates for their first stalk.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Attempts the sapling's first upper bamboo segment after inherited random-update
     * handling. A one-third random gate and internal light of at least nine above the sapling
     * must pass before the cancellable growth attempt begins.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        // Natural sapling growth is throttled separately from bone-meal growth.
        if (ThreadLocalRandom.current().nextInt(3) != 0) {
            return;
        }

        // The first stalk needs light above the sapling.
        if (block.getDimension().getLightEngine().getInternalLight(block.getPosition().add(0, 1, 0, new org.joml.Vector3i())) < 9) {
            return;
        }

        growBambooAbove(block);
    }

    /**
     * Attempts bone-meal growth after inherited interaction handling rejects the interaction.
     * Only bone meal proceeds, and an accepted upper-segment growth consumes the player's held
     * item and emits particles at the clicked sapling.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var clickedBlock = interactInfo.getClickedBlock();
        if (growBambooAbove(clickedBlock)) {
            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    /**
     * Returns one bamboo item when the sapling block is broken.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.BAMBOO.createItemStack(1));
    }

    /**
     * Attempts thin bamboo with small leaves in the air block above the sapling. A cancelled
     * growth event returns {@code false}; acceptance writes the event's replacement state
     * above and reports success without inspecting the dimension's write result.
     */
    protected boolean growBambooAbove(Block block) {
        var abovePos = block.getPosition().add(0, 1, 0, new org.joml.Vector3i());
        if (block.getDimension().getBlockState(abovePos).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        var bambooState = BlockTypes.BAMBOO.getDefaultState()
                .setPropertyValue(BAMBOO_LEAF_SIZE, BambooLeafSize.SMALL_LEAVES)
                .setPropertyValue(BAMBOO_STALK_THICKNESS, BambooStalkThickness.THIN);

        var event = new BlockGrowEvent(block, bambooState);
        if (event.call()) {
            block.getDimension().setBlockState(abovePos, event.getNewBlockState());
            return true;
        }
        return false;
    }

    /**
     * Returns whether the block below {@code pos} supports bamboo or its sapling. Dirt and
     * sand tags qualify along with bamboo, bamboo saplings, moss, podzol, and gravel; this
     * predicate does not inspect liquid at the placement position.
     */
    public static boolean canSupportBamboo(Dimension dimension, Vector3ic pos) {
        var downBlockType = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        return downBlockType.hasBlockTag(BlockTags.DIRT) ||
               downBlockType.hasBlockTag(BlockTags.SAND) ||
               downBlockType == BlockTypes.BAMBOO ||
               downBlockType == BlockTypes.BAMBOO_SAPLING ||
               downBlockType == BlockTypes.MOSS_BLOCK ||
               downBlockType == BlockTypes.PODZOL ||
               downBlockType == BlockTypes.GRAVEL;
    }
}
