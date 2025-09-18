package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.server.blockentity.type.BlockEntityTypeInitializer;
import org.allaymc.server.utils.ReflectionUtils;
import org.allaymc.server.utils.Utils;

/**
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        log.info(I18n.get().tr(TrKeys.ALLAY_BLOCKENTITYTYPE_LOADING));
        var initializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(BlockEntityTypeInitializer.class);
        initializers.forEach(Utils::callInitializer);
        log.info(I18n.get().tr(TrKeys.ALLAY_BLOCKENTITYTYPE_LOADED, initializers.size()));
    }
}
