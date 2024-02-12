package org.allaymc.spark;

import me.lucko.spark.common.SparkPlatform;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandTree;

import java.util.function.Function;

/**
 * Allay Project 08/02/2024
 *
 * @author IWareQ
 */
public class AllaySparkCommand extends SimpleCommand {

    private final SparkPlatform platform;

    public AllaySparkCommand(SparkPlatform platform) {
        super("spark", "spark");
        this.permissions.add("spark");
        this.platform = platform;
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("help").exec(executor()).root()
                .key("profiler")
                .key("info").exec(executor()).up()
                .key("open").exec(executor()).up()
                .key("start").enums("startFlags",
                        "--timeout",
                        "--thread",
                        "--only-ticks-over",
                        "--interval",
                        "--alloc"
                ).exec(executor()).up(2)
                .key("stop").exec(executor()).up()
                .key("cancel").exec(executor()).root()
                .key("tps").exec(executor()).root()
                .key("ping").enums("pingFlags", "--player").exec(executor()).root()
                .key("healthreport").enums("healthreportFlags", "--memory", "--network").exec(executor()).root()
                .key("tickmonitor").enums("tickmonitorFlags",
                        "--threshold",
                        "--threshold-tick",
                        "--without-gc"
                ).exec(executor()).root()
                .key("gc").exec(executor()).root()
                .key("gcmonitor").exec(executor()).root()
                .key("heapsummary").enums("heapsummaryFlags", "--save-to-file").exec(executor()).root()
                .key("heapdump").enums("heapdumpFlags", "--compress").exec(executor()).root()
                .key("activity").enums("activityFlags", "--page").exec(executor());
    }

    protected Function<CommandContext, CommandResult> executor() {
        return context -> {
            this.platform.executeCommand(new AllayCommandSender(context.getSender()), context.getArgs());
            return context.success();
        };
    }
}
