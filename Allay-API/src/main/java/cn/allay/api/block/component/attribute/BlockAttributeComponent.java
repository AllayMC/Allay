package cn.allay.api.block.component.attribute;

import cn.allay.api.block.component.BlockComponent;
import cn.allay.api.block.type.BlockState;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
public interface BlockAttributeComponent extends BlockComponent {
    BlockAttributes getBlockAttributes(BlockState blockState);
}
