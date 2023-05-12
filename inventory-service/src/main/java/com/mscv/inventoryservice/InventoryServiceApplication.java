package com.mscv.inventoryservice;

import com.mscv.inventoryservice.entity.Inventory;
import com.mscv.inventoryservice.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootApplication
public class InventoryServiceApplication implements CommandLineRunner {

    @Autowired
    InventoryRepository inventoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("exceute");
        Inventory imac = Inventory.builder()
                .cantidad(15)
                .codSku("imac")
                .build();

        Inventory airpods = Inventory.builder()
                .cantidad(150)
                .codSku("airod")
                .build();

        inventoryRepository.save(imac);
        inventoryRepository.save(airpods);
    }

    ;

}
