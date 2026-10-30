package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * {@code FogCommand} manages selected players' client fog stacks through their
 * controllers. Simulated players receive no controller operation but remain in
 * the target-name output. Missing fog entries produce informational messages
 * during removal operations without changing the command's successful result.
 *
 * @author daoge_cmd
 */
public class FogCommand extends Command {
    /**
     * Creates the {@code /fog} command with its built-in authorization permission.
     */
    public FogCommand() {
        super("fog", TrKeys.MC_COMMANDS_FOG_DESCRIPTION, Permissions.COMMAND_FOG);
    }

    /**
     * Installs fog push, pop, and removal branches for selected players.
     * Only actual players receive controller calls. Pop or removal of a missing
     * fog identifier adds feedback and still allows later targets and the
     * branch's success message to run.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .key("push")
                .str("fogId")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(0);
                    String fogId = context.getResult(2);
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            player.getController().pushFog(fogId);
                        }
                    });
                    var playerNames = players.stream().map(EntityPlayer::getDisplayName).collect(Collectors.joining(", "));
                    context.addOutput(TrKeys.MC_COMMANDS_FOG_SUCCESS_PUSH, playerNames, fogId);
                    return context.success();
                })
                .up(2)
                .key("pop")
                .str("fogId")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(0);
                    String fogId = context.getResult(2);
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            if (!player.getController().popFog(fogId)) {
                                context.addOutput(TrKeys.MC_COMMANDS_FOG_INVALIDFOGID, fogId);
                            }
                        }
                    });
                    var playerNames = players.stream().map(EntityPlayer::getDisplayName).collect(Collectors.joining(", "));
                    context.addOutput(TrKeys.MC_COMMANDS_FOG_SUCCESS_POP, playerNames, fogId);
                    return context.success();
                })
                .up(2)
                .key("remove")
                .str("fogId")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(0);
                    String fogId = context.getResult(2);
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            if (player.getController().removeFog(fogId) == 0) {
                                context.addOutput(TrKeys.MC_COMMANDS_FOG_INVALIDFOGID, fogId);
                            }
                        }
                    });
                    var playerNames = players.stream().map(EntityPlayer::getDisplayName).collect(Collectors.joining(", "));
                    context.addOutput(TrKeys.MC_COMMANDS_FOG_SUCCESS_REMOVE, playerNames, fogId);
                    return context.success();
                });
    }
}
