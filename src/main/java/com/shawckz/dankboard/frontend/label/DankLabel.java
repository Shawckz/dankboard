package com.shawckz.dankboard.frontend.label;

import com.shawckz.dankboard.backend.XScoreboard;
import com.shawckz.dankboard.backend.label.XScoreboardLabel;

public class DankLabel extends XScoreboardLabel {

    private String endValue = "";

    public DankLabel(XScoreboard scoreboard, String value, int score) {
        super(scoreboard, value, score);
    }

    public String getEndValue() {
        return endValue;
    }

    public DankLabel setEndValue(String endValue) {
        if (!this.endValue.equals("")) {
            setValue(getValue().getFullValue().replaceAll(this.endValue, endValue));
        }
        this.endValue = endValue;
        return this;
    }

    public DankLabel show() {
        setVisible(true);
        updateLabel();
        return this;
    }

    public DankLabel hide() {
        setVisible(false);
        updateLabel();
        return this;
    }

}
