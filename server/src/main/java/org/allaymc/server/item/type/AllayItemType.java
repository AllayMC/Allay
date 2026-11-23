package org.allaymc.server.item.type;

import com.google.common.base.Preconditions;
import com.google.common.base.Suppliers;
import lombok.Getter;
import lombok.ToString;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.data.ItemData;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.component.ComponentBlueprint;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.item.impl.ItemStackImpl;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.utils.BlockAndItemIdMapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.allaymc.server.component.ComponentProvider.toMap;

/**
 * {@code AllayItemType} represents a registered item kind with shared gameplay
 * metadata and a precompiled {@link ComponentBlueprint} stack factory. Its
 * builder validates component bindings and event handlers once during
 * registration so {@code createItemStack} instantiates stacks with zero
 * reflection scanning and zero per-stack event-bus allocation.
 *
 * <p>
 * The type retains its supplied item data, tag set, compiled blueprint, and
 * optional custom client definition. Block association resolves lazily from the
 * item identifier and memoizes the first result, including an absent mapping.
 *
 * @param <T> stack interface produced by this registered item kind
 *
 * @author daoge_cmd
 */
public final class AllayItemType<T extends ItemStack> implements ItemType<T> {

    /**
     * Compiled component blueprint shared by all stacks of this item type.
     */
    @Getter
    private final ComponentBlueprint<?> blueprint;
    /**
     * Prepared component-backed stack factory.
     */
    private final Function<ItemStackInitInfo, T> instanceCreator;
    /**
     * Registered namespaced item identifier.
     */
    @Getter
    private final Identifier identifier;
    /**
     * Assigned item runtime identifier.
     */
    @Getter
    private final int runtimeId;
    /**
     * {@code itemTags} retains the builder's selected set without another copy.
     */
    @Getter
    private final Set<ItemTag> itemTags;
    /**
     * {@code itemData} retains shared gameplay properties supplied by the builder.
     */
    @Getter
    private final ItemData itemData;
    /**
     * Custom item registration flag.
     */
    @Getter
    private final boolean customItem;
    /**
     * {@code customItemDefinition} holds optional client-definition parameters.
     */
    @Getter
    private final CustomItemDefinition customItemDefinition;
    /**
     * {@code blockType} memoizes identifier-based block lookup, including a
     * {@code null} result.
     */
    private final Supplier<BlockType<?>> blockType;

    /**
     * Creates item metadata and retains the compiled blueprint and stack factory.
     */
    private AllayItemType(
            ComponentBlueprint<?> blueprint,
            Function<ItemStackInitInfo, T> instanceCreator,
            Identifier identifier,
            int runtimeId,
            Set<ItemTag> itemTags,
            ItemData itemData,
            boolean customItem,
            CustomItemDefinition customItemDefinition
    ) {
        this.blueprint = blueprint;
        this.instanceCreator = instanceCreator;
        this.identifier = identifier;
        this.runtimeId = runtimeId;
        this.itemTags = itemTags;
        this.itemData = itemData;
        this.customItem = customItem;
        this.customItemDefinition = customItemDefinition;
        this.blockType = Suppliers.memoize(() -> Registries.BLOCKS.get(BlockAndItemIdMapper.itemIdToPossibleBlockId(identifier)));
    }

    /**
     * Creates a builder targeting the default {@link ItemStackImpl} class for
     * item types that do not require a specialized stack interface.
     */
    public static Builder builder() {
        return new Builder(ItemStackImpl.class);
    }

    /**
     * Creates a builder targeting the supplied stack implementation class or
     * composed API interface.
     */
    public static <T extends ItemStack> Builder builder(Class<T> clazz) {
        return new Builder(clazz);
    }

    /**
     * Creates a stack after assigning this item type to non-{@code null}
     * initialization data.
     */
    @Override
    public T createItemStack(ItemStackInitInfo info) {
        if (info != null) {
            info.setItemType(this);
        }
        return instanceCreator.apply(info);
    }

    /**
     * Returns the first resolved block mapping for this item identifier.
     */
    @Override
    public BlockType<?> getBlockType() {
        return blockType.get();
    }

    /**
     * {@code Builder} configures an item kind, compiles its blueprint, and
     * registers the resulting type.
     */
    @ToString
    public static class Builder {

        /**
         * {@code CUSTOM_ITEM_RUNTIME_ID_COUNTER} supplies automatic runtime IDs
         * beginning at {@code 10000}.
         */
        protected static int CUSTOM_ITEM_RUNTIME_ID_COUNTER = 10000;

        /**
         * Target item-stack implementation class or composed API interface.
         */
        protected Class<?> clazz;
        /**
         * {@code componentProviders} indexes providers by canonical contract class
         * in deterministic insertion order.
         */
        protected Map<Class<?>, ComponentProvider<? extends ItemComponent>> componentProviders = new LinkedHashMap<>();
        /**
         * Selected namespaced item identifier.
         */
        protected Identifier identifier;
        /**
         * Custom registration selection flag.
         */
        protected boolean isCustomItem = true;
        /**
         * {@code runtimeId} uses {@code Integer.MAX_VALUE} to request automatic
         * allocation during build.
         */
        protected int runtimeId = Integer.MAX_VALUE;
        /**
         * {@code itemTags} holds the selected tag set shared with built types.
         */
        protected Set<ItemTag> itemTags = Set.of();
        /**
         * {@code itemData} holds gameplay properties selected for the next build.
         */
        protected ItemData itemData = ItemData.DEFAULT;
        /**
         * {@code customItemDefinition} holds optional client-definition parameters
         * for custom items.
         */
        protected CustomItemDefinition customItemDefinition;

        /**
         * Creates an item-type builder targeting the supplied stack implementation
         * class or composed API interface.
         */
        public Builder(Class<?> clazz) {
            this.clazz = clazz;
        }

        /**
         * Selects the namespaced item identifier and returns this builder instance.
         */
        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * Parses and selects the item's namespaced identifier, returning this
         * builder.
         */
        public Builder identifier(String identifier) {
            return identifier(new Identifier(identifier));
        }

        /**
         * Selects vanilla identity, runtime ID, gameplay data, and any registered
         * tags.
         *
         * @throws ItemTypeBuildException if no gameplay data exists for {@code itemId}
         */
        public Builder vanillaItem(ItemId itemId) {
            this.isCustomItem = false;
            this.identifier = itemId.getIdentifier();
            this.runtimeId = itemId.getRuntimeId();

            itemData(InternalRegistries.ITEM_DATA.get(itemId));
            if (itemData == null) {
                throw new ItemTypeBuildException("Cannot find item data for vanilla item " + itemId + " in registry!");
            }

            var tags = InternalRegistries.ITEM_TAGS.get(itemId);
            if (tags != null) {
                setItemTags(tags);
            }

            return this;
        }

        /**
         * Selects shared gameplay data and returns this builder without copying it.
         */
        public Builder itemData(ItemData itemData) {
            this.itemData = itemData;
            return this;
        }

        /**
         * Selects non-{@code null} client-definition parameters and returns this
         * builder.
         */
        public Builder customItemDefinition(CustomItemDefinition customItemDefinition) {
            this.customItemDefinition = Objects.requireNonNull(customItemDefinition, "customItemDefinition");
            return this;
        }

        /**
         * Selects the runtime ID without validating range or uniqueness.
         */
        public Builder runtimeId(int runtimeId) {
            this.runtimeId = runtimeId;
            return this;
        }

        /**
         * Replaces component selection with providers indexed by their contract
         * keys, rejecting duplicate keys.
         */
        public Builder setComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
            return setComponents(toMap(componentProviders));
        }

        /**
         * Replaces component selection with an insertion-ordered copy of the
         * supplied map.
         */
        public Builder setComponents(Map<Class<?>, ComponentProvider<? extends ItemComponent>> componentProviders) {
            Preconditions.checkNotNull(componentProviders, "componentProviders");
            this.componentProviders = new LinkedHashMap<>(componentProviders);
            return this;
        }

        /**
         * Adds supplied component mappings, replacing providers at matching
         * contract keys.
         */
        public Builder addComponents(Map<Class<?>, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        /**
         * Adds a component factory under the contract key resolved from
         * {@code componentClass}.
         */
        public Builder addComponent(Function<ItemStackInitInfo, ? extends ItemComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.Simple<>(provider, componentClass);
            this.componentProviders.put(p.getComponentKey(), p);
            return this;
        }

        /**
         * Adds a supplier-backed component factory under the contract key resolved
         * from {@code componentClass}.
         */
        public Builder addComponent(Supplier<? extends ItemComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        /**
         * Adds a shared stateless component singleton under its resolved contract
         * key. Blueprint compilation verifies that the singleton declares no
         * instance-bound injections or lifecycle callbacks.
         */
        public Builder addComponent(ItemComponent singleton) {
            return addComponent(ComponentProvider.singleton(singleton));
        }

        /**
         * Adds the supplied provider under its contract key, replacing any
         * existing provider at that slot.
         */
        public Builder addComponent(ComponentProvider<? extends ItemComponent> p) {
            this.componentProviders.put(p.getComponentKey(), p);
            return this;
        }

        /**
         * Adds providers after converting the list to contract-key mappings,
         * rejecting duplicate keys within {@code componentProviders}.
         */
        public Builder addComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
            return addComponents(toMap(componentProviders));
        }

        /**
         * Replaces selected tags with a new set copied from {@code itemTags}.
         */
        public Builder setItemTags(Collection<ItemTag> itemTags) {
            this.itemTags = new HashSet<>(itemTags);
            return this;
        }

        /**
         * Builds and registers the item type using the server's class loader.
         */
        public <T extends ItemStack> ItemType<T> build() {
            return build(Allay.class.getClassLoader());
        }

        /**
         * Compiles the stack's {@link ComponentBlueprint}, validates all component
         * bindings eagerly, and registers the resulting item type.
         *
         * @throws NullPointerException   if {@code identifier} is unset
         * @throws ItemTypeBuildException if blueprint compilation or definition validation fails
         */
        @SuppressWarnings("unchecked")
        public <T extends ItemStack> ItemType<T> build(ClassLoader classLoader) {
            Preconditions.checkNotNull(identifier, "identifier");
            if (runtimeId == Integer.MAX_VALUE) {
                runtimeId = CUSTOM_ITEM_RUNTIME_ID_COUNTER++;
            }

            ComponentBlueprint<?> blueprint;
            Function<ItemStackInitInfo, T> instanceCreator;
            try {
                var implClass = ComponentBlueprint.resolveImplClass(clazz, classLoader);
                var defaultBase = new ComponentProvider.Simple<>(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class);
                var orderedProviders = ComponentBlueprint.orderWithBaseFirst(componentProviders, ItemBaseComponent.class, defaultBase);
                blueprint = ComponentBlueprint.compile(implClass, orderedProviders, ItemStackInitInfo.class);
                var compiledBlueprint = blueprint;
                instanceCreator = info -> {
                    try {
                        return (T) compiledBlueprint.newInstance(info);
                    } catch (Throwable t) {
                        throw new ItemTypeBuildException("Failed to create item stack instance!", t);
                    }
                };
            } catch (Exception e) {
                throw new ItemTypeBuildException("Failed to create item type!", e);
            }

            if (!isCustomItem && customItemDefinition != null) {
                throw new ItemTypeBuildException("Vanilla items cannot have a custom item definition");
            }
            var type = new AllayItemType<>(
                    blueprint,
                    instanceCreator,
                    identifier,
                    runtimeId,
                    itemTags,
                    itemData,
                    isCustomItem,
                    customItemDefinition
            );

            Registries.ITEMS.register(identifier, type);
            return type;
        }
    }
}
