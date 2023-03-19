package cn.allay.block.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.block.Block;
import cn.allay.block.definition.BlockDefinition;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public interface BlockTypeBuilder {

    ApiInstanceHolder<BlockTypeBuilder> BUILDER = ApiInstanceHolder.create();

    static BlockTypeBuilder builder() {
        return BUILDER.get();
    }

    <T extends Block> BlockType<T> build(BlockDefinition<T> definition);
}
