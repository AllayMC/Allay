package org.allaymc.api.block.type;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.data.BlockStateData;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
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
     * Gets the block type of this block state.
     *
     * @return the associated {@link BlockType}
     */
    BlockType<?> getBlockType();

    /**
     * Gets the hash of this block state.
     *
     * @return the hash value as an integer
     */
    int blockStateHash();

    /**
     * Gets the special value identifying this state.
     * <p>
     * For all states of a block type, the states have unique special values from one state to another,
     * and the special value can be computed through the state's property values
     *
     * @return the special value as a long
     */
    long specialValue();

    /**
     * Gets the property values of this state.
     *
     * @return unmodifiable map of {@link BlockPropertyType} to {@link BlockPropertyType.BlockPropertyValue}
     */
    @UnmodifiableView
    Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues();

    /**
     * Creates a new state with updated property values.
     *
     * @param propertyValues list of {@link BlockPropertyType.BlockPropertyValue} to set
     *
     * @return new {@link BlockState} with applied values
     *
     * @throws IllegalArgumentException if any value is unsupported by this block type
     */
    BlockState withPropertyValues(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    /**
     * Gets the value of a specific property.
     *
     * @param property   the {@link BlockPropertyType} to query
     * @param <DATATYPE> property value type
     * @param <PROPERTY> property type subtype
     *
     * @return the property value
     *
     * @throws IllegalArgumentException if property is unsupported by this block type
     */
    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getPropertyValue(PROPERTY property);

    /**
     * Creates a new state with an updated property value.
     *
     * @param propertyValue the {@link BlockPropertyType.BlockPropertyValue} to apply
     *
     * @return new {@link BlockState} with the updated value
     *
     * @throws IllegalArgumentException if value is unsupported by this block type
     */
    BlockState withPropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue);

    /**
     * Creates a new state with a specific property value set.
     *
     * @param property   the {@link BlockPropertyType} to modify
     * @param value      the value to set
     * @param <DATATYPE> property value type
     * @param <PROPERTY> property type subtype
     *
     * @return new {@link BlockState} with the updated value
     *
     * @throws IllegalArgumentException if property or value is unsupported by this block type
     */
    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> BlockState withPropertyValue(PROPERTY property, DATATYPE value);

    /**
     * Gets the unsigned hash of this block state.
     *
     * @return the hash value as an unsigned long
     */
    long unsignedBlockStateHash();

    /**
     * Gets the NBT representation of this state.
     *
     * @return {@link NbtMap} of this block state
     */
    NbtMap getBlockStateTag();

    /**
     * Converts this state to its item form.
     *
     * @return {@link ItemStack} representing this state
     */
    ItemStack toItemStack();

    /**
     * Converts this state to a network block definition.
     *
     * @return {@link BlockDefinition} for network use
     */
    default BlockDefinition toNetworkBlockDefinition() {
        return this::blockStateHash;
    }

    /**
     * Gets the behavior of this block state.
     *
     * @return the {@link BlockBehavior} instance
     */
    default BlockBehavior getBehavior() {
        return getBlockType().getBlockBehavior();
    }

    /**
     * Gets the data for this block state.
     *
     * @return the {@link BlockStateData} instance
     */
    default BlockStateData getBlockStateData() {
        return getBehavior().getBlockStateData(this);
    }
}
