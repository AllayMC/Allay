package cn.allay.block.property.type;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public abstract sealed class BaseBlockProperty<DATATYPE> implements BlockProperty<DATATYPE> permits BooleanProperty, EnumProperty, IntProperty {

    protected DATATYPE defaultValue;
    protected final String name;


    public BaseBlockProperty(String name, DATATYPE defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DATATYPE get() {
        return defaultValue;
    }
}
