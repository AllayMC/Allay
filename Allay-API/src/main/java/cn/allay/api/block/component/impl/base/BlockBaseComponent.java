package cn.allay.api.block.component.impl.base;

import cn.allay.api.block.Block;
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
    BlockType<? extends Block> getBlockType();

    @Inject
    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> void setProperty(PROPERTY property, DATATYPE value);

    @Inject
    void setProperty(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue);

    @Inject
    void setProperties(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    @Inject
    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getProperty(PROPERTY property);

    @Inject
    void setState(BlockState state);

    @Inject
    BlockState getCurrentState();

    @Inject
    BlockState getNextState();

    @Inject
    Pos3i getPosition();
}
