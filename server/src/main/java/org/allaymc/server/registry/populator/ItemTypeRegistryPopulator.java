package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.server.item.type.InternalItemTypeData;
import org.allaymc.server.item.type.ItemTypeDefaultInitializer;
import org.allaymc.server.item.type.ItemTypeInitializer;
import org.allaymc.server.utils.ReflectionUtils;
import org.allaymc.server.utils.Utils;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ItemTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        InternalItemTypeData.init();
        ReflectionUtils.getAllStaticVoidParameterlessMethods(ItemTypeInitializer.class).forEach(Utils::callInitializer);
        ItemTypeDefaultInitializer.init();
    }
}
