package cn.allay.api.item.interfaces.blazerod;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlazeRodStack extends ItemStack {
    ItemType<ItemBlazeRodStack> BLAZE_ROD_TYPE = ItemTypeBuilder
            .builder(ItemBlazeRodStack.class)
            .vanillaItem(VanillaItemId.BLAZE_ROD)
            .build();
}
