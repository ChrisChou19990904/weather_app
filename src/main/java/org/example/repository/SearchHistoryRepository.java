package org.example.repository;

import org.example.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    // 取得最近 5 筆紀錄
    List<SearchHistory> findTop5ByOrderBySearchTimeDesc();
}
