package org.allaymc.server.blockentity.type;

import lombok.Getter;
import lombok.SneakyThrows;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.blockentity.registry.BlockEntityTypeRegistry;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.block.type.BlockTypeBuildException;
import org.allaymc.server.blockentity.component.common.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.injector.AllayComponentInjector;
import org.allaymc.server.entity.type.EntityTypeBuildException;
import org.allaymc.server.utils.ComponentClassCacheUtils;
import org.jetbrains.annotations.ApiStatus;

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

    protected final FastConstructor<T> constructor;
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    @Getter
    protected List<ComponentProvider<? extends BlockEntityComponent>> componentProviders;
    @Getter
    protected String name;

    @SneakyThrows
    protected AllayBlockEntityType(Class<T> interfaceClass,
                                   List<ComponentProvider<? extends BlockEntityComponent>> componentProviders,
                                   String name) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.name = name;
        try {
            ArrayList<ComponentProvider<? extends Component>> components = new ArrayList<>(componentProviders);
            injectedClass = new AllayComponentInjector<T>()
                    .interfaceClass(interfaceClass)
                    .component(components)
                    .useCachedClass(ComponentClassCacheUtils.getCacheClass(interfaceClass))
                    .inject(false);
        } catch (Exception e) {
            throw new EntityTypeBuildException("Failed to create block entity type!", e);
        }
        FastMemberLoader fastMemberLoader = new FastMemberLoader(Allay.EXTRA_RESOURCE_CLASS_LOADER);
        this.constructor = FastConstructor.create(injectedClass.getConstructor(ComponentInitInfo.class), fastMemberLoader, false);
    }

    public static <T extends BlockEntity> BlockEntityTypeBuilder<T, BlockEntityComponent> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @ApiStatus.Internal
    @SneakyThrows
    @Override
    public T createBlockEntity(BlockEntityInitInfo<T> info) {
        info.setBlockEntityType(this);
        return (T) constructor.invoke(info);
    }

    public static class Builder<T extends BlockEntity> implements BlockEntityTypeBuilder<T, BlockEntityComponent> {

        protected Class<T> interfaceClass;
        protected Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders = new HashMap<>();
        protected String name;

        public Builder(Class<T> interfaceClass) {
            this.interfaceClass = interfaceClass;
        }

        @Override
        public BlockEntityTypeBuilder<T, BlockEntityComponent> name(String name) {
            this.name = name;
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
        public BlockEntityTypeBuilder<T, BlockEntityComponent> addComponent(Function<BlockEntityInitInfo<T>, BlockEntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.SimpleComponentProvider<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        @Override
        public BlockEntityTypeBuilder<T, BlockEntityComponent> addComponent(ComponentProvider<BlockEntityComponent> p) {
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        @Override
        public BlockEntityType<T> build() {
            if (!componentProviders.containsKey(BlockEntityBaseComponentImpl.IDENTIFIER)) {
                addComponent(BlockEntityBaseComponentImpl::new, BlockEntityBaseComponentImpl.class);
            }
            if (name == null) throw new EntityTypeBuildException("name cannot be null!");
            var type = new AllayBlockEntityType<>(interfaceClass, new ArrayList<>(componentProviders.values()), name);
            BlockEntityTypeRegistry.getRegistry().register(name, type);
            return type;
        }
    }
}
