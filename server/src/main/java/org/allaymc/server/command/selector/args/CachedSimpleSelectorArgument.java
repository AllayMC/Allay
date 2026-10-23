package org.allaymc.server.command.selector.args;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Sets;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.SelectorArgument;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * {@code CachedSimpleSelectorArgument} reuses parsed predicate functions keyed
 * by the set of argument strings. The key ignores value order and repeated
 * identical values, and it excludes selector type, sender, and base position.
 * Subclasses must account for those omissions when deciding whether their
 * function is safe to reuse.
 *
 * <p>
 * A cache hit returns the function currently stored for its key. A miss invokes
 * {@code cache} with the current context, then installs its result;
 * simultaneous misses can compute separately and replace one another's stored
 * functions because lookup and insertion are separate operations. The default
 * policy permits up to {@code 65535} entries and expires entries one minute
 * after access.
 */
public abstract class CachedSimpleSelectorArgument implements SelectorArgument {
    /**
     * {@code cache} retains parsed predicate functions by argument-value set.
     * Context-dependent captured values remain those of the invocation that
     * created each cached function.
     */
    private final Cache<Set<String>, Predicate<Entity>> cache;

    /**
     * Creates the handler's cache through the overridable
     * {@code provideCacheService} hook. The hook runs before subclass
     * initialization completes, so implementations cannot depend on subclass
     * fields initialized after this constructor.
     */
    public CachedSimpleSelectorArgument() {
        this.cache = provideCacheService();
    }

    /**
     * Returns the currently cached predicate for the argument-value set,
     * computing and installing one on a miss. Argument ordering and duplicates
     * do not distinguish keys. Selector type, sender, and base position are
     * passed only to a miss and cannot refresh captured state on a hit.
     *
     * <p>
     * Lookup and insertion are separate, so concurrent misses can compute
     * different functions and replace the stored entry without eviction. Each
     * hit returns whichever function its lookup observes. The subclass must
     * return a non-null cacheable function, and parse failures propagate
     * without installing a value from that invocation.
     */
    @Override
    public Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3d basePos, String... arguments) throws SelectorSyntaxException {
        var value = cache.getIfPresent(Sets.newHashSet(arguments));
        // Only argument values identify this entry. A miss supplies the current
        // context to the subclass; a hit necessarily reuses any earlier captured state.
        if (value == null) {
            value = cache(selectorType, sender, basePos, arguments);
            cache.put(Sets.newHashSet(arguments), value);
        }
        return value;
    }

    /**
     * Computes a non-null predicate for an argument-value set not currently
     * cached. Implementations must make any captured context safe for later
     * reuse because future hits with different senders or origins bypass this
     * hook.
     */
    protected abstract Predicate<Entity> cache(SelectorType selectorType, CommandSender sender, Location3dc basePos, String... arguments) throws SelectorSyntaxException;

    /**
     * Creates the default cache with at most {@code 65535} entries and
     * one-minute access expiration. Subclasses may replace the policy, but the
     * constructor calls this hook before their own initialization finishes.
     */
    protected Cache<Set<String>, Predicate<Entity>> provideCacheService() {
        return Caffeine.newBuilder().maximumSize(65535).expireAfterAccess(1, TimeUnit.MINUTES).build();
    }
}