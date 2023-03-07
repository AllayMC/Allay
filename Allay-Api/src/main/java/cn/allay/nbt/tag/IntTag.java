package cn.allay.nbt.tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntTag extends NumberTag<Integer> {
    public int data;

    public IntTag() {
    }

    public IntTag(int data) {
        this.data = data;
    }

    @Override
    public Integer getData() {
        return data;
    }

    @Override
    public void setData(Integer data) {
        this.data = data == null ? 0 : data;
    }

    @Override
    public int getId() {
        return INT;
    }

    @Override
    public String toString() {
        return "IntTag " + "(data: " + data + ")";
    }

    @Override
    public String toSNBT() {
        return String.valueOf(data);
    }

    @Override
    public String toSNBT(int space) {
        return String.valueOf(data);
    }

    @Override
    public IntTag clone() {
        return new IntTag(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            IntTag o = (IntTag) obj;
            return data == o.data;
        }
        return false;
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.data = in.readInt();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(this.data);
    }
}
