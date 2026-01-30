package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;

import java.util.Collection;

/**
 * @author daoge_cmd
 */
public class EnchantCommand extends Command {

    public EnchantCommand() {
        super("enchant", TrKeys.MC_COMMANDS_ENCHANT_DESCRIPTION, Permissions.COMMAND_ENCHANT);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .enchantment("enchantmentName")
                .intNum("level", 1).optional()
                .bool("ignoreLimit", false).optional()
                .exec(ctx -> {
                    Collection<EntityPlayer> players = ctx.getResult(0);
                    EnchantmentType enchantmentType = ctx.getResult(1);
                    int level = ctx.getResult(2);
                    boolean ignoreLimit = ctx.getResult(3);

                    if (!ignoreLimit) {
                        if (level > enchantmentType.getMaxLevel()) {
                            ctx.addError("%" + TrKeys.MC_COMMANDS_GENERIC_NUM_TOOBIG, level, enchantmentType.getMaxLevel());
                            ctx.addError("%" + TrKeys.MC_COMMANDS_ENCHANT_INVALIDLEVEL, enchantmentType.getIdentifier(), level);
                            return ctx.fail();
                        } else if (level < 1) {
                            ctx.addError("%" + TrKeys.MC_COMMANDS_GENERIC_NUM_TOOSMALL, level, 1);
                            ctx.addError("%" + TrKeys.MC_COMMANDS_ENCHANT_INVALIDLEVEL, enchantmentType.getIdentifier(), level);
                            return ctx.fail();
                        }
                    }

                    for (var player : players) {
                        var item = player.getContainer(ContainerTypes.INVENTORY).getItemInHand();
                        if (item == ItemAirStack.AIR_STACK) {
                            ctx.addError("%" + TrKeys.MC_COMMANDS_ENCHANT_NOITEM, player.getDisplayName());
                            return ctx.fail();
                        }

                        var incompatibleEnchantmentType = item.getIncompatibleEnchantmentTypes(enchantmentType);
                        if (!incompatibleEnchantmentType.isEmpty()) {
                            incompatibleEnchantmentType.forEach(incompatibleEnchantment -> {
                                ctx.addError("%" + TrKeys.MC_COMMANDS_ENCHANT_CANTCOMBINE, incompatibleEnchantment.getIdentifier(), enchantmentType.getIdentifier());
                            });
                            return ctx.fail();
                        }

                        item.addEnchantment(enchantmentType, level);
                        player.notifyItemInHandChange();
                        ctx.addOutput(TrKeys.MC_COMMANDS_ENCHANT_SUCCESS, enchantmentType.getIdentifier());
                    }

                    return ctx.success();
                });
    }
}
