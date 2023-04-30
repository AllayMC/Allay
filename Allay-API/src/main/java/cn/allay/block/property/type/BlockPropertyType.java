package cn.allay.block.property.type;

import cn.allay.block.property.BlockPropertyTypeRegistry;
import cn.allay.registry.MappedRegistry;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
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

    default <T extends BlockPropertyType<?>> T register() {
        return registerTo(BlockPropertyTypeRegistry.getRegistry());
    }

    default <T extends BlockPropertyType<?>> T registerTo(MappedRegistry<String, BlockPropertyType<?>, Map<String, BlockPropertyType<?>>> registry) {
        registry.register(this.getName(), this);
        return (T) this;
    }

    BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>> createValue(DATATYPE value);

    BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>> tryCreateValue(Object value);

    default BlockPropertyValue<DATATYPE, ? extends BlockPropertyType<DATATYPE>> createDefaultValue() {
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
    class BlockPropertyValue<DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> {

        protected final PROPERTY propertyType;
        protected final DATATYPE value;

        BlockPropertyValue(PROPERTY propertyType, DATATYPE value) {
            this.propertyType = propertyType;
            this.value = value;
        }

        public String getSerializedValue() {
            return value.toString();
        }
    }
}
