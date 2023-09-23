package cn.allay.api.item.interfaces.prismarine;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPrismarineStack extends ItemStack {
    ItemType<ItemPrismarineStack> PRISMARINE_TYPE = ItemTypeBuilder
            .builder(ItemPrismarineStack.class)
            .vanillaItem(VanillaItemId.PRISMARINE)
            .build();
}
