package cn.allay.api.block.component.impl.base;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.property.BlockState;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.block.type.BlockInitInfo;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.position.FixedPos;
import cn.allay.api.math.position.Pos;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class BlockBaseComponentImpl implements BlockBaseComponent, BlockComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_base_component");

    protected BlockState<?> currentState;
    protected BlockType<? extends Block> blockType;
    protected final Pos<Integer> pos;

    public BlockBaseComponentImpl(BlockType<? extends Block> blockType, BlockInitInfo info) {
        this.blockType = blockType;
        //TODO: Maybe need to read default block state from config file
        this.currentState = blockType.ofState(
                blockType.getMappedProperties()
                        .values()
                        .stream()
                        .map(BlockPropertyType::createDefaultValue)
                        .collect(Collectors.toList()));
        pos = info.position();
    }

    @Override
    @Impl
    public BlockType<? extends Block> getBlockType() {
        return blockType;
    }

    @Override
    @Impl
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> void setProperty(PROPERTY property, DATATYPE value) {
        if (!getBlockType().getMappedProperties().containsKey(property.getName()))
            throw new IllegalArgumentException("Property " + property + " is not supported by this block");
        currentState = currentState.updatePropertyValue(property.createValue(value));
    }

    @Override
    @Nullable
    @Impl
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getProperty(PROPERTY property) {
        return (DATATYPE) currentState.getPropertyValues().get(property).getValue();
    }

    @Override
    @Impl
    public void setState(BlockState<?> state) {
        if (blockType.allStates().containsValue(state))
            currentState = state;
        else throw new IllegalArgumentException("State " + state + " is not supported by this block");
    }

    @Override
    @Impl
    public BlockState<?> getCurrentState() {
        return currentState;
    }

    @Override
    @Impl
    public FixedPos<Integer> getPosition() {
        return pos;
    }

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }
}
