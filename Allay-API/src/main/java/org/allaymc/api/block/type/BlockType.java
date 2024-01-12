package org.allaymc.api.block.type;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.identifier.Identified;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.type.ItemType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/3/19
 *
 * @author daoge_cmd
 */
public interface BlockType<T extends BlockBehavior> extends Identified {

    T getBlockBehavior();

    List<? extends BlockComponent> getComponents();

    @UnmodifiableView
    Map<String, BlockPropertyType<?>> getProperties();

    @UnmodifiableView
    Collection<BlockState> getAllStates();

    @UnmodifiableView
    Map<Integer, BlockState> getBlockStateHashMap();

    @UnmodifiableView
    Map<Long, BlockState> getSpecialValueMap();

    byte getSpecialValueBits();

    BlockState getDefaultState();

    default Identifier getItemIdentifier() {
        return getItemType().getIdentifier();
    }

    ItemType<?> getItemType();

    BlockState ofState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    default BlockState ofState(int blockStateHash) {
        return getBlockStateHashMap().get(blockStateHash);
    }

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
