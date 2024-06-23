package org.allaymc.server.blockentity.registry;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.blockentity.registry.BlockEntityTypeRegistry;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.ReflectionUtils;
import org.allaymc.server.blockentity.type.BlockEntityTypeInitializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.allaymc.server.utils.ResourceUtils.callInitializer;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayBlockEntityTypeRegistry extends SimpleMappedRegistry<String, BlockEntityType<?>, Map<String, BlockEntityType<?>>> implements BlockEntityTypeRegistry {
    public AllayBlockEntityTypeRegistry() {
        super(null, input -> new ConcurrentHashMap<>());
    }

    @SneakyThrows
    public void init() {
        log.info(I18n.get().tr(TrKeys.A_BLOCKENTITYTYPE_LOADING));
        var initializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(BlockEntityTypeInitializer.class);
        try (var progressBar = ProgressBar
                .builder()
                .setInitialMax(initializers.size())
                .setTaskName("Loading Block Entity Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .build()) {
            initializers.forEach(method -> callInitializer(method, progressBar));
        }
        log.info(I18n.get().tr(TrKeys.A_BLOCKENTITYTYPE_LOADED, initializers.size()));
    }
}
