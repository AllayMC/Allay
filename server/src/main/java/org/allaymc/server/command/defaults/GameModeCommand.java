package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.LangCode;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.GameMode;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code GameModeCommand} requests game-mode changes for selected player
 * entities, or for the player sender when no targets are supplied. Each target
 * receives feedback independently, and the handler reports the requested mode
 * without checking whether a game-mode event cancelled or modified the change.
 *
 * @author daoge_cmd
 */
public class GameModeCommand extends Command {
    /**
     * Creates the authorized {@code /gamemode} command and its {@code /gm} alias.
     */
    public GameModeCommand() {
        super("gamemode", TrKeys.MC_COMMANDS_GAMEMODE_DESCRIPTION, Permissions.COMMAND_GAMEMODE);
        aliases.add("gm");
    }

    /**
     * Installs game-mode selection with an optional player target argument.
     * Omitted targets require {@code context.getSender()} to be an {@link EntityPlayer}
     * instance; explicit targets must match at least one player. Requests
     * process every target and retain earlier changes if a later operation fails.
     * Feedback resolves the mode label using {@link LangCode#en_US}.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .gameMode()
                .playerTarget("players").optional()
                .exec(context -> {
                    GameMode gameMode = context.getResult(0);
                    List<EntityPlayer> players = new ArrayList<>();
                    if (context.getArgCount() == 1) {
                        if (context.getSender() instanceof EntityPlayer player) {
                            players.add(player);
                        } else {
                            context.addInvalidExecutorError(SenderType.PLAYER);
                            return context.fail();
                        }
                    } else {
                        players.addAll(context.getResult(1));
                        if (players.isEmpty()) {
                            context.addNoTargetMatchError();
                            return context.fail();
                        }
                    }

                    var gameModeName = I18n.get().tr(gameMode.getTranslationKey(), LangCode.en_US);
                    for (var player : players) {
                        player.setGameMode(gameMode);

                        if (player.equals(context.getSender())) {
                            context.addOutput(TrKeys.MC_COMMANDS_GAMEMODE_SUCCESS_SELF, gameModeName);
                        } else {
                            context.addOutput(TrKeys.MC_COMMANDS_GAMEMODE_SUCCESS_OTHER, gameModeName, player.getDisplayName());
                            context.sendWhisperTo(player, TrKeys.MC_GAMEMODE_CHANGED, gameModeName);
                        }
                    }

                    return context.success();
                });
    }
}
