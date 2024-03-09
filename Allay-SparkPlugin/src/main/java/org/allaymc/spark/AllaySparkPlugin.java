package org.allaymc.spark;

import lombok.extern.slf4j.Slf4j;
import me.lucko.spark.common.SparkPlatform;
import me.lucko.spark.common.SparkPlugin;
import me.lucko.spark.common.monitor.ping.PlayerPingProvider;
import me.lucko.spark.common.platform.PlatformInfo;
import me.lucko.spark.common.platform.world.WorldInfoProvider;
import me.lucko.spark.common.sampler.source.ClassSourceLookup;
import me.lucko.spark.common.sampler.source.SourceMetadata;
import me.lucko.spark.common.tick.TickHook;
import org.allaymc.api.plugin.Plugin;

import java.nio.file.Path;
import java.util.Collection;
import java.util.logging.Level;
import java.util.stream.Stream;

/**
 * Allay Project 08/02/2024
 *
 * @author IWareQ
 */
@Slf4j
public class AllaySparkPlugin extends Plugin implements SparkPlugin {

    private SparkPlatform platform;

    @Override
    public void onEnable() {
        this.platform = new SparkPlatform(this);
        this.platform.enable();

        this.getCommandRegistry().register(new AllaySparkCommand(this.platform));
    }

    @Override
    public void onDisable() {
        this.platform.disable();
    }

    @Override
    public String getVersion() {
        return this.pluginContainer.descriptor().getVersion();
    }

    @Override
    public Path getPluginDirectory() {
        return this.pluginContainer.dataFolder();
    }

    @Override
    public String getCommandName() {
        return "spark";
    }

    @Override
    public Stream<AllayCommandSender> getCommandSenders() {
        var server = this.getServer();
        return Stream.concat(
                server.getOnlinePlayers().values().stream(),
                Stream.of(server)
        ).map(AllayCommandSender::new);
    }

    @Override
    public void executeAsync(Runnable task) {
        this.getServerScheduler().scheduleRepeating(this, () -> {
            task.run();
            return false;
        }, 1, true);
    }

    @Override
    public void executeSync(Runnable task) {
        this.getServerScheduler().scheduleRepeating(this, () -> {
            task.run();
            return false;
        }, 1);
    }

    // https://stackoverflow.com/questions/20795373/how-to-map-levels-of-java-util-logging-and-slf4j-logger
    @Override
    public void log(Level level, String msg) {
        var slf4jLevel = switch (level.getName()) {
            case "ALL", "FINEST" -> org.slf4j.event.Level.TRACE;
            case "FINER", "FINE" -> org.slf4j.event.Level.DEBUG;
            case "CONFIG", "INFO" -> org.slf4j.event.Level.INFO;
            case "WARNING" -> org.slf4j.event.Level.WARN;
            default -> org.slf4j.event.Level.ERROR;
        };

        log.atLevel(slf4jLevel).log(msg);
    }

    @Override
    public TickHook createTickHook() {
        return new AllayTickHook(this);
    }

    @Override
    public ClassSourceLookup createClassSourceLookup() {
        return new AllayClassSourceLookup(this.getPluginManager());
    }

    @Override
    public Collection<SourceMetadata> getKnownSources() {
        return SourceMetadata.gather(
                this.getPluginManager().getPlugins().values(),
                container -> container.descriptor().getName(),
                container -> container.descriptor().getVersion(),
                container -> String.join(", ", container.descriptor().getAuthors())
        );
    }

    @Override
    public PlayerPingProvider createPlayerPingProvider() {
        return new AllayPlayerPingProvider(this.getServer());
    }

    @Override
    public WorldInfoProvider createWorldInfoProvider() {
        return new AllayWorldInfoProvider();
    }

    @Override
    public PlatformInfo getPlatformInfo() {
        return new AllayPlatformInfo();
    }
}
