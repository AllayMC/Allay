package org.allaymc.api.blockentity.type;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.utils.Identifier;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityTypeBuilder<T extends BlockEntity, C extends BlockEntityComponent> {
    ApiInstanceHolder<BlockEntityTypeBuilder.BlockEntityTypeBuilderFactory> FACTORY = ApiInstanceHolder.create();

    static <T extends BlockEntity> BlockEntityTypeBuilder<T, BlockEntityComponent> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    BlockEntityType<T> build();

    BlockEntityTypeBuilder<T, C> name(String name);

    BlockEntityTypeBuilder<T, C> setComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders);

    BlockEntityTypeBuilder<T, C> addComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders);

    BlockEntityTypeBuilder<T, C> addComponent(Function<BlockEntityInitInfo<T>, C> provider, Class<?> componentClass);

    default BlockEntityTypeBuilder<T, C> addComponent(Supplier<C> supplier, Class<?> componentClass) {
        return addComponent(info -> supplier.get(), componentClass);
    }

    BlockEntityTypeBuilder<T, C> addComponent(ComponentProvider<BlockEntityComponent> componentProvider);

    interface BlockEntityTypeBuilderFactory {
        <T extends BlockEntity> BlockEntityTypeBuilder<T, BlockEntityComponent> create(Class<T> clazz);
    }
}
