package cn.allay.block.property.type;

import cn.allay.block.property.BlockPropertyTypeRegistry;
import cn.allay.registry.MappedRegistry;
import lombok.Getter;

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
        return register(BlockPropertyTypeRegistry.getRegistry());
    }

     default <T extends BlockPropertyType<?>> T register(MappedRegistry<String, BlockPropertyType<?>, Map<String, BlockPropertyType<?>>> registry) {
        registry.register(this.getName(), this);
        return (T) this;
    }

    BlockProperty<DATATYPE, ? extends BlockPropertyType<DATATYPE>> createProperty(DATATYPE value);

    BlockProperty<DATATYPE, ? extends BlockPropertyType<DATATYPE>> tryCreateProperty(Object value);

    //TODO: 减少对象创建
    @Getter
    final class BlockProperty<DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> {

        private final PROPERTY propertyType;
        private final DATATYPE value;

        BlockProperty(PROPERTY propertyType, DATATYPE value) {
            this.propertyType = propertyType;
            this.value = value;
        }

        @Override
        public String toString() {
            return propertyType.getName() + "=" + value;
        }
    }
}
