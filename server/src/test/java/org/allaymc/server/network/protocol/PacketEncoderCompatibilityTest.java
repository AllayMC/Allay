package org.allaymc.server.network.protocol;

import io.netty.buffer.Unpooled;
import org.allaymc.api.block.action.SimpleBlockAction;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.data.DiscType;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.LoginData;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.PlayerData;
import org.allaymc.api.player.Skin;
import org.allaymc.api.primitiveshape.PrimitiveSphere;
import org.allaymc.api.primitiveshape.PrimitiveText;
import org.allaymc.api.registry.DoubleKeyMappedRegistry;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.WorldViewer.BlockUpdate;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.particle.CustomParticle;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.MusicDiscPlaySound;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.container.impl.BaseContainer;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.dimension.DimensionId;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseStatus;
import org.cloudburstmc.protocol.bedrock.definition.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.joml.primitives.AABBd;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import static org.allaymc.api.item.type.ItemTypes.STONE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@code PacketEncoderCompatibilityTest} checks encoder payloads, ownership
 * boundaries, and selected codec round trips across protocol versions.
 */
@ExtendWith(AllayTestExtension.class)
class PacketEncoderCompatibilityTest {
    /**
     * {@code registry} retains the extension-installed default registry after
     * setup. Every case reuses its initialized protocols and cached definitions
     * so repeated encodings compare the same source data.
     */
    private static ProtocolRegistry registry;

    /**
     * Captures the default registry initialized by the Allay test extension.
     * Every matrix case resolves versions from this shared snapshot without
     * rebuilding protocol payloads or replacing the global registry.
     */
    @BeforeAll
    static void setUpRegistry() {
        registry = ProtocolRegistry.getDefault();
    }

    /**
     * Compares primitive-shape encoding before and after its supported
     * boundary. Legacy encoding returns {@code null}, while supported variants
     * create fresh shape packets and encode them with their own codecs.
     */
    @Test
    void primitiveShapesFollowProtocolAvailability() {
        var sphere = new PrimitiveSphere(new org.joml.Vector3f(1, 2, 3), Color.RED, 2f, 12);

        var netEaseV766 = protocol(ClientVariant.NETEASE, 766).getEncoder();
        assertNull(netEaseV766.encodePrimitiveShapes(List.of(sphere), 0));
        assertNull(netEaseV766.encodePrimitiveShapeRemovals(List.of(sphere)));

        for (var protocol : List.of(
                protocol(ClientVariant.INTERNATIONAL, 818),
                protocol(ClientVariant.NETEASE, 819)
        )) {
            var encoder = protocol.getEncoder();
            var first = onlyPacket(encoder.encodePrimitiveShapes(List.of(sphere), 2));
            var second = onlyPacket(encoder.encodePrimitiveShapes(List.of(sphere), 2));
            var removal = onlyPacket(encoder.encodePrimitiveShapeRemovals(List.of(sphere)));

            assertNotSame(first, second);
            assertEquals(1, first.getShapes().size());
            assertEquals(1, second.getShapes().size());
            assertEquals(1, removal.getShapes().size());
            assertInstanceOf(
                    org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveSphere.class,
                    first.getShapes().getFirst()
            );
            assertPacketEncodes(protocol, first);
        }
    }

    /**
     * Encodes addition and removal of an ambient effect for two protocol
     * families. Identity, duration, visibility, tick, and ambient fields must
     * survive the selected packet construction and codec checks.
     */
    @Test
    void mobEffectsIncludeTickAndAmbientState() {
        var entity = mock(Entity.class);
        when(entity.getRuntimeId()).thenReturn(42L);
        var effectType = mock(EffectType.class);
        when(effectType.getId()).thenReturn(7);
        var effect = new EffectInstance(effectType, 2, 600, true, false);

        for (var protocol : List.of(
                protocol(ClientVariant.NETEASE, 766),
                protocol(ClientVariant.INTERNATIONAL, 898)
        )) {
            var added = protocol.getEncoder().encodeMobEffect(entity, effect, null, 1234L);
            assertEquals(42L, added.getRuntimeEntityId());
            assertEquals(MobEffectPacket.Event.ADD, added.getEvent());
            assertEquals(7, added.getEffectId());
            assertEquals(2, added.getAmplifier());
            assertEquals(600, added.getDuration());
            assertFalse(added.isParticles());
            assertEquals(1234L, added.getTick());
            assertTrue(added.isAmbient());
            assertPacketEncodes(protocol, added);

            var removed = protocol.getEncoder().encodeMobEffect(entity, null, effect, 1235L);
            assertEquals(MobEffectPacket.Event.REMOVE, removed.getEvent());
            assertEquals(1235L, removed.getTick());
            assertTrue(removed.isAmbient());
            assertPacketEncodes(protocol, removed);
        }
    }

    /**
     * Probes music-disc and shelf-swap events around their introduction
     * boundaries. Earlier encoders omit the event, while the first supported
     * versions and selected NetEase variants emit an encodable sound packet.
     */
    @Test
    void soundsStartAtTheirProtocolIntroductionBoundary() {
        var position = new org.joml.Vector3d(1, 2, 3);
        var tears = new MusicDiscPlaySound(DiscType.DISC_TEARS);
        var lavaChicken = new MusicDiscPlaySound(DiscType.DISC_LAVA_CHICKEN);

        assertTrue(protocol(ClientVariant.NETEASE, 766).getEncoder()
                .encodeSound(tears, position, false)
                .isEmpty());
        assertSoundEncodes(protocol(ClientVariant.INTERNATIONAL, 818), tears, position);

        assertTrue(protocol(ClientVariant.INTERNATIONAL, 818).getEncoder()
                .encodeSound(lavaChicken, position, false)
                .isEmpty());
        assertSoundEncodes(protocol(ClientVariant.INTERNATIONAL, 819), lavaChicken, position);
        assertSoundEncodes(protocol(ClientVariant.NETEASE, 819), lavaChicken, position);

        for (var sound : List.of(SimpleSound.SHELF_SWAP_SINGLE, SimpleSound.SHELF_SWAP_MULTI)) {
            assertTrue(protocol(ClientVariant.INTERNATIONAL, 827).getEncoder()
                    .encodeSound(sound, position, false)
                    .isEmpty());
            assertSoundEncodes(protocol(ClientVariant.INTERNATIONAL, 844), sound, position);
        }
    }

    /**
     * Encodes multiline primitive text and a named custom sound for each recent
     * version. Both packet forms must pass the target codec checks across the
     * v2193 transition.
     */
    @ParameterizedTest
    @ValueSource(ints = {2168, 2169, 2193})
    void textShapesAndCustomSoundsEncodeAcrossTheV2193Boundary(int version) {
        var protocol = protocol(ClientVariant.INTERNATIONAL, version);
        var text = new PrimitiveText(new org.joml.Vector3f(1, 2, 3), Color.RED, "First line\nSecond line");
        var shapePacket = onlyPacket(protocol.getEncoder().encodePrimitiveShapes(List.of(text), 0));
        assertPacketEncodes(protocol, shapePacket);

        var soundPackets = protocol.getEncoder().encodeSound(
                new CustomSound("random.click", 0.5f, 1.2f), new org.joml.Vector3d(1, 2, 3), false
        );
        assertEquals(1, soundPackets.size());
        assertPacketEncodes(protocol, soundPackets.iterator().next());
    }

    /**
     * Temporarily installs dimensions with custom height bounds for recent
     * codecs. Encoded definitions must preserve identity, convert the upper
     * bound, and carry the expected default biomes where that version includes
     * them.
     */
    @ParameterizedTest
    @ValueSource(ints = {2168, 2169, 2193})
    @SuppressWarnings("unchecked")
    void dimensionDefinitionsEncodeWithCustomBuildHeights(int version) {
        var originalRegistry = Registries.DIMENSIONS;
        var identifiers = List.of("minecraft:overworld", "minecraft:nether", "minecraft:the_end", "allay_test:custom");
        var expectedBiomes = List.of("minecraft:plains", "minecraft:hell", "minecraft:the_end", "minecraft:plains");
        var dimensions = new java.util.LinkedHashMap<Integer, DimensionType>();
        for (int index = 0; index < identifiers.size(); index++) {
            var dimension = mock(DimensionType.class);
            when(dimension.getId()).thenReturn(index);
            when(dimension.getIdentifier()).thenReturn(new Identifier(identifiers.get(index)));
            when(dimension.getMinHeight()).thenReturn(-64);
            when(dimension.getMaxHeight()).thenReturn(511);
            dimensions.put(index, dimension);
        }

        var dimensionRegistry = mock(DoubleKeyMappedRegistry.class);
        when(dimensionRegistry.getContent()).thenReturn(new DoubleKeyMappedRegistry.MapPair<>(dimensions, Map.of()));
        try {
            // Install the replacement only around the encoding probe; restoring
            // the original registry in finally prevents assertion failures from
            // contaminating later cases.
            Registries.DIMENSIONS = dimensionRegistry;
            var protocol = protocol(ClientVariant.INTERNATIONAL, version);
            var packet = protocol.getEncoder().encodeDimensionData();
            assertEquals(4, packet.getDefinitions().size());
            for (int index = 0; index < packet.getDefinitions().size(); index++) {
                var definition = packet.getDefinitions().get(index);
                assertEquals(identifiers.get(index), definition.id());
                assertEquals(-64, definition.minimumHeight());
                assertEquals(512, definition.maximumHeight());
                assertEquals(index, definition.dimensionType());
                // The default-biome field is asserted only after its v2193
                // introduction; older definitions are still checked for
                // identity and height values.
                if (version >= 2193) {
                    assertEquals(expectedBiomes.get(index), definition.defaultBiome());
                }
            }
            assertPacketEncodes(protocol, packet);
        } finally {
            Registries.DIMENSIONS = originalRegistry;
        }
    }

    /**
     * Encodes representative world updates twice for selected protocol
     * families. Packets and selected collections must be fresh, retain their
     * requested state, and pass target-codec serialization checks.
     */
    @Test
    void worldEncodersReturnFreshCodecCompatiblePackets() {
        var position = new org.joml.Vector3i(3, 64, -5);
        var particlePosition = new org.joml.Vector3d(3, 64, -5);
        var gameRules = mock(GameRules.class);
        when(gameRules.getGameRules()).thenReturn(Map.of());
        var blockState = mock(BlockState.class);
        when(blockState.blockStateHash()).thenReturn(0);

        for (var protocol : List.of(
                protocol(ClientVariant.NETEASE, 766),
                protocol(ClientVariant.INTERNATIONAL, 1001),
                protocol(ClientVariant.INTERNATIONAL, 2168),
                protocol(ClientVariant.INTERNATIONAL, 2169),
                protocol(ClientVariant.INTERNATIONAL, 2193)
        )) {
            var encoder = protocol.getEncoder();

            var firstTime = encoder.encodeTime(6000);
            var secondTime = encoder.encodeTime(6000);
            assertNotSame(firstTime, secondTime);
            assertEquals(6000, firstTime.getTime());
            assertPacketEncodes(protocol, firstTime);
            assertPacketEncodes(protocol, secondTime);

            var firstRules = encoder.encodeGameRules(gameRules);
            var secondRules = encoder.encodeGameRules(gameRules);
            assertNotSame(firstRules, secondRules);
            assertNotSame(firstRules.getGameRules(), secondRules.getGameRules());
            assertPacketEncodes(protocol, firstRules);

            var firstUpdate = encoder.encodeBlockUpdate(position, 1, blockState);
            var secondUpdate = encoder.encodeBlockUpdate(position, 1, blockState);
            assertNotSame(firstUpdate, secondUpdate);
            assertEquals(1, firstUpdate.getDataLayer());
            assertPacketEncodes(protocol, firstUpdate);

            var firstAction = encoder.encodeBlockAction(position, SimpleBlockAction.OPEN).iterator().next();
            var secondAction = encoder.encodeBlockAction(position, SimpleBlockAction.OPEN).iterator().next();
            assertNotSame(firstAction, secondAction);
            assertPacketEncodes(protocol, firstAction);

            var firstWeather = encoder.encodeWeather(Weather.THUNDER).stream().toList();
            var secondWeather = encoder.encodeWeather(Weather.THUNDER).stream().toList();
            assertEquals(2, firstWeather.size());
            assertEquals(2, secondWeather.size());
            assertNotSame(firstWeather.getFirst(), secondWeather.getFirst());
            assertNotSame(firstWeather.get(1), secondWeather.get(1));
            firstWeather.forEach(packet -> assertPacketEncodes(protocol, packet));

            var firstSimpleParticle = assertInstanceOf(
                    LevelEventPacket.class,
                    encoder.encodeParticle(SimpleParticle.EXPLODE, particlePosition, 2).iterator().next()
            );
            var secondSimpleParticle = assertInstanceOf(
                    LevelEventPacket.class,
                    encoder.encodeParticle(SimpleParticle.EXPLODE, particlePosition, 2).iterator().next()
            );
            assertNotSame(firstSimpleParticle, secondSimpleParticle);
            assertPacketEncodes(protocol, firstSimpleParticle);

            var customParticle = assertInstanceOf(
                    SpawnParticleEffectPacket.class,
                    encoder.encodeParticle(
                            new CustomParticle("minecraft:basic_flame_particle"),
                            particlePosition,
                            2
                    ).iterator().next()
            );
            assertEquals(2, customParticle.getDimensionId());
            assertPacketEncodes(protocol, customParticle);

            var firstStop = encoder.encodeStopSound(null);
            var secondStop = encoder.encodeStopSound(null);
            assertNotSame(firstStop, secondStop);
            assertTrue(firstStop.isStoppingAllSound());
            assertPacketEncodes(protocol, firstStop);
        }
    }

    /**
     * Mutates one grouped block update and compares repeated container
     * encodings. Block-entry collections, inventory items, and container
     * packets must be independent and use definitions accepted by the selected
     * protocol.
     */
    @Test
    void blockAndContainerPayloadsAreIndependentAcrossCalls() {
        var protocol = protocol(ClientVariant.INTERNATIONAL, 1001);
        var encoder = protocol.getEncoder();
        var chunk = AllayUnsafeChunk.builder()
                .voidChunk(4, -2, DimensionTypes.OVERWORLD)
                .toSafeChunk();
        var blockState = mock(BlockState.class);
        when(blockState.blockStateHash()).thenReturn(0);
        var update = new BlockUpdate(65, 64, -31, blockState);

        var firstUpdates = encoder.encodeBlockUpdates(chunk, List.of(update), List.of()).stream().toList();
        var secondUpdates = encoder.encodeBlockUpdates(chunk, List.of(update), List.of()).stream().toList();
        assertEquals(1, firstUpdates.size());
        assertEquals(1, secondUpdates.size());
        assertNotSame(firstUpdates.getFirst(), secondUpdates.getFirst());
        assertNotSame(
                firstUpdates.getFirst().getStandardBlocks().getFirst(),
                secondUpdates.getFirst().getStandardBlocks().getFirst()
        );
        firstUpdates.getFirst().getStandardBlocks().clear();
        assertEquals(1, secondUpdates.getFirst().getStandardBlocks().size());
        assertPacketEncodes(protocol, secondUpdates.getFirst());

        var container = new BaseContainer(ContainerTypes.CHEST);
        container.setItemStack(0, STONE.createItemStack(), false);
        var firstContents = encoder.encodeContainerContents(container, 7);
        var secondContents = encoder.encodeContainerContents(container, 7);
        assertNotSame(firstContents, secondContents);
        assertNotSame(firstContents.getContents(), secondContents.getContents());
        assertNotSame(firstContents.getContents().getFirst(), secondContents.getContents().getFirst());
        assertEquals(ContainerTypes.CHEST.getSize(), secondContents.getContents().size());
        assertTrue(protocol.getItemDefinitionRegistry().isRegistered(
                secondContents.getContents().getFirst().getDefinition()
        ));
        assertPacketEncodes(protocol, secondContents);

        var firstSlot = encoder.encodeContainerSlot(container, 0, 7);
        var secondSlot = encoder.encodeContainerSlot(container, 0, 7);
        assertNotSame(firstSlot, secondSlot);
        assertNotSame(firstSlot.getItem(), secondSlot.getItem());
        assertTrue(protocol.getItemDefinitionRegistry().isRegistered(firstSlot.getItem().getDefinition()));
        assertPacketEncodes(protocol, firstSlot);

        var containerPosition = new org.joml.Vector3i(1, 65, 2);
        var firstOpen = encoder.encodeContainerOpen(container, (byte) 7, containerPosition);
        var secondOpen = encoder.encodeContainerOpen(container, (byte) 7, containerPosition);
        assertNotSame(firstOpen, secondOpen);
        assertPacketEncodes(protocol, firstOpen);

        var firstClose = encoder.encodeContainerClose(container, (byte) 7, true);
        var secondClose = encoder.encodeContainerClose(container, (byte) 7, true);
        assertNotSame(firstClose, secondClose);
        assertTrue(firstClose.isServerInitiated());
        assertPacketEncodes(protocol, firstClose);

        var firstData = encoder.encodeContainerData(7, 2, 9);
        var secondData = encoder.encodeContainerData(7, 2, 9);
        assertNotSame(firstData, secondData);
        assertPacketEncodes(protocol, firstData);
    }

    /**
     * Creates a chest-open request through the NetEase v860 encoder. The packet
     * must retain the normal block interaction flag and be accepted by its
     * variant-specific codec.
     */
    @Test
    void netEaseV860ContainerOpenUsesTargetCodec() {
        var protocol = protocol(ClientVariant.NETEASE, 860);
        var packet = protocol.getEncoder().encodeContainerOpen(
                new BaseContainer(ContainerTypes.CHEST),
                (byte) 7,
                new org.joml.Vector3i(1, 65, 2)
        );

        assertFalse(packet.isIgnoreBlock());
        assertPacketEncodes(protocol, packet);
    }

    /**
     * Encodes the same uncached void chunk twice for each selected version.
     * Mutating one packet buffer must preserve the other buffer, and both
     * packets must remain accepted by the target codec.
     */
    @ParameterizedTest
    @ValueSource(ints = {1001, 2168, 2169, 2193})
    void levelChunkPacketsOwnIndependentBuffers(int version) {
        var protocol = protocol(ClientVariant.INTERNATIONAL, version);
        var chunk = AllayUnsafeChunk.builder()
                .voidChunk(2, 3, DimensionTypes.OVERWORLD)
                .toSafeChunk();
        var first = protocol.getEncoder().encodeLevelChunk(chunk, false, null, null, 0);
        var second = protocol.getEncoder().encodeLevelChunk(chunk, false, null, null, 0);

        try {
            assertNotSame(first, second);
            assertNotSame(first.getData(), second.getData());
            assertTrue(first.getData().isReadable());
            assertTrue(second.getData().isReadable());
            int secondFirstByte = second.getData().getUnsignedByte(second.getData().readerIndex());
            first.getData().setByte(
                    first.getData().readerIndex(),
                    secondFirstByte ^ 0xff
            );
            assertEquals(
                    secondFirstByte,
                    second.getData().getUnsignedByte(second.getData().readerIndex())
            );
            assertPacketEncodes(protocol, first);
            assertPacketEncodes(protocol, second);
        } finally {
            // These unsent chunk packets retain their own buffers; release both
            // references after codec probing, including when a mutation
            // assertion fails.
            assertTrue(first.release());
            assertTrue(second.release());
        }
    }

    /**
     * Encodes a minimal entity through spawn, state, movement, action, and
     * removal operations. Repeated packets and metadata must be independent,
     * and each resulting representation must pass target-codec checks.
     */
    @Test
    void entityEncodersReturnFreshCodecCompatiblePackets() {
        var entity = simpleEntity();
        var location = entity.getLocation();

        for (var protocol : List.of(
                protocol(ClientVariant.NETEASE, 766),
                protocol(ClientVariant.INTERNATIONAL, 1001),
                protocol(ClientVariant.INTERNATIONAL, 2168),
                protocol(ClientVariant.INTERNATIONAL, 2169),
                protocol(ClientVariant.INTERNATIONAL, 2193)
        )) {
            var encoder = protocol.getEncoder();
            var firstSpawn = assertInstanceOf(AddEntityPacket.class, encoder.encodeEntitySpawn(entity));
            var secondSpawn = assertInstanceOf(AddEntityPacket.class, encoder.encodeEntitySpawn(entity));
            assertNotSame(firstSpawn, secondSpawn);
            assertNotSame(firstSpawn.getMetadata(), secondSpawn.getMetadata());
            firstSpawn.getMetadata().put(EntityDataTypes.NAME, "mutated");
            assertNotEquals("mutated", secondSpawn.getMetadata().get(EntityDataTypes.NAME));
            assertPacketEncodes(protocol, firstSpawn);
            assertPacketEncodes(protocol, secondSpawn);

            var firstState = encoder.encodeEntityState(entity);
            var secondState = encoder.encodeEntityState(entity);
            assertNotSame(firstState, secondState);
            assertNotSame(firstState.getMetadata(), secondState.getMetadata());
            assertPacketEncodes(protocol, firstState);
            assertPacketEncodes(protocol, secondState);

            var firstMove = encoder.encodeEntityLocation(entity, location, true, false);
            var secondMove = encoder.encodeEntityLocation(entity, location, true, false);
            assertNotSame(firstMove, secondMove);
            assertPacketEncodes(protocol, firstMove);
            assertPacketEncodes(protocol, secondMove);

            var bitePackets = encoder.encodeEntityAction(
                    entity,
                    SimpleEntityAction.FISHING_HOOK_BITE,
                    false
            );
            assertEquals(3, bitePackets.size());
            bitePackets.forEach(packet -> assertPacketEncodes(protocol, packet));

            var removal = encoder.encodeEntityRemove(entity);
            assertPacketEncodes(protocol, removal);
        }
    }

    /**
     * Encodes a simulated player's skin twice across selected protocol
     * versions. The ordered three-packet sequences must contain independent
     * skin bytes and, where required, explicit player-list entry actions.
     */
    @ParameterizedTest
    @ValueSource(ints = {819, 2168, 2169, 2193})
    void fakePlayerSkinPacketsDoNotShareMutableSkinPayloads(int version) {
        var protocol = protocol(ClientVariant.INTERNATIONAL, version);
        var player = mock(EntityPlayer.class);
        var uniqueId = UUID.fromString("12345678-abcd-4321-abcd-1234567890ab");
        when(player.getUniqueId()).thenReturn(uniqueId);
        when(player.isActualPlayer()).thenReturn(false);
        when(player.getSkin()).thenReturn(networkSkin());

        var firstPackets = protocol.getEncoder().encodePlayerSkin(player, true).stream().toList();
        var secondPackets = protocol.getEncoder().encodePlayerSkin(player, true).stream().toList();
        assertEquals(3, firstPackets.size());
        assertEquals(3, secondPackets.size());
        for (int i = 0; i < firstPackets.size(); i++) {
            assertNotSame(firstPackets.get(i), secondPackets.get(i));
            assertPacketEncodes(protocol, firstPackets.get(i));
            assertPacketEncodes(protocol, secondPackets.get(i));
        }
        // Recent list serialization reads the action from each entry, so the
        // temporary add and remove entries must retain their distinct actions.
        if (version >= 2168) {
            assertEquals(PlayerListPacket.Action.ADD, ((PlayerListPacket) firstPackets.getFirst()).getEntries().getFirst().getAction());
            assertEquals(PlayerListPacket.Action.REMOVE, ((PlayerListPacket) firstPackets.getLast()).getEntries().getFirst().getAction());
        }

        var listSkin = assertInstanceOf(PlayerListPacket.class, firstPackets.getFirst())
                .getEntries().getFirst().getSkin();
        var directSkin = assertInstanceOf(PlayerSkinPacket.class, firstPackets.get(1)).getSkin();
        var repeatedSkin = assertInstanceOf(PlayerSkinPacket.class, secondPackets.get(1)).getSkin();
        assertNotSame(listSkin, directSkin);
        assertNotSame(listSkin.getSkinData().getImage(), directSkin.getSkinData().getImage());
        assertNotSame(directSkin.getSkinData().getImage(), repeatedSkin.getSkinData().getImage());

        listSkin.getSkinData().getImage()[0] = 99;
        assertEquals(1, directSkin.getSkinData().getImage()[0]);
        assertEquals(1, repeatedSkin.getSkinData().getImage()[0]);
    }

    /**
     * Creates add and remove list packets for a mocked player on recent
     * protocols. Each entry must carry the requested action and pass the target
     * codec checks.
     */
    @ParameterizedTest
    @ValueSource(ints = {2168, 2169, 2193})
    void playerListsCarryActionsOnEveryEntry(int version) {
        var protocol = protocol(ClientVariant.INTERNATIONAL, version);
        var player = mock(Player.class, RETURNS_DEEP_STUBS);
        var uniqueId = UUID.fromString("12345678-abcd-4321-abcd-1234567890ab");
        when(player.getLoginData().getUuid()).thenReturn(uniqueId);
        when(player.getLoginData().getXuid()).thenReturn("1234");
        when(player.getLoginData().getSkin()).thenReturn(networkSkin());
        when(player.getLoginData().getDeviceInfo().deviceName()).thenReturn("test-device");
        when(player.getLoginData().getDeviceInfo().device()).thenReturn(LoginData.Device.ANDROID);
        when(player.getControlledEntity().getUniqueId()).thenReturn(uniqueId);
        when(player.getOriginName()).thenReturn("TestPlayer");

        for (boolean add : new boolean[]{true, false}) {
            var packet = protocol.getEncoder().encodePlayerList(List.of(player), add, true);
            assertEquals(add ? PlayerListPacket.Action.ADD : PlayerListPacket.Action.REMOVE,
                    packet.getEntries().getFirst().getAction());
            assertPacketEncodes(protocol, packet);
        }
    }

    /**
     * Combines rejected, empty, and populated item-stack responses for recent
     * versions. Encoding must normalize a {@code null} container list without
     * changing the source response and accept the version-specific optional
     * slot fields.
     */
    @ParameterizedTest
    @ValueSource(ints = {2168, 2169, 2193})
    void itemStackResponsesEncodeErrorsAndOptionalSlotFields(int version) {
        var protocol = protocol(ClientVariant.INTERNATIONAL, version);
        var rejected = new ItemStackResponse(ItemStackResponseStatus.ERROR, -1, null);
        var empty = new ItemStackResponse(ItemStackResponseStatus.OK, -2, List.of());
        // Only the v2193 fixture uses an absent filtered-name value, exercising
        // that format change while earlier codecs receive their expected string
        // field.
        var slots = List.of(
                new ItemStackResponseSlot(0, 0, 0, 0, "", 0, version >= 2193 ? null : ""),
                new ItemStackResponseSlot(1, 1, 3, 42, "Named item", 2, "Filtered item")
        );
        var accepted = new ItemStackResponse(ItemStackResponseStatus.OK, -3, List.of(
                new ItemStackResponseContainer(ContainerSlotType.INVENTORY, slots,
                        new FullContainerName(ContainerSlotType.INVENTORY, null))
        ));
        var packet = protocol.getEncoder().encodeItemStackResponse(List.of(rejected, empty, accepted));

        assertNull(rejected.containers());
        assertTrue(packet.getEntries().getFirst().containers().isEmpty());
        assertPacketEncodes(protocol, packet);
    }

    /**
     * Invokes connection-independent encoders for every registered protocol and
     * repeats each encoding. Packets must be fresh and codec-compatible, with
     * voxel-shape availability following the asserted version boundary.
     */
    @Test
    void everyProtocolCodecAcceptsAllConnectionIndependentEncoderOutput() {
        for (var protocol : registry.getProtocols()) {
            var encoder = protocol.getEncoder();
            List<Supplier<? extends BedrockPacket>> encoders = List.of(
                    encoder::encodeItemRegistry,
                    encoder::encodeCreativeContent,
                    encoder::encodeCraftingData,
                    encoder::encodeAvailableEntityIdentifiers,
                    encoder::encodeBiomeDefinitions,
                    encoder::encodeDimensionData,
                    encoder::encodeResourcePacksInfo,
                    encoder::encodeResourcePackStack,
                    encoder::encodeTrimData
            );

            for (var encode : encoders) {
                var first = encode.get();
                var second = encode.get();
                assertNotSame(first, second, () -> protocol + " reused " + first.getPacketType());
                assertPacketEncodes(protocol, first);
                assertPacketEncodes(protocol, second);
            }

            var firstVoxelShapes = encoder.encodeVoxelShapes();
            var secondVoxelShapes = encoder.encodeVoxelShapes();
            // Voxel shapes use a null unsupported result before v924; later
            // versions must return a real packet even for empty shape data.
            if (protocol.getProtocolVersion() < 924) {
                assertNull(firstVoxelShapes, protocol::toString);
                assertNull(secondVoxelShapes, protocol::toString);
            } else {
                assertNotNull(firstVoxelShapes, protocol::toString);
                assertNotNull(secondVoxelShapes, protocol::toString);
                assertEquals(firstVoxelShapes.size(), secondVoxelShapes.size(), protocol::toString);
                assertEquals(1, firstVoxelShapes.size(), protocol::toString);
                var firstVoxelIterator = firstVoxelShapes.iterator();
                var secondVoxelIterator = secondVoxelShapes.iterator();
                while (firstVoxelIterator.hasNext()) {
                    var first = firstVoxelIterator.next();
                    var second = secondVoxelIterator.next();
                    assertNotSame(first, second, () -> protocol + " reused " + first.getPacketType());
                    assertPacketEncodes(protocol, first);
                    assertPacketEncodes(protocol, second);
                }
            }

            var firstProperties = encoder.encodeSyncEntityProperties();
            var secondProperties = encoder.encodeSyncEntityProperties();
            assertEquals(firstProperties.size(), secondProperties.size(), protocol::toString);
            var firstIterator = firstProperties.iterator();
            var secondIterator = secondProperties.iterator();
            while (firstIterator.hasNext()) {
                var first = firstIterator.next();
                var second = secondIterator.next();
                assertNotSame(first, second, () -> protocol + " reused " + first.getPacketType());
                assertPacketEncodes(protocol, first);
                assertPacketEncodes(protocol, second);
            }
        }
    }

    /**
     * Builds login packets from a shared world and player fixture for every
     * protocol. Packets must be fresh, preserve the fixture's identities and
     * settings, and use the expected network position.
     */
    @Test
    void startGameIsConstructedFromDomainDataForEveryProtocol() {
        var fixture = startGameFixture();

        for (var protocol : registry.getProtocols()) {
            var first = fixture.encode(protocol.getEncoder());
            var second = fixture.encode(protocol.getEncoder());

            assertNotSame(first, second);
            assertEquals(fixture.uniqueId().getLeastSignificantBits(), first.getUniqueEntityId());
            assertEquals(fixture.runtimeId(), first.getRuntimeEntityId());
            assertEquals(GameType.CREATIVE, first.getPlayerGameType());
            assertEquals(org.cloudburstmc.math.vector.Vector3i.from(8, 64, -4), first.getDefaultSpawn());
            assertEquals(org.cloudburstmc.math.vector.Vector3f.from(1.25, 66.12, -3.5), first.getPlayerPosition());
            assertEquals(org.cloudburstmc.math.vector.Vector2f.from(15, 30), first.getRotation());
            assertEquals(0, first.getDimensionId());
            assertEquals(GameType.SURVIVAL, first.getLevelGameType());
            assertEquals(Difficulty.NORMAL.ordinal(), first.getDifficulty());
            assertFalse(first.getItemDefinitions().isEmpty());
            assertPacketEncodes(protocol, first);
        }
    }

    /**
     * Builds login packets on either side of the v844 experiment override. The
     * v827-only experiment must appear in v827 and remain absent from v844.
     */
    @Test
    void startGameExperimentsStayInsideTheirProtocolBranch() {
        var fixture = startGameFixture();
        var v827 = fixture.encode(protocol(ClientVariant.INTERNATIONAL, 827).getEncoder());
        var v844 = fixture.encode(protocol(ClientVariant.INTERNATIONAL, 844).getEncoder());

        assertTrue(hasExperiment(v827, "y_2025_drop_3"));
        assertFalse(hasExperiment(v844, "y_2025_drop_3"));
    }

    /**
     * Compares skin confirmation across international and NetEase encoders
     * using a simulated player. Supported variants must return fresh copied
     * skin bytes and the deterministic UUID-derived fallback identity.
     */
    @Test
    void skinConfirmationIsNetEaseOnlyAndFresh() {
        var uniqueId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee");
        var player = mock(EntityPlayer.class);
        when(player.getUniqueId()).thenReturn(uniqueId);

        var skinBytes = new byte[]{1, 2, 3, 4};
        var skin = Skin.builder()
                .skinGeometry("geometry-data")
                .skinData(new Skin.ImageData(1, 1, skinBytes))
                .build();

        assertNull(protocol(ClientVariant.INTERNATIONAL, 819).getEncoder()
                .encodeSkinConfirmation(player, skin));

        for (var protocol : List.of(
                protocol(ClientVariant.NETEASE, 766),
                protocol(ClientVariant.NETEASE, 819),
                protocol(ClientVariant.NETEASE, 860)
        )) {
            var first = protocol.getEncoder().encodeSkinConfirmation(player, skin).iterator().next();
            var second = protocol.getEncoder().encodeSkinConfirmation(player, skin).iterator().next();
            var entry = first.getEntries().getFirst();

            assertNotSame(first, second);
            assertNotSame(entry, second.getEntries().getFirst());
            assertTrue(entry.isValid());
            assertEquals(uniqueId, entry.getUuid());
            assertEquals("geometry-data", entry.getGeoStr());
            assertArrayEquals(skinBytes, entry.getSkinBytes());
            assertNotSame(skinBytes, entry.getSkinBytes());
            assertEquals(
                    String.valueOf(Math.abs((long) uniqueId.toString().replace("-", "").hashCode())),
                    entry.getUidStr()
            );
            assertPacketEncodes(protocol, first);
        }
    }

    /**
     * Reflects over the declared {@code PacketEncoder} operations whose names
     * begin with {@code "encode"}. None may be abstract, preserving an
     * inherited implementation for subclasses that support only selected
     * operations.
     */
    @Test
    void encoderOperationsHaveConcreteFallbacks() {
        for (var method : PacketEncoder.class.getDeclaredMethods()) {
            if (method.getName().startsWith("encode")) {
                assertFalse(Modifier.isAbstract(method.getModifiers()), method::toString);
            }
        }
    }

    /**
     * Compares legacy {@code null} results with supported data-store and UI
     * packet construction. Supported versions must detach copied map
     * containers, return fresh packets, and pass target-codec checks.
     */
    @Test
    void unsupportedDataDrivenUIOperationsReturnNull() {
        var legacy = protocol(ClientVariant.INTERNATIONAL, 924).getEncoder();
        assertNull(legacy.encodeDataStoreChange("minecraft", "property", Map.of()));
        assertNull(legacy.encodeDataStoreUpdates("minecraft", "property", List.of("value"), 1));
        assertNull(legacy.encodeDataDrivenUIShowScreen("minecraft:custom_form", 1));
        assertNull(legacy.encodeDataDrivenUICloseScreen(1));

        for (var protocol : List.of(
                protocol(ClientVariant.INTERNATIONAL, 944),
                protocol(ClientVariant.INTERNATIONAL, 1001),
                protocol(ClientVariant.INTERNATIONAL, 2168),
                protocol(ClientVariant.INTERNATIONAL, 2169),
                protocol(ClientVariant.INTERNATIONAL, 2193)
        )) {
            var encoder = protocol.getEncoder();
            var source = new java.util.LinkedHashMap<String, Object>();
            source.put("title", "Initial title");
            source.put("nested", new java.util.LinkedHashMap<>(Map.of("visible", true)));

            var change = encoder.encodeDataStoreChange("minecraft", "property", source);
            var secondChange = encoder.encodeDataStoreChange("minecraft", "property", source);
            var update = encoder.encodeDataStoreUpdates(
                    "minecraft",
                    "property",
                    List.of("title"),
                    "Updated title"
            );
            var show = encoder.encodeDataDrivenUIShowScreen("minecraft:custom_form", 7);
            var close = encoder.encodeDataDrivenUICloseScreen(7);

            assertNotSame(change, secondChange);
            assertEquals(1, change.getUpdates().size());
            var changeData = assertInstanceOf(
                    org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreChange.class,
                    change.getUpdates().getFirst()
            );
            assertNotSame(source, changeData.getNewValue());
            source.put("title", "Mutated after encoding");
            assertEquals("Initial title", ((Map<?, ?>) changeData.getNewValue()).get("title"));
            assertInstanceOf(
                    org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate.class,
                    update.getUpdates().getFirst()
            );
            assertPacketEncodes(protocol, change);
            assertPacketEncodes(protocol, update);
            assertPacketEncodes(protocol, show);
            assertPacketEncodes(protocol, close);
        }
    }

    /**
     * Creates a world and player fixture with distinct world and saved game
     * modes. Fixed identities, spawn coordinates, and entity location make
     * login field selection and eye-height conversion observable across all
     * protocols.
     */
    private static StartGameFixture startGameFixture() {
        var gameRules = mock(GameRules.class);
        when(gameRules.getGameRules()).thenReturn(Map.of());

        var worldData = mock(WorldData.class);
        when(worldData.getGameRules()).thenReturn(gameRules);
        when(worldData.getGameMode()).thenReturn(GameMode.SURVIVAL);
        when(worldData.getDifficulty()).thenReturn(Difficulty.NORMAL);
        when(worldData.getSpawnPoint()).thenReturn(new org.joml.Vector3i(8, 64, -4));

        var world = mock(World.class);
        when(world.getWorldData()).thenReturn(worldData);

        var dimensionType = mock(DimensionType.class);
        when(dimensionType.getId()).thenReturn(0);
        when(dimensionType.getIdentifier()).thenReturn(DimensionId.OVERWORLD.getIdentifier());

        var dimension = mock(Dimension.class);
        when(dimension.getDimensionType()).thenReturn(dimensionType);

        var uniqueId = UUID.fromString("12345678-1234-5678-90ab-1234567890ab");
        long runtimeId = 42L;
        var player = mock(EntityPlayer.class);
        when(player.getUniqueId()).thenReturn(uniqueId);
        when(player.getRuntimeId()).thenReturn(runtimeId);
        when(player.getLocation()).thenReturn(new Location3d(1.25, 64.5, -3.5, 15, 30, dimension));

        var playerData = PlayerData.builder()
                .nbt(NbtMap.builder().putInt("PlayerGameMode", GameMode.CREATIVE.ordinal()).build())
                .build();
        return new StartGameFixture(world, playerData, dimension, player, uniqueId, runtimeId);
    }

    /**
     * Creates a minimal entity with fixed identity, bounds, and no custom
     * properties. The fixture follows the generic spawn path and supplies the
     * data needed by shared metadata and movement encoders.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Entity simpleEntity() {
        var entityType = (EntityType<? extends Entity>) mock(EntityType.class);
        when(entityType.getIdentifier()).thenReturn(new Identifier("minecraft", "test_entity"));
        when(entityType.getProperties()).thenReturn(Map.of());

        var dimension = mock(Dimension.class);
        var entity = mock(Entity.class);
        doReturn(entityType).when(entity).getEntityType();
        when(entity.getRuntimeId()).thenReturn(42L);
        when(entity.getUniqueId()).thenReturn(UUID.fromString("00000000-0000-0000-0000-00000000002a"));
        when(entity.getLocation()).thenReturn(new Location3d(1, 64, 2, 10, 20, dimension));
        when(entity.getAABB()).thenReturn(new AABBd(0, 0, 0, 0.6, 1.8, 0.6));
        when(entity.getScale()).thenReturn(1.0);
        when(entity.getPropertyValues()).thenReturn(Map.of());
        return entity;
    }

    /**
     * Creates a complete test skin with one identifiable first pixel. Every
     * call owns a new image array, while empty animation and persona
     * collections keep assertions focused on serialization and byte-array
     * copying.
     */
    private static Skin networkSkin() {
        // The fixture stores four channel bytes for every skin pixel.
        byte[] pixels = new byte[64 * 32 * 4];
        pixels[0] = 1;
        return Skin.builder()
                .skinId("skin-id")
                .playFabId("playfab-id")
                .skinResourcePatch("{\"geometry\":{\"default\":\"geometry.humanoid.custom\"}}")
                .skinData(new Skin.ImageData(64, 32, pixels))
                .animations(List.of())
                .capeData(Skin.ImageData.EMPTY)
                .skinGeometry("geometry-data")
                .animationData("")
                .geometryDataEngineVersion("1.0.0")
                .capeId("")
                .fullId("full-skin-id")
                .skinColor("#abcdef")
                .armSize(Skin.ARM_SIZE_WIDE)
                .personaPieces(List.of())
                .pieceTintColors(List.of())
                .build();
    }

    /**
     * Returns the initialized protocol for the exact variant and version
     * requested. A missing registry entry fails immediately, avoiding
     * accidental substitution of another variant or version in compatibility
     * assertions.
     */
    private static Protocol protocol(ClientVariant variant, int version) {
        var protocol = registry.resolve(variant, version);
        assertNotNull(protocol);
        return protocol;
    }

    /**
     * Returns the sole primitive-shape packet after asserting the collection
     * size.
     */
    private static PrimitiveShapesPacket onlyPacket(Collection<PrimitiveShapesPacket> packets) {
        assertEquals(1, packets.size());
        return packets.iterator().next();
    }

    /**
     * Checks that a sound produces exactly one level-sound event packet. The
     * packet is then passed through the selected protocol's codec checks, so
     * empty or differently represented sound results fail this helper.
     */
    private static void assertSoundEncodes(
            Protocol protocol,
            org.allaymc.api.world.sound.Sound sound,
            org.joml.Vector3dc position
    ) {
        var packets = protocol.getEncoder().encodeSound(sound, position, false);
        assertEquals(1, packets.size());
        var packet = assertInstanceOf(LevelSoundEventPacket.class, packets.iterator().next());
        assertPacketEncodes(protocol, packet);
    }

    /**
     * Returns whether a login packet contains the exact experiment name.
     */
    private static boolean hasExperiment(
            org.cloudburstmc.protocol.bedrock.packet.StartGamePacket packet,
            String name
    ) {
        return packet.getExperiments().stream().anyMatch(experiment -> experiment.name().equals(name));
    }

    /**
     * Checks registration and serialization using the selected protocol's own
     * definitions. The temporary helper uses client encoding settings, and
     * serialization must produce readable bytes without throwing.
     *
     * <p> For versions at least 2168, the same bytes are decoded with the
     * registered packet ID. The decoded class must match, all bytes must be
     * consumed, and dimension definitions receive an additional equality check;
     * other packet fields are not compared by this helper. Decoded
     * reference-counted packets and the output buffer are released on all
     * corresponding assertion paths, while the supplied packet remains
     * caller-owned.
     */
    private static void assertPacketEncodes(Protocol protocol, BedrockPacket packet) {
        assertNotNull(
                protocol.getCodec().getPacketDefinition(packet.getClass()),
                () -> protocol + " does not register " + packet.getClass().getSimpleName()
        );
        var helper = protocol.getCodec().createHelper();
        helper.setEncodingSettings(org.cloudburstmc.protocol.bedrock.data.EncodingSettings.CLIENT);
        helper.setItemDefinitions(SimpleDefinitionRegistry.<ItemDefinition>builder()
                .addAll(protocol.getData().itemDefinitions())
                .build());
        helper.setBlockDefinitions(SimpleDefinitionRegistry.<BlockDefinition>builder()
                .addAll(protocol.getData().blockDefinitions())
                .build());

        var buffer = Unpooled.buffer();
        try {
            assertDoesNotThrow(() -> protocol.getCodec().tryEncode(helper, buffer, packet), protocol::toString);
            assertTrue(buffer.isReadable());
            // Round-trip the recent formats as well as encoding them, so wrong
            // packet IDs or unconsumed trailing fields fail this compatibility
            // probe.
            if (protocol.getProtocolVersion() >= 2168) {
                var definition = protocol.getCodec().getPacketDefinition(packet.getClass());
                var decoded = assertDoesNotThrow(
                        () -> protocol.getCodec().tryDecode(helper, buffer, definition.id()),
                        () -> protocol + " failed to decode " + packet.getPacketType()
                );
                try {
                    assertEquals(packet.getClass(), decoded.getClass());
                    assertFalse(buffer.isReadable(), () -> protocol + " left unread bytes in " + packet.getPacketType());
                    if (packet instanceof DimensionDataPacket dimensions) {
                        assertEquals(dimensions.getDefinitions(), ((DimensionDataPacket) decoded).getDefinitions());
                    }
                } finally {
                    // Decoded packets can own additional buffers, so release
                    // the reconstructed object even if class or remaining-byte
                    // assertions fail.
                    io.netty.util.ReferenceCountUtil.release(decoded);
                }
            }
        } finally {
            // The temporary output buffer belongs to this probe and must be
            // released after both encoding and optional decoding assertions.
            buffer.release();
        }
    }

    /**
     * {@code StartGameFixture} represents mocked world and player inputs
     * together with the expected identities for login packet assertions.
     * {@code world}, {@code playerData}, {@code dimension}, and {@code player}
     * supply the encoder inputs, while {@code uniqueId} and {@code runtimeId}
     * match the identity values stubbed on {@code player}.
     *
     * @param world mocked world and level settings
     *
     * @param playerData saved player game-mode data
     *
     * @param dimension destination dimension fixture
     *
     * @param player live entity location and identity fixture
     *
     * @param uniqueId expected persistent player UUID
     *
     * @param runtimeId expected runtime entity identifier
     */
    private record StartGameFixture(
            World world,
            PlayerData playerData,
            Dimension dimension,
            EntityPlayer player,
            UUID uniqueId,
            long runtimeId
    ) {
        /**
         * Creates a login packet from this fixture through the supplied
         * encoder.
         */
        org.cloudburstmc.protocol.bedrock.packet.StartGamePacket encode(PacketEncoder encoder) {
            return encoder.encodeStartGame(world, playerData, dimension, player);
        }
    }
}
