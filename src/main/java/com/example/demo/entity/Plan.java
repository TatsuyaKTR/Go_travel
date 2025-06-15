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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "plans")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "room_id")
	private Integer roomId;//部屋のタイプ

	private Integer price;//部屋の値段

	@Column(name = "accommodation_id")
	private Integer accommodationId;//宿泊施設のID

	private LocalDate date; //日付

	@ManyToOne
	@JoinColumn(name = "accommodation_id", insertable = false, updatable = false)
	private Accommodation accommodation;

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	@OneToOne
	@JoinColumn(name = "room_id", insertable = false, updatable = false)
	private RoomType roomType;

	@OneToMany(mappedBy = "plan")
	private List<Reserve> reserves = new ArrayList<>();

	public List<Reserve> getReserves() {
		return reserves;
	}

	public void setReserves(List<Reserve> reserves) {
		this.reserves = reserves;
	}

	@Transient
	private LocalDate oldDate;
	@Transient
	private LocalDate newDate;

	//コンストラクタ
	public Plan() {

	}

	public Plan(Integer roomId, Integer price, Integer accommodationId, LocalDate date) {
		this.roomId = roomId;
		this.price = price;
		this.accommodationId = accommodationId;
		this.date = date;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Integer accommodationId) {
		this.accommodationId = accommodationId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
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
