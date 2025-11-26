package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public class TagCommand extends Command {

    public TagCommand() {
        super("tag", TrKeys.MC_COMMANDS_TAG_DESCRIPTION, Permissions.COMMAND_TAG);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .target("targets")
                .enums("operation", "add", "remove")
                .str("name")
                .exec(context -> {
                    List<Entity> entities = context.getResult(0);
                    if (entities.isEmpty()) {
                        context.addError("%" + TrKeys.MC_COMMANDS_GENERIC_NOTARGETMATCH);
                        return context.fail();
                    }

                    String operation = context.getResult(1);
                    String tag = context.getResult(2);

                    var successCount = 0;
                    switch (operation) {
                        case "add" -> {
                            for (var entity : entities) {
                                var success = entity.addTag(tag);
                                if (success) successCount++;
                            }

                            if (successCount == 0) {
                                context.addError("%" + TrKeys.MC_COMMANDS_TAG_ADD_FAILED);
                                return context.fail();
                            }

                            if (entities.size() == 1) {
                                context.addOutput(TrKeys.MC_COMMANDS_TAG_ADD_SUCCESS_SINGLE, tag, entities.getFirst().getDisplayName());
                            } else {
                                context.addOutput(TrKeys.MC_COMMANDS_TAG_ADD_SUCCESS_MULTIPLE, tag, entities.size());
                            }
                        }
                        case "remove" -> {
                            for (var entity : entities) {
                                var success = entity.removeTag(tag);
                                if (success) successCount++;
                            }

                            if (successCount == 0) {
                                context.addError("%" + TrKeys.MC_COMMANDS_TAG_REMOVE_FAILED);
                                return context.fail();
                            }

                            if (entities.size() == 1) {
                                context.addOutput(TrKeys.MC_COMMANDS_TAG_REMOVE_SUCCESS_SINGLE, tag, entities.getFirst().getDisplayName());
                            } else {
                                context.addOutput(TrKeys.MC_COMMANDS_TAG_REMOVE_SUCCESS_MULTIPLE, tag, entities.size());
                            }
                        }
                    }

                    return context.success(successCount);
                })
                .up(2)
                .key("list")
                .exec(context -> {
                    List<Entity> entities = context.getResult(0);
                    if (entities.isEmpty()) {
                        context.addError("%" + TrKeys.MC_COMMANDS_GENERIC_NOTARGETMATCH);
                        return context.fail();
                    }

                    var tags = entities.stream()
                            .flatMap(entity -> entity.getTags().stream())
                            .collect(Collectors.toSet());

                    String tagStr = String.join(" ", tags);
                    if (tags.isEmpty()) {
                        if (entities.size() == 1) {
                            context.addError("%" + TrKeys.MC_COMMANDS_TAG_LIST_SINGLE_EMPTY, entities.getFirst().getDisplayName());
                        } else {
                            context.addError("%" + TrKeys.MC_COMMANDS_TAG_LIST_MULTIPLE_EMPTY, entities.size());
                        }

                        return context.fail();
                    } else {
                        if (entities.size() == 1) {
                            context.addOutput(TrKeys.MC_COMMANDS_TAG_LIST_SINGLE_SUCCESS, entities.getFirst().getCommandSenderName(), tags.size(), tagStr);
                        } else {
                            context.addOutput(TrKeys.MC_COMMANDS_TAG_LIST_MULTIPLE_SUCCESS, entities.size(), tags.size(), tagStr);
                        }

                        return context.success();
                    }
                });
    }
}
