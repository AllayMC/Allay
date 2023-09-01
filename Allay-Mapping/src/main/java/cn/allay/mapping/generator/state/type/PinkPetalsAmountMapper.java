package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "flower_amount", blockRegex = "minecraft:pink_petals")
public class PinkPetalsAmountMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        // Java has flower_amount   1, 2, 3, 4
        // Bedrock has growth       0, 1, 2, 3, 4, 5, 6, 7
        // but apparently growth greater than 3 can only be obtained via commands: https://minecraft.fandom.com/wiki/Pink_Petals
        return Pair.of("growth", Integer.parseUnsignedInt(value) - 1);
    }
}
