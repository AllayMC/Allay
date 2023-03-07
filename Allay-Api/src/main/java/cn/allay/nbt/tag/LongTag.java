package cn.allay.nbt.tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongTag extends NumberTag<Long> {
    public long data;

    public LongTag() {
    }

    public LongTag(long data) {
        this.data = data;
    }

    @Override
    public Long getData() {
        return data;
    }

    @Override
    public void setData(Long data) {
        this.data = data == null ? 0 : data;
    }

    @Override
    public int getId() {
        return LONG;
    }

    @Override
    public String toString() {
        return "LongTag " + " (data:" + data + ")";
    }

    @Override
    public String toSNBT() {
        return data + "L";
    }

    @Override
    public String toSNBT(int space) {
        return data + "L";
    }

    @Override
    public LongTag clone() {
        return new LongTag(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            LongTag o = (LongTag) obj;
            return data == o.data;
        }
        return false;
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.data = in.readLong();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(this.data);
    }
}
