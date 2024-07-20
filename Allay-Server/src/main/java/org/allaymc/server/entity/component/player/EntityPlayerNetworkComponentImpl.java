package org.allaymc.server.entity.component.player;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.data.LoginData;
import org.allaymc.api.client.storage.PlayerData;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.component.event.PlayerLoggedInEvent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.network.processor.PacketProcessorHolder;
import org.allaymc.api.pack.PackRegistry;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.biome.BiomeTypeRegistry;
import org.allaymc.server.network.processor.AllayPacketProcessorHolder;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.netty.channel.raknet.RakServerChannel;
import org.cloudburstmc.netty.handler.codec.raknet.common.RakSessionCodec;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.AuthoritativeMovementMode;
import org.cloudburstmc.protocol.bedrock.data.ChatRestrictionLevel;
import org.cloudburstmc.protocol.bedrock.data.GamePublishSetting;
import org.cloudburstmc.protocol.bedrock.data.SpawnBiomeType;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.joml.Vector3fc;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.allaymc.api.utils.AllayNbtUtils.readVector3f;
import static org.allaymc.api.utils.AllayNbtUtils.writeVector3f;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerNetworkComponentImpl implements EntityPlayerNetworkComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:player_network_component");

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
    @Getter
    @Setter
    protected boolean disconnected = false;
    // It will be set while client disconnecting from server
    // Otherwise, it will be null
    protected String disconnectReason = null;
    protected boolean hideDisconnectReason = false;
    protected AtomicInteger doFirstSpawnChunkThreshold = new AtomicInteger(Server.SETTINGS.worldSettings().doFirstSpawnChunkThreshold());
    @Manager
    protected ComponentManager<EntityPlayer> manager;
    @ComponentedObject
    protected EntityPlayer player;
    @Getter
    @Setter
    protected LoginData loginData;
    @Getter
    @Setter
    protected SecretKey encryptionSecretKey;
    @Dependency
    protected EntityPlayerBaseComponentImpl baseComponent;
    protected BedrockServerSession session;

    @Override
    public void handleDataPacket(BedrockPacket packet, long time) {
        var processor = packetProcessorHolder.getProcessor(packet);
        // processor won't be null as we have checked it the time it arrived
        processor.handleSync(player, packet, time);
    }

    @Override
    public void handleDisconnect() {
        if (disconnectReason != null) {
            disconnected = true;
            // Do not believe that the client will disconnect proactively
            // Especially for cheater, the BedrockPacketHandler::onDisconnect() method may won't be called
            // If we call server.onDisconnect() in BedrockPacketHandler::onDisconnect(),
            // cheaters will be able to create a lot of fake clients and make the server OOM
            onDisconnect();
            server.onDisconnect(player, disconnectReason);
            disconnectReason = null;
        }
    }

    protected void onDisconnect() {
        player.closeAllContainers();
    }

    @Override
    public boolean shouldHandleDisconnect() {
        return !disconnected && disconnectReason != null;
    }

    @Override
    public void setInitialized() {
        if (initialized) log.warn("Player.initialized is set twice");
        this.initialized = true;
    }

    @Override
    public BedrockServerSession getClientSession() {
        return session;
    }

    @Override
    public void setClientSession(BedrockServerSession session) {
        this.session = session;
        session.setPacketHandler(new BedrockPacketHandler() {
            @Override
            public PacketSignal handlePacket(BedrockPacket packet) {
                var processor = packetProcessorHolder.getProcessor(packet);
                if (processor == null) {
                    log.warn("Received a packet without packet handler: {}", packet);
                    return PacketSignal.HANDLED;
                }

                long time;
                var world = player.getWorld();
                if (world != null) {
                    time = world.getTick();
                } else {
                    // If player is not in any world, use server tick instead
                    time = Server.getInstance().getTick();
                }
                if (processor.handleAsync(player, packet, time) != PacketSignal.HANDLED) {
                    // Packet processors should make sure that PacketProcessor.handleSync() won't be called
                    // if player is not in any world
                    Preconditions.checkNotNull(world, "Player that is not in any world cannot handle sync packet");
                    world.addSyncPacketToQueue(player, packet, time);
                }

                return PacketSignal.HANDLED;
            }

            @Override
            public void onDisconnect(String reason) {
                disconnectReason = reason;
            }
        });
    }

    @Override
    public void onChunkInRangeSent() {
        if (doFirstSpawnChunkThreshold.get() > 0 && doFirstSpawnChunkThreshold.decrementAndGet() == 0) {
            doFirstSpawn();
        }
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
    public void disconnect(@MayContainTrKey String reason, boolean hideReason) {
        // Disconnection will be handled in handleDisconnect() method
        disconnectReason = I18n.get().tr(player.getLangCode(), reason);
        // Send disconnect packet to client
        try {
            player.getClientSession().disconnect(disconnectReason, hideDisconnectReason);
        } catch (Exception e) {
            log.error("Error while disconnecting the session", e);
        }
        hideDisconnectReason = hideReason;
    }

    protected void doFirstSpawn() {
        // Load EntityPlayer's NBT
        player.loadNBT(server.getPlayerStorage().readPlayerData(player).getPlayerNBT());

        var setEntityDataPacket = new SetEntityDataPacket();
        setEntityDataPacket.setRuntimeEntityId(player.getRuntimeId());
        setEntityDataPacket.getMetadata().putAll(player.getMetadata().getEntityDataMap());
        setEntityDataPacket.setTick(player.getWorld().getTick());
        sendPacket(setEntityDataPacket);

        // Update abilities, adventure settings, entity flags that are related to game type
        player.setGameType(player.getGameType());

        sendPacket(Server.getInstance().getCommandRegistry().encodeAvailableCommandsPacketFor(player));

        // PlayerListPacket can only be sent in this stage, otherwise the client won't show its skin
        server.addToPlayerList(player);
        if (server.getOnlinePlayerCount() > 1) {
            server.sendFullPlayerListInfoTo(player);
        }

        player.sendAttributesToClient();

        sendInventories();

        var playStatusPacket = new PlayStatusPacket();
        playStatusPacket.setStatus(PlayStatusPacket.Status.PLAYER_SPAWN);
        sendPacket(playStatusPacket);

        player.getLocation().dimension().getWorld().sendTime(List.of(player));
        // Save player data the first time
        server.getPlayerStorage().savePlayerData(player);
    }

    private void sendInventories() {
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.PLAYER_INVENTORY), FixedContainerId.PLAYER_INVENTORY);
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.OFFHAND), FixedContainerId.OFFHAND);
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.ARMOR), FixedContainerId.ARMOR);
        // No need to send cursor's content to client because there is nothing in cursor
    }

    @Override
    public void initializePlayer() {
        // initializePlayer() method will read all the data in PlayerData except playerNBT
        // To be more exactly, we will validate and set player's current pos and spawn point in this method
        // And playerNBT will be used in EntityPlayer::loadNBT() in doFirstSpawnPlayer() method
        var playerData = server.getPlayerStorage().readPlayerData(player);
        // Validate and set player pos
        Dimension dimension;
        Vector3fc currentPos;
        var logOffWorld = server.getWorldPool().getWorld(playerData.getCurrentWorldName());
        if (logOffWorld == null) {
            // The world where player logged off doesn't exist
            dimension = server.getWorldPool().getGlobalSpawnPoint().dimension();
            currentPos = new org.joml.Vector3f(server.getWorldPool().getGlobalSpawnPoint());
            // The old pos stored in playerNBT is invalid, we should replace it with the new one!
            var builder = playerData.getPlayerNBT().toBuilder();
            writeVector3f(builder, "Pos", "x", "y", "z", currentPos);
            playerData.setPlayerNBT(builder.build());
        } else {
            dimension = logOffWorld.getDimension(playerData.getCurrentDimensionId());
            // Read current pos from playerNBT
            currentPos = readVector3f(playerData.getPlayerNBT(), "Pos", "x", "y", "z");
        }
        // Validate and set spawn point
        validateAndSetSpawnPoint(playerData);
        // Load the current point chunk firstly so that we can add player entity into the chunk
        dimension.getChunkService().getOrLoadChunkSynchronously(
                (int) currentPos.x() >> 4,
                (int) currentPos.z() >> 4
        );
        baseComponent.setLocationBeforeSpawn(new Location3f(currentPos.x(), currentPos.y(), currentPos.z(), dimension));
        dimension.addPlayer(player);

        var spawnWorld = dimension.getWorld();
        var startGamePacket = new StartGamePacket();
        startGamePacket.getGamerules().addAll(spawnWorld.getWorldData().getGameRules().toNetworkGameRuleData());
        startGamePacket.setUniqueEntityId(player.getRuntimeId());
        startGamePacket.setRuntimeEntityId(player.getRuntimeId());
        startGamePacket.setPlayerGameType(player.getGameType());
        var loc = player.getLocation();
        var worldSpawn = spawnWorld.getWorldData().getSpawnPoint();
        startGamePacket.setDefaultSpawn(Vector3i.from(worldSpawn.x(), worldSpawn.y(), worldSpawn.z()));
        startGamePacket.setPlayerPosition(Vector3f.from(loc.x(), loc.y(), loc.z()));
        startGamePacket.setRotation(Vector2f.from(loc.pitch(), loc.yaw()));
        // We don't send world send to client for security reason
        startGamePacket.setSeed(0L);
        startGamePacket.setDimensionId(dimension.getDimensionInfo().dimensionId());
        startGamePacket.setGeneratorId(dimension.getWorldGenerator().getType().getId());
        startGamePacket.setLevelGameType(spawnWorld.getWorldData().getGameType());
        startGamePacket.setDifficulty(spawnWorld.getWorldData().getDifficulty().ordinal());
        // TODO: add it to server-settings.yml
        startGamePacket.setTrustingPlayers(true);
        startGamePacket.setLevelName(Server.SETTINGS.genericSettings().motd());
        startGamePacket.setLevelId("");
        startGamePacket.setDefaultPlayerPermission(Server.SETTINGS.genericSettings().defaultPermission());
        startGamePacket.setServerChunkTickRange(spawnWorld.getWorldData().getServerChunkTickRange());
        startGamePacket.setVanillaVersion(server.getNetworkServer().getCodec().getMinecraftVersion());
        startGamePacket.setPremiumWorldTemplateId("");
        startGamePacket.setInventoriesServerAuthoritative(true);
        startGamePacket.setItemDefinitions(Registries.ITEM_DEFINITIONS.getContent());
        startGamePacket.setAuthoritativeMovementMode(AuthoritativeMovementMode.SERVER);
        startGamePacket.setServerAuthoritativeBlockBreaking(true);
        // TODO: add it to server-settings.yml
        startGamePacket.setCommandsEnabled(true);
        startGamePacket.setMultiplayerGame(true);
        // TODO: add it to server-settings.yml
        startGamePacket.setBroadcastingToLan(true);
        startGamePacket.setMultiplayerCorrelationId(UUID.randomUUID().toString());
        startGamePacket.setXblBroadcastMode(GamePublishSetting.PUBLIC);
        startGamePacket.setPlatformBroadcastMode(GamePublishSetting.PUBLIC);
        startGamePacket.setServerEngine("Allay");
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
                        .addAll(Registries.BLOCK_DEFINITIONS.getContent())
                        .build()
        );

        var availableEntityIdentifiersPacket = new AvailableEntityIdentifiersPacket();
        availableEntityIdentifiersPacket.setIdentifiers(Registries.ENTITY_IDENTIFIERS.getContent());
        sendPacket(availableEntityIdentifiersPacket);

        var biomeDefinitionListPacket = new BiomeDefinitionListPacket();
        biomeDefinitionListPacket.setDefinitions(BiomeTypeRegistry.getRegistry().getBiomeDefinition());
        sendPacket(biomeDefinitionListPacket);

        sendPacket(DeferredData.getCreativeContentPacket());

        sendPacket(DeferredData.getCraftingDataPacket());
    }

    protected void validateAndSetSpawnPoint(PlayerData playerData) {
        Location3ic spawnPoint;
        var spawnWorld = server.getWorldPool().getWorld(playerData.getSpawnPointWorldName());
        if (spawnWorld == null) {
            // The world where the spawn point is located does not exist
            // Using global spawn point instead
            spawnPoint = server.getWorldPool().getGlobalSpawnPoint();
            playerData.setSpawnPoint(spawnPoint);
            playerData.setSpawnPointWorldName(spawnPoint.dimension().getWorld().getWorldData().getName());
            playerData.setSpawnPointDimensionId(spawnPoint.dimension().getDimensionInfo().dimensionId());
        } else {
            var vec = playerData.getSpawnPoint();
            spawnPoint = new Location3i(vec.x(), vec.y(), vec.z(), spawnWorld.getDimension(playerData.getSpawnPointDimensionId()));
        }
        player.setSpawnPoint(spawnPoint);
    }

    @Override
    public void completeLogin() {
        var playStatusPacket = new PlayStatusPacket();
        if (server.getOnlinePlayerCount() >= Server.SETTINGS.genericSettings().maxClientCount()) {
            playStatusPacket.setStatus(PlayStatusPacket.Status.FAILED_SERVER_FULL_SUB_CLIENT);
        } else {
            playStatusPacket.setStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);
        }
        sendPacket(playStatusPacket);
        loggedIn = true;
        server.onLoggedIn(player);
        // TODO: plugin event
        manager.callEvent(PlayerLoggedInEvent.INSTANCE);
        sendPacket(PackRegistry.getRegistry().getPacksInfoPacket());
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
    private static class DeferredData {
        static CraftingDataPacket CRAFTING_DATA_PACKET = null;

        static CraftingDataPacket getCraftingDataPacket() {
            if (CRAFTING_DATA_PACKET == null) {
                CRAFTING_DATA_PACKET = new CraftingDataPacket();
                CRAFTING_DATA_PACKET.getCraftingData().addAll(buildNetworkRecipeData());
                // TODO: packet.getPotionMixData().addAll();
                // TODO: packet.getContainerMixData().addAll();
                // TODO: packet.getMaterialReducers().addAll();
                CRAFTING_DATA_PACKET.setCleanRecipes(true);
            }
            return CRAFTING_DATA_PACKET;
        }

        static List<RecipeData> buildNetworkRecipeData() {
            var result = new ArrayList<RecipeData>();
            for (var recipe : Registries.RECIPES.getContent().values()) {
                result.add(recipe.toNetworkRecipeData());
            }
            return result;
        }

        static CreativeContentPacket CREATIVE_CONTENT_PACKET = null;

        static CreativeContentPacket getCreativeContentPacket() {
            if (CREATIVE_CONTENT_PACKET == null) {
                CREATIVE_CONTENT_PACKET = new CreativeContentPacket();
                CREATIVE_CONTENT_PACKET.setContents(Registries.CREATIVE_ITEMS.getContent().values().stream().map(ItemStack::toNetworkItemData).toArray(ItemData[]::new));
            }
            return CREATIVE_CONTENT_PACKET;
        }
    }
}
