package org.allaymc.server.item.enchantment;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.data.VanillaEnchantmentTypes;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.enchantment.EnchantmentRegistry;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.utils.Identifier;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayEnchantmentRegistry extends SimpleDoubleKeyMappedRegistry<Integer, Identifier, EnchantmentType> implements EnchantmentRegistry {
    public AllayEnchantmentRegistry() {
        super(null, input -> new MapPair<>(new ConcurrentHashMap<>(), new ConcurrentHashMap<>()));
    }

    @SneakyThrows
    public void init() {
        log.info(I18n.get().tr(TrKeys.A_ENCHTYPE_LOADING));
        var $ = VanillaEnchantmentTypes.AQUA_AFFINITY;
        log.info(I18n.get().tr(TrKeys.A_ENCHTYPE_LOADED, VanillaEnchantmentTypes.class.getFields().length));
    }
}
