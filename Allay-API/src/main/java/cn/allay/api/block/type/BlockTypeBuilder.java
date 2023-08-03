package cn.allay.api.block.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.component.impl.custom.CustomBlockComponentImpl;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.identifier.Identifier;

import java.util.List;
import java.util.function.Function;

/**
 * Allay Project 2023/3/19
 *
 * @author daoge_cmd
 */
public interface BlockTypeBuilder<T extends BlockBehavior> {

    ApiInstanceHolder<BlockTypeBuilderFactory> FACTORY = ApiInstanceHolder.of();

    static <T extends BlockBehavior> BlockTypeBuilder<T> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    BlockTypeBuilder<T> identifier(Identifier identifier);

    BlockTypeBuilder<T> identifier(String identifier);

    BlockTypeBuilder<T> vanillaBlock(VanillaBlockId vanillaBlockId);

    BlockTypeBuilder<T> vanillaBlock(VanillaBlockId vanillaBlockId, boolean initVanillaBlockAttributeComponent);

    BlockTypeBuilder<T> setProperties(BlockPropertyType<?>... properties);

    BlockTypeBuilder<T> setProperties(List<BlockPropertyType<?>> properties);

    BlockTypeBuilder<T> setComponents(List<BlockComponentImpl> components);

    BlockTypeBuilder<T> addComponents(List<BlockComponentImpl> components);

    BlockTypeBuilder<T> addComponent(BlockComponentImpl component);

    BlockTypeBuilder<T> addBasicComponents();

    BlockTypeBuilder<T> addCustomBlockComponent(CustomBlockComponentImpl customBlockComponent);

    BlockTypeBuilder<T> setBlockBaseComponentSupplier(Function<BlockType<T>, BlockComponentImpl> blockBaseComponentSupplier);

    BlockType<T> build();

    interface BlockTypeBuilderFactory {
        <T extends BlockBehavior> BlockTypeBuilder<T> create(Class<T> clazz);
    }
}
