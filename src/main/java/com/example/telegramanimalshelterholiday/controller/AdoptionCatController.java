package com.example.telegramanimalshelterholiday.controller;

import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.service.AdoptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat/shelter/adoption")
public class AdoptionCatController {

    private final AdoptionService adoptionService;


    public AdoptionCatController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }


    //информация о том как взять животное
    @GetMapping("/info")
    public String getInfoAboutAdoption() {
        return adoptionService.getInfoAboutAdoption();
    }

    //правила знакомства с котом
    @GetMapping("/first-meet")
    public String getInfoAboutFirstMeet() {
        return adoptionService.getInfoAboutFirstMeetWithCat();
    }

    //список документов, чтобы забрать животное из приюта
    @GetMapping("/docs-for-adoption")
    public String getInfoAboutDocsForAdoption() {
        return adoptionService.getInfoAboutDocsForAdoption();
    }

   /* //получить всех животных (List<Animal> только из котов)
    //!!!!!!!!!!!!!!!!!!!!!!!! это сделать позже, пока с этим падает, видимо потому что возвращает null
    @GetMapping("/docs-for-adoption")
    public List<Animal> getAllCats() {
        return adoptionService.getAllCats();
    }*/

    //выдает список рекомендаций по транспортировке кота
    @GetMapping("/reccomendation/transportation")
    public String getInfoAboutTransportation() {
        return adoptionService.getInfoAboutCatTransportation();
    }

    //выдает список рекомендаций по обустройству дома для котенка
    @GetMapping("/reccomendation/home-adgustment-kitty")
    public String getInfoAboutAdgustmentKitty() {
        return adoptionService.getInfoAboutAdgustmentKitty();
    }

    //выдает список рекомендаций по обустройству дома для взрослого кота
    @GetMapping("/reccomendation/home-adgustment-cat")
    public String getInfoAboutAdgustmentCat() {
        return adoptionService.getInfoAboutAdgustmentCat();
    }

    //выдает список рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение)
    @GetMapping("/reccomendation/home-adgustment-unhealthy-sight")
    public String getInfoAboutAdgustmentUnhealthyWithSightProblems() {
        return adoptionService.getInfoAboutAdgustmentUnhealthyWithSightProblems();
    }

    //выдает список рекомендаций по обустройству дома для животного с ограниченными возможностями (мобильность)
    @GetMapping("/reccomendation/home-adgustment-unhealthy-mobility")
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
}
