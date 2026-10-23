package org.allaymc.server.player;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.LangCode;
import org.allaymc.api.player.InputMode;
import org.allaymc.api.player.LoginData;
import org.allaymc.api.player.Skin;
import org.cloudburstmc.protocol.bedrock.data.auth.CertificateChainPayload;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.cloudburstmc.protocol.bedrock.util.ChainValidationResult;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;
import org.cloudburstmc.protocol.bedrock.util.NetEaseEncryptionUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * {@code AllayLoginData} holds identity, device metadata, and skin values decoded
 * from a login packet. The decoder validates ordinary authentication payloads
 * through the protocol library and selects its NetEase certificate-chain validator
 * for NetEase clients. Each decoding stage catches failure and rejects the login
 * data rather than returning a partially initialized object.
 *
 * <p>
 * Authenticated logins retain the UUID supplied by their validated identity. Offline
 * logins may lack a usable chain name, so client metadata can supply
 * {@code ThirdPartyName} before {@code resolveUuid} derives a case-sensitive
 * name-based UUID. This ordering keeps an offline account's storage key tied to its
 * resolved name instead of a transient client identity. Optional metadata remains
 * absent when the corresponding login fields are omitted.
 *
 * @author LucGamesYT | daoge_cmd
 */
@Slf4j
@ToString
@Getter
public class AllayLoginData implements LoginData {
    /**
     * {@code GSON} decodes the client JWT payload into JSON objects.
     * Authentication-chain validation occurs separately through the protocol library
     * before this parser is used.
     */
    private static final Gson GSON = new Gson();

    /**
     * Trusted authentication result from the protocol validator.
     */
    private boolean authed;
    /**
     * Resolved login player name.
     */
    private String xname;
    /**
     * Xbox identifier from identity claims.
     */
    private String xuid;
    /**
     * {@code uuid} holds the resolved storage identity. Authenticated logins retain
     * their identity UUID, while offline logins replace it with a name-derived UUID.
     */
    private UUID uuid;
    /**
     * {@code deviceInfo} contains device metadata only when the decoder's required
     * metadata keys are present; missing fields leave it {@code null}.
     */
    private DeviceInfo deviceInfo;
    /**
     * Optional client language code.
     */
    private LangCode langCode;
    /**
     * Optional client game-version string.
     */
    private String gameVersion;
    /**
     * Optional client-reported connection address.
     */
    private String serverAddress;
    /**
     * Optional input category at login.
     */
    private InputMode currentInputMode;
    /**
     * Optional platform default input category.
     */
    private InputMode defaultInputMode;
    /**
     * Initial skin decoded from client metadata.
     */
    private Skin skin;
    /**
     * Identity public key from validated claims.
     */
    private String identityPublicKey;
    /**
     * {@code netEaseData} holds optional NetEase claims. Ordinary clients, absent
     * extra claims, and caught extraction errors leave this value {@code null}.
     */
    private NetEaseData netEaseData;

    /**
     * Decodes validated identity data, client metadata, and the final persistent
     * UUID from the supplied login packet. {@code isNetEaseClient} selects the
     * applicable certificate-chain validator; failure in any stage is logged and
     * returns {@code null}.
     *
     * <p>
     * Client metadata is decoded before offline UUID resolution because it may
     * provide a missing player name. Authenticated logins require the identity UUID,
     * while offline logins require a nonblank resolved name. The returned object is
     * published only after those stages finish; optional metadata fields can
     * nevertheless remain {@code null}.
     */
    public static AllayLoginData decode(LoginPacket loginPacket, boolean isNetEaseClient) {
        var loginData = new AllayLoginData();

        try {
            ChainValidationResult result;
            if (isNetEaseClient) {
                // Validate NetEase certificate chains against their own trusted key; the ordinary validator
                // expects the Mojang authentication path and cannot establish that NetEase trust.
                result = NetEaseEncryptionUtils.validateChain((CertificateChainPayload) loginPacket.getAuthPayload());
            } else {
                result = EncryptionUtils.validatePayload(loginPacket.getAuthPayload());
            }
            loginData.decodeChainData(result, isNetEaseClient);
        } catch (Throwable t) {
            // Reject the entire login object when identity validation or extraction fails; later stages
            // must not treat partial claims as an authenticated identity.
            log.warn("Failed to decode chain data!", t);
            return null;
        }

        try {
            loginData.decodeSkinData(loginPacket.getClientJwt(), isNetEaseClient);
        } catch (Throwable t) {
            // Reject malformed client metadata rather than exposing a partially built skin or a missing
            // offline name to the final UUID-resolution stage.
            log.warn("Failed to decode skin data!", t);
            return null;
        }

        try {
            loginData.uuid = resolveUuid(loginData.authed, loginData.uuid, loginData.xname);
        } catch (Throwable t) {
            // A login without its required identity or offline name cannot select a persistent player
            // record; reject it before returning decoded data.
            log.warn("Failed to resolve player UUID!", t);
            return null;
        }

        return loginData;
    }

    /**
     * Returns the persistent UUID selected for authenticated or offline identity.
     * Authenticated input requires a non-{@code null} {@code identity}, which is
     * retained unchanged rather than recalculated from an account name.
     *
     * <p>
     * Offline input ignores {@code identity} and requires a nonblank {@code xname}.
     * The method hashes the UTF-8 bytes of the literal prefix {@code "xname:"}
     * followed by that name, preserving case and surrounding whitespace; callers
     * must resolve any permitted metadata name before invoking it.
     *
     * @throws NullPointerException if authenticated input has no {@code identity}
     * @throws IllegalArgumentException if an offline {@code xname} is absent or
     * blank
     */
    static UUID resolveUuid(boolean authed, UUID identity, String xname) {
        if (authed) {
            return Objects.requireNonNull(identity, "Authenticated player identity cannot be null");
        }
        if (xname == null || xname.isBlank()) {
            throw new IllegalArgumentException("Offline player name cannot be blank");
        }

        // Use the resolved offline name as the stable storage input instead of trusting a client-supplied
        // identity UUID that can change across offline logins.
        return UUID.nameUUIDFromBytes(("xname:" + xname).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Copies authentication status and identity claims from a validated protocol
     * result. NetEase logins additionally attempt optional claim extraction;
     * malformed required identity claims propagate to {@code decode}, which rejects
     * the entire login.
     */
    private void decodeChainData(ChainValidationResult result, boolean isNetEaseClient) {
        this.authed = result.signed();

        var extraData = result.identityClaims().extraData;
        this.xname = extraData.displayName;
        this.uuid = extraData.identity;
        this.xuid = extraData.xuid;
        this.identityPublicKey = result.identityClaims().identityPublicKey;

        if (isNetEaseClient) {
            this.netEaseData = extractNetEaseData(result.rawIdentityClaims());
        }
    }

    /**
     * Extracts optional NetEase values from the raw identity claims. An absent
     * {@code "extraData"} map returns {@code null}; missing {@code "uid"} becomes
     * zero, and conversion failures are logged at debug level and also return
     * {@code null}.
     */
    private NetEaseData extractNetEaseData(Map<String, Object> rawClaims) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> extraData = (Map<String, Object>) rawClaims.get("extraData");
            if (extraData == null) {
                return null;
            }

            long uid = extraData.containsKey("uid") ? ((Number) extraData.get("uid")).longValue() : 0L;
            String sessionId = (String) extraData.get("netease_sid");
            String platform = (String) extraData.get("platform");
            String osName = (String) extraData.get("os_name");
            String env = (String) extraData.get("env");
            String engineVersion = (String) extraData.get("engineVersion");
            String patchVersion = (String) extraData.get("patchVersion");
            String bit = (String) extraData.get("bit");

            return new NetEaseData(uid, sessionId, platform, osName, env, engineVersion, patchVersion, bit);
        } catch (Exception e) {
            // Optional NetEase claim extraction must not invalidate usable core identity data; log
            // conversion failures and retain an absent optional record instead.
            log.debug("Failed to extract NetEase data from login chain", e);
            return null;
        }
    }

    /**
     * Decodes client metadata and constructs the initial skin from the JWT payload.
     * Optional scalar fields retain their default {@code null} or {@code false}
     * values when absent; malformed present values propagate to {@code decode} and
     * reject the login.
     *
     * <p>
     * For an unauthenticated identity with a blank name, {@code "ThirdPartyName"}
     * supplies the name used later by {@code resolveUuid}; authenticated names are
     * retained. Embedded skin strings and image bytes use Base64 decoding, explicit
     * image dimensions take precedence over length inference, and optional animation
     * and Persona collections are created only when their fields exist.
     */
    private void decodeSkinData(String skinData, boolean isNetEaseClient) {
        JsonObject skinMap = decodeToken(skinData, isNetEaseClient);
        if (skinMap.has("DeviceModel") && skinMap.has("DeviceId") &&
            skinMap.has("ClientRandomId") && skinMap.has("DeviceOS") &&
            skinMap.has("GuiScale")) {
            String deviceModel = skinMap.get("DeviceModel").getAsString();
            String deviceId = skinMap.get("DeviceId").getAsString();
            long clientId = skinMap.get("ClientRandomId").getAsLong();
            int deviceOS = skinMap.get("DeviceOS").getAsInt();
            int uiProfile = skinMap.get("UIProfile").getAsInt();
            this.deviceInfo = new DeviceInfo(deviceModel, deviceId, clientId, Device.from(deviceOS), UIProfile.from(uiProfile));
        }

        // Fill only a missing offline name from client metadata before UUID resolution; authenticated
        // names must remain tied to their validated identity claims.
        if (!this.authed && (this.xname == null || this.xname.isBlank()) && skinMap.has("ThirdPartyName")) {
            this.xname = skinMap.get("ThirdPartyName").getAsString();
        }

        if (skinMap.has("LanguageCode")) {
            this.langCode = LangCode.valueOf(skinMap.get("LanguageCode").getAsString());
        }

        if (skinMap.has("GameVersion")) {
            this.gameVersion = skinMap.get("GameVersion").getAsString();
        }

        if (skinMap.has("ServerAddress")) {
            this.serverAddress = skinMap.get("ServerAddress").getAsString();
        }

        if (skinMap.has("CurrentInputMode")) {
            this.currentInputMode = InputMode.from(skinMap.get("CurrentInputMode").getAsInt());
        }

        if (skinMap.has("DefaultInputMode")) {
            this.defaultInputMode = InputMode.from(skinMap.get("DefaultInputMode").getAsInt());
        }

        var skinBuilder = Skin.builder();
        if (skinMap.has("SkinId")) {
            skinBuilder.skinId(skinMap.get("SkinId").getAsString());
        }

        if (skinMap.has("SkinResourcePatch")) {
            skinBuilder.skinResourcePatch(new String(Base64.getDecoder().decode(skinMap.get("SkinResourcePatch").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("SkinGeometryData")) {
            skinBuilder.skinGeometry(new String(Base64.getDecoder().decode(skinMap.get("SkinGeometryData").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("SkinGeometryDataEngineVersion")) {
            skinBuilder.geometryDataEngineVersion(new String(Base64.getDecoder().decode(skinMap.get("SkinGeometryDataEngineVersion").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("AnimationData")) {
            skinBuilder.animationData(new String(Base64.getDecoder().decode(skinMap.get("AnimationData").getAsString()), StandardCharsets.UTF_8));
        }

        if (skinMap.has("CapeId")) {
            skinBuilder.capeId(skinMap.get("CapeId").getAsString());
        }

        if (skinMap.has("SkinColor")) {
            skinBuilder.skinColor(skinMap.get("SkinColor").getAsString());
        }

        if (skinMap.has("ArmSize")) {
            skinBuilder.armSize(skinMap.get("ArmSize").getAsString());
        }

        if (skinMap.has("PlayFabID")) {
            skinBuilder.playFabId(skinMap.get("PlayFabID").getAsString());
        }

        skinBuilder.skinData(this.getImage(skinMap, "Skin"));
        skinBuilder.capeData(this.getImage(skinMap, "Cape"));

        if (skinMap.has("PremiumSkin")) {
            skinBuilder.premiumSkin(skinMap.get("PremiumSkin").getAsBoolean());
        }

        if (skinMap.has("PersonaSkin")) {
            skinBuilder.personaSkin(skinMap.get("PersonaSkin").getAsBoolean());
        }

        if (skinMap.has("CapeOnClassicSkin")) {
            skinBuilder.personaCapeOnClassicSkin(skinMap.get("CapeOnClassicSkin").getAsBoolean());
        }

        if (skinMap.has("AnimatedImageData")) {
            JsonArray array = skinMap.getAsJsonArray("AnimatedImageData");
            var list = new ArrayList<Skin.AnimationData>();
            for (JsonElement jsonElement : array) {
                list.add(this.getSkinAnimationData(jsonElement.getAsJsonObject()));
            }
            skinBuilder.animations(list);
        }

        if (skinMap.has("PersonaPieces")) {
            var list = new ArrayList<Skin.PersonaPieces>();
            for (JsonElement jsonElement : skinMap.getAsJsonArray("PersonaPieces")) {
                list.add(this.getPersonaPiece(jsonElement.getAsJsonObject()));
            }
            skinBuilder.personaPieces(list);
        }

        if (skinMap.has("PieceTintColors")) {
            var list = new ArrayList<Skin.PersonaPieceTintColor>();
            for (JsonElement jsonElement : skinMap.getAsJsonArray("PieceTintColors")) {
                list.add(this.getPersonaPieceTint(jsonElement.getAsJsonObject()));
            }
            skinBuilder.pieceTintColors(list);
        }

        this.skin = skinBuilder.build();
    }

    /**
     * Parses the payload segment of a client JWT as a JSON object. NetEase payloads
     * use URL-safe Base64, ordinary payloads use basic Base64, and this helper does
     * not verify the JWT signature.
     *
     * @throws IllegalArgumentException if {@code token} contains fewer than two
     * dot-separated segments or its payload cannot be Base64 decoded
     */
    private JsonObject decodeToken(String token, boolean isNetEaseClient) {
        String[] tokenSplit = token.split("\\.");
        if (tokenSplit.length < 2) {
            throw new IllegalArgumentException("Invalid token length");
        }
        // Decode the JWT payload with the alphabet selected for its client family so URL-safe NetEase
        // tokens are not rejected by the ordinary Base64 decoder.
        Base64.Decoder decoder = isNetEaseClient ? Base64.getUrlDecoder() : Base64.getDecoder();
        return GSON.fromJson(new String(decoder.decode(tokenSplit[1]), StandardCharsets.UTF_8), JsonObject.class);
    }

    /**
     * Decodes a skin or cape image selected by the supplied metadata prefix.
     * Explicit dimensions are used when both are present; otherwise recognized byte
     * lengths infer dimensions, and an absent data field yields
     * {@code Skin.ImageData.EMPTY}.
     */
    private Skin.ImageData getImage(JsonObject skinMap, String name) {
        if (skinMap.has(name + "Data")) {
            byte[] skinImage = Base64.getDecoder().decode(skinMap.get(name + "Data").getAsString());
            if (skinMap.has(name + "ImageHeight") && skinMap.has(name + "ImageWidth")) {
                int width = skinMap.get(name + "ImageWidth").getAsInt();
                int height = skinMap.get(name + "ImageHeight").getAsInt();
                return new Skin.ImageData(width, height, skinImage);
            } else {
                // Older metadata can omit explicit image dimensions; infer only recognized dimensions
                // from byte length and let unsupported images reject the login.
                return Skin.ImageData.from(skinImage);
            }
        }
        return Skin.ImageData.EMPTY;
    }

    /**
     * Decodes one animation's image, dimensions, frame count, and enum indexes.
     * Required metadata and supported indexes must be present; failures propagate to
     * the login decoder instead of selecting a replacement animation.
     */
    private Skin.AnimationData getSkinAnimationData(JsonObject animationData) {
        byte[] data = Base64.getDecoder().decode(animationData.get("Image").getAsString());
        int width = animationData.get("ImageWidth").getAsInt();
        int height = animationData.get("ImageHeight").getAsInt();
        float frames = animationData.get("Frames").getAsFloat();
        var type = Skin.AnimationType.from(animationData.get("Type").getAsInt());
        var expression = Skin.ExpressionType.from(animationData.get("AnimationExpression").getAsInt());
        return new Skin.AnimationData(new Skin.ImageData(width, height, data), type, frames, expression);
    }

    /**
     * Decodes the identifiers and default flag for one Persona skin piece.
     */
    private Skin.PersonaPieces getPersonaPiece(JsonObject personaPiece) {
        String pieceId = personaPiece.get("PieceId").getAsString();
        String pieceType = personaPiece.get("PieceType").getAsString();
        String packId = personaPiece.get("PackId").getAsString();
        String productId = personaPiece.get("ProductId").getAsString();
        boolean isDefault = personaPiece.get("IsDefault").getAsBoolean();
        return new Skin.PersonaPieces(pieceId, pieceType, packId, isDefault, productId);
    }

    /**
     * Decodes one Persona piece's type and creates its mutable color list.
     */
    private Skin.PersonaPieceTintColor getPersonaPieceTint(JsonObject personaPieceTint) {
        String pieceType = personaPieceTint.get("PieceType").getAsString();
        List<String> colors = new ArrayList<>();
        for (JsonElement element : personaPieceTint.getAsJsonArray("Colors")) {
            colors.add(element.getAsString());
        }
        return new Skin.PersonaPieceTintColor(pieceType, colors);
    }

}
