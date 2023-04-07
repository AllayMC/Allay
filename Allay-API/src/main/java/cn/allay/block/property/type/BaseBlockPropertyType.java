package cn.allay.block.property.type;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public abstract sealed class BaseBlockPropertyType<DATATYPE> implements BlockPropertyType<DATATYPE> permits BooleanPropertyType, EnumPropertyType, IntPropertyType {

    protected final String name;
    protected final List<DATATYPE> validValues;
    protected final DATATYPE defaultValue;

    protected BaseBlockPropertyType(String name, List<DATATYPE> validValues, DATATYPE defaultValue) {
        this.name = name;
        this.validValues = validValues;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DATATYPE getDefaultValue() {
        return defaultValue;
    }

    @Override
    public List<DATATYPE> getValidValues() {
        return validValues;
    }
}
