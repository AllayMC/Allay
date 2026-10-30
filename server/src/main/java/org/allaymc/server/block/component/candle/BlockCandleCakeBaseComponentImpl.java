package org.allaymc.server.block.component.candle;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
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
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.block.component.BlockCakeBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;

import java.util.Set;
import java.util.function.Supplier;

/**
 * {@code BlockCandleCakeBaseComponentImpl} handles candle-cake ignition,
 * extinguishing, eating, support, and comparator input. Eating replaces it with cake
 * after one bite and drops the configured candle item, while ordinary breaking
 * selects only the candle; a deferred supplier resolves that item type.
 *
 * @author daoge_cmd
 */
public class BlockCandleCakeBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code candleItemType} is retained for deferred item lookup. A supplier result
     * of {@code null} suppresses the candle drop during eating or breaking.
     */
    private final Supplier<ItemType<?>> candleItemType;

    /**
     * Creates candle-cake behavior retaining a deferred candle item supplier.
     * {@code candleItemTypeSupplier} is evaluated for eating and drop selection,
     * allowing item registration to finish after this component is constructed.
     */
    public BlockCandleCakeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<ItemType<?>> candleItemTypeSupplier) {
        super(blockType);
        this.candleItemType = candleItemTypeSupplier;
    }

    /**
     * Creates candle-cake behavior using a deferred lookup for its candle item
     * identifier. {@code candleItemId} resolves the item when eating or drop handling
     * needs the corresponding candle stack.
     */
    public BlockCandleCakeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId candleItemId) {
        this(blockType, candleItemId::getItemType);
    }

    /**
     * Handles ignition, extinguishing, or one cake bite when player context is
     * present. A {@code null} item can extinguish or eat; eating attempts cake
     * replacement before dropping the candle and nourishing the player without
     * checking write acceptance.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var player = interactInfo.player();
        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var isLit = blockState.getPropertyValue(BlockPropertyTypes.LIT);
        if (!isLit && itemStack != null && itemStack.getItemType() == ItemTypes.FLINT_AND_STEEL) {
            dimension.updateBlockProperty(BlockPropertyTypes.LIT, true, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.IGNITE);
            itemStack.tryIncreaseDamage(1);
            return true;
        }
        if (isLit && (itemStack == null || itemStack.getItemType() != ItemTypes.FLINT_AND_STEEL)) {
            dimension.updateBlockProperty(BlockPropertyTypes.LIT, false, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.FIRE_EXTINGUISH);
            return true;
        }
        if (!isLit && canPlayerEat(player)) {
            var newCakeState = BlockTypes.CAKE.getDefaultState()
                    .setPropertyValue(BlockPropertyTypes.BITE_COUNTER, 1);
            dimension.setBlockState(pos.x(), pos.y(), pos.z(), newCakeState, interactInfo);
            var candleItemType = getCandleItemType();
            if (candleItemType != null) {
                dimension.dropItem(candleItemType.createItemStack(), MathUtils.center(pos));
            }
            dimension.updateComparatorOutputLevel(pos);
            player.saturate(BlockCakeBaseComponentImpl.FOOD_POINTS_PER_SLICE, BlockCakeBaseComponentImpl.SATURATION_PER_SLICE);
            dimension.addSound(MathUtils.center(pos), SimpleSound.BURP);

            return true;
        }

        return false;
    }

    /**
     * Forwards the neighbor event and breaks candle cake when lower support becomes
     * nonsolid. The break is unattributed, using {@code null} tool and entity context
     * for the normal drop path.
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
     * Returns one configured candle item as the ordinary candle-cake drop. A supplier
     * result of {@code null} yields an empty set; the cake portion contributes no
     * ordinary item here.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var candleItemType = getCandleItemType();
        if (candleItemType != null) {
            return Set.of(candleItemType.createItemStack());
        }
        return Set.of();
    }

    /**
     * Returns whether the supplied player currently accepts food from cake.
     */
    protected boolean canPlayerEat(EntityPlayer player) {
        return player.canEat();
    }

    /**
     * Returns the candle item type selected by the retained supplier. A {@code null}
     * result is permitted by the drop paths and causes them to omit the candle item.
     */
    protected ItemType<?> getCandleItemType() {
        return candleItemType.get();
    }

    /**
     * Returns whether candle cake always supports specialized comparator input.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the full-cake comparator strength for this candle-cake state. Candle
     * cake retains a fixed {@code 14} signal because eating converts it to an
     * ordinary partially eaten cake.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        // The candle cake still represents an unbitten seven-slice cake.
        return 14;
    }
}
