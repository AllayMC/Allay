package org.allaymc.server.item.enchantment;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.enchantment.EnchantmentRegistry;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.registry.SimpleDoubleKeyMappedRegistry;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.ReflectionUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayEnchantmentRegistry extends SimpleDoubleKeyMappedRegistry<Short, Identifier, EnchantmentType> implements EnchantmentRegistry {
    public AllayEnchantmentRegistry() {
        super(null, input -> new MapPair<>(new HashMap<>(), new HashMap<>()));
    }

    @SneakyThrows
    public void init() {
        log.info("Loading Enchantment Types...");
        var classes = ReflectionUtils.getAllClasses("org.allaymc.api.item.enchantment.type");
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(classes.size())
                .setTaskName("Loading Enchantment Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .setUpdateIntervalMillis(100)
                .build()) {
            for (var enchantmentClassName : classes) {
                Class.forName(enchantmentClassName);
                pgbar.step();
            }
        }
        log.info("Loaded " + classes.size() + " Enchantment Types");
    }
}
