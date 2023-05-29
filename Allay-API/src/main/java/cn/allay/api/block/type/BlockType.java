package cn.allay.api.block.type;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.property.BlockState;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.identifier.Identified;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public interface BlockType<T extends Block> extends Identified {
    List<ComponentProvider<? extends BlockComponentImpl>> getComponentProviders();

    List<BlockPropertyType<?>> getProperties();

    Map<String, BlockPropertyType<?>> getMappedProperties();

    T createBlock(BlockInitInfo info);

    BlockState<T> ofState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    default BlockState<T> ofState(BlockPropertyType.BlockPropertyValue<?, ?, ?>... propertyValues) {
        return ofState(List.of(propertyValues));
    }

    @UnmodifiableView
    Map<Integer, BlockState<T>> allStates();

    default BlockType<T> register(BlockTypeRegistry registry) {
        registry.register(getNamespaceId(), this);
        return this;
    }
}
