package org.allaymc.server.entity.type;

import lombok.Getter;
import lombok.SneakyThrows;
import me.sunlan.fastreflection.FastConstructor;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.block.type.BlockTypeBuildException;
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
    protected final Function<EntityInitInfo, T> instanceCreator;
    @Getter
    protected Identifier identifier;

    @SneakyThrows
    protected AllayEntityType(
            Function<EntityInitInfo, T> instanceCreator,
            Identifier identifier
    ) {
        this.instanceCreator = instanceCreator;
        this.identifier = identifier;
    }

    public static <T extends Entity> Builder builder(Class<T> interfaceClass) {
        return new Builder(interfaceClass);
    }

    @SneakyThrows
    @Override
    public T createEntity(EntityInitInfo info) {
        info.setEntityType(this);
        return instanceCreator.apply(info);
    }

    public static class Builder {
        protected Class<?> clazz;
        protected Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders = new HashMap<>();
        protected Identifier identifier;

        public Builder(Class<?> clazz) {
            this.clazz = clazz;
        }

        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder identifier(String identifier) {
            this.identifier = new Identifier(identifier);
            return this;
        }

        public Builder vanillaEntity(EntityId entityId) {
            this.identifier = entityId.getIdentifier();
            return this;
        }

        public Builder setComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders) {
            if (componentProviders == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        public Builder addComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        public Builder addComponent(Function<EntityInitInfo, ? extends EntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.SimpleComponentProvider<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder addComponent(ComponentProvider<? extends EntityComponent> p) {
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder setComponents(List<ComponentProvider<? extends EntityComponent>> componentProviders) {
            return setComponents(toMap(componentProviders));
        }

        public Builder addComponents(List<ComponentProvider<? extends EntityComponent>> componentProviders) {
            return addComponents(toMap(componentProviders));
        }

        public Builder addComponent(Supplier<? extends EntityComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        public <T extends Entity> EntityType<T> build() {
            if (!componentProviders.containsKey(EntityBaseComponentImpl.IDENTIFIER)) {
                addComponent(EntityBaseComponentImpl::new, EntityBaseComponentImpl.class);
            }
            if (identifier == null) {
                throw new EntityTypeBuildException("identifier cannot be null!");
            }
            Function<EntityInitInfo, T> instanceCreator;
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
                throw new EntityTypeBuildException("Failed to create entity type!", e);
            }

            var type = new AllayEntityType<>(instanceCreator, identifier);
            Registries.ENTITIES.register(identifier, type);
            return type;
        }
    }
}
