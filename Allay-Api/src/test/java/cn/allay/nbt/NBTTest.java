package cn.allay.nbt;

import cn.allay.nbt.tag.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NBTTest {
    private final static File file1 = new File("./build/nbt1.nbt");
    static CompoundTag tag;

    @BeforeAll
    static void init() {
        NBTTest.tag = new CompoundTag().putByte("test1", 1)
                .putFloat("test2", 2.9f)
                .putInt("test3", 3)
                .putString("test4", "string")
                .putByteArray("test5", new byte[]{1, 2, 3, 4, 5})
                .putIntArray("test6", new int[]{11, 22, 33, 44, 55})
                .putLong("test7", 6666666666666L)
                .putList("test8", new ListTag<StringTag>()
                        .add(new StringTag("hello"))
                        .add(new StringTag("world"))
                )
                .putCompound("test9", new CompoundTag()
                        .put("key1", new IntTag(123))
                        .put("key2", new ShortTag(32767))
                );
    }

    @Test
    void writeNBT() throws IOException {
        NBTIO.writeFile(tag, file1);
    }

    @Test
    void readNBT() throws IOException {
        var tag2 = NBTIO.readFile(file1);
        assertEquals(tag, tag2);
        System.out.println(tag2.toSNBT(4));
    }

    @Test
    void stringify() throws IOException {
        var snbt1 = tag.toSNBT();
        assertEquals(tag, SNBTParser.parse(snbt1));
        var snbt2 = tag.toSNBT(4);
        assertEquals(tag, SNBTParser.parse(snbt2));
    }

    @Test
    void loadExternNBT() throws IOException {
        NBTIO.readTag(NBTTest.class.getModule().getResourceAsStream("test.mcstructure"), true);
    }
}
