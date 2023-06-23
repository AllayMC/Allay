package cn.allay.server.player;

import cn.allay.api.network.Client;
import cn.allay.api.player.data.LoginData;
import cn.allay.api.server.Server;
import lombok.Getter;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.regex.Pattern;

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
            var loginFailedPacket = new PlayStatusPacket();
            if (protocolVersion > supportedProtocolVersion) {
                loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_SERVER_OLD);
            } else {
                loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_CLIENT_OLD);
            }
            session.sendPacketImmediately(loginFailedPacket);
            return PacketSignal.HANDLED;
        }
        var settingsPacket = new NetworkSettingsPacket();
        //TODO: Support other compression algorithms
        settingsPacket.setCompressionAlgorithm(PacketCompressionAlgorithm.ZLIB);
        settingsPacket.setCompressionThreshold(0);
        sendPacketImmediately(settingsPacket);
        session.setCompression(settingsPacket.getCompressionAlgorithm());
        return PacketSignal.HANDLED;
    }

    public static final Pattern NAME_PATTERN = Pattern.compile("^(?! )([a-zA-Z0-9_ ]{2,15}[a-zA-Z0-9_])(?<! )$");

    @Override
    public PacketSignal handle(LoginPacket packet) {
        this.loginData = LoginData.decode(packet);

        //TODO: event
        if (!loginData.isXboxAuthenticated()) {
            disconnect("disconnectionScreen.notAuthenticated");
            return PacketSignal.HANDLED;

        }

        var username = this.loginData.getDisplayName();
        if (!NAME_PATTERN.matcher(username).matches()) {
            disconnect("disconnectionScreen.invalidName");
            return PacketSignal.HANDLED;
        }

        if (!loginData.getSkin().isValid()) {
            this.session.disconnect("disconnectionScreen.invalidSkin");
            return PacketSignal.HANDLED;
        }
        completeLogin();
        //TODO 网络加密
        return PacketSignal.HANDLED;
    }

    protected void completeLogin() {
        var playStatusPacket = new PlayStatusPacket();
        if (server.getOnlineClientCount() >= server.getServerSettings().maxClientCount()) {
            playStatusPacket.setStatus(PlayStatusPacket.Status.FAILED_SERVER_FULL_SUB_CLIENT);
        } else {
            playStatusPacket.setStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);
        }
        sendPacket(playStatusPacket);
        //TODO: Resource Packs
        sendPacket(new ResourcePacksInfoPacket());
    }

    @Override
    public PacketSignal handle(ResourcePackClientResponsePacket packet) {
        switch (packet.getStatus()) {
            case SEND_PACKS -> {
                //TODO
            }
            case HAVE_ALL_PACKS -> {
                var stackPacket = new ResourcePackStackPacket();
                stackPacket.setGameVersion(server.getNetworkServer().getCodec().getMinecraftVersion());
                sendPacket(stackPacket);
            }
            case COMPLETED -> {
                //TODO: Init player entity
            }
        }
        return PacketSignal.HANDLED;
    }
}
