package cn.allay.api.item.interfaces.sapling;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSaplingStack extends ItemStack {
    ItemType<ItemSaplingStack> SAPLING_TYPE = ItemTypeBuilder
            .builder(ItemSaplingStack.class)
            .vanillaItem(VanillaItemId.SAPLING)
            .build();
}
