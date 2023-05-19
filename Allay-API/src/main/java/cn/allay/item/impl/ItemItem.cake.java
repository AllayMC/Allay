package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.cake extends ItemStack{
        ItemType<ItemItem.cake>TYPE=ItemTypeBuilder
        .builder(ItemItem.cake.class)
        .vanillaItem(VanillaItemId.CAKE,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
