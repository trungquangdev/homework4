package com.example.homework4.repository;

import com.example.homework4.entity.GiangVien;
import com.example.homework4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class GiangVienRepository {

    //lấy toàn bộ ds GiangVien->dùng cho trang giangvien.jsp
    public List<GiangVien> getAll() {
        try (Session s = HibernateUtil.getFactory().openSession()) {
            //"from GiangVien" câu truy vấn HQL (truy vấn theo class java và tên thuộc tính)
            return s.createQuery("from GiangVien", GiangVien.class).list();
        }
    }

    public GiangVien findById(Long id) {// lấy một giảng viên
        try (Session s = HibernateUtil.getFactory().openSession()) {
            return s.find(GiangVien.class, id);
        }
    }

    public void add(GiangVien gv) {
        try (Session s = HibernateUtil.getFactory().openSession()) {
            Transaction tx = s.getTransaction();
            try {
                tx.begin(); // bắt đầu giao dịch
                s.persist(gv); // đánh dấu "gv" cần được INSERT vào DB
                tx.commit();// xác nhận -> Hibernate thật sự chạy câu INSERT lúc này
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw new RuntimeException("Khong them duoc giang vien",e);
            }
        }
    }

    public void update(GiangVien gv) {
        try(Session s=HibernateUtil.getFactory().openSession()){
            Transaction tx=s.getTransaction();
            try{
                tx.begin();
                s.merge(gv);
                tx.commit();
            }catch (Exception e){
                if(tx.isActive()){
                    tx.rollback();
                }throw new RuntimeException("Sua that bai",e);
            }
        }
    }

    public void delete(GiangVien gv) {
        try(Session s = HibernateUtil.getFactory().openSession()){
            Transaction tx = s.getTransaction();
            try{
                tx.begin();
                s.remove(gv);
                tx.commit();
            }catch (Exception e){
                if(tx.isActive()){
                    tx.rollback();
                }throw new RuntimeException("Xoa that bai",e);
            }
        }
    }

    public List<GiangVien> search(String ten,
                                  Long min,
                                  Long max,
                                  Boolean gioiTinh) {
        return null;
    }

    public static void main(String[] args) {


        GiangVienRepository repository = new GiangVienRepository();
        GiangVien gv = new GiangVien();

        gv.setMsgv("GV001");
        gv.setTen("Nguyen Van An");
        gv.setTuoi(30L);
        gv.setGioiTinh(true);
        gv.setQueQuan("Ha Noi");

        repository.add(gv);
        System.out.println(new GiangVienRepository().getAll());
    }
}
