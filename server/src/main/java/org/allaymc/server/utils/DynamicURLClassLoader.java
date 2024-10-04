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

    public DynamicURLClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    public void addJar(File jarFile) {
        Preconditions.checkNotNull(jarFile);
        try {
            addURL(jarFile.toURI().toURL());
        } catch (MalformedURLException e) {
            log.error(e.getCause().getMessage());
        }
    }

    public void addJar(Path jarPath) {
        Preconditions.checkNotNull(jarPath);
        addJar(jarPath.toFile());
    }

    public void addJar(String jarPath) {
        Preconditions.checkNotNull(jarPath);
        addJar(new File(jarPath));
    }
}
