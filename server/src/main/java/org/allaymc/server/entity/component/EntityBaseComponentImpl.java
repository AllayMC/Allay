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
 * @author daoge_cmd
 */
@Slf4j
public class EntityBaseComponentImpl implements EntityBaseComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_base_component");

    protected static final String TAG_POS = "Pos";
    protected static final String TAG_IDENTIFIER = "identifier";
    protected static final String TAG_ROTATION = "Rotation";
    protected static final String TAG_TAGS = "Tags";
    protected static final String TAG_UNIQUE_ID = "UniqueID";
    protected static final String TAG_PDC = "PDC";
    protected static final String TAG_PORTAL_COOLDOWN = "PortalCooldown";

    // NOTICE: the runtime id is counted from 1 not 0
    protected static final AtomicLong RUNTIME_ID_COUNTER = new AtomicLong(1);

    @Manager
    protected ComponentManager manager;
    @ComponentObject
    protected Entity thisEntity;

    @Getter
    protected final Location3d location, lastLocation;
    @Getter
    protected final long runtimeId;

    // Will be reset in method loadUniqueId()
    @Getter
    @Setter
    protected UUID uniqueId;
    @Getter
    protected EntityType<? extends Entity> entityType;
    protected Set<WorldViewer> viewers;
    @Getter
    protected EntityState state;
    @Getter
    @Setter
    protected String displayName;
    @Getter
    protected String nameTag;
    @Getter
    protected boolean nameTagAlwaysShow;
    @Getter
    protected boolean invisible;
    @Getter
    protected boolean immobile;
    @Getter
    protected double scale;
    protected Set<String> tags;
    @Getter
    @Setter
    protected PersistentDataContainer persistentDataContainer;
    @Getter
    @Setter
    protected PermissionCalculator permissionCalculator;
    @Getter
    protected Scheduler scheduler;
    @Getter
    @Setter
    protected boolean persistent = true;
    @Getter
    @Setter
    protected int portalTicks;
    @Getter
    @Setter
    protected int portalCooldown;
    @Getter
    @Setter
    protected boolean inNetherPortal;

    public EntityBaseComponentImpl(EntityInitInfo info) {
        this.location = new Location3d(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, info.dimension());
        this.lastLocation = new Location3d(this.location);
        this.runtimeId = RUNTIME_ID_COUNTER.getAndIncrement();
        this.entityType = info.getEntityType();
        this.viewers = new HashSet<>();
        this.state = EntityState.DESPAWNED;
        this.displayName = AllayStringUtils.snakeCaseToTitleCase(entityType.getIdentifier().path());
        this.scale = 1.0;
        this.tags = new HashSet<>();
        this.persistentDataContainer = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);
        this.permissionCalculator = new ConstantPermissionCalculator(Tristate.TRUE);
        this.scheduler = new AllayScheduler(Server.getInstance().getVirtualThreadPool());
    }

    @OnInitFinish
    public void onInitFinish(EntityInitInfo initInfo) {
        loadNBT(initInfo.nbt());
    }

    @Override
    public void broadcastState() {
        forEachViewers(viewer -> viewer.viewEntityState(thisEntity));
    }

    public void tick(long currentTick) {
        manager.callEvent(new CEntityTickEvent(currentTick));
        this.scheduler.tick();
        tickPortal();
        tickBlockCollision();
    }

    protected void tickBlockCollision() {
        var aabb = getOffsetAABB();
        var aabbFat = getOffsetAABBForCollisionCheck();
        var dimension = getDimension();
        // Expand search range by 1 block downward to include blocks with tall collision shapes (e.g. fences with maxY = 1.5)
        var searchAABB = new AABBd(aabbFat);
        searchAABB.minY -= 1;
        dimension.forEachBlockStates(searchAABB, 0, (x, y, z, blockState) -> {
            var block = new Block(blockState, new Position3i(x, y, z, dimension), 0);

            if (blockState.getBlockStateData().collisionShape().translate(x, y, z).intersectsAABB(aabbFat)) {
                this.onCollideWithBlock(block);
                blockState.getBehavior().onCollideWithEntity(block, thisEntity);
            }

            // Check if the entity is inside a block. Here we use the normal aabb instead of the aabb
            // for collision check
            if (blockState.getBlockStateData().shape().translate(x, y, z).intersectsAABB(aabb)) {
                blockState.getBehavior().onEntityInside(block, thisEntity);
                this.onInsideBlock(block);
            }
        });
    }

    protected void tickPortal() {
        if (portalCooldown > 0) {
            portalCooldown--;
            inNetherPortal = false;
            return;
        }

        if (inNetherPortal) {
            // Creative/spectator players and non-player entities teleport instantly
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

        // Reset flag — will be set again by the portal block's onEntityInside next tick
        inNetherPortal = false;
    }

    protected void performNetherPortalTeleport() {
        if (!new EntityPortalEnterEvent(thisEntity, EntityPortalEnterEvent.PortalType.NETHER).call()) {
            return;
        }

        // Set cooldown immediately to prevent re-entry while async teleport is in progress
        portalCooldown = PORTAL_COOLDOWN_TICKS;
        portalTicks = 0;

        // Run teleport in a virtual thread to avoid blocking the dimension tick thread
        // while loading chunks in the target dimension
        Server.getInstance().getVirtualThreadPool().submit(() -> {
            NetherPortalHelper.teleport(thisEntity).thenAccept(success -> {
                if (!success) {
                    // Teleport failed, reset cooldown so entity can try again
                    portalCooldown = 0;
                }
            });
        });
    }

    @Override
    public boolean trySetLocation(Location3dc newLocation) {
        var event = new EntityMoveEvent(thisEntity, location, newLocation);
        if (!event.call()) {
            return false;
        }

        newLocation = event.getTo();
        if (this.immobile) {
            // immobile entity cannot move around, but is still allowed to look around
            var loc = new Location3d(newLocation);
            loc.set(this.location.x, this.location.y, this.location.z);
            if (loc.equals(this.location)) {
                // Same to the current location
                return false;
            }

            newLocation = loc;
        }

        setLocation(newLocation);
        broadcastMoveToViewers(newLocation, false);
        return true;
    }

    @Override
    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
        broadcastState();
    }

    @Override
    public void setNameTagAlwaysShow(boolean nameTagAlwaysShow) {
        this.nameTagAlwaysShow = nameTagAlwaysShow;
        broadcastState();
    }

    @Override
    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
        broadcastState();
    }

    @Override
    public void setImmobile(boolean immobile) {
        this.immobile = immobile;
        broadcastState();
    }

    @Override
    public void setScale(double scale) {
        this.scale = scale;
        broadcastState();
    }

    @Override
    public void setLocationBeforeSpawn(Location3dc location) {
        if (!canBeSpawnedIgnoreLocation()) {
            throw new IllegalStateException("Trying to set location of an entity which cannot being spawned!");
        }

        setLocationWithoutChunkCheck(location, false);
    }

    protected void setLocationWithoutChunkCheck(Location3dc location, boolean calculateFallDistance) {
        if (MathUtils.hasNaN(location)) {
            throw new IllegalArgumentException("Trying to set the location of entity " + runtimeId + " to a new location which contains NaN: " + location);
        }

        this.manager.callEvent(new CEntitySetLocationEvent(location, calculateFallDistance));
        this.lastLocation.set(this.location);
        this.location.set(location);
        this.location.setYaw(location.yaw());
        this.location.setPitch(location.pitch());
        this.location.setDimension(location.dimension());
    }

    @Override
    public Dimension getDimension() {
        return location.dimension();
    }

    @Override
    public void remove(Runnable callback) {
        getDimension().getEntityManager().removeEntity(thisEntity, callback);
    }

    public synchronized boolean setState(EntityState status) {
        if (!status.getPreviousStates().isEmpty() && !status.getPreviousStates().contains(this.state)) {
            log.warn("Trying to set status of entity {} to {} but the current status is {}", thisEntity, status, this.state);
            return false;
        }

        this.state = status;
        return true;
    }

    @Override
    public boolean canBeSpawned() {
        return location.x != Integer.MAX_VALUE &&
               location.y != Integer.MAX_VALUE &&
               location.z != Integer.MAX_VALUE &&
               location.dimension() != null &&
               canBeSpawnedIgnoreLocation();
    }

    protected boolean canBeSpawnedIgnoreLocation() {
        return state == EntityState.DESPAWNED;
    }

    protected void setLocation(Location3dc newLoc) {
        setLocation(newLoc, true);
    }

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
            // Teleporting in the current same dimension,
            // and we just need to move the entity to the new coordination
            teleportInDimension(target);
        } else {
            teleportOverDimension(target);
        }

        return true;
    }

    protected void beforeTeleport(Location3dc target) {
        this.manager.callEvent(CEntityBeforeTeleportEvent.INSTANCE);
    }

    protected void teleportInDimension(Location3dc target) {
        // This method should always return true because we've loaded the chunk
        setLocation(target, false);
        broadcastMoveToViewers(target, true);
    }

    protected void teleportOverDimension(Location3dc target) {
        // Teleporting to another dimension, there will be more works to be done
        this.location.dimension().getEntityManager().removeEntity(thisEntity, () -> {
            setLocationBeforeSpawn(target);
            target.dimension().getEntityManager().addEntity(thisEntity);
        });
    }

    @Override
    public AABBdc getBaseAABB() {
        // Default aabb is player's aabb
        return new AABBd(-0.3, 0.0, -0.3, 0.3, 1.8, 0.3);
    }

    @Override
    public AABBdc getOffsetAABBForCollisionCheck() {
        return new AABBd(getOffsetAABB()).expand(2 * FAT_AABB_MARGIN);
    }

    @Override
    @UnmodifiableView
    public Set<WorldViewer> getViewers() {
        return Collections.unmodifiableSet(viewers);
    }

    @Override
    public void spawnTo(WorldViewer viewer) {
        viewers.add(viewer);
        viewer.viewEntity(thisEntity);
        viewer.viewEntityState(thisEntity);
    }

    @Override
    public void despawnFrom(WorldViewer viewer) {
        viewers.remove(viewer);
        viewer.removeEntity(thisEntity);
    }

    @Override
    public void despawnFromAll() {
        for (var iterator = viewers.iterator(); iterator.hasNext(); ) {
            iterator.next().removeEntity(thisEntity);
            iterator.remove();
        }
    }

    public void broadcastMoveToViewers(Location3dc newLocation, boolean teleporting) {
        forEachViewers(viewer -> viewer.viewEntityLocation(thisEntity, newLocation, teleporting));
        if (thisEntity instanceof EntityPhysicsComponent physicsComponent) {
            forEachViewers(viewer -> viewer.viewEntityMotion((Entity & EntityPhysicsComponent) thisEntity, physicsComponent.getMotion()));
        }
    }

    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder();
        this.manager.callEvent(new CEntitySaveNBTEvent(builder));

        builder.putString(TAG_IDENTIFIER, this.entityType.getIdentifier().toString());
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

        return builder.build();
    }

    protected void saveUniqueId(NbtMapBuilder builder) {
        builder.putLong(TAG_UNIQUE_ID, uniqueId.getLeastSignificantBits());
    }

    @Override
    public void loadNBT(NbtMap nbt) {
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
            this.persistentDataContainer.clear();
            this.persistentDataContainer.putAll(customNbt);
        });

        loadUniqueId(nbt);

        if (nbt.containsKey(TAG_PORTAL_COOLDOWN)) {
            this.portalCooldown = nbt.getInt(TAG_PORTAL_COOLDOWN);
        }
    }

    protected void loadUniqueId(NbtMap nbt) {
        if (nbt.containsKey(TAG_UNIQUE_ID)) {
            this.uniqueId = new UUID(0, nbt.getLong(TAG_UNIQUE_ID));
            return;
        }

        // Generate a new UUID with only the upper 8 bytes filled since this UUID
        // needs to be translatable to a 64bits value
        this.uniqueId = new UUID(0, UUID.randomUUID().getLeastSignificantBits());
    }

    @Override
    public String getCommandSenderName() {
        return this.displayName;
    }

    @Override
    public Location3dc getCommandExecuteLocation() {
        return getLocation();
    }

    @Override
    public boolean isEntity() {
        return true;
    }

    @Override
    public Entity asEntity() {
        return thisEntity;
    }

    @Override
    public void sendMessage(String message) {
        // Do nothing
    }

    @Override
    public void sendTranslatable(String translatable, Object... args) {
        // Do nothing
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
        // Do nothing
    }

    @Override
    public boolean addTag(String tag) {
        return tags.add(tag);
    }

    @Override
    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    @Override
    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    @Override
    @UnmodifiableView
    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public boolean checkBlockCollision() {
        if (!this.isSpawned()) {
            // Unspawned entity won't collide with blocks
            return false;
        }

        var aabb = getOffsetAABBForCollisionCheck();
        return getDimension().getCollidingBlockStates(aabb) != null;
    }

    @Override
    public void applyAction(EntityAction action) {
        forEachViewers(viewer -> viewer.viewEntityAction(thisEntity, action));
    }

    @Override
    public void applyAnimation(EntityAnimation animation) {
        forEachViewers(viewer -> viewer.viewEntityAnimation(thisEntity, animation));
    }
}
