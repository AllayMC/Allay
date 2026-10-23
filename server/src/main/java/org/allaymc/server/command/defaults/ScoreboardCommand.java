package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
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
 * {@code ScoreboardCommand} manages objectives, display slots, and scores for
 * wildcard, selector, or named scorers. Scorer identity determines which line is
 * updated: actual selected players use {@link PlayerScorer}, while other selected
 * entities use {@link EntityScorer}. The wildcard {@code "*"} uses existing
 * scorer keys from one objective or from all objectives, depending on the helper
 * overload used.
 *
 * <p>
 * Objective creation and display changes validate the selected objective before
 * updating the manager. Score mutations visit the resolved scorer set. Missing
 * lines are requested with {@code score} for {@code add} and {@code set}, or
 * {@code -score} for {@code remove}. Completed mutations have no rollback if
 * a later operation fails. Literal-name
 * resolution currently dereferences a missing player before it can construct
 * a {@link FakeScorer}.
 *
 * <p>
 * Scoreboard events can cancel line insertion or score changes, but the
 * handlers ignore their boolean results. Single-scorer addition and removal
 * read the line again for feedback; cancellation of a missing line's insertion
 * can therefore cause a {@code null} dereference. Bulk changes and setting can still
 * report success after a cancelled request.
 *
 * @author daoge_cmd
 */
public class ScoreboardCommand extends Command {

    /**
     * Creates the {@code /scoreboard} command with its built-in authorization permission.
     */
    public ScoreboardCommand() {
        super("scoreboard", TrKeys.MC_COMMANDS_SCOREBOARD_DESCRIPTION, Permissions.COMMAND_SCOREBOARD);
    }

    /**
     * Resolves scorer identities without restricting the wildcard to one objective.
     * {@code "*"} collects existing scorer keys from every registered scoreboard;
     * selectors and literal names follow the same resolution path as the
     * objective-specific overload.
     */
    private static Set<Scorer> parseScorers(CommandSender sender, String wildcardTargetStr) throws SelectorSyntaxException {
        return parseScorers(sender, wildcardTargetStr, null);
    }

    /**
     * Resolves a target expression into a distinct set of scorer identities.
     * {@code "*"} copies existing keys from {@code scoreboard}, or every
     * registered objective when {@code scoreboard} is {@code null}. Selectors
     * resolve relative to {@code sender} and distinguish actual player scorers
     * from other entity scorers.
     *
     * <p>
     * Literal names are looked up as online players before fake-name handling.
     * The current lookup dereferences its result before checking the controlled
     * entity, so an absent player throws instead of becoming a fake scorer.
     *
     * @throws NullPointerException if a literal name has no online player
     * @throws SelectorSyntaxException if selector matching rejects the expression
     */
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

        if ((player = Server.getInstance().getPlayerManager().getPlayerByName(wildcardTargetStr).getControlledEntity()) != null) {
            scorers.add(new PlayerScorer(player.getController()));
        } else {
            scorers.add(new FakeScorer(wildcardTargetStr));
        }

        return scorers;
    }

    /**
     * Installs objective creation, display selection, and player-score mutation.
     * Empty display names use the objective name, omitted display objectives
     * clear the slot, and sorting defaults to ascending. Objective creation
     * accepts only {@code "dummy"} criteria. Score actions resolve
     * all scorers before mutation and retain completed changes after later
     * failures.
     *
     * <p>
     * The handlers ignore cancellation results from line insertion and score
     * changes. Single-scorer addition or removal reads the resulting line for
     * feedback and can throw when a cancelled insertion leaves it absent;
     * other paths can report success even when requests were cancelled.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
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
                .key("players")
                .enums("action", "add", "remove", "set")
                .wildcardTarget("player")
                // TODO: Offer registered objective names through a soft enum
                // when dynamic scoreboard suggestions are implemented.
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
                        // Selector resolution fails before any score
                        // mutations. Report the target argument as invalid
                        // and retain all existing lines.
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
