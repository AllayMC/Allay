package org.allaymc.server.block.component.container;

import org.allaymc.server.block.component.BlockBaseComponentImpl;
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
import org.allaymc.server.component.annotation.Dependency;

import java.util.HashMap;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public class BlockShulkerBoxBaseComponentImpl extends BlockBaseComponentImpl {

    @Dependency
    BlockBlockEntityHolderComponent<BlockEntityShulkerBox> blockEntityHolderComponent;

    public BlockShulkerBoxBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(createShulkerBoxDrop(block));
    }

    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return createShulkerBoxDrop(block);
    }

    protected ItemStack createShulkerBoxDrop(Block block) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        var container = blockEntity.getContainer();
        var drop = block.toItemStack();
        var itemStacks = container.getItemStackArray();
        var items = new HashMap<Integer, ItemStack>();
        for (int slot = 0; slot < itemStacks.length; slot++) {
            if (!itemStacks[slot].isEmptyOrAir()) {
                items.put(slot, itemStacks[slot]);
            }
        }
        ((ItemShulkerBoxBaseComponent) drop).setStoredItems(items);
        return drop;
    }

    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        // 2024/6/21 NOTICE
        // The data exported by BDS is not quite correct. In theory, shulker boxes can be mined with bare hands, but the exported data shows they cannot.
        // Considering the special drop logic of shulker boxes (if they contain items, they will still drop when mined in creative mode), it is suspected to be an internal bug of BDS. Therefore, isDroppable() is overridden here.
        // The data exported by BDS is not used.
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        var container = blockEntity.getContainer();
        if (!container.isEmpty()) return true;
        return !(entity instanceof EntityPlayer player) || player.getGameMode() != GameMode.CREATIVE;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        var shulkerBox = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (shulkerBox == null) {
            return 0;
        }
        return shulkerBox.getContainer().calculateComparatorSignal();
    }
}
