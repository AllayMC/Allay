package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "half", blockRegex = "minecraft:pitcher_crop")
public class PitcherCropHalfMapper extends StateMapper<Boolean> {

    @Override
    public Pair<String, Boolean> translateState(String fullIdentifier, String value) {
        return Pair.of("upper_block_bit", "upper".equals(value));
    }
}
