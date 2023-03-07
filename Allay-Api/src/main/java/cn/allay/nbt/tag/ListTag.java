package cn.allay.nbt.tag;

import cn.allay.nbt.TagCreateException;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ListTag<T extends Tag> extends Tag implements Iterable<T> {
    protected List<T> list = new ArrayList<>();
    protected Integer type;

    public ListTag() {
    }

    public ListTag(List<T> list) {
        this.list = list;
    }

    /**
     * Adds a tag to this list tag.
     * If the list does not yet have a type, it will be set to the type of the tag being added.
     *
     * @param tag Tag to add. Should not be null.
     * @return the list tag
     */
    public ListTag<T> add(T tag) {
        if (type == null) type = tag.getId();
        list.add(tag);
        return this;
    }

    /**
     * Adds a tag to this list tag.
     * If the list does not yet have a type, it will be set to the type of the tag being added.
     *
     * @param index the index
     * @param tag   Tag to add. Should not be null.
     * @return the list tag
     */
    public ListTag<T> add(int index, T tag) {
        if (type == null) type = tag.getId();
        if (index >= list.size()) {
            list.add(index, tag);
        } else {
            list.set(index, tag);
        }
        return this;
    }

    /**
     * Gets the tag at the given index of this list tag.
     *
     * @param index Index of the tag.
     * @return The tag at the given index.
     */
    public T get(int index) {
        return list.get(index);
    }

    /**
     * Removes a tag from this list tag.
     *
     * @param tag Tag to remove.
     */
    public void remove(T tag) {
        list.remove(tag);
    }

    /**
     * Removes a tag from this list tag.
     *
     * @param index the index
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * set all tags from the List.
     *
     * @param tags the tags
     */
    public void setAll(List<T> tags) {
        this.list = new ArrayList<>(tags);
    }


    /**
     * Removes all tags from the Collection.
     *
     * @param tags the tags
     */
    public void removeAll(Collection<T> tags) {
        list.removeAll(tags);
    }

    /**
     * Gets the element type of the ListTag.
     *
     * @return The ListTag's element type, or null if the list does not yet have a defined type.
     */
    public int getType() {
        return type;
    }

    /**
     * Gets the number of tags in this list tag.
     *
     * @return The size of this list tag.
     */
    public int size() {
        return list.size();
    }

    @Override
    public int getId() {
        return LIST;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",\n\t");
        list.forEach(tag -> joiner.add(tag.toString().replace("\n", "\n\t")));
        return "ListTag '" + "' (" + list.size() + " entries of type " + Tag.getTagName(type) + ") {\n\t" + joiner + "\n}";
    }

    @Override
    @SuppressWarnings("unchecked")
    public void read(DataInput in) throws IOException {
        this.type = null;
        this.list.clear();

        int id = in.readUnsignedByte();
        if (id != 0) {
            this.type = id;
        }

        int count = in.readInt();
        for (int index = 0; index < count; index++) {
            Tag tag;
            try {
                tag = Tag.newTag(id);
            } catch (TagCreateException e) {
                throw new IOException("Failed to create tag.", e);
            }

            tag.read(in);
            this.list.add((T) tag);
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        if (this.type == null) {
            out.writeByte(0);
        } else {
            int id = this.type;
            out.writeByte(id);
        }

        out.writeInt(this.list.size());
        for (Tag tag : this.list) {
            tag.write(out);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }

    @Override
    public String toSNBT() {
        return "[" + list.stream()
                .map(Tag::toSNBT)
                .collect(Collectors.joining(",")) + "]";
    }

    @Override
    public String toSNBT(int space) {
        StringBuilder addSpace = new StringBuilder();
        addSpace.append(" ".repeat(Math.max(0, space)));
        if (list.isEmpty()) {
            return "[]";
        } else if (list.get(0) instanceof StringTag || list.get(0) instanceof CompoundTag || list.get(0) instanceof ListTag<?>) {
            StringJoiner joiner1 = new StringJoiner(",\n" + addSpace);
            list.forEach(tag -> joiner1.add(tag.toSNBT(space).replace("\n", "\n" + addSpace)));
            return "[\n" + addSpace + joiner1 + "\n]";
        } else {
            StringJoiner joiner2 = new StringJoiner(", ");
            list.forEach(tag -> joiner2.add(tag.toSNBT(space)));
            return "[" + joiner2 + "]";
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<T> getData() {
        List<T> value = new ArrayList<>(this.list.size());

        for (T t : this.list) {
            value.add((T) t.getData());
        }

        return value;
    }

    @Override
    public ListTag<T> clone() {
        ListTag<T> res = new ListTag<>();
        res.type = type;
        for (T t : list) {
            @SuppressWarnings("unchecked")
            T copy = (T) t.clone();
            res.list.add(copy);
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            ListTag<?> o = (ListTag<?>) obj;
            if (Objects.equals(type, o.type)) {
                return list.equals(o.list);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, list);
    }
}
