package org.allaymc.api.entity.component.player;

import it.unimi.dsi.fastutil.Pair;
import org.allaymc.api.client.data.Abilities;
import org.allaymc.api.client.data.AdventureSettings;
import org.allaymc.api.client.storage.PlayerData;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.scoreboard.ScoreboardViewer;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.data.skin.SerializedSkin;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public interface EntityPlayerBaseComponent extends EntityBaseComponent, ChunkLoader, ScoreboardViewer {

    /**
     * The default movement speed of a player.
     */
    float DEFAULT_MOVEMENT_SPEED = 0.1f;

    /**
     * Check if the player is sprinting.
     *
     * @return {@code true} if the player is sprinting, {@code false} otherwise.
     */
    default boolean isSprinting() {
        return getMetadata().get(EntityFlag.SPRINTING);
    }

    /**
     * Set the player's sprinting state.
     *
     * @param sprinting Whether the player should be sprinting.
     */
    void setSprinting(boolean sprinting);

    /**
     * Check if the player is sneaking.
     *
     * @return {@code true} if the player is sneaking, {@code false} otherwise.
     */
    default boolean isSneaking() {
        return getMetadata().get(EntityFlag.SNEAKING);
    }

    /**
     * Set the player's sneaking state.
     *
     * @param sneaking Whether the player should be sneaking.
     */
    void setSneaking(boolean sneaking);

    /**
     * Check if the player is swimming.
     *
     * @return {@code true} if the player is swimming, {@code false} otherwise.
     */
    default boolean isSwimming() {
        return getMetadata().get(EntityFlag.SWIMMING);
    }

    /**
     * Set the player's swimming state.
     *
     * @param swimming Whether the player should be swimming.
     */
    void setSwimming(boolean swimming);

    /**
     * Check if the player is gliding.
     *
     * @return {@code true} if the player is gliding, {@code false} otherwise.
     */
    default boolean isGliding() {
        return getMetadata().get(EntityFlag.GLIDING);
    }

    /**
     * Set the player's gliding state.
     *
     * @param gliding Whether the player should be gliding.
     */
    void setGliding(boolean gliding);

    /**
     * Check if the player is crawling.
     *
     * @return {@code true} if the player is crawling, {@code false} otherwise.
     */
    default boolean isCrawling() {
        return getMetadata().get(EntityFlag.CRAWLING);
    }

    /**
     * Set the player's crawling state.
     *
     * @param crawling Whether the player should be crawling.
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
     * @param usingItemOnBlock Whether the player should be using an item on a block.
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
     * @param value Whether the player should be using an item in the air.
     */
    default void setUsingItemInAir(boolean value) {
        setUsingItemInAir(value, getWorld().getTick());
    }

    /**
     * Set whether the player is using an item in the air.
     *
     * @param value Whether the player should be using an item in the air.
     * @param time  The current tick.
     */
    void setUsingItemInAir(boolean value, long time);

    /**
     * Get the time when the player most recently started using an item.
     *
     * @return The time when the player most recently started using an item.
     */
    long getStartUsingItemInAirTime();

    /**
     * Get how long the player has been using the item, in game ticks.
     *
     * @param currentTime The current time.
     *
     * @return How long the player has been using the item, in game ticks.
     */
    long getItemUsingInAirTime(long currentTime);

    default long getItemUsingInAirTime() {
        return getItemUsingInAirTime(getWorld().getTick());
    }

    /**
     * Get the hand slot of the player.
     *
     * @return The hand slot of the player.
     */
    int getHandSlot();

    default void setHandSlot(int handSlot) {
        setHandSlot(handSlot, true);
    }

    /**
     * Set the hand slot of the player.
     *
     * @param handSlot   The hand slot of the player.
     * @param sendToSelf Whether the change should be sent to the self.
     */
    void setHandSlot(int handSlot, boolean sendToSelf);

    /**
     * Get the base offset of the player.
     *
     * @return The base offset of the player.
     */
    @Override
    default float getNetworkOffset() {
        return 1.62f;
    }

    @Override
    default boolean enableHeadYaw() {
        return true;
    }

    /**
     * Get the display name of the player.
     * <p>
     * Display name is used in chat, damage message etc.
     * Normally, it is equal to the origin name, however you can change the display name
     * compared to the origin name.
     * <p>
     * This is very useful for plugin especially if plugin wants to change the appearance of player name in chat
     * because origin name cannot be changed.
     *
     * @return The display name of the player.
     */
    String getDisplayName();

    /**
     * Sets the display name of the player.
     *
     * @param displayName The display name of the player.
     */
    void setDisplayName(String displayName);

    /**
     * Get the skin of the player.
     *
     * @return The skin of the player.
     */
    SerializedSkin getSkin();

    /**
     * Sets the skin of the player.
     *
     * @param skin The skin to set.
     */
    void setSkin(SerializedSkin skin);

    /**
     * Get the game type of the player.
     *
     * @return The game type of the player.
     */
    GameType getGameType();

    /**
     * Sets the game type of the player.
     *
     * @param gameType The game type to set.
     */
    void setGameType(GameType gameType);

    /**
     * Get the adventure settings of the player.
     *
     * @return The adventure settings of the player.
     */
    AdventureSettings getAdventureSettings();

    /**
     * Get the abilities of the player.
     *
     * @return The abilities of the player.
     */
    Abilities getAbilities();

    /**
     * Set the fly speed of the player.
     *
     * @param flySpeed The fly speed to set.
     */
    default void setFlySpeed(float flySpeed) {
        getAbilities().setFlySpeed(flySpeed);
    }

    /**
     * Set the vertical fly speed of the player.
     *
     * @param verticalFlySpeed The vertical fly speed to set.
     */
    default void setVerticalFlySpeed(float verticalFlySpeed) {
        getAbilities().setVerticalFlySpeed(verticalFlySpeed);
    }

    /**
     * Set whether the player is flying.
     *
     * @param flying Whether the player is flying.
     */
    default void setFlying(boolean flying) {
        getAbilities().setFlying(flying);
    }

    /**
     * Send a tip to the player.
     *
     * @param message The message to send.
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
     * @param title   the title of the toast.
     * @param content the content of the toast.
     */
    void sendToast(String title, String content);

    /**
     * Send a title to the player which will be shown in the middle of the screen.
     *
     * @param title the title to send.
     */
    void sendTitle(String title);

    /**
     * Send a subtitle to the player which will be shown below the main title.
     * If no title is set, the subtitle won't be shown, in other words title must
     * be set before set subtitle.
     *
     * @param subtitle the subtitle to send.
     */
    void sendSubtitle(String subtitle);

    /**
     * Send an action bar message to the player which will be shown above player's hot bar.
     *
     * @param actionBar the action bar message to send.
     */
    void sendActionBar(String actionBar);

    /**
     * Set the title settings of this player. These settings will be used for the next title, subtitle and actionbar.
     *
     * @param fadeInTime  the time in ticks for the title to fade in.
     * @param duration    the time in ticks for the title to stay.
     * @param fadeOutTime the time in ticks for the title to fade out.
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
     * @return The saved player data.
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
     * @return The spawn point of the player.
     */
    Location3ic validateAndGetSpawnPoint();

    /**
     * Set the spawn point of the player.
     *
     * @param spawnPoint The spawn point to set.
     */
    void setSpawnPoint(Location3ic spawnPoint);

    /**
     * Get the forms of the player.
     *
     * @return The forms of the player.
     */
    @UnmodifiableView
    Map<Integer, Form> getForms();

    /**
     * Get a form by its ID.
     *
     * @param id The ID of the form.
     *
     * @return The form.
     */
    Form getForm(int id);

    /**
     * Remove a form by its ID.
     *
     * @param id The ID of the form.
     *
     * @return The removed form.
     */
    Form removeForm(int id);

    /**
     * Get the server setting form and its id.
     *
     * @return The server setting form and its id.
     */
    Pair<Integer, CustomForm> getServerSettingForm();

    /**
     * Set a server setting form to the player.
     *
     * @param form The form to add.
     */
    void setServerSettingForm(CustomForm form);

    /**
     * Remove the server setting form.
     */
    void removeServerSettingForm();

    /**
     * Show a form to the player.
     *
     * @param form The form to show.
     */
    void showForm(Form form);

    /**
     * Close all opened forms of the player.
     */
    void closeAllForms();

    /**
     * Check if the player can reach a block at the specified position.
     *
     * @param pos The position of the block.
     *
     * @return Whether the player can reach the block.
     */
    default boolean canReachBlock(Vector3ic pos) {
        return canReach(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f);
    }

    /**
     * Check if the player can reach a point at the specified coordinates.
     *
     * @param pos The position of the point.
     *
     * @return Whether the player can reach the point.
     */
    default boolean canReach(Vector3dc pos) {
        return canReach(pos.x(), pos.y(), pos.z());
    }

    /**
     * Check if the player can reach a point at the specified coordinates.
     *
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     * @param z The z coordinate of the point.
     *
     * @return Whether the player can reach the point.
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
     * @return The maximum distance that the player can interact with blocks.
     */
    default double getMaxInteractDistance() {
        return getGameType() == GameType.CREATIVE ? 13d : 7d;
    }

    /**
     * Get the movement speed of the player.
     *
     * @return The movement speed of the player.
     */
    float getMovementSpeed();

    /**
     * Set the movement speed of the player.
     *
     * @param speed The movement speed to set.
     */
    void setMovementSpeed(float speed);

    /**
     * Get the enchantment seed of the player.
     *
     * @return The enchantment seed of the player.
     */
    int getEnchantmentSeed();

    /**
     * Set the enchantment seed of the player.
     *
     * @param seed The enchantment seed to set.
     */
    void setEnchantmentSeed(int seed);

    /**
     * Regenerate the enchantment seed of the player.
     */
    default void regenerateEnchantmentSeed() {
        setEnchantmentSeed(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));
    }

    /**
     * Require encoding and resending {@link org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket} to
     * the player next tick. This method is usually called when command permissions change, and you don't need to
     * call this method manually, because the permission listener does it.
     */
    void requireResendingAvailableCommands();

    /**
     * {@inheritDoc}
     */
    @Override
    default boolean willBeSaved() {
        return false;
    }
}
