package org.allaymc.server.plugin.jar;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public class JarPluginClassLoader extends URLClassLoader {

    private static final Set<JarPluginClassLoader> LOADERS = new CopyOnWriteArraySet<>();

    static {
        ClassLoader.registerAsParallelCapable();
    }

    public JarPluginClassLoader(URL[] urls) {
        super(urls);
        LOADERS.add(this);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return loadClass0(name, resolve, true);
    }

    private Class<?> loadClass0(String name, boolean resolve, boolean checkOther) throws ClassNotFoundException {
        try {
            return super.loadClass(name, resolve);
        } catch (ClassNotFoundException ignored) {
            // Ignored: we'll try others
        }

        if (checkOther) {
            for (JarPluginClassLoader loader : LOADERS) {
                if (loader == this) continue;
                try {
                    return loader.loadClass0(name, resolve, false);
                } catch (ClassNotFoundException ignored) {
                    // We're trying others, safe to ignore
                }
            }
        }

        throw new ClassNotFoundException(name);
    }
}
