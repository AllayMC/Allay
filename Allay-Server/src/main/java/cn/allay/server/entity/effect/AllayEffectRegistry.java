package cn.allay.server.entity.effect;

import cn.allay.api.entity.effect.EffectType;
import cn.allay.api.entity.effect.EffectRegistry;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.SimpleDoubleKeyMappedRegistry;
import cn.allay.api.utils.ReflectionUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayEffectRegistry extends SimpleDoubleKeyMappedRegistry<Integer, Identifier, EffectType> implements EffectRegistry {
    public AllayEffectRegistry() {
        super(null, input -> new MapPair<>(new HashMap<>(), new HashMap<>()));
    }

    @SneakyThrows
    public void init() {
        log.info("Loading Effect Types...");
        var classes = ReflectionUtils.getAllClasses("cn.allay.api.entity.effect.type");
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(classes.size())
                .setTaskName("Loading Effect Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .setUpdateIntervalMillis(100)
                .build()) {
            for (var effectClassName : classes) {
                Class.forName(effectClassName);
                pgbar.step();
            }
        }
        log.info("Loaded " + classes.size() + " Effect Types");
    }
}
