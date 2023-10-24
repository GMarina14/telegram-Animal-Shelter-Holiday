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
public class AdoptionCatControllerTest {

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
        ResponseEntity<String> response = restTemplate.getForEntity("/cat/shelter/adoption/info", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetInfoAboutFirstMeet() {
        //given
        String expected = "Знакомство с кошкой происходит не за несколько минут и даже не за несколько часов. " +
                "К этому вопросу нужно подходить со всей серьезностью и ответственностью. Ведь первые два-три дня — это время, когда кошка учится вам доверять." +
                "Не забывайте, что кошки — не собаки. Они также любят людей, но их границы шире, чем у собак, поэтому важно, чтобы кошка проявила " +
                "к вам интерес сама — это наиболее экологичный способ начать общение." +
                "Все ваши действия должны быть позитивными: никаких громких звуков, резких движений. " +
                "Избегайте всего, что может хоть немного напугать кошку, это поможет закрепить в ее глазах приятные ассоциации с вами и начать вам доверять.";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/cat/shelter/adoption/first-meet", String.class);

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
        ResponseEntity<String> response = restTemplate.getForEntity("/cat/shelter/adoption/docs-for-adoption", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetRecommendationForTransporting() {
        //given
        String expected = "Корзина для кошки должна быть цельная, с водонепроницаемым дном, определенных размеров, " +
                "не превышающих установленные нормы (не более 120 см, если суммировать глубину, ширину и длину). Если переноска превышает разрешенные габариты, " +
                "необходимо произвести доплату за провоз ручной клади. Чтобы ваш питомец привык к переноске, оставьте ее доступной для кошки, " +
                "чтобы она смогла привыкнуть к ней и воспринимать как часть интерьера." +
                "Когда наступит момент воспользоваться переноской, положите в нее подстилку и что-то из одежды с вашим запахом и запахом кошки, " +
                "чтобы ваш питомец чувствовал себя максимально комфортно и безопасно.";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/cat/shelter/adoption/recommendation/transportation", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetRecommendationForKittyHomeAdgustment() {
        //given
        String expected = "В доме должно быть: 1. Качественный корм для котят, подходящий по возрасту\n" +
                "2. Миски для корма и воды\n" +
                "3. Всё для туалета: лоток, наполнитель, решетчатый совочек\n" +
                "4. Лежанка\n" +
                "5. Когтеточка\n" +
                "6. Контейнер-«переноска»\n" +
                "7. Безопасные игрушки\n" +
                "8. Впитывающие пеленки на первое время и для поездок";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/cat/shelter/adoption/recommendation/home-adgustment-kitty", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetRecommendationForCatHomeAdgustment() {
        //given
        String expected = "В доме должно быть:\n 1. Качественный, подходящий по возрасту\n" +
                "2. Миски для корма и воды\n" +
                "3. Всё для туалета: лоток, наполнитель, решетчатый совочек\n" +
                "4. Лежанка\n" +
                "5. Когтеточка\n" +
                "6. Контейнер-«переноска»\n" +
                "7. Безопасные игрушки";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/cat/shelter/adoption/reccomendation/home-adgustment-cat", String.class);

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
        ResponseEntity<String> response = restTemplate.getForEntity("/cat/shelter/adoption/reccomendation/home-adgustment-unhealthy-sight", String.class);

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
        ResponseEntity<String> response = restTemplate.getForEntity("/cat/shelter/adoption/reccomendation/home-adgustment-unhealthy-mobility", String.class);

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
        ResponseEntity<String> response = restTemplate.getForEntity("/cat/shelter/adoption/reasons-for-rejection", String.class);

        //then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();

        String actual = response.getBody();
        Assertions.assertThat(actual).isEqualTo(expected);

    }



}
