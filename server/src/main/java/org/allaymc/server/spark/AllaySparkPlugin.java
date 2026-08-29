package org.allaymc.server.spark;

import me.lucko.spark.common.SparkPlatform;
import me.lucko.spark.common.SparkPlugin;
import me.lucko.spark.common.command.sender.CommandSender;
import me.lucko.spark.common.monitor.ping.PlayerPingProvider;
import me.lucko.spark.common.platform.PlatformInfo;
import me.lucko.spark.common.platform.world.WorldInfoProvider;
import me.lucko.spark.common.sampler.source.ClassSourceLookup;
import me.lucko.spark.common.tick.TickHook;
import org.allaymc.api.server.Server;
import org.allaymc.server.AllayServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.logging.Level;
import java.util.stream.Stream;

public final class AllaySparkPlugin implements SparkPlugin {

    private static final AllaySparkPlugin INSTANCE = new AllaySparkPlugin();
    private static final Logger LOGGER = LoggerFactory.getLogger(AllaySparkPlugin.class);

    private SparkPlatform platform;

    private AllaySparkPlugin() {
    }

    public static AllaySparkPlugin getInstance() {
        return INSTANCE;
    }

    public void enable() {
        if (platform != null) {
            return;
        }
        platform = new SparkPlatform(this);
        platform.enable();
    }

    public void disable() {
        if (platform == null) {
            return;
        }
        platform.disable();
        platform = null;
    }

    public SparkPlatform getPlatform() {
        return platform;
    }

    @Override
    public String getVersion() {
        return "1.10.172";
    }

    @Override
    public Path getPluginDirectory() {
        return Path.of("spark");
    }

    @Override
    public String getCommandName() {
        return "spark";
    }

    @Override
    public Stream<? extends CommandSender> getCommandSenders() {
        Stream.Builder<CommandSender> senders = Stream.builder();
        senders.add(new AllayCommandSender(AllayServer.getInstance()));
        Server.getInstance().getPlayerManager().forEachPlayer(player -> {
            var entity = player.getControlledEntity();
            if (entity != null) {
                senders.add(new AllayCommandSender(entity));
            }
        });
        return senders.build();
    }

    @Override
    public void executeAsync(Runnable task) {
        Server.getInstance().getVirtualThreadPool().execute(task);
    }

    @Override
    public void log(Level level, String msg) {
        switch (level.getName()) {
            case "SEVERE" -> LOGGER.error(msg);
            case "WARNING" -> LOGGER.warn(msg);
            case "INFO" -> LOGGER.info(msg);
            default -> LOGGER.debug(msg);
        }
    }

    @Override
    public void log(Level level, String msg, Throwable throwable) {
        switch (level.getName()) {
            case "SEVERE" -> LOGGER.error(msg, throwable);
            case "WARNING" -> LOGGER.warn(msg, throwable);
            case "INFO" -> LOGGER.info(msg, throwable);
            default -> LOGGER.debug(msg, throwable);
        }
    }

    @Override
    public PlatformInfo getPlatformInfo() {
        return new AllayPlatformInfo();
    }

    @Override
    public TickHook createTickHook() {
        return new AllayTickHook();
    }

    @Override
    public PlayerPingProvider createPlayerPingProvider() {
        return new AllayPlayerPingProvider();
    }

    @Override
    public ClassSourceLookup createClassSourceLookup() {
        return new AllayClassSourceLookup();
    }

    @Override
    public WorldInfoProvider createWorldInfoProvider() {
        return new AllayWorldInfoProvider();
    }
}