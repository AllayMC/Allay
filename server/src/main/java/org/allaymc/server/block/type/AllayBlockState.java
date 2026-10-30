package org.allaymc.server.block.type;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.server.network.ProtocolInfo;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;

/**
 * {@code AllayBlockState} represents one block type's property combination together
 * with its NBT, palette hash, and packed property-index key. Block-type
 * construction creates the canonical states, and property updates resolve those
 * existing objects rather than mutating the state that received the request.
 * Equality deliberately uses object identity, while hashing returns {@code
 * blockStateHash}.
 *
 * <p>
 * The convenience constructor derives {@code blockStateTag}, {@code
 * blockStateHash}, and {@code specialValue} from one ordered property array. The
 * record retains that array, including through its generated accessor, so callers
 * must not modify it after construction: changing array entries would leave those
 * three cached representations inconsistent. Direct construction alone does not
 * register a canonical palette entry.
 *
 * @param blockType owning block type
 * @param blockPropertyValues ordered property wrappers; the array remains shared
 * and must stay unchanged after construction
 * @param blockStateTag precomputed serialized state compound
 * @param blockStateHash signed palette hash
 * @param specialValue packed property-index key
 *
 * @author daoge_cmd
 */
public record AllayBlockState(
        BlockType<?> blockType,
        BlockPropertyType.BlockPropertyValue<?, ?, ?>[] blockPropertyValues,
        NbtMap blockStateTag,
        int blockStateHash,
        long specialValue
) implements BlockState {
    /**
     * Creates a state and derives its NBT, hash, and packed key from the supplied
     * property array. The array is retained without copying, so the caller must
     * preserve both its entries and their order after construction.
     */
    public AllayBlockState(BlockType<?> blockType, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
        this(
                blockType, propertyValues, buildBlockStateTag(blockType, propertyValues),
                HashUtils.computeBlockStateHash(blockType.getIdentifier(), Arrays.stream(propertyValues).toList()),
                AllayBlockType.computeSpecialValue(propertyValues)
        );
    }

    /**
     * Builds the versioned NBT compound from the block identifier and serialized
     * property values. Property names are sorted, and duplicate names retain the
     * last value; the resulting compound records the supported block-state version
     * alongside the state properties.
     */
    private static NbtMap buildBlockStateTag(BlockType<?> blockType, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
        // Sort property names so serialized NBT uses a consistent ordering even
        // when the supplied wrapper array has another order.
        var states = new TreeMap<String, Object>();
        for (var value : propertyValues) {
            states.put(value.getPropertyType().getName(), value.getSerializedValue());
        }

        return NbtMap.builder()
                .putString("name", blockType.getIdentifier().toString())
                .putCompound("states", NbtMap.fromMap(states))
                .putInt("version", ProtocolInfo.BLOCK_STATE_VERSION_NUM)
                .build();
    }

    /**
     * Returns the versioned NBT compound cached when this state was constructed.
     */
    @Override
    public NbtMap getBlockStateNBT() {
        return blockStateTag;
    }

    /**
     * Creates one item from the owning block type's associated item type. This
     * conversion uses default item settings and does not copy this state's property
     * combination into the item.
     */
    @Override
    public ItemStack toItemStack() {
        return blockType.getItemType().createItemStack(1);
    }

    /**
     * Returns the block type retained as the owner of this state.
     */
    @Override
    public BlockType<?> getBlockType() {
        return blockType;
    }

    /**
     * Returns a newly built unmodifiable map of this state's property wrappers. The
     * container is independent, but its keys and wrapper values are the same
     * objects retained in {@code blockPropertyValues}.
     */
    @Override
    public @UnmodifiableView Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues() {
        var hashMap = new LinkedHashMap<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
        for (var blockPropertyValue : blockPropertyValues) {
            hashMap.put(blockPropertyValue.getPropertyType(), blockPropertyValue);
        }

        return Collections.unmodifiableMap(hashMap);
    }

    /**
     * Returns the raw value whose property object is identical to {@code p}.
     * Matching a name is insufficient, and an unsupported property throws {@code
     * IllegalArgumentException} rather than returning a default.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getPropertyValue(PROPERTY p) {
        for (var property : blockPropertyValues) {
            if (property.getPropertyType() == p) {
                return (DATATYPE) property.getValue();
            }
        }

        throw new IllegalArgumentException("Property " + p + " is not supported by block " + blockType.getIdentifier());
    }

    /**
     * Returns the indexed state after replacing the wrapper with the same property
     * identity. Other wrappers retain their order; an unsupported property throws
     * {@code IllegalArgumentException}, and this state's retained array remains
     * unchanged.
     */
    @Override
    public BlockState setPropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue) {
        var newPropertyValues = new BlockPropertyType.BlockPropertyValue<?, ?, ?>[this.blockPropertyValues.length];
        var succeed = false;
        for (int i = 0; i < blockPropertyValues.length; i++) {
            if (blockPropertyValues[i].getPropertyType() == propertyValue.getPropertyType()) {
                succeed = true;
                newPropertyValues[i] = propertyValue;
            } else {
                newPropertyValues[i] = blockPropertyValues[i];
            }
        }

        if (!succeed) {
            throw new IllegalArgumentException("Property " + propertyValue.getPropertyType() + " is not supported by block " + blockType.getIdentifier());
        }

        return getNewBlockState(newPropertyValues);
    }

    /**
     * Converts the supplied raw value through {@code property} and selects the
     * corresponding indexed state. The property must match a retained domain by
     * identity, and conversion failures follow that property's implementation.
     */
    @Override
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> BlockState setPropertyValue(PROPERTY property, DATATYPE value) {
        return setPropertyValue(property.createValue(value));
    }

    /**
     * Returns the indexed state after applying the supplied property wrappers to
     * the current combination. The replacement array follows the original
     * property's order so packed keys keep the layout expected by the owning type.
     * Properties omitted from the request retain their current wrapper.
     *
     * <p>
     * Each original property consumes only its first matching request entry. The
     * success count therefore rejects both unsupported domains and duplicate
     * request entries before lookup, rather than silently ignoring a value. Failure
     * leaves the original state array untouched and identifies the unmatched
     * property names in the exception.
     */
    @Override
    public BlockState setPropertyValues(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        var newPropertyValues = new BlockPropertyType.BlockPropertyValue<?, ?, ?>[this.blockPropertyValues.length];
        var succeedCount = 0;
        var succeed = new boolean[propertyValues.size()];
        for (int i = 0; i < blockPropertyValues.length; i++) {
            var found = false;
            for (int j = 0; j < propertyValues.size(); j++) {
                if (propertyValues.get(j).getPropertyType() == blockPropertyValues[i].getPropertyType()) {
                    succeedCount++;
                    succeed[j] = true;
                    newPropertyValues[i] = propertyValues.get(j);
                    found = true;
                    break;
                }
            }
            if (!found) newPropertyValues[i] = blockPropertyValues[i];
        }

        if (succeedCount != propertyValues.size()) {
            var errorMsgBuilder = new StringBuilder("Properties ");
            for (int i = 0; i < propertyValues.size(); i++) {
                if (!succeed[i]) {
                    errorMsgBuilder.append(propertyValues.get(i).getPropertyType().getName());
                    if (i != propertyValues.size() - 1)
                        errorMsgBuilder.append(", ");
                }
            }

            errorMsgBuilder.append(" are not supported by block " + blockType.getIdentifier());
            throw new IllegalArgumentException(errorMsgBuilder.toString());
        }

        return getNewBlockState(newPropertyValues);
    }

    /**
     * Resolves an updated combination through the owning type's precomputed state
     * indexes. The supplied array must preserve the type's property order because
     * the packed key uses that order rather than sorting names. A reported width of
     * at most {@code 64} bits selects the packed-value map.
     *
     * <p>
     * Wider combinations use the palette hash instead, since a {@code long} cannot
     * preserve all property-index bits. The method returns the indexed canonical
     * object, or {@code null} when the selected index has no entry, without
     * allocating or registering a replacement state.
     */
    private BlockState getNewBlockState(BlockPropertyType.BlockPropertyValue<?, ?, ?>[] values) {
        var bits = blockType.getSpecialValueBits();
        if (bits <= 64) {
            var specialValueMap = blockType.getSpecialValueMap();
            Objects.requireNonNull(specialValueMap);
            return specialValueMap.get(AllayBlockType.computeSpecialValue(bits, values));
        } else {
            // Wider property domains cannot be represented by one long key, so use
            // the hash index to resolve the updated state.
            return blockType.getBlockStateHashMap().get(HashUtils.computeBlockStateHash(this.blockType.getIdentifier(), values));
        }
    }

    /**
     * Returns whether the supplied reference is this exact state object. Palette
     * construction supplies canonical instances, but separately constructed records
     * remain unequal even when their stored fields describe the same combination.
     */
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    /**
     * Returns the stored signed palette hash used to index this state.
     */
    @Override
    public int hashCode() {
        return blockStateHash;
    }
}
