package com.fourkbsys.eventms.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketPostgresqlRepository extends JpaRepository<TicketEntity, String> {
}
