package org.allaymc.server.entity.component.player;

import com.google.common.base.Preconditions;
import com.google.common.base.Suppliers;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.action.BlockAction;
import org.allaymc.api.block.action.ContinueBreakAction;
import org.allaymc.api.block.action.SimpleBlockAction;
import org.allaymc.api.block.action.StartBreakAction;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.debugshape.*;
import org.allaymc.api.debugshape.DebugShape;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.ArrowShakeAction;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.action.PickedUpAction;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.*;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.FireworkExplosion;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.particle.*;
import org.allaymc.api.world.sound.*;
import org.allaymc.server.AllayServer;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.container.impl.UnopenedContainerId;
import org.allaymc.server.entity.component.event.CPlayerChunkInRangeSendEvent;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.player.SkinConvertor;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.chunk.ChunkEncoder;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.util.*;
import java.util.function.Supplier;

import static org.allaymc.server.network.NetworkHelper.toNetwork;
import static org.cloudburstmc.protocol.bedrock.data.DebugShape.Type.*;
import static org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket.Flag.*;

/**
 * @author daoge_cmd
 */
public class EntityPlayerChunkLoaderComponentImpl implements EntityPlayerChunkLoaderComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_player_chunk_loader_component");

    // Constants used in UpdateSubChunkBlocksPacket
    protected static final int BLOCK_UPDATE_NEIGHBORS = 0b0001;
    protected static final int BLOCK_UPDATE_NETWORK = 0b0010;
    protected static final int BLOCK_UPDATE_NO_GRAPHICS = 0b0100;
    protected static final int BLOCK_UPDATE_PRIORITY = 0b1000;

    // Constants used in BlockEventPacket
    protected static final int BLOCK_EVENT_TYPE_CHANGE_CHEST_STATE = 1;
    protected static final int BLOCK_EVENT_DATA_OPEN_CHEST = 1;
    protected static final int BLOCK_EVENT_DATA_CLOSE_CHEST = 0;

    /**
     * A map which contains the network offset of some entities. The network offset is the additional offset in
     * y coordinate when sent over network. This is mostly the case for older entities such as player and TNT.
     */
    protected static final Supplier<Map<EntityType<?>, Float>> NETWORK_OFFSETS = Suppliers.memoize(() -> {
        var map = new HashMap<EntityType<?>, Float>();
        map.put(EntityTypes.PLAYER, 1.62f);
        map.put(EntityTypes.FALLING_BLOCK, 0.49f);
        map.put(EntityTypes.ITEM, 0.125f);
        map.put(EntityTypes.TNT, 0.49f);
        map.put(EntityTypes.FIREWORKS_ROCKET, 0.49f);
        return map;
    });

    @ComponentObject
    protected EntityPlayer thisPlayer;
    @Manager
    protected ComponentManager manager;
    @Dependency
    protected EntityBaseComponent baseComponent;
    @Dependency
    protected EntityPlayerClientComponent clientComponent;

    @Getter
    protected int chunkLoadingRadius;
    @Getter
    @Setter
    protected int chunkMaxSendCountPerTick;

    public EntityPlayerChunkLoaderComponentImpl() {
        this.chunkLoadingRadius = AllayServer.getSettings().worldSettings().viewDistance();
        this.chunkMaxSendCountPerTick = AllayServer.getSettings().worldSettings().chunkMaxSendCountPerTick();
    }

    @Override
    public void viewEntity(Entity entity) {
        var l = entity.getLocation();
        var position = Vector3f.from(l.x(), l.y() + NETWORK_OFFSETS.get().getOrDefault(entity.getEntityType(), 0.0f), l.z());
        var motion = switch (entity) {
            case EntityPhysicsComponent physicsComponent -> {
                var m = physicsComponent.getMotion();
                yield Vector3f.from(m.x(), m.y(), m.z());
            }
            default -> Vector3f.ZERO;
        };
        BedrockPacket packet = switch (entity) {
            case EntityPlayer player -> {
                var loginData = player.getLoginData();
                var p = new AddPlayerPacket();
                p.setRuntimeEntityId(player.getRuntimeId());
                p.setUniqueEntityId(player.getUniqueId());
                p.setUuid(loginData.getUuid());
                p.setUsername(player.getOriginName());
                p.setPlatformChatId(loginData.getDeviceInfo().deviceId());
                // NOTICE: Player network offset is not used in AddPlayerPacket
                p.setPosition(Vector3f.from(l.x(), l.y(), l.z()));
                p.setMotion(motion);
                p.setRotation(Vector3f.from(l.pitch(), l.yaw(), l.headYaw()));
                p.setGameType(toNetwork(player.getGameMode()));
                p.getMetadata().putAll(parseMetadata(entity));
                p.setDeviceId(loginData.getDeviceInfo().deviceId());
                p.setHand(toNetwork(player.getContainer(ContainerTypes.INVENTORY).getItemInHand()));
                yield p;
            }
            case EntityItem item -> {
                var p = new AddItemEntityPacket();
                p.setRuntimeEntityId(item.getRuntimeId());
                p.setUniqueEntityId(item.getUniqueId());
                p.setItemInHand(toNetwork(item.getItemStack()));
                p.setPosition(position);
                p.setMotion(motion);
                p.getMetadata().putAll(parseMetadata(entity));
                yield p;
            }
            case EntityPainting painting -> {
                var p = new AddPaintingPacket();
                p.setRuntimeEntityId(painting.getRuntimeId());
                p.setUniqueEntityId(painting.getUniqueId());
                p.setMotive(painting.getPaintingType().getTitle());
                // NOTICE: The position being sent in AddPaintingPacket is the center of the painting's
                // aabb. It is not the position of the painting.
                var aabb = painting.getOffsetAABB();
                p.setPosition(Vector3f.from(
                        (aabb.minX() + aabb.maxX()) / 2,
                        (aabb.minY() + aabb.maxY()) / 2,
                        (aabb.minZ() + aabb.maxZ()) / 2
                ));
                p.setDirection(painting.getHorizontalFace().getHorizontalIndex());
                yield p;
            }
            default -> {
                var p = new AddEntityPacket();
                p.setRuntimeEntityId(entity.getRuntimeId());
                p.setUniqueEntityId(entity.getUniqueId());
                p.setIdentifier(entity.getEntityType().getIdentifier().toString());
                p.setPosition(position);
                p.setMotion(motion);
                p.setRotation(Vector2f.from(l.pitch(), l.yaw()));
                p.getMetadata().putAll(parseMetadata(entity));
                yield p;
            }
        };
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void removeEntity(Entity entity) {
        var packet = new RemoveEntityPacket();
        packet.setUniqueEntityId(entity.getUniqueId());
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewPlayerGameMode(EntityPlayer player) {
        var gameMode = player.getGameMode();
        if (thisPlayer == player) {
            var packet1 = new SetPlayerGameTypePacket();
            packet1.setGamemode(toNetwork(player.getGameMode()).ordinal());
            this.clientComponent.sendPacket(packet1);

            var packet2 = new UpdateAdventureSettingsPacket();
            packet2.setNoPvM(gameMode == GameMode.SPECTATOR);
            packet2.setNoMvP(gameMode == GameMode.SPECTATOR);
            packet2.setShowNameTags(gameMode != GameMode.SPECTATOR);
            packet2.setImmutableWorld(gameMode == GameMode.SPECTATOR);
            packet2.setAutoJump(true);
            this.clientComponent.sendPacket(packet2);
        } else {
            var packet = new UpdatePlayerGameTypePacket();
            packet.setGameType(toNetwork(player.getGameMode()));
            packet.setEntityId(player.getRuntimeId());
            this.clientComponent.sendPacket(packet);
        }
    }

    @Override
    public void viewEntityLocation(Entity entity, Location3d locationLastSent, Location3dc newLocation, boolean teleporting) {
        BedrockPacket packet;
        if (AllayServer.getSettings().entitySettings().physicsEngineSettings().useDeltaMovePacket()) {
            packet = createDeltaMovePacket(entity, locationLastSent, newLocation, teleporting);
        } else {
            packet = createAbsoluteMovePacket(entity, locationLastSent, newLocation, teleporting);
        }
        this.clientComponent.sendPacket(packet);
    }

    protected BedrockPacket createDeltaMovePacket(Entity entity, Location3d locationLastSent, Location3dc newLocation, boolean teleporting) {
        var packet = new MoveEntityDeltaPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        var moveFlags = computeMoveFlags(entity, locationLastSent, newLocation);
        packet.getFlags().addAll(moveFlags);
        if (moveFlags.contains(HAS_X)) {
            packet.setX((float) newLocation.x());
            locationLastSent.x = newLocation.x();
        }
        if (moveFlags.contains(HAS_Y)) {
            packet.setY((float) newLocation.y() + NETWORK_OFFSETS.get().getOrDefault(entity.getEntityType(), 0.0f));
            locationLastSent.y = newLocation.y();
        }
        if (moveFlags.contains(HAS_Z)) {
            packet.setZ((float) newLocation.z());
            locationLastSent.z = newLocation.z();
        }
        if (moveFlags.contains(HAS_PITCH)) {
            packet.setPitch((float) newLocation.pitch());
            locationLastSent.pitch = newLocation.pitch();
        }
        if (moveFlags.contains(HAS_YAW)) {
            packet.setYaw((float) newLocation.yaw());
            locationLastSent.yaw = newLocation.yaw();
        }
        if (moveFlags.contains(HAS_HEAD_YAW)) {
            packet.setHeadYaw((float) newLocation.headYaw());
            locationLastSent.headYaw = newLocation.headYaw();
        }
        if (teleporting) {
            packet.getFlags().add(TELEPORTING);
        }
        if (entity instanceof EntityPhysicsComponent physicsComponent && physicsComponent.isOnGround()) {
            packet.getFlags().add(ON_GROUND);
        }
        return packet;
    }

    protected Set<MoveEntityDeltaPacket.Flag> computeMoveFlags(Entity entity, Location3d locationLastSent, Location3dc newLocation) {
        var flags = EnumSet.noneOf(MoveEntityDeltaPacket.Flag.class);
        var settings = AllayServer.getSettings().entitySettings().physicsEngineSettings();
        var diffPositionThreshold = settings.diffPositionThreshold();
        var diffRotationThreshold = settings.diffRotationThreshold();
        if (Math.abs(locationLastSent.x() - newLocation.x()) > diffPositionThreshold) flags.add(HAS_X);
        if (Math.abs(locationLastSent.y() - newLocation.y()) > diffPositionThreshold) flags.add(HAS_Y);
        if (Math.abs(locationLastSent.z() - newLocation.z()) > diffPositionThreshold) flags.add(HAS_Z);
        if (Math.abs(locationLastSent.yaw() - newLocation.yaw()) > diffRotationThreshold) flags.add(HAS_YAW);
        if (Math.abs(locationLastSent.pitch() - newLocation.pitch()) > diffRotationThreshold) flags.add(HAS_PITCH);
        if (entity.isHeadYawEnabled() && Math.abs(locationLastSent.headYaw() - newLocation.headYaw()) > diffRotationThreshold) {
            flags.add(HAS_HEAD_YAW);
        }
        return flags;
    }

    protected BedrockPacket createAbsoluteMovePacket(Entity entity, Location3d locationLastSent, Location3dc newLocation, boolean teleporting) {
        locationLastSent.set(newLocation);
        locationLastSent.setPitch(newLocation.pitch());
        locationLastSent.setYaw(newLocation.yaw());
        locationLastSent.setHeadYaw(newLocation.headYaw());

        var packet = new MoveEntityAbsolutePacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setPosition(Vector3f.from(newLocation.x(), newLocation.y() + NETWORK_OFFSETS.get().getOrDefault(entity.getEntityType(), 0.0f), newLocation.z()));
        packet.setRotation(Vector3f.from(newLocation.pitch(), newLocation.yaw(), newLocation.headYaw()));
        packet.setTeleported(teleporting);
        if (entity instanceof EntityPhysicsComponent physicsComponent) {
            packet.setOnGround(physicsComponent.isOnGround());
        }

        return packet;
    }

    @Override
    public <T extends Entity & EntityPhysicsComponent> void viewEntityMotion(T entity, Vector3dc motion) {
        var packet = new SetEntityMotionPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewEntityState(Entity entity) {
        var packet = new SetEntityDataPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMetadata(parseMetadata(entity));
        this.clientComponent.sendPacket(packet);
    }

    protected EntityDataMap parseMetadata(Entity entity) {
        var map = new EntityDataMap();
        addGenericMetadata(entity, map);
        addComponentSpecificMetadata(entity, map);
        addTypeSpecificMetadata(entity, map);
        return map;
    }

    protected void addGenericMetadata(Entity entity, EntityDataMap map) {
        map.setFlag(EntityFlag.HAS_COLLISION, entity.hasEntityCollision());
        map.setFlag(EntityFlag.CAN_CLIMB, true);
        map.setFlag(EntityFlag.INVISIBLE, entity.isInvisible());
        var aabb = entity.getAABB();
        var nbt = NbtMap.builder()
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
        map.put(EntityDataTypes.HITBOX, nbt);
        map.put(EntityDataTypes.COLLISION_BOX, Vector3f.from(
                (float) (aabb.maxX() - aabb.minX()),
                (float) (aabb.maxY() - aabb.minY()),
                (float) (aabb.maxZ() - aabb.minZ())
        ));
        if (entity.hasNameTag()) {
            map.setFlag(EntityFlag.CAN_SHOW_NAME, true);
            map.put(EntityDataTypes.NAME, entity.getNameTag());
            if (entity.isNameTagAlwaysShow()) {
                map.setFlag(EntityFlag.ALWAYS_SHOW_NAME, true);
                map.put(EntityDataTypes.NAMETAG_ALWAYS_SHOW, (byte) 1);
            }
        }
    }

    protected void addComponentSpecificMetadata(Entity entity, EntityDataMap map) {
        if (entity instanceof EntityPhysicsComponent physicsComponent) {
            map.setFlag(EntityFlag.HAS_GRAVITY, physicsComponent.hasGravity());
        }
        if (entity instanceof EntityLivingComponent livingComponent) {
            map.setFlag(EntityFlag.ON_FIRE, livingComponent.isOnFire());
            map.setFlag(EntityFlag.BREATHING, livingComponent.canBreathe());
            map.put(EntityDataTypes.AIR_SUPPLY, (short) livingComponent.getAirSupplyTicks());
            map.put(EntityDataTypes.AIR_SUPPLY_MAX, (short) livingComponent.getAirSupplyMaxTicks());
            map.put(EntityDataTypes.VISIBLE_MOB_EFFECTS, encodeVisibleEffects(livingComponent.getEffects().values()));
        }
    }

    protected void addTypeSpecificMetadata(Entity entity, EntityDataMap map) {
        switch (entity) {
            case EntityTnt tnt -> {
                map.setFlag(EntityFlag.IGNITED, true);
                map.put(EntityDataTypes.FUSE_TIME, tnt.getFuseTime());
            }
            case EntityPlayer player -> {
                map.setFlag(EntityFlag.SPRINTING, player.isSprinting());
                map.setFlag(EntityFlag.SNEAKING, player.isSneaking());
                map.setFlag(EntityFlag.SWIMMING, player.isSwimming());
                map.setFlag(EntityFlag.GLIDING, player.isGliding());
                map.setFlag(EntityFlag.CRAWLING, player.isCrawling());
                map.setFlag(EntityFlag.USING_ITEM, player.isUsingItemInAir());
                map.setFlag(EntityFlag.BREATHING, player.canBreathe());
                map.put(EntityDataTypes.AIR_SUPPLY, (short) player.getAirSupplyTicks());
                map.put(EntityDataTypes.AIR_SUPPLY_MAX, (short) player.getAirSupplyMaxTicks());
                if (player.hasScoreTag()) {
                    map.put(EntityDataTypes.SCORE, player.getScoreTag());
                }
            }
            case EntityFallingBlock fallingBlock -> {
                map.setFlag(EntityFlag.FIRE_IMMUNE, true);
                map.put(EntityDataTypes.VARIANT, fallingBlock.getBlockState().blockStateHash());
            }
            case EntityXpOrb xpOrb -> {
                map.put(EntityDataTypes.VALUE, xpOrb.getExperienceValue());
            }
            case EntityArrow arrow -> {
                map.setFlag(EntityFlag.CRITICAL, arrow.isCritical());
                var potionType = arrow.getPotionType();
                if (potionType != null) {
                    map.put(EntityDataTypes.CUSTOM_DISPLAY, (byte) (potionType.ordinal() + 1));
                }
            }
            case EntityFireworksRocket firework -> {
                var nbt = NbtMap.builder()
                        .putCompound("Fireworks", NbtMap.builder()
                                .putList("Explosions", NbtType.COMPOUND, firework.getExplosions().stream().map(FireworkExplosion::saveNBT).toList())
                                .putByte("Flight", (byte) (firework.getExistenceTicks() / 20))
                                .build()
                        )
                        .build();
                map.put(EntityDataTypes.DISPLAY_FIREWORK, nbt);

                var attachedPlayer = firework.getAttachedPlayer();
                if (attachedPlayer != null) {
                    map.put(EntityDataTypes.CUSTOM_DISPLAY, (byte) attachedPlayer.getRuntimeId());
                }
            }
            default -> {
            }
        }
    }

    protected long encodeVisibleEffects(Collection<EffectInstance> effects) {
        long visibleEffects = 0;
        for (var effect : effects) {
            if (!effect.isVisible()) {
                continue;
            }

            visibleEffects = (visibleEffects << 7) | ((long) effect.getType().getId() << 1) | (effect.isAmbient() ? 1 : 0);
        }
        return visibleEffects;
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityHand(T entity) {
        var container = entity.getContainer(ContainerTypes.INVENTORY);
        var handSlot = container.getHandSlot();
        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setContainerId(UnopenedContainerId.PLAYER_INVENTORY);
        packet.setItem(toNetwork(container.getItemInHand()));
        packet.setInventorySlot(handSlot);
        packet.setHotbarSlot(handSlot);
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityOffhand(T entity) {
        var container = entity.getContainer(ContainerTypes.OFFHAND);
        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setContainerId(UnopenedContainerId.OFFHAND);
        // Network slot index for offhand is 1, see FullContainerType.OFFHAND. Field `hotbarSlot` is unused
        packet.setInventorySlot(1);
        packet.setItem(toNetwork(container.getOffhand()));
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityArmors(T entity) {
        var container = entity.getContainer(ContainerTypes.ARMOR);
        var packet = new MobArmorEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setBody(toNetwork(ItemAirStack.AIR_STACK));
        packet.setHelmet(toNetwork(container.getHelmet()));
        packet.setChestplate(toNetwork(container.getChestplate()));
        packet.setLeggings(toNetwork(container.getLeggings()));
        packet.setBoots(toNetwork(container.getBoots()));
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewEntityAnimation(Entity entity, EntityAnimation animation) {
        var packet = new AnimateEntityPacket();
        packet.setAnimation(animation.name());
        packet.setNextState(animation.nextState());
        packet.setStopExpression(animation.stopCondition());
        packet.setController(animation.controller());
        packet.getRuntimeEntityIds().add(entity.getRuntimeId());
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewPlayerSkin(EntityPlayer player) {
        var skin = SkinConvertor.toSerializedSkin(player.getSkin());
        var packet = new PlayerSkinPacket();
        packet.setUuid(player.getLoginData().getUuid());
        packet.setSkin(skin);
        packet.setNewSkinName(skin.getSkinId());
        // It seems that old skin name is unused
        packet.setOldSkinName("");
        packet.setTrustedSkin(AllayServer.getSettings().resourcePackSettings().trustAllSkins());
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewEntityAction(Entity entity, EntityAction action) {
        switch (action) {
            case SimpleEntityAction.SWING_ARM -> {
                if (entity instanceof EntityPlayer player) {
                    if (thisPlayer == player) {
                        // Do not send to the player itself
                        return;
                    }

                    var packet = new AnimatePacket();
                    packet.setAction(AnimatePacket.Action.SWING_ARM);
                    packet.setRuntimeEntityId(entity.getRuntimeId());
                    this.clientComponent.sendPacket(packet);

                } else {
                    var packet = new EntityEventPacket();
                    packet.setType(EntityEventType.ATTACK_START);
                    packet.setRuntimeEntityId(entity.getRuntimeId());
                    this.clientComponent.sendPacket(packet);
                }
            }
            case SimpleEntityAction.HURT -> {
                var packet = new EntityEventPacket();
                packet.setType(EntityEventType.HURT);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                this.clientComponent.sendPacket(packet);
            }
            case SimpleEntityAction.CRITICAL_HIT -> {
                var packet = new AnimatePacket();
                packet.setAction(AnimatePacket.Action.CRITICAL_HIT);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                this.clientComponent.sendPacket(packet);
            }
            case SimpleEntityAction.ENCHANTED_HIT -> {
                var packet = new AnimatePacket();
                packet.setAction(AnimatePacket.Action.MAGIC_CRITICAL_HIT);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                this.clientComponent.sendPacket(packet);
            }
            case SimpleEntityAction.DEATH -> {
                var packet = new EntityEventPacket();
                packet.setType(EntityEventType.DEATH);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                this.clientComponent.sendPacket(packet);
            }
            case SimpleEntityAction.EAT -> {
                if (entity instanceof ContainerHolder holder && holder.hasContainer(ContainerTypes.INVENTORY)) {
                    var item = holder.getContainer(ContainerTypes.INVENTORY).getItemInHand();
                    var packet = new EntityEventPacket();
                    packet.setType(EntityEventType.EATING_ITEM);
                    packet.setRuntimeEntityId(entity.getRuntimeId());
                    packet.setData((item.getItemType().getRuntimeId() << 16) | item.getMeta());
                    this.clientComponent.sendPacket(packet);
                }
            }
            case SimpleEntityAction.FIREWORK_EXPLODE -> {
                var packet = new EntityEventPacket();
                packet.setType(EntityEventType.FIREWORK_EXPLODE);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                this.clientComponent.sendPacket(packet);
            }
            case SimpleEntityAction.TOTEM_USE -> {
                var packet = new EntityEventPacket();
                packet.setType(EntityEventType.CONSUME_TOTEM);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                this.clientComponent.sendPacket(packet);
            }
            case PickedUpAction(Entity picker) -> {
                var packet = new TakeItemEntityPacket();
                packet.setRuntimeEntityId(picker.getRuntimeId());
                packet.setItemRuntimeEntityId(entity.getRuntimeId());
                this.clientComponent.sendPacket(packet);
            }
            case ArrowShakeAction(int times) -> {
                var packet = new EntityEventPacket();
                packet.setType(EntityEventType.ARROW_SHAKE);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                packet.setData(times);
                this.clientComponent.sendPacket(packet);
            }
            default -> throw new IllegalStateException("Unhandled entity action type: " + action.getClass().getSimpleName());
        }
    }

    @Override
    public void viewPlayerEmote(EntityPlayer player, UUID emoteId) {
        var packet = new EmotePacket();
        packet.setRuntimeEntityId(player.getRuntimeId());
        packet.setEmoteId(emoteId.toString());
        packet.getFlags().add(EmoteFlag.SERVER_SIDE);
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewEntityEffectChange(Entity entity, EffectInstance newEffect, EffectInstance oldEffect) {
        var packet = new MobEffectPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        if (newEffect == null) {
            Preconditions.checkNotNull(oldEffect);
            // Effect is removed
            packet.setEffectId(oldEffect.getType().getId());
            packet.setEvent(MobEffectPacket.Event.REMOVE);
        } else {
            packet.setEffectId(newEffect.getType().getId());
            packet.setAmplifier(newEffect.getAmplifier());
            packet.setParticles(newEffect.isVisible());
            packet.setDuration(newEffect.getDuration());
            packet.setEvent(oldEffect == null ? MobEffectPacket.Event.ADD : MobEffectPacket.Event.MODIFY);
        }
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public Location3dc getLocation() {
        // NOTICE: Don't call thisPlayer.getLocation() here which will cause stack over flow error
        return this.baseComponent.getLocation();
    }

    @Override
    public void setChunkLoadingRadius(int radius) {
        this.chunkLoadingRadius = Math.min(radius, AllayServer.getSettings().worldSettings().viewDistance());
        var packet = new ChunkRadiusUpdatedPacket();
        packet.setRadius(chunkLoadingRadius);
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewChunk(Chunk chunk) {
        this.clientComponent.sendPacket(createNetworkChunkPublisherUpdatePacket());
        this.clientComponent.sendPacket(createLevelChunkPacket(chunk));
        // This method will be called in non-ticking thread if async chunk sending is enabled. Let's
        // send the entities in this chunk to the player next tick in the main thread: use forEachEntitiesInChunk()
        // instead of forEachEntitiesInChunkImmediately()
        thisPlayer.getDimension().getEntityManager().forEachEntitiesInChunk(chunk.getX(), chunk.getZ(), entity -> entity.spawnTo(thisPlayer));
        this.manager.callEvent(CPlayerChunkInRangeSendEvent.INSTANCE);
    }

    protected NetworkChunkPublisherUpdatePacket createNetworkChunkPublisherUpdatePacket() {
        var packet = new NetworkChunkPublisherUpdatePacket();
        var location = thisPlayer.getLocation();
        packet.setPosition(Vector3i.from(location.x(), location.y(), location.z()));
        packet.setRadius(getChunkLoadingRadius() << 4);
        return packet;
    }

    protected LevelChunkPacket createLevelChunkPacket(Chunk chunk) {
        var lcp = new LevelChunkPacket[1];
        chunk.applyOperation(unsafeChunk -> {
            if (AllayServer.getSettings().worldSettings().useSubChunkSendingSystem()) {
                lcp[0] = createSubChunkLevelChunkPacket((AllayUnsafeChunk) unsafeChunk);
            } else {
                lcp[0] = createFullLevelChunkPacketChunk((AllayUnsafeChunk) unsafeChunk);
            }
        }, OperationType.READ, OperationType.READ);
        return lcp[0];
    }

    public static LevelChunkPacket createSubChunkLevelChunkPacket(AllayUnsafeChunk chunk) {
        var dimensionInfo = chunk.getDimensionInfo();
        var packet = new LevelChunkPacket();
        packet.setDimension(dimensionInfo.dimensionId());
        packet.setChunkX(chunk.getX());
        packet.setChunkZ(chunk.getZ());
        packet.setCachingEnabled(false);
        packet.setRequestSubChunks(true);
        // NOTICE: Sub chunk limit is bigger than zero
        packet.setSubChunkLimit(findHighestNonAirSectionY(chunk) - dimensionInfo.minSectionY());
        packet.setData(ChunkEncoder.writeToNetworkBiomeOnly(chunk));
        return packet;
    }

    private static int findHighestNonAirSectionY(AllayUnsafeChunk chunk) {
        var dimensionInfo = chunk.getDimensionInfo();
        for (int highest = dimensionInfo.maxSectionY(); highest > dimensionInfo.minSectionY(); highest--) {
            if (!chunk.getSection(highest).isAirSection()) {
                return highest;
            }
        }

        return dimensionInfo.minSectionY();
    }

    public static LevelChunkPacket createFullLevelChunkPacketChunk(AllayUnsafeChunk chunk) {
        var dimensionInfo = chunk.getDimensionInfo();
        var packet = new LevelChunkPacket();
        packet.setDimension(dimensionInfo.dimensionId());
        packet.setChunkX(chunk.getX());
        packet.setChunkZ(chunk.getZ());
        packet.setCachingEnabled(false);
        packet.setRequestSubChunks(false);
        packet.setSubChunksLength(dimensionInfo.chunkSectionCount());
        packet.setData(ChunkEncoder.writeToNetwork(chunk));
        return packet;
    }

    @Override
    public void removeChunk(long chunkHash) {
        thisPlayer.getDimension().getEntityManager().forEachEntitiesInChunk(
                HashUtils.getXFromHashXZ(chunkHash),
                HashUtils.getZFromHashXZ(chunkHash),
                entity -> entity.despawnFrom(thisPlayer)
        );
    }

    @Override
    public void viewTime(int timeOfDay) {
        var packet = new SetTimePacket();
        packet.setTime(timeOfDay);
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewGameRules(GameRules gameRules) {
        var packet = new GameRulesChangedPacket();
        packet.getGameRules().addAll(NetworkHelper.toNetwork(gameRules.getGameRules()));
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewBlockUpdate(Vector3ic pos, int layer, BlockState blockState) {
        var packet = new UpdateBlockPacket();
        packet.setBlockPosition(toNetwork(pos));
        packet.setDataLayer(layer);
        packet.setDefinition(blockState::blockStateHash);
        packet.getFlags().add(UpdateBlockPacket.Flag.NETWORK);
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewBlockUpdates(Chunk chunk, Collection<BlockUpdate> blockUpdates, Collection<BlockUpdate> extraBlockUpdates) {
        var packets = new UpdateSubChunkBlocksPacket[chunk.getDimensionInfo().chunkSectionCount()];
        encodeBlockUpdates(packets, chunk, blockUpdates, false);
        encodeBlockUpdates(packets, chunk, extraBlockUpdates, true);
        for (var packet : packets) {
            if (packet == null) {
                // packet can be null if there is no update in a chunk section
                continue;
            }

            this.clientComponent.sendPacket(packet);
        }
    }

    @Override
    public void viewBlockAction(Vector3ic p, BlockAction action) {
        var pos = toNetwork(p);
        var pos3f = Vector3f.from(p.x(), p.y(), p.z());
        switch (action) {
            case SimpleBlockAction.OPEN -> {
                var packet = new BlockEventPacket();
                packet.setBlockPosition(pos);
                packet.setEventType(BLOCK_EVENT_TYPE_CHANGE_CHEST_STATE);
                packet.setEventData(BLOCK_EVENT_DATA_OPEN_CHEST);
                this.clientComponent.sendPacket(packet);
            }
            case SimpleBlockAction.CLOSE -> {
                var packet = new BlockEventPacket();
                packet.setBlockPosition(pos);
                packet.setEventType(BLOCK_EVENT_TYPE_CHANGE_CHEST_STATE);
                packet.setEventData(BLOCK_EVENT_DATA_CLOSE_CHEST);
                this.clientComponent.sendPacket(packet);
            }
            case StartBreakAction(double breakTime) -> {
                var packet = new LevelEventPacket();
                packet.setType(LevelEvent.BLOCK_START_BREAK);
                packet.setPosition(pos3f);
                packet.setData(toNetworkBreakTime(breakTime));
                this.clientComponent.sendPacket(packet);
            }
            case ContinueBreakAction(double breakTime) -> {
                var packet = new LevelEventPacket();
                packet.setType(LevelEvent.BLOCK_UPDATE_BREAK);
                packet.setPosition(pos3f);
                packet.setData(toNetworkBreakTime(breakTime));
                this.clientComponent.sendPacket(packet);
            }
            case SimpleBlockAction.STOP_BREAK -> {
                var packet = new LevelEventPacket();
                packet.setType(LevelEvent.BLOCK_STOP_BREAK);
                packet.setPosition(pos3f);
                this.clientComponent.sendPacket(packet);
            }
            default -> throw new IllegalStateException("Unhandled block action type: " + action.getClass().getSimpleName());
        }
    }

    protected int toNetworkBreakTime(double breakTime) {
        if (breakTime == 0) return 65535;
        return (int) (65535 / (breakTime * 20));
    }

    protected void encodeBlockUpdates(UpdateSubChunkBlocksPacket[] packets, Chunk chunk, Collection<BlockUpdate> blockUpdates, boolean isExtraLayer) {
        if (blockUpdates.isEmpty()) {
            return;
        }

        for (var update : blockUpdates) {
            var sectionY = update.y() >> 4;
            var index = sectionY - chunk.getDimensionInfo().minSectionY();
            UpdateSubChunkBlocksPacket packet;

            if ((packet = packets[index]) == null) {
                packet = new UpdateSubChunkBlocksPacket();
                packet.setChunkX(chunk.getX() << 4);
                packet.setChunkY(sectionY << 4);
                packet.setChunkZ(chunk.getZ() << 4);
                packets[index] = packet;
            }

            // updateFlags is a combination of flags that specify the way the block is updated client-side. It is a
            // combination of the flags above, but typically sending only the BLOCK_UPDATE_NETWORK flag is sufficient.
            var entry = new BlockChangeEntry(
                    Vector3i.from(update.x(), update.y(), update.z()), update.blockState()::blockStateHash,
                    BLOCK_UPDATE_NETWORK, -1, BlockChangeEntry.MessageType.NONE
            );

            if (isExtraLayer) {
                packet.getExtraBlocks().add(entry);
            } else {
                packet.getStandardBlocks().add(entry);
            }
        }
    }

    @Override
    public void viewSound(Sound sound, Vector3dc p, boolean relative) {
        LevelSoundEventPacket packet = new LevelSoundEventPacket();
        var pos = toNetwork(MathUtils.toVec3f(p));
        packet.setPosition(pos);
        packet.setIdentifier(":");
        packet.setExtraData(-1);
        packet.setRelativeVolumeDisabled(relative);

        switch (sound) {
            case SimpleSound.FIREWORK_LAUNCH -> packet.setSound(SoundEvent.LAUNCH);
            case SimpleSound.FIREWORK_HUGE_BLAST -> packet.setSound(SoundEvent.LARGE_BLAST);
            case SimpleSound.FIREWORK_BLAST -> packet.setSound(SoundEvent.BLAST);
            case SimpleSound.FIREWORK_FLICKER -> packet.setSound(SoundEvent.TWINKLE);
            case SimpleSound.FURNACE_CRACKLE -> packet.setSound(SoundEvent.FURNACE_USE);
            case SimpleSound.CAMPFIRE_CRACKLE -> packet.setSound(SoundEvent.CAMPFIRE_CRACKLE);
            case SimpleSound.BLAST_FURNACE_CRACKLE -> packet.setSound(SoundEvent.BLAST_FURNACE_USE);
            case SimpleSound.SMOKER_CRACKLE -> packet.setSound(SoundEvent.SMOKER_USE);
            case SimpleSound.POTION_BREWED -> packet.setSound(SoundEvent.POTION_BREWED);
            case SimpleSound.USE_SPYGLASS -> packet.setSound(SoundEvent.USE_SPYGLASS);
            case SimpleSound.STOP_USING_SPYGLASS -> packet.setSound(SoundEvent.STOP_USING_SPYGLASS);
            case SimpleSound.FIRE_EXTINGUISH -> packet.setSound(SoundEvent.EXTINGUISH_FIRE);
            case SimpleSound.IGNITE -> packet.setSound(SoundEvent.IGNITE);
            case SimpleSound.BURNING -> packet.setSound(SoundEvent.PLAYER_HURT_ON_FIRE);
            case SimpleSound.DROWNING -> packet.setSound(SoundEvent.PLAYER_HURT_DROWN);
            case SimpleSound.BURP -> packet.setSound(SoundEvent.BURP);
            case SimpleSound.DENY -> packet.setSound(SoundEvent.DENY);
            case SimpleSound.CHEST_CLOSE -> packet.setSound(SoundEvent.CHEST_CLOSED);
            case SimpleSound.CHEST_OPEN -> packet.setSound(SoundEvent.CHEST_OPEN);
            case SimpleSound.ENDER_CHEST_CLOSE -> packet.setSound(SoundEvent.ENDERCHEST_CLOSED);
            case SimpleSound.ENDER_CHEST_OPEN -> packet.setSound(SoundEvent.ENDERCHEST_OPEN);
            case SimpleSound.SHULKER_BOX_CLOSE -> packet.setSound(SoundEvent.SHULKERBOX_CLOSED);
            case SimpleSound.SHULKER_BOX_OPEN -> packet.setSound(SoundEvent.SHULKERBOX_OPEN);
            case SimpleSound.BARREL_CLOSE -> packet.setSound(SoundEvent.BARREL_CLOSE);
            case SimpleSound.BARREL_OPEN -> packet.setSound(SoundEvent.BARREL_OPEN);
            case SimpleSound.FIZZ -> packet.setSound(SoundEvent.FIZZ);
            case SimpleSound.SPONGE_ABSORB -> packet.setSound(SoundEvent.SPONGE_ABSORB);
            case SimpleSound.GLASS_BREAK -> packet.setSound(SoundEvent.GLASS);
            case SimpleSound.BOW_SHOOT -> packet.setSound(SoundEvent.BOW);
            case SimpleSound.CROSSBOW_SHOOT -> packet.setSound(SoundEvent.CROSSBOW_SHOOT);
            case SimpleSound.ARROW_HIT -> packet.setSound(SoundEvent.BOW_HIT);
            case SimpleSound.MUSIC_DISC_END -> packet.setSound(SoundEvent.STOP_RECORD);
            case SimpleSound.COMPOSTER_EMPTY -> packet.setSound(SoundEvent.COMPOSTER_EMPTY);
            case SimpleSound.COMPOSTER_FILL -> packet.setSound(SoundEvent.COMPOSTER_FILL);
            case SimpleSound.COMPOSTER_FILL_LAYER -> packet.setSound(SoundEvent.COMPOSTER_FILL_LAYER);
            case SimpleSound.COMPOSTER_READY -> packet.setSound(SoundEvent.COMPOSTER_READY);
            case SimpleSound.LECTERN_BOOK_PLACE -> packet.setSound(SoundEvent.LECTERN_BOOK_PLACE);
            case SimpleSound.WAXED_SIGN_FAILED_INTERACTION -> packet.setSound(SoundEvent.WAXED_SIGN_INTERACT_FAIL);
            case SimpleSound.TELEPORT -> packet.setSound(SoundEvent.TELEPORT);
            case SimpleSound.DECORATED_POT_INSERT_FAILED -> packet.setSound(SoundEvent.DECORATED_POT_INSERT_FAILED);
            case SimpleSound.ITEM_BREAK -> packet.setSound(SoundEvent.BREAK);
            case SimpleSound.CHORUS_FLOWER_GROW -> packet.setSound(SoundEvent.CHORUS_GROW);
            case SimpleSound.END_PORTAL_FRAME_FILLED -> packet.setSound(SoundEvent.BLOCK_END_PORTAL_FRAME_FILL);
            case SimpleSound.END_PORTAL_SPAWN -> packet.setSound(SoundEvent.BLOCK_END_PORTAL_SPAWN);
            case SimpleSound.PAINTING_PLACE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_PLACE);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.GLOW_INK_SAC_USED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_INK_SACE_USED);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.DOOR_CRASH -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ZOMBIE_DOOR_CRASH);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.EXPLOSION -> {
                packet.setSound(SoundEvent.EXPLODE);
            }
            case SimpleSound.THUNDER -> {
                packet.setSound(SoundEvent.THUNDER);
                packet.setIdentifier("minecraft:lightning_bolt");
            }
            case SimpleSound.CLICK -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_CLICK);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.WAXED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_WAX_ON);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.WAX_REMOVED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_WAX_OFF);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.COPPER_SCRAPED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_SCRAPE);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.POP -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_INFINITY_ARROW_PICKUP);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ITEM_ADD -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_ADD);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ITEM_FRAME_REMOVE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_REMOVE);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ITEM_FRAME_ROTATE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_ROTATE);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.GHAST_WARNING -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_GHAST_WARNING);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.GHAST_SHOOT -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_GHAST_FIREBALL);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.TNT -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_FUSE);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ANVIL_LAND -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_LAND);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ANVIL_USE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_USED);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ANVIL_BREAK -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_BROKEN);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.FIRE_CHARGE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_BLAZE_FIREBALL);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.TOTEM -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_TOTEM_USED);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ITEM_THROW -> {
                packet.setSound(SoundEvent.THROW);
                packet.setIdentifier("minecraft:player");
            }
            case SimpleSound.LEVEL_UP -> {
                packet.setSound(SoundEvent.LEVELUP);
                packet.setExtraData(0x10000000);
            }
            case SimpleSound.EXPERIENCE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_EXPERIENCE_ORB_PICKUP);
                levelEvent.setPosition(pos.toFloat());
                this.clientComponent.sendPacket(levelEvent);
                return;
            }
            case EquipItemSound so -> packet.setSound(getEquipSound(so.itemType()));
            case NoteSound so -> {
                packet.setSound(SoundEvent.NOTE);
                packet.setExtraData((so.instrument().ordinal() << 8) | so.pitch());
            }
            case FallSound so -> {
                packet.setIdentifier("minecraft:player");
                if (so.distance() > 4) {
                    packet.setSound(SoundEvent.FALL_BIG);
                } else {
                    packet.setSound(SoundEvent.FALL_SMALL);
                }
            }
            case DoorOpenSound so -> {
                packet.setSound(SoundEvent.DOOR_OPEN);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case DoorCloseSound so -> {
                packet.setSound(SoundEvent.DOOR_CLOSE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case ButtonPressSound so -> {
                packet.setSound(SoundEvent.BUTTON_CLICK_ON);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case ButtonReleaseSound so -> {
                packet.setSound(SoundEvent.BUTTON_CLICK_OFF);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case TrapdoorOpenSound so -> {
                packet.setSound(SoundEvent.TRAPDOOR_OPEN);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case TrapdoorCloseSound so -> {
                packet.setSound(SoundEvent.TRAPDOOR_CLOSE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case FenceGateOpenSound so -> {
                packet.setSound(SoundEvent.FENCE_GATE_OPEN);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case FenceGateCloseSound so -> {
                packet.setSound(SoundEvent.FENCE_GATE_CLOSE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case BlockPlaceSound so -> {
                packet.setSound(SoundEvent.PLACE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case BlockBreakingSound so -> {
                packet.setSound(SoundEvent.HIT);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case ItemUseOnBlockSound so -> {
                packet.setSound(SoundEvent.ITEM_USE_ON);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case AttackSound so -> {
                packet.setIdentifier("minecraft:player");
                if (!so.damage()) {
                    packet.setSound(SoundEvent.ATTACK_NODAMAGE);
                } else {
                    packet.setSound(SoundEvent.ATTACK_STRONG);
                }
            }
            case BucketFillSound so -> {
                if (so.water()) {
                    packet.setSound(SoundEvent.BUCKET_FILL_WATER);
                } else {
                    packet.setSound(SoundEvent.BUCKET_FILL_LAVA);
                }
            }
            case BucketEmptySound so -> {
                if (so.water()) {
                    packet.setSound(SoundEvent.BUCKET_EMPTY_WATER);
                } else {
                    packet.setSound(SoundEvent.BUCKET_EMPTY_LAVA);
                }
            }
            case CrossbowLoadSound so -> {
                switch (so.stage()) {
                    case START -> packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_START : SoundEvent.CROSSBOW_LOADING_START);
                    case MIDDLE -> packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_MIDDLE : SoundEvent.CROSSBOW_LOADING_MIDDLE);
                    case END -> packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_END : SoundEvent.CROSSBOW_LOADING_END);
                }
            }
            case MusicDiscPlaySound so -> {
                switch (so.discType()) {
                    case DISC_13 -> packet.setSound(SoundEvent.RECORD_13);
                    case DISC_CAT -> packet.setSound(SoundEvent.RECORD_CAT);
                    case DISC_BLOCKS -> packet.setSound(SoundEvent.RECORD_BLOCKS);
                    case DISC_CHIRP -> packet.setSound(SoundEvent.RECORD_CHIRP);
                    case DISC_FAR -> packet.setSound(SoundEvent.RECORD_FAR);
                    case DISC_MALL -> packet.setSound(SoundEvent.RECORD_MALL);
                    case DISC_MELLOHI -> packet.setSound(SoundEvent.RECORD_MELLOHI);
                    case DISC_STAL -> packet.setSound(SoundEvent.RECORD_STAL);
                    case DISC_STRAD -> packet.setSound(SoundEvent.RECORD_STRAD);
                    case DISC_WARD -> packet.setSound(SoundEvent.RECORD_WARD);
                    case DISC_11 -> packet.setSound(SoundEvent.RECORD_11);
                    case DISC_WAIT -> packet.setSound(SoundEvent.RECORD_WAIT);
                    case DISC_OTHERSIDE -> packet.setSound(SoundEvent.RECORD_OTHERSIDE);
                    case DISC_PIGSTEP -> packet.setSound(SoundEvent.RECORD_PIGSTEP);
                    case DISC_5 -> packet.setSound(SoundEvent.RECORD_5);
                    case DISC_RELIC -> packet.setSound(SoundEvent.RECORD_RELIC);
                    case DISC_CREATOR -> packet.setSound(SoundEvent.RECORD_CREATOR);
                    case DISC_CREATOR_MUSIC_BOX -> packet.setSound(SoundEvent.RECORD_CREATOR_MUSIC_BOX);
                    case DISC_PRECIPICE -> packet.setSound(SoundEvent.RECORD_PRECIPICE);
                    case DISC_TEARS -> packet.setSound(SoundEvent.RECORD_TEARS);
                    case DISC_LAVA_CHICKEN -> packet.setSound(SoundEvent.RECORD_LAVA_CHICKEN);
                    default -> throw new IllegalArgumentException();
                }
            }
            case DecoratedPotInsertedSound so -> {
                PlaySoundPacket playSound = new PlaySoundPacket();
                playSound.setSound(SoundNames.BLOCK_DECORATED_POT_INSERT);
                playSound.setPosition(pos);
                playSound.setVolume(1.0f);
                playSound.setPitch(0.7f + 0.5f * (float) so.progress());
                this.clientComponent.sendPacket(playSound);
                return;
            }
            case CustomSound so -> {
                PlaySoundPacket playSound = new PlaySoundPacket();
                playSound.setSound(so.soundName());
                playSound.setPosition(pos);
                playSound.setVolume(so.volume());
                playSound.setPitch(so.pitch());
                this.clientComponent.sendPacket(playSound);
                return;
            }
            default -> throw new IllegalArgumentException("Unhandled sound type: " + sound.getClass().getSimpleName());
        }

        this.clientComponent.sendPacket(packet);
    }

    protected SoundEvent getEquipSound(ItemType<?> itemType) {
        if (itemType == ItemTypes.ELYTRA) {
            return SoundEvent.ARMOR_EQUIP_ELYTRA;
        }

        return switch (ItemHelper.getArmorTier(itemType)) {
            case LEATHER -> SoundEvent.ARMOR_EQUIP_LEATHER;
            case IRON -> SoundEvent.ARMOR_EQUIP_IRON;
            case CHAIN -> SoundEvent.ARMOR_EQUIP_CHAIN;
            case GOLD -> SoundEvent.ARMOR_EQUIP_GOLD;
            case DIAMOND, NETHERITE -> SoundEvent.ARMOR_EQUIP_DIAMOND;
            case null -> SoundEvent.ARMOR_EQUIP_GENERIC;
        };
    }

    @Override
    public void viewParticle(Particle particle, Vector3dc p) {
        var pos = toNetwork(MathUtils.toVec3f(p));
        var packet = new LevelEventPacket();
        packet.setPosition(pos);
        switch (particle) {
            case SimpleParticle.EXPLODE -> packet.setType(ParticleType.EXPLODE);
            case SimpleParticle.HUGE_EXPLOSION -> packet.setType(LevelEvent.PARTICLE_EXPLOSION);
            case SimpleParticle.BONE_MEAL -> packet.setType(LevelEvent.PARTICLE_CROP_GROWTH);
            case SimpleParticle.BLOCK_FORCE_FIELD -> packet.setType(LevelEvent.PARTICLE_DENY_BLOCK);
            case SimpleParticle.ENDERMAN_TELEPORT -> packet.setType(LevelEvent.PARTICLE_TELEPORT);
            case SimpleParticle.EVAPORATE -> packet.setType(LevelEvent.PARTICLE_EVAPORATE_WATER);
            case SimpleParticle.SNOWBALL_POOF -> packet.setType(ParticleType.SNOWBALL_POOF);
            case SimpleParticle.ENTITY_FLAME -> packet.setType(ParticleType.MOB_FLAME);
            case SimpleParticle.WATER_DRIP -> packet.setType(ParticleType.DRIP_WATER);
            case SimpleParticle.LAVA_DRIP -> packet.setType(ParticleType.DRIP_LAVA);
            case SimpleParticle.LAVA -> packet.setType(ParticleType.LAVA);
            case SimpleParticle.DUST_PLUME -> packet.setType(ParticleType.DUST_PLUME);
            case SimpleParticle.WHITE_SMOKE -> packet.setType(ParticleType.WHITE_SMOKE);
            case SimpleParticle.FIREWORK_CONTRAIL -> packet.setType(ParticleType.FIREWORKS);
            case CustomParticle pa -> {
                var pk = new SpawnParticleEffectPacket();
                pk.setDimensionId(thisPlayer.getDimension().getDimensionInfo().dimensionId());
                pk.setIdentifier(pa.particleName());
                pk.setMolangVariablesJson(Optional.ofNullable(pa.moLangVariables()));
                pk.setPosition(pos);
                this.clientComponent.sendPacket(pk);
                return;
            }
            case DragonEggTeleportParticle pa -> {
                int xSign = pa.diff().x() < 0 ? 1 << 24 : 0;
                int ySign = pa.diff().y() < 0 ? 1 << 25 : 0;
                int zSign = pa.diff().z() < 0 ? 1 << 26 : 0;
                int data = (Math.abs(pa.diff().x()) << 16) |
                           (Math.abs(pa.diff().y()) << 8) |
                           (Math.abs(pa.diff().z())) | xSign | ySign | zSign;

                packet.setType(LevelEvent.PARTICLE_DRAGON_EGG);
                packet.setData(data);
            }
            case NoteParticle pa -> {
                var pk = new BlockEventPacket();
                pk.setBlockPosition(Vector3i.from((int) p.x(), (int) p.y(), (int) p.z()));
                pk.setEventType(pa.instrument().ordinal());
                pk.setEventData(pa.pitch());
                this.clientComponent.sendPacket(pk);
                return;
            }
            case BlockBreakParticle pa -> {
                packet.setType(LevelEvent.PARTICLE_DESTROY_BLOCK);
                packet.setData(pa.blockState().blockStateHash());
            }
            case PunchBlockParticle pa -> {
                packet.setType(switch (Objects.requireNonNull(pa.blockFace())) {
                    case UP -> LevelEvent.PARTICLE_BREAK_BLOCK_UP;
                    case DOWN -> LevelEvent.PARTICLE_BREAK_BLOCK_DOWN;
                    case NORTH -> LevelEvent.PARTICLE_BREAK_BLOCK_NORTH;
                    case SOUTH -> LevelEvent.PARTICLE_BREAK_BLOCK_SOUTH;
                    case WEST -> LevelEvent.PARTICLE_BREAK_BLOCK_WEST;
                    case EAST -> LevelEvent.PARTICLE_BREAK_BLOCK_EAST;
                });
                packet.setData(pa.blockState().blockStateHash());
            }
            case FlameParticle pa -> {
                packet.setType(ParticleType.FLAME);
                packet.setData(pa.color().getRGB());
            }
            case ItemBreakParticle pa -> {
                packet.setType(ParticleType.ICON_CRACK);
                packet.setData((pa.itemStack().getItemType().getRuntimeId() << 16) | pa.itemStack().getMeta());
            }
            case SplashParticle pa -> {
                packet.setType(LevelEvent.PARTICLE_POTION_SPLASH);
                packet.setData(pa.color().getRGB());
            }
            case EffectParticle pa -> {
                packet.setType(ParticleType.MOB_SPELL);
                packet.setData(pa.color().getRGB());
            }
            case DustParticle pa -> {
                packet.setType(ParticleType.FALLING_DUST);
                packet.setData(pa.color().getRGB());
            }
            default -> throw new IllegalArgumentException("Unhandled particle type: " + particle.getClass().getSimpleName());
        }
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewWeather(Weather weather) {
        var packet1 = new LevelEventPacket();
        packet1.setPosition(Vector3f.ZERO);
        packet1.setType(LevelEvent.STOP_RAINING);
        if (weather != Weather.CLEAR) {
            packet1.setType(LevelEvent.START_RAINING);
            packet1.setData(65535);
        }
        this.clientComponent.sendPacket(packet1);

        var packet2 = new LevelEventPacket();
        packet2.setPosition(Vector3f.ZERO);
        packet2.setType(LevelEvent.STOP_THUNDERSTORM);
        if (weather == Weather.THUNDER) {
            packet2.setType(LevelEvent.START_THUNDERSTORM);
            packet2.setData(65535);
        }
        this.clientComponent.sendPacket(packet2);
    }

    @Override
    public void viewDebugShape(DebugShape debugShape) {
        var packet = new ServerScriptDebugDrawerPacket();
        packet.getShapes().add(toNetworkData(debugShape));
        clientComponent.sendPacket(packet);
    }

    @Override
    public void viewDebugShapes(Set<DebugShape> debugShapes) {
        var packet = new ServerScriptDebugDrawerPacket();
        for (var debugShape : debugShapes) {
            packet.getShapes().add(toNetworkData(debugShape));
        }
        clientComponent.sendPacket(packet);
    }

    @Override
    public void removeDebugShape(DebugShape debugShape) {
        var packet = new ServerScriptDebugDrawerPacket();
        packet.getShapes().add(createRemovalNotice(debugShape));
        clientComponent.sendPacket(packet);
    }

    @Override
    public void removeDebugShapes(Set<DebugShape> debugShapes) {
        var packet = new ServerScriptDebugDrawerPacket();
        for (var debugShape : debugShapes) {
            packet.getShapes().add(createRemovalNotice(debugShape));
        }
        clientComponent.sendPacket(packet);
    }

    protected static org.cloudburstmc.protocol.bedrock.data.DebugShape createRemovalNotice(DebugShape shape) {
        return new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                shape.getId(), null, null,
                null, null, null,
                null, null, null,
                null, null, null, null
        );
    }

    protected static org.cloudburstmc.protocol.bedrock.data.DebugShape toNetworkData(DebugShape shape) {
        return switch (shape) {
            case DebugArrow arrow -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    arrow.getId(), ARROW, toNetwork(arrow.getPosition()), arrow.getArrowHeadScale(),
                    null, null, arrow.getColor(),
                    null, null, toNetwork(arrow.getEndPosition()),
                    arrow.getArrowHeadLength(), arrow.getArrowHeadRadius(), arrow.getArrowHeadSegments()
            );
            case DebugBox box -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    box.getId(), BOX, toNetwork(box.getPosition()), box.getScale(),
                    null, null, box.getColor(),
                    null, toNetwork(box.getBoxBounds()), null,
                    null, null, null
            );
            case DebugCircle circle -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    circle.getId(), CIRCLE, toNetwork(circle.getPosition()), circle.getScale(),
                    null, null, circle.getColor(),
                    null, null, null,
                    null, null, circle.getSegments()
            );
            case DebugLine line -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    line.getId(), LINE, toNetwork(line.getPosition()), null,
                    null, null, line.getColor(),
                    null, null, toNetwork(line.getEndPosition()),
                    null, null, null
            );
            case DebugSphere sphere -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    sphere.getId(), SPHERE, toNetwork(sphere.getPosition()), sphere.getScale(),
                    null, null, sphere.getColor(),
                    null, null, null,
                    null, null, sphere.getSegments()
            );
            case DebugText text -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    text.getId(), TEXT, toNetwork(text.getPosition()), null,
                    null, null, text.getColor(),
                    text.getText(), null, null,
                    null, null, null
            );
            default -> throw new IllegalStateException("Unexpected value: " + shape);
        };
    }

    @Override
    public void viewBlockEntity(BlockEntity blockEntity) {
        if (!((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).sendToClient()) {
            return;
        }

        var packet = new BlockEntityDataPacket();
        packet.setBlockPosition(toNetwork(blockEntity.getPosition()));
        packet.setData(blockEntity.saveNBT());
        this.clientComponent.sendPacket(packet);
    }
}
