package com.example.restapiwithspring.events;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.example.restapiwithspring.events
 * fileName       : EventRepository
 * author         : user
 * date           : 2024-02-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-02-28        user       최초 생성
 */
public interface EventRepository extends JpaRepository<Event, Integer> {
}
