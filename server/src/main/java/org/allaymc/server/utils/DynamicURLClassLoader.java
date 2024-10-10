package org.allaymc.server.utils;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

/**
 * @author Cool_Loong
 */
@Slf4j
public class DynamicURLClassLoader extends URLClassLoader {
    public DynamicURLClassLoader(ClassLoader parent) {
        super(new URL[0], parent);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    public void addJar(Path jarPath) {
        Preconditions.checkNotNull(jarPath);
        try {
            addURL(jarPath.toUri().toURL());
        } catch (MalformedURLException e) {
            log.error(e.getCause().getMessage());
        }
    }
}
