package cn.allay.block.type;

import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;
import cn.allay.block.component.injector.AllayBlockComponentInjector;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.identifier.Identifier;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AllayBlockType<T extends Block> implements BlockType<T> {
    protected Class<T> injectedClass;
    protected List<BlockComponentImpl> components;
    protected List<BlockPropertyType<?>> properties;
    protected Identifier namespaceId;

    public static <T extends Block> Builder<T> builder(Class<T> blockClass) {
        return new Builder<>(blockClass);
    }

    protected void setInjectedClass(Class<T> injectedClass) {
        this.injectedClass = injectedClass;
    }

    public static class Builder<T extends Block> implements BlockTypeBuilder<T> {
        protected Class<T> blockClass;
        protected List<BlockComponentImpl> components;
        protected List<BlockPropertyType<?>> properties;
        protected Identifier namespaceId;

        public Builder(Class<T> blockClass) {
            if (blockClass == null)
                throw new BlockTypeBuildException("Block class cannot be null");
            this.blockClass = blockClass;
        }

        public Builder<T> namespaceId(Identifier namespaceId) {
            this.namespaceId = namespaceId;
            return this;
        }

        public Builder<T> namespaceId(String namespaceId) {
            this.namespaceId = new Identifier(namespaceId);
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

        public Builder<T> component(BlockComponentImpl component) {
            components.add(component);
            return this;
        }

        public Builder<T> components(BlockComponentImpl... components) {
            for (var component : components) component(component);
            return this;
        }

        public AllayBlockType<T> build() {
            if (namespaceId == null)
                throw new BlockTypeBuildException("Identifier cannot be null");
            if (properties == null)
                throw new BlockTypeBuildException("Properties cannot be null");
            if (components == null)
                throw new BlockTypeBuildException("Components cannot be null");
            var type = new AllayBlockType<T>(blockClass, components, properties, namespaceId);
            type.setInjectedClass(new AllayBlockComponentInjector<>(type).parentClass(blockClass).withComponent(components).inject());
            return type;
        }
    }
}
