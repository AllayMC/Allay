package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.player.AllayLoginData;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerToClientHandshakePacket;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;

import java.util.regex.Pattern;

/**
 * {@code LoginPacketProcessor} validates decoded login identity, access policy,
 * name, and skin before completing login or beginning encryption negotiation.
 * Decoding and name checks are overridable for client variants, while the shared
 * validation sequence uses the decoded identity for whitelist, ban, and duplicate
 * connection decisions. The selected wire protocol can retain multiple codecs;
 * international {@code v2168} clients reporting the base game version select its
 * base codec before later login packets are sent.
 *
 * <p>
 * Login data is stored before access checks, and a matching existing connection
 * can be disconnected before encryption setup completes. Encryption attempts an
 * immediate handshake send before enabling the transport cipher, then waits for
 * a client acknowledgement to complete login. The send can return without
 * submission after listener rejection; this handler does not check submission
 * or delivery before enabling the cipher. Setup errors disconnect this
 * connection without restoring earlier login data, encryption flags, or a
 * previously displaced connection. Disabling encryption delegates completion
 * immediately instead of waiting for a handshake packet.
 *
 * @author daoge_cmd
 */
@Slf4j
public class LoginPacketProcessor extends ILoginPacketProcessor<LoginPacket> {

    /**
     * {@code NAME_PATTERN} accepts three through sixteen ASCII name characters,
     * allowing letters, digits, underscores, and internal spaces while rejecting
     * leading or trailing spaces in the international name check.
     */
    public static final Pattern NAME_PATTERN = Pattern.compile("^(?! )([a-zA-Z0-9_ ]{2,15}[a-zA-Z0-9_])(?<! )$");

    /**
     * Validates this connection's decoded login data and starts its next login
     * phase. Decode failure or an unavailable required codec disconnects before
     * storing login data; access, authentication, name, and skin checks follow
     * storage and can also disconnect.
     *
     * <p>
     * A duplicate identity displaces the existing connection only when device
     * information matches; otherwise this connection is rejected. With encryption
     * enabled, an immediate handshake send is attempted before cipher installation,
     * and login waits for acknowledgement. The send path can reject the packet
     * without throwing; this handler still enables encryption without checking
     * submission or delivery. The setup catch disconnects after any earlier effects
     * without resetting the encryption flag. With encryption disabled,
     * {@code completeLogin} runs directly and owns its final admission decisions.
     */
    @Override
    public void handle(Player player, LoginPacket packet) {
        var allayPlayer = (AllayPlayer) player;
        AllayLoginData loginData;
        try {
            loginData = decodeLoginData(packet);
        } catch (RuntimeException exception) {
            log.warn("Failed to decode login packet received from {}", player.getSocketAddress(), exception);
            player.disconnect();
            return;
        }

        if (loginData == null) {
            log.warn("Failed to decode login packet received from {}. The client will be disconnected", player.getSocketAddress());
            player.disconnect();
            return;
        }

        if (!selectV2168Codec(allayPlayer, loginData.getGameVersion())) {
            log.warn("Failed to configure the v2168 codec for client {}", player.getSocketAddress());
            player.disconnect();
            return;
        }

        allayPlayer.setLoginData(loginData);

        var server = Server.getInstance();
        if (AllayServer.getSettings().genericSettings().enableWhitelist() && !server.getPlayerManager().isWhitelisted(player.getOriginName())) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOTALLOWED);
            return;
        }

        if (server.getPlayerManager().isBanned(player.getLoginData().getUuid().toString()) || server.getPlayerManager().isBanned(player.getOriginName())) {
            // TODO: Replace the literal ban message with a translation key when
            // the disconnect catalog defines a matching ban reason.
            player.disconnect("You are banned!");
            return;
        }

        if (!loginData.isAuthed() && AllayServer.getSettings().networkSettings().xboxAuth()) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOTAUTHENTICATED);
            return;
        }

        var name = loginData.getXname();
        if (!isValidName(name)) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_INVALIDNAME);
            return;
        }

        if (!loginData.getSkin().isValid()) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_INVALIDSKIN);
            return;
        }

        var otherPlayer = server.getPlayerManager().getPlayers().get(loginData.getUuid());
        if (otherPlayer != null) {
            // Matching device information selects replacement of the old
            // connection; later failures in this login do not restore that
            // disconnected session or make replacement atomic.
            if (otherPlayer.getLoginData().getDeviceInfo().equals(loginData.getDeviceInfo())) {
                otherPlayer.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_LOGGEDINOTHERLOCATION);
            } else {
                player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_LOGGEDINOTHERLOCATION);
                return;
            }
        }

        if (!AllayServer.getSettings().networkSettings().enableNetworkEncryption()) {
            allayPlayer.completeLogin();
            return;
        }

        try {
            allayPlayer.setNetworkEncryptionEnabled(true);

            var clientKey = EncryptionUtils.parseKey(loginData.getIdentityPublicKey());
            var serverKeyPair = EncryptionUtils.createKeyPair();
            var token = EncryptionUtils.generateRandomToken();

            var handshakePacket = new ServerToClientHandshakePacket();
            handshakePacket.setJwt(EncryptionUtils.createHandshakeJwt(serverKeyPair, token));
            // Attempt handshake submission before installing encryption so an
            // accepted packet uses the current unencrypted transport. This void
            // send provides no submission result for the following cipher setup.
            allayPlayer.sendPacketImmediately(handshakePacket);

            var encryptionSecretKey = EncryptionUtils.getSecretKey(serverKeyPair.getPrivate(), clientKey, token);
            allayPlayer.getSession().enableEncryption(encryptionSecretKey);
            // ClientToServerHandshakePacketProcessor completes login after the
            // client's acknowledgement, keeping resource-pack login traffic
            // behind the installed cipher rather than starting it here.
        } catch (Exception exception) {
            // Key parsing, handshake sending, and cipher installation share
            // this failure path; disconnect without pretending earlier flags or
            // displaced connections were rolled back.
            log.warn("Failed to initialize encryption for client {}", name, exception);
            player.disconnect("disconnectionScreen.internalError");
        }
    }

    /**
     * Decodes identity and skin data using international payload validation.
     * The shared decoder logs failures and returns {@code null}; the caller
     * disconnects instead of continuing policy checks with incomplete login data.
     */
    protected AllayLoginData decodeLoginData(LoginPacket packet) {
        return AllayLoginData.decode(packet, false);
    }

    /**
     * Selects the base codec for international {@code v2168} base-version clients.
     * Other variants, protocol versions, and game-version strings return
     * {@code true} without requesting a switch.
     *
     * <p>
     * The wire version alone cannot distinguish the base release from its hotfix,
     * whose score serialization differs. The reported game-version stem supplies
     * that distinction before login continues. A required switch returns the
     * player's actual switch result, so a closed connection can abort login;
     * a {@code true} result on an unmatched branch means only that no switch was needed.
     */
    boolean selectV2168Codec(AllayPlayer player, String minecraftVersion) {
        var protocol = player.getProtocol();
        if (protocol.getVariant() != ClientVariant.INTERNATIONAL
                || protocol.getProtocolVersion() != Bedrock_v2168.CODEC.getProtocolVersion()
                || !isV2168BaseVersion(minecraftVersion)) {
            return true;
        }
        // The v2168 hotfix changes SetScore serialization without changing the
        // wire version, so use the reported base-version stem to restore the
        // base serializer for these clients.
        return player.switchProtocolCodec(Bedrock_v2168.CODEC);
    }

    /**
     * Returns whether the reported version equals the base codec's game version
     * or starts with that version followed by a dot. A {@code null} string returns
     * {@code false}, and unrelated numeric prefixes do not match.
     */
    private static boolean isV2168BaseVersion(String minecraftVersion) {
        var baseVersion = Bedrock_v2168.CODEC.getMinecraftVersion();
        return minecraftVersion != null && (minecraftVersion.equals(baseVersion) || minecraftVersion.startsWith(baseVersion + "."));
    }

    /**
     * Returns whether the international name matches {@code NAME_PATTERN}.
     * A {@code null} name is invalid, and this method performs no trimming or
     * other normalization before the three-to-sixteen-character ASCII pattern
     * is applied to the supplied value.
     */
    protected boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    /**
     * Returns the login packet type shared by both login variants.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LOGIN;
    }
}
