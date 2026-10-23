package org.allaymc.server.blockentity.type;

import com.google.common.base.Preconditions;
import lombok.Getter;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.ComponentProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * {@code AllayBlockEntityType} represents a block-entity factory backed by a
 * captured constructor and component-provider configuration. Its
 * {@code createBlockEntity} operation writes this type into the supplied
 * initialization object before invoking the constructor, so providers can read
 * the selected type while constructing their per-instance state. Callers must
 * not share that initialization object between concurrent creations that mutate
 * its type reference.
 *
 * <p>
 * {@code Builder.build} snapshots the provider map's values into a list while
 * retaining the provider objects themselves. Later builder map changes do not
 * alter that captured list, but {@code HashMap} iteration provides no stable
 * component ordering. The builder adds a default base provider only when its
 * identifier is absent. Successful constructor setup precedes registry
 * publication, which can replace an existing same-name mapping without changing
 * older type references.
 *
 * @author daoge_cmd
 */
public class AllayBlockEntityType<T extends BlockEntity> implements BlockEntityType<T> {

    /**
     * {@code instanceCreator} holds the constructor adapter and captured
     * provider list. Providers remain shared between creations and determine
     * whether their component results are fresh.
     */
    protected final Function<BlockEntityInitInfo, T> instanceCreator;
    /**
     * Registered persistence type name.
     */
    @Getter
    protected String name;

    /**
     * Stores the constructor adapter and persistence name for this type.
     */
    protected AllayBlockEntityType(Function<BlockEntityInitInfo, T> instanceCreator, String name) {
        this.instanceCreator = instanceCreator;
        this.name = name;
    }

    /**
     * Creates an empty type builder targeting the supplied block-entity
     * implementation.
     */
    public static <T extends BlockEntity> Builder builder(Class<T> clazz) {
        return new Builder(clazz);
    }

    /**
     * Assigns this type into the supplied initialization object and constructs
     * an instance. Callers must not share {@code info} between concurrent
     * creations; the assignment remains even if construction fails, and no
     * chunk attachment is performed.
     */
    @Override
    public T createBlockEntity(BlockEntityInitInfo info) {
        info.setBlockEntityType(this);
        return instanceCreator.apply(info);
    }

    /**
     * {@code Builder} collects a constructor target, persistence name, and
     * identifier-keyed component providers for a block-entity factory.
     */
    public static class Builder {
        /**
         * Reflective constructor target class.
         */
        protected Class<?> clazz;
        /**
         * {@code componentProviders} indexes providers by identifier. Additions
         * replace equal keys, and {@code build} captures the values in
         * unspecified map iteration order.
         */
        protected Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> componentProviders = new HashMap<>();
        /**
         * Proposed persistence type name.
         */
        protected String name;

        /**
         * Creates a provider-free builder targeting the supplied reflective
         * constructor class.
         */
        public Builder(Class<?> clazz) {
            this.clazz = clazz;
        }

        /**
         * Selects the persistence registry name and returns this same builder.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Replaces configured providers with a shallow copy of the supplied
         * map. The map must be non-{@code null}; provider objects remain
         * shared, and later changes to the caller's map do not alter this
         * builder.
         */
        public Builder setComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> providers) {
            Preconditions.checkNotNull(providers, "providers");
            this.componentProviders = new HashMap<>(providers);
            return this;
        }

        /**
         * Merges supplied identifier-provider entries and returns this same
         * builder. Equal keys replace existing providers, while other entries
         * remain; provider objects are retained directly without copying their
         * internal state.
         */
        public Builder addComponents(Map<Identifier, ComponentProvider<? extends BlockEntityComponent>> providers) {
            this.componentProviders.putAll(providers);
            return this;
        }

        /**
         * Wraps an initialization-aware provider and indexes it by component
         * identifier. The supplied class determines identifier discovery and
         * injection metadata; an existing provider under that identifier is
         * replaced without invoking either.
         */
        public Builder addComponent(Function<BlockEntityInitInfo, ? extends BlockEntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.Simple<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        /**
         * Registers a supplier that ignores each creation's initialization
         * information. The component class determines its identifier, and
         * registration replaces an equal-key provider without invoking the
         * supplier until component construction.
         */
        public Builder addComponent(Supplier<? extends BlockEntityComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        /**
         * Registers the supplied provider under its discovered component
         * identifier. Existing equal-key entries are replaced; the provider
         * remains shared with the caller and may be reused by multiple built
         * types.
         */
        public Builder addComponent(ComponentProvider<? extends BlockEntityComponent> provider) {
            this.componentProviders.put(provider.findComponentIdentifier(), provider);
            return this;
        }

        /**
         * Builds and registers a type using Allay's class loader for
         * reflection. This delegates the same default-base insertion and
         * provider snapshot rules as the explicit-loader overload, and can
         * replace a same-name mapping.
         */
        public <T extends BlockEntity> BlockEntityType<T> build() {
            return build(Allay.class.getClassLoader());
        }

        /**
         * Prepares a constructor adapter and publishes the type under its name.
         * The target class must expose a public constructor accepting
         * {@code BlockEntityInitInfo} and {@code List}, and {@code name} must
         * be non-{@code null}. When the base identifier is missing, this
         * builder gains the default base provider before reflection begins;
         * that mutation survives later failure. Provider references are
         * captured in a separate list, isolating future map edits but
         * preserving shared provider state. Reflection setup failures become
         * {@code BlockEntityTypeBuildException}; registry rejection propagates
         * separately after setup and does not undo the builder mutation.
         */
        public <T extends BlockEntity> BlockEntityType<T> build(ClassLoader classLoader) {
            Preconditions.checkNotNull(name, "name");
            if (!componentProviders.containsKey(BlockEntityBaseComponentImpl.IDENTIFIER)) {
                // Every ordinary instance needs base behavior, but an explicit
                // provider under the base identifier wins even if it supplies a
                // custom implementation.
                addComponent(BlockEntityBaseComponentImpl::new, BlockEntityBaseComponentImpl.class);
            }

            Function<BlockEntityInitInfo, T> instanceCreator;
            try {
                var constructor = FastConstructor.create(clazz.getConstructor(BlockEntityInitInfo.class, List.class), new FastMemberLoader(classLoader), false);
                // Capture membership now so subsequent builder map edits do not
                // change existing types; provider objects themselves remain
                // shared across creations.
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
