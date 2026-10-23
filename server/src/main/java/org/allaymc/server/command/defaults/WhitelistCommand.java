package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * {@code WhitelistCommand} manages whitelist membership, visibility, and
 * enforcement through the player manager. Add and remove operations report the
 * manager's acceptance, while enable and disable feedback reports the requested
 * state even when the manager's whitelist-change event is cancelled.
 *
 * @author daoge_cmd
 */
public class WhitelistCommand extends Command {
    /**
     * Creates the authorized {@code /whitelist} command and its {@code /allowlist} alias.
     */
    public WhitelistCommand() {
        super("whitelist", TrKeys.MC_COMMANDS_ALLOWLIST_DESCRIPTION, Permissions.COMMAND_WHITELIST);
        aliases.add("allowlist");
    }

    /**
     * Installs membership edits, listing, and enforcement toggles.
     * Listing counts online players matched by either UUID or original name.
     * Membership failures become command failures, but enforcement toggles
     * report success without checking whether their change event was accepted.
     * Accepted enabling disconnects online players absent from the whitelist.
     */
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
                            if (Server.getInstance().getPlayerManager().addToWhitelist(nameOrUUID)) {
                                context.addOutput(TrKeys.MC_COMMANDS_ALLOWLIST_ADD_SUCCESS, nameOrUUID);
                                return context.success();
                            } else {
                                context.addError("%" + TrKeys.MC_COMMANDS_ALLOWLIST_ADD_FAILED, nameOrUUID);
                                return context.fail();
                            }
                        }
                        case "remove" -> {
                            if (Server.getInstance().getPlayerManager().removeFromWhitelist(nameOrUUID)) {
                                context.addOutput(TrKeys.MC_COMMANDS_ALLOWLIST_REMOVE_SUCCESS, nameOrUUID);
                                return context.success();
                            } else {
                                context.addError("%" + TrKeys.MC_COMMANDS_ALLOWLIST_REMOVE_FAILED, nameOrUUID);
                                return context.fail();
                            }
                        }
                        default -> {
                            // The enum node limits normal parsing to add or
                            // remove; preserve failure if another value arrives.
                            return context.fail();
                        }
                    }
                })
                .root()
                .key("list")
                .exec(context -> {
                    var whitelist = Server.getInstance().getPlayerManager().getWhitelistedPlayers();
                    var onlineCount = (int) Server.getInstance().getPlayerManager().getPlayers().values().stream()
                            .filter(player -> whitelist.contains(player.getLoginData().getUuid().toString()) ||
                                              whitelist.contains(player.getOriginName()))
                            .count();
                    context.addOutput(TrKeys.MC_COMMANDS_ALLOWLIST_LIST, whitelist.size(), onlineCount);
                    context.addOutput(String.join(", ", whitelist));
                    return context.success();
                })
                .root()
                .key("enable")
                .exec(context -> {
                    Server.getInstance().getPlayerManager().setWhitelistStatus(true);
                    context.addOutput(TrKeys.MC_COMMANDS_ALLOWLIST_ENABLED);
                    return context.success();
                })
                .root()
                .key("disable")
                .exec(context -> {
                    Server.getInstance().getPlayerManager().setWhitelistStatus(false);
                    context.addOutput(TrKeys.MC_COMMANDS_ALLOWLIST_DISABLED);
                    return context.success();
                });
    }
}
