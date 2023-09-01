package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "age", blockRegex = "minecraft:pitcher_crop")
public class PitcherCropAgeMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        int growth = switch (Integer.parseInt(value)) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 3;
            case 3 -> 5;
            default -> 7; // 4 -> 7
        };
        return Pair.of("growth", growth);
    }
}
