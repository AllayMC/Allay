package org.allaymc.api.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.message.LangCode;

import java.util.UUID;

/**
 * {@code LoginData} exposes the identity, device metadata, and initial skin decoded
 * from a client login. Authentication status describes verified login credentials
 * rather than connection lifetime; optional client fields may be unavailable, and
 * offline identities use the resolved player name.
 *
 * @author daoge_cmd
 */
public interface LoginData {

    /**
     * Returns whether the login credentials passed the applicable trusted
     * authentication path. This value remains login metadata after disconnecting;
     * servers requiring Xbox authentication reject untrusted ordinary clients during
     * admission.
     */
    boolean isAuthed();

    /**
     * Returns the player name resolved from login identity or offline client
     * metadata. Offline names are client-controlled, and authenticated account names
     * can change, so persistent data should use {@code getUuid} instead.
     */
    String getXname();

    /**
     * Returns the Xbox account identifier carried by the decoded login identity.
     * Offline credentials may carry an empty or unavailable identifier, so callers
     * must consult {@code isAuthed} before treating this value as authenticated.
     */
    String getXuid();

    /**
     * Returns the resolved persistent player identity for this login. The standard
     * implementation retains authenticated identity UUIDs and derives offline UUIDs
     * from the case-sensitive resolved player name encoded as UTF-8.
     */
    UUID getUuid();

    /**
     * Returns device metadata decoded from the client login payload. The standard
     * decoder leaves this value {@code null} when its required device fields are
     * absent, and individual enum classifications may also be {@code null}.
     */
    DeviceInfo getDeviceInfo();

    /**
     * Returns the platform online ID the client reported during login. Most
     * platforms report none, so the value is usually empty; it is never
     * {@code null}.
     */
    String getPlatformOnlineId();

    /**
     * Returns the client language code decoded during login when supplied. The
     * standard decoder leaves this value {@code null} when the client omits its
     * optional language metadata field.
     */
    LangCode getLangCode();

    /**
     * Returns the client-reported game version captured during login when available.
     * The standard decoder leaves this string {@code null} when the corresponding
     * optional client metadata is absent.
     */
    String getGameVersion();

    /**
     * Returns the address string the client reports using to connect. This
     * client-supplied value may distinguish hostnames sharing an endpoint and is
     * {@code null} when omitted from login metadata.
     */
    String getServerAddress();

    /**
     * Returns the input category reported as current during login. The standard
     * decoder returns {@code null} for an omitted field or an unsupported numeric
     * client input category.
     */
    InputMode getCurrentInputMode();

    /**
     * Returns the platform's default input category reported during login. The
     * standard decoder returns {@code null} for an omitted field or an unsupported
     * numeric client input category.
     */
    InputMode getDefaultInputMode();

    /**
     * Returns the initial player skin decoded from client login metadata. Its record
     * contains shared arrays and lists, so callers must preserve those values while
     * another consumer uses the skin.
     */
    Skin getSkin();

    /**
     * Returns additional identity claims extracted for a NetEase client. Ordinary
     * clients and NetEase logins without usable extra claims return {@code null};
     * individual optional fields in a returned record may also be {@code null}.
     */
    NetEaseData getNetEaseData();

    /**
     * {@code UIProfile} identifies the classic or pocket interface requested in
     * client login metadata. Its numeric lookup returns {@code null} when the client
     * supplies an unsupported profile index.
     */
    enum UIProfile {
        /**
         * Classic client interface profile.
         */
        CLASSIC,
        /**
         * Pocket client interface profile.
         */
        POCKET;

        /**
         * Returns the interface profile at the supplied declaration-order index.
         * Unsupported negative or out-of-range indexes return {@code null} so
         * callers can retain an unknown reported client profile.
         */
        public static UIProfile from(int id) {
            var values = values();
            if (id < 0 || id >= values.length) {
                return null;
            }

            return values[id];
        }
    }

    /**
     * {@code Device} identifies a client platform with its protocol number and
     * display label. The constants preserve the platform mapping used by login
     * metadata; numeric lookup does not substitute {@code UNKNOWN} for unrecognized
     * values.
     */
    @Getter
    @AllArgsConstructor
    enum Device {
        /**
         * Client platform label for Unknown.
         */
        UNKNOWN(-1, "Unknown"),
        /**
         * Client platform label for Android.
         */
        ANDROID(1, "Android"),
        /**
         * Client platform label for iOS.
         */
        IOS(2, "iOS"),
        /**
         * Client platform label for macOS.
         */
        OSX(3, "macOS"),
        /**
         * Client platform label for Fire OS.
         */
        AMAZON(4, "Fire OS"),
        /**
         * Client platform label for Gear VR.
         */
        GEAR_VR(5, "Gear VR"),
        /**
         * Client platform label for HoloLens.
         */
        HOLOLENS(6, "HoloLens"),
        /**
         * Client platform label for Windows 10.
         */
        WINDOWS(7, "Windows 10"),
        /**
         * Client platform label for Windows.
         */
        WINDOWS_32(8, "Windows"),
        /**
         * Client platform label for Dedicated.
         */
        DEDICATED(9, "Dedicated"),
        /**
         * Client platform label for tvOS.
         */
        TVOS(10, "tvOS"),
        /**
         * Client platform label for PlayStation.
         */
        PLAYSTATION(11, "PlayStation"),
        /**
         * Client platform label for Switch.
         */
        NINTENDO(12, "Switch"),
        /**
         * Client platform label for Xbox.
         */
        XBOX(13, "Xbox"),
        /**
         * Client platform label for Windows Phone.
         */
        WINDOWS_PHONE(14, "Windows Phone");

        /**
         * Numeric client platform identifier.
         */
        private final int id;
        /**
         * Display label for the client platform.
         */
        private final String name;

        /**
         * Returns the device at the supplied protocol index for supported values.
         * Values below {@code 1} or above the constant count return {@code null}; an
         * index equal to that count currently reaches an array bounds exception.
         *
         * @throws ArrayIndexOutOfBoundsException if {@code id} equals the number of
         * device constants
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
     * {@code DeviceInfo} holds device and interface metadata captured during login.
     * The record retains strings and enum references without requiring that either
     * classification be recognized.
     *
     * @param deviceName client-reported device model
     * @param deviceId client-reported device identifier
     * @param clientId client-generated random numeric identifier
     * @param device client platform, or {@code null} when its classification is
     * unknown
     * @param UIProfile interface profile, or {@code null} when its classification is
     * unknown
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
     * {@code NetEaseData} holds optional NetEase identity claims decoded from the
     * login chain. The standard decoder uses zero for an absent numeric user
     * identifier and retains {@code null} for absent string claims.
     *
     * @param uid NetEase numeric user identifier
     * @param sessionId optional NetEase session identifier
     * @param platform optional client platform description
     * @param osName optional operating system name
     * @param env optional client environment description
     * @param engineVersion optional client engine version
     * @param patchVersion optional client patch version
     * @param bit optional client architecture bitness label
     *
     * @author daoge_cmd
     */
    record NetEaseData(
            long uid, String sessionId, String platform, String osName,
            String env, String engineVersion, String patchVersion, String bit) {
    }
}
