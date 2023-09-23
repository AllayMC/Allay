package cn.allay.api.item.interfaces.sealantern;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSeaLanternStack extends ItemStack {
    ItemType<ItemSeaLanternStack> SEA_LANTERN_TYPE = ItemTypeBuilder
            .builder(ItemSeaLanternStack.class)
            .vanillaItem(VanillaItemId.SEA_LANTERN)
            .build();
}
