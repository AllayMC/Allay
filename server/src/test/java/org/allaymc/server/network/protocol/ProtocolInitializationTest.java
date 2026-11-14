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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * {@code ProtocolInitializationTest} checks protocol initialization
 * publication, component identity, and rejection of incomplete data.
 */
class ProtocolInitializationTest {
    /**
     * {@code CODEC} supplies one stable codec identity across initialization
     * fixtures. Tests vary component construction while retaining the same
     * wire-version selection.
     */
    private static final BedrockCodec CODEC = Bedrock_v819.CODEC;

    /**
     * Observes an uninitialized protocol before completing its component
     * construction. Data and encoder access must fail initially, then share one
     * data instance after success, while repeated initialization is rejected.
     */
    @Test
    void publishesOnlyAfterEveryComponentMatches() {
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL);

        assertFalse(protocol.isInitialized());
        assertThrows(IllegalStateException.class, protocol::getData);
        assertThrows(IllegalStateException.class, protocol::getEncoder);

        protocol.initialize();

        assertTrue(protocol.isInitialized());
        assertSame(protocol.getData(), protocol.getEncoder().getData());
        assertThrows(IllegalStateException.class, protocol::initialize);
    }

    /**
     * Makes the item-definition hook return {@code null} during initialization.
     * Construction must fail before readiness is published, and the data
     * accessor must continue rejecting access afterward.
     */
    @Test
    void rejectsMissingGeneratedDataWithoutPublishing() {
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL);
        protocol.itemDefinitions = null;

        assertThrows(NullPointerException.class, protocol::initialize);
        assertFalse(protocol.isInitialized());
        assertThrows(IllegalStateException.class, protocol::getData);
    }

    /**
     * Makes the encoder factory return {@code null} after data construction.
     * Initialization must reject the missing encoder and leave readiness
     * {@code false}, with the encoder accessor still unavailable.
     */
    @Test
    void rejectsMissingEncoderWithoutPublishing() {
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL);
        protocol.encoderFactory = ignored -> null;

        assertThrows(NullPointerException.class, protocol::initialize);
        assertFalse(protocol.isInitialized());
        assertThrows(IllegalStateException.class, protocol::getEncoder);
    }

    /**
     * Creates an encoder backed by a separate empty data bundle. Initialization
     * must reject the identity mismatch even though both bundles describe empty
     * payloads, and readiness must remain {@code false}.
     */
    @Test
    void rejectsEncoderBackedByAnotherDataInstanceWithoutPublishing() {
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL);
        var encoderData = emptyData();
        protocol.encoderFactory = ignored -> new PacketEncoder_v766(encoderData);

        assertThrows(IllegalStateException.class, protocol::initialize);
        assertFalse(protocol.isInitialized());
    }

    /**
     * Supplies two item definitions with the same runtime ID.
     * Definition-registry construction must reject the duplicate before
     * readiness is published or the encoder becomes available to callers.
     */
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

    /**
     * Releases two initializer threads while a reader waits for protocol
     * readiness. Exactly one attempt must succeed, the other must report a
     * duplicate, and the reader must observe matching data and encoder
     * identities.
     */
    @Test
    void concurrentInitializationPublishesAllComponentsExactlyOnce() throws Exception {
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL);
        var readerStarted = new CountDownLatch(1);
        var initializeTogether = new CountDownLatch(1);
        var successes = new AtomicInteger();
        var duplicateAttempts = new AtomicInteger();
        var unexpectedFailure = new AtomicReference<Throwable>();

        // Observe readiness from another thread and retain its failure for the
        // joining test thread; a bounded wait detects missing publication.
        var reader = Thread.ofPlatform().start(() -> {
            readerStarted.countDown();
            try {
                long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
                while (!protocol.isInitialized() && System.nanoTime() < deadline) {
                    Thread.onSpinWait();
                }
                assertTrue(protocol.isInitialized());
                assertSame(protocol.getData(), protocol.getEncoder().getData());
            } catch (Throwable throwable) {
                unexpectedFailure.compareAndSet(null, throwable);
            }
        });
        // Hold both attempts at one latch so they compete for initialization;
        // count expected duplicate rejection separately from unexpected
        // background failures.
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

        // Release initialization only after the reader starts, then join all
        // workers so their counters and failures are available to assertions.
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

    /**
     * Creates a protocol data bundle with empty definitions, inventory, and
     * recipes.
     */
    private static ProtocolData emptyData() {
        return new ProtocolData(
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                new RecipeTable(List.of(), List.of(), new Int2ObjectOpenHashMap<Recipe>()),
                NetworkBlockPalette.current()
        );
    }

    /**
     * {@code TestProtocol} provides replaceable definition and encoder hooks
     * for isolated initialization failure tests.
     */
    private static final class TestProtocol extends Protocol {
        /**
         * {@code itemDefinitions} supplies the item-definition hook result.
         * Tests replace this list, including with {@code null} or duplicate
         * runtime IDs, before {@code initialize} to select a failure path.
         */
        private List<ItemDefinition> itemDefinitions = List.of();
        /**
         * {@code encoderFactory} builds the fixture encoder from initialization
         * data. Tests replace this factory before {@code initialize} to model a
         * {@code null} result or an encoder retaining another data instance.
         */
        private Function<ProtocolData, PacketEncoder> encoderFactory = PacketEncoder_v766::new;

        /**
         * Creates an uninitialized fixture using the shared codec and requested
         * distribution.
         */
        private TestProtocol(ClientVariant variant) {
            super(CODEC, variant);
        }

        /**
         * Leaves processor registration empty so tests isolate data and encoder
         * initialization.
         */
        @Override
        protected void registerProcessors(PacketProcessorRegistry registry) {
        }

        /**
         * Returns the item-definition value selected by the current test. The
         * value may deliberately be {@code null} or contain duplicate IDs,
         * allowing initialization validation to exercise those failure paths.
         */
        @Override
        protected List<ItemDefinition> createItemDefinitions() {
            return itemDefinitions;
        }

        /**
         * Returns an empty block-definition list for the initialization
         * fixture.
         */
        @Override
        protected List<BlockDefinition> createBlockDefinitions() {
            return List.of();
        }

        /**
         * Returns no creative groups for the isolated initialization fixture.
         */
        @Override
        protected List<CreativeItemGroup> createCreativeGroups() {
            return List.of();
        }

        /**
         * Returns no creative entries for the isolated initialization fixture.
         */
        @Override
        protected List<CreativeItemData> createCreativeItems() {
            return List.of();
        }

        /**
         * Returns no custom block properties for the initialization fixture.
         */
        @Override
        protected List<BlockPropertyData> createCustomBlockProperties() {
            return List.of();
        }

        /**
         * Creates an empty consistent recipe table for the initialization
         * fixture.
         */
        @Override
        protected RecipeTable createRecipeTable() {
            return new RecipeTable(List.of(), List.of(), new Int2ObjectOpenHashMap<Recipe>());
        }

        /**
         * Invokes the factory selected by the test with the supplied data
         * bundle. The result is intentionally not corrected or validated here,
         * allowing the production initialization method to detect invalid
         * encoders.
         */
        @Override
        protected PacketEncoder createEncoder(ProtocolData data) {
            return encoderFactory.apply(data);
        }

    }
}
