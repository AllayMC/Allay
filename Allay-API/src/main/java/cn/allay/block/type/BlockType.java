package cn.allay.block.type;

import cn.allay.block.Block;
import cn.allay.block.definition.BlockDefinition;
import cn.allay.identifier.Identifier;
import cn.allay.registery.MappedRegistry;
import cn.allay.registery.SimpleMappedRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public interface BlockType<T extends Block> {

    MappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>> REGISTRY = SimpleMappedRegistry.create(i -> new HashMap<>());

    BlockDefinition<T> getDefinition();

    Class<T> getCompiledEntityClass();
}
