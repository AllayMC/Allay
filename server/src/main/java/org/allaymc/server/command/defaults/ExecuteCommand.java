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
import org.allaymc.api.utils.identifier.IdentifierUtils;
import org.allaymc.server.command.ProxyCommandSender;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.List;
import java.util.function.Function;

/**
 * {@code ExecuteCommand} composes command execution contexts by changing the
 * apparent sender, location, or dimension before parsing another execute branch.
 * The {@code as} branch uses a target's identity with the original location;
 * {@code at} keeps the original sender and uses a target's location. These
 * choices determine both permission checks and relative-coordinate resolution.
 *
 * <p>
 * Each nested parse has its own context. The helper methods append nested
 * outputs to the enclosing context and preserve or sum numeric statuses, so
 * selected targets contribute independently. Earlier subcommands retain their
 * side effects when a later subcommand fails; the command performs no rollback.
 * The {@code run} branch delegates final dispatch to the command registry.
 *
 * @author daoge_cmd
 */
public class ExecuteCommand extends Command {

    /**
     * Creates the {@code /execute} command with its built-in authorization permission.
     */
    public ExecuteCommand() {
        super("execute", TrKeys.MC_COMMANDS_EXECUTE_DESCRIPTION, Permissions.COMMAND_EXECUTE);
    }

    /**
     * Installs context-changing branches and final command dispatch.
     * {@code as} and {@code at} require matching targets and evaluate the
     * remaining arguments once per target. {@code align}, {@code positioned},
     * and {@code in} replace selected location properties while retaining the
     * sender's identity; unknown worlds, dimension types, and disabled dimensions
     * fail before the remaining branch executes.
     *
     * <p>
     * Nested parsing accumulates output in the outer context. Final
     * {@code run} dispatch reuses the active sender and copies failure detail
     * through the registry result's context. Early registry failures with a
     * {@code null} context currently throw while copying that detail instead
     * of returning the original failure status.
     */
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

                    // Flooring aligns selected axes to their containing block coordinates.
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
                .str("dimension")
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

                    var identifier = IdentifierUtils.tryParse(dimName);
                    var dimensionType = identifier == null ? null : Registries.DIMENSIONS.getByK2(identifier);
                    if (dimensionType == null) {
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_DIM_UNKNOWN, dimName);
                        return context.fail();
                    }

                    var dim = world.getDimension(dimensionType);
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

    /**
     * Parses one remaining execute branch through the supplied proxy sender.
     * Nested outputs are appended to {@code context}, and the returned result
     * retains that outer context with the nested parse's numeric status.
     */
    protected CommandResult runSubcommand(CommandTree tree, CommandContext context, ProxyCommandSender proxy, List<String> remain) {
        var result = tree.parse(proxy, remain.toArray(String[]::new));
        context.addOutputs(result.context().getOutputs());
        return new CommandResult(result.status(), context);
    }

    /**
     * Parses the same execute arguments separately for each supplied target.
     * {@code proxyFactory} supplies each parse's sender, outputs accumulate in
     * target order, and numeric statuses are summed without stopping after a
     * failed target or undoing earlier side effects.
     *
     * <p>
     * The sum determines overall success through
     * {@code CommandResult.isSuccess}. A status of {@code 1} combined with a
     * failed status of {@code 0} still succeeds overall; negative statuses
     * subtract from successful statuses and can make the total unsuccessful.
     * Feedback from every completed nested parse remains attached regardless
     * of that final sum.
     */
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
