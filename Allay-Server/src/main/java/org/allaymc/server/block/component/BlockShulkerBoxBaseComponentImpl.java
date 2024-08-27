package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemItemStorableComponent;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.protocol.bedrock.data.GameType;

import java.util.Set;

/**
 * Allay Project 2024/6/20
 *
 * @author daoge_cmd
 */
public class BlockShulkerBoxBaseComponentImpl extends BlockBaseComponentImpl {

    @Dependency
    BlockEntityHolderComponent<BlockEntityShulkerBox> blockEntityHolderComponent;

    public BlockShulkerBoxBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        return Set.of(createShulkerBoxDrop(blockState));
    }

    @Override
    public ItemStack getSilkTouchDrop(BlockStateWithPos blockState) {
        return createShulkerBoxDrop(blockState);
    }

    protected ItemStack createShulkerBoxDrop(BlockStateWithPos blockState) {
        var blockEntity = blockEntityHolderComponent.getBlockEntityAt(blockState.pos());
        var container = blockEntity.getContainer();
        var containerItems = container.saveNBT();
        var drop = blockState.blockState().toItemStack();
        ((ItemItemStorableComponent) drop).setStoredItems(containerItems);
        return drop;
    }

    @Override
    public boolean isDroppable(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        // 2024/6/21 NOTICE
        // The data exported by BDS is not quite correct. In theory, shulker boxes can be mined with bare hands, but the exported data shows they cannot.
        // Considering the special drop logic of shulker boxes (if they contain items, they will still drop when mined in creative mode), it is suspected to be an internal bug of BDS. Therefore, isDroppable() is overridden here.
        // The data exported by BDS is not used.
        var blockEntity = blockEntityHolderComponent.getBlockEntityAt(blockState.pos());
        var container = blockEntity.getContainer();
        if (!container.isEmpty()) return true;
        return !(entity instanceof EntityPlayer player) || player.getGameType() != GameType.CREATIVE;
    }
}
