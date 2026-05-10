package org.allaymc.server.entity.type;

import com.google.common.base.Preconditions;
import lombok.Getter;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.property.type.EntityPropertyType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.data.EntityId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.function.Supplier;

import static org.allaymc.server.component.ComponentProvider.toMap;

/**
 * @author daoge_cmd
 */
public class AllayEntityType<T extends Entity> implements EntityType<T> {
    protected final Function<EntityInitInfo, T> instanceCreator;
    @Getter
    protected Identifier identifier;
    @Getter
    protected Map<String, EntityPropertyType<?>> properties;

    protected AllayEntityType(Function<EntityInitInfo, T> instanceCreator, Identifier identifier, Map<String, EntityPropertyType<?>> properties) {
        this.instanceCreator = instanceCreator;
        this.identifier = identifier;
        this.properties = Collections.unmodifiableMap(properties);
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
        protected Map<String, EntityPropertyType<?>> properties = new LinkedHashMap<>();

        public Builder(Class<?> clazz) {
            this.clazz = clazz;
        }

        /**
         * Sets the entity identifier.
         *
         * @param identifier the entity identifier
         * @return this builder
         */
        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * Sets the entity identifier from a string.
         *
         * @param identifier the entity identifier string (e.g., "myplugin:my_entity")
         * @return this builder
         */
        public Builder identifier(String identifier) {
            this.identifier = new Identifier(identifier);
            return this;
        }

        /**
         * Configures this builder for a vanilla entity.
         *
         * @param entityId the vanilla entity ID
         * @return this builder
         */
        public Builder vanillaEntity(EntityId entityId) {
            this.identifier = entityId.getIdentifier();
            return this;
        }

        /**
         * Sets the entity components, replacing any existing components.
         *
         * @param providers a map of component identifiers to component providers
         * @return this builder
         */
        public Builder setComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> providers) {
            Preconditions.checkNotNull(providers, "providers");
            this.componentProviders = new HashMap<>(providers);
            return this;
        }

        /**
         * Adds multiple components to this entity.
         *
         * @param providers a map of component identifiers to component providers
         * @return this builder
         */
        public Builder addComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> providers) {
            this.componentProviders.putAll(providers);
            return this;
        }

        /**
         * Adds a single component to this entity.
         *
         * @param provider the component provider function
         * @param componentClass the component class
         * @return this builder
         */
        public Builder addComponent(Function<EntityInitInfo, ? extends EntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.Simple<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        /**
         * Adds a single component to this entity.
         *
         * @param provider the component provider
         * @return this builder
         */
        public Builder addComponent(ComponentProvider<? extends EntityComponent> provider) {
            this.componentProviders.put(provider.findComponentIdentifier(), provider);
            return this;
        }

        /**
         * Sets the entity components from a list, replacing any existing components.
         *
         * @param providers the list of component providers
         * @return this builder
         */
        public Builder setComponents(List<ComponentProvider<? extends EntityComponent>> providers) {
            return setComponents(toMap(providers));
        }

        /**
         * Adds multiple components from a list to this entity.
         *
         * @param providers the list of component providers to add
         * @return this builder
         */
        public Builder addComponents(List<ComponentProvider<? extends EntityComponent>> providers) {
            return addComponents(toMap(providers));
        }

        /**
         * Adds a single component to this entity using a supplier.
         *
         * @param supplier the component supplier
         * @param componentClass the component class
         * @return this builder
         */
        public Builder addComponent(Supplier<? extends EntityComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        /**
         * Sets the entity properties from a list.
         *
         * @param properties the list of entity properties
         * @return this builder
         */
        public Builder setProperties(List<EntityPropertyType<?>> properties) {
            this.properties = properties.stream().collect(Collectors.toMap(EntityPropertyType::getName, Function.identity()));
            return this;
        }

        /**
         * Sets the entity properties (e.g., climate variant).
         *
         * @param properties the entity properties
         * @return this builder
         */
        public Builder setProperties(EntityPropertyType<?>... properties) {
            this.properties = Arrays.stream(properties).collect(Collectors.toMap(EntityPropertyType::getName, Function.identity()));
            return this;
        }

        public <T extends Entity> EntityType<T> build() {
            return build(Allay.class.getClassLoader());
        }

        @SuppressWarnings("unchecked")
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
                        throw new EntityTypeBuildException("Failed to create entity instance!", t);
                    }
                };
            } catch (Exception e) {
                throw new EntityTypeBuildException("Failed to create entity type!", e);
            }

            var type = new AllayEntityType<>(instanceCreator, identifier, properties);
            Registries.ENTITIES.register(identifier, type);
            return type;
        }
    }
}
