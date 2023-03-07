package cn.allay.nbt.tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ShortTag extends NumberTag<Short> {
    public int data;

    public ShortTag() {
    }

    public ShortTag(int data) {
        this.data = data;
    }

    @Override
    public Short getData() {
        return (short) data;
    }

    @Override
    public void setData(Short data) {
        this.data = data == null ? 0 : data;
    }

    @Override
    public int getId() {
        return SHORT;
    }

    @Override
    public String toString() {
        return "ShortTag " + "(data: " + data + ")";
    }

    @Override
    public String toSNBT() {
        return data + "s";
    }

    @Override
    public String toSNBT(int space) {
        return data + "s";
    }

    @Override
    public ShortTag clone() {
        return new ShortTag(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            ShortTag o = (ShortTag) obj;
            return data == o.data;
        }
        return false;
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.data = in.readShort();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeShort(this.data);
    }
}
