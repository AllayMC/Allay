package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.cloudburstmc.protocol.bedrock.data.GameType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public class KillCommand extends SimpleCommand {

    public KillCommand() {
        super("kill", TrKeys.M_COMMANDS_KILL_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                // Set default value to null to
                // mark that the sender want to suicide
                .target("targets", null)
                .optional()
                .exec((context, sender) -> {
                    List<Entity> targets = context.getResult(0);
                    if (targets == null) {
                        targets = List.of(sender);
                    }

                    if (targets.stream().allMatch(target -> target instanceof EntityPlayer player && player.getGameType() == GameType.CREATIVE)) {
                        context.addError("%" + TrKeys.M_COMMANDS_KILL_ATTEMPTKILLPLAYERCREATIVE);
                        return context.fail();
                    }

                    Map<String, Integer> killedEntities = new HashMap<>();
                    for (var target : targets) {
                        if (target instanceof EntityPlayer player && (player.getGameType() == GameType.CREATIVE || player.getGameType() == GameType.SPECTATOR)) {
                            continue;
                        }
                        if (target instanceof EntityAttributeComponent damageComponent) {
                            damageComponent.kill();
                        } else {
                            target.despawn();
                        }
                        killedEntities.compute(target.getCommandSenderName(), (k, v) -> v != null ? ++v : 1);
                    }
                    context.addOutput(TrKeys.M_COMMANDS_KILL_SUCCESSFUL, killedEntities.entrySet().stream().map(entry -> entry.getKey() + " * " + entry.getValue()).collect(Collectors.joining(", ")));
                    return context.success();
                }, SenderType.ENTITY);
    }
}
