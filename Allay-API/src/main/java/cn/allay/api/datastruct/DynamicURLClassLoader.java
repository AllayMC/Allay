package cn.allay.api.datastruct;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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

    public void addJar(@NotNull File jarFile) {
        try {
            addURL(jarFile.toURI().toURL());
        } catch (MalformedURLException e) {
            log.error(e.getCause().getMessage());
        }
    }

    public void addJar(@NotNull String jarPath) {
        addJar(new File(jarPath));
    }
}
