package cn.allay.api.item.interfaces.invisiblebedrock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInvisibleBedrockStack extends ItemStack {
    ItemType<ItemInvisibleBedrockStack> INVISIBLE_BEDROCK_TYPE = ItemTypeBuilder
            .builder(ItemInvisibleBedrockStack.class)
            .vanillaItem(VanillaItemId.INVISIBLE_BEDROCK)
            .build();
}
