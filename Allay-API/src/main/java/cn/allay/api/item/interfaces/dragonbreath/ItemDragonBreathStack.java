package cn.allay.api.item.interfaces.dragonbreath;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDragonBreathStack extends ItemStack {
    ItemType<ItemDragonBreathStack> DRAGON_BREATH_TYPE = ItemTypeBuilder
            .builder(ItemDragonBreathStack.class)
            .vanillaItem(VanillaItemId.DRAGON_BREATH)
            .build();
}
