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
 * @author daoge_cmd | IWareQ
 */
@Slf4j
public class AllayMetrics {
    public static void start() {
        var settings = AllayServer.getSettings().bStatsSettings();
        if (!settings.enable()) {
            return;
        }

        var server = Server.getInstance();
        var metrics = new MetricsBase(
                "server-implementation",
                settings.serverUUID(),
                23352, // https://bstats.org/plugin/server-implementation/Allay/23352
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

    private static void appendPlatformData(JsonObjectBuilder builder) {
        builder.appendField("osName", System.getProperty("os.name"));
        builder.appendField("osArch", System.getProperty("os.arch"));
        builder.appendField("osVersion", System.getProperty("os.version"));
        builder.appendField("coreCount", Runtime.getRuntime().availableProcessors());
    }

    public static <T> Collector<T, ?, Integer> countingInt() {
        return Collectors.reducing(0, e -> 1, Integer::sum);
    }

    private static class JavaVersionRetriever implements Callable<Map<String, Map<String, Integer>>> {
        // The following code can be attributed to the PaperMC project
        @Override
        public Map<String, Map<String, Integer>> call() {
            Map<String, Map<String, Integer>> map = new HashMap<>();
            String javaVersion = System.getProperty("java.version");
            Map<String, Integer> entry = new HashMap<>();
            entry.put(javaVersion, 1);

            // http://openjdk.java.net/jeps/223
            // Java decided to change their versioning scheme and in doing so modified the java.version system
            // property to return $major[.$minor][.$security][-ea], as opposed to 1.$major.0_$identifier
            // we can handle pre-9 by checking if the "major" is equal to "1", otherwise, 9+
            String majorVersion = javaVersion.split("\\.")[0];
            String release;

            int indexOf = javaVersion.lastIndexOf('.');

            if (majorVersion.equals("1")) {
                release = "Java " + javaVersion.substring(0, indexOf);
            } else {
                // of course, it really wouldn't be all that simple if they didn't add a quirk, now would it
                // valid strings for the major may potentially include values such as -ea to deannotate a pre-release
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
