package org.allaymc.server.network.protocol;

import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code RawPacketCodecRegistrationTest} checks source-level packet
 * construction against the registered protocol codec matrix.
 */
@ExtendWith(AllayTestExtension.class)
class RawPacketCodecRegistrationTest {
    /**
     * {@code PACKET_CONSTRUCTION} recognizes simple packet constructor text for
     * the source audit. The regex includes matching comments and string
     * literals, so documentation can also affect its results.
     */
    private static final Pattern PACKET_CONSTRUCTION = Pattern.compile(
            "\\bnew\\s+([A-Za-z][A-Za-z0-9_]*Packet)\\s*\\("
    );
    /**
     * {@code RAW_PACKET_ALLOWLIST} contains permitted source-path and
     * packet-name pairs outside protocol encoders. The source audit requires
     * discovered constructions to match this set exactly.
     */
    private static final Set<RawPacketUse> RAW_PACKET_ALLOWLIST = Set.of(
            new RawPacketUse(
                    "org/allaymc/server/network/processor/login/RequestNetworkSettingsPacketProcessor.java",
                    "PlayStatusPacket"
            ),
            new RawPacketUse(
                    "org/allaymc/server/network/processor/login/RequestNetworkSettingsPacketProcessor.java",
                    "NetworkSettingsPacket"
            ),
            new RawPacketUse(
                    "org/allaymc/server/network/processor/login/LoginPacketProcessor.java",
                    "ServerToClientHandshakePacket"
            ),
            new RawPacketUse(
                    "org/allaymc/server/network/processor/ingame/PlayerAuthInputPacketProcessor.java",
                    "ItemStackRequestPacket"
            ),
            new RawPacketUse(
                    "org/allaymc/server/player/AllayPlayer.java",
                    "PlayStatusPacket"
            )
    );
    /**
     * {@code registry} retains the extension-installed default registry after
     * setup. Every case reuses its initialized protocols and cached definitions
     * so repeated encodings compare the same source data.
     */
    private static ProtocolRegistry registry;

    /**
     * Captures the extension-installed default registry for the codec matrix.
     * All tests reuse its initialized protocols and neither register new
     * versions nor replace the process-wide default registry.
     */
    @BeforeAll
    static void setUpRegistry() {
        registry = ProtocolRegistry.getDefault();
    }

    /**
     * Scans production sources outside the protocol encoder package for direct
     * packet construction. The discovered path-and-name pairs must equal the
     * allowlist, and every target codec must register their packet classes.
     */
    @Test
    void directlyConstructedRawPacketsAreAcceptedByTheirTargetCodecs() throws IOException {
        var rawPacketUses = findDirectPacketConstructions();
        assertEquals(RAW_PACKET_ALLOWLIST, rawPacketUses);

        for (var rawPacketUse : rawPacketUses) {
            var packetClass = packetClass(rawPacketUse.packetName());
            for (var protocol : registry.getProtocols()) {
                assertRegistered(protocol, packetClass);
            }
        }
    }

    /**
     * Scans the baseline encoder for directly constructed packet classes. The
     * scan must find packets, and every configured protocol must register each
     * discovered class before inherited encoding is considered compatible.
     */
    @Test
    void baselineEncoderPacketsAreAcceptedByEveryTargetCodec() throws IOException {
        var encoderSource = findSourceRoot().resolve(
                "org/allaymc/server/network/protocol/v766/PacketEncoder_v766.java"
        );
        var packetNames = findPacketConstructions(encoderSource);
        assertFalse(packetNames.isEmpty());

        for (var packetName : packetNames) {
            var packetClass = packetClass(packetName);
            for (var protocol : registry.getProtocols()) {
                assertRegistered(protocol, packetClass);
            }
        }
    }

    /**
     * Checks skin-confirmation packet registration across the complete
     * initialized protocol set. The packet must be registered exactly for
     * NetEase variants and absent from international variants.
     */
    @Test
    void confirmSkinRegistrationIsNetEaseOnly() {
        for (var protocol : registry.getProtocols()) {
            var registered = protocol.getCodec().getPacketDefinition(ConfirmSkinPacket.class) != null;
            assertEquals(
                    protocol.getVariant() == ClientVariant.NETEASE,
                    registered,
                    () -> protocol + " ConfirmSkinPacket registration"
            );
        }
    }

    /**
     * Checks primitive-shape, data-store, UI, and voxel packet registrations
     * for each initialized protocol. Registration presence must match the
     * explicit numeric introduction boundaries asserted by this test.
     */
    @Test
    void versionGatedPacketRegistrationsMatchTheirProtocolBoundaries() {
        for (var protocol : registry.getProtocols()) {
            int version = protocol.getProtocolVersion();
            assertRegistration(protocol, PrimitiveShapesPacket.class, version >= 818);
            assertRegistration(protocol, ClientboundDataStorePacket.class, version >= 898);
            assertRegistration(protocol, ClientboundDataDrivenUIShowScreenPacket.class, version >= 924);
            assertRegistration(protocol, ClientboundDataDrivenUICloseScreenPacket.class, version >= 924);
            assertRegistration(protocol, VoxelShapesPacket.class, version >= 924);
        }
    }

    /**
     * Collects source-path and packet-name pairs outside the protocol package.
     * Paths are normalized to forward slashes for allowlist comparison,
     * duplicate pairs collapse into a set, and file traversal or reading
     * failures propagate.
     */
    private static Set<RawPacketUse> findDirectPacketConstructions() throws IOException {
        var sourceRoot = findSourceRoot();
        var rawPacketUses = new HashSet<RawPacketUse>();
        try (var files = Files.walk(sourceRoot)) {
            for (var file : files.filter(path -> path.toString().endsWith(".java")).toList()) {
                var relative = sourceRoot.relativize(file).toString().replace('\\', '/');
                if (relative.startsWith("org/allaymc/server/network/protocol/")) {
                    continue;
                }

                for (var packetName : findPacketConstructions(file)) {
                    rawPacketUses.add(new RawPacketUse(relative, packetName));
                }
            }
        }
        return rawPacketUses;
    }

    /**
     * Returns distinct simple packet names matched in the source file text.
     * This regex scan includes any matching comments or string literals and is
     * intentionally narrower than a full Java syntax analysis.
     */
    private static Set<String> findPacketConstructions(Path sourceFile) throws IOException {
        var packetNames = new HashSet<String>();
        var matcher = PACKET_CONSTRUCTION.matcher(Files.readString(sourceFile));
        while (matcher.find()) {
            packetNames.add(matcher.group(1));
        }
        return packetNames;
    }

    /**
     * Returns the first existing server source root among the supported
     * working-directory layouts. Candidate order covers the test directory,
     * repository root, and module root; no match fails the test with a
     * source-location message.
     */
    private static Path findSourceRoot() {
        // Tests can run from several working directories, so choose the first
        // existing source layout before scanning rather than assuming the
        // repository root.
        for (var candidate : List.of(
                Path.of("..", "server", "src", "main", "java"),
                Path.of("server", "src", "main", "java"),
                Path.of("src", "main", "java")
        )) {
            if (Files.isDirectory(candidate)) {
                return candidate.normalize();
            }
        }
        return fail("Cannot locate server production sources");
    }

    /**
     * Resolves a simple packet name in the Cloudburst packet package. Missing
     * classes or classes that do not implement {@code BedrockPacket} fail the
     * test with the original resolution exception attached.
     */
    private static Class<? extends BedrockPacket> packetClass(String simpleName) {
        try {
            return Class.forName("org.cloudburstmc.protocol.bedrock.packet." + simpleName)
                    .asSubclass(BedrockPacket.class);
        } catch (ClassNotFoundException | ClassCastException exception) {
            // Convert reflection failures into an assertion with the packet
            // name and original cause so a missing codec dependency remains
            // diagnosable.
            return fail("Cannot resolve directly constructed packet " + simpleName, exception);
        }
    }

    /**
     * Checks that the selected codec registers the supplied packet class.
     */
    private static void assertRegistered(
            Protocol protocol,
            Class<? extends BedrockPacket> packetClass
    ) {
        assertNotNull(
                protocol.getCodec().getPacketDefinition(packetClass),
                () -> protocol + " does not register directly constructed " + packetClass.getSimpleName()
        );
    }

    /**
     * Checks packet registration against the expected presence for one
     * protocol.
     */
    private static void assertRegistration(
            Protocol protocol,
            Class<? extends BedrockPacket> packetClass,
            boolean expected
    ) {
        assertEquals(
                expected,
                protocol.getCodec().getPacketDefinition(packetClass) != null,
                () -> protocol + " " + packetClass.getSimpleName() + " registration"
        );
    }

    /**
     * {@code RawPacketUse} identifies one source file and packet class admitted
     * by the raw-construction audit.
     *
     * @param sourcePath relative Java source path with forward slashes
     *
     * @param packetName simple constructed packet class name
     */
    private record RawPacketUse(String sourcePath, String packetName) {
    }
}
