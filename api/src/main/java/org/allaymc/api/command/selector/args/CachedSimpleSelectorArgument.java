package org.allaymc.api.command.selector.args;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Sets;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * Cachable base class for target selector arguments
 * <p>
 * If a selector argument returns {@code List<Predicate<Entity>>} without expiration, you can inherit from this class to cache the parsing results for better performance.
 */
public abstract class CachedSimpleSelectorArgument implements SelectorArgument {
    private final Cache<Set<String>, Predicate<Entity>> cache;

    public CachedSimpleSelectorArgument() {
        this.cache = provideCacheService();
    }

    @Override
    public Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3d basePos, String... arguments) throws SelectorSyntaxException {
        var value = cache.getIfPresent(Sets.newHashSet(arguments));
        if (value == null) {
            value = cache(selectorType, sender, basePos, arguments);
            cache.put(Sets.newHashSet(arguments), value);
        }
        return value;
    }

    /**
     * Called when the predicate for a given set of arguments is not present in the cache.
     * <p>
     * Subclasses must implement this method to define how the predicate is computed based on the arguments.
     *
     * @param selectorType the type of selector (e.g., {@code @p}, {@code @e}).
     * @param sender       the command sender who issued the selector.
     * @param basePos      the base position used for relative coordinate parsing.
     * @param arguments    the arguments used for the selector.
     *
     * @return a {@code Predicate<Entity>} that filters entities.
     *
     * @throws SelectorSyntaxException if the arguments cannot be parsed.
     */
    protected abstract Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException;

    /**
     * Provides the cache service used to store predicates.
     * <p>
     * This method initializes a cache with a maximum size of 65,535 entries and an expiration time
     * of 1 minute after the last access. Subclasses may override this method if they need a different cache configuration.
     *
     * @return a {@code Cache<Set<String>, Predicate<Entity>>} for caching predicates.
     */
    protected Cache<Set<String>, Predicate<Entity>> provideCacheService() {
        return Caffeine.newBuilder().maximumSize(65535).expireAfterAccess(1, TimeUnit.MINUTES).build();
    }
}