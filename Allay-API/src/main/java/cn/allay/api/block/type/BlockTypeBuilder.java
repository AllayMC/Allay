package cn.allay.api.block.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.component.impl.custom.CustomBlockComponentImpl;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.identifier.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    default BlockTypeBuilder<T> setComponents(List<BlockComponentImpl> components) {
        return setComponents(listComponentToMap(components));
    }

    BlockTypeBuilder<T> setComponents(Map<Identifier, BlockComponentImpl> components);

    default BlockTypeBuilder<T> addComponents(List<BlockComponentImpl> components) {
        return addComponents(listComponentToMap(components));
    }

    BlockTypeBuilder<T> addComponents(Map<Identifier, BlockComponentImpl> components);

    private Map<Identifier, BlockComponentImpl> listComponentToMap(List<BlockComponentImpl> components) {
        var map = new HashMap<Identifier, BlockComponentImpl>();
        components.forEach(component -> {
            var id = ComponentProvider.findComponentIdentifier(component.getClass());
            if (map.containsKey(id))
                throw new IllegalArgumentException("Duplicate component: " + id);
            map.put(id, component);
        });
        return map;
    }

    BlockTypeBuilder<T> addComponent(BlockComponentImpl component);

    BlockTypeBuilder<T> addBasicComponents();

    BlockTypeBuilder<T> addCustomBlockComponent(CustomBlockComponentImpl customBlockComponent);

    BlockTypeBuilder<T> setBlockBaseComponentSupplier(Function<BlockType<T>, BlockComponentImpl> blockBaseComponentSupplier);

    BlockType<T> build();

    interface BlockTypeBuilderFactory {
        <T extends BlockBehavior> BlockTypeBuilder<T> create(Class<T> clazz);
    }
}
