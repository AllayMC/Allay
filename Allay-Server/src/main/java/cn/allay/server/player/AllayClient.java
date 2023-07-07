package cn.allay.server.player;

import cn.allay.api.entity.impl.EntityPlayer;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.entity.type.VanillaEntityTypes;
import cn.allay.api.math.location.FixedLoc;
import cn.allay.api.network.Client;
import cn.allay.api.player.data.LoginData;
import cn.allay.api.server.Server;
import cn.allay.api.world.biome.BiomeTypeRegistry;
import cn.allay.api.world.chunk.Chunk;
import lombok.Getter;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public class AllayClient implements Client {

    private final BedrockServerSession session;
    @Getter
    private final Server server;
    @Getter
    private LoginData loginData;
    @Getter
    private String name;
    @Getter
    private EntityPlayer playerEntity;
    @Getter
    private boolean online = false;

    private AllayClient(BedrockServerSession session, Server server) {
        this.session = session;
        this.server = server;
        session.setPacketHandler(new AllayClientPacketHandler());
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

    /**
     * 登入完成后调用。发送玩家需要的数据并生成玩家实体。此时可以认为玩家已进服但未加载完成
     */
    @Override
    public void initializePlayer() {
        online = true;
        server.onLoginFinish(this);
        initPlayerEntity();
        sendStartGamePacket();
    }

    private void initPlayerEntity() {
        var spawnLocation = server.getDefaultWorld().getSpawnLocation();
        //TODO: Load player data
        playerEntity = VanillaEntityTypes.PLAYER_TYPE.createEntity(new EntityInitInfo.Simple(spawnLocation));
    }

    private void sendStartGamePacket() {
        var spawnWorld = server.getDefaultWorld();
        var startGamePacket = new StartGamePacket();
        startGamePacket.setUniqueEntityId(playerEntity.getUniqueId());
        //TODO: WOC?
        startGamePacket.setRuntimeEntityId(playerEntity.getUniqueId());
        //TODO
        startGamePacket.setPlayerGameType(GameType.CREATIVE);
        var loc = playerEntity.getLocation();
        startGamePacket.setPlayerPosition(Vector3f.from(loc.getX(), loc.getY(), loc.getZ()));
        startGamePacket.setRotation(Vector2f.from(loc.getPitch(), loc.getYaw()));
        startGamePacket.setSeed(-1L);
        startGamePacket.setDimensionId(spawnWorld.getDimensionInfo().dimensionId());
        startGamePacket.setDimensionId(0);
        startGamePacket.setGeneratorId(1);
        startGamePacket.setLevelGameType(spawnWorld.getWorldGameType());
        startGamePacket.setDifficulty(spawnWorld.getDifficulty().ordinal());
        startGamePacket.setTrustingPlayers(true);
        startGamePacket.setDefaultSpawn(Vector3i.from(0, 64, 0));
        startGamePacket.setDayCycleStopTime(7000);
        startGamePacket.setLevelName(server.getServerSettings().motd());
        //TODO
        startGamePacket.setLevelId("world");
        //TODO
        startGamePacket.setDefaultPlayerPermission(PlayerPermission.OPERATOR);
        startGamePacket.setServerChunkTickRange(4);
        startGamePacket.setVanillaVersion(server.getNetworkServer().getCodec().getMinecraftVersion());
        startGamePacket.setPremiumWorldTemplateId("");
        startGamePacket.setInventoriesServerAuthoritative(false);
        //TODO
        startGamePacket.setItemDefinitions(List.of());
        //TODO
        startGamePacket.setAuthoritativeMovementMode(AuthoritativeMovementMode.CLIENT);
        startGamePacket.setCommandsEnabled(true);
        startGamePacket.setMultiplayerGame(true);
        startGamePacket.setBroadcastingToLan(true);
        startGamePacket.setMultiplayerCorrelationId(UUID.randomUUID().toString());
        startGamePacket.setXblBroadcastMode(GamePublishSetting.PUBLIC);
        startGamePacket.setPlatformBroadcastMode(GamePublishSetting.PUBLIC);
        //TODO
        startGamePacket.setCurrentTick(0);
        startGamePacket.setServerEngine("Allay");
        startGamePacket.setPlayerPropertyData(NbtMap.EMPTY);
        startGamePacket.setWorldTemplateId(new UUID(0, 0));
        startGamePacket.setWorldEditor(false);
        startGamePacket.setChatRestrictionLevel(ChatRestrictionLevel.NONE);
        startGamePacket.setSpawnBiomeType(SpawnBiomeType.DEFAULT);
        startGamePacket.setCustomBiomeName("");
        startGamePacket.setEducationProductionId("");
        startGamePacket.setForceExperimentalGameplay(OptionalBoolean.empty());
        sendPacket(startGamePacket);

        var biomeDefinitionListPacket = new BiomeDefinitionListPacket();
        biomeDefinitionListPacket.setDefinitions(BiomeTypeRegistry.getRegistry().getBiomeDefinitionListTag());
        sendPacket(biomeDefinitionListPacket);

        var availableEntityIdentifiersPacket = new AvailableEntityIdentifiersPacket();
        availableEntityIdentifiersPacket.setIdentifiers(EntityTypeRegistry.getRegistry().getAvailableEntityIdentifierTag());
        sendPacket(availableEntityIdentifiersPacket);

        var playStatusPacket = new PlayStatusPacket();
        playStatusPacket.setStatus(PlayStatusPacket.Status.PLAYER_SPAWN);
        sendPacket(playStatusPacket);
    }

    @Override
    public FixedLoc<Float> getLocation() {
        return playerEntity.getLocation();
    }

    @Override
    public boolean isLoaderActive() {
        return isOnline();
    }

    @Override
    public void onChunkLoad(Chunk chunk, int hashXZ) {
        //TODO
    }

    @Override
    public void onChunkUnload(Chunk chunk, int hashXZ) {
        //TODO
    }


    private class AllayClientPacketHandler implements BedrockPacketHandler {

        @Override
        public void onDisconnect(String reason) {
            server.onClientDisconnect(AllayClient.this);
            getLocation().getWorld().removeClient(AllayClient.this);
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
            loginData = LoginData.decode(packet);

            //TODO: event
            if (!loginData.isXboxAuthenticated()) {
                disconnect("disconnectionScreen.notAuthenticated");
                return PacketSignal.HANDLED;
            }

            name = loginData.getDisplayName();
            if (!NAME_PATTERN.matcher(name).matches()) {
                disconnect("disconnectionScreen.invalidName");
                return PacketSignal.HANDLED;
            }

            if (server.getOnlineClients().containsKey(name)) {
                disconnect("disconnectionScreen.loggedinOtherLocation");
                return PacketSignal.HANDLED;
            }

            if (!loginData.getSkin().isValid()) {
                session.disconnect("disconnectionScreen.invalidSkin");
                return PacketSignal.HANDLED;
            }

            //TODO 网络加密
            completeLogin();

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
                    //TODO: RP
                }
                case HAVE_ALL_PACKS -> {
                    var stackPacket = new ResourcePackStackPacket();
                    stackPacket.setGameVersion(server.getNetworkServer().getCodec().getMinecraftVersion());
                    sendPacket(stackPacket);
                }
                case COMPLETED -> {
                    initializePlayer();
                }
            }
            return PacketSignal.HANDLED;
        }
    }
}
