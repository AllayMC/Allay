package org.allaymc.server.entity.component;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
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
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.permission.DefaultPermissions;
import org.allaymc.api.permission.tree.PermissionTree;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayNbtUtils;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.entity.component.event.*;
import org.allaymc.server.world.chunk.AllayChunk;
import org.cloudburstmc.math.vector.Vector2f;
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
 * @author daoge_cmd
 */
@Slf4j
public class EntityBaseComponentImpl implements EntityBaseComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_base_component");

    protected static final int DEFAULT_DEAD_TIMER = 20;
    protected static final AtomicLong RUNTIME_ID_COUNTER = new AtomicLong(0);

    protected static final String TAG_IDENTIFIER = "identifier";
    protected static final String TAG_ON_GROUND = "OnGround";
    // This tag is also used in EntityPlayerNetworkComponentImpl, so make it public for reuse
    public static final String TAG_POS = "Pos";
    protected static final String TAG_MOTION = "Motion";
    protected static final String TAG_ROTATION = "Rotation";
    protected static final String TAG_TAGS = "Tags";
    protected static final String TAG_ACTIVE_EFFECTS = "ActiveEffects";
    protected static final String TAG_UNIQUE_ID = "UniqueID";

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
    protected ComponentManager manager;
    @ComponentObject
    protected Entity thisEntity;
    @Dependency(soft = true)
    protected EntityAttributeComponent attributeComponent;
    @Getter
    protected EntityType<? extends Entity> entityType;
    protected Map<Long, EntityPlayer> viewers = new Long2ObjectOpenHashMap<>();
    protected Map<EffectType, EffectInstance> effects = new HashMap<>();
    @Getter
    protected Vector3f motion = new Vector3f();
    @Getter
    protected Vector3f lastMotion = new Vector3f();
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

    public EntityBaseComponentImpl(EntityInitInfo info) {
        this.location = new Location3f(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, info.dimension());
        this.entityType = info.getEntityType();
        this.metadata = new Metadata();
        setDisplayName(entityType.getIdentifier().toString());
    }

    @OnInitFinish
    public void onInitFinish(EntityInitInfo initInfo) {
        loadNBT(initInfo.nbt());
        initMetadata();
    }

    protected void initMetadata() {
        metadata.set(EntityDataTypes.PLAYER_INDEX, 0);
        metadata.set(EntityDataTypes.AIR_SUPPLY, (short) 300);
        metadata.set(EntityDataTypes.AIR_SUPPLY_MAX, (short) 300);
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

    public void tick(long currentTick) {
        checkDead();
        tickEffects();
        tickBreathe();
        computeAndNotifyCollidedBlocks();

        manager.callEvent(new CEntityTickEvent(currentTick));
    }

    protected void computeAndNotifyCollidedBlocks() {
        var aabb = getOffsetAABB();
        var dimension = getDimension();
        dimension.forEachBlockStates(aabb, 0, (x, y, z, blockState) -> {
            var blockStateData = blockState.getBlockStateData();
            // NOTICE: use shape here instead of collision shape!
            // That's because entity colliding with block means that
            // the entity get into the shape of the block. For example
            // an entity can pass through a button block, which does not
            // have collision shape but has shape.
            if (blockState.getBehavior().canCollideWithEntity() && blockStateData.shape().translate(x, y, z).intersectsAABB(aabb)) {
                blockState.getBehavior().onCollideWithEntity(
                        new BlockStateWithPos(blockState, new Position3i(x, y, z, dimension), 0),
                        thisEntity
                );
            }
        });
    }

    protected void tickBreathe() {
        short airSupply = getMetadata().get(EntityDataTypes.AIR_SUPPLY);
        short airSupplyMax = getMetadata().get(EntityDataTypes.AIR_SUPPLY_MAX);
        short newAirSupply = airSupply;
        if (!thisEntity.canBreathe()) {
            newAirSupply = (short) (airSupply - 1);
            if (newAirSupply <= -20) {
                manager.callEvent(CEntityDrownEvent.INSTANCE);
                newAirSupply = 0;
            }
        } else if (airSupply < airSupplyMax) {
            newAirSupply = (short) (airSupply + 4);
        }
        if (airSupply != newAirSupply) {
            setAndSendEntityData(EntityDataTypes.AIR_SUPPLY, newAirSupply);
        }
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
        if (attributeComponent == null || !attributeComponent.supportHealth()) return;
        if (attributeComponent.getHealth() == 0 && !dead) {
            onDie();
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

    protected void onDie() {
        new EntityDieEvent(thisEntity).call();

        manager.callEvent(CEntityDieEvent.INSTANCE);
        dead = true;
        deadTimer = DEFAULT_DEAD_TIMER;
        applyEntityEvent(EntityEventType.DEATH, 0);
        effects.values().forEach(effect -> effect.getType().onEntityDies(thisEntity, effect));
        removeAllEffects();
    }

    protected void spawnDeadParticle() {
        var offsetAABB = getOffsetAABB();
        for (float x = offsetAABB.minX(); x <= offsetAABB.maxX(); x += 0.5f) {
            for (float z = offsetAABB.minZ(); z <= offsetAABB.maxZ(); z += 0.5f) {
                for (float y = offsetAABB.minY(); y <= offsetAABB.maxY(); y += 0.5f) {
                    this.getDimension().addParticle(x, y, z, ParticleType.EXPLODE);
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
        if (MathUtils.hasNaN(location)) {
            throw new IllegalArgumentException("Trying to set the location of entity " + runtimeId + " to a new location which contains NaN: " + location);
        }
        if (calculateFallDistance && !this.onGround) {
            if (this.fallDistance < 0) {
                // Entity start falling
                this.fallDistance = 0;
            }
            // fall distance < 0 -> move up
            // fall distance > 0 -> move down
            this.fallDistance -= location.y() - this.location.y();
            tryResetFallDistance(location);
        }

        this.location.set(location);
        this.location.setYaw(location.yaw());
        this.location.setHeadYaw(location.headYaw());
        this.location.setPitch(location.pitch());
        this.location.setDimension(location.dimension());
    }

    private void tryResetFallDistance(Location3fc location) {
        var blockState0 = location.dimension().getBlockState(location);
        var blockState1 = location.dimension().getBlockState(location, 1);
        var newEntityAABB = getAABB().translate(location, new AABBf());

        if (!blockState0.getBlockStateData().hasCollision() &&
            blockState1.getBehavior().canResetFallDamage() &&
            blockState1.getBlockStateData().computeOffsetShape(MathUtils.floor(location)).intersectsAABB(newEntityAABB)) {
            this.fallDistance = 0;
            return;
        }

        if (blockState0.getBehavior().canResetFallDamage() &&
            blockState0.getBlockStateData().computeOffsetShape(MathUtils.floor(location)).intersectsAABB(newEntityAABB)) {
            this.fallDistance = 0;
        }
    }

    @Override
    public Dimension getDimension() {
        return location.dimension();
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
               location.dimension() != null &&
               canBeSpawnedIgnoreLocation();
    }

    protected boolean canBeSpawnedIgnoreLocation() {
        return !spawned && !willBeSpawnedNextTick;
    }

    @Override
    public boolean willBeSpawnedNextTick() {
        return willBeSpawnedNextTick;
    }

    public boolean setLocationAndCheckChunk(Location3fc newLoc) {
        return setLocationAndCheckChunk(newLoc, true);
    }

    public boolean setLocationAndCheckChunk(Location3fc newLoc, boolean calculateFallDistance) {
        if (checkChunk(this.location, newLoc)) {
            setLocation(newLoc, calculateFallDistance);
            return true;
        }
        return false;
    }

    protected boolean checkChunk(Location3fc oldLoc, Location3fc newLoc) {
        var oldChunkX = (int) oldLoc.x() >> 4;
        var oldChunkZ = (int) oldLoc.z() >> 4;
        var newChunkX = (int) newLoc.x() >> 4;
        var newChunkZ = (int) newLoc.z() >> 4;
        if (oldChunkX == newChunkX && oldChunkZ == newChunkZ) {
            return true;
        }

        var newChunk = newLoc.dimension().getChunkService().getChunk(newChunkX, newChunkZ);
        if (newChunk == null) {
            // Moving into an unloaded chunk is not allowed. Because the chunk holds the entity,
            // moving to an unloaded chunk will result in the loss of the entity
            log.debug("Entity {} is trying to move into unloaded chunk {} {}", runtimeId, newChunkX, newChunkZ);
            return false;
        }

        Chunk oldChunk = null;
        if (this.location.dimension() != null) {
            oldChunk = this.location.dimension().getChunkService().getChunk(oldChunkX, oldChunkZ);
            // It is possible that the oldChunk is null
            // For example, when spawning an entity, the entity's old location is meaningless
            if (oldChunk != null) {
                ((AllayChunk) oldChunk).removeEntity(runtimeId);
            }
        }

        ((AllayChunk) newChunk).addEntity(thisEntity);
        Set<EntityPlayer> oldChunkPlayers = oldChunk != null ? oldChunk.getPlayerChunkLoaders() : Collections.emptySet();
        Set<EntityPlayer> samePlayers = new HashSet<>(newChunk.getPlayerChunkLoaders());
        samePlayers.retainAll(oldChunkPlayers);
        oldChunkPlayers.stream()
                .filter(player -> !samePlayers.contains(player) && player != thisEntity)
                .forEach(this::despawnFrom);
        newChunk.getPlayerChunkLoaders().stream()
                .filter(player -> !samePlayers.contains(player) && player != thisEntity)
                .forEach(this::spawnTo);
        return true;
    }

    @Override
    public void teleport(Location3fc target, EntityTeleportEvent.Reason reason) {
        Objects.requireNonNull(target.dimension());
        if (this.location.dimension() == null) {
            log.warn("Trying to teleport an entity whose dimension is null! Entity: {}", thisEntity);
            return;
        }

        if (!this.spawned) {
            log.warn("Trying to teleport an entity which is not spawned! Entity: {}", thisEntity);
            return;
        }

        var event = new EntityTeleportEvent(thisEntity, this.location, new Location3f(target), reason);
        if (!event.call()) {
            return;
        }

        target = event.getTo();
        beforeTeleport();
        this.fallDistance = 0;
        if (this.location.dimension() == target.dimension()) {
            // Teleporting in the current same dimension,
            // and we just need to move the entity to the new coordination
            teleportInDimension(target, reason);
        } else {
            teleportOverDimension(target, reason);
        }
    }

    protected void beforeTeleport() {
        // This method is used by EntityPlayer
    }

    protected void teleportInDimension(Location3fc target, EntityTeleportEvent.Reason reason) {
        // Ensure that the new chunk is loaded
        target.dimension().getChunkService().getOrLoadChunkSync((int) target.x() >> 4, (int) target.z() >> 4);
        // This method should always return true because we have loaded the chunk
        setLocationAndCheckChunk(target, false);
        broadcastMoveToViewers(target, true);
    }

    protected void teleportOverDimension(Location3fc target, EntityTeleportEvent.Reason reason) {
        // Teleporting to another dimension, there will be more works to be done
        this.location.dimension().getEntityService().removeEntity(thisEntity, () -> {
            target.dimension().getChunkService().getOrLoadChunkSync((int) target.x() >> 4, (int) target.z() >> 4);
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
    public AABBfc getAABB() {
        // Default aabb is player's
        return new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f);
    }

    @Override
    @UnmodifiableView
    public Map<Long, EntityPlayer> getViewers() {
        return Collections.unmodifiableMap(viewers);
    }

    @Override
    public void setMotion(Vector3fc motion) {
        if (MathUtils.hasNaN(motion)) {
            throw new IllegalArgumentException("Trying to set the motion of entity " + runtimeId + " to a new motion which contains NaN: " + motion);
        }
        this.lastMotion = this.motion;
        this.motion = new Vector3f(motion);
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        if (onGround && this.fallDistance > 0) {
            this.onFall();
        }
    }

    @Override
    public void knockback(Vector3fc source, float kb, boolean ignoreKnockbackResistance) {
        motion = calculateKnockbackMotion(source, kb, ignoreKnockbackResistance);
    }

    protected Vector3f calculateKnockbackMotion(Vector3fc source, float kb, boolean ignoreKnockbackResistance) {
        if (!ignoreKnockbackResistance) {
            var resistance = attributeComponent.getAttributeValue(AttributeType.KNOCKBACK_RESISTANCE);
            if (resistance > 0) {
                kb *= 1 - resistance;
            }
        }
        Vector3f vec;
        if (getLocation().distanceSquared(source) <= 0.0001 /* 0.01 * 0.01 */) {
            // Generate a random kb direction if distance <= 0.01m
            var rand = ThreadLocalRandom.current();
            var rx = rand.nextFloat(1) - 0.5f;
            var rz = rand.nextFloat(1) - 0.5f;
            vec = MathUtils.normalizeIfNotZero(new Vector3f(rx, 0, rz)).mul(kb);
        } else {
            vec = getLocation().sub(source, new Vector3f()).normalize().mul(kb);
            vec.y = 0;
        }
        return new Vector3f(
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
        viewers.values().forEach(client -> client.sendPacketImmediately(packet));
    }

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
        if (onGround) {
            pk.getFlags().add(ON_GROUND);
        }
        if (teleporting) {
            pk.getFlags().add(TELEPORTING);
        }
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
        builder.putString(TAG_IDENTIFIER, entityType.getIdentifier().toString());
        builder.putBoolean(TAG_ON_GROUND, onGround);
        AllayNbtUtils.writeVector3f(builder, TAG_POS, location);
        AllayNbtUtils.writeVector3f(builder, TAG_MOTION, motion);
        AllayNbtUtils.writeVector2f(builder, TAG_ROTATION, (float) location.yaw(), (float) location.pitch());
        if (!tags.isEmpty()) {
            builder.putList(TAG_TAGS, NbtType.STRING, new ArrayList<>(tags));
        }
        if (!effects.isEmpty()) {
            builder.putList(TAG_ACTIVE_EFFECTS, NbtType.COMPOUND, effects.values().stream().map(EffectInstance::saveNBT).toList());
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
        }

        if (nbt.containsKey(TAG_MOTION)) {
            var motion = readVector3f(nbt, TAG_MOTION);
            this.motion.set(motion);
        }

        if (nbt.containsKey(TAG_ROTATION)) {
            var rot = readVector2f(nbt, TAG_ROTATION);
            location.setYaw(rot.x);
            location.setPitch(rot.y);
        }

        nbt.listenForBoolean(TAG_ON_GROUND, onGround -> this.onGround = onGround);
        nbt.listenForList(TAG_TAGS, NbtType.STRING, tags -> this.tags.addAll(tags));
        nbt.listenForList(TAG_ACTIVE_EFFECTS, NbtType.COMPOUND, activeEffects -> {
            for (NbtMap activeEffect : activeEffects) {
                var effectInstance = EffectInstance.fromNBT(activeEffect);
                addEffect(effectInstance);
            }
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
    public void onFall() {
        var event = new EntityFallEvent(thisEntity, fallDistance);
        if (!event.call()) {
            this.fallDistance = 0;
            return;
        }

        var blockUnder = getBlockStateStandingOn();
        blockUnder.blockState().getBehavior().onEntityFallOn(thisEntity, blockUnder);

        this.manager.callEvent(new CEntityFallEvent(event.getFallDistance()));
        this.fallDistance = 0;
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
    public void sendTr(String key, boolean forceTranslatedByClient, Object... args) {
        // Do nothing
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs) {
        // Do nothing
    }

    @Override
    public PermissionTree getPermissionTree() {
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
