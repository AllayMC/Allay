package cn.allay.server.player;

import cn.allay.api.network.Client;
import cn.allay.api.player.data.LoginData;
import cn.allay.api.server.Server;
import lombok.Getter;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
public class AllayClient implements Client {

    private final BedrockServerSession session;
    @Getter
    private final Server server;
    /**
     * Null before receive LoginPacket
     */
    @Getter
    private LoginData loginData = null;

    private AllayClient(BedrockServerSession session, Server server) {
        this.session = session;
        this.server = server;
        session.setPacketHandler(this);
    }

    public static AllayClient hold(BedrockServerSession session, Server Server) {
        return new AllayClient(session, Server);
    }

    @Override
    public void sendPacket(BedrockPacket packet) {
        session.sendPacket(packet);
    }

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {
        session.sendPacketImmediately(packet);
    }

    @Override
    public void disconnect(String reason) {
        disconnect(reason, false);
    }

    @Override
    public void disconnect(String reason, boolean hideReason) {
        server.onClientDisconnect(this);
        session.disconnect(reason, hideReason);
    }

    @Override
    public PacketSignal handle(RequestNetworkSettingsPacket packet) {
        var protocolVersion = packet.getProtocolVersion();
        var supportedProtocolVersion = Server.getInstance().getNetworkServer().getCodec().getProtocolVersion();
        if (protocolVersion != supportedProtocolVersion) {
            String message;
            if (protocolVersion < supportedProtocolVersion) {
                message = "disconnectionScreen.outdatedClient";
            } else {
                message = "disconnectionScreen.outdatedServer";
            }
            disconnect(message, false);
        }
        var settingsPacket = new NetworkSettingsPacket();
        //TODO: Support other compression algorithms
        settingsPacket.setCompressionAlgorithm(PacketCompressionAlgorithm.ZLIB);
        settingsPacket.setCompressionThreshold(1);
        sendPacketImmediately(settingsPacket);
        return PacketSignal.HANDLED;
    }

    @Override
    public PacketSignal handle(LoginPacket packet) {
        this.loginData = LoginData.decode(packet);

        //TODO: event
        if (!loginData.isXboxAuthenticated()) {
            disconnect("disconnectionScreen.notAuthenticated");
            return PacketSignal.HANDLED;
        }

        if (server.getOnlineClientCount() >= server.getServerSettings().maxClientCount()) {
            disconnect("disconnectionScreen.serverFull");
            return PacketSignal.HANDLED;
        }

        //TODO: Resource Packs
        ResourcePacksInfoPacket resourcePacksInfoPacket = new ResourcePacksInfoPacket();
        resourcePacksInfoPacket.setForcedToAccept(false);
        resourcePacksInfoPacket.setForcingServerPacksEnabled(false);
        resourcePacksInfoPacket.setScriptingEnabled(false);
        sendPacketImmediately(resourcePacksInfoPacket);
        return PacketSignal.HANDLED;
    }
}
