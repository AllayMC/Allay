package org.allaymc.api.entity.component.player;

import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.PlayerData;
import org.allaymc.api.player.Skin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Range;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

public interface EntityPlayerBaseComponent extends EntityBaseComponent {

    double DEFAULT_SPEED = 0.1;
    double DEFAULT_FLY_SPEED = 0.05;
    double DEFAULT_VERTICAL_FLY_SPEED = 1.0;

    int MAX_FOOD_LEVEL = 20;
    float MAX_FOOD_SATURATION_LEVEL = 20;
    float MAX_FOOD_EXHAUSTION_LEVEL = 4;

    /**
     * Check if the player is sprinting.
     *
     * @return {@code true} if the player is sprinting, {@code false} otherwise.
     */
    boolean isSprinting();

    /**
     * Set the player's sprinting state.
     *
     * @param sprinting Whether the player should be sprinting
     */
    void setSprinting(boolean sprinting);

    /**
     * Check if the player is sneaking.
     *
     * @return {@code true} if the player is sneaking, {@code false} otherwise.
     */
    boolean isSneaking();

    /**
     * Set the player's sneaking state.
     *
     * @param sneaking Whether the player should be sneaking
     */
    void setSneaking(boolean sneaking);

    /**
     * Check if the player is swimming.
     *
     * @return {@code true} if the player is swimming, {@code false} otherwise.
     */
    boolean isSwimming();

    /**
     * Set the player's swimming state.
     *
     * @param swimming Whether the player should be swimming
     */
    void setSwimming(boolean swimming);

    /**
     * Check if the player is gliding.
     *
     * @return {@code true} if the player is gliding, {@code false} otherwise.
     */
    boolean isGliding();

    /**
     * Set the player's gliding state.
     *
     * @param gliding Whether the player should be gliding
     */
    void setGliding(boolean gliding);

    /**
     * Check if the player is crawling.
     *
     * @return {@code true} if the player is crawling, {@code false} otherwise.
     */
    boolean isCrawling();

    /**
     * Set the player's crawling state.
     *
     * @param crawling Whether the player should be crawling
     */
    void setCrawling(boolean crawling);

    /**
     * Check if the player is using an item on a block.
     *
     * @return {@code true} if the player is using an item on a block, {@code false} otherwise.
     */
    boolean isUsingItemOnBlock();

    /**
     * Set whether the player is using an item on a block.
     *
     * @param usingItemOnBlock Whether the player should be using an item on a block
     */
    void setUsingItemOnBlock(boolean usingItemOnBlock);

    /**
     * Eating food or using a crossbow is considered using an item.
     * <p>
     * Note the distinction from {@code usingItemOnBlock}! Using an item is unrelated to blocks!
     *
     * @return {@code true} if the player is using an item, {@code false} otherwise.
     */
    boolean isUsingItemInAir();

    /**
     * Set whether the player is using an item in the air.
     *
     * @param value Whether the player should be using an item in the air
     */
    default void setUsingItemInAir(boolean value) {
        setUsingItemInAir(value, getWorld().getTick());
    }

    /**
     * Set whether the player is using an item in the air.
     *
     * @param value Whether the player should be using an item in the air
     * @param time  The current tick
     */
    void setUsingItemInAir(boolean value, long time);

    /**
     * Get the time when the player most recently started using an item.
     *
     * @return The time when the player most recently started using an item
     */
    long getStartUsingItemInAirTime();

    /**
     * Get how long the player has been using the item, in game ticks.
     *
     * @param currentTime The current time
     * @return How long the player has been using the item in game ticks
     */
    long getItemUsingInAirTime(long currentTime);

    /**
     * Retrieves the in-air time of an item based on the current world tick.
     *
     * @return the in-air time of the item as a long value
     */
    default long getItemUsingInAirTime() {
        return getItemUsingInAirTime(getWorld().getTick());
    }

    /**
     * Get the hand slot of the player.
     *
     * @return The hand slot of the player
     */
    int getHandSlot();

    /**
     * Set the hand slot of the player.
     *
     * @param handSlot the hand slot of the player
     */
    void setHandSlot(int handSlot);

    /**
     * {@inheritDoc}
     */
    @Override
    default boolean isHeadYawEnabled() {
        return true;
    }

    /**
     * Get the skin of the player.
     *
     * @return the skin of the player
     */
    Skin getSkin();

    /**
     * Sets the skin of the player.
     *
     * @param skin the skin to set
     */
    void setSkin(Skin skin);

    /**
     * Get the game mode of the player.
     *
     * @return The game mode of the player
     */
    GameMode getGameMode();

    /**
     * Sets the game mode of the player.
     *
     * @param gameMode The game mode to set
     */
    void setGameMode(GameMode gameMode);

    /**
     * Get the speed of the player.
     *
     * @return The speed of the player
     */
    double getSpeed();

    /**
     * Set the speed of the player.
     *
     * @param speed The speed to set
     */
    void setSpeed(double speed);

    /**
     * Get the fly speed of the player.
     *
     * @return The fly speed of the player
     */
    double getFlySpeed();

    /**
     * Sets the fly speed of the player.
     *
     * @param flySpeed the fly speed to set
     */
    void setFlySpeed(double flySpeed);

    /**
     * Get the vertical fly speed of the player.
     *
     * @return The vertical fly speed of the player
     */
    double getVerticalFlySpeed();

    /**
     * Sets the vertical fly speed of the player.
     *
     * @param verticalFlySpeed the vertical fly speed to set
     */
    void setVerticalFlySpeed(double verticalFlySpeed);

    /**
     * Determines whether the player is currently flying.
     *
     * @return {@code true} if the player is flying, {@code false} otherwise
     */
    boolean isFlying();

    /**
     * Sets whether the player is flying.
     *
     * @param flying Whether the player is flying
     */
    void setFlying(boolean flying);

    /**
     * Gets the score tag of the player.
     *
     * @return the score tag of the player
     */
    String getScoreTag();

    /**
     * Sets the score tag of the player.
     *
     * @param scoreTag the score tag to set, or {@code null} to remove the score tag
     */
    void setScoreTag(String scoreTag);

    /**
     * Checks if the score tag is present.
     *
     * @return {@code true} if the score tag exists and is not null, {@code false} otherwise
     */
    default boolean hasScoreTag() {
        return getScoreTag() != null;
    }

    /**
     * Sends a tip to the player.
     *
     * @param message The message to send
     */
    void sendTip(String message);

    /**
     * Sends a popup to the player.
     *
     * @param message The message to send
     */
    void sendPopup(String message);

    /**
     * Send a toast to the player. The toast will be displayed at the top of the screen
     * with a title and content in a period of time.
     *
     * @param title   the title of the toast
     * @param content the content of the toast
     */
    void sendToast(String title, String content);

    /**
     * Send a title to the player which will be shown in the middle of the screen.
     *
     * @param title the title to send
     */
    void sendTitle(String title);

    /**
     * Send a subtitle to the player which will be shown below the main title.
     * If no title is set, the subtitle won't be shown, in other words title must
     * be set before set subtitle.
     *
     * @param subtitle the subtitle to send
     */
    void sendSubtitle(String subtitle);

    /**
     * Send an action bar message to the player which will be shown above player's hot bar.
     *
     * @param actionBar the action bar message to send
     */
    void sendActionBar(String actionBar);

    /**
     * Set the title settings of this player. These settings will be used for the next title, subtitle and actionbar.
     *
     * @param fadeInTime  the time in ticks for the title to fade in
     * @param duration    the time in ticks for the title to stay
     * @param fadeOutTime the time in ticks for the title to fade out
     */
    void setTitleSettings(int fadeInTime, int duration, int fadeOutTime);

    /**
     * Reset the title settings of this player to the default values.
     */
    void resetTitleSettings();

    /**
     * Clear the title that is being displayed on the player's screen
     */
    void clearTitle();

    /**
     * Save the player's data.
     *
     * @return The saved player data
     */
    PlayerData savePlayerData();

    /**
     * Validate and get the spawn point of the player.
     * <p>
     * This method will check if the world of the spawn point is loaded,
     * and if it is not loaded, the spawn point of the player will be set
     * to the global spawn point.
     * <p>
     * Please note that this method won't do extra checks like checking if the
     * bed is still exists, etc.
     *
     * @return The spawn point of the player
     */
    Location3ic validateAndGetSpawnPoint();

    /**
     * Set the spawn point of the player.
     *
     * @param spawnPoint The spawn point to set
     */
    void setSpawnPoint(Location3ic spawnPoint);

    /**
     * Check if the player can reach a block at the specified position.
     *
     * @param pos The position of the block
     * @return Whether the player can reach the block
     */
    default boolean canReachBlock(Vector3ic pos) {
        return canReach(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f);
    }

    /**
     * Check if the player can reach a point at the specified coordinates.
     *
     * @param pos The position of the point
     * @return Whether the player can reach the point
     */
    default boolean canReach(Vector3dc pos) {
        return canReach(pos.x(), pos.y(), pos.z());
    }

    /**
     * Check if the player can reach a point at the specified coordinates.
     *
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     * @param z The z coordinate of the point
     * @return Whether the player can reach the point
     */
    default boolean canReach(double x, double y, double z) {
        if (isDead()) return false;
        var maxDistance = getMaxInteractDistance();
        // Check whether there is a point that inside of the player's AABB
        // And can reach the provided pos
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
        // TODO: check yaw and pitch
        return false;
    }

    /**
     * Get the maximum distance that the player can interact with blocks.
     *
     * @return The maximum distance that the player can interact with blocks
     */
    default double getMaxInteractDistance() {
        return getGameMode() == GameMode.CREATIVE ? 13d : 7d;
    }

    /**
     * Get the enchantment seed of the player.
     *
     * @return The enchantment seed of the player
     */
    int getEnchantmentSeed();

    /**
     * Set the enchantment seed of the player.
     *
     * @param seed The enchantment seed to set
     */
    void setEnchantmentSeed(int seed);

    /**
     * Regenerate the enchantment seed of the player.
     */
    default void regenerateEnchantmentSeed() {
        setEnchantmentSeed(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));
    }

    /**
     * Require encoding and resending all commands to the player next tick. This method should be called when
     * command permissions change, but usually you don't need to call this method manually since the permission
     * listener does it.
     */
    @ApiStatus.Internal
    void requireResendingCommands();

    /**
     * {@inheritDoc}
     */
    @Override
    default boolean willBeSaved() {
        return false;
    }

    /**
     * Sets cool down for a specific category.
     *
     * @param category the category to set
     * @param duration the cool down tick
     * @param send     whether send packet to the client
     */
    void setCooldown(String category, @Range(from = 0, to = Integer.MAX_VALUE) int duration, boolean send);

    /**
     * @see #setCooldown(String, int, boolean)
     */
    default void setCooldown(String category, @Range(from = 0, to = Integer.MAX_VALUE) int duration) {
        setCooldown(category, duration, true);
    }

    /**
     * Sets cool down for a specific item type.
     *
     * @param itemType the item type to set
     * @param duration the cool down tick
     * @param send     whether send packet to the client
     */
    default void setCooldown(ItemType<?> itemType, @Range(from = 0, to = Integer.MAX_VALUE) int duration, boolean send) {
        setCooldown(itemType.getIdentifier().toString(), duration, send);
    }

    /**
     * @see #setCooldown(ItemType, int, boolean)
     */
    default void setCooldown(ItemType<?> itemType, @Range(from = 0, to = Integer.MAX_VALUE) int duration) {
        setCooldown(itemType, duration, false);
    }

    /**
     * Checks if the cooldown for specific category has ended.
     *
     * @param category the category to check
     * @return {@code true} if the cooldown has ended, {@code false} otherwise.
     */
    boolean isCooldownEnd(String category);

    /**
     * Checks if the cooldown for specific item type has ended.
     *
     * @param itemType the item type to check
     * @return {@code true} if the cooldown has ended, {@code false} otherwise.
     */
    default boolean isCooldownEnd(ItemType<?> itemType) {
        return isCooldownEnd(itemType.getIdentifier().toString());
    }

    /**
     * Calculates the required experience for a given level.
     *
     * @param level the level
     * @return the required experience
     */
    static int calculateRequiredExperience(int level) {
        if (level >= 30) {
            return 112 + (level - 30) * 9;
        } else if (level >= 15) {
            return 37 + (level - 15) * 5;
        } else {
            return 7 + (level << 1);
        }
    }

    /**
     * Get the current experience level.
     *
     * @return the experience level
     */
    int getExperienceLevel();

    /**
     * Set the experience level.
     *
     * @param value the new experience level
     */
    void setExperienceLevel(int value);

    /**
     * Get the current experience progress.
     *
     * @return the experience progress
     */
    float getExperienceProgress();

    /**
     * Set the experience progress.
     *
     * @param value the new experience progress
     */
    void setExperienceProgress(float value);

    /**
     * Adds experience to the player.
     *
     * @param addition the amount of experience to add
     */
    default void addExperience(int addition) {
        var currentLevel = getExperienceLevel();
        var requiredExpCurrentLevel = calculateRequiredExperience(currentLevel);
        var total = getExperienceProgress() * requiredExpCurrentLevel + addition;

        while (total >= requiredExpCurrentLevel) {
            total -= requiredExpCurrentLevel;
            currentLevel++;
            requiredExpCurrentLevel = calculateRequiredExperience(currentLevel);
        }

        setExperienceProgress(total / requiredExpCurrentLevel);
        setExperienceLevel(currentLevel);
    }

    /**
     * Get the required experience for the current level.
     *
     * @return the required experience
     */
    default int getRequiredExperienceForCurrentLevel() {
        return calculateRequiredExperience(getExperienceLevel());
    }

    /**
     * Get the experience in the current level.
     *
     * @return the experience in the current level
     */
    default int getExperienceInCurrentLevel() {
        return (int) (getExperienceProgress() * getRequiredExperienceForCurrentLevel());
    }

    /**
     * Resets the food data.
     */
    default void resetFoodData() {
        setFoodLevel(MAX_FOOD_LEVEL);
        setFoodSaturationLevel(MAX_FOOD_SATURATION_LEVEL);
        setFoodExhaustionLevel(0);
    }

    /**
     * Get the current food level.
     *
     * @return the food level
     */
    int getFoodLevel();

    /**
     * Set the food level.
     *
     * @param value the new food level
     */
    void setFoodLevel(int value);

    /**
     * Get the current food saturation level.
     *
     * @return the food saturation level
     */
    float getFoodSaturationLevel();

    /**
     * Set the food saturation level.
     *
     * @param value the new food saturation level
     */
    void setFoodSaturationLevel(float value);

    /**
     * Get the current food exhaustion level.
     *
     * @return the food exhaustion level
     */
    float getFoodExhaustionLevel();

    /**
     * Set the food exhaustion level.
     *
     * @param value the new food exhaustion level
     */
    void setFoodExhaustionLevel(float value);

    /**
     * Reduces the player's exhaustion level.
     *
     * @param level the amount of exhaustion to reduce by
     */
    void exhaust(float level);

    /**
     * Increases the player's saturation level.
     *
     * @param food       the amount of food to add
     * @param saturation the amount of saturation to add
     */
    void saturate(int food, float saturation);

    /**
     * Check if the player can eat.
     *
     * @return {@code true} if the player can eat, {@code false} otherwise.
     */
    boolean canEat();
}
