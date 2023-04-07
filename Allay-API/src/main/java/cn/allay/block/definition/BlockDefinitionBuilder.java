package cn.allay.block.definition;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/7 <br>
 * Allay Project <br>
 */
public interface BlockDefinitionBuilder<T extends Block> {

    ApiInstanceHolder<BlockDefinitionBuilder<?>> BUILDER = ApiInstanceHolder.create();

    BlockDefinitionBuilder<T> block(Class<T> blockClazz);

    BlockDefinitionBuilder<T> component(BlockComponentImpl component);

    BlockDefinitionBuilder<T> components(List<BlockComponentImpl> components);

    BlockDefinition<T> build();
}
