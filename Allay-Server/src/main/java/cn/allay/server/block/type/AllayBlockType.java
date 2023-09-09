package cn.allay.server.block.type;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.BlockComponent;
import cn.allay.api.block.component.annotation.RequireBlockProperty;
import cn.allay.api.block.component.attribute.BlockAttributeComponentImpl;
import cn.allay.api.block.component.attribute.VanillaBlockAttributeRegistry;
import cn.allay.api.block.component.base.BlockBaseComponentImpl;
import cn.allay.api.block.component.custom.CustomBlockComponentImpl;
import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.component.interfaces.Component;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.datastruct.UnmodifiableLinkedHashMap;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.api.utils.HashUtils;
import cn.allay.server.component.exception.BlockComponentInjectException;
import cn.allay.server.component.injector.AllayComponentInjector;
import cn.allay.server.utils.ComponentClassCacheUtils;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.allay.api.component.interfaces.ComponentProvider.findComponentIdentifier;
import static java.lang.reflect.Modifier.isStatic;

/**
 * Allay Project 2023/4/15
 *
 * @author daoge_cmd | Cool_Loong
 */
@Getter
public final class AllayBlockType<T extends BlockBehavior> implements BlockType<T> {
    public static int computeSpecialValue(BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
        int nbits = 0;
        for (var value : propertyValues) nbits += value.getPropertyType().getBitSize();
        return computeSpecialValue(nbits, propertyValues);
    }

    public static int computeSpecialValue(int nbits, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
        int specialValue = 0;
        for (var value : propertyValues) {
            specialValue |= value.getIndex() << (nbits - value.getPropertyType().getBitSize());
            nbits -= value.getPropertyType().getBitSize();
        }
        return specialValue;
    }

    private final Class<T> interfaceClass;
    private Class<T> injectedClass;
    private final List<BlockComponent> components;
    private final Map<String, BlockPropertyType<?>> properties;
    private final Identifier identifier;
    @Nullable
    private final Identifier itemIdentifier;
    @Nullable
    private ItemType<?> itemTypeCache;
    private final Map<Integer, BlockState> blockStateHashMap;
    private final int specialValueBits;
    private BlockState defaultState;
    private T blockBehavior;
    @Nullable
    private Map<Integer, BlockState> specialValueMap;

    private AllayBlockType(Class<T> interfaceClass,
                           List<BlockComponent> components,
                           Map<String, BlockPropertyType<?>> properties,
                           Identifier identifier,
                           @Nullable Identifier itemIdentifier) {
        this.interfaceClass = interfaceClass;
        this.components = components;
        this.properties = Collections.unmodifiableMap(properties);
        this.identifier = identifier;
        this.itemIdentifier = itemIdentifier;
        this.blockStateHashMap = initStates();
        int nbits = 0;
        for (var value : properties.values()) nbits += value.getBitSize();
        this.specialValueBits = nbits;
        if (nbits <= 32) {
            this.specialValueMap = Collections.unmodifiableMap(blockStateHashMap.values().stream().collect(Collectors.toMap(BlockState::specialValue, Function.identity(), (v1, v2) -> v1, Int2ObjectArrayMap::new)));
        }
    }

    public static <T extends BlockBehavior> BlockTypeBuilder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @Override
    public @Nullable ItemType<?> getItemType() {
        if (itemTypeCache != null) return itemTypeCache;
        if (itemIdentifier == null) return null;
        itemTypeCache = ItemTypeRegistry.getRegistry().get(itemIdentifier);
        if (itemTypeCache == null)
            throw new IllegalStateException("Item type " + itemIdentifier + " not registered");
        return itemTypeCache;
    }

    @Override
    public BlockState ofState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        return BlockStateHashPalette.getRegistry().get(HashUtils.computeBlockStateHash(identifier, propertyValues));
    }

    @Override
    @UnmodifiableView
    public Collection<BlockState> getAllStates() {
        return Collections.unmodifiableCollection(blockStateHashMap.values());
    }

    private Map<Integer, BlockState> initStates() {
        List<BlockPropertyType<?>> propertyTypeList = this.properties.values().stream().toList();
        int size = propertyTypeList.size();
        if (size == 0) {
            this.defaultState = new AllayBlockState(this, new BlockPropertyType.BlockPropertyValue[]{});
            var singleBlockStateMap = new Int2ObjectArrayMap<BlockState>();
            singleBlockStateMap.put(defaultState.blockStateHash(), defaultState);
            return singleBlockStateMap;
        }

        var blockStates = new Int2ObjectArrayMap<BlockState>();

        // to keep track of next element in each of
        // the n arrays
        int[] indices = new int[size];

        // initialize with first element's index
        Arrays.fill(indices, 0);

        while (true) {
            // Generate BlockState
            ImmutableList.Builder<BlockPropertyType.BlockPropertyValue<?, ?, ?>> values = ImmutableList.builder();
            for (int i = 0; i < size; ++i) {
                BlockPropertyType<?> type = propertyTypeList.get(i);
                values.add(type.tryCreateValue(type.getValidValues().get(indices[i])));
            }
            var state = new AllayBlockState(this, values.build().toArray(BlockPropertyType.BlockPropertyValue[]::new));
            blockStates.put(state.blockStateHash(), state);

            // find the rightmost array that has more
            // elements left after the current element
            // in that array
            int next = size - 1;
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
        int defaultStateHash = HashUtils.computeBlockStateHash(this.identifier, properties.values().stream().map(p -> p.tryCreateValue(p.getDefaultValue())).collect(Collectors.toList()));
        for (var s : blockStates.values()) {
            if (s.blockStateHash() == defaultStateHash) {
                this.defaultState = s;
            }
        }
        return Collections.unmodifiableMap(blockStates);
    }

    /**
     * Each {@link AllayBlockState} is a singleton, stored in the {@link AllayBlockStateHashPalette AllayBlockPaletteRegistry}, which means you can directly use == to compare whether two Block States are equal
     */
    record AllayBlockState(BlockType<?> blockType,
                           BlockPropertyType.BlockPropertyValue<?, ?, ?>[] blockPropertyValues,
                           int blockStateHash,
                           int specialValue) implements BlockState {
        public AllayBlockState(BlockType<?> blockType, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues, int blockStateHash) {
            this(blockType,
                    propertyValues,
                    blockStateHash,
                    AllayBlockType.computeSpecialValue(propertyValues));
        }

        public AllayBlockState(BlockType<?> blockType, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
            this(blockType, propertyValues, HashUtils.computeBlockStateHash(blockType.getIdentifier(), Arrays.stream(propertyValues).toList()));
        }

        @Override
        public long unsignedBlockStateHash() {
            return Integer.toUnsignedLong(blockStateHash);
        }

        @Override
        public @UnmodifiableView Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues() {
            return UnmodifiableLinkedHashMap.warp(Arrays.stream(blockPropertyValues).collect(
                    LinkedHashMap<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>>::new,
                    (hashMap, blockPropertyValue) -> hashMap.put(blockPropertyValue.getPropertyType(), blockPropertyValue),
                    LinkedHashMap::putAll));
        }

        @Override
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
            if (!succeed) {
                throw new IllegalArgumentException("Property " + propertyValue.getPropertyType() + " is not supported by this block");
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
            int bits = blockType.getSpecialValueBits();
            if (bits < 32) {
                Map<Integer, BlockState> specialValueMap = blockType.getSpecialValueMap();
                assert specialValueMap != null;
                return specialValueMap.get(computeSpecialValue(bits, values));
            } else {
                return blockType.getBlockStateHashMap().get(HashUtils.computeBlockStateHash(this.blockType.getIdentifier(), values));
            }
        }
    }

    public static class Builder<T extends BlockBehavior> implements BlockTypeBuilder<T> {
        protected Class<T> interfaceClass;
        protected Map<Identifier, BlockComponent> components = new HashMap<>();
        protected Map<String, BlockPropertyType<?>> properties = new HashMap<>();
        protected Identifier identifier;
        @Nullable
        protected Identifier itemIdentifier;
        protected boolean isCustomBlock = false;
        protected Function<BlockType<T>, BlockComponent> blockBaseComponentSupplier = BlockBaseComponentImpl::new;

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
        public BlockTypeBuilder<T> itemIdentifier(Identifier itemIdentifier) {
            this.itemIdentifier = itemIdentifier;
            return this;
        }

        @Override
        public Builder<T> vanillaBlock(VanillaBlockId vanillaBlockId) {
            this.identifier = vanillaBlockId.getIdentifier();
            this.itemIdentifier = vanillaBlockId.getItemIdentifier();
            var attributeMap = VanillaBlockAttributeRegistry.getRegistry().get(vanillaBlockId);
            if (attributeMap == null)
                throw new BlockTypeBuildException("Cannot find vanilla block attribute component for " + vanillaBlockId + " from vanilla block attribute registry!");
            components.put(BlockAttributeComponentImpl.IDENTIFIER, BlockAttributeComponentImpl.ofMappedBlockStateHash(attributeMap));
            return this;
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
            this.components.put(findComponentIdentifier(component.getClass()), component);
            return this;
        }

        @Override
        public Builder<T> addCustomBlockComponent(CustomBlockComponentImpl customBlockComponent) {
            components.put(findComponentIdentifier(customBlockComponent.getClass()) ,customBlockComponent);
            isCustomBlock = true;
            return this;
        }

        @Override
        public Builder<T> setBlockBaseComponentSupplier(Function<BlockType<T>, BlockComponent> blockBaseComponentSupplier) {
            this.blockBaseComponentSupplier = blockBaseComponentSupplier;
            return this;
        }

        @Override
        public AllayBlockType<T> build() {
            if (identifier == null) throw new BlockTypeBuildException("identifier cannot be null!");
            var listComponents = new ArrayList<>(components.values());
            var type = new AllayBlockType<>(interfaceClass, listComponents, properties, identifier, itemIdentifier);
            if (!components.containsKey(BlockBaseComponentImpl.IDENTIFIER))
                listComponents.add(blockBaseComponentSupplier.apply(type));
            List<ComponentProvider<? extends Component>> componentProviders = listComponents.stream().map(ComponentProvider::ofSingleton).collect(Collectors.toList());
            try {
                checkPropertyValid();
                type.injectedClass = new AllayComponentInjector<T>()
                        .interfaceClass(interfaceClass)
                        .component(componentProviders)
                        .useCachedClass(ComponentClassCacheUtils.loadBlockType(interfaceClass))
                        .inject(true);
                //Cache constructor
                type.blockBehavior = type.injectedClass.getConstructor().newInstance();
            } catch (Exception e) {
                throw new BlockTypeBuildException("Failed to create block type!", e);
            }
            type.register(BlockTypeRegistry.getRegistry());
            type.register(BlockStateHashPalette.getRegistry());
            return type;
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
