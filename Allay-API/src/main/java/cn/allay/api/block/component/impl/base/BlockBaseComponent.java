package cn.allay.api.block.component.impl.base;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.component.annotation.Inject;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public interface BlockBaseComponent {
    @Inject
    BlockType<? extends BlockBehavior> getBlockType();
}
