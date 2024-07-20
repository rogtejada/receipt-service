package com.rtejada.receipt.controller;

import com.rtejada.receipt.domain.PageReceipt;
import com.rtejada.receipt.exception.NotFoundException;
import com.rtejada.receipt.service.PageReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/page-receipts", produces = "application/json; charset=utf-8")
@RequiredArgsConstructor
public class PageReceiptController {

    private final PageReceiptService pageReceiptService;

    @GetMapping("/{id}")
    public PageReceipt getById(@PathVariable("id") String id) {
        return pageReceiptService.getById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping
    public List<PageReceipt> getAll() {
        return pageReceiptService.getAllReceipts();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        pageReceiptService.deleteById(id);
    }
    @PostMapping
    public PageReceipt create(@RequestBody PageReceipt pageReceipt) {
        return pageReceiptService.create(pageReceipt);
    }

    @PutMapping("/{id}")
    public PageReceipt update(@RequestBody PageReceipt pageReceipt, @PathVariable("id") String id) {
        pageReceipt.setId(id);
        return pageReceiptService.update(pageReceipt);
    }


    @GetMapping("/path/{path}")
    public PageReceipt getByPath(@PathVariable("path") String path) {
        return pageReceiptService.getByPath(path).orElseThrow(NotFoundException::new);
    }

}
