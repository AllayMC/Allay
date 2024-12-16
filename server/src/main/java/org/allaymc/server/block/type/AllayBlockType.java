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
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.tag.BlockTag;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.network.ProtocolInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.BlockAndItemIdMapper;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.block.component.BlockEntityHolderComponentImpl;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemBlockImpl;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.utils.ItemMetaBlockStateBiMap;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.allaymc.server.component.interfaces.ComponentProvider.findComponentIdentifierInCertainClass;

/**
 * @author daoge_cmd | Cool_Loong
 */
@Getter
public final class AllayBlockType<T extends BlockBehavior> implements BlockType<T> {
    private final Map<String, BlockPropertyType<?>> properties;
    private final Identifier identifier;
    private final Set<BlockTag> blockTags;
    private final ItemType<?> blockItemType;
    private final Map<Integer, BlockState> blockStateHashMap;
    private final byte specialValueBits;
    private final Map<Long, BlockState> specialValueMap;

    private BlockState defaultState;
    private T blockBehavior;

    private AllayBlockType(
            Map<String, BlockPropertyType<?>> properties,
            Identifier identifier,
            ItemType<?> blockItemType,
            Set<BlockTag> blockTags,
            Function<Map<Integer, BlockState>, BlockState> defaultStateSupplier
    ) {
        this.properties = Collections.unmodifiableMap(properties);
        this.identifier = identifier;
        this.blockTags = blockTags;
        this.blockItemType = blockItemType;
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
        return blockItemType;
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

    /**
     * Each {@link AllayBlockState} is a singleton, stored in Registries.BLOCK_STATE_PALETTE, which means you can directly use == to compare whether two Block States are equal
     */
    record AllayBlockState(
            BlockType<?> blockType,
            BlockPropertyType.BlockPropertyValue<?, ?, ?>[] blockPropertyValues,
            NbtMap blockStateTag,
            int blockStateHash,
            long specialValue
    ) implements BlockState {
        public AllayBlockState(BlockType<?> blockType, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues, int blockStateHash) {
            this(
                    blockType,
                    propertyValues,
                    buildBlockStateTag(blockType, propertyValues),
                    blockStateHash,
                    AllayBlockType.computeSpecialValue(propertyValues)
            );
        }

        public AllayBlockState(BlockType<?> blockType, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
            this(blockType, propertyValues, HashUtils.computeBlockStateHash(blockType.getIdentifier(), Arrays.stream(propertyValues).toList()));
        }

        private static NbtMap buildBlockStateTag(BlockType<?> blockType, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
            var states = new TreeMap<String, Object>();
            for (var value : propertyValues) {
                states.put(value.getPropertyType().getName(), value.getSerializedValue());
            }

            return NbtMap.builder()
                    .putString("name", blockType.getIdentifier().toString())
                    .putCompound("states", NbtMap.fromMap(states))
                    .putInt("version", ProtocolInfo.BLOCK_STATE_VERSION_NUM)
                    .build();
        }

        @Override
        public long unsignedBlockStateHash() {
            return Integer.toUnsignedLong(blockStateHash);
        }

        @Override
        public NbtMap getBlockStateTag() {
            return blockStateTag;
        }

        @Override
        public ItemStack toItemStack() {
            var meta = ItemMetaBlockStateBiMap.getBlockStateHashToMetaMapper(blockType).apply(blockStateHash);
            return blockType.getItemType().createItemStack(
                    ItemStackInitInfo
                            .builder()
                            .meta(meta)
                            .count(1)
                            .build()
            );
        }

        @Override
        public BlockType<?> getBlockType() {
            return blockType;
        }

        @Override
        public @UnmodifiableView Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues() {
            var hashMap = new LinkedHashMap<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
            for (var blockPropertyValue : blockPropertyValues) {
                hashMap.put(blockPropertyValue.getPropertyType(), blockPropertyValue);
            }

            return Collections.unmodifiableMap(hashMap);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> DATATYPE getPropertyValue(PROPERTY p) {
            for (var property : blockPropertyValues) {
                if (property.getPropertyType() == p) {
                    return (DATATYPE) property.getValue();
                }
            }

            throw new IllegalArgumentException("Property " + p + " is not supported by this block");
        }

        @Override
        public BlockState setProperty(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue) {
            var newPropertyValues = new BlockPropertyType.BlockPropertyValue<?, ?, ?>[this.blockPropertyValues.length];
            var succeed = false;
            for (int i = 0; i < blockPropertyValues.length; i++) {
                if (blockPropertyValues[i].getPropertyType() == propertyValue.getPropertyType()) {
                    succeed = true;
                    newPropertyValues[i] = propertyValue;
                } else {
                    newPropertyValues[i] = blockPropertyValues[i];
                }
            }

            if (!succeed) {
                throw new IllegalArgumentException("Property " + propertyValue.getPropertyType() + " is not supported by block " + blockType.getIdentifier());
            }

            return getNewBlockState(newPropertyValues);
        }

        @Override
        public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> BlockState setProperty(PROPERTY property, DATATYPE value) {
            return setProperty(property.createValue(value));
        }

        @Override
        public BlockState setProperties(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
            var newPropertyValues = new BlockPropertyType.BlockPropertyValue<?, ?, ?>[this.blockPropertyValues.length];
            var succeedCount = 0;
            var succeed = new boolean[propertyValues.size()];
            for (int i = 0; i < blockPropertyValues.length; i++) {
                int index;
                if ((index = propertyValues.indexOf(blockPropertyValues[i])) != -1) {
                    succeedCount++;
                    succeed[index] = true;
                    newPropertyValues[i] = propertyValues.get(index);
                } else newPropertyValues[i] = blockPropertyValues[i];
            }

            if (succeedCount != propertyValues.size()) {
                var errorMsgBuilder = new StringBuilder("Properties ");
                for (int i = 0; i < propertyValues.size(); i++) {
                    if (!succeed[i]) {
                        errorMsgBuilder.append(propertyValues.get(i).getPropertyType().getName());
                        if (i != propertyValues.size() - 1)
                            errorMsgBuilder.append(", ");
                    }
                }

                errorMsgBuilder.append(" are not supported by this block");
                throw new IllegalArgumentException(errorMsgBuilder.toString());
            }

            return getNewBlockState(newPropertyValues);
        }

        private BlockState getNewBlockState(BlockPropertyType.BlockPropertyValue<?, ?, ?>[] values) {
            var bits = blockType.getSpecialValueBits();
            if (bits <= 64) {
                var specialValueMap = blockType.getSpecialValueMap();
                Objects.requireNonNull(specialValueMap);
                return specialValueMap.get(computeSpecialValue(bits, values));
            } else {
                // If the special value is greater than 64 bits, fallback to compute the block state hash
                return blockType.getBlockStateHashMap().get(HashUtils.computeBlockStateHash(this.blockType.getIdentifier(), values));
            }
        }
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
        protected Function<BlockType<?>, BlockBaseComponent> baseComponentSupplier = BlockBaseComponentImpl::new;
        protected Set<BlockTag> blockTags = Set.of();
        protected Function<Map<Integer, BlockState>, BlockState> defaultStateSupplier = blockStates ->
                blockStates.get(HashUtils.computeBlockStateHash(
                        this.identifier,
                        properties.values().stream().map(p -> p.tryCreateValue(p.getDefaultValue())).collect(Collectors.toList())
                ));

        public Builder(Class<?> clazz) {
            if (clazz == null)
                throw new BlockTypeBuildException("Interface class cannot be null!");
            this.clazz = clazz;
        }

        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder identifier(String identifier) {
            return identifier(new Identifier(identifier));
        }

        public Builder vanillaBlock(BlockId blockId) {
            this.isCustomBlock = false;
            this.identifier = blockId.getIdentifier();

            var dataMap = Registries.BLOCK_STATE_DATA.get(blockId);
            if (dataMap == null)
                throw new BlockTypeBuildException("Cannot find vanilla block data component for " + blockId + " from vanilla block data registry!");
            components.put(BlockStateDataComponentImpl.IDENTIFIER, BlockStateDataComponentImpl.ofMappedBlockStateHash(dataMap));

            var tags = InternalBlockTypeData.getBlockTags(blockId);
            if (tags.length != 0) setBlockTags(tags);

            defaultStateSupplier = blockStates -> blockStates.get(InternalBlockTypeData.getDefaultBlockStateHash(blockId));
            return this;
        }

        public Builder bindBlockEntity(BlockEntityType<?> blockEntityType) {
            return addComponent(new BlockEntityHolderComponentImpl<>(blockEntityType));
        }

        public Builder setDefaultStateSupplier(Function<Map<Integer, BlockState>, BlockState> defaultStateSupplier) {
            this.defaultStateSupplier = defaultStateSupplier;
            return this;
        }

        public Builder setProperties(BlockPropertyType<?>... properties) {
            this.properties = Arrays.stream(properties).collect(Collectors.toMap(BlockPropertyType::getName, Function.identity()));
            return this;
        }

        public Builder setProperties(List<BlockPropertyType<?>> properties) {
            this.properties = properties.stream().collect(Collectors.toMap(BlockPropertyType::getName, Function.identity()));
            return this;
        }

        public Builder setComponents(Map<Identifier, BlockComponent> components) {
            if (components == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.components = new HashMap<>(components);
            return this;
        }

        public Builder addComponents(Map<Identifier, BlockComponent> components) {
            this.components.putAll(components);
            return this;
        }

        public Builder addComponent(BlockComponent component) {
            this.components.put(findComponentIdentifierInCertainClass(component.getClass()), component);
            return this;
        }

        public Builder setComponents(List<BlockComponent> components) {
            return setComponents(listComponentToMap(components));
        }

        public Builder addComponents(List<BlockComponent> components) {
            return addComponents(listComponentToMap(components));
        }

        private Map<Identifier, BlockComponent> listComponentToMap(List<BlockComponent> components) {
            var map = new HashMap<Identifier, BlockComponent>();
            components.forEach(component -> {
                var id = findComponentIdentifierInCertainClass(component.getClass());
                if (map.containsKey(id))
                    throw new IllegalArgumentException("Duplicate component: " + id);
                map.put(id, component);
            });
            return map;
        }

        public Builder setBaseComponentSupplier(Function<BlockType<?>, BlockBaseComponent> baseComponentSupplier) {
            this.baseComponentSupplier = baseComponentSupplier;
            return this;
        }

        public Builder setBlockTags(BlockTag... blockTags) {
            // Unmodifiable set
            this.blockTags = Set.of(blockTags);
            return this;
        }

        public <T extends BlockBehavior> AllayBlockType<T> build() {
            Objects.requireNonNull(identifier, "Identifier cannot be null!");
            prepareItemType();
            var type = new AllayBlockType<T>(properties, identifier, itemType, blockTags, defaultStateSupplier);

            var listComponents = new ArrayList<>(components.values());
            if (!components.containsKey(BlockBaseComponentImpl.IDENTIFIER)) {
                listComponents.add(baseComponentSupplier.apply(type));
            }
            if (!components.containsKey(BlockStateDataComponentImpl.IDENTIFIER)) {
                listComponents.add(BlockStateDataComponentImpl.ofDefault());
            }

            List<ComponentProvider<? extends Component>> componentProviderList = listComponents.stream().map(singleton -> ComponentProvider.of($ -> singleton, singleton.getClass())).collect(Collectors.toList());

            try {
                type.blockBehavior = (T) clazz.getConstructors()[0].newInstance(componentProviderList);
            } catch (Throwable t) {
                throw new BlockTypeBuildException("Failed to create block type!", t);
            }
            type.register(Registries.BLOCKS);
            type.register(Registries.BLOCK_STATE_PALETTE);
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
                // If the corresponding block item is not explicitly registered, automatically register one
                this.itemType = AllayItemType
                        .builder(ItemBlockImpl.class)
                        .identifier(itemId)
                        .build();
                this.hardItemType = itemType;
            } else {
                // If an additional block item has already been registered, add "item." prefix
                // Allay will pre-register block items with the "item." prefix in the vanilla registry, so let's check again for this ID
                this.hardItemType = Registries.ITEMS.get(hardItemIdWhenConflict);
                if (this.hardItemType == null) {
                    this.hardItemType = AllayItemType
                            .builder(ItemBlockImpl.class)
                            .identifier(hardItemIdWhenConflict)
                            .build();
                }
            }
        }
    }
}
