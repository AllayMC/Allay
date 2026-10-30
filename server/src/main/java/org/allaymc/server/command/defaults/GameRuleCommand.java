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
 * {@code GameRuleCommand} queries or requests changes to rules in the sender's
 * execution world. Separate boolean and integer branches constrain argument
 * parsing, while an omitted value selects a query. Mutation feedback reports
 * the requested value without checking cancellation or modification by a
 * game-rule event.
 *
 * @author daoge_cmd
 */
public class GameRuleCommand extends Command {

    /**
     * Creates the authorized {@code /gamerule} command and its {@code /gr} alias.
     */
    public GameRuleCommand() {
        super("gamerule", TrKeys.MC_COMMANDS_GAMERULE_DESCRIPTION, Permissions.COMMAND_GAMERULE);
        aliases.add("gr");
    }

    /**
     * Installs separately typed boolean and integer rule branches.
     * Each optional value defaults to {@code null}, allowing the executor to
     * distinguish queries from changes even when a supplied value is
     * {@code false} or {@code 0}.
     */
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

    /**
     * Returns a rule handler for the selected parsed value type.
     * {@code isBoolGameRule} must agree with the attached branch's argument
     * type; {@code null} queries the current value, while other values request
     * a change and produce success feedback.
     */
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
