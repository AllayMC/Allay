package cn.allay.server.block.hash;

import cn.allay.api.block.palette.VanillaBlockPaletteRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.testutils.AllayTestExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AllayTestExtension.class)
@Slf4j
public class MinecraftNamespaceComparator {
    private static final long FNV1_64_INIT = 0xcbf29ce484222325L;
    private static final long FNV1_PRIME_64 = 1099511628211L;

    @Test
    void test() {
        var example = VanillaBlockPaletteRegistry.getRegistry()
                .getContent()
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(e -> e.getValue().index()))
                .map(e -> e.getKey().getNamespaceId().toString())
                .toList();
        var blocks = Arrays.stream(VanillaBlockId.values()).map(vanillaBlockId -> vanillaBlockId.getNamespaceId().toString()).sorted(MinecraftNamespaceComparator::compareFNV).toList();
        assertEquals(example, blocks);
    }

    public static int compare(String idA, String idB) {
        String childIdA = idA.substring(idA.indexOf(":") + 1);
        String childIdB = idB.substring(idB.indexOf(":") + 1);

        // Compare by child first
        int childIdComparsion = childIdB.compareTo(childIdA);
        if (childIdComparsion != 0) {
            return childIdComparsion;
        }

        // Compare by namespace if childs are equal
        String namespaceA = idA.substring(0, idA.indexOf(":"));
        String namespaceB = idB.substring(0, idB.indexOf(":"));
        return namespaceB.compareTo(namespaceA);
    }

    // https://gist.github.com/SupremeMortal/5e09c8b0eb6b3a30439b317b875bc29c
    // Thank you Supreme
    public static int compareFNV(String idA, String idB) {
        byte[] bytes1 = idA.getBytes(StandardCharsets.UTF_8);
        byte[] bytes2 = idB.getBytes(StandardCharsets.UTF_8);
        long hash1 = fnv164(bytes1);
        long hash2 = fnv164(bytes2);
        return Long.compareUnsigned(hash1, hash2);
    }

    private static long fnv164(byte[] data) {
        long hash = FNV1_64_INIT;
        for (byte datum : data) {
            hash *= FNV1_PRIME_64;
            hash ^= (datum & 0xff);
        }

        return hash;
    }
}
