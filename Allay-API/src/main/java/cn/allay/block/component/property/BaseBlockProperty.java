package cn.allay.block.component.property;

import cn.allay.identifier.Identifier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/19 <br>
 * Allay Project <br>
 */
public abstract sealed class BaseBlockProperty<DATATYPE> implements BlockProperty<DATATYPE> permits BooleanProperty, EnumProperty, IntProperty {

    protected DATATYPE value;
    protected final String name;
    protected final Identifier componentNamespaceId;

    public BaseBlockProperty(String name) {
        this(name, null);
    }

    public BaseBlockProperty(String name, DATATYPE value) {
        this.name = name;
        this.value = value;
        this.componentNamespaceId = new Identifier("minecraft:" + name);
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

    @Override
    public Identifier getNamespaceId() {
        return componentNamespaceId;
    }
}
