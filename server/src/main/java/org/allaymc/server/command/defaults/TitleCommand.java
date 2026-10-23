package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.Player;

import java.util.Collection;

/**
 * {@code TitleCommand} sends title text and timing controls to selected actual
 * players. The handler supports title, subtitle, and action-bar channels;
 * simulated targets are skipped, and each branch reports success after its
 * controller calls without requiring a nonempty selection.
 *
 * @author daoge_cmd
 */
public class TitleCommand extends Command {
    /**
     * Creates the {@code /title} command with its built-in authorization permission.
     */
    public TitleCommand() {
        super("title", TrKeys.MC_COMMANDS_TITLE_DESCRIPTION, Permissions.COMMAND_TITLE);
    }

    /**
     * Installs title text, clearing, timing, and timing-reset branches.
     * Timing arguments are passed to controllers in ticks without range checks.
     * Only actual player targets receive calls, while empty or entirely
     * simulated selections still produce successful feedback.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("set")
                .playerTarget("players")
                .enumClass("type", TitleType.class)
                .str("text")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(1);
                    TitleType type = context.getResult(2);
                    String text = context.getResult(3);
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            type.send(player.getController(), text);
                        }
                    });
                    context.addOutput(TrKeys.MC_COMMANDS_TITLE_SUCCESS);
                    return context.success();
                })
                .root()
                .key("clear")
                .playerTarget("players")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(1);
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            player.getController().clearTitle();
                        }
                    });
                    context.addOutput(TrKeys.MC_COMMANDS_TITLE_SUCCESS);
                    return context.success();
                })
                .root()
                .key("times")
                .playerTarget("players")
                .intNum("fadeInTime")
                .intNum("duration")
                .intNum("fadeOutTime")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(1);
                    int fadeInTime = context.getResult(2);
                    int duration = context.getResult(3);
                    int fadeOutTime = context.getResult(4);
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            player.getController().setTitleSettings(fadeInTime, duration, fadeOutTime);
                        }
                    });
                    context.addOutput(TrKeys.MC_COMMANDS_TITLE_SUCCESS);
                    return context.success();
                })
                .root()
                .key("reset")
                .playerTarget("players")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(1);
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            player.getController().resetTitleSettings();
                        }
                    });
                    context.addOutput(TrKeys.MC_COMMANDS_TITLE_SUCCESS);
                    return context.success();
                });
    }

    /**
     * {@code TitleType} selects the player display channel used by title text.
     * Its constants route the same supplied string to title, subtitle, or
     * action-bar delivery without changing the player's timing settings here.
     */
    protected enum TitleType {
        /**
         * Main title display channel.
         */
        TITLE {
            /**
             * Sends the supplied text through the player's main title channel.
             */
            @Override
            public void send(Player player, String text) {
                player.sendTitle(text);
            }
        },
        /**
         * Subtitle text display channel.
         */
        SUBTITLE {
            /**
             * Sends the supplied text through the player's subtitle display channel.
             */
            @Override
            public void send(Player player, String text) {
                player.sendSubtitle(text);
            }
        },
        /**
         * Action-bar text display channel.
         */
        ACTIONBAR {
            /**
             * Sends the supplied text through the player's action-bar display channel.
             */
            @Override
            public void send(Player player, String text) {
                player.sendActionBar(text);
            }
        };

        /**
         * Sends text to the display channel selected by this constant.
         */
        public abstract void send(Player player, String text);
    }
}
