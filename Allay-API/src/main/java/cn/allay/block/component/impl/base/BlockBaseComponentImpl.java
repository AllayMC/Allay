package cn.allay.block.component.impl.base;

import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;
import cn.allay.block.property.state.BlockState;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.type.BlockType;
import cn.allay.component.annotation.Impl;
import cn.allay.identifier.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
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

    public BlockBaseComponentImpl(BlockType<? extends Block> blockType) {
        this.blockType = blockType;
        //TODO: 也许需要从配置文件读取默认方块状态
        this.currentState = blockType.ofState(
                blockType.getMappedProperties()
                        .values()
                        .stream()
                        .map(BlockPropertyType::createDefaultValue)
                        .collect(Collectors.toList()));
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
        //TODO: 太慢了，考虑生成一个strid然后比较strid而不是比较List
        var changed = new HashMap<>(currentState.getPropertyValues());
        changed.put(property, property.createValue(value));
        currentState = blockType.ofState(new ArrayList<>(changed.values()));
    }

    @Override
    @Nullable
    @Impl
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getProperty(PROPERTY property) {
        return (DATATYPE) currentState.getPropertyValues().get(property).getValue();
    }

    @Override
    @Impl
    public BlockState<?> getCurrentState() {
        return currentState;
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
