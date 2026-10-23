package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemCandleStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.BITE_COUNTER;

/**
 * {@code BlockCakeBaseComponentImpl} implements cake eating, support loss, and
 * comparator output. {@code FOOD_POINTS_PER_SLICE} and {@code SATURATION_PER_SLICE}
 * define nourishment for each consumed slice, while {@code MAX_BITES} identifies the
 * last stored bite count before a further bite removes the cake.
 *
 * @author daoge_cmd
 */
public class BlockCakeBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Food points per slice.
     */
    public static final int FOOD_POINTS_PER_SLICE = 2;
    /**
     * Saturation points per slice.
     */
    public static final float SATURATION_PER_SLICE = 0.4f;
    /**
     * Last stored bite count.
     */
    public static final int MAX_BITES = 6;

    /**
     * Creates the cake block behavior for the supplied block type.
     */
    public BlockCakeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Handles one cake bite when player context is present and the player can eat. A
     * candle on an unbitten cake returns {@code false} so item placement can handle
     * the interaction. Eating attempts the bite-state write before notifying
     * comparators, nourishing the player, and playing its sound; a refused state
     * change still permits those later effects and a {@code true} result.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var player = interactInfo.player();
        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var bites = blockState.getPropertyValue(BlockPropertyTypes.BITE_COUNTER);
        // An unbitten cake can receive a candle. Leave this interaction unhandled so
        // the candle item can perform its placement behavior.
        if (bites == 0 && itemStack instanceof ItemCandleStack) {
            return false;
        }
        if (!player.canEat()) {
            return false;
        }
        var newBites = bites + 1;

        if (newBites > MAX_BITES) {
            dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
        } else {
            dimension.updateBlockProperty(BlockPropertyTypes.BITE_COUNTER, newBites, pos);
        }
        dimension.updateComparatorOutputLevel(pos);
        player.saturate(FOOD_POINTS_PER_SLICE, SATURATION_PER_SLICE);
        dimension.addSound(MathUtils.center(pos), SimpleSound.BURP);

        return true;
    }

    /**
     * Forwards the neighbor event and breaks the cake when its lower support becomes
     * nonsolid. Other neighbor faces leave support handling unchanged, and break
     * handling supplies the ordinary empty cake drops.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN) {
            var belowState = neighbor.getBlockState();
            if (!belowState.getBlockStateData().isSolid()) {
                block.breakBlock(null, null);
            }
        }
    }

    /**
     * Returns an empty ordinary drop set when cake is broken. Cake eating is handled
     * by the interaction hook, and breaking does not produce cake items through this
     * drop selection.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }

    /**
     * Returns whether cake supports comparator input regardless of remaining slices.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns two comparator power units per remaining cake slice. A full cake
     * produces {@code 14}, and the last stored bite count produces {@code 2} before
     * another bite removes the block.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        int bites = block.getPropertyValue(BITE_COUNTER);
        // Two power units per slice make an untouched seven-slice cake emit fourteen.
        return (MAX_BITES + 1 - bites) * 2;
    }
}
