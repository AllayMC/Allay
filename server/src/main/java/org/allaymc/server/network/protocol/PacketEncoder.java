package org.allaymc.server.network.protocol;

import org.allaymc.api.block.action.BlockAction;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.container.Container;
import org.allaymc.api.dialog.Dialog;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantOption;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.player.*;
import org.allaymc.api.player.HudElement;
import org.allaymc.api.primitiveshape.PrimitiveShape;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldViewer.BlockUpdate;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.particle.Particle;
import org.allaymc.api.world.sound.Sound;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.player.ChunkCache;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermission;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.definition.DefinitionRegistry;
import org.cloudburstmc.protocol.bedrock.definition.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * {@code PacketEncoder} converts Allay domain data into packet objects for a
 * selected protocol implementation. The encoder retains the supplied protocol
 * data and constructs item and block lookup registries from it. Version
 * subclasses implement the operations they support; the base packet methods
 * return {@code null}, which callers must omit rather than transmit. Inherited
 * method implementations determine support, without any automatic lookup of
 * another protocol encoder.
 */
public abstract class PacketEncoder {
    /**
     * {@code data} holds the protocol payload bundle supplied at construction.
     * {@code getData()} exposes that same bundle, whose payload elements retain
     * their shared identities.
     */
    private final ProtocolData data;
    /**
     * Target item definition lookup.
     */
    private final DefinitionRegistry<ItemDefinition> itemDefinitions;
    /**
     * Target block definition lookup.
     */
    private final DefinitionRegistry<BlockDefinition> blockDefinitions;

    /**
     * Creates an encoder retaining the nonnull {@code data} instance. The
     * constructor builds item and block lookups from its definitions, and
     * duplicate runtime IDs fail construction before packet conversion can use
     * the encoder.
     */
    protected PacketEncoder(ProtocolData data) {
        this.data = Objects.requireNonNull(data, "data");
        this.itemDefinitions = SimpleDefinitionRegistry.<ItemDefinition>builder()
                .addAll(data.itemDefinitions())
                .build();
        this.blockDefinitions = SimpleDefinitionRegistry.<BlockDefinition>builder()
                .addAll(data.blockDefinitions())
                .build();
    }

    /**
     * Returns the protocol data instance retained when this encoder was
     * constructed. The record's list containers are unmodifiable, while their
     * payload elements and {@code recipeTable} remain shared with the supplied
     * bundle.
     */
    public final ProtocolData getData() {
        return data;
    }

    /**
     * Converts an item stack using this encoder's item and block definitions.
     * Missing referenced definitions fail conversion, keeping network item
     * identities tied to the protocol data rather than another version's
     * registries.
     */
    protected final ItemData encodeItemStack(ItemStack itemStack) {
        return NetworkHelper.toNetwork(itemStack, itemDefinitions, blockDefinitions, data.blockPalette(), data.itemPalette());
    }

    /**
     * Converts item stacks in list order using this encoder's definition
     * registries. The returned list contains converted entries; failure to
     * resolve any referenced item or block definition propagates from that
     * conversion.
     */
    protected final List<ItemData> encodeItemStacks(List<ItemStack> itemStacks) {
        return NetworkHelper.toNetwork(itemStacks, itemDefinitions, blockDefinitions, data.blockPalette(), data.itemPalette());
    }

    /**
     * Encodes the item definitions advertised to the client.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ItemRegistryPacket encodeItemRegistry() {
        return null;
    }

    /**
     * Encodes the creative inventory groups and entries.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public CreativeContentPacket encodeCreativeContent() {
        return null;
    }

    /**
     * Encodes the recipes and potion mixes available to the client.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public CraftingDataPacket encodeCraftingData() {
        return null;
    }

    /**
     * Encodes the entity identifiers available to the client.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public AvailableEntityIdentifiersPacket encodeAvailableEntityIdentifiers() {
        return null;
    }

    /**
     * Encodes the client-synchronized properties of registered entity types.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public Collection<SyncEntityPropertyPacket> encodeSyncEntityProperties() {
        return null;
    }

    /**
     * Encodes the registered biome definitions.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public BiomeDefinitionListPacket encodeBiomeDefinitions() {
        return null;
    }

    /**
     * Encodes custom or non-standard dimension definitions.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public DimensionDataPacket encodeDimensionData() {
        return null;
    }

    /**
     * Encodes the voxel shapes required by the target protocol.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public Collection<VoxelShapesPacket> encodeVoxelShapes() {
        return null;
    }

    /**
     * Encodes the jigsaw structure data that the target protocol requires
     * before {@code StartGamePacket}.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public JigsawStructureDataPacket encodeJigsawStructureData() {
        return null;
    }

    /**
     * Encodes metadata for the resource packs offered by the server.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ResourcePacksInfoPacket encodeResourcePacksInfo() {
        return null;
    }

    /**
     * Encodes the ordered stack of resource and behaviour packs.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ResourcePackStackPacket encodeResourcePackStack() {
        return null;
    }

    /**
     * Encodes the metadata needed to transfer a resource pack in chunks.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param pack pack to transfer
     *
     * @param maxChunkSize maximum number of bytes in each chunk; must be
     * positive
     *
     * @throws IllegalArgumentException if a supporting implementation rejects
     * the input because {@code maxChunkSize} is not positive
     */
    public ResourcePackDataInfoPacket encodeResourcePackDataInfo(
            Pack pack,
            int maxChunkSize
    ) {
        return null;
    }

    /**
     * Encodes one chunk of a resource pack.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param pack pack to transfer
     *
     * @param chunkIndex zero-based chunk index
     *
     * @param maxChunkSize maximum number of bytes in each chunk; must be
     * positive
     *
     * @throws IllegalArgumentException if a supporting implementation rejects
     * the input because the chunk size or index is invalid
     */
    public ResourcePackChunkDataPacket encodeResourcePackChunkData(
            Pack pack,
            int chunkIndex,
            int maxChunkSize
    ) {
        return null;
    }

    /**
     * Encodes the registered armour trim patterns and materials.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public TrimDataPacket encodeTrimData() {
        return null;
    }

    /**
     * Encodes the center and radius from which the player receives chunks.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public NetworkChunkPublisherUpdatePacket encodeChunkPublisher(EntityPlayer player) {
        return null;
    }

    /**
     * Encodes a chunk for a player, optionally using sub-chunk requests and the
     * client blob cache.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param chunk chunk to encode
     *
     * @param useSubChunkSending whether the client should request individual
     * sub-chunks
     *
     * @param cache client blob cache, or {@code null} to send the chunk data
     * directly
     *
     * @param playerId player that owns the cache transaction; may be
     * {@code null} only when {@code cache} is {@code null}
     *
     * @param cacheGeneration expected generation of the player's cache state
     */
    public LevelChunkPacket encodeLevelChunk(
            Chunk chunk,
            boolean useSubChunkSending,
            ChunkCache cache,
            UUID playerId,
            int cacheGeneration
    ) {
        return null;
    }

    /**
     * Encodes the current in-game time of day.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public SetTimePacket encodeTime(int timeOfDay) {
        return null;
    }

    /**
     * Encodes the complete set of current game rule values.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public GameRulesChangedPacket encodeGameRules(GameRules gameRules) {
        return null;
    }

    /**
     * Encodes a block update for one storage layer.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param position world position of the block
     *
     * @param layer storage layer containing the block
     *
     * @param blockState new block state
     */
    public UpdateBlockPacket encodeBlockUpdate(Vector3ic position, int layer, BlockState blockState) {
        return null;
    }

    /**
     * Encodes batched block updates grouped by sub-chunk.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param chunk chunk containing the updates
     *
     * @param blockUpdates updates for the standard storage layer
     *
     * @param extraBlockUpdates updates for the extra storage layer
     */
    public Collection<UpdateSubChunkBlocksPacket> encodeBlockUpdates(
            Chunk chunk,
            Collection<BlockUpdate> blockUpdates,
            Collection<BlockUpdate> extraBlockUpdates
    ) {
        return null;
    }

    /**
     * Encodes a block action at the supplied world position.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public Collection<BedrockPacket> encodeBlockAction(Vector3ic position, BlockAction action) {
        return null;
    }

    /**
     * Encodes a request to stop a sound, or all sounds when {@code soundName}
     * is {@code null}.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public StopSoundPacket encodeStopSound(String soundName) {
        return null;
    }

    /**
     * Encodes a particle at a world position.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param particle particle to encode
     *
     * @param position world position at which to spawn the particle
     *
     * @param dimensionId dimension used by particle packets that require one
     */
    public Collection<BedrockPacket> encodeParticle(
            Particle particle,
            Vector3dc position,
            int dimensionId
    ) {
        return null;
    }

    /**
     * Encodes the rain and thunder events for a weather state.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public Collection<LevelEventPacket> encodeWeather(Weather weather) {
        return null;
    }

    /**
     * Encodes the current position and persistent data of a block entity.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public BlockEntityDataPacket encodeBlockEntity(BlockEntity blockEntity) {
        return null;
    }

    /**
     * Encodes a request to open the lectern at a world position.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ContainerOpenPacket encodeLectern(Vector3ic position) {
        return null;
    }

    /**
     * Encodes a request to edit one side of a sign.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param position world position of the sign
     *
     * @param frontSide whether to edit the front side
     */
    public OpenSignPacket encodeSignEditor(Vector3ic position, boolean frontSide) {
        return null;
    }

    /**
     * Encodes all slots in a container for a network container ID.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public InventoryContentPacket encodeContainerContents(Container container, int containerId) {
        return null;
    }

    /**
     * Encodes one container slot after mapping it to the target protocol's slot
     * layout.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public InventorySlotPacket encodeContainerSlot(Container container, int slot, int containerId) {
        return null;
    }

    /**
     * Encodes a request to open a container at a world position.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param container container whose network type is advertised
     *
     * @param containerId network ID assigned to the open container
     *
     * @param position world position of the container
     */
    public ContainerOpenPacket encodeContainerOpen(
            Container container,
            byte containerId,
            Vector3ic position
    ) {
        return null;
    }

    /**
     * Encodes the closing of an open container.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param container container whose network type is advertised
     *
     * @param containerId network ID of the open container
     *
     * @param serverInitiated whether the server initiated the close
     */
    public ContainerClosePacket encodeContainerClose(
            Container container,
            byte containerId,
            boolean serverInitiated
    ) {
        return null;
    }

    /**
     * Encodes a numeric property update for an open container.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ContainerSetDataPacket encodeContainerData(int containerId, int property, int value) {
        return null;
    }

    /**
     * Encodes the packet appropriate for spawning the supplied entity type.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public BedrockPacket encodeEntitySpawn(Entity entity) {
        return null;
    }

    /**
     * Encodes the removal of an entity from the client world.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public RemoveEntityPacket encodeEntityRemove(Entity entity) {
        return null;
    }

    /**
     * Encodes a player's game mode for that player or an observing client.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param player player whose game mode changed
     *
     * @param self whether the packet is being sent to {@code player}
     */
    public BedrockPacket encodePlayerGameMode(EntityPlayer player, boolean self) {
        return null;
    }

    /**
     * Encodes an entity movement or teleport.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param entity entity that moved
     *
     * @param location new location
     *
     * @param teleporting whether the movement is a teleport
     *
     * @param self whether the packet is being sent to the moving entity
     */
    public BedrockPacket encodeEntityLocation(
            Entity entity,
            Location3dc location,
            boolean teleporting,
            boolean self
    ) {
        return null;
    }

    /**
     * Encodes a new motion vector for an entity.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public SetEntityMotionPacket encodeEntityMotion(Entity entity, Vector3dc motion) {
        return null;
    }

    /**
     * Converts the current entity state into protocol metadata.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public EntityDataMap encodeEntityMetadata(Entity entity) {
        return null;
    }

    /**
     * Encodes the current metadata and properties of an entity.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public SetEntityDataPacket encodeEntityState(Entity entity) {
        return null;
    }

    /**
     * Encodes the item held in an entity's main hand.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public <T extends Entity & EntityContainerHolderComponent> MobEquipmentPacket encodeEntityHand(T entity) {
        return null;
    }

    /**
     * Encodes the item held in an entity's offhand.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public <T extends Entity & EntityContainerHolderComponent> MobEquipmentPacket encodeEntityOffhand(T entity) {
        return null;
    }

    /**
     * Encodes the armour equipped by an entity.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public <T extends Entity & EntityContainerHolderComponent> MobArmorEquipmentPacket encodeEntityArmor(T entity) {
        return null;
    }

    /**
     * Encodes a named animation for an entity.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public AnimateEntityPacket encodeEntityAnimation(Entity entity, EntityAnimation animation) {
        return null;
    }

    /**
     * Encodes the number of sleeping players relative to the eligible total.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public LevelEventGenericPacket encodeSleepingIndicator(int sleepingCount, int totalCount) {
        return null;
    }

    /**
     * Encodes a request to open the command block editor at a world position.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ContainerOpenPacket encodeCommandBlockEditor(Vector3ic position) {
        return null;
    }

    /**
     * Encodes the enchantment choices currently offered to a player.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public PlayerEnchantOptionsPacket encodeEnchantOptions(
            Collection<Pair<Integer, EnchantOption>> enchantOptions
    ) {
        return null;
    }

    /**
     * Encodes the packets needed to update a player's skin.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param player player whose skin changed
     *
     * @param trustSkin whether the client should treat the skin as trusted
     */
    public Collection<BedrockPacket> encodePlayerSkin(EntityPlayer player, boolean trustSkin) {
        return null;
    }

    /**
     * Encodes an action performed by an entity.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param entity entity performing the action
     *
     * @param action action to encode
     *
     * @param self whether the packet is being sent to the acting entity
     */
    public Collection<BedrockPacket> encodeEntityAction(Entity entity, EntityAction action, boolean self) {
        return null;
    }

    /**
     * Encodes an emote performed by a player.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param player player performing the emote
     *
     * @param emoteId identifier of the emote
     *
     * @param silence whether to suppress the emote chat output
     */
    public EmotePacket encodePlayerEmote(EntityPlayer player, UUID emoteId, boolean silence) {
        return null;
    }

    /**
     * Encodes an entity effect addition, modification, or removal.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param entity entity whose effects changed
     *
     * @param newEffect new effect state, or {@code null} when removing the
     * effect
     *
     * @param oldEffect previous effect state, required when modifying or
     * removing the effect
     *
     * @param tick server tick associated with the change
     */
    public MobEffectPacket encodeMobEffect(
            Entity entity,
            EffectInstance newEffect,
            EffectInstance oldEffect,
            long tick
    ) {
        return null;
    }

    /**
     * Encodes a sound at a world position.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param sound sound to encode
     *
     * @param position world position of the sound
     *
     * @param relative whether volume should fall off with distance from
     * {@code position}
     */
    public Collection<BedrockPacket> encodeSound(Sound sound, Vector3dc position, boolean relative) {
        return null;
    }

    /**
     * Encodes the creation or current state of a boss bar.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public BossEventPacket encodeBossBar(long bossEntityId, BossBar bossBar) {
        return null;
    }

    /**
     * Encodes the removal of a boss bar identified by its backing entity ID.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public BossEventPacket encodeBossBarRemoval(long bossEntityId) {
        return null;
    }

    /**
     * Encodes a form request using its network ID and JSON representation.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ModalFormRequestPacket encodeForm(int formId, Form form) {
        return null;
    }

    /**
     * Encodes a request to close any form currently shown by the client.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ClientboundCloseFormPacket encodeCloseForms() {
        return null;
    }

    /**
     * Encodes a scoreboard objective, its display slot, and its current scores.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public Collection<BedrockPacket> encodeScoreboard(
            Scoreboard scoreboard,
            DisplaySlot slot,
            Collection<ScoreInfo> scores
    ) {
        return null;
    }

    /**
     * Encodes the clearing of a scoreboard display slot.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public SetDisplayObjectivePacket encodeScoreboardSlotRemoval(DisplaySlot slot) {
        return null;
    }

    /**
     * Encodes the removal of a scoreboard objective.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public RemoveObjectivePacket encodeScoreboardRemoval(Scoreboard scoreboard) {
        return null;
    }

    /**
     * Encodes one score update.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param action whether to set or remove the score
     *
     * @param score score to update, or {@code null} for an empty update
     */
    public SetScorePacket encodeScore(SetScorePacket.Action action, ScoreInfo score) {
        return null;
    }

    /**
     * Encodes an item cooldown for a client-defined category.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param category category shared by items using the cooldown
     *
     * @param duration cooldown duration in ticks
     */
    public PlayerStartItemCooldownPacket encodeCooldown(String category, int duration) {
        return null;
    }

    /**
     * Encodes the top-left 128 by 128 pixels of an image as map data.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ClientboundMapItemDataPacket encodeMapData(long mapId, BufferedImage image) {
        return null;
    }

    /**
     * Encodes the localized message shown on the death screen.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public DeathInfoPacket encodeDeathInfo(String message) {
        return null;
    }

    /**
     * Encodes notification that an entity has finished charging its held item.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public EntityEventPacket encodeItemChargingFinished(long runtimeEntityId) {
        return null;
    }

    /**
     * Encodes one current attribute value for an entity.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public UpdateAttributesPacket encodeAttribute(long runtimeEntityId, AttributeData attribute) {
        return null;
    }

    /**
     * Encodes a text message with its sender identity and presentation type.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param xuid Xbox user ID associated with the sender
     *
     * @param message message body
     *
     * @param type client presentation type
     */
    public TextPacket encodeText(String xuid, String message, TextPacket.Type type) {
        return null;
    }

    /**
     * Encodes a toast notification with a title and body.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ToastRequestPacket encodeToast(String title, String content) {
        return null;
    }

    /**
     * Encodes a title command and, for {@link SetTitlePacket.Type#TIMES}, its
     * timing values.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param type title command to issue
     *
     * @param text displayed text, or {@code null} for commands without text
     *
     * @param fadeInTime fade-in duration in ticks
     *
     * @param stayTime display duration in ticks
     *
     * @param fadeOutTime fade-out duration in ticks
     */
    public SetTitlePacket encodeTitle(
            SetTitlePacket.Type type,
            String text,
            int fadeInTime,
            int stayTime,
            int fadeOutTime
    ) {
        return null;
    }

    /**
     * Encodes a camera shake.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param shakeType positional or rotational shake type
     *
     * @param intensity shake intensity
     *
     * @param duration shake duration in seconds
     */
    public CameraShakePacket encodeCameraShake(
            CameraShakeType shakeType,
            float intensity,
            float duration
    ) {
        return null;
    }

    /**
     * Encodes a request to stop all active camera shakes.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public CameraShakePacket encodeCameraShakeStop() {
        return null;
    }

    /**
     * Encodes the client's complete ordered fog stack.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public PlayerFogPacket encodeFogStack(Collection<String> fogStack) {
        return null;
    }

    /**
     * Encodes the start of a player dimension change.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param dimensionType target dimension type
     *
     * @param x target x coordinate
     *
     * @param y target feet y coordinate
     *
     * @param z target z coordinate
     */
    public ChangeDimensionPacket encodeDimensionChange(
            DimensionType dimensionType,
            double x,
            double y,
            double z
    ) {
        return null;
    }

    /**
     * Encodes the acknowledgement that an entity completed a dimension change.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public PlayerActionPacket encodeDimensionChangeSuccess(long runtimeEntityId) {
        return null;
    }

    /**
     * Encodes a player's permissions, enabled abilities, and movement speeds.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param uniqueEntityId unique ID of the player entity
     *
     * @param commandPermission command permission level
     *
     * @param playerPermission player permission level
     *
     * @param abilities enabled abilities
     *
     * @param walkSpeed horizontal walking speed
     *
     * @param flySpeed horizontal flying speed
     *
     * @param verticalFlySpeed vertical flying speed
     */
    public UpdateAbilitiesPacket encodePlayerAbilities(
            long uniqueEntityId,
            CommandPermission commandPermission,
            PlayerPermission playerPermission,
            Collection<Ability> abilities,
            float walkSpeed,
            float flySpeed,
            float verticalFlySpeed
    ) {
        return null;
    }

    /**
     * Encodes the interaction and world restrictions applied to a player.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public UpdateAdventureSettingsPacket encodeAdventureSettings(
            boolean noPlayerVersusMob,
            boolean noMobVersusPlayer,
            boolean showNameTags,
            boolean immutableWorld
    ) {
        return null;
    }

    /**
     * Encodes additions to or removals from the client player list.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param players players affected by the update
     *
     * @param add whether to add rather than remove the players
     *
     * @param trustSkins whether the client should treat their skins as trusted
     */
    public PlayerListPacket encodePlayerList(
            Collection<? extends Player> players,
            boolean add,
            boolean trustSkins
    ) {
        return null;
    }

    /**
     * Encodes a transfer to another server address and port.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public TransferPacket encodeTransfer(String address, int port) {
        return null;
    }

    /**
     * Encodes the commands currently available to a player.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public AvailableCommandsPacket encodeCommands(Collection<CommandData> commands) {
        return null;
    }

    /**
     * Encodes the visibility of every HUD element from the supplied hidden set.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public Collection<SetHudPacket> encodeHudElements(Collection<HudElement> hiddenElements) {
        return null;
    }

    /**
     * Encodes the packets needed to present a dialog using an entity as its
     * model.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public Collection<BedrockPacket> encodeDialog(Dialog dialog, Entity entity) {
        return null;
    }

    /**
     * Encodes the closing of the dialog associated with an entity.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public NpcDialoguePacket encodeDialogClose(Entity entity) {
        return null;
    }

    /**
     * Encodes primitive shapes to add or replace in one dimension.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param primitiveShapes shapes to encode
     *
     * @param dimensionId network ID of the dimension containing the shapes
     */
    public Collection<PrimitiveShapesPacket> encodePrimitiveShapes(
            Collection<? extends PrimitiveShape> primitiveShapes,
            int dimensionId
    ) {
        return null;
    }

    /**
     * Encodes removal notices for primitive shapes.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public Collection<PrimitiveShapesPacket> encodePrimitiveShapeRemovals(
            Collection<? extends PrimitiveShape> primitiveShapes
    ) {
        return null;
    }

    /**
     * Encodes protocol-specific confirmation that a player's skin is valid.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public Collection<ConfirmSkinPacket> encodeSkinConfirmation(EntityPlayer player, Skin skin) {
        return null;
    }

    /**
     * Encodes replacement of a data store property with a snapshot of
     * {@code value}.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @throws IllegalArgumentException if a supporting implementation rejects
     * the input because {@code value} cannot be represented by the protocol
     */
    public ClientboundDataStorePacket encodeDataStoreChange(
            String dataStoreName,
            String propertyName,
            Object value
    ) {
        return null;
    }

    /**
     * Encodes the same data store value update for each supplied property path.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @throws IllegalArgumentException if a supporting implementation rejects
     * the input because {@code value} cannot be represented by the protocol
     */
    public ClientboundDataStorePacket encodeDataStoreUpdates(
            String dataStoreName,
            String propertyName,
            Collection<String> paths,
            Object value
    ) {
        return null;
    }

    /**
     * Encodes a request to show a data-driven UI screen with an associated form
     * ID.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ClientboundDataDrivenUIShowScreenPacket encodeDataDrivenUIShowScreen(
            String screenId,
            int formId
    ) {
        return null;
    }

    /**
     * Encodes a request to close a data-driven UI screen.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param formId form to close, or {@code null} when no form ID applies
     */
    public ClientboundDataDrivenUICloseScreenPacket encodeDataDrivenUICloseScreen(Integer formId) {
        return null;
    }

    /**
     * Encodes client-cache blobs by hash, copying each byte array into
     * packet-owned storage.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ClientCacheMissResponsePacket encodeClientCacheMissResponse(Map<Long, byte[]> blobs) {
        return null;
    }

    /**
     * Encodes block entity NBT at a world position using a snapshot of the
     * supplied data.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public BlockEntityDataPacket encodeBlockEntityData(Vector3ic position, NbtMap data) {
        return null;
    }

    /**
     * Encodes the chunk radius accepted by the server, measured in chunks.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ChunkRadiusUpdatedPacket encodeChunkRadiusUpdated(int radius) {
        return null;
    }

    /**
     * Encodes responses to a sub-chunk request. A supporting implementation
     * copies collection membership while transferring the supplied entries and
     * their buffers to the resulting packet; callers must retain entries
     * separately before independent use or release.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param dimension network ID of the requested dimension
     *
     * @param centerPosition center position from the sub-chunk request
     *
     * @param cacheEnabled whether response payloads reference the client blob
     * cache
     *
     * @param subChunks response entries transferred to the resulting packet
     *
     * @throws IllegalArgumentException if a supporting implementation rejects
     * {@code subChunks} because it contains {@code null}
     */
    public SubChunkPacket encodeSubChunk(
            int dimension,
            Vector3i centerPosition,
            boolean cacheEnabled,
            Collection<SubChunkData> subChunks
    ) {
        return null;
    }

    /**
     * Encodes a low-level container close packet when the network type is
     * already known.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param id network ID of the open container
     *
     * @param type network container type
     *
     * @param serverInitiated whether the server initiated the close
     */
    public ContainerClosePacket encodeContainerClose(
            byte id,
            ContainerType type,
            boolean serverInitiated
    ) {
        return null;
    }

    /**
     * Encodes item stack request results, copying the response collection into
     * the packet.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ItemStackResponsePacket encodeItemStackResponse(Collection<ItemStackResponse> responses) {
        return null;
    }

    /**
     * Encodes notification that the server is ready to respawn the player at a
     * position.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public RespawnPacket encodeRespawn(Vector3ic position) {
        return null;
    }

    /**
     * Encodes JSON form data returned for a server settings request.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     */
    public ServerSettingsResponsePacket encodeServerSettingsResponse(int formId, String formData) {
        return null;
    }

    /**
     * Encodes the initial world, registry, and player state sent when gameplay
     * starts.
     *
     * <p> The base implementation returns {@code null}, indicating that this
     * operation has no encoding. Callers must omit a {@code null} result
     * instead of submitting it to the transport.
     *
     * @param spawnWorld world providing the initial world settings
     *
     * @param playerData persisted data for the joining player
     *
     * @param dimension dimension in which the player starts
     *
     * @param player joining player entity
     */
    public StartGamePacket encodeStartGame(
            World spawnWorld,
            PlayerData playerData,
            Dimension dimension,
            EntityPlayer player
    ) {
        return null;
    }
}
