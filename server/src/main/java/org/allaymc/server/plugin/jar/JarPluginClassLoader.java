package org.allaymc.server.plugin.jar;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * {@code JarPluginClassLoader} loads plugin classes with parent-first lookup
 * and fallback to other plugin loaders. A class available to the parent or this
 * JAR wins before any peer
 * is consulted. Peer lookup can expose classes from any registered plugin
 * loader, independently of declared plugin dependencies, and returns the
 * class defined by that loader rather than defining a second copy here.
 *
 * <p>
 * Fallback probes disable further peer traversal to prevent loaders from
 * recursively bouncing the same miss between one another. The global registry
 * uses snapshot iteration and retains every constructed loader; inherited
 * closure does not remove a loader from that registry. Registration does not
 * provide an unloading lifecycle or make a multi-loader lookup atomic.
 *
 * @author daoge_cmd
 */
public class JarPluginClassLoader extends URLClassLoader {

    /**
     * {@code LOADERS} retains constructed plugin loaders for peer fallback.
     * Entries are never removed, including when inherited {@code close} closes
     * a loader's resources.
     */
    private static final Set<JarPluginClassLoader> LOADERS = new CopyOnWriteArraySet<>();

    // Register this loader class for the JDK's per-class loading locks.
    static {
        ClassLoader.registerAsParallelCapable();
    }

    /**
     * Creates a parent-first URL loader and registers it for peer lookup.
     * Registration retains the loader for the process lifetime; callers closing
     * it through the inherited API do not unregister it.
     */
    public JarPluginClassLoader(URL[] urls) {
        super(urls);
        LOADERS.add(this);
    }

    /**
     * Loads a named class from the parent, this loader, or a peer.
     * Resolution follows the supplied flag, and {@code ClassNotFoundException} is
     * reported only after the permitted lookup paths have missed.
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return loadClass0(name, resolve, true);
    }

    /**
     * Resolves a class with optional access to the global peer-loader registry.
     * Parent-first {@code URLClassLoader} lookup always has priority, preserving that
     * loader's existing class identity when it succeeds. On a miss, an outer
     * request may probe each peer with further peer traversal disabled, so
     * lookup cannot cycle indefinitely through the same loaders. A peer's
     * {@code ClassNotFoundException} permits the next probe; other failures propagate,
     * and exhausting the permitted sources reports a fresh miss for the name.
     */
    private Class<?> loadClass0(String name, boolean resolve, boolean checkOther) throws ClassNotFoundException {
        try {
            return super.loadClass(name, resolve);
        } catch (ClassNotFoundException ignored) {
            // A miss in the parent and this JAR leaves peer lookup available;
            // linkage or initialization failures are not suppressed as ordinary misses.
        }

        if (checkOther) {
            for (JarPluginClassLoader loader : LOADERS) {
                if (loader == this) continue;
                try {
                    return loader.loadClass0(name, resolve, false);
                } catch (ClassNotFoundException ignored) {
                    // Each peer is an alternative source, so its missing class
                    // permits another probe without restarting traversal through the registry.
                }
            }
        }

        throw new ClassNotFoundException(name);
    }
}
