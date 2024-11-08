package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.server.registry.AllayCommandRegistry;

public class HelpCommand extends SimpleCommand {

    public HelpCommand() {
        super("help", TrKeys.M_COMMANDS_HELP_DESCRIPTION);
    }

    @Override
    public boolean isServerSideOnly() {
        return true;
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .intNum("page",1).optional()
                .exec((context, sender) -> {
                    var num =  Registries.COMMANDS.getContent().size();
                    var pages = num / 5;
                    var page = (int) context.getResult(0);
                    if(page < 0 || page > pages) {
                        context.addSyntaxError(0);
                        context.addError("Invalid page number. The pag number must be 0 or between 1 and " + pages);
                        return context.fail();
                    }
                    if(page == 0){
                        sender.sendText("Total "+num+" commands");
                        sender.sendText("");
                        for(var command : Registries.COMMANDS.getContent().values()) {
                            printCommandHelp(sender, command);
                            sender.sendText("");
                        }
                    } else {
                        sender.sendTr(TrKeys.M_COMMANDS_HELP_HEADER,page,pages);
                        for(var command : Registries.COMMANDS.getContent().values().stream().toList().subList((page-1)*5, page*5)) {
                            printCommandHelp(sender, command);
                            sender.sendText("");
                        }

                    }
                    return context.success();
                }, SenderType.SERVER)
                .root()
                .str("name")
                .exec((context, sender) -> {
                    var command = ((AllayCommandRegistry) Registries.COMMANDS).findCommand(context.getResult(0));
                    if (command == null) {
                        context.addSyntaxError(0);
                        context.addError("no such command");
                        return context.fail();
                    }
                    printCommandHelp(sender, command);
                    return context.success();
                }, SenderType.SERVER);
    }

    private static void printCommandHelp(Server sender, Command command) {
        sender.sendTr(command.getAliases().isEmpty() ? command.getName() + ":" : TrKeys.M_COMMANDS_HELP_COMMAND_ALIASES, command.getName(), String.join(" ", command.getAliases()));
        sender.sendTr(command.getDescription());
        sender.sendTr(TrKeys.M_COMMANDS_GENERIC_USAGE_NOPARAM);
        for(var usage: command.getCommandFormatTips()){
            sender.sendText(usage);
        }
    }
}
