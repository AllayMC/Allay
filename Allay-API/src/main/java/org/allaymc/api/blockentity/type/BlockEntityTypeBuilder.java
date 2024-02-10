package org.allaymc.api.blockentity.type;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.identifier.Identifier;

import java.util.Map;
import java.util.function.Function;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityTypeBuilder<T extends BlockEntity, C extends BlockEntityComponent> {
    ApiInstanceHolder<BlockEntityTypeBuilder.BlockEntityTypeBuilderFactory> FACTORY = ApiInstanceHolder.of();

    static <T extends BlockEntity> BlockEntityTypeBuilder<T, BlockEntityComponent> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    BlockEntityType<T> build();

    BlockEntityTypeBuilder<T, C> identifier(Identifier identifier);

    BlockEntityTypeBuilder<T, C> setComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders);

    BlockEntityTypeBuilder<T, C> addComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders);

    BlockEntityTypeBuilder<T, C> addComponent(Function<BlockEntityInitInfo<T>, C> provider, Class<?> componentClass);

    BlockEntityTypeBuilder<T, C> addComponent(ComponentProvider<BlockEntityComponent> componentProvider);

    interface BlockEntityTypeBuilderFactory {
        <T extends BlockEntity> BlockEntityTypeBuilder<T, BlockEntityComponent> create(Class<T> clazz);
    }
}
