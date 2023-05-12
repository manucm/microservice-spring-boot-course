package com.mscv.inventoryservice.controller;

import com.mscv.inventoryservice.dto.InventoryResponse;
import com.mscv.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam("codSku") List<String> codSku) {
        return inventoryService.isInStock(codSku);
    }
/*
    @GetMapping("{codSku}")
    public boolean isInStock(@PathVariable("codSku") String codSku) {
        return inventoryService.isInStock(codSku);
    }*/


}
