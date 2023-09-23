package cn.allay.api.item.interfaces.bakedpotato;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBakedPotatoStack extends ItemStack {
    ItemType<ItemBakedPotatoStack> BAKED_POTATO_TYPE = ItemTypeBuilder
            .builder(ItemBakedPotatoStack.class)
            .vanillaItem(VanillaItemId.BAKED_POTATO)
            .build();
}
