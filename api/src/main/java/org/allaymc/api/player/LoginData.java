package org.allaymc.api.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.message.LangCode;

import java.util.UUID;

/**
 * @author daoge_cmd
 */
public interface LoginData {

    /**
     * Checks whether the player has been authenticated with Xbox Live. Returns {@code true} when the player
     * is online and {@code false} when the player is offline. When xbox-auth is enabled by the server, this
     * method should always return {@code true} since unauthenticated players are not allowed to join the server.
     *
     * @return whether the player has been authenticated with Xbox Live
     */
    boolean isAuthed();

    /**
     * Gets the name of the player's xbox account when online. When offline, this name can be changed locally.
     * <p>
     * One thing to note is that players can change their names, so plugins shouldn't use this
     * value as a key to store player data. Consider using {@link #getXuid()} or {@link #getUuid()}
     *
     * @return the name of the player's xbox account
     */
    String getXname();

    /**
     * Gets the player's xbox user id. XUID is the XBOX Live user ID of the player, which will remain consistent
     * as long as the player is logged in with the XBOX Live account. It is empty if the user is not logged into
     * its XBL account.
     *
     * @return the player's xbox user id
     */
    String getXuid();

    /**
     * Gets the player's uuid, derived from the player's xuid when online, or from the username when offline.
     *
     * @return the player's uuid
     */
    UUID getUuid();

    /**
     * Gets the information of player's device.
     *
     * @return the information of player's device
     */
    DeviceInfo getDeviceInfo();

    /**
     * Gets the language code of the player's client.
     *
     * @return the language code of the player's client
     */
    LangCode getLangCode();

    /**
     * Gets the game version of the player's client.
     *
     * @return the game version of the player's client
     */
    String getGameVersion();

    /**
     * Gets the player's skin.
     *
     * @return the player's skin
     */
    Skin getSkin();

    /**
     * Gets the NetEase-specific data for this player. This data is only available
     * when the player is connecting from the NetEase version of Minecraft.
     *
     * @return the NetEase data, or {@code null} if the player is not from NetEase client
     */
    NetEaseData getNetEaseData();

    enum UIProfile {
        CLASSIC,
        POCKET;

        /**
         * Looks up UIProfile from the id passed.
         *
         * @param id the id to looks up.
         * @return the UIProfile correspond to the id passed, or {@code null} if the id is invalid
         */
        public static UIProfile from(int id) {
            var values = values();
            if (id < 0 || id > values.length) {
                return null;
            }

            return values[id];
        }
    }

    /**
     * An enumeration that represents various types of devices. Each device has an associated id and name.
     */
    @Getter
    @AllArgsConstructor
    enum Device {
        UNKNOWN(-1, "Unknown"),
        ANDROID(1, "Android"),
        IOS(2, "iOS"),
        OSX(3, "macOS"),
        AMAZON(4, "Fire OS"),
        GEAR_VR(5, "Gear VR"),
        HOLOLENS(6, "HoloLens"),
        WINDOWS(7, "Windows 10"),
        WINDOWS_32(8, "Windows"),
        DEDICATED(9, "Dedicated"),
        TVOS(10, "tvOS"),
        PLAYSTATION(11, "PlayStation"),
        NINTENDO(12, "Switch"),
        XBOX(13, "Xbox"),
        WINDOWS_PHONE(14, "Windows Phone");

        private final int id;
        private final String name;

        /**
         * Get device by id.
         *
         * @param id the id of the device
         * @return the device, or {@code null} if the device id passed is unknown
         */
        public static Device from(int id) {
            var values = values();
            if (id < 1 || id > values.length) {
                return null;
            }

            return values[id];
        }
    }

    /**
     * Represents information about a player's device.
     */
    record DeviceInfo(
            String deviceName,
            String deviceId,
            long clientId,
            Device device,
            UIProfile UIProfile
    ) {
    }

    /**
     * Contains NetEase-specific player data extracted from the login chain.
     * <p>
     * This data is only available when the player is connecting from the
     * NetEase version of Minecraft.
     *
     * @param uid           the NetEase user ID (UID)
     * @param sessionId     the NetEase session ID
     * @param platform      the platform information
     * @param osName        the operating system name
     * @param env           the environment information
     * @param engineVersion the engine version
     * @param patchVersion  the patch version
     * @param bit           the bit architecture (e.g., {@code "32"} or {@code "64"})
     *
     * @author daoge_cmd
     */
    record NetEaseData(
            long uid, String sessionId, String platform, String osName,
            String env, String engineVersion, String patchVersion, String bit) {
    }
}
