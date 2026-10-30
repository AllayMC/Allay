package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.server.blockentity.type.BlockEntityTypeInitializer;
import org.allaymc.server.utils.ReflectionUtils;
import org.allaymc.server.utils.Utils;

/**
 * {@code BlockEntityTypeRegistryPopulator} supplies the startup population step for built-in
 * block-entity types. It invokes public static parameterless {@code void} methods from
 * {@code BlockEntityTypeInitializer}. Each discovered initializer supplies its corresponding
 * block-entity definition; reflection does not establish a stable execution order.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityTypeRegistryPopulator implements Runnable {
    /**
     * Invokes the discovered block-entity initializers.
     * Required registries must already be available; a reflection or initializer failure aborts
     * this pass without undoing the definitions installed by earlier calls.
     */
    @Override
    public void run() {
        ReflectionUtils.getAllStaticVoidParameterlessMethods(BlockEntityTypeInitializer.class).forEach(Utils::callInitializer);
    }
}
