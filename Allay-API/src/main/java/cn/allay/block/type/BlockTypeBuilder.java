package cn.allay.block.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.block.Block;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public interface BlockTypeBuilder<T extends Block> {

    ApiInstanceHolder<BlockTypeBuilderFactory> FACTORY = ApiInstanceHolder.create();

    static <T extends Block> BlockTypeBuilder<T> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    BlockType<T> build();

    interface BlockTypeBuilderFactory {
        <T extends Block> BlockTypeBuilder<T> create(Class<T> clazz);
    }
}
