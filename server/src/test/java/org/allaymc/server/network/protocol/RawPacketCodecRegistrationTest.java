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

@ExtendWith(AllayTestExtension.class)
class RawPacketCodecRegistrationTest {
    private static final Pattern PACKET_CONSTRUCTION = Pattern.compile(
            "\\bnew\\s+([A-Za-z][A-Za-z0-9_]*Packet)\\s*\\("
    );
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
    private static ProtocolRegistry registry;

    @BeforeAll
    static void setUpRegistry() {
        registry = ProtocolRegistry.getDefault();
    }

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

    private static Set<String> findPacketConstructions(Path sourceFile) throws IOException {
        var packetNames = new HashSet<String>();
        var matcher = PACKET_CONSTRUCTION.matcher(Files.readString(sourceFile));
        while (matcher.find()) {
            packetNames.add(matcher.group(1));
        }
        return packetNames;
    }

    private static Path findSourceRoot() {
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

    private static Class<? extends BedrockPacket> packetClass(String simpleName) {
        try {
            return Class.forName("org.cloudburstmc.protocol.bedrock.packet." + simpleName)
                    .asSubclass(BedrockPacket.class);
        } catch (ClassNotFoundException | ClassCastException exception) {
            return fail("Cannot resolve directly constructed packet " + simpleName, exception);
        }
    }

    private static void assertRegistered(
            Protocol protocol,
            Class<? extends BedrockPacket> packetClass
    ) {
        assertNotNull(
                protocol.getCodec().getPacketDefinition(packetClass),
                () -> protocol + " does not register directly constructed " + packetClass.getSimpleName()
        );
    }

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

    private record RawPacketUse(String sourcePath, String packetName) {
    }
}
