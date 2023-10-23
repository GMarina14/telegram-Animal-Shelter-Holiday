package com.example.telegramanimalshelterholiday.constants.enums;

import com.vdurmont.emoji.EmojiParser;

public enum Icon {
    PLUS(":wave:"),
    MINUS(":smiley:"),
    CHECK(":white_check_mark:"),
    NOT(":x:"),
    DOUBT(":zzz:"),
    CAT(":cat2:"),
    DOG(":dog2:"),
    FLAG(":checkered_flag:"),
    HOUSE(":house_buildings:"),
    REPORT(":page_facing_up:"),
    VOLUNTEER(":man:"),
    WARNING(":warning:"),
    CAR (":red_car:"),
    PHONE(":telephone:"),
    CONTACT(":card_index:"),
    SAMPLE (":page_with_curl:"),
    SPARKLES(":sparkles:"),
    TRANSPORT(":oncoming_taxi:"),
    REJECTION(":no_entry_sign:"),
    KITTY_CAT(":cat:"),
    PUPPY_DOG(":dog:"),
    PREVIOUS(":arrow_backward:"),
    MAIN(":small_red_triangle_down:"),
    FIRST(":one:"),

    INFO(":information_source:");

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
