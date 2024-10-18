package org.allaymc.server.command.defaults;

import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.command.ProxyCommandSender;
import org.joml.Vector3fc;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class ExecuteCommand extends SimpleCommand {

    public ExecuteCommand() {
        super("execute", TrKeys.M_COMMANDS_EXECUTE_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("as")
                .target("origin")
                .remain("subcommand")
                .exec(context -> {
                    List<Entity> targets = context.getResult(1);
                    List<String> remain = context.getResult(2);
                    if (targets.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }
                    var successCount = 0;
                    for (var target : targets) {
                        var result = tree.parse(target, remain.toArray(String[]::new));
                        context.addOutputs(result.context().getOutputs());
                        successCount += result.status();
                    }
                    return new CommandResult(successCount, context);
                })
                .root()
                .key("at")
                .pos("pos")
                .remain("subcommand")
                .exec(context -> {
                    Vector3fc pos = context.getResult(1);
                    List<String> remain = context.getResult(2);
                    var sender = context.getSender();
                    var proxySender = new ProxyCommandSender(sender);
                    var loc = sender.getCmdExecuteLocation();
                    var newLoc = new Location3f(pos.x(), pos.y(), pos.z(), loc.pitch(), loc.yaw(), loc.headYaw(), loc.dimension());
                    proxySender.setCmdExecuteLocation(newLoc);
                    return tree.parse(proxySender, remain.toArray(String[]::new));
                })
                .root()
                .key("run")
                .cmd("command")
                .exec(context -> {
                    String cmd = context.getResult(1);
                    var result = Registries.COMMANDS.execute(context.getSender(), cmd);
                    if (!result.isSuccess()) {
                        context.addError("%" + TrKeys.M_COMMANDS_EXECUTE_FAILED, cmd, context.getSender().getCommandSenderName());
                        context.addOutputs(result.context().getOutputs());
                    }
                    return new CommandResult(result.status(), context);
                });
    }
}
