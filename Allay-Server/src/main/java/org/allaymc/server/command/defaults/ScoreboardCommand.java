package org.allaymc.server.command.defaults;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
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
 * Allay Project 2024/2/28
 *
 * @author daoge_cmd
 */
public class ScoreboardCommand extends SimpleCommand {

    public ScoreboardCommand() {
        super("scoreboard", TrKeys.M_COMMANDS_SCOREBOARD_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                // Objectives
                .key("objectives")
                .key("add")
                .str("objective")
                .enums("criteria", "dummy")
                .str("displayName")
                .optional()
                .exec(context -> {
                    var service = Server.getInstance().getScoreboardService();
                    String objectiveName = context.getThirdResult();
                    if (service.contain(objectiveName)) {
                        context.addError("%" + TrKeys.M_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_ALREADYEXISTS, objectiveName);
                        return context.fail();
                    }
                    String criteriaName = context.getFourthResult();
                    String displayName = context.getFifthResult();
                    if (displayName.isEmpty()) displayName = objectiveName;
                    service.add(new Scoreboard(objectiveName, displayName, criteriaName, SortOrder.ASCENDING));
                    context.addOutput(TrKeys.M_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_SUCCESS, objectiveName);
                    return context.success();
                })
                .up(4)
                .key("setdisplay")
                .enums("displaySlot", "sidebar", "list")
                .str("objective")
                .optional()
                .enums("sortOrder", "ascending", new String[]{"ascending", "descending"})
                .optional()
                .exec(context -> {
                    var service = Server.getInstance().getScoreboardService();
                    String slotName = context.getThirdResult();
                    var slot = slotName.equals("list") ? DisplaySlot.LIST : DisplaySlot.SIDEBAR;
                    String objectiveName = context.getFourthResult();
                    if (objectiveName.isEmpty()) {
                        service.clearDisplaySlot(slot);
                        context.addOutput(TrKeys.M_COMMANDS_SCOREBOARD_OBJECTIVES_SETDISPLAY_SUCCESSCLEARED, slotName);
                        return context.success();
                    }
                    if (!service.contain(objectiveName)) {
                        context.addError("%" + TrKeys.M_COMMANDS_SCOREBOARD_OBJECTIVENOTFOUND, objectiveName);
                        return context.fail();
                    }
                    var scoreboard = service.get(objectiveName);
                    var orderName = context.getFifthResult();
                    var order = orderName.equals("ascending") ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    scoreboard.setSortOrder(order);
                    service.setDisplaySlot(slot, scoreboard);
                    context.addOutput(TrKeys.M_COMMANDS_SCOREBOARD_OBJECTIVES_SETDISPLAY_SUCCESSSET, slotName, objectiveName);
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
                    var service = Server.getInstance().getScoreboardService();
                    String action = context.getSecondResult();
                    String wildcardTargetStr = context.getThirdResult();
                    String objectiveName = context.getFourthResult();
                    int score = context.getFifthResult();
                    if (!service.contain(objectiveName)) {
                        context.addError("%" + TrKeys.M_COMMANDS_SCOREBOARD_OBJECTIVENOTFOUND, objectiveName);
                        return context.fail();
                    }
                    var scoreboard = service.get(objectiveName);
                    Set<Scorer> scorers;
                    try {
                        scorers = parseScorers(context.getSender(), wildcardTargetStr, scoreboard);
                    } catch (SelectorSyntaxException e) {
                        context.addSyntaxError(2);
                        return context.fail();
                    }
                    if (scorers.isEmpty()) {
                        context.addError("%" + TrKeys.M_COMMANDS_SCOREBOARD_PLAYERS_LIST_EMPTY);
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
                                context.addOutput(TrKeys.M_COMMANDS_SCOREBOARD_PLAYERS_ADD_SUCCESS, String.valueOf(score), objectiveName, scorer.getName(), String.valueOf(scoreboard.getLines().get(scorer).getScore()));
                            } else context.addOutput(TrKeys.M_COMMANDS_SCOREBOARD_PLAYERS_ADD_MULTIPLE_SUCCESS, String.valueOf(score), objectiveName, String.valueOf(scorerCount));
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
                                context.addOutput(TrKeys.M_COMMANDS_SCOREBOARD_PLAYERS_REMOVE_SUCCESS, String.valueOf(score), objectiveName, scorer.getName(), String.valueOf(scoreboard.getLines().get(scorer).getScore()));
                            } else context.addOutput(TrKeys.M_COMMANDS_SCOREBOARD_PLAYERS_REMOVE_MULTIPLE_SUCCESS, String.valueOf(score), objectiveName, String.valueOf(scorerCount));
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
                                context.addOutput(TrKeys.M_COMMANDS_SCOREBOARD_PLAYERS_SET_SUCCESS, objectiveName, scorer.getName(), String.valueOf(score));
                            } else context.addOutput(TrKeys.M_COMMANDS_SCOREBOARD_PLAYERS_SET_MULTIPLE_SUCCESS, objectiveName, String.valueOf(scorerCount), String.valueOf(score));
                            return context.success();
                        }
                    }
                    return context.fail();
                });
    }

    private static Set<Scorer> parseScorers(CommandSender sender, String wildcardTargetStr) throws SelectorSyntaxException {
        return parseScorers(sender, wildcardTargetStr, null);
    }

    private static Set<Scorer> parseScorers(CommandSender sender, String wildcardTargetStr, Scoreboard scoreboard) throws SelectorSyntaxException {
        var service = Server.getInstance().getScoreboardService();
        Set<Scorer> scorers = new HashSet<>();
        EntityPlayer player;
        if (wildcardTargetStr.equals("*")) {
            if (scoreboard != null) {
                scorers.addAll(scoreboard.getLines().keySet());
                return scorers;
            }
            for (var sb : service.getScoreboards().values()) {
                scorers.addAll(sb.getLines().keySet());
            }
            return scorers;
        }
        if (EntitySelectorAPI.getAPI().checkValid(wildcardTargetStr)) {
            scorers = EntitySelectorAPI.getAPI()
                    .matchEntities(sender, wildcardTargetStr)
                    .stream()
                    .map(e -> e instanceof EntityPlayer p ?
                            new PlayerScorer(p) :
                            new EntityScorer(e))
                    .collect(Collectors.toSet());
            return scorers;
        }
        if ((player = Server.getInstance().getOnlinePlayerByName(wildcardTargetStr)) != null) {
            scorers.add(new PlayerScorer(player));
        } else {
            scorers.add(new FakeScorer(wildcardTargetStr));
        }
        return scorers;
    }
}
