package org.allaymc.server.entity.component;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityStatus;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.metadata.Metadata;
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
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayNbtUtils;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.entity.component.event.*;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Math.abs;
import static org.allaymc.api.utils.AllayNbtUtils.readVector2f;
import static org.allaymc.api.utils.AllayNbtUtils.readVector3f;
import static org.allaymc.server.world.service.AllayEntityPhysicsService.FAT_AABB_MARGIN;
import static org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket.Flag.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityBaseComponentImpl implements EntityBaseComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_base_component");

    // This tag is also used in EntityPlayerNetworkComponentImpl, so make it public for reuse
    public static final String TAG_POS = "Pos";
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
    protected Map<Long, EntityPlayer> viewers;
    protected Map<EffectType, EffectInstance> effects;
    @Getter
    protected EntityStatus status;
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
        this.viewers = new Long2ObjectOpenHashMap<>();
        this.effects = new HashMap<>();
        this.status = EntityStatus.DESPAWNED;
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
        this.metadata.set(EntityDataTypes.PLAYER_INDEX, 0);
        this.metadata.set(EntityFlag.HAS_GRAVITY, true);
        this.metadata.set(EntityFlag.HAS_COLLISION, true);
        this.metadata.set(EntityFlag.CAN_CLIMB, true);
        updateHitBoxAndCollisionBoxMetadata();
    }

    protected void initPermissionGroup() {
        this.permissionGroup = PermissionGroup.create("Permission group for entity " + uniqueId, Set.of(), PermissionGroups.OPERATOR, false);
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
        metadata.set(EntityDataTypes.HITBOX, buildAABBTag());
        var aabb = getAABB();
        metadata.set(EntityDataTypes.COLLISION_BOX,
                Vector3f.from(
                        aabb.maxX() - aabb.minX(),
                        aabb.maxY() - aabb.minY(),
                        aabb.maxZ() - aabb.minZ())
        );
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
                    getDimension().getEntityService().removeEntity(thisEntity);
                }
            } else {
                getDimension().getEntityService().removeEntity(thisEntity);
            }
        }
    }

    protected boolean hasDeadTimer() {
        return true;
    }

    protected void onDie() {
        new EntityDieEvent(thisEntity).call();

        manager.callEvent(CEntityDieEvent.INSTANCE);
        setStatus(EntityStatus.DEAD);
        if (hasDeadTimer()) {
            deadTimer = DEFAULT_DEAD_TIMER;
        }

        applyEntityEvent(EntityEventType.DEATH, 0);
        effects.values().forEach(effect -> effect.getType().onEntityDies(thisEntity, effect));
        removeAllEffects();
    }

    protected void spawnDeadParticle() {
        var offsetAABB = getOffsetAABB();
        for (double x = offsetAABB.minX(); x <= offsetAABB.maxX(); x += 0.5) {
            for (double z = offsetAABB.minZ(); z <= offsetAABB.maxZ(); z += 0.5) {
                for (double y = offsetAABB.minY(); y <= offsetAABB.maxY(); y += 0.5) {
                    this.getDimension().addParticle(x, y, z, ParticleType.EXPLODE);
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
    public void despawn() {
        getDimension().getEntityService().removeEntity(thisEntity);
    }

    public synchronized boolean setStatus(EntityStatus status) {
        if (!status.getPreviousStatuses().isEmpty() && !status.getPreviousStatuses().contains(this.status)) {
            log.warn("Trying to set status of entity {} to {} but the current status is {}", thisEntity, status, this.status);
            return false;
        }

        this.status = status;
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
        return status == EntityStatus.DESPAWNED;
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

        var oldChunk = oldDimension != null ? oldDimension.getChunkService().getChunk(oldChunkX, oldChunkZ) : null;
        var newChunk = newDimension != null ? newDimension.getChunkService().getChunk(newChunkX, newChunkZ) : null;
        Set<EntityPlayer> oldChunkPlayers = oldChunk != null ? oldChunk.getPlayerChunkLoaders() : Collections.emptySet();
        Set<EntityPlayer> newChunkPlayers = newChunk != null ? newChunk.getPlayerChunkLoaders() : Collections.emptySet();
        Set<EntityPlayer> oldChunkOnlyPlayers = new HashSet<>(oldChunkPlayers);
        oldChunkOnlyPlayers.removeAll(newChunkPlayers);
        Set<EntityPlayer> newChunkOnlyPlayers = new HashSet<>(newChunkPlayers);
        newChunkOnlyPlayers.removeAll(oldChunkPlayers);

        oldChunkOnlyPlayers.stream().filter(player -> player != thisEntity).forEach(this::despawnFrom);
        newChunkOnlyPlayers.stream().filter(player -> player != thisEntity).forEach(this::spawnTo);
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
        this.location.dimension().getEntityService().removeEntity(thisEntity, () -> {
            setLocationBeforeSpawn(target);
            target.dimension().getEntityService().addEntity(thisEntity);
        });
    }

    @Override
    public void sendMetadata() {
        if (viewers.isEmpty()) {
            return;
        }

        sendPacketToViewers(createSetEntityDataPacket());
    }

    protected SetEntityDataPacket createSetEntityDataPacket() {
        var packet = new SetEntityDataPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setMetadata(this.metadata.getEntityDataMap());
        packet.setTick(this.getWorld().getTick());
        return packet;
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
    public Map<Long, EntityPlayer> getViewers() {
        return Collections.unmodifiableMap(viewers);
    }

    @Override
    public void spawnTo(EntityPlayer player) {
        player.sendPacket(createSpawnPacket());
        viewers.put(player.getRuntimeId(), player);
    }

    @Override
    public void despawnFrom(EntityPlayer player) {
        var pk = new RemoveEntityPacket();
        pk.setUniqueEntityId(runtimeId);
        player.sendPacket(pk);
        viewers.remove(player.getRuntimeId());
    }

    @Override
    public void despawnFromAll() {
        viewers.values().forEach(this::despawnFrom);
    }

    @Override
    public BedrockPacket createSpawnPacket() {
        var packet = createSpawnPacket0();
        var event = new CEntityCreateSpawnPacketEvent(packet);
        this.manager.callEvent(event);
        return event.getPacket();
    }

    protected BedrockPacket createSpawnPacket0() {
        var packet = new AddEntityPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setUniqueEntityId(runtimeId);
        packet.setIdentifier(entityType.getIdentifier().toString());
        packet.setPosition(Vector3f.from(location.x(), location.y() + getNetworkOffset(), location.z()));
        packet.setMotion(Vector3f.ZERO);
        packet.setRotation(Vector2f.from(location.pitch(), location.yaw()));
        packet.getMetadata().putAll(metadata.getEntityDataMap());
        return packet;
    }

    @Override
    public void sendPacketToViewers(BedrockPacket packet) {
        viewers.values().forEach(client -> client.sendPacket(packet));
    }

    @Override
    public void sendPacketToViewersImmediately(BedrockPacket packet) {
        viewers.values().forEach(client -> client.sendPacketImmediately(packet));
    }

    public void broadcastMoveToViewers(Location3dc newLoc, boolean teleporting) {
        var event = new CEntityCreateMovePacketEvent(Lists.newArrayList(createMovePacket(newLoc, teleporting)));
        manager.callEvent(event);
        event.getPackets().forEach(this::sendPacketToViewers);
    }

    protected BedrockPacket createMovePacket(Location3dc newLoc, boolean teleporting) {
        return Server.SETTINGS.entitySettings().physicsEngineSettings().useDeltaMovePacket() ?
                createDeltaMovePacket(newLoc, teleporting) :
                createAbsoluteMovePacket(newLoc, teleporting);
    }

    protected BedrockPacket createAbsoluteMovePacket(Location3dc newLoc, boolean teleporting) {
        var pk = new MoveEntityAbsolutePacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setPosition(Vector3f.from(newLoc.x(), newLoc.y() + getNetworkOffset(), newLoc.z()));
        pk.setRotation(Vector3f.from(newLoc.pitch(), newLoc.yaw(), newLoc.headYaw()));
        pk.setTeleported(teleporting);
        return pk;
    }

    protected BedrockPacket createDeltaMovePacket(Location3dc newLoc, boolean teleporting) {
        var pk = new MoveEntityDeltaPacket();
        pk.setRuntimeEntityId(getRuntimeId());
        var moveFlags = computeMoveFlags(newLoc);
        pk.getFlags().addAll(moveFlags);
        if (moveFlags.contains(HAS_X)) {
            pk.setX((float) newLoc.x());
            locationLastSent.x = newLoc.x();
        }
        if (moveFlags.contains(HAS_Y)) {
            pk.setY((float) newLoc.y());
            locationLastSent.y = newLoc.y() + getNetworkOffset();
        }
        if (moveFlags.contains(HAS_Z)) {
            pk.setZ((float) newLoc.z());
            locationLastSent.z = newLoc.z();
        }
        if (moveFlags.contains(HAS_PITCH)) {
            pk.setPitch((float) newLoc.pitch());
            locationLastSent.pitch = newLoc.pitch();
        }
        if (moveFlags.contains(HAS_YAW)) {
            pk.setYaw((float) newLoc.yaw());
            locationLastSent.yaw = newLoc.yaw();
        }
        if (moveFlags.contains(HAS_HEAD_YAW)) {
            pk.setHeadYaw((float) newLoc.headYaw());
            locationLastSent.headYaw = newLoc.headYaw();
        }
        if (teleporting) {
            pk.getFlags().add(TELEPORTING);
        }
        return pk;
    }

    protected Set<MoveEntityDeltaPacket.Flag> computeMoveFlags(Location3dc newLoc) {
        var flags = EnumSet.noneOf(MoveEntityDeltaPacket.Flag.class);
        var settings = Server.SETTINGS.entitySettings().physicsEngineSettings();
        var diffPositionThreshold = settings.diffPositionThreshold();
        var diffRotationThreshold = settings.diffRotationThreshold();
        if (abs(locationLastSent.x() - newLoc.x()) > diffPositionThreshold) flags.add(HAS_X);
        if (abs(locationLastSent.y() - newLoc.y()) > diffPositionThreshold) flags.add(HAS_Y);
        if (abs(locationLastSent.z() - newLoc.z()) > diffPositionThreshold) flags.add(HAS_Z);
        if (abs(locationLastSent.yaw() - newLoc.yaw()) > diffRotationThreshold) flags.add(HAS_YAW);
        if (abs(locationLastSent.pitch() - newLoc.pitch()) > diffRotationThreshold) flags.add(HAS_PITCH);
        if (enableHeadYaw() && abs(locationLastSent.headYaw() - newLoc.headYaw()) > diffRotationThreshold)
            flags.add(HAS_HEAD_YAW);
        return flags;
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
        if (!canApplyEffect(effectInstance.getType())) return false;

        var event = new EntityEffectAddEvent(thisEntity, effectInstance);
        if (!event.call()) return false;

        effectInstance = event.getEffect();

        var old = effects.put(effectInstance.getType(), effectInstance);
        if (old != null) old.getType().onRemove(thisEntity, old);

        effectInstance.getType().onAdd(thisEntity, effectInstance);

        var packet = new MobEffectPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setEffectId(effectInstance.getType().getId());
        packet.setAmplifier(effectInstance.getAmplifier());
        packet.setParticles(effectInstance.isVisible());
        packet.setDuration(effectInstance.getDuration());
        packet.setEvent(old == null ? MobEffectPacket.Event.ADD : MobEffectPacket.Event.MODIFY);
        sendMobEffectPacket(packet);

        if (old == null) syncVisibleEffects();
        return true;
    }

    @Override
    public void removeEffect(EffectType effectType) {
        var removed = effects.get(effectType);
        if (removed == null) return;

        var event = new EntityEffectRemoveEvent(thisEntity, removed);
        if (!event.call()) return;

        effects.remove(effectType);

        effectType.onRemove(thisEntity, removed);

        var packet = new MobEffectPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setEffectId(effectType.getId());
        packet.setEvent(MobEffectPacket.Event.REMOVE);
        sendMobEffectPacket(packet);

        syncVisibleEffects();
    }

    protected void sendMobEffectPacket(MobEffectPacket packet) {
        sendPacketToViewers(packet);
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

        setAndSendEntityData(EntityDataTypes.VISIBLE_MOB_EFFECTS, visibleEffects);
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
}
