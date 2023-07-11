package cn.allay.server.block.type;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.component.impl.attribute.BlockAttributeComponentImpl;
import cn.allay.api.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.api.block.component.impl.base.BlockBaseComponentImpl;
import cn.allay.api.block.component.impl.custom.CustomBlockComponentImpl;
import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.block.type.*;
import cn.allay.api.component.annotation.AutoRegister;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.datastruct.UnmodifiableLinkedHashMap;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.utils.HashUtils;
import cn.allay.server.block.component.injector.AllayBlockComponentInjector;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.reflect.Modifier.isStatic;

/**
 * Allay Project 2023/4/15
 *
 * @author daoge_cmd | Cool_Loong
 */
@Getter
public final class AllayBlockType<T extends Block> implements BlockType<T> {
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
    /**
     * The constructor of the block implementation class
     */
    private Constructor<T> constructor;
    private final List<ComponentProvider<? extends BlockComponentImpl>> componentProviders;
    private final Map<String, BlockPropertyType<?>> properties;
    private final Identifier identifier;
    private final Map<Integer, BlockState> blockStateHashMap;
    private final int specialValueBits;
    private BlockState defaultState;
    @Nullable
    private Map<Integer, BlockState> specialValueMap;


    private AllayBlockType(Class<T> interfaceClass,
                           List<ComponentProvider<? extends BlockComponentImpl>> componentProviders,
                           List<BlockPropertyType<?>> properties,
                           Identifier identifier) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.properties = Collections.unmodifiableMap(properties.stream().collect(Collectors.toMap(BlockPropertyType::getName, Function.identity())));
        this.identifier = identifier;
        this.blockStateHashMap = initStates();
        int nbits = 0;
        for (var value : properties) nbits += value.getBitSize();
        this.specialValueBits = nbits;
        if (nbits <= 32) {
            this.specialValueMap = Collections.unmodifiableMap(blockStateHashMap.values().stream().collect(Collectors.toMap(BlockState::specialValue, Function.identity(), (v1, v2) -> v1, Int2ObjectArrayMap::new)));
        }
    }

    public static <T extends Block> BlockTypeBuilder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @SneakyThrows
    @Override
    public T createBlock(BlockInitInfo info) {
        return constructor.newInstance(info);
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
                           BlockPropertyType.BlockPropertyValue<?, ?, ?>[] values,
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
        public @UnmodifiableView Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues() {
            return UnmodifiableLinkedHashMap.warp(Arrays.stream(values).collect(
                    LinkedHashMap<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>>::new,
                    (hashMap, blockPropertyValue) -> hashMap.put(blockPropertyValue.getPropertyType(), blockPropertyValue),
                    LinkedHashMap::putAll));
        }

        @Override
        public BlockState setProperty(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue) {
            var newPropertyValues = new BlockPropertyType.BlockPropertyValue<?, ?, ?>[this.values.length];
            for (int i = 0; i < values.length; i++) {
                if (values[i].getPropertyType() == propertyValue.getPropertyType()) {
                    newPropertyValues[i] = propertyValue;
                } else newPropertyValues[i] = values[i];
            }
            return getNewBlockState(newPropertyValues);
        }

        @Override
        public BlockState setProperties(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
            var newPropertyValues = new BlockPropertyType.BlockPropertyValue<?, ?, ?>[this.values.length];
            for (int i = 0; i < values.length; i++) {
                int index;
                if ((index = propertyValues.indexOf(values[i])) != -1) {
                    newPropertyValues[i] = propertyValues.get(index);
                } else newPropertyValues[i] = values[i];
            }
            return getNewBlockState(values);
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

    public static class Builder<T extends Block> implements BlockTypeBuilder<T> {
        protected Class<T> interfaceClass;
        protected List<ComponentProvider<? extends BlockComponentImpl>> componentProviders = new ArrayList<>();
        protected List<BlockPropertyType<?>> properties = new ArrayList<>();
        protected Identifier identifier;
        protected boolean isCustomBlock = false;

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
        public Builder<T> identifier(String identifier) {
            this.identifier = new Identifier(identifier);
            return this;
        }

        @Override
        public Builder<T> vanillaBlock(VanillaBlockId vanillaBlockId) {
            return vanillaBlock(vanillaBlockId, true);
        }

        @Override
        public Builder<T> vanillaBlock(VanillaBlockId vanillaBlockId, boolean initVanillaBlockAttributeComponent) {
            this.identifier = vanillaBlockId.getIdentifier();
            if (initVanillaBlockAttributeComponent) {
                var attributeMap = VanillaBlockAttributeRegistry.getRegistry().get(vanillaBlockId);
                if (attributeMap == null)
                    throw new BlockTypeBuildException("Cannot find vanilla block attribute component for " + vanillaBlockId + " from vanilla block attribute registry!");
                var attributeComponent = BlockAttributeComponentImpl.ofMappedBlockStateHash(attributeMap);
                componentProviders.add(ComponentProvider.ofSingleton(attributeComponent));
            }
            return this;
        }

        @Override
        public Builder<T> withProperties(BlockPropertyType<?>... properties) {
            this.properties = List.of(properties);
            return this;
        }

        @Override
        public Builder<T> withProperties(List<BlockPropertyType<?>> properties) {
            this.properties = properties;
            return this;
        }

        @Override
        public Builder<T> setComponents(List<ComponentProvider<? extends BlockComponentImpl>> componentProviders) {
            if (componentProviders == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.componentProviders = new ArrayList<>(componentProviders);
            return this;
        }

        @Override
        public Builder<T> addComponents(List<ComponentProvider<? extends BlockComponentImpl>> componentProviders) {
            this.componentProviders.addAll(componentProviders);
            return this;
        }

        @Override
        public Builder<T> addComponent(ComponentProvider<? extends BlockComponentImpl> componentProvider) {
            this.componentProviders.add(componentProvider);
            return this;
        }

        @Override
        public Builder<T> addBasicComponents() {
            Arrays.stream(interfaceClass.getDeclaredFields())
                    .filter(field -> isStatic(field.getModifiers()))
                    .filter(field -> field.getDeclaredAnnotation(AutoRegister.class) != null)
                    .filter(field -> ComponentProvider.class.isAssignableFrom(field.getType()))
                    .sorted(Comparator.comparingInt(field -> field.getDeclaredAnnotation(AutoRegister.class).order()))
                    .forEach(field -> {
                        try {
                            addComponent((ComponentProvider<? extends BlockComponentImpl>) field.get(null));
                        } catch (IllegalAccessException e) {
                            throw new BlockTypeBuildException(e);
                        } catch (ClassCastException e) {
                            throw new BlockTypeBuildException("Field " + field.getName() + "in class" + interfaceClass + " is not a ComponentProvider<? extends BlockComponentImpl>!", e);
                        }
                    });
            return this;
        }

        @Override
        public Builder<T> addCustomBlockComponent(CustomBlockComponentImpl customBlockComponent) {
            componentProviders.add(ComponentProvider.ofSingleton(customBlockComponent));
            isCustomBlock = true;
            return this;
        }

        @Override
        public AllayBlockType<T> build() {
            if (identifier == null) throw new BlockTypeBuildException("identifier cannot be null!");
            var type = new AllayBlockType<>(interfaceClass, componentProviders, properties, identifier);
            componentProviders.add(ComponentProvider.of(info -> new BlockBaseComponentImpl(type, (BlockInitInfo) info), BlockBaseComponentImpl.class));
            try {
                type.injectedClass = new AllayBlockComponentInjector<>(type)
                        .interfaceClass(interfaceClass)
                        .component(new ArrayList<>(componentProviders))
                        .inject();
                //Cache constructor
                type.constructor = type.injectedClass.getConstructor(ComponentInitInfo.class);
            } catch (Exception e) {
                throw new BlockTypeBuildException("Failed to create block type!", e);
            }
            type.register(BlockTypeRegistry.getRegistry());
            type.register(BlockStateHashPalette.getRegistry());
            return type;
        }
    }
}
