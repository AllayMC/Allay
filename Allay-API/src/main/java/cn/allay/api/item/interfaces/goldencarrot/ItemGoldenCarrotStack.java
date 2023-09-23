package cn.allay.api.item.interfaces.goldencarrot;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenCarrotStack extends ItemStack {
    ItemType<ItemGoldenCarrotStack> GOLDEN_CARROT_TYPE = ItemTypeBuilder
            .builder(ItemGoldenCarrotStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_CARROT)
            .build();
}
