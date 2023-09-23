package cn.allay.api.item.interfaces.leatherhelmet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherHelmetStack extends ItemStack {
    ItemType<ItemLeatherHelmetStack> LEATHER_HELMET_TYPE = ItemTypeBuilder
            .builder(ItemLeatherHelmetStack.class)
            .vanillaItem(VanillaItemId.LEATHER_HELMET)
            .build();
}
