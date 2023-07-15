package cn.allay.api.block.component.impl.base;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.component.annotation.Inject;
import cn.allay.api.math.vector.Pos3i;

import java.util.List;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public interface BlockBaseComponent {
    @Inject
    BlockType<? extends BlockBehavior> getBlockType();
}
