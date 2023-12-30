package org.allaymc.server.entity.component.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.client.data.AdventureSettings;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.component.annotation.ComponentEventListener;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.perm.Permissible;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.entity.component.common.EntityBaseComponentImpl;
import org.allaymc.api.entity.component.common.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.init.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.component.item.EntityItemBaseComponent;
import org.allaymc.api.entity.component.event.PlayerLoggedInEvent;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.chunk.Chunk;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputMessage;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jetbrains.annotations.Range;
import org.joml.primitives.AABBf;

import java.util.List;
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
    protected boolean op;
    protected PermTree permTree;
    @Getter
    protected String displayName;
    @Getter
    protected int chunkLoadingRadius = Server.SETTINGS.worldSettings().viewDistance();
    @Getter
    @Setter
    protected int chunkTrySendCountPerTick = Server.SETTINGS.worldSettings().chunkTrySendCountPerTick();

    public EntityPlayerBaseComponentImpl(EntityInitInfo<EntityPlayer> info) {
        super(info, new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f));
        op = true; // TODO: player perm
        permTree = PermTree.create();
        permTree.addPerm("*");
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
        var dimension = location.dimension;
        // pick up items
        var pickUpArea = new AABBf(
                location.x - 1.425f,
                location.y - 1.425f,
                location.z - 1.425f,
                location.x + 1.425f,
                location.y + 1.425f,
                location.z + 1.425f
        );
        var entityItems = dimension.getEntityPhysicsService().computeCollidingEntities(pickUpArea, true)
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
                    Objects.requireNonNull(dimension.getChunkService().getChunkByLevelPos((int) location.x, (int) location.z)).sendChunkPacket(takeItemEntityPacket);
                    entityItem.setItemStack(null);
                    dimension.getEntityUpdateService().removeEntity(entityItem);
                }
                // Because of the new inventory system, the client will expect a transaction confirmation, but instead of doing that
                // It's much easier to just resend the inventory.
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

    @Override
    public void sendChat(EntityPlayer sender, String message) {
        var pk = new TextPacket();
        pk.setType(TextPacket.Type.CHAT);
        pk.setMessage(message);
        pk.setSourceName(sender.getDisplayName());
        pk.setXuid(sender.getLoginData().getXuid());
        pk.setPlatformChatId(sender.getLoginData().getDeviceInfo().getDeviceId());
        networkComponent.sendPacket(pk);
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, TrContainer... outputs) {
        var pk = new CommandOutputPacket();
        pk.setType(CommandOutputType.ALL_OUTPUT);
        pk.setCommandOriginData(sender.getCommandOriginData());
        for (var output : outputs) {
            var pair = I18n.get().toClientFriendlyStyle(output.str(), output.args());
            pk.getMessages().add(new CommandOutputMessage(
                    false,
                    pair.left(),
                    pair.right() ? Utils.objectArrayToStringArray(output.args()) : new String[0]));
        }
        pk.setSuccessCount(0); // Unknown usage
        pk.setData(""); // Unknown usage
        networkComponent.sendPacket(pk);
    }

    protected CommandOriginData commandOriginData;

    @Override
    public CommandOriginData getCommandOriginData() {
        if (commandOriginData == null) {
            commandOriginData = new CommandOriginData(CommandOriginType.PLAYER, networkComponent.getLoginData().getUuid(), "", 0);
        }
        return commandOriginData;
    }

    @Override
    public void sendTip(String message) {
        sendSimpleMessage(message, TextPacket.Type.TIP);
    }

    @Override
    public void sendPopup(String message) {
        sendSimpleMessage(message, TextPacket.Type.POPUP);
    }

    @Override
    public void sendText(String text) {
        sendSimpleMessage(text, TextPacket.Type.RAW);
    }

    protected void sendSimpleMessage(String message, TextPacket.Type type) {
        var pk = new TextPacket();
        pk.setType(type);
        pk.setXuid(networkComponent.getLoginData().getXuid());
        pk.setMessage(message);
        networkComponent.sendPacket(pk);
    }

    @Override
    public boolean isLoaderActive() {
        return spawned;
    }

    @Override
    public void setChunkLoadingRadius(int radius) {
        chunkLoadingRadius = Math.min(radius, Server.SETTINGS.worldSettings().viewDistance());
        var chunkRadiusUpdatedPacket = new ChunkRadiusUpdatedPacket();
        chunkRadiusUpdatedPacket.setRadius(chunkLoadingRadius);
        networkComponent.sendPacket(chunkRadiusUpdatedPacket);
    }

    @Override
    public void publishClientChunkUpdate() {
        var chunkPublisherUpdatePacket = new NetworkChunkPublisherUpdatePacket();
        var loc = getCmdExecuteLocation();
        chunkPublisherUpdatePacket.setPosition(Vector3i.from(loc.x(), loc.y(), loc.z()));
        chunkPublisherUpdatePacket.setRadius((getChunkLoadingRadius() + 1) << 4);
        networkComponent.sendPacket(chunkPublisherUpdatePacket);
    }

    @Override
    public void onChunkInRangeSent(Chunk chunk) {
        chunk.spawnEntitiesTo(thisEntity);
        networkComponent.onChunkInRangeSent();
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
                .map(location.dimension.getChunkService()::getChunk).filter(Objects::nonNull)
                .forEach(chunk -> {
                    chunk.removeChunkLoader(this);
                    chunk.despawnEntitiesFrom(thisEntity);
                });
    }

    @Override
    public void handleChunkPacket(BedrockPacket packet) {
        networkComponent.sendPacket(packet);
    }

    @Override
    public void handleChunkPacketImmediately(BedrockPacket packet) {
        networkComponent.sendPacketImmediately(packet);
    }

    @Override
    public void sendLevelChunkPacket(LevelChunkPacket lcp) {
        networkComponent.sendPacket(lcp);
    }

    @Override
    public void sendTr(String tr, boolean forceTranslatedByClient, String... args) {
        var pk = new TextPacket();
        pk.setType(TextPacket.Type.TRANSLATION);
        pk.setXuid(networkComponent.getXUID());
        pk.setNeedsTranslation(true);
        if (forceTranslatedByClient) {
            pk.setMessage(tr);
            pk.setParameters(List.of(args));
            networkComponent.sendPacket(pk);
            return;
        }
        var pair = I18n.get().toClientFriendlyStyle(tr, args);
        if (pair.right()) {
            pk.setMessage(pair.left());
            pk.setParameters(List.of(args));
            networkComponent.sendPacket(pk);
        } else {
            sendText(pair.left());
        }
    }

    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    @Override
    public void sendTr(String tr) {
        sendTr(tr, EMPTY_STRING_ARRAY);
    }

    @Override
    public boolean hasPerm(String perm) {
        return permTree.hasPerm(perm);
    }

    @Override
    public Permissible addPerm(String perm) {
        permTree.addPerm(perm);
        return this;
    }

    @Override
    public Permissible removePerm(String perm) {
        permTree.removePerm(perm);
        return this;
    }

    @Override
    public String getName() {
        return thisEntity.getOriginName();
    }
}
