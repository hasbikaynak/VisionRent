package com.visionrent.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.visionrent.dto.CarDTO;
import com.visionrent.dto.response.ResponseMessage;
import com.visionrent.dto.response.VRResponse;
import com.visionrent.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;


	/*
	 *  {
    "model": "cupra",
    "doors": 4,
    "seats": 4,
    "luggage": 450,
    "transmission": "automatic",
    "airConditioning": true,
    "age": 4,
    "pricePerHour": 400,
    "fuelType": "diesel"
}
	 */

    //http://localhost:8080/car/admin/ffbd1a2f-301f-4d78-bd3b-238351caa558/add

    @PostMapping("/admin/{imageId}/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VRResponse> saveCar(@PathVariable String imageId, @Valid @RequestBody CarDTO carDTO){
        carService.saveCar(imageId, carDTO);

        VRResponse response=new VRResponse(ResponseMessage.CAR_SAVE_RESPONSE_MESSAGE,true);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //http://localhost:8080/car/visitors/all
    @GetMapping("/visitors/all")
    public ResponseEntity<List<CarDTO>> getAllCars(){
        List<CarDTO> allCars = carService.getAllCars();
        return ResponseEntity.ok(allCars);
    }

    //http://localhost:8080/car/visitors/pages?size=2&page=0&sort=id&direction=DESC

    @GetMapping("/visitors/pages")
    public ResponseEntity<Page<CarDTO>> getAllCarsWithPage(@RequestParam("page") int page,
                                                           @RequestParam("size") int size,
                                                           @RequestParam("sort") String prop,
                                                           @RequestParam(value="direction",required=false,defaultValue="DESC") Direction direction){

        Pageable pageable= PageRequest.of(page, size, Sort.by(direction,prop));

        Page<CarDTO> pageDTO = carService.findAllWithPage(pageable);

        return ResponseEntity.ok(pageDTO);
    }

    //http://localhost:8080/car/visitors/1
    @GetMapping("/visitors/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id){
        CarDTO carDTO= carService.findById(id);
        return ResponseEntity.ok(carDTO);
    }


}
