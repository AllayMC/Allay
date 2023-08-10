package cn.allay.api.item.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponentImpl;

import java.util.List;
import java.util.Map;

import static cn.allay.api.component.interfaces.ComponentProvider.toMap;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemTypeBuilder<T extends ItemStack> {
    ApiInstanceHolder<ItemTypeBuilderFactory> FACTORY = ApiInstanceHolder.of();

    static <T extends ItemStack> ItemTypeBuilder<T> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    ItemTypeBuilder<T> identifier(Identifier identifier);

    default ItemTypeBuilder<T> identifier(String identifier) {
        return identifier(new Identifier(identifier));
    }

    ItemTypeBuilder<T> blockIdentifier(Identifier blockIdentifier);

    default ItemTypeBuilder<T> blockIdentifier(String blockIdentifier) {
        return blockIdentifier(new Identifier(blockIdentifier));
    }

    ItemTypeBuilder<T> vanillaItem(VanillaItemId vanillaItemId);

    ItemTypeBuilder<T> runtimeId(int runtimeId);

    ItemTypeBuilder<T> setComponents(Map<Identifier, ComponentProvider<? extends ItemComponentImpl>> componentProviders);

    default ItemTypeBuilder<T> setComponents(List<ComponentProvider<? extends ItemComponentImpl>> componentProviders) {
        return setComponents(toMap(componentProviders));
    }

    ItemTypeBuilder<T> addComponents(Map<Identifier, ComponentProvider<? extends ItemComponentImpl>> componentProviders);

    default ItemTypeBuilder<T> addComponents(List<ComponentProvider<? extends ItemComponentImpl>> componentProviders) {
        return addComponents(toMap(componentProviders));
    }

    ItemTypeBuilder<T> addComponent(ComponentProvider<? extends ItemComponentImpl> componentProvider);

    ItemTypeBuilder<T> addBasicComponents();

    ItemType<T> build();

    interface ItemTypeBuilderFactory {
        <T extends ItemStack> ItemTypeBuilder<T> create(Class<T> clazz);
    }
}
