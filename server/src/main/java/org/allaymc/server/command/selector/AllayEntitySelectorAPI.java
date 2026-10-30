package org.allaymc.server.command.selector;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.selector.SelectorArgument;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.server.command.selector.args.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import static org.allaymc.api.command.selector.SelectorType.*;

/**
 * {@code AllayEntitySelectorAPI} resolves selector text into live entity
 * references using registered argument handlers and the sender's execution
 * dimension. It caches structural checks and parsed argument maps, then copies
 * the sender location for per-selection coordinate changes. Handler
 * registration maintains ascending priority order so origin coordinates precede
 * box ranges and later entity filters.
 *
 * <p> Each handler receives the surviving list or a predicate applied to it.
 * Empty candidate lists return immediately, so later argument values may never
 * be interpreted. Explicit list filters run only for present keys, while
 * predicate handlers may synthesize defaults from the full argument map. Final
 * random or nearest-player reduction occurs after those handlers. Cached parsed
 * maps are shared across instances, and registration does not invalidate them
 * when an instance's handler set changes.
 *
 * @author daoge_cmd
 */
public class AllayEntitySelectorAPI implements EntitySelectorAPI {

    /**
     * {@code ARGS_CACHE} retains parsed key/value lists by complete selector
     * text for up to one minute after access. All instances share these maps,
     * and argument registration does not invalidate them.
     */
    private static final Cache<String, Map<String, List<String>>> ARGS_CACHE = Caffeine.newBuilder()
            .maximumSize(65535)
            .expireAfterAccess(1, TimeUnit.MINUTES)
            .build();
    /**
     * {@code MATCHES_CACHE} retains structural selector checks by complete
     * token for up to one minute after access. A cached success does not
     * validate argument names or values.
     */
    private static final Cache<String, Boolean> MATCHES_CACHE = Caffeine.newBuilder()
            .maximumSize(65535)
            .expireAfterAccess(1, TimeUnit.MINUTES)
            .build();

    /**
     * {@code registry} maps each accepted key to one handler and rejects
     * duplicate registration. {@code orderedArgs} contains those same handlers
     * sorted for execution; callers must coordinate registration with matching.
     */
    private final Map<String, SelectorArgument> registry = new HashMap<>();
    /**
     * {@code orderedArgs} retains handlers in ascending priority order. Stable
     * sorting preserves registration order for equal priorities, which affects
     * handlers that transform an already filtered list.
     */
    private final List<SelectorArgument> orderedArgs = new ArrayList<>();

    /**
     * Creates a selector implementation and registers its built-in argument
     * handlers.
     */
    public AllayEntitySelectorAPI() {
        registerDefaultArguments();
    }

    /**
     * Registers built-in origin, range, count, identity, rotation, and score
     * handlers.
     */
    private void registerDefaultArguments() {
        registerArgument(new X());
        registerArgument(new Y());
        registerArgument(new Z());
        registerArgument(new DX());
        registerArgument(new DY());
        registerArgument(new DZ());
        registerArgument(new C());
        registerArgument(new R());
        registerArgument(new RM());
        registerArgument(new Name());
        registerArgument(new Tag());
        registerArgument(new L());
        registerArgument(new LM());
        registerArgument(new M());
        registerArgument(new Type());
        registerArgument(new RX());
        registerArgument(new RXM());
        registerArgument(new RY());
        registerArgument(new RYM());
        registerArgument(new Scores());
    }

    /**
     * Returns entities selected from the sender's execution dimension, or the
     * represented entity for {@code @s}. Structural failures raise
     * {@code SelectorSyntaxException}; uncached argument parsing also rejects
     * unknown keys. A static cached map can bypass that check for another
     * instance's different handler registry. Handler failures are wrapped with
     * the active key, and coordinate changes use a copy of the sender's
     * location.
     *
     * <p>
     * Handlers run in priority order against the current survivors. An empty
     * list stops processing, so successful empty results do not establish that
     * every argument value was valid. Predicate defaults apply only to absent
     * keys; list filters require explicit values. {@code @r} finally chooses
     * one survivor, while {@code @p} chooses the nearest surviving player when
     * more than one remains. Result entities are shared live objects rather
     * than entity snapshots.
     */
    @Override
    public List<Entity> matchEntities(CommandSender sender, String token) throws SelectorSyntaxException {
        var cachedMatches = MATCHES_CACHE.getIfPresent(token);
        if (cachedMatches != null && !cachedMatches)
            throw new SelectorSyntaxException("Malformed entity selector token");

        var matcher = ENTITY_SELECTOR.matcher(token);
        if (!matcher.matches()) {
            MATCHES_CACHE.put(token, false);
            throw new SelectorSyntaxException("Malformed entity selector token");
        }

        // Parsed maps depend only on selector text after registration checks. Reuse
        // that structure while each invocation still filters current live entities.
        var arguments = ARGS_CACHE.getIfPresent(token);
        if (arguments == null) {
            arguments = parseArgumentMap(matcher.group(2));
            ARGS_CACHE.put(token, arguments);
        }

        // Coordinate handlers mutate the selection origin, so copy the sender's
        // location before exposing it to handlers and leave the sender's location intact.
        var senderLocation = new Location3d(sender.getCommandExecuteLocation());
        var selectorType = parseSelectorType(matcher.group(1));
        List<Entity> entities;
        if (selectorType != SELF) {
            entities = Lists.newArrayList(senderLocation.dimension().getEntities().values());
        } else {
            if (sender.isEntity())
                entities = Lists.newArrayList(sender.asEntity());
            else return Lists.newArrayList();
        }

        // These selector modes promise player candidates before custom handlers run.
        // Random selection remains broader here because its type handler supplies the
        // player default only when the selector omits an explicit type argument.
        switch (selectorType) {
            case ALL_PLAYERS, NEAREST_PLAYER -> entities.removeIf(e -> !(e instanceof EntityPlayer));
            default -> {
            }
        }

        if (entities.isEmpty()) return entities;
        for (var arg : orderedArgs) {
            try {
                if (!arg.isFilter()) {
                    Predicate<Entity> predicate;
                    if (arguments.containsKey(arg.getKeyName()))
                        predicate = arg.getPredicate(selectorType, sender, senderLocation, arguments.get(arg.getKeyName()).toArray(new String[0]));
                    else if (arg.getDefaultValue(arguments, selectorType, sender) != null)
                        predicate = arg.getPredicate(selectorType, sender, senderLocation, arg.getDefaultValue(arguments, selectorType, sender));
                    else continue;
                    if (predicate == null) continue;
                    entities.removeIf(entity -> !predicate.test(entity));
                } else {
                    if (arguments.containsKey(arg.getKeyName()))
                        entities = arg.getFilter(selectorType, sender, senderLocation, arguments.get(arg.getKeyName()).toArray(new String[0])).apply(entities);
                    else continue;
                }
            } catch (Throwable t) {
                // Preserve the active argument key when a handler fails so command
                // feedback can associate its cause with the selector value being parsed.
                throw new SelectorSyntaxException("Error while parsing selector argument: " + arg.getKeyName(), t);
            }
            if (entities.isEmpty()) return entities;
        }

        // Apply the selector's random reduction after explicit filters, so only
        // survivors can be chosen and an explicit type can admit non-player entities.
        if (selectorType == RANDOM_PLAYER && !entities.isEmpty()) {
            var index = ThreadLocalRandom.current().nextInt(entities.size()) + 1;
            Entity currentEntity = null;
            int i = 1;
            for (var localCurrent : entities) {
                if (i == index) {
                    currentEntity = localCurrent;
                    break;
                }
                i++;
            }

            return Lists.newArrayList(currentEntity);
        }
        // Nearest-player reduction uses the adjusted origin after all argument
        // handlers, so coordinate overrides participate in the final distance choice.
        if (selectorType == NEAREST_PLAYER && entities.size() != 1) {
            Entity nearest = null;
            var min = Double.MAX_VALUE;
            for (var entity : entities) {
                var distanceSquared = 0.0;
                if ((distanceSquared = senderLocation.distanceSquared(entity.getLocation())) < min) {
                    min = distanceSquared;
                    nearest = entity;
                }
            }

            entities = Lists.newArrayList(nearest);
        }

        return entities;
    }

    /**
     * Returns the cached structural regex result for the supplied token. This
     * method does not parse argument keys or values, so a structurally accepted
     * token can still fail during {@code matchEntities}.
     */
    @Override
    public boolean checkValid(String token) {
        return MATCHES_CACHE.get(token, k -> ENTITY_SELECTOR.matcher(token).matches());
    }

    /**
     * Registers a new key and stably sorts handlers by ascending priority.
     * Duplicate keys return {@code false} without replacement; successful
     * registration does not invalidate static parsed-map caches or synchronize
     * with ongoing selections.
     */
    @Override
    public boolean registerArgument(SelectorArgument argument) {
        if (registry.containsKey(argument.getKeyName())) {
            return false;
        }

        registry.put(argument.getKeyName(), argument);
        orderedArgs.add(argument);
        Collections.sort(orderedArgs);
        return true;
    }

    /**
     * Parses an optional selector argument suffix into repeated values grouped
     * by key. {@code null} or empty input creates an empty map; unknown keys
     * raise {@code SelectorSyntaxException}, and a key without {@code =}
     * receives an empty value.
     *
     * <p> The first {@code =} separates the key from its remaining value,
     * preserving later equals signs inside that value.
     * {@code separateArguments} protects commas between braces before this
     * split. The returned lists preserve each key's occurrence order, while the
     * map itself does not preserve key order; handler priority determines
     * execution order instead.
     */
    protected Map<String, List<String>> parseArgumentMap(String inputArguments) throws SelectorSyntaxException {
        Map<String, List<String>> args = new HashMap<>();
        if (inputArguments == null || inputArguments.isEmpty()) {
            return args;
        }

        for (var arg : separateArguments(inputArguments)) {
            var split = AllayStringUtils.fastSplit(arg, ARGUMENT_JOINER, 2);

            var argName = split.get(0);
            if (!registry.containsKey(argName)) {
                throw new SelectorSyntaxException("Unknown selector argument: " + argName);
            }

            args.computeIfAbsent(argName, k -> new ArrayList<>()).add(split.size() > 1 ? split.get(1) : "");
        }

        return args;
    }

    /**
     * Splits comma-separated argument entries while retaining commas inside one
     * brace-delimited region. The parser tracks only whether it is inside
     * braces; it does not count nesting, validate balanced braces, or interpret
     * quoted commas.
     *
     * <p> This limited state keeps a score expression's internal commas in one
     * argument value until its own handler parses them. Empty entries,
     * including a trailing comma's empty entry, are omitted from the result.
     * Each retained entry is an unchanged substring, so its key/value split
     * still sees the original argument text.
     */
    protected List<String> separateArguments(String input) {
        List<String> result = new ArrayList<>();
        int start = 0;
        boolean inBraces = false;

        for (int i = 0; i < input.length(); i++) {
            var ch = input.charAt(i);
            if (ch == '{') inBraces = true;
            if (ch == '}') inBraces = false;
            // A comma inside a brace region belongs to that argument's value,
            // such as a score expression, rather than to the outer selector list.
            if (ch == ',' && !inBraces) {
                result.add(input.substring(start, i));
                start = i + 1;
            }
        }

        if (start < input.length()) {
            result.add(input.substring(start));
        }

        return result.stream().filter(s -> !s.isEmpty()).toList();
    }
}
