package org.allaymc.api.entity.type;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.utils.Identifier;

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
public interface EntityTypeBuilder<T extends Entity> {
    ApiInstanceHolder<EntityTypeBuilderFactory> FACTORY = ApiInstanceHolder.create();

    static <T extends Entity> EntityTypeBuilder<T> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    EntityTypeBuilder<T> identifier(Identifier identifier);

    EntityTypeBuilder<T> identifier(String identifier);

    EntityTypeBuilder<T> vanillaEntity(EntityId entityId);

    default EntityTypeBuilder<T> setComponents(List<ComponentProvider<? extends EntityComponent>> componentProviders) {
        return setComponents(toMap(componentProviders));
    }

    EntityTypeBuilder<T> setComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders);


    default EntityTypeBuilder<T> addComponents(List<ComponentProvider<? extends EntityComponent>> componentProviders) {
        return addComponents(toMap(componentProviders));
    }

    EntityTypeBuilder<T> addComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders);

    EntityTypeBuilder<T> addComponent(Function<EntityInitInfo, ? extends EntityComponent> provider, Class<?> componentClass);

    default EntityTypeBuilder<T> addComponent(Supplier<? extends EntityComponent> supplier, Class<?> componentClass) {
        return addComponent($ -> supplier.get(), componentClass);
    }

    EntityTypeBuilder<T> addComponent(ComponentProvider<? extends EntityComponent> componentProvider);

    EntityType<T> build();

    interface EntityTypeBuilderFactory {
        <T extends Entity> EntityTypeBuilder<T> create(Class<T> clazz);
    }
}
