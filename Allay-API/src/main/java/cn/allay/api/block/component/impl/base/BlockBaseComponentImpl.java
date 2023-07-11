package cn.allay.api.block.component.impl.base;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.block.type.BlockInitInfo;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.vector.Pos3i;
import com.google.common.collect.Lists;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public class BlockBaseComponentImpl implements BlockBaseComponent, BlockComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_base_component");

    protected BlockState currentState;
    protected BlockType<? extends Block> blockType;
    protected final Pos3i pos;

    public BlockBaseComponentImpl(BlockType<? extends Block> blockType, BlockInitInfo info) {
        this.blockType = blockType;
        this.currentState = blockType.getDefaultState();
        this.pos = info.position();
    }

    @Override
    @Impl
    public BlockType<? extends Block> getBlockType() {
        return blockType;
    }

    @Override
    @Impl
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> void setProperty(PROPERTY property, DATATYPE value) {
        if (!getBlockType().getProperties().containsKey(property.getName()))
            throw new IllegalArgumentException("Property " + property + " is not supported by this block");
        currentState = currentState.setProperty(property.createValue(value));
    }

    @Override
    @Impl
    public void setProperty(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue) {
        if (!getBlockType().getProperties().containsKey(propertyValue.getPropertyType().getName()))
            throw new IllegalArgumentException("Property " + propertyValue.getPropertyType() + " is not supported by this block");
        currentState = currentState.setProperty(propertyValue);
    }

    @Override
    @Impl
    public void setProperties(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        for (BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue : propertyValues) {
            if (!getBlockType().getProperties().containsKey(propertyValue.getPropertyType().getName()))
                throw new IllegalArgumentException("Property " + propertyValue.getPropertyType() + " is not supported by this block");
        }
        currentState = currentState.setProperties(propertyValues);
    }


    @Override
    @Nullable
    @Impl
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getProperty(PROPERTY property) {
        return (DATATYPE) currentState.propertyValues().get(property).getValue();
    }

    @Override
    @Impl
    public void setState(BlockState state) {
        if (blockType.getBlockStateHashMap().containsValue(state))
            currentState = state;
        else throw new IllegalArgumentException("State " + state + " is not supported by this block");
    }

    @Override
    @Impl
    public BlockState getCurrentState() {
        return currentState;
    }

    @Override
    @Impl
    public BlockState getNextState() {
        ArrayList<? extends BlockState> blockStates = Lists.newArrayList(getBlockType().getBlockStateHashMap().values());
        int next = blockStates.indexOf(currentState) + 1;
        if (next == blockStates.size()) {
            return blockStates.get(0);
        } else {
            return blockStates.get(next);
        }
    }

    @Override
    @Impl
    public Pos3i getPosition() {
        return pos;
    }

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }
}
