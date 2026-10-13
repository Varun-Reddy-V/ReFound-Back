package com.refound.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.refound.backend.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}