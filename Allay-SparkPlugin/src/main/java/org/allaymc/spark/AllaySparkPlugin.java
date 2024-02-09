package org.allaymc.spark;

import lombok.extern.slf4j.Slf4j;
import me.lucko.spark.common.SparkPlatform;
import me.lucko.spark.common.SparkPlugin;
import me.lucko.spark.common.monitor.ping.PlayerPingProvider;
import me.lucko.spark.common.platform.PlatformInfo;
import me.lucko.spark.common.sampler.source.ClassSourceLookup;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.server.Server;

import java.nio.file.Path;
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

        Server.getInstance().getCommandRegistry().register(new AllaySparkCommand(this.platform));
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
        var server = Server.getInstance();
        return Stream.concat(
                server.getOnlinePlayers().values().stream().map(AllayCommandSender::new),
                Stream.of(new AllayCommandSender(server))
        );
    }

    @Override
    public void executeAsync(Runnable task) {
        Server.getInstance().getScheduler().scheduleRepeating(() -> {
            task.run();
            return false;
        }, 1, true);
    }

    @Override
    public void log(Level level, String msg) {
        log.atLevel(org.slf4j.event.Level.valueOf(level.toString())).log(msg);
    }

    @Override
    public ClassSourceLookup createClassSourceLookup() {
        return new AllayClassSourceLookup();
    }

    @Override
    public PlayerPingProvider createPlayerPingProvider() {
        return new AllayPlayerPingProvider(Server.getInstance());
    }

    @Override
    public PlatformInfo getPlatformInfo() {
        return new AllayPlatformInfo();
    }
}
