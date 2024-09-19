package org.allaymc.server.command.selector.args;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.command.selector.args.CachedSimpleSelectorArgument;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.scoreboard.scorer.EntityScorer;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.scoreboard.scorer.Scorer;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.server.command.selector.ParseUtils;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @author daoge_cmd
 */
public class Scores extends CachedSimpleSelectorArgument {

    protected static final String SCORE_SEPARATOR = ",";
    protected static final String SCORE_JOINER = "=";
    protected static final String SCORE_SCOPE_SEPARATOR = "..";

    @Override
    protected Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3fc basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        final var conditions = new ArrayList<ScoreCondition>();
        for (String entry : AllayStringUtils.fastSplit(SCORE_SEPARATOR, arguments[0].substring(1, arguments[0].length() - 1))) {
            if (entry.isEmpty()) throw new SelectorSyntaxException("Empty score entry is not allowed in selector!");
            var splittedEntry = AllayStringUtils.fastSplit(SCORE_JOINER, entry, 2);
            String objectiveName = splittedEntry.get(0);
            String condition = splittedEntry.get(1);
            boolean reversed = ParseUtils.checkReversed(condition);
            if (reversed) condition = condition.substring(1);
            if (condition.contains("..")) {
                //条件为一个区间
                int min = Integer.MIN_VALUE;
                int max = Integer.MAX_VALUE;
                var splitScoreScope = AllayStringUtils.fastSplit(SCORE_SCOPE_SEPARATOR, condition);
                String min_str = splitScoreScope.get(0);
                if (!min_str.isEmpty()) {
                    min = Integer.parseInt(min_str);
                }
                String max_str = splitScoreScope.get(1);
                if (!max_str.isEmpty()) {
                    max = Integer.parseInt(max_str);
                }
                conditions.add(new ScoreCondition(objectiveName, min, max, reversed));
            } else {
                //条件为单个数字
                int score = Integer.parseInt(condition);
                conditions.add(new ScoreCondition(objectiveName, score, score, reversed));
            }
        }
        return entity -> conditions.stream().allMatch(condition -> condition.test(entity));
    }

    @Override
    public String getKeyName() {
        return "scores";
    }

    @Override
    public int getPriority() {
        return 5;
    }

    protected record ScoreCondition(String objectiveName, int min, int max, boolean reversed){
        boolean test(Entity entity) {
            var scoreboard = Server.getInstance().getScoreboardService().get(objectiveName);
            if (scoreboard == null) return false;
            Scorer scorer = entity instanceof EntityPlayer player ? new PlayerScorer(player) : new EntityScorer(entity);
            if (!scoreboard.containLine(scorer)) return false;
            var value = scoreboard.getLine(scorer).getScore();
            return (value >= min && value <= max) != reversed;
        }
    }
}
