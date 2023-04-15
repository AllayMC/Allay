package cn.allay.block.component.injector;

import cn.allay.block.Block;
import cn.allay.block.component.annotation.RequireBlockProperty;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.type.BlockType;
import cn.allay.component.annotation.Impl;
import cn.allay.component.annotation.Inject;
import cn.allay.component.exception.BlockComponentInjectException;
import cn.allay.component.exception.ComponentInjectException;
import cn.allay.component.injector.AllayComponentInjector;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.ComponentProvider;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
 */
public class AllayBlockComponentInjector<T extends Block> extends AllayComponentInjector<T> {

//    protected Map<String, BlockPropertyType<?>> mappedProperties;
//    protected BlockType<T> type;
//
//    public AllayBlockComponentInjector(BlockType<T> type) {
//        super();
//        this.mappedProperties = type.getMappedProperties();
//        this.type = type;
//    }
//
//    @Override
//    public Class<T> inject() {
//        checkComponentValid();
//        checkPropertyValid();
//        for (var component : components) {
//            injectDependency(component);
//        }
//        var bb = new ByteBuddy().subclass(parentClass);
//        var fieldNameMapping = new HashMap<ComponentProvider<?>, String>();
//        int num = 0;
//        for (var provider : componentProviders) {
//            bb = bb.defineField("f" + num++, provider.provide().getClass(), Visibility.PRIVATE);
//        }
//        //TODO: 构造函数入参
//        bb = bb.defineConstructor(Modifier.PUBLIC)
//                .intercept(MethodDelegation.to(new Object() {
//                    public void intercept(@SuperCall Callable<?> superCall, @Origin Constructor<?> constructor) throws Exception {
//                        superCall.call();
//                        //TODO: 有一些检查可以在构建类的时候完成，这边有待优化
//                        List<? extends ComponentImpl> components = componentProviders.stream().map(ComponentProvider::provide).toList();
//                        checkComponentValid(components);
//                        for (var component : components) {
//                            injectDependency(components, component);
//                        }
//                        for (var provider : componentProviders) {
//                            constructor.getDeclaringClass().getDeclaredField(fieldNameMapping.get(provider)).set(constructor.newInstance(), provider.provide());
//                        }
//                    }
//                }));
//        for (var methodShouldBeInject : Arrays.stream(parentClass.getMethods()).filter(m -> m.isAnnotationPresent(Inject.class)).toList()) {
//            var canDuplicate = methodShouldBeInject.getReturnType().equals(Void.class);
//            Implementation.Composable methodDelegation = null;
//            for (var component: components) {
//                try {
//                    Method methodImpl = component.getClass().getMethod(methodShouldBeInject.getName(), methodShouldBeInject.getParameterTypes());
//                    if (!methodImpl.isAnnotationPresent(Impl.class)) continue;
//                    if (methodDelegation == null) methodDelegation = MethodDelegation.to(component);
//                    else if (canDuplicate) methodDelegation = methodDelegation.andThen(MethodDelegation.to(component));
//                    else throw new ComponentInjectException("Duplicate implementation for non-void-return method: " + methodShouldBeInject.getName() + " in " + component.getClass().getName());
//                } catch (NoSuchMethodException ignored) {}
//            }
//            if (methodDelegation == null)
//                throw new ComponentInjectException("Missing implementation for method: " + methodShouldBeInject.getName());
//            bb = bb.method(ElementMatchers.is(methodShouldBeInject))
//                        .intercept(methodDelegation);
//        }
//        bb = afterInject(components, bb);
//        try (var unloaded = bb.make()) {
//            return (Class<T>) unloaded
//                    .load(getClass().getClassLoader())
//                    .getLoaded();
//        } catch (IOException e) {
//            throw new ComponentInjectException(e);
//        }
//    }
//
//    @Override
//    protected DynamicType.Builder<T> afterInject(DynamicType.Builder<T> bb) {
//        //Implement BlockType<? extends Block> getBlockType();
//        return super.afterInject(bb).method(named("getBlockType"))
//                .intercept(FixedValue.value(type));
//    }
//
//    protected void checkPropertyValid() {
//        for (var component : components) {
//            var requirements = component.getClass().getAnnotation(RequireBlockProperty.Requirements.class).value();
//            for (var requirement : requirements) {
//                var type = requirement.type();
//                var name = requirement.name();
//                var match = mappedProperties.get(name);
//                if (match == null)
//                    throw new BlockComponentInjectException("Component " + component.getNamespaceId() + " expects a block property of type " + type + " named " + name + ", but there is no match");
//                if (match.getType() != type)
//                    throw new BlockComponentInjectException("Component " + component.getNamespaceId() + " expects a block property of type " + type + " named " + name + ", but the type is " + mappedProperties.get(name).getType());
//            }
//        }
//    }
}
