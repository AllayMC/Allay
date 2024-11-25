package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.data.ItemId;
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
        log.info(I18n.get().tr(TrKeys.A_ITEMTYPE_LOADING));
        InternalItemTypeData.init();
        var defaultInitializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(ItemTypeDefaultInitializer.class);
        var initializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(ItemTypeInitializer.class);
        initializers.forEach(Utils::callInitializer);
        defaultInitializers.forEach(Utils::callInitializer);
        log.info(I18n.get().tr(TrKeys.A_ITEMTYPE_LOADED, ItemId.values().length));
    }
}
