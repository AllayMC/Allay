package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.gamerule.GameRule;

import java.util.function.Function;

import static org.allaymc.server.command.data.CommonEnums.BOOL_GAMERULE_ENUM;
import static org.allaymc.server.command.data.CommonEnums.INT_GAMERULE_ENUM;

/**
 * @author daoge_cmd
 */
public class GameRuleCommand extends Command {

    public GameRuleCommand() {
        super("gamerule", TrKeys.MC_COMMANDS_GAMERULE_DESCRIPTION, Permissions.COMMAND_GAMERULE);
        aliases.add("gr");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .enumsIgnoreCase("rule", "", "BoolGameRule", BOOL_GAMERULE_ENUM)
                .bool("value")
                .defaultValue(null).optional()
                .exec(executor(true))
                .root()
                .enumsIgnoreCase("rule", "", "IntGameRule", INT_GAMERULE_ENUM)
                .intNum("value")
                .defaultValue(null).optional()
                .exec(executor(false));
    }

    protected Function<CommandContext, CommandResult> executor(boolean isBoolGameRule) {
        return context -> {
            var world = context.getSender().getCommandExecuteLocation().dimension().getWorld();
            var gamerule = GameRule.fromName(context.getResult(0));
            var value = context.getResult(1);
            if (value != null) {
                value = isBoolGameRule ? (boolean) value : (int) value;
                world.getWorldData().setGameRuleValue(gamerule, value);

                context.addOutput(TrKeys.MC_COMMANDS_GAMERULE_SUCCESS, gamerule.getName(), value);
            } else {
                value = world.getWorldData().getGameRuleValue(gamerule);
                context.addOutput(gamerule.getName() + " = " + value);
            }

            return context.success();
        };
    }
}
