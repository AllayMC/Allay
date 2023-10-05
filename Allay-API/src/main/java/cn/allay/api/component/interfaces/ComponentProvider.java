package cn.allay.api.component.interfaces;

import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.BlockEntityComponent;
import cn.allay.api.blockentity.init.BlockEntityInitInfo;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponent;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponent;
import cn.allay.api.item.init.ItemStackInitInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.reflect.Modifier.isStatic;

/**
 * Allay Project 2023/4/15
 *
 * @author daoge_cmd
 */
public interface ComponentProvider<T extends Component> {
    static <T extends Component> ComponentProvider<T> of(Supplier<T> provider, Class<?> componentClass) {
        return new SimpleComponentProvider<>((info) -> provider.get(), componentClass);
    }

    static <T extends Component> ComponentProvider<T> of(Function<ComponentInitInfo, T> provider, Class<?> componentClass) {
        return new SimpleComponentProvider<>(provider, componentClass);
    }

    static <T extends Component> ComponentProvider<T> ofSingleton(T singleton) {
        return of((info) -> singleton, singleton.getClass());
    }

    static <P extends Component> Map<Identifier, ComponentProvider<? extends P>> toMap(List<ComponentProvider<? extends P>> componentProviders) {
        var map = new HashMap<Identifier, ComponentProvider<? extends P>>();
        componentProviders.forEach(componentProvider -> {
            var id = componentProvider.findComponentIdentifier();
            if (map.containsKey(id))
                throw new IllegalArgumentException("Duplicate component: " + id);
            map.put(id, componentProvider);
        });
        return map;
    }

    T provide(ComponentInitInfo info);

    Class<?> getComponentClass();

    @SneakyThrows
    @Nullable
    default Identifier findComponentIdentifier() {
        return findComponentIdentifier(getComponentClass());
    }

    @SneakyThrows
    @Nullable
    static Identifier findComponentIdentifier(Class<?> clazz) {
        Identifier identifier = null;
        while (identifier == null) {
            identifier = findComponentIdentifierInCertainClass(clazz);
            if (identifier == null) clazz = clazz.getSuperclass();
            if (clazz == null) break;
        }
        return identifier;
    }

    @SneakyThrows
    @Nullable
    static Identifier findComponentIdentifierInCertainClass(Class<?> clazz) {
        for (var field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ComponentIdentifier.class) && Identifier.class == field.getType() && isStatic(field.getModifiers())) {
                field.setAccessible(true);
                return (Identifier) field.get(null);
            }
        }
        return null;
    }

    @AllArgsConstructor
    class SimpleComponentProvider<T extends Component> implements ComponentProvider<T> {
        private Function<ComponentInitInfo, T> provider;
        @Getter
        private Class<?> componentClass;

        @Override
        public T provide(ComponentInitInfo info) {
            return provider.apply(info);
        }
    }

    @AllArgsConstructor
    class BlockEntityComponentProvider<T extends BlockEntity> implements ComponentProvider<BlockEntityComponent> {
        private Function<BlockEntityInitInfo<T>, BlockEntityComponent> provider;
        @Getter
        private Class<?> componentClass;

        @Override
        public BlockEntityComponent provide(ComponentInitInfo info) {
            return provider.apply((BlockEntityInitInfo<T>) info);
        }
    }

    @AllArgsConstructor
    class EntityComponentProvider<T extends Entity> implements ComponentProvider<EntityComponent> {
        private Function<EntityInitInfo<T>, EntityComponent> provider;
        @Getter
        private Class<?> componentClass;

        @Override
        public EntityComponent provide(ComponentInitInfo info) {
            return provider.apply((EntityInitInfo<T>) info);
        }
    }

    @AllArgsConstructor
    class ItemComponentProvider<T extends ItemStack> implements ComponentProvider<ItemComponent> {
        private Function<ItemStackInitInfo<T>, ItemComponent> provider;
        @Getter
        private Class<?> componentClass;

        @Override
        public ItemComponent provide(ComponentInitInfo info) {
            return provider.apply((ItemStackInitInfo<T>) info);
        }
    }
}
