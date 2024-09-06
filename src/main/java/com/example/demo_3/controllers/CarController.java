package com.example.demo_3.controllers;

import com.example.demo_3.entities.Car;
import com.example.demo_3.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    //metodo post(per aggiungere un auto)
    @PostMapping("/add")
    public ResponseEntity<Car> insertCar(@RequestBody Car newCar){
         return ResponseEntity.ok(carService.insertCar(newCar));
    }

    //metodo get(per visualizzare la lista di auto)
    @GetMapping("/showAll")
    public ResponseEntity<List<Car>> showCar(){
        return ResponseEntity.ok(carService.selectAllCars());
    }

    //metodo update(per aggiornare un auto by id)
    @PutMapping("/change")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestParam String newType){
        //creo un oggetto di tipo Car, tramite il carservice seleziono l'auto byId
        Car car = carService.selectCarsById(id);
        //se car (l'id) non è null , creo un nuovo oggetto di tipo Car e faccio riferimento al metodo updateCar(Service)
        if (car != null){
            Car carUpdated = carService.updateCar(id, newType);
            return ResponseEntity.ok(carUpdated);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //metodo delete per id
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCar(Long id){
        boolean isDeleted = carService.deleteCar(id);
        if (isDeleted){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //metodo delete per tutte le macchine
    @DeleteMapping("/all/deleted")
    public ResponseEntity<Void> deleteAllCars(){
        carService.deleteAllCars();
        return ResponseEntity.noContent().build();
    }
}
