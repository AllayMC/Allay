package cn.allay.api.item.interfaces.goldenhoe;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenHoeStack extends ItemStack {
    ItemType<ItemGoldenHoeStack> GOLDEN_HOE_TYPE = ItemTypeBuilder
            .builder(ItemGoldenHoeStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_HOE)
            .build();
}
