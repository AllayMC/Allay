package org.allaymc.api.command.selector.args;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Sets;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;

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
    public Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3f basePos, String... arguments) throws SelectorSyntaxException {
        var value = cache.getIfPresent(Sets.newHashSet(arguments));
        if (value == null) {
            value = cache(selectorType, sender, basePos, arguments);
            cache.put(Sets.newHashSet(arguments), value);
        }
        return value;
    }

    /**
     * Called when parsing the parameters if the parsing result is not found in the cache
     */
    protected abstract Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3fc basePos, String... arguments) throws SelectorSyntaxException;

    /**
     * Called when initializing the cache
     * <p>
     * Override this method if you need your own cache implementation
     *
     * @return {@code Cache<Set<String>, Predicate<Entity>>}
     */
    protected Cache<Set<String>, Predicate<Entity>> provideCacheService() {
        return Caffeine.newBuilder().maximumSize(65535).expireAfterAccess(1, TimeUnit.MINUTES).build();
    }
}