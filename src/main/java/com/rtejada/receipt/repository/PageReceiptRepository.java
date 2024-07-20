package com.rtejada.receipt.repository;

import com.rtejada.receipt.domain.PageReceipt;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


@EnableScan
public interface PageReceiptRepository extends CrudRepository<PageReceipt, String> {


    Optional<PageReceipt> findByIdAndUserId(String id, String userId);
    List<PageReceipt> findAllByUserId(String userId);

    Optional<PageReceipt> findByUserIdAndPath(String userId, String path);

    void deleteByIdAndUserId(String id, String userId);
}
