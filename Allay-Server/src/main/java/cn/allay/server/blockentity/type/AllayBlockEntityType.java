package cn.allay.server.blockentity.type;

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
import org.jetbrains.annotations.ApiStatus;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
    protected String blockEntityId;

    @SneakyThrows
    protected AllayBlockEntityType(Class<T> interfaceClass,
                                   List<ComponentProvider<? extends BlockEntityComponent>> componentProviders,
                                   String blockEntityId) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.blockEntityId = blockEntityId;
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

    @ApiStatus.Internal
    @SneakyThrows
    @Override
    public T createBlockEntity(BlockEntityInitInfo<T> info) {
        info.setBlockEntityType(this);
        return injectedClass.cast(constructorMethodHandle.invokeExact(info));
    }

    @Override
    public String getBlockEntityId() {
        return blockEntityId;
    }

    public static <T extends BlockEntity> BlockEntityTypeBuilder<T, BlockEntityComponent> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    public static class Builder<T extends BlockEntity> implements BlockEntityTypeBuilder<T, BlockEntityComponent> {
        protected Class<T> interfaceClass;
        protected Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders = new HashMap<>();
        protected String blockEntityId;

        public Builder(Class<T> interfaceClass) {
            this.interfaceClass = interfaceClass;
        }

        @Override
        public BlockEntityTypeBuilder<T, BlockEntityComponent> blockEntityId(String id) {
            this.blockEntityId = id;
            return this;
        }

        @Override
        public BlockEntityTypeBuilder<T, BlockEntityComponent> setComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders) {
            if (componentProviders == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        @Override
        public BlockEntityTypeBuilder<T, BlockEntityComponent> addComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        @Override
        public BlockEntityTypeBuilder<T, BlockEntityComponent> addComponent(Function<BlockEntityInitInfo<? extends BlockEntity>, BlockEntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.BlockEntityComponentProvider(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        @Override
        public BlockEntityType<T> build() {
            if (!componentProviders.containsKey(BlockEntityBaseComponentImpl.IDENTIFIER)) {
                addComponent(BlockEntityBaseComponentImpl::new, BlockEntityBaseComponentImpl.class);
            }
            if (blockEntityId == null)
                throw new EntityTypeBuildException("identifier cannot be null!");
            var type = new AllayBlockEntityType<>(interfaceClass, new ArrayList<>(componentProviders.values()), blockEntityId);
            BlockEntityTypeRegistry.getRegistry().register(blockEntityId, type);
            return type;
        }
    }
}
