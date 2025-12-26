package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.HudElement;

import java.util.Collection;

/**
 * @author daoge_cmd
 */
public class HudCommand extends Command {

    public HudCommand() {
        super("hud", TrKeys.MC_COMMANDS_HUD_DESCRIPTION, Permissions.COMMAND_HUD);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .enums("visible", "hide", "reset")
                .enumClass("hudElement", HudElement.class)
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(0);
                    String visibility = context.getResult(1);
                    HudElement hudElement = context.getResult(2);

                    if (players.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    boolean visible = visibility.equals("reset");

                    for (EntityPlayer player : players) {
                        var controller = player.getController();
                        if (controller == null) continue;

                        controller.setHudElementVisibility(hudElement, visible);
                    }

                    context.addOutput("%" + TrKeys.MC_COMMANDS_HUD_SUCCESS);
                    return context.success();
                });
    }
}
