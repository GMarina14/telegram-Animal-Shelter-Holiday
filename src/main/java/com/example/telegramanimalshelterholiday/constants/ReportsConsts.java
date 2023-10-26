package com.example.telegramanimalshelterholiday.constants;

import lombok.Data;

@Data
public class ReportsConsts {
    public final static String CAT_DIET_EXAMPLE = "Диета: \n Утро: говядина сырая,отварные овсяные хлопья \n Обед: печень сырая куриная отварные овощи \n Ужин: творог, овощи отварные";
    public final static String DOG_DIET_EXAMPLE = "Диета: \n Утро: говяжье ассорти, кабачок, яблоко, зелень и отруби \n Обед: творог, сметана, курага \n Ужин: индейка (голень), тыква, огурчик, зелень и немного риса";
    public final static String CAT_STATE_OF_HEALTH_EXAMPLE = "Состояние здоровья: \n Кошка активная, аппетит хороший, нет выделений из носа";
    public final static String DOG_STATE_OF_HEALTH_EXAMPLE = "Состояние здоровья: \n Глаза широко открыты, нос влажный, без каких-либо выделений";
    public final static String CAT_BEHAVIOR_EXAMPLE = "Поведение: \n Не проявляет агрессию, общительная";
    public final static String DOG_BEHAVIOR_EXAMPLE = "Поведение: \n Не проявляет агрессию, подвижна,с удовольствием гуляет, бегает, легко участвует в дрессировке, стоит на лапах прямо, чутко реагирует на посторонних людей, птиц, животных, различные звуки ";
    public final static String REPORT_HEADER_EXAMPLE = "Дата отчета: 14.05.2022 \n Номер контракта №ХХУУ \n";


    public final static String INCOMPLETE_REPORT_NOTIFICATION = "Дорогой усыновитель, мы заметили, что ты заполняешь отчет не так подробно, как необходимо. Пожалуйста, подойди ответственнее к этому занятию. В противном случае волонтеры приюта будут обязаны самолично проверять условия содержания животного";
    public final static String REPORT_IS_EMPTY = "Отчет не заполнен. Просим Вас предоставить данные по соответствующим разделам отчета.";
    public final static String REPORTS_ARE_NOT_SENT = "Усыновитель не направляет отчеты более двух дней. Необходимо связаться и узнать о состоянии животного";
    public final static String REPORT_PHOTO_IS_SAVED = "Фотография питомца в рамках отчета сохранена";
    public final static String REPORT_DIET_IS_SAVED = "Диета питомца в рамках отчета сохранена";
    public final static String REPORT_BEHAVIOR_IS_SAVED = "Поведение питомца в рамках отчета сохранено";
    public final static String REPORT_HEALTH_AND_STATE_IS_SAVED = "Состояние здоровья питомца в рамках отчета сохранено";

    // Report sections
    public final static String REPORT_CREATING_RULES = "Пожалуйста, направьте информацию последовательно";
    public final static String PROBATION_IS_OVER = "Дорогой усыновитель, мы поздравляем тебя с окончанием испытательного срока. Мы очень гордимся тобой и просим и впредь оказывать должную заботу питомцу";

    public final static String INFORM_VOLUNTEER_PROBATION_IS_OVER = "Испытательный срок подошел к концу, пришло время удалить контракт с усыновителем из базы";
    public final static String ASK_VOLUNTEER_ABOUT_PROBATION = "Испытательный срок усыновителя сегодня завершается. При принятии решения о продлении испытательного срока, необходимы изменения в контракте - выставите испытательный срок в значение 0, бот направит сообщение усыновителю с дальнейшими действиями. Направлен chatId и contractId";

    public final static String PROBATION_EXTENDED_FOR_14 = "С сожалением информируем Вас о том, что Вам продлен испытательный срок на 14 дополнительных дней. Просим Вас подойти ответвенно к уходу за питомцем";
    public final static String PROBATION_EXTENDED_FOR_30 = "С сожалением информируем Вас о том, что Вам продлен испытательный срок на 30 дополнительных дней. В случае отсутствия серьезных изменений в Вашем отношении к питомцу, приют будет вынужден принять соответствующие меры";
    public final static String PROBATION_FAILED = "Информируем Вас, что Вы не прошли испытательный период, просим Вас привести животное обратно в приют. В ближайшее время с Вами свяжется волонтер";

    public final static String INFORM_VOLUNTEER_PROBATION_FAILED = "Усыновитель не прошел испытательный срок. Свяжитесь, пожалуйста, для предоставления нужной информации по возврату животного в приют";
    public final static String REPORT_SAVED = "Ваш отчет сохранен. Спасибо!";
    public final static String REPORT_CANCEL = "Отправка отчета отменена. Просим Вас своевременно направлять отчеты";
    public final static String INFORM_VOLUNTEER_REPORT_INCOMPLETE = "Направлен не в полной мере заполненный отчет";


}
