package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.chain extends ItemStack{
        ItemType<ItemItem.chain>TYPE=ItemTypeBuilder
        .builder(ItemItem.chain.class)
        .vanillaItem(VanillaItemId.CHAIN,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
