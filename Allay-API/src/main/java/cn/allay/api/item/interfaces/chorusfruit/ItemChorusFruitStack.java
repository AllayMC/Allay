package cn.allay.api.item.interfaces.chorusfruit;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChorusFruitStack extends ItemStack {
    ItemType<ItemChorusFruitStack> CHORUS_FRUIT_TYPE = ItemTypeBuilder
            .builder(ItemChorusFruitStack.class)
            .vanillaItem(VanillaItemId.CHORUS_FRUIT)
            .build();
}
