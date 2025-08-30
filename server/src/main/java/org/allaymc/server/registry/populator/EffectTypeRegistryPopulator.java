package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EffectTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        log.info(I18n.get().tr(TrKeys.ALLAY_EFFECTTYPE_LOADING));
        var $ = EffectTypes.ABSORPTION;
        log.info(I18n.get().tr(TrKeys.ALLAY_EFFECTTYPE_LOADED, EffectTypes.class.getFields().length));
    }
}
