package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.server.block.type.BlockTypeDefaultInitializer;
import org.allaymc.server.block.type.BlockTypeInitializer;
import org.allaymc.server.block.type.InternalBlockTypeData;
import org.allaymc.server.utils.ReflectionUtils;
import org.allaymc.server.utils.Utils;

/**
 * @author daoge_cmd
 */
@Slf4j
public class BlockTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        log.info(I18n.get().tr(TrKeys.A_BLOCKTYPE_LOADING));
        InternalBlockTypeData.init();
        var initializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(BlockTypeInitializer.class);
        initializers.forEach(Utils::callInitializer);
        BlockTypeDefaultInitializer.init();
        log.info(I18n.get().tr(TrKeys.A_BLOCKTYPE_LOADED, BlockId.values().length));
    }
}
