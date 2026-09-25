package com.example.homework4.repository;

import com.example.homework4.entity.GiangVien;
import com.example.homework4.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class GiangVienRepository {
    private Session s;

    public GiangVienRepository() {
        //mo phien trong contructor
        s = HibernateUtil.getFACTORY().openSession();
    }


    public List<GiangVien> getAll() {
        return s.createQuery("from GiangVien ").list();
    }

    public GiangVien findById(Long id) {// lấy một giảng viên
        return s.find(GiangVien.class,id);
    }

    public void add(GiangVien giangVien) {
        // transation -> tinh toan ven
        try{
            //b1: bat dau 1 transiton
            s.getTransaction().begin();
            // b2: thuc hien chuc nang add -> persit
            s.persist(giangVien);
            //b3: commit
            s.getTransaction().commit();
        }catch (Exception e){
            s.getTransaction().rollback(); // error se quay ve trang thai ban dau
            e.printStackTrace();
        }
    }

    public void update(GiangVien giangVien) {
        // transation -> tinh toan ven
        try{
            //b1: bat dau 1 transiton
            s.getTransaction().begin();
            // b2: thuc hien chuc nang add -> persit
            s.merge(giangVien);
            //b3: commit
            s.getTransaction().commit();
        }catch (Exception e){
            s.getTransaction().rollback(); // error se quay ve trang thai ban dau
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        // transation -> tinh toan ven
        try{
            //b1: bat dau 1 transiton
            s.getTransaction().begin();
            // b2: thuc hien chuc nang add -> persit
            // B2: Thêm entity
            s.delete(id);
            //b3: commit
            s.getTransaction().commit();
        }catch (Exception e){
            s.getTransaction().rollback(); // error se quay ve trang thai ban dau
            e.printStackTrace();
        }
    }

    public List<GiangVien> search(String ten,
                                  Long min,
                                  Long max,
                                  Boolean gioiTinh) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new GiangVienRepository().getAll());
//        System.out.println(new GiangVienRepository().findById(1L));
    }
}
