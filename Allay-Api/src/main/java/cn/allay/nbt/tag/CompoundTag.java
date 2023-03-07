package cn.allay.nbt.tag;

import cn.allay.nbt.TagCreateException;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class CompoundTag extends Tag implements Iterable<Entry<String, Tag>> {
    protected final Map<String, Tag> tags = new LinkedHashMap<>();

    public CompoundTag() {
    }

    public CompoundTag(Map<String, Tag> tags) {
        this.tags.putAll(tags);
    }

    @Override
    public int getId() {
        return COMPOUND;
    }

    @Override
    public Map<String, Object> getData() {
        Map<String, Object> value = new LinkedHashMap<>(this.tags.size());
        for (Entry<String, Tag> entry : this.tags.entrySet()) {
            value.put(entry.getKey(), entry.getValue().getData());
        }
        return value;
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    @NotNull
    @Override
    public Iterator<Entry<String, Tag>> iterator() {
        return this.entrySet().iterator();
    }

    /**
     * Gets a set of entries in this compound tag.
     *
     * @return The compound tag's entrySet set.
     */
    public Set<Entry<String, Tag>> entrySet() {
        return this.tags.entrySet();
    }

    /**
     * Gets a set of keys in this compound tag.
     *
     * @return The compound tag's key set.
     */
    public Set<String> keySet() {
        return this.tags.keySet();
    }

    /**
     * Gets a collection of tags in this compound tag.
     *
     * @return This compound tag's tags.
     */
    public Collection<Tag> values() {
        return this.tags.values();
    }

    /**
     * Gets the number of tags in this compound tag.
     *
     * @return This compound tag's size.
     */
    public int size() {
        return this.tags.size();
    }

    /**
     * Clears all tags from this compound tag.
     */
    public void clear() {
        this.tags.clear();
    }


    /**
     * Get the tag of the specified name
     *
     * @param name the name
     * @return the tag
     */
    public Tag get(String name) {
        return tags.get(name);
    }

    /**
     * Gets the boolean value of the specified name.
     * <p>
     * Does not exist returns false
     *
     * @param name the name
     * @return the boolean
     */
    public boolean getBoolean(String name) {
        return getByte(name) != 0;
    }

    /**
     * Gets the byte value of the specified name.
     * <p>
     * Does not exist return 0
     *
     * @param name the name
     * @return the byte
     */
    public int getByte(String name) {
        if (!tags.containsKey(name)) return (byte) 0;
        return ((NumberTag<?>) tags.get(name)).getData().intValue();
    }

    /**
     * Gets the short value of the specified name.
     * <p>
     * Does not exist return 0
     *
     * @param name the name
     * @return the short
     */
    public int getShort(String name) {
        if (!tags.containsKey(name)) return 0;
        return ((NumberTag<?>) tags.get(name)).getData().intValue();
    }

    /**
     * Gets the int value of the specified name.
     * <p>
     * Does not exist return 0
     *
     * @param name the name
     * @return the int
     */
    public int getInt(String name) {
        if (!tags.containsKey(name)) return 0;
        return ((NumberTag<?>) tags.get(name)).getData().intValue();
    }

    /**
     * Gets the long value of the specified name.
     * <p>
     * Does not exist return 0
     *
     * @param name the name
     * @return the long
     */
    public long getLong(String name) {
        if (!tags.containsKey(name)) return 0;
        return ((NumberTag<?>) tags.get(name)).getData().longValue();
    }

    /**
     * Gets the float value of the specified name.
     * <p>
     * Does not exist return 0
     *
     * @param name the name
     * @return the float
     */
    public float getFloat(String name) {
        if (!tags.containsKey(name)) return (float) 0;
        return ((NumberTag<?>) tags.get(name)).getData().floatValue();
    }

    /**
     * Gets the double value of the specified name.
     * <p>
     * Does not exist return 0
     *
     * @param name the name
     * @return the double
     */
    public double getDouble(String name) {
        if (!tags.containsKey(name)) return 0;
        return ((NumberTag<?>) tags.get(name)).getData().doubleValue();
    }

    /**
     * Gets the String value of the specified name.
     * <p>
     * Does not exist return {@code ""}
     *
     * @param name the name
     * @return the string
     */
    public String getString(String name) {
        if (!tags.containsKey(name)) return "";
        Tag tag = tags.get(name);
        if (tag instanceof NumberTag<?>) {
            return String.valueOf(((NumberTag<?>) tag).getData());
        }
        return ((StringTag) tag).data;
    }

    /**
     * Gets the byte[] value of the specified name.
     * <p>
     * Does not exist return empty byte[]
     *
     * @param name the name
     * @return the byte [ ]
     */
    public byte[] getByteArray(String name) {
        if (!tags.containsKey(name)) return new byte[]{};
        return ((ByteArrayTag) tags.get(name)).data;
    }

    /**
     * Gets the int[] value of the specified name.
     * <p>
     * Does not exist return empty int[]
     *
     * @param name the name
     * @return the int [ ]
     */
    public int[] getIntArray(String name) {
        if (!tags.containsKey(name)) return new int[]{};
        return ((IntArrayTag) tags.get(name)).data;
    }

    /**
     * Gets the CompoundTag of the specified name.
     * <p>
     * Does not exist return empty CompoundTag
     *
     * @param name the name
     * @return the compound
     */
    public CompoundTag getCompound(String name) {
        if (!tags.containsKey(name)) return new CompoundTag();
        return (CompoundTag) tags.get(name);
    }

    /**
     * Gets the ListTag of the specified name.
     * <p>
     * Does not exist return empty ListTag
     *
     * @param name the name
     * @return the list
     */
    public ListTag<? extends Tag> getList(String name) {
        if (!tags.containsKey(name)) return new ListTag<>();
        return (ListTag<? extends Tag>) tags.get(name);
    }

    /**
     * Gets the ListTag and convert Class Type of the specified name.
     * <p>
     * Does not exist return empty ListTag
     *
     * @param <T>  the type parameter
     * @param name the name
     * @param type the type
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public <T extends Tag> ListTag<T> getList(String name, Class<T> type) {
        if (!tags.containsKey(name)) return new ListTag<>();
        return (ListTag<T>) tags.get(name);
    }

    /**
     * Write a Tag with the specified name
     *
     * @param name the name
     * @param tag  the tag
     * @return the compound tag
     */
    public CompoundTag put(String name, Tag tag) {
        tags.put(name, tag);
        return this;
    }

    /**
     * Write a ByteTag with the specified name
     *
     * @param string the string
     * @param val    the val
     * @return the compound tag
     */
    public CompoundTag putBoolean(String string, boolean val) {
        putByte(string, val ? 1 : 0);
        return this;
    }

    /**
     * Write a ByteTag with the specified name
     *
     * @param name  the name
     * @param value the value
     * @return the compound tag
     */
    public CompoundTag putByte(String name, int value) {
        tags.put(name, new ByteTag(value));
        return this;
    }

    /**
     * Write a ShortTag with the specified name
     *
     * @param name  the name
     * @param value the value
     * @return the compound tag
     */
    public CompoundTag putShort(String name, int value) {
        tags.put(name, new ShortTag(value));
        return this;
    }

    /**
     * Write a IntTag with the specified name
     *
     * @param name  the name
     * @param value the value
     * @return the compound tag
     */
    public CompoundTag putInt(String name, int value) {
        tags.put(name, new IntTag(value));
        return this;
    }

    /**
     * Write a LongTag with the specified name
     *
     * @param name  the name
     * @param value the value
     * @return the compound tag
     */
    public CompoundTag putLong(String name, long value) {
        tags.put(name, new LongTag(value));
        return this;
    }

    /**
     * Write a FloatTag with the specified name
     *
     * @param name  the name
     * @param value the value
     * @return the compound tag
     */
    public CompoundTag putFloat(String name, float value) {
        tags.put(name, new FloatTag(value));
        return this;
    }

    /**
     * Write a DoubleTag with the specified name
     *
     * @param name  the name
     * @param value the value
     * @return the compound tag
     */
    public CompoundTag putDouble(String name, double value) {
        tags.put(name, new DoubleTag(value));
        return this;
    }

    /**
     * Write a StringTag with the specified name
     *
     * @param name  the name
     * @param value the value
     * @return the compound tag
     */
    public CompoundTag putString(@NotNull String name, @NotNull String value) {
        tags.put(name, new StringTag(value));
        return this;
    }

    /**
     * Write a ByteArrayTag with the specified name
     *
     * @param name  the name
     * @param value the value
     * @return the compound tag
     */
    public CompoundTag putByteArray(String name, byte[] value) {
        tags.put(name, new ByteArrayTag(value));
        return this;
    }

    /**
     * Write a IntArrayTag with the specified name
     *
     * @param name  the name
     * @param value the value
     * @return the compound tag
     */
    public CompoundTag putIntArray(String name, int[] value) {
        tags.put(name, new IntArrayTag(value));
        return this;
    }

    /**
     * Write a ListTag with the specified name
     *
     * @param name    the name
     * @param listTag the list tag
     * @return the compound tag
     */
    public CompoundTag putList(String name, ListTag<? extends Tag> listTag) {
        tags.put(name, listTag);
        return this;
    }

    /**
     * Write a CompoundTag with the specified name
     *
     * @param name        the name
     * @param compoundTag the compound tag
     * @return the compound tag
     */
    public CompoundTag putCompound(String name, CompoundTag compoundTag) {
        tags.put(name, compoundTag);
        return this;
    }

    /**
     * Whether the specified name exists
     *
     * @param name the name
     * @return the boolean
     */
    public boolean contains(String name) {
        return tags.containsKey(name);
    }

    /**
     * Whether the specified name exists and is CompoundTag
     *
     * @param name the name
     * @return the boolean
     */
    public boolean containsCompound(String name) {
        return tags.get(name) instanceof CompoundTag;
    }

    /**
     * Whether the specified name exists and is StringTag
     *
     * @param name the name
     * @return the boolean
     */
    public boolean containsString(String name) {
        return tags.get(name) instanceof StringTag;
    }

    /**
     * Whether the specified name exists and is IntArrayTag
     *
     * @param name the name
     * @return the boolean
     */
    public boolean containsIntArray(String name) {
        return tags.get(name) instanceof IntArrayTag;
    }

    /**
     * Whether the specified name exists and is ByteArrayTag
     *
     * @param name the name
     * @return the boolean
     */
    public boolean containsByteArray(String name) {
        return tags.get(name) instanceof ByteArrayTag;
    }

    /**
     * Whether the specified name exists and is NumberTag
     *
     * @param name the name
     * @return the boolean
     */
    public boolean containsNumber(String name) {
        return tags.get(name) instanceof NumberTag;
    }

    /**
     * Whether the specified name exists and is ListTag
     *
     * @param name the name
     * @return the boolean
     */
    public boolean containsList(String name) {
        return tags.get(name) instanceof ListTag;
    }

    /**
     * Whether the specified name exists and is the specified type of ListTag
     *
     * @param name the name
     * @param type the type
     * @return the boolean
     */
    public boolean containsList(String name, byte type) {
        Tag tag = tags.get(name);
        if (!(tag instanceof ListTag<?> list)) {
            return false;
        }
        int listType = list.getType();
        return listType == 0 || listType == type;
    }

    /**
     * Whether the specified name exists and is ByteTag
     *
     * @param name the name
     * @return the boolean
     */
    public boolean containsByte(String name) {
        return tags.get(name) instanceof ByteTag;
    }

    /**
     * Whether the specified name exists and is ShortTag
     *
     * @param name the name
     * @return the boolean
     */
    public boolean containsShort(String name) {
        return tags.get(name) instanceof ShortTag;
    }

    /**
     * Whether the specified name exists and is IntTag
     *
     * @param name the name
     * @return the boolean
     */
    public boolean containsInt(String name) {
        return tags.get(name) instanceof IntTag;
    }

    /**
     * Whether the specified name exists and is DoubleTag
     *
     * @param name the name
     * @return the boolean
     */
    public boolean containsDouble(String name) {
        return tags.get(name) instanceof DoubleTag;
    }

    /**
     * Whether the specified name exists and is FloatTag
     *
     * @param name the name
     * @return the boolean
     */
    public boolean containsFloat(String name) {
        return tags.get(name) instanceof FloatTag;
    }

    /**
     * Remove the specified name
     *
     * @param name the name
     * @return the compound tag
     */
    public CompoundTag remove(String name) {
        tags.remove(name);
        return this;
    }

    /**
     * Remove the specified name and get.
     *
     * @param <T>  the type parameter
     * @param name the name
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public <T extends Tag> T removeAndGet(String name) {
        return (T) tags.remove(name);
    }


    @Override
    public CompoundTag clone() {
        Map<String, Tag> newMap = new LinkedHashMap<>();
        for (Entry<String, Tag> entry : this.tags.entrySet()) {
            newMap.put(entry.getKey(), entry.getValue().clone());
        }
        return new CompoundTag(newMap);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            CompoundTag o = (CompoundTag) obj;
            return tags.entrySet().equals(o.tags.entrySet());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tags);
    }

    @Override
    public void read(DataInput in) throws IOException {
        Map<String, Tag> tags = new LinkedHashMap<>();
        try {
            Map.Entry<String, Tag> entry;
            while ((entry = readFullyFormedTag(in)) != null) {
                tags.put(entry.getKey(), entry.getValue());
            }
        } catch (EOFException e) {
            throw new IOException("Closing EndTag was not found!");
        }
        this.tags.putAll(tags);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        for (var entry : this.tags.entrySet()) {
            writeFullyFormedTag(out, entry.getKey(), entry.getValue());
        }
        out.writeByte(Tag.END);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",\n\t");
        tags.forEach((key, tag) -> joiner.add('\'' + key + "' : " + tag.toString().replace("\n", "\n\t")));
        return "CompoundTag '" + "' (" + tags.size() + " entries) {\n\t" + joiner + "\n}";
    }


    @Override
    public String toSNBT() {
        StringJoiner joiner = new StringJoiner(",");
        tags.forEach((key, tag) -> joiner.add("\"" + key + "\":" + tag.toSNBT()));
        return "{" + joiner + "}";
    }

    @Override
    public String toSNBT(int space) {
        StringBuilder addSpace = new StringBuilder();
        addSpace.append(" ".repeat(Math.max(0, space)));
        StringJoiner joiner = new StringJoiner(",\n" + addSpace);
        tags.forEach((key, tag) -> joiner.add("\"" + key + "\": " + tag.toSNBT(space).replace("\n", "\n" + addSpace)));
        return "{\n" + addSpace + joiner + "\n}";
    }

    private Map.Entry<String, Tag> readFullyFormedTag(DataInput in) throws IOException {
        var id = in.readUnsignedByte();
        if (id == Tag.END) {
            return null;
        }
        String name = in.readUTF();
        Tag tag;
        try {
            tag = Tag.newTag(id);
            tag.read(in);
        } catch (TagCreateException e) {
            throw new IOException("Failed to create tag.", e);
        }
        return Map.entry(name, tag);
    }

    private void writeFullyFormedTag(DataOutput out, String name, Tag tag) throws IOException {
        if (tag != null) {
            out.writeByte(tag.getId());
            out.writeUTF(name);
            tag.write(out);
        } else {
            out.writeByte(Tag.END);
        }
    }

}
