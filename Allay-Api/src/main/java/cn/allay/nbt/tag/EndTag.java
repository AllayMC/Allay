package cn.allay.nbt.tag;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * The plugin should not use this EndTag, this is a reserved NBT Tag to mark the end of Compound.
 * SNBT does not implement serialization and deserialization of this Tag.
 * Any Tag after this one will not be read, because this EndTag represents the end of the Compound.
 */
public class EndTag extends Tag {
    public EndTag() {
    }

    @Override
    public int getId() {
        return Tag.END;
    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public String toString() {
        return "EndTag";
    }

    @Override
    public void read(DataInput in) throws IOException {
    }

    @Override
    public void write(DataOutput out) throws IOException {
    }

    @Override
    public String toSNBT() {
        return "";
    }

    @Override
    public String toSNBT(int space) {
        return "";
    }

    @Override
    public EndTag clone() {
        return new EndTag();
    }
}
