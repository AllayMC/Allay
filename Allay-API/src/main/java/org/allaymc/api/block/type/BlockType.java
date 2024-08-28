package org.allaymc.api.block.type;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.material.Material;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.tag.BlockTag;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.IntMappedRegistry;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.Identified;
import org.allaymc.api.utils.Identifier;
import org.jetbrains.annotations.Unmodifiable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface representing a type of block.
 * <p>
 * Allay Project 2023/3/19
 *
 * @author daoge_cmd
 */
public interface BlockType<T extends BlockBehavior> extends Identified {

    /**
     * Gets the block behavior associated with this block type.
     *
     * @return the block behavior.
     */
    T getBlockBehavior();

    /**
     * Gets the properties of this block type.
     *
     * @return an unmodifiable view of the map of block property types.
     */
    @UnmodifiableView
    Map<String, BlockPropertyType<?>> getProperties();

    /**
     * Check if the block type has the specified property.
     *
     * @param name the property name.
     *
     * @return true if the block type has the property, false otherwise.
     */
    default boolean hasProperty(String name) {
        return getProperties().containsKey(name);
    }

    /**
     * Check if the block type has the specified property.
     *
     * @param propertyType the property type.
     *
     * @return true if the block type has the property, false otherwise.
     */
    default boolean hasProperty(BlockPropertyType<?> propertyType) {
        return getProperties().containsKey(propertyType.getName());
    }

    /**
     * Gets all possible states of this block type.
     *
     * @return an unmodifiable view of the collection of all block states.
     */
    @UnmodifiableView
    Collection<BlockState> getAllStates();

    /**
     * Gets the hash map which contains all states of this block type.
     * The key is block state's hash.
     *
     * @return an unmodifiable view of the map of block states by their hash.
     */
    @UnmodifiableView
    Map<Integer, BlockState> getBlockStateHashMap();

    /**
     * Gets the special value map of block states.
     * The key is block state's special value.
     *
     * @return an unmodifiable view of the map of block states by their special values.
     */
    @UnmodifiableView
    Map<Long, BlockState> getSpecialValueMap();

    /**
     * Gets the number of bits used for special values.
     *
     * @return the number of special value bits.
     */
    byte getSpecialValueBits();

    /**
     * Gets the default state of this block type.
     *
     * @return the default block state.
     */
    BlockState getDefaultState();

    /**
     * Gets the item identifier for this block type.
     *
     * @return the item identifier.
     */
    default Identifier getItemIdentifier() {
        return getItemType().getIdentifier();
    }

    /**
     * Gets the item type associated with this block type.
     *
     * @return the item type.
     */
    ItemType<?> getItemType();

    /**
     * Creates a block state from the given list of property values.
     *
     * @param propertyValues the list of property values.
     *
     * @return the block state, or null if the given property values is invalid
     */
    BlockState ofState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    /**
     * Creates a block state, the property values are copied from the given block state. <br>
     * The given block state's type must have same property types as this block type.
     *
     * @param other the block state to copy property values from.
     *
     * @return the block state, or null if the given block state is invalid
     */
    default BlockState copyPropertyValuesFrom(BlockState other) {
        return ofState(other.getPropertyValues().values().toArray(BlockPropertyType.BlockPropertyValue<?, ?, ?>[]::new));
    }

    /**
     * Creates a block state from the given block state hash.
     *
     * @param blockStateHash the block state hash.
     *
     * @return the block state, or null if the given block state hash is invalid
     */
    default BlockState ofState(int blockStateHash) {
        return getBlockStateHashMap().get(blockStateHash);
    }

    /**
     * Creates a block state from the given array of property values.
     *
     * @param propertyValues the array of property values.
     *
     * @return the block state, or null if the given property values is invalid
     */
    default BlockState ofState(BlockPropertyType.BlockPropertyValue<?, ?, ?>... propertyValues) {
        return ofState(List.of(propertyValues));
    }

    /**
     * Registers this block type to the given registry.
     *
     * @param registry the block type registry.
     */
    default void register(SimpleMappedRegistry<Identifier, BlockType<?>> registry) {
        registry.register(getIdentifier(), this);
    }

    /**
     * Registers all block states of this block type to the given palette.
     *
     * @param registry the block state hash palette.
     */
    default void register(IntMappedRegistry<BlockState> registry) {
        for (var s : getBlockStateHashMap().values()) {
            registry.register(s.blockStateHash(), s);
        }
    }

    /**
     * Gets the set of tags associated with this block type.
     *
     * @return an unmodifiable set of block tags.
     */
    @Unmodifiable
    Set<BlockTag> getBlockTags();

    /**
     * Gets the material of this block type.
     *
     * @return the material.
     */
    Material getMaterial();

    /**
     * Checks if this block type has the specified tag.
     *
     * @param blockTag the block tag.
     *
     * @return true if the block type has the tag, false otherwise.
     */
    default boolean hasBlockTag(BlockTag blockTag) {
        return getBlockTags().contains(blockTag);
    }
}
