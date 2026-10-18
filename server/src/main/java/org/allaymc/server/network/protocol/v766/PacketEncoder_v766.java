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
 * {@code PacketEncoder_v766} converts Allay world, entity, inventory, and login
 * data into Bedrock packet payloads for the v766 encoder family. Later protocol
 * encoders inherit these mappings and override version differences. Each
 * implemented packet-producing operation constructs new packets, while protocol
 * definitions and selected nested payload values remain shared; a new packet
 * does not imply a deep copy of every referenced object.
 *
 * <p> {@code Chunk} responses advertise blob hashes only after
 * {@code ChunkCache} accepts the matching transaction; refused admission
 * selects inline data. {@code Entity} encodings distinguish runtime IDs from
 * persistent UUID low bits, and skin, scoreboard, and dialog updates expose
 * packet sequences whose order callers must preserve. Cached recipes and
 * creative items are rebuilt at mutable collection and item-array boundaries
 * while retaining their identities. {@code supportsSoundEvent} runs after
 * common sound selection so subclasses can enable newer events without
 * duplicating those mappings.
 */
public class PacketEncoder_v766 extends PacketEncoder {
    /**
     * Creates the baseline encoder using the supplied protocol payload bundle.
     * The superclass retains this nonnull bundle by reference, and every
     * inherited version uses it for item definitions and cached recipe data.
     */
    public PacketEncoder_v766(ProtocolData data) {
        super(data);
    }

    /**
     * Creates an item registry packet containing this protocol's definition
     * objects. The packet owns its item list, while individual definitions
     * retain their identities from the initialized protocol payload bundle.
     */
    @Override
    public ItemRegistryPacket encodeItemRegistry() {
        var packet = new ItemRegistryPacket();
        packet.getItems().addAll(getData().itemDefinitions());
        return packet;
    }

    /**
     * Creates creative groups and entries with fresh item payloads. Group and
     * entry IDs remain unchanged, while item restriction arrays are copied;
     * nested item tags and definition objects remain shared with the cached
     * data.
     */
    @Override
    public CreativeContentPacket encodeCreativeContent() {
        var packet = new CreativeContentPacket();
        // Rebuild item wrappers and restriction arrays for packet
        // customization; retaining group IDs and item network IDs preserves
        // their cached associations.
        getData().creativeGroups().stream()
                .map(group -> new CreativeItemGroup(group.category(), group.name(), copyItemData(group.icon())))
                .forEach(packet.getGroups()::add);
        getData().creativeItems().stream()
                .map(item -> new CreativeItemData(copyItemData(item.item()), item.netId(), item.groupId()))
                .forEach(packet.getContents()::add);
        return packet;
    }

    /**
     * Creates a replacement crafting table from this protocol's cached recipes.
     * Supported recipe structures and result items are copied, potion mix
     * objects remain shared, and the clean flag instructs the client to replace
     * its previous recipes.
     */
    @Override
    public CraftingDataPacket encodeCraftingData() {
        var packet = new CraftingDataPacket();
        // Packet recipe collections may be changed after encoding, so detach
        // their mutable containers before exposing any cached recipe payloads.
        getData().recipeTable().encodedRecipes().stream()
                .map(PacketEncoder_v766::copyRecipeData)
                .forEach(packet.getCraftingData()::add);
        packet.getPotionMixData().addAll(getData().recipeTable().potionMixes());
        packet.setCleanRecipes(true);
        return packet;
    }

    /**
     * Creates the entity identifier list from the current entity registry.
     */
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

    /**
     * Creates one property definition packet for each entity type declaring
     * properties. Types without properties are omitted, and the returned
     * mutable collection contains newly constructed packets with client
     * synchronization enabled.
     */
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

    /**
     * Creates biome definitions by converting the current registry in iteration
     * order.
     */
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

    /**
     * Creates dimension definitions for custom dimensions and altered vanilla
     * bounds. Vanilla dimensions retaining default bounds are omitted; maximum
     * heights become exclusive, and altered vanilla definitions advertise the
     * void generator.
     */
    @Override
    public DimensionDataPacket encodeDimensionData() {
        var packet = new DimensionDataPacket();
        Registries.DIMENSIONS.getContent().m1().values().stream()
                .filter(PacketEncoder_v766::shouldSendDimensionDefinition)
                // Dimension definitions use an exclusive upper height, unlike
                // the inclusive maximum stored by the server dimension type.
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

    /**
     * Creates pack download metadata using current server resource-pack
     * settings. Resource and data packs receive entries, script packs only
     * enable the scripting flag, and world templates are omitted from this
     * announcement.
     */
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

    /**
     * Creates the resource and behavior pack stack with this encoder's
     * experiment set. Acceptance is forced only when server packs are mandatory
     * and client resource packs are disallowed; script and world-template packs
     * are omitted.
     */
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

    /**
     * Creates transfer metadata for a nonnull pack and positive chunk size in
     * bytes. Invalid chunk sizes throw {@code IllegalArgumentException}, the
     * chunk count rounds upward, and the packet receives a copy of the pack
     * hash.
     */
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
        // Round upward so a partial final chunk remains downloadable.
        packet.setChunkCount((pack.getSize() + (long) maxChunkSize - 1) / maxChunkSize);
        packet.setCompressedPackSize(pack.getSize());
        packet.setHash(pack.getHash().clone());
        packet.setType(toNetworkResourcePackType(pack.getType()));
        return packet;
    }

    /**
     * Creates one resource-pack transfer chunk addressed by a zero-based index.
     * The pack must be nonnull, the chunk size must be positive, and the index
     * must select an existing chunk; invalid numeric inputs throw
     * {@code IllegalArgumentException}.
     *
     * <p> The same chunk size determines both the total count and the byte
     * offset, keeping the advertised index aligned with the selected bytes.
     * Progress reports the starting offset, not the end of this chunk. The
     * returned packet owns a reference-counted buffer wrapping the array
     * returned by the pack; callers transfer that ownership to the transport or
     * release the packet when discarding it.
     */
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

        // Use long arithmetic for count and offset so positive chunk sizes
        // cannot overflow intermediate addition or multiplication before bounds
        // validation.
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

    /**
     * Creates armor trim definitions from the internal pattern and material
     * registries.
     */
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

    /**
     * Creates the chunk publisher position and loading radius for a nonnull
     * player. The radius is converted from chunks to blocks, and the player
     * location is converted to an integer block position.
     */
    @Override
    public NetworkChunkPublisherUpdatePacket encodeChunkPublisher(EntityPlayer player) {
        Objects.requireNonNull(player, "player");
        var location = player.getLocation();
        var packet = new NetworkChunkPublisherUpdatePacket();
        packet.setPosition(Vector3i.from(location.x(), location.y(), location.z()));
        // Publisher radius uses blocks, whereas loading radius uses chunks.
        packet.setRadius(player.getChunkLoadingRadius() << 4);
        return packet;
    }

    /**
     * Creates a full or sub-chunk-request payload while holding the chunk's
     * read operation. A {@code null} cache disables blob caching; otherwise the
     * player ID and generation are passed to cache admission, whose refusal
     * falls back to uncached data.
     */
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
        // The read operation covers both terrain and height-dependent packet
        // construction, keeping section data accessible while the unsafe chunk
        // is encoded.
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

    /**
     * Creates the initial biome payload for subsequent sub-chunk requests. The
     * caller supplies a chunk already protected for reading, and an optional
     * cache receives the player ID and expected generation.
     *
     * <p> Only successful cache admission allows the packet to advertise the
     * biome hash. A stale generation or transaction limit therefore leaves no
     * hash-only response for data the cache declined to retain. Missing or
     * refused caching selects inline biome bytes instead. In either
     * representation, the request limit uses {@code findHighestNonAirSectionY}
     * relative to the dimension minimum and inherits that helper's
     * air-classification limitation.
     */
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
        // The request limit uses a section offset, not an absolute section Y.
        packet.setSubChunkLimit(findHighestNonAirSectionY(chunk) - dimensionType.minSectionY());

        if (cache != null) {
            byte[] biomesBlob = ChunkEncoder.encodeBiomesBlob(chunk);
            // Advertise the biome hash only after cache admission; otherwise a
            // stale generation or full transaction queue requires inline biome
            // data.
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

        // No accepted transaction backs blob references here, so send biome
        // bytes directly and leave the client cache path disabled.
        packet.setCachingEnabled(false);
        packet.setData(ChunkEncoder.writeToNetworkBiomeOnly(chunk));
        return packet;
    }

    /**
     * Returns the highest section above the dimension minimum whose
     * {@code isAirSection()} result is {@code false}, or the minimum when no
     * section qualifies. The predicate also returns {@code true} for a
     * one-entry non-air layer-zero palette, so this result can omit occupied
     * sections. Callers receive an absolute section coordinate for the request
     * limit, rather than a verified highest occupied block or a section count.
     */
    private static int findHighestNonAirSectionY(AllayUnsafeChunk chunk) {
        var dimensionType = chunk.getDimensionType();
        for (int highest = dimensionType.maxSectionY(); highest > dimensionType.minSectionY(); highest--) {
            if (!chunk.getSection(highest).isAirSection()) {
                return highest;
            }
        }
        // An empty chunk still needs a nonnegative request limit relative to
        // minSectionY, so use the dimension minimum as its fallback section.
        return dimensionType.minSectionY();
    }

    /**
     * Creates a complete chunk payload with optional blob caching. The caller
     * must already protect the unsafe chunk for reading, and cache admission
     * uses the supplied player identity and generation.
     *
     * <p> Section blobs are placed from the dimension minimum upward, followed
     * by the biome blob, so advertised hashes retain the payload order expected
     * by the client. Hashes are published only after cache admission succeeds.
     * Missing or refused caching instead produces ordinary chunk bytes;
     * successful caching still includes the uncached chunk tail such as block
     * entity data.
     */
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
            // Place biomes after every section so the client interprets each
            // advertised hash in the same order as full chunk encoding.
            byte[][] allBlobs = new byte[sectionCount + 1][];
            for (int i = 0; i < sectionCount; i++) {
                allBlobs[i] = ChunkEncoder.encodeSectionBlob(
                        chunk.getSection(dimensionType.minSectionY() + i)
                );
            }
            allBlobs[sectionCount] = ChunkEncoder.encodeBiomesBlob(chunk);

            // The hash sequence must match section order followed by biomes,
            // and cache admission must succeed before publishing that
            // reference-only representation.
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

        // Without an accepted cache transaction, use the full inline encoding
        // path rather than publishing hashes for blobs that admission did not
        // accept.
        packet.setCachingEnabled(false);
        packet.setData(ChunkEncoder.writeToNetwork(chunk));
        return packet;
    }

    /**
     * Creates a world-time packet using the supplied time-of-day tick value.
     */
    @Override
    public SetTimePacket encodeTime(int timeOfDay) {
        var packet = new SetTimePacket();
        packet.setTime(timeOfDay);
        return packet;
    }

    /**
     * Creates a gamerule update by converting the nonnull rule collection.
     */
    @Override
    public GameRulesChangedPacket encodeGameRules(GameRules gameRules) {
        Objects.requireNonNull(gameRules, "gameRules");
        var packet = new GameRulesChangedPacket();
        packet.getGameRules().addAll(NetworkHelper.toNetwork(gameRules.getGameRules()));
        return packet;
    }

    /**
     * Creates a single block update using the state hash as its definition ID.
     * Position and state must be nonnull, the supplied layer is retained, and
     * the network update flag is always enabled.
     */
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

    /**
     * Groups normal and extra-layer updates into packets for occupied chunk
     * sections. All inputs must be nonnull, and update heights must lie within
     * the supplied chunk dimension because section indexes are used without
     * bounds recovery.
     *
     * <p> Both input collections share one packet array indexed relative to the
     * dimension minimum. This keeps two layers at the same section address
     * together without mixing their entry lists. Returned packets follow
     * ascending section order, omit unused sections, and retain the update
     * order within each layer.
     */
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

    /**
     * Adds one layer of block changes to the shared section-indexed packet
     * array. Update heights must belong to the chunk dimension, since the
     * method indexes directly into the supplied array.
     *
     * <p> Subtracting the dimension's minimum section converts absolute section
     * coordinates into array positions without changing each packet's
     * world-space origin. Reusing an existing packet lets the second layer join
     * the first layer's section while preserving separate entry lists. Each
     * block definition continues to use the block-state hash, keeping identity
     * independent of registry enumeration order.
     */
    private static void encodeBlockUpdates(
            UpdateSubChunkBlocksPacket[] packets,
            Chunk chunk,
            Collection<BlockUpdate> blockUpdates,
            boolean extraLayer
    ) {
        for (var update : blockUpdates) {
            var sectionY = update.y() >> 4;
            // Sections can start below zero; subtracting the dimension minimum
            // preserves the correspondence between absolute update heights and
            // array slots.
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

    /**
     * Creates the packet for a chest-state or block-breaking action at the
     * given position. Inputs must be nonnull, unsupported action
     * implementations throw {@code IllegalStateException}, and the result is an
     * unmodifiable singleton packet list.
     */
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

    /**
     * Converts breaking seconds to the integer progress increment used per game
     * tick. Zero duration selects the maximum increment; other values divide
     * that maximum by twenty ticks per second and truncate the result.
     */
    private static int toNetworkBreakTime(double breakTime) {
        // Convert seconds to per-tick progress; zero requests instantaneous
        // completion.
        return breakTime == 0 ? 65535 : (int) (65535 / (breakTime * 20));
    }

    /**
     * Creates a request to stop one named sound or all sounds. A {@code null}
     * name selects the stop-all flag and an empty encoded name; an empty
     * nonnull name does not select that flag.
     */
    @Override
    public StopSoundPacket encodeStopSound(String soundName) {
        var packet = new StopSoundPacket();
        packet.setSoundName(Objects.requireNonNullElse(soundName, ""));
        packet.setStoppingAllSound(soundName == null);
        return packet;
    }

    /**
     * Creates the packet representation for a particle at a nonnull position.
     * Most particles use a level event; custom effects use their supplied
     * dimension and optional Molang data, while note particles use a block
     * event. Unsupported particle implementations throw
     * {@code IllegalArgumentException}.
     *
     * <p> Some event payloads combine multiple meanings into one integer.
     * Dragon-egg movement stores separate magnitudes and sign bits, and item
     * break effects combine runtime item identity with metadata. Callers must
     * provide values representable by those layouts, because this method does
     * not mask oversized inputs before combining them.
     */
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
            // Direction codes and positional offsets jointly place the shooting
            // effect on its selected face; changing only one misaligns its
            // presentation.
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
                // Magnitudes occupy three byte positions and signs occupy
                // separate high bits; callers must keep offsets small enough to
                // avoid field overlap.
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
                // Note particles address blocks, so fractional coordinates are
                // truncated toward zero.
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
                // Combine runtime item identity in the upper bits with item
                // metadata below it, matching the item-specific particle
                // payload layout.
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

    /**
     * Creates rain and thunder updates that together represent the selected
     * weather. The returned list contains the rain packet before the thunder
     * packet, including both stop events when {@code weather} is {@code CLEAR}.
     *
     * <p> Rain and thunder have separate client event channels, so encoding
     * only the active effect could leave an earlier thunderstorm or
     * precipitation state visible. Each invocation explicitly starts or stops
     * both channels. Active channels use the full event intensity, and the
     * packets carry zero positions because these events describe weather rather
     * than one localized effect.
     */
    @Override
    public Collection<LevelEventPacket> encodeWeather(Weather weather) {
        Objects.requireNonNull(weather, "weather");
        // Send both channels every time so changing from thunder to rain or
        // clear weather also stops effects no longer selected.
        var rainPacket = new LevelEventPacket();
        rainPacket.setPosition(Vector3f.ZERO);
        rainPacket.setType(LevelEvent.STOP_RAINING);
        if (weather != Weather.CLEAR) {
            rainPacket.setType(LevelEvent.START_RAINING);
            // Selected precipitation uses full event intensity without gradual
            // transitions.
            rainPacket.setData(65535);
        }

        var thunderPacket = new LevelEventPacket();
        thunderPacket.setPosition(Vector3f.ZERO);
        thunderPacket.setType(LevelEvent.STOP_THUNDERSTORM);
        if (weather == Weather.THUNDER) {
            thunderPacket.setType(LevelEvent.START_THUNDERSTORM);
            // Selected thunder uses the same full-intensity event scale.
            thunderPacket.setData(65535);
        }
        return List.of(rainPacket, thunderPacket);
    }

    /**
     * Creates block-entity synchronization from the entity's current position
     * and saved NBT.
     */
    @Override
    public BlockEntityDataPacket encodeBlockEntity(BlockEntity blockEntity) {
        Objects.requireNonNull(blockEntity, "blockEntity");
        return encodeBlockEntityData(blockEntity.getPosition(), blockEntity.saveNBT());
    }

    /**
     * Creates a lectern-open packet using the fixed container ID minus one. The
     * position must be nonnull, and this packet does not allocate or register a
     * server-side container session.
     */
    @Override
    public ContainerOpenPacket encodeLectern(Vector3ic position) {
        Objects.requireNonNull(position, "position");
        var packet = new ContainerOpenPacket();
        // Use the fixed editor identity rather than consuming a normal
        // container ID; this packet has no matching server-side container
        // allocation.
        packet.setId((byte) -1);
        packet.setType(ContainerType.LECTERN);
        packet.setBlockPosition(NetworkHelper.toNetwork(position));
        return packet;
    }

    /**
     * Creates a sign editor request for the selected side and block position.
     */
    @Override
    public OpenSignPacket encodeSignEditor(Vector3ic position, boolean frontSide) {
        Objects.requireNonNull(position, "position");
        var packet = new OpenSignPacket();
        packet.setPosition(NetworkHelper.toNetwork(position));
        packet.setFrontSide(frontSide);
        return packet;
    }

    /**
     * Creates a complete inventory-content packet for the supplied container
     * ID. Items are encoded from the container, and the otherwise unused
     * full-container name uses the baseline zero-valued slot type with no
     * dynamic ID.
     */
    @Override
    public InventoryContentPacket encodeContainerContents(Container container, int containerId) {
        Objects.requireNonNull(container, "container");
        var packet = new InventoryContentPacket();
        packet.setContainerId(containerId);
        // This packet addresses the inventory through containerId; encode the
        // unused full-container name as the zero slot type with no dynamic ID.
        packet.setContainerNameData(new FullContainerName(ContainerSlotType.ANVIL_INPUT, null));
        packet.setContents(encodeItemStacks(container.getItemStacks()));
        return packet;
    }

    /**
     * Creates a single-slot update using the container's network slot mapping.
     * The container must be nonnull and the slot must be valid for its
     * implementation; slot type and item payload are derived from that same
     * slot.
     */
    @Override
    public InventorySlotPacket encodeContainerSlot(Container container, int slot, int containerId) {
        Objects.requireNonNull(container, "container");
        var packet = new InventorySlotPacket();
        packet.setContainerId(containerId);
        // Container-local indexes are not always network indexes; derive both
        // the slot number and slot type from the same container mapping.
        packet.setSlot(ContainerActionProcessor.toNetworkSlotIndex(container, slot));
        packet.setContainerNameData(new FullContainerName(
                ContainerActionProcessor.getSlotType(container, slot),
                null
        ));
        packet.setItem(encodeItemStack(container.getItemStack(slot)));
        return packet;
    }

    /**
     * Creates a container-open packet with the registered network container
     * type. The container and position must be nonnull, and the caller supplies
     * the already selected byte-sized container ID without allocating it here.
     */
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

    /**
     * Creates a close request using the supplied container's registered network
     * type. The caller supplies its existing byte ID and initiation flag, and
     * this method does not change the server container lifecycle.
     */
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

    /**
     * Creates a container property update using the supplied property and
     * value. The container ID is narrowed to one byte without validation, so
     * callers must supply an ID representable by the protocol field.
     */
    @Override
    public ContainerSetDataPacket encodeContainerData(int containerId, int property, int value) {
        var packet = new ContainerSetDataPacket();
        // Only the low byte survives the window-ID conversion.
        packet.setWindowId((byte) containerId);
        packet.setProperty(property);
        packet.setValue(value);
        return packet;
    }

    /**
     * Creates the spawn packet appropriate to the entity's concrete kind.
     * Players, dropped items, paintings, and other entities use different
     * packet layouts, while runtime IDs and persistent UUID low bits fill
     * distinct identity fields.
     *
     * <p> General entities use the configured network position offset; player
     * spawn packets instead retain the unshifted location, and paintings use
     * their bounding-box center. Entities without physics advertise zero
     * motion, and dropped items without an item stack use air. Metadata and
     * properties are included only where the selected packet exposes those
     * fields.
     */
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
        // Entities without physics have no motion component to read, so their
        // spawn representation uses zero velocity while retaining position and
        // identity.
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
                // Player spawn uses the unshifted entity location even though
                // subsequent movement packets apply network offsets; retain
                // that packet-specific coordinate convention when sharing
                // entity helpers.
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
                        // An unset dropped-item stack still needs a
                        // serializable item payload; air preserves the spawn
                        // packet structure without inventing contents.
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
                // Paintings use their bounding-box center rather than the
                // general entity origin, preserving placement for motives with
                // different dimensions.
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

    /**
     * Creates an entity-removal packet addressed by the persistent UUID's low
     * bits.
     */
    @Override
    public RemoveEntityPacket encodeEntityRemove(Entity entity) {
        Objects.requireNonNull(entity, "entity");
        var packet = new RemoveEntityPacket();
        packet.setUniqueEntityId(entity.getUniqueId().getLeastSignificantBits());
        return packet;
    }

    /**
     * Creates the game-mode update appropriate to the packet recipient. A self
     * update carries only the local mode, while updates for other players
     * include the target player's runtime entity ID.
     */
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

    /**
     * Creates a self-player movement packet or an absolute remote-entity
     * movement packet. {@code Entity} and location must be nonnull, both
     * representations apply the entity network offset, and the teleport flag is
     * passed to the selected format.
     */
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

    /**
     * Creates a movement packet for the controlling player using the entity's
     * network offset. Teleports explicitly select teleport mode with an unknown
     * cause, while ordinary movement retains the packet's default mode.
     */
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

    /**
     * Creates an absolute movement packet for observers using the entity's
     * network offset. Ground state is included when the entity has physics, and
     * the supplied teleport flag marks the movement as teleported.
     */
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

    /**
     * Returns independent head yaw when the entity provides that component.
     * Entities without it use the supplied body yaw, keeping their encoded head
     * direction aligned with their body rotation.
     */
    private static double getHeadYaw(Entity entity, double yaw) {
        // Independent head rotation exists only on the head-yaw component;
        // other entities must align their encoded head with the supplied body
        // direction.
        return entity instanceof EntityHeadYawComponent headYawComponent ? headYawComponent.getHeadYaw() : yaw;
    }

    /**
     * Creates a velocity packet for the nonnull entity and motion vector.
     */
    @Override
    public SetEntityMotionPacket encodeEntityMotion(Entity entity, Vector3dc motion) {
        Objects.requireNonNull(entity, "entity");
        Objects.requireNonNull(motion, "motion");
        var packet = new SetEntityMotionPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        return packet;
    }

    /**
     * Builds metadata by applying generic, component, and concrete-type
     * contributions in order. The entity must be nonnull, and each call returns
     * a newly allocated metadata map rather than updating a previously sent
     * packet.
     *
     * <p> Later contributions may replace values written by earlier stages. In
     * particular, a baby component writes a half scale after generic scale has
     * been recorded, while type-specific flags and payloads augment the common
     * shape and naming information. Preserving this order keeps those more
     * specific representations authoritative wherever metadata keys overlap.
     */
    @Override
    public EntityDataMap encodeEntityMetadata(Entity entity) {
        Objects.requireNonNull(entity, "entity");
        var metadata = new EntityDataMap();
        // Apply broad defaults before component and concrete-type contributions
        // so specialized metadata, such as baby scale, wins when keys overlap.
        addGenericMetadata(entity, metadata);
        addComponentSpecificMetadata(entity, metadata);
        addTypeSpecificMetadata(entity, metadata);
        return metadata;
    }

    /**
     * Creates a complete entity-state update containing metadata and network
     * properties.
     */
    @Override
    public SetEntityDataPacket encodeEntityState(Entity entity) {
        Objects.requireNonNull(entity, "entity");
        var packet = new SetEntityDataPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMetadata(encodeEntityMetadata(entity));
        packet.setProperties(NetworkHelper.toNetworkProperties(entity));
        return packet;
    }

    /**
     * Adds collision dimensions, naming data, scale, and common entity flags to
     * the supplied metadata map. Item entities explicitly disable NPC metadata,
     * and entities without name tags leave the name-specific entries absent.
     */
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
        // Item entities use their dedicated spawn representation; disable NPC
        // metadata for them while retaining the baseline NPC flag on other
        // types.
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

    /**
     * Adds metadata for the optional components implemented by an entity.
     * Existing entries may be replaced, including generic scale for babies;
     * absent components leave their corresponding metadata keys untouched in
     * the supplied map.
     */
    private static void addComponentSpecificMetadata(Entity entity, EntityDataMap metadata) {
        if (entity instanceof EntityPhysicsComponent physicsComponent) {
            metadata.setFlag(EntityFlag.HAS_GRAVITY, physicsComponent.hasGravity());
        }
        if (entity instanceof EntityLivingComponent livingComponent) {
            metadata.setFlag(EntityFlag.ON_FIRE, livingComponent.isOnFire());
            metadata.setFlag(EntityFlag.BREATHING, livingComponent.canBreathe());
            // Air values retain tick units but narrow to signed shorts.
            metadata.put(EntityDataTypes.AIR_SUPPLY, (short) livingComponent.getAirSupplyTicks());
            metadata.put(EntityDataTypes.AIR_SUPPLY_MAX, (short) livingComponent.getAirSupplyMaxTicks());
            metadata.put(EntityDataTypes.VISIBLE_MOB_EFFECTS, encodeVisibleEffects(livingComponent.getEffects().values()));
            // Convert freeze ticks to a fraction without clamping overfull
            // values.
            metadata.put(
                    EntityDataTypes.FREEZING_EFFECT_STRENGTH,
                    livingComponent.getFreezeTicks() / (float) EntityLivingComponent.MAX_FREEZE_TICKS
            );
        }
        if (entity instanceof EntityPotionComponent potionComponent) {
            var potionType = potionComponent.getPotionType();
            if (potionType != null) {
                // Potion metadata reserves zero before the enum values, while
                // the custom-display encoding skips another entry after the
                // first four values.
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
                // Baby scale replaces, rather than multiplies, the generic
                // scale.
                metadata.put(EntityDataTypes.SCALE, 0.5f);
            }
        }
        if (entity instanceof EntityDyeableComponent dyeableComponent) {
            metadata.put(EntityDataTypes.COLOR, (byte) dyeableComponent.getColor().ordinal());
        }
    }

    /**
     * Adds metadata unique to supported concrete entity kinds. Missing
     * projectile owners use minus one, optional name or damage information is
     * omitted when unavailable, and unrecognized entity kinds add no further
     * entries.
     */
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
            // Only a returning trident exposes its shooter as owner here;
            // absent shooters and nonreturning tridents use the no-owner
            // sentinel.
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
                                // Flight stores whole seconds narrowed to a
                                // byte.
                                .putByte("Flight", (byte) (firework.getExistenceTicks() / 20))
                                .build()
                        )
                        .build();
                metadata.put(EntityDataTypes.DISPLAY_FIREWORK, fireworks);

                var attachedPlayer = firework.getAttachedPlayer();
                if (attachedPlayer != null) {
                    // The attachment field retains only the runtime ID's low
                    // byte.
                    metadata.put(EntityDataTypes.CUSTOM_DISPLAY, (byte) attachedPlayer.getRuntimeId());
                }
            }
            case EntityEnderCrystal enderCrystal ->
                    metadata.setFlag(EntityFlag.SHOW_BOTTOM, enderCrystal.isBaseVisible());
            case EntityAreaEffectCloud cloud -> {
                metadata.put(EntityDataTypes.AREA_EFFECT_CLOUD_RADIUS, cloud.getRadius());
                // The cloud advertises maximum duration and tiny positive
                // change values rather than its server lifetime, leaving visual
                // persistence separate from server removal.
                metadata.put(EntityDataTypes.AREA_EFFECT_CLOUD_DURATION, Integer.MAX_VALUE);
                metadata.put(EntityDataTypes.AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP, Float.MIN_VALUE);
                metadata.put(EntityDataTypes.AREA_EFFECT_CLOUD_CHANGE_RATE, Float.MIN_VALUE);
                metadata.put(EntityDataTypes.EFFECT_COLOR, cloud.getPotionType().getColor().getRGB());
                metadata.put(EntityDataTypes.EFFECT_AMBIENCE, (byte) 0);
            }
            case EntityLingeringPotion ignored -> metadata.setFlag(EntityFlag.LINGERING, true);
            // Owner and target are independent optional associations; encode
            // the no-entity sentinel for each missing relationship without
            // substituting the other identity.
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
                    // Expose the recent hurt animation for five ticks only.
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

    /**
     * Packs visible effect identities and ambient flags into one metadata
     * value. Invisible effects contribute no bits, and visible effects retain
     * the iteration order supplied by the collection.
     *
     * <p> Each appended effect shifts existing data by seven bits, stores its
     * ID above the ambient bit, and uses that low bit for ambience. A
     * {@code long} holds at most nine complete seven-bit entries, and an ID
     * must fit six nonnegative bits. This method validates neither limit:
     * excessive entries shift earlier information out of the result, while
     * oversized IDs can overlap adjacent fields.
     */
    private static long encodeVisibleEffects(Collection<EffectInstance> effects) {
        long visibleEffects = 0;
        for (var effect : effects) {
            if (effect.isVisible()) {
                // Append the effect ID and ambient flag together; preserving
                // seven-bit boundaries keeps each visible effect associated
                // with its own ambience bit.
                visibleEffects = (visibleEffects << 7)
                        | ((long) effect.getType().getId() << 1)
                        | (effect.isAmbient() ? 1 : 0);
            }
        }
        return visibleEffects;
    }

    /**
     * Creates an add, modify, or remove effect packet carrying the supplied
     * tick. {@code newEffect} supplies the payload whenever it is present;
     * otherwise {@code oldEffect} must supply the identity and ambient state
     * for removal.
     *
     * <p> An absent {@code oldEffect} selects addition, while the presence of
     * both values selects modification using the new effect's data. This method
     * does not compare the two effect types before selecting modification.
     * Callers must therefore pair the effects consistently when requesting a
     * change to one existing effect.
     */
    @Override
    public MobEffectPacket encodeMobEffect(
            Entity entity,
            EffectInstance newEffect,
            EffectInstance oldEffect,
            long tick
    ) {
        Objects.requireNonNull(entity, "entity");
        // Removal has no new effect payload, so retain the old effect identity
        // and ambient state when constructing its removal event.
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

    /**
     * Creates main-hand equipment using the dedicated entity-hand container
     * when present. Otherwise the entity must have a player inventory, whose
     * selected hand slot supplies both network slot fields and the item.
     */
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
            // Without a dedicated entity-hand container, use the selected
            // player inventory slot so the item and both slot identifiers stay
            // aligned.
            var inventory = entity.getContainer(ContainerTypes.INVENTORY);
            var handSlot = inventory.getHandSlot();
            packet.setItem(encodeItemStack(inventory.getItemInHand()));
            packet.setInventorySlot(handSlot);
            packet.setHotbarSlot(handSlot);
        }
        return packet;
    }

    /**
     * Creates offhand equipment using the fixed offhand container and slot
     * identifiers. The entity must be nonnull and provide an offhand container;
     * its current offhand item is encoded into a new packet.
     */
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

    /**
     * Creates an armor update from the entity's armor container. The entity
     * must provide that container, and the separate body slot is explicitly
     * encoded as air while the four armor slots retain their contents.
     */
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

    /**
     * Creates an animation request for one entity from the supplied animation
     * settings.
     */
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

    /**
     * Creates sleeping-player counts using the total for both population
     * fields.
     */
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

    /**
     * Creates a command-block editor request with the fixed container ID minus
     * one. The position must be nonnull, and opening this editor does not
     * allocate a normal container session here.
     */
    @Override
    public ContainerOpenPacket encodeCommandBlockEditor(Vector3ic position) {
        Objects.requireNonNull(position, "position");
        var packet = new ContainerOpenPacket();
        packet.setId((byte) -1);
        packet.setType(org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType.COMMAND_BLOCK);
        packet.setBlockPosition(NetworkHelper.toNetwork(position));
        return packet;
    }

    /**
     * Creates an enchantment-option packet by converting each supplied option
     * pair.
     */
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

    /**
     * Creates skin-update packets for an actual or simulated player entity.
     * {@code player} and its skin must be nonnull, and {@code trustSkin} is
     * copied into each relevant skin payload.
     *
     * <p> For an actual player, this method returns one
     * {@code PlayerSkinPacket}. For a simulated player, it returns a
     * player-list addition, skin update, and player-list removal, all keyed by
     * that entity's UUID. The temporary list entry supplies the simulated
     * player's identity and serialized skin without leaving it in the visible
     * player list. Callers must preserve the returned packet order when sending
     * that sequence.
     */
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

        // Simulated players need a temporary list identity around the skin
        // update; add and remove the same UUID in the returned sequence.
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

    /**
     * Creates the packets representing an entity action for its recipient. A
     * self-player arm swing and eating without an inventory produce an empty
     * list; unsupported action implementations throw
     * {@code IllegalStateException}.
     *
     * <p> Eating combines the held item's runtime ID and metadata into one
     * event integer, so the event continues to identify the displayed item. A
     * fishing bite returns three ordered events addressed to the same runtime
     * entity. Callers must preserve that sequence to transmit the complete bite
     * effect rather than selecting only one returned packet.
     */
    @Override
    public Collection<BedrockPacket> encodeEntityAction(Entity entity, EntityAction action, boolean self) {
        Objects.requireNonNull(entity, "entity");
        Objects.requireNonNull(action, "action");
        return switch (action) {
            case SimpleEntityAction.SWING_ARM -> {
                if (entity instanceof EntityPlayer) {
                    // The controlling player supplies its own arm swing, so
                    // suppress the echoed animation while observers still
                    // receive the entity's swing packet.
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
                // Eating needs an inventory item identity for its event
                // payload; without that source, omit the event rather than
                // fabricating an item.
                if (!(entity instanceof ContainerHolder holder)
                        || !holder.hasContainer(ContainerTypes.INVENTORY)) {
                    yield List.of();
                }
                var item = holder.getContainer(ContainerTypes.INVENTORY).getItemInHand();
                // Eating uses the same packed runtime item ID and metadata
                // layout as item particles, preserving which held item supplies
                // the visual.
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
            // One fishing bite expands into three events for the same runtime
            // entity; keep them together so callers preserve the complete
            // effect.
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

    /**
     * Creates a server-side emote packet for the specified player and emote
     * UUID. Both inputs must be nonnull, and silence adds the mute-chat flag
     * without changing the selected emote or player identity.
     */
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

    /**
     * Creates an entity event using runtime identity and event-specific integer
     * data.
     */
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

    /**
     * Creates an animation event using runtime identity and action-specific
     * float data.
     */
    private static AnimatePacket createAnimatePacket(Entity entity, AnimatePacket.Action action, float data) {
        var packet = new AnimatePacket();
        packet.setAction(action);
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setData(data);
        return packet;
    }

    /**
     * Creates the packet representation of a sound at the supplied position.
     * Named custom sounds and selected block or interface effects use dedicated
     * packet forms, while ordinary sound events carry identifiers or extra data
     * required by their category. Unsupported sound implementations and invalid
     * riptide levels throw {@code IllegalArgumentException}.
     *
     * <p> The relative-volume flag applies to ordinary level sound events.
     * After an event is selected, the version support hook can suppress it with
     * an empty result, allowing newer subclasses to enable additional events.
     * Direct play-sound and level-event returns bypass that hook and preserve
     * their own volume, pitch, or event representation.
     */
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
                // Use the fixed level-up payload instead of the ordinary
                // no-extra-data sentinel; this event has its own integer
                // parameter representation.
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
                // Pack instrument and pitch into separate byte positions so one
                // note event retains both its sound source and selected musical
                // pitch.
                packet.setExtraData((so.instrument().ordinal() << 8) | so.pitch());
            }
            case FallSound so -> {
                packet.setIdentifier("minecraft:player");
                // Falls above four blocks select the larger impact sound.
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
                // Insertion progress raises pitch linearly without clamping the
                // input.
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

        // A mapped event can still be unavailable in this version; omit it
        // instead of sending a numeric event the client cannot represent.
        return supportsSoundEvent(packet.getSound()) ? List.of(packet) : List.of();
    }

    /**
     * Returns whether the baseline permits the selected level sound event.
     * {@code RECORD_TEARS}, {@code RECORD_LAVA_CHICKEN},
     * {@code SINGLE_ITEM_SWAP}, and {@code MULTI_ITEM_SWAP} are excluded here;
     * subclasses enable them when their codec assigns the corresponding event
     * IDs.
     */
    protected boolean supportsSoundEvent(SoundEvent soundEvent) {
        // Later codec maps introduce these event IDs; omit their common
        // mappings here until a version-specific override explicitly enables
        // them.
        return switch (soundEvent) {
            case RECORD_TEARS, RECORD_LAVA_CHICKEN, SINGLE_ITEM_SWAP, MULTI_ITEM_SWAP -> false;
            default -> true;
        };
    }

    /**
     * Returns the equip sound for elytra or a recognized armor tier. Netherite
     * shares the diamond event; items without an armor tier other than elytra
     * produce a null-switch failure rather than a generic equip sound.
     */
    private static SoundEvent getEquipSound(ItemType<?> itemType) {
        if (itemType == ItemTypes.ELYTRA) {
            return SoundEvent.ARMOR_EQUIP_ELYTRA;
        }

        return switch (ItemHelper.getArmorTier(itemType)) {
            case LEATHER -> SoundEvent.ARMOR_EQUIP_LEATHER;
            case IRON -> SoundEvent.ARMOR_EQUIP_IRON;
            case CHAIN -> SoundEvent.ARMOR_EQUIP_CHAIN;
            case GOLD -> SoundEvent.ARMOR_EQUIP_GOLD;
            // Netherite reuses the diamond equip event in this baseline, so
            // newer armor remains audible without introducing a separate event
            // mapping.
            case DIAMOND, NETHERITE -> SoundEvent.ARMOR_EQUIP_DIAMOND;
        };
    }

    /**
     * Creates a boss-bar creation packet from the supplied bar state.
     */
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

    /**
     * Creates a boss-bar removal packet addressed by the supplied unique entity
     * ID.
     */
    @Override
    public BossEventPacket encodeBossBarRemoval(long bossEntityId) {
        var packet = new BossEventPacket();
        packet.setBossUniqueEntityId(bossEntityId);
        packet.setAction(BossEventPacket.Action.REMOVE);
        return packet;
    }

    /**
     * Creates a modal form request using the form's JSON serialization.
     */
    @Override
    public ModalFormRequestPacket encodeForm(int formId, Form form) {
        Objects.requireNonNull(form, "form");
        var packet = new ModalFormRequestPacket();
        packet.setFormId(formId);
        packet.setFormData(form.toJson());
        return packet;
    }

    /**
     * Creates a request to close the client's currently displayed forms.
     */
    @Override
    public ClientboundCloseFormPacket encodeCloseForms() {
        return new ClientboundCloseFormPacket();
    }

    /**
     * Creates the display-objective packet followed by the initial score
     * update. All inputs must be nonnull, callers must preserve that order, and
     * score objects remain shared inside a newly copied list.
     */
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
        // The objective announcement precedes scores that refer to it; retain
        // this order when sending both packets to the viewer.
        return List.of(objective, scorePacket);
    }

    /**
     * Creates a display-slot update with empty objective fields to clear the
     * slot. The slot must be nonnull, and clearing this display association
     * does not emit a separate objective-removal packet.
     */
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

    /**
     * Creates an objective-removal packet using the scoreboard's objective
     * identifier.
     */
    @Override
    public RemoveObjectivePacket encodeScoreboardRemoval(Scoreboard scoreboard) {
        Objects.requireNonNull(scoreboard, "scoreboard");
        var packet = new RemoveObjectivePacket();
        packet.setObjectiveId(scoreboard.getObjectiveName());
        return packet;
    }

    /**
     * Creates a score update for the required action and optional score entry.
     * A {@code null} score yields an empty packet entry list, while a supplied
     * score object is retained by reference.
     */
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

    /**
     * Creates an item cooldown packet using the category and duration in ticks.
     */
    @Override
    public PlayerStartItemCooldownPacket encodeCooldown(String category, int duration) {
        Objects.requireNonNull(category, "category");
        var packet = new PlayerStartItemCooldownPacket();
        packet.setItemCategory(category);
        packet.setCooldownDuration(duration);
        return packet;
    }

    /**
     * Creates a map update from the top-left 128-by-128 image region. The image
     * must be nonnull and at least that large in both dimensions; smaller
     * images fail during pixel access, while excess pixels are ignored.
     *
     * <p> Pixels are traversed row by row into a fresh color array. Each Java
     * ARGB color is reordered to the packet's ABGR layout without changing
     * alpha, preserving channel meaning across the image and network
     * representations. The map ID also identifies the tracked entry, and the
     * update starts at the zero origin.
     */
    @Override
    public ClientboundMapItemDataPacket encodeMapData(long mapId, BufferedImage image) {
        Objects.requireNonNull(image, "image");
        // Map textures use a fixed square pixel region without resampling.
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

    /**
     * Reorders one ARGB pixel into the ABGR layout consumed by map packets. The
     * result preserves all four eight-bit channel values, including alpha,
     * while exchanging the red and blue positions.
     *
     * <p> Each channel is masked before the shifted values are combined. Those
     * masks prevent Java's sign-extending right shifts from spreading a
     * channel's high bit into neighboring channels, so opaque pixels and
     * channels above 127 retain the same values after reordering.
     */
    private static int toAbgr(int argb) {
        // Exchange red and blue byte positions; masking preserves channel
        // values while the unchanged alpha byte retains the source
        // transparency.
        return ((argb >> 16) & 0xFF) |
               ((argb >> 8) & 0xFF) << 8 |
               (argb & 0xFF) << 16 |
               ((argb >> 24) & 0xFF) << 24;
    }

    /**
     * Creates death-screen information using the supplied nonnull cause
     * message.
     */
    @Override
    public DeathInfoPacket encodeDeathInfo(String message) {
        Objects.requireNonNull(message, "message");
        var packet = new DeathInfoPacket();
        packet.setCauseAttackName(message);
        return packet;
    }

    /**
     * Creates an item-charge completion event for the supplied runtime entity
     * ID.
     */
    @Override
    public EntityEventPacket encodeItemChargingFinished(long runtimeEntityId) {
        var packet = new EntityEventPacket();
        packet.setType(EntityEventType.FINISHED_CHARGING_ITEM);
        packet.setRuntimeEntityId(runtimeEntityId);
        return packet;
    }

    /**
     * Creates a single-attribute update retaining the supplied attribute object
     * by reference. The attribute must be nonnull, and any mutable state on
     * that object remains shared with the resulting packet.
     */
    @Override
    public UpdateAttributesPacket encodeAttribute(long runtimeEntityId, AttributeData attribute) {
        Objects.requireNonNull(attribute, "attribute");
        var packet = new UpdateAttributesPacket();
        packet.setRuntimeEntityId(runtimeEntityId);
        packet.getAttributes().add(attribute);
        return packet;
    }

    /**
     * Creates a text packet with the supplied sender XUID, message, and type.
     */
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

    /**
     * Creates a toast notification containing the nonnull title and content
     * strings.
     */
    @Override
    public ToastRequestPacket encodeToast(String title, String content) {
        Objects.requireNonNull(title, "title");
        Objects.requireNonNull(content, "content");
        var packet = new ToastRequestPacket();
        packet.setTitle(title);
        packet.setContent(content);
        return packet;
    }

    /**
     * Creates a title operation using the supplied nonnull packet type.
     * {@code null} text leaves the packet's text defaults unchanged, and
     * fade-in, stay, and fade-out tick values are applied only to timing
     * operations.
     */
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

    /**
     * Creates a positional or rotational camera-shake request with the supplied
     * intensity. Duration is expressed in seconds, the shake type must be
     * nonnull, and this method does not clamp either numeric input.
     */
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

    /**
     * Creates a camera-shake stop request with sentinel intensity and duration
     * values. The positional type accompanies the stop action, and both numeric
     * fields are set to minus one rather than a new shake magnitude.
     */
    @Override
    public CameraShakePacket encodeCameraShakeStop() {
        var packet = new CameraShakePacket();
        packet.setShakeAction(CameraShakeAction.STOP);
        packet.setShakeType(org.cloudburstmc.protocol.bedrock.data.camera.CameraShakeType.POSITIONAL);
        packet.setIntensity(-1);
        packet.setDuration(-1);
        return packet;
    }

    /**
     * Creates a fog-stack packet preserving the supplied collection's iteration
     * order.
     */
    @Override
    public PlayerFogPacket encodeFogStack(Collection<String> fogStack) {
        Objects.requireNonNull(fogStack, "fogStack");
        var packet = new PlayerFogPacket();
        packet.getFogStack().addAll(fogStack);
        return packet;
    }

    /**
     * Creates a dimension-change request with coordinates converted to network
     * floats. The destination dimension must be nonnull, and the vertical
     * position receives the player eye-height offset of 1.62 blocks.
     */
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
        // Dimension-change coordinates use player eye height rather than feet
        // height.
        packet.setPosition(Vector3f.from((float) x, (float) y + 1.62f, (float) z));
        return packet;
    }

    /**
     * Creates a dimension-change acknowledgement for the supplied runtime
     * entity ID.
     */
    @Override
    public PlayerActionPacket encodeDimensionChangeSuccess(long runtimeEntityId) {
        var packet = new PlayerActionPacket();
        packet.setAction(PlayerActionType.DIMENSION_CHANGE_SUCCESS);
        packet.setRuntimeEntityId(runtimeEntityId);
        packet.setBlockPosition(Vector3i.ZERO);
        packet.setResultPosition(Vector3i.ZERO);
        return packet;
    }

    /**
     * Creates a base ability layer with the supplied permission levels and
     * speeds. Every known ability is included in the layer's defined set, while
     * only the supplied abilities are enabled in its value set.
     */
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

    /**
     * Creates adventure settings from the supplied interaction and visibility
     * flags. Auto-jump is always enabled in this packet, independently of those
     * flags, and no player-specific settings are read during encoding.
     */
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

    /**
     * Creates an add or remove player-list packet from the supplied players.
     * Each player must have login data and a controlled entity even for
     * removal, because both packet actions populate the same complete entry
     * structure.
     *
     * <p> The entry UUID comes from login data, whereas the entity ID uses the
     * controlled entity UUID's low bits. Keeping these identity sources
     * distinct associates the list entry with the account and its current
     * entity. Skins use the requested trust flag, and the origin-name hash
     * supplies a deterministic RGB display color.
     */
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
            // The field carries the platform online ID, not the device name.
            entry.setPlatformChatId(player.getLoginData().getPlatformOnlineId());
            entry.setBuildPlatform(BuildPlatform.from(player.getLoginData().getDeviceInfo().device().getId()));
            entry.setSkin(SkinConvertor.toSerializedSkin(player.getLoginData().getSkin()));
            entry.setTrustedSkin(trustSkins);
            // Only the low twenty-four hash bits form the display RGB color.
            entry.setColor(new Color(player.getOriginName().hashCode() & 0xFFFFFF));
            packet.getEntries().add(entry);
        }
        return packet;
    }

    /**
     * Creates a transfer request using the nonnull address and supplied port.
     */
    @Override
    public TransferPacket encodeTransfer(String address, int port) {
        Objects.requireNonNull(address, "address");
        var packet = new TransferPacket();
        packet.setAddress(address);
        packet.setPort(port);
        return packet;
    }

    /**
     * Creates command availability using a fresh list of supplied command
     * objects. The collection must be nonnull, while the individual command
     * definitions remain shared with the caller rather than being copied.
     */
    @Override
    public AvailableCommandsPacket encodeCommands(Collection<CommandData> commands) {
        Objects.requireNonNull(commands, "commands");
        var packet = new AvailableCommandsPacket();
        packet.getCommands().addAll(commands);
        return packet;
    }

    /**
     * Creates HUD visibility updates covering all known HUD elements. Requested
     * elements are hidden, all others are reset, and empty reset or hide
     * packets are omitted from the returned mutable list.
     */
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

    /**
     * Creates the metadata update and dialogue-open packet for an entity-backed
     * dialog. {@code Dialog} and entity must be nonnull, and callers must send
     * the returned metadata packet before the dialogue packet.
     *
     * <p> Portrait settings are encoded into NPC metadata with the entity's
     * network vertical offset added to a new vector, leaving the supplied model
     * offset unchanged. The metadata packet uses runtime identity while the
     * dialogue uses persistent UUID low bits. This paired representation
     * prepares the portrait before opening the scene associated with that same
     * entity.
     */
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

        // The dialogue references an entity whose portrait offsets live in
        // metadata; send that preparation first so the opening scene uses the
        // selected model settings.
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

    /**
     * Creates a dialogue-close packet addressed by the entity's persistent UUID
     * low bits.
     */
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

    /**
     * Creates a cache-miss response with copied byte arrays for every supplied
     * blob hash. {@code blobs}, its keys, and its values must be nonnull, and
     * callers must transfer the returned packet to the transport or release it
     * when discarding it.
     *
     * <p> This method validates and clones the complete input map before
     * allocating reference-counted buffers. An invalid later entry therefore
     * cannot strand a buffer created for an earlier entry. Each buffer wraps
     * its cloned array, and releasing the returned
     * {@code ClientCacheMissResponsePacket} releases those buffers;
     * source-array edits cannot change the packet bytes.
     */
    @Override
    public ClientCacheMissResponsePacket encodeClientCacheMissResponse(Map<Long, byte[]> blobs) {
        Objects.requireNonNull(blobs, "blobs");
        // Validate and clone every blob before allocating reference-counted
        // buffers, so a bad later entry cannot strand buffers created for
        // earlier entries.
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

    /**
     * Creates block-entity data using the supplied nonnull position and NBT
     * map. The outer NBT map is rebuilt through its builder; this method does
     * not explicitly copy every nested value or array.
     */
    @Override
    public BlockEntityDataPacket encodeBlockEntityData(Vector3ic position, NbtMap data) {
        Objects.requireNonNull(position, "position");
        Objects.requireNonNull(data, "data");
        var packet = new BlockEntityDataPacket();
        packet.setBlockPosition(Vector3i.from(position.x(), position.y(), position.z()));
        packet.setData(data.toBuilder().build());
        return packet;
    }

    /**
     * Creates a chunk-radius acknowledgement with the supplied radius in
     * chunks.
     */
    @Override
    public ChunkRadiusUpdatedPacket encodeChunkRadiusUpdated(int radius) {
        var packet = new ChunkRadiusUpdatedPacket();
        packet.setRadius(radius);
        return packet;
    }

    /**
     * Creates a sub-chunk response with a copied list of payload entries.
     * {@code centerPosition} and {@code subChunks} must be nonnull, and a
     * {@code null} entry causes {@code IllegalArgumentException} before packet
     * construction.
     *
     * <p> The packet shares each {@code SubChunkData} object without retaining
     * it. Releasing the packet releases its entries and their buffers, so
     * callers transfer entry ownership with the packet and must retain entries
     * separately before using or releasing them independently. Copying the list
     * only separates membership changes; it does not duplicate payload objects
     * or their storage.
     */
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

    /**
     * Creates a container-close packet from its explicit network identity and
     * type. The type must be nonnull, and the server-initiated flag is retained
     * without changing any container state here.
     */
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

    /**
     * Creates an item-stack response packet containing the supplied response
     * objects. The collection must be nonnull and contain no {@code null}
     * entries; entries remain shared, and this baseline does not normalize
     * their nested containers.
     */
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

    /**
     * Creates a server-ready respawn packet at the supplied nonnull block
     * position.
     */
    @Override
    public RespawnPacket encodeRespawn(Vector3ic position) {
        Objects.requireNonNull(position, "position");
        var packet = new RespawnPacket();
        packet.setPosition(Vector3f.from(position.x(), position.y(), position.z()));
        packet.setState(RespawnPacket.State.SERVER_READY);
        return packet;
    }

    /**
     * Creates a server settings form response from the supplied ID and JSON.
     */
    @Override
    public ServerSettingsResponsePacket encodeServerSettingsResponse(int formId, String formData) {
        var packet = new ServerSettingsResponsePacket();
        packet.setFormId(formId);
        packet.setFormData(Objects.requireNonNull(formData, "formData"));
        return packet;
    }

    /**
     * Creates the initial game description from world settings, saved player
     * data, a destination dimension, and the player entity. All four inputs
     * must be nonnull, and the caller is responsible for supplying mutually
     * consistent world, dimension, and entity state.
     *
     * <p> The saved player game mode overrides the world mode when present,
     * while world defaults continue to populate level-wide settings.
     * {@code Entity} identity and location come from the live player, including
     * the eye-height offset used by the initial position. The seed is sent as
     * zero, server authority flags are enabled, and this protocol's cached
     * definitions and selected experiments are attached to align subsequent
     * packet decoding with the login description.
     */
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
        // A saved player mode takes precedence over the world default,
        // preserving personal game mode while the level still advertises
        // world-wide settings.
        packet.setPlayerGameType(GameType.from(playerData.getNbt().getInt(
                "PlayerGameMode",
                NetworkHelper.toNetwork(worldData.getGameMode()).ordinal()
        )));

        var location = player.getLocation();
        var worldSpawn = worldData.getSpawnPoint();
        packet.setDefaultSpawn(Vector3i.from(worldSpawn.x(), worldSpawn.y(), worldSpawn.z()));
        // Initial player position uses eye height above the entity location.
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
     * Creates the experiment set used by this encoder's pack-stack and login
     * packets. This baseline delegates to the common default set, while
     * subclasses can choose a different set for their protocol branch.
     */
    protected List<ExperimentData> createExperiments() {
        return createDefaultExperiments();
    }

    /**
     * Creates the three enabled baseline experiments with fresh payload
     * objects. The returned list is unmodifiable, and later encoders can call
     * this final helper to omit experiments introduced only by an intermediate
     * superclass.
     */
    protected final List<ExperimentData> createDefaultExperiments() {
        return List.of(
                new ExperimentData("data_driven_items", true),
                new ExperimentData("upcoming_creator_features", true),
                new ExperimentData("experimental_molang_features", true)
        );
    }

    /**
     * Creates one pack announcement entry using manifest capabilities and
     * caller-selected flags. {@code Pack} identity is used for both the UUID
     * and content identity, while optional subpack and download fields retain
     * their empty or {@code null} defaults.
     */
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

    /**
     * Converts each supported pack category to its network transfer
     * classification.
     */
    private static ResourcePackType toNetworkResourcePackType(org.allaymc.api.pack.Pack.Type type) {
        return switch (type) {
            case RESOURCES -> ResourcePackType.RESOURCES;
            case DATA -> ResourcePackType.DATA_ADD_ON;
            case WORLD_TEMPLATE -> ResourcePackType.WORLD_TEMPLATE;
            case SCRIPT -> ResourcePackType.ADDON;
        };
    }

    /**
     * Returns whether a dimension requires an explicit bounds definition.
     * Custom identifiers always qualify, while recognized vanilla identifiers
     * qualify only when their configured minimum or maximum differs from the
     * corresponding default.
     */
    private static boolean shouldSendDimensionDefinition(org.allaymc.api.world.dimension.DimensionType dimensionType) {
        var dimensionId = DimensionId.fromDimensionType(dimensionType);
        // Custom identities still need explicit definitions when their numeric
        // bounds match vanilla; only a recognized vanilla identity can omit one.
        return dimensionId == null || !dimensionId.isDefaultBounds(dimensionType);
    }

    /**
     * Returns the generator advertised alongside an explicit dimension
     * definition. Recognized vanilla dimensions with changed bounds use void,
     * while other dimensions use the ordinary vanilla-generator selection
     * shared with the login packet.
     */
    private static VanillaGeneratorType getDimensionDefinitionGeneratorType(org.allaymc.api.world.dimension.DimensionType dimensionType) {
        var dimensionId = DimensionId.fromDimensionType(dimensionType);
        if (dimensionId != null && !dimensionId.isDefaultBounds(dimensionType)) {
            // Changed vanilla bounds require their explicit definition to use
            // the void generator, while StartGame keeps the ordinary dimension
            // generator selection.
            return VanillaGeneratorType.VOID;
        }
        return getVanillaGeneratorType(dimensionType);
    }

    /**
     * Returns the vanilla generator associated with a recognized dimension
     * identifier. Custom dimension identifiers fall back to the overworld
     * generator; this helper does not inspect height bounds when selecting that
     * default.
     */
    private static VanillaGeneratorType getVanillaGeneratorType(DimensionType dimensionType) {
        var dimensionId = DimensionId.fromDimensionType(dimensionType);
        // Custom identifiers have no vanilla generator mapping; use the
        // overworld generator as the shared fallback for login and dimension
        // definition payloads.
        return dimensionId == null ?
                DimensionId.OVERWORLD.getVanillaGeneratorType() :
                dimensionId.getVanillaGeneratorType();
    }

    /**
     * Copies a supported cached recipe into a packet-owned recipe structure.
     * Shaped, shapeless, smithing, multi, and furnace payloads retain their
     * identifying fields and network IDs while copying the mutable collections
     * and result item structures available in each format.
     *
     * <p> Ingredient descriptor objects remain shared, as do item definitions
     * and nested item tags. Unlocking requirements receive new ingredient lists
     * without duplicating their elements. An unsupported recipe implementation
     * throws {@code IllegalStateException} rather than returning the cached
     * object, preventing an unknown mutable payload shape from silently
     * bypassing the copy boundary.
     */
    private static RecipeData copyRecipeData(RecipeData recipe) {
        // Reconstruct each supported payload shape instead of returning cached
        // instances; preserve identity fields while detaching lists and mutable
        // result-item storage.
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

    /**
     * Creates an unlocking requirement with the same context and a fresh
     * ingredient list. Ingredient descriptor objects remain shared, and both
     * the requirement and its source ingredient list must be nonnull.
     */
    private static RecipeUnlockingRequirement copyRequirement(RecipeUnlockingRequirement requirement) {
        return new RecipeUnlockingRequirement(requirement.context(), new ArrayList<>(requirement.ingredients()));
    }

    /**
     * Creates a mutable list of copied item payloads in source order. Item
     * objects and their restriction arrays are rebuilt, while definitions and
     * nested tags retain the sharing described by the item-copy helper.
     */
    private static List<ItemData> copyItems(List<ItemData> items) {
        return items.stream()
                .map(PacketEncoder_v766::copyItemData)
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    /**
     * Creates an item payload with copied placement and breaking restriction
     * arrays. Item and block definitions and the NBT tag remain shared, while
     * scalar properties, network identity, and stack count retain their
     * original values.
     */
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
