package com.fourkbsys.eventms.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventPostgresqlRepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {

    @Query("SELECT e FROM EventEntity e WHERE e.state <> 'deleted' AND e.fromDate >= :dateTime")
    List<EventEntity> retrieveAllByDate(@Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT count(e) FROM EventEntity e WHERE e.state <> 'deleted' AND ((e.fromDate <= :begin AND :begin < e.toDate) OR (e.fromDate <= :end AND :end < e.toDate)) AND e.room=:room")
    Long countEventOverlappingRoomId(@Param("begin") LocalDateTime fromDate, @Param("end") LocalDateTime toDate, @Param("room") RoomEntity room);

}
