package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.data.LoginData;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.event.player.PlayerLoggedInEvent;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.processor.login.ILoginPacketProcessor;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayStringUtils;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerToClientHandshakePacket;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;

import java.util.regex.Pattern;

/**
 * Allay Project 2024/2/2
 *
 * @author daoge_cmd
 */
@Slf4j
public class LoginPacketProcessor extends ILoginPacketProcessor<LoginPacket> {

    public static final Pattern NAME_PATTERN = Pattern.compile("^(?! )([a-zA-Z0-9_ ]{2,15}[a-zA-Z0-9_])(?<! )$");

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LOGIN;
    }

    @Override
    public void handle(EntityPlayer player, LoginPacket packet) {
        var loginData = LoginData.decode(packet);
        player.setLoginData(loginData);
        var server = Server.getInstance();

        if (Server.SETTINGS.genericSettings().isWhitelisted() && !server.isWhitelisted(player.getOriginName())) {
            player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOTALLOWED);
            return;
        }

        if (server.isBanned(player.getUUID().toString()) || server.isBanned(player.getOriginName())) {
            // TODO: I18n
            player.disconnect("You are banned!");
            return;
        }

        if (server.isIPBanned(AllayStringUtils.fastTwoPartSplit(player.getClientSession().getSocketAddress().toString().substring(1), ":", "")[0])) {
            // TODO: I18n
            player.disconnect("Your IP is banned!");
            return;
        }

        if (!loginData.isXboxAuthenticated() && Server.SETTINGS.networkSettings().xboxAuth()) {
            player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOTAUTHENTICATED);
            return;
        }

        var name = loginData.getDisplayName();
        if (!NAME_PATTERN.matcher(name).matches()) {
            player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_INVALIDNAME);
            return;
        }

        if (!loginData.getSkin().isValid()) {
            player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_INVALIDSKIN);
            return;
        }

        var otherDevice = server.getOnlinePlayers().get(loginData.getUuid());
        if (otherDevice != null) {
            otherDevice.disconnect(TrKeys.M_DISCONNECTIONSCREEN_LOGGEDINOTHERLOCATION);
        }

        var event = new PlayerLoggedInEvent(player);
        Server.getInstance().getEventBus().callEvent(event);
        if (event.isCancelled()) {
            player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOREASON);
            return;
        }

        if (!Server.SETTINGS.networkSettings().enableNetworkEncryption()) {
            player.completeLogin();
            return;
        }

        try {
            var clientKey = EncryptionUtils.parseKey(loginData.getIdentityPublicKey());
            var encryptionKeyPair = EncryptionUtils.createKeyPair();
            var encryptionToken = EncryptionUtils.generateRandomToken();
            var encryptionSecretKey = EncryptionUtils.getSecretKey(
                    encryptionKeyPair.getPrivate(), clientKey,
                    encryptionToken
            );
            player.setEncryptionSecretKey(encryptionSecretKey);
            var encryptionJWT = EncryptionUtils.createHandshakeJwt(encryptionKeyPair, encryptionToken);
            player.setNetworkEncryptionEnabled(true);
            var handshakePacket = new ServerToClientHandshakePacket();
            handshakePacket.setJwt(encryptionJWT);
            player.sendPacketImmediately(handshakePacket);
            player.getClientSession().enableEncryption(encryptionSecretKey);
            // completeLogin() when client send back ClientToServerHandshakePacket
        } catch (Exception exception) {
            log.warn("Failed to initialize encryption for client " + name, exception);
            player.disconnect("disconnectionScreen.internalError");
        }
    }
}
