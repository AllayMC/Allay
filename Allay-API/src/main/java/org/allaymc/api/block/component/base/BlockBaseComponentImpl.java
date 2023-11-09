package org.allaymc.api.block.component.base;

import org.allaymc.api.block.BlockBehavior;
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
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
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

    protected BlockType<? extends BlockBehavior> blockType;

    public BlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        this.blockType = blockType;
    }

    @Override
    public BlockType<? extends BlockBehavior> getBlockType() {
        return blockType;
    }

    @Override
    public void onNeighborUpdate(Vector3ic updated, Vector3ic neighbor, BlockFace face, World world) {
        manager.callEvent(new BlockOnNeighborUpdateEvent(updated, neighbor, face, world));
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos blockState) {
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos blockState) {
    }

    @Override
    public boolean place(@Nullable EntityPlayer player, @NotNull World world, @NotNull BlockState blockState, @NotNull Vector3ic targetBlockPos, @NotNull Vector3ic placeBlockPos, Vector3fc clickPos, @NotNull BlockFace blockFace) {
        world.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        return true;
    }

    @Override
    public void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState) {
        manager.callEvent(new BlockOnPlaceEvent(currentBlockState, newBlockState));
    }

    @Override
    public void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState) {
        manager.callEvent(new BlockOnReplaceEvent(currentBlockState, newBlockState));
    }

    @Override
    public boolean onInteract(@Nullable EntityPlayer player, ItemStack itemStack, World world, Vector3ic blockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        var event = new BlockOnInteractEvent(player, itemStack, world, blockPos, placeBlockPos, clickPos, blockFace, false);
        manager.callEvent(event);
        return event.success();
    }
}
