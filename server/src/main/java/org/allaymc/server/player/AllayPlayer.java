package org.allaymc.server.player;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.action.BlockAction;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.BlockContainer;
import org.allaymc.api.ddui.DDUIScreenSession;
import org.allaymc.api.ddui.type.DDUIScreen;
import org.allaymc.api.dialog.Dialog;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityPlayerBaseComponent;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.server.PlayerAbilitiesUpdateEvent;
import org.allaymc.api.eventbus.event.server.PlayerDisconnectEvent;
import org.allaymc.api.eventbus.event.server.PlayerLoginEvent;
import org.allaymc.api.eventbus.event.server.PlayerSpawnEvent;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.item.enchantment.EnchantOption;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.OpPermissionCalculator;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.*;
import org.allaymc.api.primitiveshape.PrimitiveShape;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.scorer.EntityScorer;
import org.allaymc.api.scoreboard.scorer.FakeScorer;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.particle.Particle;
import org.allaymc.api.world.sound.Sound;
import org.allaymc.server.AllayServer;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.command.tree.node.BaseNode;
import org.allaymc.server.container.ContainerNetworkInfo;
import org.allaymc.server.container.impl.AbstractPlayerContainer;
import org.allaymc.server.container.impl.FakeContainerImpl;
import org.allaymc.server.ddui.AllayDDUIScreenSession;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.eventbus.event.network.PacketReceiveEvent;
import org.allaymc.server.eventbus.event.network.PacketSendEvent;
import org.allaymc.server.network.AllayNetworkInterface;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.login.RequestNetworkSettingsPacketProcessor;
import org.allaymc.server.network.protocol.Protocol;
import org.allaymc.server.network.protocol.ProtocolSession;
import org.allaymc.server.world.AllayDimension;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.data.AttributeData;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermission;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;
import org.cloudburstmc.protocol.bedrock.data.command.*;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3dc;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.awt.image.BufferedImage;
import java.net.SocketAddress;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.allaymc.api.utils.AllayNBTUtils.*;

/**
 * {@code AllayPlayer} implements one network player connection and translates server
 * viewer operations into packets for its negotiated protocol. The connection owns
 * login metadata, client lifecycle, open-window mappings, interface registrations,
 * and tracked abilities; its separate {@code controlledEntity} owns world and
 * survival state. Most viewer operations require a selected protocol and initialized
 * entity or login data, as indicated by their inputs.
 *
 * <p>
 * Protocol installation, codec changes, and disconnection claim lifecycle
 * transitions under {@code protocolLifecycleLock}. Installation publishes
 * {@code protocolSession} only after codec setup completes, and a disconnect winning
 * that lock prevents later installation. That lock does not serialize gameplay
 * viewers, form and container operations, or packet processors. Callers must
 * preserve the owning execution context for those mutable structures.
 *
 * <p>
 * Spawning sends dimension definitions and start-game data before dimension
 * membership begins chunk delivery. Initial entity NBT is applied again when the
 * chunk threshold triggers full-join synchronization, followed by storage saving and
 * the spawn status packet. Open containers maintain matching network-ID,
 * container-type, and protocol-slot mappings; forms retain object identity under
 * assigned response IDs. These client updates and persistence steps are sequential
 * and do not form one rollback-capable transaction.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayPlayer implements Player {

    /**
     * {@code bootstrapProcessorHolder} handles the connected pre-negotiation phase.
     * Its frozen registry accepts network-settings requests until a negotiated
     * session becomes active.
     */
    protected final PacketProcessorHolder bootstrapProcessorHolder;
    /**
     * {@code protocolLifecycleLock} serializes protocol publication, codec
     * switching, and lifecycle transition claims. Gameplay and viewer state do not
     * use this lock.
     */
    private final Object protocolLifecycleLock = new Object();
    /**
     * {@code protocolSession} publishes the successfully installed negotiated
     * session through a volatile reference. {@code null} selects bootstrap
     * processing and remains after installation failure.
     */
    @Getter
    protected volatile ProtocolSession protocolSession;
    /**
     * {@code fullyJoinChunkThreshold} counts chunk-view calls toward full-join
     * synchronization. The decrement reaching zero invokes {@code onFullyJoin}; an
     * initial nonpositive value never triggers that path, and the counter does not
     * confirm client delivery.
     */
    protected final AtomicInteger fullyJoinChunkThreshold;
    /**
     * Transport session retained for this connection.
     */
    @Getter
    protected final BedrockServerSession session;
    /**
     * Network interface that accepted this connection.
     */
    @Getter
    protected final AllayNetworkInterface sourceInterface;

    /**
     * {@code controlledEntity} is assigned during player spawning. It remains
     * {@code null} before that stage and supplies entity identity to gameplay viewer
     * operations.
     */
    @Getter
    protected EntityPlayer controlledEntity;
    /**
     * {@code loginData} holds decoded identity and metadata. Login-dependent viewers
     * and storage helpers require this value after successful login decoding.
     */
    @Getter
    @Setter
    protected AllayLoginData loginData;
    /**
     * Flag recording enabled connection encryption.
     */
    @Getter
    @Setter
    protected boolean networkEncryptionEnabled;
    /**
     * Client-advertised chunk-cache support flag.
     */
    @Getter
    @Setter
    protected boolean clientCacheEnabled;
    /**
     * {@code shouldSendCommands} defers command descriptions until a tick divisible
     * by twenty. Additional requests are coalesced while this flag remains set.
     */
    protected boolean shouldSendCommands;
    /**
     * {@code containerClosedByClient} records the origin of the next close response.
     * {@code sendContainerClosePacket} consumes and resets it when deciding the
     * protocol initiation flag.
     */
    @Getter
    @Setter
    protected boolean containerClosedByClient;
    /**
     * {@code suppressNextContainerClosePacket} suppresses one close packet.
     * Consuming this flag also resets {@code containerClosedByClient} before
     * returning to the caller.
     */
    @Setter
    protected boolean suppressNextContainerClosePacket;
    /**
     * {@code speed}, {@code flySpeed}, and {@code verticalFlySpeed} retain separate
     * movement configurations. The constructor supplies their defaults, and each
     * setter sends the corresponding client update when its configuration changes.
     *
     * <p>
     * {@code speed} stores walking movement configuration. {@code flySpeed} stores
     * horizontal flight configuration. {@code verticalFlySpeed} stores vertical
     * flight configuration.
     */
    @Getter
    protected Speed speed, flySpeed, verticalFlySpeed;
    /**
     * Latest decoded client input category.
     */
    @Getter
    @Setter
    protected InputMode inputMode;
    /**
     * Latest decoded client play category.
     */
    @Getter
    @Setter
    protected ClientPlayMode playMode;
    /**
     * Latest decoded client interaction layout.
     */
    @Getter
    @Setter
    protected InputInteractionModel inputInteractionModel;

    /**
     * {@code changingDimension} records loading-screen presentation across transfer
     * work. {@code beginDimensionChange} sets it and {@code completeDimensionChange}
     * clears it; the flag is volatile for cross-thread observation.
     */
    @Getter
    protected volatile boolean changingDimension;

    /**
     * {@code containerIdCounter} assigns rotating window IDs from one through
     * ninety-eight. Allocation wraps without checking whether an older assigned ID
     * is still in use.
     */
    protected byte containerIdCounter;
    /**
     * {@code idToContainer} maps assigned protocol IDs to open container objects.
     * Registration updates this map before the type and slot maps; mutations have no
     * rollback.
     */
    protected BiMap<Byte, Container> idToContainer;
    /**
     * {@code typeToContainer} maps full container types to registered open objects.
     * Player inventory also makes armor, offhand, and crafting-grid containers
     * implicitly reachable.
     */
    protected BiMap<ContainerType<?>, Container> typeToContainer;
    /**
     * {@code slotTypeToFullType} resolves protocol slot categories to full types for
     * registered windows. Inventory-owned implicit categories take precedence during
     * slot lookup.
     */
    protected Map<ContainerSlotType, ContainerType<?>> slotTypeToFullType;

    /**
     * {@code formIdCounter} allocates response IDs shared by ordinary forms, server
     * settings, and DDUI screens. Atomic increment does not coordinate their other
     * mutable registrations.
     */
    protected AtomicInteger formIdCounter;
    /**
     * {@code forms} retains ordinary form objects under response IDs. Size eviction
     * and ten-minute write expiration can remove a registration before a client
     * responds.
     */
    protected Cache<@NotNull Integer, Form> forms;
    /**
     * {@code serverSettingForm} holds the separately registered settings form and
     * its ID. Ordinary form cache invalidation does not remove this pair.
     */
    @Getter
    protected Pair<Integer, CustomForm> serverSettingForm;
    /**
     * {@code activeScreen} holds the current DDUI session. Replacing it closes the
     * previous session before showing the new one; removal alone does not close it.
     */
    protected DDUIScreenSession activeScreen;

    /**
     * {@code dialog} retains the dialog and presenting entity for later close
     * handling. Removing this reference alone does not send a client close packet.
     */
    protected Pair<Dialog, Entity> dialog;

    /**
     * {@code hiddenHudElements} stores the HUD visibility override. Changed
     * membership sets {@code shouldSendHudElements}, and the next tick sends the
     * complete hidden selection.
     */
    protected Set<HudElement> hiddenHudElements;
    /**
     * {@code shouldSendHudElements} coalesces HUD visibility changes until the next
     * tick. The flag is cleared after sending the recorded visibility state.
     */
    protected boolean shouldSendHudElements;

    /**
     * {@code fogStack} retains ordered fog identifiers, including duplicates.
     * Mutations send the resulting complete stack, and {@code getFogs} exposes a
     * live unmodifiable view.
     */
    protected final List<String> fogStack = new ArrayList<>();

    /**
     * {@code abilities} holds connection-level tracked capability flags. Effective
     * queries additionally apply mode and operator precedence; {@code getAbilities}
     * exposes this set through a live unmodifiable view.
     */
    protected final EnumSet<PlayerAbility> abilities;
    /**
     * {@code shouldSendAbilities} defers ability broadcasting until a controlled
     * entity exists and spawning has begun. A successful tick broadcast clears this
     * pending flag.
     */
    protected boolean shouldSendAbilities;
    /**
     * Explicit connection world-editing restriction flag.
     */
    protected boolean immutableWorld;
    /**
     * Explicit connection forced-flight policy flag.
     */
    protected boolean alwaysFlying;

    /**
     * Flag identifying the NetEase client family.
     */
    @Getter
    @Setter
    protected boolean netEasePlayer;

    /**
     * Creates connection state over the supplied transport session and accepting
     * interface. The constructor installs its packet handler, initializes bootstrap
     * processing, and sets the configured chunk threshold, default speeds, and
     * interface caches before gameplay begins.
     */
    public AllayPlayer(BedrockServerSession session, AllayNetworkInterface sourceInterface) {
        this.session = session;
        this.sourceInterface = sourceInterface;
        this.session.setPacketHandler(new AllayPacketHandler());
        this.bootstrapProcessorHolder = createBootstrapProcessorHolder();
        this.fullyJoinChunkThreshold = new AtomicInteger(AllayServer.getSettings().worldSettings().fullyJoinChunkThreshold());
        this.speed = DEFAULT_SPEED;
        this.flySpeed = DEFAULT_FLY_SPEED;
        this.verticalFlySpeed = DEFAULT_VERTICAL_FLY_SPEED;

        this.containerIdCounter = 1;
        this.idToContainer = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
        this.typeToContainer = HashBiMap.create(new Object2ObjectOpenHashMap<>());
        this.slotTypeToFullType = new HashMap<>();

        this.formIdCounter = new AtomicInteger(0);
        this.forms = Caffeine.newBuilder()
                // Bound ordinary response registrations so older cached forms can be evicted when unanswered
                // display requests accumulate on this connection.
                .maximumSize(100)
                // Expire ordinary registrations ten minutes after insertion to release retained form state
                // even when the client never returns a response.
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();

        this.hiddenHudElements = EnumSet.noneOf(HudElement.class);

        this.abilities = EnumSet.noneOf(PlayerAbility.class);
    }

    /**
     * Creates a connected holder containing only the network-settings bootstrap
     * processor. The registry is frozen before holder construction so
     * pre-negotiation packet dispatch cannot add unrelated gameplay registrations
     * later.
     */
    private static PacketProcessorHolder createBootstrapProcessorHolder() {
        var registry = new PacketProcessorRegistry();
        registry.register(
                ClientState.CONNECTED,
                BedrockPacketType.REQUEST_NETWORK_SETTINGS,
                RequestNetworkSettingsPacketProcessor::new
        );
        registry.freeze();
        var holder = new PacketProcessorHolder(registry);
        holder.setClientState(ClientState.CONNECTED);
        return holder;
    }

    /**
     * Sends deferred command descriptions, HUD overrides, and ability updates for
     * this server tick. Commands wait for a tick divisible by twenty, while
     * abilities remain pending until a controlled entity exists and the lifecycle
     * reaches {@code SPAWNED}.
     */
    public void tick(long currentTick) {
        // Coalesce command-description refreshes into twenty-tick intervals to limit repeated client updates.
        if (this.shouldSendCommands && currentTick % 20 == 0) {
            this.sendCommands();
            this.shouldSendCommands = false;
        }

        if (this.shouldSendHudElements) {
            this.sendHudElements();
            this.shouldSendHudElements = false;
        }

        if (this.shouldSendAbilities &&
            this.controlledEntity != null &&
            getClientState().ordinal() >= ClientState.SPAWNED.ordinal()) {
            broadcastPlayerAbilities();
            this.shouldSendAbilities = false;
        }
    }

    /**
     * Dispatches a synchronous packet through the selected protocol using its
     * original receive tick. A packet arriving before protocol publication is logged
     * and ignored because bootstrap handling runs through the direct receive path.
     */
    public void handlePacketSync(BedrockPacket packet, long receiveTime) {
        var selectedSession = protocolSession;
        if (selectedSession == null) {
            log.debug("Ignoring sync packet before protocol negotiation: {}", packet);
            return;
        }
        selectedSession.handleSync(this, packet, receiveTime);
    }

    /**
     * Returns the next rotating window ID between one and ninety-eight. Allocation
     * wraps before one hundred and does not check occupied IDs, so callers cannot
     * assume an ID is unique across arbitrarily many open windows.
     */
    protected byte assignContainerId() {
        if (containerIdCounter + 1 >= 100) {
            containerIdCounter = 1;
        }

        return containerIdCounter++;
    }

    /**
     * Returns the next atomic ID shared by forms, settings forms, and screens.
     */
    protected int assignFormId() {
        return this.formIdCounter.getAndIncrement();
    }

    /**
     * Applies stored entity NBT and sends world, inventory, equipment, ability, and
     * survival state after the configured chunk threshold is reached. Callers must
     * already have a controlled entity, decoded login data, and a negotiated
     * protocol.
     *
     * <p>
     * The method reads storage again rather than reusing the earlier spawn envelope,
     * then synchronizes this client and other viewers before saving current data.
     * The final {@code PLAYER_SPAWN} status follows that save. Earlier failures
     * leave already sent updates intact and can prevent the final status; this
     * sequence has no rollback or separate thread handoff.
     */
    protected void onFullyJoin() {
        var server = Server.getInstance();
        var playerManager = server.getPlayerManager();
        var world = this.controlledEntity.getWorld();

        // Read the stored payload only at this full-join phase so inventory and state packets follow
        // the initial chunk/start-game sequence rather than early entity creation.
        this.controlledEntity.loadNBT(playerManager.getPlayerStorage().readPlayerData(this).getNbt());

        viewEntityState(this.controlledEntity);
        viewPlayerGameMode(this.controlledEntity);
        this.controlledEntity.forEachViewers(viewer -> viewer.viewPlayerGameMode(this.controlledEntity));
        viewTime(world.getWorldData().getTimeOfDay());
        viewWeather(world.getWeather());
        viewContainerContents(this.controlledEntity.getContainer(ContainerTypes.INVENTORY));
        viewContainerContents(this.controlledEntity.getContainer(ContainerTypes.OFFHAND));
        viewContainerContents(this.controlledEntity.getContainer(ContainerTypes.ARMOR));
        viewPlayerAbilities(this);
        viewPlayerListChange(playerManager.getPlayers().values(), true);
        playerManager.getPlayers().values().stream()
                .filter(player -> player != this && player.getControlledEntity() != null)
                .forEach(this::viewPlayerAbilities);
        broadcastPlayerAbilities();

        this.controlledEntity.forEachViewers(viewer -> {
            viewer.viewEntityArmors(this.controlledEntity);
            viewer.viewEntityHand(this.controlledEntity);
            viewer.viewEntityOffhand(this.controlledEntity);
        });

        sendSpeed(this.speed);
        sendExperienceLevel(this.controlledEntity.getExperienceLevel());
        sendExperienceProgress(this.controlledEntity.getExperienceProgress());
        sendFoodLevel(this.controlledEntity.getFoodLevel());
        sendFoodSaturationLevel(this.controlledEntity.getFoodSaturationLevel());
        sendFoodExhaustionLevel(this.controlledEntity.getFoodExhaustionLevel());

        // Persist the loaded and synchronized entity before announcing PLAYER_SPAWN; a storage failure
        // stops the final status rather than rolling back packets already sent.
        playerManager.getPlayerStorage().savePlayerData(this);

        sendPlayStatus(PlayStatusPacket.Status.PLAYER_SPAWN);
    }

    /**
     * Sends the entity spawn representation and its attached primitive shapes to
     * this client.
     */
    @Override
    public void viewEntity(Entity entity) {
        sendPacket(getProtocol().getEncoder().encodeEntitySpawn(entity));
        viewPrimitiveShapes(entity.getAttachedPrimitiveShapes());
    }

    /**
     * Removes the entity's attached primitive shapes before sending its removal
     * packet.
     */
    @Override
    public void removeEntity(Entity entity) {
        removePrimitiveShapes(entity.getAttachedPrimitiveShapes());
        sendPacket(getProtocol().getEncoder().encodeEntityRemove(entity));
    }

    /**
     * Sends the subject entity's game mode and identifies whether it is controlled
     * by this connection. For the controlled entity, adventure settings follow the
     * game-mode packet so client permissions are refreshed too.
     */
    @Override
    public void viewPlayerGameMode(EntityPlayer player) {
        boolean self = this.controlledEntity == player;
        sendPacket(getProtocol().getEncoder().encodePlayerGameMode(player, self));
        if (self) {
            sendAdventureSettings();
        }
    }

    /**
     * Sends the supplied entity location with its teleport and controlled-entity
     * flags.
     */
    @Override
    public void viewEntityLocation(Entity entity, Location3dc newLocation, boolean teleporting) {
        sendPacket(getProtocol().getEncoder().encodeEntityLocation(
                entity,
                newLocation,
                teleporting,
                this.controlledEntity == entity
        ));
    }

    /**
     * Sends the supplied motion vector for the identified physics-capable entity.
     */
    @Override
    public <T extends Entity & EntityPhysicsComponent> void viewEntityMotion(T entity, Vector3dc motion) {
        sendPacket(getProtocol().getEncoder().encodeEntityMotion(entity, motion));
    }

    /**
     * Sends the entity's current metadata through the negotiated protocol encoder.
     */
    @Override
    public void viewEntityState(Entity entity) {
        sendPacket(getProtocol().getEncoder().encodeEntityState(entity));
    }

    /**
     * Returns the protocol metadata representation of the supplied server entity.
     */
    protected EntityDataMap parseMetadata(Entity entity) {
        return getProtocol().getEncoder().encodeEntityMetadata(entity);
    }

    /**
     * Sends the identified container-holding entity's currently selected hand
     * equipment.
     */
    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityHand(T entity) {
        sendPacket(getProtocol().getEncoder().encodeEntityHand(entity));
    }

    /**
     * Sends the identified container-holding entity's current offhand equipment
     * state.
     */
    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityOffhand(T entity) {
        sendPacket(getProtocol().getEncoder().encodeEntityOffhand(entity));
    }

    /**
     * Sends the identified container-holding entity's current armor equipment state.
     */
    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityArmors(T entity) {
        sendPacket(getProtocol().getEncoder().encodeEntityArmor(entity));
    }

    /**
     * Sends the selected animation for the identified entity to this client.
     */
    @Override
    public void viewEntityAnimation(Entity entity, EntityAnimation animation) {
        sendPacket(getProtocol().getEncoder().encodeEntityAnimation(entity, animation));
    }

    /**
     * Sends the sleeping and total player counts for client sleep presentation.
     */
    @Override
    public void viewSleepingIndicator(int sleepingCount, int totalCount) {
        sendPacket(getProtocol().getEncoder().encodeSleepingIndicator(sleepingCount, totalCount));
    }

    /**
     * Requests the client command-block editor at the supplied block position.
     */
    @Override
    public void viewCommandBlockEditor(Vector3ic pos) {
        sendPacket(getProtocol().getEncoder().encodeCommandBlockEditor(pos));
    }

    /**
     * Sends the supplied option IDs and enchanting selections to this client.
     */
    @Override
    public void viewEnchantOptions(List<Pair<Integer, EnchantOption>> enchantOptions) {
        sendPacket(getProtocol().getEncoder().encodeEnchantOptions(enchantOptions));
    }

    /**
     * Sends the subject's skin update and skin-confirmation packets when a skin
     * exists. A {@code null} skin produces no packets; accepted updates use the
     * configured trust-all-skins selection during encoding.
     */
    @Override
    public void viewPlayerSkin(EntityPlayer player) {
        var skin = player.getSkin();
        if (skin == null) {
            return;
        }

        boolean trustSkin = AllayServer.getSettings().resourcePackSettings().trustAllSkins();
        sendPackets(getProtocol().getEncoder().encodePlayerSkin(player, trustSkin));
        sendPackets(getProtocol().getEncoder().encodeSkinConfirmation(player, skin));
    }

    /**
     * Sends entity-action packets with whether the subject is this connection's
     * entity.
     */
    @Override
    public void viewEntityAction(Entity entity, EntityAction action) {
        sendPackets(getProtocol().getEncoder().encodeEntityAction(
                entity,
                action,
                this.controlledEntity == entity
        ));
    }

    /**
     * Sends the selected player emote and its silence flag to this client.
     */
    @Override
    public void viewPlayerEmote(EntityPlayer player, UUID emoteId, boolean silence) {
        sendPacket(getProtocol().getEncoder().encodePlayerEmote(player, emoteId, silence));
    }

    /**
     * Sends an effect change using the current server tick for protocol timing.
     */
    @Override
    public void viewEntityEffectChange(Entity entity, EffectInstance newEffect, EffectInstance oldEffect) {
        sendPacket(getProtocol().getEncoder().encodeMobEffect(
                entity,
                newEffect,
                oldEffect,
                Server.getInstance().getTick()
        ));
    }

    /**
     * Sends chunk-publisher state before the chunk payload and schedules its
     * entities through the dimension entity manager. Entity spawning uses the
     * manager's queued traversal so asynchronous chunk delivery does not directly
     * traverse ticking entity state.
     *
     * <p>
     * Each eligible call decrements the atomic initial-chunk threshold, and the call
     * reaching zero invokes {@code onFullyJoin} on its own thread. This threshold
     * counts attempted chunk-view operations rather than confirmed client receipt;
     * packet events can suppress sends, and the counter does not serialize the
     * full-join routine against other viewer state.
     */
    @Override
    public void viewChunk(Chunk chunk) {
        sendPacket(createNetworkChunkPublisherUpdatePacket());
        sendPacket(createLevelChunkPacket(chunk));
        // Queue entity traversal through its owning dimension when chunks arrive from an asynchronous
        // sender, rather than visiting ticking entity state directly on that sender thread.
        this.controlledEntity.getDimension().getEntityManager().forEachEntitiesInChunk(chunk.getX(), chunk.getZ(), entity -> entity.spawnTo(this));
        // Use the zero-crossing decrement to select the full-join caller among concurrent chunk-view
        // calls; the counter measures attempted sends rather than client acknowledgements.
        if (fullyJoinChunkThreshold.get() > 0 && fullyJoinChunkThreshold.decrementAndGet() == 0) {
            onFullyJoin();
        }
    }

    /**
     * Creates the chunk-publisher packet from this connection's controlled entity
     * state.
     */
    protected NetworkChunkPublisherUpdatePacket createNetworkChunkPublisherUpdatePacket() {
        return getProtocol().getEncoder().encodeChunkPublisher(this.controlledEntity);
    }

    /**
     * Creates a chunk payload using current subchunk settings and optional client
     * caching. When caching is enabled, the encoder receives this player's UUID and
     * observed cache generation; the encoder decides whether it can register cached
     * blobs.
     */
    protected LevelChunkPacket createLevelChunkPacket(Chunk chunk) {
        ChunkCache cache = null;
        UUID playerId = null;
        int cacheGen = 0;

        // Capture this player's cache generation before delegated chunk encoding so registration can
        // compare delayed encoding against a later clear on that same state.
        if (isCacheEffectivelyEnabled()) {
            cache = ChunkCache.getInstance();
            playerId = this.loginData.getUuid();
            cacheGen = cache.getGeneration(playerId);
        }

        return getProtocol().getEncoder().encodeLevelChunk(
                chunk,
                AllayServer.getSettings().worldSettings().useSubChunkSendingSystem(),
                cache,
                playerId,
                cacheGen
        );
    }

    /**
     * Returns whether both client support and the server setting enable chunk
     * caching.
     */
    public boolean isCacheEffectivelyEnabled() {
        return this.clientCacheEnabled && AllayServer.getSettings().networkSettings().enableClientChunkCache();
    }

    /**
     * Queues despawning of entities in the identified chunk through the current
     * dimension's entity manager. This helper does not send a separate chunk-unload
     * packet or directly traverse entities from the calling thread.
     */
    @Override
    public void removeChunk(long chunkHash) {
        this.controlledEntity.getDimension().getEntityManager().forEachEntitiesInChunk(
                HashUtils.getXFromHashXZ(chunkHash),
                HashUtils.getZFromHashXZ(chunkHash),
                entity -> entity.despawnFrom(this)
        );
    }

    /**
     * Sends the supplied world time-of-day value through the protocol encoder.
     */
    @Override
    public void viewTime(int timeOfDay) {
        sendPacket(getProtocol().getEncoder().encodeTime(timeOfDay));
    }

    /**
     * Sends the supplied game-rule set through the negotiated protocol encoder.
     */
    @Override
    public void viewGameRules(GameRules gameRules) {
        sendPacket(getProtocol().getEncoder().encodeGameRules(gameRules));
    }

    /**
     * Sends one block state for the supplied position and storage layer.
     */
    @Override
    public void viewBlockUpdate(Vector3ic pos, int layer, BlockState blockState) {
        sendPacket(getProtocol().getEncoder().encodeBlockUpdate(pos, layer, blockState));
    }

    /**
     * Sends the supplied normal and extra block updates for the identified chunk.
     */
    @Override
    public void viewBlockUpdates(Chunk chunk, Collection<BlockUpdate> blockUpdates, Collection<BlockUpdate> extraBlockUpdates) {
        sendPackets(getProtocol().getEncoder().encodeBlockUpdates(
                chunk,
                blockUpdates,
                extraBlockUpdates
        ));
    }

    /**
     * Sends the supplied block action at the identified block position.
     */
    @Override
    public void viewBlockAction(Vector3ic p, BlockAction action) {
        sendPackets(getProtocol().getEncoder().encodeBlockAction(p, action));
    }

    /**
     * Sends the sound at the supplied position using its relative-position
     * selection.
     */
    @Override
    public void viewSound(Sound sound, Vector3dc p, boolean relative) {
        sendPackets(getProtocol().getEncoder().encodeSound(sound, p, relative));
    }

    /**
     * Requests that the client stop the supplied sound name. The standard encoder
     * treats {@code null} as stopping all sounds, while an empty non-{@code null}
     * name does not select that flag.
     */
    @Override
    public void stopSound(String soundName) {
        sendPacket(getProtocol().getEncoder().encodeStopSound(soundName));
    }

    /**
     * Sends the particle using the controlled entity's current numeric dimension ID.
     */
    @Override
    public void viewParticle(Particle particle, Vector3dc p) {
        sendPackets(getProtocol().getEncoder().encodeParticle(
                particle,
                p,
                this.controlledEntity.getDimension().getDimensionType().getId()
        ));
    }

    /**
     * Sends the supplied world weather through the negotiated protocol encoder.
     */
    @Override
    public void viewWeather(Weather weather) {
        sendPackets(getProtocol().getEncoder().encodeWeather(weather));
    }

    /**
     * Sends one primitive shape using the controlled entity's current dimension ID.
     */
    @Override
    public void viewPrimitiveShape(PrimitiveShape primitiveShape) {
        sendPackets(getProtocol().getEncoder().encodePrimitiveShapes(
                List.of(primitiveShape),
                this.controlledEntity.getDimension().getDimensionType().getId()
        ));
    }

    /**
     * Sends the supplied primitive shapes using the controlled entity's current
     * dimension ID.
     */
    @Override
    public void viewPrimitiveShapes(Set<PrimitiveShape> primitiveShapes) {
        sendPackets(getProtocol().getEncoder().encodePrimitiveShapes(
                primitiveShapes,
                this.controlledEntity.getDimension().getDimensionType().getId()
        ));
    }

    /**
     * Requests removal of one primitive shape through its protocol representation.
     */
    @Override
    public void removePrimitiveShape(PrimitiveShape primitiveShape) {
        sendPackets(getProtocol().getEncoder().encodePrimitiveShapeRemovals(List.of(primitiveShape)));
    }

    /**
     * Requests removal of the supplied primitive shapes through their protocol
     * representation.
     */
    @Override
    public void removePrimitiveShapes(Set<PrimitiveShape> primitiveShapes) {
        sendPackets(getProtocol().getEncoder().encodePrimitiveShapeRemovals(primitiveShapes));
    }

    /**
     * Sends block-entity data only when its base component allows client
     * transmission. The method expects the server's concrete block-entity
     * implementation and skips encoding when {@code sendToClient} is {@code false}.
     */
    @Override
    public void viewBlockEntity(BlockEntity blockEntity) {
        if (!((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).sendToClient()) {
            return;
        }
        sendPacket(getProtocol().getEncoder().encodeBlockEntity(blockEntity));
    }

    /**
     * Requests the lectern interface at the supplied client block position.
     */
    @Override
    public void viewLectern(Vector3ic pos) {
        sendPacket(getProtocol().getEncoder().encodeLectern(pos));
    }

    /**
     * Requests the selected front or back sign editor at the supplied position.
     */
    @Override
    public void viewSignEditor(Vector3ic pos, boolean frontSide) {
        sendPacket(getProtocol().getEncoder().encodeSignEditor(pos, frontSide));
    }

    /**
     * Sends all contents using a player container's fixed unopened ID or a
     * registered open-window ID. Other containers must already be opened by this
     * viewer; an absent mapping throws before encoding their contents.
     */
    @Override
    public void viewContainerContents(Container container) {
        if (container instanceof AbstractPlayerContainer playerContainer) {
            viewContentsWithSpecificContainerId(playerContainer, playerContainer.getUnopenedContainerId());
            return;
        }

        var id = idToContainer.inverse().get(container);
        if (id == null) {
            throw new IllegalStateException("This viewer did not open the container " + container.getContainerType());
        }

        viewContentsWithSpecificContainerId(container, id);
    }

    /**
     * Sends all container contents using the supplied protocol window identifier.
     */
    protected void viewContentsWithSpecificContainerId(Container container, int containerId) {
        sendPacket(getProtocol().getEncoder().encodeContainerContents(container, containerId));
    }

    /**
     * Sends a slot update using the entity-held or registered open-window ID.
     * Offhand updates deliberately send complete contents, while other player
     * containers use a slot packet; unregistered nonplayer windows are rejected.
     */
    @Override
    public void viewContainerSlot(Container container, int slot) {
        if (container instanceof AbstractPlayerContainer playerContainer) {
            if (playerContainer.getContainerType() == ContainerTypes.OFFHAND) {
                // Retain full offhand-content updates as this path's client-compatibility treatment;
                // replacing them with slot updates requires verifying offhand refresh on supported clients.
                viewContentsWithSpecificContainerId(playerContainer, playerContainer.getUnopenedContainerId());
            } else {
                viewSlotWithSpecificContainerId(playerContainer, slot, playerContainer.getUnopenedContainerId());
            }
            return;
        }

        var id = idToContainer.inverse().get(container);
        if (id == null) {
            throw new IllegalStateException("This viewer did not open the container " + container.getContainerType());
        }

        viewSlotWithSpecificContainerId(container, slot, id);
    }

    /**
     * Sends one container slot using the supplied protocol window identifier.
     */
    protected void viewSlotWithSpecificContainerId(Container container, int slot, int containerId) {
        sendPacket(getProtocol().getEncoder().encodeContainerSlot(container, slot, containerId));
    }

    /**
     * Sends an open request, registers the container's mappings, and returns its
     * rotating network ID. The container must not already have an ID for this
     * viewer, and callers must serialize window operations.
     *
     * <p>
     * The open packet is submitted before registration, while non-entity-held
     * contents are sent afterward. Registration updates network-ID, full-type, and
     * protocol-slot lookups sequentially; packet cancellation or a later failure
     * does not roll back earlier packets or mappings. The returned ID therefore
     * records server bookkeeping rather than client acknowledgement that the window
     * opened.
     */
    @Override
    public byte viewContainerOpen(Container container) {
        if (idToContainer.inverse().containsKey(container)) {
            throw new IllegalStateException("The container " + container.getContainerType() + " have been opened by this viewer");
        }

        var assignedId = assignContainerId();
        sendContainerOpenPacket(assignedId, container);
        registerOpenedContainer(assignedId, container);

        var containerType = container.getContainerType();
        // Entity-held containers already have their fixed content channel; send initial contents for an
        // external window only after registering the ID needed to address its update.
        if (this.controlledEntity.getContainer(containerType) == null) {
            viewContainerContents(container);
        }

        return assignedId;
    }

    /**
     * Registers one open container in network-ID, full-type, and protocol-slot
     * lookups. The supplied ID and container must satisfy the bidirectional maps'
     * uniqueness constraints, and callers must serialize registration with removal.
     *
     * <p>
     * The ID map changes first, followed by the full-type map and then each held
     * slot category. Slot lookups rely on those maps describing the same window;
     * this method has no rollback if a later uniqueness check or metadata lookup
     * fails, so callers must not treat a failed registration as untouched state.
     */
    protected void registerOpenedContainer(byte assignedId, Container container) {
        // Publish the ID, type, and held-slot mappings in that order because later viewer lookups
        // depend on them describing the same open window.
        this.idToContainer.put(assignedId, container);
        this.typeToContainer.put(container.getContainerType(), container);
        ContainerNetworkInfo.getInfo(container.getContainerType()).heldSlotTypes().forEach(slotType -> slotTypeToFullType.put(slotType, container.getContainerType()));
    }

    /**
     * Sends an open request using the block container's position, a fake container's
     * viewer position, or the controlled entity's floored location. Coordinate
     * flooring preserves the containing block when fallback entity coordinates are
     * negative.
     */
    @SneakyThrows
    protected void sendContainerOpenPacket(byte assignedId, Container container) {
        Vector3ic position = switch (container) {
            case BlockContainer blockContainer -> blockContainer.getBlockPos();
            case FakeContainerImpl fakeContainer -> fakeContainer.getFakeBlockPos(this);
            // An unpositioned window uses the entity's containing block; floor each coordinate so
            // negative fractional positions do not select the neighboring block toward zero.
            default -> {
                var location = this.controlledEntity.getLocation();
                yield new org.joml.Vector3i(
                        (int) Math.floor(location.x()),
                        (int) Math.floor(location.y()),
                        (int) Math.floor(location.z())
                );
            }
        };
        sendPacket(getProtocol().getEncoder().encodeContainerOpen(container, assignedId, position));
    }

    /**
     * Sends a close response for a currently registered window and unregisters its
     * mappings. An absent assigned ID is rejected; packet suppression or event
     * cancellation does not prevent the later server-side unregistration.
     */
    @Override
    public void viewContainerClose(Container container) {
        var assignedId = this.idToContainer.inverse().get(container);
        if (assignedId == null) {
            throw new IllegalStateException("Trying to close a container which is not opened! Type: " + container.getContainerType());
        }

        sendContainerClosePacket(assignedId, container);
        unregisterOpenedContainer(assignedId, container);
    }

    /**
     * Removes an assigned window from the ID map before removing its type and
     * protocol-slot mappings. The ID must identify an existing registration, and
     * callers must serialize this operation with registration.
     *
     * <p>
     * The removed container determines which full-type entry is cleared, while the
     * supplied {@code container} determines the held slot categories. Callers must
     * supply the same registered object so the representations stay aligned. A
     * failure after the first removal leaves partial cleanup because no rollback
     * restores earlier map entries.
     */
    protected void unregisterOpenedContainer(byte assignedId, Container container) {
        // Remove the assigned ID before dependent type and slot entries; callers must serialize window
        // mutations because intermediate mappings are not a complete open-window representation.
        this.typeToContainer.remove(Objects.requireNonNull(idToContainer.remove(assignedId)).getContainerType());
        ContainerNetworkInfo.getInfo(container.getContainerType()).heldSlotTypes().forEach(slotType -> slotTypeToFullType.remove(slotType));
    }

    /**
     * Sends a window close response while consuming its one-shot origin and
     * suppression flags. A requested suppression clears both flags and returns
     * without creating a packet, so the next close starts with fresh origin state.
     *
     * <p>
     * Otherwise {@code containerClosedByClient} selects the inverse server-initiated
     * flag and is reset before submission. The encoder needs that origin to
     * distinguish a forced close from a client acknowledgement. A cancelled send
     * does not restore either consumed flag; window bookkeeping is handled
     * separately by the caller.
     */
    @SneakyThrows
    protected void sendContainerClosePacket(byte assignedId, Container container) {
        // Consume both one-shot flags even when suppressing the packet so the next window close cannot
        // reuse this request's origin or suppression state.
        if (this.suppressNextContainerClosePacket) {
            this.suppressNextContainerClosePacket = false;
            this.containerClosedByClient = false;
            return;
        }

        // Distinguish a forced close from a response to the client's own close request, then consume
        // that origin flag so a later unrelated close does not inherit it.
        boolean serverInitiated = !this.containerClosedByClient;
        this.containerClosedByClient = false;
        sendPacket(getProtocol().getEncoder().encodeContainerClose(
                container,
                assignedId,
                serverInitiated
        ));
    }

    /**
     * Sends a container property for its currently assigned open-window ID. A
     * container without a registration is rejected before encoding, so callers must
     * open the window before sending its properties.
     */
    @Override
    public void viewContainerData(Container container, int property, int value) {
        var assignedId = idToContainer.inverse().get(container);
        if (assignedId == null) {
            throw new IllegalStateException("This viewer did not open the container " + container.getContainerType());
        }

        sendPacket(getProtocol().getEncoder().encodeContainerData(assignedId, property, value));
    }

    /**
     * Returns an opened full-type container, including implicit inventory-owned
     * armor, offhand, and crafting-grid containers. The implicit lookup takes
     * precedence while inventory is open; other types use registered mappings and
     * may return {@code null}.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Container> T getOpenedContainer(ContainerType<T> type) {
        // Opening inventory exposes its armor, offhand, and crafting inputs without independent window
        // registrations; resolve those held containers before consulting external open-window types.
        Container container = null;
        if (isPlayerInventoryOpened()) {
            if (type == ContainerTypes.ARMOR || type == ContainerTypes.OFFHAND || type == ContainerTypes.CRAFTING_GRID) {
                container = this.controlledEntity.getContainer(type);
            }
        }

        if (container == null) {
            container = typeToContainer.get(type);
        }

        return (T) container;
    }

    /**
     * Resolves a protocol slot category to its full open-container type. An open
     * inventory supplies implicit armor, offhand, and crafting-input mappings before
     * registered slot categories; unknown categories can resolve to {@code null}.
     */
    @SuppressWarnings("unchecked")
    public <T extends Container> T getOpenedContainer(ContainerSlotType slotType) {
        // Inventory-owned protocol slot categories lack independent window registrations; include their
        // implicit containers while inventory is open so lookup and cleanup see the same reachable set.
        ContainerType<?> type = null;
        if (isPlayerInventoryOpened()) {
            type = switch (slotType) {
                case ARMOR -> ContainerTypes.ARMOR;
                case OFFHAND -> ContainerTypes.OFFHAND;
                case CRAFTING_INPUT -> ContainerTypes.CRAFTING_GRID;
                default -> null;
            };
        }

        if (type == null) {
            type = slotTypeToFullType.get(slotType);
        }
        return (T) getOpenedContainer(type);
    }

    /**
     * Returns whether the player inventory has a registered open container entry.
     */
    protected boolean isPlayerInventoryOpened() {
        return typeToContainer.get(ContainerTypes.INVENTORY) != null;
    }

    /**
     * Returns the registered container for the supplied network window ID.
     * Unassigned IDs return {@code null}; this direct lookup does not synthesize the
     * extra containers implicitly reachable while the player inventory is open.
     */
    @Override
    public Container getOpenedContainer(byte id) {
        return idToContainer.get(id);
    }

    /**
     * Returns a new set of registered containers plus inventory-owned implicit
     * containers when the inventory is open. The set is independent, but its
     * container objects remain shared with this connection and their owning entity.
     */
    @Override
    public Set<Container> getOpenedContainers() {
        var containers = new HashSet<>(this.idToContainer.values());

        // Inventory-owned protocol slot categories lack independent window registrations; include their
        // implicit containers while inventory is open so lookup and cleanup see the same reachable set.
        if (isPlayerInventoryOpened()) {
            containers.add(getOpenedContainer(ContainerTypes.ARMOR));
            containers.add(getOpenedContainer(ContainerTypes.OFFHAND));
            containers.add(getOpenedContainer(ContainerTypes.CRAFTING_GRID));
        }

        return containers;
    }

    /**
     * Removes this viewer from every container in a copied open-container set. Using
     * the copied set permits close callbacks to unregister their mappings without
     * iterating the same live map being mutated.
     */
    @Override
    public void closeAllOpenedContainers() {
        for (var container : getOpenedContainers()) {
            container.removeViewer(this);
        }
    }

    /**
     * Clears this entity's previous client boss bar before sending the replacement.
     * The method recreates the bar under the controlled entity's unique ID rather
     * than updating an existing bar in place.
     */
    @Override
    public void viewBossBar(BossBar bossBar) {
        // Remove the previous bar before recreating it under the same entity identity, so this path
        // does not need to compare individual old and new boss-bar properties.
        clearBossBar();
        sendPacket(getProtocol().getEncoder().encodeBossBar(
                this.controlledEntity.getUniqueId().getLeastSignificantBits(),
                bossBar
        ));
    }

    /**
     * Removes the boss bar associated with this controlled entity's unique ID.
     */
    @Override
    public void clearBossBar() {
        sendPacket(getProtocol().getEncoder().encodeBossBarRemoval(
                this.controlledEntity.getUniqueId().getLeastSignificantBits()
        ));
    }

    /**
     * Registers the supplied settings form under a newly allocated response ID. The
     * pair replaces any previous settings registration without displaying an
     * ordinary form or adding an entry to the ordinary form cache.
     */
    @Override
    public int setServerSettingForm(CustomForm form) {
        var id = assignFormId();
        this.serverSettingForm = new Pair<>(id, form);
        return id;
    }

    /**
     * Removes and returns the current settings-form registration without sending a
     * packet. A {@code null} result means none was registered, and ordinary cached
     * forms remain unchanged by this operation.
     */
    @Override
    public Pair<Integer, CustomForm> removeServerSettingForm() {
        var tmp = this.serverSettingForm;
        this.serverSettingForm = null;
        return tmp;
    }

    /**
     * Caches the supplied form under a new response ID before sending its display
     * packet. The cache can expire or evict that registration independently; a
     * cancelled send does not remove the cached entry.
     */
    @Override
    public int viewForm(Form form) {
        var id = assignFormId();
        this.forms.put(id, form);

        sendPacket(getProtocol().getEncoder().encodeForm(id, form));

        return id;
    }

    /**
     * Resends a cached form only when its stored object is identical to
     * {@code form}. The existing response ID is retained, and {@code false}
     * indicates no current ordinary-form registration matched that object.
     */
    @Override
    public boolean updateForm(Form form) {
        for (var entry : this.forms.asMap().entrySet()) {
            if (entry.getValue() == form) {
                sendPacket(getProtocol().getEncoder().encodeForm(entry.getKey(), form));
                return true;
            }
        }

        return false;
    }

    /**
     * Returns an unmodifiable live view of ordinary forms keyed by response ID.
     * Cache expiration and eviction remain visible through the view; settings forms
     * and DDUI sessions have separate registrations.
     */
    @Override
    public @UnmodifiableView Map<Integer, Form> getForms() {
        return Collections.unmodifiableMap(this.forms.asMap());
    }

    /**
     * Removes and returns an ordinary form registration when its ID is cached.
     * Missing or expired entries return {@code null}; removal does not close a
     * displayed form or invoke its callbacks.
     */
    @Override
    public Form removeForm(int formId) {
        var form = this.forms.getIfPresent(formId);
        if (form != null) {
            // Remove the response ID before returning the form so subsequent response handling cannot
            // find this registration again through the ordinary cache.
            this.forms.invalidate(formId);
        }

        return form;
    }

    /**
     * Sends the client close-forms request and invalidates ordinary cached
     * registrations. Individual callbacks are not invoked, and the separate
     * settings-form registration remains even when sending is unsupported or
     * cancelled.
     */
    @Override
    public void closeAllForms() {
        sendPacket(getProtocol().getEncoder().encodeCloseForms());
        // Forget ordinary response IDs after the close request, while retaining the separately
        // registered settings form and leaving individual form callbacks uninvoked.
        this.forms.invalidateAll();
    }

    /**
     * Closes any retained DDUI session before creating and showing a replacement.
     * The new session becomes {@code activeScreen} before {@code show} runs, so a
     * show failure can leave that new registration retained.
     */
    @Override
    public DDUIScreenSession viewScreen(DDUIScreen screen) {
        // Close the previous session before assigning its replacement because concrete close handling
        // may clear activeScreen through callbacks on this same player.
        if (this.activeScreen != null) {
            this.activeScreen.close();
        }

        var session = new AllayDDUIScreenSession(this, assignFormId(), screen);
        this.activeScreen = session;
        session.show();
        return session;
    }

    /**
     * Returns the currently registered DDUI session or {@code null} when none
     * exists. The reference is shared with this connection; merely retrieving it
     * does not display, close, or detach the screen.
     */
    @Override
    public DDUIScreenSession getActiveScreen() {
        return this.activeScreen;
    }

    /**
     * Clears and returns the retained DDUI session without sending or invoking
     * closure. A {@code null} result means no screen was registered; callers decide
     * whether the removed session needs closing or discarding.
     */
    @Override
    public DDUIScreenSession removeActiveScreen() {
        var tmp = this.activeScreen;
        this.activeScreen = null;
        return tmp;
    }

    /**
     * Closes the currently retained screen when one exists. The session
     * implementation performs its own close handling; this method does not
     * unconditionally clear {@code activeScreen} for arbitrary session
     * implementations.
     */
    @Override
    public void closeScreen() {
        if (this.activeScreen instanceof AllayDDUIScreenSession screenSession) {
            screenSession.close();
        } else if (this.activeScreen != null) {
            this.activeScreen.close();
        }
    }

    /**
     * Sends an objective and its encodable scores before updating the controlled
     * entity's below-name text when applicable. Player scorers without an online
     * identity are omitted, and the client receives current score values with the
     * objective display.
     */
    @Override
    public void displayScoreboard(Scoreboard scoreboard, DisplaySlot slot) {
        // Include current score values with objective display so this client can populate the newly
        // displayed slot without depending on an earlier objective-viewing session.
        sendPackets(getProtocol().getEncoder().encodeScoreboard(
                scoreboard,
                slot,
                scoreboard.getLines().values()
                        .stream()
                        .map(this::toNetworkScoreInfo)
                        .filter(Objects::nonNull)
                        .toList()
        ));

        var scorer = new PlayerScorer(this);
        var line = scoreboard.getLine(scorer);
        if (slot == DisplaySlot.BELOW_NAME && line != null) {
            this.controlledEntity.setScoreTag(line.getScore() + " " + scoreboard.getDisplayName());
        }
    }

    /**
     * Converts one scoreboard line to its protocol scorer representation. Entity
     * scorers use their unique entity ID, and fake scorers retain their display text
     * in the protocol record.
     *
     * <p>
     * A player scorer must have a UUID that resolves to an online connection or the
     * method returns {@code null}. The current player branch nevertheless encodes
     * this viewer's controlled runtime ID rather than the resolved subject's ID.
     * Callers must not assume this helper currently preserves another player
     * scorer's identity.
     */
    protected ScoreInfo toNetworkScoreInfo(ScoreboardLine line) {
        var scoreboard = line.getScoreboard();
        switch (line.getScorer()) {
            case EntityScorer scorer -> {
                return new ScoreInfo(
                        line.getLineId(), scoreboard.getObjectiveName(), line.getScore(),
                        ScoreInfo.ScorerType.ENTITY, scorer.getUniqueId()
                );
            }
            // A player score needs an online identity to be encoded; skip unresolved subjects rather
            // than fabricating a protocol entry for an absent connection.
            case PlayerScorer scorer -> {
                if (scorer.getUuid() == null) {
                    return null;
                }

                var player = Server.getInstance().getPlayerManager().getPlayers().get(scorer.getUuid());
                if (player == null) {
                    return null;
                }

                return new ScoreInfo(
                        line.getLineId(), scoreboard.getObjectiveName(), line.getScore(),
                        ScoreInfo.ScorerType.PLAYER, this.controlledEntity.getRuntimeId()
                );
            }
            case FakeScorer scorer -> {
                return new ScoreInfo(
                        line.getLineId(), scoreboard.getObjectiveName(),
                        line.getScore(), scorer.getFakeName()
                );
            }
        }
    }

    /**
     * Sends removal of the selected scoreboard display slot. Hiding
     * {@code BELOW_NAME} also clears the controlled entity's score tag so entity
     * metadata no longer advertises that local below-name score.
     */
    @Override
    public void hideScoreboardSlot(DisplaySlot slot) {
        sendPacket(getProtocol().getEncoder().encodeScoreboardSlotRemoval(slot));

        if (slot == DisplaySlot.BELOW_NAME) {
            this.controlledEntity.setScoreTag(null);
        }
    }

    /**
     * Sends removal of the supplied scoreboard objective from this client.
     */
    @Override
    public void removeScoreboard(Scoreboard scoreboard) {
        sendPacket(getProtocol().getEncoder().encodeScoreboardRemoval(scoreboard));
    }

    /**
     * Sends score removal for the line's protocol representation. When the line
     * belongs to this connection and its scoreboard is viewed below the name, the
     * controlled entity's score tag is cleared too.
     */
    @Override
    public void removeScoreboardLine(ScoreboardLine line) {
        sendPacket(getProtocol().getEncoder().encodeScore(
                SetScorePacket.Action.REMOVE,
                toNetworkScoreInfo(line)
        ));

        var scorer = new PlayerScorer(this);
        if (line.getScorer().equals(scorer) && line.getScoreboard().getViewers(DisplaySlot.BELOW_NAME).contains(this)) {
            this.controlledEntity.setScoreTag(null);
        }
    }

    /**
     * Sends the line's current protocol score and refreshes matching below-name
     * text. The text update applies only when this connection is the scorer and
     * already views the scoreboard in {@code BELOW_NAME}.
     */
    @Override
    public void updateScore(ScoreboardLine line) {
        sendPacket(getProtocol().getEncoder().encodeScore(
                SetScorePacket.Action.SET,
                toNetworkScoreInfo(line)
        ));

        var scorer = new PlayerScorer(this);
        if (line.getScorer().equals(scorer) && line.getScoreboard().getViewers(DisplaySlot.BELOW_NAME).contains(this)) {
            this.controlledEntity.setScoreTag(line.getScore() + " " + line.getScoreboard().getDisplayName());
        }
    }

    /**
     * Returns whether this connection has not reached its disconnected state.
     */
    @Override
    public boolean isScoreboardViewerValid() {
        return !isDisconnected();
    }

    /**
     * Sends the supplied experience level as a client attribute update.
     */
    @Override
    public void sendExperienceLevel(int value) {
        sendAttribute(new AttributeData("minecraft:player.level", 0, Float.MAX_VALUE, value));
    }

    /**
     * Sends the supplied within-level experience progress as a client attribute
     * update.
     */
    @Override
    public void sendExperienceProgress(float value) {
        sendAttribute(new AttributeData("minecraft:player.experience", 0, 1, value));
    }

    /**
     * Sends the supplied food level as a client hunger attribute update.
     */
    @Override
    public void sendFoodLevel(int value) {
        var max = EntityPlayerBaseComponent.MAX_FOOD_LEVEL;
        sendAttribute(new AttributeData(
                "minecraft:player.hunger", 0, max,
                value, 0, max, max, Collections.emptyList()
        ));
    }

    /**
     * Sends the supplied food saturation as a client attribute update.
     */
    @Override
    public void sendFoodSaturationLevel(float value) {
        var max = EntityPlayerBaseComponent.MAX_FOOD_SATURATION_LEVEL;
        sendAttribute(new AttributeData(
                "minecraft:player.saturation", 0, max,
                value, 0, max, max, Collections.emptyList()
        ));
    }

    /**
     * Sends the supplied food exhaustion as a client attribute update.
     */
    @Override
    public void sendFoodExhaustionLevel(float value) {
        var max = EntityPlayerBaseComponent.MAX_FOOD_EXHAUSTION_LEVEL;
        sendAttribute(new AttributeData(
                "minecraft:player.exhaustion", 0, max,
                value, 0, max, 0, Collections.emptyList()
        ));
    }

    /**
     * Sends the supplied current and maximum health as client attribute data.
     */
    @Override
    public void sendHealth(float health, float maxHealth) {
        var defaultMax = EntityLivingComponent.DEFAULT_MAX_HEALTH;
        sendAttribute(new AttributeData(
                "minecraft:health", 0, maxHealth,
                health, 0, defaultMax, defaultMax,
                Collections.emptyList()
        ));
    }

    /**
     * Sends the supplied absorption amount as a client attribute update.
     */
    @Override
    public void sendAbsorption(float absorption) {
        // Use sixteen as this packet's advertised absorption bound.
        var maxAbsorption = 16.0f;
        sendAttribute(new AttributeData(
                "minecraft:absorption", 0, maxAbsorption,
                absorption, 0, maxAbsorption, 0,
                Collections.emptyList()
        ));
    }

    /**
     * Sends the selected cooldown category and duration in game ticks to the client.
     * This notification does not update the controlled entity's server-side cooldown
     * storage, which the owning item or gameplay logic manages.
     */
    @Override
    public void sendCooldown(String category, int duration) {
        sendPacket(getProtocol().getEncoder().encodeCooldown(category, duration));
    }

    /**
     * Sends a map image using the identified map resource. The standard encoder
     * reads the top-left {@code 128} by {@code 128} pixels without scaling, so the
     * image must supply at least that region.
     */
    @Override
    public void sendMapData(long mapId, BufferedImage image) {
        sendPacket(getProtocol().getEncoder().encodeMapData(mapId, image));
    }

    /**
     * Sends translated death-screen text using this connection's login language.
     * {@code deathInfo} supplies the translation key and parameters; callers must
     * provide decoded login data and a non-{@code null} pair.
     */
    @Override
    public void sendDeathInfo(Pair<String, String[]> deathInfo) {
        sendPacket(getProtocol().getEncoder().encodeDeathInfo(
                I18n.get().tr(this.loginData.getLangCode(), deathInfo.left(), (Object[]) deathInfo.right())
        ));
    }

    /**
     * Notifies the client that its controlled entity finished charging an item.
     */
    @Override
    public void sendItemChargingFinished() {
        sendPacket(getProtocol().getEncoder().encodeItemChargingFinished(
                this.controlledEntity.getRuntimeId()
        ));
    }

    /**
     * Sends the supplied attribute data for this connection's controlled entity.
     */
    protected void sendAttribute(AttributeData attributeData) {
        sendPacket(getProtocol().getEncoder().encodeAttribute(
                this.controlledEntity.getRuntimeId(),
                attributeData
        ));
    }

    /**
     * Sends the supplied text using the client's tip message presentation.
     */
    @Override
    public void sendTip(String message) {
        sendSimpleMessage(message, TextPacket.Type.TIP);
    }

    /**
     * Sends the supplied text using the client's popup message presentation.
     */
    @Override
    public void sendPopup(String message) {
        sendSimpleMessage(message, TextPacket.Type.POPUP);
    }

    /**
     * Translates the supplied text key and arguments using the decoded login
     * language before sending raw text. Callers must provide login data; this helper
     * performs server-side translation rather than sending unresolved translation
     * parameters.
     */
    @Override
    public void sendTranslatable(String translatable, Object... args) {
        sendMessage(I18n.get().tr(this.loginData.getLangCode(), translatable, args));
    }

    /**
     * Sends translated command results only when the controlled entity has all
     * required permissions. Self-issued results use ordinary text, while results
     * from another sender additionally require {@code COMMAND_VIEW_OTHER_OUTPUTS}
     * and include its name.
     */
    @Override
    public void sendCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
        if (!this.controlledEntity.hasPermissions(permissions)) {
            return;
        }

        if (sender == this.controlledEntity) {
            // Send translated self-issued output through ordinary text packets so this compatibility
            // path does not depend on command-output packet handling for variable-length messages.
            for (var output : outputs) {
                sendMessage(I18n.get().tr(this.loginData.getLangCode(), output.str(), output.args()));
            }
        } else if (this.controlledEntity.hasPermission(Permissions.COMMAND_VIEW_OTHER_OUTPUTS).asBoolean()) {
            for (var output : outputs) {
                var str = TextFormat.GRAY + "" + TextFormat.ITALIC + "[" + sender.getCommandSenderName() + ": " + I18n.get().tr(this.loginData.getLangCode(), output.str(), output.args()) + "]";
                sendMessage(str);
            }
        }
    }

    /**
     * Sends the supplied string as a raw client text message.
     */
    @Override
    public void sendMessage(String message) {
        sendSimpleMessage(message, TextPacket.Type.RAW);
    }

    /**
     * Sends text with the selected presentation type and the login XUID.
     */
    protected void sendSimpleMessage(String message, TextPacket.Type type) {
        sendPacket(getProtocol().getEncoder().encodeText(this.loginData.getXuid(), message, type));
    }

    /**
     * Sends a notification toast containing the supplied title and content.
     */
    @Override
    public void sendToast(String title, String content) {
        sendPacket(getProtocol().getEncoder().encodeToast(title, content));
    }

    /**
     * Sends the main title text for the client's centered title display.
     */
    @Override
    public void sendTitle(String title) {
        sendPacket(getProtocol().getEncoder().encodeTitle(SetTitlePacket.Type.TITLE, title, 0, 0, 0));
    }

    /**
     * Sends subtitle text for the client's title display. A subtitle depends on an
     * active main title for presentation; this call does not itself send or create
     * that main title.
     */
    @Override
    public void sendSubtitle(String subtitle) {
        sendPacket(getProtocol().getEncoder().encodeTitle(SetTitlePacket.Type.SUBTITLE, subtitle, 0, 0, 0));
    }

    /**
     * Sends action-bar text for presentation above the player's hotbar.
     */
    @Override
    public void sendActionBar(String actionBar) {
        sendPacket(getProtocol().getEncoder().encodeTitle(SetTitlePacket.Type.ACTIONBAR, actionBar, 0, 0, 0));
    }

    /**
     * Sends fade-in, visible duration, and fade-out timing values in game ticks.
     * These values configure subsequent client title presentation; this call does
     * not send title text or validate the supplied timing range.
     */
    @Override
    public void setTitleSettings(int fadeInTime, int duration, int fadeOutTime) {
        sendPacket(getProtocol().getEncoder().encodeTitle(
                SetTitlePacket.Type.TIMES,
                null,
                fadeInTime,
                duration,
                fadeOutTime
        ));
    }

    /**
     * Requests the client's default timing and state for title presentation.
     */
    @Override
    public void resetTitleSettings() {
        sendPacket(getProtocol().getEncoder().encodeTitle(SetTitlePacket.Type.RESET, null, 0, 0, 0));
    }

    /**
     * Requests removal of the title currently displayed on this client.
     */
    @Override
    public void clearTitle() {
        sendPacket(getProtocol().getEncoder().encodeTitle(SetTitlePacket.Type.CLEAR, null, 0, 0, 0));
    }

    /**
     * Requests positional or rotational camera shake with the supplied intensity and
     * duration. {@code duration} is measured in seconds; this presentation request
     * does not move the server-side player entity.
     */
    @Override
    public void shakeCamera(CameraShakeType shakeType, float intensity, float duration) {
        sendPacket(getProtocol().getEncoder().encodeCameraShake(shakeType, intensity, duration));
    }

    /**
     * Requests that the client stop its active camera shake presentation.
     */
    @Override
    public void stopCameraShake() {
        sendPacket(getProtocol().getEncoder().encodeCameraShakeStop());
    }

    /**
     * Appends a fog identifier and sends the updated stack to the client. Repeated
     * identifiers are retained in stack order; callers supply resource identifiers
     * understood by the client.
     */
    @Override
    public void pushFog(String fogId) {
        fogStack.add(fogId);
        sendFogStack();
    }

    /**
     * Removes the last matching fog identifier and sends the stack when a match
     * exists. The result is {@code false} when absent; earlier occurrences remain in
     * their previous order.
     */
    @Override
    public boolean popFog(String fogId) {
        for (int i = fogStack.size() - 1; i >= 0; i--) {
            if (fogStack.get(i).equals(fogId)) {
                fogStack.remove(i);
                sendFogStack();
                return true;
            }
        }
        return false;
    }

    /**
     * Removes every matching fog identifier and returns the number removed. The
     * standard implementation sends the resulting stack only when at least one entry
     * changed; other identifiers retain their relative order.
     */
    @Override
    public int removeFog(String fogId) {
        int count = 0;
        var iterator = fogStack.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(fogId)) {
                iterator.remove();
                count++;
            }
        }
        if (count > 0) {
            sendFogStack();
        }
        return count;
    }

    /**
     * Returns an unmodifiable live view of the connection's ordered fog identifiers.
     * Repeated identifiers are retained, and later stack mutations remain visible
     * through the returned view rather than an independent snapshot.
     */
    @Override
    public List<String> getFogs() {
        return Collections.unmodifiableList(fogStack);
    }

    /**
     * Clears every fog identifier and sends the empty stack when entries existed. An
     * already empty stack produces no update; this operation discards the retained
     * order and does not return a prior snapshot.
     */
    @Override
    public void removeAllFogs() {
        if (!fogStack.isEmpty()) {
            fogStack.clear();
            sendFogStack();
        }
    }

    /**
     * Sends the complete current fog stack in its retained order.
     */
    protected void sendFogStack() {
        sendPacket(getProtocol().getEncoder().encodeFogStack(fogStack));
    }

    /**
     * Marks dimension switching active and submits the client loading-screen request
     * before returning. Callers provide the target dimension and approximate
     * coordinates, then invoke {@code completeDimensionChange} after transfer work
     * or to dismiss the presentation after failure.
     */
    @Override
    public void beginDimensionChange(DimensionType targetDimType, double x, double y, double z) {
        this.changingDimension = true;
        sendPacket(getProtocol().getEncoder().encodeDimensionChange(targetDimType, x, y, z));
    }

    /**
     * Clears dimension-switching state and sends the server acknowledgement for the
     * controlled entity. Callers invoke this after adding the entity to its target
     * dimension or when dismissing an unsuccessful transfer's loading screen.
     */
    @Override
    public void completeDimensionChange() {
        this.changingDimension = false;

        // Complete loading presentation with the server-sent dimension-success action after target
        // membership is established; the failure-dismissal path also uses this acknowledgement.
        sendPacket(getProtocol().getEncoder().encodeDimensionChangeSuccess(
                this.controlledEntity.getRuntimeId()
        ));
    }

    /**
     * Queues one packet through the selected protocol after its send event.
     * {@code null} is ignored; before negotiation only bootstrap play-status packets
     * are accepted, and normal return does not confirm remote delivery.
     */
    public void sendPacket(BedrockPacket packet) {
        if (packet == null) {
            return;
        }
        var selectedSession = protocolSession;
        if (selectedSession != null) {
            selectedSession.send(this, packet);
        } else {
            // Before negotiation, route this queued packet through the restricted bootstrap sender so
            // a play-status failure can still be reported without a selected codec.
            sendBootstrapPacket(packet, false);
        }
    }

    /**
     * Queues collection entries in iteration order after validating every entry is
     * non-{@code null}. {@code null} or empty collections are ignored; a later send
     * exception leaves earlier submissions intact and stops subsequent entries.
     */
    public void sendPackets(Collection<? extends BedrockPacket> packets) {
        if (packets == null || packets.isEmpty()) {
            return;
        }
        packets.forEach(packet ->
                Objects.requireNonNull(packet, "packets contains null")
        );
        var selectedSession = protocolSession;
        if (selectedSession != null) {
            selectedSession.send(this, packets);
        } else {
            // Before negotiation, submit queued entries individually through bootstrap validation so
            // each event replacement still obeys its restricted play-status packet contract.
            packets.forEach(packet -> sendBootstrapPacket(packet, false));
        }
    }

    /**
     * Submits one packet immediately through the selected protocol after its send
     * event. {@code null} is ignored; before negotiation only bootstrap play-status
     * packets are accepted, and submission does not confirm remote delivery.
     */
    public void sendPacketImmediately(BedrockPacket packet) {
        if (packet == null) {
            return;
        }
        var selectedSession = protocolSession;
        if (selectedSession != null) {
            selectedSession.sendImmediately(this, packet);
        } else {
            // Before negotiation, use the immediate bootstrap path so a play-status failure can reach
            // transport without requiring the unavailable negotiated session.
            sendBootstrapPacket(packet, true);
        }
    }

    /**
     * Submits collection entries immediately in iteration order after validating
     * every entry is non-{@code null}. {@code null} or empty collections are ignored; a
     * later send exception leaves earlier submissions intact and stops subsequent
     * entries.
     */
    public void sendPacketsImmediately(Collection<? extends BedrockPacket> packets) {
        if (packets == null || packets.isEmpty()) {
            return;
        }
        packets.forEach(packet ->
                Objects.requireNonNull(packet, "packets contains null")
        );
        var selectedSession = protocolSession;
        if (selectedSession != null) {
            selectedSession.sendImmediately(this, packets);
        } else {
            // Before negotiation, submit immediate entries individually through bootstrap validation
            // so each accepted packet retains the restricted pre-negotiation type and event checks.
            packets.forEach(packet -> sendBootstrapPacket(packet, true));
        }
    }

    /**
     * Sends a pre-negotiation play-status packet after applying its send event. The
     * input and any listener replacement must both be non-{@code null}
     * {@code PlayStatusPacket} instances; other packet types are rejected rather
     * than encoded with an unknown protocol.
     *
     * <p>
     * The method checks lifecycle state before dispatch and checks state plus
     * transport connectivity again afterward because a listener can disconnect the
     * connection. Cancellation returns without sending. {@code immediately} chooses
     * transport submission mode; normal return does not confirm remote delivery, and
     * listener or transport exceptions propagate.
     */
    private void sendBootstrapPacket(BedrockPacket packet, boolean immediately) {
        Objects.requireNonNull(packet, "packet");
        if (!(packet instanceof PlayStatusPacket)) {
            throw new IllegalStateException(
                    "Only PlayStatusPacket may be sent before protocol negotiation: " + packet.getPacketType()
            );
        }
        if (!getClientState().canHandlePackets()) {
            return;
        }

        var event = new PacketSendEvent(this, packet);
        if (!event.call()) {
            return;
        }
        // A send listener may replace the packet object; enforce bootstrap type restrictions on its
        // final result rather than trusting the original play-status input.
        var outgoingPacket = event.getPacket();
        if (outgoingPacket == null) {
            throw new IllegalStateException("PacketSendEvent replaced a bootstrap packet with null");
        }
        if (!(outgoingPacket instanceof PlayStatusPacket)) {
            throw new IllegalStateException(
                    "PacketSendEvent replaced a bootstrap packet with " + outgoingPacket.getPacketType()
            );
        }
        // A send listener can disconnect this connection, so recheck both lifecycle and transport
        // before submitting the validated replacement packet through either send mode.
        if (!getClientState().canHandlePackets() || !session.isConnected()) {
            return;
        }

        if (immediately) {
            this.session.sendPacketImmediately(outgoingPacket);
        } else {
            this.session.sendPacket(outgoingPacket);
        }
    }

    /**
     * Encodes command aliases and overloads visible to this player's permissions.
     * Each leaf's path is traversed from the end so parameter positions retain
     * command-tree order; a missing permission discards the entire overload rather
     * than publishing a partial path.
     *
     * <p>
     * The alias set includes the canonical name when aliases exist. If no permitted
     * overload remains, one empty overload is emitted to preserve a command
     * description shape. The description uses the login language, and callers
     * separately filter server-only commands and command-level permissions.
     */
    protected CommandData encodeCommand(Command command) {
        CommandEnumData aliases = null;
        if (!command.getAliases().isEmpty()) {
            var values = new LinkedHashMap<String, Set<CommandEnumConstraint>>();
            command.getAliases().forEach(alias -> values.put(alias, Collections.emptySet()));
            values.put(command.getName(), Collections.emptySet());
            aliases = new CommandEnumData(command.getName() + "CommandAliases", values, false);
        }

        var overloads = new ArrayList<CommandOverloadData>();
        for (var leaf : command.getCommandTree().getLeaves()) {
            var params = new CommandParamData[leaf.depth()];

            var hasPermission = true;
            var node = leaf;
            var index = leaf.depth() - 1;
            // Fill parameters backward while traversing parents so each visible overload keeps its
            // root-to-leaf argument order without exposing a suffix from a denied path.
            while (!node.isRoot()) {
                if (!this.controlledEntity.hasPermissions(node.getPermissions())) {
                    hasPermission = false;
                    break;
                }

                params[index] = ((BaseNode) node).toNetworkData();
                node = node.parent();
                index--;
            }

            if (hasPermission) {
                overloads.add(new CommandOverloadData(false, params));
            }
        }
        // Retain an empty overload when no permission-visible leaf survives so the command description
        // still has an encodable overload entry rather than an empty list.
        if (overloads.isEmpty()) {
            overloads.add(new CommandOverloadData(false, new CommandParamData[0]));
        }

        var flags = new HashSet<CommandData.Flag>();
        flags.add(CommandData.Flag.NOT_CHEAT);
        if (command.isDebugCommand()) {
            flags.add(CommandData.Flag.TEST_USAGE);
        }

        return new CommandData(
                command.getName(), I18n.get().tr(this.loginData.getLangCode(), command.getDescription()),
                flags, CommandPermission.ANY, aliases, overloads.toArray(CommandOverloadData[]::new), List.of()
        );
    }

    /**
     * Claims disconnection under the protocol lifecycle lock before running cleanup
     * and requesting transport closure. A connection already disconnected returns
     * without repeating cleanup, and the previous active state selects the
     * translation language.
     *
     * <p>
     * The lock is released before translation, callbacks, and transport operations
     * so cleanup does not retain the protocol installation lock. Translation occurs
     * outside the guarded cleanup block. A cleanup exception is logged and prevents
     * the later transport-disconnect call in that block; already completed state
     * changes and cleanup steps are not rolled back.
     */
    @Override
    public void disconnect(@MayContainTrKey String reason) {
        ClientState lastClientState;
        // Claim disconnection against the active holder while excluding protocol installation, so only
        // the winning transition schedules cleanup outside the lifecycle lock.
        synchronized (protocolLifecycleLock) {
            var processorHolder = activeProcessorHolder();
            if (!processorHolder.setClientState(ClientState.DISCONNECTED)) {
                log.warn("Trying to disconnect a player who is already disconnected!");
                return;
            }
            lastClientState = processorHolder.getLastClientState();
        }

        String translatedReason;
        if (lastClientState.ordinal() >= ClientState.LOGGED_IN.ordinal()) {
            translatedReason = I18n.get().tr(this.loginData.getLangCode(), reason);
        } else {
            // Before LOGGED_IN, client language metadata is not guaranteed to exist; translate with
            // the default language so the disconnect path can handle early bootstrap failures.
            translatedReason = I18n.get().tr(reason);
        }

        try {
            onDisconnect(translatedReason);
            if (this.session.isConnected()) {
                this.session.disconnect(translatedReason);
            }
        } catch (Throwable t) {
            // Log callback or transport failures after the disconnection claim; already completed cleanup
            // is retained and later operations in the guarded block are not retried.
            log.error("Error while disconnecting the session", t);
        }
    }

    /**
     * Returns the current lifecycle state from the active processor holder.
     */
    @Override
    public ClientState getClientState() {
        return activeProcessorHolder().getClientState();
    }

    /**
     * Requests a lifecycle transition on the active holder while holding the
     * protocol lock. Invalid predecessor transitions are rejected by the holder, and
     * this void wrapper does not expose its boolean acceptance result.
     */
    public void setClientState(ClientState state) {
        // Select the holder and request its transition under the protocol lock so installation cannot
        // replace the holder between selection and state change.
        synchronized (protocolLifecycleLock) {
            activeProcessorHolder().setClientState(state);
        }
    }

    /**
     * Returns the preceding lifecycle state from the active processor holder.
     */
    @Override
    public ClientState getLastClientState() {
        return activeProcessorHolder().getLastClientState();
    }

    /**
     * Installs and publishes an initialized protocol session only while bootstrap
     * state and transport are connected. An existing selected session is rejected
     * rather than replaced, and a disconnect that claims the lifecycle lock first
     * causes a {@code false} result.
     *
     * <p>
     * The lifecycle lock covers the eligibility checks, codec installation, and
     * volatile publication so disconnect cannot switch to a different holder halfway
     * through installation. Codec setup must finish before the new session becomes
     * visible. Installation failures leave {@code protocolSession} unset, but
     * delegated codec rollback can itself fail, so the transport is not guaranteed
     * unchanged.
     *
     * @throws IllegalStateException if a protocol has already been published
     * @throws IllegalArgumentException if {@code protocol} is not initialized
     */
    public boolean installProtocol(Protocol protocol) {
        Objects.requireNonNull(protocol, "protocol");
        // Hold eligibility checks through codec installation and publication so a competing disconnect
        // cannot leave a newly published holder outside its disconnected lifecycle.
        synchronized (protocolLifecycleLock) {
            if (protocolSession != null) {
                throw new IllegalStateException("A protocol is already installed for this connection");
            }
            if (bootstrapProcessorHolder.getClientState() != ClientState.CONNECTED || !session.isConnected()) {
                return false;
            }

            var selectedSession = new ProtocolSession(protocol, session);
            selectedSession.installCodec();
            protocolSession = selectedSession;
            return true;
        }
    }

    /**
     * Switches the transport codec for the already negotiated protocol before login
     * completes. An absent protocol session or disconnected transport returns
     * {@code false}; the delegated session validates the connected state and
     * matching wire version.
     *
     * <p>
     * The lifecycle lock keeps explicit disconnect and protocol publication from
     * interleaving with this switch. The negotiated protocol's processors and
     * definitions remain selected even when another compatible codec is installed.
     * Codec or definition failures propagate after delegated restoration attempts,
     * whose own failure can leave transport state partially changed.
     */
    public boolean switchProtocolCodec(BedrockCodec codec) {
        Objects.requireNonNull(codec, "codec");
        // Keep the selected session connected through delegated codec replacement so explicit
        // disconnect cannot claim the lifecycle while transport codec definitions are being changed.
        synchronized (protocolLifecycleLock) {
            var selectedSession = protocolSession;
            if (selectedSession == null || !session.isConnected()) {
                return false;
            }
            selectedSession.switchCodec(codec);
            return true;
        }
    }

    /**
     * Returns the protocol selected by a successfully published session. Calling
     * before negotiation completes throws rather than returning a bootstrap
     * protocol, so packet-producing viewers require successful installation first.
     *
     * @throws IllegalStateException if no protocol session has been published
     */
    public Protocol getProtocol() {
        var selectedSession = protocolSession;
        if (selectedSession == null) {
            throw new IllegalStateException("Protocol negotiation has not completed");
        }
        return selectedSession.getProtocol();
    }

    /**
     * Returns the negotiated session's holder when one is published, otherwise the
     * bootstrap holder. The volatile session read selects one reference for this
     * call without locking the later holder operation.
     */
    private PacketProcessorHolder activeProcessorHolder() {
        var selectedSession = protocolSession;
        return selectedSession == null ? bootstrapProcessorHolder : selectedSession.getProcessorHolder();
    }

    /**
     * Returns the remote socket address exposed by the retained transport session.
     */
    @Override
    public SocketAddress getSocketAddress() {
        return this.session.getSocketAddress();
    }

    /**
     * Cleans up interface registrations and server membership after disconnection
     * has been claimed. The method removes its active screen first and discards
     * concrete DDUI sessions before dispatching the disconnect event.
     *
     * <p>
     * It then closes open containers, removes chunk-cache tracking for connections
     * that reached spawning, and delegates player removal to the manager. These
     * steps are sequential and may trigger callbacks or storage writes. A failure
     * stops subsequent cleanup; the caller determines whether that failure is
     * caught, and no rollback restores already removed registrations.
     */
    protected void onDisconnect(String disconnectReason) {
        // Detach the active DDUI registration before discarding it so disconnect cleanup cannot send
        // an ordinary screen-close packet through that still-active registration.
        var activeScreen = removeActiveScreen();
        if (activeScreen instanceof AllayDDUIScreenSession session) {
            session.discard();
        }
        new PlayerDisconnectEvent(this, disconnectReason).call();
        closeAllOpenedContainers();
        if (getLastClientState().ordinal() >= ClientState.SPAWNED.ordinal()) {
            ChunkCache.getInstance().removePlayer(this.loginData.getUuid());
        }
        ((AllayPlayerManager) Server.getInstance().getPlayerManager()).removePlayer(this);
    }

    /**
     * Returns transport-measured round-trip latency in milliseconds when available.
     * The standard network interface uses {@code -1} when it cannot provide a
     * measurement, so callers must preserve that unavailable result.
     */
    @Override
    public int getPing() {
        return sourceInterface.getPing(session);
    }

    /**
     * Sends the subject's effective network abilities, permission presentation, and
     * flight speeds. The subject must control an entity, and protocol speed fields
     * use calculated floats.
     *
     * <p>
     * When viewing this connection, adventure settings follow the ability packet,
     * command descriptions are marked dirty, and the pending ability flag is
     * cleared. Viewing another player does not change this connection's deferred
     * flags. The advertised command permission enables presentation features;
     * command descriptions and execution still depend on separate server permission
     * checks.
     */
    @Override
    public void viewPlayerAbilities(Player player) {
        var entity = Preconditions.checkNotNull(player.getControlledEntity());
        // Keep command quick-bar presentation separate from effective interaction capabilities; command
        // descriptions and execution still use the entity's independent server permission checks.
        sendPacket(getProtocol().getEncoder().encodePlayerAbilities(
                entity.getUniqueId().getLeastSignificantBits(),
                entity.hasPermission(Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR).asBoolean()
                        ? CommandPermission.GAME_DIRECTORS
                        : CommandPermission.ANY,
                calculatePlayerPermission(player),
                calculateAbilities(player),
                (float) DEFAULT_SPEED.calculate(),
                (float) player.getFlySpeed().calculate(),
                (float) player.getVerticalFlySpeed().calculate()
        ));

        if (player == this) {
            sendAdventureSettings();
            this.shouldSendCommands = true;
            this.shouldSendAbilities = false;
        }
    }

    /**
     * Sends this connection's abilities to itself and its other player viewers when
     * an entity exists. Nonplayer viewers are skipped, and the self update also
     * refreshes adventure settings and schedules commands.
     */
    protected void broadcastPlayerAbilities() {
        if (this.controlledEntity == null) {
            return;
        }

        viewPlayerAbilities(this);
        this.controlledEntity.forEachViewers(viewer -> {
            if (viewer instanceof Player playerViewer && playerViewer != this) {
                playerViewer.viewPlayerAbilities(this);
            }
        });
    }

    /**
     * Builds protocol capability flags from the subject's tracked abilities and
     * effective flight policy. Explicit mapping preserves API semantics when
     * protocol capability names differ from the API enum.
     *
     * <p>
     * The operator quick-bar permission adds the protocol operator flag when an
     * entity exists. A player unable to fly loses both flying flags, while effective
     * always-flying policy adds both afterward. That final override is intentional
     * in this calculation's order, and separate queries can observe changing player
     * state because this method does not take a snapshot lock.
     */
    protected EnumSet<Ability> calculateAbilities(Player player) {
        var abilities = EnumSet.noneOf(Ability.class);
        for (var ability : player.getAbilities()) {
            abilities.add(toNetworkAbility(ability));
        }

        if (player.getControlledEntity() != null && player.getControlledEntity().hasPermission(Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR).asBoolean()) {
            abilities.add(Ability.OPERATOR_COMMANDS);
        }

        // Normalize tracked flying flags against effective flight permission before the forced-flight
        // override, so the final advertised layer follows this calculation's explicit precedence.
        if (!player.canFly()) {
            abilities.remove(Ability.MAY_FLY);
            abilities.remove(Ability.FLYING);
        }

        if (player.isAlwaysFlying()) {
            abilities.add(Ability.MAY_FLY);
            abilities.add(Ability.FLYING);
        }

        return abilities;
    }

    /**
     * Selects protocol permission presentation from six effective interaction
     * capabilities and stored operator status. A player with all six capabilities is
     * {@code OPERATOR} only when also listed as an operator; otherwise that complete
     * capability set is {@code MEMBER}.
     *
     * <p>
     * No capabilities yields {@code VISITOR}, and any mixed selection yields
     * {@code CUSTOM}. The six queries are evaluated before the category decision and
     * are not captured under a common state lock, so callers must coordinate player
     * policy changes when they need a consistent presentation.
     */
    protected PlayerPermission calculatePlayerPermission(Player player) {
        var build = player.canPlaceBlocks();
        var mine = player.canBreakBlocks();
        var doorsAndSwitches = player.canInteractWithBlocks();
        var openContainers = player.canOpenContainers();
        var attackPlayers = player.canAttackPlayers();
        var attackMobs = player.canAttackMobs();

        if (
            Server.getInstance().getPlayerManager().isOperator(player) &&
            build && mine && doorsAndSwitches && openContainers && attackPlayers && attackMobs
        ) {
            return PlayerPermission.OPERATOR;
        }

        if (build && mine && doorsAndSwitches && openContainers && attackPlayers && attackMobs) {
            return PlayerPermission.MEMBER;
        }

        if (!build && !mine && !doorsAndSwitches && !openContainers && !attackPlayers && !attackMobs) {
            return PlayerPermission.VISITOR;
        }

        return PlayerPermission.CUSTOM;
    }

    /**
     * Sends interaction and immutable-world settings only after an entity exists and
     * spawning has begun. Both encoded attack-related restriction arguments
     * currently use {@code !canAttackMobs()}; this helper does not independently
     * consult player-attack permission for that packet.
     */
    protected void sendAdventureSettings() {
        if (this.controlledEntity == null || getClientState().ordinal() < ClientState.SPAWNED.ordinal()) {
            return;
        }

        sendPacket(getProtocol().getEncoder().encodeAdventureSettings(
                !canAttackMobs(),
                !canAttackMobs(),
                this.controlledEntity.getGameMode() != GameMode.SPECTATOR,
                isImmutableWorld()
        ));
    }

    /**
     * Returns an empty typed ability set for building permission defaults.
     */
    protected static EnumSet<PlayerAbility> createBaseAbilitySet() {
        return EnumSet.noneOf(PlayerAbility.class);
    }

    /**
     * Creates initial interaction abilities from the configured protocol permission
     * category. {@code OPERATOR} and {@code MEMBER} grant the same six interaction
     * flags here; {@code VISITOR} and {@code CUSTOM} produce an empty set before
     * game-mode defaults are added.
     */
    protected static EnumSet<PlayerAbility> abilitiesFromPermission(PlayerPermission permission) {
        var abilities = createBaseAbilitySet();
        // Derive only interaction grants from the permission category; flight and creative behavior
        // are added later from game mode when stored overrides are absent.
        switch (permission) {
            case OPERATOR -> abilities.addAll(EnumSet.of(
                    PlayerAbility.PLACE_BLOCK,
                    PlayerAbility.BREAK_BLOCK,
                    PlayerAbility.INTERACT_BLOCK,
                    PlayerAbility.OPEN_CONTAINER,
                    PlayerAbility.ATTACK_PLAYER,
                    PlayerAbility.ATTACK_MOB
            ));
            case MEMBER -> abilities.addAll(EnumSet.of(
                    PlayerAbility.PLACE_BLOCK,
                    PlayerAbility.BREAK_BLOCK,
                    PlayerAbility.INTERACT_BLOCK,
                    PlayerAbility.OPEN_CONTAINER,
                    PlayerAbility.ATTACK_PLAYER,
                    PlayerAbility.ATTACK_MOB
            ));
            case VISITOR, CUSTOM -> {
                // VISITOR and CUSTOM add no interaction grants here; later game-mode defaults may still
                // contribute flight or creative flags when no explicit saved ability set exists.
            }
        }
        return abilities;
    }

    /**
     * Maps one tracked API ability to its corresponding protocol capability flag.
     */
    protected Ability toNetworkAbility(PlayerAbility ability) {
        // Map by capability meaning rather than passing enum values through: API action names and
        // protocol flag names belong to separate representations.
        return switch (ability) {
            case PLACE_BLOCK -> Ability.BUILD;
            case BREAK_BLOCK -> Ability.MINE;
            case INTERACT_BLOCK -> Ability.DOORS_AND_SWITCHES;
            case OPEN_CONTAINER -> Ability.OPEN_CONTAINERS;
            case ATTACK_PLAYER -> Ability.ATTACK_PLAYERS;
            case ATTACK_MOB -> Ability.ATTACK_MOBS;
            case FLYING -> Ability.FLYING;
            case MAY_FLY -> Ability.MAY_FLY;
            case INFINITE_BLOCK -> Ability.INSTABUILD;
            case NO_CLIP -> Ability.NO_CLIP;
        };
    }

    /**
     * Dispatches a noncancellable ability-update event when a controlled entity
     * exists, then marks client abilities dirty. The event captures snapshots, while
     * this helper retains the changed connection set for a later tick broadcast.
     */
    protected void scheduleAbilitiesUpdate(Set<PlayerAbility> previous) {
        if (this.controlledEntity != null) {
            new PlayerAbilitiesUpdateEvent(this, previous, this.abilities).call();
        }
        this.shouldSendAbilities = true;
    }

    /**
     * Returns an unmodifiable live view of this connection's tracked abilities. The
     * view reflects later changes and is not an independent snapshot; effective
     * capability queries also consider mode and policy.
     */
    @Override
    public @UnmodifiableView Set<PlayerAbility> getAbilities() {
        return Collections.unmodifiableSet(abilities);
    }

    /**
     * Returns whether this connection's tracked set contains the supplied ability.
     */
    @Override
    public boolean hasAbility(PlayerAbility ability) {
        return this.abilities.contains(ability);
    }

    /**
     * Changes one ability only when the requested membership differs from current
     * state. Callers must coordinate this mutable connection state and provide an
     * entity whenever flight reconciliation needs to change its flying flag.
     *
     * <p>
     * The method captures prior effective flight policy and a separate ability
     * snapshot before mutation. It then dispatches the post-change update event and
     * marks client synchronization pending before reconciling flying state. A
     * callback failure can interrupt that later reconciliation without restoring the
     * changed ability set.
     */
    @Override
    public void setAbility(PlayerAbility ability, boolean value) {
        if (this.abilities.contains(ability) == value) {
            return;
        }

        // Capture effective flight before changing abilities; the later comparison needs the prior
        // policy rather than deriving both sides from the already mutated set.
        var wasAlwaysFlying = isAlwaysFlying();
        var couldFly = canFly();

        var snap = EnumSet.copyOf(this.abilities);
        if (value) {
            this.abilities.add(ability);
        } else {
            this.abilities.remove(ability);
        }
        scheduleAbilitiesUpdate(snap);

        syncFlyingState(wasAlwaysFlying, couldFly);
    }

    /**
     * Changes the supplied ability memberships only when the requested membership
     * differs from current state. Callers must coordinate this mutable connection
     * state and provide an entity whenever flight reconciliation needs to change its
     * flying flag.
     *
     * <p>
     * The method captures prior effective flight policy and a separate ability
     * snapshot before mutation. It then dispatches the post-change update event and
     * marks client synchronization pending before reconciling flying state. A
     * callback failure can interrupt that later reconciliation without restoring the
     * changed ability set.
     */
    @Override
    public void setAbilities(Set<PlayerAbility> abilities, boolean value) {
        if ((value && this.abilities.containsAll(abilities)) || (!value && Collections.disjoint(this.abilities, abilities))) {
            return;
        }

        // Capture effective flight before changing abilities; the later comparison needs the prior
        // policy rather than deriving both sides from the already mutated set.
        var wasAlwaysFlying = isAlwaysFlying();
        var couldFly = canFly();

        var snap = EnumSet.copyOf(this.abilities);
        if (value) {
            this.abilities.addAll(abilities);
        } else {
            this.abilities.removeAll(abilities);
        }
        scheduleAbilitiesUpdate(snap);

        syncFlyingState(wasAlwaysFlying, couldFly);
    }

    /**
     * Replaces the complete ability set only when the requested contents differ from
     * current state. The full live {@code getAbilities()} view compares equal and
     * returns unchanged; a differing derived view backed by the same storage must be
     * copied before replacement clears that storage.
     *
     * <p>
     * The method captures prior effective flight policy and a separate ability
     * snapshot before mutation. It then dispatches the post-change update event and
     * marks client synchronization pending before reconciling flying state. A
     * callback failure can interrupt that later reconciliation without restoring the
     * changed ability set.
     */
    @Override
    public void setAbilities(Set<PlayerAbility> abilities) {
        if (this.abilities.equals(abilities)) {
            return;
        }

        // Capture effective flight before changing abilities; the later comparison needs the prior
        // policy rather than deriving both sides from the already mutated set.
        var wasAlwaysFlying = isAlwaysFlying();
        var couldFly = canFly();

        var snap = EnumSet.copyOf(this.abilities);
        this.abilities.clear();
        this.abilities.addAll(abilities);
        scheduleAbilitiesUpdate(snap);

        syncFlyingState(wasAlwaysFlying, couldFly);
    }

    /**
     * Returns whether effective world policy and abilities allow this player to
     * place blocks. The standard implementation first rejects effective
     * immutable-world state and then rejects {@code ADVENTURE}, including operators
     * in that mode.
     *
     * <p>
     * An operator otherwise succeeds without requiring {@code PLACE_BLOCK}; other
     * players need that ability. Operator status bypasses the immutable-world query,
     * including its spectator-derived result, so this helper alone does not reject
     * every operator in {@code SPECTATOR}. Callers must preserve any additional
     * action-specific validation.
     */
    @Override
    public boolean canPlaceBlocks() {
        if (isImmutableWorld()) {
            return false;
        }
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.ADVENTURE) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.PLACE_BLOCK);
    }

    /**
     * Returns whether effective world policy and abilities allow this player to
     * break blocks. The standard implementation first rejects effective
     * immutable-world state and then rejects {@code ADVENTURE}, including operators
     * in that mode.
     *
     * <p>
     * An operator otherwise succeeds without requiring {@code BREAK_BLOCK}; other
     * players need that ability. Operator status bypasses the immutable-world query,
     * including its spectator-derived result, so this helper alone does not reject
     * every operator in {@code SPECTATOR}. Callers must preserve any additional
     * action-specific validation.
     */
    @Override
    public boolean canBreakBlocks() {
        if (isImmutableWorld()) {
            return false;
        }
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.ADVENTURE) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.BREAK_BLOCK);
    }

    /**
     * Returns whether effective world policy and abilities permit block interaction.
     * The standard implementation rejects effective immutable-world state before
     * explicitly rejecting {@code SPECTATOR}, so spectator operators remain rejected
     * by this helper.
     *
     * <p>
     * An operator outside spectator mode succeeds without requiring
     * {@code INTERACT_BLOCK}. Other players need that ability after the
     * immutable-world check; adventure mode contributes to immutable-world state for
     * nonoperators. This query does not perform reach checks or dispatch a
     * block-interaction event, which remain responsibilities of the action path.
     */
    @Override
    public boolean canInteractWithBlocks() {
        if (isImmutableWorld()) {
            return false;
        }
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.INTERACT_BLOCK);
    }

    /**
     * Returns whether game mode and abilities permit opening containers. The
     * standard implementation rejects {@code SPECTATOR} before considering operator
     * status, so an operator cannot bypass that mode restriction through this
     * helper.
     *
     * <p>
     * Other operators succeed without requiring {@code OPEN_CONTAINER}; remaining
     * players need that ability. The helper does not consult the independent
     * immutable-world flag or reject adventure mode separately. Callers still
     * validate the target container and its reachability before applying a
     * particular open request.
     */
    @Override
    public boolean canOpenContainers() {
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.OPEN_CONTAINER);
    }

    /**
     * Returns whether game mode and abilities permit attacking another player. The
     * standard implementation rejects {@code SPECTATOR} before considering operator
     * status, so an operator cannot bypass that mode restriction through this
     * helper.
     *
     * <p>
     * Other operators succeed without requiring {@code ATTACK_PLAYER}; remaining
     * players need that ability. The query does not enforce world damage rules,
     * distance, or target validity. The attacking path must still apply those rules
     * before it changes the target's health or combat state.
     */
    @Override
    public boolean canAttackPlayers() {
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.ATTACK_PLAYER);
    }

    /**
     * Returns whether game mode and abilities permit attacking nonplayer mobs. The
     * standard implementation rejects {@code SPECTATOR} before considering operator
     * status, so an operator cannot bypass that mode restriction through this
     * helper.
     *
     * <p>
     * Other operators succeed without requiring {@code ATTACK_MOB}; remaining
     * players need that ability. The query does not enforce world damage rules,
     * distance, or target validity. The attacking path must still apply those rules
     * before it changes the target's health or combat state.
     */
    @Override
    public boolean canAttackMobs() {
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.ATTACK_MOB);
    }

    /**
     * Returns whether a controlled player entity is permitted to fly. The standard
     * implementation requires an entity even when stored flags would otherwise force
     * flight, so connections without an entity return {@code false}.
     *
     * <p>
     * With an entity, effective always-flying state or {@code MAY_FLY} grants
     * permission. The stored {@code FLYING} ability alone does not grant that
     * permission. This query does not start flight; input handling and
     * ability-update synchronization apply the entity's flying state separately.
     */
    @Override
    public boolean canFly() {
        return this.controlledEntity != null && (isAlwaysFlying() || hasAbility(PlayerAbility.MAY_FLY));
    }

    /**
     * Returns whether this connection tracks the {@code INFINITE_BLOCK} ability.
     */
    @Override
    public boolean hasInfiniteBlock() {
        return hasAbility(PlayerAbility.INFINITE_BLOCK);
    }

    /**
     * Returns whether a controlled entity is treated as moving through blocks. The
     * standard implementation requires a controlled entity, then accepts either
     * {@code NO_CLIP} or {@code SPECTATOR}; a connection without an entity returns
     * {@code false}.
     *
     * <p>
     * Operator status does not grant this result by itself. The query describes
     * effective capability rather than changing entity motion or resolving
     * collisions, and callers must still use the physics and movement paths for
     * actual location updates.
     */
    @Override
    public boolean isNoClip() {
        return this.controlledEntity != null && (hasAbility(PlayerAbility.NO_CLIP) || this.controlledEntity.getGameMode() == GameMode.SPECTATOR);
    }

    /**
     * Returns whether this connection is effectively restricted from world editing.
     * The standard implementation gives operator status precedence and returns
     * {@code false} for operators even when an explicit flag or restrictive mode is
     * present.
     *
     * <p>
     * For other players, the explicit immutable-world flag takes precedence,
     * followed by {@code ADVENTURE} or {@code SPECTATOR} on a controlled entity. A
     * worldless connection without the explicit flag returns {@code false}.
     * Individual action queries may impose further restrictions after consulting
     * this result.
     */
    @Override
    public boolean isImmutableWorld() {
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return false;
        }

        if (this.immutableWorld) {
            return true;
        }

        if (this.controlledEntity != null) {
            return this.controlledEntity.getGameMode() == GameMode.SPECTATOR || this.controlledEntity.getGameMode() == GameMode.ADVENTURE;
        }

        return false;
    }

    /**
     * Sets the explicit immutable-world flag and schedules an ability refresh when
     * it changes. Effective policy still gives operator status precedence and may
     * derive immutability from the controlled entity's game mode.
     */
    @Override
    public void setImmutableWorld(boolean immutableWorld) {
        if (this.immutableWorld != immutableWorld) {
            this.immutableWorld = immutableWorld;
            this.shouldSendAbilities = true;
        }
    }

    /**
     * Returns whether flight is forced by the connection's explicit flag,
     * {@code NO_CLIP}, or a controlled spectator entity. The standard implementation
     * evaluates these alternatives independently, so an explicit flag or no-clip
     * ability can return {@code true} before an entity exists.
     *
     * <p>
     * This result describes effective policy and does not itself alter entity state.
     * {@code canFly} adds its own entity requirement, and ability-changing
     * operations synchronize the entity's flying flag using the policy observed
     * before and after their change.
     */
    @Override
    public boolean isAlwaysFlying() {
        return this.alwaysFlying || hasAbility(PlayerAbility.NO_CLIP) || (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.SPECTATOR);
    }

    /**
     * Updates the raw forced-flight flag, marks abilities dirty, and invokes
     * flying-state synchronization. Callers must provide a controlled entity
     * whenever the resulting synchronization branch changes its flying state.
     *
     * <p>
     * This setter passes the previous raw {@code alwaysFlying} value and the
     * post-update {@code canFly()} result to {@code syncFlyingState}. Those
     * arguments differ from the prior effective observations used by ability
     * setters. In particular, clearing the last forced-flight source can leave the
     * entity flying when the post-update permission is already {@code false}; this
     * method does not guarantee complete flight-state normalization.
     */
    @Override
    public void setAlwaysFlying(boolean alwaysFlying) {
        var wasAlwaysFlying = this.alwaysFlying;
        this.alwaysFlying = alwaysFlying;
        this.shouldSendAbilities = true;
        syncFlyingState(wasAlwaysFlying, canFly());
    }

    /**
     * Synchronizes the entity flying flag from earlier and current effective policy
     * observations. Callers supply the previous always-flying and flight-permission
     * values, and must have a controlled entity whenever a branch updates its state.
     *
     * <p>
     * Newly forced flight takes precedence and sets flying to {@code true}.
     * Otherwise a transition from permitted flight to no permission clears flying.
     * The method does not generally mirror policy into the flag: unchanged policies
     * and callers passing post-change permission leave the current entity flying
     * state untouched.
     */
    protected void syncFlyingState(boolean wasAlwaysFlying, boolean couldFly) {
        // Prioritize newly forced flight over revocation; otherwise stop flight only when the caller's
        // earlier permission existed and the current effective permission has disappeared.
        if (!wasAlwaysFlying && isAlwaysFlying()) {
            this.controlledEntity.setFlying(true);
        } else if (couldFly && !canFly()) {
            this.controlledEntity.setFlying(false);
        }
    }

    /**
     * Sends player-list additions or removals for the supplied connections to this
     * client. {@code add} selects the operation, and the standard encoder reads each
     * subject's initialized login, entity, and skin data.
     */
    @Override
    public void viewPlayerListChange(Collection<Player> players, boolean add) {
        sendPacket(getProtocol().getEncoder().encodePlayerList(
                players,
                add,
                AllayServer.getSettings().resourcePackSettings().trustAllSkins()
        ));
    }

    /**
     * Replaces the walking speed configuration and sends it when changed. The
     * standard implementation requires a controlled entity for its attribute packet
     * and does not validate the speed's numeric range.
     */
    @Override
    public void setSpeed(Speed speed) {
        if (!this.speed.equals(speed)) {
            this.speed = speed;
            sendSpeed(this.speed);
        }
    }

    /**
     * Sends the calculated walking speed as a float movement attribute with the
     * default baseline. The connection must control an entity, and the
     * double-to-float conversion follows the protocol attribute representation
     * without additional range validation.
     */
    protected void sendSpeed(Speed speed) {
        sendAttribute(new AttributeData(
                "minecraft:movement", 0, Float.MAX_VALUE, (float) speed.calculate(), 0,
                Float.MAX_VALUE, (float) DEFAULT_SPEED.calculate(), Collections.emptyList()
        ));
    }

    /**
     * Replaces the horizontal flying speed and refreshes this player's client
     * abilities when changed. The standard implementation requires a controlled
     * entity and sends the calculated value through its float protocol
     * representation.
     */
    @Override
    public void setFlySpeed(Speed flySpeed) {
        if (!this.flySpeed.equals(flySpeed)) {
            this.flySpeed = flySpeed;
            viewPlayerAbilities(this);
        }
    }

    /**
     * Replaces the vertical flying speed and refreshes this player's client
     * abilities when changed. The standard implementation requires a controlled
     * entity and sends the calculated value through its float protocol
     * representation.
     */
    @Override
    public void setVerticalFlySpeed(Speed verticalFlySpeed) {
        if (!this.verticalFlySpeed.equals(verticalFlySpeed)) {
            this.verticalFlySpeed = verticalFlySpeed;
            viewPlayerAbilities(this);
        }
    }

    /**
     * Sends a requested motion vector for the controlled player entity to the
     * client. The standard implementation encodes the vector without changing the
     * server entity's stored motion through this helper.
     */
    @Override
    public void setMotion(Vector3dc m) {
        sendPacket(getProtocol().getEncoder().encodeEntityMotion(this.controlledEntity, m));
    }

    /**
     * Records whether the selected HUD element is visible and schedules a client
     * update when changed. The standard implementation batches the visibility state
     * until its next tick rather than sending immediately.
     */
    @Override
    public void setHudElementVisibility(HudElement element, boolean visible) {
        if (visible == getHudElementVisibility(element)) {
            return;
        }

        if (!visible) {
            this.hiddenHudElements.add(element);
        } else {
            this.hiddenHudElements.remove(element);
        }

        this.shouldSendHudElements = true;
    }

    /**
     * Returns whether the selected HUD element is absent from the hidden set.
     */
    @Override
    public boolean getHudElementVisibility(HudElement element) {
        return !this.hiddenHudElements.contains(element);
    }

    /**
     * Requests that the client connect to the supplied server address and port. This
     * sends a transfer packet; it does not itself connect the server to that
     * destination or confirm the client's arrival.
     */
    @Override
    public void transfer(String ip, int port) {
        sendPacket(getProtocol().getEncoder().encodeTransfer(ip, port));
    }

    /**
     * Restores the saved destination, initializes abilities, creates the controlled
     * player entity, and begins its spawning protocol sequence. Callers must already
     * have decoded login data, a negotiated protocol, and a lifecycle ready to
     * transition from {@code LOGGED_IN}.
     *
     * <p>
     * An unavailable saved world or dimension is replaced with the global spawn
     * point and persisted before entity creation. Absent stored abilities combine
     * configured permission defaults with the saved or world game mode; an explicit
     * set is restored instead. The spawn event can reject joining or change the
     * entity destination, and accepted destination changes are written back so later
     * NBT loading uses that location.
     *
     * <p>
     * Dimension definitions and voxel-shape data precede start-game data, which
     * precedes dimension registration and chunk delivery. Entity NBT is deliberately
     * deferred to {@code onFullyJoin}, which reads storage again. These writes,
     * event effects, and packets have no common rollback, so an intermediate failure
     * can leave partial spawning work visible.
     */
    public void spawnEntityPlayer() {
        var server = Server.getInstance();
        var playerManager = (AllayPlayerManager) server.getPlayerManager();
        var playerData = playerManager.getPlayerStorage().readPlayerData(this);

        AllayDimension dimension;
        Vector3fc currentPos;

        var logOffWorld = server.getWorldPool().getWorld(playerData.getWorld());
        var logOffDimension = logOffWorld == null ? null : logOffWorld.getDimension(new Identifier(playerData.getDimension()));
        if (logOffDimension == null) {
            // A saved destination can disappear between sessions; replace its world, dimension, and
            // position together so the later storage reread uses a coherent available spawn destination.
            dimension = (AllayDimension) server.getWorldPool().getGlobalSpawnPoint().dimension();
            currentPos = new org.joml.Vector3f(server.getWorldPool().getGlobalSpawnPoint());
            var currentWorldName = dimension.getWorld().getWorldData().getDisplayName();
            var currentDimension = dimension.getDimensionType().getIdentifier().toString();

            // Update the NBT position as well as its envelope destination before persisting, because
            // full-join loading later applies this stored entity payload again.
            var builder = playerData.getNbt().toBuilder();
            writeVector3f(builder, "Pos", currentPos);
            playerData.setNbt(builder.build());
            playerData.setWorld(currentWorldName);
            playerData.setDimension(currentDimension);

            playerManager.getPlayerStorage().savePlayerData(this.loginData.getUuid(), playerData);
        } else {
            dimension = (AllayDimension) logOffDimension;
            currentPos = readVector3f(playerData.getNbt(), "Pos");
        }

        var storedAbilities = playerData.getAbilities();
        // Distinguish an absent saved override from an explicitly empty set; only absence authorizes
        // deriving configured permission grants and game-mode default abilities.
        if (storedAbilities == null) {
            var permissionName = AllayServer.getSettings().genericSettings().defaultPermission().toUpperCase();
            this.abilities.addAll(abilitiesFromPermission(PlayerPermission.valueOf(permissionName)));

            var gameMode = GameMode.from(playerData.getNbt().getInt("PlayerGameMode", NetworkHelper.toNetwork(dimension.getWorld().getWorldData().getGameMode()).ordinal()));
            this.abilities.addAll(gameMode.getAbilities());
        } else {
            this.abilities.addAll(storedAbilities);
        }

        this.controlledEntity = EntityTypes.PLAYER.createEntity();
        this.controlledEntity.setSkin(this.loginData.getSkin());
        this.controlledEntity.setDisplayName(loginData.getXname());
        this.controlledEntity.setNameTag(loginData.getXname());
        this.controlledEntity.setNameTagAlwaysShow(true);
        this.controlledEntity.setLocationBeforeSpawn(new Location3d(currentPos.x(), currentPos.y(), currentPos.z(), dimension));

        var baseComponent = (EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) this.controlledEntity).getBaseComponent();
        baseComponent.setController(this);
        baseComponent.setUniqueId(this.loginData.getUuid());
        baseComponent.setPermissionCalculator(new OpPermissionCalculator(this));

        var event = new PlayerSpawnEvent(this, TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
        if (!event.call()) {
            disconnect(event.getDisconnectReason());
            return;
        }

        var loc = this.controlledEntity.getLocation();
        dimension = (AllayDimension) loc.dimension();
        var currentWorldName = dimension.getWorld().getWorldData().getDisplayName();
        var currentDimension = dimension.getDimensionType().getIdentifier().toString();
        var currentRotation = readVector2f(playerData.getNbt(), "Rotation");

        var positionChanged = loc.x() != currentPos.x() || loc.y() != currentPos.y() || loc.z() != currentPos.z();
        var rotationChanged = (float) loc.yaw() != currentRotation.x() || (float) loc.pitch() != currentRotation.y();
        var worldChanged = !Objects.equals(playerData.getWorld(), currentWorldName);
        var dimensionChanged = !Objects.equals(playerData.getDimension(), currentDimension);
        // Persist accepted spawn-event location changes before full-join loading rereads entity NBT,
        // so its old destination does not undo the event's chosen spawn location.
        if (positionChanged || rotationChanged || worldChanged || dimensionChanged) {
            if (positionChanged || rotationChanged) {
                var builder = playerData.getNbt().toBuilder();
                writeVector3f(builder, "Pos", new org.joml.Vector3f(loc));
                writeVector2f(builder, "Rotation", (float) loc.yaw(), (float) loc.pitch());
                playerData.setNbt(builder.build());
            }

            playerData.setWorld(currentWorldName);
            playerData.setDimension(currentDimension);

            playerManager.getPlayerStorage().savePlayerData(this.getLoginData().getUuid(), playerData);
        }

        setClientState(ClientState.SPAWNED);

        // Submit dimension definitions and voxel-shape data before start-game state so subsequent
        // spawning packets can refer to the definitions selected for this protocol.
        var encoder = getProtocol().getEncoder();
        sendPacket(encoder.encodeDimensionData());
        sendPacket(encoder.encodeJigsawStructureData());
        sendPackets(encoder.encodeVoxelShapes());

        // Submit start-game state before dimension registration can deliver chunks; those payloads
        // depend on the client having the world and controlled-entity initialization first.
        startGame(dimension.getWorld(), playerData, dimension);

        dimension.addPlayer(this);
        playerManager.addPlayer(this);

        sendPacket(encoder.encodeItemRegistry());
        sendPacket(encoder.encodeCreativeContent());
        sendPacket(encoder.encodeAvailableEntityIdentifiers());
        sendPackets(encoder.encodeSyncEntityProperties());
        sendPacket(encoder.encodeBiomeDefinitions());
        sendPacket(encoder.encodeCraftingData());
        sendPacket(encoder.encodeTrimData());
    }

    /**
     * Sends the start-game representation for the selected spawn world, saved data,
     * and controlled entity. Callers send required dimension definitions first and
     * invoke this before dimension membership can start delivering chunks.
     */
    protected void startGame(World spawnWorld, PlayerData playerData, Dimension dimension) {
        sendPacket(getProtocol().getEncoder().encodeStartGame(
                spawnWorld,
                playerData,
                dimension,
                this.controlledEntity
        ));
    }

    /**
     * Checks current capacity and dispatches the cancellable login event before
     * sending login success and resource-pack information. Rejection disconnects
     * with the selected reason; this count check does not reserve a slot against
     * concurrent logins.
     */
    public void completeLogin() {
        var playerManager = (AllayPlayerManager) Server.getInstance().getPlayerManager();
        // Reject an already full server before dispatching login success, while leaving actual
        // membership registration to spawning rather than reserving capacity in this check.
        if (playerManager.getPlayerCount() >= playerManager.getMaxPlayerCount()) {
            disconnect(TrKeys.MC_DISCONNECTIONSCREEN_SERVERFULL_TITLE);
            return;
        }

        var event = new PlayerLoginEvent(this, TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
        if (!event.call()) {
            disconnect(event.getDisconnectReason());
            return;
        }

        setClientState(ClientState.LOGGED_IN);
        sendPlayStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);
        sendPacket(getProtocol().getEncoder().encodeResourcePacksInfo());
    }

    /**
     * Creates and sends a play-status packet containing the supplied status value.
     */
    protected void sendPlayStatus(PlayStatusPacket.Status status) {
        var packet = new PlayStatusPacket();
        packet.setStatus(status);
        sendPacket(packet);
    }

    /**
     * Sends descriptions for non-server-only commands whose root permissions the
     * controlled entity satisfies. Each command's overloads are separately filtered
     * during encoding, and the login language supplies translated descriptions.
     */
    protected void sendCommands() {
        var commands = Registries.COMMANDS.getContent().values().stream()
                .filter(command -> !command.isServerSideOnly() && this.controlledEntity.hasPermissions(command.getPermissions()))
                .map(this::encodeCommand)
                .toList();
        sendPacket(getProtocol().getEncoder().encodeCommands(commands));
    }

    /**
     * Sends protocol visibility updates for the current hidden HUD element set.
     */
    protected void sendHudElements() {
        sendPackets(getProtocol().getEncoder().encodeHudElements(hiddenHudElements));
    }

    /**
     * Retains the dialog and its presenting entity before sending dialog packets.
     * This pair is used by close handling; packet cancellation or encoding failure
     * does not undo the stored registration.
     */
    @Override
    public void viewDialog(Dialog dialog, Entity entity) {
        this.dialog = new Pair<>(dialog, entity);
        sendPackets(getProtocol().getEncoder().encodeDialog(dialog, entity));
    }

    /**
     * Returns the currently retained dialog and presenting entity, or {@code null}
     * when absent. The pair represents server bookkeeping; this accessor does not
     * confirm that the client displayed its dialog packets.
     */
    @Override
    public Pair<Dialog, Entity> getDialog() {
        return this.dialog;
    }

    /**
     * Clears the retained dialog pair without sending a client close request.
     * Callers needing visible closure use {@code closeDialog}; removing this
     * reference alone leaves client presentation to another action or packet.
     */
    @Override
    public void removeDialog() {
        this.dialog = null;
    }

    /**
     * Sends a close request for the retained dialog entity and then clears its
     * registration. An absent dialog is a no-op; a propagated encoding failure
     * leaves the registration in place.
     */
    @Override
    public void closeDialog() {
        if (this.dialog == null) {
            return;
        }

        sendPacket(getProtocol().getEncoder().encodeDialogClose(this.dialog.right()));

        this.dialog = null;
    }

    /**
     * Dispatches pre-negotiation input through receive listeners and the bootstrap
     * registry. Disabled handling, cancelled events, {@code null} replacements, and
     * unregistered packet types are consumed without calling a processor.
     *
     * <p>
     * The accepted replacement determines processor selection. Its asynchronous
     * phase runs first, and an unhandled result invokes the synchronous phase
     * directly because no negotiated world queue is selected here. The receive time
     * is the server tick observed after processor lookup. Normal completion returns
     * {@code HANDLED}; listener and processor exceptions propagate.
     */
    private PacketSignal handleBootstrapPacket(BedrockPacket packet) {
        if (!getClientState().canHandlePackets()) {
            return PacketSignal.HANDLED;
        }

        var event = new PacketReceiveEvent(this, packet);
        if (!event.call()) {
            return PacketSignal.HANDLED;
        }

        packet = event.getPacket();
        if (packet == null) {
            log.warn("Ignored a null packet produced by PacketReceiveEvent before protocol negotiation");
            return PacketSignal.HANDLED;
        }
        // Resolve the listener-selected packet in the bootstrap state; unsupported replacements are
        // consumed because no negotiated processor can safely handle them before protocol selection.
        var processor = bootstrapProcessorHolder.getProcessor(packet);
        if (processor == null) {
            log.debug("Received a packet before protocol negotiation without a bootstrap processor: {}", packet);
            return PacketSignal.HANDLED;
        }

        var receiveTime = Server.getInstance().getTick();
        // Run unhandled bootstrap work synchronously on this thread because the connection has no
        // negotiated world queue to own that second processing phase.
        if (processor.handleAsync(this, packet, receiveTime) != PacketSignal.HANDLED) {
            processor.handleSync(this, packet, receiveTime);
        }
        return PacketSignal.HANDLED;
    }

    /**
     * {@code AllayPacketHandler} routes transport callbacks through bootstrap or
     * negotiated processing for this connection. Its disconnect callback claims the
     * same lifecycle transition as explicit disconnection before invoking outer
     * player cleanup.
     */
    private class AllayPacketHandler implements BedrockPacketHandler {
        /**
         * Routes the packet to the negotiated session observed for this callback or
         * to bootstrap handling. A concurrently published session can affect later
         * callbacks without changing this call's selected reference.
         */
        @Override
        public PacketSignal handlePacket(BedrockPacket packet) {
            var selectedSession = protocolSession;
            return selectedSession == null
                    ? handleBootstrapPacket(packet)
                    : selectedSession.receive(AllayPlayer.this, packet);
        }

        /**
         * Claims transport-originated disconnection under the same lifecycle lock as
         * protocol installation and explicit disconnect. If another path already
         * moved the active holder to {@code DISCONNECTED}, this callback returns
         * without repeating player cleanup.
         *
         * <p>
         * The state claim chooses the holder only while the lock is held, so a
         * completed installation is visible before disconnect updates it. Cleanup
         * runs after releasing the lock and delegates to the outer connection.
         * Exceptions from that cleanup propagate through this callback rather than
         * being caught here.
         */
        @Override
        public void onDisconnect(String reason) {
            // Use the installation lock for the transport callback's state claim so it cannot
            // disconnect the bootstrap holder while a negotiated holder is being published.
            synchronized (protocolLifecycleLock) {
                if (!activeProcessorHolder().setClientState(ClientState.DISCONNECTED, false)) {
                    // Another disconnect path already claimed this transition, so repeating outer cleanup
                    // would remove memberships and save state a second time for the same connection.
                    return;
                }
            }
            AllayPlayer.this.onDisconnect(reason);
        }
    }
}
