package cn.allay.nbt.tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FloatTag extends NumberTag<Float> {
    protected float data;

    public FloatTag() {
    }

    public FloatTag(float data) {
        this.data = data;
    }

    @Override
    public Float getData() {
        return data;
    }

    @Override
    public void setData(Float data) {
        this.data = data == null ? 0 : data;
    }

    @Override
    public int getId() {
        return FLOAT;
    }

    @Override
    public String toString() {
        return "FloatTag " + " (data: " + data + ")";
    }

    @Override
    public String toSNBT() {
        return data + "f";
    }

    @Override
    public String toSNBT(int space) {
        return data + "f";
    }

    @Override
    public FloatTag clone() {
        return new FloatTag(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            FloatTag o = (FloatTag) obj;
            return data == o.data;
        }
        return false;
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.data = in.readFloat();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeFloat(this.data);
    }
}
