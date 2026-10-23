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
 * {@code HelpCommand} prints command descriptions and tree-derived usage for
 * server senders. Numeric lookups display complete seven-command pages from
 * registry iteration order, while name lookups resolve one command directly.
 * The command stays server-side because the client handles its own help command.
 *
 * @author harryxi | daoge_cmd
 */
public class HelpCommand extends Command {
    /**
     * {@code COMMANDS_PER_PAGE} controls the size of complete registry slices
     * displayed by numeric help requests.
     */
    private static final int COMMANDS_PER_PAGE = 7;

    /**
     * Creates the authorized {@code /help} command and its {@code /?} alias.
     */
    public HelpCommand() {
        super("help", TrKeys.MC_COMMANDS_HELP_DESCRIPTION, Permissions.COMMAND_HELP);
        aliases.add("?");
    }

    /**
     * Installs server-only page and command-name lookup branches.
     * Page numbers default to {@code 1} and use only complete pages; fewer than
     * seven registered commands produce invalid slice bounds instead of an
     * empty page. Name lookups reject unknown commands before printing usage.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .intNum("page", 1).optional()
                .exec((context, sender) -> {
                    var commands = Registries.COMMANDS.getContent();
                    // The trailing partial page is excluded from this count
                    // and cannot be selected by numeric lookup.
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

    /**
     * Sends the selected command's description, aliases, and generated usage.
     */
    private void printCommandHelp(Server sender, Command command) {
        sender.sendTranslatable(command.getAliases().isEmpty() ? command.getName() + ":" : TrKeys.MC_COMMANDS_HELP_COMMAND_ALIASES, command.getName(), String.join(" ", command.getAliases()));
        sender.sendTranslatable(command.getDescription());
        sender.sendTranslatable(TrKeys.MC_COMMANDS_GENERIC_USAGE_NOPARAM);
        for (var usage : buildCommandTips(command)) {
            sender.sendMessage(usage);
        }
    }

    /**
     * Returns whether help is omitted from client command advertisements.
     * This command returns {@code true} because clients handle their own help
     * requests and the installed executors require a server sender.
     */
    @Override
    public boolean isServerSideOnly() {
        return true;
    }

    /**
     * Builds one usage line for each leaf of the command tree.
     * Every node on each path must be a {@link BaseNode}; optional parameters
     * use brackets, required parameters use angle brackets, and long enum lists
     * display only their first ten alternatives.
     */
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
                            .append(param.getType().paramType().name().toLowerCase(Locale.ENGLISH))
                            .append(param.isOptional() ? "]" : ">");
                } else {
                    var enums = new ArrayList<>(param.getEnumData().values().keySet());
                    if (enums.size() == 1 && !param.isOptional()) {
                        tip.append(" ").append(enums.getFirst());
                    } else {
                        // Empty metadata, such as Item and Block enumerations,
                        // supplies no alternatives. Display parameter and enum
                        // names so the usage still identifies the required input.
                        // Remaining enum alternatives are omitted after the first ten.
                        tip.append(param.isOptional() ? " [" : " <")
                                .append(
                                        enums.isEmpty()
                                                ? param.getName() + ": " + param.getEnumData().name()
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
