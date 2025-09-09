package org.allaymc.server.entity.component.player;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.UnopenedContainerId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.player.EntityPlayerEntityViewerComponent;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityImpl;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.joml.Vector3dc;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket.Flag.*;

/**
 * @author daoge_cmd
 */
public class EntityPlayerEntityViewerComponentImpl implements EntityPlayerEntityViewerComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_player_entity_viewer_component");

    /**
     * A map which contains the network offset of some entities. The network offset is the additional offset in
     * y coordinate when sent over network.This is mostly the case for older entities such as player and TNT.
     */
    protected static final Map<EntityType<?>, Float> NETWORK_OFFSETS;

    static {
        NETWORK_OFFSETS = new HashMap<>();
        NETWORK_OFFSETS.put(EntityTypes.PLAYER, 1.62f);
        NETWORK_OFFSETS.put(EntityTypes.FALLING_BLOCK, 0.49f);
        NETWORK_OFFSETS.put(EntityTypes.ITEM, 0.125f);
        NETWORK_OFFSETS.put(EntityTypes.TNT, 0.49f);
    }

    @ComponentObject
    protected EntityPlayer thisPlayer;
    @Dependency
    protected EntityPlayerNetworkComponentImpl networkComponent;

    @Override
    public void viewEntity(Entity entity) {
        var l = entity.getLocation();
        var position = Vector3f.from(l.x(), l.y() + NETWORK_OFFSETS.getOrDefault(entity.getEntityType(), 0.0f), l.z());
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
                p.setGameType(player.getGameType());
                p.getMetadata().putAll(getMetadata(entity));
                p.setDeviceId(loginData.getDeviceInfo().deviceId());
                p.setHand(player.getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand().toNetworkItemData());
                yield p;
            }
            case EntityItem item -> {
                var p = new AddItemEntityPacket();
                p.setRuntimeEntityId(item.getRuntimeId());
                p.setUniqueEntityId(item.getUniqueId());
                p.setItemInHand(item.getItemStack().toNetworkItemData());
                p.setPosition(position);
                p.setMotion(motion);
                p.getMetadata().putAll(getMetadata(entity));
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
                p.getMetadata().putAll(getMetadata(entity));
                yield p;
            }
        };
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void removeEntity(Entity entity) {
        var packet = new RemoveEntityPacket();
        packet.setUniqueEntityId(entity.getRuntimeId());
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewPlayerGameType(EntityPlayer player) {
        if (player == thisPlayer) {
            var packet = new SetPlayerGameTypePacket();
            packet.setGamemode(thisPlayer.getGameType().ordinal());
            this.networkComponent.sendPacket(packet);
        } else {
            var packet = new UpdatePlayerGameTypePacket();
            packet.setGameType(thisPlayer.getGameType());
            packet.setEntityId(thisPlayer.getRuntimeId());
            this.networkComponent.sendPacket(packet);
        }
    }

    @Override
    public void viewEntityLocation(Entity entity, Location3d locationLastSent, Location3dc newLocation, boolean teleporting) {
        BedrockPacket packet;
        if (Server.SETTINGS.entitySettings().physicsEngineSettings().useDeltaMovePacket()) {
            packet = createDeltaMovePacket(entity, locationLastSent, newLocation, teleporting);
        } else {
            packet = createAbsoluteMovePacket(entity, newLocation, teleporting);
        }
        this.networkComponent.sendPacket(packet);
    }

    protected BedrockPacket createAbsoluteMovePacket(Entity entity, Location3dc newLocation, boolean teleporting) {
        var packet = new MoveEntityAbsolutePacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setPosition(Vector3f.from(newLocation.x(), newLocation.y() + NETWORK_OFFSETS.getOrDefault(entity.getEntityType(), 0.0f), newLocation.z()));
        packet.setRotation(Vector3f.from(newLocation.pitch(), newLocation.yaw(), newLocation.headYaw()));
        packet.setTeleported(teleporting);
        if (entity instanceof EntityPhysicsComponent physicsComponent) {
            packet.setOnGround(physicsComponent.isOnGround());
        }
        return packet;
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
            packet.setY((float) newLocation.y());
            locationLastSent.y = newLocation.y() + NETWORK_OFFSETS.getOrDefault(entity.getEntityType(), 0.0f);
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
        var settings = Server.SETTINGS.entitySettings().physicsEngineSettings();
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

    @Override
    public <T extends Entity & EntityPhysicsComponent> void viewEntityMotion(T entity, Vector3dc motion) {
        var packet = new SetEntityMotionPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewEntityMetadata(Entity entity) {
        var packet = new SetEntityDataPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMetadata(getMetadata(entity));
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityHand(T entity) {
        var container = entity.getContainer(FullContainerType.PLAYER_INVENTORY);
        var handSlot = container.getHandSlot();
        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setContainerId(UnopenedContainerId.PLAYER_INVENTORY);
        packet.setItem(container.getItemInHand().toNetworkItemData());
        packet.setInventorySlot(handSlot);
        packet.setHotbarSlot(handSlot);
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityOffhand(T entity) {
        var container = entity.getContainer(FullContainerType.OFFHAND);
        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setContainerId(UnopenedContainerId.OFFHAND);
        // Network slot index for offhand is 1, see FullContainerType.OFFHAND. Field `hotbarSlot` is unused
        packet.setInventorySlot(1);
        packet.setItem(container.getOffhand().toNetworkItemData());
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityArmors(T entity) {
        var container = entity.getContainer(FullContainerType.ARMOR);
        var packet = new MobArmorEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setBody(ItemAirStack.AIR_STACK.toNetworkItemData());
        packet.setHelmet(container.getHelmet().toNetworkItemData());
        packet.setChestplate(container.getChestplate().toNetworkItemData());
        packet.setLeggings(container.getLeggings().toNetworkItemData());
        packet.setBoots(container.getBoots().toNetworkItemData());
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewPlayerSkin(EntityPlayer player) {
        var skin = player.getSkin();
        var packet = new PlayerSkinPacket();
        packet.setUuid(networkComponent.getLoginData().getUuid());
        packet.setSkin(skin);
        packet.setNewSkinName(skin.getSkinId());
        // It seems that old skin name is unused
        packet.setOldSkinName("");
        packet.setTrustedSkin(true);
        this.networkComponent.sendPacket(packet);
    }

    protected EntityDataMap getMetadata(Entity entity) {
        return ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).getMetadata();
    }
}
