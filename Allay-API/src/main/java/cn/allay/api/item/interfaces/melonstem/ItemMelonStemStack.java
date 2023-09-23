package cn.allay.api.item.interfaces.melonstem;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMelonStemStack extends ItemStack {
    ItemType<ItemMelonStemStack> MELON_STEM_TYPE = ItemTypeBuilder
            .builder(ItemMelonStemStack.class)
            .vanillaItem(VanillaItemId.MELON_STEM)
            .build();
}
