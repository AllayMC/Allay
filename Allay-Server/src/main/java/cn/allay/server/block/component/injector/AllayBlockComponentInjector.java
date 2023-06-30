package cn.allay.server.block.component.injector;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.annotation.RequireBlockProperty;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.block.type.BlockType;
import cn.allay.server.component.exception.BlockComponentInjectException;
import cn.allay.server.component.injector.AllayComponentInjector;

import java.util.Map;

/**
 * Allay Project 2023/4/15
 *
 * @author daoge_cmd
 */
public class AllayBlockComponentInjector<T extends Block> extends AllayComponentInjector<T> {

    protected Map<String, BlockPropertyType<?>> mappedProperties;
    protected BlockType<T> type;

    public AllayBlockComponentInjector(BlockType<T> type) {
        super();
        this.mappedProperties = type.getProperties();
        this.type = type;
    }

    @Override
    public Class<T> inject() {
        checkPropertyValid();
        return super.inject();
    }

    protected void checkPropertyValid() {
        for (var provider : componentProviders) {
            var annotation = provider.getComponentClass().getAnnotation(RequireBlockProperty.Requirements.class);
            if (annotation == null) continue;
            var requirements = annotation.value();
            for (var requirement : requirements) {
                var type = requirement.type();
                var name = requirement.name();
                var match = mappedProperties.get(name);
                if (match == null)
                    throw new BlockComponentInjectException("Component " + provider.getComponentClass().getSimpleName() + " expects a block property of type " + type + " named " + name + ", but there is no match");
                if (match.getType() != type)
                    throw new BlockComponentInjectException("Component " + provider.getComponentClass().getSimpleName() + " expects a block property of type " + type + " named " + name + ", but the type is " + mappedProperties.get(name).getType());
            }
        }
    }
}
