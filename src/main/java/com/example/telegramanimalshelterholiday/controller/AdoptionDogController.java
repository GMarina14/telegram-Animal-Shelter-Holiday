package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.service.AdoptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dog/shelter/adoption")
public class AdoptionDogController {

    private final AdoptionService adoptionService;

    public AdoptionDogController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    //информация о том как взять животное
    @GetMapping("/info")
    public String getInfoAboutAdoption() {
        return adoptionService.getInfoAboutAdoption();
    }

   //правила знакомства с собакой
    @GetMapping("/first-meet")
    public String getInfoAboutFirstMeet() {
        return adoptionService.getInfoAboutFirstMeetWithDog();
    }

    //список документов, чтобы забрать животное из приюта
    @GetMapping("/docs-for-adoption")
    public String getInfoAboutDocsForAdoption() {
        return adoptionService.getInfoAboutDocsForAdoption();
    }

   /*//получить всех животных (List<Animal> только из собак)
    //!!!!!!!!!!!!!!!!!!!!!!!! это сделать позже, пока с этим падает, видимо потому что возвращает null
    @GetMapping("/docs-for-adoption")
    public List<Animal> getAllDogs() {
        return adoptionService.getAllDogs();
    }*/

    //выдает список рекомендаций по транспортировке собаки
    @GetMapping("/recommendation/transportation")
    public String getInfoAboutTransportation() {
        return adoptionService.getInfoAboutDogTransportation();
    }

    //выдает список рекомендаций по обустройству дома для щенка
    @GetMapping("/recommendation/home-adgustment-puppy")
    public String getInfoAboutAdgustmentPuppy() {
        return adoptionService.getInfoAboutAdgustmentPuppy();
    }

    //выдает список рекомендаций по обустройству дома для взрослой собаки
    @GetMapping("/recommendation/home-adgustment-dog")
    public String getInfoAboutAdgustmentDog() {
        return adoptionService.getInfoAboutAdgustmentDog();
    }

    //выдает список рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение)
    @GetMapping("/recommendation/home-adgustment-unhealthy-sight")
    public String getInfoAboutAdgustmentUnhealthyWithSightProblems() {
        return adoptionService.getInfoAboutAdgustmentUnhealthyWithSightProblems();
    }

    //выдает список рекомендаций по обустройству дома для животного с ограниченными возможностями (мобильность)
    @GetMapping("/recommendation/home-adgustment-unhealthy-mobility")
    public String getInfoAboutAdgustmentUnhealthyMobilityProblems() {
        return adoptionService.getInfoAboutAdgustmentUnhealthyMobilityProblems();
    }

    //выдает список причин, по которым могут отказать в усыновлении животного
    @GetMapping("/reasons-for-rejection")
    public String getInfoAboutReasonsForRejection() {
        return adoptionService.getInfoAboutReasonsForRejection();
    }

    //вносит в базу информацию об имени и номере телефона клиента
    //!!!!!!!!!!!!!!!!!!!!!!!! это сделать позже
    @PostMapping("/call-back")
    public void post(@RequestParam String name, @RequestParam String phoneNumber) {
        //adoptionService.addClientNameAndPhoneNumber;//тут вероятно будет один метод на все случаи
    }

    //приглашает волонтера к общению с клиентом
    //!!!!!!!!!!!!!!!!!!!!!!!! это сделать позже
    @GetMapping("/call-volunteer")
    public void callVolunteer() {
        //adoptionService.callVolunteer; //тут вероятно будет один метод на все случаи
    }

    //совета кинолога по обращению с собакой
    @GetMapping("/recommendation/first-meeting-doghandler")
    public String getAdviceFromDoghandler() {
        return adoptionService.getAdviceFromDoghandler();
    }

    //список проверенных кинологов
    @GetMapping("/recommendation/doghandlers")
    public String getGoodDoghandlers() {
        return adoptionService.getGoodDoghandlers();
    }


}