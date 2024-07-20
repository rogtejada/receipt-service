package com.rtejada.receipt.service;

import com.rtejada.receipt.domain.PageReceipt;
import com.rtejada.receipt.exception.BusinessException;
import com.rtejada.receipt.exception.NotFoundException;
import com.rtejada.receipt.repository.PageReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PageReceiptService {
    private final String userId = "123";

    private final PageReceiptRepository repository;

    public PageReceipt create(PageReceipt pageReceipt) {
        pageReceipt.setUserId(userId);

        getByPath(pageReceipt.getPath()).ifPresent((receipt) -> {
            throw new BusinessException("Path Already Exists");
        });

        return repository.save(pageReceipt);
    }

    public PageReceipt update(PageReceipt pageReceipt) {
        PageReceipt dbReceipt = getById(pageReceipt.getId()).orElseThrow(NotFoundException::new);

        if (!dbReceipt.getPath().equals(pageReceipt.getPath())) {
            getByPath(pageReceipt.getPath()).ifPresent((receipt) -> {
                throw new BusinessException("Path Already Exists");
            });
        }

        dbReceipt.setPath(pageReceipt.getPath());
        dbReceipt.setJsonConfig(pageReceipt.getJsonConfig());
        return repository.save(pageReceipt);
    }

    public List<PageReceipt> getAllReceipts() {
        return repository.findAllByUserId(userId);
    }

    public Optional<PageReceipt> getById(String id) {
        return repository.findByIdAndUserId(id, userId);
    }

    public void deleteById(String id) {
        repository.deleteByIdAndUserId(id, userId);
    }

    public Optional<PageReceipt> getByPath(String path) {
        return repository.findByUserIdAndPath(userId, path);
    }
}
