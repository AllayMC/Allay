package cn.allay.api.block.type;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.identifier.Identified;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Allay Project 2023/3/19
 *
 * @author daoge_cmd
 */
public interface BlockType<T extends Block> extends Identified {
    List<ComponentProvider<? extends BlockComponentImpl>> getComponentProviders();

    @UnmodifiableView
    Map<String, BlockPropertyType<?>> getProperties();

    @UnmodifiableView
    Collection<BlockState> getAllStates();

    @UnmodifiableView
    Map<Integer, BlockState> getBlockStateHashMap();

    @UnmodifiableView
    Map<Integer, BlockState> getSpecialValueMap();

    BlockState getDefaultState();

    T createBlock(BlockInitInfo info);

    BlockState ofState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    default BlockState ofState(BlockPropertyType.BlockPropertyValue<?, ?, ?>... propertyValues) {
        return ofState(List.of(propertyValues));
    }

    default void register(BlockTypeRegistry registry) {
        registry.register(getIdentifier(), this);
    }

    default void register(BlockStateHashPalette registry) {
        for (var s : getBlockStateHashMap().values()) {
            registry.register(s.blockStateHash(), s);
        }
    }
}
