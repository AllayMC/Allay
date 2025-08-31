package org.allaymc.api.command.selector.args;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Sets;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Similar to {@link CachedSimpleSelectorArgument}, but designed for filter mode. No further explanation is provided here.<p/>
 *
 * @see CachedSimpleSelectorArgument
 */
public abstract class CachedFilterSelectorArgument implements SelectorArgument {
    private final Cache<Set<String>, Function<List<Entity>, List<Entity>>> cache;

    public CachedFilterSelectorArgument() {
        this.cache = provideCacheService();
    }

    @Override
    public Function<List<Entity>, List<Entity>> getFilter(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException {
        var value = cache.getIfPresent(Sets.newHashSet(arguments));
        if (value == null) {
            value = cache(selectorType, sender, basePos, arguments);
            cache.put(Sets.newHashSet(arguments), value);
        }
        return value;
    }

    @Override
    public boolean isFilter() {
        return true;
    }

    /**
     * Computes and returns a filter function if it is not found in the cache.
     * <p>
     * This method must be implemented by subclasses to define how the filter function is
     * generated based on the provided arguments.
     *
     * @param selectorType the type of selector being used
     * @param sender       the command sender invoking the selector
     * @param basePos      the base position for relative coordinates (if required)
     * @param arguments    the arguments used to compute the filter
     *
     * @return a function that filters a list of entities
     *
     * @throws SelectorSyntaxException if there is an error parsing the arguments
     */
    protected abstract Function<List<Entity>, List<Entity>> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException;

    /**
     * Provides the cache service used to store filter functions.
     * <p>
     * By default, this method creates a cache with a maximum size of 65,535 entries and an
     * expiration time of 1 minute after the last access. Subclasses may override this method
     * to provide their own cache configurations if necessary.
     *
     * @return a {@code Cache<Set<String>, Function<List<Entity>, List<Entity>>>} to store filter functions
     */
    protected Cache<Set<String>, Function<List<Entity>, List<Entity>>> provideCacheService() {
        return Caffeine.newBuilder().maximumSize(65535).expireAfterAccess(1, TimeUnit.MINUTES).build();
    }
}
