package com.highfive.refurmoa.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notice_board")
public class NoticeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noti_num", nullable = false)
    private int notiNum;

    @Column(name = "noti_title", nullable = false, length = 20)
    private String notiTitle;

    @Column(name = "noti_inf", nullable = false, length = 50)
    private String notiInf;

    @Column(name = "noti_image", length = 100)
    private String notiImage;

    @Column(name = "noti_date", nullable = false)
    private Date notiDate;

}
