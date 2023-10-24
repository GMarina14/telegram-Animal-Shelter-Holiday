package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.service.AdoptionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdoptionDogControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AdoptionService adoptionService;

    @Test
    public void shouldGetInfoAboutAdoption() {
        //given
        String expected = "Усыновление животного это ответственный шаг. Прежде чем решиться на него необходимо " +
                " объективно оценить свои возможности, отдавая себе отчет в том, что это не только большая радость, но и большая ответственность. " +
                "Наши рекомендации помогут вам в этом, а если этого будет недостаточно, то вы всегда можете обратиться к нашим специалистам, " +
                "они с радостью ответят на интересующие вас вопросы.";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/dog/shelter/adoption/info", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetInfoAboutFirstMeet() {
        //given
        String expected = "Если вы хотите с ней познакомиться, присядьте на ее уровень и спокойно попросите ее подойти, " +
                "произнеся кличку. Если собака насторожилась или боится, не настаивайте на контакте, дождитесь, пока она сама захочет к вам подойти. " +
                "Дайте собаке возможность вас обнюхать, не смотрите ей прямо в глаза.";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/dog/shelter/adoption/first-meet", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetInfoAboutDocsForAdoption() {
        //given
        String expected = "Для усыновления Вам понадобятся: \n 1. Справки из психдиспансера и наркодиспансера, так как мы очень беспокоимся о наших подопечных. \n 2. П" +
                "аспорт. \n Так же для усыновления необходимо заключить контракт с нашим приютом";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/dog/shelter/adoption/docs-for-adoption", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetRecommendationForTransporting() {
        //given
        String expected = " В наземном городском транспорте общего пользования, а именно в трамваях, троллейбусах и автобусах, " +
                "крупных собак можно провозить на задней площадке, но они обязательно должны быть в наморднике и на коротком поводке. " +
                "Маленьких животных провозят в специальных контейнерах." +
                "Что касается перевозки питомца в личном автомобиле, согласно Правилам дорожного движения " +
                "транспортировка животных приравнивается к перевозке грузов.  " +
                "В п. 23.3 ПДД говорится, что груз не должен ограничивать обзор водителя или как-то иначе мешать управлению.";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/dog/shelter/adoption/recommendation/transportation", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetRecommendationForPuppyHomeAdgustment() {
        //given
        String expected = "В доме должно быть: 1. Место для сна Выберите место, где будет легко поддерживать чистоту и где щенку будет просторно даже в более зрелом возрасте.\n" +
                "2. Пеленки для щенков \n" +
                "3. Переноска \n" +
                "4. Миски для воды и корма \n" +
                "5. Корм для щенков в период роста \n" +
                "6. Игрушки \n" +
                "7. Ошейник и поводок \n" +
                "8. Моющие средства";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/dog/shelter/adoption/recommendation/home-adgustment-puppy", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetRecommendationForDogHomeAdgustment() {
        //given
        String expected = "В доме должно быть: 1. Место для сна, лежанка.\n" +
                "2. Миски для воды и корма \n" +
                "3. Корм \n" +
                "4. Игрушки \n" +
                "5. Ошейник и поводок \n" +
                "6. Моющие средства";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/dog/shelter/adoption/recommendation/home-adgustment-dog", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetRecommendationHomeAdgustmentForAnimalWithSightProblem() {
        //given
        String expected = "Орагнизуйте максимально просторное пространство, чтобы избежать" +
                "столкновений питомца с различными предметами. Для обозначения места для сна или туалета используйте вещи с запахом самого животного или " +
                "специальные средства.";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/dog/shelter/adoption/recommendation/home-adgustment-unhealthy-sight", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetRecommendationHomeAdgustmentForAnimalWithMobilityProblem() {
        //given
        String expected = "Если питомцу тяжело передвигаться, то организуйте пространство" +
                " так, чтобы ему не приходилось преодолевать большое расстояние. " +
                "В особо тяжелых случаях вы можете использовать специальные инвалидные коляски для животных (нативная реклама)";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/dog/shelter/adoption/recommendation/home-adgustment-unhealthy-mobility", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetInfoAboutReasonForRejection() {
        //given
        String expected = "Мы можем назвать только три причины, по которым приют может не отдать человеку животное: " +
                "человек состоит на учёте в психдиспансере, у человека есть судимость, человек замечен в жестоком обращении с животными";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/dog/shelter/adoption/reasons-for-rejection", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }


    @Test
    public void shouldGetAdviceFromDoghandler() {
        //given
        String expected = "1. Обеспечьте своей собаке достаточную физическую активность. Регулярные прогулки, игры и тренировки помогут поддерживать ее физическую форму и здоровье.\n" +
                "2. Познакомьтесь с основами положительного обучения. Используйте поощрения и похвалу, чтобы стимулировать желаемое поведение у собаки.\n" +
                "3. Уделите время социализации своей собаки. Познакомьте ее с разными людьми, другими собаками и различными ситуациями, чтобы она чувствовала себя комфортно в разных обстановках.\n" +
                "4. Позаботьтесь о правильном питании своей собаки. Выберите качественные сухие или натуральные корма, соответствующие ее возрасту, размеру и потребностям.\n" +
                "5. Уделите внимание уходу за шерстью и кожей собаки. Регулярно расчесывайте ее шерсть, проверяйте наличие паразитов и используйте специальные средства для ухода.\n" +
                "6. Обеспечьте своей собаке безопасное и комфортное место для отдыха и сна. Установите ей свою специальную кошку или место, где она будет чувствовать себя защищенной.\n" +
                "7. Не забывайте о регулярных посещениях ветеринара. Вакцинация, обработка от паразитов и осмотр помогут поддерживать здоровье вашей собаки.\n" +
                "8. Не используйте физическое насилие или жестокие методы в обучении собаки. Используйте только положительные методы обучения и поведения.\n" +
                "9. Внимательно следите за поведением своей собаки и реагируйте на любые изменения. Если у вас возникнут вопросы или проблемы, обратитесь за помощью к профессиональному кинологу.\n" +
                "10. Не забывайте о любви и заботе. Собаки нуждаются в вашей привязанности и внимании, поэтому проводите время с ними, играйте, ласкайте и показывайте им свою любовь.";


        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/dog/shelter/adoption/recommendation/first-meeting-doghandler", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetListGoodDoghandlers() {
        //given
        String expected = "Список проверенных кинологов: \n" +
        "Грызлов Павел Петрович тел. 0000000 \n" +
                "Дружков Константин Иванович тел. 000000 \n" +
                "Щенкова Оксана Анатольевна тел. 00000 \n" +
                "Сычев Роман Андреевич тел. 00000";
        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/dog/shelter/adoption/recommendation/doghandlers", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

}
