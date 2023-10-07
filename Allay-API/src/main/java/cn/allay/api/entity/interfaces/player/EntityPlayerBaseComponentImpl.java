package cn.allay.api.entity.interfaces.player;

import cn.allay.api.client.Client;
import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.container.FixedContainerId;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.entity.component.base.EntityBaseComponentImpl;
import cn.allay.api.entity.component.container.EntityContainerHolderComponent;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.entity.interfaces.item.EntityItem;
import cn.allay.api.entity.interfaces.item.EntityItemBaseComponent;
import cn.allay.api.math.location.Location3f;
import cn.allay.api.math.location.Location3fc;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jetbrains.annotations.Range;
import org.joml.primitives.AABBf;

import java.util.Objects;
import java.util.Set;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public class EntityPlayerBaseComponentImpl extends EntityBaseComponentImpl<EntityPlayer> implements EntityPlayerBaseComponent {

    @Dependency
    protected EntityContainerHolderComponent containerHolderComponent;
    protected Client client;

    public EntityPlayerBaseComponentImpl(EntityInitInfo<EntityPlayer> info) {
        super(info, new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f));
        if (info instanceof EntityPlayerInitInfo playerInitInfo) {
            client = playerInitInfo.getClient();
        } else {
            throw new IllegalArgumentException("EntityPlayerInitInfo is required for EntityPlayer");
        }
    }

    @Override
    public void tick() {
        super.tick();
        tryPickUpItems();
    }

    protected void tryPickUpItems() {
        var world = location.world;
        //pick up items
        var pickUpArea = new AABBf(
                location.x - 1.425f,
                location.y - 1.425f,
                location.z - 1.425f,
                location.x + 1.425f,
                location.y + 1.425f,
                location.z + 1.425f
        );
        var entityItems = world.getEntityPhysicsService().computeCollidingEntities(pickUpArea, true)
                .stream()
                .filter(e -> e instanceof EntityItem)
                .map(e -> (EntityItem) e)
                .filter(EntityItemBaseComponent::canBePicked)
                .toList();
        for (var entityItem : entityItems) {
            var item = entityItem.getItemStack();
            var inventory = Objects.requireNonNull(containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY));
            var slot = inventory.tryAddItem(item);
            if (slot != -1) {
                if (item.getCount() == 0) {
                    TakeItemEntityPacket takeItemEntityPacket = new TakeItemEntityPacket();
                    takeItemEntityPacket.setRuntimeEntityId(uniqueId);
                    takeItemEntityPacket.setItemRuntimeEntityId(entityItem.getUniqueId());
                    Objects.requireNonNull(world.getChunkService().getChunkByLevelPos((int) location.x, (int) location.z)).sendChunkPacket(takeItemEntityPacket);
                    world.removeEntity(entityItem);
                }
                // Because of the new inventory system, the client will expect a transaction confirmation, but instead of doing that
                // it's much easier to just resend the inventory.
                client.getPlayerEntity().sendContentsWithSpecificContainerId(inventory, FixedContainerId.PLAYER_INVENTORY, slot);
            }
        }
    }

    @Override
    public void spawnTo(Client client) {
        if (this.client != client)
            super.spawnTo(client);
    }

    @Override
    public void despawnFrom(Client client) {
        if (this.client != client)
            super.despawnFrom(client);
    }

    @Override
    public void broadcastMoveToViewers(Set<MoveEntityDeltaPacket.Flag> moveFlags, Location3fc newLoc) {
        var offsetLoc = new Location3f(newLoc).add(0, getBaseOffset(), 0);
        super.broadcastMoveToViewers(moveFlags, offsetLoc);
    }

    @Override
    public BedrockPacket createSpawnPacket() {
        var addPlayerPacket = new AddPlayerPacket();
        addPlayerPacket.setRuntimeEntityId(uniqueId);
        addPlayerPacket.setUniqueEntityId(uniqueId);
        addPlayerPacket.setUuid(client.getLoginData().getUuid());
        addPlayerPacket.setUsername(client.getName());
        addPlayerPacket.setPlatformChatId(client.getLoginData().getDeviceInfo().getDeviceId());
        addPlayerPacket.setPosition(Vector3f.from(location.x(), location.y(), location.z()));
        addPlayerPacket.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        addPlayerPacket.setRotation(Vector3f.from(location.pitch(), location.yaw(), location.headYaw()));
        addPlayerPacket.setGameType(client.getGameType());
        addPlayerPacket.getMetadata().putAll(this.metadata.getEntityDataMap());
        addPlayerPacket.setDeviceId(client.getLoginData().getDeviceInfo().getDeviceId());
        addPlayerPacket.setHand(containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand().toNetworkItemData());//TODO: itemInHand
        return addPlayerPacket;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public boolean isSprinting() {
        return metadata.getFlag(EntityFlag.SPRINTING);
    }

    @Override
    public void setSprinting(boolean sprinting) {
        metadata.setFlag(EntityFlag.SPRINTING, sprinting);
        sendEntityFlags(EntityFlag.SPRINTING);
    }

    @Override
    public boolean isSneaking() {
        return metadata.getFlag(EntityFlag.SNEAKING);
    }

    @Override
    public void setSneaking(boolean sneaking) {
        metadata.setFlag(EntityFlag.SNEAKING, sneaking);
        sendEntityFlags(EntityFlag.SNEAKING);
    }

    @Override
    public boolean isSwimming() {
        return metadata.getFlag(EntityFlag.SWIMMING);
    }

    @Override
    public void setSwimming(boolean swimming) {
        metadata.setFlag(EntityFlag.SWIMMING, swimming);
        sendEntityFlags(EntityFlag.SWIMMING);
    }

    @Override
    public boolean isGliding() {
        return metadata.getFlag(EntityFlag.GLIDING);
    }

    @Override
    public void setGliding(boolean gliding) {
        metadata.setFlag(EntityFlag.GLIDING, gliding);
        sendEntityFlags(EntityFlag.GLIDING);
    }

    @Override
    public boolean isCrawling() {
        return metadata.getFlag(EntityFlag.CRAWLING);
    }

    @Override
    public void setCrawling(boolean crawling) {
        metadata.setFlag(EntityFlag.CRAWLING, crawling);
        sendEntityFlags(EntityFlag.CRAWLING);
    }

    @Override
    public int getHandSlot() {
        return containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).getHandSlot();
    }

    @Override
    public void setHandSlot(@Range(from = 0, to = 8) int handSlot) {
        var inv = containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY);
        inv.setHandSlot(handSlot);
        var itemStack = inv.getItemStack(handSlot);

        var mobEquipmentPacket = new MobEquipmentPacket();
        mobEquipmentPacket.setRuntimeEntityId(uniqueId);
        mobEquipmentPacket.setItem(itemStack.toNetworkItemData());
        mobEquipmentPacket.setInventorySlot(handSlot);
        mobEquipmentPacket.setHotbarSlot(handSlot);

        sendPacketToViewers(mobEquipmentPacket);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putList(
                        "Offhand",
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(FullContainerType.OFFHAND).saveNBT())
                .putList(
                        "Inventory",
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).saveNBT())
                .putList(
                        "Armor",
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(FullContainerType.ARMOR).saveNBT())
                .build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        if (nbt.containsKey("Offhand")) {
            containerHolderComponent.getContainer(FullContainerType.OFFHAND).loadNBT(nbt.getList("Offhand", NbtType.COMPOUND));
        }
        if (nbt.containsKey("Inventory")) {
            containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).loadNBT(nbt.getList("Inventory", NbtType.COMPOUND));
        }
        if (nbt.containsKey("Armor")) {
            containerHolderComponent.getContainer(FullContainerType.ARMOR).loadNBT(nbt.getList("Armor", NbtType.COMPOUND));
        }
    }
}
