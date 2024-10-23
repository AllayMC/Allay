package org.allaymc.server.entity.component.player;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.data.LoginData;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.UnopenedContainerId;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.network.PacketReceiveEvent;
import org.allaymc.api.eventbus.event.network.PacketSendEvent;
import org.allaymc.api.eventbus.event.player.PlayerLoginEvent;
import org.allaymc.api.eventbus.event.player.PlayerQuitEvent;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.network.ProtocolInfo;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.AllayServer;
import org.allaymc.server.component.annotation.ComponentedObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.CPlayerLoggedInEvent;
import org.allaymc.server.network.processor.AllayPacketProcessorHolder;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.world.AllayWorld;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.netty.channel.raknet.RakServerChannel;
import org.cloudburstmc.netty.handler.codec.raknet.common.RakSessionCodec;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.joml.Vector3fc;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.allaymc.api.utils.AllayNbtUtils.readVector3f;
import static org.allaymc.api.utils.AllayNbtUtils.writeVector3f;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerNetworkComponentImpl implements EntityPlayerNetworkComponent {

    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:player_network_component");

    protected final Server server = Server.getInstance();

    @Getter
    protected final PacketProcessorHolder packetProcessorHolder = new AllayPacketProcessorHolder();

    @Getter
    protected boolean loggedIn = false;
    @Getter
    @Setter
    protected boolean networkEncryptionEnabled = false;
    @Getter
    protected boolean initialized = false;
    protected AtomicInteger fullyJoinChunkThreshold = new AtomicInteger(Server.SETTINGS.worldSettings().fullyJoinChunkThreshold());
    @Manager
    protected ComponentManager manager;
    @ComponentedObject
    protected EntityPlayer thisPlayer;
    @Getter
    @Setter
    protected LoginData loginData;
    @Getter
    @Setter
    protected SecretKey encryptionSecretKey;
    @Dependency
    protected EntityPlayerBaseComponentImpl baseComponent;
    protected BedrockServerSession session;
    protected AtomicBoolean disconnected = new AtomicBoolean(false);

    public void handleDataPacket(BedrockPacket packet, long time) {
        var processor = packetProcessorHolder.getProcessor(packet);
        // processor won't be null as we have checked it the time it arrived
        processor.handleSync(thisPlayer, packet, time);
    }

    protected void onDisconnect(String disconnectReason) {
        var event = new PlayerQuitEvent(thisPlayer, disconnectReason);
        event.call();
        thisPlayer.closeAllContainers();
        ((AllayServer) server).onDisconnect(thisPlayer);
    }

    public void setInitialized() {
        if (initialized) log.warn("Player.initialized is set twice");
        this.initialized = true;
    }

    @Override
    public BedrockServerSession getClientSession() {
        return session;
    }

    public void setClientSession(BedrockServerSession session) {
        this.session = session;
        session.setPacketHandler(new BedrockPacketHandler() {
            @Override
            public PacketSignal handlePacket(BedrockPacket packet) {
                var event = new PacketReceiveEvent(thisPlayer, packet);
                event.call();
                if (event.isCancelled()) return PacketSignal.HANDLED;

                packet = event.getPacket();

                var processor = packetProcessorHolder.getProcessor(packet);
                if (processor == null) {
                    log.warn("Received a packet without packet handler: {}", packet);
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
                    // Packet processors should make sure that PacketProcessor.handleSync() won't be called
                    // if player is not in any world
                    Objects.requireNonNull(world, "Player that is not in any world cannot handle sync packet");
                    ((AllayWorld) world).addSyncPacketToQueue(thisPlayer, packet, time);
                }

                return PacketSignal.HANDLED;
            }

            @Override
            public void onDisconnect(String reason) {
                if (!disconnected.compareAndSet(false, true)) {
                    // Failed to set disconnected field from false to true
                    // Which means the client may be disconnected by server
                    // by calling EntityPlayerNetworkComponentImpl::disconnect() method
                    return;
                }
                EntityPlayerNetworkComponentImpl.this.onDisconnect(reason);
            }
        });
    }

    public void onChunkInRangeSent() {
        if (fullyJoinChunkThreshold.get() > 0 && fullyJoinChunkThreshold.decrementAndGet() == 0) {
            onFullyJoin();
        }
    }

    @Override
    public void sendPacket(BedrockPacket packet) {
        var event = new PacketSendEvent(thisPlayer, packet);
        event.call();
        if (event.isCancelled()) return;

        session.sendPacket(event.getPacket());
    }

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {
        var event = new PacketSendEvent(thisPlayer, packet);
        event.call();
        if (event.isCancelled()) return;

        session.sendPacketImmediately(event.getPacket());
    }

    @Override
    public void disconnect(@MayContainTrKey String reason) {
        if (!disconnected.compareAndSet(false, true)) {
            log.warn("Trying to disconnect a player who is already disconnected!");
            return;
        }
        var disconnectReason = I18n.get().tr(thisPlayer.getLangCode(), reason);
        try {
            onDisconnect(disconnectReason);
            // Tell the client that it should disconnect
            thisPlayer.getClientSession().disconnect(disconnectReason);
        } catch (Throwable t) {
            log.error("Error while disconnecting the session", t);
        }
    }

    @Override
    public boolean isDisconnected() {
        return disconnected.get();
    }

    protected void onFullyJoin() {
        var world = thisPlayer.getWorld();
        // Load EntityPlayer's NBT
        thisPlayer.loadNBT(server.getPlayerStorage().readPlayerData(thisPlayer).getNbt());

        var setEntityDataPacket = new SetEntityDataPacket();
        setEntityDataPacket.setRuntimeEntityId(thisPlayer.getRuntimeId());
        setEntityDataPacket.getMetadata().putAll(thisPlayer.getMetadata().getEntityDataMap());
        setEntityDataPacket.setTick(world.getTick());
        sendPacket(setEntityDataPacket);

        // Update abilities, adventure settings, entity flags that are related to game type
        thisPlayer.setGameType(thisPlayer.getGameType());

        sendPacket(Registries.COMMANDS.encodeAvailableCommandsPacketFor(thisPlayer));

        // PlayerListPacket can only be sent in this stage, otherwise the client won't show its skin
        ((AllayServer) server).addToPlayerList(thisPlayer);
        if (server.getOnlinePlayerCount() > 1) {
            ((AllayServer) server).sendFullPlayerListInfoTo(thisPlayer);
        }

        thisPlayer.sendAttributesToClient();

        sendInventories();

        var playStatusPacket = new PlayStatusPacket();
        playStatusPacket.setStatus(PlayStatusPacket.Status.PLAYER_SPAWN);
        sendPacket(playStatusPacket);

        world.getWorldData().sendTime(thisPlayer);
        ((AllayWorld) world).sendWeather(thisPlayer);
        // Save player data the first time
        server.getPlayerStorage().savePlayerData(thisPlayer);
    }

    private void sendInventories() {
        thisPlayer.sendContentsWithSpecificContainerId(thisPlayer.getContainer(FullContainerType.PLAYER_INVENTORY), UnopenedContainerId.PLAYER_INVENTORY);
        thisPlayer.sendContentsWithSpecificContainerId(thisPlayer.getContainer(FullContainerType.OFFHAND), UnopenedContainerId.OFFHAND);
        thisPlayer.sendContentsWithSpecificContainerId(thisPlayer.getContainer(FullContainerType.ARMOR), UnopenedContainerId.ARMOR);
        // No need to send cursor's content to client because there is nothing in cursor
    }

    public void initializePlayer() {
        // initializePlayer() method will read all the data in PlayerData except nbt
        // To be more exactly, we will validate and set player's current pos in this method
        // And nbt will be used in EntityPlayer::loadNBT() in doFirstSpawn() method
        var playerData = server.getPlayerStorage().readPlayerData(thisPlayer);
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
            writeVector3f(builder, "Pos", "x", "y", "z", currentPos);
            playerData.setNbt(builder.build());
            // Save new player data back to storage
            server.getPlayerStorage().savePlayerData(thisPlayer.getUUID(), playerData);
        } else {
            dimension = logOffWorld.getDimension(playerData.getDimension());
            // Read current pos from playerNBT
            currentPos = readVector3f(playerData.getNbt(), "Pos", "x", "y", "z");
        }
        // Load the current point chunk firstly so that we can add player entity into the chunk
        dimension.getChunkService().getOrLoadChunkSync(
                (int) currentPos.x() >> 4,
                (int) currentPos.z() >> 4
        );
        baseComponent.setLocationBeforeSpawn(new Location3f(currentPos.x(), currentPos.y(), currentPos.z(), dimension));
        dimension.addPlayer(thisPlayer);

        var spawnWorld = dimension.getWorld();
        var startGamePacket = new StartGamePacket();
        startGamePacket.getGamerules().addAll(spawnWorld.getWorldData().getGameRules().toNetworkGameRuleData());
        startGamePacket.setUniqueEntityId(thisPlayer.getRuntimeId());
        startGamePacket.setRuntimeEntityId(thisPlayer.getRuntimeId());
        startGamePacket.setPlayerGameType(GameType.from(playerData.getNbt().getInt("GameType", Server.SETTINGS.genericSettings().defaultGameType().ordinal())));
        var loc = thisPlayer.getLocation();
        var worldSpawn = spawnWorld.getWorldData().getSpawnPoint();
        startGamePacket.setDefaultSpawn(Vector3i.from(worldSpawn.x(), worldSpawn.y(), worldSpawn.z()));
        startGamePacket.setPlayerPosition(Vector3f.from(loc.x(), loc.y(), loc.z()));
        startGamePacket.setRotation(Vector2f.from(loc.pitch(), loc.yaw()));
        // We don't send world seed to client for security reason
        startGamePacket.setSeed(0L);
        startGamePacket.setDimensionId(dimension.getDimensionInfo().dimensionId());
        startGamePacket.setGeneratorId(dimension.getChunkService().getWorldGenerator().getType().getId());
        startGamePacket.setLevelGameType(spawnWorld.getWorldData().getGameType());
        startGamePacket.setDifficulty(spawnWorld.getWorldData().getDifficulty().ordinal());
        startGamePacket.setTrustingPlayers(true);
        startGamePacket.setLevelName(Server.SETTINGS.genericSettings().motd());
        startGamePacket.setLevelId("");
        startGamePacket.setDefaultPlayerPermission(Server.SETTINGS.genericSettings().defaultPermission());
        startGamePacket.setServerChunkTickRange(Server.SETTINGS.worldSettings().tickRadius());
        startGamePacket.setVanillaVersion("*");
        startGamePacket.setPremiumWorldTemplateId("");
        startGamePacket.setInventoriesServerAuthoritative(true);
        startGamePacket.setItemDefinitions(DeferredData.getItemDefinitions());
        startGamePacket.setAuthoritativeMovementMode(AuthoritativeMovementMode.SERVER);
        startGamePacket.setServerAuthoritativeBlockBreaking(true);
        startGamePacket.setCommandsEnabled(true);
        startGamePacket.setMultiplayerGame(true);
        // TODO: add it to server-settings.yml
        startGamePacket.setBroadcastingToLan(true);
        startGamePacket.setMultiplayerCorrelationId(UUID.randomUUID().toString());
        startGamePacket.setXblBroadcastMode(GamePublishSetting.PUBLIC);
        startGamePacket.setPlatformBroadcastMode(GamePublishSetting.PUBLIC);
        startGamePacket.setServerEngine(ProtocolInfo.getMinecraftVersionStr());
        startGamePacket.setBlockRegistryChecksum(0L);
        startGamePacket.setPlayerPropertyData(NbtMap.EMPTY);
        startGamePacket.setWorldTemplateId(new UUID(0, 0));
        startGamePacket.setWorldEditor(false);
        startGamePacket.setChatRestrictionLevel(ChatRestrictionLevel.NONE);
        startGamePacket.setSpawnBiomeType(SpawnBiomeType.DEFAULT);
        startGamePacket.setCustomBiomeName("plains");
        startGamePacket.setEducationProductionId("");
        startGamePacket.setForceExperimentalGameplay(OptionalBoolean.empty());
        startGamePacket.setBlockNetworkIdsHashed(true);
        startGamePacket.setServerId("");
        startGamePacket.setWorldId("");
        startGamePacket.setScenarioId("");
        sendPacket(startGamePacket);

        session.getPeer().getCodecHelper().setItemDefinitions(
                SimpleDefinitionRegistry
                        .<ItemDefinition>builder()
                        .addAll(startGamePacket.getItemDefinitions())
                        .build()
        );

        session.getPeer().getCodecHelper().setBlockDefinitions(
                SimpleDefinitionRegistry
                        .<BlockDefinition>builder()
                        .addAll(DeferredData.getBlockDefinitions())
                        .build()
        );

        sendPacket(DeferredData.getAvailableEntityIdentifiersPacket());
        sendPacket(DeferredData.getBiomeDefinitionListPacket());
        sendPacket(DeferredData.getCreativeContentPacket());
        sendPacket(DeferredData.getCraftingDataPacket());
    }

    public void completeLogin() {
        if (server.getOnlinePlayerCount() >= Server.SETTINGS.genericSettings().maxPlayerCount()) {
            disconnect(TrKeys.M_DISCONNECTIONSCREEN_SERVERFULL_TITLE);
            return;
        }

        var event = new PlayerLoginEvent(thisPlayer);
        event.call();
        if (event.isCancelled()) {
            disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOREASON);
            return;
        }

        var playStatusPacket = new PlayStatusPacket();
        playStatusPacket.setStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);
        sendPacket(playStatusPacket);

        loggedIn = true;
        ((AllayServer) server).onLoggedIn(thisPlayer);
        // TODO: plugin event
        manager.callEvent(CPlayerLoggedInEvent.INSTANCE);
        sendPacket(DeferredData.getResourcePacksInfoPacket());
    }

    @Override
    public int getPing() {
        var rakServerChannel = (RakServerChannel) session.getPeer().getChannel().parent();
        var childChannel = rakServerChannel.getChildChannel(session.getSocketAddress());
        var rakSessionCodec = childChannel.rakPipeline().get(RakSessionCodec.class);
        return (int) rakSessionCodec.getPing();
    }

    /**
     * Only build these data when player join <br>
     * Which allows plugins to register their custom stuff
     */
    public static class DeferredData {
        private static CraftingDataPacket CRAFTING_DATA_PACKET;
        private static CreativeContentPacket CREATIVE_CONTENT_PACKET;
        private static List<ItemDefinition> ITEM_DEFINITIONS;
        private static List<BlockDefinition> BLOCK_DEFINITIONS;
        private static AvailableEntityIdentifiersPacket AVAILABLE_ENTITY_IDENTIFIERS_PACKET;
        private static BiomeDefinitionListPacket BIOME_DEFINITION_LIST_PACKET;
        private static ResourcePacksInfoPacket RESOURCE_PACKS_INFO_PACKET;
        private static ResourcePackStackPacket RESOURCES_PACK_STACK_PACKET;

        public static CraftingDataPacket getCraftingDataPacket() {
            if (CRAFTING_DATA_PACKET == null) {
                CRAFTING_DATA_PACKET = new CraftingDataPacket();
                CRAFTING_DATA_PACKET.getCraftingData().addAll(
                        Registries.RECIPES.getContent().values().stream()
                                .map(Recipe::toNetworkRecipeData)
                                .toList()
                );
                CRAFTING_DATA_PACKET.getCraftingData().addAll(
                        Registries.FURNACE_RECIPES.getContent().values().stream()
                                .map(Recipe::toNetworkRecipeData)
                                .toList()
                );
                // TODO: packet.getPotionMixData().addAll();
                // TODO: packet.getContainerMixData().addAll();
                // TODO: packet.getMaterialReducers().addAll();
                CRAFTING_DATA_PACKET.setCleanRecipes(true);
            }
            return CRAFTING_DATA_PACKET;
        }

        public static CreativeContentPacket getCreativeContentPacket() {
            if (CREATIVE_CONTENT_PACKET == null) {
                CREATIVE_CONTENT_PACKET = new CreativeContentPacket();
                // We should sort it first!
                var map = new TreeMap<>(Registries.CREATIVE_ITEMS.getContent());
                CREATIVE_CONTENT_PACKET.setContents(map.values().stream().map(ItemStack::toNetworkItemData).toArray(ItemData[]::new));
            }
            return CREATIVE_CONTENT_PACKET;
        }

        public static List<ItemDefinition> getItemDefinitions() {
            if (ITEM_DEFINITIONS == null) {
                ITEM_DEFINITIONS = new ArrayList<>();
                for (var itemType : Registries.ITEMS.getContent().values()) {
                    ITEM_DEFINITIONS.add(itemType.toNetworkDefinition());
                }
            }
            return ITEM_DEFINITIONS;
        }

        public static List<BlockDefinition> getBlockDefinitions() {
            if (BLOCK_DEFINITIONS == null) {
                BLOCK_DEFINITIONS = new ArrayList<>();
                for (var blockType : Registries.BLOCKS.getContent().values()) {
                    blockType.getAllStates().forEach(state -> BLOCK_DEFINITIONS.add(state.toNetworkBlockDefinition()));
                }
            }
            return BLOCK_DEFINITIONS;
        }

        public static AvailableEntityIdentifiersPacket getAvailableEntityIdentifiersPacket() {
            // TODO: support custom entity
            // We just read it from file currently
            if (AVAILABLE_ENTITY_IDENTIFIERS_PACKET == null) {
                try (var stream = NbtUtils.createNetworkReader(Utils.getResource("entity_identifiers.nbt"))) {
                    var tag = (NbtMap) stream.readTag();
                    AVAILABLE_ENTITY_IDENTIFIERS_PACKET = new AvailableEntityIdentifiersPacket();
                    AVAILABLE_ENTITY_IDENTIFIERS_PACKET.setIdentifiers(tag);
                } catch (Exception e) {
                    throw new AssertionError("Failed to load entity_identifiers.nbt", e);
                }
            }
            assert AVAILABLE_ENTITY_IDENTIFIERS_PACKET != null;
            return AVAILABLE_ENTITY_IDENTIFIERS_PACKET;
        }

        public static BiomeDefinitionListPacket getBiomeDefinitionListPacket() {
            // TODO: support custom biome
            // Same to entity, we just read it from file currently
            if (BIOME_DEFINITION_LIST_PACKET == null) {
                try (var stream = Utils.getResource("biome_definitions.nbt")) {
                    var tag = (NbtMap) NbtUtils.createGZIPReader(stream).readTag();
                    BIOME_DEFINITION_LIST_PACKET = new BiomeDefinitionListPacket();
                    BIOME_DEFINITION_LIST_PACKET.setDefinitions(tag);
                } catch (Exception e) {
                    throw new AssertionError("Failed to load biome_definitions.nbt", e);
                }
            }
            assert BIOME_DEFINITION_LIST_PACKET != null;
            return BIOME_DEFINITION_LIST_PACKET;
        }

        public static ResourcePacksInfoPacket getResourcePacksInfoPacket() {
            if (RESOURCE_PACKS_INFO_PACKET == null) {
                buildResourcePackPacket();
            }
            assert RESOURCE_PACKS_INFO_PACKET != null;
            return RESOURCE_PACKS_INFO_PACKET;
        }

        public static ResourcePackStackPacket getResourcesPackStackPacket() {
            if (RESOURCES_PACK_STACK_PACKET == null) {
                buildResourcePackPacket();
            }
            assert RESOURCES_PACK_STACK_PACKET != null;
            return RESOURCES_PACK_STACK_PACKET;
        }

        public static void buildResourcePackPacket() {
            RESOURCE_PACKS_INFO_PACKET = new ResourcePacksInfoPacket();
            RESOURCES_PACK_STACK_PACKET = new ResourcePackStackPacket();

            var forceResourcePacks = Server.SETTINGS.resourcePackSettings().forceResourcePacks();
            var allowClientResourcePacks = Server.SETTINGS.resourcePackSettings().allowClientResourcePacks();
            RESOURCE_PACKS_INFO_PACKET.setForcedToAccept(forceResourcePacks);

            RESOURCES_PACK_STACK_PACKET.setForcedToAccept(forceResourcePacks && !allowClientResourcePacks);
            // Just left a '*' here, if we put in an exact game version,
            // it is possible that client won't send back ResourcePackClientResponsePacket(packIds=[*], status=COMPLETED)
            RESOURCES_PACK_STACK_PACKET.setGameVersion("*");

            for (var pack : Registries.PACKS.getContent().values()) {
                var type = pack.getType();
                if (type != Pack.Type.RESOURCES) continue;

                RESOURCE_PACKS_INFO_PACKET.getResourcePackInfos().add(pack.toEntryInfo());
                RESOURCES_PACK_STACK_PACKET.getResourcePacks().add(pack.toEntryStack());
            }
        }
    }
}
