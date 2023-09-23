package cn.allay.api.item.interfaces.chorusflower;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChorusFlowerStack extends ItemStack {
    ItemType<ItemChorusFlowerStack> CHORUS_FLOWER_TYPE = ItemTypeBuilder
            .builder(ItemChorusFlowerStack.class)
            .vanillaItem(VanillaItemId.CHORUS_FLOWER)
            .build();
}
