package cn.allay.api.block.property.type;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/19 <br>
 * Allay Project <br>
 */
public sealed interface BlockPropertyType<DATATYPE> permits BaseBlockPropertyType {
    @Nullable
    static Type getPropertyType(Class<?> clazz) {
        if (clazz == BooleanPropertyType.class) return Type.BOOLEAN;
        else if (clazz == IntPropertyType.class) return Type.INT;
        else if (clazz == EnumPropertyType.class) return Type.ENUM;
        else return null;
    }

    String getName();

    DATATYPE getDefaultValue();

    List<DATATYPE> getValidValues();

    Type getType();
    BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>, ?> createValue(DATATYPE value);

    BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>, ?> tryCreateValue(Object value);

    default BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>, ?> createDefaultValue() {
        return createValue(getDefaultValue());
    }

    @Getter
    enum Type {
        BOOLEAN,
        INT,
        ENUM
    }

    @Getter
    @ToString
    abstract sealed class BlockPropertyValue<DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>, SERIALIZED_DATATYPE> permits BooleanPropertyType.BooleanPropertyValue, EnumPropertyType.EnumPropertyValue, IntPropertyType.IntPropertyValue {

        protected final PROPERTY propertyType;
        protected final DATATYPE value;

        BlockPropertyValue(PROPERTY propertyType, DATATYPE value) {
            this.propertyType = propertyType;
            this.value = value;
        }

        public abstract SERIALIZED_DATATYPE getSerializedValue();
    }
}
