package com.example.homework4.repository;

import com.example.homework4.entity.GiangVien;
import com.example.homework4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
                tx.commit();// xác nhận
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw new RuntimeException("Khong them duoc giang vien", e);
            }
        }
    }

    public void update(GiangVien gv) {
        try (Session s = HibernateUtil.getFactory().openSession()) {
            Transaction tx = s.getTransaction();
            try {
                tx.begin();
                s.merge(gv); // gv có id -> Hibernate UPDATE dòng có id đó
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw new RuntimeException("Sua that bai", e);
            }
        }
    }

    public void delete(GiangVien gv) {
        try (Session s = HibernateUtil.getFactory().openSession()) {
            Transaction tx = s.getTransaction();
            try {
                tx.begin();
                // gv được lấy từ session khác (đã đóng) -> tìm lại trong session này rồi mới remove
                GiangVien canXoa = s.find(GiangVien.class, gv.getId());
                if (canXoa != null) {
                    s.remove(canXoa);
                }
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw new RuntimeException("Xoa that bai", e);
            }
        }
    }

    // Tìm kiếm: ô nào không nhập (null / rỗng) thì bỏ qua điều kiện đó,
    // các ô có nhập được kết hợp bằng AND.
    public List<GiangVien> search(String ten, Long min, Long max) {
        boolean coTen = ten != null && !ten.trim().isEmpty();

        StringBuilder hql = new StringBuilder("from GiangVien where 1=1");
        if (coTen) {
            hql.append(" and lower(ten) like :ten");
        }
        if (min != null) {
            hql.append(" and tuoi >= :min");
        }
        if (max != null) {
            hql.append(" and tuoi <= :max");
        }

        try (Session s = HibernateUtil.getFactory().openSession()) {
            Query<GiangVien> q = s.createQuery(hql.toString(), GiangVien.class);
            // Dùng tham số (:ten, :min, :max) thay vì nối chuỗi -> tránh SQL injection
            if (coTen) {
                q.setParameter("ten", "%" + ten.trim().toLowerCase() + "%");
            }
            if (min != null) {
                q.setParameter("min", min);
            }
            if (max != null) {
                q.setParameter("max", max);
            }
            return q.list();
        }
    }
}
