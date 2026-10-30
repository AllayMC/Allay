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
 * {@code EnchantCommand} adds one enchantment to the held items of selected
 * players. The handler validates the requested level before visiting targets,
 * then stops at the first empty hand or incompatible enchantment. Earlier
 * targets keep their changes when a later target fails validation.
 *
 * @author daoge_cmd
 */
public class EnchantCommand extends Command {

    /**
     * Creates the {@code /enchant} command with its built-in authorization permission.
     */
    public EnchantCommand() {
        super("enchant", TrKeys.MC_COMMANDS_ENCHANT_DESCRIPTION, Permissions.COMMAND_ENCHANT);
    }

    /**
     * Installs enchantment application with level {@code 1} and limits enabled.
     * Normal levels range from {@code 1} through
     * {@code enchantmentType.getMaxLevel()}.
     * {@code ignoreLimit} bypasses level bounds but still checks existing
     * enchantment incompatibility. Successful targets are updated immediately,
     * and a later failure leaves those updates in place.
     */
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
