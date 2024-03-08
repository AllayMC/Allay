package org.allaymc.api.entity.type;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.init.EntityInitInfo;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.allaymc.api.component.interfaces.ComponentProvider.toMap;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityTypeBuilder<T extends Entity, C extends EntityComponent> {
    ApiInstanceHolder<EntityTypeBuilderFactory> FACTORY = ApiInstanceHolder.create();

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

    default EntityTypeBuilder<T, C> addComponent(Supplier<EntityComponent> supplier, Class<?> componentClass) {
        return addComponent(unused -> supplier.get(), componentClass);
    }

    EntityTypeBuilder<T, C> addComponent(ComponentProvider<EntityComponent> componentProvider);

    EntityType<T> build();

    interface EntityTypeBuilderFactory {
        <T extends Entity> EntityTypeBuilder<T, EntityComponent> create(Class<T> clazz);
    }
}
