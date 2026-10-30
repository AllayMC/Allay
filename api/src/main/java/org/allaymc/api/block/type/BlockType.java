package org.allaymc.api.block.type;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockTag;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.utils.identifier.Identified;
import org.allaymc.api.utils.identifier.Identifier;
import org.jetbrains.annotations.Unmodifiable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * {@code BlockType} identifies a block family with shared behavior, property
 * domains, tags, and an associated item type. Its state indexes resolve canonical
 * property combinations by palette hash or packed property indices, while the
 * default state supplies the family's configured initial combination.
 *
 * @author daoge_cmd
 */
public interface BlockType<T extends BlockBehavior> extends Identified {

    /**
     * Returns the behavior instance shared by this type's block states.
     */
    T getBlockBehavior();

    /**
     * Returns the property domains keyed by their serialized names. Callers must
     * treat the returned view as read-only because state enumeration and
     * packed-index layout depend on the domains prepared during type construction.
     */
    @UnmodifiableView
    Map<String, BlockPropertyType<?>> getProperties();

    /**
     * Returns whether this type declares a property with the supplied serialized
     * name.
     */
    default boolean hasProperty(String name) {
        return getProperties().containsKey(name);
    }

    /**
     * Returns whether this type declares the supplied property's serialized name.
     * This check does not establish property-instance identity, so a positive
     * result alone does not guarantee that state property access accepts that
     * instance.
     */
    default boolean hasProperty(BlockPropertyType<?> propertyType) {
        return getProperties().containsKey(propertyType.getName());
    }

    /**
     * Returns the states represented in this type's hash index. Callers must treat
     * this collection as read-only; it describes the constructed domain rather than
     * a way to register or remove palette entries.
     */
    @UnmodifiableView
    Collection<BlockState> getAllStates();

    /**
     * Returns this type's state index keyed by signed palette hashes. Callers must
     * leave the index unchanged because default-state selection and state lookup
     * use the entries prepared during construction.
     */
    @UnmodifiableView
    Map<Integer, BlockState> getBlockStateHashMap();

    /**
     * Returns this type's index keyed by packed property indices, or {@code null}
     * when its reported width exceeds {@code 64} bits. Callers must treat a present
     * index as read-only and use hash lookup for wider domains.
     */
    @UnmodifiableView
    Map<Long, BlockState> getSpecialValueMap();

    /**
     * Returns the combined bit width reserved for this type's packed property
     * indices.
     */
    byte getSpecialValueBits();

    /**
     * Returns the initial state chosen when this block type was constructed.
     */
    BlockState getDefaultState();

    /**
     * Returns the registered identifier of this block type's associated item type.
     */
    default Identifier getItemIdentifier() {
        return getItemType().getIdentifier();
    }

    /**
     * Returns the item type associated with this block family for item creation.
     */
    ItemType<?> getItemType();

    /**
     * Resolves a registered state from the supplied complete serialized property
     * values. Missing or invalid combinations can return {@code null}; this
     * operation does not fill omitted properties with defaults or require
     * property-instance identity.
     */
    BlockState ofState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    /**
     * Resolves this type's state using the other state's serialized property
     * values. The property names and encodings must describe a registered
     * combination of this type; incompatible combinations can return {@code null}.
     */
    default BlockState copyPropertyValuesFrom(BlockState other) {
        return ofState(other.getPropertyValues().values().toArray(BlockPropertyType.BlockPropertyValue<?, ?, ?>[]::new));
    }

    /**
     * Returns this type's state indexed by the supplied signed palette hash. An
     * absent hash returns {@code null}, and this lookup does not reinterpret the
     * number as a packed property-index key.
     */
    default BlockState ofState(int blockStateHash) {
        return getBlockStateHashMap().get(blockStateHash);
    }

    /**
     * Resolves a registered state from the complete supplied property array.
     * Omitted properties are not defaulted; an unresolved combination can return
     * {@code null}, while a {@code null} array element is rejected when
     * constructing the list.
     */
    default BlockState ofState(BlockPropertyType.BlockPropertyValue<?, ?, ?>... propertyValues) {
        return ofState(List.of(propertyValues));
    }

    /**
     * Returns the category tags associated with this block type. Callers must treat
     * the returned set as read-only because tag checks consult it directly and do
     * not keep a separate snapshot.
     */
    @Unmodifiable
    Set<BlockTag> getBlockTags();

    /**
     * Returns whether this block type's tag set contains the supplied category.
     */
    default boolean hasBlockTag(BlockTag blockTag) {
        return getBlockTags().contains(blockTag);
    }
}
