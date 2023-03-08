package cn.allay.nbt;

import cn.allay.nbt.stream.LittleEndianDataInputStream;
import cn.allay.nbt.stream.LittleEndianDataOutputStream;
import cn.allay.nbt.stream.PGZIPOutputStream;
import cn.allay.nbt.tag.CompoundTag;
import cn.allay.nbt.tag.EndTag;
import cn.allay.nbt.tag.Tag;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * A class containing methods for reading/writing NBT tags.
 */
public final class NBTIO {
    /**
     * Reads the compressed, big endian root CompoundTag from the given file.
     *
     * @param path Path of the file.
     * @return The read compound tag.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static CompoundTag readFile(String path) throws IOException {
        return readFile(new File(path));
    }

    /**
     * Reads the compressed, big endian root CompoundTag from the given file.
     *
     * @param file File to read from.
     * @return The read compound tag.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static CompoundTag readFile(File file) throws IOException {
        return readFile(file, true, false);
    }

    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param path         Path of the file.
     * @param compressed   Whether the NBT file is compressed.
     * @param littleEndian Whether the NBT file is little endian.
     * @return The read compound tag.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static CompoundTag readFile(String path, boolean compressed, boolean littleEndian) throws IOException {
        return readFile(new File(path), compressed, littleEndian);
    }

    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param file         File to read from.
     * @param compressed   Whether the NBT file is compressed.
     * @param littleEndian Whether the NBT file is little endian.
     * @return The read compound tag.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static CompoundTag readFile(File file, boolean compressed, boolean littleEndian) throws IOException {
        InputStream in = new FileInputStream(file);
        try {
            if (compressed) {
                in = new GZIPInputStream(in);
            }

            Tag tag = readTag(in, littleEndian);
            if (!(tag instanceof CompoundTag)) {
                throw new IOException("Root tag is not a CompoundTag!");
            }

            return (CompoundTag) tag;
        } finally {
            in.close();
        }
    }

    /**
     * Writes the given root CompoundTag to the given file, compressed and in big endian.
     *
     * @param tag  Tag to write.
     * @param path Path to write to.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static void writeFile(CompoundTag tag, String path) throws IOException {
        writeFile(tag, new File(path));
    }

    /**
     * Writes the given root CompoundTag to the given file, compressed and in big endian.
     *
     * @param tag  Tag to write.
     * @param file File to write to.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static void writeFile(CompoundTag tag, File file) throws IOException {
        writeFile(tag, file, true, false);
    }

    /**
     * Writes the given root CompoundTag to the given file.
     *
     * @param tag          Tag to write.
     * @param path         Path to write to.
     * @param compressed   Whether the NBT file should be compressed.
     * @param littleEndian Whether to write little endian NBT.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static void writeFile(CompoundTag tag, String path, boolean compressed, boolean littleEndian) throws IOException {
        writeFile(tag, new File(path), compressed, littleEndian);
    }

    /**
     * Writes the given root CompoundTag to the given file.
     *
     * @param tag          Tag to write.
     * @param file         File to write to.
     * @param compressed   Whether the NBT file should be compressed.
     * @param littleEndian Whether to write little endian NBT.
     * @throws java.io.IOException If an I/O error occurs.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void writeFile(CompoundTag tag, File file, boolean compressed, boolean littleEndian) throws IOException {
        if (!file.exists()) {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            file.createNewFile();
        }
        try (OutputStream out = new FileOutputStream(file)) {
            if (compressed) {
                var pgzipOutputStream = new PGZIPOutputStream(out);
                writeTag(pgzipOutputStream, tag, littleEndian);
                pgzipOutputStream.finish();
            } else writeTag(out, tag, littleEndian);
        }
    }

    /**
     * Reads a big endian NBT tag.
     *
     * @param in Input stream to read from.
     * @return The read tag, or null if the tag is an end tag.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static Tag readTag(InputStream in) throws IOException {
        return readTag(in, false);
    }

    /**
     * Reads an NBT tag.
     *
     * @param in           Input stream to read from.
     * @param littleEndian Whether to read little endian NBT.
     * @return The read tag, or null if the tag is an end tag.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static Tag readTag(InputStream in, boolean littleEndian) throws IOException {
        return readTag((DataInput) (littleEndian ? new LittleEndianDataInputStream(in) : new DataInputStream(in)));
    }

    /**
     * Reads an NBT tag.
     *
     * @param in Data input to read from.
     * @return The read tag, or null if the tag is an end tag.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static Tag readTag(DataInput in) throws IOException {
        int id = in.readUnsignedByte();
        if (id == Tag.END) {
            return new EndTag();
        }
        Tag tag;
        try {
            tag = Tag.newTag(id);
            tag.read(in);
        } catch (TagCreateException e) {
            throw new IOException("Failed to create tag.", e);
        }
        return tag;
    }

    /**
     * Writes an NBT tag in big endian.
     *
     * @param out Output stream to write to.
     * @param tag Tag to write.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static void writeTag(OutputStream out, Tag tag) throws IOException {
        writeTag(out, tag, false);
    }

    /**
     * Writes an NBT tag.
     *
     * @param out          Output stream to write to.
     * @param tag          Tag to write.
     * @param littleEndian Whether to write little endian NBT.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static void writeTag(OutputStream out, Tag tag, boolean littleEndian) throws IOException {
        writeTag((DataOutput) (littleEndian ? new LittleEndianDataOutputStream(out) : new DataOutputStream(out)), tag);
    }

    /**
     * Writes an NBT tag.
     *
     * @param out Data output to write to.
     * @param tag Tag to write.
     * @throws java.io.IOException If an I/O error occurs.
     */
    public static void writeTag(DataOutput out, Tag tag) throws IOException {
        if (tag != null) {
            out.writeByte(tag.getId());
            tag.write(out);
        } else {
            out.writeByte(Tag.END);
        }
    }


}