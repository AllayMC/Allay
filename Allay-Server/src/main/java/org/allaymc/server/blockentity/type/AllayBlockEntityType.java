package org.allaymc.server.blockentity.type;

import lombok.Getter;
import lombok.SneakyThrows;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.block.type.BlockTypeBuildException;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.injector.AllayComponentInjector;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.entity.type.EntityTypeBuildException;
import org.allaymc.server.utils.ComponentClassCacheUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public class AllayBlockEntityType<T extends BlockEntity> implements BlockEntityType<T> {

    protected final FastConstructor<T> constructor;
    @Getter
    protected String name;

    @SneakyThrows
    protected AllayBlockEntityType(FastConstructor<T> constructor, String name) {
        this.constructor = constructor;
        this.name = name;
    }

    public static <T extends BlockEntity> Builder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @SneakyThrows
    @Override
    public T createBlockEntity(BlockEntityInitInfo info) {
        info.setBlockEntityType(this);
        return (T) constructor.invoke(info);
    }

    public static class Builder<T extends BlockEntity> {

        protected Class<T> interfaceClass;
        protected Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders = new HashMap<>();
        protected String name;

        public Builder(Class<T> interfaceClass) {
            this.interfaceClass = interfaceClass;
        }

        public Builder<T> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<T> setComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders) {
            if (componentProviders == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        public Builder<T> addComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        public Builder<T> addComponent(Function<BlockEntityInitInfo, ? extends BlockEntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.SimpleComponentProvider<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder<T> addComponent(Supplier<? extends BlockEntityComponent> supplier, Class<?> componentClass) {
            return addComponent(info -> supplier.get(), componentClass);
        }

        public Builder<T> addComponent(ComponentProvider<? extends BlockEntityComponent> p) {
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public BlockEntityType<T> build() {
            if (!componentProviders.containsKey(BlockEntityBaseComponentImpl.IDENTIFIER)) {
                addComponent(BlockEntityBaseComponentImpl::new, BlockEntityBaseComponentImpl.class);
            }
            if (name == null) throw new EntityTypeBuildException("name cannot be null!");

            FastConstructor<T> constructor;
            try {
                var injectedClass = new AllayComponentInjector<T>()
                        .interfaceClass(interfaceClass)
                        .component(new ArrayList<>(componentProviders.values()))
                        .useCachedClass(ComponentClassCacheUtils.getCacheClass(interfaceClass))
                        .inject(false);


                var fastMemberLoader = new FastMemberLoader(Allay.EXTRA_RESOURCE_CLASS_LOADER);
                constructor = FastConstructor.create(
                        injectedClass.getConstructor(ComponentInitInfo.class),
                        fastMemberLoader,
                        false
                );
            } catch (Exception e) {
                throw new EntityTypeBuildException("Failed to create block entity type!", e);
            }

            var type = new AllayBlockEntityType<>(constructor, name);
            Registries.BLOCK_ENTITIES.register(name, type);
            return type;
        }
    }
}
