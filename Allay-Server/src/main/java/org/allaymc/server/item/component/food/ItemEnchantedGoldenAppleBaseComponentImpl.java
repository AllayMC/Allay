package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectAbsorptionType;
import org.allaymc.api.entity.effect.type.EffectFireResistanceType;
import org.allaymc.api.entity.effect.type.EffectRegenerationType;
import org.allaymc.api.entity.effect.type.EffectResistanceType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemEnchantedGoldenAppleStack;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
public class ItemEnchantedGoldenAppleBaseComponentImpl extends ItemFoodBaseComponentImpl<ItemEnchantedGoldenAppleStack> {
    public ItemEnchantedGoldenAppleBaseComponentImpl(ItemStackInitInfo<ItemEnchantedGoldenAppleStack> initInfo) {
        super(initInfo, 4, 9.6f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.addEffect(EffectRegenerationType.REGENERATION_TYPE.createInstance(4, 30 * 20)); // 5 lvl, 30 seconds
        player.addEffect(EffectAbsorptionType.ABSORPTION_TYPE.createInstance(3, 60 * 2 * 20)); // 4 lvl, 2 minutes
        player.addEffect(EffectResistanceType.RESISTANCE_TYPE.createInstance(0, 60 * 5 * 20)); // 1 lvl, 5 minutes
        player.addEffect(EffectFireResistanceType.FIRE_RESISTANCE_TYPE.createInstance(0, 60 * 5 * 20)); // 1 lvl, 5 minutes
    }
}
