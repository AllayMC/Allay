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
 * @author daoge_cmd
 */
public class AllayEntitySelectorAPI implements EntitySelectorAPI {

    /**
     * Pre-parsing cache for target selector text
     */
    private static final Cache<String, Map<String, List<String>>> ARGS_CACHE = Caffeine.newBuilder()
            .maximumSize(65535)
            .expireAfterAccess(1, TimeUnit.MINUTES)
            .build();
    private static final Cache<String, Boolean> MATCHES_CACHE = Caffeine.newBuilder()
            .maximumSize(65535)
            .expireAfterAccess(1, TimeUnit.MINUTES)
            .build();

    private final Map<String, SelectorArgument> registry = new HashMap<>();
    private final List<SelectorArgument> orderedArgs = new ArrayList<>();

    public AllayEntitySelectorAPI() {
        registerDefaultArguments();
    }

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

    // TODO: refactor this
    @Override
    public List<Entity> matchEntities(CommandSender sender, String token) throws SelectorSyntaxException {
        var cachedMatches = MATCHES_CACHE.getIfPresent(token);
        // First confirm from the cache that it is not an illegal selector
        if (cachedMatches != null && !cachedMatches)
            throw new SelectorSyntaxException("Malformed entity selector token");

        var matcher = ENTITY_SELECTOR.matcher(token);
        // Illegal target selector text
        if (!matcher.matches()) {
            // Record the illegal selector to the cache
            MATCHES_CACHE.put(token, false);
            throw new SelectorSyntaxException("Malformed entity selector token");
        }

        // Query if there is a pre-parsing result. If not, parse
        var arguments = ARGS_CACHE.getIfPresent(token);
        if (arguments == null) {
            arguments = parseArgumentMap(matcher.group(2));
            ARGS_CACHE.put(token, arguments);
        }

        // Get the cloned location information of the executor
        var senderLocation = new Location3d(sender.getCommandExecuteLocation());
        // Get the selector type
        var selectorType = parseSelectorType(matcher.group(1));
        // Determine the entity detection range according to the selector type first
        List<Entity> entities;
        if (selectorType != SELF) {
            entities = Lists.newArrayList(senderLocation.dimension().getEntities().values());
        } else {
            if (sender.isEntity())
                entities = Lists.newArrayList(sender.asEntity());
                // There is no entity that meets the condition.
            else return Lists.newArrayList();
        }

        // For the player type selector that is determined, exclude entities that are not players
        switch (selectorType) {
            case ALL_PLAYERS, NEAREST_PLAYER -> entities.removeIf(e -> !(e instanceof EntityPlayer));
            default -> {
            }
        }

        // There is no entity that meets the condition, return
        if (entities.isEmpty()) return entities;
        // Refer to the coordinates
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
                throw new SelectorSyntaxException("Error while parsing selector argument: " + arg.getKeyName(), t);
            }
            // There is no entity that meets the condition, return
            if (entities.isEmpty()) return entities;
        }

        // Randomly select one
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
        // Select the closest player
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

    @Override
    public boolean checkValid(String token) {
        return Boolean.TRUE.equals(MATCHES_CACHE.get(token, k -> ENTITY_SELECTOR.matcher(token).matches()));
    }

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

    protected List<String> separateArguments(String input) {
        List<String> result = new ArrayList<>();
        int start = 0;
        boolean inBraces = false;

        for (int i = 0; i < input.length(); i++) {
            var ch = input.charAt(i);
            if (ch == '{') inBraces = true;
            if (ch == '}') inBraces = false;
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
