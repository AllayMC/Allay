package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.server.command.tree.node.BaseNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author harryxi | daoge_cmd
 */
public class HelpCommand extends Command {
    private static final int COMMANDS_PER_PAGE = 7;

    public HelpCommand() {
        super("help", TrKeys.MC_COMMANDS_HELP_DESCRIPTION, Permissions.COMMAND_HELP);
        aliases.add("?");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .intNum("page", 1).optional()
                .exec((context, sender) -> {
                    var commands = Registries.COMMANDS.getContent();
                    var pages = commands.size() / COMMANDS_PER_PAGE;
                    var page = (int) context.getResult(0);
                    page = Math.min(pages, Math.max(1, page));

                    sender.sendTranslatable(TrKeys.MC_COMMANDS_HELP_HEADER, page, pages);
                    for (var command : commands.values().stream().toList().subList((page - 1) * COMMANDS_PER_PAGE, page * COMMANDS_PER_PAGE)) {
                        printCommandHelp(sender, command);
                        sender.sendMessage("");
                    }
                    sender.sendTranslatable(TrKeys.MC_COMMANDS_HELP_FOOTER);
                    return context.success();
                }, SenderType.SERVER)
                .root()
                .str("command")
                .exec((context, sender) -> {
                    var command = Registries.COMMANDS.findCommand(context.getResult(0));
                    if (command == null) {
                        context.addSyntaxError(0);
                        return context.fail();
                    }

                    printCommandHelp(sender, command);
                    return context.success();
                }, SenderType.SERVER);
    }

    private void printCommandHelp(Server sender, Command command) {
        sender.sendTranslatable(command.getAliases().isEmpty() ? command.getName() + ":" : TrKeys.MC_COMMANDS_HELP_COMMAND_ALIASES, command.getName(), String.join(" ", command.getAliases()));
        sender.sendTranslatable(command.getDescription());
        sender.sendTranslatable(TrKeys.MC_COMMANDS_GENERIC_USAGE_NOPARAM);
        for (var usage : buildCommandTips(command)) {
            sender.sendMessage(usage);
        }
    }

    @Override
    public boolean isServerSideOnly() {
        return true;
    }

    private static List<String> buildCommandTips(Command command) {
        var tips = new ArrayList<String>();
        for (var leaf : command.getCommandTree().getLeaves()) {
            var params = new CommandParamData[leaf.depth()];
            var node = leaf;
            var index = leaf.depth() - 1;
            while (!node.isRoot()) {
                params[index] = ((BaseNode) node).toNetworkData();
                node = node.parent();
                index--;
            }

            var tip = new StringBuilder("- /").append(command.getName());
            for (var param : params) {
                if (param.getEnumData() == null) {
                    tip.append(param.isOptional() ? " [" : " <")
                            .append(param.getName())
                            .append(": ")
                            .append(param.getType().getParamType().name().toLowerCase(Locale.ENGLISH))
                            .append(param.isOptional() ? "]" : ">");
                } else {
                    var enums = new ArrayList<>(param.getEnumData().getValues().keySet());
                    if (enums.size() == 1 && !param.isOptional()) {
                        tip.append(" ").append(enums.getFirst());
                    } else {
                        tip.append(param.isOptional() ? " [" : " <")
                                .append(
                                        enums.isEmpty()
                                                ? param.getName() + ": " + param.getEnumData().getName()
                                                : String.join("|", enums.subList(0, Math.min(enums.size(), 10)))
                                )
                                .append(enums.size() > 10 ? "|..." : "")
                                .append(param.isOptional() ? "]" : ">");
                    }
                }
            }

            tips.add(tip.toString());
        }

        return tips;
    }
}
