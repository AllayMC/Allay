package cn.allay.block.type;

import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.component.interfaces.ComponentProvider;
import cn.allay.identifier.Identifier;

import java.util.List;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public interface BlockType<T extends Block> {
    List<ComponentProvider<? extends BlockComponentImpl>> getComponentProviders();

    List<BlockPropertyType<?>> getProperties();

    Map<String, BlockPropertyType<?>> getMappedProperties();

    Identifier getNamespaceId();
}
