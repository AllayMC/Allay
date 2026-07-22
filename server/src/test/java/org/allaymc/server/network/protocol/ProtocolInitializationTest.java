package org.allaymc.server.network.protocol;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.protocol.v766.PacketEncoder_v766;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v819.Bedrock_v819;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemGroup;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProtocolInitializationTest {
    private static final BedrockCodec CODEC = Bedrock_v819.CODEC;

    @Test
    void publishesOnlyAfterEveryComponentMatches() {
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL);

        assertFalse(protocol.isInitialized());
        assertThrows(IllegalStateException.class, protocol::getData);
        assertThrows(IllegalStateException.class, protocol::getEncoder);
        assertThrows(IllegalStateException.class, protocol::getFeatures);

        protocol.initialize();

        assertTrue(protocol.isInitialized());
        assertSame(protocol.getData(), protocol.getEncoder().getData());
        assertTrue(protocol.supports(ProtocolFeature.DATA_DRIVEN_UI));
        assertThrows(IllegalStateException.class, protocol::initialize);
    }

    @Test
    void rejectsMissingGeneratedDataWithoutPublishing() {
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL);
        protocol.itemDefinitions = null;

        assertThrows(NullPointerException.class, protocol::initialize);
        assertFalse(protocol.isInitialized());
        assertThrows(IllegalStateException.class, protocol::getData);
    }

    @Test
    void rejectsMissingEncoderWithoutPublishing() {
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL);
        protocol.encoderFactory = ignored -> null;

        assertThrows(NullPointerException.class, protocol::initialize);
        assertFalse(protocol.isInitialized());
        assertThrows(IllegalStateException.class, protocol::getEncoder);
    }

    @Test
    void rejectsEncoderBackedByAnotherDataInstanceWithoutPublishing() {
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL);
        var encoderData = emptyData();
        protocol.encoderFactory = ignored -> new PacketEncoder_v766(encoderData);

        assertThrows(IllegalStateException.class, protocol::initialize);
        assertFalse(protocol.isInitialized());
    }

    @Test
    void rejectsDuplicateDefinitionsBeforePublishing() {
        var first = mock(ItemDefinition.class);
        var duplicate = mock(ItemDefinition.class);
        when(first.runtimeId()).thenReturn(7);
        when(duplicate.runtimeId()).thenReturn(7);
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL);
        protocol.itemDefinitions = List.of(first, duplicate);

        assertThrows(IllegalArgumentException.class, protocol::initialize);
        assertFalse(protocol.isInitialized());
        assertThrows(IllegalStateException.class, protocol::getEncoder);
    }

    @Test
    void concurrentInitializationPublishesAllComponentsExactlyOnce() throws Exception {
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL);
        var readerStarted = new CountDownLatch(1);
        var initializeTogether = new CountDownLatch(1);
        var successes = new AtomicInteger();
        var duplicateAttempts = new AtomicInteger();
        var unexpectedFailure = new AtomicReference<Throwable>();

        var reader = Thread.ofPlatform().start(() -> {
            readerStarted.countDown();
            try {
                long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
                while (!protocol.isInitialized() && System.nanoTime() < deadline) {
                    Thread.onSpinWait();
                }
                assertTrue(protocol.isInitialized());
                assertSame(protocol.getData(), protocol.getEncoder().getData());
                assertTrue(protocol.supports(ProtocolFeature.DATA_DRIVEN_UI));
            } catch (Throwable throwable) {
                unexpectedFailure.compareAndSet(null, throwable);
            }
        });
        Runnable initialize = () -> {
            try {
                initializeTogether.await();
                protocol.initialize();
                successes.incrementAndGet();
            } catch (IllegalStateException exception) {
                duplicateAttempts.incrementAndGet();
            } catch (Throwable throwable) {
                unexpectedFailure.compareAndSet(null, throwable);
            }
        };
        var firstInitializer = Thread.ofPlatform().start(initialize);
        var secondInitializer = Thread.ofPlatform().start(initialize);

        assertTrue(readerStarted.await(5, TimeUnit.SECONDS));
        initializeTogether.countDown();
        firstInitializer.join(TimeUnit.SECONDS.toMillis(6));
        secondInitializer.join(TimeUnit.SECONDS.toMillis(6));
        reader.join(TimeUnit.SECONDS.toMillis(6));

        assertFalse(firstInitializer.isAlive());
        assertFalse(secondInitializer.isAlive());
        assertFalse(reader.isAlive());
        assertNull(unexpectedFailure.get(), () -> String.valueOf(unexpectedFailure.get()));
        assertEquals(1, successes.get());
        assertEquals(1, duplicateAttempts.get());
    }

    private static ProtocolData emptyData() {
        return new ProtocolData(
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                new RecipeTable(List.of(), List.of(), new Int2ObjectOpenHashMap<Recipe>())
        );
    }

    private static final class TestProtocol extends Protocol {
        private List<ItemDefinition> itemDefinitions = List.of();
        private Function<ProtocolData, PacketEncoder> encoderFactory = PacketEncoder_v766::new;

        private TestProtocol(ClientVariant variant) {
            super(CODEC, variant);
        }

        @Override
        protected void registerProcessors(PacketProcessorRegistry registry) {
        }

        @Override
        protected List<ItemDefinition> createItemDefinitions() {
            return itemDefinitions;
        }

        @Override
        protected List<BlockDefinition> createBlockDefinitions() {
            return List.of();
        }

        @Override
        protected List<CreativeItemGroup> createCreativeGroups() {
            return List.of();
        }

        @Override
        protected List<CreativeItemData> createCreativeItems() {
            return List.of();
        }

        @Override
        protected List<BlockPropertyData> createCustomBlockProperties() {
            return List.of();
        }

        @Override
        protected RecipeTable createRecipeTable() {
            return new RecipeTable(List.of(), List.of(), new Int2ObjectOpenHashMap<Recipe>());
        }

        @Override
        protected PacketEncoder createEncoder(ProtocolData data) {
            return encoderFactory.apply(data);
        }

        @Override
        protected Set<ProtocolFeature> createFeatures() {
            return Set.of(ProtocolFeature.DATA_DRIVEN_UI);
        }
    }
}
