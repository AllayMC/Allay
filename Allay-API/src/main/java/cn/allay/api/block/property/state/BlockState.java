package cn.allay.api.block.property.state;

import cn.allay.api.block.Block;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.block.type.BlockType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/29 <br>
 * Allay Project <br>
 */
public interface BlockState<T extends Block> {
    BlockType<T> getBlockType();

    @UnmodifiableView
    Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues();

    BlockState<T> updatePropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> newPropertyValue);

    //TODO: updatePropertyValues

    int getBlockStateHash();

    long getUnsignedBlockStateHash();
}
