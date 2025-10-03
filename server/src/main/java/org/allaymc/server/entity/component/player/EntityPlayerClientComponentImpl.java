package org.allaymc.server.entity.component.player;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.component.player.EntityPlayerClientComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.network.ClientDisconnectEvent;
import org.allaymc.api.eventbus.event.player.PlayerLoginEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.PlayerData;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.server.AllayServer;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.CPlayerChunkInRangeSendEvent;
import org.allaymc.server.entity.component.event.CPlayerLoggedInEvent;
import org.allaymc.server.eventbus.event.network.PacketReceiveEvent;
import org.allaymc.server.eventbus.event.network.PacketSendEvent;
import org.allaymc.server.network.MultiVersion;
import org.allaymc.server.network.NetworkData;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.player.AllayLoginData;
import org.allaymc.server.player.AllayPlayerManager;
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
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermission;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.joml.Vector3fc;

import java.net.SocketAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.allaymc.api.utils.AllayNbtUtils.readVector3f;
import static org.allaymc.api.utils.AllayNbtUtils.writeVector3f;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerClientComponentImpl implements EntityPlayerClientComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:player_client_component");

    @Manager
    protected ComponentManager manager;
    @ComponentObject
    protected EntityPlayer thisPlayer;
    @Dependency
    protected EntityPlayerBaseComponent baseComponent;

    protected final PacketProcessorHolder packetProcessorHolder;
    protected final AtomicInteger fullyJoinChunkThreshold;

    @Getter
    @Setter
    protected AllayLoginData loginData;
    @Getter
    @Setter
    protected boolean networkEncryptionEnabled;
    @Getter
    @Setter
    protected boolean clientCacheEnabled;
    @Getter
    protected BedrockServerSession clientSession;

    public EntityPlayerClientComponentImpl() {
        this.packetProcessorHolder = new PacketProcessorHolder();
        this.fullyJoinChunkThreshold = new AtomicInteger(AllayServer.getSettings().worldSettings().fullyJoinChunkThreshold());
    }

    public void handlePacketSync(BedrockPacket packet, long receiveTime) {
        var processor = packetProcessorHolder.getProcessor(packet);
        if (processor == null) {
            log.warn("Received a packet which doesn't have correspond packet handler: {}, client status: {}", packet, getClientState());
            return;
        }
        processor.handleSync(thisPlayer, packet, receiveTime);
    }

    public void setClientStatus(ClientState status) {
        this.packetProcessorHolder.setClientState(status);
    }

    public void setClientSession(BedrockServerSession session) {
        this.clientSession = session;
        this.packetProcessorHolder.setClientState(ClientState.CONNECTED);

        var maxLoginTime = AllayServer.getSettings().networkSettings().maxLoginTime();
        if (maxLoginTime > 0) {
            Server.getInstance().getScheduler().scheduleDelayed(Server.getInstance(), () -> {
                var status = getClientState();
                if (status != ClientState.DISCONNECTED && status.ordinal() < ClientState.IN_GAME.ordinal()) {
                    log.warn("Session {} didn't log in within {} seconds, disconnecting...", clientSession.getSocketAddress(), maxLoginTime / 20d);
                    disconnect(TrKeys.MC_DISCONNECTIONSCREEN_TIMEOUT);
                }
                return true;
            }, maxLoginTime);
        }

        session.setPacketHandler(new BedrockPacketHandler() {
            @Override
            public PacketSignal handlePacket(BedrockPacket packet) {
                if (!getClientState().canHandlePackets()) {
                    return PacketSignal.HANDLED;
                }

                var event = new PacketReceiveEvent(thisPlayer, packet);
                if (!event.call()) {
                    return PacketSignal.HANDLED;
                }

                packet = event.getPacket();

                var processor = packetProcessorHolder.getProcessor(packet);
                if (processor == null) {
                    log.warn("Received a packet which doesn't have correspond packet handler: {}, client status: {}", packet, getClientState());
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
            public void onDisconnect(CharSequence seq) {
                var reason = seq.toString();
                if (!packetProcessorHolder.setClientState(ClientState.DISCONNECTED, false)) {
                    // Failed to set disconnected field from false to true
                    // Which means the client may be disconnected by server
                    // by calling EntityPlayerNetworkComponentImpl::disconnect() method
                    // this shouldn't be an error
                    return;
                }
                EntityPlayerClientComponentImpl.this.onDisconnect(reason);
            }
        });
    }

    @EventHandler
    protected void onChunkInRangeSend(CPlayerChunkInRangeSendEvent event) {
        if (fullyJoinChunkThreshold.get() > 0 && fullyJoinChunkThreshold.decrementAndGet() == 0) {
            onFullyJoin();
        }
    }

    protected void onFullyJoin() {
        var server = Server.getInstance();
        var world = thisPlayer.getWorld();

        thisPlayer.loadNBT(server.getPlayerManager().getPlayerStorage().readPlayerData(thisPlayer).getNbt());
        thisPlayer.viewEntityState(thisPlayer);
        thisPlayer.viewPlayerGameMode(thisPlayer);
        thisPlayer.forEachViewers(viewer -> viewer.viewPlayerGameMode(thisPlayer));
        thisPlayer.viewTime(world.getWorldData().getTimeOfDay());
        thisPlayer.viewWeather(world.getWeather());
        thisPlayer.viewContents(thisPlayer.getContainer(ContainerTypes.INVENTORY));
        thisPlayer.viewContents(thisPlayer.getContainer(ContainerTypes.OFFHAND));
        thisPlayer.viewContents(thisPlayer.getContainer(ContainerTypes.ARMOR));
        sendSpeed(thisPlayer.getSpeed());
        sendExperienceLevel(thisPlayer.getExperienceLevel());
        sendExperienceProgress(thisPlayer.getExperienceProgress());
        sendFoodLevel(thisPlayer.getFoodLevel());
        sendFoodSaturationLevel(thisPlayer.getFoodSaturationLevel());
        sendFoodExhaustionLevel(thisPlayer.getFoodExhaustionLevel());
        sendAbilities(thisPlayer);

        var playerManager = (AllayPlayerManager) server.getPlayerManager();
        playerManager.broadcastPlayerListChange(thisPlayer, true);
        if (server.getPlayerManager().getPlayerCount() > 1) {
            playerManager.sendPlayerListTo(thisPlayer);
        }

        // Save player data the first time it joins
        server.getPlayerManager().getPlayerStorage().savePlayerData(thisPlayer);

        sendPlayStatus(PlayStatusPacket.Status.PLAYER_SPAWN);
    }

    public void sendAbilities(EntityPlayer player) {
        var packet = new UpdateAbilitiesPacket();

        packet.setUniqueEntityId(player.getRuntimeId());
        // The command permissions set here are actually not very useful. Their main function is to allow OPs to have quick command options.
        // If this player does not have specific command permissions, the command description won't even be sent to the client
        packet.setCommandPermission(player.hasPermission(Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR) ? CommandPermission.GAME_DIRECTORS : CommandPermission.ANY);
        // PlayerPermissions is the permission level of the player as it shows up in the player list built up using the PlayerList packet
        packet.setPlayerPermission(calculatePlayerPermission(player));

        var layer = new AbilityLayer();
        layer.setLayerType(AbilityLayer.Type.BASE);
        layer.getAbilitiesSet().addAll(Arrays.asList(Ability.values()));
        layer.getAbilityValues().addAll(calculateAbilities(player));
        // NOTICE: this shouldn't be changed
        layer.setWalkSpeed(EntityPlayerBaseComponent.DEFAULT_SPEED);
        layer.setFlySpeed(player.getFlySpeed());
        layer.setVerticalFlySpeed(player.getVerticalFlySpeed());
        packet.getAbilityLayers().add(layer);

        sendPacket(packet);
    }

    private EnumSet<Ability> calculateAbilities(EntityPlayer player) {
        var abilities = EnumSet.noneOf(Ability.class);
        abilities.add(Ability.TELEPORT);
        abilities.add(Ability.WALK_SPEED);
        abilities.add(Ability.FLY_SPEED);
        abilities.add(Ability.VERTICAL_FLY_SPEED);
        if (player.getGameMode() != GameMode.SPECTATOR) {
            abilities.add(Ability.BUILD);
            abilities.add(Ability.MINE);
            abilities.add(Ability.DOORS_AND_SWITCHES);
            abilities.add(Ability.OPEN_CONTAINERS);
            abilities.add(Ability.ATTACK_PLAYERS);
            abilities.add(Ability.ATTACK_MOBS);
        } else {
            abilities.add(Ability.NO_CLIP);
            abilities.add(Ability.FLYING);
        }
        if (player.getGameMode() == GameMode.CREATIVE) {
            abilities.add(Ability.INSTABUILD);
        }
        if (player.hasPermission(Permissions.ABILITY_FLY)) {
            abilities.add(Ability.MAY_FLY);
        }
        if (player.isFlying()) {
            abilities.add(Ability.FLYING);
        }
        return abilities;
    }

    protected PlayerPermission calculatePlayerPermission(EntityPlayer player) {
        if (player.hasPermissions(PermissionGroups.OPERATOR, true)) {
            return PlayerPermission.OPERATOR;
        } else if (player.hasPermissions(PermissionGroups.MEMBER, true)) {
            return PlayerPermission.MEMBER;
        }
        return PlayerPermission.VISITOR;
    }

    public void sendSpeed(float value) {
        sendAttribute(new AttributeData(
                "minecraft:movement", 0, Float.MAX_VALUE, value, 0,
                Float.MAX_VALUE, EntityPlayerBaseComponent.DEFAULT_SPEED, Collections.emptyList()
        ));
    }

    public void sendExperienceLevel(int value) {
        sendAttribute(new AttributeData("minecraft:player.level", 0, Float.MAX_VALUE, value));
    }

    public void sendExperienceProgress(float value) {
        sendAttribute(new AttributeData("minecraft:player.experience", 0, 1, value));
    }

    public void sendFoodLevel(int value) {
        var max = EntityPlayerBaseComponent.MAX_FOOD_LEVEL;
        sendAttribute(new AttributeData(
                "minecraft:player.hunger", 0, max,
                value, 0, max, max, Collections.emptyList()
        ));
    }

    public void sendFoodSaturationLevel(float value) {
        var max = EntityPlayerBaseComponent.MAX_FOOD_SATURATION_LEVEL;
        sendAttribute(new AttributeData(
                "minecraft:player.saturation", 0, max,
                value, 0, max, max, Collections.emptyList()
        ));
    }

    public void sendFoodExhaustionLevel(float value) {
        var max = EntityPlayerBaseComponent.MAX_FOOD_EXHAUSTION_LEVEL;
        sendAttribute(new AttributeData(
                "minecraft:player.exhaustion", 0, max,
                value, 0, max, 0, Collections.emptyList()
        ));
    }

    protected void sendAttribute(AttributeData attributeData) {
        var packet = new UpdateAttributesPacket();
        packet.setRuntimeEntityId(thisPlayer.getRuntimeId());
        packet.getAttributes().add(attributeData);
        sendPacket(packet);
    }

    @Override
    public void sendPacket(Object p) {
        if (!(p instanceof BedrockPacket packet)) {
            return;
        }

        if (!getClientState().canHandlePackets()) {
            return;
        }

        var event = new PacketSendEvent(thisPlayer, packet);
        if (!event.call()) {
            return;
        }

        this.clientSession.sendPacket(event.getPacket());
    }

    @Override
    public void sendPacketImmediately(Object p) {
        if (!(p instanceof BedrockPacket packet)) {
            return;
        }

        if (!getClientState().canHandlePackets()) {
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
        if (!packetProcessorHolder.setClientState(ClientState.DISCONNECTED)) {
            log.warn("Trying to disconnect a player who is already disconnected!");
            return;
        }

        var disconnectReason = I18n.get().tr(thisPlayer.getLoginData().getLangCode(), reason);
        try {
            onDisconnect(disconnectReason);
            // Tell the client that it should disconnect
            if (this.clientSession.isConnected()) {
                this.clientSession.disconnect(disconnectReason);
            }
        } catch (Throwable t) {
            log.error("Error while disconnecting the session", t);
        }
    }

    @Override
    public ClientState getClientState() {
        return packetProcessorHolder.getClientState();
    }

    @Override
    public ClientState getLastClientState() {
        return packetProcessorHolder.getLastClientState();
    }

    @Override
    public SocketAddress getSocketAddress() {
        return this.clientSession.getSocketAddress();
    }

    protected void onDisconnect(String disconnectReason) {
        new ClientDisconnectEvent(clientSession.getSocketAddress(), disconnectReason).call();
        thisPlayer.closeAllOpenedContainers();
        ((AllayPlayerManager) Server.getInstance().getPlayerManager()).removePlayer(thisPlayer);
    }

    @Override
    public int getPing() {
        var rakServerChannel = (RakServerChannel) clientSession.getPeer().getChannel().parent();
        var childChannel = rakServerChannel.getChildChannel(clientSession.getSocketAddress());
        var rakSessionCodec = childChannel.rakPipeline().get(RakSessionCodec.class);
        return (int) rakSessionCodec.getPing();
    }

    public void initializePlayer() {
        var server = Server.getInstance();
        // initializePlayer() method will read all the data in PlayerData except nbt
        // To be more exact, we will validate and set player's current pos in this method,
        // and nbt will be used in EntityPlayer::loadNBT() in doFirstSpawn() method
        var playerData = server.getPlayerManager().getPlayerStorage().readPlayerData(thisPlayer);

        // Validate and set player pos
        Dimension dimension;
        Vector3fc currentPos;

        var logOffWorld = server.getWorldPool().getWorld(playerData.getWorld());
        if (logOffWorld == null || logOffWorld.getDimension(playerData.getDimension()) == null) {
            // The world or dimension where the player logged off doesn't exist, fallback to the global spawn point
            dimension = server.getWorldPool().getGlobalSpawnPoint().dimension();
            currentPos = new org.joml.Vector3f(server.getWorldPool().getGlobalSpawnPoint());

            // The old pos stored in player's nbt is invalid, and we should replace it with the new one!
            var builder = playerData.getNbt().toBuilder();
            writeVector3f(builder, "Pos", currentPos);
            playerData.setNbt(builder.build());

            // Save new player data back to storage
            server.getPlayerManager().getPlayerStorage().savePlayerData(thisPlayer.getLoginData().getUuid(), playerData);
        } else {
            dimension = logOffWorld.getDimension(playerData.getDimension());
            currentPos = readVector3f(playerData.getNbt(), "Pos");
        }

        this.baseComponent.setLocationBeforeSpawn(new Location3d(currentPos.x(), currentPos.y(), currentPos.z(), dimension));
        dimension.addPlayer(thisPlayer);

        startGame(dimension.getWorld(), playerData, dimension);

        var helper = clientSession.getPeer().getCodecHelper();
        helper.setItemDefinitions(SimpleDefinitionRegistry.<ItemDefinition>builder().addAll(NetworkData.ITEM_DEFINITIONS.get()).build());
        helper.setBlockDefinitions(SimpleDefinitionRegistry.<BlockDefinition>builder().addAll(NetworkData.BLOCK_DEFINITIONS.get()).build());

        sendPacketImmediately(NetworkData.ITEM_COMPONENT_PACKET.get());
        sendPacket(NetworkData.CREATIVE_CONTENT_PACKET.get());
        sendPacket(NetworkData.AVAILABLE_ENTITY_IDENTIFIERS_PACKET.get());
        sendPacket(NetworkData.BIOME_DEFINITION_LIST_PACKET.get());
        sendPacket(NetworkData.CRAFTING_DATA_PACKET.get());
        sendPacket(NetworkData.TRIM_DATA_PACKET.get());
    }

    protected void startGame(World spawnWorld, PlayerData playerData, Dimension dimension) {
        var packet = new StartGamePacket();
        packet.getGamerules().addAll(((AllayGameRules) spawnWorld.getWorldData().getGameRules()).toNetworkGameRuleData());
        packet.setUniqueEntityId(thisPlayer.getRuntimeId());
        packet.setRuntimeEntityId(thisPlayer.getRuntimeId());
        packet.setPlayerGameType(GameType.from(playerData.getNbt().getInt("GameType", NetworkHelper.toNetwork(spawnWorld.getWorldData().getGameMode()).ordinal())));
        var loc = thisPlayer.getLocation();
        var worldSpawn = spawnWorld.getWorldData().getSpawnPoint();
        packet.setDefaultSpawn(Vector3i.from(worldSpawn.x(), worldSpawn.y(), worldSpawn.z()));
        packet.setPlayerPosition(Vector3f.from(loc.x(), loc.y() + 1.62, loc.z()));
        packet.setRotation(Vector2f.from(loc.pitch(), loc.yaw()));
        // We don't send world seed to the client for security reason
        packet.setSeed(0L);
        packet.setDimensionId(dimension.getDimensionInfo().dimensionId());
        // 0 - limit 1 - infinite
        // 2 - flat  3 - nether
        // 4 - end   5 - void
        packet.setGeneratorId(1);
        packet.setLevelGameType(NetworkHelper.toNetwork(spawnWorld.getWorldData().getGameMode()));
        packet.setDifficulty(spawnWorld.getWorldData().getDifficulty().ordinal());
        packet.setTrustingPlayers(true);
        packet.setLevelName(AllayServer.getSettings().genericSettings().motd());
        packet.setLevelId("");
        packet.setDefaultPlayerPermission(PlayerPermission.valueOf(AllayServer.getSettings().genericSettings().defaultPermission()));
        packet.setServerChunkTickRange(AllayServer.getSettings().worldSettings().tickRadius());
        // VanillaVersion is the version of the game from which Vanilla features will be used
        packet.setVanillaVersion(ProtocolInfo.getLatestCodec().getMinecraftVersion());
        // ServerEngine(aka.GameVersion) is the version of the game the server is running
        packet.setServerEngine(ProtocolInfo.getLatestCodec().getMinecraftVersion());
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
        packet.setBlockRegistryChecksum(0L);
        packet.setPlayerPropertyData(NbtMap.EMPTY);
        packet.setWorldTemplateId(new UUID(0, 0));
        packet.setEditorWorldType(WorldType.NON_EDITOR);
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
        packet.getExperiments().addAll(NetworkData.EXPERIMENT_DATA_LIST.get());
        MultiVersion.adaptExperimentData(thisPlayer, packet.getExperiments());
        sendPacketImmediately(packet);
    }

    public void completeLogin() {
        var playerManager = (AllayPlayerManager) Server.getInstance().getPlayerManager();
        if (playerManager.getPlayerCount() >= playerManager.getMaxPlayerCount()) {
            disconnect(TrKeys.MC_DISCONNECTIONSCREEN_SERVERFULL_TITLE);
            return;
        }

        var event = new PlayerLoginEvent(thisPlayer, TrKeys.MC_DISCONNECTIONSCREEN_NOREASON, TextFormat.YELLOW + "%" + TrKeys.MC_MULTIPLAYER_PLAYER_JOINED);
        if (!event.call()) {
            disconnect(event.getDisconnectReason());
            return;
        }

        this.packetProcessorHolder.setClientState(ClientState.LOGGED_IN);
        sendPlayStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);

        playerManager.addPlayer(thisPlayer);
        this.manager.callEvent(CPlayerLoggedInEvent.INSTANCE);
        Object[] args = new Object[]{thisPlayer.getOriginName()};
        Server.getInstance().getMessageChannel().broadcastTranslatable(event.getJoinMessage(), args);

        sendPacket(NetworkData.RESOURCE_PACKS_INFO_PACKET.get());
    }

    protected void sendPlayStatus(PlayStatusPacket.Status status) {
        var packet = new PlayStatusPacket();
        packet.setStatus(status);
        sendPacket(packet);
    }
}
