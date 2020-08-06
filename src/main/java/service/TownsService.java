package service;

import entity.Town;
import repository.TownsRepository;

import java.util.List;

public class TownsService {


    TownsRepository townsRepository;

    public TownsService(TownsRepository townsRepository) {
        this.townsRepository = townsRepository;
    }

    public void createTown(String name, String distance) {
        Town town = new Town();
        town.setName(name);
        try {
            int distanceInt = Integer.parseInt(distance);
            town.setDistance(distanceInt);
        } catch(NumberFormatException e) {
            System.out.println("Братан, этоне перевести!");
            return;
        }
        townsRepository.save(town);
    }

    public void deleteTown(String id) {
        townsRepository.remove(id);
    }

    public List<Town> showTown() {
        return townsRepository.findAll();
    }

    public Town findOne(String id) {
        return townsRepository.findOne(id);
    }
}
