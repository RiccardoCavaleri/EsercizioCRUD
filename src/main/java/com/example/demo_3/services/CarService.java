package com.example.demo_3.services;

import com.example.demo_3.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo_3.repositories.CarRepository;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    //metodo per inserire e salvare una macchina
    public Car insertCar(Car newCar) {
        return carRepository.save(newCar);
    }

    //metodo select all
    public List<Car> selectAllCars() {
        return carRepository.findAll();
    }

    //metodo by id
    public Car selectCarsById(Long id) {
        if (carRepository.existsById(id)) {
            return carRepository.findById(id).get();
        } else {
            return new Car();
        }
    }

    //metodo update
    public Car updateCar(Long id, String newType) {
        if (carRepository.existsById(id)) {
            Car car = carRepository.findById(id).get();
            car.setType(newType);
            return carRepository.save(car);
        } else {
            return new Car();
        }
    }
    //metodo delete per una macchina
    public boolean deleteCar(Long id){
        if (carRepository.existsById(id)){
            carRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
    //metodo per eliminare tutte le macchine
    public void deleteAllCars(){
        carRepository.deleteAll();
    }
}
