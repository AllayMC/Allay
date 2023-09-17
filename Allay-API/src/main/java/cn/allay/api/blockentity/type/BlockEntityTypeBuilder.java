package cn.allay.api.blockentity.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.BlockEntityComponent;
import cn.allay.api.blockentity.init.BlockEntityInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.identifier.Identifier;

import java.util.Map;
import java.util.function.Function;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityTypeBuilder<T extends BlockEntity, C extends BlockEntityComponent> {
    ApiInstanceHolder<BlockEntityTypeBuilder.BlockEntityTypeBuilderFactory> FACTORY = ApiInstanceHolder.of();

    static <T extends BlockEntity, C extends BlockEntityComponent> BlockEntityTypeBuilder<T, C> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    BlockEntityType<T> build();

    BlockEntityTypeBuilder<T, C> blockEntityId(String id);

    BlockEntityTypeBuilder<T, C> setComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders);

    BlockEntityTypeBuilder<T, C> addComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders);

    BlockEntityTypeBuilder<T, C> addComponent(Function<BlockEntityInitInfo<? extends BlockEntity>, C> provider, Class<?> componentClass);

    interface BlockEntityTypeBuilderFactory {
        <T extends BlockEntity, C extends BlockEntityComponent> BlockEntityTypeBuilder<T, C> create(Class<T> clazz);
    }
}
