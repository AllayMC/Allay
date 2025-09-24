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
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.api.permission.Permissions;
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
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.command.*;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jctools.maps.NonBlockingHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;

import java.util.*;
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
    @ComponentObject
    protected EntityPlayer thisPlayer;

    @Getter
    protected GameMode gameMode;
    @Getter
    protected Skin skin;
    protected Location3ic spawnPoint;
    protected boolean requireResendingCommands;
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
    @Getter
    @Setter
    protected boolean usingItemOnBlock;
    @Getter
    protected boolean usingItemInAir;
    protected long startUsingItemInAirTime;
    protected AtomicInteger formIdCounter;
    protected Map<Integer, Form> forms;
    protected Map<String, Long> cooldowns;
    protected CustomForm serverSettingForm;
    protected int serverSettingFormId;
    @Getter
    protected float speed;
    @Getter
    protected float flySpeed;
    @Getter
    protected float verticalFlySpeed;
    @Getter
    protected String scoreTag;
    @Getter
    protected boolean sprinting;
    @Getter
    protected boolean sneaking;
    @Getter
    protected boolean swimming;
    @Getter
    protected boolean gliding;
    @Getter
    protected boolean crawling;
    @Getter
    protected boolean flying;
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
        this.speed = DEFAULT_SPEED;
        this.flySpeed = DEFAULT_FLY_SPEED;
        this.verticalFlySpeed = DEFAULT_VERTICAL_FLY_SPEED;
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
        // Do not register the player's permission group
        this.permissionGroup = PermissionGroup.create("Permission group for player " + runtimeId, Set.of(), Set.of(), false);
        // Add the parent permission group alone, so that adventure settings and abilities will also be updated
        this.permissionGroup.addParent(PermissionGroup.get(Server.SETTINGS.genericSettings().defaultPermission()), thisPlayer);
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
        setPermission(Permissions.ABILITY_FLY, gameMode != GameMode.SURVIVAL && gameMode != GameMode.ADVENTURE);
        sendAbilities(thisPlayer);

        thisPlayer.viewPlayerGameMode(thisPlayer);
        forEachViewers(viewer -> viewer.viewPlayerGameMode(thisPlayer));
    }

    public void setSpeed(float speed) {
        if (this.speed != speed) {
            this.speed = speed;
            attributeComponent.setAttributeValue(AttributeType.MOVEMENT_SPEED, this.speed);
            // NOTICE: abilities.setWalkSpeed(speed) shouldn't be called otherwise player can't sprint
        }
    }

    @Override
    public void setFlySpeed(float flySpeed) {
        if (this.flySpeed != flySpeed) {
            this.flySpeed = flySpeed;
            sendAbilities(thisPlayer);
        }
    }

    @Override
    public void setVerticalFlySpeed(float verticalFlySpeed) {
        if (this.verticalFlySpeed != verticalFlySpeed) {
            this.verticalFlySpeed = verticalFlySpeed;
            sendAbilities(thisPlayer);
        }
    }

    @Override
    public void setFlying(boolean flying) {
        if (this.flying != flying) {
            this.flying = flying;
            sendAbilities(thisPlayer);
        }
    }

    public void sendAbilities(EntityPlayer player) {
        ((EntityPlayerImpl) player).sendPacket(encodeAbilities());
    }

    protected UpdateAbilitiesPacket encodeAbilities() {
        UpdateAbilitiesPacket packet = new UpdateAbilitiesPacket();

        packet.setUniqueEntityId(this.runtimeId);
        // The command permissions set here are actually not very useful. Their main function is to allow OPs to have quick command options.
        // If this player does not have specific command permissions, the command description won't even be sent to the client
        packet.setCommandPermission(hasPermission(Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR) ? CommandPermission.GAME_DIRECTORS : CommandPermission.ANY);
        // PlayerPermissions is the permission level of the player as it shows up in the player list built up using the PlayerList packet
        packet.setPlayerPermission(calculatePlayerPermission());

        var layer = new AbilityLayer();
        layer.setLayerType(AbilityLayer.Type.BASE);
        layer.getAbilitiesSet().addAll(Arrays.asList(Ability.values()));
        layer.getAbilityValues().addAll(calculateAbilities());
        // NOTICE: this shouldn't be changed
        layer.setWalkSpeed(EntityPlayerBaseComponent.DEFAULT_SPEED);
        layer.setFlySpeed(this.flySpeed);
        layer.setVerticalFlySpeed(this.verticalFlySpeed);
        packet.getAbilityLayers().add(layer);

        return packet;
    }

    private @NotNull EnumSet<Ability> calculateAbilities() {
        var abilities = EnumSet.noneOf(Ability.class);
        abilities.add(Ability.TELEPORT);
        abilities.add(Ability.WALK_SPEED);
        abilities.add(Ability.FLY_SPEED);
        abilities.add(Ability.VERTICAL_FLY_SPEED);
        if (this.gameMode != GameMode.SPECTATOR) {
            abilities.add(Ability.BUILD);
            abilities.add(Ability.MINE);
            abilities.add(Ability.DOORS_AND_SWITCHES);
            abilities.add(Ability.OPEN_CONTAINERS);
            abilities.add(Ability.ATTACK_PLAYERS);
            abilities.add(Ability.ATTACK_MOBS);
        } else {
            abilities.add(Ability.NO_CLIP);
            abilities.add(Ability.FLYING);
        }
        if (this.gameMode == GameMode.CREATIVE) {
            abilities.add(Ability.INSTABUILD);
        }
        if (hasPermission(Permissions.ABILITY_FLY)) {
            abilities.add(Ability.MAY_FLY);
        }
        if (this.flying) {
            abilities.add(Ability.FLYING);
        }
        return abilities;
    }

    protected PlayerPermission calculatePlayerPermission() {
        if (hasPermissions(PermissionGroups.OPERATOR, true)) {
            return PlayerPermission.OPERATOR;
        } else if (hasPermissions(PermissionGroups.MEMBER, true)) {
            return PlayerPermission.MEMBER;
        }
        return PlayerPermission.VISITOR;
    }

    @Override
    public void setScoreTag(String scoreTag) {
        this.scoreTag = scoreTag;
        broadcastState();
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
        if (this.gameMode == GameMode.SPECTATOR) {
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
    public void setUsingItemInAir(boolean value, long time) {
        this.usingItemInAir = value;
        if (value) {
            startUsingItemInAirTime = time;
        }
        broadcastState();
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

    @Override
    public void broadcastState() {
        super.broadcastState();
        thisPlayer.viewEntityState(thisPlayer);
    }

    public void onJump() {
        new PlayerJumpEvent(thisPlayer).call();
        manager.callEvent(CPlayerJumpEvent.INSTANCE);
    }

    @Override
    public void setSprinting(boolean sprinting) {
        if (this.sprinting != sprinting) {
            this.sprinting = sprinting;
            var speed = this.getSpeed();
            if (sprinting) {
                speed *= 1.3f;
            } else {
                speed /= 1.3f;
            }

            setSpeed(speed);
            broadcastState();
            new PlayerToggleSprintEvent(thisPlayer, sprinting).call();
        }
    }

    @Override
    public void setSneaking(boolean sneaking) {
        if (this.sneaking != sneaking) {
            this.sneaking = sneaking;
            broadcastState();
            new PlayerToggleSneakEvent(thisPlayer, sneaking).call();
        }
    }

    @Override
    public void setSwimming(boolean swimming) {
        if (this.swimming != swimming) {
            this.swimming = swimming;
            broadcastState();
            new PlayerToggleSwimEvent(thisPlayer, swimming).call();
        }
    }

    @Override
    public void setGliding(boolean gliding) {
        if (this.gliding != gliding) {
            this.gliding = gliding;
            broadcastState();
            new PlayerToggleGlideEvent(thisPlayer, gliding).call();
        }
    }

    @Override
    public void setCrawling(boolean crawling) {
        if (this.crawling != crawling) {
            this.crawling = crawling;
            broadcastState();
            new PlayerToggleCrawlEvent(thisPlayer, crawling).call();
        }
    }

    @Override
    public boolean hasEntityCollision() {
        return this.gameMode != GameMode.SPECTATOR;
    }

    public boolean isAwaitingTeleportACK() {
        return expectedTeleportPos != null;
    }

    public void ackTeleported() {
        this.expectedTeleportPos = null;
    }
}
