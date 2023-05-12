package com.mscv.inventoryservice.service;

import com.mscv.inventoryservice.dto.InventoryResponse;
import com.mscv.inventoryservice.entity.Inventory;
import com.mscv.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String codSku) {
        return inventoryRepository.findByCodSku(codSku)
                .isPresent();
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> codSkuList) {
        return inventoryRepository.findByCodSkuIn(codSkuList)
                .stream()
                .map(this::mapInventoryToInventoryResponse)
                .collect(toList());
    }

    private InventoryResponse mapInventoryToInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .codSku(inventory.getCodSku())
                .inStock(inventory.getCantidad() > 0)
                .build();
    }


}
