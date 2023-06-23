package cn.allay.server.utils;

import lombok.SneakyThrows;
import org.reflections.Reflections;

import static org.reflections.scanners.Scanners.SubTypes;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/20 <br>
 * Allay Project <br>
 */
public final class PackageClassLoader {
    private PackageClassLoader() {
    }

    @SneakyThrows
    public static void loadClasses(String packageName) {
        for (String s : new Reflections(packageName).getAll(SubTypes)) {
            if (s.startsWith(packageName)) {
                Class.forName(s);
            }
        }
    }
}
