package cn.allay.api.block.type;

import cn.allay.api.block.property.type.BlockPropertyType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/29 <br>
 * Allay Project <br>
 */
public interface BlockState {
    BlockType<?> blockType();

    @UnmodifiableView
    Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues();

    BlockState updatePropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> newPropertyValue);


    int blockStateHash();

    long unsignedBlockStateHash();
}
