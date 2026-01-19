package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.item.data.ItemId;

import java.util.Set;

/**
 * Base component for candle cake block behavior.
 * <p>
 * Candle cake is a cake with a single candle on top. It can:
 * - Be lit with flint and steel (lit property true/false)
 * - Be extinguished by clicking
 * - When eaten, converts back to regular cake and drops the candle
 *
 * @author daoge_cmd
 */
public class BlockCandleCakeBaseComponentImpl extends BlockBaseComponentImpl {

    private final ItemId candleItemId;

    public BlockCandleCakeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId candleItemId) {
        super(blockType);
        this.candleItemId = candleItemId;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var player = interactInfo.player();
        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var isLit = blockState.getPropertyValue(BlockPropertyTypes.LIT);

        // Light with flint and steel
        if (!isLit && itemStack != null && itemStack.getItemType() == ItemTypes.FLINT_AND_STEEL) {
            dimension.updateBlockProperty(BlockPropertyTypes.LIT, true, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.IGNITE);
            itemStack.tryIncreaseDamage(1);
            return true;
        }

        // Extinguish with empty hand or any non-flint-and-steel item when lit
        if (isLit && (itemStack == null || itemStack.getItemType() != ItemTypes.FLINT_AND_STEEL)) {
            dimension.updateBlockProperty(BlockPropertyTypes.LIT, false, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.FIRE_EXTINGUISH);
            return true;
        }

        // Try to eat the cake (when not lit or not using flint and steel)
        if (!isLit && canPlayerEat(player)) {
            // Convert to regular cake with 1 bite already taken
            var newCakeState = BlockTypes.CAKE.getDefaultState()
                    .setPropertyValue(BlockPropertyTypes.BITE_COUNTER, 1);
            dimension.setBlockState(pos.x(), pos.y(), pos.z(), newCakeState, interactInfo);

            // Drop the candle
            var candleItemType = getCandleItemType();
            if (candleItemType != null) {
                dimension.dropItem(candleItemType.createItemStack(), MathUtils.center(pos));
            }

            // Add food and saturation to player
            player.saturate(BlockCakeBaseComponentImpl.FOOD_POINTS_PER_SLICE, BlockCakeBaseComponentImpl.SATURATION_PER_SLICE);

            // Play eating sound
            dimension.addSound(MathUtils.center(pos), SimpleSound.BURP);

            return true;
        }

        return false;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        // Break candle cake if support block is removed
        if (face == BlockFace.DOWN) {
            var belowState = neighbor.getBlockState();
            if (!belowState.getBlockStateData().isSolid()) {
                block.breakBlock(null, null);
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        // Candle cake drops only the candle (not the cake)
        var candleItemType = getCandleItemType();
        if (candleItemType != null) {
            return Set.of(candleItemType.createItemStack());
        }
        return Set.of();
    }

    /**
     * Check if player can eat (not full hunger or creative mode).
     */
    protected boolean canPlayerEat(EntityPlayer player) {
        return player.canEat();
    }

    /**
     * Get the candle item type for this candle cake.
     */
    protected ItemType<?> getCandleItemType() {
        return candleItemId.getItemType();
    }
}
