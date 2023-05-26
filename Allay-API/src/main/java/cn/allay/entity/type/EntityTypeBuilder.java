package cn.allay.entity.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.component.interfaces.ComponentProvider;
import cn.allay.entity.Entity;
import cn.allay.entity.component.EntityComponentImpl;
import cn.allay.entity.data.VanillaEntityId;
import cn.allay.identifier.Identifier;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/20 <br>
 * Allay Project <br>
 */
public interface EntityTypeBuilder<T extends Entity> {
    ApiInstanceHolder<EntityTypeBuilderFactory> FACTORY = ApiInstanceHolder.create();

    static <T extends Entity> EntityTypeBuilder<T> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    EntityTypeBuilder<T> namespace(Identifier identifier);

    EntityTypeBuilder<T> namespace(String namespaceId);

    EntityTypeBuilder<T> vanillaEntity(VanillaEntityId vanillaEntityId);

    EntityTypeBuilder<T> setComponents(List<ComponentProvider<? extends EntityComponentImpl>> componentProviders);

    EntityTypeBuilder<T> addComponents(List<ComponentProvider<? extends EntityComponentImpl>> componentProviders);

    EntityTypeBuilder<T> addBasicComponents();

    EntityType<T> build();

    interface EntityTypeBuilderFactory {
        <T extends Entity> EntityTypeBuilder<T> create(Class<T> clazz);
    }
}
