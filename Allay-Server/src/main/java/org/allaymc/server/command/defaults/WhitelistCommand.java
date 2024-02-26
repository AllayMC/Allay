package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * Allay Project 2024/2/26
 *
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
                    String operation = context.getFirstResult();
                    String nameOrUUID = context.getSecondResult();
                    switch (operation) {
                        case "add" -> {
                            if (Server.getInstance().addToWhitelist(nameOrUUID)) {
                                context.addOutput(TrKeys.M_COMMANDS_ALLOWLIST_ADD_SUCCESS, nameOrUUID);
                                return context.success();
                            } else {
                                context.addError("%" + TrKeys.M_COMMANDS_ALLOWLIST_ADD_FAILED, nameOrUUID);
                                return context.fail();
                            }
                        }
                        case "remove" -> {
                            if (Server.getInstance().removeFromWhitelist(nameOrUUID)) {
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
                    var whitelist = Server.getInstance().getWhitelistedPlayers();
                    var onlineCount = 0;
                    for (var player : Server.getInstance().getOnlinePlayers().values()) {
                        if (whitelist.contains(player.getUUID()) || whitelist.contains(player.getOriginName())) {
                            onlineCount++;
                        }
                    }
                    context.addOutput(TrKeys.M_COMMANDS_ALLOWLIST_LIST, whitelist.size(), onlineCount);
                    context.addOutput(String.join(", ", whitelist));
                    return context.success();
                })
                .root()
                .key("enable")
                .exec(context -> {
                    Server.SETTINGS.genericSettings().isWhitelisted(true);
                    for (var player : Server.getInstance().getOnlinePlayers().values()) {
                        if (!Server.getInstance().isWhitelisted(player.getUUID().toString()) && !Server.getInstance().isWhitelisted(player.getOriginName())) {
                            player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOTALLOWED);
                        }
                    }
                    context.addOutput(TrKeys.M_COMMANDS_ALLOWLIST_ENABLED);
                    return context.success();
                })
                .root()
                .key("disable")
                .exec(context -> {
                    Server.SETTINGS.genericSettings().isWhitelisted(false);
                    context.addOutput(TrKeys.M_COMMANDS_ALLOWLIST_DISABLED);
                    return context.success();
                });
    }
}
