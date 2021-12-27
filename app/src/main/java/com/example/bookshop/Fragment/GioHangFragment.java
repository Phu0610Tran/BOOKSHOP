package com.example.bookshop.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.bookshop.Adapter.GioHangAdapter;
import com.example.bookshop.Models.GioHang;
import com.example.bookshop.Data.Database;
import com.example.bookshop.ActivityUser.HomeActivity;
import com.example.bookshop.ActivityUser.LoginActivity;
import com.example.bookshop.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class GioHangFragment extends Fragment {

    private View view;
    ListView Listview_SanPham;
    ArrayList<GioHang> sanPhamArrayList;
    GioHangAdapter adapter;
    Button btn_tieptuc,btn_thanhtoan;
    TextView txtthongbao,tongthanhtien;
    int tong;
    int idcthd = 0;

    public GioHangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        TrangChuFragment.database = new Database(getActivity(),"BookShop",null,2);
//        database.QueryData("CREATE TABLE IF NOT EXISTS DoAn(Id INTEGER PRIMARY KEY AUTOINCREMENT" +
//                ", Ten VARCHAR(150), MoTa VARCHAR(250), HinhAnh BLOB)");
        AnhXa();
        Listview_SanPham = (ListView) view.findViewById(R.id.listview_danhsachsp_gohang);

        sanPhamArrayList = new ArrayList<>();
        adapter = new GioHangAdapter(GioHangFragment.this, R.layout.products_giohang, sanPhamArrayList);
        Listview_SanPham.setAdapter(adapter);
        registerForContextMenu(Listview_SanPham);

        GetData();

        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
        btn_tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),HomeActivity.class));
            }
        });





        return view;
    }

    @Override
    public void onStart() {
        Tongtien();

        super.onStart();
    }

    private void Tongtien() {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( THANHTIEN ) FROM GIOHANG WHERE IDTK = "
                + LoginActivity.taiKhoan.getMATK());
        cursor.moveToNext();
        tong = cursor.getInt(0);
        tongthanhtien.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(tong) + " VNĐ"));
    }

    private void AnhXa() {

        txtthongbao = (TextView) view.findViewById(R.id.thongbaogiohang);
        tongthanhtien = (TextView) view.findViewById(R.id.tongthanhtien);
        btn_tieptuc = (Button) view.findViewById(R.id.tieptucmuahang);
        btn_thanhtoan = (Button) view.findViewById(R.id.thanhtoan_giohang);


    }

    private void GetData() {
        //get data

        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM GIOHANG WHERE IDTK = " + LoginActivity.taiKhoan.getMATK());
        sanPhamArrayList.clear();
        while (cursor.moveToNext())
        {
            sanPhamArrayList.add(new GioHang(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
            ));
        }
        adapter.notifyDataSetChanged();


        if (LoginActivity.taiKhoan.getMATK() == -1)
        {
            txtthongbao.setText(" Bạn hãy đăng nhập để có thể mua hàng !");
        }else if (sanPhamArrayList.isEmpty()){
            txtthongbao.setText(" Bạn chưa mua hàng !");
        }
    }
    private void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thanhtoan,null);
        final EditText diachi = view.findViewById(R.id.diachi_thanhtoan);
        final EditText ghichu= view.findViewById(R.id.ghichu_thanhtoan);
        diachi.setText(LoginActivity.taiKhoan.getDIACHI());
        builder.setView(view);
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(TrangChuFragment.database.HoaDonChuaCoTrongHD()){
                    idcthd = 1;
                }
                else {
                    Cursor cursor = TrangChuFragment.database.Getdata("SELECT IDCTHOADON FROM CHITIETHOADON ORDER BY IDCTHOADON DESC");
                    cursor.moveToNext();
                    idcthd = cursor.getInt(0) + 1;
                }



                for (int position = 0; position<GioHangAdapter.sanPhamGioHangList.size();position++)
                {
                    GioHang themhoadon = GioHangAdapter.sanPhamGioHangList.get(position);
                    TrangChuFragment.database.INSERT_CTHOADON(idcthd, themhoadon.getIDTK(), themhoadon.getIDSP(), themhoadon.getTENSANPHAM(),
                            themhoadon.getSOLUONG(), themhoadon.getTHANHTIEN());
                    TrangChuFragment.database.UPDATE_SOLUONG(themhoadon.getIDSP(),themhoadon.getSOLUONG());

                }
                TrangChuFragment.database.INSERT_HOADON(tong,idcthd,diachi.getText().toString(),ghichu.getText().toString(),LoginActivity.taiKhoan.getMATK());
                TrangChuFragment.database.DELETE_GIOHANG(LoginActivity.taiKhoan.getMATK());
                GetData();
                Tongtien();
                Toast.makeText(getActivity(),"Thanh toán thành công",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(),HomeActivity.class));

            }
        }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_content, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.menu_delete_item:

                GioHang gioHang = GioHangAdapter.sanPhamGioHangList.get(info.position);
                TrangChuFragment.database.DELETE_DOAN(
                        gioHang.getIDSP(),
                        gioHang.getIDTK()
                );

                Toast.makeText(getActivity(),"Xóa thành công",Toast.LENGTH_LONG).show();
                GetData();
                Tongtien();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}