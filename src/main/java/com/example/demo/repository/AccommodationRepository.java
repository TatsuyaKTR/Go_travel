package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {

	/**
	 * 宿一覧検索
	 * 日付を組み合わせるときはJPAで使用してください
	 * @param categoryId
	 * @param bathId
	 * @param address
	 * @param languageIdList
	 * @return
	 */
	List<Accommodation> findByCategoryIdAndBathIdAndAddressContainingAndLanguageIdIn(
			Integer categoryId, Integer bathId, String address, List<Integer> languageIdList);

	List<Accommodation> findByCategoryIdAndBathIdAndLanguageIdIn(
			Integer categoryId, Integer bathId, List<Integer> languageIdList);

	List<Accommodation> findByCategoryIdAndAddressContainingAndLanguageIdIn(
			Integer categoryId, String address, List<Integer> languageIdList);

	List<Accommodation> findByBathIdAndAddressContainingAndLanguageIdIn(
			Integer bathId, String address, List<Integer> languageIdList);

	List<Accommodation> findByCategoryIdAndLanguageIdIn(Integer categoryId, List<Integer> languageIdList);

	List<Accommodation> findByBathIdAndLanguageIdIn(Integer bathId, List<Integer> languageIdList);

	List<Accommodation> findByAddressContainingAndLanguageIdIn(String address, List<Integer> languageIdList);

	List<Accommodation> findByCategoryId(Integer categoryId);

	List<Accommodation> findByBathId(Integer bathId);

	List<Accommodation> findByLanguageIdIn(List<Integer> languageIdList);

}