package org.allaymc.server.block.component;

import org.allaymc.api.block.component.BlockComponentImplFactory;
import org.allaymc.api.block.component.common.BlockAttributeComponent;
import org.allaymc.api.block.component.common.BlockAttributes;
import org.allaymc.api.block.component.common.BlockBaseComponent;
import org.allaymc.api.block.component.common.BlockEntityHolderComponent;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.server.block.component.barrel.BlockBarrelBaseComponentImpl;
import org.allaymc.server.block.component.common.BlockAttributeComponentImpl;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.allaymc.server.block.component.common.BlockEntityHolderComponentImpl;
import org.allaymc.server.block.component.craftingtable.BlockCraftingTableBaseComponentImpl;
import org.allaymc.server.block.component.stairs.BlockStairsBaseComponentImpl;
import org.allaymc.server.block.component.torch.BlockTorchBaseComponentImpl;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public class AllayBlockComponentImplFactory implements BlockComponentImplFactory {
    @Override
    public BlockBaseComponent createBlockBaseComponent(BlockType<?> blockType) {
        return new BlockBaseComponentImpl(blockType);
    }

    @Override
    public BlockBaseComponent createBlockStairBaseComponent(BlockType<?> blockType) {
        return new BlockStairsBaseComponentImpl(blockType);
    }

    @Override
    public BlockBaseComponent createBlockTorchBaseComponent(BlockType<?> blockType) {
        return new BlockTorchBaseComponentImpl(blockType);
    }

    @Override
    public BlockBaseComponent createBlockBarrelBaseComponent(BlockType<?> blockType) {
        return new BlockBarrelBaseComponentImpl(blockType);
    }

    @Override
    public BlockBaseComponent createBlockCraftingTableBaseComponent(BlockType<?> blockType) {
        return new BlockCraftingTableBaseComponentImpl(blockType);
    }

    @Override
    public BlockAttributeComponent createGlobalStaticBlockAttributeComponent(BlockAttributes attributes) {
        return BlockAttributeComponentImpl.ofGlobalStatic(attributes);
    }

    @Override
    public BlockAttributeComponent createDirectDynamicBlockAttributeComponent(Function<BlockState, BlockAttributes> directAttributeAccessor) {
        return BlockAttributeComponentImpl.ofDirectDynamic(directAttributeAccessor);
    }

    @Override
    public BlockAttributeComponent createCachedDynamicBlockAttributeComponent(Function<BlockState, BlockAttributes> directAttributeAccessor) {
        return BlockAttributeComponentImpl.ofCachedDynamic(directAttributeAccessor);
    }

    @Override
    public BlockAttributeComponent createMappedBlockStateHashBlockAttributeComponent(Supplier<Map<Integer, BlockAttributes>> attributeMapSupplier) {
        return BlockAttributeComponentImpl.ofMappedBlockStateHash(attributeMapSupplier);
    }

    @Override
    public BlockAttributeComponent createMappedBlockStateHashBlockAttributeComponent(Map<Integer, BlockAttributes> attributeMap) {
        return BlockAttributeComponentImpl.ofMappedBlockStateHash(attributeMap);
    }

    @Override
    public BlockAttributeComponent createMappedBlockStateHashBlockAttributeComponent(Map<Integer, BlockAttributes> attributeMap, BlockAttributes defaultAttributes) {
        return BlockAttributeComponentImpl.ofMappedBlockStateHash(attributeMap, defaultAttributes);
    }

    @Override
    public BlockAttributeComponent createMappedBlockStateHashLazyLoadBlockAttributeComponent(Function<BlockType<?>, Map<Integer, BlockAttributes>> lazyLoader) {
        return BlockAttributeComponentImpl.ofMappedBlockStateHashLazyLoad(lazyLoader);
    }

    @Override
    public BlockAttributeComponent createRedefinedAABBBlockAttributeComponent(Function<BlockState, VoxelShape> aabbRedefiner) {
        return BlockAttributeComponentImpl.ofRedefinedAABB(aabbRedefiner);
    }

    @Override
    public BlockEntityHolderComponent<?> createBlockEntityHolderComponent(BlockEntityType<?> blockEntityType) {
        return new BlockEntityHolderComponentImpl<>(blockEntityType);
    }
}
