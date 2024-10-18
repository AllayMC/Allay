package org.allaymc.server.command.defaults;

import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.server.command.ProxyCommandSender;
import org.joml.Vector3f;
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
                    List<Entity> origin = context.getResult(1);
                    if (origin.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    List<String> remain = context.getResult(2);

                    var successCount = 0;
                    for (var target : origin) {
                        var proxySender = new ProxyCommandSender(target);
                        proxySender.setCmdExecuteLocation(context.getSender().getCmdExecuteLocation());
                        var result = tree.parse(proxySender, remain.toArray(String[]::new));
                        context.addOutputs(result.context().getOutputs());
                        successCount += result.status();
                    }
                    return new CommandResult(successCount, context);
                })
                .root()
                .key("align")
                .str("axes")
                .remain("subcommand")
                .exec(context -> {
                    String axes = context.getResult(1);
                    List<String> remain = context.getResult(2);

                    var proxySender = new ProxyCommandSender(context.getSender());
                    var newLoc = new Location3f(context.getSender().getCmdExecuteLocation());
                    var newLocFloor = newLoc.floor(new Vector3f());
                    if (axes.contains("x")) {
                        newLoc.x = newLocFloor.x;
                    }
                    if (axes.contains("y")) {
                        newLoc.y = newLocFloor.y;
                    }
                    if (axes.contains("z")) {
                        newLoc.z = newLocFloor.z;
                    }
                    proxySender.setCmdExecuteLocation(newLoc);
                    var result = tree.parse(proxySender, remain.toArray(String[]::new));
                    context.addOutputs(result.context().getOutputs());
                    return new CommandResult(result.status(), context);
                })
                .root()
                .key("at")
                .target("origin")
                .remain("subcommand")
                .exec(context -> {
                    List<Entity> origin = context.getResult(1);
                    if (origin.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    List<String> remain = context.getResult(2);

                    var successCount = 0;
                    for (var target : origin) {
                        var proxySender = new ProxyCommandSender(context.getSender());
                        proxySender.setCmdExecuteLocation(target.getLocation());
                        var result = tree.parse(proxySender, remain.toArray(String[]::new));
                        context.addOutputs(result.context().getOutputs());
                        successCount += result.status();
                    }
                    return new CommandResult(successCount, context);
                })
                .root()
                .key("positioned")
                .pos("pos")
                .remain("subcommand")
                .exec(context -> {
                    Vector3fc pos = context.getResult(1);
                    List<String> remain = context.getResult(2);
                    var sender = context.getSender();
                    var proxySender = new ProxyCommandSender(sender);
                    var loc = sender.getCmdExecuteLocation();
                    var newLoc = new Location3f(loc);
                    newLoc.set(pos);
                    proxySender.setCmdExecuteLocation(newLoc);
                    var result = tree.parse(proxySender, remain.toArray(String[]::new));
                    context.addOutputs(result.context().getOutputs());
                    return new CommandResult(result.status(), context);
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
                        context.addError("%" + TrKeys.A_COMMAND_WORLD_UNKNOWN, worldName);
                        return context.fail();
                    }

                    var dimInfo = DimensionInfo.fromName(dimName);
                    if (dimInfo == null) {
                        context.addError("%" + TrKeys.A_COMMAND_WORLD_UNKNOWNDIM, dimName);
                        return context.fail();
                    }

                    var dim = world.getDimension(dimInfo.dimensionId());
                    var sender = context.getSender();
                    var proxySender = new ProxyCommandSender(sender);
                    var loc = sender.getCmdExecuteLocation();
                    var newLoc = new Location3f(loc);
                    newLoc.setDimension(dim);
                    proxySender.setCmdExecuteLocation(newLoc);
                    var result = tree.parse(proxySender, remain.toArray(String[]::new));
                    context.addOutputs(result.context().getOutputs());
                    return new CommandResult(result.status(), context);
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
