package com.example.telegramanimalshelterholiday.constants.enums;

public enum BotState {

    CHOICES_SHELTER, //вызывается после "позвать волонтера" для возврата в главное меню
    SHELTER_DOG,
    SHELTER_CAT,
    INFO_SHELTER,  // вызывается для возврата в меню " Консультация нового пользователя".
    KITTY_STATE,
    PUPPY_STATE,
    SIGHT_PROBLEMS_STATE,
    MOBILITY_PROBLEMS_STATE,
    POTENTIAL_ADOPTER,  // вызывается для возврата в меню "Консультация с потенциальным хозяином животного из приюта".

    STAGE_ADOPTER_IN_ADOPTION_INFO_MENU,

    STAGE_WAITING_FOR_PET_PICTURE,
    STAGE_WAITING_FOR_PET_DIET,
    WAITING_FOR_BEHAVIOR_CHANGE,
    STAGE_WAITING_FOR_HEALTH_INFO,
    NOT_COMPLETE_REPORT, //не полный отчет

    STAGE_WAITING_TO_SAVE_REPORT,


    DEFAULT
}
