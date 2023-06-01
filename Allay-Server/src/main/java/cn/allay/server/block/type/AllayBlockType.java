package cn.allay.server.block.type;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.component.impl.attribute.BlockAttributeComponentImpl;
import cn.allay.api.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.api.block.component.impl.base.BlockBaseComponentImpl;
import cn.allay.api.block.component.impl.custom.CustomBlockComponentImpl;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.block.type.*;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.utils.HashUtils;
import cn.allay.server.block.component.injector.AllayBlockComponentInjector;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.UnmodifiableView;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Author: daoge_cmd | Cool_Loong<br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
 */
@Getter
public class AllayBlockType<T extends Block> implements BlockType<T> {
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    /**
     * The constructor of the block implementation class
     */
    protected Constructor<T> constructor;
    protected List<ComponentProvider<? extends BlockComponentImpl>> componentProviders;
    protected Identifier identifier;
    protected Map<String, BlockPropertyType<?>> properties;

    //Empty on first load, initialized in the AllayBlockPaletteRegistry
    protected Set<BlockState> allStates;
    protected BlockState defaultState;

    protected AllayBlockType(Class<T> interfaceClass,
                             List<ComponentProvider<? extends BlockComponentImpl>> componentProviders,
                             List<BlockPropertyType<?>> properties,
                             Identifier identifier) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.identifier = identifier;
        this.properties = Collections.unmodifiableMap(properties.stream().collect(Collectors.toMap(BlockPropertyType::getName, Function.identity())));
        this.allStates = new LinkedHashSet<>();
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
        return BlockStateRegistry.getRegistry().get(HashUtils.computeBlockStateHash(identifier, propertyValues));
    }

    @UnmodifiableView
    @Override
    public Set<BlockState> getAllStates() {
        return Collections.unmodifiableSet(allStates);
    }

    //package private
    Set<BlockState> getUnSafeAllStates() {
        return allStates;
    }

    void setDefaultState(BlockState defaultState) {
        this.defaultState = defaultState;
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

        public Builder<T> addCustomBlockComponent(CustomBlockComponentImpl customBlockComponent) {
            componentProviders.add(ComponentProvider.ofSingleton(customBlockComponent));
            isCustomBlock = true;
            return this;
        }

        public AllayBlockType<T> build() {
            if (identifier == null) throw new BlockTypeBuildException("identifier cannot be null!");
            var type = new AllayBlockType<>(interfaceClass, componentProviders, properties, identifier);
            //Register all blockStates for the CustomBlock
            if (isCustomBlock) {
                registerCustomBlockAllStates(type);
            }
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
            return type;
        }

        private void registerCustomBlockAllStates(AllayBlockType<T> blockType) {
            List<BlockPropertyType<?>> propertyTypeList = blockType.getProperties().values().stream().toList();
            int size = propertyTypeList.size();
            if (size == 0) {
                AllayBlockState state = new AllayBlockState(blockType, List.of());
                blockType.setDefaultState(state);
                blockType.getUnSafeAllStates().add(state);
                BlockStateRegistry.getRegistry().register(state.getBlockStateHash(), state);
                return;
            }
            ImmutableList.Builder<BlockState> states = ImmutableList.builder();

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
                states.add(new AllayBlockState(blockType, values.build()));

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
            ImmutableList<BlockState> build = states.build();
            BlockState defaultState = new AllayBlockState(blockType, blockType.properties.values().stream().map(p -> p.tryCreateValue(p.getDefaultValue())).collect(Collectors.toList()));
            blockType.setDefaultState(defaultState);
            blockType.getUnSafeAllStates().add(defaultState);
            BlockStateRegistry.getRegistry().register(defaultState.getBlockStateHash(), defaultState);
            int defaultStateHash = defaultState.getBlockStateHash();
            for (int i = 0, len = build.size(); i < len; i++) {
                BlockState blockState = build.get(i);
                if (defaultStateHash == blockState.getBlockStateHash()) {
                    continue;
                }
                blockType.getUnSafeAllStates().add(blockState);
                BlockStateRegistry.getRegistry().register(blockState.getBlockStateHash(), blockState);
            }
        }
    }
}
