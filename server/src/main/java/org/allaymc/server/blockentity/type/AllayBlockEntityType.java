package org.allaymc.server.blockentity.type;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.Allay;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.component.ComponentBlueprint;
import org.allaymc.server.component.ComponentProvider;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.allaymc.server.component.ComponentProvider.toMap;

/**
 * {@code AllayBlockEntityType} represents a registered block-entity factory
 * backed by a precompiled {@link ComponentBlueprint} and persistence name.
 * {@code Builder.build} validates all component bindings eagerly during
 * registration and orders the base component ahead of sibling components in
 * deterministic insertion order.
 *
 * @param <T> block-entity interface produced by this type
 *
 * @author daoge_cmd
 */
public class AllayBlockEntityType<T extends BlockEntity> implements BlockEntityType<T> {

    /**
     * {@code instanceCreator} invokes this type's compiled blueprint factory.
     */
    protected final Function<BlockEntityInitInfo, T> instanceCreator;
    /**
     * Registered persistence type name.
     */
    @Getter
    protected String name;

    /**
     * Stores the compiled instance factory and persistence name for this type.
     */
    protected AllayBlockEntityType(Function<BlockEntityInitInfo, T> instanceCreator, String name) {
        this.instanceCreator = instanceCreator;
        this.name = name;
    }

    /**
     * Creates a type builder targeting the default {@link BlockEntityImpl}
     * implementation for block entities without a specialized API interface.
     */
    public static Builder builder() {
        return new Builder(BlockEntityImpl.class);
    }

    /**
     * Creates a type builder targeting the supplied block-entity implementation
     * class or composed API interface.
     */
    public static <T extends BlockEntity> Builder builder(Class<T> clazz) {
        return new Builder(clazz);
    }

    /**
     * Assigns this type into {@code info} and constructs an instance through the
     * compiled blueprint without attaching it to a chunk.
     */
    @Override
    public T createBlockEntity(BlockEntityInitInfo info) {
        info.setBlockEntityType(this);
        return instanceCreator.apply(info);
    }

    /**
     * {@code Builder} collects a target class, persistence name, and
     * insertion-ordered component providers keyed by contract class.
     */
    public static class Builder {
        /**
         * Target block-entity implementation class or composed API interface.
         */
        protected Class<?> clazz;
        /**
         * {@code componentProviders} indexes providers by canonical contract class
         * in deterministic insertion order.
         */
        protected Map<Class<?>, ComponentProvider<? extends BlockEntityComponent>> componentProviders = new LinkedHashMap<>();
        /**
         * Proposed persistence type name.
         */
        protected String name;

        /**
         * Creates a builder targeting the supplied block-entity implementation
         * class or composed API interface.
         */
        public Builder(Class<?> clazz) {
            this.clazz = clazz;
        }

        /**
         * Selects the persistence registry name and returns this builder.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Replaces configured providers with an insertion-ordered copy of the
         * supplied map.
         */
        public Builder setComponents(Map<Class<?>, ComponentProvider<? extends BlockEntityComponent>> providers) {
            Preconditions.checkNotNull(providers, "providers");
            this.componentProviders = new LinkedHashMap<>(providers);
            return this;
        }

        /**
         * Replaces configured providers using contract keys derived from the
         * supplied list, rejecting duplicate keys.
         */
        public Builder setComponents(List<ComponentProvider<? extends BlockEntityComponent>> providers) {
            return setComponents(toMap(providers));
        }

        /**
         * Merges supplied contract-keyed provider entries and returns this
         * builder.
         */
        public Builder addComponents(Map<Class<?>, ComponentProvider<? extends BlockEntityComponent>> providers) {
            this.componentProviders.putAll(providers);
            return this;
        }

        /**
         * Adds providers indexed by their contract keys, rejecting duplicate keys
         * within {@code providers}.
         */
        public Builder addComponents(List<ComponentProvider<? extends BlockEntityComponent>> providers) {
            return addComponents(toMap(providers));
        }

        /**
         * Registers an initialization-aware provider under the contract key
         * resolved from {@code componentClass}.
         */
        public Builder addComponent(Function<BlockEntityInitInfo, ? extends BlockEntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.Simple<>(provider, componentClass);
            this.componentProviders.put(p.getComponentKey(), p);
            return this;
        }

        /**
         * Registers a supplier-backed provider under the contract key resolved
         * from {@code componentClass}.
         */
        public Builder addComponent(Supplier<? extends BlockEntityComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        /**
         * Registers a shared stateless component singleton under its resolved
         * contract key.
         */
        public Builder addComponent(BlockEntityComponent singleton) {
            return addComponent(ComponentProvider.singleton(singleton));
        }

        /**
         * Registers the supplied provider under its contract key and returns this
         * builder.
         */
        public Builder addComponent(ComponentProvider<? extends BlockEntityComponent> provider) {
            this.componentProviders.put(provider.getComponentKey(), provider);
            return this;
        }

        /**
         * Builds and registers a type using Allay's class loader.
         */
        public <T extends BlockEntity> BlockEntityType<T> build() {
            return build(Allay.class.getClassLoader());
        }

        /**
         * Compiles the type's {@link ComponentBlueprint}, validates all component
         * bindings eagerly, and publishes the block-entity type under {@code name}.
         */
        @SuppressWarnings("unchecked")
        public <T extends BlockEntity> BlockEntityType<T> build(ClassLoader classLoader) {
            Preconditions.checkNotNull(name, "name");
            Function<BlockEntityInitInfo, T> instanceCreator;
            try {
                var implClass = ComponentBlueprint.resolveImplClass(clazz, classLoader);
                var defaultBase = new ComponentProvider.Simple<>(BlockEntityBaseComponentImpl::new, BlockEntityBaseComponentImpl.class);
                var orderedProviders = ComponentBlueprint.orderWithBaseFirst(componentProviders, BlockEntityBaseComponent.class, defaultBase);
                var blueprint = ComponentBlueprint.compile(implClass, orderedProviders, BlockEntityInitInfo.class);
                instanceCreator = info -> {
                    try {
                        return (T) blueprint.newInstance(info);
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
