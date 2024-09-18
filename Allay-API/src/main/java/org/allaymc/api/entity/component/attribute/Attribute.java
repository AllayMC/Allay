package org.allaymc.api.entity.component.attribute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.AttributeData;

/**
 *
 * @author JukeboxMC | daoge_cmd
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Attribute implements Cloneable {

    private final String key;
    private final float defaultValue;
    private float minValue;
    private float maxValue;
    private float currentValue;

    public Attribute(String key, float minValue, float maxValue, float currentValue, float defaultValue) {
        this.key = key;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentValue = currentValue;
        this.defaultValue = defaultValue;
    }

    public static Attribute fromNBT(NbtMap nbt) {
        return new Attribute(
                nbt.getString("Name"),
                nbt.getFloat("Min"),
                nbt.getFloat("Max"),
                nbt.getFloat("Current"),
                nbt.getFloat("Base")
        );
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
