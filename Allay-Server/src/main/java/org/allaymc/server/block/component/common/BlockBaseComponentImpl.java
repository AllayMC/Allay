package org.allaymc.server.block.component.common;

import lombok.Getter;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.common.BlockBaseComponent;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.component.event.BlockOnInteractEvent;
import org.allaymc.api.block.component.event.BlockOnNeighborUpdateEvent;
import org.allaymc.api.block.component.event.BlockOnPlaceEvent;
import org.allaymc.api.block.component.event.BlockOnReplaceEvent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3f;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public class BlockBaseComponentImpl implements BlockBaseComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_base_component");

    @Manager
    protected ComponentManager<?> manager;

    @Getter
    protected BlockType<? extends BlockBehavior> blockType;

    public BlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        this.blockType = blockType;
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        manager.callEvent(new BlockOnNeighborUpdateEvent(current, neighbor, face));
        if (!canKeepExisting(current, neighbor, face)) {
            current.pos().dimension().breakBlock(current.pos(), null, null);
        }
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos blockState) {}

    @Override
    public void onScheduledUpdate(BlockStateWithPos blockState) {}

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new BlockOnPlaceEvent(currentBlockState, newBlockState, placementInfo));
    }

    @Override
    public void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new BlockOnReplaceEvent(currentBlockState, newBlockState, placementInfo));
    }

    @Override
    public void onBreak(BlockStateWithPos blockState, ItemStack usedItem, EntityPlayer player) {
        if (!isDroppable(blockState, usedItem, player)) return;

        var dropPos = new Vector3f(blockState.pos()).add(0.5f, 0.5f, 0.5f);
        var dimension = blockState.pos().dimension();
        if (usedItem != null && usedItem.hasEnchantment(EnchantmentTypes.SILK_TOUCH)) {
            // Silk Touch, directly drop the block itself
            dimension.dropItem(getSilkTouchDrop(blockState), dropPos);
            return;
        }

        var drops = getDrops(blockState, usedItem);
        for (var drop : drops) {
            dimension.dropItem(drop, dropPos);
        }
    }

    @Override
    public boolean isDroppable(BlockStateWithPos blockState, ItemStack usedItem, EntityPlayer player) {
        if (player != null && player.getGameType() == GameType.CREATIVE) return false;
        return blockState.blockState().getBlockType().getMaterial().isAlwaysDestroyable() || (usedItem != null && usedItem.isCorrectToolFor(blockState.blockState()));
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var event = new BlockOnInteractEvent(itemStack, dimension, interactInfo, false);
        manager.callEvent(event);
        return event.isSuccess();
    }
}
