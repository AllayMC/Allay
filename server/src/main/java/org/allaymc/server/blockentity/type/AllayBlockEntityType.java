package org.allaymc.server.blockentity.type;

import com.google.common.base.Preconditions;
import lombok.Getter;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class AllayBlockEntityType<T extends BlockEntity> implements BlockEntityType<T> {

    protected final Function<BlockEntityInitInfo, T> instanceCreator;
    @Getter
    protected String name;

    protected AllayBlockEntityType(Function<BlockEntityInitInfo, T> instanceCreator, String name) {
        this.instanceCreator = instanceCreator;
        this.name = name;
    }

    public static <T extends BlockEntity> Builder builder(Class<T> clazz) {
        return new Builder(clazz);
    }

    @Override
    public T createBlockEntity(BlockEntityInitInfo info) {
        info.setBlockEntityType(this);
        return instanceCreator.apply(info);
    }

    public static class Builder {
        protected Class<?> clazz;
        protected Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders = new HashMap<>();
        protected String name;

        public Builder(Class<?> clazz) {
            this.clazz = clazz;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder setComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> providers) {
            Preconditions.checkNotNull(providers, "providers");
            this.componentProviders = new HashMap<>(providers);
            return this;
        }

        public Builder addComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> providers) {
            this.componentProviders.putAll(providers);
            return this;
        }

        public Builder addComponent(Function<BlockEntityInitInfo, ? extends BlockEntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.Simple<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder addComponent(Supplier<? extends BlockEntityComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        public Builder addComponent(ComponentProvider<? extends BlockEntityComponent> provider) {
            this.componentProviders.put(provider.findComponentIdentifier(), provider);
            return this;
        }

        public <T extends BlockEntity> BlockEntityType<T> build() {
            Preconditions.checkNotNull(name, "name");
            if (!componentProviders.containsKey(BlockEntityBaseComponentImpl.IDENTIFIER)) {
                addComponent(BlockEntityBaseComponentImpl::new, BlockEntityBaseComponentImpl.class);
            }

            Function<BlockEntityInitInfo, T> instanceCreator;
            try {
                var constructor = FastConstructor.create(
                        clazz.getConstructor(BlockEntityInitInfo.class, List.class),
                        new FastMemberLoader(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getClassLoader()),
                        false
                );
                var componentProviderList = new ArrayList<>(componentProviders.values());
                instanceCreator = info -> {
                    try {
                        return (T) constructor.invoke(info, componentProviderList);
                    } catch (Throwable t) {
                        throw new BlockEntityTypeBuildException("Failed to create block entity instance!", t);
                    }
                };
            } catch (Exception e) {
                throw new BlockEntityTypeBuildException("Failed to create block entity type!", e);
            }

            var type = new AllayBlockEntityType<>(instanceCreator, name);
            Registries.BLOCK_ENTITIES.register(name, type);
            return type;
        }
    }
}
