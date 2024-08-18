package org.allaymc.api.block.type;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.material.Material;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.tag.BlockTag;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.utils.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.allaymc.api.component.interfaces.ComponentProvider.findComponentIdentifierInCertainClass;

/**
 * Allay Project 2023/3/19
 *
 * @author daoge_cmd
 */
public interface BlockTypeBuilder<T extends BlockBehavior> {

    ApiInstanceHolder<BlockTypeBuilderFactory> FACTORY = ApiInstanceHolder.create();

    static <T extends BlockBehavior> BlockTypeBuilder<T> builder(Class<T> clazz) {
        return FACTORY.get().create(clazz);
    }

    BlockTypeBuilder<T> identifier(Identifier identifier);

    default BlockTypeBuilder<T> identifier(String identifier) {
        return identifier(new Identifier(identifier));
    }

    BlockTypeBuilder<T> vanillaBlock(VanillaBlockId vanillaBlockId);

    BlockTypeBuilder<T> bindBlockEntity(BlockEntityType<?> blockEntityType);

    BlockTypeBuilder<T> setProperties(BlockPropertyType<?>... properties);

    BlockTypeBuilder<T> setProperties(List<BlockPropertyType<?>> properties);

    default BlockTypeBuilder<T> setComponents(List<BlockComponent> components) {
        return setComponents(listComponentToMap(components));
    }

    BlockTypeBuilder<T> setComponents(Map<Identifier, BlockComponent> components);

    default BlockTypeBuilder<T> addComponents(List<BlockComponent> components) {
        return addComponents(listComponentToMap(components));
    }

    BlockTypeBuilder<T> addComponents(Map<Identifier, BlockComponent> components);

    private Map<Identifier, BlockComponent> listComponentToMap(List<BlockComponent> components) {
        var map = new HashMap<Identifier, BlockComponent>();
        components.forEach(component -> {
            var id = findComponentIdentifierInCertainClass(component.getClass());
            if (map.containsKey(id))
                throw new IllegalArgumentException("Duplicate component: " + id);
            map.put(id, component);
        });
        return map;
    }

    BlockTypeBuilder<T> addComponent(BlockComponent component);

    BlockTypeBuilder<T> setBlockBaseComponentSupplier(Function<BlockType<T>, BlockBaseComponent> blockBaseComponentSupplier);

    BlockTypeBuilder<T> setBlockTags(BlockTag... blockTags);

    BlockTypeBuilder<T> setMaterial(Material material);

    BlockType<T> build();

    interface BlockTypeBuilderFactory {
        <T extends BlockBehavior> BlockTypeBuilder<T> create(Class<T> clazz);
    }
}
