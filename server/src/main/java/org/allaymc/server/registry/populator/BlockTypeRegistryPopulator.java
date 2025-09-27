package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.server.block.type.BlockTypeDefaultInitializer;
import org.allaymc.server.block.type.BlockTypeInitializer;
import org.allaymc.server.utils.ReflectionUtils;
import org.allaymc.server.utils.Utils;

/**
 * @author daoge_cmd
 */
@Slf4j
public class BlockTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        ReflectionUtils.getAllStaticVoidParameterlessMethods(BlockTypeInitializer.class).forEach(Utils::callInitializer);
        BlockTypeDefaultInitializer.init();
    }
}
