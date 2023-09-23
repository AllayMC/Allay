package cn.allay.api.item.interfaces.sculkcatalyst;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSculkCatalystStack extends ItemStack {
    ItemType<ItemSculkCatalystStack> SCULK_CATALYST_TYPE = ItemTypeBuilder
            .builder(ItemSculkCatalystStack.class)
            .vanillaItem(VanillaItemId.SCULK_CATALYST)
            .build();
}
