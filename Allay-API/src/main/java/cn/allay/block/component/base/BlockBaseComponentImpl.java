package cn.allay.block.component.base;

import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.type.BlockType;
import cn.allay.component.annotation.Inject;
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

    //This method will be called by the injector when assembling the class to set the Block Type
    //DO NOT CALL IT!!!
    public static void setBlockType(BlockBaseComponentImpl component, BlockType<? extends Block> type) {
        component.setBlockType(type);
    }

    @Override
    @Inject
    public BlockType<? extends Block> getBlockType() {
        return type;
    }

    private void setBlockType(BlockType<? extends Block> type) {
        if (this.type != null)
            throw new IllegalStateException("Block type has been set");
        this.type = type;
        for (var propertyType : type.getProperties())
            currentProperties.put(propertyType, propertyType.tryCreateValue(propertyType.getDefaultValue()));
    }

    @Override
    @Inject
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> void setProperty(PROPERTY property, DATATYPE value) {
        //TODO: 也许需要额外的工作？
        ensureMapping();
        if (!type.getMappedProperties().containsKey(property.getName()))
            throw new IllegalArgumentException("Property " + property + " is not supported by this block");
        currentProperties.put(property, property.createValue(value));
    }

    @Override
    @Nullable
    @Inject
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getProperty(PROPERTY property) {
        ensureMapping();
        return (DATATYPE) currentProperties.computeIfAbsent(property, BlockPropertyType::createDefaultValue).getValue();
    }

    @Override
    @Inject
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
