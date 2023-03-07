package cn.allay.nbt.tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteTag extends NumberTag<Integer> {
    public int data;

    public ByteTag() {
    }

    public ByteTag(int data) {
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
        return BYTE;
    }

    @Override
    public String toString() {
        String hex = Integer.toHexString(this.data);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return "ByteTag " + " (data: 0x" + hex + ")";
    }

    @Override
    public String toSNBT() {
        return data + "b";
    }

    @Override
    public String toSNBT(int space) {
        return data + "b";
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            ByteTag byteTag = (ByteTag) obj;
            return data == byteTag.data;
        }
        return false;
    }

    @Override
    public ByteTag clone() {
        return new ByteTag(data);
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.data = in.readByte();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeByte(this.data);
    }
}
