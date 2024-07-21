package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.utils.ReflectionUtils;
import org.allaymc.server.blockentity.type.BlockEntityTypeInitializer;

import static org.allaymc.server.utils.Utils.callInitializer;

/**
 * Allay Project 2024/7/20
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
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
