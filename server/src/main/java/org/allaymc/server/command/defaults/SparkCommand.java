package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.server.spark.AllayCommandSender;
import org.allaymc.server.spark.AllaySparkPlugin;

import java.util.List;

public class SparkCommand extends Command {

    public SparkCommand() {
        super("spark", TrKeys.ALLAY_COMMAND_SPARK_DESCRIPTION, Permissions.COMMAND_SPARK);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .remain("args").optional()
                .exec(context -> {
                    if (AllaySparkPlugin.getInstance().getPlatform() == null) {
                        AllaySparkPlugin.getInstance().enable();
                    }
                    List<String> args = context.getResult(0);
                    var sparkSender = new AllayCommandSender(context.getSender());
                    AllaySparkPlugin.getInstance().getPlatform().executeCommand(sparkSender, args.toArray(new String[0]));
                    return context.success();
                });
    }
}