package com.caonhatlong.inventoryservice.service;

import com.caonhatlong.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {

    public List<InventoryResponse> isInStock(List<String> skuCode);
}
