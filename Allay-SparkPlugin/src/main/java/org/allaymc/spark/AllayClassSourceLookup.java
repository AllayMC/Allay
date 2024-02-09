package org.allaymc.spark;

import me.lucko.spark.common.sampler.source.ClassSourceLookup;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Allay Project 08/02/2024
 *
 * @author IWareQ
 */
public class AllayClassSourceLookup extends ClassSourceLookup.ByFirstUrlSource {

    private static final Class<?> JAR_PLUGIN_CLASS_LOADER;

    static {
        try {
            JAR_PLUGIN_CLASS_LOADER = Class.forName("org.allaymc.server.plugin.jar.JarPluginClassLoader");
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public String identify(ClassLoader loader) throws IOException, URISyntaxException {
        if (!JAR_PLUGIN_CLASS_LOADER.isAssignableFrom(loader.getClass())) return null;
        return super.identify(loader);
    }
}
