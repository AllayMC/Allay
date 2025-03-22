package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.type.ItemType;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public class GiveCommand extends SimpleCommand {

    public GiveCommand() {
        super("give", TrKeys.M_COMMANDS_GIVE_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .itemType("itemName")
                .intNum("amount", 1)
                .optional()
                .intNum("data", 0)
                .optional()
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(0);
                    if (players.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    ItemType<?> itemType = context.getResult(1);
                    final int amount = context.getResult(2);
                    if (amount <= 0) {
                        context.addSyntaxError(2);
                        return context.fail();
                    }

                    int data = context.getResult(3);
                    if (data < 0) {
                        context.addSyntaxError(3);
                        return context.fail();
                    }

                    for (var player : players) {
                        var maxStackSize = itemType.getItemData().maxStackSize();
                        var i = amount;
                        while (i != 0) {
                            int currentItemAmount;
                            if (i > maxStackSize) {
                                currentItemAmount = maxStackSize;
                                i -= maxStackSize;
                            } else {
                                currentItemAmount = i;
                                i = 0;
                            }
                            var itemStack = itemType.createItemStack(currentItemAmount, data);
                            player.getContainer(FullContainerType.PLAYER_INVENTORY).tryAddItem(itemStack);
                            if (itemStack.getCount() != 0) {
                                player.dropItemInPlayerPos(itemStack);
                            }
                        }
                        player.sendTr(TrKeys.M_COMMANDS_GIVE_SUCCESSRECIPIENT, itemType.getIdentifier().toString(), amount);
                    }
                    context.addOutput(
                            TrKeys.M_COMMANDS_GIVE_SUCCESS, itemType.getIdentifier().toString(),
                            amount, players.stream().map(EntityPlayer::getDisplayName).collect(Collectors.joining(", "))
                    );
                    return context.success();
                });
    }
}
