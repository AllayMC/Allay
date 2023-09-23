package cn.allay.codeupdate;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public class Mover {
    @SneakyThrows
    public static void main(String[] args) {
        var path = Path.of("Allay-API/src/main/java/cn/allay/api/entity/interfaces");
        for (var f : Objects.requireNonNull(path.toFile().listFiles())) {
            var dname = f.getName().substring(6, f.getName().length() - 5).toLowerCase();
            Files.createDirectories(path.resolve(dname));
            var s = Files.readString(f.toPath());
            s = s.replace("cn.allay.api.entity.interfaces", "cn.allay.api.entity.interfaces." + dname);
            Files.writeString(f.toPath(), s);
            Files.move(f.toPath(), path.resolve(dname).resolve(f.getName()));
        }
    }
}
