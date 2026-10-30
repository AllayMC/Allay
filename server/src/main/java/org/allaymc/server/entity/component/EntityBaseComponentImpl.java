package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.EntityState;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.property.type.*;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.event.entity.EntityMoveEvent;
import org.allaymc.api.eventbus.event.entity.EntityPortalEnterEvent;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.permission.ConstantPermissionCalculator;
import org.allaymc.api.permission.PermissionCalculator;
import org.allaymc.api.permission.Tristate;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.primitiveshape.PrimitiveShape;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.allaymc.server.block.NetherPortalHelper;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.entity.component.event.*;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.allaymc.server.scheduler.AllayScheduler;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import static org.allaymc.api.utils.AllayNBTUtils.readVector2f;
import static org.allaymc.api.utils.AllayNBTUtils.readVector3f;
import static org.allaymc.server.world.physics.AllayEntityPhysicsEngine.FAT_AABB_MARGIN;

/**
 * {@code EntityBaseComponentImpl} stores common entity identity, location,
 * lifecycle, persistence data, and viewer membership. The entity manager owns
 * queued dimension insertion and removal, while this component validates
 * lifecycle transitions and updates local state and viewer presentation.
 * Location updates notify sibling components before copying the replacement
 * and adjust viewers when chunk coordinates change.
 *
 * <p> Saving lets components contribute NBT before common keys are written;
 * loading invokes components before common fields, then initialization fills
 * absent property defaults. These sequences apply changes incrementally and do
 * not roll back a preceding handler or field update after failure. Portal
 * preparation runs asynchronously behind a tick-based cooldown, whereas
 * ordinary tick ordering consumes previous contact observations before block
 * collision records new ones. Mutable location, viewer, tag, and property
 * state requires callers to preserve the owning entity's phase and lifecycle
 * rules.
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityBaseComponentImpl implements EntityBaseComponent {

    /**
     * {@code IDENTIFIER} names the common entity base component, allowing
     * entity type builders to detect and supply or replace the provider for
     * core entity behavior.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_base_component");

    // TAG_POS, TAG_IDENTIFIER, TAG_ROTATION, TAG_TAGS, TAG_UNIQUE_ID, TAG_PDC,
    // TAG_PORTAL_COOLDOWN, and TAG_PROPERTIES identify the common entity state
    // written after component contributions and read by the paired common loader.

    /**
     * NBT key for {@code float} position coordinates.
     */
    protected static final String TAG_POS = "Pos";
    /**
     * NBT key for registered entity type identifier.
     */
    protected static final String TAG_IDENTIFIER = "identifier";
    /**
     * NBT key for {@code float} yaw and pitch.
     */
    protected static final String TAG_ROTATION = "Rotation";
    /**
     * NBT key for string tag collection.
     */
    protected static final String TAG_TAGS = "Tags";
    /**
     * NBT key for sixty-four-bit entity identity.
     */
    protected static final String TAG_UNIQUE_ID = "UniqueID";
    /**
     * NBT key for plugin persistent data compound.
     */
    protected static final String TAG_PDC = "PDC";
    /**
     * NBT key for remaining portal reuse ticks.
     */
    protected static final String TAG_PORTAL_COOLDOWN = "PortalCooldown";
    /**
     * NBT key for named entity property compound.
     */
    protected static final String TAG_PROPERTIES = "properties";

    /**
     * {@code RUNTIME_ID_COUNTER} allocates runtime identifiers across common
     * entity instances with atomic increments. Its first allocated value is
     * {@code 1}, independently of the persistent identifier loaded from NBT.
     */
    protected static final AtomicLong RUNTIME_ID_COUNTER = new AtomicLong(1);

    /**
     * Injected component manager for initialization, tick, location, and
     * persistence events.
     */
    @Manager
    protected ComponentManager manager;
    /**
     * Injected entity that owns this component and viewer presentation.
     */
    @ComponentObject
    protected Entity thisEntity;

    // location and lastLocation begin with the same unplaced coordinates and
    // dimension. Ordinary movement copies only the previous XYZ coordinates into
    // lastLocation; its angles and dimension are not refreshed by that vector copy.
    // Direct NBT loading initializes both sets of XYZ coordinates together.

    /**
     * {@code location} holds current placement, while {@code lastLocation}
     * retains prior position coordinates only. Generated getters expose both
     * mutable objects, and ordinary history updates leave
     * {@code lastLocation}'s angles and dimension unchanged.
     */
    @Getter
    protected final Location3d location, lastLocation;
    /**
     * Runtime identifier allocated independently of persistent NBT identity.
     */
    @Getter
    protected final long runtimeId;
    /**
     * {@code propertyValues} stores values keyed by property-definition
     * identity. The generated getter returns this mutable map directly, and
     * entries are not validated before persistence performs type-specific
     * conversions.
     */
    @Getter
    protected final Map<EntityPropertyType<?>, Object> propertyValues;

    /**
     * {@code uniqueId} holds the persistent identity initialized by
     * {@code loadUniqueId}. The generated setter accepts other UUIDs, but
     * ordinary saving retains only their least significant sixty-four bits.
     */
    @Getter
    @Setter
    protected UUID uniqueId;
    /**
     * Entity definition assigned by the factory before component construction.
     */
    @Getter
    protected EntityType<? extends Entity> entityType;
    /**
     * {@code viewers} owns current entity presentation membership. Public
     * access wraps this set without copying, and viewer callbacks can fail
     * after preceding membership or presentation updates have already
     * occurred.
     */
    protected Set<WorldViewer> viewers;
    /**
     * {@code primitiveShapes} retains attached shape objects without copying
     * them. Attachment updates their owning entity and current viewers, while
     * the public set view shares these mutable shapes.
     */
    protected Set<PrimitiveShape> primitiveShapes;
    /**
     * {@code state} records lifecycle progress, initially
     * {@code EntityState.DESPAWNED}. {@code setState} serializes individual
     * transitions, while its generated getter does not independently
     * synchronize reads or related manager membership changes.
     */
    @Getter
    protected EntityState state;
    /**
     * Configurable name used for command and message presentation.
     */
    @Getter
    @Setter
    protected String displayName;
    /**
     * Optional name tag sent through entity metadata.
     */
    @Getter
    protected String nameTag;
    /**
     * Whether metadata requests continuous name-tag display.
     */
    @Getter
    protected boolean nameTagAlwaysShow;
    /**
     * Whether metadata marks the entity body as invisible.
     */
    @Getter
    protected boolean invisible;
    /**
     * Whether ordinary movement preserves current entity coordinates.
     */
    @Getter
    protected boolean immobile;
    /**
     * Multiplier applied to model metadata and the base collision box.
     */
    @Getter
    protected double scale;
    /**
     * {@code tags} stores entity string tags and merges loaded entries into
     * existing membership. Public views share the set without allowing
     * structural mutation through that view.
     */
    protected Set<String> tags;
    /**
     * {@code persistentDataContainer} holds plugin-owned data serialized under
     * the common PDC compound. Loading a present compound clears and replaces
     * its entries, while missing data retains the current container contents.
     */
    @Getter
    @Setter
    protected PersistentDataContainer persistentDataContainer;
    /**
     * Permission calculator used by the entity's command-sender behavior.
     */
    @Getter
    @Setter
    protected PermissionCalculator permissionCalculator;
    /**
     * {@code scheduler} owns tasks advanced after component tick events. The
     * constructor configures its asynchronous executor from the server's
     * virtual-thread pool, and the generated getter exposes this same
     * scheduler.
     */
    @Getter
    protected Scheduler scheduler;
    /**
     * {@code persistent} enables ordinary world-storage saving and unload
     * removal. Owners disabling it must arrange separate persistence or
     * explicit removal, as the entity manager leaves those entities resident.
     */
    @Getter
    @Setter
    protected boolean persistent = true;
    // portalTicks accumulates contact toward transition, while portalCooldown
    // suppresses that accumulation. tickPortal consumes inNetherPortal observations
    // and clears them before the next block-collision pass can establish new contact.

    /**
     * Accumulated nether-portal contact duration measured in ticks.
     */
    @Getter
    @Setter
    protected int portalTicks;
    /**
     * Remaining portal reuse delay measured in ticks.
     */
    @Getter
    @Setter
    protected int portalCooldown;
    /**
     * {@code inNetherPortal} records contact for the next portal update.
     * {@code tickPortal} consumes and clears it before block-interior
     * callbacks can mark contact again later in the tick.
     */
    @Getter
    @Setter
    protected boolean inNetherPortal;
    /**
     * Number of ordinary ticks completed by this entity.
     */
    @Getter
    protected long tick;

    /**
     * Creates unspawned common state using the dimension and type supplied by
     * the entity factory. Position begins at the unplaced
     * {@code Integer.MAX_VALUE} sentinel, while runtime identity, empty
     * collections, plugin data, permission defaults, and the scheduler are
     * allocated immediately.
     *
     * <p> The type must already be assigned because its identifier provides
     * the initial display name. NBT loading occurs later in
     * {@code onInitFinish}, after component injection is available; the
     * constructor therefore does not dispatch persistence events or insert
     * dimension membership.
     */
    public EntityBaseComponentImpl(EntityInitInfo info) {
        this.location = new Location3d(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, info.dimension());
        this.lastLocation = new Location3d(this.location);
        this.runtimeId = RUNTIME_ID_COUNTER.getAndIncrement();
        this.propertyValues = new HashMap<>();
        this.entityType = info.getEntityType();
        this.viewers = new HashSet<>();
        this.primitiveShapes = new HashSet<>();
        this.state = EntityState.DESPAWNED;
        this.displayName = AllayStringUtils.snakeCaseToTitleCase(entityType.getIdentifier().path());
        this.scale = 1.0;
        this.tags = new HashSet<>();
        this.persistentDataContainer = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);
        this.permissionCalculator = new ConstantPermissionCalculator(Tristate.TRUE);
        this.scheduler = new AllayScheduler(Server.getInstance().getVirtualThreadPool());
    }

    /**
     * Loads the initialization compound after component injection and fills
     * absent property entries from the entity definition. Loaded values take
     * precedence over defaults, while this phase does not validate that their
     * runtime types or ranges match the property definition.
     */
    @OnInitFinish
    public void onInitFinish(EntityInitInfo initInfo) {
        loadNBT(initInfo.nbt());
        for (var propType : this.entityType.getProperties().values()) {
            // Keep successfully loaded property values rather than replacing them
            // with defaults when initialization fills the remaining definition entries.
            this.propertyValues.putIfAbsent(propType, propType.getDefaultValue());
        }
    }

    /**
     * Sends the owning entity's current metadata through every registered
     * viewer.
     */
    @Override
    public void broadcastState() {
        forEachViewers(viewer -> viewer.viewEntityState(thisEntity));
    }

    /**
     * Advances the entity-local tick and dispatches ordinary component work
     * before scheduled tasks. Portal handling then consumes prior contact, and
     * block collision runs last so its powder-snow and portal observations
     * become input to the next ordinary tick.
     */
    public void tick(long currentTick) {
        this.tick++;
        manager.callEvent(new CEntityTickEvent(currentTick));
        this.scheduler.tick();
        tickPortal();
        tickBlockCollision();
    }

    /**
     * Invokes entity and block callbacks for collision and interior contact at
     * current placement. Collision callbacks use the expanded check box, while
     * interior callbacks use the ordinary scaled box; the search extends one
     * block downward to include tall shapes anchored below the entity.
     */
    protected void tickBlockCollision() {
        var aabb = getOffsetAABB();
        var aabbFat = getOffsetAABBForCollisionCheck();
        var dimension = getDimension();
        // Include the anchor row below the box because shapes such as fences
        // extend above their block and can intersect the entity from that lower row.
        var searchAABB = new AABBd(aabbFat);
        searchAABB.minY -= 1;
        dimension.forEachBlockStates(searchAABB, 0, (x, y, z, blockState) -> {
            var block = new Block(blockState, new Position3i(x, y, z, dimension), 0);

            if (blockState.getBlockStateData().collisionShape().translate(x, y, z).intersectsAABB(aabbFat)) {
                this.onCollideWithBlock(block);
                blockState.getBehavior().onCollideWithEntity(block, thisEntity);
            }

            // Interior contact uses the ordinary body box, keeping the collision
            // margin from reporting block-interior effects before the body enters its shape.
            if (blockState.getBlockStateData().shape().translate(x, y, z).intersectsAABB(aabb)) {
                blockState.getBehavior().onEntityInside(block, thisEntity);
                this.onInsideBlock(block);
            }
        });
    }

    /**
     * Consumes nether-portal contact and starts a transfer after the
     * applicable wait. Positive cooldown suppresses contact accumulation,
     * decrements once, and clears the contact flag. Survival and adventure
     * players accumulate contact ticks; other players and nonplayer entities
     * select the transition threshold immediately.
     *
     * <p> Missing contact resets the accumulated wait, and the flag is cleared
     * before the later block-interior pass can mark a fresh observation. A
     * cancelled portal event can leave the accumulated threshold reached,
     * while asynchronous preparation manages its own cooldown reset only for a
     * normal unsuccessful result.
     */
    protected void tickPortal() {
        if (portalCooldown > 0) {
            portalCooldown--;
            inNetherPortal = false;
            return;
        }

        if (inNetherPortal) {
            if (thisEntity instanceof EntityPlayer player) {
                var gameMode = player.getGameMode();
                if (gameMode == GameMode.CREATIVE || gameMode == GameMode.SPECTATOR) {
                    portalTicks = NETHER_PORTAL_TRANSITION_TICKS;
                } else {
                    portalTicks++;
                }
            } else {
                portalTicks = NETHER_PORTAL_TRANSITION_TICKS;
            }

            if (portalTicks >= NETHER_PORTAL_TRANSITION_TICKS) {
                performNetherPortalTeleport();
            }
        } else {
            portalTicks = 0;
        }

        // Require fresh contact from the later block-interior pass so leaving
        // a portal does not keep a previous tick's observation active indefinitely.
        inNetherPortal = false;
    }

    /**
     * Starts asynchronous portal preparation after the cancellable entry event
     * permits it. The method installs reuse cooldown and clears accumulated
     * contact before submitting work, suppressing immediate retries while the
     * helper prepares target chunks. Submission returns without waiting for
     * the helper's future.
     *
     * <p> Only a normally completed {@code false} helper result clears
     * cooldown early. Exceptional completion, thrown work, or an incomplete
     * future has no cleanup continuation here, and the returned worker future
     * is not retained. Cooldown remains ordinary tick state rather than an
     * exclusive lifetime token for all asynchronously progressing preparation.
     */
    protected void performNetherPortalTeleport() {
        if (!new EntityPortalEnterEvent(thisEntity, EntityPortalEnterEvent.PortalType.NETHER).call()) {
            return;
        }

        // Install cooldown before submission so the next portal tick observes
        // reuse suppression even while destination preparation has not started running.
        portalCooldown = PORTAL_COOLDOWN_TICKS;
        portalTicks = 0;

        // Destination preparation can wait for chunk loading; run it away from
        // the dimension tick so ordinary entity work can continue during that wait.
        Server.getInstance().getVirtualThreadPool().submit(() -> {
            NetherPortalHelper.teleport(thisEntity).thenAccept(success -> {
                if (!success) {
                    // A normal unsuccessful preparation permits another contact attempt;
                    // exceptional or incomplete helper futures do not reach this continuation.
                    portalCooldown = 0;
                }
            });
        });
    }

    /**
     * Proposes ordinary movement through the public movement event and uses
     * its editable destination. The event type is not cancellable, although
     * the dispatch result is checked. Immobility restores existing coordinates
     * while allowing changed orientation; a completely unchanged immobile
     * result returns {@code false}.
     *
     * <p> Accepted movement copies location and may update chunk-based viewers
     * before broadcasting location and motion. A later callback failure does
     * not undo an already copied location or preceding viewer changes, so
     * normal completion is required before callers can treat the full
     * presentation sequence as finished.
     */
    @Override
    public boolean trySetLocation(Location3dc newLocation) {
        var event = new EntityMoveEvent(thisEntity, location, newLocation);
        if (!event.call()) {
            return false;
        }

        newLocation = event.getTo();
        if (this.immobile) {
            // Preserve current coordinates for immobile entities while retaining
            // the proposed orientation so looking around does not require movement permission.
            var loc = new Location3d(newLocation);
            loc.set(this.location.x, this.location.y, this.location.z);
            if (loc.equals(this.location)) {
                return false;
            }

            newLocation = loc;
        }

        setLocation(newLocation);
        broadcastMoveToViewers(newLocation, false);
        return true;
    }

    /**
     * Stores the name tag before broadcasting current entity metadata to
     * viewers. An unchanged request still broadcasts, and a viewer failure
     * leaves the already stored value in place rather than restoring its
     * predecessor.
     */
    @Override
    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
        broadcastState();
    }

    /**
     * Stores the continuous name-tag display flag before broadcasting current
     * entity metadata to viewers. An unchanged request still broadcasts, and a
     * viewer failure leaves the already stored value in place rather than
     * restoring its predecessor.
     */
    @Override
    public void setNameTagAlwaysShow(boolean nameTagAlwaysShow) {
        this.nameTagAlwaysShow = nameTagAlwaysShow;
        broadcastState();
    }

    /**
     * Stores the invisible-body flag before broadcasting current entity
     * metadata to viewers. An unchanged request still broadcasts, and a viewer
     * failure leaves the already stored value in place rather than restoring
     * its predecessor.
     */
    @Override
    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
        broadcastState();
    }

    /**
     * Stores the immobility flag before broadcasting current entity metadata
     * to viewers. An unchanged request still broadcasts, and a viewer failure
     * leaves the already stored value in place rather than restoring its
     * predecessor.
     */
    @Override
    public void setImmobile(boolean immobile) {
        this.immobile = immobile;
        broadcastState();
    }

    /**
     * Stores the model and collision-box scale before broadcasting current
     * entity metadata to viewers. An unchanged request still broadcasts, and a
     * viewer failure leaves the already stored value in place rather than
     * restoring its predecessor.
     */
    @Override
    public void setScale(double scale) {
        this.scale = scale;
        broadcastState();
    }

    /**
     * Copies placement only while the entity remains in its initial despawned
     * setup state. The internal location event runs without fall-distance
     * calculation, and this operation does not update chunk viewers or insert
     * entity-manager membership.
     *
     * @throws IllegalStateException if the lifecycle no longer permits pre-spawn placement
     */
    @Override
    public void setLocationBeforeSpawn(Location3dc location) {
        if (!canBeSpawnedIgnoreLocation()) {
            throw new IllegalStateException("Trying to set location of an entity which cannot being spawned!");
        }

        setLocationWithoutChunkCheck(location, false);
    }

    /**
     * Rejects NaN coordinates and notifies components before copying the
     * replacement location. The event sees the owner's previous location;
     * afterward {@code lastLocation} receives only the previous XYZ
     * coordinates, while current coordinates, angles, and dimension are
     * replaced without checking chunk membership.
     */
    protected void setLocationWithoutChunkCheck(Location3dc location, boolean calculateFallDistance) {
        if (MathUtils.hasNaN(location)) {
            throw new IllegalArgumentException("Trying to set the location of entity " + runtimeId + " to a new location which contains NaN: " + location);
        }

        // Notify physics before overwriting the current location so it can derive
        // vertical displacement from the old placement and the proposed replacement.
        this.manager.callEvent(new CEntitySetLocationEvent(location, calculateFallDistance));
        this.lastLocation.set(this.location);
        this.location.set(location);
        this.location.setYaw(location.yaw());
        this.location.setPitch(location.pitch());
        this.location.setDimension(location.dimension());
    }

    /**
     * Returns the dimension retained by the current mutable location.
     */
    @Override
    public Dimension getDimension() {
        return location.dimension();
    }

    /**
     * Delegates queued removal and its completion callback to the current
     * dimension's entity manager. Rejected lifecycle transitions do not run
     * the callback; a {@code null} callback reaches an unguarded invocation
     * only after successful removal.
     */
    @Override
    public void remove(Runnable callback) {
        getDimension().getEntityManager().removeEntity(thisEntity, callback);
    }

    /**
     * Attempts a lifecycle transition under this component's monitor. An empty
     * predecessor set accepts any current state; otherwise the current state
     * must be listed. This lock serializes the state decision alone and does
     * not make manager insertion or viewer updates atomic.
     */
    public synchronized boolean setState(EntityState status) {
        // Keep predecessor validation and assignment under the same monitor so
        // competing lifecycle requests cannot both transition from an obsolete state.
        if (!status.getPreviousStates().isEmpty() && !status.getPreviousStates().contains(this.state)) {
            log.warn("Trying to set status of entity {} to {} but the current status is {}", thisEntity, status, this.state);
            return false;
        }

        this.state = status;
        return true;
    }

    /**
     * Returns whether every coordinate differs from the unplaced sentinel, a
     * dimension is assigned, and the lifecycle is despawned. This query does
     * not require the current chunk to be loaded or reserve a future spawn
     * transition.
     */
    @Override
    public boolean canBeSpawned() {
        return location.x != Integer.MAX_VALUE &&
               location.y != Integer.MAX_VALUE &&
               location.z != Integer.MAX_VALUE &&
               location.dimension() != null &&
               canBeSpawnedIgnoreLocation();
    }

    /**
     * Returns whether the lifecycle remains despawned for setup and spawn
     * eligibility.
     */
    protected boolean canBeSpawnedIgnoreLocation() {
        return state == EntityState.DESPAWNED;
    }

    /**
     * Copies ordinary movement and updates chunk viewers while enabling
     * fall-distance calculation.
     */
    protected void setLocation(Location3dc newLoc) {
        setLocation(newLoc, true);
    }

    /**
     * Copies a replacement placement and reconciles viewers when derived chunk
     * coordinates change. Old and new chunk indices use integer truncation
     * before shifting, and the component event and location copy occur before
     * viewer membership is compared. Equal chunk indices return immediately,
     * even if dimensions differ.
     *
     * <p> For changed indices, loaders present only in the old chunk lose
     * presentation and loaders present only in the new chunk gain it. Shared
     * loaders retain their existing membership, and the owning entity is
     * excluded from both changes. Missing chunks contribute empty loader sets;
     * exceptions leave the copied location and preceding presentation changes
     * in place.
     */
    protected void setLocation(Location3dc newLoc, boolean calculateFallDistance) {
        var oldChunkX = (int) this.location.x() >> 4;
        var oldChunkZ = (int) this.location.z() >> 4;
        var newChunkX = (int) newLoc.x() >> 4;
        var newChunkZ = (int) newLoc.z() >> 4;
        var oldDimension = this.location.dimension();
        var newDimension = newLoc.dimension();
        setLocationWithoutChunkCheck(newLoc, calculateFallDistance);

        if (oldChunkX == newChunkX && oldChunkZ == newChunkZ) {
            return;
        }

        var oldChunk = oldDimension != null ? oldDimension.getChunkManager().getChunk(oldChunkX, oldChunkZ) : null;
        var newChunk = newDimension != null ? newDimension.getChunkManager().getChunk(newChunkX, newChunkZ) : null;
        Set<ChunkLoader> oldChunkLoaders = oldChunk != null ? oldChunk.getChunkLoaders() : Collections.emptySet();
        Set<ChunkLoader> newChunkLoaders = newChunk != null ? newChunk.getChunkLoaders() : Collections.emptySet();
        // Compare loader membership before sending changes so viewers shared by
        // both chunks keep their existing presentation instead of despawning and respawning.
        Set<ChunkLoader> oldChunkOnlyLoaders = new HashSet<>(oldChunkLoaders);
        oldChunkOnlyLoaders.removeAll(newChunkLoaders);
        Set<ChunkLoader> newChunkOnlyLoaders = new HashSet<>(newChunkLoaders);
        newChunkOnlyLoaders.removeAll(oldChunkLoaders);

        oldChunkOnlyLoaders.stream()
                .filter(loader -> loader != thisEntity)
                .map(ChunkLoader::toWorldViewer)
                .filter(Objects::nonNull)
                .forEach(this::despawnFrom);
        newChunkOnlyLoaders.stream()
                .filter(loader -> loader != thisEntity)
                .map(ChunkLoader::toWorldViewer)
                .filter(Objects::nonNull)
                .forEach(this::spawnTo);
    }

    /**
     * Accepts a spawned-entity teleport through its cancellable public event
     * and prepares sibling components before movement. The initial target must
     * have a dimension, and an entity without a current dimension or spawned
     * state is refused. The event receives a copied initial destination but
     * can replace it.
     *
     * <p> Same-dimension movement copies location immediately and broadcasts a
     * teleport update. A different dimension queues source removal whose
     * callback performs target placement and queues insertion. The
     * {@code true} result reports acceptance of that path rather than
     * completed destination membership; later failures do not restore prior
     * motion, location, or manager state.
     */
    @Override
    public boolean teleport(Location3dc target, EntityTeleportEvent.Reason reason) {
        Objects.requireNonNull(target.dimension());
        if (this.location.dimension() == null) {
            log.warn("Trying to teleport an entity whose dimension is null! Entity: {}", thisEntity);
            return false;
        }

        if (!this.isSpawned()) {
            log.warn("Trying to teleport an entity which is not spawned! Entity: {}", thisEntity);
            return false;
        }

        var event = new EntityTeleportEvent(thisEntity, this.location, new Location3d(target), reason);
        if (!event.call()) {
            return false;
        }

        target = event.getTo();
        beforeTeleport(target);
        if (this.location.dimension() == target.dimension()) {
            teleportInDimension(target);
        } else {
            teleportOverDimension(target);
        }

        return true;
    }

    /**
     * Notifies sibling components before an accepted teleport changes entity
     * placement.
     */
    protected void beforeTeleport(Location3dc target) {
        this.manager.callEvent(CEntityBeforeTeleportEvent.INSTANCE);
    }

    /**
     * Copies the target within the current dimension without fall accumulation
     * and broadcasts a teleport. This path does not load the destination
     * chunk; callers that require prepared chunks must arrange that before
     * acceptance.
     */
    protected void teleportInDimension(Location3dc target) {
        setLocation(target, false);
        broadcastMoveToViewers(target, true);
    }

    /**
     * Queues removal from the source dimension and transfers the same entity
     * from its removal callback. The callback runs after source detachment,
     * allowing pre-spawn placement before the target manager receives its own
     * addition request. This method returns before either queued phase
     * necessarily finishes.
     *
     * <p> A rejected source transition skips the callback entirely, and an
     * exception or rejected target addition does not reinsert the entity into
     * its old dimension. Callers therefore receive no atomic transfer or
     * completion future from this helper and must not infer destination
     * membership from its return.
     */
    protected void teleportOverDimension(Location3dc target) {
        // Wait for source removal before using the pre-spawn placement path,
        // because target insertion requires the same entity to be despawned again.
        this.location.dimension().getEntityManager().removeEntity(thisEntity, () -> {
            setLocationBeforeSpawn(target);
            target.dimension().getEntityManager().addEntity(thisEntity);
        });
    }

    /**
     * Returns a fresh default humanoid box measuring 0.6 by 1.8 blocks.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.3, 0.0, -0.3, 0.3, 1.8, 0.3);
    }

    /**
     * Returns the world-space body box expanded by twice the physics margin.
     */
    @Override
    public AABBdc getOffsetAABBForCollisionCheck() {
        return new AABBd(getOffsetAABB()).expand(2 * FAT_AABB_MARGIN);
    }

    /**
     * Returns an unmodifiable live view of the retained viewer set. The
     * wrapper does not snapshot membership or synchronize iteration, so
     * readers must coordinate with the entity's normal viewer mutation phase.
     */
    @Override
    @UnmodifiableView
    public Set<WorldViewer> getViewers() {
        return Collections.unmodifiableSet(viewers);
    }

    /**
     * Adds the viewer before sending entity presentation and state in that
     * order. Existing membership does not suppress either callback, and an
     * exception after insertion leaves the viewer registered without rolling
     * back prior presentation.
     */
    @Override
    public void spawnTo(WorldViewer viewer) {
        viewers.add(viewer);
        viewer.viewEntity(thisEntity);
        viewer.viewEntityState(thisEntity);
    }

    /**
     * Removes viewer membership before requesting client-side entity removal.
     * The callback still runs when the viewer was absent, and a failure does
     * not restore membership or remove the entity from its dimension.
     */
    @Override
    public void despawnFrom(WorldViewer viewer) {
        viewers.remove(viewer);
        viewer.removeEntity(thisEntity);
    }

    /**
     * Removes presentation and membership one viewer at a time from the live
     * set. A failing viewer callback stops the pass before its iterator
     * removal, leaving remaining membership for a later cleanup attempt.
     */
    @Override
    public void despawnFromAll() {
        for (var iterator = viewers.iterator(); iterator.hasNext(); ) {
            iterator.next().removeEntity(thisEntity);
            iterator.remove();
        }
    }

    /**
     * Sends the proposed location update to current viewers, then sends motion
     * when the entity has physics. The two passes use live membership
     * separately, so callbacks or failures can prevent an identical complete
     * recipient sequence.
     */
    public void broadcastMoveToViewers(Location3dc newLocation, boolean teleporting) {
        forEachViewers(viewer -> viewer.viewEntityLocation(thisEntity, newLocation, teleporting));
        if (thisEntity instanceof EntityPhysicsComponent physicsComponent) {
            forEachViewers(viewer -> viewer.viewEntityMotion((Entity & EntityPhysicsComponent) thisEntity, physicsComponent.getMotion()));
        }
    }

    /**
     * Builds a compound from component contributions followed by common entity
     * fields. The base writes its keys after component handlers, so colliding
     * component keys are replaced. Position and rotation narrow to floats,
     * tags and plugin data are included when present, and positive portal
     * cooldown is preserved.
     *
     * <p> Property definitions choose enum-name, integer, boolean-as-integer,
     * or {@code float} encoding. Values are cast rather than revalidated, so
     * incompatible entries can fail serialization. The helper returns data
     * without committing it to world storage, and persistent {@code UUID} handling
     * preserves the ordinary lower sixty-four-bit representation rather than
     * an arbitrary full {@code UUID}.
     */
    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder();
        this.manager.callEvent(new CEntitySaveNBTEvent(builder));

        // Write common keys after component contributions so core identity and
        // placement remain authoritative if a handler supplied the same NBT keys.
        builder.putString(TAG_IDENTIFIER, this.entityType.getIdentifier().toString());
        // Entity placement uses float-vector tags in the paired loader, so save
        // narrows both position and rotation before they cross the NBT boundary.
        AllayNBTUtils.writeVector3f(builder, TAG_POS, (float) this.location.x, (float) this.location.y, (float) this.location.z);
        AllayNBTUtils.writeVector2f(builder, TAG_ROTATION, (float) this.location.yaw(), (float) this.location.pitch());

        if (!tags.isEmpty()) {
            builder.putList(TAG_TAGS, NbtType.STRING, new ArrayList<>(tags));
        }

        if (!persistentDataContainer.isEmpty()) {
            builder.put(TAG_PDC, persistentDataContainer.toNbt());
        }

        saveUniqueId(builder);

        if (portalCooldown > 0) {
            builder.putInt(TAG_PORTAL_COOLDOWN, portalCooldown);
        }

        if (!propertyValues.isEmpty()) {
            var propsBuilder = NbtMap.builder();
            for (var entry : propertyValues.entrySet()) {
                var propType = entry.getKey();
                var value = entry.getValue();
                // Encode by the definition rather than the runtime value class so
                // each property keeps the representation expected by the paired loader.
                switch (propType) {
                    case EnumPropertyType<?> enumProp -> propsBuilder.putString(propType.getName(), serializeEnumPropertyValue(enumProp, value));
                    case IntPropertyType ignored -> propsBuilder.putInt(propType.getName(), (Integer) value);
                    case BooleanPropertyType ignored -> propsBuilder.putInt(propType.getName(), ((Boolean) value) ? 1 : 0);
                    case FloatPropertyType ignored -> propsBuilder.putFloat(propType.getName(), (Float) value);
                }
            }
            builder.putCompound(TAG_PROPERTIES, propsBuilder.build());
        }

        return builder.build();
    }

    /**
     * Serializes a property value using its enum definition after an unchecked
     * cast. Callers must keep the stored object compatible with that
     * definition; this adapter supplies no fallback for a mismatched or
     * missing enum value.
     */
    @SuppressWarnings("unchecked")
    private static <T extends Enum<T>> String serializeEnumPropertyValue(EnumPropertyType<T> enumProp, Object value) {
        return enumProp.serializeValue((T) value);
    }

    /**
     * Stores the least significant sixty-four bits of the retained {@code UUID} in
     * entity NBT. Ordinary generated identities use zero upper bits, but an
     * arbitrary {@code UUID} assigned through the setter loses its upper half on this
     * persistence path.
     */
    protected void saveUniqueId(NbtMapBuilder builder) {
        builder.putLong(TAG_UNIQUE_ID, uniqueId.getLeastSignificantBits());
    }

    /**
     * Applies component load events before common position, rotation, tags,
     * plugin data, identity, cooldown, and property data. Position initializes
     * both current and previous coordinates, while rotation updates only
     * current angles. Tags merge into their existing set; a present
     * plugin-data compound replaces the container's contents.
     *
     * <p> Property loading resolves names through this entity type, ignores
     * unknown definitions or incompatible stored kinds, and converts numeric
     * values according to each property category. It does not validate ranges
     * or clear earlier property entries. Missing tags preserve current fields,
     * and failure after a preceding handler or assignment leaves that partial
     * load applied.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        // Let sibling components consume their fields before common data changes
        // the owner location and identity they may inspect during initialization.
        this.manager.callEvent(new CEntityLoadNBTEvent(nbt));

        if (nbt.containsKey(TAG_POS)) {
            var pos = readVector3f(nbt, TAG_POS);
            this.location.set(pos.x, pos.y, pos.z);
            this.lastLocation.set(pos.x, pos.y, pos.z);
        }

        if (nbt.containsKey(TAG_ROTATION)) {
            var rot = readVector2f(nbt, TAG_ROTATION);
            this.location.setYaw(rot.x);
            this.location.setPitch(rot.y);
        }

        nbt.listenForList(TAG_TAGS, NbtType.STRING, tags -> this.tags.addAll(tags));

        nbt.listenForCompound(TAG_PDC, customNbt -> {
            // A present plugin compound replaces existing plugin data as one load
            // phase; missing compounds leave previously retained plugin entries untouched.
            this.persistentDataContainer.clear();
            this.persistentDataContainer.putAll(customNbt);
        });

        loadUniqueId(nbt);

        if (nbt.containsKey(TAG_PORTAL_COOLDOWN)) {
            this.portalCooldown = nbt.getInt(TAG_PORTAL_COOLDOWN);
        }

        nbt.listenForCompound(TAG_PROPERTIES, compound -> {
            var propTypeMap = entityType.getProperties();
            for (var entry : compound.entrySet()) {
                var propType = propTypeMap.get(entry.getKey());
                // Unknown property names cannot be associated with this type's
                // definitions, so leave the existing property map unchanged for those entries.
                if (propType == null) continue;
                var nbtValue = entry.getValue();
                switch (propType) {
                    case EnumPropertyType<?> enumProp -> {
                        if (nbtValue instanceof String str) {
                            propertyValues.put(propType, enumProp.deserializeValue(str));
                        }
                    }
                    case IntPropertyType ignored -> {
                        if (nbtValue instanceof Number num) {
                            propertyValues.put(propType, num.intValue());
                        }
                    }
                    case BooleanPropertyType ignored -> {
                        if (nbtValue instanceof Number num) {
                            propertyValues.put(propType, num.intValue() != 0);
                        }
                    }
                    case FloatPropertyType ignored -> {
                        if (nbtValue instanceof Number num) {
                            propertyValues.put(propType, num.floatValue());
                        }
                    }
                }
            }
        });
    }

    /**
     * Restores an ordinary entity identifier from its sixty-four-bit NBT value
     * or allocates a fresh value when absent. The reconstructed {@code UUID} always
     * has zero upper bits, matching this component's save representation.
     *
     * <p> Existing stored lower bits retain identity across a save/load cycle.
     * Missing identity data instead takes random {@code UUID} lower bits and discards
     * the generated upper half, allowing the same signed-long encoding to
     * represent every identity this fallback creates. This operation does not
     * change the separately allocated runtime identifier.
     */
    protected void loadUniqueId(NbtMap nbt) {
        if (nbt.containsKey(TAG_UNIQUE_ID)) {
            this.uniqueId = new UUID(0, nbt.getLong(TAG_UNIQUE_ID));
            return;
        }

        // Keep random identity in the lower half because UniqueID stores one
        // signed long and the paired loader reconstructs the upper half as zero.
        this.uniqueId = new UUID(0, UUID.randomUUID().getLeastSignificantBits());
    }

    /**
     * Returns the display name used for this entity's command identity.
     */
    @Override
    public String getCommandSenderName() {
        return this.displayName;
    }

    /**
     * Returns the retained entity location used as its command execution
     * origin.
     */
    @Override
    public Location3dc getCommandExecuteLocation() {
        return getLocation();
    }

    /**
     * Returns {@code true} because this command sender represents an entity.
     */
    @Override
    public boolean isEntity() {
        return true;
    }

    /**
     * Returns the injected entity owning this command-sender component.
     */
    @Override
    public Entity asEntity() {
        return thisEntity;
    }

    /**
     * Discards plain messages because ordinary entities have no player
     * controller destination.
     */
    @Override
    public void sendMessage(String message) {
    }

    /**
     * Discards translated messages because ordinary entities have no player
     * controller destination.
     */
    @Override
    public void sendTranslatable(String translatable, Object... args) {
    }

    /**
     * Discards structured command output for ordinary entities without a
     * player controller.
     */
    @Override
    public void sendCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
    }

    /**
     * Adds the supplied tag and reports whether membership changed.
     */
    @Override
    public boolean addTag(String tag) {
        return tags.add(tag);
    }

    /**
     * Removes the supplied tag and reports whether membership changed.
     */
    @Override
    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    /**
     * Returns whether the retained tag set contains the supplied tag.
     */
    @Override
    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    /**
     * Returns an unmodifiable live view of the retained entity tags. The
     * wrapper shares membership with tag operations and NBT merges rather than
     * copying it, so iteration follows the entity's normal mutation context.
     */
    @Override
    @UnmodifiableView
    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Checks the expanded world-space body box against block collision shapes
     * only for spawned lifecycle states. Pending or completed removal states
     * return {@code false} before querying blocks, regardless of their
     * retained position.
     */
    @Override
    public boolean checkBlockCollision() {
        if (!this.isSpawned()) {
            return false;
        }

        var aabb = getOffsetAABBForCollisionCheck();
        return getDimension().getCollidingBlockStates(aabb) != null;
    }

    /**
     * Sends the supplied action through every current entity viewer's action
     * hook.
     */
    @Override
    public void applyAction(EntityAction action) {
        forEachViewers(viewer -> viewer.viewEntityAction(thisEntity, action));
    }

    /**
     * Sends the supplied animation through every current entity viewer's
     * animation hook.
     */
    @Override
    public void applyAnimation(EntityAnimation animation) {
        forEachViewers(viewer -> viewer.viewEntityAnimation(thisEntity, animation));
    }

    /**
     * Returns the stored property value or the definition's default when no
     * entry exists. The lookup uses the definition object as the key, and this
     * unchecked getter does not validate stored value compatibility.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <DATATYPE> DATATYPE getPropertyValue(EntityPropertyType<DATATYPE> propertyType) {
        return (DATATYPE) propertyValues.getOrDefault(propertyType, propertyType.getDefaultValue());
    }

    /**
     * Stores the supplied property value under its definition without
     * validation or viewer notification. Callers must preserve compatible
     * value types and arrange any required broadcast because persistence later
     * casts values according to that definition.
     */
    @Override
    public <DATATYPE> void setPropertyValue(EntityPropertyType<DATATYPE> propertyType, DATATYPE value) {
        propertyValues.put(propertyType, value);
    }

    /**
     * Retains the shape and assigns this entity as its attachment before
     * adding current viewers. The shape remains shared and mutable; a later
     * viewer failure leaves prior attachment and presentation changes applied
     * without rollback.
     */
    @Override
    public void attachPrimitiveShape(PrimitiveShape primitiveShape) {
        this.primitiveShapes.add(primitiveShape);
        primitiveShape.setAttachedEntity(thisEntity);
        for (var viewer : this.viewers) {
            primitiveShape.addViewer(viewer);
        }
    }

    /**
     * Returns an unmodifiable live view of the attachment set. The contained
     * shapes remain shared objects, so callers must use their own attachment
     * and viewer contracts rather than treating this view as an independent
     * copy.
     */
    @Override
    public Set<PrimitiveShape> getAttachedPrimitiveShapes() {
        return Collections.unmodifiableSet(this.primitiveShapes);
    }

    /**
     * Removes current entity viewers from the supplied shape, clears its
     * attachment, and removes membership. These side effects also occur for an
     * unregistered shape; an intervening viewer failure can leave later
     * cleanup steps unapplied.
     */
    @Override
    public void detachPrimitiveShape(PrimitiveShape primitiveShape) {
        for (var viewer : this.viewers) {
            primitiveShape.removeViewer(viewer);
        }
        primitiveShape.setAttachedEntity(null);
        this.primitiveShapes.remove(primitiveShape);
    }
}
