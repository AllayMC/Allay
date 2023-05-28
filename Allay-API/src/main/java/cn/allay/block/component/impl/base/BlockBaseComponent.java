package cn.allay.block.component.impl.base;

import cn.allay.block.Block;
import cn.allay.block.property.state.BlockState;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.type.BlockType;
import cn.allay.component.annotation.Inject;
import cn.allay.math.position.FixedPos;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public interface BlockBaseComponent {
    @Inject
    BlockType<? extends Block> getBlockType();

    @Inject
    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> void setProperty(PROPERTY property, DATATYPE value);

    @Inject
    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getProperty(PROPERTY property);

    @Inject
    void setState(BlockState<?> state);

    @Inject
    BlockState<?> getCurrentState();

    @Inject
    FixedPos<Integer> getPosition();
}
