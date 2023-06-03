package cn.allay.api.block.type;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.impl.BlockAir;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.identifier.Identified;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    Set<BlockState> getAllStates();

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
        for (var s : getAllStates()) {
            registry.register(s.blockStateHash(), s);
        }
    }
}
