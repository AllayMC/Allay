package cn.allay.block.property.type;

import cn.allay.block.property.BlockPropertyTypeRegistry;
import cn.allay.registry.MappedRegistry;
import lombok.Getter;

import javax.annotation.Nullable;
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

    Type getType();

    default <T extends BlockPropertyType<?>> T register() {
        return registerTo(BlockPropertyTypeRegistry.getRegistry());
    }

     default <T extends BlockPropertyType<?>> T registerTo(MappedRegistry<String, BlockPropertyType<?>, Map<String, BlockPropertyType<?>>> registry) {
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

    @Getter
    enum Type{
        BOOLEAN,
        INT,
        ENUM
    }

    @Nullable
    static Type getPropertyType(Class<?> clazz) {
        if (clazz == BooleanPropertyType.class) return Type.BOOLEAN;
        else if (clazz == IntPropertyType.class) return Type.INT;
        else if (clazz == EnumPropertyType.class) return Type.ENUM;
        else return null;
    }
}
