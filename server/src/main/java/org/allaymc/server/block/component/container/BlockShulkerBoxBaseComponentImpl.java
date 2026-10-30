package org.allaymc.server.block.component.container;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemShulkerBoxBaseComponent;
import org.allaymc.api.player.GameMode;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;

import java.util.HashMap;
import java.util.Set;

/**
 * {@code BlockShulkerBoxBaseComponentImpl} creates shulker-box item drops carrying
 * their stored container slots. Normal and silk-touch drops share the same packing
 * path, and occupied boxes remain droppable even for creative players. Empty boxes
 * are suppressed only for creative players, independently of the exported
 * correct-tool requirement.
 *
 * <p>
 * Packing records each nonempty stack under its original slot index so later
 * placement can restore the slot layout rather than compacting the inventory. The
 * new item receives a fresh map through {@code setStoredItems}, but that setter
 * retains the mutable item-stack values. The dropped item and original container
 * therefore share those stack references until the original container is
 * discarded.
 *
 * @author daoge_cmd
 */
public class BlockShulkerBoxBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code blockEntityHolderComponent} resolves stored slots for packed drops
     * and comparator readings. Component initialization injects the dependency
     * before these block callbacks run.
     */
    @Dependency
    BlockBlockEntityHolderComponent<BlockEntityShulkerBox> blockEntityHolderComponent;

    /**
     * Creates this container block component for the supplied registered block
     * type.
     */
    public BlockShulkerBoxBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns one packed shulker-box item containing the stored slot contents.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(createShulkerBoxDrop(block));
    }

    /**
     * Returns the same packed shulker-box representation used by ordinary drops.
     */
    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return createShulkerBoxDrop(block);
    }

    /**
     * Creates a shulker-box item preserving nonempty container slot indices. The
     * holder must resolve its container and the block item must implement
     * {@code ItemShulkerBoxBaseComponent}. A new map records the original slot
     * numbers and stack references, and the item setter copies the map while
     * retaining its stack values. This preserves layout across block-to-item
     * conversion without producing independent copies of the stored mutable items.
     */
    protected ItemStack createShulkerBoxDrop(Block block) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        var container = blockEntity.getContainer();
        var drop = block.toItemStack();
        var itemStacks = container.getItemStackArray();
        var items = new HashMap<Integer, ItemStack>();
        // Retain original slot numbers while omitting empty entries, so later
        // placement restores the same inventory layout instead of packing every
        // item toward slot zero.
        for (int slot = 0; slot < itemStacks.length; slot++) {
            if (!itemStacks[slot].isEmptyOrAir()) {
                items.put(slot, itemStacks[slot]);
            }
        }
        ((ItemShulkerBoxBaseComponent) drop).setStoredItems(items);
        return drop;
    }

    /**
     * Returns whether this box should produce its packed item drop. A nonempty
     * container always permits dropping; an empty container is suppressed only for
     * a creative player, bypassing the ordinary correct-tool check.
     */
    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        var container = blockEntity.getContainer();
        // Preserve occupied boxes even for creative breaks so their packed
        // contents remain recoverable; empty creative boxes can be removed without
        // producing an item.
        if (!container.isEmpty()) return true;
        return !(entity instanceof EntityPlayer player) || player.getGameMode() != GameMode.CREATIVE;
    }

    /**
     * Returns whether this container supplies its own comparator signal.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the shulker inventory's comparator fullness signal. A missing holder
     * result produces zero; otherwise the container performs its normal
     * calculation on the current contents. The container owns the fullness scaling
     * for this query.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        var shulkerBox = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (shulkerBox == null) {
            return 0;
        }
        return shulkerBox.getContainer().calculateComparatorSignal();
    }
}
