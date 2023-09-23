package cn.allay.api.item.interfaces.goldenapple;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenAppleStack extends ItemStack {
    ItemType<ItemGoldenAppleStack> GOLDEN_APPLE_TYPE = ItemTypeBuilder
            .builder(ItemGoldenAppleStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_APPLE)
            .build();
}
