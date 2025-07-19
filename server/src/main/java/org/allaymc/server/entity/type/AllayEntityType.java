package org.allaymc.server.entity.type;

import com.google.common.base.Preconditions;
import lombok.Getter;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.Allay;
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

    protected AllayEntityType(Function<EntityInitInfo, T> instanceCreator, Identifier identifier) {
        this.instanceCreator = instanceCreator;
        this.identifier = identifier;
    }

    public static <T extends Entity> Builder builder(Class<T> clazz) {
        return new Builder(clazz);
    }

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

        public Builder setComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> providers) {
            Preconditions.checkNotNull(providers, "providers");
            this.componentProviders = new HashMap<>(providers);
            return this;
        }

        public Builder addComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> providers) {
            this.componentProviders.putAll(providers);
            return this;
        }

        public Builder addComponent(Function<EntityInitInfo, ? extends EntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.Simple<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder addComponent(ComponentProvider<? extends EntityComponent> provider) {
            this.componentProviders.put(provider.findComponentIdentifier(), provider);
            return this;
        }

        public Builder setComponents(List<ComponentProvider<? extends EntityComponent>> providers) {
            return setComponents(toMap(providers));
        }

        public Builder addComponents(List<ComponentProvider<? extends EntityComponent>> providers) {
            return addComponents(toMap(providers));
        }

        public Builder addComponent(Supplier<? extends EntityComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        public <T extends Entity> EntityType<T> build() {
            return build(Allay.class.getClassLoader());
        }

        public <T extends Entity> EntityType<T> build(ClassLoader classLoader) {
            Preconditions.checkNotNull(identifier, "identifier");
            if (!componentProviders.containsKey(EntityBaseComponentImpl.IDENTIFIER)) {
                addComponent(EntityBaseComponentImpl::new, EntityBaseComponentImpl.class);
            }

            Function<EntityInitInfo, T> instanceCreator;
            try {
                var constructor = FastConstructor.create(clazz.getConstructor(EntityInitInfo.class, List.class), new FastMemberLoader(classLoader), false);
                var componentProviderList = new ArrayList<>(componentProviders.values());
                instanceCreator = info -> {
                    try {
                        return (T) constructor.invoke(info, componentProviderList);
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
