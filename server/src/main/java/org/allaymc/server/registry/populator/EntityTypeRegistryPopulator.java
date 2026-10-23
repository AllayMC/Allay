package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.server.entity.type.EntityTypeDefaultInitializer;
import org.allaymc.server.entity.type.EntityTypeInitializer;
import org.allaymc.server.utils.ReflectionUtils;
import org.allaymc.server.utils.Utils;

/**
 * {@code EntityTypeRegistryPopulator} supplies the startup population step for built-in
 * entity types. It invokes public static parameterless {@code void} methods from
 * {@code EntityTypeInitializer}. {@code EntityTypeDefaultInitializer.init} then fills constants
 * that remain {@code null}, so specialized definitions take precedence over generated defaults.
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityTypeRegistryPopulator implements Runnable {
    /**
     * Invokes the discovered entity initializers and then fills unresolved constants with
     * defaults.
     * Required registries must already be available; a reflection or initializer failure aborts
     * this pass without undoing the definitions installed by earlier calls.
     */
    @Override
    public void run() {
        ReflectionUtils.getAllStaticVoidParameterlessMethods(EntityTypeInitializer.class).forEach(Utils::callInitializer);
        // Specialized initialization must finish first. The default pass only fills null
        // constants, preserving the implementations already installed by those specialized methods.
        EntityTypeDefaultInitializer.init();
    }
}
