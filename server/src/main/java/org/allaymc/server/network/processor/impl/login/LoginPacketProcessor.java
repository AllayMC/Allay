package org.allaymc.server.network.processor.impl.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.data.LoginData;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.impl.ingame.ILoginPacketProcessor;
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
    public void handle(EntityPlayer player, LoginPacket packet) {
        var loginData = LoginData.decode(packet);
        var networkComponent = (EntityPlayerNetworkComponentImpl) ((EntityPlayerImpl) player).getPlayerNetworkComponent();
        networkComponent.setLoginData(loginData);

        var server = Server.getInstance();
        if (Server.SETTINGS.genericSettings().isWhitelisted() && !server.getPlayerService().isWhitelisted(player.getOriginName())) {
            player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOTALLOWED);
            return;
        }

        if (server.getPlayerService().isBanned(player.getUUID().toString()) || server.getPlayerService().isBanned(player.getOriginName())) {
            // TODO: I18n
            player.disconnect("You are banned!");
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

        var otherDevice = server.getPlayerService().getPlayers().get(loginData.getUuid());
        if (otherDevice != null) {
            otherDevice.disconnect(TrKeys.M_DISCONNECTIONSCREEN_LOGGEDINOTHERLOCATION);
        }

        if (!Server.SETTINGS.networkSettings().enableNetworkEncryption()) {
            networkComponent.completeLogin();
            return;
        }

        try {
            var clientKey = EncryptionUtils.parseKey(loginData.getIdentityPublicKey());
            var serverKeyPair = EncryptionUtils.createKeyPair();
            var token = EncryptionUtils.generateRandomToken();
            var encryptionSecretKey = EncryptionUtils.getSecretKey(serverKeyPair.getPrivate(), clientKey, token);

            networkComponent.setEncryptionSecretKey(encryptionSecretKey);
            networkComponent.setNetworkEncryptionEnabled(true);

            var handshakePacket = new ServerToClientHandshakePacket();
            handshakePacket.setJwt(EncryptionUtils.createHandshakeJwt(serverKeyPair, token));
            player.sendPacketImmediately(handshakePacket);

            player.getClientSession().enableEncryption(encryptionSecretKey);
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
