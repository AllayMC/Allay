package org.allaymc.api.player;

import org.allaymc.api.pdc.PersistentDataContainer;
import org.cloudburstmc.nbt.NbtMap;

import java.util.UUID;

/**
 * Represents an offline player - a player whose data exists on the server but is not currently online.
 *
 * @author IWareQ
 */
public interface OfflinePlayer {
    /**
     * Gets the Xbox User ID (XUID) of this player.
     * <p>
     * XUID is a unique identifier provided by Xbox Live that remains
     * consistent across nickname changes. It is only available for players
     * who have logged in with Xbox authentication.
     *
     * @return The Xbox User ID, or 0 if the player has never used Xbox authentication
     */
    long getXuid();

    /**
     * Gets the nickname-based UUID of this player.
     * <p>
     * This UUID is generated from the player's nickname using UUID v5
     * and changes when the nickname changes. It's primarily used for
     * offline mode players who don't have an XUID.
     *
     * @return The nickname-based UUID, never null
     */
    UUID getNameUuid();

    /**
     * Gets the current nickname of this player.
     * <p>
     * Note: Nicknames can change, especially for Xbox-authenticated players
     * who can modify their Xbox gamertag. Plugins should not use nicknames
     * as permanent identifiers - use {@link #getXuid()} or {@link #getStorageUuid()} instead.
     *
     * @return The player's current nickname, never null
     */
    String getNickname();

    /**
     * Gets the storage UUID used to identify this player's data files.
     * <p>
     * This UUID is generated once when the player first joins and never changes.
     * It's used internally by {@link PlayerStorage} to locate the player's data file.
     *
     * @return The storage UUID, never null
     */
    UUID getStorageUuid();

    /**
     * Gets the persistent data container for this player.
     * <p>
     * The PDC allows plugins to store custom data that persists across sessions.
     * Data is automatically saved when {@link #save()} is called.
     *
     * @return The persistent data container, never null
     */
    PersistentDataContainer getPersistentDataContainer();

    /**
     * Gets the complete NBT data for this player.
     * <p>
     * This includes all player data such as inventory, position, health, etc.
     * Modifications to this map are not automatically saved - call {@link #save()}
     * after making changes.
     *
     * @return The player's NBT data, never null
     */
    NbtMap getNbtData();

    void setNbtData(NbtMap nbtData);

    /**
     * Gets the offline-specific NBT data for this player.
     * <p>
     * This is a special section of NBT data used by the offline player system
     * to store metadata like nickname collision information and forced rename flags.
     *
     * @return The offline NBT data, or an empty map if not present
     */
    NbtMap getOfflineNbtData();

    /**
     * Sets the offline-specific NBT data for this player.
     * <p>
     * This updates the special offline data section.
     * Call {@link #save()} after setting to persist changes.
     *
     * @param nbtData The new offline NBT data
     */
    void setOfflineNbtData(NbtMap nbtData);

    /**
     * Saves this player's data to disk.
     * <p>
     * This persists all changes made to NBT data, PDC, and offline data.
     * Should be called after any modifications to ensure data is not lost.
     */
    void save();

    /**
     * Checks if this player is currently online.
     *
     * @return true if the player is online, false otherwise
     */
    default boolean isOnline() {
        return this.getOnlinePlayer() != null;
    }

    /**
     * Gets the online Player instance if this player is currently connected.
     *
     * @return The online Player instance, or null if the player is offline
     */
    Player getOnlinePlayer();

    /**
     * Checks if this player was forced to change their nickname due to a collision.
     * <p>
     * Nickname collisions occur when multiple players attempt to use the same nickname.
     * The offline player system resolves this by assigning a unique temporary nickname
     * (e.g., "Steve_a1b2c3d4") to the player who logs in later.
     *
     * @return true if the nickname was forcibly changed, false otherwise
     */
    default boolean wasForcedNicknameChange() {
        var data = this.getOfflineNbtData();
        return data.getBoolean("ForcedNicknameChange", false);
    }

    /**
     * Gets the original nickname before it was forcibly changed.
     * <p>
     * Only applicable when {@link #wasForcedNicknameChange()} returns true.
     *
     * @return The original nickname, or null if not applicable
     */
    default String getOriginalNickname() {
        if (!this.wasForcedNicknameChange()) {
            return null;
        }

        var data = this.getOfflineNbtData();
        return data.getString("OriginalNickname", null);
    }

    /**
     * Gets the nickname that the player attempted to use but was taken.
     * <p>
     * Only applicable when {@link #wasForcedNicknameChange()} returns true.
     * This is the nickname the player tried to use when logging in, but it
     * was already in use by another player.
     *
     * @return The attempted nickname, or null if not applicable
     */
    default String getAttemptedNickname() {
        if (!this.wasForcedNicknameChange()) {
            return null;
        }

        var data = this.getOfflineNbtData();
        return data.getString("AttemptedNickname", null);
    }

    /**
     * Clears the forced nickname change flag.
     * This should be called after the player has been notified about the change.
     */
    void clearForcedNicknameChange();
}
