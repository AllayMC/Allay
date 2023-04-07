package cn.allay.block.property.type;

import cn.allay.block.property.BlockPropertyTypeRegistry;
import cn.allay.registry.MappedRegistry;

import java.util.List;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public sealed interface BlockPropertyType<DATATYPE> permits BaseBlockPropertyType {
    String getName();
    DATATYPE getDefaultValue();
    List<DATATYPE> getValidValues();

    default <T extends BlockPropertyType<?>> T register() {
        return register(BlockPropertyTypeRegistry.getInstance());
    }

     default <T extends BlockPropertyType<?>> T register(MappedRegistry<String, BlockPropertyType<?>, Map<String, BlockPropertyType<?>>> registry) {
        registry.register(this.getName(), this);
        return (T) this;
    }
}
