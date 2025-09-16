package org.allaymc.server.entity.component;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityState;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.data.EntityData;
import org.allaymc.api.entity.data.EntityFlag;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.event.entity.*;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.permission.Permissible;
import org.allaymc.api.permission.PermissionGroup;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.AllayNbtUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.entity.Metadata;
import org.allaymc.server.entity.component.event.*;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3f;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import static org.allaymc.api.utils.AllayNbtUtils.readVector2f;
import static org.allaymc.api.utils.AllayNbtUtils.readVector3f;
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
    protected static final String TAG_ACTIVE_EFFECTS = "ActiveEffects";
    protected static final String TAG_UNIQUE_ID = "UniqueID";
    protected static final String TAG_PDC = "PDC";

    protected static final int DEFAULT_DEAD_TIMER = 20;
    // NOTICE: the runtime id is counted from 1 not 0
    protected static final AtomicLong RUNTIME_ID_COUNTER = new AtomicLong(1);
    protected static final CommandOriginData ENTITY_COMMAND_ORIGIN_DATA = new CommandOriginData(CommandOriginType.ENTITY, UUID.randomUUID(), "", 0);

    @Getter
    protected final Location3d location;
    @Getter
    protected final Location3d lastLocation;
    protected final Location3d locationLastSent;
    @Getter
    protected final long runtimeId;
    @Getter
    protected final Metadata metadata;
    @Getter
    protected PermissionGroup permissionGroup;
    // Will be reset in method loadUniqueId()
    @Getter
    protected long uniqueId = Long.MAX_VALUE;
    @Manager
    protected ComponentManager manager;
    @ComponentObject
    protected Entity thisEntity;
    @Dependency(optional = true)
    protected EntityAttributeComponent attributeComponent;
    @Getter
    protected EntityType<? extends Entity> entityType;
    protected Set<WorldViewer> viewers;
    protected Map<EffectType, EffectInstance> effects;
    @Getter
    protected EntityState state;
    protected int deadTimer;
    @Getter
    @Setter
    protected String displayName;
    protected Set<String> tags;
    @Getter
    @Setter
    protected PersistentDataContainer persistentDataContainer;

    public EntityBaseComponentImpl(EntityInitInfo info) {
        this.location = new Location3d(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, info.dimension());
        this.lastLocation = new Location3d(this.location);
        this.locationLastSent = new Location3d(this.location);
        this.runtimeId = RUNTIME_ID_COUNTER.getAndIncrement();
        this.metadata = new Metadata();
        this.entityType = info.getEntityType();
        this.viewers = new HashSet<>();
        this.effects = new HashMap<>();
        this.state = EntityState.DESPAWNED;
        this.tags = new HashSet<>();
        this.persistentDataContainer = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);
        setDisplayName(entityType.getIdentifier().toString());
    }

    @OnInitFinish
    public void onInitFinish(EntityInitInfo initInfo) {
        loadNBT(initInfo.nbt());
        initMetadata();
        initPermissionGroup();
    }

    protected void initMetadata() {
        this.manager.callEvent(CEntityInitMetadataEvent.INSTANCE);
        setData(EntityData.PLAYER_INDEX, 0);
        setFlag(EntityFlag.HAS_GRAVITY, true);
        setFlag(EntityFlag.HAS_COLLISION, true);
        setFlag(EntityFlag.CAN_CLIMB, true);
        updateHitBoxAndCollisionBoxMetadata();
    }

    protected void initPermissionGroup() {
        this.permissionGroup = PermissionGroup.create("Permission group for entity " + uniqueId, Set.of(), Set.of(PermissionGroups.OPERATOR), false);
    }

    @Override
    public Permissible getPermissible() {
        return thisEntity;
    }

    protected NbtMap buildAABBTag() {
        var aabb = getAABB();
        return NbtMap.builder()
                .putFloat("MinX", 0)
                .putFloat("MinY", 0)
                .putFloat("MinZ", 0)
                .putFloat("MaxX", (float) (aabb.maxX() - aabb.minX()))
                .putFloat("MaxY", (float) (aabb.maxY() - aabb.minY()))
                .putFloat("MaxZ", (float) (aabb.maxZ() - aabb.minZ()))
                .putFloat("PivotX", 0)
                .putFloat("PivotY", 0)
                .putFloat("PivotZ", 0)
                .build();
    }

    protected void updateHitBoxAndCollisionBoxMetadata() {
        var aabb = getAABB();
        setData(EntityData.HITBOX, buildAABBTag());
        setData(EntityData.COLLISION_BOX, new Vector3f(
                (float) (aabb.maxX() - aabb.minX()),
                (float) (aabb.maxY() - aabb.minY()),
                (float) (aabb.maxZ() - aabb.minZ())
        ));
    }

    public void tick(long currentTick) {
        checkDead();
        tickEffects();
        computeAndNotifyCollidedBlocks();

        manager.callEvent(new CEntityTickEvent(currentTick));
    }

    protected void computeAndNotifyCollidedBlocks() {
        var aabb = getOffsetAABBForCollisionCheck();
        var dimension = getDimension();
        dimension.forEachBlockStates(aabb, 0, (x, y, z, blockState) -> {
            var block = new Block(blockState, new Position3i(x, y, z, dimension), 0);

            if (blockState.getBlockStateData().collisionShape().translate(x, y, z).intersectsAABB(aabb)) {
                this.onCollideWithBlock(block);
                blockState.getBehavior().onCollideWithEntity(block, thisEntity);
            }

            // Check if the entity is inside block
            if (blockState.getBlockStateData().shape().translate(x, y, z).intersectsAABB(aabb)) {
                blockState.getBehavior().onEntityInside(block, thisEntity);
                this.onInsideBlock(block);
            }
        });
    }

    @Override
    public boolean trySetLocation(Location3dc newLocation) {
        var event = new EntityMoveEvent(thisEntity, location, newLocation);
        if (!event.call()) {
            return false;
        }

        newLocation = event.getTo();
        this.setLocation(newLocation);
        this.broadcastMoveToViewers(newLocation, false);
        return true;
    }

    protected void tickEffects() {
        if (effects.isEmpty()) {
            return;
        }

        for (var effect : effects.values().toArray(EffectInstance[]::new)) {
            effect.setDuration(effect.getDuration() - 1);
            effect.getType().onTick(thisEntity, effect);
            if (effect.getDuration() <= 0) {
                removeEffect(effect.getType());
            }
        }
    }

    protected void checkDead() {
        // TODO: move these code to EntityAttributeComponentImpl
        if (attributeComponent == null || !attributeComponent.supportAttribute(AttributeType.HEALTH)) {
            return;
        }

        if (attributeComponent.getHealth() == 0 && !isDead()) {
            onDie();
        }
        if (isDead()) {
            if (hasDeadTimer()) {
                if (deadTimer > 0) deadTimer--;
                if (deadTimer == 0) {
                    // Spawn dead particle
                    spawnDeadParticle();
                    getDimension().getEntityManager().removeEntity(thisEntity);
                }
            } else {
                getDimension().getEntityManager().removeEntity(thisEntity);
            }
        }
    }

    protected boolean hasDeadTimer() {
        return true;
    }

    protected void onDie() {
        new EntityDieEvent(thisEntity).call();

        manager.callEvent(CEntityDieEvent.INSTANCE);
        setState(EntityState.DEAD);
        if (hasDeadTimer()) {
            deadTimer = DEFAULT_DEAD_TIMER;
        }

        applyAction(SimpleEntityAction.DEATH);
        effects.values().forEach(effect -> effect.getType().onEntityDies(thisEntity, effect));
        removeAllEffects();
    }

    protected void spawnDeadParticle() {
        var offsetAABB = getOffsetAABB();
        for (double x = offsetAABB.minX(); x <= offsetAABB.maxX(); x += 0.5) {
            for (double z = offsetAABB.minZ(); z <= offsetAABB.maxZ(); z += 0.5) {
                for (double y = offsetAABB.minY(); y <= offsetAABB.maxY(); y += 0.5) {
                    this.getDimension().addParticle(x, y, z, SimpleParticle.EXPLODE);
                }
            }
        }
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
        this.location.setHeadYaw(location.headYaw());
        this.location.setPitch(location.pitch());
        this.location.setDimension(location.dimension());
    }

    @Override
    public Dimension getDimension() {
        return location.dimension();
    }

    @Override
    public void remove() {
        getDimension().getEntityManager().removeEntity(thisEntity);
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

        oldChunkOnlyLoaders.stream().filter(loader -> loader != thisEntity).forEach(this::despawnFrom);
        newChunkOnlyLoaders.stream().filter(loader -> loader != thisEntity).forEach(this::spawnTo);
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
    public AABBdc getAABB() {
        // Default aabb is player's aabb
        return new AABBd(-0.3, 0.0, -0.3, 0.3, 1.8, 0.3);
    }

    @Override
    public AABBd getOffsetAABBForCollisionCheck() {
        return getOffsetAABB().expand(2 * FAT_AABB_MARGIN);
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
    }

    @Override
    public void despawnFrom(WorldViewer viewer) {
        viewers.remove(viewer);
        viewer.removeEntity(thisEntity);
    }

    @Override
    public void despawnFromAll() {
        viewers.forEach(this::despawnFrom);
    }

    public void broadcastMoveToViewers(Location3dc newLocation, boolean teleporting) {
        forEachViewers(viewer -> viewer.viewEntityLocation(thisEntity, locationLastSent, newLocation, teleporting));
        if (thisEntity instanceof EntityPhysicsComponent physicsComponent) {
            forEachViewers(viewer -> viewer.viewEntityMotion((Entity & EntityPhysicsComponent) thisEntity, physicsComponent.getMotion()));
        }
    }

    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder();
        builder.putString(TAG_IDENTIFIER, entityType.getIdentifier().toString());
        AllayNbtUtils.writeVector3f(builder, TAG_POS, (float) location.x, (float) location.y, (float) location.z);
        AllayNbtUtils.writeVector2f(builder, TAG_ROTATION, (float) location.yaw(), (float) location.pitch());
        if (!tags.isEmpty()) {
            builder.putList(TAG_TAGS, NbtType.STRING, new ArrayList<>(tags));
        }
        if (!effects.isEmpty()) {
            builder.putList(TAG_ACTIVE_EFFECTS, NbtType.COMPOUND, effects.values().stream().map(EffectInstance::saveNBT).toList());
        }
        if (!persistentDataContainer.isEmpty()) {
            builder.put(TAG_PDC, persistentDataContainer.toNbt());
        }
        saveUniqueId(builder);
        var event = new CEntitySaveNBTEvent(builder);
        manager.callEvent(event);
        return builder.build();
    }

    protected void saveUniqueId(NbtMapBuilder builder) {
        builder.putLong(TAG_UNIQUE_ID, uniqueId);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        if (nbt.containsKey(TAG_POS)) {
            var pos = readVector3f(nbt, TAG_POS);
            location.set(pos.x, pos.y, pos.z);
            lastLocation.set(location);
        }

        if (nbt.containsKey(TAG_ROTATION)) {
            var rot = readVector2f(nbt, TAG_ROTATION);
            location.setYaw(rot.x);
            location.setPitch(rot.y);
        }

        nbt.listenForList(TAG_TAGS, NbtType.STRING, tags -> this.tags.addAll(tags));
        nbt.listenForList(TAG_ACTIVE_EFFECTS, NbtType.COMPOUND, activeEffects -> {
            for (NbtMap activeEffect : activeEffects) {
                var effectInstance = EffectInstance.fromNBT(activeEffect);
                addEffect(effectInstance);
            }
        });
        nbt.listenForCompound(TAG_PDC, customNbt -> {
            this.persistentDataContainer.clear();
            this.persistentDataContainer.putAll(customNbt);
        });

        loadUniqueId(nbt);
        var event = new CEntityLoadNBTEvent(nbt);
        manager.callEvent(event);
    }

    protected void loadUniqueId(NbtMap nbt) {
        if (nbt.containsKey(TAG_UNIQUE_ID)) {
            this.uniqueId = nbt.getLong(TAG_UNIQUE_ID);
            return;
        }

        this.uniqueId = UUID.randomUUID().getMostSignificantBits();
    }

    @Override
    public Map<EffectType, EffectInstance> getAllEffects() {
        return Collections.unmodifiableMap(effects);
    }

    @Override
    public boolean hasEffect(EffectType effectType) {
        return effects.containsKey(effectType);
    }

    @Override
    public int getEffectLevel(EffectType effectType) {
        var effect = effects.get(effectType);
        return effect == null ? 0 : effect.getLevel();
    }

    @Override
    public boolean addEffect(EffectInstance effectInstance) {
        if (!canApplyEffect(effectInstance.getType())) {
            return false;
        }

        var event = new EntityEffectAddEvent(thisEntity, effectInstance);
        if (!event.call()) {
            return false;
        }

        effectInstance = event.getEffect();
        var old = effects.put(effectInstance.getType(), effectInstance);
        if (old != null && old.getType() != effectInstance.getType()) {
            old.getType().onRemove(thisEntity, old);
            effectInstance.getType().onAdd(thisEntity, effectInstance);
        }

        sendMobEffect(effectInstance, old);
        if (old == null) {
            syncVisibleEffects();
        }

        return true;
    }

    @Override
    public void removeEffect(EffectType effectType) {
        var removed = effects.get(effectType);
        if (removed == null) {
            return;
        }

        var event = new EntityEffectRemoveEvent(thisEntity, removed);
        if (!event.call()) {
            return;
        }

        effects.remove(effectType);
        effectType.onRemove(thisEntity, removed);
        sendMobEffect(null, removed);
        syncVisibleEffects();
    }

    protected void sendMobEffect(EffectInstance newEffect, EffectInstance oldEffect) {
        forEachViewers(viewer -> viewer.viewEntityEffectChange(thisEntity, newEffect, oldEffect));
    }

    @Override
    public void removeAllEffects() {
        // Prevent ConcurrentModificationException
        for (EffectType effectType : this.effects.keySet().toArray(EffectType[]::new)) {
            removeEffect(effectType);
        }
    }

    protected void syncVisibleEffects() {
        long visibleEffects = 0;
        for (var effect : this.effects.values()) {
            if (!effect.isVisible()) {
                continue;
            }

            visibleEffects = (visibleEffects << 7) | ((long) effect.getType().getId() << 1) | (effect.isAmbient() ? 1 : 0);
        }

        setData(EntityData.VISIBLE_MOB_EFFECTS, visibleEffects);
    }

    @Override
    public String getCommandSenderName() {
        return getDisplayName();
    }

    @Override
    public CommandOriginData getCommandOriginData() {
        return ENTITY_COMMAND_ORIGIN_DATA;
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
    public void sendText(String text) {
        // Do nothing
    }

    @Override
    public void sendTr(String key, boolean forceTranslatedByClient, Object... args) {
        // Do nothing
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs) {
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
    public <T> T getData(EntityData<T> dataType) {
        return this.metadata.get(dataType);
    }

    @Override
    public <T> void setData(EntityData<T> dataType, T value) {
        Preconditions.checkArgument(dataType.getType().isInstance(value));
        if (this.metadata.get(dataType) != value) {
            this.metadata.set(dataType, value);
            sendMetadata();
        }
    }

    @Override
    public boolean getFlag(EntityFlag flag) {
        return this.metadata.get(flag);
    }

    @Override
    public void setFlag(EntityFlag flag, boolean value) {
        if (this.metadata.get(flag) != value) {
            this.metadata.set(flag, value);
            sendMetadata();
        }
    }

    protected void sendMetadata() {
        forEachViewers(viewer -> viewer.viewEntityMetadata(thisEntity));
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
