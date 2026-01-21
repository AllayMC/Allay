package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.network.multiversion.MultiVersion;
import org.allaymc.server.player.AllayLoginData;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerToClientHandshakePacket;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;

import java.util.regex.Pattern;

/**
 * @author daoge_cmd
 */
@Slf4j
public class LoginPacketProcessor extends ILoginPacketProcessor<LoginPacket> {

    public static final Pattern NAME_PATTERN = Pattern.compile("^(?! )([a-zA-Z0-9_ ]{2,15}[a-zA-Z0-9_])(?<! )$");

    @Override
    @MultiVersion(version = "*-NetEase", details = "NetEase clients skip Xbox authentication and allow Chinese player names")
    public void handle(Player player, LoginPacket packet) {
        var allayPlayer = (AllayPlayer) player;
        boolean isNetEaseClient = allayPlayer.isNetEasePlayer();

        var loginData = AllayLoginData.decode(packet, isNetEaseClient);
        if (loginData == null) {
            log.warn("Failed to decode login packet received from {}. The client will be disconnected", player.getSocketAddress());
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
            // TODO: I18n
            player.disconnect("You are banned!");
            return;
        }

        if (!loginData.isAuthed() && AllayServer.getSettings().networkSettings().xboxAuth()) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOTAUTHENTICATED);
            return;
        }

        var name = loginData.getXname();
        // NetEase clients allow Chinese names, skip the standard name pattern validation
        if (!isNetEaseClient && !NAME_PATTERN.matcher(name).matches()) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_INVALIDNAME);
            return;
        }
        // For NetEase clients, only check that name is not empty
        if (isNetEaseClient && (name == null || name.trim().isEmpty())) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_INVALIDNAME);
            return;
        }

        if (!loginData.getSkin().isValid()) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_INVALIDSKIN);
            return;
        }

        var otherDevice = server.getPlayerManager().getPlayers().get(loginData.getUuid());
        if (otherDevice != null) {
            otherDevice.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_LOGGEDINOTHERLOCATION);
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
            player.sendPacketImmediately(handshakePacket);

            var encryptionSecretKey = EncryptionUtils.getSecretKey(serverKeyPair.getPrivate(), clientKey, token);
            allayPlayer.getSession().enableEncryption(encryptionSecretKey);
            // completeLogin() when client send back ClientToServerHandshakePacket
        } catch (Exception exception) {
            log.warn("Failed to initialize encryption for client {}", name, exception);
            player.disconnect("disconnectionScreen.internalError");
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LOGIN;
    }
}
