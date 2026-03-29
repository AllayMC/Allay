package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.BindingScope;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.LongProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;
import org.allaymc.api.player.Player;

/**
 * DDUI slider element for selecting a numeric value.
 *
 * @author Miroshka
 */
public final class SliderElement extends CustomFormElement {

    private static final String VALUE_BINDING = "binding:value";
    private static final String DESCRIPTION_BINDING = "binding:description";
    private final Observable<Long> currentValue;

    public SliderElement(String label, Observable<Long> currentValue, long minValue, long maxValue, ObjectProperty<?> parent) {
        super("slider", parent);
        this.currentValue = currentValue;
        setLabel(label);
        setVisibility(true);
        setDisabled(false);
        setDescription("");
        setMinValue(minValue);
        setMaxValue(maxValue);
        setStep(1L);
        setValue(currentValue);
    }

    public SliderElement setMinValue(long minValue) {
        writeLongProperty("minValue", minValue);
        return this;
    }

    public SliderElement setMaxValue(long maxValue) {
        writeLongProperty("maxValue", maxValue);
        return this;
    }

    public SliderElement setStep(long step) {
        writeLongProperty("step", step);
        return this;
    }

    public long getValueLong() {
        var property = getProperty("value");
        return property instanceof LongProperty longProperty ? longProperty.getValue() : 0L;
    }

    public SliderElement setValue(long value) {
        unbind(VALUE_BINDING);
        valueProperty().setValue(value);
        return this;
    }

    public SliderElement setValue(Observable<Long> value) {
        var longProperty = valueProperty();
        longProperty.setValue(value.getValue());
        bind(VALUE_BINDING, value, longProperty::setValue);
        return this;
    }

    public SliderElement setDescription(String description) {
        unbind(DESCRIPTION_BINDING);
        writeStringProperty("description", description);
        return this;
    }

    @Override
    protected void applyVisibility(boolean visible) {
        super.applyVisibility(visible);
        writeBooleanProperty("slider_visible", visible);
    }

    @Override
    public void triggerListeners(Player player, Object data) {
        super.triggerListeners(player, data);
        if (data instanceof Number number) {
            var value = number.longValue();
            setValue(value);
            BindingScope.suppressed(() -> currentValue.setValue(value));
        }
    }

    private LongProperty valueProperty() {
        return ensureProperty("value", () -> {
            var property = new LongProperty("value", 0L, this);
            property.addListener(this::triggerListeners);
            return property;
        });
    }
}
