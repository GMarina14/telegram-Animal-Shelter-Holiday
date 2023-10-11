package com.example.telegramanimalshelterholiday.constants.enums;

import com.vdurmont.emoji.EmojiParser;

public enum Icon {
    PLUS(":wave:"),
    MINUS(":smiley:"),
    CHECK(":white_check_mark:"),
    NOT(":x:"),
    DOUBT(":zzz:"),
    CAT(":cat2:"),
    FLAG(":checkered_flag:");
    private String value;
    Icon(String value) {
        this.value = value;
    }
    public String getParse() {
        return EmojiParser.parseToUnicode(value);
    }
//аналог записи
//    public final static String EMOJI_CAT=EmojiParser.parseToUnicode(":cat:");


}
