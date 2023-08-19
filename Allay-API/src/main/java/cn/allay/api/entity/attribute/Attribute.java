package cn.allay.api.entity.attribute;

import lombok.Getter;
import lombok.ToString;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.AttributeData;

/**
 * Allay Project 2023/7/9
 *
 * @author JukeboxMC | daoge_cmd
 */
@ToString
public class Attribute implements Cloneable {

    @Getter
    private final String key;
    private final float defaultValue;
    @Getter
    private float minValue;
    @Getter
    private float maxValue;
    @Getter
    private float currentValue;
    private boolean dirty;

    public Attribute(String key, float minValue, float maxValue, float currentValue, float defaultValue) {
        this.key = key;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentValue = currentValue;
        this.defaultValue = currentValue;
        this.dirty = true;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public void setCurrentValue(float currentValue) {
        this.currentValue = currentValue;
        this.dirty = true;
    }

    public boolean isDirty() {
        boolean value = this.dirty;
        this.dirty = false;
        return value;
    }

    public void reset() {
        this.currentValue = this.defaultValue;
        this.dirty = true;
    }

    public AttributeData toNetwork() {
        return new AttributeData(this.key, this.minValue, this.maxValue, this.currentValue, this.defaultValue);
    }

    public NbtMap toNBT() {
        return NbtMap.builder()
                .putString("Name", this.key)
                .putFloat("Min", this.minValue)
                .putFloat("Max", this.maxValue)
                .putFloat("Current", this.currentValue)
                .putFloat("Base", this.defaultValue)
                .build();
    }

    @Override
    protected Attribute clone() throws CloneNotSupportedException {
        return (Attribute) super.clone();
    }
}
