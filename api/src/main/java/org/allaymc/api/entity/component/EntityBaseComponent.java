package org.allaymc.api.entity.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityState;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.entity.property.type.EntityPropertyType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.event.entity.EntityMoveEvent;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.pdc.PersistentDataHolder;
import org.allaymc.api.player.LoginData;
import org.allaymc.api.player.PlayerStorage;
import org.allaymc.api.primitiveshape.PrimitiveShape;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.manager.EntityManager;
import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;
import org.allaymc.api.world.storage.WorldStorage;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * {@code EntityBaseComponent} exposes the lifecycle, identity, placement,
 * persistence, and viewer-facing behavior common to entity types. Entity
 * managers own dimension membership, while this component supplies movement
 * proposals, state queries, NBT data, tags, and property values used by
 * specialized components and client presentation.
 *
 * @author daoge_cmd
 */
public interface EntityBaseComponent extends EntityComponent, CommandSender, HasAABB, HasLongId, PersistentDataHolder, TaskCreator {

    /**
     * Returns the entity definition used to construct this component-backed
     * entity.
     */
    EntityType<? extends Entity> getEntityType();

    /**
     * Sends the current entity metadata to its registered viewers.
     */
    void broadcastState();

    /**
     * Returns the configurable entity name used for command and message
     * presentation.
     */
    String getDisplayName();

    /**
     * Sets the entity display name used for messages and command identity.
     */
    void setDisplayName(String displayName);

    /**
     * Returns the name tag used in entity metadata, or {@code null} when
     * absent. An empty string still counts as a present tag for the default
     * {@code hasNameTag} query.
     */
    String getNameTag();

    /**
     * Stores the name tag and broadcasts the resulting entity state. Ordinary
     * entities can use {@code null} to remove it, while specialized player
     * presentation has additional requirements for visible player name tags.
     */
    void setNameTag(String nameTag);

    /**
     * Returns whether the stored name tag reference is nonnull.
     */
    default boolean hasNameTag() {
        return getNameTag() != null;
    }

    /**
     * Returns whether entity metadata requests continuous name-tag display.
     */
    boolean isNameTagAlwaysShow();

    /**
     * Sets continuous name-tag display and broadcasts the updated entity
     * metadata. The common server implementation sends state even when the
     * value is unchanged, without adding or removing viewer membership.
     */
    void setNameTagAlwaysShow(boolean nameTagAlwaysShow);

    /**
     * Returns whether metadata marks this entity's body as invisible.
     */
    boolean isInvisible();

    /**
     * Sets the invisible-body flag and broadcasts entity state. This metadata
     * flag controls body presentation; equipment and effect particles have
     * their own presentation paths and are not removed from viewers by this
     * assignment.
     */
    void setInvisible(boolean invisible);

    /**
     * Returns whether ordinary movement preserves this entity's current
     * coordinates.
     */
    boolean isImmobile();

    /**
     * Sets whether ordinary movement preserves the entity's current
     * coordinates and broadcasts state. An immobile entity can still accept
     * orientation changes, while direct teleport and pre-spawn placement have
     * their own paths.
     */
    void setImmobile(boolean immobile);

    /**
     * Returns the multiplier applied to the base collision box and model
     * metadata. The common server component starts at {@code 1.0}, and its
     * setter stores later values without validating their range.
     */
    double getScale();

    /**
     * Sets the model and collision-box multiplier and broadcasts updated
     * entity metadata. The common server stores the supplied value without
     * validating positivity or finiteness, so callers must choose a scale
     * suitable for geometry consumers.
     */
    void setScale(double scale);

    /**
     * Returns the reused location object holding prior position coordinates.
     * Ordinary server movement updates only its {@code x}, {@code y}, and
     * {@code z}; its angles and dimension are not a complete
     * previous-placement history, and callers need a copy for stable
     * coordinate snapshots.
     */
    Location3dc getLastLocation();

    /**
     * Returns the current location through a read-only location interface. The
     * server retains and reuses its mutable location object, so callers must
     * copy it when they need a stable independent snapshot.
     */
    Location3dc getLocation();

    /**
     * Copies a location into an entity that is still in its despawned setup
     * state. The server rejects NaN coordinates and dispatches internal
     * location handling, but does not load chunks or insert the entity into a
     * dimension.
     *
     * @throws IllegalStateException if the entity is no longer eligible for pre-spawn placement
     */
    void setLocationBeforeSpawn(Location3dc location);

    /**
     * Returns the dimension currently retained by this entity's location.
     */
    Dimension getDimension();

    /**
     * Returns the world belonging to the entity's current dimension, or
     * {@code null} before a dimension is assigned. The result follows the
     * retained location rather than an independent world reference.
     */
    default World getWorld() {
        return getLocation().dimension() != null ? getLocation().dimension().getWorld() : null;
    }

    /**
     * Returns the entity scheduler advanced by its ordinary entity ticks.
     */
    Scheduler getScheduler();

    /**
     * Returns this entity's own ordinary tick count, independent of the
     * dimension's tick number. The count advances only when the entity is
     * ticked, so time in an unticked state does not increase it.
     */
    long getTick();

    /**
     * Returns the currently stored lifecycle state of this entity.
     */
    EntityState getState();

    /**
     * Returns whether this entity is currently queued for dimension insertion.
     */
    default boolean willBeSpawnedLater() {
        return getState() == EntityState.SPAWNED_LATER;
    }

    /**
     * Returns whether this entity is currently queued for dimension removal.
     */
    default boolean willBeDespawnedLater() {
        return getState() == EntityState.DESPAWNED_LATER;
    }

    /**
     * Returns whether the current lifecycle state is exactly
     * {@code EntityState.DEAD}.
     */
    default boolean isDead() {
        return getState() == EntityState.DEAD;
    }

    /**
     * Returns whether the current lifecycle state's spawned flag is set. Alive
     * and dead entities use spawned states, while pending removal already
     * reports {@code false} before the entity manager finishes detaching
     * membership.
     */
    default boolean isSpawned() {
        return getState().isSpawned();
    }

    /**
     * Returns whether the current lifecycle state is exactly
     * {@code EntityState.ALIVE}.
     */
    default boolean isAlive() {
        return getState() == EntityState.ALIVE;
    }

    /**
     * Returns whether the current lifecycle state is exactly
     * {@code EntityState.DESPAWNED}.
     */
    default boolean isDespawned() {
        return getState() == EntityState.DESPAWNED;
    }

    /**
     * Returns whether location setup and lifecycle state permit a spawn
     * request. The server requires a dimension, coordinates different from its
     * uninitialized sentinel, and the despawned state; this query does not
     * insert the entity or load its chunk.
     */
    boolean canBeSpawned();

    /**
     * {@code NETHER_PORTAL_TRANSITION_TICKS} sets the ordinary
     * survival/adventure player portal wait to eighty ticks; other common
     * portal users can reach this threshold immediately.
     */
    int NETHER_PORTAL_TRANSITION_TICKS = 80;
    /**
     * {@code PORTAL_COOLDOWN_TICKS} starts a three-hundred-tick reuse delay
     * before asynchronous portal preparation, preventing ordinary contact
     * processing from immediately starting another transfer.
     */
    int PORTAL_COOLDOWN_TICKS = 300;

    /**
     * Returns accumulated nether-portal contact time measured in entity ticks.
     */
    int getPortalTicks();

    /**
     * Sets accumulated nether-portal contact time measured in entity ticks.
     */
    void setPortalTicks(int ticks);

    /**
     * Returns the stored portal reuse delay measured in entity ticks.
     */
    int getPortalCooldown();

    /**
     * Sets the portal reuse delay measured in entity ticks.
     */
    void setPortalCooldown(int cooldown);

    /**
     * Returns whether a portal contact was marked for portal tick processing.
     */
    boolean isInNetherPortal();

    /**
     * Records portal contact for the next portal tick to consume.
     */
    void setInNetherPortal(boolean inNetherPortal);

    /**
     * Requests a teleport with reason
     * {@code EntityTeleportEvent.Reason.UNKNOWN}. The normal teleport event
     * can refuse the request, and a {@code true} result does not wait for
     * queued transfer between dimensions to finish.
     */
    default boolean teleport(Location3dc location) {
        return teleport(location, EntityTeleportEvent.Reason.UNKNOWN);
    }

    /**
     * Requests a spawned entity teleport after validating the initial target
     * dimension and dispatching its cancellable event. Same-dimension movement
     * runs directly; transfer to another dimension queues removal followed by
     * placement and addition, so acceptance does not establish completed
     * destination membership.
     */
    boolean teleport(Location3dc location, EntityTeleportEvent.Reason reason);

    /**
     * Copies the supplied integer location into a floating-point teleport
     * request with an unknown reason. The normal cancellation and
     * spawned-state rules apply, and any cross-dimension transfer remains
     * queued after acceptance.
     */
    default boolean teleport(Location3ic location) {
        return teleport(new Location3d(location));
    }

    /**
     * Returns the runtime identifier used for entity lookup and protocol
     * references.
     */
    long getRuntimeId();

    /**
     * Returns the runtime identifier used by long-keyed physics and lookup
     * services.
     */
    @Override
    default long getLongId() {
        return getRuntimeId();
    }

    /**
     * Returns the persistent identity associated with this entity. Ordinary
     * server entities store their identifier in the least significant
     * sixty-four {@code UUID} bits with zero upper bits; actual player identity can
     * instead come from its controller's login data.
     */
    UUID getUniqueId();

    /**
     * Returns the entity-relative collision box before the scale factor is
     * applied.
     */
    AABBdc getBaseAABB();

    /**
     * Returns a new entity-relative box with every base coordinate scaled.
     */
    default AABBdc getAABB() {
        var base = getBaseAABB();
        var scale = getScale();
        return new AABBd(
                base.minX() * scale,
                base.minY() * scale,
                base.minZ() * scale,
                base.maxX() * scale,
                base.maxY() * scale,
                base.maxZ() * scale
        );
    }

    /**
     * Returns a new scaled entity box translated to the current location.
     */
    default AABBdc getOffsetAABB() {
        return getAABB().translate(getLocation(), new AABBd());
    }

    /**
     * Returns the world-space entity box expanded for collision-contact
     * checks.
     */
    AABBdc getOffsetAABBForCollisionCheck();

    /**
     * Returns whether ordinary entity collision eligibility is enabled, with
     * the default enabled.
     */
    default boolean hasEntityCollision() {
        return true;
    }

    /**
     * Receives an entity-collision callback, with no behavior supplied by
     * default.
     */
    @ApiStatus.OverrideOnly
    default void onCollideWithEntity(Entity other) {
    }

    /**
     * Receives a block-interior callback, with no behavior supplied by
     * default.
     */
    @ApiStatus.OverrideOnly
    default void onInsideBlock(Block block) {
    }

    /**
     * Receives a block-collision callback, with no behavior supplied by
     * default.
     */
    @ApiStatus.OverrideOnly
    default void onCollideWithBlock(Block block) {
    }

    /**
     * Receives a projectile-impact callback, with no behavior supplied by
     * default.
     */
    @ApiStatus.OverrideOnly
    default void onProjectileHit(EntityProjectile projectile, Vector3dc hitPos) {
    }

    /**
     * Returns an unmodifiable live view of registered entity viewers. This
     * view shares membership with the entity rather than taking a snapshot, so
     * caller iteration must respect the same ticking context as viewer
     * changes.
     */
    @UnmodifiableView
    Set<WorldViewer> getViewers();

    /**
     * Invokes the consumer for each viewer in the current live set. The helper
     * does not snapshot or synchronize iteration, so consumers and callers
     * must avoid conflicting membership changes during traversal.
     */
    default void forEachViewers(Consumer<WorldViewer> consumer) {
        getViewers().forEach(consumer);
    }

    /**
     * Proposes movement through {@code EntityMoveEvent}, then applies the
     * event's destination subject to immobility handling. The event currently
     * does not support cancellation; the server can still return {@code false}
     * when an immobile proposal produces no change.
     */
    @ApiStatus.OverrideOnly
    boolean trySetLocation(Location3dc newLocation);

    /**
     * Registers a viewer and sends the entity followed by its current state.
     * The common implementation sends both presentations even when the viewer
     * was already registered, and this operation does not change dimension
     * membership.
     */
    void spawnTo(WorldViewer viewer);

    /**
     * Presents the entity to each supplied viewer using the single-viewer
     * operation. Registration and packets occur in iteration order; a failure
     * can leave earlier viewers registered and presented without completing
     * the collection.
     */
    default void spawnTo(Collection<? extends WorldViewer> viewers) {
        viewers.forEach(this::spawnTo);
    }

    /**
     * Removes a viewer from membership and asks that viewer to remove the
     * entity. The common implementation sends the removal even when the viewer
     * was not registered, while the entity remains in its dimension.
     */
    void despawnFrom(WorldViewer viewer);

    /**
     * Removes presentation from each supplied viewer through the single-viewer
     * operation. The entity remains in its dimension, and a failure can leave
     * later viewers untouched after earlier removal calls completed.
     */
    default void despawnFrom(Collection<? extends WorldViewer> viewers) {
        viewers.forEach(this::despawnFrom);
    }

    /**
     * Removes this entity's presentation from every currently registered
     * viewer. This operation clears viewer membership without removing
     * dimension or physics membership, and a viewer failure can interrupt the
     * iteration after preceding removals.
     */
    void despawnFromAll();

    /**
     * Requests queued removal from the current dimension using an empty
     * completion callback. Lifecycle transition rules can reject the request,
     * and normal return does not mean that manager membership has already been
     * detached.
     */
    default void remove() {
        remove(() -> {
        });
    }

    /**
     * Requests dimension removal and invokes the supplied callback after the
     * queued removal completes normally. The server requires a nonnull
     * callback; rejected lifecycle transitions or failures before completion
     * do not invoke it, and prior removal side effects are not rolled back.
     */
    void remove(Runnable callback);

    /**
     * Builds entity NBT from component contributions and common persisted
     * fields. This operation produces a compound without writing storage, and
     * numeric values use the entity NBT representation, including {@code float}
     * position and rotation and the ordinary sixty-four-bit unique identifier.
     */
    NbtMap saveNBT();

    /**
     * Applies NBT through component handlers and common-field loading without
     * a rollback boundary. Missing fields generally retain current values,
     * tags merge, and persistence-sensitive setters or effect handlers can
     * produce their usual events and side effects during loading.
     */
    void loadNBT(NbtMap nbt);

    /**
     * Receives a splash-water callback, with no behavior supplied by default.
     */
    @ApiStatus.OverrideOnly
    default void onSplash() {
    }


    /**
     * Returns ninety percent of the scaled box height as the default eye
     * offset.
     */
    default double getEyeHeight() {
        // Approximate the eye position from ninety percent of the scaled body height.
        return (getAABB().maxY() - getAABB().minY()) * 0.9;
    }

    /**
     * Returns whether the chunk at the floored entity coordinates is active.
     * Activity follows the dimension's chunk-loader ticking radius and can
     * guide lower-frequency AI work; it does not itself change lifecycle
     * membership.
     */
    default boolean isActive() {
        var loc = getLocation();
        int cx = (int) Math.floor(loc.x()) >> 4;
        int cz = (int) Math.floor(loc.z()) >> 4;
        return getDimension().getChunkManager().isChunkActive(cx, cz);
    }

    /**
     * Returns whether the chunk derived from the current location is loaded.
     * This helper truncates coordinates to integers before shifting to chunk
     * coordinates, which differs from flooring for negative fractional
     * positions near chunk boundaries.
     */
    default boolean isCurrentChunkLoaded() {
        var loc = getLocation();
        var cx = (int) loc.x() >> 4;
        var cz = (int) loc.z() >> 4;
        return loc.dimension().getChunkManager().isChunkLoaded(cx, cz);
    }

    /**
     * Returns whether the current height lies within the dimension's allowed Y
     * range.
     */
    default boolean isYInRange() {
        var loc = getLocation();
        return loc.dimension().isYInRange(loc.y());
    }

    /**
     * Returns whether the current height is in range and its derived chunk is
     * loaded.
     */
    default boolean isInWorld() {
        return isYInRange() && isCurrentChunkLoaded();
    }

    /**
     * Returns the loaded chunk derived from the current location, or
     * {@code null} when unavailable. Coordinates are truncated before chunk
     * conversion, so negative fractional positions follow this helper's
     * truncation behavior rather than floor-based chunk selection.
     */
    default Chunk getCurrentChunk() {
        var loc = getLocation();
        var cx = (int) loc.x() >> 4;
        var cz = (int) loc.z() >> 4;
        return loc.dimension().getChunkManager().getChunk(cx, cz);
    }

    /**
     * Returns the cardinal horizontal face selected from normalized yaw in
     * degrees. The four sectors use half-open ninety-degree intervals centered
     * on south, west, north, and east, so boundary angles deterministically
     * belong to the next sector.
     */
    default BlockFace getHorizontalFace() {
        // Normalize yaw before selecting half-open cardinal sectors so negative
        // angles and equivalent full rotations select the same horizontal face.
        var rotation = getLocation().yaw() % 360;
        if (rotation < 0) rotation += 360.0;

        if (45 <= rotation && rotation < 135) {
            return BlockFace.WEST;
        } else if (135 <= rotation && rotation < 225) {
            return BlockFace.NORTH;
        } else if (225 <= rotation && rotation < 315) {
            return BlockFace.EAST;
        } else {
            return BlockFace.SOUTH;
        }
    }

    /**
     * Sends an entity action to viewers through their action presentation
     * hooks.
     */
    void applyAction(EntityAction action);

    /**
     * Sends a resource-pack animation request through the entity's viewer
     * hooks.
     */
    void applyAnimation(EntityAnimation animation);

    /**
     * Adds an entity tag and returns whether set membership changed.
     */
    boolean addTag(String tag);

    /**
     * Removes an entity tag and returns whether set membership changed.
     */
    boolean removeTag(String tag);

    /**
     * Returns whether the entity's tag set contains the supplied value.
     */
    boolean hasTag(String tag);

    /**
     * Returns an unmodifiable live view of the entity tag set. Changes through
     * the entity's tag operations remain visible, and callers must observe the
     * owning entity's mutation context when iterating that shared membership.
     */
    @UnmodifiableView
    Set<String> getTags();

    /**
     * Returns whether a spawned entity's expanded collision-check box
     * intersects block collision shapes. Unspawned states return {@code false}
     * without querying blocks, even when the retained location overlaps an
     * occupied block.
     */
    boolean checkBlockCollision();

    /**
     * Returns whether the sampled eye point lies in a water-tagged or
     * bubble-column shape. The test uses one eye-position sample rather than
     * checking the entire body box or every block intersected by the entity.
     */
    default boolean isEyesInWater() {
        var dim = getDimension();
        var eyeLoc = getLocation().add(0, getEyeHeight(), 0, new Vector3d());
        var eyesBlockState = dim.getBlockState(eyeLoc);

        return isWaterOrBubbleColumn(eyesBlockState) &&
               eyesBlockState.getBlockStateData().computeOffsetShape(MathUtils.floor(eyeLoc)).intersectsPoint(eyeLoc);
    }

    /**
     * Returns whether the location point lies in a water-tagged or
     * bubble-column shape. The test samples the entity location rather than
     * the whole body, so other body contact does not necessarily make this
     * query {@code true}.
     */
    default boolean isTouchingWater() {
        var dim = getDimension();
        var loc = getLocation();
        var blockState = dim.getBlockState(loc);

        return isWaterOrBubbleColumn(blockState) &&
               blockState.getBlockStateData().computeOffsetShape(MathUtils.floor(loc)).intersectsPoint(loc);
    }

    /**
     * Returns whether a block is water-tagged or is the bubble-column block
     * type.
     */
    private static boolean isWaterOrBubbleColumn(BlockState blockState) {
        return blockState.getBlockType().hasBlockTag(BlockTags.WATER) ||
               blockState.getBlockType() == BlockTypes.BUBBLE_COLUMN;
    }

    /**
     * Returns whether the entity location point lies inside a lava-tagged
     * block shape. This single-point test does not scan every body
     * intersection, so it represents location contact rather than complete
     * volume immersion.
     */
    default boolean isTouchingLava() {
        var dim = getDimension();
        var loc = getLocation();
        var blockState = dim.getBlockState(loc);

        return blockState.getBlockType().hasBlockTag(BlockTags.LAVA) &&
               blockState.getBlockStateData().computeOffsetShape(MathUtils.floor(loc)).intersectsPoint(loc);
    }

    /**
     * Handles a player interaction and returns whether it was accepted, with
     * the default refusing it. Specialized handlers may receive {@code null}
     * for the player when the interaction has no player source.
     */
    default boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        return false;
    }

    /**
     * Returns whether the ordinary entity manager may save and unload this
     * entity through world storage. The default enables persistence; disabling
     * it leaves storage and eventual removal to other owners, as actual player
     * state uses separate player storage.
     */
    default boolean isPersistent() {
        return true;
    }

    /**
     * Selects whether ordinary world-storage handling owns entity persistence
     * and unload removal. The setter does not immediately save or remove the
     * entity, and owners disabling persistence must arrange any needed
     * separate storage and cleanup.
     */
    void setPersistent(boolean persistent);

    /**
     * Returns the stored value for the supplied property definition, falling
     * back to its configured default when absent. Property definition identity
     * selects the map entry, and the current server getter does not validate
     * registration on this entity type.
     */
    <DATATYPE> DATATYPE getPropertyValue(EntityPropertyType<DATATYPE> propertyType);

    /**
     * Stores the value under the supplied property definition without
     * validation or a state broadcast. Callers must keep values compatible
     * with their property type because later persistence and network encoding
     * perform concrete numeric or enum conversions.
     */
    <DATATYPE> void setPropertyValue(EntityPropertyType<DATATYPE> propertyType, DATATYPE value);

    /**
     * Returns the current property-value map exposed by this entity
     * implementation. The server's generated getter currently returns its
     * mutable backing map despite the view annotation, so edits through this
     * reference bypass the property setter and its intended access path.
     */
    @UnmodifiableView
    Map<EntityPropertyType<?>, Object> getPropertyValues();

    /**
     * Registers a primitive shape, sets this entity as its attachment, and
     * adds current viewers to that shape. The server retains the shape
     * directly; callers must avoid assigning the same mutable attachment to
     * conflicting owners.
     */
    void attachPrimitiveShape(PrimitiveShape primitiveShape);

    /**
     * Returns an unmodifiable live view of registered primitive shapes. The
     * shape objects remain shared and mutable, so the view prevents membership
     * changes through the set rather than making attachment state independent.
     */
    @UnmodifiableView
    Set<PrimitiveShape> getAttachedPrimitiveShapes();

    /**
     * Removes current entity viewers from the shape and clears its attached
     * entity before removing set membership. The common implementation
     * performs these side effects even when the shape was not in the
     * registered set.
     */
    void detachPrimitiveShape(PrimitiveShape primitiveShape);

    /**
     * Returns whether the current lifecycle state is treated as spawned.
     */
    @Override
    default boolean isValid() {
        return getState().isSpawned();
    }
}
