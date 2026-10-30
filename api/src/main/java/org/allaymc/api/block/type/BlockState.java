package org.allaymc.api.block.type;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;

/**
 * {@code BlockState} represents a block type's selected property combination and
 * exposes its palette hash, serialized NBT, behavior, and metadata. Registered
 * states are canonical palette entries, so property updates select existing state
 * objects rather than changing the original state's property values.
 *
 * @author daoge_cmd
 */
public interface BlockState {

    /**
     * Returns the block type that owns this state's property combination.
     */
    BlockType<?> getBlockType();

    /**
     * Returns the signed 32-bit palette hash computed from the block identifier and
     * serialized property values. The hash is not a mathematical guarantee against
     * collisions; callers use the registered palette entry for state identity.
     */
    int blockStateHash();

    /**
     * Returns the property-index key packed in the owning type's property order.
     * The key is meaningful within that type when its property widths fit in a
     * {@code long}; wider domains require hash-based lookup instead.
     */
    long specialValue();

    /**
     * Returns an unmodifiable map associating each property domain with its
     * selected wrapper. The map retains the property and wrapper objects, so
     * callers use those exact property instances when querying or updating this
     * state.
     */
    @UnmodifiableView
    Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues();

    /**
     * Selects the canonical state after replacing the supplied properties and
     * retaining the others. Each property must be supported by identity and appear
     * once; unsupported or duplicate property entries throw {@code
     * IllegalArgumentException}.
     */
    BlockState setPropertyValues(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues);

    /**
     * Returns the raw value for the exact supplied property instance. A separate
     * property with the same name is not sufficient; an unsupported property
     * produces {@code IllegalArgumentException}.
     */
    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getPropertyValue(PROPERTY property);

    /**
     * Selects the canonical state after replacing one supported property wrapper.
     * The original state stays unchanged, and an unsupported property instance
     * throws {@code IllegalArgumentException} rather than being matched by its
     * name.
     */
    BlockState setPropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue);

    /**
     * Converts the raw value through its property domain and selects the resulting
     * canonical state. The property must be supported by identity, and invalid raw
     * values follow that property's conversion failure behavior.
     */
    <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> BlockState setPropertyValue(PROPERTY property, DATATYPE value);

    /**
     * Returns the state hash as an unsigned 32-bit value stored in a {@code long}.
     */
    default long unsignedBlockStateHash() {
        return Integer.toUnsignedLong(blockStateHash());
    }

    /**
     * Returns the serialized block identifier, property values, and state-version
     * NBT.
     */
    NbtMap getBlockStateNBT();

    /**
     * Creates one item of the item type associated with this block type. The
     * conversion uses the item's default construction settings and does not promise
     * to encode this state's individual property values.
     */
    ItemStack toItemStack();

    /**
     * Returns the behavior shared by states of the owning block type.
     */
    default BlockBehavior getBehavior() {
        return getBlockType().getBlockBehavior();
    }

    /**
     * Returns the metadata selected for this state by its owning behavior.
     */
    default BlockStateData getBlockStateData() {
        return getBehavior().getBlockStateData(this);
    }
}
