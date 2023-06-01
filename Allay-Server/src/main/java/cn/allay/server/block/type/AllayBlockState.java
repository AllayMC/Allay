package cn.allay.server.block.type;

/**
 * Author: Cool_Loong <br>
 * Date: 6/1/2023 <br>
 * Allay Project
 */

import cn.allay.api.block.palette.BlockPaletteRegistry;
import cn.allay.api.block.property.BlockState;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.utils.HashUtils;
import lombok.ToString;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Each {@link AllayBlockState} is a singleton, stored in the {@link AllayBlockPaletteRegistry AllayBlockPaletteRegistry}, which means you can directly use == to compare whether two Block States are equal
 */
@ToString
class AllayBlockState implements BlockState {
    private Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues;
    private final int blockStateHash;
    private BlockType<?> blockType;

    public AllayBlockState(BlockType<?> blockType, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        this(blockType, propertyValues, HashUtils.computeBlockStateHash(blockType.getIdentifier(), propertyValues));
    }

    public AllayBlockState(BlockType<?> blockType, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues, int blockStateHash) {
        var mappedPropertyValues = new HashMap<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
        for (var value : propertyValues)
            mappedPropertyValues.put(value.getPropertyType(), value);
        this.propertyValues = mappedPropertyValues;
        this.blockStateHash = blockStateHash;
        this.blockType = blockType;
    }

    @Override
    public BlockType<?> getBlockType() {
        return blockType;
    }

    @UnmodifiableView
    @Override
    public Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues() {
        return propertyValues;
    }

    @Override
    public int getBlockStateHash() {
        return blockStateHash;
    }

    @Override
    public long getUnsignedBlockStateHash() {
        return Integer.toUnsignedLong(blockStateHash);
    }

    @Override
    public BlockState updatePropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> newPropertyValue) {
        var newPropertyValues = new ArrayList<BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
        for (var value : propertyValues.values()) {
            if (value.getPropertyType() == newPropertyValue.getPropertyType())
                newPropertyValues.add(newPropertyValue);
            else newPropertyValues.add(value);
        }
        return BlockPaletteRegistry.getRegistry().get(HashUtils.computeBlockStateHash(this.blockType.getIdentifier(), newPropertyValues));
    }
}
