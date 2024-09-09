package com.example.demo_3.controllers;

import com.example.demo_3.entities.Car;
import com.example.demo_3.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    //metodo get(per visualizzare tutte le auto)
    @GetMapping("/showAll")
    public ResponseEntity<List<Car>> showCar(){
        return ResponseEntity.ok(carService.selectAllCars());
    }

    //Metodo get per visualizzare un auto by id
    @GetMapping("/showOneCar")
    public ResponseEntity<Car> showCarById(@PathVariable Long id){
        Car car = carService.selectCarsById(id);
        if (car != null){
            return ResponseEntity.ok(car);
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
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
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    //metodo delete per id
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        boolean isDeleted = carService.deleteCar(id);
        if (isDeleted){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    //metodo delete per tutte le macchine
    @DeleteMapping("/all/deleted")
    public ResponseEntity<Void> deleteAllCars(){
        carService.deleteAllCars();
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
