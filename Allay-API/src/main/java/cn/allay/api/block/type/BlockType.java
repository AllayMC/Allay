package cn.allay.api.block.type;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.impl.BlockAir;
import cn.allay.api.block.palette.BlockPaletteData;
import cn.allay.api.block.palette.BlockPaletteRegistry;
import cn.allay.api.block.property.BlockState;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.identifier.Identified;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
@SuppressWarnings("unchecked")
public interface BlockType<T extends Block> extends Identified {
    BlockType<BlockAir> AIR = (BlockType<BlockAir>) VanillaBlockId.AIR.getBlockType();

    List<ComponentProvider<? extends BlockComponentImpl>> getComponentProviders();

    @UnmodifiableView
    Map<String, BlockPropertyType<?>> getProperties();

    @UnmodifiableView
    Map<Integer, BlockState<T>> allStates();

    T createBlock(BlockInitInfo info);

    BlockState<T> getDefaultState();

    BlockState<T> ofState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    default BlockState<T> ofState(BlockPropertyType.BlockPropertyValue<?, ?, ?>... propertyValues) {
        return ofState(List.of(propertyValues));
    }

    default void register(BlockTypeRegistry registry) {
        registry.register(getIdentifier(), this);
    }

    default void register(BlockPaletteRegistry registry) {
        registry.register(getDefaultState().getBlockStateHash(), new BlockPaletteData(getIdentifier(), getDefaultState()));
        for (var s : this.allStates().values()) {
            registry.getContent().putIfAbsent(s.getBlockStateHash(), new BlockPaletteData(getIdentifier(), s));
        }
    }
}
