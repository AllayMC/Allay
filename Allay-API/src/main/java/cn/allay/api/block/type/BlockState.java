package cn.allay.api.block.type;

import cn.allay.api.block.property.type.BlockPropertyType;
import org.jetbrains.annotations.UnmodifiableView;

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

    BlockState updatePropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> newPropertyValue);


    int blockStateHash();

    long unsignedBlockStateHash();
}
