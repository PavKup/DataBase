package service;

import entity.Shipping;
import entity.Town;
import repository.ItemsRepository;
import repository.ShippingsRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.zip.DataFormatException;

public class ShippingsService {

    ItemsService itemsService;
    TownsService townsService;

    ShippingsRepository shippingsRepository;

    public ShippingsService(ShippingsRepository shippingsRepository, ItemsService itemsService, TownsService townsService) {
        this.shippingsRepository = shippingsRepository;
        this.itemsService = itemsService;
        this.townsService = townsService;

    }

    public void createShipping(String town_id, String item_id, String strat_date, String end_date) {

        if (itemsService.findOne(item_id) == null) {
            System.out.println("Нельзя создать запись! Братан, потому что нет item");
            return;
        }

        if (townsService.findOne(town_id) == null) {
            System.out.println("Нельзя создать запись! Братан, потому что нет town");
            return;
        }

        if (strat_date == null || strat_date.isEmpty()) {
            System.out.println("Братан, не сегодня");
            return;
        }
        LocalDate start_date;
        try {
            start_date = LocalDate.parse(strat_date);
        }
        catch (DateTimeParseException e) {
            System.out.println("Брат, формат даты не тот");
            return;
        }
        LocalDate endDate = null;
        if (end_date != null || !end_date.isEmpty()){
            try {
                endDate = LocalDate.parse(strat_date);
            }
            catch (DateTimeParseException e) {
                System.out.println("Брат, формат даты не тот");
                return;
            }
            if(start_date.isAfter(endDate)) {
                System.out.println("Брат, даты перепутал местами");
                return;
            }

        }

        Shipping shipping = new Shipping(item_id, town_id);
        shipping.setStart_date(start_date);
        shipping.setEnd_date(endDate);
        shippingsRepository.save(shipping);
    }

    public void deleteShipping(String id){
        shippingsRepository.remove(id);
    }

    public List<Shipping> showShippings() {
        return shippingsRepository.findAll();
    }
}
