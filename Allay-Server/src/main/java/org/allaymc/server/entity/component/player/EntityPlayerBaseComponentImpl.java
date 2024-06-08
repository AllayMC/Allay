package org.allaymc.server.entity.component.player;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.data.Abilities;
import org.allaymc.api.client.data.AdventureSettings;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.client.storage.PlayerData;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.common.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.event.PlayerLoggedInEvent;
import org.allaymc.api.entity.component.item.EntityItemBaseComponent;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.init.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.data.SortOrder;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.MathUtils;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.server.entity.component.common.EntityBaseComponentImpl;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputMessage;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.joml.primitives.AABBf;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes.SCORE;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerBaseComponentImpl extends EntityBaseComponentImpl<EntityPlayer> implements EntityPlayerBaseComponent {

    @Dependency
    protected EntityContainerHolderComponent containerHolderComponent;
    @Dependency
    protected EntityPlayerNetworkComponent networkComponent;
    @Getter
    protected GameType gameType = GameType.CREATIVE;
    @Getter
    protected Skin skin;
    @Getter
    protected AdventureSettings adventureSettings;
    @Getter
    protected Abilities abilities;
    @Getter
    protected PermTree permTree;
    @Getter
    protected int chunkLoadingRadius = Server.SETTINGS.worldSettings().viewDistance();
    @Getter
    @Setter
    protected int chunkTrySendCountPerTick = Server.SETTINGS.worldSettings().chunkTrySendCountPerTick();
    protected CommandOriginData commandOriginData;
    @Getter
    @Setter
    protected Location3ic spawnPoint;
    protected boolean awaitingDimensionChangeACK;
    protected AtomicInteger formIdCounter = new AtomicInteger(0);
    protected Map<Integer, Form> forms = new Int2ObjectOpenHashMap<>();
    protected Map<Integer, CustomForm> serverSettingForms = new Int2ObjectOpenHashMap<>();

    public EntityPlayerBaseComponentImpl(EntityInitInfo<EntityPlayer> info) {
        super(info);
    }

    @EventHandler
    protected void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        var loginData = networkComponent.getLoginData();
        skin = loginData.getSkin();
        setDisplayName(loginData.getDisplayName());
        uniqueId = loginData.getUuid().getMostSignificantBits();
    }

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        super.onInitFinish(initInfo);
        permTree = PermTree.create();
        permTree.setOp(true);
        adventureSettings = new AdventureSettings(thisEntity);
        abilities = new Abilities(thisEntity);
        // Init adventure settings and abilities
        permTree.notifyAllPermListeners();
    }

    @Override
    protected void saveUniqueId(NbtMapBuilder builder) {
        // Do nothing
    }

    @Override
    protected void loadUniqueId(NbtMap nbt) {
        // Do nothing, as the uniqueId will be set in method onPlayerLoggedIn()
    }

    @Override
    public void setGameType(GameType gameType) {
        this.gameType = gameType;

        this.fallDistance = 0;

        this.adventureSettings.applyGameType(gameType);
        this.abilities.applyGameType(gameType);

        setAndSendEntityFlag(EntityFlag.SILENT, gameType == GameType.SPECTATOR);
        setAndSendEntityFlag(EntityFlag.HAS_COLLISION, gameType != GameType.SPECTATOR);
        setHasGravity(gameType != GameType.SPECTATOR);

        var pk = new UpdatePlayerGameTypePacket();
        pk.setGameType(gameType);
        pk.setEntityId(runtimeId);
        networkComponent.sendPacket(pk);
        sendPacketToViewers(pk);
    }

    @Override
    public void tick() {
        super.tick();
        syncData();
        tryPickUpItems();
    }

    protected void syncData() {
        abilities.sync();
        adventureSettings.sync();
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
                .filter(EntityItem.class::isInstance)
                .map(EntityItem.class::cast)
                .filter(EntityItemBaseComponent::canBePicked)
                .toList();
        for (var entityItem : entityItems) {
            var item = entityItem.getItemStack();
            var inventory = Objects.requireNonNull(containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY));
            var slot = inventory.tryAddItem(item);
            if (slot != -1) {
                if (item.getCount() == 0) {
                    TakeItemEntityPacket takeItemEntityPacket = new TakeItemEntityPacket();
                    takeItemEntityPacket.setRuntimeEntityId(runtimeId);
                    takeItemEntityPacket.setItemRuntimeEntityId(entityItem.getRuntimeId());
                    Objects.requireNonNull(dimension.getChunkService().getChunkByLevelPos((int) location.x, (int) location.z)).sendChunkPacket(takeItemEntityPacket);
                    entityItem.setItemStack(null);
                    dimension.getEntityService().removeEntity(entityItem);
                }
                // Because of the new inventory system, the client will expect a transaction confirmation, but instead of doing that
                // It's much easier to just resend the inventory.
                thisEntity.sendContentsWithSpecificContainerId(inventory, FixedContainerId.PLAYER_INVENTORY, slot);
            }
        }
    }

    @Override
    protected void teleportInDimension(Location3fc target) {
        super.teleportInDimension(target);
        // For player, we also need to send move packet to client
        // However, there is no need to send motion packet as we are teleporting the player
        sendLocationToSelf();
    }

    @Override
    protected void teleportOverDimension(Location3fc target) {
        var currentDim = location.dimension();
        var targetDim = target.dimension();
        if (currentDim.getWorld() != targetDim.getWorld()) {
            targetDim.getWorld().sendTime(thisEntity);
            networkComponent.sendPacket(targetDim.getWorld().getWorldData().getGameRules().buildPacket());
        }
        this.location.dimension().removePlayer(thisEntity, () -> {
            targetDim.getChunkService().getOrLoadChunkSynchronously((int) target.x() >> 4, (int) target.z() >> 4);
            setLocation(target, false);
            sendLocationToSelf();
            if (currentDim.getDimensionInfo().dimensionId() != targetDim.getDimensionInfo().dimensionId()) {
                var changeDimensionPacket = new ChangeDimensionPacket();
                changeDimensionPacket.setDimension(targetDim.getDimensionInfo().dimensionId());
                changeDimensionPacket.setPosition(MathUtils.JOMLVecToCBVec(target));
                networkComponent.sendPacket(changeDimensionPacket);
                awaitingDimensionChangeACK = true;
            }
            targetDim.addPlayer(thisEntity);
        });
    }

    @Override
    public void spawnTo(EntityPlayer player) {
        if (thisEntity != player) super.spawnTo(player);
    }

    @Override
    public void despawnFrom(EntityPlayer player) {
        if (thisEntity != player) super.despawnFrom(player);
    }

    @Override
    public void broadcastMoveToViewers(Location3fc newLoc, boolean teleporting) {
        var loc = new Location3f(newLoc);
        loc.add(0, getBaseOffset(), 0f);
        super.broadcastMoveToViewers(loc, teleporting);
    }

    @Override
    public BedrockPacket createSpawnPacket() {
        var addPlayerPacket = new AddPlayerPacket();
        addPlayerPacket.setRuntimeEntityId(runtimeId);
        addPlayerPacket.setUniqueEntityId(runtimeId);
        addPlayerPacket.setUuid(networkComponent.getLoginData().getUuid());
        addPlayerPacket.setUsername(networkComponent.getOriginName());
        addPlayerPacket.setPlatformChatId(networkComponent.getLoginData().getDeviceInfo().getDeviceId());
        addPlayerPacket.setPosition(Vector3f.from(location.x(), location.y() + getBaseOffset(), location.z()));
        addPlayerPacket.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        addPlayerPacket.setRotation(Vector3f.from(location.pitch(), location.yaw(), location.headYaw()));
        addPlayerPacket.setGameType(gameType);
        addPlayerPacket.getMetadata().putAll(this.metadata.getEntityDataMap());
        addPlayerPacket.setDeviceId(networkComponent.getLoginData().getDeviceInfo().getDeviceId());
        addPlayerPacket.setHand(containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand().toNetworkItemData());
        return addPlayerPacket;
    }

    @Override
    public boolean isSprinting() {
        return metadata.get(EntityFlag.SPRINTING);
    }

    @Override
    public void setSprinting(boolean sprinting) {
        setAndSendEntityFlag(EntityFlag.SPRINTING, sprinting);
    }

    @Override
    public boolean isSneaking() {
        return metadata.get(EntityFlag.SNEAKING);
    }

    @Override
    public void setSneaking(boolean sneaking) {
        setAndSendEntityFlag(EntityFlag.SNEAKING, sneaking);
    }

    @Override
    public boolean isSwimming() {
        return metadata.get(EntityFlag.SWIMMING);
    }

    @Override
    public void setSwimming(boolean swimming) {
        setAndSendEntityFlag(EntityFlag.SWIMMING, swimming);
    }

    @Override
    public boolean isGliding() {
        return metadata.get(EntityFlag.GLIDING);
    }

    @Override
    public void setGliding(boolean gliding) {
        setAndSendEntityFlag(EntityFlag.GLIDING, gliding);
    }

    @Override
    public boolean isCrawling() {
        return metadata.get(EntityFlag.CRAWLING);
    }

    @Override
    public void setCrawling(boolean crawling) {
        setAndSendEntityFlag(EntityFlag.CRAWLING, crawling);
    }

    @Override
    public int getHandSlot() {
        return containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).getHandSlot();
    }

    @Override
    public void setHandSlot(int handSlot) {
        Preconditions.checkArgument(handSlot >= 0 && handSlot <= 8);
        var inv = containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY);
        inv.setHandSlot(handSlot);
        var itemStack = inv.getItemStack(handSlot);

        var mobEquipmentPacket = new MobEquipmentPacket();
        mobEquipmentPacket.setRuntimeEntityId(runtimeId);
        mobEquipmentPacket.setItem(itemStack.toNetworkItemData());
        mobEquipmentPacket.setInventorySlot(handSlot);
        mobEquipmentPacket.setHotbarSlot(handSlot);

        sendPacketToViewers(mobEquipmentPacket);
    }

    @Override
    public void setSkin(Skin skin) {
        this.skin = skin;
        var pk = new PlayerSkinPacket();
        pk.setUuid(networkComponent.getLoginData().getUuid());
        pk.setSkin(skin.toNetwork());
        pk.setNewSkinName(skin.getSkinId());
        // It seems that old skin name is unused
        pk.setOldSkinName("");
        pk.setTrustedSkin(true);
        var server = Server.getInstance();
        server.broadcastPacket(pk);
        server.onSkinUpdate(thisEntity);
    }

    @Override
    public boolean computeMovementServerSide() {
        // TODO: fake client
        return false;
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putCompound("Perm", permTree.saveNBT())
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
        if (nbt.containsKey("Perm")) {
            permTree.loadNBT(nbt.getCompound("Perm"), true);
        }
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
    public void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs) {
        var cmdOutputPk = new CommandOutputPacket();
        cmdOutputPk.setType(CommandOutputType.ALL_OUTPUT);
        cmdOutputPk.setCommandOriginData(sender.getCommandOriginData());
        for (var output : outputs) {
            cmdOutputPk.getMessages().add(new CommandOutputMessage(
                    status != CommandResult.FAIL_STATUS, // Indicates if the output message was one of a successful command execution
                    I18n.get().tr(thisEntity.getLangCode(), output.str(), output.args()),
                    Utils.EMPTY_STRING_ARRAY));
        }
        cmdOutputPk.setSuccessCount(status);
        networkComponent.sendPacket(cmdOutputPk);
    }

    @Override
    public CommandOriginData getCommandOriginData() {
        if (commandOriginData == null) {
            commandOriginData = new CommandOriginData(
                    CommandOriginType.PLAYER,
                    networkComponent.getLoginData().getUuid(),
                    "",
                    -1
            );
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
    public PlayerData savePlayerData() {
        return PlayerData.builder()
                .playerNBT(saveNBT())
                .currentWorldName(getWorld().getWorldData().getName())
                .currentDimensionId(getDimension().getDimensionInfo().dimensionId())
                .spawnPoint(new org.joml.Vector3i(spawnPoint.x(), spawnPoint.y(), spawnPoint.z()))
                .spawnPointWorldName(spawnPoint.dimension().getWorld().getWorldData().getName())
                .spawnPointDimensionId(spawnPoint.dimension().getDimensionInfo().dimensionId())
                .build();
    }

    @Override
    public void sendLocationToSelf() {
        networkComponent.sendPacket(createMovePacket(location, true));
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
        var loc = getLocation();
        chunkPublisherUpdatePacket.setPosition(Vector3i.from(loc.x(), loc.y(), loc.z()));
        chunkPublisherUpdatePacket.setRadius(getChunkLoadingRadius() << 4);
        networkComponent.sendPacket(chunkPublisherUpdatePacket);
    }

    @Override
    public void onChunkInRangeSent(Chunk chunk) {
        if (awaitingDimensionChangeACK) {
            sendDimensionChangeSuccess();
        }
        chunk.spawnEntitiesTo(thisEntity);
        networkComponent.onChunkInRangeSent();
    }

    @Override
    public void sendDimensionChangeSuccess() {
        var ackPk = new PlayerActionPacket();
        ackPk.setAction(PlayerActionType.DIMENSION_CHANGE_SUCCESS);
        ackPk.setRuntimeEntityId(runtimeId);
        ackPk.setBlockPosition(Vector3i.ZERO);
        ackPk.setResultPosition(Vector3i.ZERO);
        networkComponent.sendPacket(ackPk);
        awaitingDimensionChangeACK = false;
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
                    chunk.despawnEntitiesFrom(thisEntity);
                    chunk.removeChunkLoader(thisEntity);
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
    public void sendTr(String key, boolean forceTranslatedByClient, String... args) {
        if (forceTranslatedByClient) {
            var pk = new TextPacket();
            pk.setType(TextPacket.Type.TRANSLATION);
            pk.setXuid("");
            pk.setNeedsTranslation(true);
            pk.setMessage(key);
            pk.setParameters(List.of(args));
            networkComponent.sendPacket(pk);
        } else sendText(I18n.get().tr(thisEntity.getLangCode(), key, args));
    }

    @Override
    public String getCommandSenderName() {
        return thisEntity.getOriginName();
    }

    @Override
    public void applyEntityEvent(EntityEventType event, int data) {
        var pk = new EntityEventPacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setType(event);
        pk.setData(data);
        sendPacketToViewers(pk);
        // Player should also send the packet to itself
        networkComponent.sendPacket(pk);
    }

    @Override
    public void applyAnimation(AnimatePacket.Action action, float rowingTime) {
        var pk = new AnimatePacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setAction(action);
        pk.setRowingTime(rowingTime);
        sendPacketToViewers(pk);
        // Player should also send the packet to itself
        networkComponent.sendPacket(pk);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public EntityPlayer asPlayer() {
        return thisEntity;
    }

    @Override
    public Map<Integer, Form> getForms() {
        return Collections.unmodifiableMap(forms);
    }

    @Override
    public Form getForm(int id) {
        return forms.get(id);
    }

    @Override
    public Form removeForm(int id) {
        return forms.remove(id);
    }

    @Override
    public void addServerSettingForm(CustomForm form) {
        serverSettingForms.put(assignFormId(), form);
    }

    @Override
    public Map<Integer, Form> getServerSettingForms() {
        return Collections.unmodifiableMap(serverSettingForms);
    }

    @Override
    public CustomForm getServerSettingForm(int id) {
        return serverSettingForms.get(id);
    }

    @Override
    public CustomForm removeServerSettingForm(int id) {
        return serverSettingForms.remove(id);
    }

    @Override
    public void showForm(Form form) {
        if (this.forms.size() > 100) {
            networkComponent.disconnect("Possible DoS vulnerability: More Than 100 FormWindow sent to client already.");
        }
        var packet = new ModalFormRequestPacket();
        var id = assignFormId();
        packet.setFormId(id);
        packet.setFormData(form.toJson());
        forms.putIfAbsent(id, form);
        networkComponent.sendPacket(packet);
    }

    protected int assignFormId() {
        return formIdCounter.getAndIncrement();
    }

    @Override
    public void displayScoreboard(Scoreboard scoreboard, DisplaySlot slot) {
        SetDisplayObjectivePacket pk1 = new SetDisplayObjectivePacket();
        pk1.setDisplaySlot(slot.getSlotName());
        pk1.setObjectiveId(scoreboard.getObjectiveName());
        pk1.setDisplayName(scoreboard.getDisplayName());
        pk1.setCriteria(scoreboard.getCriteriaName());
        pk1.setSortOrder(scoreboard.getSortOrder().ordinal());
        networkComponent.sendPacket(pk1);

        //client won't storage the score of a scoreboard,so we should send the score to client
        SetScorePacket pk2 = new SetScorePacket();
        pk2.setInfos(scoreboard.getLines().values().stream().map(ScoreboardLine::toNetworkInfo).filter(Objects::nonNull).collect(Collectors.toList()));
        pk2.setAction(SetScorePacket.Action.SET);
        networkComponent.sendPacket(pk2);

        var scorer = new PlayerScorer(thisEntity);
        var line = scoreboard.getLine(scorer);
        if (slot == DisplaySlot.BELOW_NAME && line != null) {
            this.setAndSendEntityData(SCORE, line.getScore() + " " + scoreboard.getDisplayName());
        }
    }

    @Override
    public void hideScoreboardSlot(DisplaySlot slot) {
        SetDisplayObjectivePacket pk = new SetDisplayObjectivePacket();
        pk.setDisplaySlot(slot.getSlotName());
        pk.setObjectiveId("");
        pk.setDisplayName("");
        pk.setCriteria("");
        pk.setSortOrder(SortOrder.ASCENDING.ordinal());
        networkComponent.sendPacket(pk);

        if (slot == DisplaySlot.BELOW_NAME) {
            this.setAndSendEntityData(SCORE, "");
        }
    }

    @Override
    public void removeScoreboard(Scoreboard scoreboard) {
        RemoveObjectivePacket pk = new RemoveObjectivePacket();
        pk.setObjectiveId(scoreboard.getObjectiveName());

        networkComponent.sendPacket(pk);
    }

    @Override
    public void removeScoreboardLine(ScoreboardLine line) {
        SetScorePacket pk = new SetScorePacket();
        pk.setAction(SetScorePacket.Action.REMOVE);
        var networkInfo = line.toNetworkInfo();
        if (networkInfo != null)
            pk.getInfos().add(networkInfo);
        networkComponent.sendPacket(pk);

        var scorer = new PlayerScorer(thisEntity);
        if (line.getScorer().equals(scorer) && line.getScoreboard().getViewers(DisplaySlot.BELOW_NAME).contains(thisEntity)) {
            this.setAndSendEntityData(SCORE, "");
        }
    }

    @Override
    public void updateScore(ScoreboardLine line) {
        SetScorePacket packet = new SetScorePacket();
        packet.setAction(SetScorePacket.Action.SET);
        var networkInfo = line.toNetworkInfo();
        if (networkInfo != null)
            packet.getInfos().add(networkInfo);
        networkComponent.sendPacket(packet);

        var scorer = new PlayerScorer(thisEntity);
        if (line.getScorer().equals(scorer) && line.getScoreboard().getViewers(DisplaySlot.BELOW_NAME).contains(this)) {
            this.setAndSendEntityData(SCORE, line.getScore() + " " + line.getScoreboard().getDisplayName());
        }
    }

    @Override
    public boolean isScoreboardViewerValid() {
        return !networkComponent.isDisconnected();
    }
}
