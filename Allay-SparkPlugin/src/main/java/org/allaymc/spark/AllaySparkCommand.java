package org.allaymc.spark;

import me.lucko.spark.common.SparkPlatform;
import org.allaymc.api.command.BaseCommand;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;

import java.util.List;

/**
 * Allay Project 08/02/2024
 *
 * @author IWareQ
 */
public class AllaySparkCommand extends BaseCommand {

    private final SparkPlatform platform;

    public AllaySparkCommand(SparkPlatform platform) {
        super("spark", "spark", List.of(Command.COMMAND_PERM_PREFIX + "spark"));
        this.platform = platform;
    }

    @Override
    public CommandResult execute(CommandSender sender, String[] args) {
        this.platform.executeCommand(new AllayCommandSender(sender), args);
        return CommandResult.success(null);
    }
}
