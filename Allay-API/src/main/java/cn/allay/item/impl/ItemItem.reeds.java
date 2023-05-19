package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.reeds extends ItemStack{
        ItemType<ItemItem.reeds>TYPE=ItemTypeBuilder
        .builder(ItemItem.reeds.class)
        .vanillaItem(VanillaItemId.REEDS,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
