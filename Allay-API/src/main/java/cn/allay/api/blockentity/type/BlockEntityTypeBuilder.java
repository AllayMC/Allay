package cn.allay.api.blockentity.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.BlockEntityComponent;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.identifier.Identifier;

import java.util.List;
import java.util.Map;

import static cn.allay.api.component.interfaces.ComponentProvider.toMap;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityTypeBuilder<T extends BlockEntity> {
    ApiInstanceHolder<BlockEntityTypeBuilder.BlockEntityTypeBuilderFactory> FACTORY = ApiInstanceHolder.of();

    BlockEntityType<T> build();

    BlockEntityTypeBuilder<T> identifier(Identifier identifier);

    BlockEntityTypeBuilder<T> identifier(String identifier);

    default BlockEntityTypeBuilder<T> setComponents(List<ComponentProvider<? extends BlockEntityComponent>> componentProviders) {
        return setComponents(toMap(componentProviders));
    }

    BlockEntityTypeBuilder<T> setComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders);

    default BlockEntityTypeBuilder<T> addComponents(List<ComponentProvider<? extends BlockEntityComponent>> componentProviders) {
        return addComponents(toMap(componentProviders));
    }

    BlockEntityTypeBuilder<T> addComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders);

    BlockEntityTypeBuilder<T> addComponent(ComponentProvider<? extends BlockEntityComponent> componentProvider);

    interface BlockEntityTypeBuilderFactory {
        <T extends BlockEntity> BlockEntityTypeBuilder<T> create(Class<T> clazz);
    }
}
