package cn.allay.server.blockentity.type;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.BlockEntityComponent;
import cn.allay.api.blockentity.component.base.BlockEntityBaseComponentImpl;
import cn.allay.api.blockentity.init.BlockEntityInitInfo;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.blockentity.type.BlockEntityTypeBuilder;
import cn.allay.api.blockentity.type.BlockEntityTypeRegistry;
import cn.allay.api.component.interfaces.Component;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.block.type.BlockTypeBuildException;
import cn.allay.server.component.injector.AllayComponentInjector;
import cn.allay.server.entity.type.EntityTypeBuildException;
import cn.allay.server.utils.ComponentClassCacheUtils;
import lombok.SneakyThrows;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public class AllayBlockEntityType<T extends BlockEntity> implements BlockEntityType<T> {
    protected final MethodHandle constructorMethodHandle;
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected List<ComponentProvider<? extends BlockEntityComponent>> componentProviders;
    protected Identifier identifier;

    @SneakyThrows
    protected AllayBlockEntityType(Class<T> interfaceClass,
                                   List<ComponentProvider<? extends BlockEntityComponent>> componentProviders,
                                   Identifier identifier) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.identifier = identifier;
        try {
            ArrayList<ComponentProvider<? extends Component>> components = new ArrayList<>(componentProviders);
            injectedClass = new AllayComponentInjector<T>()
                    .interfaceClass(interfaceClass)
                    .component(components)
                    .useCachedClass(ComponentClassCacheUtils.loadBlockEntityType(interfaceClass))
                    .inject(true);
        } catch (Exception e) {
            throw new EntityTypeBuildException("Failed to create block entity type!", e);
        }
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(void.class, ComponentInitInfo.class);
        //Cache constructor Method Handle
        var temp = lookup.findConstructor(injectedClass, methodType);
        constructorMethodHandle = temp.asType(temp.type().changeParameterType(0, BlockEntityInitInfo.class).changeReturnType(Object.class));
    }

    @Override
    public List<ComponentProvider<? extends BlockEntityComponent>> getComponentProviders() {
        return componentProviders;
    }

    @SneakyThrows
    @Override
    public T createEntity(BlockEntityInitInfo<T> info) {
        info.setBlockEntityType(this);
        return injectedClass.cast(constructorMethodHandle.invokeExact(info));
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    public static <T extends BlockEntity> BlockEntityTypeBuilder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    public static class Builder<T extends BlockEntity> implements BlockEntityTypeBuilder<T> {
        protected Class<T> interfaceClass;
        protected Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders = new HashMap<>();
        protected Identifier identifier;

        public Builder(Class<T> interfaceClass) {
            this.interfaceClass = interfaceClass;
        }

        @Override
        public BlockEntityTypeBuilder<T> identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        @Override
        public BlockEntityTypeBuilder<T> identifier(String identifier) {
            this.identifier = new Identifier(identifier);
            return this;
        }

        @Override
        public BlockEntityTypeBuilder<T> setComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders) {
            if (componentProviders == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        @Override
        public BlockEntityTypeBuilder<T> addComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        @Override
        public BlockEntityTypeBuilder<T> addComponent(ComponentProvider<? extends BlockEntityComponent> componentProvider) {
            this.componentProviders.put(componentProvider.findComponentIdentifier(), componentProvider);
            return this;
        }

        @Override
        public BlockEntityType<T> build() {
            if (!componentProviders.containsKey(BlockEntityBaseComponentImpl.IDENTIFIER))
                addComponent(ComponentProvider.of(
                        info -> new BlockEntityBaseComponentImpl<>((BlockEntityInitInfo<T>) info),
                        BlockEntityBaseComponentImpl.class
                ));
            if (identifier == null)
                throw new EntityTypeBuildException("identifier cannot be null!");
            var type = new AllayBlockEntityType<>(interfaceClass, new ArrayList<>(componentProviders.values()), identifier);
            BlockEntityTypeRegistry.getRegistry().register(identifier, type);
            return type;
        }
    }
}
