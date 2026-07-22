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
import org.allaymc.api.player.PlayerData;
import org.allaymc.api.player.Skin;
import org.allaymc.api.primitiveshape.PrimitiveSphere;
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
import org.cloudburstmc.protocol.bedrock.definition.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.joml.primitives.AABBd;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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

@ExtendWith(AllayTestExtension.class)
class PacketEncoderCompatibilityTest {
    private static ProtocolRegistry registry;

    @BeforeAll
    static void setUpRegistry() {
        registry = ProtocolRegistry.getDefault();
    }

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
                protocol(ClientVariant.INTERNATIONAL, 1001)
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

    @Test
    void levelChunkPacketsOwnIndependentBuffers() {
        var protocol = protocol(ClientVariant.INTERNATIONAL, 1001);
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
            assertTrue(first.release());
            assertTrue(second.release());
        }
    }

    @Test
    void entityEncodersReturnFreshCodecCompatiblePackets() {
        var entity = simpleEntity();
        var location = entity.getLocation();

        for (var protocol : List.of(
                protocol(ClientVariant.NETEASE, 766),
                protocol(ClientVariant.INTERNATIONAL, 1001)
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

    @Test
    void fakePlayerSkinPacketsDoNotShareMutableSkinPayloads() {
        var protocol = protocol(ClientVariant.INTERNATIONAL, 819);
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

    @Test
    void startGameExperimentsStayInsideTheirProtocolBranch() {
        var fixture = startGameFixture();
        var v827 = fixture.encode(protocol(ClientVariant.INTERNATIONAL, 827).getEncoder());
        var v844 = fixture.encode(protocol(ClientVariant.INTERNATIONAL, 844).getEncoder());

        assertTrue(hasExperiment(v827, "y_2025_drop_3"));
        assertFalse(hasExperiment(v844, "y_2025_drop_3"));
    }

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
                protocol(ClientVariant.NETEASE, 819)
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

    @Test
    void encoderOperationsHaveConcreteFallbacks() {
        for (var method : PacketEncoder.class.getDeclaredMethods()) {
            if (method.getName().startsWith("encode")) {
                assertFalse(Modifier.isAbstract(method.getModifiers()), method::toString);
            }
        }
    }

    @Test
    void unsupportedDataDrivenUIOperationsReturnNull() {
        var legacy = protocol(ClientVariant.INTERNATIONAL, 924).getEncoder();
        assertNull(legacy.encodeDataStoreChange("minecraft", "property", Map.of()));
        assertNull(legacy.encodeDataStoreUpdates("minecraft", "property", List.of("value"), 1));
        assertNull(legacy.encodeDataDrivenUIShowScreen("minecraft:custom_form", 1));
        assertNull(legacy.encodeDataDrivenUICloseScreen(1));

        for (var protocol : List.of(
                protocol(ClientVariant.INTERNATIONAL, 944),
                protocol(ClientVariant.INTERNATIONAL, 1001)
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

    private static Skin networkSkin() {
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

    private static Protocol protocol(ClientVariant variant, int version) {
        var protocol = registry.resolve(variant, version);
        assertNotNull(protocol);
        return protocol;
    }

    private static PrimitiveShapesPacket onlyPacket(Collection<PrimitiveShapesPacket> packets) {
        assertEquals(1, packets.size());
        return packets.iterator().next();
    }

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

    private static boolean hasExperiment(
            org.cloudburstmc.protocol.bedrock.packet.StartGamePacket packet,
            String name
    ) {
        return packet.getExperiments().stream().anyMatch(experiment -> experiment.name().equals(name));
    }

    private static void assertPacketEncodes(Protocol protocol, BedrockPacket packet) {
        assertNotNull(
                protocol.getCodec().getPacketDefinition(packet.getClass()),
                () -> protocol + " does not register " + packet.getClass().getSimpleName()
        );
        var helper = protocol.getCodec().createHelper();
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
        } finally {
            buffer.release();
        }
    }

    private record StartGameFixture(
            World world,
            PlayerData playerData,
            Dimension dimension,
            EntityPlayer player,
            UUID uniqueId,
            long runtimeId
    ) {
        org.cloudburstmc.protocol.bedrock.packet.StartGamePacket encode(PacketEncoder encoder) {
            return encoder.encodeStartGame(world, playerData, dimension, player);
        }
    }
}
