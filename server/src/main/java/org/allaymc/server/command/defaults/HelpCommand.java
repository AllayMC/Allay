package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.server.registry.AllayCommandRegistry;

/**
 * @author harryxi
 */
public class HelpCommand extends SimpleCommand {
    private static final int COMMANDS_PER_PAGE = 7;

    public HelpCommand() {
        super("help", TrKeys.M_COMMANDS_HELP_DESCRIPTION);
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

                    sender.sendTr(TrKeys.M_COMMANDS_HELP_HEADER, page, pages);
                    for (var command : commands.values().stream().toList().subList((page - 1) * COMMANDS_PER_PAGE, page * COMMANDS_PER_PAGE)) {
                        printCommandHelp(sender, command);
                        sender.sendText("");
                    }
                    sender.sendTr(TrKeys.M_COMMANDS_HELP_FOOTER);
                    return context.success();
                }, SenderType.SERVER)
                .root()
                .str("command")
                .exec((context, sender) -> {
                    if (Registries.COMMANDS instanceof AllayCommandRegistry commandRegistry) {
                        var command = commandRegistry.findCommand(context.getResult(0));
                        if (command == null) {
                            context.addSyntaxError(0);
                            return context.fail();
                        }

                        printCommandHelp(sender, command);
                        return context.success();
                    }

                    return context.fail();
                }, SenderType.SERVER);
    }

    private void printCommandHelp(Server sender, Command command) {
        sender.sendTr(command.getAliases().isEmpty() ? command.getName() + ":" : TrKeys.M_COMMANDS_HELP_COMMAND_ALIASES, command.getName(), String.join(" ", command.getAliases()));
        sender.sendTr(command.getDescription());
        sender.sendTr(TrKeys.M_COMMANDS_GENERIC_USAGE_NOPARAM);
        for (var usage : command.getCommandFormatTips()) {
            sender.sendText(usage);
        }
    }

    @Override
    public boolean isServerSideOnly() {
        return true;
    }
}
