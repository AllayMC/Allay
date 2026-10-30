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
import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntityPortalEnterEvent;
import org.allaymc.api.eventbus.event.player.*;
import org.allaymc.api.item.component.ItemShieldBaseComponent;
import org.allaymc.api.item.interfaces.ItemShieldStack;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.PlayerAbility;
import org.allaymc.api.player.Skin;
import org.allaymc.api.primitiveshape.PrimitiveShape;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.AllayServer;
import org.allaymc.server.block.NetherPortalHelper;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.*;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.player.ChunkCache;
import org.allaymc.server.world.AllayDimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.protocol.bedrock.data.GameType;
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
 * {@code EntityPlayerBaseComponentImpl} manages player identity, gameplay
 * state, visibility, food, persistence, and controller feedback around the
 * shared entity lifecycle. A non-{@code null} {@code controller} distinguishes an
 * actual player from a fake player. Most controller messages are conditional on
 * that distinction; experience setters require a controller when their events
 * accept an update.
 *
 * <p>
 * Game-mode changes coordinate sibling components, client abilities, resource
 * resets, and spectator visibility. Food processing combines difficulty,
 * hunger, saturation, and exhaustion on entity ticks, while autosave deadlines
 * use server ticks so world transfers do not switch the scheduling clock.
 * Portal transfers start client loading before worker-based destination
 * preparation and finish client transfer messages through dimension callbacks.
 *
 * <p>
 * NBT retains player resources and a dimension-qualified spawn point. Spawn
 * loading accepts both string and numeric dimension identifiers and falls back
 * to the global spawn when the named world or dimension is unavailable.
 * Movement postures also govern collision height and shield presentation, so
 * setters refresh their dependent state before publishing the resulting entity
 * state.
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerBaseComponentImpl extends EntityBaseComponentImpl implements EntityPlayerBaseComponent {

    /**
     * {@code TAG_ENCHANTMENT_SEED} names the persisted enchanting random seed.
     * Together with {@code TAG_PLAYER_GAME_MODE}, it retains general player
     * configuration across NBT saves and loads.
     */
    protected static final String TAG_ENCHANTMENT_SEED = "EnchantmentSeed";
    /**
     * NBT key for the network game-mode identifier.
     */
    protected static final String TAG_PLAYER_GAME_MODE = "PlayerGameMode";

    /**
     * {@code TAG_SPAWN_POINT} owns the persisted personal-spawn compound. Its
     * position, world, dimension, and origin type let loading reconstruct a
     * dimension-qualified spawn or select the global fallback.
     */
    protected static final String TAG_SPAWN_POINT = "SpawnPoint";
    /**
     * NBT key for the spawn-origin enum ordinal.
     */
    protected static final String TAG_SPAWN_POINT_TYPE = "SpawnPointType";
    /**
     * Spawn-compound key for world display name.
     */
    protected static final String TAG_WORLD = "World";
    /**
     * Spawn-compound key for the dimension identifier.
     */
    protected static final String TAG_DIMENSION = "Dimension";

    /**
     * {@code TAG_PLAYER_LEVEL} and {@code TAG_PLAYER_LEVEL_PROGRESS} retain the
     * experience level and progress fraction separately so loading can restore
     * both parts of the player's experience state.
     */
    protected static final String TAG_PLAYER_LEVEL = "PlayerLevel";
    /**
     * NBT key for within-level experience progress.
     */
    protected static final String TAG_PLAYER_LEVEL_PROGRESS = "PlayerLevelProgress";

    /**
     * {@code TAG_FOOD_LEVEL}, {@code TAG_FOOD_SATURATION_LEVEL},
     * {@code TAG_FOOD_EXHAUSTION_LEVEL}, and {@code TAG_FOOD_TICK_TIMER} retain
     * hunger resources and their update cadence; loading assigns these
     * persisted values directly rather than calling the clamping setters.
     */
    protected static final String TAG_FOOD_LEVEL = "FoodLevel";
    /**
     * NBT key for the food-saturation reserve.
     */
    protected static final String TAG_FOOD_SATURATION_LEVEL = "FoodSaturationLevel";
    /**
     * NBT key for accumulated food exhaustion.
     */
    protected static final String TAG_FOOD_EXHAUSTION_LEVEL = "FoodExhaustionLevel";
    /**
     * NBT key for the hunger-update cycle position.
     */
    protected static final String TAG_FOOD_TICK_TIMER = "FoodTickTimer";

    /**
     * {@code FOOD_TICK_THRESHOLD} sets the eighty-tick hunger cycle.
     * Non-peaceful regeneration and starvation checks occur when
     * {@code foodTickTimer} wraps to zero.
     */
    protected static final int FOOD_TICK_THRESHOLD = 80;
    /**
     * {@code EXHAUSTION_MOVEMENT_THRESHOLD} batches swimming and sprinting
     * exhaustion until ten blocks accumulate, reducing the frequency of
     * food-resource updates sent by the setters.
     */
    protected static final int EXHAUSTION_MOVEMENT_THRESHOLD = 10;

    /**
     * Player entity owning this gameplay component.
     */
    @ComponentObject
    protected EntityPlayer thisPlayer;

    /**
     * {@code controller} identifies the client-backed player and starts as
     * {@code null}. {@code setController} permits initial binding but rejects
     * every later replacement once a non-{@code null} controller is present.
     */
    @Getter
    protected Player controller;
    /**
     * {@code gameMode} holds the current gameplay mode. Construction selects
     * the server default, NBT loading can replace it directly, and explicit
     * changes dispatch mode-dependent updates through {@code setGameMode}.
     */
    @Getter
    protected GameMode gameMode;
    /**
     * {@code phantom} explicitly hides the player from every viewer. Spectator
     * mode also makes {@code isPhantom} {@code true}, but {@code shouldBeVisibleTo} can
     * still show spectators to other spectator players.
     */
    protected boolean phantom;
    /**
     * {@code skin} retains the supplied skin reference. The generated getter
     * exposes that same object, and {@code setSkin} sends it to all connected
     * players rather than only the entity's current viewers.
     */
    @Getter
    protected Skin skin;
    /**
     * {@code spawnPoint} stores the dimension-qualified personal spawn.
     * Explicit setters copy the supplied location, while fallback paths retain
     * the global-spawn reference returned by the world pool.
     */
    protected Location3ic spawnPoint;
    /**
     * {@code spawnPointType} records how the personal spawn was established.
     * {@code loadSpawnPoint} and validation fallbacks select {@code FORCED};
     * block-based updates retain the supplied type, while loading an absent
     * spawn compound leaves the existing type unchanged.
     */
    @Getter
    protected SpawnPointType spawnPointType;

    /**
     * {@code expectedTeleportPos} holds a teleport destination awaiting client
     * movement acknowledgement; {@code null} permits ordinary movement
     * processing. The input processor clears it when the reported position is
     * accepted, and the physics engine skips queued client movement while it
     * remains non-{@code null}. The generated accessors retain and expose the same
     * vector reference.
     *
     * @see <a href="https://github.com/AllayMC/Allay/issues/517">Teleport acknowledgement and fall-distance issue</a>
     */
    @Getter
    @Setter
    protected Vector3dc expectedTeleportPos;

    /**
     * Random seed retained for enchanting option generation.
     */
    @Getter
    @Setter
    protected int enchantmentSeed;

    /**
     * {@code usingItemOnBlock} tracks block-targeted item use, and
     * {@code usingItemInAir} tracks untargeted held-item use. Either flag
     * suppresses shield presentation in {@code updateBlockingFlag}, while only
     * air use has a start-tick counter.
     */
    @Getter
    protected boolean usingItemOnBlock, usingItemInAir;
    /**
     * {@code startUsingItemInAirTime} records the entity tick of the latest
     * air-use start. Construction uses {@code -1}, and stopping use leaves the
     * last start tick stored rather than restoring that sentinel.
     */
    protected long startUsingItemInAirTime;

    /**
     * {@code cooldowns} maps categories to entity-tick expiry values. A missing
     * entry is expired; stored entries remain active through their exact expiry
     * tick and are not removed by expiry queries.
     */
    protected Map<String, Long> cooldowns;

    /**
     * {@code scoreTag} stores the additional player display tag. The setter
     * retains the supplied string, including {@code null}, and broadcasts state
     * on every call.
     */
    @Getter
    protected String scoreTag;
    /**
     * {@code sprinting} tracks the sprint-speed state; {@code sneaking} tracks
     * the crouched posture; {@code swimming} tracks swim posture;
     * {@code gliding} tracks elytra posture; {@code crawling} tracks crawl
     * posture; {@code flying} tracks ability-based flight; {@code blocking}
     * tracks shield presentation; and {@code spinAttacking} tracks spin-attack
     * presentation. Their setters update related postures and broadcast state,
     * except {@code setFlying}, which updates the controller's flight ability
     * when present.
     */
    @Getter
    protected boolean sprinting, sneaking, swimming, gliding, crawling, flying, blocking, spinAttacking;

    /**
     * {@code experienceLevel} stores whole experience levels used for display
     * and death experience drops. NBT loading assigns it directly, while
     * explicit changes pass through the cancellable experience-level event.
     */
    @Getter
    protected int experienceLevel;
    /**
     * {@code experienceProgress} stores the fraction within the current level.
     * Explicit updates validate the requested fraction through the event
     * constructor, while NBT loading assigns the stored value directly.
     */
    @Getter
    protected float experienceProgress;

    /**
     * {@code foodLevel} stores hunger points used by eating, sprinting,
     * regeneration, and starvation checks. Explicit updates clamp the request
     * before the food event; NBT loading assigns the stored value directly.
     */
    @Getter
    protected int foodLevel;
    /**
     * {@code foodSaturationLevel} stores the reserve consumed before hunger
     * points; {@code foodExhaustionLevel} stores pending exhaustion units.
     * {@code exhaust} consumes saturation first, then hunger, and applies both
     * resources through their clamping setters after calculating the complete
     * exhaustion cost.
     */
    @Getter
    protected float foodSaturationLevel, foodExhaustionLevel;
    /**
     * {@code foodTickTimer} advances only outside creative and spectator modes.
     * It wraps at {@code FOOD_TICK_THRESHOLD}, and its phase is persisted so
     * loading can continue the existing hunger-update cadence.
     */
    protected int foodTickTimer;
    /**
     * {@code swimDistance} accumulates swimming travel in blocks, and
     * {@code sprintDistance} accumulates sprinting travel in blocks.
     * Non-teleport moves add to each active counter independently; each counter
     * is exhausted and reset when it reaches
     * {@code EXHAUSTION_MOVEMENT_THRESHOLD}.
     */
    protected float swimDistance, sprintDistance;

    /**
     * {@code nextSavePlayerDataTime} holds the next autosave deadline on the
     * server tick clock. {@code Integer.MAX_VALUE} initially marks an
     * unscheduled deadline, which the first actual-player autosave tick
     * replaces with the configured cycle.
     */
    protected long nextSavePlayerDataTime;

    /**
     * {@code chunkLoadingRadius} stores the requested view radius in chunks.
     * Construction uses the server view distance, and the generated setter
     * stores later values without validation.
     */
    @Getter
    @Setter
    protected int chunkLoadingRadius;
    /**
     * Configured maximum chunk sends per player tick.
     */
    @Getter
    protected int chunkMaxSendCountPerTick;

    /**
     * {@code fishingHook} retains the player's current fishing hook reference.
     * The setter assigns it directly, and {@code getFishingHook} clears the
     * reference when the retained hook is no longer alive.
     */
    @Setter
    protected EntityFishingHook fishingHook;

    /**
     * Creates player gameplay state using server defaults and the supplied
     * entity data. The constructor starts full food and saturation, chooses a
     * random enchantment seed, and sets a visible {@code "Player"} name tag.
     * Player persistence is disabled in the entity chunk because actual player
     * data uses separate storage.
     */
    public EntityPlayerBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.gameMode = AllayServer.getSettings().genericSettings().defaultGameMode();
        this.spawnPointType = SpawnPointType.FORCED;
        this.enchantmentSeed = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        this.startUsingItemInAirTime = -1;
        this.cooldowns = new NonBlockingHashMap<>();
        this.foodLevel = MAX_FOOD_LEVEL;
        this.foodSaturationLevel = MAX_FOOD_SATURATION_LEVEL;
        this.nextSavePlayerDataTime = Integer.MAX_VALUE;
        this.chunkLoadingRadius = AllayServer.getSettings().worldSettings().viewDistance();
        this.chunkMaxSendCountPerTick = AllayServer.getSettings().worldSettings().chunkMaxSendCountPerTick();
        // The client requires a name tag for player presentation. Supply a visible
        // default before NBT or login data replaces it with the player's actual name.
        this.nameTag = "Player";
        this.nameTagAlwaysShow = true;
        // Player storage owns actual-player persistence. Excluding this entity from
        // chunk persistence avoids writing another copy through the chunk save path.
        this.persistent = false;
    }

    /**
     * Binds the controller while the player has no controller assigned. A
     * {@code null} argument leaves the entity fake and eligible for a later
     * binding; any call after a non-{@code null} binding throws before changing the
     * retained reference.
     *
     * @throws IllegalStateException if a controller is already assigned
     */
    public void setController(Player controller) {
        if (isActualPlayer()) {
            throw new IllegalStateException("Player's controller cannot be changed");
        }

        this.controller = controller;
    }

    /**
     * Refreshes abilities on the attached controller after a permission change.
     * Fake players have no controller to refresh, so this hook performs no
     * viewer broadcast or other state change for them.
     */
    @Override
    public void onPermissionChange() {
        if (isActualPlayer()) {
            this.controller.viewPlayerAbilities(this.controller);
        }
    }

    /**
     * Delegates unique-identifier persistence only when no controller is
     * attached. Actual players skip the inherited NBT write, while fake players
     * retain the base entity identifier representation in the supplied builder.
     */
    @Override
    protected void saveUniqueId(NbtMapBuilder builder) {
        if (!isActualPlayer()) {
            super.saveUniqueId(builder);
        }
    }

    /**
     * Delegates unique-identifier loading only when no controller is attached.
     * Actual players leave their current identifier unchanged through this
     * hook, while fake players use the inherited NBT loader and its
     * representation rules.
     */
    @Override
    protected void loadUniqueId(NbtMap nbt) {
        if (!isActualPlayer()) {
            super.loadUniqueId(nbt);
        }
    }

    /**
     * Applies an accepted game-mode change and refreshes dependent gameplay and
     * client state. Cancellation or an unchanged event result leaves the
     * current mode intact. An accepted mode is stored and dispatched to sibling
     * components before resource resets, ability changes, and viewer
     * notifications.
     *
     * <p>
     * Creative and spectator modes request full food through its cancellable
     * setter and reset saturation, exhaustion, and air supply. For actual
     * players, old abilities are disabled and new
     * abilities enabled before the controller receives game mode followed by
     * its final ability state. Entering or leaving spectator mode also
     * refreshes visibility in both viewing directions.
     */
    @Override
    public void setGameMode(GameMode gameMode) {
        var event = new PlayerGameModeChangeEvent(thisPlayer, this.gameMode, gameMode);
        if (!event.call() || this.gameMode == event.getNewGameMode()) {
            return;
        }

        gameMode = event.getNewGameMode();
        var oldGameMode = this.gameMode;
        this.gameMode = gameMode;
        this.manager.callEvent(new CPlayerGameModeChangeEvent(this.gameMode));

        if (gameMode == GameMode.CREATIVE || gameMode == GameMode.SPECTATOR) {
            setFoodLevel(MAX_FOOD_LEVEL);
            setFoodSaturationLevel(MAX_FOOD_SATURATION_LEVEL);
            setFoodExhaustionLevel(0);
            thisPlayer.setAirSupplyTicks(thisPlayer.getAirSupplyMaxTicks());
        }

        if (isActualPlayer()) {
            this.controller.setAbilities(oldGameMode.getAbilities(), false);
            this.controller.setAbilities(gameMode.getAbilities(), true);
        }

        if (isActualPlayer()) {
            this.controller.viewPlayerGameMode(thisPlayer);
            // Send final abilities after game mode because the mode message can reset
            // client ability state, including flight permissions supplied by the server.
            this.controller.viewPlayerAbilities(this.controller);
        }
        forEachViewers(viewer -> viewer.viewPlayerGameMode(thisPlayer));

        if (isActualPlayer() && (oldGameMode == GameMode.SPECTATOR || this.gameMode == GameMode.SPECTATOR)) {
            refreshPhantom();
            refreshObservedPlayers();
        }
    }

    /**
     * Changes the explicit phantom flag and refreshes this player's viewer
     * membership. An unchanged value performs no work. Spectator mode remains
     * an independent source of phantom behavior even when the explicit flag is
     * cleared.
     */
    @Override
    public void setPhantom(boolean phantom) {
        if (this.phantom == phantom) {
            return;
        }

        this.phantom = phantom;
        refreshPhantom();
    }

    /**
     * Returns whether spectator mode or the explicit phantom flag makes this
     * player phantom. Clearing the explicit flag does not change the result
     * while the current {@code gameMode} remains {@code SPECTATOR}.
     */
    @Override
    public boolean isPhantom() {
        return this.gameMode == GameMode.SPECTATOR || this.phantom;
    }

    /**
     * Refreshes current and potential viewers after a visibility-rule change.
     * Only an alive actual player is processed. The method removes disallowed
     * viewers from a snapshot, then asks loaders of the current chunk to spawn
     * this player when a viewer is not already registered.
     */
    protected void refreshPhantom() {
        if (!isActualPlayer() || !isAlive()) {
            return;
        }

        // Despawning mutates viewer membership. Iterate a snapshot so removals
        // do not invalidate traversal of the set being updated by this refresh.
        for (var viewer : new ArrayList<>(getViewers())) {
            if (!shouldBeVisibleTo(viewer)) {
                despawnFrom(viewer);
            }
        }

        var chunk = thisPlayer.getCurrentChunk();
        if (chunk == null) {
            return;
        }

        chunk.getChunkLoaders().stream()
                .filter(loader -> loader != thisPlayer)
                .map(loader -> loader.toWorldViewer())
                .filter(Objects::nonNull)
                .filter(viewer -> !getViewers().contains(viewer))
                .forEach(this::spawnTo);
    }

    /**
     * Returns whether this player's visibility rules permit the supplied
     * viewer. The explicit phantom flag hides the entity from everyone. An
     * ordinary player is visible, while a spectator is visible only to a player
     * controller whose controlled entity is also a spectator.
     */
    protected boolean shouldBeVisibleTo(WorldViewer viewer) {
        if (this.phantom) {
            return false;
        }

        if (this.gameMode != GameMode.SPECTATOR) {
            return true;
        }

        if (!(viewer instanceof Player player)) {
            return false;
        }

        var viewerEntity = player.getControlledEntity();
        return viewerEntity != null && viewerEntity.getGameMode() == GameMode.SPECTATOR;
    }

    /**
     * Rechecks nearby phantom and spectator players from this controller's
     * viewpoint. Fake players perform no refresh. The method scans a circular
     * chunk radius around the player's current location and tracks runtime
     * identifiers so each qualifying other player is processed at most once
     * during this scan.
     *
     * <p>
     * Each qualifying player is despawned and then asked to spawn again to this
     * controller. Its own visibility gate decides whether the spawn proceeds,
     * allowing a spectator-mode transition to update what this player can see
     * as well as who can see this player.
     */
    protected void refreshObservedPlayers() {
        if (!isActualPlayer()) {
            return;
        }

        var location = thisPlayer.getLocation();
        // Integer conversion truncates fractional negative coordinates before the
        // chunk shift, so a position just below zero selects the zero-side center.
        var centerChunkX = (int) location.x() >> 4;
        var centerChunkZ = (int) location.z() >> 4;
        var radius = thisPlayer.getChunkLoadingRadius();
        // Runtime identifiers deduplicate players encountered by the chunk scan,
        // avoiding repeated despawn and spawn messages for the same observed player.
        var refreshed = new HashSet<Long>();

        for (int rx = -radius; rx <= radius; rx++) {
            for (int rz = -radius; rz <= radius; rz++) {
                if (rx * rx + rz * rz > radius * radius) {
                    continue;
                }

                thisPlayer.getDimension().getEntityManager().forEachEntitiesInChunkImmediately(centerChunkX + rx, centerChunkZ + rz, entity -> {
                    if (!(entity instanceof EntityPlayer player) || player == thisPlayer) {
                        return;
                    }

                    if (player.getGameMode() != GameMode.SPECTATOR && !player.isPhantom()) {
                        return;
                    }

                    if (!refreshed.add(player.getRuntimeId())) {
                        return;
                    }

                    player.despawnFrom(this.controller);
                    player.spawnTo(this.controller);
                });
            }
        }
    }

    /**
     * Updates the flight flag and attached controller ability when the value
     * changes. An unchanged request has no effect. This setter does not
     * broadcast entity state; its controller update uses
     * {@code PlayerAbility.FLYING} directly.
     */
    @Override
    public void setFlying(boolean flying) {
        if (this.flying != flying) {
            this.flying = flying;
            if (isActualPlayer()) {
                this.controller.setAbility(PlayerAbility.FLYING, flying);
            }
        }
    }

    /**
     * Stores the supplied score tag and broadcasts updated entity state. The
     * value is retained without validation, including {@code null}, and the
     * broadcast occurs even when the new tag equals the current tag.
     */
    @Override
    public void setScoreTag(String scoreTag) {
        this.scoreTag = scoreTag;
        broadcastState();
    }

    /**
     * Runs the base tick, player resource updates, and autosave scheduling. An
     * alive player also ticks food, held-item use, and shield presentation.
     * Autosave is checked independently of that alive-state gate and applies
     * only to actual players.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);

        if (isAlive()) {
            tickFood();
            tickItemUsingInAir(currentTick);
            updateBlockingFlag();
        }

        tickPlayerDataAutoSave();
    }

    /**
     * Ticks the current held item while air-use state remains active. An absent
     * or empty hand item receives no callback. The callback duration is the
     * supplied {@code currentTick} minus the stored start tick, measured in
     * entity ticks.
     */
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

    /**
     * Advances the hunger cycle and applies difficulty-dependent regeneration
     * or starvation. Creative and spectator modes skip the cycle entirely.
     * Peaceful mode attempts a food-point increase every ten cycle ticks and
     * health regeneration every twenty, without charging exhaustion for that
     * peaceful regeneration.
     *
     * <p>
     * Other difficulties check when the eighty-tick cycle wraps. At least
     * eighteen food points enable exhaustion-charging regeneration; zero food
     * enables starvation only above the easy or normal pre-attack threshold, or at any
     * health on hard difficulty. The food snapshot taken before these updates
     * also stops sprinting when it is six or lower.
     */
    protected void tickFood() {
        if (this.gameMode == GameMode.CREATIVE || this.gameMode == GameMode.SPECTATOR) {
            return;
        }

        this.foodTickTimer++;
        // Preserve the saved cycle phase while wrapping the hunger clock; a zero
        // phase triggers the non-peaceful regeneration or starvation decision below.
        if (this.foodTickTimer >= FOOD_TICK_THRESHOLD) {
            this.foodTickTimer = 0;
        }

        var currentFoodLevel = getFoodLevel();
        var difficulty = getWorld().getWorldData().getDifficulty();
        // Peaceful recovery uses ten-tick food pulses and twenty-tick health
        // pulses within the same persisted hunger cycle rather than another timer.
        if (difficulty == Difficulty.PEACEFUL && foodTickTimer % 10 == 0) {
            if (currentFoodLevel < MAX_FOOD_LEVEL) {
                setFoodLevel(currentFoodLevel + 1);
            }
            if (foodTickTimer % 20 == 0) {
                regenerate(false);
            }
        }

        if (foodTickTimer == 0 && difficulty != Difficulty.PEACEFUL) {
            // Regeneration adds six exhaustion units to an existing four-unit
            // cycle, so pending exhaustion can make this recovery spend multiple reserve points.
            if (currentFoodLevel >= 18) {
                regenerate(true);
            } else if (currentFoodLevel == 0) {
                if (
                        // Easy and normal test pre-attack health thresholds rather than
                        // enforce post-damage floors; fractional health or event changes can still make damage lethal.
                        (difficulty == Difficulty.EASY && thisPlayer.getHealth() > 10) ||
                        (difficulty == Difficulty.NORMAL && thisPlayer.getHealth() > 1) ||
                        (difficulty == Difficulty.HARD)
                ) {
                    thisPlayer.attack(DamageContainer.starve(1));
                }
            }
        }

        // Peaceful refill can change stored hunger after the snapshot. This check
        // still uses that earlier value, so a refill above six does not preserve sprinting here.
        if (currentFoodLevel <= 6 && thisPlayer.isSprinting()) {
            setSprinting(false);
        }
    }

    /**
     * Schedules and requests periodic player-data saves using the server tick
     * clock. Fake players are skipped. The initial sentinel installs the next
     * configured deadline without saving, and a reached deadline requests a
     * save before setting the following deadline from the current server tick.
     *
     * <p>
     * The server clock remains the reference when players change worlds with
     * different world-tick counters. This helper does not await the storage
     * result or add a retry policy; its next deadline advances after the save
     * request returns.
     */
    protected void tickPlayerDataAutoSave() {
        if (!isActualPlayer()) {
            return;
        }

        // Keep deadlines on the server clock across world transfers; switching to
        // a destination world's unrelated tick counter would shift the autosave interval.
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

    /**
     * Attempts to restore one health point when current health differs from its
     * maximum. When {@code exhaust} is {@code true}, the method charges six
     * exhaustion units after the health request, even if the delegated
     * health-change event rejects that request.
     */
    protected void regenerate(boolean exhaust) {
        if (thisPlayer.getHealth() == thisPlayer.getMaxHealth()) {
            return;
        }

        thisPlayer.setHealth(thisPlayer.getHealth() + 1);
        if (exhaust) {
            exhaust(6);
        }
    }

    /**
     * Stores a category expiry relative to the current entity tick. The
     * supplied duration is not normalized, and expiry remains active through
     * its exact deadline tick. An attached controller receives the duration
     * only when {@code send} is {@code true}.
     */
    @Override
    public void setCooldown(String category, int duration, boolean send) {
        this.cooldowns.put(category, this.tick + duration);
        if (send && isActualPlayer()) {
            this.controller.sendCooldown(category, duration);
        }
    }

    /**
     * Returns whether a category is absent or its stored deadline precedes this
     * tick. A deadline equal to the current entity tick remains active, and the
     * query leaves expired entries in the cooldown map.
     */
    @Override
    public boolean isCooldownEnd(String category) {
        var coolDown = cooldowns.get(category);
        if (coolDown == null) {
            return true;
        }

        return coolDown < this.tick;
    }

    /**
     * Stores and sends the experience level accepted by the cancellable change
     * event. The event validates {@code value} from {@code 0} through
     * {@code 24791} and can replace it. An accepted update requires an attached
     * controller for the send, which occurs after the field changes.
     *
     * @throws IllegalArgumentException if {@code value} is outside the supported range
     */
    @Override
    public void setExperienceLevel(int value) {
        var event = new PlayerExperienceLevelChangeEvent(thisPlayer, this.experienceLevel, value);
        if (!event.call()) {
            return;
        }

        this.experienceLevel = event.getNewExperienceLevel();
        this.controller.sendExperienceLevel(this.experienceLevel);
    }

    /**
     * Stores the requested experience fraction after the change event approves
     * it. The event constructor validates {@code value} from {@code 0} through
     * {@code 1}; listener replacements are currently ignored. An accepted
     * update requires a controller and sends the original argument after
     * storing it.
     *
     * @throws IllegalArgumentException if {@code value} is outside the supported range
     */
    @Override
    public void setExperienceProgress(float value) {
        var event = new PlayerExperienceProgressChangeEvent(thisPlayer, this.experienceProgress, value);
        if (!event.call()) {
            return;
        }

        this.experienceProgress = value;
        this.controller.sendExperienceProgress(this.experienceProgress);
    }

    /**
     * Clamps the requested food level before dispatching a change event. An
     * unchanged clamped value skips dispatch. Cancellation leaves stored food
     * unchanged; approval stores the event's replacement and sends it to an
     * attached controller.
     */
    @Override
    public void setFoodLevel(int value) {
        value = Math.max(0, Math.min(value, MAX_FOOD_LEVEL));

        if (this.foodLevel != value) {
            var event = new PlayerFoodLevelChangeEvent(thisPlayer, this.foodLevel, value);
            if (!event.call()) {
                return;
            }

            this.foodLevel = event.getNewFoodLevel();
            if (isActualPlayer()) {
                this.controller.sendFoodLevel(this.foodLevel);
            }
        }
    }

    /**
     * Clamps saturation to its configured range and reports the stored reserve.
     * The update always stores the clamped value, including when unchanged, and
     * sends it only when a controller is attached to this player.
     */
    @Override
    public void setFoodSaturationLevel(float value) {
        this.foodSaturationLevel = Math.max(0, Math.min(value, MAX_FOOD_SATURATION_LEVEL));
        if (isActualPlayer()) {
            this.controller.sendFoodSaturationLevel(this.foodSaturationLevel);
        }
    }

    /**
     * Clamps exhaustion to its configured range and reports the stored amount.
     * The update always stores the clamped value, including when unchanged, and
     * sends it only when a controller is attached to this player.
     */
    @Override
    public void setFoodExhaustionLevel(float value) {
        this.foodExhaustionLevel = Math.max(0, Math.min(value, MAX_FOOD_EXHAUSTION_LEVEL));
        if (isActualPlayer()) {
            this.controller.sendFoodExhaustionLevel(this.foodExhaustionLevel);
        }
    }

    /**
     * Adds food and saturation while limiting the new reserve to accepted
     * hunger points. Food updates occur first through {@code setFoodLevel}, so
     * cancellation or listener replacement determines the food value used for
     * the saturation cap. The saturation setter then applies its own configured
     * bounds.
     *
     * <p>
     * This ordering matters when the requested food increase is rejected:
     * saturation uses the resulting stored food rather than assuming that the
     * requested increase succeeded. Negative additions are also passed through
     * these setters and their clamping behavior.
     */
    @Override
    public void saturate(int food, float saturation) {
        setFoodLevel(getFoodLevel() + food);
        setFoodSaturationLevel(Math.min(getFoodSaturationLevel() + saturation, getFoodLevel()));
    }

    /**
     * Converts accumulated exhaustion into saturation loss and then hunger
     * loss. Creative mode, spectator mode, and peaceful difficulty leave
     * resources unchanged. Other players add the supplied {@code level} to
     * pending exhaustion and consume one resource point for each complete
     * exhaustion threshold reached.
     *
     * <p>
     * Each threshold first removes saturation when any remains; only a later
     * threshold with no saturation removes a hunger point. The method
     * calculates all three resulting values locally before applying exhaustion,
     * saturation, and food setters. Those setters retain their clamping and
     * food-event behavior, so a cancelled food update can leave the computed
     * hunger loss unapplied.
     */
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

        // Consume complete exhaustion units against local resource values first,
        // so each later unit sees saturation already spent by the preceding one.
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

    /**
     * Returns whether hunger, creative mode, or peaceful difficulty allows
     * eating. Ordinary players need fewer than twenty food points; creative
     * players and players in peaceful worlds remain eligible regardless of the
     * stored hunger level.
     */
    @Override
    public boolean canEat() {
        // This literal matches MAX_FOOD_LEVEL, tying ordinary eating eligibility
        // to the same hunger cap enforced by the explicit food-level setter.
        return getFoodLevel() < 20 ||
               thisPlayer.getGameMode() == GameMode.CREATIVE ||
               thisPlayer.getWorld().getWorldData().getDifficulty() == Difficulty.PEACEFUL;
    }

    /**
     * Returns the retained fishing hook after clearing a hook that is no longer
     * alive. A missing or dead hook yields {@code null}, and this query can
     * mutate the stored reference through the generated setter.
     */
    @Override
    public EntityFishingHook getFishingHook() {
        if (this.fishingHook != null && !this.fishingHook.isAlive()) {
            // A dead hook remains referenced until queried. Clear it here so callers
            // can treat the returned null as permission to create another active hook.
            setFishingHook(null);
        }

        return this.fishingHook;
    }

    /**
     * Applies inherited block-collision callbacks only when the player is not
     * phantom. The explicit phantom flag and spectator mode both bypass these
     * world interactions through {@code isPhantom}, while other players use the
     * base handler.
     */
    @Override
    protected void tickBlockCollision() {
        if (isPhantom()) {
            return;
        }

        super.tickBlockCollision();
    }

    /**
     * Runs base teleport preparation and records an actual player's expected
     * destination. The destination coordinates are copied into a new vector so
     * subsequent client movement waits for acknowledgement against the copied
     * destination; fake players do not install this gate.
     */
    @Override
    protected void beforeTeleport(Location3dc target) {
        super.beforeTeleport(target);
        if (isActualPlayer()) {
            // Install the gate before placement publishes teleport movement. Even
            // client moves already queued by the input thread must wait for destination acknowledgement.
            this.expectedTeleportPos = new Vector3d(target);
        }
    }

    /**
     * Performs the inherited same-dimension teleport and informs the attached
     * controller. The controller receives a teleporting location update after
     * the base movement broadcast; this path does not send a separate motion
     * request to the client.
     */
    @Override
    protected void teleportInDimension(Location3dc target) {
        super.teleportInDimension(target);
        // The owning client is not an ordinary viewer of itself. Send its teleport
        // location explicitly after the inherited broadcast reaches other viewers.
        if (isActualPlayer()) {
            this.controller.viewEntityLocation(thisPlayer, location, true);
        }
    }

    /**
     * Starts player portal travel with client loading feedback before
     * destination preparation. Fake players delegate to the base
     * implementation. Actual players require an available Overworld or Nether
     * destination and an uncancelled portal-entry event before cooldown state
     * changes and the loading screen begins.
     *
     * <p>
     * The virtual-thread worker runs the helper that joins destination chunk
     * loads. A returned {@code false} result clears portal cooldown and
     * completes the loading screen; successful transfer completion is handled
     * by the dimension-transfer callbacks. This continuation does not handle
     * thrown failures or a helper future that remains incomplete, so those
     * paths do not perform this failure cleanup.
     */
    @Override
    protected void performNetherPortalTeleport() {
        if (!isActualPlayer()) {
            super.performNetherPortalTeleport();
            return;
        }

        var world = thisEntity.getWorld();
        var currentDimType = thisEntity.getDimension().getDimensionType();
        Dimension targetDim;
        if (currentDimType == DimensionTypes.OVERWORLD) {
            targetDim = world.getNether();
        } else if (currentDimType == DimensionTypes.NETHER) {
            targetDim = world.getOverWorld();
        } else {
            return;
        }
        if (targetDim == null) return;

        if (!new EntityPortalEnterEvent(thisEntity, EntityPortalEnterEvent.PortalType.NETHER).call()) {
            return;
        }

        portalCooldown = PORTAL_COOLDOWN_TICKS;
        portalTicks = 0;

        // Begin client loading before submitting portal preparation, because the
        // worker can wait for destination chunks before a transfer is scheduled.
        var loc = thisEntity.getLocation();
        var targetDimType = targetDim.getDimensionType();
        double targetX = NetherPortalHelper.scaleCoordinate(loc.x(), currentDimType, targetDimType);
        double targetZ = NetherPortalHelper.scaleCoordinate(loc.z(), currentDimType, targetDimType);
        this.controller.beginDimensionChange(targetDimType, targetX, loc.y(), targetZ);

        // Portal preparation joins destination chunk loads. Run it on a worker
        // rather than making the current dimension tick wait for those loads.
        Server.getInstance().getVirtualThreadPool().submit(() -> {
            NetherPortalHelper.teleport(thisEntity).thenAccept(success -> {
                if (!success) {
                    portalCooldown = 0;
                    this.controller.completeDimensionChange();
                }
            });
        });
    }

    /**
     * Transfers an actual player through dimension membership callbacks and
     * controller updates. A world change sends the destination world's time,
     * rules, and weather before removing the player. The chunk cache is cleared
     * before source removal so the destination does not reuse the old world's
     * cached chunks.
     *
     * <p>
     * The removal callback installs the destination location, begins a
     * dimension change if one was not already started, and adds the player to
     * the target. The target callback completes dimension-change feedback when
     * dimension types differ, then sends the final teleport location. Fake
     * players delegate to the base transfer path, and failures do not add
     * rollback here.
     */
    @Override
    protected void teleportOverDimension(Location3dc target) {
        if (isActualPlayer()) {
            var currentDim = (AllayDimension) location.dimension();
            var targetDim = (AllayDimension) target.dimension();
            if (currentDim.getWorld() != targetDim.getWorld()) {
                this.controller.viewTime(targetDim.getWorld().getWorldData().getTimeOfDay());
                this.controller.viewGameRules(targetDim.getWorld().getWorldData().getGameRules());
                this.controller.viewWeather(targetDim.getWorld().getWeather());
            }
            boolean dimensionChanged = currentDim.getDimensionType() != targetDim.getDimensionType();
            // Source and destination worlds can reuse chunk coordinates. Clear the
            // player cache before transfer so those coordinates receive destination data.
            ChunkCache.getInstance().clearPlayer(((AllayPlayer) this.controller).getLoginData().getUuid());
            currentDim.removePlayer(this.controller, () -> {
                setLocationBeforeSpawn(target);
                if (dimensionChanged && !this.controller.isChangingDimension()) {
                    // Portal travel may already have started client loading. Begin it here
                    // only when this transfer still lacks that dimension-change phase.
                    this.controller.beginDimensionChange(targetDim.getDimensionType(), target.x(), target.y(), target.z());
                }
                targetDim.addPlayer(this.controller, () -> {
                    if (dimensionChanged) {
                        // Complete client dimension feedback only after target membership is
                        // established, before publishing the final location for this transfer.
                        this.controller.completeDimensionChange();
                    }
                    this.controller.viewEntityLocation(thisPlayer, location, true);
                });
            });
        } else {
            super.teleportOverDimension(target);
        }
    }

    /**
     * Spawns this player to another eligible viewer with current equipment and
     * skin. The owning controller is skipped, and the visibility gate can
     * reject phantom or spectator presentation. Equipment and skin follow the
     * base spawn so the viewer can associate them with the player entity.
     */
    @Override
    public void spawnTo(WorldViewer viewer) {
        if (this.controller != viewer && shouldBeVisibleTo(viewer)) {
            super.spawnTo(viewer);
            viewer.viewEntityArmors(thisPlayer);
            viewer.viewEntityHand(thisPlayer);
            viewer.viewEntityOffhand(thisPlayer);
            // Send skin with every observer spawn so a newly visible or respawned
            // player carries its current appearance instead of relying on earlier skin state.
            viewer.viewPlayerSkin(thisPlayer);
        }
    }

    /**
     * Removes this player from a viewer other than its owning controller. The
     * controller is skipped because its own player presentation is managed by
     * player transfer and lifecycle handling rather than ordinary observer
     * removal.
     */
    @Override
    public void despawnFrom(WorldViewer viewer) {
        if (this.controller != viewer) {
            super.despawnFrom(viewer);
        }
    }

    /**
     * Broadcasts movement and converts accumulated swimming or sprinting
     * distance into exhaustion. Teleporting updates do not add travel costs.
     * Other updates measure the entity's stored last and current locations,
     * then add that distance independently to each movement counter whose
     * corresponding state is active.
     *
     * <p>
     * Each counter accumulates until ten blocks are reached, charges exhaustion
     * for its entire accumulated distance, and resets to zero. Swimming uses a
     * factor of {@code 0.01}, while sprinting uses {@code 0.1}; overlapping
     * states can therefore charge both counters for the same movement.
     */
    @Override
    public void broadcastMoveToViewers(Location3dc newLocation, boolean teleporting) {
        super.broadcastMoveToViewers(newLocation, teleporting);
        if (!teleporting) {
            var distance = (float) thisPlayer.getLastLocation().distance(thisPlayer.getLocation());

            // Keep swimming and sprinting meters independent: each active state
            // charges its own accumulated travel, including distance beyond the batching threshold.
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

    /**
     * Returns the entity tick retained from the latest air-use start. Inactive
     * use logs a warning through the controller but still returns the stored
     * value, which is {@code -1} before any use; that warning path requires a
     * controller.
     */
    public long getStartUsingItemInAirTime() {
        if (!isUsingItemInAir()) {
            log.warn("Player {} is not using item in air", this.controller.getOriginName());
        }
        return this.startUsingItemInAirTime;
    }

    /**
     * Returns current entity ticks elapsed since the retained air-use start.
     * The calculation does not check whether use is active, so callers needing
     * an active duration must first inspect {@code isUsingItemInAir}; the
     * initial start sentinel is {@code -1}.
     */
    @Override
    public long getItemUsingInAirTime() {
        return this.tick - this.startUsingItemInAirTime;
    }

    /**
     * Retains the supplied skin reference and broadcasts it to all connected
     * players. The broadcast is not limited to this entity's current viewers,
     * and no copy or {@code null} validation is performed before controller
     * presentation.
     */
    @Override
    public void setSkin(Skin skin) {
        this.skin = skin;
        var server = Server.getInstance();
        server.getPlayerManager().forEachPlayer(player -> player.viewPlayerSkin(thisPlayer));
    }

    /**
     * Builds player NBT by extending the base entity's saved compound. The
     * result includes general state, the dimension-qualified spawn, experience,
     * food resources, and hunger timing; component save handlers also
     * contribute container and living state through the base save operation.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putInt(TAG_ENCHANTMENT_SEED, this.enchantmentSeed)
                .putInt(TAG_PLAYER_GAME_MODE, toNetwork(this.gameMode).ordinal())
                .putCompound(TAG_SPAWN_POINT, saveSpawnPoint())
                .putInt(TAG_PLAYER_LEVEL, this.experienceLevel)
                .putFloat(TAG_PLAYER_LEVEL_PROGRESS, this.experienceProgress)
                .putInt(TAG_FOOD_LEVEL, foodLevel)
                .putFloat(TAG_FOOD_SATURATION_LEVEL, this.foodSaturationLevel)
                .putFloat(TAG_FOOD_EXHAUSTION_LEVEL, this.foodExhaustionLevel)
                .putInt(TAG_FOOD_TICK_TIMER, this.foodTickTimer)
                .build();
    }

    /**
     * Serializes the current spawn coordinates, world, dimension identifier,
     * and origin type. The retained spawn must have a dimension and world, and
     * its type must be non-{@code null}. World lookup later uses the stored world
     * display name, while dimension lookup accepts this serialized identifier.
     */
    protected NbtMap saveSpawnPoint() {
        var builder = NbtMap.builder()
                .putString(TAG_WORLD, spawnPoint.dimension().getWorld().getWorldData().getDisplayName())
                .putString(TAG_DIMENSION, spawnPoint.dimension().getDimensionType().getIdentifier().toString())
                .putInt(TAG_SPAWN_POINT_TYPE, spawnPointType.ordinal());
        AllayNBTUtils.writeVector3i(builder, TAG_POS, spawnPoint);
        return builder.build();
    }

    /**
     * Restores base and player NBT state without replaying gameplay setters.
     * Present typed entries replace the corresponding fields directly. An
     * absent spawn compound selects the world pool's global spawn, while a
     * present compound uses {@code loadSpawnPoint} and its compatibility and
     * fallback rules.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_ENCHANTMENT_SEED, this::setEnchantmentSeed);
        nbt.listenForInt(TAG_PLAYER_GAME_MODE, id -> this.gameMode = fromNetwork(GameType.from(id)));

        // Older data can omit the spawn compound. Select the world pool's
        // global spawn rather than leaving the personal spawn reference uninitialized.
        if (nbt.containsKey(TAG_SPAWN_POINT)) {
            loadSpawnPoint(nbt.getCompound(TAG_SPAWN_POINT));
        } else {
            this.spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
        }

        nbt.listenForInt(TAG_PLAYER_LEVEL, value -> this.experienceLevel = value);
        nbt.listenForFloat(TAG_PLAYER_LEVEL_PROGRESS, value -> this.experienceProgress = value);

        nbt.listenForInt(TAG_FOOD_LEVEL, value -> this.foodLevel = value);
        nbt.listenForFloat(TAG_FOOD_SATURATION_LEVEL, value -> this.foodSaturationLevel = value);
        nbt.listenForFloat(TAG_FOOD_EXHAUSTION_LEVEL, value -> this.foodExhaustionLevel = value);
        nbt.listenForInt(TAG_FOOD_TICK_TIMER, value -> this.foodTickTimer = value);
    }

    /**
     * Restores a dimension-qualified spawn from current or legacy NBT
     * representations. The stored world name must resolve first. String
     * dimension values are parsed as identifiers, numeric values use the legacy
     * numeric lookup, and absent or other values select that world's Overworld.
     *
     * <p>
     * An unavailable world or dimension replaces both spawn and type with the
     * global spawn and {@code FORCED}. A resolved dimension uses the stored
     * integer position; a missing origin-type tag defaults to {@code FORCED}.
     * Invalid identifier text propagates its parsing failure instead of taking
     * the unavailable-dimension fallback.
     */
    protected void loadSpawnPoint(NbtMap nbt) {
        var world = Server.getInstance().getWorldPool().getWorld(nbt.getString(TAG_WORLD));
        // An unavailable saved world cannot supply a usable spawn dimension.
        // Replace both the location and its block-derived type with the global fallback.
        if (world == null) {
            this.spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
            this.spawnPointType = SpawnPointType.FORCED;
            return;
        }

        // Accept both current identifier strings and legacy numeric dimension
        // identifiers so older player data can still resolve its saved spawn dimension.
        var dimension = switch (nbt.get(TAG_DIMENSION)) {
            case String dimensionId -> world.getDimension(new Identifier(dimensionId));
            case Number dimensionId -> world.getDimension(dimensionId.intValue());
            case null, default -> world.getDimension(DimensionTypes.OVERWORLD);
        };
        // A missing dimension invalidates the saved world-relative coordinates.
        // Use the global spawn and clear any bed or anchor origin together.
        if (dimension == null) {
            this.spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
            this.spawnPointType = SpawnPointType.FORCED;
            return;
        }

        this.spawnPoint = new Location3i(
                AllayNBTUtils.readVector3i(nbt, TAG_POS),
                0, 0, dimension
        );
        // Data without an origin type predates block-specific spawn validation.
        // Treat that saved location as forced rather than assuming a bed or anchor.
        this.spawnPointType = SpawnPointType.fromId(nbt.getInt(TAG_SPAWN_POINT_TYPE, SpawnPointType.FORCED.ordinal()));
    }

    /**
     * Returns the retained personal spawn after checking its dimension and
     * world state. A missing dimension or non-running world replaces the stored
     * spawn with the global reference and marks it {@code FORCED}. The returned
     * reference is not a copy, and this check does not validate bed or anchor
     * blocks.
     */
    @Override
    public Location3ic validateAndGetSpawnPoint() {
        var dimension = spawnPoint.dimension();
        // The stored location can outlive its world. Replace the location and
        // origin together so later respawn handling does not validate an obsolete block spawn.
        if (dimension == null || dimension.getWorld().getState() != WorldState.RUNNING) {
            spawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
            spawnPointType = SpawnPointType.FORCED;
        }
        return spawnPoint;
    }

    /**
     * Copies a spawn location in a running world and marks it forced. A
     * non-running world logs a warning and leaves both stored values unchanged;
     * callers must supply a location with a non-{@code null} dimension and world.
     */
    @Override
    public void setSpawnPoint(Location3ic spawnPoint) {
        if (spawnPoint.dimension().getWorld().getState() != WorldState.RUNNING) {
            log.warn("Trying to set spawn point to a world which is not running");
            return;
        }
        this.spawnPoint = new Location3i(spawnPoint);
        this.spawnPointType = SpawnPointType.FORCED;
    }

    /**
     * Copies a block-based spawn location and retains its supplied origin type.
     * A non-running world logs a warning and leaves the existing spawn
     * unchanged. Callers supply a dimension-qualified location and a non-{@code null}
     * {@code type} for later persistence and respawn validation.
     */
    @Override
    public void setBlockSpawnPoint(Location3ic spawnPoint, SpawnPointType type) {
        if (spawnPoint.dimension().getWorld().getState() != WorldState.RUNNING) {
            log.warn("Trying to set spawn point to a world which is not running");
            return;
        }
        this.spawnPoint = new Location3i(spawnPoint);
        this.spawnPointType = type;
    }

    /**
     * Sets air-use state, refreshes shield presentation, and broadcasts entity
     * state. Every {@code true} call resets the start tick to the current
     * entity tick; {@code false} leaves the previous start tick stored.
     * Blocking is refreshed before the final broadcast.
     */
    @Override
    public void setUsingItemInAir(boolean value) {
        this.usingItemInAir = value;
        if (value) {
            this.startUsingItemInAirTime = this.tick;
        }
        updateBlockingFlag();
        broadcastState();
    }

    /**
     * Forwards a plain message to the player when a controller is attached.
     * Fake players have no message destination, so the method performs no
     * delivery and leaves the supplied message unused through this component.
     */
    @Override
    public void sendMessage(String message) {
        if (isActualPlayer()) {
            this.controller.sendMessage(message);
        }
    }

    /**
     * Forwards a translation key and arguments to an attached player
     * controller. Fake players have no message destination, so this component
     * performs no translation or delivery when the controller reference is
     * {@code null}.
     */
    @Override
    public void sendTranslatable(String translatable, Object... args) {
        if (isActualPlayer()) {
            this.controller.sendTranslatable(translatable, args);
        }
    }

    /**
     * Forwards structured command results to the attached player controller.
     * Fake players receive no command output through this component; the
     * controller owns the interpretation of sender, status, permission names,
     * and translatable output entries.
     */
    @Override
    public void sendCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
        if (isActualPlayer()) {
            this.controller.sendCommandOutputs(sender, status, permissions, outputs);
        }
    }

    /**
     * Applies an entity action to ordinary viewers and the attached controller.
     * The inherited broadcast runs first. Fake players receive only that viewer
     * broadcast, while an actual player's own controller receives the same
     * action separately.
     */
    @Override
    public void applyAction(EntityAction action) {
        super.applyAction(action);
        if (isActualPlayer()) {
            this.controller.viewEntityAction(thisPlayer, action);
        }
    }

    /**
     * Applies an animation to ordinary viewers and the attached controller. The
     * inherited broadcast runs first. Fake players receive only that viewer
     * broadcast, while an actual player's own controller receives the same
     * animation separately.
     */
    @Override
    public void applyAnimation(EntityAnimation animation) {
        super.applyAnimation(animation);
        if (isActualPlayer()) {
            this.controller.viewEntityAnimation(thisPlayer, animation);
        }
    }

    /**
     * Returns whether this component represents a player, which is always
     * {@code true}.
     */
    @Override
    public boolean isPlayer() {
        return true;
    }

    /**
     * Returns the injected player entity owning this base component.
     */
    @Override
    public EntityPlayer asPlayer() {
        return thisPlayer;
    }

    /**
     * Sets a pre-spawn location while preserving an already assigned player
     * dimension. Once the current dimension is non-{@code null}, a target with a
     * {@code null} dimension is rejected before the inherited spawn-state and
     * coordinate checks run.
     *
     * @throws IllegalArgumentException if the target clears an existing dimension
     */
    @Override
    public void setLocationBeforeSpawn(Location3dc location) {
        if (this.location.dimension() != null && location.dimension() == null) {
            // Player state can already be bound to a dimension before spawn. Reject
            // clearing that binding rather than passing an unlocated player into inherited placement.
            throw new IllegalArgumentException("Reset dimension back to null is not allowed for player!");
        }

        super.setLocationBeforeSpawn(location);
    }

    /**
     * Broadcasts entity state to ordinary viewers and the player's own
     * controller. The inherited viewer broadcast runs first; fake players have
     * no additional controller destination, while actual players receive their
     * own current state separately.
     */
    @Override
    public void broadcastState() {
        super.broadcastState();
        if (isActualPlayer()) {
            this.controller.viewEntityState(thisPlayer);
        }
    }

    /**
     * Updates sprint state, compatible posture, controller speed, and viewer
     * feedback. An unchanged request performs no work. Starting a sprint first
     * clears sneaking; actual players then receive a speed-multiplier
     * adjustment of {@code 0.3} when starting or {@code -0.3} when stopping.
     *
     * <p>
     * The method broadcasts the resulting state and dispatches the toggle event
     * after the changes are applied. The event's return value is not used to
     * roll back state or speed, so listeners observe an already applied
     * transition.
     */
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

    /**
     * Updates sneaking, dependent sprint state, and shield presentation when
     * the flag changes. Entering sneaking stops sprinting unless the player is
     * flying. Blocking refreshes before the state broadcast, and the toggle
     * event runs afterward without rolling back the applied posture from its
     * return value.
     *
     * <p>
     * The flying exception permits sprint state to remain set while sneaking in
     * flight. When flight is absent, the nested sprint update can send its own
     * speed and state feedback before this method publishes the final sneaking
     * state.
     */
    @Override
    public void setSneaking(boolean sneaking) {
        if (this.sneaking != sneaking) {
            this.sneaking = sneaking;
            if (sneaking && !this.flying) {
                setSprinting(false);
            }

            updateBlockingFlag();

            broadcastState();
            new PlayerToggleSneakEvent(thisPlayer, sneaking).call();
        }
    }

    /**
     * Replaces shield presentation and broadcasts state only when its value
     * changes. This helper stores the derived display flag; the damage
     * component separately selects a shield and checks whether an incoming
     * attack can actually be blocked.
     */
    protected void setBlocking(boolean blocking) {
        if (this.blocking != blocking) {
            this.blocking = blocking;
            broadcastState();
        }
    }

    /**
     * Recomputes shield presentation from cooldown, posture, item use, and held
     * equipment. The player must be sneaking, hold a shield in either hand, and
     * have no active shield cooldown. Both block-targeted item use and air item
     * use suppress this presentation even when those other conditions pass.
     *
     * <p>
     * The method invokes {@code setBlocking} only when the derived result
     * differs from the stored flag. Tick updates and relevant setters call this
     * helper so equipment changes, cooldown expiry, and item-use transitions
     * can refresh the displayed shield state.
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
     * Returns whether either player hand currently contains a shield stack.
     */
    protected boolean isHoldingShield() {
        var inventory = thisPlayer.getContainer(ContainerTypes.INVENTORY);
        var offhand = thisPlayer.getContainer(ContainerTypes.OFFHAND);

        var handItem = inventory.getItemInHand();
        var offhandItem = offhand.getOffhand();

        return handItem instanceof ItemShieldStack || offhandItem instanceof ItemShieldStack;
    }

    /**
     * Updates swim posture and clears sneaking when swimming begins. Only
     * changed values broadcast state and dispatch the swim-toggle event. The
     * event runs after mutation, and its return value does not undo the applied
     * posture.
     */
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

    /**
     * Updates glide posture and broadcasts state only when the flag changes.
     * The glide-toggle event runs after the mutation and broadcast; its return
     * value does not restore the previous value or cancel this transition.
     */
    @Override
    public void setGliding(boolean gliding) {
        if (this.gliding != gliding) {
            this.gliding = gliding;
            broadcastState();
            new PlayerToggleGlideEvent(thisPlayer, gliding).call();
        }
    }

    /**
     * Updates crawl posture and clears sneaking when crawling begins. Only
     * changed values broadcast state and dispatch the crawl-toggle event. The
     * event runs after mutation, and its return value does not undo the applied
     * posture.
     */
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

    /**
     * Updates spin-attack presentation only when the requested flag differs.
     * The method broadcasts the stored value before dispatching its toggle
     * event; the event's return value does not roll back the already applied
     * spin state.
     */
    @Override
    public void setSpinAttacking(boolean spinAttacking) {
        if (this.spinAttacking == spinAttacking) {
            return;
        }

        this.spinAttacking = spinAttacking;
        broadcastState();
        new PlayerToggleSpinAttackEvent(thisPlayer, spinAttacking).call();
    }

    /**
     * Returns whether touching water or exposed non-clear weather permits
     * riptide use. Water contact succeeds immediately. Otherwise the world
     * weather must be non-clear and the sky-visibility query must accept the
     * player's integer-cast position; this method performs no biome-specific
     * rain check.
     */
    @Override
    public boolean canUseRiptide() {
        if (thisPlayer.isTouchingWater()) {
            return true;
        }

        var dimension = thisPlayer.getDimension();
        var world = dimension.getWorld();
        var weather = world.getWeather();

        if (weather == Weather.CLEAR) {
            return false;
        }

        var pos = thisPlayer.getLocation();
        // Java integer casts truncate negative fractional coordinates toward zero,
        // so sky visibility can use the neighboring block instead of the containing block.
        return dimension.canPosSeeSky((int) pos.x(), (int) pos.y(), (int) pos.z());
    }

    /**
     * Stores block-targeted item-use state and immediately refreshes shield
     * presentation. The refresh runs even for an unchanged request, but this
     * setter has no separate state broadcast beyond any blocking-state change
     * produced by the helper.
     */
    @Override
    public void setUsingItemOnBlock(boolean usingItemOnBlock) {
        this.usingItemOnBlock = usingItemOnBlock;
        updateBlockingFlag();
    }

    /**
     * Returns whether ordinary entity collision is enabled for this player. The
     * result is the inverse of {@code isPhantom}, so either spectator mode or
     * the explicit phantom flag disables entity collision through this
     * contract.
     */
    @Override
    public boolean hasEntityCollision() {
        return !isPhantom();
    }

    /**
     * Returns a fresh local collision box using the current posture priority.
     * The box remains {@code 0.6} blocks wide and starts at the entity's feet.
     * Sneaking selects height {@code 1.5} before any other posture is
     * considered.
     *
     * <p>
     * Without sneaking, swimming or gliding selects {@code 0.6}, then crawling
     * selects {@code 0.625}; otherwise the height is {@code 1.8}. This priority
     * matters when flags overlap, because the method does not assume their
     * setters made every posture mutually exclusive. Callers receive newly
     * allocated geometry rather than a retained collision box.
     */
    @Override
    public AABBdc getBaseAABB() {
        // Posture heights are local block distances. Preserve this priority when
        // flags overlap so sneaking, swim or glide, and crawling retain their chosen collision sizes.
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

    /**
     * Returns this player's controller as a world viewer when one is attached.
     * Fake players return {@code null}; callers must handle that special result
     * when converting entity chunk loaders into potential world viewers.
     */
    @Override
    public WorldViewer toWorldViewer() {
        if (isActualPlayer()) {
            return this.controller;
        }

        return null;
    }

    /**
     * Attaches the shape through the base component and adds the owning
     * controller. Actual players become viewers of the shape after base
     * attachment; fake players retain only the inherited attachment behavior
     * and have no extra controller viewer.
     */
    @Override
    public void attachPrimitiveShape(PrimitiveShape primitiveShape) {
        super.attachPrimitiveShape(primitiveShape);
        if (isActualPlayer()) {
            primitiveShape.addViewer(this.controller);
        }
    }

    /**
     * Removes the owning controller from the shape before base detachment. Fake
     * players skip the controller removal. Actual players stop viewing this
     * shape through their own controller before the inherited entity attachment
     * is removed.
     */
    @Override
    public void detachPrimitiveShape(PrimitiveShape primitiveShape) {
        if (isActualPlayer()) {
            primitiveShape.removeViewer(this.controller);
        }
        super.detachPrimitiveShape(primitiveShape);
    }

    /**
     * Adds one tenth exhaustion through the player resource rules after damage.
     */
    @EventHandler
    protected void onDamage(CEntityAfterDamageEvent event) {
        exhaust(0.1f);
    }

    /**
     * Adds one tenth exhaustion through the player resource rules after
     * attacking.
     */
    @EventHandler
    protected void onAttack(CEntityAttackEvent event) {
        exhaust(0.1f);
    }

    /**
     * Sets the player death-drop origin and a per-item randomized motion
     * factory. Drops begin three tenths of a block below eye height. Each
     * factory invocation draws a separate horizontal speed below {@code 0.5}
     * and angle around a full circle, with a fixed upward velocity of
     * {@code 0.2} blocks per tick.
     *
     * <p>
     * The shared death handler requests motion for each dropped stack, so
     * separate items can scatter in different horizontal directions from the
     * same origin. This hook changes event configuration without itself
     * inserting any item or experience drops into the world.
     */
    @EventHandler
    protected void onDie(CEntityDieEvent event) {
        // Player drops originate near the eyes instead of at the feet, while each
        // stack receives an independent horizontal direction from the motion factory.
        var pos = thisPlayer.getLocation();
        event.setDropPosition(new Vector3d(pos.x(), pos.y() + thisPlayer.getEyeHeight() - 0.3, pos.z()));
        event.setDropMotionFactory(() -> {
            var rand = ThreadLocalRandom.current();
            float speed = rand.nextFloat() * 0.5f;
            // Convert the uniform unit draw to a full circle in radians so the
            // horizontal scatter has no preferred compass direction.
            float angle = rand.nextFloat() * (float) (Math.PI * 2);
            return new Vector3d(-Math.sin(angle) * speed, 0.2, Math.cos(angle) * speed);
        });
    }

    /**
     * Sets death experience to seven points per current level, capped at one
     * hundred. This handler replaces the event's current amount; the living
     * component separately decides whether the death qualifies for dropping any
     * experience.
     */
    @EventHandler
    protected void onGetDropXp(CEntityGetDropXpEvent event) {
        // Seven points per level reaches the one-hundred-point cap, limiting the
        // death reward even when the player has accumulated many experience levels.
        event.setXp(Math.min(experienceLevel * 7, 100));
    }
}
