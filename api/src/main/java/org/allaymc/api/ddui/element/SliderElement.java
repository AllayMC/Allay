package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
import org.allaymc.api.ddui.internal.LongProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;
import org.allaymc.api.player.Player;

/**
 * DDUI slider element for selecting a numeric value.
 *
 * @author xRookieFight
 */
public final class SliderElement extends CustomFormElement {

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
        setValue(currentValue.getValue());
        currentValue.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                setValue(value);
            }
        });
    }

    public SliderElement setMinValue(long minValue) {
        var property = getProperty("minValue");
        if (property instanceof LongProperty longProperty) {
            longProperty.setValue(minValue);
            return this;
        }

        setProperty(new LongProperty("minValue", minValue, this));
        return this;
    }

    public SliderElement setMaxValue(long maxValue) {
        var property = getProperty("maxValue");
        if (property instanceof LongProperty longProperty) {
            longProperty.setValue(maxValue);
            return this;
        }

        setProperty(new LongProperty("maxValue", maxValue, this));
        return this;
    }

    public SliderElement setStep(long step) {
        var property = getProperty("step");
        if (property instanceof LongProperty longProperty) {
            longProperty.setValue(step);
            return this;
        }

        setProperty(new LongProperty("step", step, this));
        return this;
    }

    public long getValueLong() {
        var property = getProperty("value");
        return property instanceof LongProperty longProperty ? longProperty.getValue() : 0L;
    }

    public SliderElement setValue(long value) {
        var property = getProperty("value");
        if (property instanceof LongProperty longProperty) {
            longProperty.setValue(value);
            return this;
        }

        var longProperty = new LongProperty("value", value, this);
        longProperty.addListener(this::triggerListeners);
        setProperty(longProperty);
        return this;
    }

    public SliderElement setDescription(String description) {
        var property = getProperty("description");
        if (property instanceof StringProperty stringProperty) {
            stringProperty.setValue(description);
            return this;
        }

        setProperty(new StringProperty("description", description, this));
        return this;
    }

    @Override
    public SliderElement setVisibility(boolean visible) {
        super.setVisibility(visible);
        var property = getProperty("slider_visible");
        if (property instanceof BooleanProperty booleanProperty) {
            booleanProperty.setValue(visible);
            return this;
        }

        setProperty(new BooleanProperty("slider_visible", visible, this));
        return this;
    }

    @Override
    public void triggerListeners(Player player, Object data) {
        super.triggerListeners(player, data);
        if (data instanceof Number number) {
            var value = number.longValue();
            setValue(value);
            Observable.withBindingsSuppressed(() -> currentValue.setValue(value));
        }
    }
}
