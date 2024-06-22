package org.allaymc.api.command.selector.args;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Sets;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3fc;

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
    public Function<List<Entity>, List<Entity>> getFilter(SelectorType selectorType, CommandSender sender, Location3fc basePos, String... arguments) throws SelectorSyntaxException {
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
     * Called when parsing the parameters if the parsing result is not found in the cache
     */
    protected abstract Function<List<Entity>, List<Entity>> cache(SelectorType selectorType, CommandSender sender, Location3fc basePos, String... arguments) throws SelectorSyntaxException;

    /**
     * Called when initializing the cache
     * <p>
     * Override this method if you need your own cache implementation
     *
     * @return {@code Cache<Set<String>, Function<List<Entity>, List<Entity>>>}
     */
    protected Cache<Set<String>, Function<List<Entity>, List<Entity>>> provideCacheService() {
        return Caffeine.newBuilder().maximumSize(65535).expireAfterAccess(1, TimeUnit.MINUTES).build();
    }
}
