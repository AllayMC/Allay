package org.allaymc.server.block.type;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.server.network.ProtocolInfo;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;

/**
 * @author daoge_cmd
 */
public record AllayBlockState(
        BlockType<?> blockType,
        BlockPropertyType.BlockPropertyValue<?, ?, ?>[] blockPropertyValues,
        NbtMap blockStateTag,
        int blockStateHash,
        long specialValue
) implements BlockState {
    public AllayBlockState(BlockType<?> blockType, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
        this(
                blockType, propertyValues, buildBlockStateTag(blockType, propertyValues),
                HashUtils.computeBlockStateHash(blockType.getIdentifier(), Arrays.stream(propertyValues).toList()),
                AllayBlockType.computeSpecialValue(propertyValues)
        );
    }

    private static NbtMap buildBlockStateTag(BlockType<?> blockType, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
        var states = new TreeMap<String, Object>();
        for (var value : propertyValues) {
            states.put(value.getPropertyType().getName(), value.getSerializedValue());
        }

        return NbtMap.builder()
                .putString("name", blockType.getIdentifier().toString())
                .putCompound("states", NbtMap.fromMap(states))
                .putInt("version", ProtocolInfo.BLOCK_STATE_VERSION_NUM)
                .build();
    }

    @Override
    public NbtMap getBlockStateNBT() {
        return blockStateTag;
    }

    @Override
    public ItemStack toItemStack() {
        return blockType.getItemType().createItemStack(1);
    }

    @Override
    public BlockType<?> getBlockType() {
        return blockType;
    }

    @Override
    public @UnmodifiableView Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues() {
        var hashMap = new LinkedHashMap<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
        for (var blockPropertyValue : blockPropertyValues) {
            hashMap.put(blockPropertyValue.getPropertyType(), blockPropertyValue);
        }

        return Collections.unmodifiableMap(hashMap);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getPropertyValue(PROPERTY p) {
        for (var property : blockPropertyValues) {
            if (property.getPropertyType() == p) {
                return (DATATYPE) property.getValue();
            }
        }

        throw new IllegalArgumentException("Property " + p + " is not supported by block " + blockType.getIdentifier());
    }

    @Override
    public BlockState setPropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue) {
        var newPropertyValues = new BlockPropertyType.BlockPropertyValue<?, ?, ?>[this.blockPropertyValues.length];
        var succeed = false;
        for (int i = 0; i < blockPropertyValues.length; i++) {
            if (blockPropertyValues[i].getPropertyType() == propertyValue.getPropertyType()) {
                succeed = true;
                newPropertyValues[i] = propertyValue;
            } else {
                newPropertyValues[i] = blockPropertyValues[i];
            }
        }

        if (!succeed) {
            throw new IllegalArgumentException("Property " + propertyValue.getPropertyType() + " is not supported by block " + blockType.getIdentifier());
        }

        return getNewBlockState(newPropertyValues);
    }

    @Override
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> BlockState setPropertyValue(PROPERTY property, DATATYPE value) {
        return setPropertyValue(property.createValue(value));
    }

    @Override
    public BlockState setPropertyValues(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        var newPropertyValues = new BlockPropertyType.BlockPropertyValue<?, ?, ?>[this.blockPropertyValues.length];
        var succeedCount = 0;
        var succeed = new boolean[propertyValues.size()];
        for (int i = 0; i < blockPropertyValues.length; i++) {
            int index;
            if ((index = propertyValues.indexOf(blockPropertyValues[i])) != -1) {
                succeedCount++;
                succeed[index] = true;
                newPropertyValues[i] = propertyValues.get(index);
            } else newPropertyValues[i] = blockPropertyValues[i];
        }

        if (succeedCount != propertyValues.size()) {
            var errorMsgBuilder = new StringBuilder("Properties ");
            for (int i = 0; i < propertyValues.size(); i++) {
                if (!succeed[i]) {
                    errorMsgBuilder.append(propertyValues.get(i).getPropertyType().getName());
                    if (i != propertyValues.size() - 1)
                        errorMsgBuilder.append(", ");
                }
            }

            errorMsgBuilder.append(" are not supported by block " + blockType.getIdentifier());
            throw new IllegalArgumentException(errorMsgBuilder.toString());
        }

        return getNewBlockState(newPropertyValues);
    }

    private BlockState getNewBlockState(BlockPropertyType.BlockPropertyValue<?, ?, ?>[] values) {
        var bits = blockType.getSpecialValueBits();
        if (bits <= 64) {
            var specialValueMap = blockType.getSpecialValueMap();
            Objects.requireNonNull(specialValueMap);
            return specialValueMap.get(AllayBlockType.computeSpecialValue(bits, values));
        } else {
            // If the special value is greater than 64 bits, fallback to compute the block state hash
            return blockType.getBlockStateHashMap().get(HashUtils.computeBlockStateHash(this.blockType.getIdentifier(), values));
        }
    }

    @Override
    public boolean equals(Object obj) {
        // For each block state, we use a unique object to represent
        // it, so here we can directly compare the memory addresses
        return this == obj;
    }

    @Override
    public int hashCode() {
        return blockStateHash;
    }
}
