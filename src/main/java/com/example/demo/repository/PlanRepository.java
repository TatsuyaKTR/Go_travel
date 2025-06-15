package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
	/**
	 * 日付範囲検索(宿一覧検索用)
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Plan> findByDateBetween(LocalDate startDate, LocalDate endDate);

	List<Plan> findByAccommodationId(Integer accommodationId);

	List<Plan> findByAccommodationIdAndDateBetween(Integer accommodationId, LocalDate startDate, LocalDate endDate);

	/**
	 * プラン用全検索
	 * 今のところ使うのが未定です
	 * @param roomId
	 * @param price
	 * @param accommodationId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Plan> findByRoomIdAndPriceAndAccommodationIdAndDateBetween(Integer roomId, Integer price,
			Integer accommodationId, LocalDate startDate, LocalDate endDate);

}
