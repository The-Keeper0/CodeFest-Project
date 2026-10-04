package com.example.practice_codefest.util;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

public class ImpactStars {

    private static final int MAX_STARS = 5;
    private static final int COLOR_ON = Color.parseColor("#FFC107");
    private static final int COLOR_OFF = Color.parseColor("#BDBDBD");

    private ImpactStars() {
    }

    public static CharSequence build(int score) {
        int filled = Math.max(0, Math.min(score, MAX_STARS));

        SpannableString text = new SpannableString(
                "\u2605\u2605\u2605\u2605\u2605");

        if (filled > 0) {
            text.setSpan(new ForegroundColorSpan(COLOR_ON),
                    0, filled, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (filled < MAX_STARS) {
            text.setSpan(new ForegroundColorSpan(COLOR_OFF),
                    filled, MAX_STARS, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return text;
    }
}
