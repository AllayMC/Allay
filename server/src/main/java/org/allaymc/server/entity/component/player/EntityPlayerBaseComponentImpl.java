package org.allaymc.server.entity.component.player;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.action.PickedUpAction;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.component.EntityPlayerBaseComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.*;
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
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.server.AllayServer;
import org.allaymc.server.command.tree.node.BaseNode;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CEntityAfterDamageEvent;
import org.allaymc.server.entity.component.event.CEntityAttackEvent;
import org.allaymc.server.entity.component.event.CPlayerGameModeChangeEvent;
import org.allaymc.server.entity.component.event.CPlayerLoggedInEvent;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.data.command.*;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jctools.maps.NonBlockingHashMap;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.server.network.NetworkHelper.fromNetwork;
import static org.allaymc.server.network.NetworkHelper.toNetwork;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerBaseComponentImpl extends EntityBaseComponentImpl implements EntityPlayerBaseComponent {

    protected static final String TAG_PERMISSION = "Permission";
    protected static final String TAG_ENCHANTMENT_SEED = "EnchantmentSeed";
    protected static final String TAG_PLAYER_GAME_MODE = "PlayerGameMode";

    protected static final String TAG_OFFHAND = "Offhand";
    protected static final String TAG_INVENTORY = "Inventory";
    protected static final String TAG_ARMOR = "Armor";
    protected static final String TAG_ENDER_ITEMS = "EnderChestInventory";

    protected static final String TAG_SPAWN_POINT = "SpawnPoint";
    protected static final String TAG_WORLD = "World";
    protected static final String TAG_DIMENSION = "Dimension";

    protected static final String TAG_PLAYER_LEVEL = "PlayerLevel";
    protected static final String TAG_PLAYER_LEVEL_PROGRESS = "PlayerLevelProgress";

    protected static final String TAG_FOOD_LEVEL = "FoodLevel";
    protected static final String TAG_FOOD_SATURATION_LEVEL = "FoodSaturationLevel";
    protected static final String TAG_FOOD_EXHAUSTION_LEVEL = "FoodExhaustionLevel";
    protected static final String TAG_FOOD_TICK_TIMER = "FoodTickTimer";

    protected static final int FOOD_TICK_THRESHOLD = 80;
    /**
     * To reduce network traffic, we only update food data every 10 blocks of movement
     */
    protected static final int EXHAUSTION_MOVEMENT_THRESHOLD = 10;

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
     * expectedTeleportPos is used to solve the desynchronization of data at both ends. Because PlayerAuthInputPacket
     * will be sent from the client to the server at a rate of 20 per second. After teleporting, the server still
     * receives the PlayerAuthInputPacket sent by the client before teleporting. The following is a simple simulation
     * (initial player position is (0, 1000, 0)):
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
    @Getter
    @Setter
    protected int enchantmentSeed;

    @Getter
    @Setter
    protected boolean usingItemOnBlock, usingItemInAir;
    protected long startUsingItemInAirTime;

    protected Map<String, Long> cooldowns;

    @Getter
    protected double speed, flySpeed, verticalFlySpeed;
    @Getter
    protected String scoreTag;
    @Getter
    protected boolean sprinting, sneaking, swimming, gliding, crawling, flying;

    @Getter
    protected int experienceLevel;
    @Getter
    protected float experienceProgress;

    @Getter
    protected int foodLevel;
    @Getter
    protected float foodSaturationLevel, foodExhaustionLevel;
    protected int foodTickTimer;
    protected float swimDistance, sprintDistance;

    protected long nextSavePlayerDataTime;

    public EntityPlayerBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.gameMode = AllayServer.getSettings().genericSettings().defaultGameMode();
        // Set enchantment seed to a random value, and if the player has enchantment
        // seed previously, this random value will be covered
        this.enchantmentSeed = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        this.startUsingItemInAirTime = -1;
        this.cooldowns = new NonBlockingHashMap<>();
        this.speed = DEFAULT_SPEED;
        this.flySpeed = DEFAULT_FLY_SPEED;
        this.verticalFlySpeed = DEFAULT_VERTICAL_FLY_SPEED;
        this.foodLevel = MAX_FOOD_LEVEL;
        this.nextSavePlayerDataTime = Integer.MAX_VALUE;
        // Player's name tag is always shown
        this.nameTagAlwaysShow = true;
    }

    @Override
    protected void initPermissionGroup() {
        // Do not register the player's permission group
        this.permissionGroup = PermissionGroup.create("Permission group for player " + runtimeId, Set.of(), Set.of(), false);
        // Add the parent permission group alone, so that the permission listeners will be triggered
        // Commands will be sent to the client during this method call
        this.permissionGroup.addParent(PermissionGroups.DEFAULT.get(), thisPlayer);
        // The default game mode may be creative/spectator, and in that case we should give player fly ability
        setPermission(Permissions.ABILITY_FLY, this.gameMode != GameMode.SURVIVAL && this.gameMode != GameMode.ADVENTURE);
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
        var event = new PlayerGameModeChangeEvent(thisPlayer, this.gameMode, gameMode);
        if (!event.call() || this.gameMode == event.getNewGameMode()) {
            return;
        }

        gameMode = event.getNewGameMode();
        this.gameMode = gameMode;
        setPermission(Permissions.ABILITY_FLY, gameMode != GameMode.SURVIVAL && gameMode != GameMode.ADVENTURE);
        this.manager.callEvent(new CPlayerGameModeChangeEvent(this.gameMode));

        this.clientComponent.viewPlayerPermission(thisPlayer);
        thisPlayer.viewPlayerGameMode(thisPlayer);
        forEachViewers(viewer -> viewer.viewPlayerGameMode(thisPlayer));
    }

    public void setSpeed(double speed) {
        if (this.speed != speed) {
            this.speed = speed;
            this.clientComponent.sendSpeed(this.speed);
        }
    }

    @Override
    public void setFlySpeed(double flySpeed) {
        if (this.flySpeed != flySpeed) {
            this.flySpeed = flySpeed;
            this.clientComponent.viewPlayerPermission(thisPlayer);
        }
    }

    @Override
    public void setVerticalFlySpeed(double verticalFlySpeed) {
        if (this.verticalFlySpeed != verticalFlySpeed) {
            this.verticalFlySpeed = verticalFlySpeed;
            this.clientComponent.viewPlayerPermission(thisPlayer);
        }
    }

    @Override
    public void setFlying(boolean flying) {
        if (this.flying != flying) {
            this.flying = flying;
            this.clientComponent.viewPlayerPermission(thisPlayer);
        }
    }

    @Override
    public void setScoreTag(String scoreTag) {
        this.scoreTag = scoreTag;
        broadcastState();
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);

        if (isAlive()) {
            tickFood();
            tickPickUpEntities();
        }

        tickPlayerDataAutoSave();
        if (this.requireResendingCommands) {
            sendCommands();
            this.requireResendingCommands = false;
        }
    }

    protected void tickFood() {
        this.foodTickTimer++;
        if (this.foodTickTimer >= FOOD_TICK_THRESHOLD) {
            this.foodTickTimer = 0;
        }

        var currentFoodLevel = getFoodLevel();
        var difficulty = getWorld().getWorldData().getDifficulty();
        if (difficulty == Difficulty.PEACEFUL && foodTickTimer % 10 == 0) {
            setFoodLevel(currentFoodLevel + 1);
            if (foodTickTimer % 20 == 0) {
                regenerate(false);
            }
        }

        if (foodTickTimer == 0 && difficulty != Difficulty.PEACEFUL) {
            if (currentFoodLevel >= 18) {
                regenerate(true);
            } else if (currentFoodLevel == 0) {
                if (
                        (difficulty == Difficulty.EASY && thisPlayer.getHealth() > 10) ||
                        (difficulty == Difficulty.NORMAL && thisPlayer.getHealth() > 1) ||
                        difficulty == Difficulty.HARD
                ) {
                    thisPlayer.attack(DamageContainer.starve(1));
                }
            }
        }

        if (currentFoodLevel <= 6 && thisPlayer.isSprinting()) {
            setSprinting(false);
        }
    }

    protected void tickPickUpEntities() {
        if (!isCurrentChunkLoaded()) {
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

            var inventory = Objects.requireNonNull(containerHolderComponent.getContainer(ContainerTypes.INVENTORY));
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
            if (thisPlayer.getContainer(ContainerTypes.INVENTORY).tryAddItem(arrow) != -1) {
                entityArrow.applyAction(new PickedUpAction(thisPlayer));
                entityArrow.remove();
            }
        }
    }

    protected void tickPlayerDataAutoSave() {
        // We use server's tick instead of world's tick
        // because player may teleport between worlds
        // and the tick in different worlds may not be same
        var currentServerTick = Server.getInstance().getTick();
        if (nextSavePlayerDataTime == Integer.MAX_VALUE) {
            nextSavePlayerDataTime = currentServerTick + AllayServer.getSettings().storageSettings().playerDataAutoSaveCycle();
            return;
        }
        if (currentServerTick >= nextSavePlayerDataTime) {
            Server.getInstance().getPlayerManager().getPlayerStorage().savePlayerData(thisPlayer);
            nextSavePlayerDataTime = currentServerTick + AllayServer.getSettings().storageSettings().playerDataAutoSaveCycle();
        }
    }

    protected void sendCommands() {
        var packet = new AvailableCommandsPacket();
        Registries.COMMANDS.getContent().values().stream()
                .filter(command -> !command.isServerSideOnly() && thisPlayer.hasPermissions(command.getPermissions()))
                .forEach(command -> packet.getCommands().add(encodeCommand(command)));
        this.clientComponent.sendPacket(packet);
    }

    protected CommandData encodeCommand(Command command) {
        // Aliases
        CommandEnumData aliases = null;
        if (!command.getAliases().isEmpty()) {
            var values = new LinkedHashMap<String, Set<CommandEnumConstraint>>();
            command.getAliases().forEach(alias -> values.put(alias, Collections.emptySet()));
            values.put(command.getName(), Collections.emptySet());
            aliases = new CommandEnumData(command.getName() + "CommandAliases", values, false);
        }

        // Overloads
        var overloads = new ArrayList<CommandOverloadData>();
        for (var leaf : command.getCommandTree().getLeaves()) {
            var params = new CommandParamData[leaf.depth()];
            var node = leaf;
            var index = leaf.depth() - 1;
            while (!node.isRoot()) {
                params[index] = ((BaseNode) node).toNetworkData();
                node = node.parent();
                index--;
            }
            overloads.add(new CommandOverloadData(false, params));
        }
        if (overloads.isEmpty()) {
            overloads.add(new CommandOverloadData(false, new CommandParamData[0]));
        }

        // Flags
        var flags = new HashSet<CommandData.Flag>();
        flags.add(CommandData.Flag.NOT_CHEAT);
        if (command.isDebugCommand()) {
            flags.add(CommandData.Flag.TEST_USAGE);
        }

        return new CommandData(
                command.getName(), I18n.get().tr(thisPlayer.getLoginData().getLangCode(), command.getDescription()),
                flags, CommandPermission.ANY, aliases, List.of(), overloads.toArray(CommandOverloadData[]::new)
        );
    }

    protected void regenerate(boolean exhaust) {
        if (thisPlayer.getHealth() == thisPlayer.getMaxHealth()) {
            return;
        }

        thisPlayer.setHealth(thisPlayer.getHealth() + 1);
        if (exhaust) {
            exhaust(6);
        }
    }

    @Override
    public void setCooldown(String category, int duration, boolean send) {
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
    public void setExperienceLevel(int value) {
        var event = new PlayerExperienceLevelChangeEvent(thisPlayer, this.experienceLevel, value);
        if (!event.call()) {
            return;
        }

        this.experienceLevel = event.getNewExperienceLevel();
        this.clientComponent.sendExperienceLevel(this.experienceLevel);
    }

    @Override
    public void setExperienceProgress(float value) {
        var event = new PlayerExperienceProgressChangeEvent(thisPlayer, this.experienceProgress, value);
        if (!event.call()) {
            return;
        }

        this.experienceProgress = value;
        this.clientComponent.sendExperienceProgress(this.experienceProgress);
    }

    @Override
    public void setFoodLevel(int value) {
        value = Math.max(0, Math.min(value, MAX_FOOD_LEVEL));
        var event = new PlayerFoodLevelChangeEvent(thisPlayer, this.foodLevel, value);
        if (!event.call()) {
            return;
        }

        this.foodLevel = event.getNewFoodLevel();
        this.clientComponent.sendFoodLevel(this.foodLevel);
    }

    @Override
    public void setFoodSaturationLevel(float value) {
        this.foodSaturationLevel = Math.max(0, Math.min(value, MAX_FOOD_SATURATION_LEVEL));
        this.clientComponent.sendFoodSaturationLevel(this.foodSaturationLevel);
    }

    @Override
    public void setFoodExhaustionLevel(float value) {
        this.foodExhaustionLevel = Math.max(0, Math.min(value, MAX_FOOD_EXHAUSTION_LEVEL));
        this.clientComponent.sendFoodExhaustionLevel(this.foodExhaustionLevel);
    }

    @Override
    public void saturate(int food, float saturation) {
        setFoodLevel(getFoodLevel() + food);
        setFoodSaturationLevel(Math.min(getFoodSaturationLevel() + saturation, getFoodLevel()));
    }

    @Override
    public void exhaust(float level) {
        if (this.gameMode == GameMode.CREATIVE ||
            this.gameMode == GameMode.SPECTATOR ||
            getWorld().getWorldData().getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }

        var exhaustionLevel = this.foodExhaustionLevel + level;
        var saturationLevel = this.foodSaturationLevel;
        var foodLevel = this.foodLevel;

        while (exhaustionLevel >= MAX_FOOD_EXHAUSTION_LEVEL) {
            exhaustionLevel -= MAX_FOOD_EXHAUSTION_LEVEL;

            if (saturationLevel > 0) {
                saturationLevel = Math.max(saturationLevel - 1, 0);
            } else {
                foodLevel--;
            }
        }

        setFoodExhaustionLevel(exhaustionLevel);
        setFoodSaturationLevel(saturationLevel);
        setFoodLevel(foodLevel);
    }

    @Override
    public boolean canEat() {
        return getFoodLevel() < 20 ||
               thisPlayer.getGameMode() == GameMode.CREATIVE ||
               thisPlayer.getWorld().getWorldData().getDifficulty() == Difficulty.PEACEFUL;
    }

    @Override
    protected void tickBlockCollision() {
        if (this.gameMode == GameMode.SPECTATOR) {
            return;
        }

        super.tickBlockCollision();
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
                this.clientComponent.sendPacket(packet1);

                // As of v1.19.50, the dimension ack that is meant to be sent by the client is now sent by the server. The client
                // still sends the ack, but after the server has sent it. Thanks to Mojang for another groundbreaking change.
                var packet2 = new PlayerActionPacket();
                packet2.setAction(PlayerActionType.DIMENSION_CHANGE_SUCCESS);
                packet2.setRuntimeEntityId(this.runtimeId);
                packet2.setBlockPosition(org.cloudburstmc.math.vector.Vector3i.ZERO);
                packet2.setResultPosition(org.cloudburstmc.math.vector.Vector3i.ZERO);
                this.clientComponent.sendPacket(packet2);
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
            var distance = (float) thisPlayer.getLastLocation().distance(thisPlayer.getLocation());

            if (thisPlayer.isSwimming()) {
                swimDistance += distance;
                if (swimDistance >= EXHAUSTION_MOVEMENT_THRESHOLD) {
                    exhaust(0.01f * swimDistance);
                    swimDistance = 0;
                }
            }

            if (thisPlayer.isSprinting()) {
                sprintDistance += distance;
                if (sprintDistance >= EXHAUSTION_MOVEMENT_THRESHOLD) {
                    exhaust(0.1f * sprintDistance);
                    sprintDistance = 0;
                }
            }
        }
    }

    public long getStartUsingItemInAirTime() {
        if (!isUsingItemInAir()) {
            log.warn("Player {} is not using item in air", thisPlayer.getOriginName());
        }
        return this.startUsingItemInAirTime;
    }

    @Override
    public long getItemUsingInAirTime(long currentTime) {
        return currentTime - this.startUsingItemInAirTime;
    }

    @Override
    public int getHandSlot() {
        return containerHolderComponent.getContainer(ContainerTypes.INVENTORY).getHandSlot();
    }

    @Override
    public void setHandSlot(int handSlot) {
        setHandSlot(handSlot, true);
    }

    public void setHandSlot(int handSlot, boolean sendToSelf) {
        Preconditions.checkArgument(handSlot >= 0 && handSlot <= 8);

        var container = containerHolderComponent.getContainer(ContainerTypes.INVENTORY);
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
                // General
                .putCompound(TAG_PERMISSION, this.permissionGroup.saveNBT())
                .putInt(TAG_ENCHANTMENT_SEED, this.enchantmentSeed)
                .putInt(TAG_PLAYER_GAME_MODE, toNetwork(this.gameMode).ordinal())
                // SpawnPoint
                .putCompound(TAG_SPAWN_POINT, saveSpawnPoint())
                // Container
                .putList(
                        TAG_OFFHAND,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(ContainerTypes.OFFHAND).saveNBT())
                .putList(
                        TAG_INVENTORY,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(ContainerTypes.INVENTORY).saveNBT())
                .putList(
                        TAG_ARMOR,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(ContainerTypes.ARMOR).saveNBT())
                .putList(
                        TAG_ENDER_ITEMS,
                        NbtType.COMPOUND,
                        containerHolderComponent.getContainer(ContainerTypes.ENDER_CHEST).saveNBT())
                // Experience
                .putInt(TAG_PLAYER_LEVEL, this.experienceLevel)
                .putFloat(TAG_PLAYER_LEVEL_PROGRESS, this.experienceProgress)
                // Food
                .putInt(TAG_FOOD_LEVEL, foodLevel)
                .putFloat(TAG_FOOD_SATURATION_LEVEL, this.foodSaturationLevel)
                .putFloat(TAG_FOOD_EXHAUSTION_LEVEL, this.foodExhaustionLevel)
                .putInt(TAG_FOOD_TICK_TIMER, this.foodTickTimer)
                .build();
    }

    protected NbtMap saveSpawnPoint() {
        var builder = NbtMap.builder()
                .putString(TAG_WORLD, spawnPoint.dimension().getWorld().getWorldData().getDisplayName())
                .putInt(TAG_DIMENSION, spawnPoint.dimension().getDimensionInfo().dimensionId());
        AllayNBTUtils.writeVector3i(builder, TAG_POS, spawnPoint);
        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        // General
        nbt.listenForCompound(TAG_PERMISSION, permNbt -> this.permissionGroup.loadNBT(permNbt, thisPlayer));
        nbt.listenForInt(TAG_ENCHANTMENT_SEED, this::setEnchantmentSeed);
        nbt.listenForInt(TAG_PLAYER_GAME_MODE, id -> {
            this.gameMode = fromNetwork(GameType.from(id));
            setPermission(Permissions.ABILITY_FLY, this.gameMode != GameMode.SURVIVAL && this.gameMode != GameMode.ADVENTURE);
        });

        // SpawnPoint
        if (nbt.containsKey(TAG_SPAWN_POINT)) {
            loadSpawnPoint(nbt.getCompound(TAG_SPAWN_POINT));
        } else {
            this.spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
        }

        // Container
        nbt.listenForList(TAG_OFFHAND, NbtType.COMPOUND, offhandNbt ->
                this.containerHolderComponent.getContainer(ContainerTypes.OFFHAND).loadNBT(offhandNbt)
        );
        nbt.listenForList(TAG_INVENTORY, NbtType.COMPOUND, inventoryNbt ->
                this.containerHolderComponent.getContainer(ContainerTypes.INVENTORY).loadNBT(inventoryNbt)
        );
        nbt.listenForList(TAG_ARMOR, NbtType.COMPOUND, armorNbt ->
                this.containerHolderComponent.getContainer(ContainerTypes.ARMOR).loadNBT(armorNbt)
        );
        nbt.listenForList(TAG_ENDER_ITEMS, NbtType.COMPOUND, enderItemsNbt ->
                this.containerHolderComponent.getContainer(ContainerTypes.ENDER_CHEST).loadNBT(enderItemsNbt)
        );

        // Experience
        nbt.listenForInt(TAG_PLAYER_LEVEL, value -> this.experienceLevel = value);
        nbt.listenForFloat(TAG_PLAYER_LEVEL_PROGRESS, value -> this.experienceProgress = value);

        // Food
        nbt.listenForInt(TAG_FOOD_LEVEL, value -> this.foodLevel = value);
        nbt.listenForFloat(TAG_FOOD_SATURATION_LEVEL, value -> this.foodSaturationLevel = value);
        nbt.listenForFloat(TAG_FOOD_EXHAUSTION_LEVEL, value -> this.foodExhaustionLevel = value);
        nbt.listenForInt(TAG_FOOD_TICK_TIMER, value -> this.foodTickTimer = value);
    }

    protected void loadSpawnPoint(NbtMap nbt) {
        var world = Server.getInstance().getWorldPool().getWorld(nbt.getString(TAG_WORLD));
        if (world == null) {
            this.spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
            return;
        }

        var dimension = world.getDimension(nbt.getInt(TAG_DIMENSION));
        if (dimension == null) {
            this.spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
            return;
        }

        this.spawnPoint = new Location3i(
                AllayNBTUtils.readVector3i(nbt, TAG_POS),
                0, 0, 0, dimension
        );
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
        thisPlayer.viewEntityLocation(thisPlayer, lastSentLocation, location, true);
    }

    @Override
    public void setUsingItemInAir(boolean value, long time) {
        this.usingItemInAir = value;
        if (value) {
            this.startUsingItemInAirTime = time;
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
        packet.setXuid(this.clientComponent.getLoginData().getXuid());
        packet.setMessage(message);
        this.clientComponent.sendPacket(packet);
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
    public void requireResendingCommands() {
        this.requireResendingCommands = true;
    }

    @Override
    public void setLocationBeforeSpawn(Location3dc location) {
        if (this.location.dimension() != null && location.dimension() == null) {
            // Different from the normal entity, reset the dimension of the player back to null is not allowed
            throw new IllegalArgumentException("Reset dimension back to null is not allowed for player!");
        }

        super.setLocationBeforeSpawn(location);
    }

    @Override
    public void broadcastState() {
        super.broadcastState();
        thisPlayer.viewEntityState(thisPlayer);
    }

    @Override
    public void setSprinting(boolean sprinting) {
        if (this.sprinting != sprinting) {
            this.sprinting = sprinting;
            var speed = this.speed;
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

    @Override
    public AABBdc getAABB() {
        var height = 1.8;
        if (this.sneaking) {
            height = 1.5;
        } else if (this.swimming || this.gliding) {
            height = 0.6;
        } else if (this.crawling) {
            height = 0.625;
        }

        return new AABBd(-0.3, 0.0, -0.3, 0.3, height, 0.3);
    }

    @EventHandler
    protected void onPlayerLoggedIn(CPlayerLoggedInEvent event) {
        var loginData = this.clientComponent.getLoginData();
        this.skin = loginData.getSkin();
        this.uniqueId = loginData.getUuid().getMostSignificantBits();
        setDisplayName(loginData.getXname());
        setNameTag(loginData.getXname());
    }

    @EventHandler
    protected void onDamage(CEntityAfterDamageEvent event) {
        exhaust(0.1f);
    }

    @EventHandler
    protected void onAttack(CEntityAttackEvent event) {
        exhaust(0.1f);
    }
}
