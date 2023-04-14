package cn.allay.block;

import cn.allay.block.component.attribute.BlockAttributeComponent;
import cn.allay.block.component.base.BlockBaseComponent;
import cn.allay.block.component.position.BlockPositionComponent;

/**
 * Author: daoge_cmd <br>
    * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public interface Block extends
        BlockBaseComponent,
        BlockAttributeComponent,
        BlockPositionComponent {
}
