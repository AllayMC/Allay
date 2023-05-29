package cn.allay.server.block.type;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.component.impl.attribute.BlockAttributeComponentImpl;
import cn.allay.api.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.api.block.component.impl.base.BlockBaseComponentImpl;
import cn.allay.api.block.palette.BlockPaletteRegistry;
import cn.allay.api.block.property.BlockState;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.block.type.BlockInitInfo;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.block.component.injector.AllayBlockComponentInjector;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.jetbrains.annotations.UnmodifiableView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
 */
@Getter
public class AllayBlockType<T extends Block> implements BlockType<T> {
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected Constructor<T> constructor;
    protected List<ComponentProvider<? extends BlockComponentImpl>> componentProviders;
    protected Map<String, BlockPropertyType<?>> properties;
    protected BlockState<T> defaultState;
    protected Identifier namespaceId;

    protected AllayBlockType(Class<T> interfaceClass,
                             List<ComponentProvider<? extends BlockComponentImpl>> componentProviders,
                             List<BlockPropertyType<?>> properties,
                             Identifier namespaceId) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.properties = Collections.unmodifiableMap(properties.stream().collect(Collectors.toMap(BlockPropertyType::getName, Function.identity())));
        this.defaultState = ofState(properties.stream().map(p -> (BlockPropertyType.BlockPropertyValue<?, ?, ?>) p.getDefaultValue()).collect(Collectors.toList()));
        this.namespaceId = namespaceId;
    }

    @SneakyThrows
    protected AllayBlockType<T> complete() {
        try {
            injectedClass = new AllayBlockComponentInjector<>(this)
                    .interfaceClass(interfaceClass)
                    .component(new ArrayList<>(componentProviders))
                    .inject();
        } catch (Exception e) {
            throw new BlockTypeBuildException("Failed to create block type!", e);
        }
        //Cache constructor
        constructor = injectedClass.getConstructor(ComponentInitInfo.class);
        return this;
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
    @SuppressWarnings("unchecked")
    public BlockState<T> ofState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        var blockStateHash = AllayBlockState.computeBlockStateHash(namespaceId, propertyValues);
        //对于每一组唯一的属性值，有且仅有一个AllayBlockState与之对应
        //这意味着你可以直接用==比较两个BlockState是否相等
        return (BlockState<T>) BlockPaletteRegistry.getRegistry().get(blockStateHash).blockState();
    }

    @Override
    public Map<Integer, BlockState<T>> allStates() {
        List<BlockPropertyType<?>> propertyTypeList = this.properties.values().stream().collect(Collectors.toList());
        int size = propertyTypeList.size();
        if (size == 0) {
            BlockState<T> state = ofState();
            return Map.of(state.getBlockStateHash(), state);
        }

        ImmutableList.Builder<BlockState<T>> states = ImmutableList.builder();

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
            states.add(ofState(values.build()));

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
        ImmutableList<BlockState<T>> build = states.build();
        return Collections.unmodifiableMap(build.stream().collect(Collectors.toMap(BlockState::getBlockStateHash, Function.identity())));
    }

    @ToString
    protected class AllayBlockState implements BlockState<T> {

        protected Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues;
        protected int blockStateHash;

        private AllayBlockState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
            this(propertyValues, computeBlockStateHash(namespaceId, propertyValues));
        }

        private AllayBlockState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues, int blockStateHash) {
            var mappedPropertyValues = new HashMap<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
            for (var value : propertyValues)
                mappedPropertyValues.put(value.getPropertyType(), value);
            this.propertyValues = mappedPropertyValues;
            this.blockStateHash = blockStateHash;
        }

        @Override
        public BlockType<T> getBlockType() {
            return AllayBlockType.this;
        }

        @UnmodifiableView
        @Override
        public Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> getPropertyValues() {
            return propertyValues;
        }

        @Override
        public int getBlockStateHash() {
            return blockStateHash;
        }

        @Override
        public long getUnsignedBlockStateHash() {
            return Integer.toUnsignedLong(blockStateHash);
        }

        @Override
        public BlockState<T> updatePropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> newPropertyValue) {
            var newPropertyValues = new ArrayList<BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
            for (var value : propertyValues.values()) {
                if (value.getPropertyType() == newPropertyValue.getPropertyType())
                    newPropertyValues.add(newPropertyValue);
                else newPropertyValues.add(value);
            }
            return ofState(newPropertyValues);
        }

        //https://gist.github.com/Alemiz112/504d0f79feac7ef57eda174b668dd345
        protected static int computeBlockStateHash(Identifier namespaceId, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
            if (namespaceId.equals(VanillaBlockId.UNKNOWN.getNamespaceId())) {
                return -2; // This is special case
            }

            var states = new TreeMap<String, Object>();
            for (var value : propertyValues) {
                states.put(value.getPropertyType().getName(), value.getSerializedValue());
            }

            var tag = NbtMap.builder()
                    .putString("name", namespaceId.toString())
                    .putCompound("states", NbtMap.fromMap(states))
                    .build();

            byte[] bytes;
            try (var stream = new ByteArrayOutputStream();
                 var outputStream = NbtUtils.createWriterLE(stream)) {
                outputStream.writeTag(tag);
                bytes = stream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return fnv1a_32(bytes);
        }

        protected static final int FNV1_32_INIT = 0x811c9dc5;
        protected static final int FNV1_PRIME_32 = 0x01000193;

        protected static int fnv1a_32(byte[] data) {
            int hash = FNV1_32_INIT;
            for (byte datum : data) {
                hash ^= (datum & 0xff);
                hash *= FNV1_PRIME_32;
            }
            return hash;
        }
    }

    public static class Builder<T extends Block> implements BlockTypeBuilder<T> {
        protected Class<T> interfaceClass;
        protected List<ComponentProvider<? extends BlockComponentImpl>> componentProviders = new ArrayList<>();
        protected List<BlockPropertyType<?>> properties = new ArrayList<>();
        protected Identifier namespaceId;

        public Builder(Class<T> interfaceClass) {
            if (interfaceClass == null)
                throw new BlockTypeBuildException("Interface class cannot be null!");
            this.interfaceClass = interfaceClass;
        }

        public Builder<T> namespaceId(Identifier identifier) {
            this.namespaceId = identifier;
            return this;
        }

        public Builder<T> namespaceId(String namespaceId) {
            this.namespaceId = new Identifier(namespaceId);
            return this;
        }

        public Builder<T> vanillaBlock(VanillaBlockId vanillaBlockId) {
            return vanillaBlock(vanillaBlockId, true);
        }

        public Builder<T> vanillaBlock(VanillaBlockId vanillaBlockId, boolean initVanillaBlockAttributeComponent) {
            this.namespaceId = vanillaBlockId.getNamespaceId();
            if (initVanillaBlockAttributeComponent) {
                var attributeMap = VanillaBlockAttributeRegistry.getRegistry().get(vanillaBlockId);
                if (attributeMap == null)
                    throw new BlockTypeBuildException("Cannot find vanilla block attribute component for " + vanillaBlockId + " from vanilla block attribute registry!");
                var attributeComponent = BlockAttributeComponentImpl.ofMappedBlockStateHash(attributeMap);
                componentProviders.add(ComponentProvider.ofSingleton(attributeComponent));
            }
            return this;
        }

        public Builder<T> withProperties(BlockPropertyType<?>... properties) {
            this.properties = List.of(properties);
            return this;
        }

        public Builder<T> withProperties(List<BlockPropertyType<?>> properties) {
            this.properties = properties;
            return this;
        }

        public Builder<T> setComponents(List<ComponentProvider<? extends BlockComponentImpl>> componentProviders) {
            if (componentProviders == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.componentProviders = new ArrayList<>(componentProviders);
            return this;
        }

        public Builder<T> addComponents(List<ComponentProvider<? extends BlockComponentImpl>> componentProviders) {
            this.componentProviders.addAll(componentProviders);
            return this;
        }

        public Builder<T> addBasicComponents() {
            //Unused
            return this;
        }

        public AllayBlockType<T> build() {
            if (namespaceId == null)
                throw new BlockTypeBuildException("NamespaceId cannot be null!");
            var type = new AllayBlockType<>(interfaceClass, componentProviders, properties, namespaceId);
            componentProviders.add(ComponentProvider.of(info -> new BlockBaseComponentImpl(type, (BlockInitInfo) info), BlockBaseComponentImpl.class));
            return type.complete();
        }
    }
}
