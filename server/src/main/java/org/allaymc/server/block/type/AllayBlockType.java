package org.allaymc.server.block.type;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.data.BlockTag;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.block.component.BlockBlockEntityHolderComponentImpl;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.utils.BlockAndItemIdMapper;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.allaymc.server.component.ComponentProvider.findComponentIdentifierInClass;

/**
 * {@code AllayBlockType} represents a block family with shared behavior, property
 * domains, item association, and indexes of its canonical states. Construction
 * enumerates the property combinations and prepares their NBT-derived palette
 * hashes. When the reported property width fits in a {@code long}, a second index
 * resolves updates from the ordered property indices without recomputing the
 * serialized hash.
 *
 * <p>
 * The builder prepares the item first, then constructs the type, assembles its
 * behavior, and resolves optional client definitions before registering the block
 * and its states. Registration is incremental and has no rollback, so failure can
 * leave earlier item or block entries present. The type retains its property map's
 * backing storage and tag set; callers must preserve them after construction to
 * keep the prepared state indexes and behavior assumptions consistent.
 *
 * @author daoge_cmd | Cool_Loong
 */
@Getter
public final class AllayBlockType<T extends BlockBehavior> implements BlockType<T> {

    /**
     * {@code properties} is an unmodifiable view of the constructor's map. Its
     * backing map must remain unchanged because the state arrays and packed-key
     * order are derived during construction.
     */
    private final Map<String, BlockPropertyType<?>> properties;
    /**
     * Registered block family identifier.
     */
    private final Identifier identifier;
    /**
     * {@code blockTags} retains the builder's set without wrapping or copying. The
     * generated getter exposes it directly, so consumers must preserve its contents
     * after publication.
     */
    private final Set<BlockTag> blockTags;
    /**
     * Associated registered item type.
     */
    private final ItemType<?> itemType;
    /**
     * {@code blockStateHashMap} indexes constructed states by signed palette hash.
     * Property-bearing types receive an unmodifiable map; propertyless types retain
     * the mutable singleton map returned by {@code initStates}.
     */
    private final Map<Integer, BlockState> blockStateHashMap;
    /**
     * {@code specialValueBits} holds the sum of property widths in a byte. Domains
     * must avoid byte overflow because this reported width selects packed lookup or
     * hash fallback.
     */
    private final byte specialValueBits;
    /**
     * {@code specialValueMap} indexes packed property keys when {@code
     * specialValueBits} is at most {@code 64}. A larger reported width leaves this
     * map {@code null} for hash-based updates.
     */
    private final Map<Long, BlockState> specialValueMap;
    /**
     * Custom rather than vanilla classification.
     */
    private final boolean customBlock;

    /**
     * {@code defaultState} is selected during {@code initStates}. Propertyless
     * types use their only state directly, while property-bearing types invoke the
     * configured supplier and reject a {@code null} result.
     */
    private BlockState defaultState;
    /**
     * {@code blockBehavior} is assigned by the builder after state construction.
     * Default-state suppliers run before that assignment and must not rely on an
     * initialized behavior.
     */
    private T blockBehavior;

    /**
     * {@code customBlockDefinition} holds the resolved client appearance model
     * before block registration. It remains {@code null} when the builder has no
     * custom definition function.
     */
    private CustomBlockDefinition customBlockDefinition;

    /**
     * Creates the block family and derives its state indexes before behavior
     * assembly. The property map is wrapped without copying and the tag set is
     * retained directly, so their backing contents must remain stable after this
     * constructor.
     *
     * <p>
     * State enumeration determines the default state before the packed-key index is
     * prepared. The latter is available only when the byte-valued combined width
     * reports at most {@code 64} bits; wider reported domains retain only the hash
     * index for subsequent property updates.
     */
    private AllayBlockType(
            Map<String, BlockPropertyType<?>> properties,
            Identifier identifier,
            ItemType<?> itemType,
            Set<BlockTag> blockTags,
            boolean customBlock,
            Function<Map<Integer, BlockState>, BlockState> defaultStateSupplier
    ) {
        this.properties = Collections.unmodifiableMap(properties);
        this.identifier = identifier;
        this.blockTags = blockTags;
        this.itemType = itemType;
        this.customBlock = customBlock;
        this.blockStateHashMap = initStates(defaultStateSupplier);

        byte specialValueBits = 0;
        for (var value : properties.values()) specialValueBits += value.getBitSize();

        this.specialValueBits = specialValueBits;
        // Only keys fitting the reported long width can resolve all property
        // indices without the hash-based fallback.
        if (specialValueBits <= 64) {
            this.specialValueMap = Collections.unmodifiableMap(
                    blockStateHashMap
                            .values()
                            .stream()
                            .collect(Collectors.toMap(
                                    BlockState::specialValue,
                                    Function.identity(),
                                    (v1, v2) -> v1, Long2ObjectOpenHashMap::new)
                            )
            );
        } else {
            this.specialValueMap = null;
        }
    }

    /**
     * Computes a packed property key after summing the supplied domains' widths.
     * The sum is stored in a byte, and callers must preserve the owning type's
     * property order and use widths that fit without overflow.
     */
    public static long computeSpecialValue(BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
        byte specialValueBits = 0;
        for (var value : propertyValues) specialValueBits += value.getPropertyType().getBitSize();
        return computeSpecialValue(specialValueBits, propertyValues);
    }

    /**
     * Packs the supplied value indices into a key using the declared combined
     * width. Each array element occupies its property's bit width, with earlier
     * properties placed toward the high end. The array order must match the owning
     * type's state arrays or an equivalent combination can address a different key.
     *
     * <p>
     * The supplied width must equal the sum of the property widths and fit within
     * {@code 64} bits for the result to preserve every index. This helper does not
     * validate widths or indices, and Java shift behavior still produces a value
     * for oversized or inconsistent inputs.
     */
    public static long computeSpecialValue(byte specialValueBits, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
        // Keep wrapper order aligned with state construction; changing that order
        // would assign the same property indices different packed positions.
        long specialValue = 0;
        for (var value : propertyValues) {
            specialValue |= ((long) value.getIndex()) << (specialValueBits - value.getPropertyType().getBitSize());
            specialValueBits -= value.getPropertyType().getBitSize();
        }
        return specialValue;
    }

    /**
     * Creates a builder for the supplied behavior implementation class. The class
     * must expose the component-provider list constructor used by {@code build};
     * this factory does not verify that constructor yet.
     */
    public static <T extends BlockBehavior> Builder builder(Class<T> clazz) {
        return new Builder(clazz);
    }

    /**
     * Returns the item type selected before this block family was constructed.
     */
    @Override
    public ItemType<?> getItemType() {
        return itemType;
    }

    /**
     * Resolves the supplied serialized property combination through the global
     * state palette. Hashing sorts property names and retains the last duplicate
     * name; this lookup does not verify property-object identity or fill omitted
     * defaults.
     */
    @Override
    public BlockState ofState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        return Registries.BLOCK_STATE_PALETTE.get(HashUtils.computeBlockStateHash(identifier, propertyValues));
    }

    /**
     * Returns the value collection backed by this type's state-hash map. Callers
     * must leave it unchanged; the propertyless path currently exposes a mutable
     * backing map despite the read-only API intent.
     */
    @Override
    @UnmodifiableView
    public Collection<BlockState> getAllStates() {
        return blockStateHashMap.values();
    }

    /**
     * Enumerates the Cartesian product of this type's property domains and selects
     * its default state. Property order is captured once so every state's wrapper
     * array uses the same packed-key layout. A propertyless type creates one state
     * and bypasses {@code defaultStateSupplier}.
     *
     * <p>
     * The index vector advances the rightmost unfinished domain and resets the
     * domains to its right, visiting each combination before the supplier receives
     * the completed hash map. The supplier must return a non-{@code null} state.
     * Hash collisions overwrite earlier entries, and the propertyless map is
     * returned without the unmodifiable wrapper used for property-bearing types.
     */
    private Map<Integer, BlockState> initStates(Function<Map<Integer, BlockState>, BlockState> defaultStateSupplier) {
        var propertyTypeList = this.properties.values().stream().toList();
        var size = propertyTypeList.size();
        if (size == 0) {
            this.defaultState = new AllayBlockState(this, new BlockPropertyType.BlockPropertyValue[]{});
            return new Int2ObjectArrayMap<>(new int[]{defaultState.blockStateHash()}, new BlockState[]{defaultState});
        }

        var blockStates = new Int2ObjectOpenHashMap<BlockState>();

        // Each index selects one domain value; keeping this vector in property
        // order preserves the packed layout across every generated state.
        var indices = new int[size];
        Arrays.fill(indices, 0);

        while (true) {
            ImmutableList.Builder<BlockPropertyType.BlockPropertyValue<?, ?, ?>> values = ImmutableList.builder();
            for (int i = 0; i < size; ++i) {
                var type = propertyTypeList.get(i);
                values.add(type.tryCreateValue(type.getValidValues().get(indices[i])));
            }

            var state = new AllayBlockState(this, values.build().toArray(BlockPropertyType.BlockPropertyValue[]::new));
            blockStates.put(state.blockStateHash(), state);

            // Advance the rightmost unfinished domain and reset its suffix, so each
            // combination retains the same property order without skipping
            // combinations.
            var next = size - 1;
            while (next >= 0 && (indices[next] + 1 >= propertyTypeList.get(next).getValidValues().size())) {
                next--;
            }

            if (next < 0) break;

            indices[next]++;

            for (int i = next + 1; i < size; i++) {
                indices[i] = 0;
            }
        }

        this.defaultState = defaultStateSupplier.apply(blockStates);
        if (this.defaultState == null) {
            throw new BlockTypeBuildException("Block default state supplier cannot return null!");
        }

        return Collections.unmodifiableMap(blockStates);
    }

    /**
     * {@code Builder} configures block domains, components, item preparation, and
     * optional client definitions before registration. Builder instances hold
     * mutable configuration and share supplied component objects with the
     * constructed behavior, so configuration should finish before building and
     * publishing a block type.
     */
    @Slf4j
    public static class Builder {

        /**
         * Behavior implementation constructor class.
         */
        protected Class<?> clazz;
        /**
         * {@code components} retains singleton component instances keyed by
         * identifier. Build wraps those instances in providers, so the constructed
         * behavior reuses the configured objects.
         */
        protected Map<Identifier, BlockComponent> components = new HashMap<>();
        /**
         * {@code properties} holds the domains keyed by name. The constructed type
         * retains this map through an unmodifiable view, so subclasses must
         * preserve the backing contents after building.
         */
        protected Map<String, BlockPropertyType<?>> properties = new HashMap<>();
        /**
         * Requested block registration identifier.
         */
        protected Identifier identifier;
        /**
         * {@code itemType} receives the registered item selected by {@code
         * prepareItemType}. The type constructor retains that reference before
         * behavior construction and block publication.
         */
        protected ItemType<?> itemType;
        /**
         * Custom block construction flag.
         */
        protected boolean isCustomBlock = true;
        /**
         * Automatic missing-item creation flag.
         */
        protected boolean autoCreateItemType = true;
        /**
         * {@code itemTypeCustomizer} configures only item builders created by
         * {@code prepareItemType}. Existing registered items are reused without
         * invoking this callback.
         */
        protected Consumer<AllayItemType.Builder> itemTypeCustomizer = $ -> {
        };
        /**
         * {@code baseComponentSupplier} creates the default base component after
         * type construction. An explicitly supplied base component prevents this
         * callback from being invoked.
         */
        protected Function<BlockType<?>, BlockBaseComponent> baseComponentSupplier = BlockBaseComponentImpl::new;
        /**
         * {@code blockTags} holds the set passed directly to the constructed type.
         * {@code setBlockTags} replaces it with a copy, but the built type exposes
         * the retained set.
         */
        protected Set<BlockTag> blockTags = Set.of();
        /**
         * {@code defaultStateSupplier} selects a default from the completed hash
         * map for property-bearing types. The initial function hashes configured
         * default values; propertyless types bypass it.
         */
        protected Function<Map<Integer, BlockState>, BlockState> defaultStateSupplier = blockStates ->
                blockStates.get(HashUtils.computeBlockStateHash(
                        this.identifier,
                        properties.values().stream().map(p -> p.tryCreateValue(p.getDefaultValue())).collect(Collectors.toList())
                ));
        /**
         * {@code customBlockDefinitionFunction} is evaluated during each build
         * after behavior assembly. The built type retains the resolved definitions
         * rather than this callback.
         */
        protected Function<BlockState, CustomBlockStateDefinition> customBlockDefinitionFunction;
        /**
         * Placement rotation offset, degrees.
         */
        protected float customBlockRotationOffset;
        /**
         * {@code customBlockRawComponents} accumulates raw global client
         * components. The final definition copies its map during build, while NBT
         * payload references remain shared.
         */
        protected Map<String, NbtMap> customBlockRawComponents = new LinkedHashMap<>();

        /**
         * Creates a builder retaining the behavior implementation class. A {@code
         * null} class throws {@code BlockTypeBuildException}; constructor
         * compatibility is checked later when {@code build} reflectively creates
         * the behavior.
         */
        public Builder(Class<?> clazz) {
            if (clazz == null) {
                throw new BlockTypeBuildException("Interface class cannot be null!");
            }
            this.clazz = clazz;
        }

        /**
         * Stores the requested block identifier and returns this mutable builder.
         */
        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * Parses and stores the supplied namespaced block identifier, returning
         * this builder.
         */
        public Builder identifier(String identifier) {
            return identifier(new Identifier(identifier));
        }

        /**
         * Configures vanilla identity, state metadata, tags, and the
         * registry-defined default-state selector. Required state data must already
         * be loaded; missing data throws {@code BlockTypeBuildException}, and later
         * build expects the corresponding item to be registered.
         */
        public Builder vanillaBlock(BlockId blockId) {
            this.isCustomBlock = false;
            this.identifier = blockId.getIdentifier();

            var dataMap = InternalRegistries.BLOCK_STATE_DATA.get(blockId);
            if (dataMap == null)
                throw new BlockTypeBuildException("Cannot find vanilla block data component for " + blockId + " from vanilla block data registry!");
            components.put(BlockStateDataComponentImpl.IDENTIFIER, BlockStateDataComponentImpl.ofMappedBlockStateHash(dataMap));

            var tags = InternalRegistries.BLOCK_TAGS.get(blockId);
            if (tags != null) {
                setBlockTags(tags);
            }

            defaultStateSupplier = blockStates -> blockStates.get(InternalRegistries.BLOCK_DEFAULT_STATE_HASHES.get(blockId));
            return this;
        }

        /**
         * Adds a block-entity holder component for the supplied type and returns
         * this builder.
         */
        public Builder bindBlockEntity(BlockEntityType<?> blockEntityType) {
            return addComponent(new BlockBlockEntityHolderComponentImpl<>(blockEntityType));
        }

        /**
         * Replaces the default-state selector and returns this builder. The
         * selector runs only for property-bearing types, before behavior
         * assignment, and must return a non-{@code null} result from the
         * constructed state map.
         */
        public Builder setDefaultStateSupplier(Function<Map<Integer, BlockState>, BlockState> defaultStateSupplier) {
            this.defaultStateSupplier = defaultStateSupplier;
            return this;
        }

        /**
         * Replaces the configured property map with domains keyed by their names.
         * Duplicate names fail collection, and the resulting map's iteration order
         * becomes the layout used when constructing state wrapper arrays.
         */
        public Builder setProperties(BlockPropertyType<?>... properties) {
            this.properties = Arrays.stream(properties).collect(Collectors.toMap(BlockPropertyType::getName, Function.identity()));
            return this;
        }

        /**
         * Replaces the configured property map with domains keyed by their names.
         * Duplicate names fail collection, and the resulting map's iteration order
         * becomes the layout used when constructing state wrapper arrays.
         */
        public Builder setProperties(List<BlockPropertyType<?>> properties) {
            this.properties = properties.stream().collect(Collectors.toMap(BlockPropertyType::getName, Function.identity()));
            return this;
        }

        /**
         * Replaces component configuration with a shallow copy of the supplied map.
         * A {@code null} map throws {@code BlockTypeBuildException}; the component
         * instances remain shared and are reused when the behavior is built.
         */
        public Builder setComponents(Map<Identifier, BlockComponent> components) {
            if (components == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.components = new HashMap<>(components);
            return this;
        }

        /**
         * Adds the supplied component entries and returns this builder. Existing
         * identifiers are replaced, and component instances remain shared rather
         * than copied or recreated when the behavior is constructed.
         */
        public Builder addComponents(Map<Identifier, BlockComponent> components) {
            this.components.putAll(components);
            return this;
        }

        /**
         * Adds the component under its declared identifier and returns this
         * builder. An existing entry with that identifier is replaced, and the
         * supplied component instance is retained for later behavior construction.
         */
        public Builder addComponent(BlockComponent component) {
            this.components.put(findComponentIdentifierInClass(component.getClass()), component);
            return this;
        }

        /**
         * Replaces component configuration after indexing the supplied instances by
         * their declared identifiers. Duplicate identifiers throw {@code
         * IllegalArgumentException}; successfully indexed component objects remain
         * shared with the later constructed behavior.
         */
        public Builder setComponents(List<BlockComponent> components) {
            return setComponents(listComponentToMap(components));
        }

        /**
         * Indexes the supplied components and adds them to existing configuration.
         * Duplicate identifiers within the list throw {@code
         * IllegalArgumentException}, while identifiers already present in the
         * builder are replaced by the list entries.
         */
        public Builder addComponents(List<BlockComponent> components) {
            return addComponents(listComponentToMap(components));
        }

        /**
         * Indexes components by the identifiers discovered on their runtime
         * classes. Duplicate identifiers throw {@code IllegalArgumentException}
         * before the map is returned, preventing one list entry from silently
         * replacing another.
         */
        private Map<Identifier, BlockComponent> listComponentToMap(List<BlockComponent> components) {
            var map = new HashMap<Identifier, BlockComponent>();
            components.forEach(component -> {
                var id = findComponentIdentifierInClass(component.getClass());
                if (map.containsKey(id))
                    throw new IllegalArgumentException("Duplicate component: " + id);
                map.put(id, component);
            });
            return map;
        }

        /**
         * Replaces the factory used when no explicit base component is configured.
         * The factory receives the constructed type before behavior assignment, and
         * the builder retains it until a later build needs it.
         */
        public Builder setBaseComponentSupplier(Function<BlockType<?>, BlockBaseComponent> baseComponentSupplier) {
            this.baseComponentSupplier = baseComponentSupplier;
            return this;
        }

        /**
         * Copies the supplied tag collection into a new mutable set and returns
         * this builder.
         */
        public Builder setBlockTags(Collection<BlockTag> blockTags) {
            this.blockTags = new HashSet<>(blockTags);
            return this;
        }

        /**
         * Configures one non-{@code null} client state definition for every
         * constructed state. Build resolves the same reference for each state, and
         * vanilla block configuration rejects custom definitions before block
         * registration.
         */
        public Builder customBlockDefinition(CustomBlockStateDefinition definition) {
            Objects.requireNonNull(definition, "definition");
            return customBlockDefinition(state -> definition);
        }

        /**
         * Stores a non-{@code null} function that resolves client appearance after
         * state and behavior construction. Each build invokes it for each indexed
         * state until completion or failure, and the built type retains only the
         * resolved definitions.
         */
        public Builder customBlockDefinition(Function<BlockState, CustomBlockStateDefinition> definitionFunction) {
            this.customBlockDefinitionFunction = Objects.requireNonNull(definitionFunction, "definitionFunction");
            return this;
        }

        /**
         * Sets the placement-direction rotation offset in degrees and returns this
         * builder. Only {@code 0}, {@code 90}, {@code 180}, and {@code 270} are
         * accepted, and a nonzero offset requires a custom definition during build.
         */
        public Builder customBlockRotationOffset(float rotationOffset) {
            if (rotationOffset != 0f && rotationOffset != 90f && rotationOffset != 180f && rotationOffset != 270f) {
                throw new IllegalArgumentException("Rotation offset must be 0, 90, 180, or 270 degrees");
            }
            this.customBlockRotationOffset = rotationOffset;
            return this;
        }

        /**
         * Replaces raw global client components with a shallow insertion-ordered
         * copy of the supplied map. Build requires a custom definition when this
         * map is nonempty and validates its keys and values when constructing the
         * final model.
         */
        public Builder customBlockRawComponents(Map<String, NbtMap> components) {
            this.customBlockRawComponents = new LinkedHashMap<>(Objects.requireNonNull(components, "components"));
            return this;
        }

        /**
         * Adds or replaces a raw client component and returns this builder. Both
         * arguments must be non-{@code null}, and nonempty raw configuration
         * requires a custom block definition before build can complete.
         */
        public Builder customBlockRawComponent(String name, NbtMap component) {
            this.customBlockRawComponents.put(
                    Objects.requireNonNull(name, "name"),
                    Objects.requireNonNull(component, "component")
            );
            return this;
        }

        /**
         * Sets whether custom blocks can create missing associated items,
         * defaulting to {@code true}. Disabling creation requires a pre-registered
         * item; enabling it can also register a prefixed conflict item when a
         * direct item already exists.
         */
        public Builder autoCreateItemType(boolean autoCreateItemType) {
            this.autoCreateItemType = autoCreateItemType;
            return this;
        }

        /**
         * Sets the non-{@code null} callback applied to automatically created item
         * builders. The callback runs before the derived identifier is assigned,
         * and it does not modify item types already present in the registry.
         */
        public Builder customizeItemType(Consumer<AllayItemType.Builder> consumer) {
            this.itemTypeCustomizer = Objects.requireNonNull(consumer, "autoGeneratedItemTypeBuilderCustomizer");
            return this;
        }

        /**
         * Builds the configured block type and registers the block followed by its
         * indexed states. Item preparation occurs first and can itself register new
         * item types. The type and base component are constructed before reflective
         * behavior assembly, and the behavior class must provide the expected
         * list-accepting constructor.
         *
         * <p>
         * Custom definitions are resolved only after the behavior is ready, with
         * each result required to be non-{@code null}. Invalid definition settings,
         * callback failures, reflection failures, or registration errors abort the
         * remaining steps without undoing earlier item registration or any block
         * and palette entries already published.
         */
        public <T extends BlockBehavior> AllayBlockType<T> build() {
            Objects.requireNonNull(identifier, "Identifier cannot be null!");
            prepareItemType();

            // Supply default metadata when no data component exists, allowing the
            // assembled behavior to answer state-data requests without a
            // specialized provider.
            if (!components.containsKey(BlockStateDataComponentImpl.IDENTIFIER)) {
                components.put(BlockStateDataComponentImpl.IDENTIFIER, BlockStateDataComponentImpl.ofDefault());
            }

            // Construct states before the base component because its factory needs
            // the type; assemble behavior before resolving client definitions that
            // may inspect it.
            var type = new AllayBlockType<T>(properties, identifier, itemType, blockTags, isCustomBlock, defaultStateSupplier);
            var listComponents = new ArrayList<>(components.values());
            if (!components.containsKey(BlockBaseComponentImpl.IDENTIFIER)) {
                listComponents.add(baseComponentSupplier.apply(type));
            }

            List<ComponentProvider<? extends Component>> componentProviderList = listComponents.stream().map(singleton -> ComponentProvider.of($ -> singleton, singleton.getClass())).collect(Collectors.toList());
            try {
                type.blockBehavior = (T) clazz.getDeclaredConstructor(List.class).newInstance(componentProviderList);
            } catch (Throwable t) {
                throw new BlockTypeBuildException("Failed to create block type!", t);
            }

            if (!isCustomBlock && customBlockDefinitionFunction != null) {
                throw new BlockTypeBuildException("Vanilla blocks cannot have a custom block definition");
            }
            if (customBlockDefinitionFunction == null) {
                if (customBlockRotationOffset != 0f || !customBlockRawComponents.isEmpty()) {
                    throw new BlockTypeBuildException("Custom block options require a custom block definition");
                }
            } else {
                var stateDefinitions = new LinkedHashMap<Integer, CustomBlockStateDefinition>();
                for (var blockState : type.getAllStates()) {
                    var definition = Objects.requireNonNull(
                            customBlockDefinitionFunction.apply(blockState),
                            "Custom block definition function returned null for state " + blockState
                    );
                    stateDefinitions.put(blockState.blockStateHash(), definition);
                }
                type.customBlockDefinition = new CustomBlockDefinition(
                        customBlockRotationOffset,
                        stateDefinitions,
                        customBlockRawComponents
                );
            }

            // Publish the block before its palette entries so state consumers can
            // resolve the owner; later registration failures leave earlier entries
            // registered.
            Registries.BLOCKS.register(type.getIdentifier(), type);
            for (var blockState : type.blockStateHashMap.values()) {
                Registries.BLOCK_STATE_PALETTE.register(blockState.blockStateHash(), blockState);
            }

            return type;
        }

        /**
         * Selects the associated item and registers automatically created items
         * when custom-block configuration permits it. Vanilla blocks require an
         * existing mapped item. A custom block without one creates the direct
         * mapped item, unless automatic creation is disabled, in which case the
         * method throws {@code BlockTypeBuildException}.
         *
         * <p>
         * When a direct item already exists and automatic creation is enabled, a
         * missing prefixed conflict item is also registered. The existing direct
         * item remains in {@code itemType}; creating the prefixed entry does not
         * replace that reference. These registry writes occur before block
         * construction and are not rolled back if a later build step fails.
         */
        private void prepareItemType() {
            var itemId = BlockAndItemIdMapper.blockIdToActualBlockItemId(identifier);
            var hardItemIdWhenConflict = new Identifier(itemId.namespace(), BlockAndItemIdMapper.NAMING_CONFLICT_PATH_PREFIX + itemId.path());
            this.itemType = Registries.ITEMS.get(itemId);

            if (!isCustomBlock) {
                // Vanilla item initialization precedes block initialization, so
                // missing mapped items indicate an unmet registry prerequisite
                // rather than a custom-item creation request.
                Preconditions.checkNotNull(this.itemType);
                return;
            }

            if (this.itemType == null) {
                if (!autoCreateItemType) {
                    throw new BlockTypeBuildException(
                            "autoCreateItemType is set to false but no item type is registered for block " + identifier +
                            ". Please register the item type before building the block type, or set autoCreateItemType to true."
                    );
                }

                // Register the missing custom item before block construction so the
                // new type can retain its associated item immediately.
                var itemTypeBuilder = AllayItemType.builder(ItemStackImpl.class);
                itemTypeCustomizer.accept(itemTypeBuilder);
                this.itemType = itemTypeBuilder
                        .identifier(itemId)
                        .build();
            } else if (autoCreateItemType && Registries.ITEMS.get(hardItemIdWhenConflict) == null) {
                // Preserve the existing direct item and register the missing
                // prefixed conflict entry separately; itemType continues to refer
                // to the original direct item.
                var itemTypeBuilder = AllayItemType.builder(ItemStackImpl.class);
                itemTypeCustomizer.accept(itemTypeBuilder);
                itemTypeBuilder
                        .identifier(hardItemIdWhenConflict)
                        .build();
            }
        }
    }
}
