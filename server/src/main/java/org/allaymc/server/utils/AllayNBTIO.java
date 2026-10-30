package org.allaymc.server.utils;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.updater.block.BlockStateUpdaters;
import org.allaymc.updater.item.ItemStateUpdaters;
import org.cloudburstmc.nbt.NbtMap;

import java.util.ArrayList;
import java.util.Objects;

/**
 * {@code AllayNBTIO} reconstructs registered blocks, item stacks, entities, and block entities
 * from stored NBT. These readers depend on initialized type registries and apply different
 * failure policies: blocks can select the unknown state, items can become air, and unresolved
 * entity types return {@code null}.
 *
 * <p>
 * Block decoding compares the stored version with the supported protocol version before
 * resolving identity. Older data passes through the block updater, and missing state properties
 * are filled from the resolved type's defaults so partial property maps can still identify a
 * state. Future versions, unresolved block types, and a rejected state selection use the unknown
 * block fallback; other malformed property data can still throw during conversion.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayNBTIO implements NBTIO {
    /**
     * Decodes a stored block state using version updates and registered property definitions.
     * A missing version behaves as zero, while a future version returns the unknown block state
     * without attempting an updater conversion.
     *
     * <p>
     * After identity lookup, absent properties receive their default serialized values before
     * state selection. Unknown types or a {@code null} selected state use the unknown fallback;
     * malformed identifiers, undeclared properties, and invalid property conversion can still
     * throw rather than being suppressed by that fallback.
     */
    @Override
    public BlockState fromBlockStateNBT(NbtMap nbt) {
        // A missing version is treated as old data so the updater can supply the current
        // representation instead of interpreting an unversioned map as already compatible.
        var version = nbt.getInt("version", 0);
        if (version > ProtocolInfo.BLOCK_STATE_VERSION_NUM) {
            // The updater does not downgrade future formats. Use the unknown state so newer
            // serialized fields are not interpreted using this runtime's older property schema.
            log.warn("Block state version is too new: {}", nbt);
            return BlockTypes.UNKNOWN.getDefaultState();
        }

        if (version < ProtocolInfo.BLOCK_STATE_VERSION_NUM) {
            // Bring older names and properties to the supported format before registry
            // lookup, allowing persisted states from earlier protocol versions to resolve.
            nbt = BlockStateUpdaters.updateBlockState(nbt, ProtocolInfo.BLOCK_STATE_UPDATER.getVersion());
        }

        var blockType = Registries.BLOCKS.get(new Identifier(nbt.getString("name")));
        if (blockType == null) {
            // No registered type can interpret these properties. Retain a valid runtime
            // state through the unknown fallback and report the unresolved serialized identity.
            log.warn("Unknown block type {}", nbt.getString("name"));
            return BlockTypes.UNKNOWN.getDefaultState();
        }

        // Persisted states can omit properties added by newer definitions. Fill only absent
        // names from the type's defaults so supplied values retain their serialized meaning.
        var states = nbt.getCompound("states");
        var builder = states.toBuilder();
        for (var entry : blockType.getDefaultState().getPropertyValues().entrySet()) {
            if (builder.containsKey(entry.getKey().getName())) {
                continue;
            }

            builder.put(entry.getKey().getName(), entry.getValue().getSerializedValue());
        }
        states = builder.build();

        var blockPropertyValues = new ArrayList<BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
        for (var entry : states.entrySet()) {
            blockPropertyValues.add(blockType.getProperties().get(entry.getKey()).tryCreateValue(entry.getValue()));
        }

        var blockState = blockType.ofState(blockPropertyValues);
        if (blockState == null) {
            // A property combination without a runtime state cannot enter chunk storage.
            // Substitute the unknown state while reporting the original serialized block data.
            log.warn("Invalid block state {}", nbt);
            return BlockTypes.UNKNOWN.getDefaultState();
        }

        return blockState;
    }

    /**
     * Updates item NBT and creates a registered stack with its count, metadata, and extra tag.
     * Missing count defaults to one; any thrown failure is logged and replaced with the shared
     * air stack, including invalid or unknown item identities.
     */
    @Override
    public ItemStack fromItemStackNBT(NbtMap nbt) {
        try {
            // Stored items can use older names and metadata layouts. Update them before
            // looking up the current item type so its constructor receives the supported schema.
            nbt = ItemStateUpdaters.updateItemState(nbt, ProtocolInfo.ITEM_STATE_UPDATER.getVersion());
            int count = nbt.getByte("Count", (byte) 1);
            int meta = nbt.getShort("Damage");
            var name = nbt.getString("Name");
            var itemType = Objects.requireNonNull(Registries.ITEMS.get(new Identifier(name)), "Unknown item type " + name + " while loading container items!");
            return itemType.createItemStack(
                    ItemStackInitInfo
                            .builder()
                            .count(count)
                            .meta(meta)
                            .extraTag(nbt.getCompound("tag"))
                            .build()
            );
        } catch (Throwable t) {
            // One unreadable item must not supply an invalid stack to its container reader.
            // Report the failing conversion and use the shared air value for that entry.
            log.error("Failed to load item from NBT", t);
            return ItemAirStack.AIR_STACK;
        }
    }

    /**
     * Creates an entity in {@code dimension} from its stored registered identifier. Unknown entity
     * types are logged and return {@code null}; malformed identifiers and entity-construction
     * failures still propagate rather than using that missing-type result.
     */
    @Override
    public Entity fromEntityNBT(Dimension dimension, NbtMap nbt) {
        var identifier = new Identifier(nbt.getString("identifier"));
        var entityType = Registries.ENTITIES.get(identifier);
        if (entityType == null) {
            // No factory exists for this stored identity. Return an absent entity so the
            // owning loader can omit it rather than constructing an unrelated registered type.
            log.warn("Unknown entity type {}", identifier);
            return null;
        }

        return entityType.createEntity(EntityInitInfo.builder().dimension(dimension).nbt(nbt).build());
    }

    /**
     * Creates a block entity in {@code dimension} from its stored {@code "id"}. Unknown types are
     * logged and return {@code null}, while failures inside a resolved type's constructor still
     * propagate to the owning loader.
     */
    @Override
    public BlockEntity fromBlockEntityNBT(Dimension dimension, NbtMap nbt) {
        var id = nbt.getString("id");
        var blockEntityType = Registries.BLOCK_ENTITIES.get(id);
        if (blockEntityType == null) {
            // No block-entity factory recognizes this stored ID. Return an absent value so
            // the owning loader can skip that record without inventing another entity type.
            log.warn("Unknown block entity type: {}", id);
            return null;
        }

        return blockEntityType.createBlockEntity(BlockEntityInitInfo.builder().dimension(dimension).nbt(nbt).build());
    }
}
