package cn.allay.api.block.type;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.attribute.BlockAttributes;
import cn.allay.api.block.property.type.BlockPropertyType;
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

    int VERSION = 18090528;

    BlockType<?> blockType();

    int blockStateHash();

    int specialValue();

    @UnmodifiableView
    Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues();

    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getPropertyValue(PROPERTY property);

    BlockState setProperty(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue);

    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> BlockState setProperty(PROPERTY property, DATATYPE value);

    BlockState setProperties(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    long unsignedBlockStateHash();

    NbtMap getBlockStateTag();

    //TODO: 确认是否只需要实现BlockDefinition::getRuntimeId(), 现有实现较为复杂且低效
    default SimpleBlockDefinition toNetworkBlockDefinition() {
        var statesBuilder = NbtMap.builder();
        for (var propertyValue : getPropertyValues().values()) {
            statesBuilder.put(
                    propertyValue.getPropertyType().getName(),
                    propertyValue.getSerializedValue()
            );
        }
        return new SimpleBlockDefinition(
                blockType().getIdentifier().toString(),
                blockStateHash(),
                statesBuilder.build()
        );
    }

    default BlockDefinition toNetworkBlockDefinitionRuntime() {
        return this::blockStateHash;
    }

    default BlockBehavior getBehavior() {
        return blockType().getBlockBehavior();
    }

    default BlockAttributes getBlockAttributes() {
        return getBehavior().getBlockAttributes(this);
    }
}
