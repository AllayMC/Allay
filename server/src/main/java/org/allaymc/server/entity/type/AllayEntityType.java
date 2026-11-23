package org.allaymc.server.entity.type;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.property.type.EntityPropertyType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.component.ComponentBlueprint;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.data.EntityId;
import org.allaymc.server.entity.impl.EntityImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.allaymc.server.component.ComponentProvider.toMap;

/**
 * {@code AllayEntityType} represents a registered entity definition backed by a
 * precompiled {@link ComponentBlueprint}, registry identifier, and named property
 * definitions. {@code Builder.build} validates all component bindings and event
 * handlers eagerly during registration before publishing the type;
 * {@code createEntity} assigns this type to the initialization payload and
 * instantiates the entity through the compiled blueprint without per-instance
 * reflection or event-bus allocation.
 *
 * @param <T> entity interface produced by this type
 *
 * @author daoge_cmd
 */
public class AllayEntityType<T extends Entity> implements EntityType<T> {
    /**
     * {@code instanceCreator} invokes this type's compiled blueprint factory.
     */
    protected final Function<EntityInitInfo, T> instanceCreator;
    /**
     * Registered identifier used for lookup and entity persistence.
     */
    @Getter
    protected Identifier identifier;
    /**
     * {@code properties} exposes an unmodifiable view of the supplied backing
     * map.
     */
    @Getter
    protected Map<String, EntityPropertyType<?>> properties;

    /**
     * Creates a type retaining the compiled instance factory, identifier, and
     * property definitions.
     */
    protected AllayEntityType(Function<EntityInitInfo, T> instanceCreator, Identifier identifier, Map<String, EntityPropertyType<?>> properties) {
        this.instanceCreator = instanceCreator;
        this.identifier = identifier;
        this.properties = Collections.unmodifiableMap(properties);
    }

    /**
     * Returns a builder targeting the default {@link EntityImpl} implementation
     * for entity types that do not require a specialized API interface.
     */
    public static Builder builder() {
        return new Builder(EntityImpl.class);
    }

    /**
     * Returns a builder targeting the supplied entity implementation class or
     * composed API interface.
     */
    public static <T extends Entity> Builder builder(Class<T> clazz) {
        return new Builder(clazz);
    }

    /**
     * Assigns this entity type to {@code info} and invokes the compiled factory.
     * The returned instance remains unspawned until added to a dimension.
     */
    @Override
    public T createEntity(EntityInitInfo info) {
        info.setEntityType(this);
        return instanceCreator.apply(info);
    }

    /**
     * {@code Builder} collects entity identity, insertion-ordered component
     * providers keyed by contract class, and property definitions for
     * registration.
     */
    public static class Builder {
        /**
         * Target entity implementation class or composed API interface.
         */
        protected Class<?> clazz;
        /**
         * {@code componentProviders} holds configured providers keyed by canonical
         * component contract class in deterministic insertion order.
         */
        protected Map<Class<?>, ComponentProvider<? extends EntityComponent>> componentProviders = new LinkedHashMap<>();
        /**
         * Identifier selected for the type's eventual registry entry.
         */
        protected Identifier identifier;
        /**
         * {@code properties} holds name-indexed entity property definitions in
         * declaration order.
         */
        protected Map<String, EntityPropertyType<?>> properties = new LinkedHashMap<>();

        /**
         * Creates a builder targeting the supplied entity implementation class or
         * composed API interface.
         */
        public Builder(Class<?> clazz) {
            this.clazz = clazz;
        }

        /**
         * Selects the registry identifier and returns this entity type builder.
         */
        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * Parses the registry identifier and returns this entity type builder.
         */
        public Builder identifier(String identifier) {
            this.identifier = new Identifier(identifier);
            return this;
        }

        /**
         * Selects the supplied vanilla entity's identifier for this type builder.
         */
        public Builder vanillaEntity(EntityId entityId) {
            this.identifier = entityId.getIdentifier();
            return this;
        }

        /**
         * Replaces all configured providers with an insertion-ordered copy of the
         * supplied map.
         */
        public Builder setComponents(Map<Class<?>, ComponentProvider<? extends EntityComponent>> providers) {
            Preconditions.checkNotNull(providers, "providers");
            this.componentProviders = new LinkedHashMap<>(providers);
            return this;
        }

        /**
         * Adds or replaces component providers by their contract-class keys and
         * returns this builder.
         */
        public Builder addComponents(Map<Class<?>, ComponentProvider<? extends EntityComponent>> providers) {
            this.componentProviders.putAll(providers);
            return this;
        }

        /**
         * Registers an initialization-aware provider under the contract key
         * resolved from {@code componentClass}.
         */
        public Builder addComponent(Function<EntityInitInfo, ? extends EntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.Simple<>(provider, componentClass);
            this.componentProviders.put(p.getComponentKey(), p);
            return this;
        }

        /**
         * Registers a supplier-backed provider under the contract key resolved
         * from {@code componentClass}.
         */
        public Builder addComponent(Supplier<? extends EntityComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        /**
         * Registers a shared stateless component singleton under its resolved
         * contract key. Blueprint compilation verifies that the singleton declares
         * no instance-bound injections or lifecycle callbacks.
         */
        public Builder addComponent(EntityComponent singleton) {
            return addComponent(ComponentProvider.singleton(singleton));
        }

        /**
         * Registers the supplied provider under its contract key and returns this
         * builder.
         */
        public Builder addComponent(ComponentProvider<? extends EntityComponent> provider) {
            this.componentProviders.put(provider.getComponentKey(), provider);
            return this;
        }

        /**
         * Replaces configured providers using contract keys derived from the
         * supplied list, rejecting duplicate keys.
         */
        public Builder setComponents(List<ComponentProvider<? extends EntityComponent>> providers) {
            return setComponents(toMap(providers));
        }

        /**
         * Adds configured providers using contract keys derived from the supplied
         * list, rejecting duplicates within that list.
         */
        public Builder addComponents(List<ComponentProvider<? extends EntityComponent>> providers) {
            return addComponents(toMap(providers));
        }

        /**
         * Replaces the property map with definitions indexed by their names.
         */
        public Builder setProperties(List<EntityPropertyType<?>> properties) {
            this.properties = properties.stream().collect(Collectors.toMap(
                    EntityPropertyType::getName,
                    Function.identity(),
                    (a, b) -> {
                        throw new IllegalStateException("Duplicate property: " + a.getName());
                    },
                    LinkedHashMap::new
            ));
            return this;
        }

        /**
         * Replaces the property map with definitions indexed by their names.
         */
        public Builder setProperties(EntityPropertyType<?>... properties) {
            return setProperties(Arrays.asList(properties));
        }

        /**
         * Builds and registers the configured entity type using Allay's class
         * loader.
         */
        public <T extends Entity> EntityType<T> build() {
            return build(Allay.class.getClassLoader());
        }

        /**
         * Compiles the type's {@link ComponentBlueprint}, validates all component
         * bindings eagerly, and registers the resulting entity type.
         */
        @SuppressWarnings("unchecked")
        public <T extends Entity> EntityType<T> build(ClassLoader classLoader) {
            Preconditions.checkNotNull(identifier, "identifier");
            Function<EntityInitInfo, T> instanceCreator;
            try {
                var implClass = ComponentBlueprint.resolveImplClass(clazz, classLoader);
                var defaultBase = new ComponentProvider.Simple<>(EntityBaseComponentImpl::new, EntityBaseComponentImpl.class);
                var orderedProviders = ComponentBlueprint.orderWithBaseFirst(componentProviders, EntityBaseComponent.class, defaultBase);
                var blueprint = ComponentBlueprint.compile(implClass, orderedProviders, EntityInitInfo.class);
                instanceCreator = info -> {
                    try {
                        return (T) blueprint.newInstance(info);
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
