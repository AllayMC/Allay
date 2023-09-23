package cn.allay.api.item.interfaces.medicine;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMedicineStack extends ItemStack {
    ItemType<ItemMedicineStack> MEDICINE_TYPE = ItemTypeBuilder
            .builder(ItemMedicineStack.class)
            .vanillaItem(VanillaItemId.MEDICINE)
            .build();
}
