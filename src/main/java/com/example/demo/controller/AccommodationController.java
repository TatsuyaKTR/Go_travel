package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Accommodation;
import com.example.demo.entity.Plan;
import com.example.demo.repository.AccommodationRepository;
import com.example.demo.repository.BathTypeRepository;
import com.example.demo.repository.LanguageRepository;
import com.example.demo.repository.PlanRepository;

@Controller
public class AccommodationController {
	@Autowired
	AccommodationRepository accommodationRepository;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	LanguageRepository languageRepository;

	@Autowired
	BathTypeRepository bathTypeRepository;

	@GetMapping("/")
	public String index() {
		return "search";
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param address
	 * @param categoryId
	 * @param bathId
	 * @param languageId
	 * @param languageList //選んだ言語のリスト
	 * @param model
	 * @return
	 */
	@GetMapping("/search")
	public String search(
			@RequestParam(name = "startDate", defaultValue = "") LocalDate startDate,
			@RequestParam(name = "endDate", defaultValue = "") LocalDate endDate,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "bathId", defaultValue = "") Integer bathId,
			@RequestParam(name = "languageId", defaultValue = "") List<String> languageList,
			Model model) {

		/**
		 * 言語のチェックボックスのvalueリストから
		 * 取得が必要なlanguageIdをリストで振り分ける
		 */
		List<Integer> languageIdList = new ArrayList<>();

		if (languageList.equals(Arrays.asList("ja"))) {
			//日本語を含むもの
			languageIdList = Arrays.asList(1, 2, 3, 4);
		} else if (languageList.equals(Arrays.asList("en"))) {
			// 英語を含むもの
			languageIdList = Arrays.asList(2, 4);
		} else if (languageList.equals(Arrays.asList("cn"))) {
			// 中国語を含むもの
			languageIdList = Arrays.asList(3, 4);
		} else if (languageList.equals(Arrays.asList("ja", "en"))) {
			//日本語と英語を含むもの
			languageIdList = Arrays.asList(1, 2, 4);
		} else if (languageList.equals(Arrays.asList("ja", "cn"))) {
			//日本語と中国語を含むもの
			languageIdList = Arrays.asList(1, 3, 4);
		} else if (languageList.equals(Arrays.asList("ja", "en", "cn"))) {
			//日本語と英語と中国語を含むもの
			languageIdList = Arrays.asList(4);
		} else {
			//全部
			languageIdList = Arrays.asList(1, 2, 3, 4);
		}

		/**
		 * 日付検索
		 * 日付が選択されていない場合は現在日から2099年12月31日までを取得
		 */
		List<Accommodation> accommodationsWithDate = new ArrayList<>();
		List<Plan> planList = new ArrayList<>();
		if (startDate != null && endDate != null) {
			planList = planRepository.findByDateBetween(startDate, endDate);
		} else if (startDate != null) {
			planList = planRepository.findByDateBetween(startDate, LocalDate.of(2099, 12, 31));
		} else if (endDate != null) {
			planList = planRepository.findByDateBetween(LocalDate.now(), endDate);
		} else {
			planList = planRepository.findByDateBetween(LocalDate.now(), LocalDate.of(2099, 12, 31));
			;
		}
		for (Plan plan : planList) {
			Accommodation accommodationList = accommodationRepository.findById(plan.getAccommodationId()).get();
			accommodationsWithDate.add(accommodationList);
		}

		/**
		 * 日付以外の組み合わせ検索
		 * 検索する言語は必ず指定する
		 */
		List<Accommodation> accommodationsWithConditions = new ArrayList<>();

		if (categoryId != null && bathId != null && address != null) {
			accommodationsWithConditions = accommodationRepository
					.findByCategoryIdAndBathIdAndAddressContainingAndLanguageIdIn(
							categoryId, bathId, address, languageIdList);
		} else if (categoryId != null && bathId != null) {
			accommodationsWithConditions = accommodationRepository.findByCategoryIdAndBathIdAndLanguageIdIn(
					categoryId, bathId, languageIdList);
		} else if (categoryId != null && address != null) {
			accommodationsWithConditions = accommodationRepository.findByCategoryIdAndAddressContainingAndLanguageIdIn(
					categoryId, address, languageIdList);
		} else if (bathId != null && address != null) {
			accommodationsWithConditions = accommodationRepository.findByBathIdAndAddressContainingAndLanguageIdIn(
					bathId, address, languageIdList);
		} else if (categoryId != null) {
			accommodationsWithConditions = accommodationRepository.findByCategoryIdAndLanguageIdIn(categoryId,
					languageIdList);
		} else if (bathId != null) {
			accommodationsWithConditions = accommodationRepository.findByBathIdAndLanguageIdIn(bathId, languageIdList);
		} else if (address != null) {
			accommodationsWithConditions = accommodationRepository.findByAddressContainingAndLanguageIdIn(address,
					languageIdList);
		} else {
			accommodationsWithConditions = accommodationRepository.findByLanguageIdIn(languageIdList);
		}

		/**
		 * 日付とその他条件の複合検索
		 * (日付検索)と(組み合わせ検索)がAND条件のときに一致する
		 */
		List<Accommodation> accommodationsWithDateAndConditons = new ArrayList<>();

		for (Accommodation accommodationWithCondition : accommodationsWithConditions) {
			for (Accommodation accommodationWithDate : accommodationsWithDate) {
				if (accommodationWithCondition.getId() == accommodationWithDate.getId()) {
					accommodationsWithDateAndConditons.add(accommodationWithCondition);
				}
			}
		}
		/**
		 * 宿泊施設の中で最安値(price)とその日付の最も古い日(oldDate)と最も新しい日(newDate)を取得
		 * priceの初期値をInteger.MAX_VALUEとし，もっと安い値段があればなれば更新
		 * oldDateの初期値を2099年12月31日とし，もっと古い日付があれば更新
		 * newDateの初期値を現在の日付とし，もっと新しい日付があれば更新
		 */
		for (Accommodation accommodation : accommodationsWithDateAndConditons) {
			Integer price = Integer.MAX_VALUE;
			LocalDate oldDate = LocalDate.of(2099, 12, 31);
			LocalDate newDate = LocalDate.now();
			for (Plan plan : accommodation.getPlans()) {
				if (price > plan.getPrice()) {
					price = plan.getPrice();
				}
				if (oldDate.isAfter(plan.getDate())) {
					oldDate = plan.getDate();
				}
				if (newDate.isBefore(plan.getDate())) {
					newDate = plan.getDate();
				}
			}
			accommodation.setMinPrice(price);
			accommodation.setOldDate(oldDate);
			accommodation.setNewDate(newDate);
		}

		/**
		 * 宿IDの重複を削除する処理
		 * つまり同じ宿IDが出てきたらスキップする
		 */
		List<Accommodation> accommodations = new ArrayList<>();

		//HashSetクラスでIdを記録(Idは重複しない)
		Set<Integer> Ids = new HashSet<>();
		for (Accommodation accommodation : accommodationsWithDateAndConditons) {
			if (!Ids.contains(accommodation.getId())) {
				accommodations.add(accommodation);
				Ids.add(accommodation.getId());
			}
		}
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("address", address);
		model.addAttribute("bathId", bathId);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("languageList", languageList);
		model.addAttribute("accommodations", accommodations);
		return "accommodation";
	}

	@PostMapping("/search")
	public String searchListView() {
		return "redirect:/reserveList";
	}

	@GetMapping("/search/{accommodationId}")
	public String detail(
			@PathVariable("accommodationId") Integer accommodationId,
			Model model) {
		Accommodation accommodation = accommodationRepository.findById(accommodationId).get();

		/**
		 * プランの中で最安値(price)とその日付の最も古い日(oldDate)と最も新しい日(newDate)を取得
		 * priceの初期値をInteger.MAX_VALUEとし，もっと安い値段があればなれば更新
		 * oldDateの初期値を2099年12月31日とし，もっと古い日付があれば更新
		 * newDateの初期値を現在の日付とし，もっと新しい日付があれば更新
		 */
		List<Plan> plans = planRepository.findByAccommodationId(accommodationId);
		for (Plan plan : plans) {
			LocalDate oldDate = LocalDate.of(2099, 12, 31);
			LocalDate newDate = LocalDate.now();
			if (oldDate.isAfter(plan.getDate())) {
				oldDate = plan.getDate();
			}
			if (newDate.isBefore(plan.getDate())) {
				newDate = plan.getDate();
			}
			plan.setOldDate(oldDate);
			plan.setNewDate(newDate);
		}

		accommodation.setPlans(plans);
		model.addAttribute("accommodation", accommodation);
		return "accommodationInf";
	}
}
