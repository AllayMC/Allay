package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "facing", blockRegex = ".*wall_torch.?$")
public class TorchFacingMapper extends StateMapper<String> {

    @Override
    public Pair<String, String> translateState(String fullIdentifier, String value) {
        String direction = switch (value) {
            case "north" -> "south";
            case "west" -> "east";
            case "south" -> "north";
            case "east" -> "west";
            default -> "";
        };
        return Pair.of("torch_facing_direction", direction);
    }
}
