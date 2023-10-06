package cn.allay.api.server;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.CustomKey;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.cloudburstmc.protocol.bedrock.data.GameType;

import java.util.Objects;

/**
 * Server settings
 */
@Getter
@Accessors(fluent = true)
public class ServerSettings extends OkaeriConfig {

    @CustomKey("generic-settings")
    private final GenericSettings genericSettings = new GenericSettings();

    @Getter
    @Accessors(fluent = true)
    public static class GenericSettings extends OkaeriConfig {

        private final String motd = "An allay-powered server";

        @CustomKey("sub-motd")
        private final String subMotd = "https://github.com/AllayMC/Allay";

        @CustomKey("max-client-count")
        private final int maxClientCount = 20;

        @CustomKey("game-type")
        private final GameType defaultGameType = GameType.CREATIVE;
    }

    @CustomKey("network-settings")
    private final NetworkSettings networkSettings = new NetworkSettings();

    @Getter
    @Accessors(fluent = true)
    public static class NetworkSettings extends OkaeriConfig {

        private final String ip = "0.0.0.0";

        private final int port = 19132;

        @CustomKey("xbox-auth")
        private final boolean xboxAuth = true;

        @CustomKey("enable-network-encryption")
        private final boolean enableNetworkEncryption = true;
    }

    @CustomKey("chunk-settings")
    private final ChunkSettings chunkSettings = new ChunkSettings();

    @Getter
    @Accessors(fluent = true)
    public static class ChunkSettings extends OkaeriConfig {

        @CustomKey("ticking-radius")
        private final int tickingRadius = 8;

        @CustomKey("view-distance")
        private final int viewDistance = 16;

        @CustomKey("chunk-try-send-count-per-tick")
        private final int chunkTrySendCountPerTick = 32;
    }
}
