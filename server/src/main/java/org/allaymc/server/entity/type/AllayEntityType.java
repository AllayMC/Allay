package org.allaymc.server.entity.type;

import lombok.Getter;
import lombok.SneakyThrows;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.block.type.BlockTypeBuildException;
import org.allaymc.server.component.injector.AllayComponentInjector;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.allaymc.server.component.interfaces.ComponentProvider.toMap;

/**
 * @author daoge_cmd
 */
public class AllayEntityType<T extends Entity> implements EntityType<T> {
    protected final FastConstructor<T> constructor;
    @Getter
    protected Identifier identifier;

    @SneakyThrows
    protected AllayEntityType(
            FastConstructor<T> constructor,
            Identifier identifier
    ) {
        this.constructor = constructor;
        this.identifier = identifier;
    }

    public static <T extends Entity> Builder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @SneakyThrows
    @Override
    public T createEntity(EntityInitInfo info) {
        info.setEntityType(this);
        return (T) constructor.invoke(info);
    }

    public static class Builder<T extends Entity> {
        protected Class<T> interfaceClass;
        protected Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders = new HashMap<>();
        protected Identifier identifier;

        public Builder(Class<T> interfaceClass) {
            this.interfaceClass = interfaceClass;
        }

        public Builder<T> identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder<T> identifier(String identifier) {
            this.identifier = new Identifier(identifier);
            return this;
        }

        public Builder<T> vanillaEntity(EntityId entityId) {
            this.identifier = entityId.getIdentifier();
            return this;
        }

        public Builder<T> setComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders) {
            if (componentProviders == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        public Builder<T> addComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        public Builder<T> addComponent(Function<EntityInitInfo, ? extends EntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.SimpleComponentProvider<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder<T> addComponent(ComponentProvider<? extends EntityComponent> p) {
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder<T> setComponents(List<ComponentProvider<? extends EntityComponent>> componentProviders) {
            return setComponents(toMap(componentProviders));
        }

        public Builder<T> addComponents(List<ComponentProvider<? extends EntityComponent>> componentProviders) {
            return addComponents(toMap(componentProviders));
        }

        public Builder<T> addComponent(Supplier<? extends EntityComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        public EntityType<T> build() {
            if (!componentProviders.containsKey(EntityBaseComponentImpl.IDENTIFIER)) {
                addComponent(EntityBaseComponentImpl::new, EntityBaseComponentImpl.class);
            }
            if (identifier == null) {
                throw new EntityTypeBuildException("identifier cannot be null!");
            }

            FastConstructor<T> constructor;
            try {
                var injectedClass = new AllayComponentInjector<T>()
                        .interfaceClass(interfaceClass)
                        .component(new ArrayList<>(componentProviders.values()))
                        .inject(false);

                var fastMemberLoader = new FastMemberLoader(Allay.EXTRA_RESOURCE_CLASS_LOADER);
                constructor = FastConstructor.create(
                        injectedClass.getConstructor(ComponentInitInfo.class),
                        fastMemberLoader,
                        false
                );
            } catch (Exception e) {
                throw new EntityTypeBuildException("Failed to create entity type!", e);
            }

            var type = new AllayEntityType<>(constructor, identifier);
            Registries.ENTITIES.register(identifier, type);
            return type;
        }
    }
}
