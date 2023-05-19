package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.beetroot extends ItemStack{
        ItemType<ItemItem.beetroot>TYPE=ItemTypeBuilder
        .builder(ItemItem.beetroot.class)
        .vanillaItem(VanillaItemId.BEETROOT,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
