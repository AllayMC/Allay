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
 * Stateless conversion from Allay domain data to packets accepted by one protocol codec.
 * Unsupported operations return {@code null}; callers must treat that as an empty encoding result.
 */
public abstract class PacketEncoder {
    private final ProtocolData data;
    private final DefinitionRegistry<ItemDefinition> itemDefinitions;
    private final DefinitionRegistry<BlockDefinition> blockDefinitions;

    protected PacketEncoder(ProtocolData data) {
        this.data = Objects.requireNonNull(data, "data");
        this.itemDefinitions = SimpleDefinitionRegistry.<ItemDefinition>builder()
                .addAll(data.itemDefinitions())
                .build();
        this.blockDefinitions = SimpleDefinitionRegistry.<BlockDefinition>builder()
                .addAll(data.blockDefinitions())
                .build();
    }

    public final ProtocolData getData() {
        return data;
    }

    protected final ItemData encodeItemStack(ItemStack itemStack) {
        return NetworkHelper.toNetwork(itemStack, itemDefinitions, blockDefinitions);
    }

    protected final List<ItemData> encodeItemStacks(List<ItemStack> itemStacks) {
        return NetworkHelper.toNetwork(itemStacks, itemDefinitions, blockDefinitions);
    }

    public ItemRegistryPacket encodeItemRegistry() {
        return null;
    }

    public CreativeContentPacket encodeCreativeContent() {
        return null;
    }

    public CraftingDataPacket encodeCraftingData() {
        return null;
    }

    public AvailableEntityIdentifiersPacket encodeAvailableEntityIdentifiers() {
        return null;
    }

    public Collection<SyncEntityPropertyPacket> encodeSyncEntityProperties() {
        return null;
    }

    public BiomeDefinitionListPacket encodeBiomeDefinitions() {
        return null;
    }

    public DimensionDataPacket encodeDimensionData() {
        return null;
    }

    public Collection<VoxelShapesPacket> encodeVoxelShapes() {
        return null;
    }

    public ResourcePacksInfoPacket encodeResourcePacksInfo() {
        return null;
    }

    public ResourcePackStackPacket encodeResourcePackStack() {
        return null;
    }

    public ResourcePackDataInfoPacket encodeResourcePackDataInfo(
            Pack pack,
            int maxChunkSize
    ) {
        return null;
    }

    public ResourcePackChunkDataPacket encodeResourcePackChunkData(
            Pack pack,
            int chunkIndex,
            int maxChunkSize
    ) {
        return null;
    }

    public TrimDataPacket encodeTrimData() {
        return null;
    }

    public NetworkChunkPublisherUpdatePacket encodeChunkPublisher(EntityPlayer player) {
        return null;
    }

    public LevelChunkPacket encodeLevelChunk(
            Chunk chunk,
            boolean useSubChunkSending,
            ChunkCache cache,
            UUID playerId,
            int cacheGeneration
    ) {
        return null;
    }

    public SetTimePacket encodeTime(int timeOfDay) {
        return null;
    }

    public GameRulesChangedPacket encodeGameRules(GameRules gameRules) {
        return null;
    }

    public UpdateBlockPacket encodeBlockUpdate(Vector3ic position, int layer, BlockState blockState) {
        return null;
    }

    public Collection<UpdateSubChunkBlocksPacket> encodeBlockUpdates(
            Chunk chunk,
            Collection<BlockUpdate> blockUpdates,
            Collection<BlockUpdate> extraBlockUpdates
    ) {
        return null;
    }

    public Collection<BedrockPacket> encodeBlockAction(Vector3ic position, BlockAction action) {
        return null;
    }

    public StopSoundPacket encodeStopSound(String soundName) {
        return null;
    }

    public Collection<BedrockPacket> encodeParticle(
            Particle particle,
            Vector3dc position,
            int dimensionId
    ) {
        return null;
    }

    public Collection<LevelEventPacket> encodeWeather(Weather weather) {
        return null;
    }

    public BlockEntityDataPacket encodeBlockEntity(BlockEntity blockEntity) {
        return null;
    }

    public ContainerOpenPacket encodeLectern(Vector3ic position) {
        return null;
    }

    public OpenSignPacket encodeSignEditor(Vector3ic position, boolean frontSide) {
        return null;
    }

    public InventoryContentPacket encodeContainerContents(Container container, int containerId) {
        return null;
    }

    public InventorySlotPacket encodeContainerSlot(Container container, int slot, int containerId) {
        return null;
    }

    public ContainerOpenPacket encodeContainerOpen(
            Container container,
            byte containerId,
            Vector3ic position
    ) {
        return null;
    }

    public ContainerClosePacket encodeContainerClose(
            Container container,
            byte containerId,
            boolean serverInitiated
    ) {
        return null;
    }

    public ContainerSetDataPacket encodeContainerData(int containerId, int property, int value) {
        return null;
    }

    public BedrockPacket encodeEntitySpawn(Entity entity) {
        return null;
    }

    public RemoveEntityPacket encodeEntityRemove(Entity entity) {
        return null;
    }

    public BedrockPacket encodePlayerGameMode(EntityPlayer player, boolean self) {
        return null;
    }

    public BedrockPacket encodeEntityLocation(
            Entity entity,
            Location3dc location,
            boolean teleporting,
            boolean self
    ) {
        return null;
    }

    public SetEntityMotionPacket encodeEntityMotion(Entity entity, Vector3dc motion) {
        return null;
    }

    public EntityDataMap encodeEntityMetadata(Entity entity) {
        return null;
    }

    public SetEntityDataPacket encodeEntityState(Entity entity) {
        return null;
    }

    public <T extends Entity & EntityContainerHolderComponent> MobEquipmentPacket encodeEntityHand(T entity) {
        return null;
    }

    public <T extends Entity & EntityContainerHolderComponent> MobEquipmentPacket encodeEntityOffhand(T entity) {
        return null;
    }

    public <T extends Entity & EntityContainerHolderComponent> MobArmorEquipmentPacket encodeEntityArmor(T entity) {
        return null;
    }

    public AnimateEntityPacket encodeEntityAnimation(Entity entity, EntityAnimation animation) {
        return null;
    }

    public LevelEventGenericPacket encodeSleepingIndicator(int sleepingCount, int totalCount) {
        return null;
    }

    public ContainerOpenPacket encodeCommandBlockEditor(Vector3ic position) {
        return null;
    }

    public PlayerEnchantOptionsPacket encodeEnchantOptions(
            Collection<Pair<Integer, EnchantOption>> enchantOptions
    ) {
        return null;
    }

    public Collection<BedrockPacket> encodePlayerSkin(EntityPlayer player, boolean trustSkin) {
        return null;
    }

    public Collection<BedrockPacket> encodeEntityAction(Entity entity, EntityAction action, boolean self) {
        return null;
    }

    public EmotePacket encodePlayerEmote(EntityPlayer player, UUID emoteId, boolean silence) {
        return null;
    }

    public MobEffectPacket encodeMobEffect(
            Entity entity,
            EffectInstance newEffect,
            EffectInstance oldEffect,
            long tick
    ) {
        return null;
    }

    public Collection<BedrockPacket> encodeSound(Sound sound, Vector3dc position, boolean relative) {
        return null;
    }

    public BossEventPacket encodeBossBar(long bossEntityId, BossBar bossBar) {
        return null;
    }

    public BossEventPacket encodeBossBarRemoval(long bossEntityId) {
        return null;
    }

    public ModalFormRequestPacket encodeForm(int formId, Form form) {
        return null;
    }

    public ClientboundCloseFormPacket encodeCloseForms() {
        return null;
    }

    public Collection<BedrockPacket> encodeScoreboard(
            Scoreboard scoreboard,
            DisplaySlot slot,
            Collection<ScoreInfo> scores
    ) {
        return null;
    }

    public SetDisplayObjectivePacket encodeScoreboardSlotRemoval(DisplaySlot slot) {
        return null;
    }

    public RemoveObjectivePacket encodeScoreboardRemoval(Scoreboard scoreboard) {
        return null;
    }

    public SetScorePacket encodeScore(SetScorePacket.Action action, ScoreInfo score) {
        return null;
    }

    public PlayerStartItemCooldownPacket encodeCooldown(String category, int duration) {
        return null;
    }

    public ClientboundMapItemDataPacket encodeMapData(long mapId, BufferedImage image) {
        return null;
    }

    public DeathInfoPacket encodeDeathInfo(String message) {
        return null;
    }

    public EntityEventPacket encodeItemChargingFinished(long runtimeEntityId) {
        return null;
    }

    public UpdateAttributesPacket encodeAttribute(long runtimeEntityId, AttributeData attribute) {
        return null;
    }

    public TextPacket encodeText(String xuid, String message, TextPacket.Type type) {
        return null;
    }

    public ToastRequestPacket encodeToast(String title, String content) {
        return null;
    }

    public SetTitlePacket encodeTitle(
            SetTitlePacket.Type type,
            String text,
            int fadeInTime,
            int stayTime,
            int fadeOutTime
    ) {
        return null;
    }

    public CameraShakePacket encodeCameraShake(
            CameraShakeType shakeType,
            float intensity,
            float duration
    ) {
        return null;
    }

    public CameraShakePacket encodeCameraShakeStop() {
        return null;
    }

    public PlayerFogPacket encodeFogStack(Collection<String> fogStack) {
        return null;
    }

    public ChangeDimensionPacket encodeDimensionChange(
            DimensionType dimensionType,
            double x,
            double y,
            double z
    ) {
        return null;
    }

    public PlayerActionPacket encodeDimensionChangeSuccess(long runtimeEntityId) {
        return null;
    }

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

    public UpdateAdventureSettingsPacket encodeAdventureSettings(
            boolean noPlayerVersusMob,
            boolean noMobVersusPlayer,
            boolean showNameTags,
            boolean immutableWorld
    ) {
        return null;
    }

    public PlayerListPacket encodePlayerList(
            Collection<? extends Player> players,
            boolean add,
            boolean trustSkins
    ) {
        return null;
    }

    public TransferPacket encodeTransfer(String address, int port) {
        return null;
    }

    public AvailableCommandsPacket encodeCommands(Collection<CommandData> commands) {
        return null;
    }

    public Collection<SetHudPacket> encodeHudElements(Collection<HudElement> hiddenElements) {
        return null;
    }

    public Collection<BedrockPacket> encodeDialog(Dialog dialog, Entity entity) {
        return null;
    }

    public NpcDialoguePacket encodeDialogClose(Entity entity) {
        return null;
    }

    public Collection<PrimitiveShapesPacket> encodePrimitiveShapes(
            Collection<? extends PrimitiveShape> primitiveShapes,
            int dimensionId
    ) {
        return null;
    }

    public Collection<PrimitiveShapesPacket> encodePrimitiveShapeRemovals(
            Collection<? extends PrimitiveShape> primitiveShapes
    ) {
        return null;
    }

    public Collection<ConfirmSkinPacket> encodeSkinConfirmation(EntityPlayer player, Skin skin) {
        return null;
    }

    public ClientboundDataStorePacket encodeDataStoreChange(
            String dataStoreName,
            String propertyName,
            Object value
    ) {
        return null;
    }

    public ClientboundDataStorePacket encodeDataStoreUpdates(
            String dataStoreName,
            String propertyName,
            Collection<String> paths,
            Object value
    ) {
        return null;
    }

    public ClientboundDataDrivenUIShowScreenPacket encodeDataDrivenUIShowScreen(
            String screenId,
            int formId
    ) {
        return null;
    }

    public ClientboundDataDrivenUICloseScreenPacket encodeDataDrivenUICloseScreen(Integer formId) {
        return null;
    }

    public ClientCacheMissResponsePacket encodeClientCacheMissResponse(Map<Long, byte[]> blobs) {
        return null;
    }

    public BlockEntityDataPacket encodeBlockEntityData(Vector3ic position, NbtMap data) {
        return null;
    }

    public ChunkRadiusUpdatedPacket encodeChunkRadiusUpdated(int radius) {
        return null;
    }

    public SubChunkPacket encodeSubChunk(
            int dimension,
            Vector3i centerPosition,
            boolean cacheEnabled,
            Collection<SubChunkData> subChunks
    ) {
        return null;
    }

    public ContainerClosePacket encodeContainerClose(
            byte id,
            ContainerType type,
            boolean serverInitiated
    ) {
        return null;
    }

    public ItemStackResponsePacket encodeItemStackResponse(Collection<ItemStackResponse> responses) {
        return null;
    }

    public RespawnPacket encodeRespawn(Vector3ic position) {
        return null;
    }

    public ServerSettingsResponsePacket encodeServerSettingsResponse(int formId, String formData) {
        return null;
    }

    public StartGamePacket encodeStartGame(
            World spawnWorld,
            PlayerData playerData,
            Dimension dimension,
            EntityPlayer player
    ) {
        return null;
    }
}
