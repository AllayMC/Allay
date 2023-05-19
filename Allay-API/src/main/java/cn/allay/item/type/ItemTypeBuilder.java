package cn.allay.item.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.component.interfaces.ComponentProvider;
import cn.allay.identifier.Identifier;
import cn.allay.item.ItemStack;
import cn.allay.item.component.ItemComponentImpl;
import cn.allay.item.data.VanillaItemId;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
public interface ItemTypeBuilder<T extends ItemStack> {
    ApiInstanceHolder<ItemTypeBuilderFactory> FACTORY = ApiInstanceHolder.create();

    static <T extends ItemStack> ItemTypeBuilder<T> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    ItemTypeBuilder<T> namespace(Identifier identifier);

    ItemTypeBuilder<T> namespace(String namespaceId);

    ItemTypeBuilder<T> vanillaItem(VanillaItemId vanillaItemId);

    ItemTypeBuilder<T> vanillaItem(VanillaItemId vanillaItemId, boolean initVanillaItemAttributeComponent);

    ItemTypeBuilder<T> setComponents(List<ComponentProvider<? extends ItemComponentImpl>> componentProviders);

    ItemTypeBuilder<T> addComponents(List<ComponentProvider<? extends ItemComponentImpl>> componentProviders);

    ItemType<T> build();

    interface ItemTypeBuilderFactory {
        <T extends ItemStack> ItemTypeBuilder<T> create(Class<T> clazz);
    }
}
