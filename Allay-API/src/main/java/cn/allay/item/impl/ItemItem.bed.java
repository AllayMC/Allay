package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.bed extends ItemStack{
        ItemType<ItemItem.bed>TYPE=ItemTypeBuilder
        .builder(ItemItem.bed.class)
        .vanillaItem(VanillaItemId.BED,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
