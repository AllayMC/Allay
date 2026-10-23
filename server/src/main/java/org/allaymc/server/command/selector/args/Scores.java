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
 * {@code Scores} implements the selector key for combining scoreboard
 * constraints across objectives. Each compiled condition identifies an
 * objective, inclusive integer bounds, and optional reversal. Entity evaluation
 * chooses player-account scoring for an actual player and entity scoring for
 * other candidates; missing objectives or missing score lines reject the
 * candidate even when the condition is reversed.
 *
 * <p> The text compiler currently passes separator literals as the source
 * argument to {@code fastSplit}, whose signature expects source text first.
 * Ordinary objective assignments therefore fail before a predicate is produced;
 * the condition evaluator and range helper describe the downstream
 * representation but do not establish that the current text compiler accepts
 * that grammar. Cached predicates, when created, evaluate current scoreboard
 * values rather than storing score snapshots.
 *
 * @author daoge_cmd
 */
public class Scores extends CachedSimpleSelectorArgument {

    /**
     * {@code SCORE_SEPARATOR} is the comma token for objective entries. The
     * current compiler passes this token as {@code fastSplit} source text
     * rather than its delimiter.
     */
    protected static final String SCORE_SEPARATOR = ",";
    /**
     * {@code SCORE_JOINER} is the equals token between an objective and
     * condition. The current compiler likewise supplies it as the split source
     * rather than delimiter.
     */
    protected static final String SCORE_JOINER = "=";
    /**
     * {@code SCORE_SCOPE_SEPARATOR} identifies the two-dot range token. The
     * range helper detects it in input but then passes the token as the split
     * source.
     */
    protected static final String SCORE_SCOPE_SEPARATOR = "..";

    /**
     * Attempts to compile one brace-wrapped score argument into a conjunction
     * of objective conditions. The outer characters are removed before
     * splitting, and each parsed entry would supply an objective name, an
     * optional reversal marker, and a score condition.
     *
     * <p> The current {@code fastSplit} calls reverse source and delimiter
     * positions, so an ordinary objective assignment raises a syntax error
     * instead of creating the expected predicate. Input shorter than two
     * characters can fail during substring extraction. Failures propagate to
     * the enclosing selector handler wrapper; a predicate result, when
     * produced, requires every condition to accept the candidate.
     */
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

    /**
     * Creates an exact-score condition or attempts to parse a two-ended
     * inclusive range. A single number sets both bounds to that value. Range
     * text currently calls {@code fastSplit} with the literal separator as
     * source, so ordinary numeric ranges fail the expected-two-parts check.
     *
     * <p> If a split yields two parts, an empty first or last part expands to
     * the corresponding integer limit. The method retains the supplied
     * objective name and reversal flag in the condition; numeric conversion
     * failures propagate without substituting a valid score.
     */
    private ScoreCondition parseScoreCondition(String objectiveName, String condition, boolean reversed) throws SelectorSyntaxException {
        if (condition.contains(SCORE_SCOPE_SEPARATOR)) {
            var scope = AllayStringUtils.fastSplit(SCORE_SCOPE_SEPARATOR, condition);
            if (scope.size() != 2) {
                throw new SelectorSyntaxException("Invalid score range: " + condition);
            }

            // Empty endpoints represent open score bounds using the complete
            // integer domain; an absent endpoint must not become an exact zero bound.
            var min = scope.getFirst().isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(scope.getFirst());
            var max = scope.getLast().isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(scope.getLast());
            return new ScoreCondition(objectiveName, min, max, reversed);
        } else {
            var score = Integer.parseInt(condition);
            return new ScoreCondition(objectiveName, score, score, reversed);
        }
    }

    /**
     * Returns {@code "scores"} as the registration key for scoreboard condition
     * filtering.
     */
    @Override
    public String getKeyName() {
        return "scores";
    }

    /**
     * Returns priority five so score checks follow the other built-in selector
     * handlers.
     */
    @Override
    public int getPriority() {
        return 5;
    }

    /**
     * {@code ScoreCondition} represents one objective's inclusive score bounds
     * and optional reversed comparison. The evaluator rejects a missing
     * objective or score line before applying reversal to an existing score's
     * range membership.
     *
     * @param objectiveName exact scoreboard objective name
     * @param min inclusive lower score bound
     * @param max inclusive upper score bound
     * @param reversed whether existing-score range membership is inverted
     */
    protected record ScoreCondition(String objectiveName, int min, int max, boolean reversed) {
        /**
         * Returns whether an entity has an existing score satisfying this
         * condition. A missing objective or missing line returns {@code false}
         * before reversal, so negation does not make an absent score match.
         *
         * <p> Actual players use a {@code PlayerScorer} associated with their
         * controller, while fake players and other entities use
         * {@code EntityScorer}. The method reads the current line value and
         * applies reversal only to the inclusive range test, preserving the
         * distinction between an absent score and a present score outside the
         * range.
         */
        boolean test(Entity entity) {
            var scoreboard = Server.getInstance().getScoreboardManager().get(objectiveName);
            // An absent objective supplies no comparable value. Reversal applies
            // only to an existing score, so missing data rejects this condition too.
            if (scoreboard == null) {
                return false;
            }

            var scorer = entity instanceof EntityPlayer player && player.isActualPlayer() ? new PlayerScorer(player.getController()) : new EntityScorer(entity);
            // A known objective can still lack this scorer's line; keep that absence
            // distinct from an existing zero score before applying the reversed bound.
            if (!scoreboard.containLine(scorer)) {
                return false;
            }

            var value = scoreboard.getLine(scorer).getScore();
            return (value >= min && value <= max) != reversed;
        }
    }
}
