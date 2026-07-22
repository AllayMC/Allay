package org.allaymc.server.network.protocol.v766;

import io.netty.buffer.Unpooled;
import org.allaymc.api.AllayAPI;
import org.allaymc.api.block.action.BlockAction;
import org.allaymc.api.block.action.ContinueBreakAction;
import org.allaymc.api.block.action.SimpleBlockAction;
import org.allaymc.api.block.action.StartBreakAction;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.dialog.Dialog;
import org.allaymc.api.dialog.ModelSettings;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.*;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.*;
import org.allaymc.api.entity.property.type.BooleanPropertyType;
import org.allaymc.api.entity.property.type.EnumPropertyType;
import org.allaymc.api.entity.property.type.FloatPropertyType;
import org.allaymc.api.entity.property.type.IntPropertyType;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.enchantment.EnchantOption;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackManifest;
import org.allaymc.api.player.CameraShakeType;
import org.allaymc.api.player.HudElement;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.PlayerData;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.data.SortOrder;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldViewer.BlockUpdate;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.explosion.FireworkExplosion;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.particle.*;
import org.allaymc.api.world.sound.*;
import org.allaymc.server.AllayServer;
import org.allaymc.server.container.ContainerNetworkInfo;
import org.allaymc.server.container.impl.UnopenedContainerId;
import org.allaymc.server.container.processor.ContainerActionProcessor;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.player.ChunkCache;
import org.allaymc.server.player.SkinConvertor;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.utils.GitProperties;
import org.allaymc.server.utils.JSONUtils;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.chunk.ChunkEncoder;
import org.allaymc.server.world.dimension.DimensionId;
import org.allaymc.server.world.dimension.VanillaGeneratorType;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionData;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitions;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraShakeAction;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermission;
import org.cloudburstmc.protocol.bedrock.data.definitions.DimensionDefinition;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.data.inventory.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeUnlockingRequirement;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.util.OptionalBoolean;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Complete packet encoder baseline for protocol v766.
 */
public class PacketEncoder_v766 extends PacketEncoder {
    public PacketEncoder_v766(ProtocolData data) {
        super(data);
    }

    @Override
    public ItemRegistryPacket encodeItemRegistry() {
        var packet = new ItemRegistryPacket();
        packet.getItems().addAll(getData().itemDefinitions());
        return packet;
    }

    @Override
    public CreativeContentPacket encodeCreativeContent() {
        var packet = new CreativeContentPacket();
        getData().creativeGroups().stream()
                .map(group -> new CreativeItemGroup(group.category(), group.name(), copyItemData(group.icon())))
                .forEach(packet.getGroups()::add);
        getData().creativeItems().stream()
                .map(item -> new CreativeItemData(copyItemData(item.item()), item.netId(), item.groupId()))
                .forEach(packet.getContents()::add);
        return packet;
    }

    @Override
    public CraftingDataPacket encodeCraftingData() {
        var packet = new CraftingDataPacket();
        getData().recipeTable().encodedRecipes().stream()
                .map(PacketEncoder_v766::copyRecipeData)
                .forEach(packet.getCraftingData()::add);
        packet.getPotionMixData().addAll(getData().recipeTable().potionMixes());
        packet.setCleanRecipes(true);
        return packet;
    }

    @Override
    public AvailableEntityIdentifiersPacket encodeAvailableEntityIdentifiers() {
        var identifiers = new ArrayList<NbtMap>();
        for (var entityType : Registries.ENTITIES.getContent().values()) {
            identifiers.add(NbtMap.builder()
                    .putString("id", entityType.getIdentifier().toString())
                    .build());
        }

        var packet = new AvailableEntityIdentifiersPacket();
        packet.setIdentifiers(NbtMap.builder()
                .putList("idlist", NbtType.COMPOUND, identifiers)
                .build());
        return packet;
    }

    @Override
    public Collection<SyncEntityPropertyPacket> encodeSyncEntityProperties() {
        var packets = new ArrayList<SyncEntityPropertyPacket>();
        for (var entityType : Registries.ENTITIES.getContent().values()) {
            if (entityType.getProperties().isEmpty()) {
                continue;
            }

            var properties = new ArrayList<NbtMap>();
            for (var propertyType : entityType.getProperties().values()) {
                var property = NbtMap.builder()
                        .putString("name", propertyType.getName())
                        .putBoolean("clientSync", true)
                        .putInt("type", propertyType.getType().ordinal());
                switch (propertyType) {
                    case EnumPropertyType<?> enumProperty ->
                            property.putList("enum", NbtType.STRING, enumProperty.serializedValues());
                    case IntPropertyType intProperty -> property
                            .putInt("default", intProperty.getDefaultValue())
                            .putInt("min", intProperty.getMin())
                            .putInt("max", intProperty.getMax());
                    case FloatPropertyType floatProperty -> property
                            .putFloat("default", floatProperty.getDefaultValue())
                            .putFloat("min", floatProperty.getMin())
                            .putFloat("max", floatProperty.getMax());
                    case BooleanPropertyType ignored -> {
                    }
                }
                properties.add(property.build());
            }

            var packet = new SyncEntityPropertyPacket();
            packet.setData(NbtMap.builder()
                    .putString("type", entityType.getIdentifier().toString())
                    .putList("properties", NbtType.COMPOUND, properties)
                    .build());
            packets.add(packet);
        }
        return packets;
    }

    @Override
    public BiomeDefinitionListPacket encodeBiomeDefinitions() {
        var definitions = new LinkedHashMap<String, BiomeDefinitionData>();
        for (var biomeType : Registries.BIOMES.getContent().m1().values()) {
            definitions.put(biomeType.getIdentifier().toString(), NetworkHelper.toNetwork(biomeType));
        }

        var packet = new BiomeDefinitionListPacket();
        packet.setBiomes(new BiomeDefinitions(definitions));
        return packet;
    }

    @Override
    public DimensionDataPacket encodeDimensionData() {
        var packet = new DimensionDataPacket();
        Registries.DIMENSIONS.getContent().m1().values().stream()
                .filter(PacketEncoder_v766::shouldSendDimensionDefinition)
                .map(dimensionType -> new DimensionDefinition(
                        dimensionType.getIdentifier().toString(),
                        dimensionType.getMaxHeight() + 1,
                        dimensionType.getMinHeight(),
                        getDimensionDefinitionGeneratorType(dimensionType).ordinal(),
                        dimensionType.getId()
                ))
                .forEach(packet.getDefinitions()::add);
        return packet;
    }

    @Override
    public ResourcePacksInfoPacket encodeResourcePacksInfo() {
        var settings = AllayServer.getSettings().resourcePackSettings();
        var packet = new ResourcePacksInfoPacket();
        packet.setForcedToAccept(settings.forceResourcePacks());
        packet.setWorldTemplateId(new UUID(0, 0));
        packet.setWorldTemplateVersion("");
        packet.setVibrantVisualsForceDisabled(settings.disableVibrantVisuals());

        for (var pack : Registries.PACKS.getContent().values()) {
            boolean scripting = pack.getType() == Pack.Type.SCRIPT;
            if (scripting) {
                packet.setScriptingEnabled(true);
            }

            ResourcePacksInfoPacket.Entry entry = switch (pack.getType()) {
                case RESOURCES -> createResourcePackInfo(pack, scripting, false);
                case DATA -> {
                    packet.setHasAddonPacks(true);
                    yield createResourcePackInfo(pack, scripting, true);
                }
                case WORLD_TEMPLATE, SCRIPT -> null;
            };
            if (entry != null) {
                packet.getResourcePackInfos().add(entry);
            }
        }
        return packet;
    }

    @Override
    public ResourcePackStackPacket encodeResourcePackStack() {
        var settings = AllayServer.getSettings().resourcePackSettings();
        var packet = new ResourcePackStackPacket();
        packet.setForcedToAccept(settings.forceResourcePacks() && !settings.allowClientResourcePacks());
        packet.setGameVersion("*");
        packet.getExperiments().addAll(createExperiments());

        for (var pack : Registries.PACKS.getContent().values()) {
            switch (pack.getType()) {
                case RESOURCES -> packet.getResourcePacks().add(new ResourcePackStackPacket.Entry(
                        pack.getId().toString(), pack.getStringVersion(), ""));
                case DATA -> packet.getBehaviorPacks().add(new ResourcePackStackPacket.Entry(
                        pack.getId().toString(), pack.getStringVersion(), ""));
                case WORLD_TEMPLATE, SCRIPT -> {
                }
            }
        }
        return packet;
    }

    @Override
    public ResourcePackDataInfoPacket encodeResourcePackDataInfo(
            Pack pack,
            int maxChunkSize
    ) {
        Objects.requireNonNull(pack, "pack");
        if (maxChunkSize <= 0) {
            throw new IllegalArgumentException("Resource-pack chunk size must be positive");
        }

        var packet = new ResourcePackDataInfoPacket();
        packet.setPackId(pack.getId());
        packet.setPackVersion(pack.getStringVersion());
        packet.setMaxChunkSize(maxChunkSize);
        packet.setChunkCount((pack.getSize() + (long) maxChunkSize - 1) / maxChunkSize);
        packet.setCompressedPackSize(pack.getSize());
        packet.setHash(pack.getHash().clone());
        packet.setType(toNetworkResourcePackType(pack.getType()));
        return packet;
    }

    @Override
    public ResourcePackChunkDataPacket encodeResourcePackChunkData(
            Pack pack,
            int chunkIndex,
            int maxChunkSize
    ) {
        Objects.requireNonNull(pack, "pack");
        if (maxChunkSize <= 0) {
            throw new IllegalArgumentException("Resource-pack chunk size must be positive");
        }
        if (chunkIndex < 0) {
            throw new IllegalArgumentException("Resource-pack chunk index cannot be negative");
        }

        long chunkCount = (pack.getSize() + (long) maxChunkSize - 1) / maxChunkSize;
        if (chunkIndex >= chunkCount) {
            throw new IllegalArgumentException("Resource-pack chunk index is out of bounds: " + chunkIndex);
        }
        long offset = (long) chunkIndex * maxChunkSize;

        var packet = new ResourcePackChunkDataPacket();
        packet.setPackId(pack.getId());
        packet.setPackVersion(pack.getStringVersion());
        packet.setChunkIndex(chunkIndex);
        packet.setData(Unpooled.wrappedBuffer(pack.getChunk((int) offset, maxChunkSize)));
        packet.setProgress(offset);
        return packet;
    }

    @Override
    public TrimDataPacket encodeTrimData() {
        var packet = new TrimDataPacket();
        InternalRegistries.TRIM_PATTERNS.getContent().values().stream()
                .map(pattern -> new TrimPattern(pattern.itemType().getIdentifier().toString(), pattern.patternId()))
                .forEach(packet.getPatterns()::add);
        InternalRegistries.TRIM_MATERIALS.getContent().values().stream()
                .map(material -> new TrimMaterial(
                        material.materialId(),
                        material.color(),
                        material.itemType().getIdentifier().toString()
                ))
                .forEach(packet.getMaterials()::add);
        return packet;
    }

    @Override
    public NetworkChunkPublisherUpdatePacket encodeChunkPublisher(EntityPlayer player) {
        Objects.requireNonNull(player, "player");
        var location = player.getLocation();
        var packet = new NetworkChunkPublisherUpdatePacket();
        packet.setPosition(Vector3i.from(location.x(), location.y(), location.z()));
        packet.setRadius(player.getChunkLoadingRadius() << 4);
        return packet;
    }

    @Override
    public LevelChunkPacket encodeLevelChunk(
            Chunk chunk,
            boolean useSubChunkSending,
            ChunkCache cache,
            UUID playerId,
            int cacheGeneration
    ) {
        Objects.requireNonNull(chunk, "chunk");
        var packet = new LevelChunkPacket[1];
        chunk.applyOperation(unsafeChunk -> packet[0] = useSubChunkSending
                        ? createSubChunkLevelChunkPacket(
                                (AllayUnsafeChunk) unsafeChunk,
                                cache,
                                playerId,
                                cacheGeneration
                        )
                        : createFullLevelChunkPacket(
                                (AllayUnsafeChunk) unsafeChunk,
                                cache,
                                playerId,
                                cacheGeneration
                        ),
                OperationType.READ,
                OperationType.READ
        );
        return packet[0];
    }

    private static LevelChunkPacket createSubChunkLevelChunkPacket(
            AllayUnsafeChunk chunk,
            ChunkCache cache,
            UUID playerId,
            int cacheGeneration
    ) {
        var dimensionType = chunk.getDimensionType();
        var packet = new LevelChunkPacket();
        packet.setDimension(dimensionType.getId());
        packet.setChunkX(chunk.getX());
        packet.setChunkZ(chunk.getZ());
        packet.setRequestSubChunks(true);
        // Sub-chunk limit is always relative to the dimension's minimum section.
        packet.setSubChunkLimit(findHighestNonAirSectionY(chunk) - dimensionType.minSectionY());

        if (cache != null) {
            byte[] biomesBlob = ChunkEncoder.encodeBiomesBlob(chunk);
            long[] hashes = cache.tryStoreBlobsAndOpenTransaction(
                    playerId,
                    cacheGeneration,
                    biomesBlob
            );
            if (hashes != null) {
                packet.setCachingEnabled(true);
                packet.getBlobIds().add(hashes[0]);
                packet.setData(Unpooled.wrappedBuffer(new byte[]{0}));
                return packet;
            }
        }

        packet.setCachingEnabled(false);
        packet.setData(ChunkEncoder.writeToNetworkBiomeOnly(chunk));
        return packet;
    }

    private static int findHighestNonAirSectionY(AllayUnsafeChunk chunk) {
        var dimensionType = chunk.getDimensionType();
        for (int highest = dimensionType.maxSectionY(); highest > dimensionType.minSectionY(); highest--) {
            if (!chunk.getSection(highest).isAirSection()) {
                return highest;
            }
        }
        return dimensionType.minSectionY();
    }

    private static LevelChunkPacket createFullLevelChunkPacket(
            AllayUnsafeChunk chunk,
            ChunkCache cache,
            UUID playerId,
            int cacheGeneration
    ) {
        var dimensionType = chunk.getDimensionType();
        var packet = new LevelChunkPacket();
        packet.setDimension(dimensionType.getId());
        packet.setChunkX(chunk.getX());
        packet.setChunkZ(chunk.getZ());
        packet.setRequestSubChunks(false);
        packet.setSubChunksLength(dimensionType.chunkSectionCount());

        if (cache != null) {
            int sectionCount = dimensionType.chunkSectionCount();
            byte[][] allBlobs = new byte[sectionCount + 1][];
            for (int i = 0; i < sectionCount; i++) {
                allBlobs[i] = ChunkEncoder.encodeSectionBlob(
                        chunk.getSection(dimensionType.minSectionY() + i)
                );
            }
            allBlobs[sectionCount] = ChunkEncoder.encodeBiomesBlob(chunk);

            long[] hashes = cache.tryStoreBlobsAndOpenTransaction(
                    playerId,
                    cacheGeneration,
                    allBlobs
            );
            if (hashes != null) {
                packet.setCachingEnabled(true);
                for (long hash : hashes) {
                    packet.getBlobIds().add(hash);
                }
                packet.setData(ChunkEncoder.writeCachedChunkData(chunk));
                return packet;
            }
        }

        packet.setCachingEnabled(false);
        packet.setData(ChunkEncoder.writeToNetwork(chunk));
        return packet;
    }

    @Override
    public SetTimePacket encodeTime(int timeOfDay) {
        var packet = new SetTimePacket();
        packet.setTime(timeOfDay);
        return packet;
    }

    @Override
    public GameRulesChangedPacket encodeGameRules(GameRules gameRules) {
        Objects.requireNonNull(gameRules, "gameRules");
        var packet = new GameRulesChangedPacket();
        packet.getGameRules().addAll(NetworkHelper.toNetwork(gameRules.getGameRules()));
        return packet;
    }

    @Override
    public UpdateBlockPacket encodeBlockUpdate(Vector3ic position, int layer, BlockState blockState) {
        Objects.requireNonNull(position, "position");
        Objects.requireNonNull(blockState, "blockState");
        var packet = new UpdateBlockPacket();
        packet.setBlockPosition(NetworkHelper.toNetwork(position));
        packet.setDataLayer(layer);
        packet.setDefinition(blockState::blockStateHash);
        packet.getFlags().add(UpdateBlockPacket.Flag.NETWORK);
        return packet;
    }

    @Override
    public Collection<UpdateSubChunkBlocksPacket> encodeBlockUpdates(
            Chunk chunk,
            Collection<BlockUpdate> blockUpdates,
            Collection<BlockUpdate> extraBlockUpdates
    ) {
        Objects.requireNonNull(chunk, "chunk");
        Objects.requireNonNull(blockUpdates, "blockUpdates");
        Objects.requireNonNull(extraBlockUpdates, "extraBlockUpdates");
        var packets = new UpdateSubChunkBlocksPacket[chunk.getDimensionType().chunkSectionCount()];
        encodeBlockUpdates(packets, chunk, blockUpdates, false);
        encodeBlockUpdates(packets, chunk, extraBlockUpdates, true);
        return Arrays.stream(packets).filter(Objects::nonNull).toList();
    }

    private static void encodeBlockUpdates(
            UpdateSubChunkBlocksPacket[] packets,
            Chunk chunk,
            Collection<BlockUpdate> blockUpdates,
            boolean extraLayer
    ) {
        for (var update : blockUpdates) {
            var sectionY = update.y() >> 4;
            var index = sectionY - chunk.getDimensionType().minSectionY();
            var packet = packets[index];
            if (packet == null) {
                packet = new UpdateSubChunkBlocksPacket();
                packet.setPosition(Vector3i.from(
                        chunk.getX() << 4,
                        sectionY << 4,
                        chunk.getZ() << 4
                ));
                packets[index] = packet;
            }

            var entry = new BlockChangeEntry(
                    Vector3i.from(update.x(), update.y(), update.z()),
                    update.blockState()::blockStateHash,
                    NetworkHelper.BLOCK_UPDATE_NETWORK,
                    -1,
                    BlockChangeEntry.MessageType.NONE
            );
            if (extraLayer) {
                packet.getExtraBlocks().add(entry);
            } else {
                packet.getStandardBlocks().add(entry);
            }
        }
    }

    @Override
    public Collection<BedrockPacket> encodeBlockAction(Vector3ic position, BlockAction action) {
        Objects.requireNonNull(position, "position");
        Objects.requireNonNull(action, "action");
        var blockPosition = NetworkHelper.toNetwork(position);
        var eventPosition = Vector3f.from(position.x(), position.y(), position.z());
        BedrockPacket packet = switch (action) {
            case SimpleBlockAction.OPEN -> {
                var blockEvent = new BlockEventPacket();
                blockEvent.setBlockPosition(blockPosition);
                blockEvent.setEventType(NetworkHelper.BLOCK_EVENT_TYPE_CHANGE_CHEST_STATE);
                blockEvent.setEventData(NetworkHelper.BLOCK_EVENT_DATA_OPEN_CHEST);
                yield blockEvent;
            }
            case SimpleBlockAction.CLOSE -> {
                var blockEvent = new BlockEventPacket();
                blockEvent.setBlockPosition(blockPosition);
                blockEvent.setEventType(NetworkHelper.BLOCK_EVENT_TYPE_CHANGE_CHEST_STATE);
                blockEvent.setEventData(NetworkHelper.BLOCK_EVENT_DATA_CLOSE_CHEST);
                yield blockEvent;
            }
            case StartBreakAction(double breakTime) -> {
                var levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.BLOCK_START_BREAK);
                levelEvent.setPosition(eventPosition);
                levelEvent.setData(toNetworkBreakTime(breakTime));
                yield levelEvent;
            }
            case ContinueBreakAction(double breakTime) -> {
                var levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.BLOCK_UPDATE_BREAK);
                levelEvent.setPosition(eventPosition);
                levelEvent.setData(toNetworkBreakTime(breakTime));
                yield levelEvent;
            }
            case SimpleBlockAction.STOP_BREAK -> {
                var levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.BLOCK_STOP_BREAK);
                levelEvent.setPosition(eventPosition);
                yield levelEvent;
            }
            default -> throw new IllegalStateException(
                    "Unhandled block action type: " + action.getClass().getSimpleName()
            );
        };
        return List.of(packet);
    }

    private static int toNetworkBreakTime(double breakTime) {
        return breakTime == 0 ? 65535 : (int) (65535 / (breakTime * 20));
    }

    @Override
    public StopSoundPacket encodeStopSound(String soundName) {
        var packet = new StopSoundPacket();
        packet.setSoundName(Objects.requireNonNullElse(soundName, ""));
        packet.setStoppingAllSound(soundName == null);
        return packet;
    }

    @Override
    public Collection<BedrockPacket> encodeParticle(
            Particle particle,
            Vector3dc position,
            int dimensionId
    ) {
        Objects.requireNonNull(particle, "particle");
        Objects.requireNonNull(position, "position");
        var networkPosition = NetworkHelper.toNetwork(MathUtils.toVec3f(position));
        var packet = new LevelEventPacket();
        packet.setPosition(networkPosition);
        switch (particle) {
            case SimpleParticle.EXPLODE -> packet.setType(ParticleType.EXPLODE);
            case SimpleParticle.HUGE_EXPLOSION -> packet.setType(LevelEvent.PARTICLE_EXPLOSION);
            case SimpleParticle.BONE_MEAL -> packet.setType(LevelEvent.PARTICLE_CROP_GROWTH);
            case SimpleParticle.BLOCK_FORCE_FIELD -> packet.setType(LevelEvent.PARTICLE_DENY_BLOCK);
            case SimpleParticle.ENDERMAN_TELEPORT -> packet.setType(LevelEvent.PARTICLE_TELEPORT);
            case SimpleParticle.EVAPORATE -> packet.setType(LevelEvent.PARTICLE_EVAPORATE_WATER);
            case SimpleParticle.SNOWBALL_POOF -> packet.setType(ParticleType.SNOWBALL_POOF);
            case SimpleParticle.ENTITY_FLAME -> packet.setType(ParticleType.MOB_FLAME);
            case SimpleParticle.WATER_DRIP -> packet.setType(ParticleType.DRIP_WATER);
            case SimpleParticle.LAVA_DRIP -> packet.setType(ParticleType.DRIP_LAVA);
            case SimpleParticle.LAVA -> packet.setType(ParticleType.LAVA);
            case SimpleParticle.DUST_PLUME -> packet.setType(ParticleType.DUST_PLUME);
            case SimpleParticle.WHITE_SMOKE -> packet.setType(ParticleType.WHITE_SMOKE);
            case SimpleParticle.FIREWORK_CONTRAIL -> packet.setType(ParticleType.FIREWORKS);
            case SimpleParticle.SMASH_ATTACK_GROUND_DUST ->
                    packet.setType(LevelEvent.PARTICLE_SMASH_ATTACK_GROUND_DUST);
            case SimpleParticle.WIND_EXPLOSION -> packet.setType(ParticleType.WIND_EXPLOSION);
            case SimpleParticle.BREEZE_WIND_EXPLOSION ->
                    packet.setType(ParticleType.BREEZE_WIND_EXPLOSION);
            case SimpleParticle.WATER_WAKE -> packet.setType(ParticleType.WATER_WAKE);
            case SimpleParticle.BUBBLE -> packet.setType(ParticleType.BUBBLE);
            case ShootParticle shoot -> {
                packet.setType(LevelEvent.PARTICLE_SHOOT);
                int data = 0;
                switch (shoot.face()) {
                    case DOWN -> {
                        data = 4;
                        networkPosition = networkPosition.add(0, -0.9f, 0);
                    }
                    case UP -> {
                        data = 4;
                        networkPosition = networkPosition.add(0, 0.5f, 0);
                    }
                    case NORTH -> {
                        data = 1;
                        networkPosition = networkPosition.add(0, -0.2f, -0.7f);
                    }
                    case SOUTH -> {
                        data = 7;
                        networkPosition = networkPosition.add(0, -0.2f, 0.7f);
                    }
                    case WEST -> {
                        data = 3;
                        networkPosition = networkPosition.add(-0.7f, -0.2f, 0);
                    }
                    case EAST -> {
                        data = 5;
                        networkPosition = networkPosition.add(0.7f, -0.2f, 0);
                    }
                }
                packet.setPosition(networkPosition);
                packet.setData(data);
            }
            case CustomParticle custom -> {
                var customPacket = new SpawnParticleEffectPacket();
                customPacket.setDimensionId(dimensionId);
                customPacket.setIdentifier(custom.particleName());
                customPacket.setMolangVariablesJson(Optional.ofNullable(custom.moLangVariables()));
                customPacket.setPosition(networkPosition);
                return List.of(customPacket);
            }
            case DragonEggTeleportParticle teleport -> {
                int xSign = teleport.diff().x() < 0 ? 1 << 24 : 0;
                int ySign = teleport.diff().y() < 0 ? 1 << 25 : 0;
                int zSign = teleport.diff().z() < 0 ? 1 << 26 : 0;
                int data = (Math.abs(teleport.diff().x()) << 16)
                           | (Math.abs(teleport.diff().y()) << 8)
                           | Math.abs(teleport.diff().z())
                           | xSign
                           | ySign
                           | zSign;
                packet.setType(LevelEvent.PARTICLE_DRAGON_EGG);
                packet.setData(data);
            }
            case NoteParticle note -> {
                var notePacket = new BlockEventPacket();
                notePacket.setBlockPosition(Vector3i.from(
                        (int) position.x(),
                        (int) position.y(),
                        (int) position.z()
                ));
                notePacket.setEventType(note.instrument().ordinal());
                notePacket.setEventData(note.pitch());
                return List.of(notePacket);
            }
            case BlockBreakParticle blockBreak -> {
                packet.setType(LevelEvent.PARTICLE_DESTROY_BLOCK);
                packet.setData(blockBreak.blockState().blockStateHash());
            }
            case PunchBlockParticle punch -> {
                packet.setType(switch (Objects.requireNonNull(punch.blockFace())) {
                    case UP -> LevelEvent.PARTICLE_BREAK_BLOCK_UP;
                    case DOWN -> LevelEvent.PARTICLE_BREAK_BLOCK_DOWN;
                    case NORTH -> LevelEvent.PARTICLE_BREAK_BLOCK_NORTH;
                    case SOUTH -> LevelEvent.PARTICLE_BREAK_BLOCK_SOUTH;
                    case WEST -> LevelEvent.PARTICLE_BREAK_BLOCK_WEST;
                    case EAST -> LevelEvent.PARTICLE_BREAK_BLOCK_EAST;
                });
                packet.setData(punch.blockState().blockStateHash());
            }
            case FlameParticle flame -> {
                packet.setType(ParticleType.FLAME);
                packet.setData(flame.color().getRGB());
            }
            case ItemBreakParticle itemBreak -> {
                packet.setType(ParticleType.ICON_CRACK);
                packet.setData(
                        (itemBreak.itemStack().getItemType().getRuntimeId() << 16)
                        | itemBreak.itemStack().getMeta()
                );
            }
            case SplashParticle splash -> {
                packet.setType(LevelEvent.PARTICLE_POTION_SPLASH);
                packet.setData(splash.color().getRGB());
            }
            case EffectParticle effect -> {
                packet.setType(ParticleType.MOB_SPELL);
                packet.setData(effect.color().getRGB());
            }
            case DustParticle dust -> {
                packet.setType(ParticleType.FALLING_DUST);
                packet.setData(dust.color().getRGB());
            }
            default -> throw new IllegalArgumentException(
                    "Unhandled particle type: " + particle.getClass().getSimpleName()
            );
        }
        return List.of(packet);
    }

    @Override
    public Collection<LevelEventPacket> encodeWeather(Weather weather) {
        Objects.requireNonNull(weather, "weather");
        var rainPacket = new LevelEventPacket();
        rainPacket.setPosition(Vector3f.ZERO);
        rainPacket.setType(LevelEvent.STOP_RAINING);
        if (weather != Weather.CLEAR) {
            rainPacket.setType(LevelEvent.START_RAINING);
            rainPacket.setData(65535);
        }

        var thunderPacket = new LevelEventPacket();
        thunderPacket.setPosition(Vector3f.ZERO);
        thunderPacket.setType(LevelEvent.STOP_THUNDERSTORM);
        if (weather == Weather.THUNDER) {
            thunderPacket.setType(LevelEvent.START_THUNDERSTORM);
            thunderPacket.setData(65535);
        }
        return List.of(rainPacket, thunderPacket);
    }

    @Override
    public BlockEntityDataPacket encodeBlockEntity(BlockEntity blockEntity) {
        Objects.requireNonNull(blockEntity, "blockEntity");
        return encodeBlockEntityData(blockEntity.getPosition(), blockEntity.saveNBT());
    }

    @Override
    public ContainerOpenPacket encodeLectern(Vector3ic position) {
        Objects.requireNonNull(position, "position");
        var packet = new ContainerOpenPacket();
        // The client does not send ContainerClose for lecterns, so use a fixed ID.
        packet.setId((byte) -1);
        packet.setType(ContainerType.LECTERN);
        packet.setBlockPosition(NetworkHelper.toNetwork(position));
        return packet;
    }

    @Override
    public OpenSignPacket encodeSignEditor(Vector3ic position, boolean frontSide) {
        Objects.requireNonNull(position, "position");
        var packet = new OpenSignPacket();
        packet.setPosition(NetworkHelper.toNetwork(position));
        packet.setFrontSide(frontSide);
        return packet;
    }

    @Override
    public InventoryContentPacket encodeContainerContents(Container container, int containerId) {
        Objects.requireNonNull(container, "container");
        var packet = new InventoryContentPacket();
        packet.setContainerId(containerId);
        // Both fields must be zero when FullContainerName is otherwise unused.
        packet.setContainerNameData(new FullContainerName(ContainerSlotType.ANVIL_INPUT, null));
        packet.setContents(encodeItemStacks(container.getItemStacks()));
        return packet;
    }

    @Override
    public InventorySlotPacket encodeContainerSlot(Container container, int slot, int containerId) {
        Objects.requireNonNull(container, "container");
        var packet = new InventorySlotPacket();
        packet.setContainerId(containerId);
        packet.setSlot(ContainerActionProcessor.toNetworkSlotIndex(container, slot));
        packet.setContainerNameData(new FullContainerName(
                ContainerActionProcessor.getSlotType(container, slot),
                null
        ));
        packet.setItem(encodeItemStack(container.getItemStack(slot)));
        return packet;
    }

    @Override
    public ContainerOpenPacket encodeContainerOpen(
            Container container,
            byte containerId,
            Vector3ic position
    ) {
        Objects.requireNonNull(container, "container");
        Objects.requireNonNull(position, "position");
        var packet = new ContainerOpenPacket();
        packet.setId(containerId);
        packet.setType(ContainerNetworkInfo.getInfo(container.getContainerType()).toNetworkType());
        packet.setBlockPosition(NetworkHelper.toNetwork(position));
        return packet;
    }

    @Override
    public ContainerClosePacket encodeContainerClose(
            Container container,
            byte containerId,
            boolean serverInitiated
    ) {
        Objects.requireNonNull(container, "container");
        return encodeContainerClose(
                containerId,
                ContainerNetworkInfo.getInfo(container.getContainerType()).toNetworkType(),
                serverInitiated
        );
    }

    @Override
    public ContainerSetDataPacket encodeContainerData(int containerId, int property, int value) {
        var packet = new ContainerSetDataPacket();
        packet.setWindowId((byte) containerId);
        packet.setProperty(property);
        packet.setValue(value);
        return packet;
    }

    @Override
    public BedrockPacket encodeEntitySpawn(Entity entity) {
        Objects.requireNonNull(entity, "entity");
        var location = entity.getLocation();
        var position = Vector3f.from(
                location.x(),
                location.y() + NetworkHelper.NETWORK_OFFSETS.getOrDefault(
                        entity.getEntityType().getIdentifier(), 0.0f
                ),
                location.z()
        );
        var motion = switch (entity) {
            case EntityPhysicsComponent physicsComponent -> {
                var entityMotion = physicsComponent.getMotion();
                yield Vector3f.from(entityMotion.x(), entityMotion.y(), entityMotion.z());
            }
            default -> Vector3f.ZERO;
        };

        return switch (entity) {
            case EntityPlayer player -> {
                var packet = new AddPlayerPacket();
                packet.setRuntimeEntityId(player.getRuntimeId());
                packet.setUniqueEntityId(player.getUniqueId().getLeastSignificantBits());
                packet.setUuid(player.getUniqueId());
                packet.setUsername(player.getNameTag());
                packet.setPlatformChatId("");
                packet.setDeviceId("");
                // AddPlayerPacket does not use the per-entity network position offset.
                packet.setPosition(Vector3f.from(location.x(), location.y(), location.z()));
                packet.setMotion(motion);
                packet.setRotation(Vector3f.from(
                        location.pitch(), location.yaw(), getHeadYaw(entity, location.yaw())
                ));
                packet.setGameType(NetworkHelper.toNetwork(player.getGameMode()));
                packet.getMetadata().putAll(encodeEntityMetadata(entity));
                packet.setHand(encodeItemStack(
                        player.getContainer(ContainerTypes.INVENTORY).getItemInHand()
                ));
                var properties = NetworkHelper.toNetworkProperties(entity);
                packet.getProperties().intProperties().addAll(properties.intProperties());
                packet.getProperties().floatProperties().addAll(properties.floatProperties());
                yield packet;
            }
            case EntityItem item -> {
                var packet = new AddItemEntityPacket();
                packet.setRuntimeEntityId(item.getRuntimeId());
                packet.setUniqueEntityId(item.getUniqueId().getLeastSignificantBits());
                packet.setItemInHand(encodeItemStack(
                        Objects.requireNonNullElse(item.getItemStack(), ItemAirStack.AIR_STACK)
                ));
                packet.setPosition(position);
                packet.setMotion(motion);
                packet.getMetadata().putAll(encodeEntityMetadata(entity));
                yield packet;
            }
            case EntityPainting painting -> {
                var packet = new AddPaintingPacket();
                packet.setRuntimeEntityId(painting.getRuntimeId());
                packet.setUniqueEntityId(painting.getUniqueId().getLeastSignificantBits());
                packet.setMotive(painting.getPaintingType().getTitle());
                var aabb = painting.getOffsetAABB();
                packet.setPosition(Vector3f.from(
                        (aabb.minX() + aabb.maxX()) / 2,
                        (aabb.minY() + aabb.maxY()) / 2,
                        (aabb.minZ() + aabb.maxZ()) / 2
                ));
                packet.setDirection(painting.getHorizontalFace().getHorizontalIndex());
                yield packet;
            }
            default -> {
                var packet = new AddEntityPacket();
                packet.setRuntimeEntityId(entity.getRuntimeId());
                packet.setUniqueEntityId(entity.getUniqueId().getLeastSignificantBits());
                packet.setIdentifier(entity.getEntityType().getIdentifier().toString());
                packet.setPosition(position);
                packet.setMotion(motion);
                packet.setRotation(Vector2f.from(location.pitch(), location.yaw()));
                packet.setHeadRotation((float) getHeadYaw(entity, location.yaw()));
                packet.setBodyRotation((float) location.yaw());
                packet.getMetadata().putAll(encodeEntityMetadata(entity));
                var properties = NetworkHelper.toNetworkProperties(entity);
                packet.getProperties().intProperties().addAll(properties.intProperties());
                packet.getProperties().floatProperties().addAll(properties.floatProperties());
                yield packet;
            }
        };
    }

    @Override
    public RemoveEntityPacket encodeEntityRemove(Entity entity) {
        Objects.requireNonNull(entity, "entity");
        var packet = new RemoveEntityPacket();
        packet.setUniqueEntityId(entity.getUniqueId().getLeastSignificantBits());
        return packet;
    }

    @Override
    public BedrockPacket encodePlayerGameMode(EntityPlayer player, boolean self) {
        Objects.requireNonNull(player, "player");
        if (self) {
            var packet = new SetPlayerGameTypePacket();
            packet.setGamemode(NetworkHelper.toNetwork(player.getGameMode()).ordinal());
            return packet;
        }

        var packet = new UpdatePlayerGameTypePacket();
        packet.setGameType(NetworkHelper.toNetwork(player.getGameMode()));
        packet.setEntityId(player.getRuntimeId());
        return packet;
    }

    @Override
    public BedrockPacket encodeEntityLocation(
            Entity entity,
            Location3dc location,
            boolean teleporting,
            boolean self
    ) {
        Objects.requireNonNull(entity, "entity");
        Objects.requireNonNull(location, "location");
        return self
                ? createMovePlayerPacket(entity, location, teleporting)
                : createAbsoluteMovePacket(entity, location, teleporting);
    }

    private static MovePlayerPacket createMovePlayerPacket(
            Entity entity,
            Location3dc location,
            boolean teleporting
    ) {
        var packet = new MovePlayerPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setPosition(Vector3f.from(
                location.x(),
                location.y() + NetworkHelper.NETWORK_OFFSETS.getOrDefault(
                        entity.getEntityType().getIdentifier(), 0.0f
                ),
                location.z()
        ));
        packet.setRotation(Vector3f.from(location.pitch(), location.yaw(), getHeadYaw(entity, location.yaw())));
        packet.setTeleportationCause(MovePlayerPacket.TeleportationCause.UNKNOWN);
        if (teleporting) {
            packet.setMode(MovePlayerPacket.Mode.TELEPORT);
        }
        return packet;
    }

    private static MoveEntityAbsolutePacket createAbsoluteMovePacket(
            Entity entity,
            Location3dc location,
            boolean teleporting
    ) {
        var packet = new MoveEntityAbsolutePacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setPosition(Vector3f.from(
                location.x(),
                location.y() + NetworkHelper.NETWORK_OFFSETS.getOrDefault(
                        entity.getEntityType().getIdentifier(), 0.0f
                ),
                location.z()
        ));
        packet.setRotation(Vector3f.from(location.pitch(), location.yaw(), getHeadYaw(entity, location.yaw())));
        packet.setTeleported(teleporting);
        if (entity instanceof EntityPhysicsComponent physicsComponent) {
            packet.setOnGround(physicsComponent.isOnGround());
        }
        return packet;
    }

    private static double getHeadYaw(Entity entity, double yaw) {
        return entity instanceof EntityHeadYawComponent headYawComponent ? headYawComponent.getHeadYaw() : yaw;
    }

    @Override
    public SetEntityMotionPacket encodeEntityMotion(Entity entity, Vector3dc motion) {
        Objects.requireNonNull(entity, "entity");
        Objects.requireNonNull(motion, "motion");
        var packet = new SetEntityMotionPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        return packet;
    }

    @Override
    public EntityDataMap encodeEntityMetadata(Entity entity) {
        Objects.requireNonNull(entity, "entity");
        var metadata = new EntityDataMap();
        addGenericMetadata(entity, metadata);
        addComponentSpecificMetadata(entity, metadata);
        addTypeSpecificMetadata(entity, metadata);
        return metadata;
    }

    @Override
    public SetEntityDataPacket encodeEntityState(Entity entity) {
        Objects.requireNonNull(entity, "entity");
        var packet = new SetEntityDataPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMetadata(encodeEntityMetadata(entity));
        packet.setProperties(NetworkHelper.toNetworkProperties(entity));
        return packet;
    }

    private static void addGenericMetadata(Entity entity, EntityDataMap metadata) {
        metadata.setFlag(EntityFlag.HAS_COLLISION, entity.hasEntityCollision());
        metadata.setFlag(EntityFlag.CAN_CLIMB, true);
        metadata.setFlag(EntityFlag.INVISIBLE, entity.isInvisible());
        metadata.setFlag(EntityFlag.NO_AI, entity.isImmobile());
        var aabb = entity.getAABB();
        var hitbox = NbtMap.builder()
                .putFloat("MinX", 0)
                .putFloat("MinY", 0)
                .putFloat("MinZ", 0)
                .putFloat("MaxX", (float) (aabb.maxX() - aabb.minX()))
                .putFloat("MaxY", (float) (aabb.maxY() - aabb.minY()))
                .putFloat("MaxZ", (float) (aabb.maxZ() - aabb.minZ()))
                .putFloat("PivotX", 0)
                .putFloat("PivotY", 0)
                .putFloat("PivotZ", 0)
                .build();
        metadata.put(EntityDataTypes.HITBOX, hitbox);
        metadata.put(EntityDataTypes.COLLISION_BOX, Vector3f.from(
                (float) (aabb.maxX() - aabb.minX()),
                (float) (aabb.maxY() - aabb.minY()),
                (float) (aabb.maxZ() - aabb.minZ())
        ));
        metadata.put(EntityDataTypes.WIDTH, (float) (aabb.maxX() - aabb.minX()));
        metadata.put(EntityDataTypes.HEIGHT, (float) (aabb.maxY() - aabb.minY()));
        metadata.put(EntityDataTypes.SCALE, (float) entity.getScale());
        // v766-v827 clients crash if HAS_NPC is true for an item entity.
        metadata.put(EntityDataTypes.HAS_NPC, !(entity instanceof EntityItem));
        if (entity.hasNameTag()) {
            metadata.setFlag(EntityFlag.CAN_SHOW_NAME, true);
            metadata.put(EntityDataTypes.NAME, entity.getNameTag());
            if (entity.isNameTagAlwaysShow()) {
                metadata.setFlag(EntityFlag.ALWAYS_SHOW_NAME, true);
                metadata.put(EntityDataTypes.NAMETAG_ALWAYS_SHOW, (byte) 1);
            }
        }
    }

    private static void addComponentSpecificMetadata(Entity entity, EntityDataMap metadata) {
        if (entity instanceof EntityPhysicsComponent physicsComponent) {
            metadata.setFlag(EntityFlag.HAS_GRAVITY, physicsComponent.hasGravity());
        }
        if (entity instanceof EntityLivingComponent livingComponent) {
            metadata.setFlag(EntityFlag.ON_FIRE, livingComponent.isOnFire());
            metadata.setFlag(EntityFlag.BREATHING, livingComponent.canBreathe());
            metadata.put(EntityDataTypes.AIR_SUPPLY, (short) livingComponent.getAirSupplyTicks());
            metadata.put(EntityDataTypes.AIR_SUPPLY_MAX, (short) livingComponent.getAirSupplyMaxTicks());
            metadata.put(EntityDataTypes.VISIBLE_MOB_EFFECTS, encodeVisibleEffects(livingComponent.getEffects().values()));
            metadata.put(
                    EntityDataTypes.FREEZING_EFFECT_STRENGTH,
                    livingComponent.getFreezeTicks() / (float) EntityLivingComponent.MAX_FREEZE_TICKS
            );
        }
        if (entity instanceof EntityPotionComponent potionComponent) {
            var potionType = potionComponent.getPotionType();
            if (potionType != null) {
                var data = potionType.ordinal() + 1;
                metadata.put(EntityDataTypes.AUX_VALUE_DATA, (short) data);
                if (data > 4) {
                    metadata.put(EntityDataTypes.CUSTOM_DISPLAY, (byte) (data + 1));
                }
            }
        }
        if (entity instanceof EntitySleepableComponent sleepableComponent && sleepableComponent.isSleeping()) {
            var bedPosition = sleepableComponent.getSleepingPos();
            metadata.setFlag(EntityFlag.SLEEPING, true);
            metadata.put(EntityDataTypes.BED_POSITION, Vector3i.from(
                    bedPosition.x(), bedPosition.y(), bedPosition.z()
            ));
            metadata.put(EntityDataTypes.PLAYER_FLAGS, (byte) 2);
        }
        if (entity instanceof EntityBabyComponent babyComponent) {
            metadata.setFlag(EntityFlag.BABY, babyComponent.isBaby());
            if (babyComponent.isBaby()) {
                metadata.put(EntityDataTypes.SCALE, 0.5f);
            }
        }
        if (entity instanceof EntityDyeableComponent dyeableComponent) {
            metadata.put(EntityDataTypes.COLOR, (byte) dyeableComponent.getColor().ordinal());
        }
    }

    private static void addTypeSpecificMetadata(Entity entity, EntityDataMap metadata) {
        switch (entity) {
            case EntityTnt tnt -> {
                metadata.setFlag(EntityFlag.IGNITED, true);
                metadata.put(EntityDataTypes.FUSE_TIME, tnt.getFuseTime());
            }
            case EntityPlayer player -> {
                metadata.setFlag(EntityFlag.SPRINTING, player.isSprinting());
                metadata.setFlag(EntityFlag.SNEAKING, player.isSneaking());
                metadata.setFlag(EntityFlag.BLOCKING, player.isBlocking());
                metadata.setFlag(EntityFlag.SWIMMING, player.isSwimming());
                metadata.setFlag(EntityFlag.GLIDING, player.isGliding());
                metadata.setFlag(EntityFlag.CRAWLING, player.isCrawling());
                metadata.setFlag(EntityFlag.DAMAGE_NEARBY_MOBS, player.isSpinAttacking());
                metadata.setFlag(EntityFlag.USING_ITEM, player.isUsingItemInAir());
                if (player.hasScoreTag()) {
                    metadata.put(EntityDataTypes.SCORE, player.getScoreTag());
                }
            }
            case EntityFallingBlock fallingBlock -> {
                metadata.setFlag(EntityFlag.FIRE_IMMUNE, true);
                metadata.put(EntityDataTypes.VARIANT, fallingBlock.getBlockState().blockStateHash());
            }
            case EntityXpOrb xpOrb -> metadata.put(EntityDataTypes.VALUE, xpOrb.getExperienceValue());
            case EntityArrow arrow -> metadata.setFlag(EntityFlag.CRITICAL, arrow.isCritical());
            case EntityThrownTrident trident -> {
                metadata.setFlag(EntityFlag.RETURN_TRIDENT, trident.isReturning());
                if (trident.isReturning()) {
                    var shooter = trident.getShooter();
                    metadata.put(
                            EntityDataTypes.OWNER_EID,
                            shooter != null ? shooter.getUniqueId().getLeastSignificantBits() : -1L
                    );
                } else {
                    metadata.put(EntityDataTypes.OWNER_EID, -1L);
                }
            }
            case EntityFireworksRocket firework -> {
                var fireworks = NbtMap.builder()
                        .putCompound("Fireworks", NbtMap.builder()
                                .putList(
                                        "Explosions",
                                        NbtType.COMPOUND,
                                        firework.getExplosions().stream().map(FireworkExplosion::saveNBT).toList()
                                )
                                .putByte("Flight", (byte) (firework.getExistenceTicks() / 20))
                                .build()
                        )
                        .build();
                metadata.put(EntityDataTypes.DISPLAY_FIREWORK, fireworks);

                var attachedPlayer = firework.getAttachedPlayer();
                if (attachedPlayer != null) {
                    metadata.put(EntityDataTypes.CUSTOM_DISPLAY, (byte) attachedPlayer.getRuntimeId());
                }
            }
            case EntityEnderCrystal enderCrystal ->
                    metadata.setFlag(EntityFlag.SHOW_BOTTOM, enderCrystal.isBaseVisible());
            case EntityAreaEffectCloud cloud -> {
                metadata.put(EntityDataTypes.AREA_EFFECT_CLOUD_RADIUS, cloud.getRadius());
                metadata.put(EntityDataTypes.AREA_EFFECT_CLOUD_DURATION, Integer.MAX_VALUE);
                metadata.put(EntityDataTypes.AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP, Float.MIN_VALUE);
                metadata.put(EntityDataTypes.AREA_EFFECT_CLOUD_CHANGE_RATE, Float.MIN_VALUE);
                metadata.put(EntityDataTypes.EFFECT_COLOR, cloud.getPotionType().getColor().getRGB());
                metadata.put(EntityDataTypes.EFFECT_AMBIENCE, (byte) 0);
            }
            case EntityLingeringPotion ignored -> metadata.setFlag(EntityFlag.LINGERING, true);
            case EntityFishingHook fishingHook -> {
                var shooter = fishingHook.getShooter();
                metadata.put(
                        EntityDataTypes.OWNER_EID,
                        shooter != null ? shooter.getUniqueId().getLeastSignificantBits() : -1L
                );
                var hookedEntity = fishingHook.getHookedEntity();
                metadata.put(
                        EntityDataTypes.TARGET_EID,
                        hookedEntity != null ? hookedEntity.getUniqueId().getLeastSignificantBits() : -1L
                );
            }
            case EntityArmorStand armorStand -> {
                metadata.put(EntityDataTypes.ARMOR_STAND_POSE_INDEX, armorStand.getPoseIndex());
                if (armorStand.getLastDamage() != null) {
                    var interval = armorStand.getTick() - armorStand.getLastDamageTime();
                    if (interval <= 5) {
                        metadata.put(EntityDataTypes.HURT_TICKS, (int) interval);
                    }
                }
            }
            case EntitySheep sheep -> metadata.setFlag(EntityFlag.SHEARED, sheep.isSheared());
            default -> {
            }
        }
    }

    private static long encodeVisibleEffects(Collection<EffectInstance> effects) {
        long visibleEffects = 0;
        for (var effect : effects) {
            if (effect.isVisible()) {
                visibleEffects = (visibleEffects << 7)
                        | ((long) effect.getType().getId() << 1)
                        | (effect.isAmbient() ? 1 : 0);
            }
        }
        return visibleEffects;
    }

    @Override
    public MobEffectPacket encodeMobEffect(
            Entity entity,
            EffectInstance newEffect,
            EffectInstance oldEffect,
            long tick
    ) {
        Objects.requireNonNull(entity, "entity");
        var effect = newEffect != null ? newEffect : Objects.requireNonNull(oldEffect, "oldEffect");

        var packet = new MobEffectPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setEffectId(effect.getType().getId());
        packet.setTick(tick);
        packet.setAmbient(effect.isAmbient());
        if (newEffect == null) {
            packet.setEvent(MobEffectPacket.Event.REMOVE);
        } else {
            packet.setAmplifier(newEffect.getAmplifier());
            packet.setParticles(newEffect.isVisible());
            packet.setDuration(newEffect.getDuration());
            packet.setEvent(oldEffect == null ? MobEffectPacket.Event.ADD : MobEffectPacket.Event.MODIFY);
        }
        return packet;
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> MobEquipmentPacket encodeEntityHand(T entity) {
        Objects.requireNonNull(entity, "entity");
        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setContainerId(UnopenedContainerId.PLAYER_INVENTORY);

        var handContainer = entity.getContainer(ContainerTypes.ENTITY_HAND);
        if (handContainer != null) {
            packet.setItem(encodeItemStack(handContainer.getItemInHand()));
            packet.setInventorySlot(0);
            packet.setHotbarSlot(0);
        } else {
            var inventory = entity.getContainer(ContainerTypes.INVENTORY);
            var handSlot = inventory.getHandSlot();
            packet.setItem(encodeItemStack(inventory.getItemInHand()));
            packet.setInventorySlot(handSlot);
            packet.setHotbarSlot(handSlot);
        }
        return packet;
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> MobEquipmentPacket encodeEntityOffhand(T entity) {
        Objects.requireNonNull(entity, "entity");
        var offhand = entity.getContainer(ContainerTypes.OFFHAND);
        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setContainerId(UnopenedContainerId.OFFHAND);
        packet.setInventorySlot(1);
        packet.setHotbarSlot(1);
        packet.setItem(encodeItemStack(offhand.getOffhand()));
        return packet;
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> MobArmorEquipmentPacket encodeEntityArmor(T entity) {
        Objects.requireNonNull(entity, "entity");
        var armor = entity.getContainer(ContainerTypes.ARMOR);
        var packet = new MobArmorEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setBody(encodeItemStack(ItemAirStack.AIR_STACK));
        packet.setHelmet(encodeItemStack(armor.getHelmet()));
        packet.setChestplate(encodeItemStack(armor.getChestplate()));
        packet.setLeggings(encodeItemStack(armor.getLeggings()));
        packet.setBoots(encodeItemStack(armor.getBoots()));
        return packet;
    }

    @Override
    public AnimateEntityPacket encodeEntityAnimation(Entity entity, EntityAnimation animation) {
        Objects.requireNonNull(entity, "entity");
        Objects.requireNonNull(animation, "animation");
        var packet = new AnimateEntityPacket();
        packet.setAnimation(animation.name());
        packet.setNextState(animation.nextState());
        packet.setStopExpression(animation.stopCondition());
        packet.setController(animation.controller());
        packet.getRuntimeEntityIds().add(entity.getRuntimeId());
        return packet;
    }

    @Override
    public LevelEventGenericPacket encodeSleepingIndicator(int sleepingCount, int totalCount) {
        var packet = new LevelEventGenericPacket();
        packet.setType(LevelEvent.SLEEPING_PLAYERS);
        packet.setTag(NbtMap.builder()
                .putInt("ableToSleep", totalCount)
                .putInt("overworldPlayerCount", totalCount)
                .putInt("sleepingPlayerCount", sleepingCount)
                .build());
        return packet;
    }

    @Override
    public ContainerOpenPacket encodeCommandBlockEditor(Vector3ic position) {
        Objects.requireNonNull(position, "position");
        var packet = new ContainerOpenPacket();
        packet.setId((byte) -1);
        packet.setType(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.COMMAND_BLOCK);
        packet.setBlockPosition(NetworkHelper.toNetwork(position));
        return packet;
    }

    @Override
    public PlayerEnchantOptionsPacket encodeEnchantOptions(
            Collection<Pair<Integer, EnchantOption>> enchantOptions
    ) {
        Objects.requireNonNull(enchantOptions, "enchantOptions");
        var packet = new PlayerEnchantOptionsPacket();
        enchantOptions.stream()
                .map(NetworkHelper::toNetwork)
                .forEach(packet.getOptions()::add);
        return packet;
    }

    @Override
    public Collection<BedrockPacket> encodePlayerSkin(EntityPlayer player, boolean trustSkin) {
        Objects.requireNonNull(player, "player");
        var skin = Objects.requireNonNull(player.getSkin(), "player.skin");
        if (player.isActualPlayer()) {
            var serializedSkin = SkinConvertor.toSerializedSkin(skin);
            var packet = new PlayerSkinPacket();
            packet.setUuid(player.getUniqueId());
            packet.setSkin(serializedSkin);
            packet.setNewSkinName(serializedSkin.getSkinId());
            packet.setOldSkinName("");
            packet.setTrustedSkin(trustSkin);
            return List.of(packet);
        }

        var listEntry = new PlayerListPacket.Entry(player.getUniqueId());
        listEntry.setEntityId(player.getUniqueId().getLeastSignificantBits());
        listEntry.setName(player.getUniqueId().toString());
        listEntry.setXuid("");
        listEntry.setPlatformChatId("");
        listEntry.setSkin(SkinConvertor.toSerializedSkin(skin));
        listEntry.setTrustedSkin(trustSkin);
        listEntry.setColor(Color.WHITE);

        var addPacket = new PlayerListPacket();
        addPacket.setAction(PlayerListPacket.Action.ADD);
        addPacket.getEntries().add(listEntry);

        var playerSkin = SkinConvertor.toSerializedSkin(skin);
        var skinPacket = new PlayerSkinPacket();
        skinPacket.setUuid(player.getUniqueId());
        skinPacket.setSkin(playerSkin);
        skinPacket.setNewSkinName(playerSkin.getSkinId());
        skinPacket.setOldSkinName("");
        skinPacket.setTrustedSkin(trustSkin);

        var removePacket = new PlayerListPacket();
        removePacket.setAction(PlayerListPacket.Action.REMOVE);
        removePacket.getEntries().add(new PlayerListPacket.Entry(player.getUniqueId()));
        return List.of(addPacket, skinPacket, removePacket);
    }

    @Override
    public Collection<BedrockPacket> encodeEntityAction(Entity entity, EntityAction action, boolean self) {
        Objects.requireNonNull(entity, "entity");
        Objects.requireNonNull(action, "action");
        return switch (action) {
            case SimpleEntityAction.SWING_ARM -> {
                if (entity instanceof EntityPlayer) {
                    yield self
                            ? List.of()
                            : List.of(createAnimatePacket(entity, AnimatePacket.Action.SWING_ARM, 0));
                }
                yield List.of(createEntityEventPacket(entity, EntityEventType.ATTACK_START, 0));
            }
            case SimpleEntityAction.HURT ->
                    List.of(createEntityEventPacket(entity, EntityEventType.HURT, 0));
            case SimpleEntityAction.DEATH ->
                    List.of(createEntityEventPacket(entity, EntityEventType.DEATH, 0));
            case SimpleEntityAction.EAT -> {
                if (!(entity instanceof ContainerHolder holder)
                        || !holder.hasContainer(ContainerTypes.INVENTORY)) {
                    yield List.of();
                }
                var item = holder.getContainer(ContainerTypes.INVENTORY).getItemInHand();
                int data = (item.getItemType().getRuntimeId() << 16) | item.getMeta();
                yield List.of(createEntityEventPacket(entity, EntityEventType.EATING_ITEM, data));
            }
            case SimpleEntityAction.FIREWORK_EXPLODE ->
                    List.of(createEntityEventPacket(entity, EntityEventType.FIREWORK_EXPLODE, 0));
            case SimpleEntityAction.TOTEM_USE ->
                    List.of(createEntityEventPacket(entity, EntityEventType.CONSUME_TOTEM, 0));
            case PickedUpAction(Entity picker) -> {
                var packet = new TakeItemEntityPacket();
                packet.setRuntimeEntityId(picker.getRuntimeId());
                packet.setItemRuntimeEntityId(entity.getRuntimeId());
                yield List.of(packet);
            }
            case ArrowShakeAction(int times) ->
                    List.of(createEntityEventPacket(entity, EntityEventType.ARROW_SHAKE, times));
            case CriticalHit(int count) ->
                    List.of(createAnimatePacket(entity, AnimatePacket.Action.CRITICAL_HIT, count));
            case EnchantedHit(int count) ->
                    List.of(createAnimatePacket(entity, AnimatePacket.Action.MAGIC_CRITICAL_HIT, count));
            case SimpleEntityAction.FISHING_HOOK_BITE -> List.of(
                    createEntityEventPacket(entity, EntityEventType.FISH_HOOK_BUBBLE, 0),
                    createEntityEventPacket(entity, EntityEventType.FISH_HOOK_TIME, 0),
                    createEntityEventPacket(entity, EntityEventType.FISH_HOOK_TEASE, 0)
            );
            case SimpleEntityAction.WAKE_UP ->
                    List.of(createAnimatePacket(entity, AnimatePacket.Action.WAKE_UP, 0));
            case SimpleEntityAction.IN_LOVE ->
                    List.of(createEntityEventPacket(entity, EntityEventType.LOVE_PARTICLES, 0));
            case SimpleEntityAction.EAT_GRASS ->
                    List.of(createEntityEventPacket(entity, EntityEventType.EAT_GRASS, 0));
            default -> throw new IllegalStateException(
                    "Unhandled entity action type: " + action.getClass().getSimpleName()
            );
        };
    }

    @Override
    public EmotePacket encodePlayerEmote(EntityPlayer player, UUID emoteId, boolean silence) {
        Objects.requireNonNull(player, "player");
        Objects.requireNonNull(emoteId, "emoteId");
        var packet = new EmotePacket();
        packet.setRuntimeEntityId(player.getRuntimeId());
        packet.setEmoteId(emoteId.toString());
        packet.setPlatformId("");
        packet.setXuid("");
        packet.getFlags().add(EmoteFlag.SERVER_SIDE);
        if (silence) {
            packet.getFlags().add(EmoteFlag.MUTE_EMOTE_CHAT);
        }
        return packet;
    }

    private static EntityEventPacket createEntityEventPacket(
            Entity entity,
            EntityEventType eventType,
            int data
    ) {
        var packet = new EntityEventPacket();
        packet.setType(eventType);
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setData(data);
        return packet;
    }

    private static AnimatePacket createAnimatePacket(Entity entity, AnimatePacket.Action action, float data) {
        var packet = new AnimatePacket();
        packet.setAction(action);
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setData(data);
        return packet;
    }

    @Override
    public Collection<BedrockPacket> encodeSound(Sound sound, Vector3dc p, boolean relative) {
        LevelSoundEventPacket packet = new LevelSoundEventPacket();
        var pos = NetworkHelper.toNetwork(MathUtils.toVec3f(p));
        packet.setPosition(pos);
        packet.setIdentifier(":");
        packet.setExtraData(-1);
        packet.setRelativeVolumeDisabled(!relative);

        switch (sound) {
            case SimpleSound.SHIELD_BLOCK -> packet.setSound(SoundEvent.SHIELD_BLOCK);
            case SimpleSound.FIREWORK_LAUNCH -> packet.setSound(SoundEvent.LAUNCH);
            case SimpleSound.FIREWORK_HUGE_BLAST -> packet.setSound(SoundEvent.LARGE_BLAST);
            case SimpleSound.FIREWORK_BLAST -> packet.setSound(SoundEvent.BLAST);
            case SimpleSound.FIREWORK_FLICKER -> packet.setSound(SoundEvent.TWINKLE);
            case SimpleSound.FURNACE_CRACKLE -> packet.setSound(SoundEvent.FURNACE_USE);
            case SimpleSound.CAMPFIRE_CRACKLE -> packet.setSound(SoundEvent.CAMPFIRE_CRACKLE);
            case SimpleSound.BLAST_FURNACE_CRACKLE -> packet.setSound(SoundEvent.BLAST_FURNACE_USE);
            case SimpleSound.SMOKER_CRACKLE -> packet.setSound(SoundEvent.SMOKER_USE);
            case SimpleSound.POTION_BREWED -> packet.setSound(SoundEvent.POTION_BREWED);
            case SimpleSound.USE_SPYGLASS -> packet.setSound(SoundEvent.USE_SPYGLASS);
            case SimpleSound.STOP_USING_SPYGLASS -> packet.setSound(SoundEvent.STOP_USING_SPYGLASS);
            case SimpleSound.FIRE_EXTINGUISH -> packet.setSound(SoundEvent.EXTINGUISH_FIRE);
            case SimpleSound.IGNITE -> packet.setSound(SoundEvent.IGNITE);
            case SimpleSound.BURNING -> packet.setSound(SoundEvent.PLAYER_HURT_ON_FIRE);
            case SimpleSound.DROWNING -> packet.setSound(SoundEvent.PLAYER_HURT_DROWN);
            case SimpleSound.BURP -> packet.setSound(SoundEvent.BURP);
            case SimpleSound.DENY -> packet.setSound(SoundEvent.DENY);
            case SimpleSound.CHEST_CLOSE -> packet.setSound(SoundEvent.CHEST_CLOSED);
            case SimpleSound.CHEST_OPEN -> packet.setSound(SoundEvent.CHEST_OPEN);
            case SimpleSound.ENDER_CHEST_CLOSE -> packet.setSound(SoundEvent.ENDERCHEST_CLOSED);
            case SimpleSound.ENDER_CHEST_OPEN -> packet.setSound(SoundEvent.ENDERCHEST_OPEN);
            case SimpleSound.SHULKER_BOX_CLOSE -> packet.setSound(SoundEvent.SHULKERBOX_CLOSED);
            case SimpleSound.SHULKER_BOX_OPEN -> packet.setSound(SoundEvent.SHULKERBOX_OPEN);
            case SimpleSound.BARREL_CLOSE -> packet.setSound(SoundEvent.BARREL_CLOSE);
            case SimpleSound.BARREL_OPEN -> packet.setSound(SoundEvent.BARREL_OPEN);
            case SimpleSound.FIZZ -> packet.setSound(SoundEvent.FIZZ);
            case SimpleSound.SPONGE_ABSORB -> packet.setSound(SoundEvent.SPONGE_ABSORB);
            case SimpleSound.GLASS_BREAK -> packet.setSound(SoundEvent.GLASS);
            case SimpleSound.BOW_SHOOT -> packet.setSound(SoundEvent.BOW);
            case SimpleSound.CROSSBOW_SHOOT -> packet.setSound(SoundEvent.CROSSBOW_SHOOT);
            case SimpleSound.ARROW_HIT -> packet.setSound(SoundEvent.BOW_HIT);
            case SimpleSound.MUSIC_DISC_END -> packet.setSound(SoundEvent.STOP_RECORD);
            case SimpleSound.COMPOSTER_EMPTY -> packet.setSound(SoundEvent.COMPOSTER_EMPTY);
            case SimpleSound.COMPOSTER_FILL -> packet.setSound(SoundEvent.COMPOSTER_FILL);
            case SimpleSound.COMPOSTER_FILL_LAYER -> packet.setSound(SoundEvent.COMPOSTER_FILL_LAYER);
            case SimpleSound.COMPOSTER_READY -> packet.setSound(SoundEvent.COMPOSTER_READY);
            case SimpleSound.LECTERN_BOOK_PLACE -> packet.setSound(SoundEvent.LECTERN_BOOK_PLACE);
            case SimpleSound.WAXED_SIGN_FAILED_INTERACTION -> packet.setSound(SoundEvent.WAXED_SIGN_INTERACT_FAIL);
            case SimpleSound.TELEPORT -> packet.setSound(SoundEvent.TELEPORT);
            case SimpleSound.DECORATED_POT_INSERT_FAILED -> packet.setSound(SoundEvent.DECORATED_POT_INSERT_FAILED);
            case SimpleSound.ITEM_BREAK -> packet.setSound(SoundEvent.BREAK);
            case SimpleSound.CHORUS_FLOWER_GROW -> packet.setSound(SoundEvent.CHORUS_GROW);
            case SimpleSound.END_PORTAL_FRAME_FILLED -> packet.setSound(SoundEvent.BLOCK_END_PORTAL_FRAME_FILL);
            case SimpleSound.END_PORTAL_SPAWN -> packet.setSound(SoundEvent.BLOCK_END_PORTAL_SPAWN);
            case SimpleSound.MACE_SMASH_AIR -> packet.setSound(SoundEvent.MACE_SMASH_AIR);
            case SimpleSound.MACE_SMASH_GROUND -> packet.setSound(SoundEvent.MACE_SMASH_GROUND);
            case SimpleSound.MACE_SMASH_HEAVY_GROUND -> packet.setSound(SoundEvent.MACE_SMASH_HEAVY_GROUND);
            case SimpleSound.EXPLOSION -> packet.setSound(SoundEvent.EXPLODE);
            case SimpleSound.WIND_CHARGE_BURST -> packet.setSound(SoundEvent.WIND_CHARGE_BURST);
            case SimpleSound.BREEZE_WIND_CHARGE_BURST -> packet.setSound(SoundEvent.WIND_CHARGE_BURST);
            case SimpleSound.PISTON_PUSH -> packet.setSound(SoundEvent.PISTON_OUT);
            case SimpleSound.PISTON_PULL -> packet.setSound(SoundEvent.PISTON_IN);
            case SimpleSound.BLOCK_CLICK -> packet.setSound(SoundEvent.BLOCK_CLICK);
            case SimpleSound.BLOCK_CLICK_FAIL -> packet.setSound(SoundEvent.BLOCK_CLICK_FAIL);
            case SimpleSound.TRIDENT_THROW -> packet.setSound(SoundEvent.ITEM_TRIDENT_THROW);
            case SimpleSound.TRIDENT_HIT -> packet.setSound(SoundEvent.ITEM_TRIDENT_HIT);
            case SimpleSound.TRIDENT_HIT_GROUND -> packet.setSound(SoundEvent.ITEM_TRIDENT_HIT_GROUND);
            case SimpleSound.TRIDENT_RETURN -> packet.setSound(SoundEvent.ITEM_TRIDENT_RETURN);
            case SimpleSound.TRIDENT_THUNDER -> packet.setSound(SoundEvent.ITEM_TRIDENT_THUNDER);
            case SimpleSound.BIG_DRIPLEAF_TILT_DOWN -> packet.setSound(SoundEvent.BIG_DRIPLEAF_TILT_DOWN);
            case SimpleSound.BIG_DRIPLEAF_TILT_UP -> packet.setSound(SoundEvent.BIG_DRIPLEAF_TILT_UP);
            case SimpleSound.BELL_HIT -> packet.setSound(SoundEvent.BELL);
            case SimpleSound.MILKING -> packet.setSound(SoundEvent.MILK);
            case SimpleSound.EGG_LAY -> packet.setSound(SoundEvent.PLOP);
            case SimpleSound.POWER_ON -> packet.setSound(SoundEvent.POWER_ON);
            case SimpleSound.POWER_OFF -> packet.setSound(SoundEvent.POWER_OFF);
            case SimpleSound.ACTIVATED -> packet.setSound(SoundEvent.ACTIVATE);
            case SimpleSound.DEACTIVATED -> packet.setSound(SoundEvent.DEACTIVATE);
            case SimpleSound.RESPAWN_ANCHOR_SET_SPAWN -> packet.setSound(SoundEvent.RESPAWN_ANCHOR_SET_SPAWN);
            case SimpleSound.RESPAWN_ANCHOR_DEPLETE -> packet.setSound(SoundEvent.RESPAWN_ANCHOR_DEPLETE);
            case SimpleSound.SHELF_SWAP_SINGLE -> packet.setSound(SoundEvent.SINGLE_ITEM_SWAP);
            case SimpleSound.SHELF_SWAP_MULTI -> packet.setSound(SoundEvent.MULTI_ITEM_SWAP);
            case EquipItemSound so -> packet.setSound(getEquipSound(so.itemType()));
            case TridentRiptideSound riptide -> packet.setSound(switch (riptide.level()) {
                case 1 -> SoundEvent.ITEM_TRIDENT_RIPTIDE_1;
                case 2 -> SoundEvent.ITEM_TRIDENT_RIPTIDE_2;
                case 3 -> SoundEvent.ITEM_TRIDENT_RIPTIDE_3;
                default -> throw new IllegalArgumentException("Invalid riptide level: " + riptide.level());
            });
            case SimpleSound.PAINTING_PLACE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_PLACE);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.GLOW_INK_SAC_USED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_INK_SACE_USED);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.DOOR_CRASH -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ZOMBIE_DOOR_CRASH);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.THUNDER -> {
                packet.setSound(SoundEvent.THUNDER);
                packet.setIdentifier("minecraft:lightning_bolt");
            }
            case SimpleSound.CLICK -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_CLICK);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.WAXED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_WAX_ON);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.WAX_REMOVED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_WAX_OFF);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.COPPER_SCRAPED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_SCRAPE);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.POP -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_INFINITY_ARROW_PICKUP);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.ITEM_ADD -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_ADD);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.ITEM_FRAME_REMOVE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_REMOVE);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.ITEM_FRAME_ROTATE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_ROTATE);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.GHAST_WARNING -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_GHAST_WARNING);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.GHAST_SHOOT -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_GHAST_FIREBALL);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.TNT -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_FUSE);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.ANVIL_LAND -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_LAND);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.ANVIL_USE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_USED);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.ANVIL_BREAK -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_BROKEN);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_FILL_WATER -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_FILL_WATER);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_TAKE_WATER -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_TAKE_WATER);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_FILL_LAVA -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_FILL_LAVA);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_TAKE_LAVA -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_TAKE_LAVA);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_FILL_POWDER_SNOW -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_FILL_POWDER_SNOW);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_TAKE_POWDER_SNOW -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_TAKE_POWDER_SNOW);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_FILL_POTION -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_FILL_POTION);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_TAKE_POTION -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_TAKE_POTION);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_ADD_DYE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_ADD_DYE);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_DYE_ARMOR -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_DYE_ARMOR);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_CLEAN_ARMOR -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_CLEAN_ARMOR);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_CLEAN_BANNER -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_CLEAN_BANNER);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.CAULDRON_EXPLODE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.CAULDRON_EXPLODE);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.FIRE_CHARGE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_BLAZE_FIREBALL);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.TOTEM -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_TOTEM_USED);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.ITEM_THROW -> {
                packet.setSound(SoundEvent.THROW);
                packet.setIdentifier("minecraft:player");
            }
            case SimpleSound.LEVEL_UP -> {
                packet.setSound(SoundEvent.LEVELUP);
                packet.setExtraData(0x10000000);
            }
            case SimpleSound.EXPERIENCE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_EXPERIENCE_ORB_PICKUP);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case NoteSound so -> {
                packet.setSound(SoundEvent.NOTE);
                packet.setExtraData((so.instrument().ordinal() << 8) | so.pitch());
            }
            case FallSound so -> {
                packet.setIdentifier("minecraft:player");
                if (so.distance() > 4) {
                    packet.setSound(SoundEvent.FALL_BIG);
                } else {
                    packet.setSound(SoundEvent.FALL_SMALL);
                }
            }
            case DoorSound so -> {
                packet.setSound(so.open() ? SoundEvent.DOOR_OPEN : SoundEvent.DOOR_CLOSE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case ButtonPressSound so -> {
                packet.setSound(SoundEvent.BUTTON_CLICK_ON);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case ButtonReleaseSound so -> {
                packet.setSound(SoundEvent.BUTTON_CLICK_OFF);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case PressurePlateSound so -> {
                packet.setSound(so.activated() ? SoundEvent.PRESSURE_PLATE_CLICK_ON : SoundEvent.PRESSURE_PLATE_CLICK_OFF);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case TrapdoorSound so -> {
                packet.setSound(so.open() ? SoundEvent.TRAPDOOR_OPEN : SoundEvent.TRAPDOOR_CLOSE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case FenceGateSound so -> {
                packet.setSound(so.open() ? SoundEvent.FENCE_GATE_OPEN : SoundEvent.FENCE_GATE_CLOSE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case BlockPlaceSound so -> {
                packet.setSound(SoundEvent.PLACE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case BlockBreakingSound so -> {
                packet.setSound(SoundEvent.HIT);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case ItemUseOnBlockSound so -> {
                packet.setSound(SoundEvent.ITEM_USE_ON);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case AttackSound so -> {
                packet.setIdentifier("minecraft:player");
                if (!so.damage()) {
                    packet.setSound(SoundEvent.ATTACK_NODAMAGE);
                } else {
                    packet.setSound(SoundEvent.ATTACK_STRONG);
                }
            }
            case BucketFillSound so -> {
                packet.setSound(switch (so.type()) {
                    case WATER -> SoundEvent.BUCKET_FILL_WATER;
                    case LAVA -> SoundEvent.BUCKET_FILL_LAVA;
                    case POWDER_SNOW -> SoundEvent.BUCKET_FILL_POWDER_SNOW;
                    case FISH -> SoundEvent.BUCKET_FILL_FISH;
                });
            }
            case BucketEmptySound so -> {
                packet.setSound(switch (so.type()) {
                    case WATER -> SoundEvent.BUCKET_EMPTY_WATER;
                    case LAVA -> SoundEvent.BUCKET_EMPTY_LAVA;
                    case POWDER_SNOW -> SoundEvent.BUCKET_EMPTY_POWDER_SNOW;
                    case FISH -> SoundEvent.BUCKET_EMPTY_FISH;
                });
            }
            case CrossbowLoadSound so -> {
                switch (so.stage()) {
                    case START -> packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_START : SoundEvent.CROSSBOW_LOADING_START);
                    case MIDDLE -> packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_MIDDLE : SoundEvent.CROSSBOW_LOADING_MIDDLE);
                    case END -> packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_END : SoundEvent.CROSSBOW_LOADING_END);
                }
            }
            case MusicDiscPlaySound so -> {
                switch (so.discType()) {
                    case DISC_13 -> packet.setSound(SoundEvent.RECORD_13);
                    case DISC_CAT -> packet.setSound(SoundEvent.RECORD_CAT);
                    case DISC_BLOCKS -> packet.setSound(SoundEvent.RECORD_BLOCKS);
                    case DISC_CHIRP -> packet.setSound(SoundEvent.RECORD_CHIRP);
                    case DISC_FAR -> packet.setSound(SoundEvent.RECORD_FAR);
                    case DISC_MALL -> packet.setSound(SoundEvent.RECORD_MALL);
                    case DISC_MELLOHI -> packet.setSound(SoundEvent.RECORD_MELLOHI);
                    case DISC_STAL -> packet.setSound(SoundEvent.RECORD_STAL);
                    case DISC_STRAD -> packet.setSound(SoundEvent.RECORD_STRAD);
                    case DISC_WARD -> packet.setSound(SoundEvent.RECORD_WARD);
                    case DISC_11 -> packet.setSound(SoundEvent.RECORD_11);
                    case DISC_WAIT -> packet.setSound(SoundEvent.RECORD_WAIT);
                    case DISC_OTHERSIDE -> packet.setSound(SoundEvent.RECORD_OTHERSIDE);
                    case DISC_PIGSTEP -> packet.setSound(SoundEvent.RECORD_PIGSTEP);
                    case DISC_5 -> packet.setSound(SoundEvent.RECORD_5);
                    case DISC_RELIC -> packet.setSound(SoundEvent.RECORD_RELIC);
                    case DISC_CREATOR -> packet.setSound(SoundEvent.RECORD_CREATOR);
                    case DISC_CREATOR_MUSIC_BOX -> packet.setSound(SoundEvent.RECORD_CREATOR_MUSIC_BOX);
                    case DISC_PRECIPICE -> packet.setSound(SoundEvent.RECORD_PRECIPICE);
                    case DISC_TEARS -> packet.setSound(SoundEvent.RECORD_TEARS);
                    case DISC_LAVA_CHICKEN -> packet.setSound(SoundEvent.RECORD_LAVA_CHICKEN);
                    default -> throw new IllegalArgumentException();
                }
            }
            case PointedDripstoneDripSound so -> {
                if (so.isWater()) {
                    packet.setSound(SoundEvent.POINTED_DRIPSTONE_CAULDRON_DRIP_WATER);
                } else {
                    packet.setSound(SoundEvent.POINTED_DRIPSTONE_CAULDRON_DRIP_LAVA);
                }
            }
            case RespawnAnchorChargeSound so -> {
                packet.setSound(SoundEvent.RESPAWN_ANCHOR_CHARGE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case ChiseledBookshelfSound so -> {
                PlaySoundPacket playSound = new PlaySoundPacket();
                playSound.setSound(switch (so.type()) {
                    case INSERT -> so.enchanted() ? SoundNames.INSERT_ENCHANTED_CHISELED_BOOKSHELF : SoundNames.INSERT_CHISELED_BOOKSHELF;
                    case PICKUP -> so.enchanted() ? SoundNames.PICKUP_ENCHANTED_CHISELED_BOOKSHELF : SoundNames.PICKUP_CHISELED_BOOKSHELF;
                });
                playSound.setPosition(pos);
                playSound.setVolume(1.0f);
                playSound.setPitch(1.0f);
                return List.of(playSound);
            }
            case DecoratedPotInsertedSound so -> {
                PlaySoundPacket playSound = new PlaySoundPacket();
                playSound.setSound(SoundNames.BLOCK_DECORATED_POT_INSERT);
                playSound.setPosition(pos);
                playSound.setVolume(1.0f);
                playSound.setPitch(0.7f + 0.5f * (float) so.progress());
                return List.of(playSound);
            }
            case SimpleSound.ARMOR_STAND_PLACE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ARMOR_STAND_PLACE);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.ARMOR_STAND_HIT -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ARMOR_STAND_HIT);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.ARMOR_STAND_BREAK -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ARMOR_STAND_BREAK);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case SimpleSound.ARMOR_STAND_LAND -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ARMOR_STAND_LAND);
                levelEvent.setPosition(pos.toFloat());
                return List.of(levelEvent);
            }
            case CustomSound so -> {
                PlaySoundPacket playSound = new PlaySoundPacket();
                playSound.setSound(so.soundName());
                playSound.setPosition(pos);
                playSound.setVolume(so.volume());
                playSound.setPitch(so.pitch());
                return List.of(playSound);
            }
            default -> throw new IllegalArgumentException("Unhandled sound type: " + sound.getClass().getSimpleName());
        }

        return supportsSoundEvent(packet.getSound()) ? List.of(packet) : List.of();
    }

    protected boolean supportsSoundEvent(SoundEvent soundEvent) {
        return switch (soundEvent) {
            case RECORD_TEARS, RECORD_LAVA_CHICKEN, SINGLE_ITEM_SWAP, MULTI_ITEM_SWAP -> false;
            default -> true;
        };
    }

    private static SoundEvent getEquipSound(ItemType<?> itemType) {
        if (itemType == ItemTypes.ELYTRA) {
            return SoundEvent.ARMOR_EQUIP_ELYTRA;
        }

        return switch (ItemHelper.getArmorTier(itemType)) {
            case LEATHER -> SoundEvent.ARMOR_EQUIP_LEATHER;
            case IRON -> SoundEvent.ARMOR_EQUIP_IRON;
            case CHAIN -> SoundEvent.ARMOR_EQUIP_CHAIN;
            case GOLD -> SoundEvent.ARMOR_EQUIP_GOLD;
            case DIAMOND, NETHERITE -> SoundEvent.ARMOR_EQUIP_DIAMOND;
        };
    }

    @Override
    public BossEventPacket encodeBossBar(long bossEntityId, BossBar bossBar) {
        Objects.requireNonNull(bossBar, "bossBar");
        var packet = new BossEventPacket();
        packet.setBossUniqueEntityId(bossEntityId);
        packet.setAction(BossEventPacket.Action.CREATE);
        packet.setTitle(bossBar.getTitle());
        packet.setHealthPercentage(bossBar.getProgress());
        packet.setDarkenSky(bossBar.isDarkenSky() ? 1 : 0);
        packet.setColor(bossBar.getColor().ordinal());
        packet.setOverlay(bossBar.getStyle().ordinal());
        return packet;
    }

    @Override
    public BossEventPacket encodeBossBarRemoval(long bossEntityId) {
        var packet = new BossEventPacket();
        packet.setBossUniqueEntityId(bossEntityId);
        packet.setAction(BossEventPacket.Action.REMOVE);
        return packet;
    }

    @Override
    public ModalFormRequestPacket encodeForm(int formId, Form form) {
        Objects.requireNonNull(form, "form");
        var packet = new ModalFormRequestPacket();
        packet.setFormId(formId);
        packet.setFormData(form.toJson());
        return packet;
    }

    @Override
    public ClientboundCloseFormPacket encodeCloseForms() {
        return new ClientboundCloseFormPacket();
    }

    @Override
    public Collection<BedrockPacket> encodeScoreboard(
            Scoreboard scoreboard,
            DisplaySlot slot,
            Collection<ScoreInfo> scores
    ) {
        Objects.requireNonNull(scoreboard, "scoreboard");
        Objects.requireNonNull(slot, "slot");
        Objects.requireNonNull(scores, "scores");

        var objective = new SetDisplayObjectivePacket();
        objective.setDisplaySlot(slot.getSlotName());
        objective.setObjectiveId(scoreboard.getObjectiveName());
        objective.setDisplayName(scoreboard.getDisplayName());
        objective.setCriteria(scoreboard.getCriteriaName());
        objective.setSortOrder(scoreboard.getSortOrder().ordinal());

        var scorePacket = new SetScorePacket();
        scorePacket.setInfos(new ArrayList<>(scores));
        scorePacket.setAction(SetScorePacket.Action.SET);
        return List.of(objective, scorePacket);
    }

    @Override
    public SetDisplayObjectivePacket encodeScoreboardSlotRemoval(DisplaySlot slot) {
        Objects.requireNonNull(slot, "slot");
        var packet = new SetDisplayObjectivePacket();
        packet.setDisplaySlot(slot.getSlotName());
        packet.setObjectiveId("");
        packet.setDisplayName("");
        packet.setCriteria("");
        packet.setSortOrder(SortOrder.ASCENDING.ordinal());
        return packet;
    }

    @Override
    public RemoveObjectivePacket encodeScoreboardRemoval(Scoreboard scoreboard) {
        Objects.requireNonNull(scoreboard, "scoreboard");
        var packet = new RemoveObjectivePacket();
        packet.setObjectiveId(scoreboard.getObjectiveName());
        return packet;
    }

    @Override
    public SetScorePacket encodeScore(SetScorePacket.Action action, ScoreInfo score) {
        Objects.requireNonNull(action, "action");
        var packet = new SetScorePacket();
        packet.setAction(action);
        if (score != null) {
            packet.getInfos().add(score);
        }
        return packet;
    }

    @Override
    public PlayerStartItemCooldownPacket encodeCooldown(String category, int duration) {
        Objects.requireNonNull(category, "category");
        var packet = new PlayerStartItemCooldownPacket();
        packet.setItemCategory(category);
        packet.setCooldownDuration(duration);
        return packet;
    }

    @Override
    public ClientboundMapItemDataPacket encodeMapData(long mapId, BufferedImage image) {
        Objects.requireNonNull(image, "image");
        int imageSize = 128;
        var packet = new ClientboundMapItemDataPacket();
        packet.setUniqueMapId(mapId);
        packet.setOrigin(Vector3i.ZERO);
        packet.getTrackedEntityIds().add(mapId);
        packet.setHeight(imageSize);
        packet.setWidth(imageSize);

        var colors = new int[imageSize * imageSize];
        int index = 0;
        for (int y = 0; y < imageSize; y++) {
            for (int x = 0; x < imageSize; x++) {
                colors[index++] = toAbgr(image.getRGB(x, y));
            }
        }
        packet.setColors(colors);
        return packet;
    }

    private static int toAbgr(int argb) {
        return ((argb >> 16) & 0xFF) |
               ((argb >> 8) & 0xFF) << 8 |
               (argb & 0xFF) << 16 |
               ((argb >> 24) & 0xFF) << 24;
    }

    @Override
    public DeathInfoPacket encodeDeathInfo(String message) {
        Objects.requireNonNull(message, "message");
        var packet = new DeathInfoPacket();
        packet.setCauseAttackName(message);
        return packet;
    }

    @Override
    public EntityEventPacket encodeItemChargingFinished(long runtimeEntityId) {
        var packet = new EntityEventPacket();
        packet.setType(EntityEventType.FINISHED_CHARGING_ITEM);
        packet.setRuntimeEntityId(runtimeEntityId);
        return packet;
    }

    @Override
    public UpdateAttributesPacket encodeAttribute(long runtimeEntityId, AttributeData attribute) {
        Objects.requireNonNull(attribute, "attribute");
        var packet = new UpdateAttributesPacket();
        packet.setRuntimeEntityId(runtimeEntityId);
        packet.getAttributes().add(attribute);
        return packet;
    }

    @Override
    public TextPacket encodeText(String xuid, String message, TextPacket.Type type) {
        Objects.requireNonNull(xuid, "xuid");
        Objects.requireNonNull(message, "message");
        Objects.requireNonNull(type, "type");
        var packet = new TextPacket();
        packet.setType(type);
        packet.setXuid(xuid);
        packet.setMessage(message);
        return packet;
    }

    @Override
    public ToastRequestPacket encodeToast(String title, String content) {
        Objects.requireNonNull(title, "title");
        Objects.requireNonNull(content, "content");
        var packet = new ToastRequestPacket();
        packet.setTitle(title);
        packet.setContent(content);
        return packet;
    }

    @Override
    public SetTitlePacket encodeTitle(
            SetTitlePacket.Type type,
            String text,
            int fadeInTime,
            int stayTime,
            int fadeOutTime
    ) {
        Objects.requireNonNull(type, "type");
        var packet = new SetTitlePacket();
        packet.setType(type);
        if (text != null) {
            packet.setText(text);
            packet.setXuid("");
            packet.setPlatformOnlineId("");
        }
        if (type == SetTitlePacket.Type.TIMES) {
            packet.setFadeInTime(fadeInTime);
            packet.setStayTime(stayTime);
            packet.setFadeOutTime(fadeOutTime);
        }
        return packet;
    }

    @Override
    public CameraShakePacket encodeCameraShake(CameraShakeType shakeType, float intensity, float duration) {
        Objects.requireNonNull(shakeType, "shakeType");
        var packet = new CameraShakePacket();
        packet.setShakeType(switch (shakeType) {
            case POSITIONAL -> org.cloudburstmc.protocol.bedrock.data.camera.CameraShakeType.POSITIONAL;
            case ROTATIONAL -> org.cloudburstmc.protocol.bedrock.data.camera.CameraShakeType.ROTATIONAL;
        });
        packet.setIntensity(intensity);
        packet.setDuration(duration);
        packet.setShakeAction(CameraShakeAction.ADD);
        return packet;
    }

    @Override
    public CameraShakePacket encodeCameraShakeStop() {
        var packet = new CameraShakePacket();
        packet.setShakeAction(CameraShakeAction.STOP);
        packet.setShakeType(org.cloudburstmc.protocol.bedrock.data.camera.CameraShakeType.POSITIONAL);
        packet.setIntensity(-1);
        packet.setDuration(-1);
        return packet;
    }

    @Override
    public PlayerFogPacket encodeFogStack(Collection<String> fogStack) {
        Objects.requireNonNull(fogStack, "fogStack");
        var packet = new PlayerFogPacket();
        packet.getFogStack().addAll(fogStack);
        return packet;
    }

    @Override
    public ChangeDimensionPacket encodeDimensionChange(
            DimensionType dimensionType,
            double x,
            double y,
            double z
    ) {
        Objects.requireNonNull(dimensionType, "dimensionType");
        var packet = new ChangeDimensionPacket();
        packet.setDimension(dimensionType.getId());
        packet.setPosition(Vector3f.from((float) x, (float) y + 1.62f, (float) z));
        return packet;
    }

    @Override
    public PlayerActionPacket encodeDimensionChangeSuccess(long runtimeEntityId) {
        var packet = new PlayerActionPacket();
        packet.setAction(PlayerActionType.DIMENSION_CHANGE_SUCCESS);
        packet.setRuntimeEntityId(runtimeEntityId);
        packet.setBlockPosition(Vector3i.ZERO);
        packet.setResultPosition(Vector3i.ZERO);
        return packet;
    }

    @Override
    public UpdateAbilitiesPacket encodePlayerAbilities(
            long uniqueEntityId,
            CommandPermission commandPermission,
            PlayerPermission playerPermission,
            Collection<Ability> abilities,
            float walkSpeed,
            float flySpeed,
            float verticalFlySpeed
    ) {
        Objects.requireNonNull(commandPermission, "commandPermission");
        Objects.requireNonNull(playerPermission, "playerPermission");
        Objects.requireNonNull(abilities, "abilities");
        var packet = new UpdateAbilitiesPacket();
        packet.setUniqueEntityId(uniqueEntityId);
        packet.setCommandPermission(commandPermission);
        packet.setPlayerPermission(playerPermission);

        var layer = new AbilityLayer();
        layer.setLayerType(AbilityLayer.Type.BASE);
        layer.getAbilitiesSet().addAll(Arrays.asList(Ability.values()));
        layer.getAbilityValues().addAll(abilities);
        layer.setWalkSpeed(walkSpeed);
        layer.setFlySpeed(flySpeed);
        layer.setVerticalFlySpeed(verticalFlySpeed);
        packet.getAbilityLayers().add(layer);
        return packet;
    }

    @Override
    public UpdateAdventureSettingsPacket encodeAdventureSettings(
            boolean noPlayerVersusMob,
            boolean noMobVersusPlayer,
            boolean showNameTags,
            boolean immutableWorld
    ) {
        var packet = new UpdateAdventureSettingsPacket();
        packet.setNoPvM(noPlayerVersusMob);
        packet.setNoMvP(noMobVersusPlayer);
        packet.setShowNameTags(showNameTags);
        packet.setImmutableWorld(immutableWorld);
        packet.setAutoJump(true);
        return packet;
    }

    @Override
    public PlayerListPacket encodePlayerList(
            Collection<? extends Player> players,
            boolean add,
            boolean trustSkins
    ) {
        Objects.requireNonNull(players, "players");
        var packet = new PlayerListPacket();
        packet.setAction(add ? PlayerListPacket.Action.ADD : PlayerListPacket.Action.REMOVE);
        for (var player : players) {
            var entry = new PlayerListPacket.Entry(player.getLoginData().getUuid());
            var entity = Objects.requireNonNull(player.getControlledEntity(), "controlledEntity");
            entry.setEntityId(entity.getUniqueId().getLeastSignificantBits());
            entry.setName(player.getOriginName());
            entry.setXuid(player.getLoginData().getXuid());
            entry.setPlatformChatId(player.getLoginData().getDeviceInfo().deviceName());
            entry.setBuildPlatform(BuildPlatform.from(player.getLoginData().getDeviceInfo().device().getId()));
            entry.setSkin(SkinConvertor.toSerializedSkin(player.getLoginData().getSkin()));
            entry.setTrustedSkin(trustSkins);
            entry.setColor(new Color(player.getOriginName().hashCode() & 0xFFFFFF));
            packet.getEntries().add(entry);
        }
        return packet;
    }

    @Override
    public TransferPacket encodeTransfer(String address, int port) {
        Objects.requireNonNull(address, "address");
        var packet = new TransferPacket();
        packet.setAddress(address);
        packet.setPort(port);
        return packet;
    }

    @Override
    public AvailableCommandsPacket encodeCommands(Collection<CommandData> commands) {
        Objects.requireNonNull(commands, "commands");
        var packet = new AvailableCommandsPacket();
        packet.getCommands().addAll(commands);
        return packet;
    }

    @Override
    public Collection<SetHudPacket> encodeHudElements(Collection<HudElement> hiddenElements) {
        Objects.requireNonNull(hiddenElements, "hiddenElements");
        var show = new SetHudPacket();
        var hide = new SetHudPacket();
        show.setVisibility(HudVisibility.RESET);
        hide.setVisibility(HudVisibility.HIDE);

        for (var element : HudElement.values()) {
            if (hiddenElements.contains(element)) {
                hide.getElements().add(NetworkHelper.toNetwork(element));
            } else {
                show.getElements().add(NetworkHelper.toNetwork(element));
            }
        }

        var packets = new ArrayList<SetHudPacket>(2);
        if (!show.getElements().isEmpty()) {
            packets.add(show);
        }
        if (!hide.getElements().isEmpty()) {
            packets.add(hide);
        }
        return packets;
    }

    @Override
    public Collection<BedrockPacket> encodeDialog(Dialog dialog, Entity entity) {
        Objects.requireNonNull(dialog, "dialog");
        Objects.requireNonNull(entity, "entity");
        var buttonJson = JSONUtils.to(NetworkHelper.toNetworkDialogButtons(dialog.getButtons()));
        var modelSettings = dialog.getModelSettings();
        var portraitOffsetJson = JSONUtils.to(Map.of("portrait_offsets", NetworkHelper.toNetwork(new ModelSettings(
                modelSettings.scale(),
                modelSettings.offset().add(
                        0,
                        NetworkHelper.NETWORK_OFFSETS.getOrDefault(entity.getEntityType().getIdentifier(), 0.0f),
                        0,
                        new Vector3d()
                ),
                modelSettings.rotation()
        ))));

        var metadata = encodeEntityMetadata(entity);
        metadata.put(EntityDataTypes.NPC_DATA, portraitOffsetJson);
        var entityData = new SetEntityDataPacket();
        entityData.setRuntimeEntityId(entity.getRuntimeId());
        entityData.setMetadata(metadata);

        var dialogue = new NpcDialoguePacket();
        dialogue.setUniqueEntityId(entity.getUniqueId().getLeastSignificantBits());
        dialogue.setAction(NpcDialoguePacket.Action.OPEN);
        dialogue.setDialogue(dialog.getBody());
        dialogue.setSceneName("default");
        dialogue.setNpcName(dialog.getTitle());
        dialogue.setActionJson(buttonJson);
        return List.of(entityData, dialogue);
    }

    @Override
    public NpcDialoguePacket encodeDialogClose(Entity entity) {
        Objects.requireNonNull(entity, "entity");
        var packet = new NpcDialoguePacket();
        packet.setUniqueEntityId(entity.getUniqueId().getLeastSignificantBits());
        packet.setDialogue("");
        packet.setSceneName("");
        packet.setNpcName("");
        packet.setActionJson("");
        packet.setAction(NpcDialoguePacket.Action.CLOSE);
        return packet;
    }

    @Override
    public ClientCacheMissResponsePacket encodeClientCacheMissResponse(Map<Long, byte[]> blobs) {
        Objects.requireNonNull(blobs, "blobs");
        var copiedBlobs = new LinkedHashMap<Long, byte[]>();
        for (var entry : blobs.entrySet()) {
            copiedBlobs.put(
                    Objects.requireNonNull(entry.getKey(), "blob hash"),
                    Objects.requireNonNull(entry.getValue(), "blob data").clone()
            );
        }

        var packet = new ClientCacheMissResponsePacket();
        copiedBlobs.forEach((hash, data) -> packet.getBlobs().put(hash, Unpooled.wrappedBuffer(data)));
        return packet;
    }

    @Override
    public BlockEntityDataPacket encodeBlockEntityData(Vector3ic position, NbtMap data) {
        Objects.requireNonNull(position, "position");
        Objects.requireNonNull(data, "data");
        var packet = new BlockEntityDataPacket();
        packet.setBlockPosition(Vector3i.from(position.x(), position.y(), position.z()));
        packet.setData(data.toBuilder().build());
        return packet;
    }

    @Override
    public ChunkRadiusUpdatedPacket encodeChunkRadiusUpdated(int radius) {
        var packet = new ChunkRadiusUpdatedPacket();
        packet.setRadius(radius);
        return packet;
    }

    @Override
    public SubChunkPacket encodeSubChunk(
            int dimension,
            Vector3i centerPosition,
            boolean cacheEnabled,
            Collection<SubChunkData> subChunks
    ) {
        Objects.requireNonNull(centerPosition, "centerPosition");
        Objects.requireNonNull(subChunks, "subChunks");
        if (subChunks.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Sub-chunk response cannot contain null data");
        }

        var packet = new SubChunkPacket();
        packet.setDimension(dimension);
        packet.setCenterPosition(Vector3i.from(
                centerPosition.getX(),
                centerPosition.getY(),
                centerPosition.getZ()
        ));
        packet.setCacheEnabled(cacheEnabled);
        packet.setSubChunks(new ArrayList<>(subChunks));
        return packet;
    }

    @Override
    public ContainerClosePacket encodeContainerClose(
            byte id,
            ContainerType type,
            boolean serverInitiated
    ) {
        var packet = new ContainerClosePacket();
        packet.setId(id);
        packet.setType(Objects.requireNonNull(type, "type"));
        packet.setServerInitiated(serverInitiated);
        return packet;
    }

    @Override
    public ItemStackResponsePacket encodeItemStackResponse(Collection<ItemStackResponse> responses) {
        Objects.requireNonNull(responses, "responses");
        if (responses.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Item-stack response cannot contain null entries");
        }

        var packet = new ItemStackResponsePacket();
        packet.getEntries().addAll(responses);
        return packet;
    }

    @Override
    public RespawnPacket encodeRespawn(Vector3ic position) {
        Objects.requireNonNull(position, "position");
        var packet = new RespawnPacket();
        packet.setPosition(Vector3f.from(position.x(), position.y(), position.z()));
        packet.setState(RespawnPacket.State.SERVER_READY);
        return packet;
    }

    @Override
    public ServerSettingsResponsePacket encodeServerSettingsResponse(int formId, String formData) {
        var packet = new ServerSettingsResponsePacket();
        packet.setFormId(formId);
        packet.setFormData(Objects.requireNonNull(formData, "formData"));
        return packet;
    }

    @Override
    public StartGamePacket encodeStartGame(
            World spawnWorld,
            PlayerData playerData,
            Dimension dimension,
            EntityPlayer player
    ) {
        Objects.requireNonNull(spawnWorld, "spawnWorld");
        Objects.requireNonNull(playerData, "playerData");
        Objects.requireNonNull(dimension, "dimension");
        Objects.requireNonNull(player, "player");

        var worldData = spawnWorld.getWorldData();
        var packet = new StartGamePacket();
        packet.getGamerules().addAll(NetworkHelper.toNetwork(worldData.getGameRules().getGameRules()));
        packet.setUniqueEntityId(player.getUniqueId().getLeastSignificantBits());
        packet.setRuntimeEntityId(player.getRuntimeId());
        packet.setPlayerGameType(GameType.from(playerData.getNbt().getInt(
                "PlayerGameMode",
                NetworkHelper.toNetwork(worldData.getGameMode()).ordinal()
        )));

        var location = player.getLocation();
        var worldSpawn = worldData.getSpawnPoint();
        packet.setDefaultSpawn(Vector3i.from(worldSpawn.x(), worldSpawn.y(), worldSpawn.z()));
        packet.setPlayerPosition(Vector3f.from(location.x(), location.y() + 1.62, location.z()));
        packet.setRotation(Vector2f.from(location.pitch(), location.yaw()));
        // The world seed is intentionally hidden from clients.
        packet.setSeed(0L);
        packet.setDimensionId(dimension.getDimensionType().getId());
        packet.setGeneratorId(getVanillaGeneratorType(dimension.getDimensionType()).ordinal());
        packet.setLevelGameType(NetworkHelper.toNetwork(worldData.getGameMode()));
        packet.setDifficulty(worldData.getDifficulty().ordinal());
        packet.setTrustingPlayers(true);

        var settings = AllayServer.getSettings();
        var levelName = settings.genericSettings().motd();
        packet.setLevelName(levelName);
        packet.setLevelId(levelName);
        packet.setDefaultPlayerPermission(PlayerPermission.valueOf(settings.genericSettings().defaultPermission()));
        packet.setServerChunkTickRange(settings.worldSettings().tickRadius());
        packet.setVanillaVersion("*");
        packet.setServerEngine(AllayAPI.getInstance().getCoreName() + " " + GitProperties.getBuildVersion());
        packet.setPremiumWorldTemplateId("00000000-0000-0000-0000-000000000000");
        packet.setInventoriesServerAuthoritative(true);
        packet.setAuthoritativeMovementMode(AuthoritativeMovementMode.SERVER_WITH_REWIND);
        packet.setServerAuthoritativeBlockBreaking(true);
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
        packet.getItemDefinitions().addAll(getData().itemDefinitions());
        packet.getBlockProperties().addAll(getData().customBlockProperties());
        packet.getExperiments().addAll(createExperiments());
        return packet;
    }

    /**
     * Creates experiment data for this exact protocol. This intentionally returns fresh payload
     * objects instead of caching small data.
     */
    protected List<ExperimentData> createExperiments() {
        return createDefaultExperiments();
    }

    protected final List<ExperimentData> createDefaultExperiments() {
        return List.of(
                new ExperimentData("data_driven_items", true),
                new ExperimentData("upcoming_creator_features", true),
                new ExperimentData("experimental_molang_features", true)
        );
    }

    private static ResourcePacksInfoPacket.Entry createResourcePackInfo(
            Pack pack,
            boolean scripting,
            boolean addon
    ) {
        return new ResourcePacksInfoPacket.Entry(
                pack.getId(),
                pack.getStringVersion(),
                pack.getSize(),
                pack.getContentKey(),
                "",
                pack.getId().toString(),
                scripting,
                pack.getManifest().getCapabilities().contains(PackManifest.Capability.RAYTRACED),
                null,
                addon
        );
    }

    private static ResourcePackType toNetworkResourcePackType(org.allaymc.api.pack.Pack.Type type) {
        return switch (type) {
            case RESOURCES -> ResourcePackType.RESOURCES;
            case DATA -> ResourcePackType.DATA_ADD_ON;
            case WORLD_TEMPLATE -> ResourcePackType.WORLD_TEMPLATE;
            case SCRIPT -> ResourcePackType.ADDON;
        };
    }

    private static boolean shouldSendDimensionDefinition(org.allaymc.api.world.dimension.DimensionType dimensionType) {
        var dimensionId = DimensionId.fromDimensionType(dimensionType);
        return dimensionId == null || !dimensionId.isDefaultBounds(dimensionType);
    }

    private static VanillaGeneratorType getDimensionDefinitionGeneratorType(org.allaymc.api.world.dimension.DimensionType dimensionType) {
        var dimensionId = DimensionId.fromDimensionType(dimensionType);
        if (dimensionId != null && !dimensionId.isDefaultBounds(dimensionType)) {
            return VanillaGeneratorType.VOID;
        }
        return getVanillaGeneratorType(dimensionType);
    }

    private static VanillaGeneratorType getVanillaGeneratorType(DimensionType dimensionType) {
        var dimensionId = DimensionId.fromDimensionType(dimensionType);
        return dimensionId == null ?
                DimensionId.OVERWORLD.getVanillaGeneratorType() :
                dimensionId.getVanillaGeneratorType();
    }

    private static RecipeData copyRecipeData(RecipeData recipe) {
        return switch (recipe) {
            case ShapedRecipeData shaped -> ShapedRecipeData.of(
                    shaped.getType(), shaped.getId(), shaped.getWidth(), shaped.getHeight(),
                    new ArrayList<>(shaped.getIngredients()), copyItems(shaped.getResults()), shaped.getUuid(),
                    shaped.getTag(), shaped.getPriority(), shaped.getNetId(), shaped.isAssumeSymetry(),
                    copyRequirement(shaped.getRequirement())
            );
            case ShapelessRecipeData shapeless -> ShapelessRecipeData.of(
                    shapeless.getType(), shapeless.getId(), new ArrayList<>(shapeless.getIngredients()),
                    copyItems(shapeless.getResults()), shapeless.getUuid(), shapeless.getTag(),
                    shapeless.getPriority(), shapeless.getNetId(), copyRequirement(shapeless.getRequirement())
            );
            case SmithingTransformRecipeData smithing -> SmithingTransformRecipeData.of(
                    smithing.getId(), smithing.getTemplate(), smithing.getBase(), smithing.getAddition(),
                    copyItemData(smithing.getResult()), smithing.getTag(), smithing.getNetId()
            );
            case SmithingTrimRecipeData smithing -> SmithingTrimRecipeData.of(
                    smithing.getId(), smithing.getBase(), smithing.getAddition(), smithing.getTemplate(),
                    smithing.getTag(), smithing.getNetId()
            );
            case MultiRecipeData multi -> MultiRecipeData.of(multi.getUuid(), multi.getNetId());
            case FurnaceRecipeData furnace -> FurnaceRecipeData.of(
                    furnace.getType(), furnace.getInputId(), furnace.getInputData(),
                    copyItemData(furnace.getResult()), furnace.getTag()
            );
            default -> throw new IllegalStateException("Unsupported cached recipe payload: " + recipe.getClass().getName());
        };
    }

    private static RecipeUnlockingRequirement copyRequirement(RecipeUnlockingRequirement requirement) {
        return new RecipeUnlockingRequirement(requirement.context(), new ArrayList<>(requirement.ingredients()));
    }

    private static List<ItemData> copyItems(List<ItemData> items) {
        return items.stream()
                .map(PacketEncoder_v766::copyItemData)
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    private static ItemData copyItemData(ItemData item) {
        return ItemData.builder()
                .definition(item.getDefinition())
                .damage(item.getDamage())
                .count(item.getCount())
                .tag(item.getTag())
                .canPlace(item.getCanPlace().clone())
                .canBreak(item.getCanBreak().clone())
                .blockingTicks(item.getBlockingTicks())
                .blockDefinition(item.getBlockDefinition())
                .usingNetId(item.isUsingNetId())
                .netId(item.getNetId())
                .build();
    }
}
