package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "accommodations")
public class Accommodation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//宿ID

	@Column(name = "category_id")
	private Integer categoryId;//宿カテゴリーID

	@Column(name = "bath_id")
	private Integer bathId;//風呂ID

	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;
	@ManyToOne
	@JoinColumn(name = "language_id", insertable = false, updatable = false)
	private Language language;
	@ManyToOne
	@JoinColumn(name = "bath_id", insertable = false, updatable = false)
	private BathType bathType;

	@OneToMany(mappedBy = "accommodation")
	private List<Plan> plans = new ArrayList<>();

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

	private String name;//宿泊施設の名前

	private String tel;//宿泊施設の電話番号

	private String address;//宿泊施設住所

	@Column(name = "language_id")
	private Integer languageId;//対応言語ID

	private String content;//サービス内容

	@Transient
	private LocalDate minDate;

	@Transient
	private Integer minPrice;

	@Transient
	private LocalDate oldDate;

	@Transient
	private LocalDate newDate;

	//デフォルトコンストラクタ
	public Accommodation() {

	}

	//コンストラクタ(修正版)
	public Accommodation(Integer categoryId, Integer bathId, String name, String tel, String address,
			Integer languageId,
			String content) {
		this.categoryId = categoryId;
		this.bathId = bathId;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.languageId = languageId;
		this.content = content;
	}
	//	//こっち使っているなら上記のほうにしてこのコンストラクタを削除してね
	//	public Accommodation(Integer categoryId, String name, String tel, String address,
	//			Integer languageId,
	//			String content) {
	//		this.categoryId = categoryId;
	//		this.name = name;
	//		this.tel = tel;
	//		this.address = address;
	//		this.languageId = languageId;
	//		this.content = content;
	//	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getBathId() {
		return bathId;
	}

	public void setBath(Integer bathId) {
		this.bathId = bathId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public Category getCategory() {
		return category;
	}

	public BathType getBathType() {
		return bathType;
	}

	public void setBathType(BathType bathType) {
		this.bathType = bathType;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public LocalDate getMinDate() {
		return minDate;
	}

	public void setMinDate(LocalDate minDate) {
		this.minDate = minDate;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public LocalDate getOldDate() {
		return oldDate;
	}

	public void setOldDate(LocalDate oldDate) {
		this.oldDate = oldDate;
	}

	public LocalDate getNewDate() {
		return newDate;
	}

	public void setNewDate(LocalDate newDate) {
		this.newDate = newDate;
	}

}
