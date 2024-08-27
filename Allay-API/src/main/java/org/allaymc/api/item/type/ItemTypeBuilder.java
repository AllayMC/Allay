package org.allaymc.api.item.type;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.tag.ItemTag;
import org.allaymc.api.utils.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.allaymc.api.component.interfaces.ComponentProvider.toMap;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemTypeBuilder<T extends ItemStack> {
    ApiInstanceHolder<ItemTypeBuilderFactory> FACTORY = ApiInstanceHolder.create();

    static <T extends ItemStack> ItemTypeBuilder<T> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    ItemTypeBuilder<T> identifier(Identifier identifier);

    default ItemTypeBuilder<T> identifier(String identifier) {
        return identifier(new Identifier(identifier));
    }

    ItemTypeBuilder<T> vanillaItem(VanillaItemId vanillaItemId);

    ItemTypeBuilder<T> runtimeId(int runtimeId);

    ItemTypeBuilder<T> setComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders);

    default ItemTypeBuilder<T> setComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
        return setComponents(toMap(componentProviders));
    }

    ItemTypeBuilder<T> addComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders);

    default ItemTypeBuilder<T> addComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
        return addComponents(toMap(componentProviders));
    }

    ItemTypeBuilder<T> addComponent(Function<ItemStackInitInfo, ? extends ItemComponent> provider, Class<?> componentClass);

    default ItemTypeBuilder<T> addComponent(Supplier<? extends ItemComponent> supplier, Class<?> componentClass) {
        return addComponent($ -> supplier.get(), componentClass);
    }

    ItemTypeBuilder<T> addComponent(ComponentProvider<? extends ItemComponent> componentProvider);

    ItemTypeBuilder<T> setItemTags(ItemTag... itemTags);

    ItemType<T> build();

    interface ItemTypeBuilderFactory {
        <T extends ItemStack> ItemTypeBuilder<T> create(Class<T> clazz);
    }
}
