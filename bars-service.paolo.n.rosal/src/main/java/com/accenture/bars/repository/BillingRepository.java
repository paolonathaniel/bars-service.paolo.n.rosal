package com.accenture.bars.repository;

import com.accenture.bars.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface BillingRepository extends JpaRepository<Billing, Long> {
    @Query
    Billing findByBillingCycleAndStartDateAndEndDate(
            int billingCycle, LocalDate startDate, LocalDate endDate);
}
