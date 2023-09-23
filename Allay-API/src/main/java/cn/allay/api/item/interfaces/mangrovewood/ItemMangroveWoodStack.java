package cn.allay.api.item.interfaces.mangrovewood;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveWoodStack extends ItemStack {
    ItemType<ItemMangroveWoodStack> MANGROVE_WOOD_TYPE = ItemTypeBuilder
            .builder(ItemMangroveWoodStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_WOOD)
            .build();
}
