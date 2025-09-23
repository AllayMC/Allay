package org.allaymc.server.entity.component.player;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.action.PickedUpAction;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.data.EntityData;
import org.allaymc.api.entity.data.EntityFlag;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.*;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.permission.PermissionGroup;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.PlayerData;
import org.allaymc.api.player.Skin;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayNbtUtils;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CPlayerGameModeChangeEvent;
import org.allaymc.server.entity.component.event.CPlayerJumpEvent;
import org.allaymc.server.entity.component.event.CPlayerLoggedInEvent;
import org.allaymc.server.entity.component.event.CPlayerMoveEvent;
import org.allaymc.server.player.Abilities;
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
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jctools.maps.NonBlockingHashMap;
import org.jetbrains.annotations.Range;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static org.allaymc.server.network.NetworkHelper.fromNetwork;
import static org.allaymc.server.network.NetworkHelper.toNetwork;

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
    protected EntityContainerHolderComponent containerHolderComponent;
    @Dependency
    protected EntityPlayerClientComponentImpl clientComponent;

    @Getter
    protected GameMode gameMode;
    @Getter
    protected Skin skin;
    @Getter
    protected Abilities abilities;
    protected Location3ic spawnPoint;
    protected boolean requireResendingCommands;
    @Getter
    @Setter
    protected boolean usingItemOnBlock;
    /**
     * expectedTeleportPos is used to solve the desynchronization of data at both ends.
     * Because PlayerAuthInputPacket will be sent from the client to the server at a rate of 20 per second.
     * After teleporting, the server still receives the PlayerAuthInputPacket sent by the client before teleporting.
     * The following is a simple simulation (initial player position is (0, 1000, 0)):
     * <p>
     * [C -> S] Send PlayerAuthInputPacket with pos (0, 999, 0) `pk1`                              <br>
     * [Server] Set player pos to ground (0, 100, 0) without fall distance calculation             <br>
     * [S -> C] Send new pos (0, 100, 0) `pk2`                                                     <br>
     * [Server] Receive `pk1`, set player pos to (0, 999 ,0)                                       <br>
     * [Client] Receive `pk2`, set player pos to (0, 100, 0)                                       <br>
     * [C -> S] Send PlayerAuthInputPacket with pos (0, 100, 0) `pk3`                              <br>
     * [Server] Receive `pk3`, set player pos from (0, 999, 0) to (0, 100, 0), deltaY=899 -> death
     *
     * @see <a href="https://github.com/AllayMC/Allay/issues/517">Teleport method should reset fall distance</a>
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
        this.gameMode = Server.SETTINGS.genericSettings().defaultGameMode();
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
        var loginData = clientComponent.getLoginData();
        this.skin = loginData.getSkin();
        this.uniqueId = loginData.getUuid().getMostSignificantBits();
        setDisplayName(loginData.getXname());
    }

    @Override
    protected void initPermissionGroup() {
        this.abilities = new Abilities(thisPlayer);
        // Do not register player's permission group
        this.permissionGroup = PermissionGroup.create("Permission group for player " + runtimeId, Set.of(), Set.of(), false);
        // Add parent permission group alone, so that adventure settings and abilities will also be updated
        this.permissionGroup.addParent(PermissionGroup.get(Server.SETTINGS.genericSettings().defaultPermission()), thisPlayer);
    }

    @Override
    protected void initMetadata() {
        super.initMetadata();
        // Player name is always shown
        setData(EntityData.NAMETAG_ALWAYS_SHOW, (byte) 1);
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
    public void setGameMode(GameMode gameMode) {
        setGameMode(gameMode, false);
    }

    protected void setGameMode(GameMode gameMode, boolean internal) {
        if (!internal) {
            var event = new PlayerGameModeChangeEvent(thisPlayer, this.gameMode, gameMode);
            if (!event.call() || this.gameMode == event.getNewGameMode()) {
                return;
            }

            gameMode = event.getNewGameMode();
        }

        this.gameMode = gameMode;
        this.manager.callEvent(new CPlayerGameModeChangeEvent(this.gameMode));
        this.abilities.applyGameMode(this.gameMode);

        setFlag(EntityFlag.SILENT, this.gameMode == GameMode.SPECTATOR);
        setFlag(EntityFlag.HAS_COLLISION, this.gameMode != GameMode.SPECTATOR);

        thisPlayer.viewPlayerGameMode(thisPlayer);
        forEachViewers(viewer -> viewer.viewPlayerGameMode(thisPlayer));
    }

    @Override
    public void setFlySpeed(float flySpeed) {
        this.abilities.setFlySpeed(flySpeed);
    }

    @Override
    public void setVerticalFlySpeed(float verticalFlySpeed) {
        this.abilities.setVerticalFlySpeed(verticalFlySpeed);
    }

    @Override
    public boolean isFlying() {
        return this.abilities.has(Ability.FLYING);
    }

    @Override
    public void setFlying(boolean flying) {
        this.abilities.setFlying(flying);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);

        tryPickUpEntities();
        tickPlayerDataAutoSave();

        syncData();
    }

    @Override
    public void requireResendingCommands() {
        this.requireResendingCommands = true;
    }

    @Override
    public void setCooldown(String category, @Range(from = 0, to = Integer.MAX_VALUE) int duration, boolean send) {
        this.cooldowns.put(category, getWorld().getTick() + duration);
        if (send) {
            var packet = new PlayerStartItemCooldownPacket();
            packet.setItemCategory(category);
            packet.setCooldownDuration(duration);
            this.clientComponent.sendPacket(packet);
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
        clientComponent.sendPacket(respawnPacket);
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
            Server.getInstance().getPlayerManager().getPlayerStorage().savePlayerData(thisPlayer);
            nextSavePlayerDataTime = currentServerTick + Server.SETTINGS.storageSettings().playerDataAutoSaveCycle();
        }
    }

    protected void syncData() {
        if (requireResendingCommands) {
            this.clientComponent.sendCommands();
            this.requireResendingCommands = false;
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
        var entityItems = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(pickUpArea, true)
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

            var inventory = Objects.requireNonNull(containerHolderComponent.getContainer(ContainerType.INVENTORY));
            var slot = inventory.tryAddItem(item);
            if (slot == -1) {
                // Player's inventory is full and cannot pick up the item
                continue;
            }

            if (item.getCount() == 0) {
                entityItem.applyAction(new PickedUpAction(thisPlayer));
                // Set item to null to prevent others from picking this item twice
                entityItem.setItemStack(null);
                entityItem.remove();
            }
        }

        // Pick up arrows
        var entityArrows = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(pickUpArea, true)
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

            if (entityArrow.isPickUpDisabled()) {
                continue;
            }

            if (entityArrow.isInfinite()) {
                // Arrow shot by bow with infinity enchantment or shot by creative player can't be picked up
                entityArrow.remove();
                continue;
            }

            var arrow = ItemTypes.ARROW.createItemStack(1);
            arrow.setPotionType(entityArrow.getPotionType());
            if (thisPlayer.getContainer(ContainerType.INVENTORY).tryAddItem(arrow) != -1) {
                entityArrow.applyAction(new PickedUpAction(thisPlayer));
                entityArrow.remove();
            }
        }
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
            thisPlayer.viewTime(targetDim.getWorld().getWorldData().getTimeOfDay());
            // Send new world's game rules
            thisPlayer.viewGameRules(targetDim.getWorld().getWorldData().getGameRules());
            thisPlayer.viewWeather(targetDim.getWorld().getWeather());
        }
        location.dimension().removePlayer(thisPlayer, () -> {
            setLocationBeforeSpawn(target);
            if (currentDim.getDimensionInfo().dimensionId() != targetDim.getDimensionInfo().dimensionId()) {
                // TODO: implement boolean changingDimension here
                var packet1 = new ChangeDimensionPacket();
                packet1.setDimension(targetDim.getDimensionInfo().dimensionId());
                packet1.setPosition(Vector3f.from(target.x(), target.y() + 1.62f, target.z()));
                clientComponent.sendPacket(packet1);

                // As of v1.19.50, the dimension ack that is meant to be sent by the client is now sent by the server. The client
                // still sends the ack, but after the server has sent it. Thanks to Mojang for another groundbreaking change.
                var packet2 = new PlayerActionPacket();
                packet2.setAction(PlayerActionType.DIMENSION_CHANGE_SUCCESS);
                packet2.setRuntimeEntityId(this.runtimeId);
                packet2.setBlockPosition(org.cloudburstmc.math.vector.Vector3i.ZERO);
                packet2.setResultPosition(org.cloudburstmc.math.vector.Vector3i.ZERO);
                clientComponent.sendPacket(packet2);
            }
            targetDim.addPlayer(thisPlayer, this::sendLocationToSelf);
        });
    }

    @Override
    public void spawnTo(WorldViewer viewer) {
        if (thisPlayer != viewer) {
            super.spawnTo(viewer);
            viewer.viewEntityArmors(thisPlayer);
            viewer.viewEntityHand(thisPlayer);
            viewer.viewEntityOffhand(thisPlayer);
            // Skin should be sent to the player, otherwise player's skin will become Steve in other player's eyes after respawn
            viewer.viewPlayerSkin(thisPlayer);
        }
    }

    @Override
    public void despawnFrom(WorldViewer viewer) {
        if (thisPlayer != viewer) {
            super.despawnFrom(viewer);
        }
    }

    @Override
    public void broadcastMoveToViewers(Location3dc newLocation, boolean teleporting) {
        super.broadcastMoveToViewers(newLocation, teleporting);
        if (!teleporting) {
            manager.callEvent(CPlayerMoveEvent.INSTANCE);
        }
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
        return containerHolderComponent.getContainer(ContainerType.INVENTORY).getHandSlot();
    }

    @Override
    public void setHandSlot(int handSlot) {
        setHandSlot(handSlot, true);
    }

    public void setHandSlot(int handSlot, boolean sendToSelf) {
        Preconditions.checkArgument(handSlot >= 0 && handSlot <= 8);

        var container = containerHolderComponent.getContainer(ContainerType.INVENTORY);
        container.setHandSlot(handSlot);
        new PlayerItemHeldEvent(thisPlayer, container.getItemInHand(), handSlot).call();
        if (sendToSelf) {
            thisPlayer.viewEntityHand(thisPlayer);
        }
        forEachViewers(viewer -> viewer.viewEntityHand(thisPlayer));
    }

    @Override
    public void setSkin(Skin skin) {
        this.skin = skin;
        var server = Server.getInstance();
        server.getPlayerManager().forEachPlayer(player -> player.viewPlayerSkin(thisPlayer));
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putCompound(TAG_PERMISSION, permissionGroup.saveNBT())
                .putList(
                        TAG_OFFHAND,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(ContainerType.OFFHAND).saveNBT())
                .putList(
                        TAG_INVENTORY,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(ContainerType.INVENTORY).saveNBT())
                .putList(
                        TAG_ARMOR,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(ContainerType.ARMOR).saveNBT())
                .putList(
                        TAG_ENDER_ITEMS,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(ContainerType.ENDER_CHEST).saveNBT())
                .putInt(TAG_ENCHANTMENT_SEED, enchantmentSeed)
                .putInt(TAG_GAME_TYPE, toNetwork(gameMode).ordinal())
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
                containerHolderComponent.getContainer(ContainerType.OFFHAND).loadNBT(offhandNbt)
        );
        nbt.listenForList(TAG_INVENTORY, NbtType.COMPOUND, inventoryNbt ->
                containerHolderComponent.getContainer(ContainerType.INVENTORY).loadNBT(inventoryNbt)
        );
        nbt.listenForList(TAG_ARMOR, NbtType.COMPOUND, armorNbt ->
                containerHolderComponent.getContainer(ContainerType.ARMOR).loadNBT(armorNbt)
        );
        nbt.listenForList(TAG_ENDER_ITEMS, NbtType.COMPOUND, enderItemsNbt ->
                containerHolderComponent.getContainer(ContainerType.ENDER_CHEST).loadNBT(enderItemsNbt)
        );
        nbt.listenForInt(TAG_ENCHANTMENT_SEED, this::setEnchantmentSeed);
        nbt.listenForInt(TAG_GAME_TYPE, id -> setGameMode(fromNetwork(GameType.from(id)), true));
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
            packet.setCommandOriginData(new CommandOriginData(CommandOriginType.PLAYER, thisPlayer.getLoginData().getUuid(), "", 0));
            for (var output : outputs) {
                packet.getMessages().add(new CommandOutputMessage(
                        // Indicates if the output message was one of a successful command execution
                        status != CommandResult.FAIL_STATUS,
                        I18n.get().tr(thisPlayer.getLoginData().getLangCode(), output.str(), output.args()),
                        new String[0]
                ));
            }
            packet.setSuccessCount(status);
            this.clientComponent.sendPacket(packet);
        } else {
            for (var output : outputs) {
                var str = TextFormat.GRAY + "" + TextFormat.ITALIC + "[" + sender.getCommandSenderName() + ": " + I18n.get().tr(thisPlayer.getLoginData().getLangCode(), output.str(), output.args()) + "]";
                sendMessage(str);
            }
        }
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
        this.clientComponent.sendPacket(pk);
    }

    @Override
    public void sendTitle(String title) {
        var pk = new SetTitlePacket();
        pk.setText(title);
        pk.setType(SetTitlePacket.Type.TITLE);
        pk.setXuid("");
        pk.setPlatformOnlineId("");
        this.clientComponent.sendPacket(pk);
    }

    @Override
    public void sendSubtitle(String subtitle) {
        var pk = new SetTitlePacket();
        pk.setText(subtitle);
        pk.setType(SetTitlePacket.Type.SUBTITLE);
        pk.setXuid("");
        pk.setPlatformOnlineId("");
        this.clientComponent.sendPacket(pk);
    }

    @Override
    public void sendActionBar(String actionBar) {
        var pk = new SetTitlePacket();
        pk.setText(actionBar);
        pk.setType(SetTitlePacket.Type.ACTIONBAR);
        pk.setXuid("");
        pk.setPlatformOnlineId("");
        this.clientComponent.sendPacket(pk);
    }

    @Override
    public void setTitleSettings(int fadeInTime, int duration, int fadeOutTime) {
        var pk = new SetTitlePacket();
        pk.setType(SetTitlePacket.Type.TIMES);
        pk.setFadeInTime(fadeInTime);
        pk.setFadeOutTime(fadeOutTime);
        pk.setStayTime(duration);
        this.clientComponent.sendPacket(pk);
    }

    @Override
    public void resetTitleSettings() {
        var pk = new SetTitlePacket();
        pk.setType(SetTitlePacket.Type.RESET);
        this.clientComponent.sendPacket(pk);
    }

    @Override
    public void clearTitle() {
        var pk = new SetTitlePacket();
        pk.setType(SetTitlePacket.Type.CLEAR);
        this.clientComponent.sendPacket(pk);
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
        this.spawnPoint = new Location3i(spawnPoint);
    }

    public void sendLocationToSelf() {
        // NOTICE: do not use MovePlayerPacket. Sometimes this packet does not have any
        // effect especially when teleporting player to another world or a far away place.
        thisPlayer.viewEntityLocation(thisPlayer, locationLastSent, location, true);
    }

    @Override
    public boolean isUsingItemInAir() {
        return getFlag(EntityFlag.USING_ITEM);
    }

    @Override
    public void setUsingItemInAir(boolean value, long time) {
        setFlag(EntityFlag.USING_ITEM, value);
        if (value) {
            startUsingItemInAirTime = time;
        }
    }

    @Override
    public void sendMessage(String message) {
        sendSimpleMessage(message, TextPacket.Type.RAW);
    }

    protected void sendSimpleMessage(String message, TextPacket.Type type) {
        var packet = new TextPacket();
        packet.setType(type);
        packet.setXuid(clientComponent.getLoginData().getXuid());
        packet.setMessage(message);
        clientComponent.sendPacket(packet);
    }

    @Override
    public void sendTranslatable(String translatable, Object... args) {
        sendMessage(I18n.get().tr(thisPlayer.getLoginData().getLangCode(), translatable, args));
    }

    @Override
    public void applyAction(EntityAction action) {
        super.applyAction(action);
        thisPlayer.viewEntityAction(thisPlayer, action);
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

    public Form removeForm(int id) {
        return forms.remove(id);
    }

    @Override
    public Pair<Integer, CustomForm> getServerSettingForm() {
        return new Pair<>(serverSettingFormId, serverSettingForm);
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
            clientComponent.disconnect("Possible DoS vulnerability: More Than 100 FormWindow sent to client already.");
        }
        var packet = new ModalFormRequestPacket();
        var id = assignFormId();
        packet.setFormId(id);
        packet.setFormData(form.toJson());
        forms.putIfAbsent(id, form);
        clientComponent.sendPacket(packet);
    }

    @Override
    public void closeAllForms() {
        clientComponent.sendPacket(new ClientboundCloseFormPacket());
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
    public void setLocationBeforeSpawn(Location3dc location) {
        if (this.location.dimension() != null && location.dimension() == null) {
            // Different from normal entity, reset the dimension of player entity back to null is not allowed
            throw new IllegalArgumentException("Reset dimension back to null is not allowed for player!");
        }

        super.setLocationBeforeSpawn(location);
    }

    protected void sendMetadata() {
        super.sendMetadata();
        // The current player should also view his metadata
        thisPlayer.viewEntityMetadata(thisPlayer);
    }

    public void onJump() {
        new PlayerJumpEvent(thisPlayer).call();
        manager.callEvent(CPlayerJumpEvent.INSTANCE);
    }

    @Override
    public boolean isSprinting() {
        return getFlag(EntityFlag.SPRINTING);
    }

    @Override
    public void setSprinting(boolean sprinting) {
        if (sprinting != isSprinting()) {
            new PlayerToggleSprintEvent(thisPlayer, sprinting).call();
            var speed = getMovementSpeed();
            if (sprinting) {
                speed *= 1.3f;
            } else {
                speed /= 1.3f;
            }

            setMovementSpeed(speed);
            setFlag(EntityFlag.SPRINTING, sprinting);
        }
    }

    @Override
    public boolean isSneaking() {
        return getFlag(EntityFlag.SNEAKING);
    }

    @Override
    public void setSneaking(boolean sneaking) {
        if (sneaking != isSneaking()) {
            new PlayerToggleSneakEvent(thisPlayer, sneaking).call();
            setFlag(EntityFlag.SNEAKING, sneaking);
        }
    }

    @Override
    public boolean isSwimming() {
        return getFlag(EntityFlag.SWIMMING);
    }

    @Override
    public void setSwimming(boolean swimming) {
        if (swimming != isSwimming()) {
            new PlayerToggleSwimEvent(thisPlayer, swimming).call();
            setFlag(EntityFlag.SWIMMING, swimming);
        }
    }

    @Override
    public boolean isGliding() {
        return getFlag(EntityFlag.GLIDING);
    }

    @Override
    public void setGliding(boolean gliding) {
        if (gliding != isGliding()) {
            new PlayerToggleGlideEvent(thisPlayer, gliding).call();
            setFlag(EntityFlag.GLIDING, gliding);
        }
    }

    @Override
    public boolean isCrawling() {
        return getFlag(EntityFlag.CRAWLING);
    }

    @Override
    public void setCrawling(boolean crawling) {
        if (crawling == isCrawling()) return;

        new PlayerToggleCrawlEvent(thisPlayer, crawling).call();

        setFlag(EntityFlag.CRAWLING, crawling);
    }

    public boolean isAwaitingTeleportACK() {
        return expectedTeleportPos != null;
    }

    public void ackTeleported() {
        this.expectedTeleportPos = null;
    }
}
