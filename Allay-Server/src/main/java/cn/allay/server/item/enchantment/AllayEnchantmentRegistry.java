package cn.allay.server.item.enchantment;

import cn.allay.api.item.enchantment.EnchantmentRegistry;
import cn.allay.api.item.enchantment.EnchantmentType;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.api.utils.ReflectionUtils;
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
public class AllayEnchantmentRegistry extends SimpleMappedRegistry<Short, EnchantmentType, Map<Short, EnchantmentType>> implements EnchantmentRegistry {
    public AllayEnchantmentRegistry() {
        super(null, input -> new HashMap<>());
    }

    @SneakyThrows
    public void init() {
        log.info("Loading Enchantment Types...");
        var classes = ReflectionUtils.getAllClasses("cn.allay.server.item.enchantment.type");
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(classes.size())
                .setTaskName("Loading Enchantment Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .setUpdateIntervalMillis(100)
                .build()) {
            for (var enchantmentClassName : classes) {
                Constructor<?> constructor = Class.forName(enchantmentClassName).getDeclaredConstructor();
                constructor.setAccessible(true);
                EnchantmentType enchantment = (EnchantmentType) constructor.newInstance();
                register(enchantment.getId(), enchantment);
                pgbar.step();
            }
        }
        log.info("Loaded " + classes.size() + " Enchantment Types");
    }
}
