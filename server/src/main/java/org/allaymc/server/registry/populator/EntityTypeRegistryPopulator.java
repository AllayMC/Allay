package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
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
        log.info(I18n.get().tr(TrKeys.A_ENTITYTYPE_LOADING));
        var initializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(EntityTypeInitializer.class);
        initializers.forEach(Utils::callInitializer);
        EntityTypeDefaultInitializer.init();
        log.info(I18n.get().tr(TrKeys.A_ENTITYTYPE_LOADED, EntityId.values().length));
    }
}
