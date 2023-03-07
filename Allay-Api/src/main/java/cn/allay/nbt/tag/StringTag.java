package cn.allay.nbt.tag;

import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class StringTag extends Tag {
    protected String data;

    public StringTag() {
    }

    public StringTag(@NotNull String data) {
        this.data = data;
    }

    @Override
    public int getId() {
        return STRING;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "StringTag " + " (data: " + data + ")";
    }

    @Override
    public String toSNBT() {
        return "\"" + data + "\"";
    }

    @Override
    public String toSNBT(int space) {
        return "\"" + data + "\"";
    }

    @Override
    public StringTag clone() {
        return new StringTag(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            StringTag o = (StringTag) obj;
            return ((data == null && o.data == null) || (data != null && data.equals(o.data)));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data);
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.data = in.readUTF();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.data);
    }
}
