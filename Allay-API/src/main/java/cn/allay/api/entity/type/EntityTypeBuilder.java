package cn.allay.api.entity.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.identifier.Identifier;

import java.util.List;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityTypeBuilder<T extends Entity> {
    ApiInstanceHolder<EntityTypeBuilderFactory> FACTORY = ApiInstanceHolder.of();

    static <T extends Entity> EntityTypeBuilder<T> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    EntityTypeBuilder<T> namespace(Identifier identifier);

    EntityTypeBuilder<T> namespace(String identifier);

    EntityTypeBuilder<T> vanillaEntity(VanillaEntityId vanillaEntityId);

    EntityTypeBuilder<T> setComponents(List<ComponentProvider<? extends EntityComponentImpl>> componentProviders);

    EntityTypeBuilder<T> addComponents(List<ComponentProvider<? extends EntityComponentImpl>> componentProviders);

    EntityTypeBuilder<T> addComponent(ComponentProvider<? extends EntityComponentImpl> componentProvider);

    EntityTypeBuilder<T> addBasicComponents();

    EntityType<T> build();

    interface EntityTypeBuilderFactory {
        <T extends Entity> EntityTypeBuilder<T> create(Class<T> clazz);
    }
}
