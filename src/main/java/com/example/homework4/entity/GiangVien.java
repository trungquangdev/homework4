package com.example.homework4.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "giang_vien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiangVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Tên Java = tên DB
    //       ↓
    //Không cần @Column
    //
    //Tên Java ≠ tên DB
    //       ↓
    //Dùng @Column(name = "tên_cột_DB")
    private Long id;
    private String msgv;
    private String ten;
    private Long tuoi;
    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;
    @Column(name = "que_quan")
    private String queQuan;
}
