package cn.allay.block.property.type;

import cn.allay.block.property.BlockPropertyTypeRegistry;
import cn.allay.registry.MappedRegistry;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public sealed interface BlockPropertyType<DATATYPE> permits BaseBlockPropertyType {
    String getName();

    DATATYPE getDefaultValue();

    List<DATATYPE> getValidValues();

    default boolean checkValid(DATATYPE value) {
        //Only IntPropertyType need to check
        return true;
    }

    default <T extends BlockPropertyType<?>> T register() {
        return register(BlockPropertyTypeRegistry.getInstance());
    }

     default <T extends BlockPropertyType<?>> T register(MappedRegistry<String, BlockPropertyType<?>, Map<String, BlockPropertyType<?>>> registry) {
        registry.register(this.getName(), this);
        return (T) this;
    }

    default BlockProperty<DATATYPE, BlockPropertyType<DATATYPE>> createProperty() {
        return createProperty(getDefaultValue());
    }

    default BlockProperty<DATATYPE, BlockPropertyType<DATATYPE>> createProperty(DATATYPE value) {
        return new BlockProperty<>(this, value);
    }

    //TODO: 减少对象创建
    @Getter
    final class BlockProperty<DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> {

        private final PROPERTY propertyType;
        private DATATYPE value;

        private BlockProperty(PROPERTY propertyType, DATATYPE value) {
            this.propertyType = propertyType;
            this.value = value;
        }

        public void setValue(DATATYPE value) {
            if (propertyType.checkValid(value)) this.value = value;
            else throw new IllegalArgumentException("Invalid value");
        }

        @Override
        public int hashCode() {
            return Objects.hash(propertyType, value);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof BlockProperty<?, ?> anotherProperty) {
                return propertyType.equals(anotherProperty.propertyType) && value.equals(anotherProperty.value);
            }
            return false;
        }
    }
}
