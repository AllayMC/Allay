package cn.allay.block.property;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public abstract sealed class BaseBlockProperty<DATATYPE> implements BlockProperty<DATATYPE> permits BooleanProperty, EnumProperty, IntProperty {

    protected DATATYPE value;
    protected final String name;

    public BaseBlockProperty(String name) {
        this(name, null);
    }

    public BaseBlockProperty(String name, DATATYPE value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void set(DATATYPE value) {
        this.value = value;
    }

    @Override
    public DATATYPE get() {
        return value;
    }
}
