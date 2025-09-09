package org.allaymc.server.entity.component.player;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.network.ClientDisconnectEvent;
import org.allaymc.api.eventbus.event.network.PacketReceiveEvent;
import org.allaymc.api.eventbus.event.network.PacketSendEvent;
import org.allaymc.api.eventbus.event.player.PlayerLoginEvent;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.network.ClientStatus;
import org.allaymc.api.network.ProtocolInfo;
import org.allaymc.api.player.LoginData;
import org.allaymc.api.player.PlayerData;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.CPlayerLoggedInEvent;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.DeferredData;
import org.allaymc.server.network.MultiVersion;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.player.AllayPlayerManager;
import org.allaymc.server.utils.Utils;
import org.allaymc.server.world.AllayWorld;
import org.allaymc.server.world.gamerule.AllayGameRules;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.netty.channel.raknet.RakServerChannel;
import org.cloudburstmc.netty.handler.codec.raknet.common.RakSessionCodec;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.joml.Vector3fc;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.allaymc.api.utils.AllayNbtUtils.readVector3f;
import static org.allaymc.api.utils.AllayNbtUtils.writeVector3f;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerNetworkComponentImpl implements EntityPlayerNetworkComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:player_network_component");

    @Manager
    protected ComponentManager manager;
    @ComponentObject
    protected EntityPlayer thisPlayer;
    @Dependency
    protected EntityPlayerBaseComponentImpl baseComponent;

    protected final PacketProcessorHolder packetProcessorHolder;
    protected final AtomicInteger fullyJoinChunkThreshold;

    @Getter
    @Setter
    protected LoginData loginData;
    @Getter
    @Setter
    protected boolean networkEncryptionEnabled;
    @Getter
    @Setter
    protected boolean clientCacheEnabled;
    @Getter
    protected BedrockServerSession clientSession;

    public EntityPlayerNetworkComponentImpl() {
        this.packetProcessorHolder = new PacketProcessorHolder();
        this.fullyJoinChunkThreshold = new AtomicInteger(Server.SETTINGS.worldSettings().fullyJoinChunkThreshold());
    }

    public void handlePacketSync(BedrockPacket packet, long receiveTime) {
        var processor = packetProcessorHolder.getProcessor(packet);
        if (processor == null) {
            log.warn("Received a packet which doesn't have correspond packet handler: {}, client status: {}", packet, getClientStatus());
            return;
        }
        processor.handleSync(thisPlayer, packet, receiveTime);
    }

    public void setClientStatus(ClientStatus status) {
        this.packetProcessorHolder.setClientStatus(status);
    }

    public void setClientSession(BedrockServerSession session) {
        this.clientSession = session;
        this.packetProcessorHolder.setClientStatus(ClientStatus.CONNECTED);

        var maxLoginTime = Server.SETTINGS.networkSettings().maxLoginTime();
        if (maxLoginTime > 0) {
            Server.getInstance().getScheduler().scheduleDelayed(Server.getInstance(), () -> {
                var status = getClientStatus();
                if (status != ClientStatus.DISCONNECTED && status.ordinal() < ClientStatus.IN_GAME.ordinal()) {
                    log.warn("Session {} didn't log in within {} seconds, disconnecting...", clientSession.getSocketAddress(), maxLoginTime / 20d);
                    disconnect(TrKeys.MC_DISCONNECTIONSCREEN_TIMEOUT);
                }
                return true;
            }, maxLoginTime);
        }

        session.setPacketHandler(new BedrockPacketHandler() {
            @Override
            public PacketSignal handlePacket(BedrockPacket packet) {
                if (!getClientStatus().canHandlePackets()) {
                    return PacketSignal.HANDLED;
                }

                var event = new PacketReceiveEvent(thisPlayer, packet);
                if (!event.call()) {
                    return PacketSignal.HANDLED;
                }

                packet = event.getPacket();

                var processor = packetProcessorHolder.getProcessor(packet);
                if (processor == null) {
                    log.warn("Received a packet which doesn't have correspond packet handler: {}, client status: {}", packet, getClientStatus());
                    return PacketSignal.HANDLED;
                }

                long time;
                var world = thisPlayer.getWorld();
                if (world != null) {
                    time = world.getTick();
                } else {
                    // If player is not in any world, use server tick instead
                    time = Server.getInstance().getTick();
                }

                if (processor.handleAsync(thisPlayer, packet, time) != PacketSignal.HANDLED) {
                    if (world == null) {
                        // Packet processors should make sure that PacketProcessor.handleSync()
                        // method won't be called if player is not in any world
                        log.warn("Cannot handle sync packet {} for player {} which is not in any world!", packet.getPacketType().name(), thisPlayer);
                        processor.handleSync(thisPlayer, packet, time);
                    } else {
                        ((AllayWorld) world).addSyncPacketToQueue(thisPlayer, packet, time);
                    }
                }

                return PacketSignal.HANDLED;
            }

            @Override
            public void onDisconnect(String reason) {
                if (!packetProcessorHolder.setClientStatus(ClientStatus.DISCONNECTED, false)) {
                    // Failed to set disconnected field from false to true
                    // Which means the client may be disconnected by server
                    // by calling EntityPlayerNetworkComponentImpl::disconnect() method
                    // this shouldn't be an error
                    return;
                }
                EntityPlayerNetworkComponentImpl.this.onDisconnect(reason);
            }
        });
    }

    public void onChunkInRangeSend() {
        if (fullyJoinChunkThreshold.get() > 0 && fullyJoinChunkThreshold.decrementAndGet() == 0) {
            onFullyJoin();
        }
    }

    protected void onFullyJoin() {
        var server = Server.getInstance();
        var world = thisPlayer.getWorld();
        // Load EntityPlayer's NBT, player game mode is also updated in loadNBT()
        thisPlayer.loadNBT(server.getPlayerManager().getPlayerStorage().readPlayerData(thisPlayer).getNbt());
        thisPlayer.viewEntityMetadata(thisPlayer);
        // Send other players' abilities data to this player
        Server.getInstance().getPlayerManager().getPlayers().values().forEach(other -> {
            var abilities = ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) other).getBaseComponent()).getAbilities();
            sendPacket(abilities.encodeUpdateAbilitiesPacket());
        });
        sendPacket(Registries.COMMANDS.encodeAvailableCommandsPacketFor(thisPlayer));
        // PlayerListPacket can only be sent in this stage, otherwise the client won't show its skin
        ((AllayPlayerManager) server.getPlayerManager()).addToPlayerList(thisPlayer);
        if (server.getPlayerManager().getPlayerCount() > 1) {
            ((AllayPlayerManager) server.getPlayerManager()).sendFullPlayerListInfoTo(thisPlayer);
        }
        thisPlayer.sendAttributesToClient();
        sendInventories();
        var playStatusPacket = new PlayStatusPacket();
        playStatusPacket.setStatus(PlayStatusPacket.Status.PLAYER_SPAWN);
        sendPacket(playStatusPacket);
        world.getWorldData().sendTimeOfDay(thisPlayer);
        ((AllayWorld) world).sendWeather(thisPlayer);
        // Save player data the first time it joins
        server.getPlayerManager().getPlayerStorage().savePlayerData(thisPlayer);
    }

    @Override
    public void sendPacket(BedrockPacket packet) {
        if (!getClientStatus().canHandlePackets()) {
            return;
        }

        var event = new PacketSendEvent(thisPlayer, packet);
        if (!event.call()) {
            return;
        }

        this.clientSession.sendPacket(event.getPacket());
    }

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {
        if (!getClientStatus().canHandlePackets()) {
            return;
        }

        var event = new PacketSendEvent(thisPlayer, packet);
        if (!event.call()) {
            return;
        }

        this.clientSession.sendPacketImmediately(event.getPacket());
    }

    @Override
    public void disconnect(@MayContainTrKey String reason) {
        if (!packetProcessorHolder.setClientStatus(ClientStatus.DISCONNECTED)) {
            log.warn("Trying to disconnect a player who is already disconnected!");
            return;
        }

        var disconnectReason = I18n.get().tr(thisPlayer.getLoginData().getLangCode(), reason);
        try {
            onDisconnect(disconnectReason);
            // Tell the client that it should disconnect
            if (thisPlayer.getClientSession().isConnected()) {
                thisPlayer.getClientSession().disconnect(disconnectReason);
            }
        } catch (Throwable t) {
            log.error("Error while disconnecting the session", t);
        }
    }

    @Override
    public ClientStatus getClientStatus() {
        return packetProcessorHolder.getClientStatus();
    }

    @Override
    public ClientStatus getLastClientStatus() {
        return packetProcessorHolder.getLastClientStatus();
    }

    protected void onDisconnect(String disconnectReason) {
        new ClientDisconnectEvent(clientSession, disconnectReason).call();
        thisPlayer.closeAllOpenedContainers();
        ((AllayPlayerManager) Server.getInstance().getPlayerManager()).onDisconnect(thisPlayer);
    }

    @Override
    public int getPing() {
        var rakServerChannel = (RakServerChannel) clientSession.getPeer().getChannel().parent();
        var childChannel = rakServerChannel.getChildChannel(clientSession.getSocketAddress());
        var rakSessionCodec = childChannel.rakPipeline().get(RakSessionCodec.class);
        return (int) rakSessionCodec.getPing();
    }

    protected void sendInventories() {
        thisPlayer.viewContents(thisPlayer.getContainer(FullContainerType.PLAYER_INVENTORY));
        thisPlayer.viewContents(thisPlayer.getContainer(FullContainerType.OFFHAND));
        thisPlayer.viewContents(thisPlayer.getContainer(FullContainerType.ARMOR));
        // No need to send cursor's content to client because there is nothing in cursor
    }

    public void initializePlayer() {
        var server = Server.getInstance();
        // initializePlayer() method will read all the data in PlayerData except nbt
        // To be more exactly, we will validate and set player's current pos in this method
        // And nbt will be used in EntityPlayer::loadNBT() in doFirstSpawn() method
        var playerData = server.getPlayerManager().getPlayerStorage().readPlayerData(thisPlayer);
        // Validate and set player pos
        Dimension dimension;
        Vector3fc currentPos;

        var logOffWorld = server.getWorldPool().getWorld(playerData.getWorld());
        if (logOffWorld == null || logOffWorld.getDimension(playerData.getDimension()) == null) {
            // The world or dimension where player logged off doesn't exist
            // Fallback to global spawn point
            dimension = server.getWorldPool().getGlobalSpawnPoint().dimension();
            currentPos = new org.joml.Vector3f(server.getWorldPool().getGlobalSpawnPoint());
            // The old pos stored in playerNBT is invalid, we should replace it with the new one!
            var builder = playerData.getNbt().toBuilder();
            writeVector3f(builder, EntityPlayerBaseComponentImpl.TAG_POS, currentPos);
            playerData.setNbt(builder.build());
            // Save new player data back to storage
            server.getPlayerManager().getPlayerStorage().savePlayerData(thisPlayer.getLoginData().getUuid(), playerData);
        } else {
            dimension = logOffWorld.getDimension(playerData.getDimension());
            // Read current pos from playerNBT
            currentPos = readVector3f(playerData.getNbt(), EntityPlayerBaseComponentImpl.TAG_POS);
        }

        baseComponent.setLocationBeforeSpawn(new Location3d(currentPos.x(), currentPos.y(), currentPos.z(), dimension));
        dimension.addPlayer(thisPlayer);

        sendPacketImmediately(encodeStartGamePacket(dimension.getWorld(), playerData, dimension));

        var codecHelper = clientSession.getPeer().getCodecHelper();
        codecHelper.setItemDefinitions(SimpleDefinitionRegistry.<ItemDefinition>builder().addAll(DeferredData.ITEM_DEFINITIONS.get()).build());
        codecHelper.setBlockDefinitions(SimpleDefinitionRegistry.<BlockDefinition>builder().addAll(DeferredData.BLOCK_DEFINITIONS.get()).build());

        var itemComponentPacket = new ItemComponentPacket();
        itemComponentPacket.getItems().addAll(DeferredData.ITEM_DEFINITIONS.get());
        sendPacketImmediately(itemComponentPacket);

        sendPacket(Registries.CREATIVE_ITEMS.getCreativeContentPacketFor(thisPlayer.getLoginData().getLangCode()));

        sendPacket(DeferredData.AVAILABLE_ENTITY_IDENTIFIERS_PACKET.get());
        sendPacket(DeferredData.BIOME_DEFINITION_LIST_PACKET.get());
        sendPacket(DeferredData.CRAFTING_DATA_PACKET.get());
        sendPacket(DeferredData.TRIM_DATA_PACKET.get());
    }

    protected StartGamePacket encodeStartGamePacket(World spawnWorld, PlayerData playerData, Dimension dimension) {
        var packet = new StartGamePacket();
        packet.getGamerules().addAll(((AllayGameRules) spawnWorld.getWorldData().getGameRules()).toNetworkGameRuleData());
        packet.setUniqueEntityId(thisPlayer.getRuntimeId());
        packet.setRuntimeEntityId(thisPlayer.getRuntimeId());
        packet.setPlayerGameType(GameType.from(playerData.getNbt().getInt("GameType", Utils.toGameType(spawnWorld.getWorldData().getGameMode()).ordinal())));
        var loc = thisPlayer.getLocation();
        var worldSpawn = spawnWorld.getWorldData().getSpawnPoint();
        packet.setDefaultSpawn(Vector3i.from(worldSpawn.x(), worldSpawn.y(), worldSpawn.z()));
        packet.setPlayerPosition(Vector3f.from(loc.x(), loc.y(), loc.z()));
        packet.setRotation(Vector2f.from(loc.pitch(), loc.yaw()));
        // We don't send world seed to client for security reason
        packet.setSeed(0L);
        packet.setDimensionId(dimension.getDimensionInfo().dimensionId());
        packet.setGeneratorId(dimension.getChunkManager().getWorldGenerator().getType().getId());
        packet.setLevelGameType(Utils.toGameType(spawnWorld.getWorldData().getGameMode()));
        packet.setDifficulty(spawnWorld.getWorldData().getDifficulty().ordinal());
        packet.setTrustingPlayers(true);
        packet.setLevelName(Server.SETTINGS.genericSettings().motd());
        packet.setLevelId("");
        packet.setDefaultPlayerPermission(Server.SETTINGS.genericSettings().defaultPermission());
        packet.setServerChunkTickRange(Server.SETTINGS.worldSettings().tickRadius());
        packet.setVanillaVersion("*");
        packet.setPremiumWorldTemplateId("");
        packet.setInventoriesServerAuthoritative(true);
        packet.setServerAuthoritativeBlockBreaking(true);
        // MultiVersion: set to ensure compatibility for client below 1.21.90
        packet.setAuthoritativeMovementMode(AuthoritativeMovementMode.SERVER);
        packet.setCommandsEnabled(true);
        packet.setMultiplayerGame(true);
        packet.setBroadcastingToLan(true);
        packet.setMultiplayerCorrelationId(UUID.randomUUID().toString());
        packet.setXblBroadcastMode(GamePublishSetting.PUBLIC);
        packet.setPlatformBroadcastMode(GamePublishSetting.PUBLIC);
        packet.setServerEngine(ProtocolInfo.getLatestCodec().getMinecraftVersion());
        packet.setBlockRegistryChecksum(0L);
        packet.setPlayerPropertyData(NbtMap.EMPTY);
        packet.setWorldTemplateId(new UUID(0, 0));
        packet.setWorldEditor(false);
        packet.setChatRestrictionLevel(ChatRestrictionLevel.NONE);
        packet.setSpawnBiomeType(SpawnBiomeType.DEFAULT);
        packet.setCustomBiomeName("plains");
        packet.setEducationProductionId("");
        packet.setForceExperimentalGameplay(OptionalBoolean.empty());
        packet.setBlockNetworkIdsHashed(true);
        packet.setServerId("");
        packet.setWorldId("");
        packet.setScenarioId("");
        packet.setOwnerId("");
        packet.getExperiments().addAll(DeferredData.EXPERIMENT_DATA_LIST.get());
        MultiVersion.adaptExperimentData(thisPlayer, packet.getExperiments());
        return packet;
    }

    public void completeLogin() {
        var playerManager = Server.getInstance().getPlayerManager();
        if (playerManager.getPlayerCount() >= playerManager.getMaxPlayerCount()) {
            disconnect(TrKeys.MC_DISCONNECTIONSCREEN_SERVERFULL_TITLE);
            return;
        }

        var event = new PlayerLoginEvent(thisPlayer, TrKeys.MC_DISCONNECTIONSCREEN_NOREASON, TextFormat.YELLOW + "%" + TrKeys.MC_MULTIPLAYER_PLAYER_JOINED);
        if (!event.call()) {
            disconnect(event.getDisconnectReason());
            return;
        }

        this.packetProcessorHolder.setClientStatus(ClientStatus.LOGGED_IN);

        var playStatusPacket = new PlayStatusPacket();
        playStatusPacket.setStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);
        sendPacket(playStatusPacket);

        ((AllayPlayerManager) playerManager).onLoggedIn(thisPlayer);
        this.manager.callEvent(CPlayerLoggedInEvent.INSTANCE);
        Server.getInstance().broadcastTr(event.getJoinMessage(), thisPlayer.getOriginName());

        sendPacket(DeferredData.RESOURCE_PACKS_INFO_PACKET.get());
    }
}
