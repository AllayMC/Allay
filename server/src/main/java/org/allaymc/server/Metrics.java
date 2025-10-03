package org.allaymc.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.player.LoginData;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerState;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.utils.GitProperties;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

import static java.util.stream.Collectors.groupingBy;

/**
 * bStats collects some data for plugin authors.
 * <p>
 * Check out <a href="https://bStats.org/">bStats</a> to learn more about bStats!
 */
@Slf4j
public class Metrics {

    // The version of this bStats class
    public static final int B_STATS_VERSION = 1;
    // The url to which the data is sent
    private static final String URL = "https://bStats.org/submitData/server-implementation";
    // Should failed requests be logged?
    private static boolean logFailedRequests = false;
    // Executor service for requests
    // We use an executor service because the server scheduler is affected by server lags
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, t -> new Thread(t, "metrics-scheduler-thread"));
    // The name of the server software
    private final String name;

    // The uuid of the server
    private final String serverUUID;

    // A list with all custom charts
    private final List<CustomChart> charts = new ArrayList<>();

    /**
     * Class constructor.
     *
     * @param name              The name of the server software
     * @param serverUUID        The uuid of the server
     * @param logFailedRequests Whether failed requests should be logged or not
     */
    public Metrics(String name, String serverUUID, boolean logFailedRequests) {
        this.name = name;
        this.serverUUID = serverUUID;
        Metrics.logFailedRequests = logFailedRequests;

        // Start submitting the data
        startSubmitting();
    }

    /**
     * Sends the data to the bStats server.
     *
     * @param data The data to send
     * @throws Exception If the request failed
     */
    private static void sendData(JsonObject data) throws Exception {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        HttpsURLConnection connection = (HttpsURLConnection) URI.create(URL).toURL().openConnection();

        // Compress the data to save bandwidth
        byte[] compressedData = compress(data.toString());

        // Add headers
        connection.setRequestMethod("POST");
        connection.addRequestProperty("Accept", "application/json");
        connection.addRequestProperty("Connection", "close");
        connection.addRequestProperty("Content-Encoding", "gzip"); // We gzip our request
        connection.addRequestProperty("Content-Length", String.valueOf(compressedData.length));
        connection.setRequestProperty("Content-Type", "application/json"); // We send our data in JSON format
        connection.setRequestProperty("User-Agent", "MC-Server/" + B_STATS_VERSION);

        // Send data
        connection.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.write(compressedData);
        outputStream.flush();
        outputStream.close();

        connection.getInputStream().close(); // We don't care about the response - Just send our data :)
    }

    /**
     * G-zips the given String.
     *
     * @param str The string to gzip
     * @return The gzipped String
     * @throws IOException If the compression failed
     */
    private static byte[] compress(final String str) throws IOException {
        if (str == null) {
            return null;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(outputStream);
        gzip.write(str.getBytes(StandardCharsets.UTF_8));
        gzip.close();
        return outputStream.toByteArray();
    }

    /**
     * Returns a {@code Collector} accepting elements of type {@code T} that
     * counts the number of input elements.  If no elements are present, the
     * result is 0.
     *
     * @param <T> the type of the input elements
     * @return a {@code Collector} that counts the input elements
     */
    public static <T> Collector<T, ?, Integer> countingInt() {
        return Collectors.reducing(0, e -> 1, Integer::sum);
    }

    /**
     * Adds a custom chart.
     *
     * @param chart The chart to add
     */
    public void addCustomChart(CustomChart chart) {
        if (chart == null) {
            throw new IllegalArgumentException("Chart cannot be null!");
        }
        charts.add(chart);
    }

    /**
     * Starts the Scheduler which submits our data every 30 minutes.
     */
    private void startSubmitting() {
        final Runnable submitTask = () -> {
            if (Server.getInstance().getState() != ServerState.RUNNING) {
                return;
            }
            submitData();
        };

        // Many servers tend to restart at a fixed time at xx:00 which causes an uneven distribution of requests on the
        // bStats backend. To circumvent this problem, we introduce some randomness into the initial and second delay.
        // WARNING: You must not modify any part of this Metrics class, including the submit delay or frequency!
        // WARNING: Modifying this code will get your plugin banned on bStats. Just don't do it!
        long initialDelay = (long) (1000 * 60 * (3 + Math.random() * 3));
        long secondDelay = (long) (1000 * 60 * (Math.random() * 30));
        scheduler.schedule(submitTask, initialDelay, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(submitTask, initialDelay + secondDelay, 1000 * 60 * 30, TimeUnit.MILLISECONDS);
    }

    /**
     * Gets the plugin specific data.
     *
     * @return The plugin specific data
     */
    private JsonObject getPluginData() {
        JsonObject data = new JsonObject();

        data.addProperty("pluginName", name); // Append the name of the server software
        JsonArray customCharts = new JsonArray();
        for (CustomChart customChart : charts) {
            // Add the data of the custom charts
            JsonObject chart = customChart.getRequestJsonObject();
            if (chart == null) { // If the chart is null, we skip it
                continue;
            }
            customCharts.add(chart);
        }
        data.add("customCharts", customCharts);

        return data;
    }

    /**
     * Gets the server specific data.
     *
     * @return The server specific data
     */
    private JsonObject getServerData() {
        // OS specific data
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        String osVersion = System.getProperty("os.version");
        int coreCount = Runtime.getRuntime().availableProcessors();

        JsonObject data = new JsonObject();

        data.addProperty("serverUUID", serverUUID);

        data.addProperty("osName", osName);
        data.addProperty("osArch", osArch);
        data.addProperty("osVersion", osVersion);
        data.addProperty("coreCount", coreCount);

        return data;
    }

    /**
     * Collects the data and sends it afterward.
     */
    private void submitData() {
        final JsonObject data = getServerData();

        JsonArray pluginData = new JsonArray();
        pluginData.add(getPluginData());
        data.add("plugins", pluginData);

        try {
            // We are still in the Thread of the timer, so nothing get blocked :)
            sendData(data);
        } catch (Exception e) {
            // Something went wrong! :(
            if (logFailedRequests) {
                log.warn("Could not submit stats of {}", name, e);
            }
        }
    }

    /**
     * Represents a custom chart.
     */
    public static abstract class CustomChart {

        // The id of the chart
        final String chartId;

        /**
         * Class constructor.
         *
         * @param chartId The id of the chart
         */
        CustomChart(String chartId) {
            if (chartId == null || chartId.isEmpty()) {
                throw new IllegalArgumentException("ChartId cannot be null or empty!");
            }
            this.chartId = chartId;
        }

        private JsonObject getRequestJsonObject() {
            JsonObject chart = new JsonObject();
            chart.addProperty("chartId", chartId);
            try {
                JsonObject data = getChartData();
                if (data == null) {
                    // If the data is null we don't send the chart.
                    return null;
                }
                chart.add("data", data);
            } catch (Throwable t) {
                if (logFailedRequests) {
                    log.warn("Failed to get data for custom chart with id {}", chartId, t);
                }
                return null;
            }
            return chart;
        }

        protected abstract JsonObject getChartData() throws Exception;
    }

    /**
     * Represents a custom simple pie.
     */
    public static class SimplePie extends CustomChart {

        private final Callable<String> callable;

        /**
         * Class constructor.
         *
         * @param chartId  The id of the chart
         * @param callable The callable which is used to request the chart data
         */
        public SimplePie(String chartId, Callable<String> callable) {
            super(chartId);
            this.callable = callable;
        }

        @Override
        protected JsonObject getChartData() throws Exception {
            JsonObject data = new JsonObject();
            String value = callable.call();
            if (value == null || value.isEmpty()) {
                // Null = skip the chart
                return null;
            }
            data.addProperty("value", value);
            return data;
        }
    }

    /**
     * Represents a custom advanced pie.
     */
    public static class AdvancedPie extends CustomChart {

        private final Callable<Map<String, Integer>> callable;

        /**
         * Class constructor.
         *
         * @param chartId  The id of the chart
         * @param callable The callable which is used to request the chart data
         */
        public AdvancedPie(String chartId, Callable<Map<String, Integer>> callable) {
            super(chartId);
            this.callable = callable;
        }

        @SuppressWarnings("DuplicatedCode")
        @Override
        protected JsonObject getChartData() throws Exception {
            JsonObject data = new JsonObject();
            JsonObject values = new JsonObject();
            Map<String, Integer> map = callable.call();
            if (map == null || map.isEmpty()) {
                // Null = skip the chart
                return null;
            }
            boolean allSkipped = true;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 0) {
                    continue; // Skip this invalid
                }
                allSkipped = false;
                values.addProperty(entry.getKey(), entry.getValue());
            }
            if (allSkipped) {
                // Null = skip the chart
                return null;
            }
            data.add("values", values);
            return data;
        }
    }

    /**
     * Represents a custom drilldown pie.
     */
    public static class DrilldownPie extends CustomChart {

        private final Callable<Map<String, Map<String, Integer>>> callable;

        /**
         * Class constructor.
         *
         * @param chartId  The id of the chart
         * @param callable The callable which is used to request the chart data
         */
        public DrilldownPie(String chartId, Callable<Map<String, Map<String, Integer>>> callable) {
            super(chartId);
            this.callable = callable;
        }

        @Override
        public JsonObject getChartData() throws Exception {
            JsonObject data = new JsonObject();
            JsonObject values = new JsonObject();
            Map<String, Map<String, Integer>> map = callable.call();
            if (map == null || map.isEmpty()) {
                // Null = skip the chart
                return null;
            }
            boolean reallyAllSkipped = true;
            for (Map.Entry<String, Map<String, Integer>> entryValues : map.entrySet()) {
                JsonObject value = new JsonObject();
                boolean allSkipped = true;
                for (Map.Entry<String, Integer> valueEntry : map.get(entryValues.getKey()).entrySet()) {
                    value.addProperty(valueEntry.getKey(), valueEntry.getValue());
                    allSkipped = false;
                }
                if (!allSkipped) {
                    reallyAllSkipped = false;
                    values.add(entryValues.getKey(), value);
                }
            }
            if (reallyAllSkipped) {
                // Null = skip the chart
                return null;
            }
            data.add("values", values);
            return data;
        }
    }

    /**
     * Represents a custom single line chart.
     */
    public static class SingleLineChart extends CustomChart {

        private final Callable<Integer> callable;

        /**
         * Class constructor.
         *
         * @param chartId  The id of the chart
         * @param callable The callable which is used to request the chart data
         */
        public SingleLineChart(String chartId, Callable<Integer> callable) {
            super(chartId);
            this.callable = callable;
        }

        @Override
        protected JsonObject getChartData() throws Exception {
            JsonObject data = new JsonObject();
            int value = callable.call();
            if (value == 0) {
                // Null = skip the chart
                return null;
            }
            data.addProperty("value", value);
            return data;
        }

    }

    /**
     * Represents a custom multi line chart.
     */
    public static class MultiLineChart extends CustomChart {

        private final Callable<Map<String, Integer>> callable;

        /**
         * Class constructor.
         *
         * @param chartId  The id of the chart
         * @param callable The callable which is used to request the chart data
         */
        public MultiLineChart(String chartId, Callable<Map<String, Integer>> callable) {
            super(chartId);
            this.callable = callable;
        }

        @SuppressWarnings("DuplicatedCode")
        @Override
        protected JsonObject getChartData() throws Exception {
            JsonObject data = new JsonObject();
            JsonObject values = new JsonObject();
            Map<String, Integer> map = callable.call();
            if (map == null || map.isEmpty()) {
                // Null = skip the chart
                return null;
            }
            boolean allSkipped = true;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 0) {
                    continue; // Skip this invalid
                }
                allSkipped = false;
                values.addProperty(entry.getKey(), entry.getValue());
            }
            if (allSkipped) {
                // Null = skip the chart
                return null;
            }
            data.add("values", values);
            return data;
        }

    }

    /**
     * Represents a custom simple bar chart.
     */
    public static class SimpleBarChart extends CustomChart {

        private final Callable<Map<String, Integer>> callable;

        /**
         * Class constructor.
         *
         * @param chartId  The id of the chart
         * @param callable The callable which is used to request the chart data
         */
        public SimpleBarChart(String chartId, Callable<Map<String, Integer>> callable) {
            super(chartId);
            this.callable = callable;
        }

        @Override
        protected JsonObject getChartData() throws Exception {
            JsonObject data = new JsonObject();
            JsonObject values = new JsonObject();
            Map<String, Integer> map = callable.call();
            if (map == null || map.isEmpty()) {
                // Null = skip the chart
                return null;
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                JsonArray categoryValues = new JsonArray();
                categoryValues.add(entry.getValue());
                values.add(entry.getKey(), categoryValues);
            }
            data.add("values", values);
            return data;
        }

    }

    /**
     * Represents a custom advanced bar chart.
     */
    public static class AdvancedBarChart extends CustomChart {

        private final Callable<Map<String, int[]>> callable;

        /**
         * Class constructor.
         *
         * @param chartId  The id of the chart
         * @param callable The callable which is used to request the chart data
         */
        public AdvancedBarChart(String chartId, Callable<Map<String, int[]>> callable) {
            super(chartId);
            this.callable = callable;
        }

        @Override
        protected JsonObject getChartData() throws Exception {
            JsonObject data = new JsonObject();
            JsonObject values = new JsonObject();
            Map<String, int[]> map = callable.call();
            if (map == null || map.isEmpty()) {
                // Null = skip the chart
                return null;
            }
            boolean allSkipped = true;
            for (Map.Entry<String, int[]> entry : map.entrySet()) {
                if (entry.getValue().length == 0) {
                    continue; // Skip this invalid
                }
                allSkipped = false;
                JsonArray categoryValues = new JsonArray();
                for (int categoryValue : entry.getValue()) {
                    categoryValues.add(categoryValue);
                }
                values.add(entry.getKey(), categoryValues);
            }
            if (allSkipped) {
                // Null = skip the chart
                return null;
            }
            data.add("values", values);
            return data;
        }

    }

    public static class AllayMetrics {
        public static void startMetrics() {
            var server = Server.getInstance();
            var settings = AllayServer.getSettings().bStatsSettings();
            // Only start Metrics if it's enabled in the config
            if (!settings.enable()) {
                return;
            }

            Metrics metrics = new Metrics("Allay", settings.serverUUID(), settings.logFailedRequests());

            metrics.addCustomChart(new Metrics.SingleLineChart("players", server.getPlayerManager()::getPlayerCount));
            metrics.addCustomChart(new Metrics.SimplePie("minecraft_version", () -> ProtocolInfo.getLatestCodec().getMinecraftVersion()));
            metrics.addCustomChart(new Metrics.SimplePie("allay_api_version", GitProperties::getBuildApiVersion));
            metrics.addCustomChart(new Metrics.SimplePie("allay_server_version", GitProperties::getBuildVersion));
            metrics.addCustomChart(new Metrics.SimplePie("max_memory", () -> String.format("%.2f", Runtime.getRuntime().maxMemory() / (1024d * 1024d * 1024d)) + "G"));
            metrics.addCustomChart(new Metrics.SimplePie("xbox_auth", () -> AllayServer.getSettings().networkSettings().xboxAuth() ? "Required" : "Not required"));

            metrics.addCustomChart(new Metrics.AdvancedPie("player_platform", () -> server.getPlayerManager().getPlayers().values().stream()
                    .map(EntityPlayer::getLoginData)
                    .map(LoginData::getDeviceInfo)
                    .map(LoginData.DeviceInfo::device)
                    .collect(groupingBy(LoginData.Device::getName, countingInt()))));

            metrics.addCustomChart(new Metrics.AdvancedPie("player_game_version", () -> server.getPlayerManager().getPlayers().values().stream()
                    .map(EntityPlayer::getLoginData)
                    .collect(groupingBy(LoginData::getGameVersion, countingInt()))));

            metrics.addCustomChart(new Metrics.DrilldownPie("java_version", new JavaVersionRetriever()));
        }
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
