package org.allaymc.api.block.type;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.common.BlockAttributes;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/4/29
 *
 * @author daoge_cmd
 */
public interface BlockState {

    BlockType<?> getBlockType();

    int blockStateHash();

    long specialValue();

    @UnmodifiableView
    Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues();

    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getPropertyValue(PROPERTY property);

    BlockState setProperty(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue);

    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> BlockState setProperty(PROPERTY property, DATATYPE value);

    BlockState setProperties(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    long unsignedBlockStateHash();

    NbtMap getBlockStateTag();

    ItemStack toItemStack();

    // TODO: Confirm if only need to implement BlockDefinition::getRuntimeId(), the existing implementation is quite complex and inefficient
    default SimpleBlockDefinition toNetworkBlockDefinition() {
        var statesBuilder = NbtMap.builder();
        for (var propertyValue : getPropertyValues().values()) {
            statesBuilder.put(
                    propertyValue.getPropertyType().getName(),
                    propertyValue.getSerializedValue()
            );
        }
        return new SimpleBlockDefinition(
                getBlockType().getIdentifier().toString(),
                blockStateHash(),
                statesBuilder.build()
        );
    }

    default BlockDefinition toNetworkBlockDefinitionRuntime() {
        return this::blockStateHash;
    }

    default BlockBehavior getBehavior() {
        return getBlockType().getBlockBehavior();
    }

    default BlockAttributes getBlockAttributes() {
        return getBehavior().getBlockAttributes(this);
    }
}
