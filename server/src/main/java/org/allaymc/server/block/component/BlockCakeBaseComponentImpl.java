package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemCandleStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;

import java.util.Set;

/**
 * Base component for cake block behavior.
 * <p>
 * Cake can be eaten in 7 bites (bite_counter 0-6), each bite restores 2 food and 0.4 saturation.
 * Candles can be placed on a full cake (bite_counter = 0) to create candle cake.
 *
 * @author daoge_cmd
 */
public class BlockCakeBaseComponentImpl extends BlockBaseComponentImpl {

    public static final int FOOD_POINTS_PER_SLICE = 2;
    public static final float SATURATION_PER_SLICE = 0.4f;
    public static final int MAX_BITES = 6;

    public BlockCakeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var player = interactInfo.player();
        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var bites = blockState.getPropertyValue(BlockPropertyTypes.BITE_COUNTER);

        // If player is holding a candle and cake is full, let the candle handle placement
        if (bites == 0 && itemStack instanceof ItemCandleStack) {
            return false;
        }

        // Check if player can eat
        if (!player.canEat()) {
            return false;
        }

        // Increment bite counter
        var newBites = bites + 1;

        if (newBites > MAX_BITES) {
            // Cake is fully eaten, break the block
            dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
        } else {
            // Update bite counter
            dimension.updateBlockProperty(BlockPropertyTypes.BITE_COUNTER, newBites, pos);
        }

        // Add food and saturation to player
        player.saturate(FOOD_POINTS_PER_SLICE, SATURATION_PER_SLICE);

        // Play eating sound
        dimension.addSound(MathUtils.center(pos), SimpleSound.BURP);

        return true;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        // Break cake if support block is removed
        if (face == BlockFace.DOWN) {
            var belowState = neighbor.getBlockState();
            if (!belowState.getBlockStateData().isSolid()) {
                block.breakBlock(null, null);
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        // Cake doesn't drop anything when broken
        return Set.of();
    }
}
