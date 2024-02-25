package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.i18n.TrKeys;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Allay Project 2024/2/25
 *
 * @author daoge_cmd
 */
public class TagCommand extends SimpleCommand {

    public TagCommand() {
        super("tag", TrKeys.M_COMMANDS_TAG_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .target("targets")
                .enums("operation", "add", "remove")
                .str("name")
                .exec(context -> {
                    List<Entity> entities = context.getFirstResult();
                    if (entities.isEmpty()) {
                        context.addError("%" + TrKeys.M_COMMANDS_GENERIC_NOTARGETMATCH);
                        return context.failed();
                    }
                    String operation = context.getSecondResult();
                    String tag = context.getThirdResult();
                    var successCount = 0;
                    switch (operation) {
                        case "add" -> {
                            for (var entity : entities) {
                                var success = entity.addTag(tag);
                                if (success) successCount++;
                            }
                            if (successCount == 0) {
                                context.addError("%" + TrKeys.M_COMMANDS_TAG_ADD_FAILED);
                                return context.failed();
                            }
                            if (entities.size() == 1) {
                                context.addOutput(TrKeys.M_COMMANDS_TAG_ADD_SUCCESS_SINGLE, tag, entities.getFirst().getDisplayName());
                            } else {
                                context.addOutput(TrKeys.M_COMMANDS_TAG_ADD_SUCCESS_MULTIPLE, tag, entities.size());
                            }
                        }
                        case "remove" -> {
                            for (var entity : entities) {
                                var success = entity.removeTag(tag);
                                if (success) successCount++;
                            }
                            if (successCount == 0) {
                                context.addError("%" + TrKeys.M_COMMANDS_TAG_REMOVE_FAILED);
                                return context.failed();
                            }
                            if (entities.size() == 1) {
                                context.addOutput(TrKeys.M_COMMANDS_TAG_REMOVE_SUCCESS_SINGLE, tag, entities.getFirst().getDisplayName());
                            } else {
                                context.addOutput(TrKeys.M_COMMANDS_TAG_REMOVE_SUCCESS_MULTIPLE, tag, entities.size());
                            }
                        }
                    }
                    return context.success(successCount);
                })
                .up(2)
                .key("list")
                .exec(context -> {
                    List<Entity> entities = context.getFirstResult();
                    if (entities.isEmpty()) {
                        context.addError("%" + TrKeys.M_COMMANDS_GENERIC_NOTARGETMATCH);
                        return context.failed();
                    }
                    Set<String> tagSet = new HashSet<>();
                    for (var entity : entities) {
                        tagSet.addAll(entity.getTags());
                    }
                    String tagStr = String.join(" ", tagSet);
                    if (tagSet.isEmpty()) {
                        if (entities.size() == 1) {
                            context.addError("%" + TrKeys.M_COMMANDS_TAG_LIST_SINGLE_EMPTY, entities.getFirst().getDisplayName());
                        } else {
                            context.addError("%" + TrKeys.M_COMMANDS_TAG_LIST_MULTIPLE_EMPTY, entities.size());
                        }
                        return context.failed();
                    } else {
                        if (entities.size() == 1) {
                            context.addOutput(TrKeys.M_COMMANDS_TAG_LIST_SINGLE_SUCCESS, entities.getFirst().getName(), tagSet.size(), tagStr);
                        } else {
                            context.addOutput(TrKeys.M_COMMANDS_TAG_LIST_MULTIPLE_SUCCESS, entities.size(), tagSet.size(), tagStr);
                        }
                        return context.success();
                    }
                });
    }
}
