package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.GameMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@code KillCommand} requests command damage for living targets and removal for
 * other entities. Creative and spectator players are skipped during processing,
 * while an all-creative or empty selection fails the initial guard. Feedback
 * counts processed targets without confirming their resulting death or removal.
 *
 * @author daoge_cmd
 */
public class KillCommand extends Command {

    /**
     * Creates the {@code /kill} command with its built-in authorization permission.
     */
    public KillCommand() {
        super("kill", TrKeys.MC_COMMANDS_KILL_DESCRIPTION, Permissions.COMMAND_KILL);
    }

    /**
     * Installs entity-only killing with the sender as the omitted target.
     * Living targets receive {@link DamageType#COMMAND} damage; other entities
     * receive removal requests. The summary counts attempted targets even when
     * downstream events prevent damage or removal.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().target("targets", null).optional().exec((context, sender) -> {
            List<Entity> targets = context.getResult(0);
            if (targets == null) {
                targets = List.of(sender);
            }

            if (targets.stream().allMatch(target -> target instanceof EntityPlayer player && player.getGameMode() == GameMode.CREATIVE)) {
                context.addError("%" + TrKeys.MC_COMMANDS_KILL_ATTEMPTKILLPLAYERCREATIVE);
                return context.fail();
            }

            Map<String, Integer> killedEntities = new HashMap<>();
            for (var target : targets) {
                if (target instanceof EntityPlayer player && (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)) {
                    continue;
                }

                if (target instanceof EntityLiving living) {
                    // The fixed damage amount requests a lethal-sized command hit.
                    living.attack(new DamageContainer(null, DamageType.COMMAND, 1_000_000));
                } else {
                    target.remove();
                }

                killedEntities.merge(target.getCommandSenderName(), 1, Integer::sum);
            }

            var killedSummary = killedEntities.entrySet().stream()
                    .map(entry -> entry.getKey() + " * " + entry.getValue())
                    .collect(Collectors.joining(", "));
            context.addOutput(TrKeys.MC_COMMANDS_KILL_SUCCESSFUL, killedSummary);
            return context.success();
        }, SenderType.ENTITY);
    }
}
