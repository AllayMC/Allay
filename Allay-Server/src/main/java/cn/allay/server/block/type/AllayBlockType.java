package cn.allay.server.block.type;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.component.impl.attribute.BlockAttributeComponentImpl;
import cn.allay.api.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.api.block.component.impl.base.BlockBaseComponentImpl;
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
import java.util.concurrent.ConcurrentHashMap;
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
    protected List<BlockPropertyType<?>> properties;
    protected Map<String, BlockPropertyType<?>> mappedProperties;
    //blockStateHash -> blockState
    protected Map<Integer, BlockState<T>> possibleBlockStateMap = new ConcurrentHashMap<>();
    protected Identifier identifier;

    protected AllayBlockType(Class<T> interfaceClass,
                             List<ComponentProvider<? extends BlockComponentImpl>> componentProviders,
                             List<BlockPropertyType<?>> properties,
                             Identifier identifier) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.properties = properties;
        this.identifier = identifier;

        mappedProperties = properties.stream().collect(Collectors.toMap(BlockPropertyType::getName, Function.identity()));
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
    public BlockState<T> ofState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        var blockStateHash = AllayBlockState.computeBlockStateHash(identifier, propertyValues);
        //对于每一组唯一的属性值，有且仅有一个AllayBlockState与之对应
        //这意味着你可以直接用==比较两个BlockState是否相等
        return possibleBlockStateMap.computeIfAbsent(blockStateHash, k -> new AllayBlockState(propertyValues, k));
    }

    @Override
    public Map<Integer, BlockState<T>> allStates() {
        return Collections.unmodifiableMap(possibleBlockStateMap);
    }

    @ToString
    protected class AllayBlockState implements BlockState<T> {

        protected Map<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues;
        protected int blockStateHash;

        private AllayBlockState(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
            this(propertyValues, computeBlockStateHash(identifier, propertyValues));
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
        protected static int computeBlockStateHash(Identifier identifier, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
            if (identifier.equals(VanillaBlockId.UNKNOWN.getIdentifier())) {
                return -2; // This is special case
            }

            var states = new TreeMap<String, Object>();
            for (var value : propertyValues) {
                states.put(value.getPropertyType().getName(), value.getSerializedValue());
            }

            var tag = NbtMap.builder()
                    .putString("name", identifier.toString())
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
        protected Identifier identifier;

        public Builder(Class<T> interfaceClass) {
            if (interfaceClass == null)
                throw new BlockTypeBuildException("Interface class cannot be null!");
            this.interfaceClass = interfaceClass;
        }

        public Builder<T> identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder<T> identifier(String identifier) {
            this.identifier = new Identifier(identifier);
            return this;
        }

        public Builder<T> vanillaBlock(VanillaBlockId vanillaBlockId) {
            return vanillaBlock(vanillaBlockId, true);
        }

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
            if (identifier == null)
                throw new BlockTypeBuildException("identifier cannot be null!");
            var type = new AllayBlockType<>(interfaceClass, componentProviders, properties, identifier);
            componentProviders.add(ComponentProvider.of(info -> new BlockBaseComponentImpl(type, (BlockInitInfo) info), BlockBaseComponentImpl.class));
            return type.complete();
        }
    }
}
