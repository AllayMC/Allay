package org.allaymc.server.blockentity.type;

import lombok.Getter;
import lombok.SneakyThrows;
import me.sunlan.fastreflection.FastConstructor;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.entity.type.EntityTypeBuildException;

import java.util.ArrayList;
import java.util.HashMap;
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

    @SneakyThrows
    protected AllayBlockEntityType(Function<BlockEntityInitInfo, T> instanceCreator, String name) {
        this.instanceCreator = instanceCreator;
        this.name = name;
    }

    public static <T extends BlockEntity> Builder builder(Class<T> interfaceClass) {
        return new Builder(interfaceClass);
    }

    @SneakyThrows
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

        public Builder setComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders) {
            if (componentProviders == null)
                throw new BlockEntityTypeBuildException("Component providers cannot be null");
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        public Builder addComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        public Builder addComponent(Function<BlockEntityInitInfo, ? extends BlockEntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.SimpleComponentProvider<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder addComponent(Supplier<? extends BlockEntityComponent> supplier, Class<?> componentClass) {
            return addComponent(info -> supplier.get(), componentClass);
        }

        public Builder addComponent(ComponentProvider<? extends BlockEntityComponent> p) {
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public <T extends BlockEntity> BlockEntityType<T> build() {
            if (!componentProviders.containsKey(BlockEntityBaseComponentImpl.IDENTIFIER)) {
                addComponent(BlockEntityBaseComponentImpl::new, BlockEntityBaseComponentImpl.class);
            }
            if (name == null) throw new EntityTypeBuildException("name cannot be null!");

            Function<BlockEntityInitInfo, T> instanceCreator;
            try {
                var fastMemberConstructor = FastConstructor.create(clazz.getConstructors()[0]);
                var componentProviderList = new ArrayList<>(componentProviders.values());
                instanceCreator = info -> {
                    try {
                        return (T) fastMemberConstructor.invoke(info, componentProviderList);
                    } catch (Throwable t) {
                        throw new EntityTypeBuildException("Failed to create block entity instance!", t);
                    }
                };
            } catch (Exception e) {
                throw new EntityTypeBuildException("Failed to create block entity type!", e);
            }

            var type = new AllayBlockEntityType<>(instanceCreator, name);
            Registries.BLOCK_ENTITIES.register(name, type);
            return type;
        }
    }
}
