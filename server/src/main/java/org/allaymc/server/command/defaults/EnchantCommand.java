package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.interfaces.ItemAirStack;

import java.util.Collection;

/**
 * @author daoge_cmd
 */
public class EnchantCommand extends SimpleCommand {

    public EnchantCommand() {
        super("enchant", TrKeys.M_COMMANDS_ENCHANT_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .enchantment("enchantmentName")
                .intNum("level", 1).optional()
                .exec(ctx -> {
                    Collection<EntityPlayer> players = ctx.getResult(0);
                    EnchantmentType enchantmentType = ctx.getResult(1);
                    int level = ctx.getResult(2);

                    if (level > enchantmentType.getMaxLevel()) {
                        ctx.addOutput(TrKeys.M_COMMANDS_GENERIC_NUM_TOOBIG, level, enchantmentType.getMaxLevel());
                        ctx.addOutput(TrKeys.M_COMMANDS_ENCHANT_INVALIDLEVEL, enchantmentType.getIdentifier(), level);
                        return ctx.fail();
                    } else if (level < 1) {
                        ctx.addOutput(TrKeys.M_COMMANDS_GENERIC_NUM_TOOSMALL, level, 1);
                        ctx.addOutput(TrKeys.M_COMMANDS_ENCHANT_INVALIDLEVEL, enchantmentType.getIdentifier(), level);
                        return ctx.fail();
                    }

                    for (var player : players) {
                        var item = player.getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand();
                        if (item == ItemAirStack.AIR_STACK) {
                            ctx.addOutput(TrKeys.M_COMMANDS_ENCHANT_NOITEM, player.getDisplayName());
                            return ctx.fail();
                        }

                        var incompatibleEnchantmentType = item.getIncompatibleEnchantmentTypes(enchantmentType);
                        if (!incompatibleEnchantmentType.isEmpty()) {
                            incompatibleEnchantmentType.forEach(incompatibleEnchantment -> {
                                ctx.addOutput(TrKeys.M_COMMANDS_ENCHANT_CANTCOMBINE, incompatibleEnchantment.getIdentifier(), enchantmentType.getIdentifier());
                            });
                            return ctx.fail();
                        }

                        item.addEnchantment(enchantmentType, level);
                        player.notifyItemInHandChange();
                        ctx.addOutput(TrKeys.M_COMMANDS_ENCHANT_SUCCESS, enchantmentType.getIdentifier());
                    }

                    return ctx.success();
                });
    }
}
