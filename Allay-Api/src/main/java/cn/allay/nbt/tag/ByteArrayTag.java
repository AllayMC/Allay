package cn.allay.nbt.tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayTag extends Tag {
    protected byte[] data;

    public ByteArrayTag() {
    }

    public ByteArrayTag(byte[] data) {
        this.data = data;
    }

    @Override
    public int getId() {
        return BYTE_ARRAY;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public ByteArrayTag clone() {
        return new ByteArrayTag(this.data);
    }

    @Override
    public String toString() {
        return "ByteArrayTag" + "(data:" + Arrays.toString(this.data) + "[" + data.length + " bytes])";
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.data = new byte[in.readInt()];
        in.readFully(this.data);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(this.data.length);
        out.write(this.data);
    }

    @Override
    public String toSNBT() {
        StringBuilder builder = new StringBuilder("[B;");
        for (int i = 0; i < this.data.length - 1; i++) {
            builder.append(data[i]).append('b').append(',');
        }
        builder.append(data[data.length - 1]).append("b]");
        return builder.toString();
    }

    @Override
    public String toSNBT(int space) {
        StringBuilder builder = new StringBuilder("[B; ");
        for (int i = 0; i < this.data.length - 1; i++) {
            builder.append(data[i]).append("b, ");
        }
        builder.append(data[data.length - 1]).append("b]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            ByteArrayTag byteArrayTag = (ByteArrayTag) obj;
            return ((data == null && byteArrayTag.data == null) || (data != null && Arrays.equals(data, byteArrayTag.data)));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    /**
     * Gets the length of this tag's array.
     *
     * @return This tag's array length.
     */
    public int length() {
        return this.data.length;
    }
}
