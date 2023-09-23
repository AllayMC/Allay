package cn.allay.api.item.interfaces.bowl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBowlStack extends ItemStack {
    ItemType<ItemBowlStack> BOWL_TYPE = ItemTypeBuilder
            .builder(ItemBowlStack.class)
            .vanillaItem(VanillaItemId.BOWL)
            .build();
}
