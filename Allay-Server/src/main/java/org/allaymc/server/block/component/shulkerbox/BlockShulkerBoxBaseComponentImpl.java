package org.allaymc.server.block.component.shulkerbox;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.common.BlockEntityHolderComponent;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.common.ItemItemStorableComponent;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3ic;

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
    public ItemStack[] getDrops(BlockStateWithPos blockState, ItemStack usedItem) {
        return new ItemStack[]{createShulkerBoxDrop(blockState)};
    }

    @Override
    public ItemStack getSilkTouchDrop(BlockStateWithPos blockState) {
        return createShulkerBoxDrop(blockState);
    }

    protected ItemStack createShulkerBoxDrop(BlockStateWithPos blockState) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(blockState.pos());
        var container = blockEntity.getContainer();
        var containerItems = container.saveNBT();
        var drop = blockState.blockState().toItemStack();
        ((ItemItemStorableComponent)drop).setStoredItems(containerItems);
        return drop;
    }

    @Override
    public boolean isDroppable(BlockStateWithPos blockState, ItemStack usedItem, EntityPlayer player) {
        // 2024/6/21 NOTICE
        // BDS导出的数据不太对，按理说潜影盒可以被空手挖取，但是导出的数据显示不可以
        // 考虑到潜影盒本身特殊的掉落逻辑（如果有内容物，创造模式下挖取也会掉落），猜测是bds内部的bug，故这边直接覆写了isDroppable()
        // 并不使用BDS导出的数据
        var blockEntity = blockEntityHolderComponent.getBlockEntity(blockState.pos());
        var container = blockEntity.getContainer();
        if (!container.isEmpty()) return true;
        return player.getGameType() != GameType.CREATIVE;
    }
}
