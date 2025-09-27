package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.server.entity.type.EntityTypeDefaultInitializer;
import org.allaymc.server.entity.type.EntityTypeInitializer;
import org.allaymc.server.utils.ReflectionUtils;
import org.allaymc.server.utils.Utils;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        ReflectionUtils.getAllStaticVoidParameterlessMethods(EntityTypeInitializer.class).forEach(Utils::callInitializer);
        EntityTypeDefaultInitializer.init();
    }
}
