package org.allaymc.server.network.protocol;

import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AllayTestExtension.class)
class CreativeContentBdsParityTest {

    @Test
    void creativeItemsAreSentLikeBds() throws Exception {
        var expected = new ArrayList<String>();
        try (var reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(
                getClass().getResourceAsStream("/bds_1_26_52_creative_content.txt")), StandardCharsets.UTF_8))) {
            reader.lines().filter(line -> !line.startsWith("#")).forEach(expected::add);
        }

        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2193);
        var actual = protocol.getEncoder().encodeCreativeContent().getContents().stream()
                .map(entry -> entry.item())
                .map(item -> item.getDefinition().runtimeId() + " " + item.getDamage() + " "
                             + (item.getBlockDefinition() == null ? 0 : item.getBlockDefinition().runtimeId()) + " "
                             + (item.getTag() == null ? 0 : 1))
                .toList();

        assertEquals(expected.size(), actual.size());
        var mismatches = new ArrayList<String>();
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                mismatches.add("#" + i + " expected [" + expected.get(i) + "] but was [" + actual.get(i) + "]");
            }
        }
        assertEquals(List.of(), mismatches);
    }
}
