package org.allaymc.server.block.component.plant;

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
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.FortuneDropHelper;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_4;

/**
 * {@code BlockNetherWartBaseComponentImpl} implements soul-sand support, random age growth,
 * and Nether-wart harvest drops. Player placement requires the upper face of soul sand, while
 * placement without interaction information delegates directly. Growth advances through
 * cancellable one-stage proposals, and mature harvests use a Fortune-adjusted random stack.
 *
 * @author daoge_cmd
 */
public class BlockNetherWartBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the nether wart behavior component for the supplied block type.
     */
    public BlockNetherWartBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places Nether wart after validating an upper-face soul-sand interaction when placement
     * information exists. A {@code null} {@code placementInfo} bypasses those local checks and
     * delegates directly to inherited placement.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            if (placementInfo.blockFace() != BlockFace.UP) {
                return false;
            }
            if (placementInfo.getClickedBlock().getBlockType() != BlockTypes.SOUL_SAND) {
                return false;
            }
        }
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the inherited neighbor hook and breaks Nether wart when lower support changes
     * away from soul sand. Other faces leave this local support test untouched during the
     * callback.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && neighbor.getBlockType() != BlockTypes.SOUL_SAND) {
            block.breakBlock();
        }
    }

    /**
     * Returns whether Nether wart receives random updates for age progression.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Gives immature Nether wart a one-in-ten attempt to advance one age after inherited
     * random-update handling. The growth event can cancel that proposal or replace its state
     * before the local world write.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var age = block.getPropertyValue(AGE_4);
        if (age >= AGE_4.getMax()) {
            return;
        }

        // Random updates attempt age progression independently of light and bone-meal interaction.
        if (ThreadLocalRandom.current().nextInt(10) != 0) {
            return;
        }

        var newState = block.getBlockState().setPropertyValue(AGE_4, age + 1);
        var event = new BlockGrowEvent(block, newState);
        if (event.call()) {
            block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
        }
    }

    /**
     * Returns one wart from an immature crop and a Fortune-adjusted random stack from a mature
     * crop. Mature counts range from two through four without Fortune, with the adjusted upper
     * bound capped at seven.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var age = block.getPropertyValue(AGE_4);
        if (age < AGE_4.getMax()) {
            return Set.of(ItemTypes.NETHER_WART.createItemStack(1));
        }
        var count = FortuneDropHelper.discrete(usedItem, 2, 4, 7);
        return Set.of(ItemTypes.NETHER_WART.createItemStack(count));
    }
}
