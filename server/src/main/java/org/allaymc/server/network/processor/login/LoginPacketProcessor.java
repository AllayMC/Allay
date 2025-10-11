package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.server.Server;
import org.allaymc.server.AllayServer;
import org.allaymc.server.entity.component.EntityPlayerClientComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayLoginData;
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
        AllayLoginData loginData;
        try {
            loginData = AllayLoginData.decode(packet);
        } catch (Throwable t) {
            log.warn("Failed to decode login data of {}", player.getSocketAddress(), t);
            player.disconnect();
            return;
        }

        var clientComponent = (EntityPlayerClientComponentImpl) ((EntityPlayerImpl) player).getPlayerClientComponent();
        clientComponent.setLoginData(loginData);

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
        if (!NAME_PATTERN.matcher(name).matches()) {
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
            clientComponent.completeLogin();
            return;
        }

        try {
            clientComponent.setNetworkEncryptionEnabled(true);

            var clientKey = EncryptionUtils.parseKey(loginData.getIdentityPublicKey());
            var serverKeyPair = EncryptionUtils.createKeyPair();
            var token = EncryptionUtils.generateRandomToken();

            var handshakePacket = new ServerToClientHandshakePacket();
            handshakePacket.setJwt(EncryptionUtils.createHandshakeJwt(serverKeyPair, token));
            player.sendPacketImmediately(handshakePacket);

            var encryptionSecretKey = EncryptionUtils.getSecretKey(serverKeyPair.getPrivate(), clientKey, token);
            ((EntityPlayerImpl) player).getClientSession().enableEncryption(encryptionSecretKey);
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
