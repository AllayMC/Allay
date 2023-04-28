package cn.allay.block.component.impl.base;

import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.type.BlockType;
import cn.allay.component.annotation.Impl;
import cn.allay.identifier.Identifier;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class BlockBaseComponentImpl implements BlockBaseComponent, BlockComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_base_component");

    //TODO: 也许可以优化结构以进一步节省占用
    protected Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?>> currentProperties = new HashMap<>();
    protected BlockType<? extends Block> type;

    public BlockBaseComponentImpl(BlockType<? extends Block> type) {
        this.type = type;
    }

    @Override
    @Impl
    public BlockType<? extends Block> getBlockType() {
        return type;
    }

    @Override
    @Impl
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> void setProperty(PROPERTY property, DATATYPE value) {
        //TODO: 也许需要额外的工作？
        ensureMapping();
        if (!getBlockType().getMappedProperties().containsKey(property.getName()))
            throw new IllegalArgumentException("Property " + property + " is not supported by this block");
        currentProperties.put(property, property.createValue(value));
    }

    @Override
    @Nullable
    @Impl
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getProperty(PROPERTY property) {
        ensureMapping();
        return (DATATYPE) currentProperties.computeIfAbsent(property, BlockPropertyType::createDefaultValue).getValue();
    }

    @Override
    @Impl
    @UnmodifiableView
    public Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?>> getCurrentProperties() {
        return Collections.unmodifiableMap(currentProperties);
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }

    protected void ensureMapping() {
        if (currentProperties == null) currentProperties = new HashMap<>();
    }
}
