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
 * {@code ClearCommand} counts or removes matching player items across inventory,
 * offhand, and armor containers. Each target receives its own removal limit;
 * zero selects counting, and omitted filters select all non-air items. A failed
 * target does not undo removals already made for other targets.
 *
 * <p>
 * The handler combines an all-target removal-success flag with a numeric status
 * overwritten by each target. A removal failure anywhere makes the whole
 * command fail, while successful processing returns the last target's count.
 * Counting mode leaves the flag successful even for zero matches, but a final
 * count of zero still produces a result that {@code CommandResult.isSuccess}
 * classifies as failure. These outputs and statuses describe different aspects
 * of the selection rather than one aggregate item total.
 *
 * @author daoge_cmd
 */
public class ClearCommand extends Command {
    /**
     * Creates the {@code /clear} command with its built-in authorization permission.
     */
    public ClearCommand() {
        super("clear", TrKeys.MC_COMMANDS_CLEAR_DESCRIPTION, Permissions.COMMAND_CLEAR);
    }

    /**
     * Installs player-only clearing with optional targets and item filters.
     * Omitted targets select the sender, {@code -1} disables metadata or count
     * limits, and {@code 0} counts matches. The final target supplies the status
     * value; a target with no removals makes removal mode fail overall.
     * Other negative limits reach the removal helper unchanged and can increase
     * a matching stack's count instead of removing items.
     *
     * <p>
     * Counting mode preserves the local success flag for every target, but its
     * last count becomes the returned status. Zero therefore yields a result
     * whose {@code CommandResult.isSuccess} value is {@code false}, even
     * though counting itself completed and emitted feedback.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
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

    /**
     * Removes matching items up to a nonnegative {@code maxCount} limit.
     * Matching stacks are cleared or reduced in slot order and notify container
     * listeners immediately; {@code null} item types and {@code -1} metadata
     * match any non-air type and metadata respectively.
     *
     * <p>
     * A negative limit reaches stack subtraction unchanged, so it can increase
     * a matching stack and return a negative removed count. Callers requiring
     * removal must reject negative limits before invoking this helper.
     */
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

    /**
     * Sums the counts of non-air stacks matching the supplied filters.
     * A {@code null} item type permits every type, and {@code -1} metadata
     * disables the metadata restriction without changing the supplied stacks.
     */
    protected int countMatchingItems(List<ItemStack> items, ItemType<?> itemType, int data) {
        return items.stream()
                .filter(item -> matchesItem(item, itemType, data))
                .mapToInt(ItemStack::getCount)
                .sum();
    }

    /**
     * Returns whether a non-air stack matches the item and metadata filters.
     * A {@code null} item type and {@code -1} metadata independently disable
     * their corresponding restrictions; air never matches either wildcard.
     */
    protected boolean matchesItem(ItemStack itemStack, ItemType<?> itemType, int data) {
        return itemStack.getItemType() != ItemTypes.AIR
               && (itemType == null || itemStack.getItemType() == itemType)
               && (data == -1 || itemStack.getMeta() == data);
    }
}
