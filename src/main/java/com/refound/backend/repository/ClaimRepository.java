
package com.refound.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.refound.backend.model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
