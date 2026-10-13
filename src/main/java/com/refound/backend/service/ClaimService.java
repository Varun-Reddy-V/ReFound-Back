
package com.refound.backend.service;

import com.refound.backend.model.Claim;
import com.refound.backend.model.Item;
import com.refound.backend.repository.ClaimRepository;
import com.refound.backend.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final ItemRepository itemRepository;

    public ClaimService(
            ClaimRepository claimRepository,
            ItemRepository itemRepository) {
        this.claimRepository = claimRepository;
        this.itemRepository = itemRepository;
    }

    public Claim createClaim(Claim claim) {
        claim.setStatus("PENDING");
        return claimRepository.save(claim);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Optional<Claim> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    public Optional<Claim> approveClaim(Long id) {
        Optional<Claim> result = claimRepository.findById(id);

        if (result.isEmpty()) {
            return Optional.empty();
        }

        Claim claim = result.get();
        claim.setStatus("APPROVED");

        Item item = claim.getItem();

        if (item != null) {
            item.setStatus("RESOLVED");
            itemRepository.save(item);
        }

        return Optional.of(claimRepository.save(claim));
    }

    public Optional<Claim> rejectClaim(Long id) {
        Optional<Claim> result = claimRepository.findById(id);

        if (result.isEmpty()) {
            return Optional.empty();
        }

        Claim claim = result.get();
        claim.setStatus("REJECTED");

        return Optional.of(claimRepository.save(claim));
    }
}
