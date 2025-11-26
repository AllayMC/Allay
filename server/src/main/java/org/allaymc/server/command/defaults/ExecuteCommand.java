package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.server.command.ProxyCommandSender;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.List;
import java.util.function.Function;

/**
 * @author daoge_cmd
 */
public class ExecuteCommand extends Command {

    public ExecuteCommand() {
        super("execute", TrKeys.MC_COMMANDS_EXECUTE_DESCRIPTION, Permissions.COMMAND_EXECUTE);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("as")
                .target("origin")
                .remain("subcommand")
                .exec(context -> {
                    List<Entity> targets = context.getResult(1);
                    if (targets.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    List<String> remain = context.getResult(2);
                    return runForEachTarget(tree, context, targets, remain.toArray(String[]::new), target -> {
                        var proxySender = new ProxyCommandSender(target);
                        proxySender.setCmdExecuteLocation(context.getSender().getCommandExecuteLocation());
                        return proxySender;
                    });
                })
                .root()
                .key("align")
                .str("axes")
                .remain("subcommand")
                .exec(context -> {
                    String axes = context.getResult(1);
                    List<String> remain = context.getResult(2);

                    Location3d alignedLoc = new Location3d(context.getSender().getCommandExecuteLocation());
                    Vector3d floored = alignedLoc.floor(new Vector3d());

                    if (axes.contains("x")) alignedLoc.x = floored.x;
                    if (axes.contains("y")) alignedLoc.y = floored.y;
                    if (axes.contains("z")) alignedLoc.z = floored.z;

                    var proxySender = new ProxyCommandSender(context.getSender());
                    proxySender.setCmdExecuteLocation(alignedLoc);
                    return runSubcommand(tree, context, proxySender, remain);
                })
                .root()
                .key("at")
                .target("origin")
                .remain("subcommand")
                .exec(context -> {
                    List<Entity> targets = context.getResult(1);
                    if (targets.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    List<String> remain = context.getResult(2);
                    return runForEachTarget(tree, context, targets, remain.toArray(String[]::new), target -> {
                        var proxySender = new ProxyCommandSender(context.getSender());
                        proxySender.setCmdExecuteLocation(target.getLocation());
                        return proxySender;
                    });
                })
                .root()
                .key("positioned")
                .pos("pos")
                .remain("subcommand")
                .exec(context -> {
                    Vector3dc pos = context.getResult(1);
                    List<String> remain = context.getResult(2);

                    var proxy = new ProxyCommandSender(context.getSender());

                    var newLoc = new Location3d(context.getSender().getCommandExecuteLocation());
                    newLoc.set(pos);
                    proxy.setCmdExecuteLocation(newLoc);

                    return runSubcommand(tree, context, proxy, remain);
                })
                .root()
                .key("in")
                .str("world")
                .enums("dimension", "overworld", "nether", "the_end")
                .remain("subcommand")
                .exec(context -> {
                    String worldName = context.getResult(1);
                    String dimName = context.getResult(2);
                    List<String> remain = context.getResult(3);

                    var world = Server.getInstance().getWorldPool().getWorld(worldName);
                    if (world == null) {
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_UNKNOWN, worldName);
                        return context.fail();
                    }

                    var dimInfo = DimensionInfo.fromName(dimName);
                    if (dimInfo == null) {
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_DIM_UNKNOWN, dimName);
                        return context.fail();
                    }

                    var dim = world.getDimension(dimInfo.dimensionId());
                    if (dim == null) {
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_DIM_DISABLED, dimName);
                        return context.fail();
                    }

                    var sender = context.getSender();
                    var proxySender = new ProxyCommandSender(sender);

                    var newLoc = new Location3d(sender.getCommandExecuteLocation());
                    newLoc.setDimension(dim);
                    proxySender.setCmdExecuteLocation(newLoc);

                    return runSubcommand(tree, context, proxySender, remain);
                })
                .root()
                .key("run")
                .cmd("command")
                .exec(context -> {
                    String cmd = context.getResult(1);
                    var result = Registries.COMMANDS.execute(context.getSender(), cmd);
                    if (!result.isSuccess()) {
                        context.addError("%" + TrKeys.MC_COMMANDS_EXECUTE_FAILED, cmd, context.getSender().getCommandSenderName());
                        context.addOutputs(result.context().getOutputs());
                    }
                    return new CommandResult(result.status(), context);
                });
    }

    protected CommandResult runSubcommand(CommandTree tree, CommandContext context, ProxyCommandSender proxy, List<String> remain) {
        var result = tree.parse(proxy, remain.toArray(String[]::new));
        context.addOutputs(result.context().getOutputs());
        return new CommandResult(result.status(), context);
    }

    private CommandResult runForEachTarget(CommandTree tree, CommandContext context, List<Entity> targets, String[] args, Function<Entity, ProxyCommandSender> proxyFactory) {
        int successCount = 0;
        for (var target : targets) {
            var proxy = proxyFactory.apply(target);
            var result = tree.parse(proxy, args);
            context.addOutputs(result.context().getOutputs());
            successCount += result.status();
        }

        return new CommandResult(successCount, context);
    }
}
