package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author daoge_cmd
 */
public class ClearCommand extends SimpleCommand {
    public ClearCommand() {
        super("clear", TrKeys.M_COMMANDS_CLEAR_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                // Use null to mark that the sender
                // want to clear all his items
                .playerTarget("players", null)
                .optional()
                .itemType("item")
                .optional()
                .intNum("data", -1)
                .optional()
                .intNum("maxCount", -1)
                .optional()
                .exec((context, sender) -> {
                    var targets = new ArrayList<EntityPlayer>();
                    targets.addAll(context.getResult(0) != null ? context.getResult(0) : List.of(sender));

                    ItemType<?> itemType = context.getResult(1);
                    int data = context.getResult(2);
                    int maxCount = context.getResult(3);
                    if (maxCount == -1) {
                        maxCount = Integer.MAX_VALUE;
                    }

                    for (var target : targets) {
                        var containers = Stream.of(FullContainerType.PLAYER_INVENTORY, FullContainerType.OFFHAND, FullContainerType.ARMOR).map(target::getContainer).toList();
                        if (maxCount == 0) {
                            int count = containers.stream()
                                    .mapToInt(container ->
                                            container.getItemStacks()
                                                    .stream()
                                                    .filter(itemStack -> itemStack.getItemType() != ItemTypes.AIR && (itemType == null || itemStack.getItemType() == itemType) && (data == -1 || itemStack.getMeta() == data))
                                                    .toList()
                                                    .size())
                                    .sum();
                            context.addOutput(TrKeys.M_COMMANDS_CLEAR_TESTING, target.getOriginName(), count);
                        } else {
                            int c = maxCount;
                            for (var container : containers) {
                                ItemStack[] itemStackArray = container.getItemStackArray();
                                for (int slot = 0; slot < itemStackArray.length; slot++) {
                                    var itemStack = itemStackArray[slot];
                                    if (itemStack.getItemType() != ItemTypes.AIR && (itemType == null || itemStack.getItemType() == itemType) && (data == -1 || itemStack.getMeta() == data)) {
                                        var itemStackCount = itemStack.getCount();
                                        if (itemStackCount <= c) {
                                            c -= itemStackCount;
                                            container.clearSlot(slot);
                                        } else {
                                            itemStack.setCount(itemStackCount - c);
                                            c = 0;
                                            container.notifySlotChange(slot);
                                        }
                                    }
                                }
                            }
                            if (maxCount != c) {
                                context.addOutput(TrKeys.M_COMMANDS_CLEAR_SUCCESS, target.getOriginName(), maxCount - c);
                            } else {
                                context.addOutput(TrKeys.M_COMMANDS_CLEAR_FAILURE_NO_ITEMS, target.getOriginName());
                            }
                        }
                    }
                    return context.success();
                }, SenderType.PLAYER);
    }


}
