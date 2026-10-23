package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveCleanNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Objects;

/**
 * {@code BlockEntityBaseComponentImpl} stores shared block-entity identity,
 * location, naming, and persistent custom data while coordinating component NBT
 * hooks. Its constructor records the type and dimension, and
 * {@code onInitFinish} loads NBT after component injection and listener
 * registration. Coordinates change within the existing dimension; loading NBT
 * does not attach the entity to a different chunk or world.
 *
 * <p>
 * {@code saveNBT} lets component handlers contribute first and then writes
 * authoritative base identity, position, and movable tags. {@code loadNBT}
 * dispatches to components before applying base fields and preserves omitted
 * base values. {@code saveCleanNBT} removes base coordinates and delegates
 * other location-dependent cleanup to components. These operations update
 * shared state incrementally without rollback; callers coordinate mutation, and
 * component handlers that contribute to a completed save must finish
 * synchronously during dispatch.
 *
 * @author daoge_cmd
 */
public class BlockEntityBaseComponentImpl implements BlockEntityBaseComponent {

    /**
     * {@code IDENTIFIER} identifies the default base provider. Type builders
     * supply that provider when no component with this identifier has been
     * explicitly configured.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_base_component");

    // These tags encode base identity, coordinates, naming, and plugin data;
    // base saving writes them after component hooks according to field
    // presence.
    /**
     * Persisted block-entity type name.
     */
    protected static final String TAG_ID = "id";
    /**
     * Persisted block x coordinate.
     */
    protected static final String TAG_X = "x";
    /**
     * Persisted block y coordinate.
     */
    protected static final String TAG_Y = "y";
    /**
     * Persisted block z coordinate.
     */
    protected static final String TAG_Z = "z";
    /**
     * Persisted piston-movable flag.
     */
    protected static final String TAG_IS_MOVABLE = "isMovable";
    /**
     * Optional custom display name.
     */
    protected static final String TAG_CUSTOM_NAME = "CustomName";
    /**
     * Persistent plugin data compound.
     */
    protected static final String TAG_PDC = "PDC";

    /**
     * Injected component-event dispatch manager.
     */
    @Manager
    protected ComponentManager manager;
    /**
     * Injected owning block entity.
     */
    @ComponentObject
    protected BlockEntity thisBlockEntity;

    /**
     * Type supplied by the creating factory.
     */
    @Getter
    protected BlockEntityType<?> blockEntityType;
    /**
     * {@code position} holds the current block coordinates and dimension.
     * Loading coordinates replaces this object while retaining its dimension,
     * and the generated getter exposes the current position object.
     */
    @Getter
    protected Position3ic position;
    /**
     * {@code customName} holds the optional display name. {@code null}
     * suppresses the base name tag on save, while an absent load tag leaves
     * this value unchanged.
     */
    @Getter
    @Setter
    protected String customName;
    /**
     * {@code persistentDataContainer} holds shared plugin data. Loading a
     * {@code "PDC"} compound clears then refills this container, while absence
     * leaves its existing entries unchanged.
     */
    @Getter
    @Setter
    protected PersistentDataContainer persistentDataContainer;

    /**
     * Creates base state at zero coordinates in the supplied dimension. The
     * creating type must already be assigned, and persistent-data types must be
     * registered. Initial NBT is deferred until completion hooks run after
     * component dependencies and listeners have been connected.
     */
    public BlockEntityBaseComponentImpl(BlockEntityInitInfo initInfo) {
        this.blockEntityType = initInfo.getBlockEntityType();
        this.position = new Position3i(0, 0, 0, initInfo.dimension());
        this.persistentDataContainer = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);
    }

    /**
     * Loads the initialization NBT after component injection and listener
     * setup. Component load handlers can therefore access their wired
     * dependencies; failures may leave earlier fields or components partially
     * initialized.
     */
    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        loadNBT(initInfo.nbt());
    }

    /**
     * Provides a no-op world-tick hook for specialized block-entity base
     * components.
     */
    public void tick(long currentTick) {
    }

    /**
     * Builds persisted state by combining component contributions with base
     * fields. Synchronous save handlers receive the builder first; identity,
     * coordinates, and {@code "isMovable"} then overwrite conflicting
     * contributions with current base values. {@code "CustomName"} is written
     * only when present and {@code "PDC"} only when nonempty, so those absent
     * base fields do not erase handler-written tags. The method returns a newly
     * built map but does not persist it, and callers must coordinate shared
     * component state while serialization runs.
     */
    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder();
        // Component contributions precede authoritative base tags so
        // conflicting component identity or coordinates cannot replace the base
        // component's saved location.
        manager.callEvent(new CBlockEntitySaveNBTEvent(builder));
        builder.putString(TAG_ID, blockEntityType.getName())
                .putInt(TAG_X, position.x())
                .putInt(TAG_Y, position.y())
                .putInt(TAG_Z, position.z())
                .putBoolean(TAG_IS_MOVABLE, true);
        if (customName != null) {
            builder.putString(TAG_CUSTOM_NAME, customName);
        }
        if (!persistentDataContainer.isEmpty()) {
            builder.put(TAG_PDC, persistentDataContainer.toNbt());
        }
        return builder.build();
    }

    /**
     * Builds relocation state from a normal save and removes base coordinates.
     * After {@code "x"}, {@code "y"}, and {@code "z"} are removed, synchronous
     * clean-save handlers remove their own location-dependent tags, such as
     * chest pairing. Other saved fields remain unless a handler changes them;
     * this method does not move the live block entity or alter its dimension.
     * The final map is built after dispatch, so asynchronous edits cannot
     * reliably contribute to the result.
     */
    @Override
    public NbtMap saveCleanNBT() {
        var builder = saveNBT().toBuilder();
        // Preserve the ordinary saved payload while removing location bindings;
        // components then strip their own coordinates from that same mutable
        // copy.
        builder.remove(TAG_X);
        builder.remove(TAG_Y);
        builder.remove(TAG_Z);
        manager.callEvent(new CBlockEntitySaveCleanNBTEvent(builder));
        return builder.build();
    }

    /**
     * Applies component NBT before updating the base name, coordinates, and
     * data. Absent expected tags preserve current base values rather than
     * resetting them. Coordinate changes create a new position with the
     * existing dimension, and a supplied {@code "PDC"} compound clears then
     * refills the existing container. Handlers may already have changed their
     * state when a later step fails; this operation has no rollback. Replacing
     * the load event's map does not change the original {@code nbt} map used by
     * this base-field pass.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        // Load component state before base fields, preserving the established
        // callback view of the old base position until synchronous handlers
        // finish.
        manager.callEvent(new CBlockEntityLoadNBTEvent(nbt));
        nbt.listenForString(TAG_CUSTOM_NAME, customName -> this.customName = customName);

        var pos = new Position3i(this.position);
        nbt.listenForInt(TAG_X, value -> pos.x = value);
        nbt.listenForInt(TAG_Y, value -> pos.y = value);
        nbt.listenForInt(TAG_Z, value -> pos.z = value);
        this.position = pos;

        nbt.listenForCompound(TAG_PDC, customNbt -> {
            // A present compound replaces plugin data rather than merging with
            // stale keys; a missing compound never enters this replacement
            // path.
            this.persistentDataContainer.clear();
            this.persistentDataContainer.putAll(customNbt);
        });
    }

    /**
     * Applies player-supplied NBT through the ordinary base loading path. This
     * default implementation performs no additional authorization or validation
     * and ignores {@code player}; specialized components must override it when
     * player edits require narrower accepted fields.
     */
    public void applyPlayerChange(EntityPlayer player, NbtMap nbt) {
        loadNBT(nbt);
    }

    /**
     * Returns whether player viewers should receive this block entity's data.
     * The base implementation always permits sending; {@code viewBlockEntity}
     * consults this hook before encoding, allowing specialized bases to
     * suppress updates.
     */
    public boolean sendToClient() {
        return true;
    }

    /**
     * Sends current state immediately to actual-player loaders of the
     * containing chunk. The chunk must already be loaded, and each player's
     * normal block-entity view path can still suppress sending through
     * {@code sendToClient}.
     */
    public void sendBlockEntityToViewers() {
        sendBlockEntityToViewers(true);
    }

    /**
     * Sends state to actual-player loaders immediately or through a chunk task.
     * The containing chunk must be loaded at invocation or a
     * {@code NullPointerException} is thrown. Deferred delivery uses the
     * selected chunk's loaders when its task runs and the live block entity,
     * rather than capturing recipients or serialized state now.
     */
    public void sendBlockEntityToViewers(boolean immediately) {
        var pos = getPosition();
        var chunk = pos.dimension().getChunkManager().getChunkByDimensionPos(pos.x(), pos.z());
        Objects.requireNonNull(chunk, "The chunk located at pos " + pos + " is not loaded!");
        if (immediately) {
            chunk.forEachChunkLoaders(loader -> {
                if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                    player.getController().viewBlockEntity(thisBlockEntity);
                }
            });
        } else {
            // Defer traversal to the chosen chunk's task queue so membership is
            // evaluated during processing; the captured entity remains live
            // until delivery.
            chunk.forEachChunkLoaderLater(loader -> {
                if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                    player.getController().viewBlockEntity(thisBlockEntity);
                }
            });
        }
    }
}
