package org.allaymc.server.block.type;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.material.Material;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.tag.BlockTag;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.network.ProtocolInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.BlockAndItemIdMapper;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.exception.BlockComponentInjectException;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.block.component.BlockEntityHolderComponentImpl;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.component.injector.AllayComponentInjector;
import org.allaymc.server.utils.ComponentClassCacheUtils;
import org.allaymc.server.utils.ItemMetaBlockStateBiMap;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.allaymc.api.component.interfaces.ComponentProvider.findComponentIdentifierInCertainClass;

/**
 * Allay Project 2023/4/15
 *
 * @author daoge_cmd | Cool_Loong
 */
@Getter
public final class AllayBlockType<T extends BlockBehavior> implements BlockType<T> {
    private final Class<T> interfaceClass;
    private final List<BlockComponent> components;
    private final Map<String, BlockPropertyType<?>> properties;
    private final Identifier identifier;
    private final Set<BlockTag> blockTags;
    private final Material material;
    private final ItemType<?> blockItemType;
    private final Map<Integer, BlockState> blockStateHashMap;
    private final byte specialValueBits;
    private final Map<Long, BlockState> specialValueMap;

    private Class<T> injectedClass;
    private BlockState defaultState;
    private T blockBehavior;

    private AllayBlockType(
            Class<T> interfaceClass,
            List<BlockComponent> components,
            Map<String, BlockPropertyType<?>> properties,
            Identifier identifier,
            ItemType<?> blockItemType,
            Set<BlockTag> blockTags,
            Material material
    ) {
        this.interfaceClass = interfaceClass;
        this.components = components;
        this.properties = Collections.unmodifiableMap(properties);
        this.identifier = identifier;
        this.blockTags = blockTags;
        this.material = material;
        this.blockItemType = blockItemType;
        this.blockStateHashMap = initStates();

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

    public static <T extends BlockBehavior> BlockTypeBuilder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
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

    private Map<Integer, BlockState> initStates() {
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
        var defaultStateHash = HashUtils.computeBlockStateHash(
                this.identifier,
                properties.values().stream().map(p -> p.tryCreateValue(p.getDefaultValue())).collect(Collectors.toList())
        );

        this.defaultState = blockStates.values().stream()
                .filter(s -> s.blockStateHash() == defaultStateHash)
                .findFirst()
                .orElse(this.defaultState);

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
                    SimpleItemStackInitInfo
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
                } else newPropertyValues[i] = blockPropertyValues[i];
            }

            if (!succeed)
                throw new IllegalArgumentException("Property " + propertyValue.getPropertyType() + " is not supported by this block");

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
                return blockType.getBlockStateHashMap().get(HashUtils.computeBlockStateHash(this.blockType.getIdentifier(), values));
            }
        }
    }

    @Slf4j
    public static class Builder<T extends BlockBehavior> implements BlockTypeBuilder<T> {

        protected Class<T> interfaceClass;
        protected Map<Identifier, BlockComponent> components = new HashMap<>();
        protected Map<String, BlockPropertyType<?>> properties = new HashMap<>();
        protected Identifier identifier;
        protected ItemType<?> itemType;
        protected ItemType<?> hardItemType;
        protected boolean isCustomBlock = true;
        protected Function<BlockType<T>, BlockBaseComponent> blockBaseComponentSupplier = BlockBaseComponentImpl::new;
        protected Set<BlockTag> blockTags = Set.of();
        protected Material material;

        public Builder(Class<T> interfaceClass) {
            if (interfaceClass == null)
                throw new BlockTypeBuildException("Interface class cannot be null!");
            this.interfaceClass = interfaceClass;
        }

        @Override
        public Builder<T> identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        @Override
        public Builder<T> vanillaBlock(VanillaBlockId vanillaBlockId) {
            this.isCustomBlock = false;
            this.identifier = vanillaBlockId.getIdentifier();
            var dataMap = Registries.VANILLA_BLOCK_STATE_DATA.get(vanillaBlockId);
            if (dataMap == null)
                throw new BlockTypeBuildException("Cannot find vanilla block data component for " + vanillaBlockId + " from vanilla block data registry!");
            components.put(BlockStateDataComponentImpl.IDENTIFIER, BlockStateDataComponentImpl.ofMappedBlockStateHash(dataMap));
            var tags = InternalBlockTypeData.getBlockTags(vanillaBlockId);
            if (tags.length != 0) setBlockTags(tags);
            setMaterial(Registries.MATERIALS.get(InternalBlockTypeData.getMaterialType(vanillaBlockId)));
            return this;
        }

        @Override
        public BlockTypeBuilder<T> bindBlockEntity(BlockEntityType<?> blockEntityType) {
            return addComponent(new BlockEntityHolderComponentImpl<>(blockEntityType));
        }

        @Override
        public Builder<T> setProperties(BlockPropertyType<?>... properties) {
            this.properties = Arrays.stream(properties).collect(Collectors.toMap(BlockPropertyType::getName, Function.identity()));
            return this;
        }

        @Override
        public Builder<T> setProperties(List<BlockPropertyType<?>> properties) {
            this.properties = properties.stream().collect(Collectors.toMap(BlockPropertyType::getName, Function.identity()));
            return this;
        }

        @Override
        public Builder<T> setComponents(Map<Identifier, BlockComponent> components) {
            if (components == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.components = new HashMap<>(components);
            return this;
        }

        @Override
        public Builder<T> addComponents(Map<Identifier, BlockComponent> components) {
            this.components.putAll(components);
            return this;
        }

        @Override
        public Builder<T> addComponent(BlockComponent component) {
            this.components.put(findComponentIdentifierInCertainClass(component.getClass()), component);
            return this;
        }

        @Override
        public Builder<T> setBlockBaseComponentSupplier(Function<BlockType<T>, BlockBaseComponent> blockBaseComponentSupplier) {
            this.blockBaseComponentSupplier = blockBaseComponentSupplier;
            return this;
        }

        @Override
        public Builder<T> setBlockTags(BlockTag... blockTags) {
            // Unmodifiable set
            this.blockTags = Set.of(blockTags);
            return this;
        }

        @Override
        public Builder<T> setMaterial(Material material) {
            this.material = material;
            return this;
        }

        @Override
        public AllayBlockType<T> build() {
            Objects.requireNonNull(identifier, "Identifier cannot be null!");
            prepareItemType();
            var listComponents = new ArrayList<>(components.values());
            var type = new AllayBlockType<>(interfaceClass, listComponents, properties, identifier, itemType, blockTags, material);
            if (!components.containsKey(BlockBaseComponentImpl.IDENTIFIER))
                listComponents.add(blockBaseComponentSupplier.apply(type));
            if (!components.containsKey(BlockStateDataComponentImpl.IDENTIFIER))
                listComponents.add(BlockStateDataComponentImpl.ofDefault());
            List<ComponentProvider<? extends Component>> componentProviders = listComponents.stream().map(singleton -> {
                var currentClass = singleton.getClass();
                //For anonymous class, we give it's super class to component provider
                //So that injector can work with anonymous class
                //Because anonymous class classes cannot be used directly as field types during the dynamic class generation phase
                return ComponentProvider.of(initInfo -> singleton, currentClass.isAnonymousClass() ? currentClass.getSuperclass() : currentClass);
            }).collect(Collectors.toList());
            try {
                checkPropertyValid();
                type.injectedClass = new AllayComponentInjector<T>()
                        .interfaceClass(interfaceClass)
                        .component(componentProviders)
                        .useCachedClass(ComponentClassCacheUtils.getCacheClass(interfaceClass))
                        .inject(isCustomBlock);
                type.blockBehavior = type.injectedClass.getConstructor().newInstance();
            } catch (Exception e) {
                throw new BlockTypeBuildException("Failed to create block type!", e);
            }
            type.register(Registries.BLOCKS);
            type.register(Registries.BLOCK_STATE_PALETTE);
            return type;
        }

        private void prepareItemType() {
            var itemId = BlockAndItemIdMapper.blockIdToActualBlockItemId(identifier);
            itemType = Registries.ITEMS.get(itemId);
            if (itemType == null) {
                // If the corresponding block item is not explicitly registered, automatically register one
                itemType = ItemTypeBuilder
                        .builder(ItemStack.class)
                        .identifier(itemId)
                        .build();
                hardItemType = itemType;
            } else {
                // If an additional block item has already been registered, add "item." prefix
                var hardItemId = new Identifier(itemId.namespace(), BlockAndItemIdMapper.NAMING_CONFLICT_PATH_PREFIX + itemId.path());
                // Allay will pre-register block items with the "item." prefix in the vanilla registry, so let's check again for this ID
                hardItemType = Registries.ITEMS.get(hardItemId);
                if (hardItemType == null) {
                    hardItemType = ItemTypeBuilder
                            .builder(ItemStack.class)
                            .identifier(hardItemId)
                            .build();
                }
            }
        }

        private void checkPropertyValid() {
            for (var component : components.values()) {
                var annotation = component.getClass().getAnnotation(RequireBlockProperty.Requirements.class);
                if (annotation == null) continue;
                var requirements = annotation.value();
                for (var requirement : requirements) {
                    var type = requirement.type();
                    var name = requirement.name();
                    var match = properties.get(name);
                    if (match == null)
                        throw new BlockComponentInjectException("Component " + component.getClass().getSimpleName() + " expects a block property of type " + type + " named " + name + ", but there is no match");
                    if (match.getType() != type)
                        throw new BlockComponentInjectException("Component " + component.getClass().getSimpleName() + " expects a block property of type " + type + " named " + name + ", but the type is " + properties.get(name).getType());
                }
            }
        }
    }
}
