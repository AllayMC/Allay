package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
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
    public void handle(Player player, LoginPacket packet) {
        var allayPlayer = (AllayPlayer) player;
        var loginData = decodeLoginData(packet);
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
            allayPlayer.sendPacketImmediately(handshakePacket);

            var encryptionSecretKey = EncryptionUtils.getSecretKey(serverKeyPair.getPrivate(), clientKey, token);
            allayPlayer.getSession().enableEncryption(encryptionSecretKey);
            // completeLogin() when client send back ClientToServerHandshakePacket
        } catch (Exception exception) {
            log.warn("Failed to initialize encryption for client {}", name, exception);
            player.disconnect("disconnectionScreen.internalError");
        }
    }

    /**
     * Decodes login data for the international protocol branch.
     *
     * @param packet the login packet
     * @return decoded login data, or {@code null} when decoding fails
     */
    protected AllayLoginData decodeLoginData(LoginPacket packet) {
        return AllayLoginData.decode(packet, false);
    }

    /**
     * Validates an international player name.
     *
     * @param name the decoded player name
     * @return whether the name is valid for this protocol branch
     */
    protected boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LOGIN;
    }
}
