package org.allaymc.server.utils;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

/**
 * {@code DynamicURLClassLoader} exposes runtime URL additions for a parent-delegating class
 * loader. Instances begin with no search URLs, callers can append JAR paths or URLs later,
 * and the inherited {@code close} operation releases loader resources when their owning
 * plugin or service finishes using them.
 *
 * @author Cool_Loong
 */
@Slf4j
public class DynamicURLClassLoader extends URLClassLoader {
    /**
     * Creates an empty URL search path with the supplied delegation parent.
     */
    public DynamicURLClassLoader(ClassLoader parent) {
        super(new URL[0], parent);
    }

    /**
     * Appends a new URL through the inherited search-path implementation. A {@code null} URL, a URL
     * already present, or a closed loader makes this operation a no-op, following the JDK
     * class-loader contract.
     */
    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    /**
     * Adds the URL corresponding to a non-{@code null} JAR path without checking its existence or
     * contents.
     * URL-conversion failures enter a logging path; if the exception has no cause, that logging
     * path itself can throw {@code NullPointerException}.
     */
    public void addJar(Path jarPath) {
        Preconditions.checkNotNull(jarPath);
        try {
            addURL(jarPath.toUri().toURL());
        } catch (MalformedURLException e) {
            // A failed URL conversion cannot extend the search path. This path attempts to
            // log its cause, whose absence can itself fail while constructing the log message.
            log.error(e.getCause().getMessage());
        }
    }
}
