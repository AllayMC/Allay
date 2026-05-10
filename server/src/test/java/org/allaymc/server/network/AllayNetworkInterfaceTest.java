package org.allaymc.server.network;

import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class AllayNetworkInterfaceTest {

    private final BedrockServerSession session = mock(BedrockServerSession.class);

    @Test
    void shouldTreatMissingRakVersionAsInternationalClient() {
        var networkInterface = new TestNetworkInterface(OptionalInt.empty());

        assertFalse(networkInterface.isNetEaseClient(session));
    }

    @Test
    void shouldDetectNetEaseClientFromRakVersion() {
        var networkInterface = new TestNetworkInterface(OptionalInt.of(8));

        assertTrue(networkInterface.isNetEaseClient(session));
    }

    private static final class TestNetworkInterface extends AllayNetworkInterface {

        private final OptionalInt rakNetProtocolVersion;
        private String motd = "";
        private String subMotd = "";

        private TestNetworkInterface(OptionalInt rakNetProtocolVersion) {
            this.rakNetProtocolVersion = rakNetProtocolVersion;
        }

        @Override
        public int getPing(BedrockServerSession session) {
            return -1;
        }

        @Override
        public OptionalInt getRakNetProtocolVersion(BedrockServerSession session) {
            return rakNetProtocolVersion;
        }

        @Override
        public String getMotd() {
            return motd;
        }

        @Override
        public void setMotd(String motd) {
            this.motd = motd;
        }

        @Override
        public String getSubMotd() {
            return subMotd;
        }

        @Override
        public void setSubMotd(String subMotd) {
            this.subMotd = subMotd;
        }
    }
}
