package org.allaymc.server.entity.effect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.entity.effect.EffectRegistry;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.registry.SimpleDoubleKeyMappedRegistry;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.ReflectionUtils;

import java.util.HashMap;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayEffectRegistry extends SimpleDoubleKeyMappedRegistry<Integer, Identifier, EffectType> implements EffectRegistry {
    public AllayEffectRegistry() {
        super(null, $ -> new MapPair<>(new HashMap<>(), new HashMap<>()));
    }

    @SneakyThrows
    public void init() {
        log.info(I18n.get().tr(TrKeys.A_EFFECTTYPE_LOADING));
        var classes = ReflectionUtils.getAllClasses("org.allaymc.api.entity.effect.type");
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(classes.size())
                .setTaskName("Loading Effect Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .build()) {
            for (var effectClassName : classes) {
                Class.forName(effectClassName);
                pgbar.step();
            }
        }
        log.info(I18n.get().tr(TrKeys.A_EFFECTTYPE_LOADED, classes.size()));
    }
}
