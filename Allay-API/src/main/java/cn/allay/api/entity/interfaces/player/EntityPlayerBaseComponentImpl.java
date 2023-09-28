package cn.allay.api.entity.interfaces.player;

import cn.allay.api.client.Client;
import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.entity.component.base.EntityBaseComponentImpl;
import cn.allay.api.entity.component.container.EntityContainerHolderComponent;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.entity.interfaces.item.EntityItem;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.packet.AddPlayerPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.MobEquipmentPacket;
import org.cloudburstmc.protocol.bedrock.packet.TakeItemEntityPacket;
import org.jetbrains.annotations.Range;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import java.util.Objects;
import java.util.function.Function;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public class EntityPlayerBaseComponentImpl extends EntityBaseComponentImpl<EntityPlayer> implements EntityPlayerBaseComponent {

    @Dependency
    protected EntityContainerHolderComponent containerHolderComponent;
    protected Client client;
    protected boolean sprinting;
    protected boolean sneaking;
    protected boolean swimming;
    protected boolean gliding;
    protected boolean crawling;

    public EntityPlayerBaseComponentImpl(EntityInitInfo<EntityPlayer> info, Function<EntityPlayer, AABBfc> aabbGetter) {
        super(info, aabbGetter);
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
        var entityItems = world.getEntityPhysicsService().computeCollidingEntities(pickUpArea)
                .stream()
                .filter(entity -> entity instanceof EntityItem entityItem && entityItem.canBePicked())
                .toList();
        for (var entityItem : entityItems) {
            var item = ((EntityItem) entityItem).getItemStack();
            var inventory = Objects.requireNonNull(containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY));
            var remain = inventory.tryAddItem(item, true);
            if (remain == null) {
                TakeItemEntityPacket takeItemEntityPacket = new TakeItemEntityPacket();
                takeItemEntityPacket.setRuntimeEntityId(uniqueId);
                takeItemEntityPacket.setItemRuntimeEntityId(entityItem.getUniqueId());
                Objects.requireNonNull(world.getChunkService().getChunkByLevelPos((int) location.x, (int) location.z)).sendChunkPacket(takeItemEntityPacket);
                world.removeEntity(entityItem);
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
    public BedrockPacket createSpawnPacket() {
        var addPlayerPacket = new AddPlayerPacket();
        addPlayerPacket.setRuntimeEntityId(uniqueId);
        addPlayerPacket.setUniqueEntityId(uniqueId);
        addPlayerPacket.setUuid(client.getLoginData().getUuid());
        addPlayerPacket.setUsername(client.getName());
        addPlayerPacket.setPlatformChatId(client.getLoginData().getDeviceInfo().getDeviceId());
        addPlayerPacket.setPosition(Vector3f.from(location.x(), location.y() + getBaseOffset(), location.z()));
        addPlayerPacket.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        addPlayerPacket.setRotation(Vector3f.from(location.pitch(), location.yaw(), location.headYaw()));
        addPlayerPacket.setGameType(client.getGameType());
        addPlayerPacket.getMetadata().putAll(this.metadata.getEntityDataMap());
        addPlayerPacket.setDeviceId(client.getLoginData().getDeviceInfo().getDeviceId());
        addPlayerPacket.setHand(ItemData.AIR);//TODO: itemInHand
        return addPlayerPacket;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }

    @Override
    public boolean isSprinting() {
        return sprinting;
    }

    @Override
    public void setSneaking(boolean sneaking) {
        this.sneaking = sneaking;
    }

    @Override
    public boolean isSneaking() {
        return sneaking;
    }

    @Override
    public void setSwimming(boolean swimming) {
        this.swimming = swimming;
    }

    @Override
    public boolean isSwimming() {
        return swimming;
    }

    @Override
    public void setGliding(boolean gliding) {
        this.gliding = gliding;
    }

    @Override
    public boolean isGliding() {
        return gliding;
    }

    @Override
    public void setCrawling(boolean crawling) {
        this.crawling = crawling;
    }

    @Override
    public boolean isCrawling() {
        return crawling;
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
