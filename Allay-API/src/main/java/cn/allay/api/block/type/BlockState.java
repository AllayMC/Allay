package cn.allay.api.block.type;

import cn.allay.api.block.property.type.BlockPropertyType;
import org.cloudburstmc.nbt.NbtMap;
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
    BlockType<?> blockType();

    @UnmodifiableView
    Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues();

    BlockState setProperty(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue);

    BlockState setProperties(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    int blockStateHash();

    long unsignedBlockStateHash();

    int specialValue();

    int paletteIndex();

    default SimpleBlockDefinition toBlockDefinition() {
        var statesBuilder = NbtMap.builder();
        for (var propertyValue : propertyValues().values()) {
            var propertyType = propertyValue.getPropertyType();
            switch (propertyType.getType()) {
                case INT -> {
                    statesBuilder.putInt(propertyType.getName(), (int) propertyValue.getSerializedValue());
                }
                case BOOLEAN -> {
                    statesBuilder.putByte(propertyType.getName(), (byte) propertyValue.getSerializedValue());
                }
                case ENUM -> {
                    statesBuilder.putString(propertyType.getName(), (String) propertyValue.getSerializedValue());
                }
            }
        }
        return new SimpleBlockDefinition(
                blockType().getIdentifier().toString(),
                paletteIndex(),
                statesBuilder.build()
        );
    }
}
