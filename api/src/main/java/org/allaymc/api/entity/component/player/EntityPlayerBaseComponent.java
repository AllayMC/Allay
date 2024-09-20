package org.allaymc.api.entity.component.player;

import org.allaymc.api.client.data.Abilities;
import org.allaymc.api.client.data.AdventureSettings;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.client.storage.PlayerData;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.scoreboard.ScoreboardViewer;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.Map;

public interface EntityPlayerBaseComponent extends EntityBaseComponent, ChunkLoader, ScoreboardViewer {

    /**
     * The default movement speed of a player.
     */
    float DEFAULT_MOVEMENT_SPEED = 0.1f;

    /**
     * Checks if the player is sprinting.
     *
     * @return Whether the player is sprinting
     */
    default boolean isSprinting() {
        return getMetadata().get(EntityFlag.SPRINTING);
    }

    /**
     * Sets the player's sprinting state.
     *
     * @param sprinting Whether the player should be sprinting
     */
    void setSprinting(boolean sprinting);

    /**
     * Checks if the player is sneaking.
     *
     * @return Whether the player is sneaking
     */
    default boolean isSneaking() {
        return getMetadata().get(EntityFlag.SNEAKING);
    }

    /**
     * Sets the player's sneaking state.
     *
     * @param sneaking Whether the player should be sneaking
     */
    void setSneaking(boolean sneaking);

    /**
     * Checks if the player is swimming.
     *
     * @return Whether the player is swimming
     */
    default boolean isSwimming() {
        return getMetadata().get(EntityFlag.SWIMMING);
    }

    /**
     * Sets the player's swimming state.
     *
     * @param swimming Whether the player should be swimming
     */
    void setSwimming(boolean swimming);

    /**
     * Checks if the player is gliding.
     *
     * @return Whether the player is gliding
     */
    default boolean isGliding() {
        return getMetadata().get(EntityFlag.GLIDING);
    }

    /**
     * Sets the player's gliding state.
     *
     * @param gliding Whether the player should be gliding
     */
    void setGliding(boolean gliding);

    /**
     * Checks if the player is crawling.
     *
     * @return Whether the player is crawling
     */
    default boolean isCrawling() {
        return getMetadata().get(EntityFlag.CRAWLING);
    }

    /**
     * Sets the player's crawling state.
     *
     * @param crawling Whether the player should be crawling
     */
    void setCrawling(boolean crawling);

    /**
     * Checks if the player is using an item on a block.
     *
     * @return Whether the player is using an item on a block
     */
    boolean isUsingItemOnBlock();

    /**
     * Sets whether the player is using an item on a block.
     *
     * @param usingItemOnBlock Whether the player should be using an item on a block
     */
    void setUsingItemOnBlock(boolean usingItemOnBlock);

    /**
     * Eating food or using a crossbow is considered using an item.
     * Note the distinction from {@code usingItemOnBlock}! Using an item is unrelated to blocks!
     *
     * @return Whether the player is using an item in the air
     */
    boolean isUsingItemInAir();

    /**
     * Sets whether the player is using an item in the air.
     *
     * @param value Whether the player should be using an item in the air
     */
    default void setUsingItemInAir(boolean value) {
        setUsingItemInAir(value, getWorld().getTick());
    }

    /**
     * Sets whether the player is using an item in the air.
     *
     * @param value Whether the player should be using an item in the air
     * @param time  The current tick
     */
    void setUsingItemInAir(boolean value, long time);

    /**
     * Gets the time when the player most recently started using an item.
     *
     * @return The time when the player most recently started using an item
     */
    long getStartUsingItemInAirTime();

    /**
     * Gets how long the player has been using the item, in game ticks.
     *
     * @param currentTime The current time
     *
     * @return How long the player has been using the item, in game ticks
     */
    long getItemUsingInAirTime(long currentTime);

    default long getItemUsingInAirTime() {
        return getItemUsingInAirTime(getWorld().getTick());
    }

    /**
     * Gets the hand slot of the player.
     *
     * @return The hand slot of the player
     */
    int getHandSlot();

    /**
     * Sets the hand slot of the player.
     *
     * @param handSlot The hand slot of the player
     */
    void setHandSlot(int handSlot);

    /**
     * Gets the base offset of the player.
     *
     * @return The base offset of the player
     */
    @Override
    default float getBaseOffset() {
        return 1.62f;
    }

    /**
     * Whether the player's head yaw is enabled.
     *
     * @return Whether the player's head yaw is enabled
     */
    @Override
    default boolean enableHeadYaw() {
        return true;
    }

    /**
     * Gets the display name of the player.
     * <p>
     * Display name is used in chat, damage message and etc <br>
     * Normally, it is equal to the origin name, however you can change the display name
     * compared to the origin name <br>
     * This is very useful for plugin especially if plugin wants to change the appearance of player name in chat
     * because origin name cannot be changed
     *
     * @return The display name of the player
     */
    String getDisplayName();

    /**
     * Sets the display name of the player.
     *
     * @param displayName The display name of the player
     */
    void setDisplayName(String displayName);

    /**
     * Gets the skin of the player.
     *
     * @return The skin of the player
     */
    Skin getSkin();

    /**
     * Sets the skin of the player.
     *
     * @param skin The skin to set
     */
    void setSkin(Skin skin);

    /**
     * Gets the game type of the player.
     *
     * @return The game type of the player
     */
    GameType getGameType();

    /**
     * Sets the game type of the player.
     *
     * @param gameType The game type to set
     */
    void setGameType(GameType gameType);

    /**
     * Gets the adventure settings of the player.
     *
     * @return The adventure settings of the player
     */
    AdventureSettings getAdventureSettings();

    /**
     * Gets the abilities of the player.
     *
     * @return The abilities of the player
     */
    Abilities getAbilities();

    /**
     * Sets the fly speed of the player.
     *
     * @param flySpeed The fly speed to set
     */
    default void setFlySpeed(float flySpeed) {
        getAbilities().setFlySpeed(flySpeed);
    }

    /**
     * Sets whether the player is flying.
     *
     * @param flying Whether the player is flying
     */
    default void setFlying(boolean flying) {
        getAbilities().setFlying(flying);
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
     * Saves the player's data.
     *
     * @return The saved player data
     */
    PlayerData savePlayerData();

    /**
     * Gets the spawn point of the player.
     *
     * @return The spawn point of the player
     */
    Location3ic getSpawnPoint();

    /**
     * Sets the spawn point of the player.
     *
     * @param spawnPoint The spawn point to set
     */
    void setSpawnPoint(Location3ic spawnPoint);

    /**
     * Sends the player's location to themselves.
     */
    void sendLocationToSelf();

    /**
     * Gets the forms of the player.
     *
     * @return The forms of the player
     */
    @UnmodifiableView
    Map<Integer, Form> getForms();

    /**
     * Gets a form by its ID.
     *
     * @param id The ID of the form
     *
     * @return The form
     */
    Form getForm(int id);

    /**
     * Removes a form by its ID.
     *
     * @param id The ID of the form
     *
     * @return The removed form
     */
    Form removeForm(int id);

    /**
     * Gets the server setting forms of the player.
     *
     * @return The server setting forms of the player
     */
    @UnmodifiableView
    Map<Integer, Form> getServerSettingForms();

    /**
     * Adds a server setting form to the player.
     *
     * @param form The form to add
     */
    void addServerSettingForm(CustomForm form);

    /**
     * Gets a server setting form by its ID.
     *
     * @param id The ID of the form
     *
     * @return The form
     */
    CustomForm getServerSettingForm(int id);

    /**
     * Removes a server setting form by its ID.
     *
     * @param id The ID of the form
     *
     * @return The removed form
     */
    CustomForm removeServerSettingForm(int id);

    /**
     * Shows a form to the player.
     *
     * @param form The form to show
     */
    void showForm(Form form);

    /**
     * Closes all opened forms of the player.
     */
    void closeAllForms();

    /**
     * Checks if the player can reach a block at the specified position.
     *
     * @param pos The position of the block
     *
     * @return Whether the player can reach the block
     */
    default boolean canReachBlock(Vector3ic pos) {
        return canReach(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f);
    }

    /**
     * Checks if the player can reach a point at the specified coordinates.
     *
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     * @param z The z coordinate of the point
     *
     * @return Whether the player can reach the point
     */
    default boolean canReach(float x, float y, float z) {
        if (isDead()) return false;
        var maxDistance = getMaxInteractDistance();
        // Check whether there is a point that inside of the player's AABB
        // And can reach the provided pos
        var aabb = getOffsetAABB();
        float[] aabbXs = new float[]{aabb.minX(), aabb.maxX()};
        float[] aabbYs = new float[]{aabb.minY(), aabb.maxY()};
        float[] aabbZs = new float[]{aabb.minZ(), aabb.maxZ()};
        for (var aabbX : aabbXs) {
            for (var aabbY : aabbYs) {
                for (var aabbZ : aabbZs) {
                    if (new Vector3f(aabbX, aabbY, aabbZ).distanceSquared(x, y, z) <= maxDistance * maxDistance) {
                        return true;
                    }
                }
            }
        }
        // TODO: check yaw and pitch
        return false;
    }

    default double getMaxInteractDistance() {
        return getGameType() == GameType.CREATIVE ? 13d : 7d;
    }

    float getMovementSpeed();

    void setMovementSpeed(float speed);

    void setMotionValueOnly(Vector3fc motion);
}
