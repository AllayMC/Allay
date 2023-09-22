package cn.allay.api.block.component.base;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.event.BlockOnInteractEvent;
import cn.allay.api.block.component.event.BlockOnNeighborChangedEvent;
import cn.allay.api.block.component.event.BlockOnPlaceEvent;
import cn.allay.api.block.component.event.BlockOnReplaceEvent;
import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.block.function.*;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.annotation.Manager;
import cn.allay.api.component.interfaces.ComponentManager;
import cn.allay.api.entity.interfaces.EntityPlayer;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.world.World;
import lombok.Builder;
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
    protected OnNeighborChanged onNeighborChanged = (blockState, neighborBlockState, blockFace) -> {
    };
    protected Place place = (player, world, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace) -> {
        world.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        return true;
    };
    protected OnInteract onInteract = (player, itemStack, world, blockPos, placeBlockPos, clickPos, blockFace) -> false;
    protected OnPlace onPlace = (currentBlockState, newBlockState) -> {
    };
    protected OnRandomUpdate onRandomUpdate = blockState -> {
    };
    protected OnReplace onReplace = (currentBlockState, newBlockState) -> {
    };
    protected OnScheduledUpdate onScheduledUpdate = blockState -> {
    };

    public BlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        this.blockType = blockType;
    }

    @Builder
    public BlockBaseComponentImpl(
            BlockType<? extends BlockBehavior> blockType,
            @Nullable OnNeighborChanged onNeighborChanged,
            @Nullable OnRandomUpdate onRandomUpdate,
            @Nullable OnScheduledUpdate onScheduledUpdate,
            @Nullable Place place,
            @Nullable OnInteract onInteract,
            @Nullable OnPlace onPlace,
            @Nullable OnReplace onReplace) {
        this.blockType = blockType;
        if (onNeighborChanged != null) this.onNeighborChanged = onNeighborChanged;
        if (onRandomUpdate != null) this.onRandomUpdate = onRandomUpdate;
        if (onScheduledUpdate != null) this.onScheduledUpdate = onScheduledUpdate;
        if (place != null) this.place = place;
        if (onInteract != null) this.onInteract = onInteract;
        if (onPlace != null) this.onPlace = onPlace;
        if (onReplace != null) this.onReplace = onReplace;
    }

    @Override
    @Impl
    public BlockType<? extends BlockBehavior> getBlockType() {
        return blockType;
    }

    @Override
    @Impl
    public void onNeighborChanged(BlockStateWithPos blockState, BlockStateWithPos neighborBlockState, BlockFace blockFace) {
        manager.callEvent(new BlockOnNeighborChangedEvent(blockState, neighborBlockState, blockFace));
        onNeighborChanged.onNeighborChanged(blockState, neighborBlockState, blockFace);
    }

    @Override
    @Impl
    public void onRandomUpdate(BlockStateWithPos blockState) {
        onRandomUpdate.onRandomUpdate(blockState);
    }

    @Override
    @Impl
    public void onScheduledUpdate(BlockStateWithPos blockState) {
        onScheduledUpdate.onScheduledUpdate(blockState);
    }

    @Override
    @Impl
    public boolean place(@Nullable EntityPlayer player, @NotNull World world, @NotNull BlockState blockState, @NotNull Vector3ic targetBlockPos, @NotNull Vector3ic placeBlockPos, Vector3fc clickPos, @NotNull BlockFace blockFace) {
        return place.place(player, world, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace);
    }

    @Override
    @Impl
    public void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState) {
        manager.callEvent(new BlockOnPlaceEvent(currentBlockState, newBlockState));
        onPlace.onPlace(currentBlockState, newBlockState);
    }

    @Override
    @Impl
    public void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState) {
        manager.callEvent(new BlockOnReplaceEvent(currentBlockState, newBlockState));
        onReplace.onReplace(currentBlockState, newBlockState);
    }

    @Override
    @Impl
    public boolean onInteract(@Nullable EntityPlayer player, ItemStack itemStack, World world, Vector3ic blockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        manager.callEvent(new BlockOnInteractEvent(player, itemStack, world, blockPos, placeBlockPos, clickPos, blockFace));
        return onInteract.onInteract(player, itemStack, world, blockPos, placeBlockPos, clickPos, blockFace);
    }
}
