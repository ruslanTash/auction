package ru.skypro.lessons.auction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.auction.model.Bid;
import ru.skypro.lessons.auction.service.LotsService;

import java.util.List;

@RestController
@RequestMapping("lot")
@RequiredArgsConstructor
public class LotsController {

    private final LotsService lotsService;

    //    Получить информацию о первом ставившем на лот
    @GetMapping("/{id}/first")
    public Bid getFirstBidder(@PathVariable int id) {
        return null;
    }

    //    Возвращает имя ставившего на данный лот наибольшее количество раз
    @GetMapping("/{id}/frequent")
    public Bid getFrequentBidder(@PathVariable int id) {
        return null;
    }

    //    Получить полную информацию о лоте
    @GetMapping("/{id}")
    public FullLot getEmployeeFullLotById(@PathVariable int id) {
        return null;
    }


    //    Начать торги по лоту
    @PostMapping("/{id}/start")
    public void startLot(@PathVariable int id) {
    }


    //    Сделать ставку по лоту
    @PostMapping("/{id}/bid")
    public String makeBet(@PathVariable int id) {
        return null;
    }


    //    Остановить торги по лоту
    @PostMapping("/{id}/stop")
    public void stopLot(@PathVariable int id) {
    }


    //    Создает новый лот
    @PostMapping
    public CreateLot createLot(@RequestParam String title,
                               @RequestParam String description,
                               @RequestParam int startPrice,
                               @RequestParam int bidPrice) {
        return null;
    }

    //    Получить все лоты, основываясь на фильтре статуса и номере страницы
    @GetMapping()
    public List<Lot> getLotsByStatusAndPage(@RequestParam("status") String status,
                                            @RequestParam("page") int page) {
        return null;
    }


    //    Экспортировать все лоты в файл CSV
    @GetMapping("/export")
    public String exportLots() {
        return null;
    }


}
