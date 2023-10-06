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
    private GenericSettings genericSettings = new GenericSettings();

    @Getter
    @Accessors(fluent = true)
    public class GenericSettings extends OkaeriConfig {

        private String motd = "An allay-powered server";

        @CustomKey("sub-motd")
        private String subMotd = "https://github.com/AllayMC/Allay";

        @CustomKey("max-client-count")
        private int maxClientCount = 20;

        @CustomKey("game-type")
        private GameType defaultGameType = GameType.CREATIVE;
    }

    @CustomKey("network-settings")
    private NetworkSettings networkSettings = new NetworkSettings();

    @Getter
    @Accessors(fluent = true)
    public class NetworkSettings extends OkaeriConfig {

        private String ip = "0.0.0.0";

        private int port = 19132;

        @CustomKey("xbox-auth")
        private boolean xboxAuth = true;

        @CustomKey("enable-network-encryption")
        private boolean enableNetworkEncryption = true;
    }

    @CustomKey("chunk-settings")
    private ChunkSettings chunkSettings = new ChunkSettings();

    @Getter
    @Accessors(fluent = true)
    public class ChunkSettings extends OkaeriConfig {

        @CustomKey("ticking-radius")
        private int tickingRadius = 8;

        @CustomKey("view-distance")
        private int viewDistance = 16;

        @CustomKey("chunk-try-send-count-per-tick")
        private int chunkTrySendCountPerTick = 32;
    }
}
