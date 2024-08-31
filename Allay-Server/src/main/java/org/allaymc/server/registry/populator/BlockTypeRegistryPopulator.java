package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.server.block.type.BlockLootTable;
import org.allaymc.server.block.type.BlockTypeDefaultInitializer;
import org.allaymc.server.block.type.BlockTypeInitializer;
import org.allaymc.server.block.type.InternalBlockTypeData;
import org.allaymc.server.utils.ReflectionUtils;
import org.allaymc.server.utils.Utils;

/**
 * Allay Project 2024/7/19
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        log.info(I18n.get().tr(TrKeys.A_BLOCKTYPE_LOADING));
        InternalBlockTypeData.init();
        BlockLootTable.init();
        var defaultInitializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(BlockTypeDefaultInitializer.class);
        var initializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(BlockTypeInitializer.class);
        initializers.forEach(Utils::callInitializer);
        defaultInitializers.forEach(Utils::callInitializer);
        log.info(I18n.get().tr(TrKeys.A_BLOCKTYPE_LOADED, defaultInitializers.size()));
    }
}
