package cn.allay.api.entity.interfaces.player;

import cn.allay.api.client.data.AdventureSettings;
import cn.allay.api.client.skin.Skin;
import cn.allay.api.component.annotation.ComponentEventListener;
import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.container.FixedContainerId;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.base.EntityBaseComponentImpl;
import cn.allay.api.entity.component.container.EntityContainerHolderComponent;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.entity.interfaces.item.EntityItem;
import cn.allay.api.entity.interfaces.item.EntityItemBaseComponent;
import cn.allay.api.math.location.Location3f;
import cn.allay.api.math.location.Location3fc;
import cn.allay.api.server.Server;
import cn.allay.api.world.chunk.Chunk;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jetbrains.annotations.Range;
import org.joml.primitives.AABBf;

import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public class EntityPlayerBaseComponentImpl extends EntityBaseComponentImpl<EntityPlayer> implements EntityPlayerBaseComponent {

    @Dependency
    protected EntityContainerHolderComponent containerHolderComponent;
    @Dependency
    protected EntityPlayerNetworkComponent networkComponent;
    @Getter
    @Setter
    protected GameType gameType = GameType.CREATIVE;
    @Getter
    @Setter
    protected Skin skin;
    @Getter
    protected AdventureSettings adventureSettings;
    @Getter
    @Setter
    protected boolean op = true;//TODO
    @Getter
    protected String displayName;
    @Getter
    protected int chunkLoadingRadius = Server.getInstance().getServerSettings().worldSettings().viewDistance();
    @Getter
    @Setter
    protected int chunkTrySendCountPerTick = Server.getInstance().getServerSettings().worldSettings().chunkTrySendCountPerTick();
    @Getter
    @Setter
    private Function<SubChunkRequestPacket, SubChunkPacket> subChunkRequestHandler =
            packet -> {
                throw new UnsupportedOperationException();
            };

    public EntityPlayerBaseComponentImpl(EntityInitInfo<EntityPlayer> info) {
        super(info, new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f));
    }

    @ComponentEventListener
    protected void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        var loginData = networkComponent.getLoginData();
        skin = loginData.getSkin();
        setDisplayName(loginData.getDisplayName());
    }

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        super.onInitFinish(initInfo);
        this.adventureSettings = new AdventureSettings(thisEntity);
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        metadata.setString(EntityDataTypes.NAME, displayName);
        sendEntityData(EntityDataTypes.NAME);
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
                thisEntity.sendContentsWithSpecificContainerId(inventory, FixedContainerId.PLAYER_INVENTORY, slot);
            }
        }
    }

    @Override
    public void spawnTo(EntityPlayer player) {
        if (thisEntity != player)
            super.spawnTo(player);
    }

    @Override
    public void despawnFrom(EntityPlayer player) {
        if (thisEntity != player)
            super.despawnFrom(player);
    }

    @Override
    public void broadcastMoveToViewers(Location3fc newLoc) {
        super.broadcastMoveToViewers(new Location3f(newLoc).add(0, getBaseOffset(), 0));
    }

    @Override
    public BedrockPacket createSpawnPacket() {
        var addPlayerPacket = new AddPlayerPacket();
        addPlayerPacket.setRuntimeEntityId(uniqueId);
        addPlayerPacket.setUniqueEntityId(uniqueId);
        addPlayerPacket.setUuid(networkComponent.getLoginData().getUuid());
        addPlayerPacket.setUsername(networkComponent.getOriginName());
        addPlayerPacket.setPlatformChatId(networkComponent.getLoginData().getDeviceInfo().getDeviceId());
        addPlayerPacket.setPosition(Vector3f.from(location.x(), location.y(), location.z()));
        addPlayerPacket.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        addPlayerPacket.setRotation(Vector3f.from(location.pitch(), location.yaw(), location.headYaw()));
        addPlayerPacket.setGameType(gameType);
        addPlayerPacket.getMetadata().putAll(this.metadata.getEntityDataMap());
        addPlayerPacket.setDeviceId(networkComponent.getLoginData().getDeviceInfo().getDeviceId());
        addPlayerPacket.setHand(containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand().toNetworkItemData());//TODO: itemInHand
        return addPlayerPacket;
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
    public boolean computeMovementServerSide() {
        //TODO: fake client
        return false;
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

    public void sendChat(EntityPlayer sender, String message) {
        var pk = new TextPacket();
        pk.setType(TextPacket.Type.CHAT);
        pk.setMessage(message);
        pk.setSourceName(sender.getDisplayName());
        pk.setXuid(sender.getLoginData().getXuid());
        pk.setPlatformChatId(sender.getLoginData().getDeviceInfo().getDeviceId());
        networkComponent.sendPacket(pk);
    }

    public void sendRawMessage(String message) {
        sendSimpleMessage(message, TextPacket.Type.RAW);
    }

    public void sendTip(String message) {
        sendSimpleMessage(message, TextPacket.Type.TIP);
    }

    public void sendPopup(String message) {
        sendSimpleMessage(message, TextPacket.Type.POPUP);
    }

    protected void sendSimpleMessage(String message, TextPacket.Type type) {
        var pk = new TextPacket();
        pk.setType(TextPacket.Type.RAW);
        pk.setXuid(networkComponent.getLoginData().getXuid());
        pk.setMessage(message);
        networkComponent.sendPacket(pk);
    }

    @Override
    public boolean isLoaderActive() {
        return networkComponent.isOnline();
    }

    @Override
    public void setChunkLoadingRadius(int radius) {
        chunkLoadingRadius = Math.min(radius, Server.getInstance().getServerSettings().worldSettings().viewDistance());
        var chunkRadiusUpdatedPacket = new ChunkRadiusUpdatedPacket();
        chunkRadiusUpdatedPacket.setRadius(chunkLoadingRadius);
        networkComponent.sendPacket(chunkRadiusUpdatedPacket);
    }

    @Override
    public void preSendChunks(Set<Long> chunkHashes) {
        var chunkPublisherUpdatePacket = new NetworkChunkPublisherUpdatePacket();
        var loc = getLocation();
        chunkPublisherUpdatePacket.setPosition(Vector3i.from(loc.x(), loc.y(), loc.z()));
        chunkPublisherUpdatePacket.setRadius(getChunkLoadingRadius() << 4);
        networkComponent.sendPacket(chunkPublisherUpdatePacket);
    }

    @Override
    public void onChunkInRangeLoaded(Chunk chunk) {
        var levelChunkPacket = chunk.createLevelChunkPacket();
        networkComponent.sendPacket(levelChunkPacket);
        chunk.spawnEntitiesTo(thisEntity);
        networkComponent.onChunkInRangeLoaded();
    }

    @Override
    public void spawnEntity(Entity entity) {
        entity.spawnTo(this.thisEntity);
    }

    @Override
    public void despawnEntity(Entity entity) {
        entity.despawnFrom(this.thisEntity);
    }

    @Override
    public void onChunkOutOfRange(Set<Long> chunkHashes) {
        chunkHashes
                .stream()
                .map(location.world.getChunkService()::getChunk).filter(Objects::nonNull)
                .forEach(chunk -> chunk.despawnEntitiesFrom(thisEntity));
    }

    @Override
    public void handleChunkPacket(BedrockPacket packet) {
        networkComponent.sendPacket(packet);
    }

    @Override
    public void handleChunkPacketImmediately(BedrockPacket packet) {
        networkComponent.sendPacketImmediately(packet);
    }
}
