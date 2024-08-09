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
public interface BlockEntityTypeBuilder<T extends BlockEntity> {
    ApiInstanceHolder<BlockEntityTypeBuilder.BlockEntityTypeBuilderFactory> FACTORY = ApiInstanceHolder.create();

    static <T extends BlockEntity> BlockEntityTypeBuilder<T> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    BlockEntityType<T> build();

    BlockEntityTypeBuilder<T> name(String name);

    BlockEntityTypeBuilder<T> setComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders);

    BlockEntityTypeBuilder<T> addComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders);

    BlockEntityTypeBuilder<T> addComponent(Function<BlockEntityInitInfo, ? extends BlockEntityComponent> provider, Class<?> componentClass);

    default BlockEntityTypeBuilder<T> addComponent(Supplier<? extends BlockEntityComponent> supplier, Class<?> componentClass) {
        return addComponent(info -> supplier.get(), componentClass);
    }

    BlockEntityTypeBuilder<T> addComponent(ComponentProvider<? extends BlockEntityComponent> componentProvider);

    interface BlockEntityTypeBuilderFactory {
        <T extends BlockEntity> BlockEntityTypeBuilder<T> create(Class<T> clazz);
    }
}
