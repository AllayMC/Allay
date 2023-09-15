package cn.allay.api.block.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.BlockComponent;
import cn.allay.api.block.component.base.BlockBaseComponent;
import cn.allay.api.block.component.blockentity.BlockEntityHolderComponentImpl;
import cn.allay.api.block.component.custom.CustomBlockComponentImpl;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.blockentity.type.BlockEntityType;
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

    default BlockTypeBuilder<T> identifier(String identifier) {
        return identifier(new Identifier(identifier));
    }

    BlockTypeBuilder<T> itemIdentifier(Identifier itemIdentifier);

    BlockTypeBuilder<T> vanillaBlock(VanillaBlockId vanillaBlockId);

    default BlockTypeBuilder<T> bindBlockEntity(BlockEntityType<?> blockEntityType) {
        return addComponent(new BlockEntityHolderComponentImpl(blockEntityType));
    }

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
            var id = ComponentProvider.findComponentIdentifier(component.getClass());
            if (map.containsKey(id))
                throw new IllegalArgumentException("Duplicate component: " + id);
            map.put(id, component);
        });
        return map;
    }

    BlockTypeBuilder<T> addComponent(BlockComponent component);

    BlockTypeBuilder<T> addCustomBlockComponent(CustomBlockComponentImpl customBlockComponent);

    BlockTypeBuilder<T> setBlockBaseComponentSupplier(Function<BlockType<T>, BlockBaseComponent> blockBaseComponentSupplier);

    BlockType<T> build();

    interface BlockTypeBuilderFactory {
        <T extends BlockBehavior> BlockTypeBuilder<T> create(Class<T> clazz);
    }
}
