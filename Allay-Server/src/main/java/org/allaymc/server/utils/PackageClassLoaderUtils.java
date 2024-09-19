package org.allaymc.server.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.reflections.Reflections;

import static org.reflections.scanners.Scanners.SubTypes;

/**
 * @author daoge_cmd
 */
@UtilityClass
public final class PackageClassLoaderUtils {

    @SneakyThrows
    public static void loadClasses(String packageName) {
        for (String s : new Reflections(packageName).getAll(SubTypes)) {
            if (s.startsWith(packageName)) {
                Class.forName(s);
            }
        }
    }
}
