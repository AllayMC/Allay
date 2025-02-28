package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;

import java.util.Collection;

/**
 * @author daoge_cmd
 */
public class TitleCommand extends SimpleCommand {
    public TitleCommand() {
        super("title", TrKeys.M_COMMANDS_TITLE_DESCRIPTION);
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
                    players.forEach(p -> sendTitle(p, text, type));
                    context.addOutput(TrKeys.M_COMMANDS_TITLE_SUCCESS);
                    return context.success();
                })
                .root()
                .key("clear")
                .playerTarget("players")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(1);
                    players.forEach(EntityPlayerBaseComponent::clearTitle);
                    context.addOutput(TrKeys.M_COMMANDS_TITLE_SUCCESS);
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
                    players.forEach(p -> p.setTitleSettings(fadeInTime, duration, fadeOutTime));
                    context.addOutput(TrKeys.M_COMMANDS_TITLE_SUCCESS);
                    return context.success();
                })
                .root()
                .key("reset")
                .playerTarget("players")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(1);
                    players.forEach(EntityPlayerBaseComponent::resetTitleSettings);
                    context.addOutput(TrKeys.M_COMMANDS_TITLE_SUCCESS);
                    return context.success();
                });
    }

    protected void sendTitle(EntityPlayer player, String text, TitleType type) {
        switch (type) {
            case TITLE -> player.sendTitle(text);
            case SUBTITLE -> player.sendSubtitle(text);
            case ACTIONBAR -> player.sendActionBar(text);
        }
    }

    protected enum TitleType {
        TITLE,
        SUBTITLE,
        ACTIONBAR
    }
}
