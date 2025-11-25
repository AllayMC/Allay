package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.scoreboard.scorer.EntityScorer;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author daoge_cmd
 */
public class Scores extends CachedSimpleSelectorArgument {

    protected static final String SCORE_SEPARATOR = ",";
    protected static final String SCORE_JOINER = "=";
    protected static final String SCORE_SCOPE_SEPARATOR = "..";

    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());

        List<ScoreCondition> conditions = new ArrayList<>();
        for (var entry : AllayStringUtils.fastSplit(SCORE_SEPARATOR, arguments[0].substring(1, arguments[0].length() - 1))) {
            if (entry.isEmpty()) {
                throw new SelectorSyntaxException("Empty score entry is not allowed in selector!");
            }

            var splittedEntry = AllayStringUtils.fastSplit(SCORE_JOINER, entry, 2);
            if (splittedEntry.size() != 2) {
                throw new SelectorSyntaxException("Invalid score entry format: " + entry);
            }

            String objectiveName = splittedEntry.get(0);
            String condition = splittedEntry.get(1);

            var reversed = ParseUtils.checkReversed(condition);
            if (reversed) {
                condition = condition.substring(1);
            }

            conditions.add(parseScoreCondition(objectiveName, condition, reversed));
        }

        return entity -> conditions.stream().allMatch(condition -> condition.test(entity));
    }

    private ScoreCondition parseScoreCondition(String objectiveName, String condition, boolean reversed) throws SelectorSyntaxException {
        if (condition.contains(SCORE_SCOPE_SEPARATOR)) {
            // Parse range condition
            var scope = AllayStringUtils.fastSplit(SCORE_SCOPE_SEPARATOR, condition);
            if (scope.size() != 2) {
                throw new SelectorSyntaxException("Invalid score range: " + condition);
            }

            var min = scope.getFirst().isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(scope.getFirst());
            var max = scope.getLast().isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(scope.getLast());
            return new ScoreCondition(objectiveName, min, max, reversed);
        } else {
            // Parse single score condition
            var score = Integer.parseInt(condition);
            return new ScoreCondition(objectiveName, score, score, reversed);
        }
    }

    @Override
    public String getKeyName() {
        return "scores";
    }

    @Override
    public int getPriority() {
        return 5;
    }

    protected record ScoreCondition(String objectiveName, int min, int max, boolean reversed) {
        boolean test(Entity entity) {
            var scoreboard = Server.getInstance().getScoreboardManager().get(objectiveName);
            if (scoreboard == null) {
                return false;
            }

            var scorer = entity instanceof EntityPlayer player && player.isActualPlayer() ? new PlayerScorer(player.getController()) : new EntityScorer(entity);
            if (!scoreboard.containLine(scorer)) {
                return false;
            }

            var value = scoreboard.getLine(scorer).getScore();
            return (value >= min && value <= max) != reversed;
        }
    }
}
