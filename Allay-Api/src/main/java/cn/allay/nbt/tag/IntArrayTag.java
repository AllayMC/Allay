package cn.allay.nbt.tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class IntArrayTag extends Tag {
    protected int[] data;

    public IntArrayTag() {
    }

    public IntArrayTag(int[] data) {
        this.data = data;
    }

    public int[] getData() {
        return data;
    }

    /**
     * Gets the length of this tag's array.
     *
     * @return This tag's array length.
     */
    public int length() {
        return this.data.length;
    }

    @Override
    public int getId() {
        return INT_ARRAY;
    }

    @Override
    public String toString() {
        return "IntArrayTag " + " [" + data.length + " bytes]";
    }

    @Override
    public String toSNBT() {
        return Arrays.toString(data).replace("[", "[I;");
    }

    @Override
    public String toSNBT(int space) {
        return Arrays.toString(data).replace("[", "[I;");
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            IntArrayTag intArrayTag = (IntArrayTag) obj;
            return ((data == null && intArrayTag.data == null) || (data != null && Arrays.equals(data, intArrayTag.data)));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public IntArrayTag clone() {
        int[] cp = new int[data.length];
        System.arraycopy(data, 0, cp, 0, data.length);
        return new IntArrayTag(cp);
    }

    @Override
    public void read(DataInput in) throws IOException {
        this.data = new int[in.readInt()];
        for (int index = 0; index < this.data.length; index++) {
            this.data[index] = in.readInt();
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(this.data.length);
        for (int index = 0; index < this.data.length; index++) {
            out.writeInt(this.data[index]);
        }
    }
}
