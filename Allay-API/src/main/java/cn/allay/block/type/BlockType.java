package cn.allay.block.type;

import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.identifier.Identifier;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public interface BlockType<T extends Block> {
    List<BlockComponentImpl> getComponents();
    List<BlockPropertyType<?>> getProperties();
    Identifier getNamespaceId();
}
