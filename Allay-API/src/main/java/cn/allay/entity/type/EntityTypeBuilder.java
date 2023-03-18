package cn.allay.entity.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.entity.Entity;
import cn.allay.entity.definition.EntityDefinition;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/18 <br>
 * Allay Project <br>
 */
public interface EntityTypeBuilder {

    ApiInstanceHolder<EntityTypeBuilder> BUILDER = ApiInstanceHolder.create();

     static EntityTypeBuilder builder() {
        return BUILDER.get();
    }

    <T extends Entity> EntityType<T> build(EntityDefinition<T> definition);
}
