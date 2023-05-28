package cn.allay.block.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.block.Block;
import cn.allay.block.component.BlockComponentImpl;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.component.interfaces.ComponentProvider;
import cn.allay.identifier.Identifier;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public interface BlockTypeBuilder<T extends Block> {

    ApiInstanceHolder<BlockTypeBuilderFactory> FACTORY = ApiInstanceHolder.of();

    static <T extends Block> BlockTypeBuilder<T> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    BlockTypeBuilder<T> namespaceId(Identifier namespaceId);

    BlockTypeBuilder<T> namespaceId(String namespaceId);

    BlockTypeBuilder<T> vanillaBlock(VanillaBlockId vanillaBlockId);

    BlockTypeBuilder<T> vanillaBlock(VanillaBlockId vanillaBlockId, boolean initVanillaBlockAttributeComponent);

    BlockTypeBuilder<T> withProperties(BlockPropertyType<?>... properties);

    BlockTypeBuilder<T> withProperties(List<BlockPropertyType<?>> properties);

    BlockTypeBuilder<T> setComponents(List<ComponentProvider<? extends BlockComponentImpl>> componentProviders);

    BlockTypeBuilder<T> addComponents(List<ComponentProvider<? extends BlockComponentImpl>> componentProviders);

    BlockTypeBuilder<T> addBasicComponents();

    BlockType<T> build();

    interface BlockTypeBuilderFactory {
        <T extends Block> BlockTypeBuilder<T> create(Class<T> clazz);
    }
}
