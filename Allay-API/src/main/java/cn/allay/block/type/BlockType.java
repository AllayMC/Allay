package cn.allay.block.type;

import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;
import cn.allay.block.property.state.BlockState;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.component.interfaces.ComponentProvider;
import cn.allay.identifier.Identified;
import cn.allay.identifier.Identifier;
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
