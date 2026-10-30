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

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.allaymc.server.component.ComponentProvider.toMap;

/**
 * {@code AllayEntityType} represents a registered entity definition with an
 * identifier, component-backed instance factory, and named property
 * definitions. {@code Builder.build} prepares the entity constructor and
 * registers the resulting type; {@code createEntity} assigns this type to its
 * initialization information before invoking that factory.
 *
 * <p> The factory captures a snapshot of the configured component-provider
 * values, so later provider-map edits do not alter that type's construction
 * list. The property map instead remains behind an unmodifiable view,
 * preserving the backing map's identity rather than copying its entries.
 * Entity construction can fail after initialization information has been
 * updated, and registration or component initialization does not provide
 * rollback of preceding side effects.
 *
 * @author daoge_cmd
 */
public class AllayEntityType<T extends Entity> implements EntityType<T> {
    /**
     * {@code instanceCreator} invokes the prepared entity constructor with its
     * captured component-provider list. Constructor failures are wrapped as
     * {@code EntityTypeBuildException} by the builder-created factory.
     */
    protected final Function<EntityInitInfo, T> instanceCreator;
    /**
     * Registered identifier used for lookup and entity persistence.
     */
    @Getter
    protected Identifier identifier;
    /**
     * {@code properties} exposes an unmodifiable view of the supplied backing
     * map. The constructor does not copy entries, and the property definitions
     * retain their own value-list mutability.
     */
    @Getter
    protected Map<String, EntityPropertyType<?>> properties;

    /**
     * Creates a type retaining the supplied constructor function and
     * identifier. The property map is wrapped without copying, so its existing
     * backing map remains shared with the code that supplied it.
     */
    protected AllayEntityType(Function<EntityInitInfo, T> instanceCreator, Identifier identifier, Map<String, EntityPropertyType<?>> properties) {
        this.instanceCreator = instanceCreator;
        this.identifier = identifier;
        this.properties = Collections.unmodifiableMap(properties);
    }

    /**
     * Returns a builder targeting the supplied component-backed entity
     * implementation class.
     */
    public static <T extends Entity> Builder builder(Class<T> clazz) {
        return new Builder(clazz);
    }

    /**
     * Assigns this entity type to {@code info} and invokes the captured
     * factory. The result remains unspawned, and a construction failure leaves
     * the initialization information's type assignment in place rather than
     * restoring its previous value.
     */
    @Override
    public T createEntity(EntityInitInfo info) {
        info.setEntityType(this);
        return instanceCreator.apply(info);
    }

    /**
     * {@code Builder} collects entity identity, component providers, and
     * property definitions for registration.
     */
    public static class Builder {
        /**
         * Entity implementation class used for public constructor lookup.
         */
        protected Class<?> clazz;
        /**
         * {@code componentProviders} holds the current provider for each
         * component identifier. Build captures its values in a list, and later
         * map replacements do not alter an already built factory.
         */
        protected Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders = new HashMap<>();
        /**
         * Identifier selected for the type's eventual registry entry.
         */
        protected Identifier identifier;
        /**
         * {@code properties} holds the current name-indexed definitions. Build
         * shares this map through an unmodifiable view; the property setters
         * replace the map rather than mutating that existing backing map.
         */
        protected Map<String, EntityPropertyType<?>> properties = new LinkedHashMap<>();

        /**
         * Creates a builder targeting the supplied entity implementation
         * class.
         */
        public Builder(Class<?> clazz) {
            this.clazz = clazz;
        }

        /**
         * Selects the registry identifier and returns this entity type
         * builder.
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
         * Selects the supplied vanilla entity's identifier for this type
         * builder.
         */
        public Builder vanillaEntity(EntityId entityId) {
            this.identifier = entityId.getIdentifier();
            return this;
        }

        /**
         * Replaces all configured providers with a shallow copy of the
         * supplied map. Provider objects remain shared, while later mutations
         * to the caller's map do not change this builder's component entries.
         */
        public Builder setComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> providers) {
            Preconditions.checkNotNull(providers, "providers");
            this.componentProviders = new HashMap<>(providers);
            return this;
        }

        /**
         * Adds or replaces component providers by their map keys and returns
         * this builder. The map entries are copied into the existing
         * configuration, while each provider object remains shared with the
         * caller.
         */
        public Builder addComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> providers) {
            this.componentProviders.putAll(providers);
            return this;
        }

        /**
         * Registers an initialization-aware provider under the component
         * identifier discovered from {@code componentClass}. An existing
         * provider for that identifier is replaced; the function is retained
         * for later entity construction rather than invoked here.
         */
        public Builder addComponent(Function<EntityInitInfo, ? extends EntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.Simple<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        /**
         * Registers the supplied provider using its discovered component
         * identifier and returns this builder. The provider is retained
         * directly, and an existing provider under the same identifier is
         * replaced.
         */
        public Builder addComponent(ComponentProvider<? extends EntityComponent> provider) {
            this.componentProviders.put(provider.findComponentIdentifier(), provider);
            return this;
        }

        /**
         * Replaces the configured providers using component identifiers
         * derived from the supplied list. The list is converted to a map
         * before configuration; duplicate identifiers are rejected by that
         * conversion rather than merged in list order.
         */
        public Builder setComponents(List<ComponentProvider<? extends EntityComponent>> providers) {
            return setComponents(toMap(providers));
        }

        /**
         * Adds the configured providers using component identifiers derived
         * from the supplied list. The list is converted to a map before
         * configuration; duplicate identifiers are rejected by that conversion
         * rather than merged in list order.
         */
        public Builder addComponents(List<ComponentProvider<? extends EntityComponent>> providers) {
            return addComponents(toMap(providers));
        }

        /**
         * Registers a supplier-backed component provider that ignores entity
         * initialization information. The supplier runs for each entity
         * construction, so callers must return an instance suitable for that
         * entity rather than unintentionally sharing mutable component state.
         */
        public Builder addComponent(Supplier<? extends EntityComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        /**
         * Replaces the property map with definitions indexed by their names.
         * Duplicate names fail during collection, property objects are
         * retained directly, and a previously built type keeps the prior map
         * when this setter replaces it.
         */
        public Builder setProperties(List<EntityPropertyType<?>> properties) {
            this.properties = properties.stream().collect(Collectors.toMap(EntityPropertyType::getName, Function.identity()));
            return this;
        }

        /**
         * Replaces the property map with definitions indexed by their names.
         * Duplicate names fail during collection, property objects are
         * retained directly, and a previously built type keeps the prior map
         * when this setter replaces it.
         */
        public Builder setProperties(EntityPropertyType<?>... properties) {
            this.properties = Arrays.stream(properties).collect(Collectors.toMap(EntityPropertyType::getName, Function.identity()));
            return this;
        }

        /**
         * Builds and registers the configured entity type using Allay's class
         * loader. This operation delegates constructor lookup and provider
         * preparation to {@code build(ClassLoader)} and can fail before any
         * usable type is returned.
         */
        public <T extends Entity> EntityType<T> build() {
            return build(Allay.class.getClassLoader());
        }

        /**
         * Builds and registers a component-backed type with the selected class
         * loader. The identifier must be set, and the target class must expose
         * a public constructor accepting {@code EntityInitInfo} and
         * {@code List}. Missing base behavior is added to this builder before
         * constructor preparation.
         *
         * <p> The factory captures a list of current provider objects, while
         * the type retains a view of the current property map.
         * Constructor-setup exceptions caught as {@code Exception} become
         * {@code EntityTypeBuildException}; setup errors outside that catch
         * propagate directly. Later constructor invocation catches
         * {@code Throwable} and wraps it when the factory runs. Registry
         * failure does not undo the builder's added base component, and entity
         * construction remains deferred until {@code createEntity}.
         */
        @SuppressWarnings("unchecked")
        public <T extends Entity> EntityType<T> build(ClassLoader classLoader) {
            Preconditions.checkNotNull(identifier, "identifier");
            // Supply common entity behavior only when the configured providers
            // do not already replace its identifier with a specialized component.
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
                        // Preserve constructor failures as the cause while exposing
                        // the same entity-type failure category to factory callers.
                        throw new EntityTypeBuildException("Failed to create entity instance!", t);
                    }
                };
            } catch (Exception e) {
                // Constructor discovery and access setup fail before a type exists;
                // retain the original cause for diagnostics at the builder boundary.
                throw new EntityTypeBuildException("Failed to create entity type!", e);
            }

            var type = new AllayEntityType<>(instanceCreator, identifier, properties);
            Registries.ENTITIES.register(identifier, type);
            return type;
        }
    }
}
