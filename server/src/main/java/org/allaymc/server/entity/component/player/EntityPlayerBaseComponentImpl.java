package org.allaymc.server.entity.component.player;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.Pair;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.data.Abilities;
import org.allaymc.api.client.data.AdventureSettings;
import org.allaymc.api.client.storage.PlayerData;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.UnopenedContainerId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.component.player.EntityPlayerContainerHolderComponent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.*;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.permission.PermissionGroup;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.data.SortOrder;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayNbtUtils;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.server.client.service.AllayPlayerService;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CPlayerGameTypeChangeEvent;
import org.allaymc.server.entity.component.event.CPlayerJumpEvent;
import org.allaymc.server.entity.component.event.CPlayerLoggedInEvent;
import org.allaymc.server.entity.component.event.CPlayerMoveEvent;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.world.AllayWorld;
import org.allaymc.server.world.gamerule.AllayGameRules;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputMessage;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.data.skin.SerializedSkin;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jctools.maps.NonBlockingHashMap;
import org.jetbrains.annotations.Range;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes.SCORE;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerBaseComponentImpl extends EntityBaseComponentImpl implements EntityPlayerBaseComponent {

    protected static final String TAG_PERMISSION = "Permission";
    protected static final String TAG_OFFHAND = "Offhand";
    protected static final String TAG_INVENTORY = "Inventory";
    protected static final String TAG_ARMOR = "Armor";
    protected static final String TAG_ENCHANTMENT_SEED = "EnchantmentSeed";
    protected static final String TAG_GAME_TYPE = "GameType";
    protected static final String TAG_SPAWN_POINT = "SpawnPoint";
    protected static final String TAG_WORLD = "World";
    protected static final String TAG_DIMENSION = "Dimension";
    protected static final String TAG_ENDER_ITEMS = "EnderItems";

    @Dependency
    protected EntityPlayerContainerHolderComponent containerHolderComponent;
    @Dependency
    protected EntityPlayerNetworkComponent networkComponent;

    @Getter
    protected GameType gameType;
    @Getter
    protected SerializedSkin skin;
    @Getter
    protected AdventureSettings adventureSettings;
    @Getter
    protected Abilities abilities;
    @Getter
    protected int chunkLoadingRadius;
    @Getter
    @Setter
    protected int chunkMaxSendCountPerTick;
    protected CommandOriginData commandOriginData;
    protected Location3ic spawnPoint;
    protected boolean awaitingDimensionChangeACK;
    protected boolean requireResendingAvailableCommands;
    @Getter
    @Setter
    protected boolean usingItemOnBlock;
    /**
     * expectedTeleportPos is used to solve the desynchronization of data at both ends.
     * Because PlayerAuthInputPacket will be sent from the client to the server at a rate of 20 per second.
     * After teleporting, the server still receives the PlayerAuthInputPacket sent by the client before teleporting.
     * The following is a simple simulation (initial player position is (0, 1000, 0)):
     * <p>
     * [C->S] Send PlayerAuthInputPacket with pos (0, 999, 0) `pk1`                           <br>
     * [S] Set player pos to ground (0, 100, 0) without fall distance calculation             <br>
     * [S->C] Send new pos (0, 100, 0) `pk2`                                                  <br>
     * [S] Receive `pk1`, set player pos to (0, 999 ,0)                                       <br>
     * [C] Receive `pk2`, set player pos to (0, 100, 0)                                       <br>
     * [C->S] Send PlayerAuthInputPacket with pos (0, 100, 0) `pk3`                           <br>
     * [S] Receive `pk3`, set player pos from (0, 999, 0) to (0, 100, 0), deltaY=899 -> death
     *
     * @see <a href="https://github.com/AllayMC/Allay/issues/517">teleport method should reset fall distance</a>
     */
    @Getter
    @Setter
    protected Vector3dc expectedTeleportPos;
    // Set enchantment seed to a random value
    // and if player has enchantment seed previously,
    // this random value will be covered
    @Getter
    @Setter
    protected int enchantmentSeed;
    protected long startUsingItemInAirTime;
    protected AtomicInteger formIdCounter;
    protected Map<Integer, Form> forms;
    protected Map<String, Long> cooldowns;
    protected CustomForm serverSettingForm;
    protected int serverSettingFormId;
    @Getter
    protected float movementSpeed;
    @ComponentObject
    protected EntityPlayer thisPlayer;
    protected long nextSavePlayerDataTime;

    public EntityPlayerBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.gameType = Server.SETTINGS.genericSettings().defaultGameType();
        this.chunkLoadingRadius = Server.SETTINGS.worldSettings().viewDistance();
        this.chunkMaxSendCountPerTick = Server.SETTINGS.worldSettings().chunkMaxSendCountPerTick();
        this.enchantmentSeed = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        this.startUsingItemInAirTime = -1;
        this.formIdCounter = new AtomicInteger(0);
        this.forms = new NonBlockingHashMap<>();
        this.cooldowns = new NonBlockingHashMap<>();
        this.serverSettingFormId = -1;
        this.movementSpeed = DEFAULT_MOVEMENT_SPEED;
        this.nextSavePlayerDataTime = Integer.MAX_VALUE;
    }

    @EventHandler
    protected void onPlayerLoggedIn(CPlayerLoggedInEvent event) {
        var loginData = networkComponent.getLoginData();
        this.skin = loginData.getSkin();
        this.uniqueId = loginData.getUuid().getMostSignificantBits();
        setDisplayName(loginData.getXname());
    }

    @Override
    protected void initPermissionGroup() {
        this.adventureSettings = new AdventureSettings(thisPlayer);
        this.abilities = new Abilities(thisPlayer);
        // Do not register player's permission group
        this.permissionGroup = PermissionGroup.create("Permission group for player " + runtimeId, Set.of(), null, false);
        // Set parent permission group alone, so that adventure settings and abilities will also be updated
        this.permissionGroup.setParent(PermissionGroup.get(Server.SETTINGS.genericSettings().defaultPermission().name()), thisPlayer);
    }

    @Override
    protected void initMetadata() {
        super.initMetadata();
        // Player name is always shown
        this.metadata.set(EntityDataTypes.NAMETAG_ALWAYS_SHOW, (byte) 1);
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
        if (!event.call()) {
            return;
        }

        gameType = event.getNewGameType();
        this.gameType = gameType;
        this.manager.callEvent(new CPlayerGameTypeChangeEvent(gameType));
        this.adventureSettings.applyGameType(gameType);
        this.abilities.applyGameType(gameType);

        setAndSendEntityFlag(EntityFlag.SILENT, gameType == GameType.SPECTATOR);
        setAndSendEntityFlag(EntityFlag.HAS_COLLISION, gameType != GameType.SPECTATOR);

        var packet = new UpdatePlayerGameTypePacket();
        packet.setGameType(gameType);
        packet.setEntityId(runtimeId);
        networkComponent.sendPacket(packet);
        sendPacketToViewers(packet);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);

        tryPickUpEntities();
        tickPlayerDataAutoSave();

        syncData();
    }

    @Override
    public void requireResendingAvailableCommands() {
        this.requireResendingAvailableCommands = true;
    }

    @Override
    public void setCooldown(String category, @Range(from = 0, to = Integer.MAX_VALUE) int duration, boolean send) {
        this.cooldowns.put(category, getWorld().getTick() + duration);
        if (send) {
            var packet = new PlayerStartItemCooldownPacket();
            packet.setItemCategory(category);
            packet.setCooldownDuration(duration);
            this.networkComponent.sendPacket(packet);
        }
    }

    @Override
    public boolean isCooldownEnd(String category) {
        var coolDown = cooldowns.get(category);
        if (coolDown == null) {
            return true;
        }

        return coolDown < getWorld().getTick();
    }

    @Override
    protected void computeAndNotifyCollidedBlocks() {
        if (abilities.has(Ability.NO_CLIP)) {
            return;
        }

        super.computeAndNotifyCollidedBlocks();
    }

    @Override
    protected void onDie() {
        super.onDie();

        var respawnPacket = new RespawnPacket();
        respawnPacket.setPosition(Vector3f.ZERO);
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
            Server.getInstance().getPlayerService().getPlayerStorage().savePlayerData(thisPlayer);
            nextSavePlayerDataTime = currentServerTick + Server.SETTINGS.storageSettings().playerDataAutoSaveCycle();
        }
    }

    protected void syncData() {
        // These data are checked every tick, and are sent to client if changed
        // We don't send these data immediately after changed, because they may be changed multiple times in a tick
        // and sending these data will take up a lot of bandwidth
        abilities.sync();
        adventureSettings.sync();

        if (requireResendingAvailableCommands) {
            sendPacket(Registries.COMMANDS.encodeAvailableCommandsPacketFor(thisPlayer));
            requireResendingAvailableCommands = false;
        }
    }

    protected void tryPickUpEntities() {
        if (isDead() || !isSpawned() || willBeDespawnedNextTick() || !isCurrentChunkLoaded()) {
            return;
        }

        var dimension = location.dimension();
        var pickUpArea = new AABBd(
                location.x - 1.425,
                location.y - 1.425,
                location.z - 1.425,
                location.x + 1.425,
                location.y + 1.425,
                location.z + 1.425
        );

        // Pick up items
        var entityItems = dimension.getEntityService().getPhysicsService().computeCollidingEntities(pickUpArea, true)
                .stream()
                .filter(EntityItem.class::isInstance)
                .map(EntityItem.class::cast)
                .filter(EntityItemBaseComponent::canBePicked)
                .toList();
        for (var entityItem : entityItems) {
            var item = entityItem.getItemStack();
            if (item == null) {
                // Have been picked by others
                continue;
            }

            var inventory = Objects.requireNonNull(containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY));
            var slot = inventory.tryAddItem(item);
            if (slot == -1) {
                // Player's inventory is full and cannot pick up the item
                continue;
            }

            if (item.getCount() == 0) {
                sendPickUpPacket(entityItem);
                // Set item to null to prevent others from picking this item twice
                entityItem.setItemStack(null);
                entityItem.despawn();
            }
        }

        // Pick up arrows
        var entityArrows = dimension.getEntityService().getPhysicsService().computeCollidingEntities(pickUpArea, true)
                .stream()
                .filter(EntityArrow.class::isInstance)
                .map(EntityArrow.class::cast)
                .filter(arrow -> arrow.getMotion().lengthSquared() == 0)
                .toList();
        for (var entityArrow : entityArrows) {
            if (entityArrow.willBeDespawnedNextTick()) {
                // Have been picked by others
                continue;
            }

            if (!entityArrow.isShotByPlayer()) {
                // Arrow not shot by player can't be picked up and will keep existing
                continue;
            }

            if (entityArrow.getInfinityLevel() != 0 || entityArrow.isShotByCreativePlayer()) {
                // Arrow shot by infinity bow or shot by creative player can't be picked up
                entityArrow.despawn();
                continue;
            }

            var arrow = ItemTypes.ARROW.createItemStack(1);
            arrow.setPotionType(entityArrow.getPotionType());
            if (thisPlayer.getContainer(FullContainerType.PLAYER_INVENTORY).tryAddItem(arrow) != -1) {
                sendPickUpPacket(entityArrow);
                entityArrow.despawn();
            }
        }
    }

    protected void sendPickUpPacket(Entity entity) {
        var packet = new TakeItemEntityPacket();
        packet.setRuntimeEntityId(this.runtimeId);
        packet.setItemRuntimeEntityId(entity.getRuntimeId());
        getCurrentChunk().addChunkPacket(packet);
    }

    @Override
    protected void beforeTeleport(Location3dc target) {
        super.beforeTeleport(target);
        this.expectedTeleportPos = new Vector3d(target);
    }

    @Override
    protected void teleportInDimension(Location3dc target) {
        super.teleportInDimension(target);
        // For player, we also need to send move packet to client
        // However, there is no need to send motion packet as we are teleporting the player
        sendLocationToSelf();
    }

    @Override
    protected void teleportOverDimension(Location3dc target) {
        var currentDim = location.dimension();
        var targetDim = target.dimension();
        if (currentDim.getWorld() != targetDim.getWorld()) {
            // Send new world's time
            targetDim.getWorld().getWorldData().sendTimeOfDay(thisPlayer);
            // Send new world's game rules
            networkComponent.sendPacket(((AllayGameRules) targetDim.getWorld().getWorldData().getGameRules()).buildPacket());
            // Clear old world's weather
            ((AllayWorld) currentDim.getWorld()).clearWeather(thisPlayer);
            // Send new world's weather
            ((AllayWorld) targetDim.getWorld()).sendWeather(thisPlayer);
        }
        location.dimension().removePlayer(thisPlayer, () -> {
            setLocationBeforeSpawn(target);
            if (currentDim.getDimensionInfo().dimensionId() != targetDim.getDimensionInfo().dimensionId()) {
                awaitingDimensionChangeACK = true;
                var packet = new ChangeDimensionPacket();
                packet.setDimension(targetDim.getDimensionInfo().dimensionId());
                packet.setPosition(Vector3f.from(target.x(), target.y(), target.z()));
                packet.setRespawn(!thisPlayer.isAlive());
                networkComponent.sendPacket(packet);
            }
            targetDim.addPlayer(thisPlayer, this::sendLocationToSelf);
        });
    }

    @Override
    public void spawnTo(EntityPlayer player) {
        if (thisPlayer != player) {
            super.spawnTo(player);
            containerHolderComponent.getContainer(FullContainerType.ARMOR).sendArmorEquipmentPacketTo(player);
            containerHolderComponent.getContainer(FullContainerType.OFFHAND).sendEquipmentPacketTo(player);
            // Skin should be sent to the player
            // Otherwise player's skin will become steve
            // in other player's eyes after respawn
            player.sendPacket(createSkinPacket(skin));
        }
    }

    @Override
    public void despawnFrom(EntityPlayer player) {
        if (thisPlayer != player) {
            super.despawnFrom(player);
        }
    }

    @Override
    public void broadcastMoveToViewers(Location3dc newLoc, boolean teleporting) {
        super.broadcastMoveToViewers(newLoc, teleporting);
        if (!teleporting) {
            manager.callEvent(CPlayerMoveEvent.INSTANCE);
        }
    }

    @Override
    public BedrockPacket createSpawnPacket0() {
        var packet = new AddPlayerPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setUniqueEntityId(runtimeId);
        packet.setUuid(networkComponent.getLoginData().getUuid());
        packet.setUsername(networkComponent.getOriginName());
        packet.setPlatformChatId(networkComponent.getLoginData().getDeviceInfo().deviceId());
        packet.setPosition(Vector3f.from(location.x(), location.y(), location.z()));
        packet.setMotion(Vector3f.ZERO);
        packet.setRotation(Vector3f.from(location.pitch(), location.yaw(), location.headYaw()));
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
    public void setHandSlot(int handSlot, boolean sendToSelf) {
        Preconditions.checkArgument(handSlot >= 0 && handSlot <= 8);

        var inv = containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY);
        inv.setHandSlot(handSlot);
        var itemStack = inv.getItemStack(handSlot);

        new PlayerItemHeldEvent(thisPlayer, itemStack, handSlot).call();

        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setContainerId(UnopenedContainerId.PLAYER_INVENTORY);
        packet.setItem(itemStack.toNetworkItemData());
        packet.setInventorySlot(handSlot);
        packet.setHotbarSlot(handSlot);

        if (sendToSelf) {
            sendPacket(packet);
        }
        sendPacketToViewers(packet);
    }

    @Override
    public void setSkin(SerializedSkin skin) {
        this.skin = skin;
        var server = Server.getInstance();
        server.getPlayerService().broadcastPacket(createSkinPacket(skin));
        ((AllayPlayerService) server.getPlayerService()).onSkinUpdate(thisPlayer);
    }

    protected PlayerSkinPacket createSkinPacket(SerializedSkin skin) {
        var packet = new PlayerSkinPacket();
        packet.setUuid(networkComponent.getLoginData().getUuid());
        packet.setSkin(skin);
        packet.setNewSkinName(skin.getSkinId());
        // It seems that old skin name is unused
        packet.setOldSkinName("");
        packet.setTrustedSkin(true);
        return packet;
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putCompound(TAG_PERMISSION, permissionGroup.saveNBT())
                .putList(
                        TAG_OFFHAND,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(FullContainerType.OFFHAND).saveNBT())
                .putList(
                        TAG_INVENTORY,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).saveNBT())
                .putList(
                        TAG_ARMOR,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(FullContainerType.ARMOR).saveNBT())
                .putList(
                        TAG_ENDER_ITEMS,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(FullContainerType.ENDER_CHEST).saveNBT())
                .putInt(TAG_ENCHANTMENT_SEED, enchantmentSeed)
                .putInt(TAG_GAME_TYPE, gameType.ordinal())
                .putCompound(TAG_SPAWN_POINT, saveSpawnPoint())
                .build();
    }

    protected NbtMap saveSpawnPoint() {
        var builder = NbtMap.builder()
                .putString(TAG_WORLD, spawnPoint.dimension().getWorld().getWorldData().getDisplayName())
                .putInt(TAG_DIMENSION, spawnPoint.dimension().getDimensionInfo().dimensionId());
        AllayNbtUtils.writeVector3i(builder, EntityBaseComponentImpl.TAG_POS, spawnPoint);
        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForCompound(TAG_PERMISSION, permNbt -> permissionGroup.loadNBT(permNbt, thisPlayer));
        nbt.listenForList(TAG_OFFHAND, NbtType.COMPOUND, offhandNbt ->
                containerHolderComponent.getContainer(FullContainerType.OFFHAND).loadNBT(offhandNbt)
        );
        nbt.listenForList(TAG_INVENTORY, NbtType.COMPOUND, inventoryNbt ->
                containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).loadNBT(inventoryNbt)
        );
        nbt.listenForList(TAG_ARMOR, NbtType.COMPOUND, armorNbt ->
                containerHolderComponent.getContainer(FullContainerType.ARMOR).loadNBT(armorNbt)
        );
        nbt.listenForList(TAG_ENDER_ITEMS, NbtType.COMPOUND, enderItemsNbt ->
                containerHolderComponent.getContainer(FullContainerType.ENDER_CHEST).loadNBT(enderItemsNbt)
        );
        nbt.listenForInt(TAG_ENCHANTMENT_SEED, this::setEnchantmentSeed);
        nbt.listenForInt(TAG_GAME_TYPE, id -> setGameType(GameType.from(id)));
        if (nbt.containsKey(TAG_SPAWN_POINT)) {
            loadSpawnPoint(nbt.getCompound(TAG_SPAWN_POINT));
        } else {
            spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
        }
    }

    protected void loadSpawnPoint(NbtMap nbt) {
        var world = Server.getInstance().getWorldPool().getWorld(nbt.getString(TAG_WORLD));
        if (world == null) {
            spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
            return;
        }
        var dimension = world.getDimension(nbt.getInt(TAG_DIMENSION));
        if (dimension == null) {
            spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
            return;
        }
        var pos = AllayNbtUtils.readVector3i(nbt, EntityBaseComponentImpl.TAG_POS);
        spawnPoint = new Location3i(pos, 0, 0, 0, dimension);
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
                        I18n.get().tr(thisPlayer.getLoginData().getLangCode(), output.str(), output.args()),
                        Utils.EMPTY_STRING_ARRAY));
            }
            packet.setSuccessCount(status);
            networkComponent.sendPacket(packet);
        } else {
            for (var output : outputs) {
                var str = TextFormat.GRAY + "" + TextFormat.ITALIC + "[" + sender.getCommandSenderName() + ": " + I18n.get().tr(thisPlayer.getLoginData().getLangCode(), output.str(), output.args()) + "]";
                sendText(str);
            }
        }
    }

    @Override
    public CommandOriginData getCommandOriginData() {
        if (commandOriginData == null) {
            commandOriginData = new CommandOriginData(CommandOriginType.PLAYER, networkComponent.getLoginData().getUuid(), "", -1);
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
    public void sendToast(String title, String content) {
        ToastRequestPacket pk = new ToastRequestPacket();
        pk.setTitle(title);
        pk.setContent(content);
        this.sendPacket(pk);
    }

    @Override
    public void sendTitle(String title) {
        var pk = new SetTitlePacket();
        pk.setText(title);
        pk.setType(SetTitlePacket.Type.TITLE);
        pk.setXuid("");
        pk.setPlatformOnlineId("");
        this.sendPacket(pk);
    }

    @Override
    public void sendSubtitle(String subtitle) {
        var pk = new SetTitlePacket();
        pk.setText(subtitle);
        pk.setType(SetTitlePacket.Type.SUBTITLE);
        pk.setXuid("");
        pk.setPlatformOnlineId("");
        this.sendPacket(pk);
    }

    @Override
    public void sendActionBar(String actionBar) {
        var pk = new SetTitlePacket();
        pk.setText(actionBar);
        pk.setType(SetTitlePacket.Type.ACTIONBAR);
        pk.setXuid("");
        pk.setPlatformOnlineId("");
        this.sendPacket(pk);
    }

    @Override
    public void setTitleSettings(int fadeInTime, int duration, int fadeOutTime) {
        var pk = new SetTitlePacket();
        pk.setType(SetTitlePacket.Type.TIMES);
        pk.setFadeInTime(fadeInTime);
        pk.setFadeOutTime(fadeOutTime);
        pk.setStayTime(duration);
        this.sendPacket(pk);
    }

    @Override
    public void resetTitleSettings() {
        var pk = new SetTitlePacket();
        pk.setType(SetTitlePacket.Type.RESET);
        this.sendPacket(pk);
    }

    @Override
    public void clearTitle() {
        var pk = new SetTitlePacket();
        pk.setType(SetTitlePacket.Type.CLEAR);
        this.sendPacket(pk);
    }

    @Override
    public PlayerData savePlayerData() {
        return PlayerData.builder()
                .nbt(saveNBT())
                .world(getWorld().getWorldData().getDisplayName())
                .dimension(getDimension().getDimensionInfo().dimensionId())
                .build();
    }

    @Override
    public Location3ic validateAndGetSpawnPoint() {
        if (spawnPoint.dimension().getWorld().getState() != WorldState.RUNNING) {
            spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
        }
        return spawnPoint;
    }

    @Override
    public void setSpawnPoint(Location3ic spawnPoint) {
        if (spawnPoint.dimension().getWorld().getState() != WorldState.RUNNING) {
            log.warn("Trying to set spawn point to a world which is not running");
            return;
        }
        this.spawnPoint = spawnPoint;
    }

    public void sendLocationToSelf() {
        // NOTICE: do not use MovePlayerPacket. Sometimes this packet does not have any effect,
        // especially when teleporting player to another world or a far away place.
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
        return status.isSpawned();
    }

    @Override
    public void setChunkLoadingRadius(int radius) {
        chunkLoadingRadius = Math.min(radius, Server.SETTINGS.worldSettings().viewDistance());
        var chunkRadiusUpdatedPacket = new ChunkRadiusUpdatedPacket();
        chunkRadiusUpdatedPacket.setRadius(chunkLoadingRadius);
        networkComponent.sendPacket(chunkRadiusUpdatedPacket);
    }

    @Override
    public void onChunkPosChanged() {
        var packet = new NetworkChunkPublisherUpdatePacket();
        packet.setPosition(org.cloudburstmc.math.vector.Vector3i.from(location.x(), location.y(), location.z()));
        packet.setRadius(getChunkLoadingRadius() << 4);
        networkComponent.sendPacket(packet);
    }

    @Override
    public void onChunkInRangeSend(Chunk chunk) {
        if (awaitingDimensionChangeACK) {
            sendDimensionChangeSuccess();
        }
        // This method will be called in non-ticking thread if async chunk sending is enabled. Let's
        // send the entities in this chunk to the player next tick in the main thread: use forEachEntitiesInChunk()
        // instead of forEachEntitiesInChunkImmediately()
        getDimension().getEntityService().forEachEntitiesInChunk(chunk.getX(), chunk.getZ(), entity -> entity.spawnTo(thisPlayer));
        ((EntityPlayerNetworkComponentImpl) ((EntityPlayerImpl) thisPlayer).getPlayerNetworkComponent()).onChunkInRangeSend();
    }

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
    public void onChunkOutOfRange(Set<Long> chunkHashes) {
        for (var hash : chunkHashes) {
            getDimension().getEntityService().forEachEntitiesInChunk(HashUtils.getXFromHashXZ(hash), HashUtils.getZFromHashXZ(hash), entity -> entity.despawnFrom(thisPlayer));
        }
    }

    @Override
    public void sendPacket(BedrockPacket packet) {
        networkComponent.sendPacket(packet);
    }

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {
        networkComponent.sendPacketImmediately(packet);
    }

    @Override
    public void sendTr(String key, boolean forceTranslatedByClient, Object... args) {
        if (!forceTranslatedByClient) {
            sendText(I18n.get().tr(thisPlayer.getLoginData().getLangCode(), key, args));
            return;
        }

        var packet = new TextPacket();
        packet.setType(TextPacket.Type.TRANSLATION);
        packet.setXuid("");
        packet.setNeedsTranslation(true);
        packet.setMessage(key);
        packet.setParameters(List.of(Utils.objectArrayToStringArray(args)));
        networkComponent.sendPacket(packet);
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
    public void applyAction(AnimatePacket.Action action, double rowingTime) {
        var packet = new AnimatePacket();
        packet.setRuntimeEntityId(getRuntimeId());
        packet.setAction(action);
        packet.setRowingTime((float) rowingTime);
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
    public Pair<Integer, CustomForm> getServerSettingForm() {
        return Pair.of(serverSettingFormId, serverSettingForm);
    }

    @Override
    public void setServerSettingForm(CustomForm form) {
        serverSettingFormId = assignFormId();
        serverSettingForm = form;
    }

    @Override
    public void removeServerSettingForm() {
        serverSettingForm = null;
        serverSettingFormId = -1;
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
        attributeComponent.setAttributeValue(AttributeType.MOVEMENT_SPEED, movementSpeed);
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
    public void setLocationBeforeSpawn(Location3dc location) {
        if (this.location.dimension() != null && location.dimension() == null) {
            // Different from normal entity, reset the dimension of player entity back to null is not allowed
            throw new IllegalArgumentException("Reset dimension back to null is not allowed for player!");
        }

        super.setLocationBeforeSpawn(location);
    }

    @Override
    public void sendMetadata() {
        super.sendMetadata();
        networkComponent.sendPacket(createSetEntityDataPacket());
    }

    public void onJump() {
        new PlayerJumpEvent(thisPlayer).call();
        manager.callEvent(CPlayerJumpEvent.INSTANCE);
    }

    @Override
    public void setSprinting(boolean sprinting) {
        if (sprinting == isSprinting()) return;

        new PlayerToggleSprintEvent(thisPlayer, sprinting).call();

        var speed = getMovementSpeed();
        if (sprinting) speed *= 1.3f;
        else speed /= 1.3f;
        setMovementSpeed(speed);
        setAndSendEntityFlag(EntityFlag.SPRINTING, sprinting);
    }

    @Override
    public void setSneaking(boolean sneaking) {
        if (sneaking == isSneaking()) return;

        new PlayerToggleSneakEvent(thisPlayer, sneaking).call();

        setAndSendEntityFlag(EntityFlag.SNEAKING, sneaking);
    }

    @Override
    public void setSwimming(boolean swimming) {
        if (swimming == isSwimming()) return;

        new PlayerToggleSwimEvent(thisPlayer, swimming).call();

        setAndSendEntityFlag(EntityFlag.SWIMMING, swimming);
    }

    @Override
    public void setGliding(boolean gliding) {
        if (gliding == isGliding()) return;

        new PlayerToggleGlideEvent(thisPlayer, gliding).call();

        setAndSendEntityFlag(EntityFlag.GLIDING, gliding);
    }

    @Override
    public void setCrawling(boolean crawling) {
        if (crawling == isCrawling()) return;

        new PlayerToggleCrawlEvent(thisPlayer, crawling).call();

        setAndSendEntityFlag(EntityFlag.CRAWLING, crawling);
    }

    public boolean isAwaitingTeleportACK() {
        return expectedTeleportPos != null;
    }

    public void ackTeleported() {
        this.expectedTeleportPos = null;
    }
}
