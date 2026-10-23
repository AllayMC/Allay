package org.allaymc.server.item.type;

import com.google.common.base.Preconditions;
import com.google.common.base.Suppliers;
import lombok.Getter;
import lombok.ToString;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.data.ItemData;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.utils.BlockAndItemIdMapper;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.allaymc.server.component.ComponentProvider.toMap;

/**
 * {@code AllayItemType} represents a registered item kind with shared gameplay
 * metadata and a component-backed stack factory. Its builder resolves the stack
 * class's public initialization constructor and captures a provider list once
 * for later stack construction. {@code createItemStack} sets the supplied
 * initialization object's owning type before invoking that factory.
 *
 * <p>
 * The type retains its supplied item data, tag set, and optional custom client
 * definition rather than making deep snapshots. Block association resolves
 * lazily from the item identifier and memoizes the first result, including an
 * absent mapping. Builder registration happens only after factory preparation,
 * but automatic runtime-ID selection occurs earlier and is not rolled back
 * when a later build step fails.
 *
 * @param <T> stack interface produced by this registered item kind
 *
 * @author daoge_cmd
 */
public final class AllayItemType<T extends ItemStack> implements ItemType<T> {

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
     * The generated getter exposes that same set; callers must respect the API's
     * read-only usage contract even when the stored set is mutable.
     */
    @Getter
    private final Set<ItemTag> itemTags;
    /**
     * {@code itemData} retains shared gameplay properties supplied by the builder.
     * The generated getter returns the same object, so this type does not create
     * an independent data snapshot.
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
     * The type retains the supplied record for protocol initialization;
     * {@code null} means no custom definition was configured.
     */
    @Getter
    private final CustomItemDefinition customItemDefinition;

    /**
     * {@code blockType} memoizes identifier-based block lookup, including a
     * {@code null} result. Later block registration does not cause a previously
     * resolved mapping to be recomputed.
     */
    private final Supplier<BlockType<?>> blockType;

    /**
     * Creates item metadata and a stack factory while retaining the supplied values.
     * The tag set, item data, and custom definition are not copied; the constructor
     * prepares a memoized block lookup without resolving it immediately.
     */
    private AllayItemType(
            Function<ItemStackInitInfo, T> instanceCreator,
            Identifier identifier,
            int runtimeId,
            Set<ItemTag> itemTags,
            ItemData itemData,
            boolean customItem,
            CustomItemDefinition customItemDefinition
    ) {
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
     * Creates a builder targeting the supplied stack implementation class.
     * Building later requires its public constructor accepting {@code ItemStackInitInfo}
     * and {@code List}; this factory retains the class without validating that
     * constructor immediately.
     */
    public static <T extends ItemStack> Builder builder(Class<T> clazz) {
        return new Builder(clazz);
    }

    /**
     * Creates a stack after assigning this item type to non-{@code null}
     * initialization data. The prepared component constructor decides whether
     * {@code null} is accepted; ordinary base components require data, while air
     * uses predefined initialization.
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
     * The supplier memoizes that result, including {@code null} when no block exists,
     * so callers must wait for block registration before the initial lookup.
     */
    @Override
    public BlockType<?> getBlockType() {
        return blockType.get();
    }

    /**
     * {@code Builder} configures an item kind, prepares its stack factory, and
     * registers the resulting type. Custom items start with default data and request
     * an automatically allocated runtime ID unless the caller selects one.
     */
    @ToString
    public static class Builder {

        /**
         * {@code CUSTOM_ITEM_RUNTIME_ID_COUNTER} supplies automatic runtime IDs beginning
         * at {@code 10000}. Allocation is unsynchronized and unchecked for duplicates
         * or overflow, so callers coordinate concurrent builds using this allocator.
         */
        protected static int CUSTOM_ITEM_RUNTIME_ID_COUNTER = 10000;

        /**
         * Target item-stack implementation class.
         */
        protected Class<?> clazz;
        /**
         * {@code componentProviders} indexes providers by component identifier.
         * Setters copy map structure, additions replace matching keys, and building
         * captures a separate list containing the same provider objects.
         */
        protected Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders = new HashMap<>();
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
         * allocation. Building replaces that sentinel in this builder before preparing
         * the reflective constructor.
         */
        protected int runtimeId = Integer.MAX_VALUE;
        /**
         * {@code itemTags} holds the selected tag set shared with built types.
         * {@code setItemTags} replaces this reference with a copy of the caller's
         * collection, while building passes the reference directly.
         */
        protected Set<ItemTag> itemTags = Set.of();
        /**
         * {@code itemData} holds gameplay properties selected for the next build.
         * The initial value is shared {@code ItemData.DEFAULT}; setters and built types
         * retain the supplied reference directly.
         */
        protected ItemData itemData = ItemData.DEFAULT;
        /**
         * {@code customItemDefinition} holds optional client-definition parameters for
         * custom items. A vanilla build rejects a non-{@code null} value after factory
         * preparation.
         */
        protected CustomItemDefinition customItemDefinition;

        /**
         * Creates an item-type builder retaining the supplied stack implementation class.
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
         * Parses and selects the item's namespaced identifier, returning this builder.
         * Identifier construction applies its namespace and path rules immediately;
         * invalid text fails here before item factory preparation or registry updates.
         */
        public Builder identifier(String identifier) {
            return identifier(new Identifier(identifier));
        }

        /**
         * Selects vanilla identity, runtime ID, gameplay data, and any registered tags.
         * Required gameplay data must exist in the internal registry; missing data
         * throws after the builder has already changed its vanilla identity fields.
         * Missing tag data leaves the previous selected tags intact.
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
         * The value is retained directly, including {@code null}; callers provide usable
         * data for later gameplay and protocol consumers of the built type.
         */
        public Builder itemData(ItemData itemData) {
            this.itemData = itemData;
            return this;
        }

        /**
         * Selects non-{@code null} client-definition parameters and returns this builder.
         * The record is retained directly; a later build rejects this selection when
         * the builder is configured as a vanilla item.
         *
         * @throws NullPointerException if {@code customItemDefinition} is {@code null}
         */
        public Builder customItemDefinition(CustomItemDefinition customItemDefinition) {
            this.customItemDefinition = Objects.requireNonNull(customItemDefinition, "customItemDefinition");
            return this;
        }

        /**
         * Selects the runtime ID without validating range or uniqueness.
         * {@code Integer.MAX_VALUE} requests allocation during building; any other value
         * is retained, so callers maintain compatibility and avoid conflicting IDs.
         */
        public Builder runtimeId(int runtimeId) {
            this.runtimeId = runtimeId;
            return this;
        }

        /**
         * Replaces component selection with providers indexed by their discovered IDs.
         * The list-to-map conversion rejects duplicate component identifiers; provider
         * objects are retained, and building later supplies a base provider when its
         * identifier is absent.
         */
        public Builder setComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
            return setComponents(toMap(componentProviders));
        }

        /**
         * Replaces component selection with a shallow copy of the supplied map.
         * Later structural changes to the caller's map do not affect this builder;
         * the provider objects and their construction policies remain shared.
         */
        public Builder setComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        /**
         * Adds supplied component mappings, replacing providers at matching identifiers.
         * The map entries and provider objects are retained in the builder's own map;
         * other previously selected component identifiers remain present.
         */
        public Builder addComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        /**
         * Adds a component factory under the identifier discovered from its declared
         * class. The factory receives stack initialization data later, and a matching
         * existing component mapping is replaced without constructing a component now.
         */
        public Builder addComponent(Function<ItemStackInitInfo, ? extends ItemComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.Simple<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        /**
         * Adds the supplied provider under its discovered component identifier.
         * A matching existing provider is replaced, and the retained provider determines
         * whether later stack construction creates fresh or shared component instances.
         */
        public Builder addComponent(ComponentProvider<? extends ItemComponent> p) {
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        /**
         * Adds providers after converting the list to component-identifier mappings.
         * Duplicate identifiers within the supplied list fail conversion; otherwise
         * matching existing mappings are replaced while unrelated components remain
         * selected.
         */
        public Builder addComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
            return addComponents(toMap(componentProviders));
        }

        /**
         * Adds a supplier-backed component factory that ignores stack initialization data.
         * The supplier runs during component creation, and its policy determines
         * whether instances are fresh or shared; the declared class supplies identifier
         * lookup.
         */
        public Builder addComponent(Supplier<? extends ItemComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        /**
         * Replaces selected tags with a new set copied from the supplied collection.
         * Later collection edits do not change this set; building passes the selected
         * set directly to the item type without another wrapper.
         */
        public Builder setItemTags(Collection<ItemTag> itemTags) {
            this.itemTags = new HashSet<>(itemTags);
            return this;
        }

        /**
         * Builds and registers the item type using the server's class loader.
         * The full build path fills a missing base provider, assigns an automatic
         * runtime ID when requested, and can replace an existing registry mapping.
         */
        public <T extends ItemStack> ItemType<T> build() {
            return build(Allay.class.getClassLoader());
        }

        /**
         * Prepares a component-backed stack factory and registers a new item type.
         * The identifier must be present, and {@code clazz} must expose a public
         * constructor accepting {@code ItemStackInitInfo} and {@code List}. The chosen
         * class loader prepares reflective invocation; component provider objects are
         * captured in a separate list.
         *
         * <p>
         * The builder adds a missing base provider and replaces the automatic-ID sentinel
         * before constructor lookup, so a later failure retains those builder changes
         * and consumes any allocated ID. Stack-construction failures are wrapped when
         * the saved factory runs. A vanilla/custom-definition conflict is rejected
         * before registry registration; successful registration replaces any current
         * mapping for this identifier and fails if the registry is frozen.
         *
         * @throws NullPointerException if {@code identifier} is unset
         * @throws ItemTypeBuildException if factory preparation or definition validation fails
         */
        public <T extends ItemStack> ItemType<T> build(ClassLoader classLoader) {
            Preconditions.checkNotNull(identifier, "identifier");
            // A stack's delegated base operations require the base provider;
            // preserve an explicitly selected replacement under that identifier.
            if (!componentProviders.containsKey(ItemBaseComponentImpl.IDENTIFIER)) {
                addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class);
            }
            // Resolve the sentinel before preparing the factory. This builder
            // keeps the allocated ID even when a later build phase fails.
            if (runtimeId == Integer.MAX_VALUE) {
                runtimeId = CUSTOM_ITEM_RUNTIME_ID_COUNTER++;
            }

            Function<ItemStackInitInfo, T> instanceCreator;
            try {
                var constructor = FastConstructor.create(clazz.getConstructor(ItemStackInitInfo.class, List.class), new FastMemberLoader(classLoader), false);
                var componentProviderList = new ArrayList<>(componentProviders.values());
                instanceCreator = info -> {
                    try {
                        return (T) constructor.invoke(info, componentProviderList);
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
