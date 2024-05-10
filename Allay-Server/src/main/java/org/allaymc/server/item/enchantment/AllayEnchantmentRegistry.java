package org.allaymc.server.item.enchantment;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.item.enchantment.EnchantmentRegistry;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.registry.SimpleDoubleKeyMappedRegistry;
import org.allaymc.api.utils.ReflectionUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayEnchantmentRegistry extends SimpleDoubleKeyMappedRegistry<Short, Identifier, EnchantmentType> implements EnchantmentRegistry {
    public AllayEnchantmentRegistry() {
        super(null, input -> new MapPair<>(new ConcurrentHashMap<>(), new ConcurrentHashMap<>()));
    }

    @SneakyThrows
    public void init() {
        log.info(I18n.get().tr("allay:enchtype.loading"));
        var classes = ReflectionUtils.getAllClasses("org.allaymc.api.item.enchantment.type");
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(classes.size())
                .setTaskName("Loading Enchantment Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .build()) {
            for (var enchantmentClassName : classes) {
                Class.forName(enchantmentClassName);
                pgbar.step();
            }
        }
        log.info(I18n.get().tr("allay:enchtype.loaded", classes.size()));
    }
}
