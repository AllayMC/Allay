package cn.allay.api.entity.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponent;
import cn.allay.api.identifier.Identifier;

import java.util.List;
import java.util.Map;

import static cn.allay.api.component.interfaces.ComponentProvider.toMap;

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

    default EntityTypeBuilder<T> setComponents(List<ComponentProvider<? extends EntityComponent>> componentProviders) {
        return setComponents(toMap(componentProviders));
    }

    EntityTypeBuilder<T> setComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders);


    default EntityTypeBuilder<T> addComponents(List<ComponentProvider<? extends EntityComponent>> componentProviders) {
        return addComponents(toMap(componentProviders));
    }

    EntityTypeBuilder<T> addComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders);

    EntityTypeBuilder<T> addComponent(ComponentProvider<? extends EntityComponent> componentProvider);

    EntityTypeBuilder<T> addBasicComponents();

    EntityType<T> build();

    interface EntityTypeBuilderFactory {
        <T extends Entity> EntityTypeBuilder<T> create(Class<T> clazz);
    }
}
