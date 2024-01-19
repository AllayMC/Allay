package org.allaymc.server.command.defaults;

import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.world.gamerule.GameRule;

import java.util.function.Function;

import static org.allaymc.api.command.data.CommonEnum.GAMERULE_ENUM;

/**
 * Allay Project 2024/1/19
 *
 * @author daoge_cmd
 */
public class GameRuleCommand extends SimpleCommand {

    public GameRuleCommand() {
        super("gamerule", TrKeys.M_COMMANDS_GAMERULE_DESCRIPTION);
        aliases.add("gr");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .enums("gamerule", GAMERULE_ENUM)
                .bool("value")
                .optional()
                .exec(exec())
                .up()
                .intNum("value")
                .optional()
                .exec(exec());
    }

    protected Function<CommandContext, CommandResult> exec() {
        return context -> {
            var world = context.getSender().getCmdExecuteLocation().dimension().getWorld();
            var gamerule = GameRule.fromName(context.getFirstResult());
            if (context.getSecondResult() != null) {
                boolean value = context.getSecondResult();
                world.setGameRule(gamerule, value);
                context.addOutput(TrKeys.M_COMMANDS_GAMERULE_SUCCESS, gamerule.getName(), value);
            } else {
                boolean value = world.getWorldData().getGameRule(gamerule);
                context.addOutput(String.valueOf(value));
            }
            return context.success();
        };
    }
}
