package cn.allay.block.component.injector;

import cn.allay.block.Block;
import cn.allay.block.component.annotation.RequireBlockProperty;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.component.exception.BlockComponentInjectException;
import cn.allay.component.injector.AllayComponentInjector;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
 */
public class AllayBlockComponentInjector<T extends Block> extends AllayComponentInjector<T> {

    protected Map<String, BlockPropertyType<?>> properties;

    public AllayBlockComponentInjector(List<BlockPropertyType<?>> properties) {
        super();
        this.properties = properties.stream().collect(Collectors.toMap(BlockPropertyType::getName, Function.identity()));
    }

    @Override
    public Class<T> inject() {
        checkComponentValid();
        checkPropertyValid();
        for (var component : components) {
            injectDependency(component);
        }
        return buildClass();
    }

    protected void checkPropertyValid() {
        for (var component : components) {
            var requirements = component.getClass().getAnnotation(RequireBlockProperty.Requirements.class).value();
            for (var requirement : requirements) {
                var type = requirement.type();
                var name = requirement.name();
                var match = properties.get(name);
                if (match == null)
                    throw new BlockComponentInjectException("Component " + component.getNamespaceId() + " expects a block property of type " + type + " named " + name + ", but there is no match");
                if (match.getType() != type)
                    throw new BlockComponentInjectException("Component " + component.getNamespaceId() + " expects a block property of type " + type + " named " + name + ", but the type is " + properties.get(name).getType());
            }
        }
    }
}
