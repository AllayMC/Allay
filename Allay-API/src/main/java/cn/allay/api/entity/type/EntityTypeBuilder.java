package cn.allay.api.entity.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponent;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.identifier.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static cn.allay.api.component.interfaces.ComponentProvider.toMap;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityTypeBuilder<T extends Entity, C extends EntityComponent> {
    ApiInstanceHolder<EntityTypeBuilderFactory> FACTORY = ApiInstanceHolder.of();

    static <T extends Entity> EntityTypeBuilder<T, EntityComponent> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    EntityTypeBuilder<T, C> identifier(Identifier identifier);

    EntityTypeBuilder<T, C> identifier(String identifier);

    EntityTypeBuilder<T, C> vanillaEntity(VanillaEntityId vanillaEntityId);

    default EntityTypeBuilder<T, C> setComponents(List<ComponentProvider<? extends EntityComponent>> componentProviders) {
        return setComponents(toMap(componentProviders));
    }

    EntityTypeBuilder<T, C> setComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders);


    default EntityTypeBuilder<T, C> addComponents(List<ComponentProvider<? extends EntityComponent>> componentProviders) {
        return addComponents(toMap(componentProviders));
    }

    EntityTypeBuilder<T, C> addComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders);

    EntityTypeBuilder<T, C> addComponent(Function<EntityInitInfo<T>, EntityComponent> provider, Class<?> componentClass);

    EntityType<T> build();

    interface EntityTypeBuilderFactory {
        <T extends Entity> EntityTypeBuilder<T, EntityComponent> create(Class<T> clazz);
    }
}
