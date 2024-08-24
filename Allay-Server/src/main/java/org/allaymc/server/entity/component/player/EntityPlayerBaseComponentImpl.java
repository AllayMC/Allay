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
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.annotation.OnInitFinish;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.component.event.CPlayerLoggedInEvent;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.component.player.EntityPlayerContainerHolderComponent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.init.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerGameTypeChangeEvent;
import org.allaymc.api.eventbus.event.player.PlayerItemHeldEvent;
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
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputMessage;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3i;
import org.joml.primitives.AABBf;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes.SCORE;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerBaseComponentImpl extends EntityBaseComponentImpl implements EntityPlayerBaseComponent {

    @Dependency
    protected EntityPlayerContainerHolderComponent containerHolderComponent;
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
    @Getter
    @Setter
    protected boolean usingItemOnBlock;
    protected long startUsingItemInAirTime = -1;
    protected AtomicInteger formIdCounter = new AtomicInteger(0);
    protected Map<Integer, Form> forms = new Int2ObjectOpenHashMap<>();
    protected Map<Integer, CustomForm> serverSettingForms = new Int2ObjectOpenHashMap<>();
    @Getter
    protected float movementSpeed = DEFAULT_MOVEMENT_SPEED;
    @ComponentedObject
    protected EntityPlayer thisPlayer;
    protected long nextSavePlayerDataTime = Integer.MAX_VALUE;

    public EntityPlayerBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @EventHandler
    protected void onPlayerLoggedIn(CPlayerLoggedInEvent event) {
        var loginData = networkComponent.getLoginData();
        skin = loginData.getSkin();
        setDisplayName(loginData.getDisplayName());
        uniqueId = loginData.getUuid().getMostSignificantBits();
    }

    @Override
    @OnInitFinish
    public void onInitFinish(EntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        permTree = PermTree.create();
        permTree.setOp(true);
        adventureSettings = new AdventureSettings(thisPlayer);
        abilities = new Abilities(thisPlayer);
        // Init adventure settings and abilities
        permTree.notifyAllPermListeners();
    }

    @Override
    protected void initMetadata() {
        super.initMetadata();
        // Player name is always shown
        metadata.set(EntityDataTypes.NAMETAG_ALWAYS_SHOW, (byte) 1);
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
        var event = new PlayerGameTypeChangeEvent(thisPlayer, this.gameType, gameType);
        event.call();
        if (event.isCancelled()) return;

        gameType = event.getNewGameType();

        this.gameType = gameType;

        this.fallDistance = 0;

        this.adventureSettings.applyGameType(gameType);
        this.abilities.applyGameType(gameType);

        setAndSendEntityFlag(EntityFlag.SILENT, gameType == GameType.SPECTATOR);
        setAndSendEntityFlag(EntityFlag.HAS_COLLISION, gameType != GameType.SPECTATOR);
        setHasGravity(gameType != GameType.SPECTATOR);

        var packet = new UpdatePlayerGameTypePacket();
        packet.setGameType(gameType);
        packet.setEntityId(runtimeId);
        networkComponent.sendPacket(packet);
        sendPacketToViewers(packet);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);

        tryPickUpItems();
        tickPlayerDataAutoSave();

        syncData();
    }

    @Override
    protected void onDie() {
        super.onDie();

        var respawnPacket = new RespawnPacket();
        respawnPacket.setPosition(org.cloudburstmc.math.vector.Vector3f.ZERO);
        respawnPacket.setState(RespawnPacket.State.SERVER_SEARCHING);
        networkComponent.sendPacket(respawnPacket);
    }

    protected void tickPlayerDataAutoSave() {
        // We use server's tick instead of world's tick
        // because player may teleport between worlds
        // and the tick in different worlds may not be same
        var currentServerTick = Server.getInstance().getTick();
        if (nextSavePlayerDataTime == Integer.MAX_VALUE) {
            nextSavePlayerDataTime = currentServerTick + Server.SETTINGS.storageSettings().playerDataAutoSaveCycle();
            return;
        }
        if (currentServerTick >= nextSavePlayerDataTime) {
            Server.getInstance().getPlayerStorage().savePlayerData(thisPlayer);
            nextSavePlayerDataTime = currentServerTick + Server.SETTINGS.storageSettings().playerDataAutoSaveCycle();
        }
    }

    protected void syncData() {
        abilities.sync();
        adventureSettings.sync();
    }

    protected void tryPickUpItems() {
        if (dead || !spawned || willBeDespawnedNextTick) return;

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
            if (item == null) continue; // Have been picked by others

            var inventory = Objects.requireNonNull(containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY));
            var slot = inventory.tryAddItem(item);
            if (slot == -1) continue;

            if (item.getCount() == 0) {
                var packet = new TakeItemEntityPacket();
                packet.setRuntimeEntityId(runtimeId);
                packet.setItemRuntimeEntityId(entityItem.getRuntimeId());
                Objects.requireNonNull(dimension.getChunkService().getChunkByLevelPos((int) location.x, (int) location.z)).sendChunkPacket(packet);
                // Set item to null to prevent others from picking this item twice
                entityItem.setItemStack(null);
                entityItem.despawn();
            }
            // Because of the new inventory system, the client will expect a transaction confirmation, but instead of doing that
            // It's much easier to just resend the inventory.
            thisPlayer.sendContentsWithSpecificContainerId(inventory, FixedContainerId.PLAYER_INVENTORY, slot);
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
            targetDim.getWorld().getWorldData().sendTime(thisPlayer);
            networkComponent.sendPacket(targetDim.getWorld().getWorldData().getGameRules().buildPacket());
        }
        location.dimension().removePlayer(thisPlayer, () -> {
            targetDim.getChunkService().getOrLoadChunkSync((int) target.x() >> 4, (int) target.z() >> 4);
            setLocationBeforeSpawn(target);
            sendLocationToSelf();
            if (currentDim.getDimensionInfo().dimensionId() != targetDim.getDimensionInfo().dimensionId()) {
                var packet = new ChangeDimensionPacket();
                packet.setDimension(targetDim.getDimensionInfo().dimensionId());
                packet.setPosition(MathUtils.JOMLVecToCBVec(target));
                networkComponent.sendPacket(packet);
                awaitingDimensionChangeACK = true;
            }
            targetDim.addPlayer(thisPlayer);
        });
    }

    @Override
    public void spawnTo(EntityPlayer player) {
        if (thisPlayer != player) {
            super.spawnTo(player);
        }
    }

    @Override
    public void despawnFrom(EntityPlayer player) {
        if (thisPlayer != player) {
            super.despawnFrom(player);
        }
    }

    @Override
    public void broadcastMoveToViewers(Location3fc newLoc, boolean teleporting) {
        var loc = new Location3f(newLoc);
        // base offset seems not being used in move packet
        // loc.add(0, getBaseOffset(), 0f);

        if (!teleporting) {
            var exhaust = 0f;
            var distance = getLocation().distance(newLoc);
            if (isSwimming()) exhaust += 0.01f * distance;
            if (isSprinting()) exhaust += 0.1f * distance;
            thisPlayer.exhaust(exhaust);
        }

        super.broadcastMoveToViewers(loc, teleporting);
    }

    @Override
    public BedrockPacket createSpawnPacket() {
        var packet = new AddPlayerPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setUniqueEntityId(runtimeId);
        packet.setUuid(networkComponent.getLoginData().getUuid());
        packet.setUsername(networkComponent.getOriginName());
        packet.setPlatformChatId(networkComponent.getLoginData().getDeviceInfo().deviceId());
        packet.setPosition(org.cloudburstmc.math.vector.Vector3f.from(location.x(), location.y(), location.z()));
        packet.setMotion(org.cloudburstmc.math.vector.Vector3f.from(motion.x(), motion.y(), motion.z()));
        packet.setRotation(org.cloudburstmc.math.vector.Vector3f.from(location.pitch(), location.yaw(), location.headYaw()));
        packet.setGameType(gameType);
        packet.getMetadata().putAll(metadata.getEntityDataMap());
        packet.setDeviceId(networkComponent.getLoginData().getDeviceInfo().deviceId());
        packet.setHand(containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand().toNetworkItemData());
        return packet;
    }

    public long getStartUsingItemInAirTime() {
        if (!isUsingItemInAir()) {
            log.warn("Trying to get a player's start action time who doesn't have action!");
        }
        return startUsingItemInAirTime;
    }

    @Override
    public long getItemUsingInAirTime(long currentTime) {
        return currentTime - startUsingItemInAirTime;
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

        new PlayerItemHeldEvent(thisPlayer, itemStack, handSlot).call();

        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setItem(itemStack.toNetworkItemData());
        packet.setInventorySlot(handSlot);
        packet.setHotbarSlot(handSlot);

        sendPacketToViewers(packet);
    }

    @Override
    public void setSkin(Skin skin) {
        this.skin = skin;

        var packet = new PlayerSkinPacket();
        packet.setUuid(networkComponent.getLoginData().getUuid());
        packet.setSkin(skin.toNetwork());
        packet.setNewSkinName(skin.getSkinId());
        // It seems that old skin name is unused
        packet.setOldSkinName("");
        packet.setTrustedSkin(true);

        var server = Server.getInstance();
        server.broadcastPacket(packet);
        server.onSkinUpdate(thisPlayer);
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
        nbt.listenForCompound("Perm", permNbt -> permTree.loadNBT(permNbt, true));
        nbt.listenForList("Offhand", NbtType.COMPOUND, offhandNbt ->
                containerHolderComponent.getContainer(FullContainerType.OFFHAND).loadNBT(offhandNbt)
        );
        nbt.listenForList("Inventory", NbtType.COMPOUND, inventoryNbt ->
                containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).loadNBT(inventoryNbt)
        );
        nbt.listenForList("Armor", NbtType.COMPOUND, armorNbt ->
                containerHolderComponent.getContainer(FullContainerType.ARMOR).loadNBT(armorNbt)
        );
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs) {
        if (sender == thisPlayer) {
            var packet = new CommandOutputPacket();
            packet.setType(CommandOutputType.ALL_OUTPUT);
            packet.setCommandOriginData(sender.getCommandOriginData());
            for (var output : outputs) {
                packet.getMessages().add(new CommandOutputMessage(
                        status != CommandResult.FAIL_STATUS, // Indicates if the output message was one of a successful command execution
                        I18n.get().tr(thisPlayer.getLangCode(), output.str(), output.args()),
                        Utils.EMPTY_STRING_ARRAY));
            }
            packet.setSuccessCount(status);
            networkComponent.sendPacket(packet);
        } else {
            for (var output : outputs) {
                var str = TextFormat.GRAY + "" + TextFormat.ITALIC + "[" + sender.getCommandSenderName() + ": " + I18n.get().tr(thisPlayer.getLangCode(), output.str(), output.args()) + "]";
                sendText(str);
            }
        }
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
                .spawnPoint(new Vector3i(spawnPoint.x(), spawnPoint.y(), spawnPoint.z()))
                .spawnPointWorldName(spawnPoint.dimension().getWorld().getWorldData().getName())
                .spawnPointDimensionId(spawnPoint.dimension().getDimensionInfo().dimensionId())
                .build();
    }

    @Override
    public void sendLocationToSelf() {
        networkComponent.sendPacket(createMovePacket(location, true));
    }

    @Override
    public boolean isUsingItemInAir() {
        return getMetadata().get(EntityFlag.USING_ITEM);
    }

    @Override
    public void setUsingItemInAir(boolean value, long time) {
        setAndSendEntityFlag(EntityFlag.USING_ITEM, value);
        if (value) {
            startUsingItemInAirTime = time;
        }
    }

    @Override
    public void sendText(String text) {
        sendSimpleMessage(text, TextPacket.Type.RAW);
    }

    protected void sendSimpleMessage(String message, TextPacket.Type type) {
        var packet = new TextPacket();
        packet.setType(type);
        packet.setXuid(networkComponent.getLoginData().getXuid());
        packet.setMessage(message);
        networkComponent.sendPacket(packet);
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
        var packet = new NetworkChunkPublisherUpdatePacket();
        var loc = getLocation();
        packet.setPosition(org.cloudburstmc.math.vector.Vector3i.from(loc.x(), loc.y(), loc.z()));
        packet.setRadius(getChunkLoadingRadius() << 4);
        networkComponent.sendPacket(packet);
    }

    @Override
    public void onChunkInRangeSent(Chunk chunk) {
        if (awaitingDimensionChangeACK) {
            sendDimensionChangeSuccess();
        }
        chunk.spawnEntitiesTo(thisPlayer);
        networkComponent.onChunkInRangeSent();
    }

    @Override
    public void sendDimensionChangeSuccess() {
        var packet = new PlayerActionPacket();
        packet.setAction(PlayerActionType.DIMENSION_CHANGE_SUCCESS);
        packet.setRuntimeEntityId(runtimeId);
        packet.setBlockPosition(org.cloudburstmc.math.vector.Vector3i.ZERO);
        packet.setResultPosition(org.cloudburstmc.math.vector.Vector3i.ZERO);
        networkComponent.sendPacket(packet);
        awaitingDimensionChangeACK = false;
    }

    @Override
    public void spawnEntity(Entity entity) {
        entity.spawnTo(thisPlayer);
    }

    @Override
    public void despawnEntity(Entity entity) {
        entity.despawnFrom(thisPlayer);
    }

    @Override
    public void onChunkOutOfRange(Set<Long> chunkHashes) {
        chunkHashes.stream()
                .map(location.dimension.getChunkService()::getChunk)
                .filter(Objects::nonNull)
                .forEach(chunk -> {
                    chunk.despawnEntitiesFrom(thisPlayer);
                    chunk.removeChunkLoader(thisPlayer);
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
            var packet = new TextPacket();
            packet.setType(TextPacket.Type.TRANSLATION);
            packet.setXuid("");
            packet.setNeedsTranslation(true);
            packet.setMessage(key);
            packet.setParameters(List.of(args));
            networkComponent.sendPacket(packet);
        } else sendText(I18n.get().tr(thisPlayer.getLangCode(), key, args));
    }

    @Override
    public String getCommandSenderName() {
        return thisPlayer.getOriginName();
    }

    @Override
    public void applyEntityEvent(EntityEventType event, int data) {
        var packet = new EntityEventPacket();
        packet.setRuntimeEntityId(getRuntimeId());
        packet.setType(event);
        packet.setData(data);
        sendPacketToViewers(packet);
        // Player should also send the packet to itself
        networkComponent.sendPacket(packet);
    }

    @Override
    public void applyAnimation(AnimatePacket.Action action, float rowingTime) {
        var packet = new AnimatePacket();
        packet.setRuntimeEntityId(getRuntimeId());
        packet.setAction(action);
        packet.setRowingTime(rowingTime);
        sendPacketToViewers(packet);
        // Player should also send the packet to itself
        networkComponent.sendPacket(packet);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public EntityPlayer asPlayer() {
        return thisPlayer;
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
        if (forms.size() > 100) {
            networkComponent.disconnect("Possible DoS vulnerability: More Than 100 FormWindow sent to client already.");
        }
        var packet = new ModalFormRequestPacket();
        var id = assignFormId();
        packet.setFormId(id);
        packet.setFormData(form.toJson());
        forms.putIfAbsent(id, form);
        networkComponent.sendPacket(packet);
    }

    @Override
    public void closeAllForms() {
        networkComponent.sendPacket(new ClientboundCloseFormPacket());
        forms.clear();
    }

    @Override
    public void setMovementSpeed(float speed) {
        movementSpeed = speed;
        attributeComponent.setAttribute(AttributeType.MOVEMENT_SPEED, movementSpeed);
        // NOTICE: abilities.setWalkSpeed(speed) shouldn't be called otherwise player can't sprint
    }

    protected int assignFormId() {
        return formIdCounter.getAndIncrement();
    }

    @Override
    public void displayScoreboard(Scoreboard scoreboard, DisplaySlot slot) {
        var setDisplayObjectivePacket = new SetDisplayObjectivePacket();
        setDisplayObjectivePacket.setDisplaySlot(slot.getSlotName());
        setDisplayObjectivePacket.setObjectiveId(scoreboard.getObjectiveName());
        setDisplayObjectivePacket.setDisplayName(scoreboard.getDisplayName());
        setDisplayObjectivePacket.setCriteria(scoreboard.getCriteriaName());
        setDisplayObjectivePacket.setSortOrder(scoreboard.getSortOrder().ordinal());
        networkComponent.sendPacket(setDisplayObjectivePacket);

        //client won't storage the score of a scoreboard,so we should send the score to client
        var setScorePacket = new SetScorePacket();
        setScorePacket.setInfos(
                scoreboard.getLines().values()
                        .stream()
                        .map(ScoreboardLine::toNetworkInfo)
                        .filter(Objects::nonNull)
                        .toList()
        );
        setScorePacket.setAction(SetScorePacket.Action.SET);
        networkComponent.sendPacket(setScorePacket);

        var scorer = new PlayerScorer(thisPlayer);
        var line = scoreboard.getLine(scorer);
        if (slot == DisplaySlot.BELOW_NAME && line != null) {
            setAndSendEntityData(SCORE, line.getScore() + " " + scoreboard.getDisplayName());
        }
    }

    @Override
    public void hideScoreboardSlot(DisplaySlot slot) {
        var packet = new SetDisplayObjectivePacket();
        packet.setDisplaySlot(slot.getSlotName());
        packet.setObjectiveId("");
        packet.setDisplayName("");
        packet.setCriteria("");
        packet.setSortOrder(SortOrder.ASCENDING.ordinal());
        networkComponent.sendPacket(packet);

        if (slot == DisplaySlot.BELOW_NAME) {
            setAndSendEntityData(SCORE, "");
        }
    }

    @Override
    public void removeScoreboard(Scoreboard scoreboard) {
        var packet = new RemoveObjectivePacket();
        packet.setObjectiveId(scoreboard.getObjectiveName());

        networkComponent.sendPacket(packet);
    }

    @Override
    public void removeScoreboardLine(ScoreboardLine line) {
        var packet = new SetScorePacket();
        packet.setAction(SetScorePacket.Action.REMOVE);
        var networkInfo = line.toNetworkInfo();
        if (networkInfo != null)
            packet.getInfos().add(networkInfo);
        networkComponent.sendPacket(packet);

        var scorer = new PlayerScorer(thisPlayer);
        if (line.getScorer().equals(scorer) && line.getScoreboard().getViewers(DisplaySlot.BELOW_NAME).contains(thisPlayer)) {
            setAndSendEntityData(SCORE, "");
        }
    }

    @Override
    public void updateScore(ScoreboardLine line) {
        var packet = new SetScorePacket();
        packet.setAction(SetScorePacket.Action.SET);
        var networkInfo = line.toNetworkInfo();
        if (networkInfo != null)
            packet.getInfos().add(networkInfo);
        networkComponent.sendPacket(packet);

        var scorer = new PlayerScorer(thisPlayer);
        if (line.getScorer().equals(scorer) && line.getScoreboard().getViewers(DisplaySlot.BELOW_NAME).contains(this)) {
            setAndSendEntityData(SCORE, line.getScore() + " " + line.getScoreboard().getDisplayName());
        }
    }

    @Override
    public boolean isScoreboardViewerValid() {
        return !networkComponent.isDisconnected();
    }

    @Override
    protected void sendMobEffectPacket(MobEffectPacket packet) {
        super.sendMobEffectPacket(packet);
        networkComponent.sendPacket(packet);
    }

    @Override
    public void setAbsorption(float absorption) {
        if (this.absorption == absorption) return;
        super.setAbsorption(absorption);
        attributeComponent.setAttribute(AttributeType.ABSORPTION, absorption);
    }

    @Override
    public void knockback(Vector3fc source, float kb, boolean ignoreKnockbackResistance) {
        var kbMotion = calculateKnockbackMotion(source, kb, ignoreKnockbackResistance);
        setMotion(kbMotion);
    }

    @Override
    public void setMotionValueOnly(Vector3fc motion) {
        this.motion = new Vector3f(motion);
    }

    @Override
    public void setMotion(Vector3fc motion) {
        // For player, motion effect is calculated by the client rather than the server
        // We only need to send SetEntityMotionPacket to client when
        // we want to apply motion on a player
        var packet = new SetEntityMotionPacket();
        packet.setMotion(MathUtils.JOMLVecToCBVec(motion));
        packet.setRuntimeEntityId(runtimeId);
        networkComponent.sendPacket(packet);
    }

    @Override
    public void sendEntityData(EntityDataType<?>... dataTypes) {
        super.sendEntityData(dataTypes);
        networkComponent.sendPacket(createSetEntityDataPacket(dataTypes, new EntityFlag[0]));
    }
}
