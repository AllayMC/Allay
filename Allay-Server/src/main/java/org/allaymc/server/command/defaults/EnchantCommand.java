package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.enchantment.EnchantmentType;

import java.util.Collection;

/**
 * Allay Project 2024/6/15
 *
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
                .enchantmentNode("enchantmentName")
                .shortNum("level")
                .exec(ctx -> {
                    Collection<EntityPlayer> players = ctx.getFirstResult();
                    EnchantmentType enchantmentType = ctx.getSecondResult();
                    short level = ctx.getThirdResult();
                    for (var player : players) {
                        var item = player.getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand();
                        if (item == Container.EMPTY_SLOT_PLACE_HOLDER) {
                            // 手上没东西
                            ctx.addOutput(TrKeys.M_COMMANDS_ENCHANT_NOITEM);
                            return ctx.fail();
                        }
                        var incompatibleEnchantmentType = item.getIncompatibleEnchantmentType(enchantmentType);
                        if (incompatibleEnchantmentType != null) {
                            // 新附魔和已有附魔有冲突
                            ctx.addOutput(TrKeys.M_COMMANDS_ENCHANT_CANTCOMBINE, incompatibleEnchantmentType.getIdentifier(), enchantmentType.getIdentifier());
                            return ctx.fail();
                        }
                        item.addEnchantment(enchantmentType, level);
                        player.sendItemInHandUpdate();
                        ctx.addOutput(TrKeys.M_COMMANDS_ENCHANT_SUCCESS, enchantmentType.getIdentifier());
                    }
                    return ctx.success();
                });
    }
}
