package cn.allay.api.item.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponent;
import cn.allay.api.item.init.ItemStackInitInfo;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static cn.allay.api.component.interfaces.ComponentProvider.toMap;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemTypeBuilder<T extends ItemStack, C extends ItemComponent> {
    ApiInstanceHolder<ItemTypeBuilderFactory> FACTORY = ApiInstanceHolder.of();

    static <T extends ItemStack> ItemTypeBuilder<T, ItemComponent> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    ItemTypeBuilder<T, C> identifier(Identifier identifier);

    default ItemTypeBuilder<T, C> identifier(String identifier) {
        return identifier(new Identifier(identifier));
    }

    ItemTypeBuilder<T, C> blockIdentifier(Identifier blockIdentifier);

    default ItemTypeBuilder<T, C> blockIdentifier(String blockIdentifier) {
        return blockIdentifier(new Identifier(blockIdentifier));
    }

    ItemTypeBuilder<T, C> vanillaItem(VanillaItemId vanillaItemId);

    ItemTypeBuilder<T, C> runtimeId(int runtimeId);

    ItemTypeBuilder<T, C> setComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders);

    default ItemTypeBuilder<T, C> setComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
        return setComponents(toMap(componentProviders));
    }

    ItemTypeBuilder<T, C> addComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders);

    default ItemTypeBuilder<T, C> addComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
        return addComponents(toMap(componentProviders));
    }

    ItemTypeBuilder<T, C> addComponent(Function<ItemStackInitInfo<T>, C> provider, Class<?> componentClass);

    ItemType<T> build();

    interface ItemTypeBuilderFactory {
        <T extends ItemStack> ItemTypeBuilder<T, ItemComponent> create(Class<T> clazz);
    }
}
