package org.allaymc.server;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.LoginData;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerState;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.utils.GitProperties;
import org.bstats.MetricsBase;
import org.bstats.charts.AdvancedPie;
import org.bstats.charts.DrilldownPie;
import org.bstats.charts.SimplePie;
import org.bstats.charts.SingleLineChart;
import org.bstats.json.JsonObjectBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * {@code AllayMetrics} registers bStats measurements for the server process and
 * its connected players.
 * Reporting is controlled by server configuration, and the reporter's enabled
 * predicate follows {@code ServerState.RUNNING}. Chart suppliers read current platform,
 * protocol, version, and player data when the metrics library requests samples.
 * Repeated registration is not suppressed, so bootstrap is responsible for
 * invoking startup once for its server lifecycle.
 *
 * <p>
 * Java version reporting retains the complete runtime version as a nested
 * label while deriving a broader release-family bucket. Legacy 1.x versions
 * and modern major-version strings use different grouping rules; early-access
 * suffixes are removed from the modern family label when numeric major digits
 * are found, without losing those suffixes from the full version label.
 *
 * @author daoge_cmd | IWareQ
 */
@Slf4j
public class AllayMetrics {
    /**
     * Creates a metrics reporter and registers charts when reporting is enabled.
     * Disabled configuration returns immediately. Each invocation creates a new
     * reporter; sampling is delegated to bStats and gated by the server's
     * {@code ServerState.RUNNING} rather than by retaining a reporter here.
     */
    public static void start() {
        var settings = AllayServer.getSettings().bStatsSettings();
        if (!settings.enable()) {
            return;
        }

        var server = Server.getInstance();
        var metrics = new MetricsBase(
                "server-implementation",
                settings.serverUUID(),
                23352, // Allay's registered bStats service identifier: https://bstats.org/plugin/server-implementation/Allay/23352
                true,
                AllayMetrics::appendPlatformData,
                builder -> {},
                null,
                () -> server.getState() == ServerState.RUNNING,
                log::error,
                log::info,
                settings.logFailedRequests(),
                settings.logSentData(),
                settings.logResponseStatusText(),
                true
        );

        metrics.addCustomChart(new SingleLineChart("players", server.getPlayerManager()::getPlayerCount));
        metrics.addCustomChart(new SimplePie("minecraft_version", () -> ProtocolInfo.getLatestCodec().getMinecraftVersion()));
        metrics.addCustomChart(new SimplePie("allay_api_version", GitProperties::getBuildApiVersion));
        metrics.addCustomChart(new SimplePie("allay_server_version", GitProperties::getBuildVersion));
        // Report maximum heap in binary gigabytes rounded to two decimal places.
        metrics.addCustomChart(new SimplePie("max_memory", () -> String.format("%.2f", Runtime.getRuntime().maxMemory() / (1024d * 1024d * 1024d)) + "G"));
        metrics.addCustomChart(new SimplePie("xbox_auth", () -> AllayServer.getSettings().networkSettings().xboxAuth() ? "Required" : "Not required"));

        metrics.addCustomChart(new AdvancedPie("player_platform", () -> server.getPlayerManager().getPlayers().values().stream()
                .map(Player::getLoginData)
                .map(LoginData::getDeviceInfo)
                .map(LoginData.DeviceInfo::device)
                .collect(Collectors.groupingBy(LoginData.Device::getName, countingInt()))));

        metrics.addCustomChart(new AdvancedPie("player_game_version", () -> server.getPlayerManager().getPlayers().values().stream()
                .map(Player::getLoginData)
                .collect(Collectors.groupingBy(LoginData::getGameVersion, countingInt()))));

        metrics.addCustomChart(new DrilldownPie("java_version", new JavaVersionRetriever()));
    }

    /**
     * Appends operating-system identity and available processor count to the payload.
     */
    private static void appendPlatformData(JsonObjectBuilder builder) {
        builder.appendField("osName", System.getProperty("os.name"));
        builder.appendField("osArch", System.getProperty("os.arch"));
        builder.appendField("osVersion", System.getProperty("os.version"));
        builder.appendField("coreCount", Runtime.getRuntime().availableProcessors());
    }

    /**
     * Counts stream elements into the integer values required by player charts.
     */
    public static <T> Collector<T, ?, Integer> countingInt() {
        return Collectors.reducing(0, e -> 1, Integer::sum);
    }

    /**
     * {@code JavaVersionRetriever} groups the exact Java version under its release
     * family for bStats.
     */
    private static class JavaVersionRetriever implements Callable<Map<String, Map<String, Integer>>> {
        // The following code can be attributed to the PaperMC project
        /**
         * Builds one release-family bucket containing the unmodified Java version.
         * The complete {@code java.version} string is stored first so grouping cannot
         * discard patch or prerelease information from the nested chart label.
         * Legacy 1.x versions use the prefix before the final dot as their
         * family. Modern versions use the first dot-separated field, replacing
         * it with its first numeric run when available; a field without digits
         * is retained as supplied. Each invocation counts this process once
         * in the resulting family and exact-version pair.
         */
        @Override
        public Map<String, Map<String, Integer>> call() {
            Map<String, Map<String, Integer>> map = new HashMap<>();
            String javaVersion = System.getProperty("java.version");
            Map<String, Integer> entry = new HashMap<>();
            entry.put(javaVersion, 1);

            // http://openjdk.java.net/jeps/223
            // Java 9 and later use the JEP 223 version scheme. Keep legacy 1.x
            // grouping separate so earlier versions retain their release-family labels.
            String majorVersion = javaVersion.split("\\.")[0];
            String release;

            int indexOf = javaVersion.lastIndexOf('.');

            if (majorVersion.equals("1")) {
                release = "Java " + javaVersion.substring(0, indexOf);
            } else {
                // A modern first field can include a prerelease suffix such as
                // -ea; use its numeric run for grouping while preserving the full nested label.
                Matcher versionMatcher = Pattern.compile("\\d+").matcher(majorVersion);
                if (versionMatcher.find()) {
                    majorVersion = versionMatcher.group(0);
                }
                release = "Java " + majorVersion;
            }
            map.put(release, entry);
            return map;
        }
    }
}
