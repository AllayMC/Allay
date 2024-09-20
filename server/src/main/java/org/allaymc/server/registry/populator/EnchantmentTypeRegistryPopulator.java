package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.enchantment.type.EnchantmentTypes;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EnchantmentTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        log.info(I18n.get().tr(TrKeys.A_ENCHTYPE_LOADING));
        var $ = EnchantmentTypes.AQUA_AFFINITY;
        log.info(I18n.get().tr(TrKeys.A_ENCHTYPE_LOADED, EnchantmentTypes.class.getFields().length));
    }
}
