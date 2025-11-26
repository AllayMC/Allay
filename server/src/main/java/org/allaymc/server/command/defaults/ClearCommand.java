package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author daoge_cmd
 */
public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear", TrKeys.MC_COMMANDS_CLEAR_DESCRIPTION, Permissions.COMMAND_CLEAR);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                // Use null to mark that the sender
                // want to clear all his items
                .playerTarget("players", null).optional()
                .itemType("item").optional()
                .intNum("data", -1).optional()
                .intNum("maxCount", -1).optional()
                .exec((context, sender) -> {
                    List<EntityPlayer> targets = context.getResult(0);
                    if (targets != null && targets.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    } else if (targets == null) {
                        targets = List.of(sender);
                    }

                    ItemType<?> itemType = context.getResult(1);
                    int data = context.getResult(2);
                    int maxCount = context.getResult(3);
                    if (maxCount == -1) {
                        maxCount = Integer.MAX_VALUE;
                    }

                    boolean success = true;
                    int status = 0;
                    for (var target : targets) {
                        var containers = Stream.of(
                                ContainerTypes.INVENTORY,
                                ContainerTypes.OFFHAND,
                                ContainerTypes.ARMOR
                        ).map(target::getContainer).toList();
                        if (maxCount == 0) {
                            int count = containers.stream()
                                    .mapToInt(container -> countMatchingItems(container.getItemStacks(), itemType, data))
                                    .sum();
                            context.addOutput(TrKeys.MC_COMMANDS_CLEAR_TESTING, target.getDisplayName(), count);
                            status = count;
                        } else {
                            int removed = 0;
                            int remaining = maxCount;
                            for (var container : containers) {
                                removed += clearItemsFromContainer(container, itemType, data, remaining);
                                remaining = maxCount - removed;
                                if (remaining <= 0) {
                                    break;
                                }
                            }

                            if (removed > 0) {
                                context.addOutput(TrKeys.MC_COMMANDS_CLEAR_SUCCESS, target.getDisplayName(), removed);
                            } else {
                                context.addError("%" + TrKeys.MC_COMMANDS_CLEAR_FAILURE_NO_ITEMS, target.getDisplayName());
                                success = false;
                            }

                            status = removed;
                        }
                    }

                    return success ? context.success(status) : context.fail();
                }, SenderType.PLAYER);
    }

    protected int clearItemsFromContainer(Container container, ItemType<?> itemType, int data, int maxCount) {
        int removed = 0;
        var remaining = maxCount;

        var itemStacks = container.getItemStackArray();
        for (int slot = 0; slot < itemStacks.length; slot++) {
            ItemStack itemStack = itemStacks[slot];
            if (matchesItem(itemStack, itemType, data)) {
                var count = itemStack.getCount();
                if (count <= remaining) {
                    remaining -= count;
                    removed += count;
                    container.clearSlot(slot);
                } else {
                    itemStack.setCount(count - remaining);
                    container.notifySlotChange(slot);
                    removed += remaining;
                    break;
                }
            }
        }

        return removed;
    }

    protected int countMatchingItems(List<ItemStack> items, ItemType<?> itemType, int data) {
        return items.stream()
                .filter(item -> matchesItem(item, itemType, data))
                .mapToInt(ItemStack::getCount)
                .sum();
    }

    protected boolean matchesItem(ItemStack itemStack, ItemType<?> itemType, int data) {
        return itemStack.getItemType() != ItemTypes.AIR
               && (itemType == null || itemStack.getItemType() == itemType)
               && (data == -1 || itemStack.getMeta() == data);
    }
}