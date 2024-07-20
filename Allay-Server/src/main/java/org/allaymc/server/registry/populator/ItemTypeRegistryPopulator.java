package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.utils.ReflectionUtils;
import org.allaymc.server.item.type.InternalItemTypeData;
import org.allaymc.server.item.type.ItemTypeDefaultInitializer;
import org.allaymc.server.item.type.ItemTypeInitializer;

import static org.allaymc.server.utils.Utils.callInitializer;

/**
 * Allay Project 2024/7/20
 *
 * @author daoge_cmd
 */
@Slf4j
public class ItemTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        InternalItemTypeData.init();
        log.info(I18n.get().tr(TrKeys.A_ITEMTYPE_LOADING));
        var defaultInitializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(ItemTypeDefaultInitializer.class);
        var initializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(ItemTypeInitializer.class);
        try (var progressBar = ProgressBar
                .builder()
                .setInitialMax(defaultInitializers.size())
                .setTaskName("Loading Item Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .build()) {
            initializers.forEach(method -> callInitializer(method, null));
            defaultInitializers.forEach(method -> callInitializer(method, progressBar));
        }
        log.info(I18n.get().tr(TrKeys.A_ITEMTYPE_LOADED, defaultInitializers.size()));
    }
}
