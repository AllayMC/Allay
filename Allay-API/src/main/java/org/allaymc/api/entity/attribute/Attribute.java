package org.allaymc.api.entity.attribute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.AttributeData;

/**
 * Allay Project 2023/7/9
 *
 * @author JukeboxMC | daoge_cmd
 */
@Getter
@ToString
public class Attribute implements Cloneable {

    private final String key;
    private final float defaultValue;
    @Setter
    private float minValue;
    @Setter
    private float maxValue;
    @Setter
    private float currentValue;

    public Attribute(String key, float minValue, float maxValue, float currentValue, float defaultValue) {
        this.key = key;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentValue = currentValue;
        this.defaultValue = currentValue;
    }

    public void reset() {
        this.currentValue = this.defaultValue;
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
