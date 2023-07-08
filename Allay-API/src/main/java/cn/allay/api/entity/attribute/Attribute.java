package cn.allay.api.entity.attribute;

import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.AttributeData;

/**
 * Allay Project 2023/7/9
 *
 * @author JukeboxMC | daoge_cmd
 */
@ToString
public class Attribute implements Cloneable {

    private final String key;
    private final float defaultValue;
    private float minValue;
    private float maxValue;
    private float currentValue;
    private boolean dirty;

    public Attribute(String key, float minValue, float maxValue, float currentValue) {
        this.key = key;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentValue = currentValue;
        this.defaultValue = currentValue;
        this.dirty = true;
    }

    public String getKey() {
        return this.key;
    }

    public float getMinValue() {
        return this.minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return this.maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public float getCurrentValue() {
        return this.currentValue;
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

    @Override
    protected Attribute clone() throws CloneNotSupportedException {
        return (Attribute) super.clone();
    }
}
