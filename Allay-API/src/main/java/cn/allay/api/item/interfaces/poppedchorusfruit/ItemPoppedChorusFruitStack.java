package cn.allay.api.item.interfaces.poppedchorusfruit;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPoppedChorusFruitStack extends ItemStack {
    ItemType<ItemPoppedChorusFruitStack> POPPED_CHORUS_FRUIT_TYPE = ItemTypeBuilder
            .builder(ItemPoppedChorusFruitStack.class)
            .vanillaItem(VanillaItemId.POPPED_CHORUS_FRUIT)
            .build();
}
