/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.NguoiHoc;
import helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NguoiHocDAO {
    public void insert(NguoiHoc model){
        String sql="INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
        model.getMaNH(),
        model.getHoTen(),
        model.getNgaySinh(),
        model.isGioiTinh(),
        model.getDienThoai(),
        model.getEmail(),
        model.getGhiChu(),
        model.getMaNV());
    }
    public void update(NguoiHoc model){
        String sql="UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV=? WHERE MaNH=?";
        jdbcHelper.executeUpdate(sql,
        model.getHoTen(),
        model.getNgaySinh(),
        model.isGioiTinh(),
        model.getDienThoai(),
        model.getEmail(),
        model.getGhiChu(),
        model.getMaNV(),
        model.getMaNH());
        }

        public void delete(String id){
        String sql="DELETE FROM NguoiHoc WHERE MaNH=?";
        jdbcHelper.executeUpdate(sql, id);
        }

        public List<NguoiHoc> select(){
        String sql="SELECT * FROM NguoiHoc";
        return select(sql);
        }

        public List<NguoiHoc> selectByKeyword(String keyword){
        String sql="SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return select(sql, "%"+keyword+"%");
        }

        public List<NguoiHoc> selectByCourse(Integer makh){
        String sql="SELECT * FROM NguoiHoc WHERE MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return select(sql, makh);
        }

        public NguoiHoc findById(String manh){
        String sql="SELECT * FROM NguoiHoc WHERE MaNH=?";
        List<NguoiHoc> list = select(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
        }

        private List<NguoiHoc> select(String sql, Object...args){
        List<NguoiHoc> list=new ArrayList<>();
        try {
        ResultSet rs = null;
        try {
        rs = jdbcHelper.executeQuery(sql, args);
        while(rs.next()){
        NguoiHoc model=readFromResultSet(rs);
        list.add(model);
       }
        }
        finally{

        }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return list;
        }
        private NguoiHoc readFromResultSet(ResultSet rs) throws SQLException{
            NguoiHoc model=new NguoiHoc();
            model.setMaNH(rs.getString("MaNH"));
            model.setHoTen(rs.getString("HoTen"));
            model.setNgaySinh(rs.getDate("NgaySinh"));
            model.setGioiTinh(rs.getBoolean("GioiTinh"));
            model.setDienThoai(rs.getString("DienThoai"));
            model.setEmail(rs.getString("Email"));
            model.setGhiChu(rs.getString("GhiChu"));
            model.setMaNV(rs.getString("MaNV"));
            model.setNgayDK(rs.getDate("NgayDK"));
            return model;
        }
}
