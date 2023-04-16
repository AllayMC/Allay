package cn.allay.block.component.injector;

import cn.allay.block.Block;
import cn.allay.block.component.annotation.RequireBlockProperty;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.type.BlockType;
import cn.allay.component.exception.BlockComponentInjectException;
import cn.allay.component.injector.AllayComponentInjector;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.ComponentProvider;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;

import java.util.List;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
 */
public class AllayBlockComponentInjector<T extends Block> extends AllayComponentInjector<T> {

    protected Map<String, BlockPropertyType<?>> mappedProperties;
    protected BlockType<T> type;

    public AllayBlockComponentInjector(BlockType<T> type) {
        super();
        this.mappedProperties = type.getMappedProperties();
        this.type = type;
    }

    @Override
    public Class<T> inject() {
        checkPropertyValid();
        return super.inject();
    }

    @Override
    protected DynamicType.Builder<T> afterInject(List<ComponentProvider<? extends ComponentImpl>> providers, DynamicType.Builder<T> bb) {
        //Implement BlockType<? extends Block> getBlockType();
        return super.afterInject(providers, bb)
                .method(named("getBlockType"))
                .intercept(FixedValue.value(type));
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
