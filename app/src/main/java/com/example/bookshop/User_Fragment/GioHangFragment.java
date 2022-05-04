package com.example.bookshop.User_Fragment;

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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bookshop.Adapter.CategoryAdapter;
import com.example.bookshop.Adapter.GioHangAdapter;
import com.example.bookshop.Models.Category;
import com.example.bookshop.Models.GioHang;
import com.example.bookshop.Data.Database;
import com.example.bookshop.User_Activity.HomeActivity;
import com.example.bookshop.User_Activity.LoginActivity;
import com.example.bookshop.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class GioHangFragment extends Fragment {


    AutoCompleteTextView AutoCompleteTextView;
    ArrayList<Category> listCategory;
    ArrayList<Category> listvanchuyen;
    ArrayList<Category> list;
    ArrayList<Category> listvc;
    CategoryAdapter categoryAdapter;
    CategoryAdapter categoryAdapter1;
    private View view;
    ListView Listview_SanPham;
    ArrayList<GioHang> sanPhamArrayList;
    GioHangAdapter adapter;
    Button btn_tieptuc,btn_thanhtoan;
    TextView txtthongbao,tongthanhtien;
    int tong;
    String tt="null";
    int tongsoluong;
    int idcthd = 0;
    int Voucher = 0;
    double phantram=0;
    TextView saukhuyenmai,truockhuyenmai,tiengiam,tienship;
    public GioHangFragment() {
        // Required empty public constructor
    }
    int tienvanchuyen;
    int idvanchuyen=1;
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

        Events();





        return view;
    }

    private void Events() {
        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(
                        R.layout.dialog_thanhtoan, view.findViewById(R.id.dialog_thanhtoan_ne)
                );

                Button btn_xacnhan_thanhtoan = bottomSheetView.findViewById(R.id.btn_xacnhan_thanhtoan);
                EditText diachi = bottomSheetView.findViewById(R.id.diachi_thanhtoan);
                EditText ghichu = bottomSheetView.findViewById(R.id.ghichu_thanhtoan);
                Spinner spinner_Voucher = bottomSheetView.findViewById(R.id.spinner_Voucher);
                Spinner spinner_Vanchuyen = bottomSheetView.findViewById(R.id.spinner_Vanchuyen);
                saukhuyenmai = bottomSheetView.findViewById(R.id.saukhuyenmai);
                truockhuyenmai = bottomSheetView.findViewById(R.id.truockhuyenmai);
                tiengiam = bottomSheetView.findViewById(R.id.tiengiam);
                tienship = bottomSheetView.findViewById(R.id.tienship);
                tiengiam.setText("0");
//---------------------Spiner Voucher-----------------------
                listCategory = getListCategory();


                categoryAdapter = new CategoryAdapter(getActivity(), R.layout.item_select, listCategory);
                spinner_Voucher.setAdapter(categoryAdapter);

                spinner_Voucher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Voucher = categoryAdapter.getItem(position).getIDcategory();
                        if (Voucher == 0)
                        {

                        }else if (Voucher == 1){
                            phantram = 0.1;
                        }
                        else if (Voucher == 2)
                        {
                            phantram = 0.2;
                        }
                        else if (Voucher == 3)
                        {
                            phantram = 0.3;
                        }

                        NumberFormat formatter = new DecimalFormat("#0");
                        tiengiam.setText(String.valueOf(formatter.format(tong*phantram)));





                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                //-----------------------------tinh thanh----------------
                String[] tinhthanh = getResources().getStringArray(R.array.tinhthanh);
                AutoCompleteTextView = bottomSheetView.findViewById(R.id.AutoCompleteTextView);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,tinhthanh);
                AutoCompleteTextView.setAdapter(adapter);
                //---------------------Spiner VanChuyen-----------------------

                listvanchuyen = getListvc();
                categoryAdapter1 = new CategoryAdapter(getActivity(), R.layout.item_select, listvanchuyen);
                spinner_Vanchuyen.setAdapter(categoryAdapter1);
                spinner_Vanchuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        idvanchuyen = categoryAdapter1.getItem(i).getIDcategory();
                        HamTinhShip();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                truockhuyenmai.setText(String.valueOf(tong));
                diachi.setText(LoginActivity.taiKhoan.getDIACHI());
                btn_xacnhan_thanhtoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(TrangChuFragment.database.HoaDonChuaCoTrongHD()){
                            idcthd = 1;
                        }
                        else {
                            Cursor cursor = TrangChuFragment.database.Getdata("SELECT IDCTHOADON FROM CHITIETHOADON ORDER BY IDCTHOADON DESC");
                            cursor.moveToNext();
                            idcthd = cursor.getInt(0) + 1;
                        }


                        GetData();
                        Tongtien();
                        for (int position = 0; position<GioHangAdapter.sanPhamGioHangList.size();position++)
                        {
                            GioHang themhoadon = GioHangAdapter.sanPhamGioHangList.get(position);
                            TrangChuFragment.database.INSERT_CTHOADON(idcthd, themhoadon.getIDTK(), themhoadon.getIDSP(), themhoadon.getTENSANPHAM(),
                                    themhoadon.getSOLUONG(), themhoadon.getTHANHTIEN());
                            TrangChuFragment.database.UPDATE_SOLUONG(themhoadon.getIDSP(),themhoadon.getSOLUONG());

                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
                        String currentDateandTime = sdf.format(new Date());
                        TrangChuFragment.database.INSERT_HOADON(Double.valueOf(saukhuyenmai.getText().toString()),
                                idcthd,diachi.getText().toString(),ghichu.getText().toString(),LoginActivity.taiKhoan.getMATK(),
                                Integer.valueOf(tienship.getText().toString()),idvanchuyen,currentDateandTime,
                                Integer.valueOf(tiengiam.getText().toString()));
                        TrangChuFragment.database.DELETE_GIOHANG(LoginActivity.taiKhoan.getMATK());
                        TrangChuFragment.database.UPDATE_VOUCHER(LoginActivity.taiKhoan.getMATK(),Voucher);

                        Toast.makeText(getActivity(),"Thanh toán thành công",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getActivity(),HomeActivity.class));
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        btn_tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),HomeActivity.class));
            }
        });
    }

    @Override
    public void onStart() {
        Tongtien();
        Tongsoluong();
        Events();
        super.onStart();
    }

    private void Tongtien() {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( THANHTIEN ) FROM GIOHANG WHERE IDTK = "
                + LoginActivity.taiKhoan.getMATK());
        cursor.moveToNext();
        tong = cursor.getInt(0);
        tongthanhtien.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(tong) + " VNĐ"));
    }
    private void Tongsoluong() {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( SOLUONG ) FROM GIOHANG WHERE IDTK = "
                + LoginActivity.taiKhoan.getMATK());
        cursor.moveToNext();
        tongsoluong = cursor.getInt(0);
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

    private ArrayList<Category> getListCategory() {


        Cursor cursor =  TrangChuFragment.database.Getdata("SELECT VOUCHER FROM VONGCHINH WHERE IDTK = " +
                LoginActivity.taiKhoan.getMATK() + " ORDER BY VOUCHER DESC " );
        list = new ArrayList<>();
        list.clear();
        String namevoucher = null;
        int IDVC;
        while (cursor.moveToNext()){
            if (cursor.getInt(0)==1)
            {
                namevoucher = " Voucher 10% ";
//                IDVC = cursor.getInt(0);
            }
            else if(cursor.getInt(0)==2)
            {
                namevoucher = " Voucher 20% ";
//                IDVC = cursor.getInt(0);
            }
            else if (cursor.getInt(0) == 3)
            {
                namevoucher = " Voucher 30% ";
//                IDVC = cursor.getInt(0);
            }
            else if (cursor.getInt(0) == 0){
                return list;
            }

            list.add(new Category(
                            namevoucher,
                            cursor.getInt(0)
                    )
            );
        }
        list.add(new Category(
                        "Không chọn",
                        0
                )
        );


        return list;
    }
    private ArrayList<Category> getListvc() {
        listvc = new ArrayList<>();
        listvc.clear();
        listvc.add(new Category(
                        "Không chọn",
                        0
                )
        );
        listvc.add(new Category(
                        "Giao hàng tiết kiệm",
                        1
                )
        );
        listvc.add(new Category(
                        "Giao hàng nhanh",
                        2
                )
        );



        return listvc;
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
    public boolean TinhMienBac(String tenmien){
        if (tenmien.equals("TP Hồ Chí Minh")  ||
                tenmien.equals("Bà Rịa – Vũng Tàu") ||
                tenmien.equals("Bình Phước") ||
                tenmien.equals("Đồng Nai" )||
                tenmien.equals("Tây Ninh" )||
                tenmien.equals("An Giang" )||
                tenmien.equals( "Cà Mau" )||
                tenmien.equals("Bạc Liêu" )||
                tenmien.equals("Sóc Trăng" )||
                tenmien.equals("Tiền Giang" )||
                tenmien.equals("Kiên Giang") ||
                tenmien.equals("Bến Tre") ||
                tenmien.equals("Long An") ||
                tenmien.equals("Đồng Tháp") ||
                tenmien.equals("Cần Thơ") ||
                tenmien.equals("Trà Vinh") ||
                tenmien.equals("Vĩnh Long")){
            return false;

        }
        return true;
    }
    public void HamTinhShip()
    {
        tt = AutoCompleteTextView.getText().toString();
        if (tt.equals(""))
        {
            Toast.makeText(getActivity(), "Vui lòng chọn tỉnh thành!", Toast.LENGTH_SHORT).show();
        }else if (idvanchuyen==0)
        {
            tienvanchuyen=0;
        }
        else if(tt.equals("Bình Dương"))
        {
            if (tongsoluong <= 6)
            {
                tienvanchuyen = 16000;
            }
            else if (tongsoluong%2==0)
            {
                tienvanchuyen = 16000 + (tongsoluong-6)*1250;
            }else {
                tienvanchuyen = 16000 + (tongsoluong-7)*1250;
            }
        }
        else if (idvanchuyen == 1)
        {
            if (TinhMienBac(tt))
            {
                if (tongsoluong <= 2)
                {
                    tienvanchuyen = 32000;
                }
                else if (tongsoluong%2==0)
                {
                    tienvanchuyen = 32000 + (tongsoluong-2)*2500;
                }else {
                    tienvanchuyen = 32000 + (tongsoluong-3)*2500;
                }
            }
            else{
                if (tongsoluong <= 2)
                {
                    tienvanchuyen = 30000;
                }
                else if (tongsoluong%2==0)
                {
                    tienvanchuyen = 30000 + (tongsoluong-2)*1250;
                }else {
                    tienvanchuyen = 30000 + (tongsoluong-3)*1250;
                }
            }
        }
        else if(idvanchuyen == 2)// giao nhanh
        {
            if (TinhMienBac(tt))// Liên Miền
            {
                if (tongsoluong <= 2)
                {
                    tienvanchuyen = 35000;
                }
                else if (tongsoluong%2==0)
                {
                    tienvanchuyen = 35000 + (tongsoluong-2)*5000;
                }else {
                    tienvanchuyen = 35000 + (tongsoluong-3)*5000;
                }
            }
            else{ // Nội Miền
                if (tongsoluong <= 2)
                {
                    tienvanchuyen = 30000;
                }
                else if (tongsoluong%2==0)
                {
                    tienvanchuyen = 30000 + (tongsoluong-2)*2500;
                }else {
                    tienvanchuyen = 30000 + (tongsoluong-3)*2500;
                }
            }
        }

        tienship.setText(String.valueOf(tienvanchuyen));

        saukhuyenmai.setText(String.valueOf(tong - Integer.valueOf(tiengiam.getText().toString()) + Integer.valueOf( tienship.getText().toString())));


    }
}