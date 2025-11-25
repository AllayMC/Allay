package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;

import java.util.Collection;

/**
 * @author daoge_cmd
 */
public class TitleCommand extends VanillaCommand {
    public TitleCommand() {
        super("title", TrKeys.MC_COMMANDS_TITLE_DESCRIPTION);
    }

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

    protected enum TitleType {
        TITLE {
            @Override
            public void send(Player player, String text) {
                player.sendTitle(text);
            }
        },
        SUBTITLE {
            @Override
            public void send(Player player, String text) {
                player.sendSubtitle(text);
            }
        },
        ACTIONBAR {
            @Override
            public void send(Player player, String text) {
                player.sendActionBar(text);
            }
        };

        public abstract void send(Player player, String text);
    }
}
