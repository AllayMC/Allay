package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
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
        ReflectionUtils.getAllStaticVoidParameterlessMethods(BlockEntityTypeInitializer.class).forEach(Utils::callInitializer);
    }
}
