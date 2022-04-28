package com.example.bookshop.ActivityUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookshop.Adapter.CTHoaDonAdapter;
import com.example.bookshop.Adapter.HoaDonAdapter;
import com.example.bookshop.Models.CTHoaDon;
import com.example.bookshop.Models.HoaDon;
import com.example.bookshop.Data.Database;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.R;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ChiTietLichSu extends AppCompatActivity {

    ListView Listview_Lichsu;
    ImageView ibtnExit_lichsu,imageHinhlichsu_HD;
    TextView textviewTongTien_HD,textviewdc_HD,textviewgc_HD,tienship_lichsu,htvc_lichsu;
    ArrayList<CTHoaDon> cthoaDonArrayList;
    CTHoaDonAdapter adapter;
    StateProgressBar stateProgressBar;

    String[] descriptionData = {"\nChờ\nduyệt", "\nChờ\nlấy hàng", "\nĐang\ngiao", "\nĐã\ngiao", "\nĐã\nnhận hàng"};

    int idcthd,KEYhd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_lich_su);
        TrangChuFragment.database = new Database(ChiTietLichSu.this,"BookShop",null,2);

        Intent intent = getIntent();
        idcthd = intent.getIntExtra("idcthd",1123);
        KEYhd = intent.getIntExtra("KEYHD",123);
//        Toast.makeText(ChiTietLichSu.this, "ssss : " + idcthd, Toast.LENGTH_SHORT).show();

        AnhXa();
        Listview_Lichsu = (ListView) findViewById(R.id.listview_danhsachchitiethoadon_lichsu);

        cthoaDonArrayList = new ArrayList<>();
        adapter = new CTHoaDonAdapter(ChiTietLichSu.this, R.layout.danhsachchitietlichsu, cthoaDonArrayList);
        Listview_Lichsu.setAdapter(adapter);
        registerForContextMenu(Listview_Lichsu);

        GetData();
    }
    private void AnhXa() {
        tienship_lichsu = findViewById(R.id.tienship_lichsu);
        htvc_lichsu = findViewById(R.id.htvc_lichsu);
        textviewgc_HD = findViewById(R.id.textviewgc_HD);
        textviewdc_HD = findViewById(R.id.textviewdc_HD);
        textviewTongTien_HD = findViewById(R.id.textviewTongTien_HD);
        imageHinhlichsu_HD = findViewById(R.id.imageHinhlichsu_HD);
        ibtnExit_lichsu = findViewById(R.id.ibtnExit_lichsu);
        stateProgressBar = (StateProgressBar) findViewById(R.id.stapro_menu);
        stateProgressBar.setStateDescriptionData(descriptionData);
        stateProgressBar.setStateDescriptionSize(12f);

        ibtnExit_lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void GetData() {
        //get data
        HoaDon hoaDon = HoaDonAdapter.ListHoaDon.get(KEYhd);
        textviewgc_HD.setText("Ghi chú : " + hoaDon.getGHICHU());
        textviewdc_HD.setText("Địa chỉ : " + hoaDon.getDIACHI());
        tienship_lichsu.setText("Tiền Ship: " + hoaDon.getTIENSHIP());
        if (hoaDon.getHTVC()==1)
        {
            htvc_lichsu.setText("Giao hàng tiết kiệm");
        }
        else {
            htvc_lichsu.setText("Giao hàng nhanh ");
        }
        textviewTongTien_HD.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(hoaDon.getTONGTIEN())) + " VNĐ");

        switch (hoaDon.getTINHTRANG()) {
            case 1:
//                btn_Huydonhang.setEnabled(true);
//                btn_Huydonhang.setBackgroundTintList(ContextCompat.getColorStateList(TransactionHistoryDetailsActivity.this, R.color.red));
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                break;
            case 2:
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                break;
            case 3:
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                break;
            case 4:
//                btn_Danhanduochang.setEnabled(true);
//                btn_Danhanduochang.setBackgroundTintList(ContextCompat.getColorStateList(TransactionHistoryDetailsActivity.this, R.color.red));
               stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                break;
            case 5:
//                btn_Danhgiasanpham.setEnabled(true);
//                btn_Danhgiasanpham.setBackgroundTintList(ContextCompat.getColorStateList(TransactionHistoryDetailsActivity.this, R.color.red));
                stateProgressBar.setAllStatesCompleted(true);
                break;
            case 6:
                stateProgressBar.setAllStatesCompleted(true);
                break;
            default:
                break;

        }

        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM CHITIETHOADON WHERE IDCTHOADON = " + idcthd);
        cthoaDonArrayList.clear();
        while (cursor.moveToNext())
        {
            cthoaDonArrayList.add(new CTHoaDon(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5)
            ));
        }
        adapter.notifyDataSetChanged();
    }
}