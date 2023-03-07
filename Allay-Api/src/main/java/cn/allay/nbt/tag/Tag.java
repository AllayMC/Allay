package cn.allay.nbt.tag;

import cn.allay.nbt.TagCreateException;
import org.jetbrains.annotations.Nullable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Tag implements Cloneable {
    public final static byte END = 0;
    public final static byte BYTE = 1;
    public final static byte SHORT = 2;
    public final static byte INT = 3;
    public final static byte LONG = 4;
    public final static byte FLOAT = 5;
    public final static byte DOUBLE = 6;
    public final static byte BYTE_ARRAY = 7;
    public final static byte STRING = 8;
    public final static byte LIST = 9;
    public final static byte COMPOUND = 10;
    public final static byte INT_ARRAY = 11;

    /**
     * New tag tag.
     *
     * @param type the type
     * @return the tag
     * @throws ClassCastException the class cast exception
     */
    public static Tag newTag(int type) throws TagCreateException {
        return newTag(type, null);
    }

    /**
     * New tag tag.
     *
     * @param type the type
     * @param data the data
     * @return the tag
     * @throws TagCreateException the tag create exception
     */
    @SuppressWarnings("unchecked")
    public static Tag newTag(int type, @Nullable Object data) throws TagCreateException {
        try {
            switch (type) {
                case END -> {
                    return new EndTag();
                }
                case BYTE -> {
                    if (data == null) {
                        return new ByteTag();
                    } else {
                        return new ByteTag((byte) data);
                    }
                }
                case SHORT -> {
                    if (data == null) {
                        return new ShortTag();
                    } else {
                        return new ShortTag((short) data);
                    }
                }
                case INT -> {
                    if (data == null) {
                        return new IntTag();
                    } else {
                        return new IntTag((int) data);
                    }
                }
                case LONG -> {
                    if (data == null) {
                        return new LongTag();
                    } else {
                        return new LongTag((long) data);
                    }
                }
                case FLOAT -> {
                    if (data == null) {
                        return new FloatTag();
                    } else {
                        return new FloatTag((float) data);
                    }
                }
                case DOUBLE -> {
                    if (data == null) {
                        return new DoubleTag();
                    } else {
                        return new DoubleTag((double) data);
                    }
                }
                case BYTE_ARRAY -> {
                    if (data == null) {
                        return new ByteArrayTag();
                    } else {
                        return new ByteArrayTag((byte[]) data);
                    }
                }
                case INT_ARRAY -> {
                    if (data == null) {
                        return new IntArrayTag();
                    } else {
                        return new IntArrayTag((int[]) data);
                    }
                }
                case STRING -> {
                    if (data == null) {
                        return new StringTag();
                    } else {
                        return new StringTag((String) data);
                    }
                }
                case LIST -> {
                    if (data == null) {
                        return new ListTag<>();
                    } else {
                        return new ListTag<>((List<? extends Tag>) data);
                    }
                }
                case COMPOUND -> {
                    if (data == null) {
                        return new CompoundTag();
                    } else {
                        return new CompoundTag((Map<String, Tag>) data);
                    }
                }
                default -> throw new IllegalStateException("Unknow Type ID : " + type);
            }
        } catch (Exception e) {
            throw new TagCreateException("Failed to create instance of tag \"Type: " + getTagName(type) + "\".", e);
        }
    }

    /**
     * Gets the type name.
     *
     * @param type the type
     * @return the tag name
     */
    public static String getTagName(int type) {
        return switch (type) {
            case END -> "TAG_End";
            case BYTE -> "TAG_Byte";
            case SHORT -> "TAG_Short";
            case INT -> "TAG_Int";
            case LONG -> "TAG_Long";
            case FLOAT -> "TAG_Float";
            case DOUBLE -> "TAG_Double";
            case BYTE_ARRAY -> "TAG_Byte_Array";
            case INT_ARRAY -> "TAG_Int_Array";
            case STRING -> "TAG_String";
            case LIST -> "TAG_List";
            case COMPOUND -> "TAG_Compound";
            default -> "UNKNOWN";
        };
    }

    /**
     * Reads this tag from an input stream.
     *
     * @param in Stream to read from.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public abstract void read(DataInput in) throws IOException;

    /**
     * Writes this tag to an output stream.
     *
     * @param out Stream to write to.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public abstract void write(DataOutput out) throws IOException;

    /**
     * Write this tag as SNBT.
     */
    public abstract String toSNBT();

    /**
     * Write this tag as SNBT.
     *
     * @param space the space
     * @return the string
     */
    public abstract String toSNBT(int space);

    /**
     * Gets the type id of Tag.
     *
     * @return the type id
     */
    public abstract int getId();

    /**
     * Gets data of Tag.
     *
     * @return the data
     */
    public abstract Object getData();

    @Override
    public abstract Tag clone();

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag o)) {
            return false;
        }
        return getId() == o.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
