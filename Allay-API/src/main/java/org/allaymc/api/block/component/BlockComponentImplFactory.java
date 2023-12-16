package org.allaymc.api.block.component;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.block.component.common.BlockAttributeComponent;
import org.allaymc.api.block.component.common.BlockAttributes;
import org.allaymc.api.block.component.common.BlockBaseComponent;
import org.allaymc.api.block.component.common.BlockEntityHolderComponent;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.math.voxelshape.VoxelShape;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public interface BlockComponentImplFactory {
    ApiInstanceHolder<BlockComponentImplFactory> FACTORY = ApiInstanceHolder.of();

    static BlockComponentImplFactory getFactory() {
        return FACTORY.get();
    }

    BlockBaseComponent createBlockBaseComponent(BlockType<?> blockType);

    BlockBaseComponent createBlockStairBaseComponent(BlockType<?> blockType);

    BlockBaseComponent createBlockTorchBaseComponent(BlockType<?> blockType);

    BlockBaseComponent createBlockBarrelBaseComponent(BlockType<?> blockType);

    BlockBaseComponent createBlockCraftingTableBaseComponent(BlockType<?> blockType);

    BlockAttributeComponent createGlobalStaticBlockAttributeComponent(BlockAttributes attributes);

    BlockAttributeComponent createDirectDynamicBlockAttributeComponent(Function<BlockState, BlockAttributes> directAttributeAccessor);

    BlockAttributeComponent createCachedDynamicBlockAttributeComponent(Function<BlockState, BlockAttributes> directAttributeAccessor);

    BlockAttributeComponent createMappedBlockStateHashBlockAttributeComponent(Supplier<Map<Integer, BlockAttributes>> attributeMapSupplier);

    BlockAttributeComponent createMappedBlockStateHashBlockAttributeComponent(Map<Integer, BlockAttributes> attributeMap);

    BlockAttributeComponent createMappedBlockStateHashBlockAttributeComponent(Map<Integer, BlockAttributes> attributeMap, BlockAttributes defaultAttributes);

    BlockAttributeComponent createMappedBlockStateHashLazyLoadBlockAttributeComponent(Function<BlockType<?>, Map<Integer, BlockAttributes>> lazyLoader);

    BlockAttributeComponent createRedefinedAABBBlockAttributeComponent(Function<BlockState, VoxelShape> aabbRedefiner);

    BlockEntityHolderComponent<?> createBlockEntityHolderComponent(BlockEntityType<?> blockEntityType);
}
