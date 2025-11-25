package org.allaymc.server.command.defaults;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.data.SortOrder;
import org.allaymc.api.scoreboard.scorer.EntityScorer;
import org.allaymc.api.scoreboard.scorer.FakeScorer;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.scoreboard.scorer.Scorer;
import org.allaymc.api.server.Server;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public class ScoreboardCommand extends VanillaCommand {

    public ScoreboardCommand() {
        super("scoreboard", TrKeys.MC_COMMANDS_SCOREBOARD_DESCRIPTION);
    }

    private static Set<Scorer> parseScorers(CommandSender sender, String wildcardTargetStr) throws SelectorSyntaxException {
        return parseScorers(sender, wildcardTargetStr, null);
    }

    private static Set<Scorer> parseScorers(CommandSender sender, String wildcardTargetStr, Scoreboard scoreboard) throws SelectorSyntaxException {
        var manager = Server.getInstance().getScoreboardManager();
        Set<Scorer> scorers = new HashSet<>();
        EntityPlayer player;
        if (wildcardTargetStr.equals("*")) {
            if (scoreboard != null) {
                scorers.addAll(scoreboard.getLines().keySet());
                return scorers;
            }

            for (var sb : manager.getScoreboards().values()) {
                scorers.addAll(sb.getLines().keySet());
            }

            return scorers;
        }

        if (EntitySelectorAPI.getAPI().checkValid(wildcardTargetStr)) {
            scorers = EntitySelectorAPI.getAPI()
                    .matchEntities(sender, wildcardTargetStr)
                    .stream()
                    .map(e -> e instanceof EntityPlayer p && p.isActualPlayer() ?
                            new PlayerScorer(p.getController()) :
                            new EntityScorer(e))
                    .collect(Collectors.toSet());
            return scorers;
        }

        if ((player = Server.getInstance().getPlayerManager().getOnlinePlayerByName(wildcardTargetStr).getControlledEntity()) != null) {
            scorers.add(new PlayerScorer(player.getController()));
        } else {
            scorers.add(new FakeScorer(wildcardTargetStr));
        }

        return scorers;
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                // Objectives
                .key("objectives")
                .key("add")
                .str("objective")
                .enums("criteria", "dummy")
                .str("displayName").optional()
                .exec(context -> {
                    var manager = Server.getInstance().getScoreboardManager();
                    String objectiveName = context.getResult(2);
                    if (manager.contain(objectiveName)) {
                        context.addError("%" + TrKeys.MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_ALREADYEXISTS, objectiveName);
                        return context.fail();
                    }

                    String criteriaName = context.getResult(3);
                    String displayName = context.getResult(4);
                    if (displayName.isEmpty()) displayName = objectiveName;
                    manager.add(new Scoreboard(objectiveName, displayName, criteriaName, SortOrder.ASCENDING));
                    context.addOutput(TrKeys.MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_SUCCESS, objectiveName);
                    return context.success();
                })
                .up(4)
                .key("setdisplay")
                .enums("displaySlot", "sidebar", "list")
                .str("objective").optional()
                .enums("sortOrder", "ascending", new String[]{"ascending", "descending"}).optional()
                .exec(context -> {
                    var manager = Server.getInstance().getScoreboardManager();
                    String slotName = context.getResult(2);
                    var slot = slotName.equals("list") ? DisplaySlot.LIST : DisplaySlot.SIDEBAR;
                    String objectiveName = context.getResult(3);
                    if (objectiveName.isEmpty()) {
                        manager.clearDisplaySlot(slot);
                        context.addOutput(TrKeys.MC_COMMANDS_SCOREBOARD_OBJECTIVES_SETDISPLAY_SUCCESSCLEARED, slotName);
                        return context.success();
                    }

                    if (!manager.contain(objectiveName)) {
                        context.addError("%" + TrKeys.MC_COMMANDS_SCOREBOARD_OBJECTIVENOTFOUND, objectiveName);
                        return context.fail();
                    }

                    var scoreboard = manager.get(objectiveName);
                    var orderName = context.getResult(4);
                    var order = orderName.equals("ascending") ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    scoreboard.setSortOrder(order);
                    manager.setDisplaySlot(slot, scoreboard);
                    context.addOutput(TrKeys.MC_COMMANDS_SCOREBOARD_OBJECTIVES_SETDISPLAY_SUCCESSSET, slotName, objectiveName);
                    return context.success();
                })
                .root()
                // Players
                .key("players")
                .enums("action", "add", "remove", "set")
                .wildcardTarget("player")
                // TODO: soft enum
                .str("targetObjective")
                .intNum("count")
                .exec(context -> {
                    var manager = Server.getInstance().getScoreboardManager();
                    String action = context.getResult(1);
                    String wildcardTargetStr = context.getResult(2);
                    String objectiveName = context.getResult(3);
                    int score = context.getResult(4);
                    if (!manager.contain(objectiveName)) {
                        context.addError("%" + TrKeys.MC_COMMANDS_SCOREBOARD_OBJECTIVENOTFOUND, objectiveName);
                        return context.fail();
                    }

                    var scoreboard = manager.get(objectiveName);
                    Set<Scorer> scorers;
                    try {
                        scorers = parseScorers(context.getSender(), wildcardTargetStr, scoreboard);
                    } catch (SelectorSyntaxException e) {
                        context.addSyntaxError(2);
                        return context.fail();
                    }

                    if (scorers.isEmpty()) {
                        context.addError("%" + TrKeys.MC_COMMANDS_SCOREBOARD_PLAYERS_LIST_EMPTY);
                        return context.fail();
                    }

                    int scorerCount = scorers.size();
                    switch (action) {
                        case "add" -> {
                            for (Scorer scorer : scorers) {
                                var current = scoreboard.getLines().get(scorer);
                                if (current == null) {
                                    scoreboard.addLine(new ScoreboardLine(scoreboard, scorer, score));
                                } else current.addScore(score);
                            }

                            if (scorerCount == 1) {
                                var scorer = scorers.iterator().next();
                                context.addOutput(TrKeys.MC_COMMANDS_SCOREBOARD_PLAYERS_ADD_SUCCESS, String.valueOf(score), objectiveName, scorer.getName(), String.valueOf(scoreboard.getLines().get(scorer).getScore()));
                            } else
                                context.addOutput(TrKeys.MC_COMMANDS_SCOREBOARD_PLAYERS_ADD_MULTIPLE_SUCCESS, String.valueOf(score), objectiveName, String.valueOf(scorerCount));

                            return context.success();
                        }
                        case "remove" -> {
                            for (Scorer scorer : scorers) {
                                var current = scoreboard.getLines().get(scorer);
                                if (current == null) {
                                    current = new ScoreboardLine(scoreboard, scorer, -score);
                                    scoreboard.addLine(current);
                                } else current.removeScore(score);
                            }

                            if (scorerCount == 1) {
                                var scorer = scorers.iterator().next();
                                context.addOutput(TrKeys.MC_COMMANDS_SCOREBOARD_PLAYERS_REMOVE_SUCCESS, String.valueOf(score), objectiveName, scorer.getName(), String.valueOf(scoreboard.getLines().get(scorer).getScore()));
                            } else
                                context.addOutput(TrKeys.MC_COMMANDS_SCOREBOARD_PLAYERS_REMOVE_MULTIPLE_SUCCESS, String.valueOf(score), objectiveName, String.valueOf(scorerCount));

                            return context.success();
                        }
                        case "set" -> {
                            for (Scorer scorer : scorers) {
                                var current = scoreboard.getLines().get(scorer);
                                if (current == null) {
                                    scoreboard.addLine(new ScoreboardLine(scoreboard, scorer, score));
                                } else current.setScore(score);
                            }

                            if (scorerCount == 1) {
                                var scorer = scorers.iterator().next();
                                context.addOutput(TrKeys.MC_COMMANDS_SCOREBOARD_PLAYERS_SET_SUCCESS, objectiveName, scorer.getName(), String.valueOf(score));
                            } else
                                context.addOutput(TrKeys.MC_COMMANDS_SCOREBOARD_PLAYERS_SET_MULTIPLE_SUCCESS, objectiveName, String.valueOf(scorerCount), String.valueOf(score));

                            return context.success();
                        }
                    }

                    return context.fail();
                });
    }
}
