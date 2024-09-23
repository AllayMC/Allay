package org.allaymc.api.block.type;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.data.BlockStateData;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleBlockDefinition;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;

/**
 * Represents a block state.
 *
 * @author daoge_cmd
 */
public interface BlockState {

    /**
     * Get the block type of this block state.
     *
     * @return the block type.
     */
    BlockType<?> getBlockType();

    /**
     * Get the block state's hash.
     *
     * @return block state's hash.
     */
    int blockStateHash();

    /**
     * Get the special value of this block state.
     * <p>
     * For all states of a block type, the states have unique special values from one state to another,
     * and the special value can be computed through the state's property values
     *
     * @return the special value.
     */
    long specialValue();

    /**
     * Get the property value of this block state.
     *
     * @return the property value of this block state.
     */
    @UnmodifiableView
    Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues();

    /**
     * Get the value of a specific property type.
     *
     * @param property the specific property type.
     *
     * @return the value of the specific property type.
     *
     * @throws IllegalArgumentException if the property type is not supported by this block type.
     */
    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getPropertyValue(PROPERTY property);

    /**
     * Apply a specific property value on this block state.
     *
     * @param propertyValue the specific property value.
     *
     * @return the new block state.
     *
     * @throws IllegalArgumentException if the property value is not supported by this block type.
     */
    BlockState setProperty(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue);

    /**
     * Set a specific property type's value.
     *
     * @param property the specific property type.
     * @param value    the value you want to be set.
     *
     * @return the new block state.
     *
     * @throws IllegalArgumentException if the property type or value is not supported by this block type
     */
    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> BlockState setProperty(PROPERTY property, DATATYPE value);

    /**
     * Set multiple property values at once.
     *
     * @param propertyValues the property values.
     *
     * @return the new block state.
     *
     * @throws IllegalArgumentException if the property values are not supported by this block type.
     */
    BlockState setProperties(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    long unsignedBlockStateHash();

    /**
     * Get the nbt display format of this block state.
     *
     * @return block state's nbt format.
     */
    NbtMap getBlockStateTag();

    /**
     * Get the <b>really used</b> item form of this block state.
     *
     * @return the <b>really used</b> item form of this block state.
     */
    ItemStack toItemStack();

    // TODO: Confirm if only BlockDefinition::getRuntimeId() needs to be implemented.
    //       The current implementation is complex and inefficient.
    default SimpleBlockDefinition toNetworkBlockDefinition() {
        var statesBuilder = NbtMap.builder();
        for (var propertyValue : getPropertyValues().values()) {
            statesBuilder.put(
                    propertyValue.getPropertyType().getName(),
                    propertyValue.getSerializedValue()
            );
        }
        return new SimpleBlockDefinition(
                getBlockType().getIdentifier().toString(),
                blockStateHash(),
                statesBuilder.build()
        );
    }

    default BlockDefinition toNetworkBlockDefinitionRuntime() {
        return this::blockStateHash;
    }

    /**
     * Get the block state's behavior.
     *
     * @return the block state's behavior.
     */
    default BlockBehavior getBehavior() {
        return getBlockType().getBlockBehavior();
    }

    /**
     * Get the data of this block state.
     *
     * @return the data of this block state.
     */
    default BlockStateData getBlockStateData() {
        return getBehavior().getBlockStateData(this);
    }
}
