package org.allaymc.api.entity.component;

import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.Skin;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.jetbrains.annotations.Range;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EntityPlayerBaseComponent} exposes the player entity's controller,
 * posture, spawn preference, cooldowns, food resources, experience, and
 * fishing state. Actual players have an attached controller, while fake
 * players share the entity-facing contract without necessarily supporting
 * every operation that sends controller updates.
 *
 * <p> Experience is split between a whole level and a fractional progress
 * value whose point cost changes at level boundaries. {@code addExperience}
 * converts through those costs, then proposes progress before level through
 * separate setters, so cancellation or failure can apply only part of the
 * requested change. Other helpers likewise preserve the concrete setters'
 * event and validation rules; callers must not assume that a convenience
 * operation bypasses those decisions or makes several updates atomic.
 */
public interface EntityPlayerBaseComponent extends EntityBaseComponent, ChunkLoader {

    /**
     * {@code MAX_FOOD_LEVEL} defines the twenty-point hunger cap used by food
     * setters, eating checks, and the helper that restores player food data.
     */
    int MAX_FOOD_LEVEL = 20;
    /**
     * {@code MAX_FOOD_SATURATION_LEVEL} defines the twenty-point saturation
     * cap used by saturation updates and the helper that restores player food
     * data.
     */
    float MAX_FOOD_SATURATION_LEVEL = 20;
    /**
     * {@code MAX_FOOD_EXHAUSTION_LEVEL} defines the four-unit exhaustion
     * threshold at which the server consumes one saturation point or,
     * afterward, one hunger point.
     */
    float MAX_FOOD_EXHAUSTION_LEVEL = 4;

    /**
     * Returns the player controller attached to this entity, or {@code null}
     * for a fake player. The reference represents the controlling connection
     * and abilities rather than the entity object itself.
     */
    Player getController();

    /**
     * Returns whether this entity currently has a nonnull player controller.
     */
    default boolean isActualPlayer() {
        return getController() != null;
    }

    /**
     * Returns whether this player entity currently has the sprinting flag set.
     */
    boolean isSprinting();

    /**
     * Updates the player's sprinting state when its value changes. Starting a
     * sprint clears sneaking and updates an actual controller's speed
     * multiplier. Toggle listeners observe the applied transition; their event
     * result does not roll back the server's updated flag.
     */
    void setSprinting(boolean sprinting);

    /**
     * Returns whether this player entity currently has the sneaking flag set.
     */
    boolean isSneaking();

    /**
     * Updates the player's sneaking state when its value changes. Starting
     * sneaking stops sprinting unless the player is flying and refreshes
     * shield presentation. Toggle listeners observe the applied transition;
     * their event result does not roll back the server's updated flag.
     */
    void setSneaking(boolean sneaking);

    /**
     * Returns the derived shield-presentation flag for this player. The server
     * combines sneaking, held shield, expired shield cooldown, and absence of
     * both item-use states; actual damage blocking additionally follows the
     * living component's attack checks.
     */
    boolean isBlocking();

    /**
     * Returns whether this player entity currently has the swimming flag set.
     */
    boolean isSwimming();

    /**
     * Updates the player's swimming state when its value changes. Starting
     * swimming clears sneaking before the swim state is broadcast. Toggle
     * listeners observe the applied transition; their event result does not
     * roll back the server's updated flag.
     */
    void setSwimming(boolean swimming);

    /**
     * Returns whether this player entity currently has the gliding flag set.
     */
    boolean isGliding();

    /**
     * Updates the player's gliding state when its value changes. A changed
     * glide flag is broadcast without changing the other posture flags. Toggle
     * listeners observe the applied transition; their event result does not
     * roll back the server's updated flag.
     */
    void setGliding(boolean gliding);

    /**
     * Returns whether this player entity currently has the crawling flag set.
     */
    boolean isCrawling();

    /**
     * Updates the player's crawling state when its value changes. Starting
     * crawling clears sneaking before the crawl state is broadcast. Toggle
     * listeners observe the applied transition; their event result does not
     * roll back the server's updated flag.
     */
    void setCrawling(boolean crawling);

    /**
     * Returns whether this player entity currently has the spin-attack flag
     * set.
     */
    boolean isSpinAttacking();

    /**
     * Updates the player's spinattacking state when its value changes. A
     * changed spin-attack flag is broadcast before the corresponding toggle
     * event. Toggle listeners observe the applied transition; their event
     * result does not roll back the server's updated flag.
     */
    void setSpinAttacking(boolean spinAttacking);

    /**
     * Returns whether location contact with water or exposed non-clear weather
     * permits riptide use. The server's weather branch checks sky visibility
     * at integer-cast coordinates and does not require a separate
     * biome-specific rain classification.
     */
    boolean canUseRiptide();

    /**
     * Returns whether this player entity currently has the block-targeted
     * item-use flag set.
     */
    boolean isUsingItemOnBlock();

    /**
     * Stores block-targeted item use and refreshes the derived
     * shield-presentation flag. This can broadcast a changed blocking state
     * even though the item-use setter has no separate unconditional state
     * broadcast of its own.
     */
    void setUsingItemOnBlock(boolean usingItemOnBlock);

    /**
     * Returns whether this player entity currently has the untargeted
     * held-item-use flag set.
     */
    boolean isUsingItemInAir();

    /**
     * Stores untargeted held-item use, refreshes shield presentation, and
     * broadcasts entity state. Every {@code true} assignment resets the start
     * tick; stopping use preserves the previous start tick for later duration
     * queries.
     */
    void setUsingItemInAir(boolean value);

    /**
     * Returns the entity tick retained from the latest air-use start,
     * initially {@code -1}. Callers should query while use is active; the
     * current server warning path for inactive use dereferences the controller
     * and is unsuitable for a fake player.
     */
    long getStartUsingItemInAirTime();

    /**
     * Returns entity ticks elapsed since the retained air-use start. The
     * server does not require the use flag to remain active, so callers must
     * check {@code isUsingItemInAir} when they need an active-use duration.
     */
    long getItemUsingInAirTime();

    /**
     * Returns the retained skin reference, or {@code null} when no custom skin
     * is assigned. The skin object is shared rather than copied by this
     * component's generated getter.
     */
    Skin getSkin();

    /**
     * Stores the supplied skin reference and broadcasts player skin
     * presentation to connected players. The server does not copy the skin or
     * limit this notification to the entity's current viewer set.
     */
    void setSkin(Skin skin);

    /**
     * Returns the game mode currently stored for this player entity.
     */
    GameMode getGameMode();

    /**
     * Proposes a game-mode change through its cancellable event. Accepted
     * changes update sibling components, relevant resources, abilities, and
     * viewer state in sequence; cancellation or an unchanged accepted mode
     * leaves the current mode unchanged.
     */
    void setGameMode(GameMode gameMode);

    /**
     * Returns whether this player entity currently has the ability-based
     * flight flag set.
     */
    boolean isFlying();

    /**
     * Sets the flight flag and updates the attached controller's flying
     * ability when the value changes. Fake players retain the local flag
     * without a controller update, and this setter does not broadcast ordinary
     * entity metadata.
     */
    void setFlying(boolean flying);

    /**
     * Returns whether the attached controller currently permits flight. Fake
     * players return {@code false} because no controller supplies abilities,
     * even when the entity's separate flying flag has been set.
     */
    default boolean canFly() {
        return isActualPlayer() ? getController().canFly() : false;
    }

    /**
     * Changes the explicit phantom flag used to hide this player and suppress
     * ordinary entity interactions. The server refreshes viewer membership for
     * an alive actual player; clearing the flag still leaves spectator-mode
     * phantom behavior active.
     */
    void setPhantom(boolean phantom);

    /**
     * Returns whether spectator mode or the explicit phantom flag enables
     * phantom behavior. Spectators can still be visible to spectator
     * controllers under the server's visibility rules, while the explicit flag
     * hides the player from all viewers.
     */
    boolean isPhantom();

    /**
     * Returns the optional additional score tag used in player metadata. A
     * {@code null} value means that no score tag is assigned, while an empty
     * string remains present for the default presence query.
     */
    String getScoreTag();

    /**
     * Stores an additional score tag and broadcasts entity metadata even for
     * an unchanged value. The server retains {@code null} to remove the tag,
     * and it performs no text normalization here.
     */
    void setScoreTag(String scoreTag);

    /**
     * Returns whether the current score-tag reference is nonnull, including
     * empty strings.
     */
    default boolean hasScoreTag() {
        return getScoreTag() != null;
    }

    /**
     * Returns the retained personal spawn after checking that its dimension
     * belongs to a running world. Invalid world state selects the global spawn
     * and marks it forced; this query does not validate a bed or
     * respawn-anchor block.
     */
    Location3ic validateAndGetSpawnPoint();

    /**
     * Copies a dimension-qualified spawn location and marks it as forced. The
     * server leaves existing spawn data unchanged when the location's world is
     * not running, and callers must supply usable dimension and world
     * references.
     */
    void setSpawnPoint(Location3ic spawnPoint);

    /**
     * Copies a dimension-qualified spawn location and records its supplied
     * origin type. The server rejects non-running worlds by leaving existing
     * data unchanged; later respawn handling uses the type's invalid-block
     * message rule when no respawn-capable block remains.
     */
    void setBlockSpawnPoint(Location3ic spawnPoint, SpawnPointType type);

    /**
     * Returns the origin category retained with the player's personal spawn
     * location.
     */
    SpawnPointType getSpawnPointType();

    /**
     * {@code SpawnPointType} identifies how a personal spawn location was
     * established. Its ordinal is persisted, and its invalid-spawn message key
     * distinguishes block-based fallback from an ordinary forced location.
     */
    enum SpawnPointType {
        /**
         * Forced location with no missing-block message during respawn
         * fallback.
         */
        FORCED(null),
        /**
         * Bed-derived location whose missing block triggers the bed validation
         * message.
         */
        BED(TrKeys.MC_TILE_BED_NOTVALID),
        /**
         * Anchor-derived location whose missing block triggers the anchor
         * validation message.
         */
        RESPAWN_ANCHOR(TrKeys.MC_TILE_RESPAWN_ANCHOR_NOTVALID);

        /**
         * {@code invalidSpawnKey} names the message used when respawn finds no
         * supporting respawn behavior. {@code null} marks {@code FORCED},
         * which keeps its validated location without that missing-block
         * fallback.
         */
        public final String invalidSpawnKey;

        /**
         * {@code VALUES} caches the ordinal lookup array used by
         * {@code fromId}. This public array is mutable, so replacing an entry
         * changes subsequent persisted-origin decoding.
         */
        public static final SpawnPointType[] VALUES = values();

        /**
         * Creates a spawn-origin category with its invalid-block translation
         * key. A {@code null} key identifies a forced origin that does not
         * trigger the missing-respawn-block notification and reset path.
         */
        SpawnPointType(String invalidSpawnKey) {
            this.invalidSpawnKey = invalidSpawnKey;
        }

        /**
         * Returns the origin stored at the supplied ordinal-array index
         * without validating it. Invalid indices throw, and replacements in
         * the public {@code VALUES} array directly affect the category
         * returned by this helper.
         *
         * @throws ArrayIndexOutOfBoundsException if {@code id} is outside the cached array
         */
        public static SpawnPointType fromId(int id) {
            return VALUES[id];
        }
    }

    /**
     * Checks reach against the center point of the supplied block. Each
     * coordinate receives a half-block offset before the normal reach helper
     * examines distances from the player's world-space box corners.
     */
    default boolean canReachBlock(Vector3ic pos) {
        return canReach(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f);
    }

    /**
     * Checks reach for the supplied point using the coordinate-based reach
     * calculation.
     */
    default boolean canReach(Vector3dc pos) {
        return canReach(pos.x(), pos.y(), pos.z());
    }

    /**
     * Returns whether any corner of the player's world-space box lies within
     * the allowed distance of the point. Dead entities fail immediately. The
     * helper samples all eight corners rather than finding the closest point
     * on the box, and it does not test view direction or intervening blocks.
     *
     * <p> The selected limit depends on game mode and is compared as a squared
     * distance for each corner. This approximation can reject a point near a
     * box face when none of its corners qualifies, so callers must not treat
     * the result as a complete geometric or line-of-sight interaction test.
     */
    default boolean canReach(double x, double y, double z) {
        if (isDead()) return false;
        var maxDistance = getMaxInteractDistance();
        // Sample corners instead of solving the closest point on the box; this
        // keeps the query approximate and omits view direction and line-of-sight checks.
        var aabb = getOffsetAABB();
        double[] aabbXs = new double[]{aabb.minX(), aabb.maxX()};
        double[] aabbYs = new double[]{aabb.minY(), aabb.maxY()};
        double[] aabbZs = new double[]{aabb.minZ(), aabb.maxZ()};
        for (var aabbX : aabbXs) {
            for (var aabbY : aabbYs) {
                for (var aabbZ : aabbZs) {
                    if (new Vector3d(aabbX, aabbY, aabbZ).distanceSquared(x, y, z) <= maxDistance * maxDistance) {
                        return true;
                    }
                }
            }
        }
        // TODO: Include yaw, pitch, and obstruction checks if this helper becomes
        // the authoritative interaction validator rather than a distance approximation.
        return false;
    }

    /**
     * Returns the reach limit used by the default distance approximation,
     * measured in blocks. Creative mode selects thirteen blocks, while every
     * other mode selects seven, before the box-corner calculation is applied.
     */
    default double getMaxInteractDistance() {
        return getGameMode() == GameMode.CREATIVE ? 13d : 7d;
    }

    /**
     * Returns the retained seed used to generate enchanting-table option
     * choices.
     */
    int getEnchantmentSeed();

    /**
     * Stores the seed used for subsequent enchanting-table option generation.
     */
    void setEnchantmentSeed(int seed);

    /**
     * Replaces the enchanting seed with a random nonnegative integer below the
     * integer maximum.
     */
    default void regenerateEnchantmentSeed() {
        setEnchantmentSeed(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));
    }

    /**
     * Stores a category expiry relative to the current entity tick and
     * optionally notifies an actual controller. Expiry remains active through
     * its exact deadline tick; the current server stores supplied durations
     * without normalizing them despite the range annotation.
     */
    void setCooldown(String category, @Range(from = 0, to = Integer.MAX_VALUE) int duration, boolean send);

    /**
     * Sets a category cooldown and requests notification of an attached
     * controller. The duration uses entity ticks, and fake players retain the
     * server expiry without a client packet destination.
     */
    default void setCooldown(String category, @Range(from = 0, to = Integer.MAX_VALUE) int duration) {
        setCooldown(category, duration, true);
    }

    /**
     * Sets a cooldown category using the item type's full namespaced
     * identifier. The explicit {@code send} flag controls attached-controller
     * notification, while the stored duration and deadline follow the
     * category-based operation.
     */
    default void setCooldown(ItemType<?> itemType, @Range(from = 0, to = Integer.MAX_VALUE) int duration, boolean send) {
        setCooldown(itemType.getIdentifier().toString(), duration, send);
    }

    /**
     * Sets an item-identifier cooldown without requesting a controller
     * notification. This overload deliberately passes {@code false} to the
     * full item helper, whereas the category-only convenience overload
     * requests notification by default.
     */
    default void setCooldown(ItemType<?> itemType, @Range(from = 0, to = Integer.MAX_VALUE) int duration) {
        setCooldown(itemType, duration, false);
    }

    /**
     * Returns whether the category is absent or its stored deadline precedes
     * the current entity tick. Equality still counts as active, and the
     * current server query leaves expired entries in the map.
     */
    boolean isCooldownEnd(String category);

    /**
     * Checks cooldown expiry under the item type's full namespaced identifier.
     */
    default boolean isCooldownEnd(ItemType<?> itemType) {
        return isCooldownEnd(itemType.getIdentifier().toString());
    }

    /**
     * Returns the point cost of advancing from the supplied experience level.
     * The piecewise formulas change at levels fifteen and thirty; the helper
     * does not validate level range or protect arithmetic from overflow for
     * arbitrary integer inputs.
     */
    static int calculateRequiredExperience(int level) {
        // Higher levels use steeper incremental costs; choose the level band
        // before converting progress fractions into the points needed for advancement.
        if (level >= 30) {
            return 112 + (level - 30) * 9;
        } else if (level >= 15) {
            return 37 + (level - 15) * 5;
        } else {
            return 7 + (level << 1);
        }
    }

    /**
     * Returns the currently stored whole experience level for this player.
     */
    int getExperienceLevel();

    /**
     * Proposes an experience level through the cancellable level-change event.
     * The server accepts initial levels from {@code 0} through {@code 24791},
     * stores the listener-adjusted result, then requires an attached
     * controller to send it.
     *
     * @throws IllegalArgumentException if the initial value is outside the supported range
     */
    void setExperienceLevel(int value);

    /**
     * Returns the stored fractional progress within the current experience
     * level.
     */
    float getExperienceProgress();

    /**
     * Proposes progress through a cancellable event with a fraction from
     * {@code 0} through {@code 1}. The current server stores the original
     * argument after acceptance, ignores listener replacement, and then
     * requires an attached controller for its send.
     *
     * @throws IllegalArgumentException if the initial fraction is outside the supported range
     */
    void setExperienceProgress(float value);

    /**
     * Adds points by converting current fractional progress into the point
     * cost of the current level. Each crossed level consumes its own required
     * cost before the next level is evaluated. Negative additions do not
     * borrow levels and can produce a progress value rejected by the setter.
     *
     * <p> The helper proposes the resulting fraction before the whole level,
     * using separate setter calls. Those calls retain independent
     * cancellation, range checks, and controller requirements; a later refusal
     * or exception does not restore an already applied fraction. Callers
     * needing an accepted combined update cannot infer one solely from this
     * helper returning normally.
     */
    default void addExperience(int addition) {
        var currentLevel = getExperienceLevel();
        var requiredExpCurrentLevel = calculateRequiredExperience(currentLevel);
        var total = getExperienceProgress() * requiredExpCurrentLevel + addition;

        // Recompute the cost after every crossed level because a fraction of
        // progress represents different point totals in different experience level bands.
        while (total >= requiredExpCurrentLevel) {
            total -= requiredExpCurrentLevel;
            currentLevel++;
            requiredExpCurrentLevel = calculateRequiredExperience(currentLevel);
        }

        setExperienceProgress(total / requiredExpCurrentLevel);
        setExperienceLevel(currentLevel);
    }

    /**
     * Returns the advancement point cost calculated for the stored experience
     * level.
     */
    default int getRequiredExperienceForCurrentLevel() {
        return calculateRequiredExperience(getExperienceLevel());
    }

    /**
     * Returns whole experience points represented by the stored fraction at
     * the current level. The floating-point product is truncated to an
     * integer, so this helper omits any fractional point remainder.
     */
    default int getExperienceInCurrentLevel() {
        // Expose whole points only; the integer cast discards any fractional remainder.
        return (int) (getExperienceProgress() * getRequiredExperienceForCurrentLevel());
    }

    /**
     * Requests maximum hunger and saturation, then zero exhaustion through
     * their normal setters. A cancellable food-level update can refuse its
     * part while the later saturation and exhaustion updates still proceed.
     */
    default void resetFoodData() {
        setFoodLevel(MAX_FOOD_LEVEL);
        setFoodSaturationLevel(MAX_FOOD_SATURATION_LEVEL);
        setFoodExhaustionLevel(0);
    }

    /**
     * Returns the current hunger level measured in food points.
     */
    int getFoodLevel();

    /**
     * Proposes a hunger level after clamping it to
     * {@code [0, MAX_FOOD_LEVEL]}. Cancellation leaves the stored value
     * unchanged; an accepted listener replacement is retained and sent to an
     * attached controller.
     */
    void setFoodLevel(int value);

    /**
     * Returns the current saturation reserve consumed before hunger points.
     */
    float getFoodSaturationLevel();

    /**
     * Stores saturation after clamping it to
     * {@code [0, MAX_FOOD_SATURATION_LEVEL]}. The current server sends the
     * result to an actual controller, and this setter alone does not cap
     * saturation against current hunger.
     */
    void setFoodSaturationLevel(float value);

    /**
     * Returns the stored exhaustion units waiting to consume food resources.
     */
    float getFoodExhaustionLevel();

    /**
     * Stores exhaustion after clamping it to
     * {@code [0, MAX_FOOD_EXHAUSTION_LEVEL]}. This direct setter does not
     * consume saturation or hunger; callers use {@code exhaust} to apply
     * accumulated resource costs.
     */
    void setFoodExhaustionLevel(float value);

    /**
     * Adds exhaustion and converts complete thresholds into saturation loss,
     * then hunger loss. Creative, spectator, and peaceful cases are unchanged;
     * normal updates use the food setters, so cancellation can refuse computed
     * hunger loss after other resource changes.
     */
    void exhaust(float level);

    /**
     * Adds hunger through its event-mediated setter before increasing
     * saturation. Saturation is limited using the resulting stored hunger, so
     * listener cancellation or replacement affects the cap applied to this
     * food-consumption operation.
     */
    void saturate(int food, float saturation);

    /**
     * Returns whether hunger, creative mode, or peaceful difficulty permits
     * eating. The current server accepts any player below twenty food points
     * and accepts creative or peaceful cases regardless of the stored hunger
     * level.
     */
    boolean canEat();

    /**
     * Returns the retained fishing hook, clearing it when that entity is no
     * longer alive. A missing or cleared hook returns {@code null}, so this
     * query can also update the stored fishing reference.
     */
    EntityFishingHook getFishingHook();

    /**
     * Stores the fishing hook reference directly without spawning or removing
     * either entity. {@code null} clears the association, and the getter later
     * clears a retained hook that is no longer alive.
     */
    void setFishingHook(EntityFishingHook fishingHook);

    /**
     * Returns whether the fishing-hook query currently yields a live
     * associated hook.
     */
    default boolean isFishing() {
        return getFishingHook() != null;
    }
}
