package org.allaymc.exampleplugin;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.form.Forms;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class ExampleCommand extends SimpleCommand {
    public ExampleCommand() {
        super("example-cmd", "Example command");
        aliases.add("ec");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("test_modal_form")
                .exec((context, player) -> {
                    Forms.modal()
                            .title("title of this modal form")
                            .content("this is a test modal form c:")
                            .trueButton("a true button")
                            .falseButton("a false button")
                            .onTrue(() -> player.sendText("you clicked the true button"))
                            .onFalse(() -> player.sendText("you clicked the false button"))
                            .onClose(() -> player.sendText("you closed the form"))
                            .sendTo(player);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("test_simple_form")
                .exec((context, player) -> {
                    Forms.simple()
                            .title("title of this simple form")
                            .content("this is a test simple form c:")
                            .button("first_button").onClick(button -> player.sendText("you clicked the first button"))
                            .button("second_button").onClick(button -> player.sendText("you clicked the second button"))
                            .button("third_button").onClick(button -> player.sendText("you clicked the third button"))
                            .sendTo(player);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("test_custom_form")
                .exec((context, player) -> {
                    Forms.custom()
                            .title("title of this custom form")
                            .dropdown("a dropdown", "a", "b", "c")
                            .input("an input", "a placeholder", "a default text")
                            .label("a label")
                            .slider("a slider", 0f, 100f)
                            .stepSlider("a step slider", "a", "b", "c")
                            .toggle("a toggle", false)
                            .onResponse(responses -> {
                                player.sendText("dropdown: " + responses.get(0));
                                player.sendText("input: " + responses.get(1));
                                player.sendText("label: " + responses.get(2));
                                player.sendText("slider: " + responses.get(3));
                                player.sendText("step slider: " + responses.get(4));
                                player.sendText("toggle: " + responses.get(5));
                            })
                            .sendTo(player);
                    return context.success();
                }, SenderType.PLAYER);
    }
}
