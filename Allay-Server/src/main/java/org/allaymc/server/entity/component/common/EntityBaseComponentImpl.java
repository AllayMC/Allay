package org.allaymc.server.entity.component.common;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;
import org.allaymc.api.entity.component.common.EntityBaseComponent;
import org.allaymc.api.entity.component.event.EntityFallEvent;
import org.allaymc.api.entity.component.event.EntityLoadNBTEvent;
import org.allaymc.api.entity.component.event.EntitySaveNBTEvent;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.init.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.metadata.Metadata;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.event.world.entity.EntityDieEvent;
import org.allaymc.api.eventbus.event.world.entity.EntityTeleportEvent;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.perm.DefaultPermissions;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.server.world.chunk.AllayChunk;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static org.allaymc.api.utils.AllayNbtUtils.readVector2f;
import static org.allaymc.api.utils.AllayNbtUtils.readVector3f;
import static org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket.Flag.*;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityBaseComponentImpl<T extends Entity> implements EntityBaseComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_base_component");

    public static final int DEFAULT_DEAD_TIMER = 20;

    protected static final AtomicLong RUNTIME_ID_COUNTER = new AtomicLong(0);

    private static final CommandOriginData ENTITY_COMMAND_ORIGIN_DATA = new CommandOriginData(CommandOriginType.ENTITY, UUID.randomUUID(), "", 0);

    @Getter
    protected final Location3f location;
    protected final Location3f locLastSent = new Location3f(0, 0, 0, null);
    @Getter
    protected final long runtimeId = RUNTIME_ID_COUNTER.getAndIncrement();
    @Getter
    protected final Metadata metadata;
    // Will be reset in method loadUniqueId()
    @Getter
    protected long uniqueId = Long.MAX_VALUE;
    @Manager
    protected ComponentManager<T> manager;
    @ComponentedObject
    protected T thisEntity;
    @Dependency(soft = true)
    protected EntityAttributeComponent attributeComponent;
    @Getter
    protected EntityType<T> entityType;
    protected Map<Long, EntityPlayer> viewers = new Long2ObjectOpenHashMap<>();
    protected Map<EffectType, EffectInstance> effects = new HashMap<>();
    protected Vector3f motion = new Vector3f();
    @Getter
    protected boolean onGround = true;
    @Setter
    protected boolean willBeDespawnedNextTick = false;
    @Setter
    protected boolean willBeSpawnedNextTick = false;
    @Getter
    @Setter
    protected boolean spawned;
    @Getter
    protected boolean dead;
    protected int deadTimer;
    @Getter
    protected float fallDistance;
    @Getter
    @Setter
    protected String displayName;
    protected Set<String> tags = new HashSet<>();

    public EntityBaseComponentImpl(EntityInitInfo<T> info) {
        this.location = new Location3f(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, info.dimension());
        this.entityType = info.getEntityType();
        this.metadata = new Metadata();
        setDisplayName(entityType.getIdentifier().toString());
    }

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        loadNBT(((EntityInitInfo<T>) initInfo).nbt());
        initMetadata();
    }

    protected void initMetadata() {
        metadata.set(EntityDataTypes.PLAYER_INDEX, 0);
        metadata.set(EntityDataTypes.AIR_SUPPLY, (short) 400);
        metadata.set(EntityDataTypes.AIR_SUPPLY_MAX, (short) 400);
        updateHitBoxAndCollisionBoxMetadata();
        metadata.set(EntityFlag.HAS_GRAVITY, true);
        metadata.set(EntityFlag.HAS_COLLISION, true);
        metadata.set(EntityFlag.CAN_CLIMB, true);
        metadata.set(EntityFlag.BREATHING, true);
    }

    private NbtMap buildAABBTag() {
        var aabb = getAABB();
        return NbtMap.builder()
                .putFloat("MinX", 0)
                .putFloat("MinY", 0)
                .putFloat("MinZ", 0)
                .putFloat("MaxX", aabb.maxX() - aabb.minX())
                .putFloat("MaxY", aabb.maxY() - aabb.minY())
                .putFloat("MaxZ", aabb.maxZ() - aabb.minZ())
                .putFloat("PivotX", 0)
                .putFloat("PivotY", 0)
                .putFloat("PivotZ", 0)
                .build();
    }

    private void updateHitBoxAndCollisionBoxMetadata() {
        metadata.set(EntityDataTypes.HITBOX, buildAABBTag());
        var aabb = getAABB();
        metadata.set(EntityDataTypes.COLLISION_BOX,
                org.cloudburstmc.math.vector.Vector3f.from(
                        aabb.maxX() - aabb.minX(),
                        aabb.maxY() - aabb.minY(),
                        aabb.maxZ() - aabb.minZ())
        );
    }

    @Override
    public void tick() {
        checkDead();
    }

    protected void checkDead() {
        if (attributeComponent == null) return;
        if (attributeComponent.getHealth() == 0 && !dead) {
            var event = new EntityDieEvent(thisEntity);
            getWorld().getEventBus().callEvent(event);
            manager.callEvent(new org.allaymc.api.entity.component.event.EntityDieEvent());
            dead = true;
            deadTimer = DEFAULT_DEAD_TIMER;
            applyEntityEvent(EntityEventType.DEATH, 0);
        }
        if (dead) {
            if (deadTimer > 0) deadTimer--;
            if (deadTimer == 0) {
                // Spawn dead particle
                spawnDeadParticle();
                despawn();
                // After call 'removeEntity()', this entity won't be ticked in next tick
                // So we set dead back to false
                dead = false;
            }
        }
    }

    protected void spawnDeadParticle() {
        var offsetAABB = getOffsetAABB();
        for (float x = offsetAABB.minX(); x <= offsetAABB.maxX(); x += 0.5f) {
            for (float z = offsetAABB.minZ(); z <= offsetAABB.maxZ(); z += 0.5f) {
                for (float y = offsetAABB.minY(); y <= offsetAABB.maxY(); y += 0.5f) {
                    this.getDimension().addParticle(ParticleType.EXPLODE, new Vector3f(x, y, z));
                }
            }
        }
    }

    @Override
    public void setLocationBeforeSpawn(Location3fc location) {
        if (!canBeSpawnedIgnoreLocation()) {
            throw new IllegalStateException("Trying to set location of an entity which cannot being spawned!");
        }
        setLocation(location, false);
    }

    protected void setLocation(Location3fc location, boolean calculateFallDistance) {
        if (calculateFallDistance && !this.onGround) {
            if (this.fallDistance < 0) this.fallDistance = 0;
            this.fallDistance -= location.y() - this.location.y();
        }

        this.location.set(location);
        this.location.setYaw(location.yaw());
        this.location.setHeadYaw(location.headYaw());
        this.location.setPitch(location.pitch());
        this.location.setDimension(location.dimension());
    }

    @Override
    public Dimension getDimension() {
        return location.dimension;
    }

    @Override
    public World getWorld() {
        return location.dimension.getWorld();
    }

    @Override
    public void despawn() {
        getDimension().getEntityService().removeEntity(thisEntity);
    }

    @Override
    public boolean willBeDespawnedNextTick() {
        return willBeDespawnedNextTick;
    }

    @Override
    public boolean canBeSpawned() {
        return location.x != Integer.MAX_VALUE &&
               location.y != Integer.MAX_VALUE &&
               location.z != Integer.MAX_VALUE &&
               location.dimension != null &&
               canBeSpawnedIgnoreLocation();
    }

    protected boolean canBeSpawnedIgnoreLocation() {
        return !spawned && !willBeSpawnedNextTick;
    }

    @Override
    public boolean willBeSpawnedNextTick() {
        return willBeSpawnedNextTick;
    }

    @Override
    public void setLocationAndCheckChunk(Location3fc newLoc) {
        checkChunk(this.location, newLoc);
        setLocation(newLoc, true);
    }

    protected void checkChunk(Location3fc oldLoc, Location3fc newLoc) {
        var oldChunkX = (int) oldLoc.x() >> 4;
        var oldChunkZ = (int) oldLoc.z() >> 4;
        var newChunkX = (int) newLoc.x() >> 4;
        var newChunkZ = (int) newLoc.z() >> 4;
        if (this.location.dimension != null && (oldChunkX != newChunkX || oldChunkZ != newChunkZ)) {
            var oldChunk = this.location.dimension().getChunkService().getChunk(oldChunkX, oldChunkZ);
            if (oldChunk != null) ((AllayChunk) oldChunk).removeEntity(runtimeId);
            else log.debug("Old chunk {} {} is null while moving entity!", oldChunkX, oldChunkZ);
        }

        var newChunk = newLoc.dimension().getChunkService().getChunk(newChunkX, newChunkZ);
        if (newChunk != null) ((AllayChunk) newChunk).addEntity(thisEntity);
        else {
            // Moving into an unloaded chunk is not allowed. Because the chunk holds the entity,
            // moving to an unloaded chunk will result in the loss of the entity
            log.debug("New chunk {} {} is null while moving entity!", newChunkX, newChunkZ);
        }
    }

    @Override
    public void teleport(Location3fc target) {
        Objects.requireNonNull(target.dimension());
        if (this.location.dimension == null) {
            log.warn("Trying to teleport an entity whose dimension is null! Entity: {}", thisEntity);
            return;
        }

        if (!this.spawned) {
            log.warn("Trying to teleport an entity which is not spawned! Entity: {}", thisEntity);
            return;
        }

        var event = new EntityTeleportEvent(thisEntity, this.location, new Location3f(target));
        var currentWorld = this.getWorld();
        var targetWorld = target.dimension().getWorld();
        currentWorld.getEventBus().callEvent(event);
        if (event.isCancelled()) return;
        if (targetWorld != currentWorld) targetWorld.getEventBus().callEvent(event);
        if (event.isCancelled()) return;
        target = event.getTo();
        if (this.location.dimension == target.dimension()) {
            // Teleporting in the current same dimension,
            // and we just need to move the entity to the new coordination
            teleportInDimension(target);
        } else {
            teleportOverDimension(target);
        }
    }

    protected void teleportInDimension(Location3fc target) {
        setLocationAndCheckChunk(target);
        broadcastMoveToViewers(target, true);
    }

    protected void teleportOverDimension(Location3fc target) {
        // Teleporting to another dimension, there will be more works to be done
        this.location.dimension().getEntityService().removeEntity(thisEntity, () -> {
            target.dimension().getChunkService().getOrLoadChunkSynchronously((int) target.x() >> 4, (int) target.z() >> 4);
            setLocationBeforeSpawn(target);
            target.dimension().getEntityService().addEntity(thisEntity);
        });
    }

    @Override
    public void sendEntityData(EntityDataType<?>... dataTypes) {
        if (viewers.isEmpty()) return;

        var pk = new SetEntityDataPacket();
        pk.setRuntimeEntityId(runtimeId);
        for (EntityDataType<?> type : dataTypes) {
            pk.getMetadata().put(type, metadata.getEntityDataMap().get(type));
        }
        pk.setTick(this.getWorld().getTick());
        sendPacketToViewers(pk);
    }

    @Override
    public void sendEntityFlags(EntityFlag... flags) {
        if (viewers.isEmpty()) return;

        var pk = new SetEntityDataPacket();
        pk.setRuntimeEntityId(runtimeId);
        for (EntityFlag flag : flags) {
            pk.getMetadata().setFlag(flag, metadata.get(flag));
        }
        pk.setTick(this.getWorld().getTick());
        sendPacketToViewers(pk);
    }

    @Override
    public AABBfc getAABB() {
        // Default aabb is player's
        return new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f);
    }

    @Override
    public void setHasEntityCollision(boolean hasEntityCollision) {
        metadata.set(EntityFlag.HAS_COLLISION, hasEntityCollision);
        sendEntityFlags(EntityFlag.HAS_COLLISION);
    }

    @Override
    public @UnmodifiableView Map<Long, EntityPlayer> getViewers() {
        return Collections.unmodifiableMap(viewers);
    }

    @Override
    public Vector3fc getMotion() {
        return motion;
    }

    @Override
    public void setMotion(Vector3fc motion) {
        this.motion = new Vector3f(motion);
    }

    @Override
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        if (onGround && this.fallDistance > 0) this.onFall();
    }

    @Override
    public void setHasGravity(boolean hasGravity) {
        metadata.set(EntityFlag.HAS_GRAVITY, hasGravity);
        sendEntityFlags(EntityFlag.HAS_GRAVITY);
    }

    @Override
    public void knockback(Vector3fc source) {
        var resistance = 0.6f;
        if (attributeComponent != null) {
            resistance = attributeComponent.getAttributeValue(AttributeType.KNOCKBACK_RESISTANCE);
        }
        var kb = 1 - resistance;
        if (kb <= 0) return;
        knockback(source, kb);
    }

    @Override
    public void knockback(Vector3fc source, float kb) {
        Vector3f vec;
        if (getLocation().distanceSquared(source) <= 0.0001 /* 0.01 * 0.01 */) {
            // Generate a random kb direction if distance <= 0.01m
            var rand = ThreadLocalRandom.current();
            var rx = rand.nextFloat(1) - 0.5f;
            var rz = rand.nextFloat(1) - 0.5f;
            vec = new Vector3f(rx, 0, rz).normalize().mul(kb);
        } else {
            vec = getLocation().sub(source, new Vector3f()).normalize().mul(kb);
            vec.y = 0;
        }
        motion = new Vector3f(
                motion.x / 2f + vec.x,
                min(motion.y / 2 + kb, kb),
                motion.z / 2f + vec.z
        );
    }

    @Override
    public void spawnTo(EntityPlayer player) {
        var pk = createSpawnPacket();
        player.sendPacket(pk);
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
        var addEntityPacket = new AddEntityPacket();
        addEntityPacket.setRuntimeEntityId(runtimeId);
        addEntityPacket.setUniqueEntityId(runtimeId);
        addEntityPacket.setIdentifier(entityType.getIdentifier().toString());
        addEntityPacket.setPosition(org.cloudburstmc.math.vector.Vector3f.from(location.x(), location.y() + getBaseOffset(), location.z()));
        addEntityPacket.setMotion(org.cloudburstmc.math.vector.Vector3f.from(motion.x(), motion.y(), motion.z()));
        addEntityPacket.setRotation(Vector2f.from(location.pitch(), location.yaw()));
        addEntityPacket.getMetadata().putAll(metadata.getEntityDataMap());
        return addEntityPacket;
    }

    @Override
    public void sendPacketToViewers(BedrockPacket packet) {
        viewers.values().forEach(client -> client.sendPacket(packet));
    }

    @Override
    public void sendPacketToViewersImmediately(BedrockPacket packet) {
        viewers.values().forEach(client -> client.handleChunkPacketImmediately(packet));
    }

    @Override
    public void broadcastMoveToViewers(Location3fc newLoc) {
        broadcastMoveToViewers(newLoc, false);
    }

    @Override
    public void broadcastMoveToViewers(Location3fc newLoc, boolean teleporting) {
        var movementPk = createMovePacket(newLoc, teleporting);
        var motionPk = createMotionPacket();
        sendPacketToViewers(movementPk);
        sendPacketToViewers(motionPk);
    }

    protected BedrockPacket createMovePacket(Location3fc newLoc, boolean teleporting) {
        return Server.SETTINGS.entitySettings().physicsEngineSettings().useDeltaMovePacket() ?
                createDeltaMovePacket(newLoc, teleporting) :
                createAbsoluteMovePacket(newLoc, teleporting);
    }

    protected BedrockPacket createMotionPacket() {
        var pk = new SetEntityMotionPacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setMotion(MathUtils.JOMLVecToCBVec(motion));
        return pk;
    }

    protected BedrockPacket createAbsoluteMovePacket(Location3fc newLoc, boolean teleporting) {
        var pk = new MoveEntityAbsolutePacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setPosition(org.cloudburstmc.math.vector.Vector3f.from(newLoc.x(), newLoc.y() + getBaseOffset(), newLoc.z()));
        pk.setRotation(org.cloudburstmc.math.vector.Vector3f.from(newLoc.pitch(), newLoc.yaw(), newLoc.headYaw()));
        pk.setOnGround(onGround);
        pk.setTeleported(teleporting);
        return pk;
    }

    protected BedrockPacket createDeltaMovePacket(Location3fc newLoc, boolean teleporting) {
        var pk = new MoveEntityDeltaPacket();
        pk.setRuntimeEntityId(getRuntimeId());
        var moveFlags = computeMoveFlags(newLoc);
        pk.getFlags().addAll(moveFlags);
        if (moveFlags.contains(HAS_X)) {
            pk.setX(newLoc.x());
            locLastSent.x = newLoc.x();
        }
        if (moveFlags.contains(HAS_Y)) {
            pk.setY(newLoc.y());
            locLastSent.y = newLoc.y() + getBaseOffset();
        }
        if (moveFlags.contains(HAS_Z)) {
            pk.setZ(newLoc.z());
            locLastSent.z = newLoc.z();
        }
        if (moveFlags.contains(HAS_PITCH)) {
            pk.setPitch((float) newLoc.pitch());
            locLastSent.pitch = newLoc.pitch();
        }
        if (moveFlags.contains(HAS_YAW)) {
            pk.setYaw((float) newLoc.yaw());
            locLastSent.yaw = newLoc.yaw();
        }
        if (moveFlags.contains(HAS_HEAD_YAW)) {
            pk.setHeadYaw((float) newLoc.headYaw());
            locLastSent.headYaw = newLoc.headYaw();
        }
        if (onGround) pk.getFlags().add(ON_GROUND);
        if (teleporting) pk.getFlags().add(TELEPORTING);
        return pk;
    }

    protected Set<MoveEntityDeltaPacket.Flag> computeMoveFlags(Location3fc newLoc) {
        var flags = EnumSet.noneOf(MoveEntityDeltaPacket.Flag.class);
        var settings = Server.SETTINGS.entitySettings().physicsEngineSettings();
        var diffPositionThreshold = settings.diffPositionThreshold();
        var diffRotationThreshold = settings.diffRotationThreshold();
        if (abs(locLastSent.x() - newLoc.x()) > diffPositionThreshold) flags.add(HAS_X);
        if (abs(locLastSent.y() - newLoc.y()) > diffPositionThreshold) flags.add(HAS_Y);
        if (abs(locLastSent.z() - newLoc.z()) > diffPositionThreshold) flags.add(HAS_Z);
        if (abs(locLastSent.yaw() - newLoc.yaw()) > diffRotationThreshold) flags.add(HAS_YAW);
        if (abs(locLastSent.pitch() - newLoc.pitch()) > diffRotationThreshold) flags.add(HAS_PITCH);
        if (enableHeadYaw() && abs(locLastSent.headYaw() - newLoc.headYaw()) > diffRotationThreshold)
            flags.add(HAS_HEAD_YAW);
        return flags;
    }

    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder();
        builder.putString("identifier", entityType.getIdentifier().toString())
                .putCompound("Pos",
                        NbtMap.builder()
                                .putFloat("x", location.x())
                                .putFloat("y", location.y())
                                .putFloat("z", location.z())
                                .build())
                .putCompound("Rotation",
                        NbtMap.builder()
                                .putFloat("yaw", (float) location.yaw())
                                .putFloat("pitch", (float) location.pitch())
                                .build())
                .putCompound("Motion",
                        NbtMap.builder()
                                .putFloat("dx", motion.x())
                                .putFloat("dy", motion.y())
                                .putFloat("dz", motion.z())
                                .build())
                .putBoolean("OnGround", onGround);
        if (!tags.isEmpty()) {
            builder.putList("Tags", NbtType.STRING, new ArrayList<>(tags));
        }
        saveUniqueId(builder);
        var event = new EntitySaveNBTEvent(builder);
        manager.callEvent(event);
        return builder.build();
    }

    protected void saveUniqueId(NbtMapBuilder builder) {
        builder.putLong("UniqueID", uniqueId);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        if (nbt.containsKey("Pos")) {
            var pos = readVector3f(nbt, "Pos", "x", "y", "z");
            location.set(pos.x, pos.y, pos.z);
        }

        if (nbt.containsKey("Rotation")) {
            var rot = readVector2f(nbt, "Rotation", "yaw", "pitch");
            location.setYaw(rot.x);
            location.setPitch(rot.y);
        }

        if (nbt.containsKey("Motion")) {
            var motion = readVector3f(nbt, "Motion", "dx", "dy", "dz");
            this.motion.set(motion);
        }

        nbt.listenForBoolean("OnGround", onGround -> this.onGround = onGround);
        nbt.listenForList("Tags", NbtType.STRING, tags -> this.tags.addAll(tags));

        loadUniqueId(nbt);
        var event = new EntityLoadNBTEvent(nbt);
        manager.callEvent(event);
    }

    protected void loadUniqueId(NbtMap nbt) {
        if (nbt.containsKey("UniqueID")) {
            this.uniqueId = nbt.getLong("UniqueID");
            return;
        }

        this.uniqueId = UUID.randomUUID().getMostSignificantBits();
    }

    @Override
    public void onFall() {
        var event = new org.allaymc.api.eventbus.event.world.entity.EntityFallEvent(thisEntity, fallDistance);
        getWorld().getEventBus().callEvent(event);
        if (event.isCancelled()) {
            this.fallDistance = 0;
            return;
        }
        this.manager.callEvent(new EntityFallEvent(this.fallDistance));
        this.fallDistance = 0;
    }

    @Override
    public boolean hasEffect(EffectType effectType) {
        return effects.containsKey(effectType);
    }

    @Override
    public int getEffectLevel(EffectType effectType) {
        var effect = effects.get(effectType);
        return effect == null ? 0 : effect.getAmplifier() + 1;
    }

    @Override
    public void addEffect(EffectInstance effectInstance) {
        var old = effects.put(effectInstance.getType(), effectInstance);
        effectInstance.getType().onAdd(thisEntity, effectInstance);
        if (old == null) calculateEffectColor();
        var mobEffectPk = new MobEffectPacket();
        mobEffectPk.setRuntimeEntityId(runtimeId);
        mobEffectPk.setEffectId(effectInstance.getType().getId());
        mobEffectPk.setAmplifier(effectInstance.getAmplifier());
        mobEffectPk.setParticles(effectInstance.isVisible());
        mobEffectPk.setDuration(effectInstance.getDuration());
        mobEffectPk.setEvent(old == null ? MobEffectPacket.Event.ADD : MobEffectPacket.Event.MODIFY);
        sendPacketToViewers(mobEffectPk);
    }

    @Override
    public void removeEffect(EffectType effectType) {
        var removed = effects.remove(effectType);
        if (removed != null) {
            effectType.onRemove(thisEntity, removed);
            calculateEffectColor();
            var mobEffectPk = new MobEffectPacket();
            mobEffectPk.setRuntimeEntityId(runtimeId);
            mobEffectPk.setEffectId(effectType.getId());
            mobEffectPk.setEvent(MobEffectPacket.Event.REMOVE);
            sendPacketToViewers(mobEffectPk);
        }
    }

    @Override
    public void removeAllEffects() {
        this.effects.keySet().forEach(this::removeEffect);
    }

    protected void calculateEffectColor() {
        int[] color = new int[3];
        int count = 0;
        for (var effectInstance : this.effects.values()) {
            if (effectInstance.isVisible()) {
                var c = effectInstance.getType().getColor();
                color[0] += c.getRed() * (effectInstance.getAmplifier() + 1);
                color[1] += c.getGreen() * (effectInstance.getAmplifier() + 1);
                color[2] += c.getBlue() * (effectInstance.getAmplifier() + 1);
                count += effectInstance.getAmplifier() + 1;
            }
        }

        if (count > 0) {
            int r = (color[0] / count) & 0xff;
            int g = (color[1] / count) & 0xff;
            int b = (color[2] / count) & 0xff;

            this.metadata.set(EntityDataTypes.EFFECT_COLOR, (r << 16) + (g << 8) + b);
            this.metadata.set(EntityDataTypes.EFFECT_AMBIENCE, (byte) 0);
        } else {
            this.metadata.set(EntityDataTypes.EFFECT_COLOR, 0);
            this.metadata.set(EntityDataTypes.EFFECT_AMBIENCE, (byte) 0);
        }

        sendEntityData(EntityDataTypes.EFFECT_COLOR, EntityDataTypes.EFFECT_AMBIENCE);
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
    public Location3fc getCmdExecuteLocation() {
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
    public void sendTr(String key, boolean forceTranslatedByClient, String... args) {
        // Do nothing
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs) {
        // Do nothing
    }

    @Override
    public PermTree getPermTree() {
        return DefaultPermissions.OPERATOR;
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
}
