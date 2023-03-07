package cn.allay.nbt.tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DoubleTag extends NumberTag<Double> {
    public double data;

    public DoubleTag() {
    }

    public DoubleTag(double data) {
        this.data = data;
    }

    @Override
    public Double getData() {
        return data;
    }

    @Override
    public void setData(Double data) {
        this.data = data == null ? 0 : data;
    }

    @Override
    public int getId() {
        return DOUBLE;
    }

    @Override
    public String toString() {
        return "DoubleTag " + " (data: " + data + ")";
    }

    @Override
    public String toSNBT() {
        return data + "d";
    }

    @Override
    public String toSNBT(int space) {
        return data + "d";
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            DoubleTag o = (DoubleTag) obj;
            return data == o.data;
        }
        return false;
    }

    @Override
    public DoubleTag clone() {
        return new DoubleTag(data);
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.data = in.readDouble();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(this.data);
    }
}
