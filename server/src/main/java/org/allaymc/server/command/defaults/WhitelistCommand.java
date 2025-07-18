package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class WhitelistCommand extends SimpleCommand {
    public WhitelistCommand() {
        super("whitelist", TrKeys.M_COMMANDS_ALLOWLIST_DESCRIPTION);
        aliases.add("allowlist");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .enums("operation", "add", "remove")
                .str("nameOrUUID")
                .exec(context -> {
                    String operation = context.getResult(0);
                    String nameOrUUID = context.getResult(1);
                    switch (operation) {
                        case "add" -> {
                            if (Server.getInstance().getPlayerService().addToWhitelist(nameOrUUID)) {
                                context.addOutput(TrKeys.M_COMMANDS_ALLOWLIST_ADD_SUCCESS, nameOrUUID);
                                return context.success();
                            } else {
                                context.addError("%" + TrKeys.M_COMMANDS_ALLOWLIST_ADD_FAILED, nameOrUUID);
                                return context.fail();
                            }
                        }
                        case "remove" -> {
                            if (Server.getInstance().getPlayerService().removeFromWhitelist(nameOrUUID)) {
                                context.addOutput(TrKeys.M_COMMANDS_ALLOWLIST_REMOVE_SUCCESS, nameOrUUID);
                                return context.success();
                            } else {
                                context.addError("%" + TrKeys.M_COMMANDS_ALLOWLIST_REMOVE_FAILED, nameOrUUID);
                                return context.fail();
                            }
                        }
                        default -> {
                            // Won't happen
                            return context.fail();
                        }
                    }
                })
                .root()
                .key("list")
                .exec(context -> {
                    var whitelist = Server.getInstance().getPlayerService().getWhitelistedPlayers();
                    var onlineCount = (int) Server.getInstance().getPlayerService().getPlayers().values().stream()
                            .filter(player -> whitelist.contains(player.getLoginData().getUuid().toString()) ||
                                              whitelist.contains(player.getOriginName()))
                            .count();
                    context.addOutput(TrKeys.M_COMMANDS_ALLOWLIST_LIST, whitelist.size(), onlineCount);
                    context.addOutput(String.join(", ", whitelist));
                    return context.success();
                })
                .root()
                .key("enable")
                .exec(context -> {
                    Server.getInstance().getPlayerService().setWhitelistStatus(true);
                    context.addOutput(TrKeys.M_COMMANDS_ALLOWLIST_ENABLED);
                    return context.success();
                })
                .root()
                .key("disable")
                .exec(context -> {
                    Server.getInstance().getPlayerService().setWhitelistStatus(false);
                    context.addOutput(TrKeys.M_COMMANDS_ALLOWLIST_DISABLED);
                    return context.success();
                });
    }
}
