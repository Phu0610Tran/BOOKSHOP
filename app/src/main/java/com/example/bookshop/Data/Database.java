package com.example.bookshop.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.bookshop.User_Activity.LoginActivity;
import com.example.bookshop.Models.BinhLuan;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.Models.TaiKhoan;
import com.example.bookshop.Models.ThongBao;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Truy vấn không trả kết quả: INSERT, CREATE, UPDATE, DELETE, ...
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // Truy vấn có trả kết quả: SELECT
    public Cursor Getdata(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    public int demthongbao(int IDTK)
    {
        Cursor cursor = Getdata("SELECT COUNT ( IDTBNEW ) FROM THONGBAONEW WHERE IDTK = "
                + IDTK);
        while (cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return 0;
    }
    public int isDaLike(int IDTB, int IDTK){
        Cursor cursor = Getdata("SELECT TRANGTHAI FROM LUOTDANHGIA WHERE IDTB = " + IDTB + " AND IDTK = " + IDTK );
        while (cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return -1;
    }
    public void xoathem(int IDTB, int IDTK, int THICH){
        if(THICH == 1){
            QueryData("UPDATE THONGBAO SET THICH = THICH - " + 1 + " WHERE IDTB = " + IDTB + "");
        }
        else if(THICH == 2 )
        {
            QueryData("UPDATE THONGBAO SET KHONGTHICH = KHONGTHICH - " + 1 + " WHERE IDTB = " + IDTB + "");
        }

        QueryData("DELETE FROM LUOTDANHGIA WHERE IDTB = " + IDTB + " AND IDTK = " + IDTK);
    }
    public void them(int IDTB, int IDTK, int THICH){
        if(THICH == 1){
            QueryData("UPDATE THONGBAO SET THICH = THICH + " + 1 + " WHERE IDTB = " + IDTB);
        }
        else if(THICH == 2 )
        {
            QueryData("UPDATE THONGBAO SET KHONGTHICH = KHONGTHICH + " + 1 + " WHERE IDTB = " + IDTB);
        }

        QueryData("INSERT INTO LUOTDANHGIA( IDTK, IDTB, TRANGTHAI) VALUES (" +
                IDTK + "," + IDTB + " , " + THICH + ")");
    }
    public String Layten(int IDTK){
        Cursor cursor = Getdata("SELECT TENTAIKHOAN FROM TAIKHOAN WHERE IDTAIKHOAN = "+ IDTK);
        cursor.moveToNext();
        return cursor.getString(0);
    }
    public int demsoBL(int IDTB ){
        Cursor cursor = Getdata("SELECT count(IDTB) FROM BINHLUANTB WHERE IDTB =" + IDTB +" GROUP by IDTB");
        while (cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return 0;
    }
    public void DELETE_DOAN(int IDSP, int IDTK){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE  FROM GIOHANG WHERE IDSP = "+ IDSP + " AND IDTK= " + IDTK  ;
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();


        statement.executeInsert();
    }
    public boolean KIEMTRAVOUCHER(int IDTK)
    {
        Cursor cursor = Getdata("SELECT VOUCHER FROM VONGCHINH WHERE IDTK = " +
                LoginActivity.taiKhoan.getMATK() + " ORDER BY VOUCHER DESC " );
        while (cursor.moveToNext())
        {
            return true;
        }
        return false;
    }
    public void DELETE_SANPHAM(int IDSP){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE  FROM SANPHAM WHERE IDSP = "+ IDSP  ;
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();


        statement.executeInsert();
    }
    public void UPDATE_IMAGE_TK(int IDTAIKHOAN, byte[] hinh){
        String sql = "UPDATE TAIKHOAN SET HINHANH = ? WHERE IDTAIKHOAN="+ IDTAIKHOAN ;
        SQLiteDatabase database = this.getWritableDatabase();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1,hinh);
        statement.executeInsert();
    }

    //---------------------------------------------quan ly
    public void UPDATE_DOAN(String ten,byte[] hinh,int SOLUONG,int  GIA,int IDDANHMUC,int SPNEW, int IDSP ){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSANPHAM", ten);
        values.put("GIA", GIA);
        values.put("SOLUONG", SOLUONG);
        values.put("IDDANHMUC", IDDANHMUC);
        values.put("SPNEW", SPNEW);

        sqLiteDatabase.update("SANPHAM",values,"IDSP =" + IDSP,null);


        String sql = "UPDATE SANPHAM SET HINHANH = ? WHERE IDSP="+ IDSP ;
        SQLiteDatabase database = this.getWritableDatabase();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1,hinh);
        statement.executeInsert();
    }
    // BL
    public void ThemBL(int IDTK, int IDTB, String NOIDUNG, String THOIGIAN){
        QueryData("INSERT INTO BINHLUANTB (IDTK,IDTB,NOIDUNG,THOIGIAN) VALUES (" +IDTK + ",'" +
                IDTB + "','" + NOIDUNG+"','" + THOIGIAN + "')");
    }
    public void ThemDanhGia(int IDTK, int IDSP, String NOIDUNG, String THOIGIAN, float SOSAO, int IDCTHOADON){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put("IDTK",   IDTK);
        cv.put("IDSP",   IDSP);
        cv.put("NOIDUNG",   NOIDUNG);
        cv.put("THOIGIAN",   THOIGIAN);
        cv.put("SOSAO",   SOSAO);
        cv.put("IDCTHOADON",   IDCTHOADON);
        database.insert( "DANHGIA", null, cv );

    }

    public boolean KiemTraDanhGia(int IDTK, int IDSP, int IDCTHOADON)
    {
        Cursor cursor = Getdata("SELECT IDTK,NOIDUNG,THOIGIAN,A.IDCTHOADON,IDSP FROM DANHGIA A, CHITIETHOADON B WHERE A.IDSP = B.IDSANPHAM AND A.IDCTHOADON = B.IDCTHOADON AND " +
                " IDTK = " + IDTK +  " AND IDSP = " + IDSP +  " AND A.IDCTHOADON = " + IDCTHOADON );
        while (cursor.moveToNext())
        {
            return false;
        }
        return true;
    }
    public int countCTSP(int IDTK)
    {
        Cursor cursor = Getdata("SELECT COUNT(IDCTHOADON) FROM CHITIETHOADON WHERE IDTAIKHOAN = " + IDTK);
        while (cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return 0;
    }
    public int countDG(int IDTK)
    {
        Cursor cursor = Getdata("SELECT COUNT(IDCTHOADON) FROM DANHGIA WHERE IDTK = " + IDTK);
        while (cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return 0;
    }
    public ArrayList<BinhLuan> LayBinhLuan(int IDSP){
        ArrayList<BinhLuan> list = new ArrayList<>();
        Cursor tro = Getdata("SELECT A.IDTK,B.HINHANH,A.NOIDUNG,A.THOIGIAN,A.SOSAO,B.TENTAIKHOAN FROM DANHGIA A,TAIKHOAN B WHERE A.IDTK = B.IDTAIKHOAN AND A.IDSP = " + IDSP + " ORDER BY THOIGIAN ASC");
        while (tro.moveToNext()){
            list.add(new BinhLuan(
                    tro.getInt(0),
                    tro.getBlob(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getFloat(4),
                    tro.getString(5)
            ));
        }

        return list;
    }
    public void Dangbai(String NOIDUNG, String THOIGIAN, byte[] HinhAnh, String TIEUDE){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put("NOIDUNG",   NOIDUNG);
        cv.put("DATE",   THOIGIAN);
        cv.put("HINHANH",   HinhAnh);
        cv.put("THICH",   0);
        cv.put("KHONGTHICH",   0);
        cv.put("TIEUDE",   TIEUDE);
        database.insert( "THONGBAO", null, cv );
    }
    public void DangbaiALL(String NOIDUNG, int IDTK, String TIEUDE){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put("NOIDUNG",   NOIDUNG);
        cv.put("IDTK",IDTK);
        cv.put("TIEUDE",TIEUDE);
        database.insert( "THONGBAONEW", null, cv );
    }
    public boolean tieudeSOSANH(int IDTK, String TIEUDE){
        Cursor cursor = Getdata("SELECT B.TIEUDE FROM THONGBAONEW A,THONGBAO B WHERE IDTK = "
                + IDTK + " AND '" + TIEUDE + "' = A.TIEUDE");
       while (cursor.moveToNext())
       {
           return true;
       }
       return false;
    }
    public int layidthongbao (int IDTK, String TIEUDE){
        Cursor cursor = Getdata("SELECT B.IDTB FROM THONGBAONEW A,THONGBAO B WHERE IDTK = "
                + IDTK + " AND '" + TIEUDE + "' = B.TIEUDE");
        while (cursor.moveToNext())
        {
            return cursor.getInt(0);
        }
        return 0;
    }
    public void HUYDON(int IDHOADON, int TINHTRANG){
        QueryData("UPDATE HOADON SET TINHTRANG = " + TINHTRANG + "   WHERE IDHOADON = " + IDHOADON);

    }
    public void XoaThongBao(int IDTK,String TIEUDE){
        QueryData(" DELETE FROM THONGBAONEW WHERE IDTK = " + IDTK + " AND TIEUDE = '" + TIEUDE + "' " );
    }
//    public String tieude(int IDTK){
//        Cursor cursor = Getdata("SELECT TIEUDE FROM THONGBAONEW WHERE IDTK = "
//                + IDTK);
//        cursor.moveToNext();
//        return cursor.getString(0);
//    }
    public ArrayList<TaiKhoan> LayALLTK(){
        ArrayList<TaiKhoan> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM TAIKHOAN WHERE LOAITK = 1 ");
        while (cursor.moveToNext()){
            list.add(new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7),
                    cursor.getBlob(8)
            ));
        }

        return list;
    }
    public ArrayList<ThongBao> LayALLTB(int IDTK){
        ArrayList<ThongBao> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT TIEUDE,NOIDUNG FROM THONGBAONEW WHERE IDTK =  " + IDTK);
        while (cursor.moveToNext()){
            list.add(new ThongBao(
                    cursor.getString(0),
                    cursor.getString(1)
            ));
        }

        return list;
    }
    public ArrayList<BinhLuan> LayBinhLuanTB(int IDTB){
        ArrayList<BinhLuan> list = new ArrayList<>();
        Cursor tro = Getdata("SELECT A.IDTK,B.HINHANH,A.NOIDUNG,A.THOIGIAN FROM BINHLUANTB A,TAIKHOAN B WHERE A.IDTK = B.IDTAIKHOAN AND A.IDTB =" + IDTB +" ORDER BY THOIGIAN ASC ");
        while (tro.moveToNext()){
            list.add(new BinhLuan(
                    tro.getInt(0),
                    tro.getBlob(1),
                    tro.getString(2),
                    tro.getString(3)
            ));
        }

        return list;
    }
    public void Tangdiem(int IDTK, int diem){
        QueryData("UPDATE TAIKHOAN SET DIEM = " + diem + "   WHERE IDTAIKHOAN = " + IDTK);

    }
    public boolean VONGTONTAI(int IDTK,float VONG){
        Cursor tro = Getdata("SELECT * FROM VONGCHINH WHERE IDTK = " + IDTK + " AND VONG = " + VONG );
        while (tro.moveToNext()) {
            return false;
        }
        return true;
    }
    public void VONGGAME(int IDTK,float VONG, int SOSAO, int VOUCHER){
        if(VONGTONTAI(IDTK, VONG)){
            QueryData("INSERT INTO VONGCHINH ( IDTK, VONG, SOSAO, VOUCHER ) VALUES ( " + IDTK + " , " + VONG + " , "
                    + VOUCHER + " , "
                    + SOSAO + ")");
        }
        else {
            QueryData("UPDATE VONGCHINH SET SOSAO = " + SOSAO + "   WHERE IDTK = " + IDTK + " AND VONG = " + VONG);
        }
    }
    public boolean kiemtraVoucher(int IDTK,int VOUCHER){
        Cursor tro = Getdata("SELECT * FROM VONGCHINH WHERE IDTK = " + IDTK + " AND VOUCHER = " + VOUCHER );
        while (tro.moveToNext()) {
            return false;
        }
        return true;
    }
    public boolean TonTaiBanBe(int IDTK,int IDBAN)
    {
        Cursor tro = Getdata("SELECT * FROM BANBE WHERE IDTK_CHINH = " + IDTK + " AND IDTK_BAN = " + IDBAN );
        while (tro.moveToNext()) {
            return false;
        }
        return true;
    }
    public void ThemBan(int IDTK,int IDBAN){
        QueryData("INSERT INTO BANBE ( IDTK_CHINH, IDTK_BAN) VALUES ( " + IDTK + " , " + IDBAN + ")");
    }
    public void XoaBan(int IDTK,int IDBAN){
        QueryData(" DELETE FROM BANBE WHERE IDTK_CHINH = " + IDTK + " AND IDTK_BAN = " + IDBAN);
    }
    //-------------------------
    public void INSERT_DOAN(String ten,byte[] hinh,int soluong,int  Gia,int danhmuc,int spmoi ) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put(CreateDatabase.tbl_SANPHAM_TENSANPHAM,    ten);
        cv.put(CreateDatabase.tbl_SANPHAM_GIA,   Gia);
        cv.put(CreateDatabase.tbl_SANPHAM_IDDANHMUC,   danhmuc);
        cv.put(CreateDatabase.tbl_SANPHAM_SOLUONG,   soluong);
        cv.put(CreateDatabase.tbl_SANPHAM_IDSP_NEW,   spmoi);
        cv.put(CreateDatabase.tbl_SANPHAM_HINHANH,   hinh);
        database.insert( CreateDatabase.tbl_SANPHAM, null, cv );

    }


    //-----------------------------------------Quanlytaikhoan
    public void UPDATE_TAIKHOAN(int IDTAIKHOAN,String TENTAIKHOAN, String MATKHAU, int SDT,String EMAIL, String NGAYSINH,
                                int LOAITK, String DIACHI, byte[] HINH){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENTAIKHOAN", TENTAIKHOAN);
        values.put("MATKHAU", MATKHAU);
        values.put("SDT", SDT);
        values.put("EMAIL", EMAIL);
        values.put("NGAYSINH", NGAYSINH);
        values.put("LOAITK", LOAITK);
        values.put("DIACHI", DIACHI);

        sqLiteDatabase.update("TAIKHOAN",values,"IDTAIKHOAN =" + IDTAIKHOAN,null);


        String sql = "UPDATE TAIKHOAN SET HINHANH = ? WHERE IDTAIKHOAN="+ IDTAIKHOAN ;
        SQLiteDatabase database = this.getWritableDatabase();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1,HINH);
        statement.executeInsert();
    }

    public void DELETE_TAIKHOAN(int IDTAIKHOAN){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE  FROM TAIKHOAN WHERE IDTAIKHOAN = "+ IDTAIKHOAN  ;
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();


        statement.executeInsert();
    }
    //-----------------------------thanh toan


    public boolean SPChuaCoTrongGH(int IDTK,int IDSP){
        Cursor tro = Getdata("SELECT * FROM GIOHANG WHERE IDTK = " + IDTK + " AND IDSP = " + IDSP );
        while (tro.moveToNext()) {
            return false;
        }
        return true;
    }


    public boolean HoaDonChuaCoTrongHD(){
        Cursor tro = Getdata("SELECT IDCTHOADON FROM CHITIETHOADON " );
        while (tro.moveToNext()) {
            return false;
            // DA CO TON TAI TRONG HOA DON
        }
        return true;
        // CHUA ID HOA DON
    }
    public float TrungBinhSoSao(int IDSP){
        Cursor tro = Getdata("SELECT AVG(SOSAO) FROM DANHGIA WHERE IDSP =  " + IDSP );
        while (tro.moveToNext()) {
            return tro.getFloat(0);
        }
        return 0;
    }
    public int DemSoDanhGia(int IDSP){
        Cursor tro = Getdata("SELECT COUNT(SOSAO) FROM DANHGIA WHERE IDSP =  " + IDSP );
        while (tro.moveToNext()) {
            return tro.getInt(0);
        }
        return 0;
    }

    public void INSERT_HOADON(double TONGTIEN, int IDCTHOADON, String GHICHU, String DIACHI, int IDTK, int tienship, int HTVC, String NGAYDAT, int TIENGIAM)
    {
        QueryData("INSERT INTO " + CreateDatabase.tbl_HOADON +
                " ( "
                + CreateDatabase.tbl_HOADON_TONGTIEN + " , "
                + CreateDatabase.tbl_HOADON_IDCTHOADON + " , "
                + CreateDatabase.tbl_HOADON_GHICHU+ " , "
                + CreateDatabase.tbl_HOADON_DIACHI + " , "
                + CreateDatabase.tbl_HOADON_IDTAIKHOAN + " , "
                + "TIENSHIP" + " , "
                + "HINHTHUCVANCHUYEN" + " , "
                + "NGAYDAT" + " , "
                + "TINHTRANG" + " , "
                + "TIENGIAM" +
                " ) VALUES ( " + TONGTIEN + " , "
                + IDCTHOADON + " , '"
                + GHICHU + "' , '"
                + DIACHI + "' , "
                + IDTK + " , "
                + tienship + " , "
                + HTVC + " , '"
                + NGAYDAT + "' , "
                + 1 + " , "
                + TIENGIAM + " , "
                + " ) ");
    }
    public void INSERT_CTHOADON(int IDCTHOADON,int IDTK, int IDSP, String TenSP, int Soluong, int thanhtien)
    {
        QueryData("INSERT INTO " + CreateDatabase.tbl_CHITIETHOADON +
        " ( "
        + CreateDatabase.tbl_CHITIETHOADON_IDCTHOADON + " , "
        + CreateDatabase.tbl_CHITIETHOADON_IDSANPHAM + " , "
        + CreateDatabase.tbl_CHITIETHOADON_IDTAIKHOAN+ " , "
        + CreateDatabase.tbl_CHITIETHOADON_TENSANPHAM + " , "
        + CreateDatabase.tbl_CHITIETHOADON_SOLUONG + " , "
        + CreateDatabase.tbl_CHITIETHOADON_THANHTIEN
        + " ) VALUES ( " + IDCTHOADON +" , " + IDSP + " , " + IDTK+" , '" + TenSP + "' , " + Soluong + " , "
        + thanhtien + " ) ");
    }

    public void DELETE_GIOHANG(int IDTK){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE  FROM GIOHANG WHERE IDTK = "+ IDTK ;
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();


        statement.executeInsert();
    }

    public void UPDATE_VOUCHER(int IDTK,int VOUCHER)
    {
        QueryData("UPDATE VONGCHINH SET VOUCHER = " + 0 + "   WHERE IDTK = " + IDTK + " AND VOUCHER = " + VOUCHER);

    }
//----------------------------------------------thanh toan
    public void SPGH(int IDTK,byte[] hinh, int IDSP, String TenSP, int Soluong, int thanhtien){
        if(SPChuaCoTrongGH(IDTK, IDSP)){
            QueryData("INSERT INTO " + CreateDatabase.tbl_GIOHANG +
                    " ( "
                    + CreateDatabase.tbl_GIOHANG_IDTK + " , "
                    + CreateDatabase.tbl_GIOHANG_HINHANH + " , "
                    + CreateDatabase.tbl_GIOHANG_IDSP + " , "
                    + CreateDatabase.tbl_GIOHANG_TENSANPHAM + " , "
                    + CreateDatabase.tbl_GIOHANG_SOLUONG + " , "
                    + CreateDatabase.tbl_GIOHANG_THANHTIEN
                    + " ) VALUES ( " + IDTK +" , " + null + " , " + IDSP+" , '" + TenSP + "' , " + Soluong + " , "
                    + thanhtien + " ) ");
            //------------------------------

            //------------------------------

            SQLiteDatabase database = getWritableDatabase();
            String sql = "UPDATE GIOHANG SET HinhAnh = ? WHERE IDTK="+ IDTK + " AND IDSP=" + IDSP ;
            SQLiteStatement statement = database.compileStatement(sql);
            statement.clearBindings();
            statement.bindBlob(1,hinh);
            statement.executeInsert();



        }
        else {
            QueryData("UPDATE " + CreateDatabase.tbl_GIOHANG + " SET "
                    + CreateDatabase.tbl_GIOHANG_SOLUONG + " = "+CreateDatabase.tbl_GIOHANG_SOLUONG + " + " + Soluong + " , "
                    + CreateDatabase.tbl_GIOHANG_THANHTIEN + " = " + CreateDatabase.tbl_GIOHANG_THANHTIEN + " + " + thanhtien
                    + " WHERE " + CreateDatabase.tbl_GIOHANG_IDTK + " = " + IDTK+ " AND "
                    + CreateDatabase.tbl_GIOHANG_IDSP + " = " + IDSP)
                    ;
            ;
        }
    }
    public SanPham SANPHAM(int IDSP){
        Cursor cursor = Getdata("SELECT * FROM SANPHAM WHERE IDSP = " + IDSP );
        while (cursor.moveToNext()) {
            return new SanPham(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5)
            );

        }
        return null;
    }

    public ArrayList<SanPham> TIMKIEM(){
        ArrayList<SanPham> doAnArrayList = new ArrayList<>();
        String truyvan = "SELECT * FROM SANPHAM ";
        Cursor cursor = Getdata(truyvan);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()){
                doAnArrayList.add(new SanPham(
                        cursor.getInt(0),
                        cursor.getBlob(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5)
                ));
            }
            return doAnArrayList;
        }
        return doAnArrayList;
    }

    public void UPDATE_SOLUONG(int IDSP,int Soluong)
    {
        QueryData("UPDATE " + CreateDatabase.tbl_SANPHAM + " SET "
                + CreateDatabase.tbl_SANPHAM_SOLUONG + " = "+CreateDatabase.tbl_SANPHAM_SOLUONG + " - " + Soluong +
                " WHERE " + CreateDatabase.tbl_GIOHANG_IDSP + " = " + IDSP)
        ;
    }

    public void UPDATE(String tentaikhoan, int sdt, String email, String diachi, int Id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE TAIKHOAN SET TENTAIKHOAN = ? , SDT = ?, EMAIL = ?, DIACHI = ? WHERE IDTAIKHOAN=" + Id;
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, tentaikhoan);
        statement.bindDouble(2, sdt);
        statement.bindString(3, email);
        statement.bindString(4, diachi);

        statement.executeUpdateDelete();
//        statement.executeInsert();
    }

    // region Tài Khoản
    public void CapNhatMatKhau(int IDTAIKHOAN, String MATKHAU){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_MATKHAU + " = '" + MATKHAU + "' WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = " + IDTAIKHOAN);
    }

    public boolean isMatKhau(int IDTAIKHOAN, String MATKHAU){
        Cursor tro = Getdata("SELECT * FROM " + CreateDatabase.tbl_TAIKHOAN + " WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = " + IDTAIKHOAN + " AND " + CreateDatabase.tbl_TAIKHOAN_MATKHAU + " = '" + MATKHAU + "'");
        while (tro.moveToNext()){
            return true;
        }
        return false;
    }

    public void CapNhatTaiKhoan(int IDTAIKHOAN, int SDT, String EMAIL, String DIACHI){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_SDT + " = '" + SDT + "', " + CreateDatabase.tbl_TAIKHOAN_EMAIL + " = '" + EMAIL +
                "' , " + CreateDatabase.tbl_TAIKHOAN_DIACHI + " = '" + DIACHI + "' WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = '" + IDTAIKHOAN +"'");
    }

    public boolean isTonTaiTK(String IDTAIKHOAN){
        Cursor tro = Getdata("SELECT * FROM " + CreateDatabase.tbl_TAIKHOAN + " WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = '" + IDTAIKHOAN + "'");
        while (tro.moveToNext()){
            return true;
        }
        return false;
    }

    public TaiKhoan Load(int IDTK)
    {
        Cursor cursor = Getdata("SELECT * FROM TAIKHOAN WHERE IDTAIKHOAN = " + IDTK );
        while (cursor.moveToNext()) {
            return new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7),
                    cursor.getBlob(8)
            );
        }
        return null;

    }

    public void INSERT_GOPY(String tentaikhoan, int sdt, String noidung){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO GOPY VALUES(null,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, tentaikhoan);
        statement.bindDouble(2, sdt);
        statement.bindString(3, noidung);

        statement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
