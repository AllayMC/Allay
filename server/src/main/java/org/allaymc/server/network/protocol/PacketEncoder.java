package org.allaymc.server.network.protocol;

import org.allaymc.api.block.action.BlockAction;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.container.Container;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.dialog.Dialog;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.item.enchantment.EnchantOption;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.player.CameraShakeType;
import org.allaymc.api.player.HudElement;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.PlayerData;
import org.allaymc.api.player.Skin;
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
import org.allaymc.server.player.ChunkCache;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.data.AttributeData;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermission;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermission;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.SubChunkData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Stateless conversion from Allay domain data to packets accepted by one protocol codec.
 */
public abstract class PacketEncoder {
    private final ProtocolData data;

    protected PacketEncoder(ProtocolData data) {
        this.data = Objects.requireNonNull(data, "data");
    }

    public final ProtocolData getData() {
        return data;
    }

    public abstract ItemRegistryPacket encodeItemRegistry();

    public abstract CreativeContentPacket encodeCreativeContent();

    public abstract CraftingDataPacket encodeCraftingData();

    public abstract AvailableEntityIdentifiersPacket encodeAvailableEntityIdentifiers();

    public abstract Collection<SyncEntityPropertyPacket> encodeSyncEntityProperties();

    public abstract BiomeDefinitionListPacket encodeBiomeDefinitions();

    public abstract DimensionDataPacket encodeDimensionData();

    public abstract Collection<VoxelShapesPacket> encodeVoxelShapes();

    public abstract ResourcePacksInfoPacket encodeResourcePacksInfo();

    public abstract ResourcePackStackPacket encodeResourcePackStack();

    public abstract ResourcePackDataInfoPacket encodeResourcePackDataInfo(
            Pack pack,
            int maxChunkSize
    );

    public abstract ResourcePackChunkDataPacket encodeResourcePackChunkData(
            Pack pack,
            int chunkIndex,
            int maxChunkSize
    );

    public abstract TrimDataPacket encodeTrimData();

    public abstract NetworkChunkPublisherUpdatePacket encodeChunkPublisher(EntityPlayer player);

    public abstract LevelChunkPacket encodeLevelChunk(
            Chunk chunk,
            boolean useSubChunkSending,
            ChunkCache cache,
            UUID playerId,
            int cacheGeneration
    );

    public abstract SetTimePacket encodeTime(int timeOfDay);

    public abstract GameRulesChangedPacket encodeGameRules(GameRules gameRules);

    public abstract UpdateBlockPacket encodeBlockUpdate(Vector3ic position, int layer, BlockState blockState);

    public abstract Collection<UpdateSubChunkBlocksPacket> encodeBlockUpdates(
            Chunk chunk,
            Collection<BlockUpdate> blockUpdates,
            Collection<BlockUpdate> extraBlockUpdates
    );

    public abstract Collection<BedrockPacket> encodeBlockAction(Vector3ic position, BlockAction action);

    public abstract StopSoundPacket encodeStopSound(String soundName);

    public abstract Collection<BedrockPacket> encodeParticle(
            Particle particle,
            Vector3dc position,
            int dimensionId
    );

    public abstract Collection<LevelEventPacket> encodeWeather(Weather weather);

    public abstract BlockEntityDataPacket encodeBlockEntity(BlockEntity blockEntity);

    public abstract ContainerOpenPacket encodeLectern(Vector3ic position);

    public abstract OpenSignPacket encodeSignEditor(Vector3ic position, boolean frontSide);

    public abstract InventoryContentPacket encodeContainerContents(Container container, int containerId);

    public abstract InventorySlotPacket encodeContainerSlot(Container container, int slot, int containerId);

    public abstract ContainerOpenPacket encodeContainerOpen(
            Container container,
            byte containerId,
            Vector3ic position
    );

    public abstract ContainerClosePacket encodeContainerClose(
            Container container,
            byte containerId,
            boolean serverInitiated
    );

    public abstract ContainerSetDataPacket encodeContainerData(int containerId, int property, int value);

    public abstract BedrockPacket encodeEntitySpawn(Entity entity);

    public abstract RemoveEntityPacket encodeEntityRemove(Entity entity);

    public abstract BedrockPacket encodePlayerGameMode(EntityPlayer player, boolean self);

    public abstract BedrockPacket encodeEntityLocation(
            Entity entity,
            Location3dc location,
            boolean teleporting,
            boolean self
    );

    public abstract SetEntityMotionPacket encodeEntityMotion(Entity entity, Vector3dc motion);

    public abstract EntityDataMap encodeEntityMetadata(Entity entity);

    public abstract SetEntityDataPacket encodeEntityState(Entity entity);

    public abstract <T extends Entity & EntityContainerHolderComponent> MobEquipmentPacket encodeEntityHand(T entity);

    public abstract <T extends Entity & EntityContainerHolderComponent> MobEquipmentPacket encodeEntityOffhand(T entity);

    public abstract <T extends Entity & EntityContainerHolderComponent> MobArmorEquipmentPacket encodeEntityArmor(T entity);

    public abstract AnimateEntityPacket encodeEntityAnimation(Entity entity, EntityAnimation animation);

    public abstract LevelEventGenericPacket encodeSleepingIndicator(int sleepingCount, int totalCount);

    public abstract ContainerOpenPacket encodeCommandBlockEditor(Vector3ic position);

    public abstract PlayerEnchantOptionsPacket encodeEnchantOptions(
            Collection<Pair<Integer, EnchantOption>> enchantOptions
    );

    public abstract Collection<BedrockPacket> encodePlayerSkin(EntityPlayer player, boolean trustSkin);

    public abstract Collection<BedrockPacket> encodeEntityAction(Entity entity, EntityAction action, boolean self);

    public abstract EmotePacket encodePlayerEmote(EntityPlayer player, UUID emoteId, boolean silence);

    public abstract MobEffectPacket encodeMobEffect(
            Entity entity,
            EffectInstance newEffect,
            EffectInstance oldEffect,
            long tick
    );

    public abstract Collection<BedrockPacket> encodeSound(Sound sound, Vector3dc position, boolean relative);

    public abstract BossEventPacket encodeBossBar(long bossEntityId, BossBar bossBar);

    public abstract BossEventPacket encodeBossBarRemoval(long bossEntityId);

    public abstract ModalFormRequestPacket encodeForm(int formId, Form form);

    public abstract ClientboundCloseFormPacket encodeCloseForms();

    public abstract Collection<BedrockPacket> encodeScoreboard(
            Scoreboard scoreboard,
            DisplaySlot slot,
            Collection<ScoreInfo> scores
    );

    public abstract SetDisplayObjectivePacket encodeScoreboardSlotRemoval(DisplaySlot slot);

    public abstract RemoveObjectivePacket encodeScoreboardRemoval(Scoreboard scoreboard);

    public abstract SetScorePacket encodeScore(SetScorePacket.Action action, ScoreInfo score);

    public abstract PlayerStartItemCooldownPacket encodeCooldown(String category, int duration);

    public abstract ClientboundMapItemDataPacket encodeMapData(long mapId, BufferedImage image);

    public abstract DeathInfoPacket encodeDeathInfo(String message);

    public abstract EntityEventPacket encodeItemChargingFinished(long runtimeEntityId);

    public abstract UpdateAttributesPacket encodeAttribute(long runtimeEntityId, AttributeData attribute);

    public abstract TextPacket encodeText(String xuid, String message, TextPacket.Type type);

    public abstract ToastRequestPacket encodeToast(String title, String content);

    public abstract SetTitlePacket encodeTitle(
            SetTitlePacket.Type type,
            String text,
            int fadeInTime,
            int stayTime,
            int fadeOutTime
    );

    public abstract CameraShakePacket encodeCameraShake(
            CameraShakeType shakeType,
            float intensity,
            float duration
    );

    public abstract CameraShakePacket encodeCameraShakeStop();

    public abstract PlayerFogPacket encodeFogStack(Collection<String> fogStack);

    public abstract ChangeDimensionPacket encodeDimensionChange(
            DimensionType dimensionType,
            double x,
            double y,
            double z
    );

    public abstract PlayerActionPacket encodeDimensionChangeSuccess(long runtimeEntityId);

    public abstract UpdateAbilitiesPacket encodePlayerAbilities(
            long uniqueEntityId,
            CommandPermission commandPermission,
            PlayerPermission playerPermission,
            Collection<Ability> abilities,
            float walkSpeed,
            float flySpeed,
            float verticalFlySpeed
    );

    public abstract UpdateAdventureSettingsPacket encodeAdventureSettings(
            boolean noPlayerVersusMob,
            boolean noMobVersusPlayer,
            boolean showNameTags,
            boolean immutableWorld
    );

    public abstract PlayerListPacket encodePlayerList(
            Collection<? extends Player> players,
            boolean add,
            boolean trustSkins
    );

    public abstract TransferPacket encodeTransfer(String address, int port);

    public abstract AvailableCommandsPacket encodeCommands(Collection<CommandData> commands);

    public abstract Collection<SetHudPacket> encodeHudElements(Collection<HudElement> hiddenElements);

    public abstract Collection<BedrockPacket> encodeDialog(Dialog dialog, Entity entity);

    public abstract NpcDialoguePacket encodeDialogClose(Entity entity);

    public abstract Collection<PrimitiveShapesPacket> encodePrimitiveShapes(
            Collection<? extends PrimitiveShape> primitiveShapes,
            int dimensionId
    );

    public abstract Collection<PrimitiveShapesPacket> encodePrimitiveShapeRemovals(
            Collection<? extends PrimitiveShape> primitiveShapes
    );

    public abstract Collection<ConfirmSkinPacket> encodeSkinConfirmation(EntityPlayer player, Skin skin);

    public abstract ClientboundDataStorePacket encodeDataStoreChange(
            String dataStoreName,
            String propertyName,
            Object value
    );

    public abstract ClientboundDataStorePacket encodeDataStoreUpdates(
            String dataStoreName,
            String propertyName,
            Collection<String> paths,
            Object value
    );

    public abstract ClientboundDataDrivenUIShowScreenPacket encodeDataDrivenUIShowScreen(
            String screenId,
            int formId
    );

    public abstract ClientboundDataDrivenUICloseScreenPacket encodeDataDrivenUICloseScreen(Integer formId);

    public abstract ClientCacheMissResponsePacket encodeClientCacheMissResponse(Map<Long, byte[]> blobs);

    public abstract BlockEntityDataPacket encodeBlockEntityData(Vector3ic position, NbtMap data);

    public abstract ChunkRadiusUpdatedPacket encodeChunkRadiusUpdated(int radius);

    public abstract SubChunkPacket encodeSubChunk(
            int dimension,
            Vector3i centerPosition,
            boolean cacheEnabled,
            Collection<SubChunkData> subChunks
    );

    public abstract ContainerClosePacket encodeContainerClose(
            byte id,
            ContainerType type,
            boolean serverInitiated
    );

    public abstract ItemStackResponsePacket encodeItemStackResponse(Collection<ItemStackResponse> responses);

    public abstract RespawnPacket encodeRespawn(Vector3ic position);

    public abstract ServerSettingsResponsePacket encodeServerSettingsResponse(int formId, String formData);

    public abstract StartGamePacket encodeStartGame(
            World spawnWorld,
            PlayerData playerData,
            Dimension dimension,
            EntityPlayer player
    );
}
