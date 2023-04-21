package cn.allay.block.type;

import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;
import cn.allay.block.component.attribute.BlockAttributeComponentImpl;
import cn.allay.block.component.attribute.VanillaBlockAttributeRegistry;
import cn.allay.block.component.base.BlockBaseComponentImpl;
import cn.allay.block.component.injector.AllayBlockComponentInjector;
import cn.allay.block.component.position.BlockPositionComponentImpl;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.ComponentProvider;
import cn.allay.identifier.Identifier;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
 */
@Getter
public class AllayBlockType<T extends Block> implements BlockType<T> {
    protected Class<T> injectedClass;
    protected List<ComponentProvider<? extends BlockComponentImpl>> componentProviders;
    protected List<BlockPropertyType<?>> properties;
    protected Map<String, BlockPropertyType<?>> mappedProperties;
    protected Identifier namespaceId;

    public AllayBlockType(Class<T> injectedClass, List<ComponentProvider<? extends BlockComponentImpl>> componentProviders, List<BlockPropertyType<?>> properties, Identifier namespaceId) {
        this.injectedClass = injectedClass;
        this.componentProviders = componentProviders;
        this.properties = properties;
        this.namespaceId = namespaceId;
        this.mappedProperties = properties.stream().collect(Collectors.toMap(BlockPropertyType::getName, Function.identity()));
    }

    public static <T extends Block> Builder<T> builder(Class<T> blockClass) {
        return new Builder<>(blockClass);
    }

    public static class Builder<T extends Block> implements BlockTypeBuilder<T> {
        protected Class<T> blockClass;
        protected List<ComponentProvider<? extends BlockComponentImpl>> componentProviders;
        protected List<BlockPropertyType<?>> properties;
        protected Identifier namespaceId;
        protected VanillaBlockId vanillaBlockId;
        protected boolean isVanillaBlock;

        public Builder(Class<T> blockClass) {
            if (blockClass == null)
                throw new BlockTypeBuildException("Block class cannot be null");
            this.blockClass = blockClass;
        }

        //TODO: Check it
        private static <T extends ComponentImpl> List<ComponentProvider<? extends ComponentImpl>> convertList(List<ComponentProvider<? extends T>> inputList) {
            return new ArrayList<>(inputList);
        }

        public Builder<T> namespaceId(Identifier namespaceId) {
            this.namespaceId = namespaceId;
            return this;
        }

        public Builder<T> namespaceId(String namespaceId) {
            this.namespaceId = new Identifier(namespaceId);
            return this;
        }

        public Builder<T> vanillaBlock(VanillaBlockId vanillaBlockId) {
            this.vanillaBlockId = vanillaBlockId;
            this.namespaceId = vanillaBlockId.getNamespaceId();
            isVanillaBlock = true;
            return this;
        }

        public Builder<T> property(BlockPropertyType<?>... properties) {
            this.properties = List.of(properties);
            return this;
        }

        public Builder<T> property(List<BlockPropertyType<?>> properties) {
            this.properties = properties;
            return this;
        }

        public Builder<T> component(List<ComponentProvider<? extends BlockComponentImpl>> componentProviders) {
            this.componentProviders = componentProviders;
            return this;
        }

        public AllayBlockType<T> build() {
            if (namespaceId == null)
                throw new BlockTypeBuildException("Identifier cannot be null");
            if (properties == null)
                throw new BlockTypeBuildException("Properties cannot be null");
            if (componentProviders == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            var type = new AllayBlockType<T>(blockClass, componentProviders, properties, namespaceId);
            //Make sure it's not an immutable list, to add the default component
            componentProviders = new ArrayList<>(componentProviders);
            componentProviders.add(ComponentProvider.of(BlockBaseComponentImpl::new, BlockBaseComponentImpl.class));
            //TODO: 在创建方块时传入x,y,z,level，当前未实现
            componentProviders.add(ComponentProvider.of(info -> {
                var blockInitInfo = (BlockInitInfo) info;
                return new BlockPositionComponentImpl(blockInitInfo.location());
            }, BlockPositionComponentImpl.class));
            BlockAttributeComponentImpl attributeComponent;
            if (isVanillaBlock) {
                attributeComponent = VanillaBlockAttributeRegistry.getRegistry().get(vanillaBlockId);
                if (attributeComponent == null)
                    throw new BlockTypeBuildException("Cannot find vanilla block attribute component for " + vanillaBlockId + " from vanilla block attribute registry");
            }
            //default attribute component
            else attributeComponent = BlockAttributeComponentImpl.builder().build();
            //TODO: 对于所有Block实例，只持有一个BlockAttributeComponentImpl实例，需要确认后续是否需要改进
            componentProviders.add(ComponentProvider.ofSingleton(attributeComponent));
            type.injectedClass = new AllayBlockComponentInjector<>(type)
                    .parentClass(blockClass)
                    .component(convertList(componentProviders))
                    .inject();
            return type;
        }
    }
}
