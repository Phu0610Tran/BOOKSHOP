package com.example.bookshop.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.bookshop.Models.BinhLuan;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.Models.TaiKhoan;

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
    public void DELETE_DOAN(int IDSP, int IDTK){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE  FROM GIOHANG WHERE IDSP = "+ IDSP + " AND IDTK= " + IDTK  ;
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();


        statement.executeInsert();
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
    public void ThemBL(int IDTK, int IDSP, String NOIDUNG, String THOIGIAN){
        QueryData("INSERT INTO BINHLUAN (IDTK,IDSP,NOIDUNG,THOIGIAN) VALUES (" +IDTK + ",'" +
                IDSP + "','" + NOIDUNG+"','" + THOIGIAN + "')");
    }
    public ArrayList<BinhLuan> LayBinhLuan(int IDSP){
        ArrayList<BinhLuan> list = new ArrayList<>();
        Cursor tro = Getdata("SELECT A.IDTK,B.HINHANH,A.NOIDUNG,A.THOIGIAN FROM BINHLUAN A,TAIKHOAN B WHERE A.IDTK = B.IDTAIKHOAN AND A.IDSP ='" + IDSP +"'");
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

    public void INSERT_HOADON(int TONGTIEN, int IDCTHOADON, String GHICHU, String DIACHI, int IDTK)
    {
        QueryData("INSERT INTO " + CreateDatabase.tbl_HOADON +
                " ( "
                + CreateDatabase.tbl_HOADON_TONGTIEN + " , "
                + CreateDatabase.tbl_HOADON_IDCTHOADON + " , "
                + CreateDatabase.tbl_HOADON_GHICHU+ " , "
                + CreateDatabase.tbl_HOADON_DIACHI + " , "
                + CreateDatabase.tbl_HOADON_IDTAIKHOAN +
                " ) VALUES ( " + TONGTIEN + " , " + IDCTHOADON + " , '" + GHICHU + "' , '" + DIACHI + "' , " + IDTK + " ) ");
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
