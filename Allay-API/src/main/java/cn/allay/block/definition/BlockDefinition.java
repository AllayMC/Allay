package cn.allay.block.definition;

import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public interface BlockDefinition<T extends Block> {
    List<BlockComponentImpl> getComponents();
    Class<T> getTargetInterface();
}
