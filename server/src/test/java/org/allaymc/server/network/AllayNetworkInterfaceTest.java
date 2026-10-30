package org.allaymc.server.network;

import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * {@code AllayNetworkInterfaceTest} checks transport metadata used for NetEase client detection.
 */
class AllayNetworkInterfaceTest {

    /** Session supplied to detection tests. */
    private final BedrockServerSession session = mock(BedrockServerSession.class);

    /**
     * Supplies a transport with no RakNet version metadata. The shared detector
     * must return {@code false} rather than classify the unknown connection as
     * a NetEase client.
     */
    @Test
    void shouldTreatMissingRakVersionAsInternationalClient() {
        var networkInterface = new TestNetworkInterface(OptionalInt.empty());

        assertFalse(networkInterface.isNetEaseClient(session));
    }

    /**
     * Supplies the RakNet version used to identify NetEase clients. The shared
     * detector must recognize that version through the transport override and
     * return {@code true}.
     */
    @Test
    void shouldDetectNetEaseClientFromRakVersion() {
        var networkInterface = new TestNetworkInterface(OptionalInt.of(8));

        assertTrue(networkInterface.isNetEaseClient(session));
    }

    /**
     * {@code TestNetworkInterface} supplies controlled version metadata without
     * opening network resources.
     */
    private static final class TestNetworkInterface extends AllayNetworkInterface {

        /** Version result selected by each test. */
        private final OptionalInt rakNetProtocolVersion;
        /** Mutable primary description text. */
        private String motd = "";
        /** Mutable secondary description text. */
        private String subMotd = "";

        /**
         * Creates a transport stub returning the supplied RakNet version result.
         */
        private TestNetworkInterface(OptionalInt rakNetProtocolVersion) {
            this.rakNetProtocolVersion = rakNetProtocolVersion;
        }

        /**
         * Returns an unavailable latency result without inspecting the mocked session.
         * Detection tests use only the version override, so this transport stub
         * supplies {@code -1} without creating a RakNet channel or codec.
         */
        @Override
        public int getPing(BedrockServerSession session) {
            return -1;
        }

        /**
         * Returns the optional RakNet version chosen when constructing the stub.
         * An empty result models a transport without version metadata; the shared
         * detector consumes this result without consulting the mocked session.
         */
        @Override
        public OptionalInt getRakNetProtocolVersion(BedrockServerSession session) {
            return rakNetProtocolVersion;
        }

        /**
         * Returns the primary description currently retained by this transport stub.
         */
        @Override
        public String getMotd() {
            return motd;
        }

        /**
         * Stores the primary description for subsequent reads from this transport stub.
         */
        @Override
        public void setMotd(String motd) {
            this.motd = motd;
        }

        /**
         * Returns the secondary description currently retained by this transport stub.
         */
        @Override
        public String getSubMotd() {
            return subMotd;
        }

        /**
         * Stores the secondary description for subsequent reads from this transport stub.
         */
        @Override
        public void setSubMotd(String subMotd) {
            this.subMotd = subMotd;
        }
    }
}
