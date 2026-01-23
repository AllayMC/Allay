package org.allaymc.server.entity.component.player;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.component.EntityPlayerBaseComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.*;
import org.allaymc.api.item.component.ItemShieldBaseComponent;
import org.allaymc.api.item.interfaces.ItemShieldStack;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.Skin;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.server.AllayServer;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CEntityAfterDamageEvent;
import org.allaymc.server.entity.component.event.CEntityAttackEvent;
import org.allaymc.server.entity.component.event.CPlayerGameModeChangeEvent;
import org.allaymc.server.world.AllayDimension;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.packet.ChangeDimensionPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerActionPacket;
import org.jctools.maps.NonBlockingHashMap;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.server.network.NetworkHelper.fromNetwork;
import static org.allaymc.server.network.NetworkHelper.toNetwork;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerBaseComponentImpl extends EntityBaseComponentImpl implements EntityPlayerBaseComponent {

    protected static final String TAG_ENCHANTMENT_SEED = "EnchantmentSeed";
    protected static final String TAG_PLAYER_GAME_MODE = "PlayerGameMode";

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

    @ComponentObject
    protected EntityPlayer thisPlayer;

    @Getter
    protected Player controller;
    @Getter
    protected GameMode gameMode;
    @Getter
    protected Skin skin;
    protected Location3ic spawnPoint;

    /**
     * Used to solve the desynchronization of data at both ends.
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
    protected boolean usingItemOnBlock, usingItemInAir;
    protected long startUsingItemInAirTime;

    protected Map<String, Long> cooldowns;

    @Getter
    protected String scoreTag;
    @Getter
    protected boolean sprinting, sneaking, swimming, gliding, crawling, flying, blocking;

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

    @Getter
    @Setter
    protected int chunkLoadingRadius;
    @Getter
    protected int chunkMaxSendCountPerTick;

    public EntityPlayerBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.gameMode = AllayServer.getSettings().genericSettings().defaultGameMode();
        // Set enchantment seed to a random value, and if the player has enchantment
        // seed previously, this random value will be covered
        this.enchantmentSeed = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        this.startUsingItemInAirTime = -1;
        this.cooldowns = new NonBlockingHashMap<>();
        this.foodLevel = MAX_FOOD_LEVEL;
        this.nextSavePlayerDataTime = Integer.MAX_VALUE;
        this.chunkLoadingRadius = AllayServer.getSettings().worldSettings().viewDistance();
        this.chunkMaxSendCountPerTick = AllayServer.getSettings().worldSettings().chunkMaxSendCountPerTick();
        // Player entity must have a non-null name tag, otherwise it will not be shown client-side
        this.nameTag = "Player";
        this.nameTagAlwaysShow = true;
        // Player entity is stored separately
        this.persistent = false;
    }

    public void setController(Player controller) {
        if (isActualPlayer()) {
            throw new IllegalStateException("Player's controller cannot be changed");
        }

        this.controller = controller;
    }

    @Override
    public void onPermissionChange() {
        if (isActualPlayer()) {
            this.controller.viewPlayerPermission(this.controller);
        }
    }

    @Override
    protected void saveUniqueId(NbtMapBuilder builder) {
        if (!isActualPlayer()) {
            // Only save the unique id from nbt if the player is a fake player
            super.saveUniqueId(builder);
        }
    }

    @Override
    protected void loadUniqueId(NbtMap nbt) {
        if (!isActualPlayer()) {
            // Only load the unique id from nbt if the player is a fake player
            super.loadUniqueId(nbt);
        }
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        var event = new PlayerGameModeChangeEvent(thisPlayer, this.gameMode, gameMode);
        if (!event.call() || this.gameMode == event.getNewGameMode()) {
            return;
        }

        gameMode = event.getNewGameMode();
        this.gameMode = gameMode;
        this.manager.callEvent(new CPlayerGameModeChangeEvent(this.gameMode));

        if (isActualPlayer()) {
            this.controller.viewPlayerGameMode(thisPlayer);
            // Send permission after game mode to make overriding client's state (e.g., mayfly) possible
            this.controller.viewPlayerPermission(this.controller);
        }
        forEachViewers(viewer -> viewer.viewPlayerGameMode(thisPlayer));
    }

    @Override
    public void setFlying(boolean flying) {
        if (this.flying != flying) {
            this.flying = flying;
            if (isActualPlayer()) {
                this.controller.viewPlayerPermission(this.controller);
            }
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
            tickItemUsingInAir(currentTick);
            // Update blocking state for edge cases (shield equip/unequip while sneaking, cooldown expiry)
            updateBlockingFlag();
        }

        tickPlayerDataAutoSave();
    }

    protected void tickItemUsingInAir(long currentTick) {
        if (!usingItemInAir) {
            return;
        }

        var itemInHand = thisPlayer.getItemInHand();
        if (itemInHand != null && !itemInHand.isEmptyOrAir()) {
            long usedTime = currentTick - startUsingItemInAirTime;
            itemInHand.onUseInAirTick(thisPlayer, usedTime);
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
                        (difficulty == Difficulty.HARD)
                ) {
                    thisPlayer.attack(DamageContainer.starve(1));
                }
            }
        }

        if (currentFoodLevel <= 6 && thisPlayer.isSprinting()) {
            setSprinting(false);
        }
    }

    protected void tickPlayerDataAutoSave() {
        if (!isActualPlayer()) {
            // Do not save fake players
            return;
        }

        // We use server's tick instead of world's tick
        // because player may teleport between worlds
        // and the tick in different worlds may not be same
        var currentServerTick = Server.getInstance().getTick();
        if (nextSavePlayerDataTime == Integer.MAX_VALUE) {
            nextSavePlayerDataTime = currentServerTick + AllayServer.getSettings().storageSettings().playerDataAutoSaveCycle();
            return;
        }
        if (currentServerTick >= nextSavePlayerDataTime) {
            Server.getInstance().getPlayerManager().getPlayerStorage().savePlayerData(this.controller);
            nextSavePlayerDataTime = currentServerTick + AllayServer.getSettings().storageSettings().playerDataAutoSaveCycle();
        }
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
        if (send && isActualPlayer()) {
            this.controller.sendCooldown(category, duration);
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
        this.controller.sendExperienceLevel(this.experienceLevel);
    }

    @Override
    public void setExperienceProgress(float value) {
        var event = new PlayerExperienceProgressChangeEvent(thisPlayer, this.experienceProgress, value);
        if (!event.call()) {
            return;
        }

        this.experienceProgress = value;
        this.controller.sendExperienceProgress(this.experienceProgress);
    }

    @Override
    public void setFoodLevel(int value) {
        value = Math.max(0, Math.min(value, MAX_FOOD_LEVEL));
        var event = new PlayerFoodLevelChangeEvent(thisPlayer, this.foodLevel, value);
        if (!event.call()) {
            return;
        }

        this.foodLevel = event.getNewFoodLevel();
        if (isActualPlayer()) {
            this.controller.sendFoodLevel(this.foodLevel);
        }
    }

    @Override
    public void setFoodSaturationLevel(float value) {
        this.foodSaturationLevel = Math.max(0, Math.min(value, MAX_FOOD_SATURATION_LEVEL));
        if (isActualPlayer()) {
            this.controller.sendFoodSaturationLevel(this.foodSaturationLevel);
        }
    }

    @Override
    public void setFoodExhaustionLevel(float value) {
        this.foodExhaustionLevel = Math.max(0, Math.min(value, MAX_FOOD_EXHAUSTION_LEVEL));
        if (isActualPlayer()) {
            this.controller.sendFoodExhaustionLevel(this.foodExhaustionLevel);
        }
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
        if (isActualPlayer()) {
            this.expectedTeleportPos = new Vector3d(target);
        }
    }

    @Override
    protected void teleportInDimension(Location3dc target) {
        super.teleportInDimension(target);
        // For player, we also need to send the move packet to client
        // However, there is no need to send the motion packet as we are teleporting the player
        if (isActualPlayer()) {
            this.controller.viewEntityLocation(thisPlayer, lastSentLocation, location, true);
        }
    }

    @Override
    protected void teleportOverDimension(Location3dc target) {
        if (isActualPlayer()) {
            var currentDim = (AllayDimension) location.dimension();
            var targetDim = (AllayDimension) target.dimension();
            if (currentDim.getWorld() != targetDim.getWorld()) {
                // Send new world's time
                this.controller.viewTime(targetDim.getWorld().getWorldData().getTimeOfDay());
                // Send new world's game rules
                this.controller.viewGameRules(targetDim.getWorld().getWorldData().getGameRules());
                this.controller.viewWeather(targetDim.getWorld().getWeather());
            }
            currentDim.removePlayer(this.controller, () -> {
                setLocationBeforeSpawn(target);
                if (currentDim.getDimensionInfo().dimensionId() != targetDim.getDimensionInfo().dimensionId()) {
                    // TODO: implement boolean changingDimension here
                    var packet1 = new ChangeDimensionPacket();
                    packet1.setDimension(targetDim.getDimensionInfo().dimensionId());
                    packet1.setPosition(Vector3f.from(target.x(), target.y() + 1.62f, target.z()));
                    this.controller.sendPacket(packet1);

                    // As of v1.19.50, the dimension ack that is meant to be sent by the client is now sent by the server. The client
                    // still sends the ack, but after the server has sent it. Thanks to Mojang for another groundbreaking change.
                    var packet2 = new PlayerActionPacket();
                    packet2.setAction(PlayerActionType.DIMENSION_CHANGE_SUCCESS);
                    packet2.setRuntimeEntityId(this.runtimeId);
                    packet2.setBlockPosition(org.cloudburstmc.math.vector.Vector3i.ZERO);
                    packet2.setResultPosition(org.cloudburstmc.math.vector.Vector3i.ZERO);
                    this.controller.sendPacket(packet2);
                }
                targetDim.addPlayer(this.controller, () -> {
                    this.controller.viewEntityLocation(thisPlayer, lastSentLocation, location, true);
                });
            });
        } else {
            super.teleportOverDimension(target);
        }
    }

    @Override
    public void spawnTo(WorldViewer viewer) {
        if (this.controller != viewer) {
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
        if (this.controller != viewer) {
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
            log.warn("Player {} is not using item in air", this.controller.getOriginName());
        }
        return this.startUsingItemInAirTime;
    }

    @Override
    public long getItemUsingInAirTime(long currentTime) {
        return currentTime - this.startUsingItemInAirTime;
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
                .putInt(TAG_ENCHANTMENT_SEED, this.enchantmentSeed)
                .putInt(TAG_PLAYER_GAME_MODE, toNetwork(this.gameMode).ordinal())
                // SpawnPoint
                .putCompound(TAG_SPAWN_POINT, saveSpawnPoint())
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
        nbt.listenForInt(TAG_ENCHANTMENT_SEED, this::setEnchantmentSeed);
        nbt.listenForInt(TAG_PLAYER_GAME_MODE, id -> this.gameMode = fromNetwork(GameType.from(id)));

        // SpawnPoint
        if (nbt.containsKey(TAG_SPAWN_POINT)) {
            loadSpawnPoint(nbt.getCompound(TAG_SPAWN_POINT));
        } else {
            this.spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
        }

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
                0, 0, dimension
        );
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

    @Override
    public void setUsingItemInAir(boolean value, long time) {
        this.usingItemInAir = value;
        if (value) {
            this.startUsingItemInAirTime = time;
        }
        // Update blocking state immediately when item use changes
        updateBlockingFlag();
        broadcastState();
    }

    @Override
    public void sendMessage(String message) {
        if (isActualPlayer()) {
            this.controller.sendMessage(message);
        }
    }

    @Override
    public void sendTranslatable(String translatable, Object... args) {
        if (isActualPlayer()) {
            this.controller.sendTranslatable(translatable, args);
        }
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
        if (isActualPlayer()) {
            this.controller.sendCommandOutputs(sender, status, permissions, outputs);
        }
    }

    @Override
    public void applyAction(EntityAction action) {
        super.applyAction(action);
        if (isActualPlayer()) {
            this.controller.viewEntityAction(thisPlayer, action);
        }
    }

    @Override
    public void applyAnimation(EntityAnimation animation) {
        super.applyAnimation(animation);
        if (isActualPlayer()) {
            this.controller.viewEntityAnimation(thisPlayer, animation);
        }
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
        if (isActualPlayer()) {
            this.controller.viewEntityState(thisPlayer);
        }
    }

    @Override
    public void setSprinting(boolean sprinting) {
        if (this.sprinting != sprinting) {
            this.sprinting = sprinting;
            if (sprinting) {
                setSneaking(false);
            }

            if (isActualPlayer()) {
                var speed = this.controller.getSpeed();
                if (sprinting) {
                    speed = speed.addMultiplier(0.3);
                } else {
                    speed = speed.addMultiplier(-0.3);
                }
                this.controller.setSpeed(speed);
            }

            broadcastState();
            new PlayerToggleSprintEvent(thisPlayer, sprinting).call();
        }
    }

    @Override
    public void setSneaking(boolean sneaking) {
        if (this.sneaking != sneaking) {
            this.sneaking = sneaking;
            if (sneaking && !this.flying) {
                setSprinting(false);
            }

            // Update blocking state when sneaking changes
            updateBlockingFlag();

            broadcastState();
            new PlayerToggleSneakEvent(thisPlayer, sneaking).call();
        }
    }

    protected void setBlocking(boolean blocking) {
        if (this.blocking != blocking) {
            this.blocking = blocking;
            broadcastState();
        }
    }

    /**
     * Updates the blocking flag based on the player's current state.
     * <p>
     * A player is considered blocking when:
     * <ul>
     *   <li>Shield is not on cooldown</li>
     *   <li>Player is sneaking</li>
     *   <li>Player is holding a shield in main hand or off hand</li>
     *   <li>Player is not using an item (e.g., drawing a bow/crossbow)</li>
     *   <li>Player is not using an item on a block</li>
     * </ul>
     */
    protected void updateBlockingFlag() {
        boolean shouldBlock = isCooldownEnd(ItemShieldBaseComponent.SHIELD_COOLDOWN_CATEGORY)
                && this.sneaking
                && !this.usingItemInAir
                && !this.usingItemOnBlock
                && isHoldingShield();

        if (this.blocking != shouldBlock) {
            setBlocking(shouldBlock);
        }
    }

    /**
     * Checks if the player is holding a shield in either main hand or off hand.
     *
     * @return {@code true} if holding a shield, {@code false} otherwise
     */
    protected boolean isHoldingShield() {
        var inventory = thisPlayer.getContainer(ContainerTypes.INVENTORY);
        var offhand = thisPlayer.getContainer(ContainerTypes.OFFHAND);

        var handItem = inventory.getItemInHand();
        var offhandItem = offhand.getOffhand();

        return handItem instanceof ItemShieldStack || offhandItem instanceof ItemShieldStack;
    }

    @Override
    public void setSwimming(boolean swimming) {
        if (this.swimming != swimming) {
            this.swimming = swimming;
            if (swimming) {
                setSneaking(false);
            }

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
            if (crawling) {
                setSneaking(false);
            }

            broadcastState();
            new PlayerToggleCrawlEvent(thisPlayer, crawling).call();
        }
    }

    @Override
    public void setUsingItemOnBlock(boolean usingItemOnBlock) {
        this.usingItemOnBlock = usingItemOnBlock;
        // Update blocking state immediately when item use on block changes
        updateBlockingFlag();
    }

    @Override
    public boolean hasEntityCollision() {
        return this.gameMode != GameMode.SPECTATOR;
    }

    @Override
    public AABBdc getBaseAABB() {
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

    @Override
    public WorldViewer toWorldViewer() {
        if (isActualPlayer()) {
            return this.controller;
        }

        return null;
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
