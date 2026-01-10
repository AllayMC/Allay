package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayLoginData;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.player.AllayPlayerManager;
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
    public void handle(Player player, LoginPacket packet) {
        var allayPlayer = (AllayPlayer) player;

        var loginData = AllayLoginData.decode(packet);
        if (loginData == null) {
            log.warn("Failed to decode login packet from {}", player.getSocketAddress());
            player.disconnect("Invalid login data");
            return;
        }

        allayPlayer.setLoginData(loginData);

        var server = Server.getInstance();
        var playerManager = (AllayPlayerManager) server.getPlayerManager();

        var offlinePlayer = playerManager.getOfflinePlayerService().handleUpdates(loginData);
        allayPlayer.setStorageUuid(offlinePlayer.getStorageUuid());

        if (AllayServer.getSettings().genericSettings().enableWhitelist() && !playerManager.isWhitelisted(player.getOriginName())) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOTALLOWED);
            return;
        }

        if (playerManager.isBanned(player.getLoginData().getUuid().toString()) || playerManager.isBanned(player.getOriginName())) {
            // TODO: I18n
            player.disconnect("You are banned!");
            return;
        }

        if (!loginData.isAuthed() && AllayServer.getSettings().networkSettings().xboxAuth()) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOTAUTHENTICATED);
            return;
        }

        var name = loginData.getXname();
        if (!NAME_PATTERN.matcher(name).matches()) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_INVALIDNAME);
            return;
        }

        if (!loginData.getSkin().isValid()) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_INVALIDSKIN);
            return;
        }

        var existingPlayer = playerManager.getPlayers().get(loginData.getUuid());
        if (existingPlayer != null) {
            existingPlayer.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_LOGGEDINOTHERLOCATION);
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

            var secretKey = EncryptionUtils.getSecretKey(serverKeyPair.getPrivate(), clientKey, token);
            allayPlayer.getSession().enableEncryption(secretKey);
            // completeLogin() will be called when ClientToServerHandshakePacket is received
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
