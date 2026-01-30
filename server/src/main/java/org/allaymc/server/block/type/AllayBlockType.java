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
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.allaymc.server.component.ComponentProvider.findComponentIdentifierInClass;

/**
 * @author daoge_cmd | Cool_Loong
 */
@Getter
public final class AllayBlockType<T extends BlockBehavior> implements BlockType<T> {

    private final Map<String, BlockPropertyType<?>> properties;
    private final Identifier identifier;
    private final Set<BlockTag> blockTags;
    private final ItemType<?> itemType;
    private final Map<Integer, BlockState> blockStateHashMap;
    private final byte specialValueBits;
    private final Map<Long, BlockState> specialValueMap;
    private final boolean customBlock;

    private BlockState defaultState;
    private T blockBehavior;
    private BlockDefinition blockDefinition;

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

    public static long computeSpecialValue(BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
        byte specialValueBits = 0;
        for (var value : propertyValues) specialValueBits += value.getPropertyType().getBitSize();
        return computeSpecialValue(specialValueBits, propertyValues);
    }

    public static long computeSpecialValue(byte specialValueBits, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
        long specialValue = 0;
        for (var value : propertyValues) {
            specialValue |= ((long) value.getIndex()) << (specialValueBits - value.getPropertyType().getBitSize());
            specialValueBits -= value.getPropertyType().getBitSize();
        }
        return specialValue;
    }

    public static <T extends BlockBehavior> Builder builder(Class<T> clazz) {
        return new Builder(clazz);
    }

    @Override
    public ItemType<?> getItemType() {
        return itemType;
    }

    @Override
    public BlockState ofState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        return Registries.BLOCK_STATE_PALETTE.get(HashUtils.computeBlockStateHash(identifier, propertyValues));
    }

    @Override
    @UnmodifiableView
    public Collection<BlockState> getAllStates() {
        // blockStateHashMap is unmodifiableMap, so values is unmodifiableCollection
        return blockStateHashMap.values();
    }

    private Map<Integer, BlockState> initStates(Function<Map<Integer, BlockState>, BlockState> defaultStateSupplier) {
        var propertyTypeList = this.properties.values().stream().toList();
        var size = propertyTypeList.size();
        if (size == 0) {
            this.defaultState = new AllayBlockState(this, new BlockPropertyType.BlockPropertyValue[]{});
            return new Int2ObjectArrayMap<>(new int[]{defaultState.blockStateHash()}, new BlockState[]{defaultState});
        }

        var blockStates = new Int2ObjectOpenHashMap<BlockState>();

        // to keep track of next element in each of
        // the n arrays
        var indices = new int[size];
        // initialize with first element's index
        Arrays.fill(indices, 0);

        while (true) {
            // Generate BlockState
            ImmutableList.Builder<BlockPropertyType.BlockPropertyValue<?, ?, ?>> values = ImmutableList.builder();
            for (int i = 0; i < size; ++i) {
                var type = propertyTypeList.get(i);
                values.add(type.tryCreateValue(type.getValidValues().get(indices[i])));
            }

            var state = new AllayBlockState(this, values.build().toArray(BlockPropertyType.BlockPropertyValue[]::new));
            blockStates.put(state.blockStateHash(), state);

            // find the rightmost array that has more
            // elements left after the current element
            // in that array
            var next = size - 1;
            while (next >= 0 && (indices[next] + 1 >= propertyTypeList.get(next).getValidValues().size())) {
                next--;
            }

            // no such array is found so no more
            // combinations left
            if (next < 0) break;

            // if found move to next element in that
            // array
            indices[next]++;

            // for all arrays to the right of this
            // array current index again points to
            // first element
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

    @Slf4j
    public static class Builder {

        protected Class<?> clazz;
        protected Map<Identifier, BlockComponent> components = new HashMap<>();
        protected Map<String, BlockPropertyType<?>> properties = new HashMap<>();
        protected Identifier identifier;
        protected ItemType<?> itemType;
        protected ItemType<?> hardItemType;
        protected boolean isCustomBlock = true;
        protected boolean autoCreateItemType = true;
        protected Function<BlockType<?>, BlockBaseComponent> baseComponentSupplier = BlockBaseComponentImpl::new;
        protected Set<BlockTag> blockTags = Set.of();
        protected Function<Map<Integer, BlockState>, BlockState> defaultStateSupplier = blockStates ->
                blockStates.get(HashUtils.computeBlockStateHash(
                        this.identifier,
                        properties.values().stream().map(p -> p.tryCreateValue(p.getDefaultValue())).collect(Collectors.toList())
                ));
        protected BlockDefinitionGenerator blockDefinitionGenerator = $ -> BlockDefinition.DEFAULT;

        /**
         * Creates a new block type builder.
         *
         * @param clazz the block behavior interface class
         * @throws BlockTypeBuildException if clazz is null
         */
        public Builder(Class<?> clazz) {
            if (clazz == null) {
                throw new BlockTypeBuildException("Interface class cannot be null!");
            }
            this.clazz = clazz;
        }

        /**
         * Sets the block identifier.
         *
         * @param identifier the block identifier
         * @return this builder
         */
        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * Sets the block identifier from a string.
         *
         * @param identifier the block identifier string (e.g., "myplugin:my_block")
         * @return this builder
         */
        public Builder identifier(String identifier) {
            return identifier(new Identifier(identifier));
        }

        /**
         * Configures this builder for a vanilla block.
         * <p>
         * This method sets up the block state data, block tags, and default state
         * from the internal registries based on the vanilla block ID.
         *
         * @param blockId the vanilla block ID
         * @return this builder
         * @throws BlockTypeBuildException if vanilla block data is not found
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
         * Binds a block entity type to this block.
         * <p>
         * Blocks with bound block entities will automatically create and manage
         * block entity instances when placed in the world.
         *
         * @param blockEntityType the block entity type to bind
         * @return this builder
         */
        public Builder bindBlockEntity(BlockEntityType<?> blockEntityType) {
            return addComponent(new BlockBlockEntityHolderComponentImpl<>(blockEntityType));
        }

        /**
         * Sets a custom supplier for determining the default block state.
         *
         * @param defaultStateSupplier a function that takes a map of block states and returns the default state
         * @return this builder
         */
        public Builder setDefaultStateSupplier(Function<Map<Integer, BlockState>, BlockState> defaultStateSupplier) {
            this.defaultStateSupplier = defaultStateSupplier;
            return this;
        }

        /**
         * Sets the block properties (e.g., facing direction, powered state).
         *
         * @param properties the block properties
         * @return this builder
         */
        public Builder setProperties(BlockPropertyType<?>... properties) {
            this.properties = Arrays.stream(properties).collect(Collectors.toMap(BlockPropertyType::getName, Function.identity()));
            return this;
        }

        /**
         * Sets the block properties from a list.
         *
         * @param properties the list of block properties
         * @return this builder
         */
        public Builder setProperties(List<BlockPropertyType<?>> properties) {
            this.properties = properties.stream().collect(Collectors.toMap(BlockPropertyType::getName, Function.identity()));
            return this;
        }

        /**
         * Sets the block components, replacing any existing components.
         *
         * @param components a map of component identifiers to components
         * @return this builder
         * @throws BlockTypeBuildException if components is null
         */
        public Builder setComponents(Map<Identifier, BlockComponent> components) {
            if (components == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.components = new HashMap<>(components);
            return this;
        }

        /**
         * Adds multiple components to this block.
         *
         * @param components a map of component identifiers to components
         * @return this builder
         */
        public Builder addComponents(Map<Identifier, BlockComponent> components) {
            this.components.putAll(components);
            return this;
        }

        /**
         * Adds a single component to this block.
         *
         * @param component the component to add
         * @return this builder
         */
        public Builder addComponent(BlockComponent component) {
            this.components.put(findComponentIdentifierInClass(component.getClass()), component);
            return this;
        }

        /**
         * Sets the block components from a list, replacing any existing components.
         *
         * @param components the list of components
         * @return this builder
         */
        public Builder setComponents(List<BlockComponent> components) {
            return setComponents(listComponentToMap(components));
        }

        /**
         * Adds multiple components from a list to this block.
         *
         * @param components the list of components to add
         * @return this builder
         */
        public Builder addComponents(List<BlockComponent> components) {
            return addComponents(listComponentToMap(components));
        }

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
         * Sets a custom supplier for the base component.
         * <p>
         * The base component provides core block functionality. Use this to customize
         * the base behavior of the block.
         *
         * @param baseComponentSupplier a function that creates a base component from a block type
         * @return this builder
         */
        public Builder setBaseComponentSupplier(Function<BlockType<?>, BlockBaseComponent> baseComponentSupplier) {
            this.baseComponentSupplier = baseComponentSupplier;
            return this;
        }

        /**
         * Sets the block tags for this block.
         * <p>
         * Block tags are used for grouping blocks and checking block properties
         * (e.g., "minecraft:logs", "minecraft:planks").
         *
         * @param blockTags the collection of block tags
         * @return this builder
         */
        public Builder setBlockTags(Collection<BlockTag> blockTags) {
            this.blockTags = new HashSet<>(blockTags);
            return this;
        }

        /**
         * Sets the block definition generator for custom blocks.
         * <p>
         * The generator creates the client-side block definition that is sent to players.
         * Use {@link CustomBlockDefinitionGenerator} for custom block rendering configuration.
         *
         * @param blockDefinitionGenerator the block definition generator
         * @return this builder
         * @see CustomBlockDefinitionGenerator
         */
        public Builder blockDefinitionGenerator(BlockDefinitionGenerator blockDefinitionGenerator) {
            this.blockDefinitionGenerator = blockDefinitionGenerator;
            return this;
        }

        /**
         * Sets whether to automatically create a block item type if the user hasn't registered one.
         * <p>
         * When set to {@code false}, you should pre-register the block item type manually before
         * building this block type. This allows for custom item type configuration.
         * <p>
         * Default is {@code true}.
         *
         * @param autoCreateItemType whether to auto-create item type
         * @return this builder
         */
        public Builder autoCreateItemType(boolean autoCreateItemType) {
            this.autoCreateItemType = autoCreateItemType;
            return this;
        }

        /**
         * Builds and registers the block type.
         * <p>
         * This method creates the block type, registers it to the block registry,
         * and registers all block states to the block state palette.
         *
         * @param <T> the block behavior type
         * @return the built and registered block type
         * @throws NullPointerException    if identifier is not set
         * @throws BlockTypeBuildException if block type creation fails or if autoCreateItemType
         *                                 is false and no item type is registered
         */
        public <T extends BlockBehavior> AllayBlockType<T> build() {
            Objects.requireNonNull(identifier, "Identifier cannot be null!");
            prepareItemType();

            // Add a default block state data component if missing
            if (!components.containsKey(BlockStateDataComponentImpl.IDENTIFIER)) {
                components.put(BlockStateDataComponentImpl.IDENTIFIER, BlockStateDataComponentImpl.ofDefault());
            }

            // Create the block type instance first since we need this to create base component
            var type = new AllayBlockType<T>(properties, identifier, itemType, blockTags, isCustomBlock, defaultStateSupplier);
            var listComponents = new ArrayList<>(components.values());
            // Create and add the base component
            if (!components.containsKey(BlockBaseComponentImpl.IDENTIFIER)) {
                listComponents.add(baseComponentSupplier.apply(type));
            }

            List<ComponentProvider<? extends Component>> componentProviderList = listComponents.stream().map(singleton -> ComponentProvider.of($ -> singleton, singleton.getClass())).collect(Collectors.toList());
            try {
                type.blockBehavior = (T) clazz.getDeclaredConstructor(List.class).newInstance(componentProviderList);
            } catch (Throwable t) {
                throw new BlockTypeBuildException("Failed to create block type!", t);
            }

            // Generate block definition after we initialized the block behavior instance
            type.blockDefinition = blockDefinitionGenerator.generate(type);

            Registries.BLOCKS.register(type.getIdentifier(), type);
            for (var blockState : type.blockStateHashMap.values()) {
                Registries.BLOCK_STATE_PALETTE.register(blockState.blockStateHash(), blockState);
            }

            return type;
        }

        private void prepareItemType() {
            var itemId = BlockAndItemIdMapper.blockIdToActualBlockItemId(identifier);
            var hardItemIdWhenConflict = new Identifier(itemId.namespace(), BlockAndItemIdMapper.NAMING_CONFLICT_PATH_PREFIX + itemId.path());
            this.itemType = Registries.ITEMS.get(itemId);

            if (!isCustomBlock) {
                // This is a vanilla block
                // The hard item type (when conflict) for vanilla block is pre-registered
                Preconditions.checkNotNull(this.itemType);
                this.hardItemType = Registries.ITEMS.get(hardItemIdWhenConflict);
                if (this.hardItemType == null) {
                    // The actual item type and the hard item type
                    // are the same for this vanilla block
                    this.hardItemType = itemType;
                }
                return;
            }

            if (this.itemType == null) {
                if (!autoCreateItemType) {
                    // User explicitly disabled auto-creation but didn't register the item type
                    throw new BlockTypeBuildException(
                            "autoCreateItemType is set to false but no item type is registered for block " + identifier +
                            ". Please register the item type before building the block type, or set autoCreateItemType to true."
                    );
                }

                // If the corresponding block item is not explicitly registered, automatically register one
                this.itemType = AllayItemType
                        .builder(ItemStackImpl.class)
                        .identifier(itemId)
                        .build();
                this.hardItemType = itemType;
            } else {
                if (!autoCreateItemType) {
                    // User pre-registered the item type, use it directly without creating "item." prefix version
                    this.hardItemType = itemType;
                    return;
                }

                // If an additional block item has already been registered, add "item." prefix
                // Allay will pre-register block items with the "item." prefix in the vanilla registry, so let's check again for this ID
                this.hardItemType = Registries.ITEMS.get(hardItemIdWhenConflict);
                if (this.hardItemType == null) {
                    this.hardItemType = AllayItemType
                            .builder(ItemStackImpl.class)
                            .identifier(hardItemIdWhenConflict)
                            .build();
                }
            }
        }
    }
}
