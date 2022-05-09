package com.example.bookshop.Admin_Fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.Data.Database;
import com.example.bookshop.Models.ThongKeThang;
import com.example.bookshop.R;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class BieuDoFragment extends Fragment {

    View view;
    private BarChart chart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labelNames;
    ArrayList<ThongKeThang> thongKeThang = new ArrayList<>();
    Database database;
    TextView txt_Namchon;
    Button btn_Namchon;
    ImageButton ibtnExitdathang;
    int thang1,thang2,thang3,thang4,thang5,thang6,thang7,thang8,thang9,thang10,thang11,thang12;
    String strNam;

    public BieuDoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bieu_do, container, false);

        chart = view.findViewById(R.id.chart1);
        txt_Namchon = view.findViewById(R.id.txt_Namchon);
        btn_Namchon = view.findViewById(R.id.btn_Namchon);

        btn_Namchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar today = Calendar.getInstance();
                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(getActivity(),
                        new MonthPickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(int selectedMonth, int selectedYear) { // on date set
                                txt_Namchon.setText(String.valueOf(selectedYear));
                                strNam = txt_Namchon.getText().toString();
                                Toast.makeText(getActivity(), strNam, Toast.LENGTH_SHORT).show();
                                Tongdoanhthu_thang1(strNam);
                                Tongdoanhthu_thang2(strNam);
                                Tongdoanhthu_thang3(strNam);
                                Tongdoanhthu_thang4(strNam);
                                Tongdoanhthu_thang5(strNam);
                                Tongdoanhthu_thang6(strNam);
                                Tongdoanhthu_thang7(strNam);
                                Tongdoanhthu_thang8(strNam);
                                Tongdoanhthu_thang9(strNam);
                                Tongdoanhthu_thang10(strNam);
                                Tongdoanhthu_thang11(strNam);
                                Tongdoanhthu_thang12(strNam);

                                getMonthSales();
                                barEntryArrayList = new ArrayList<>();
                                labelNames = new ArrayList<>();
                                barEntryArrayList.clear();
                                labelNames.clear();
                                for (int i = 0; i < thongKeThang.size(); i++) {
                                    String month = thongKeThang.get(i).getMonth();
                                    int sales = thongKeThang.get(i).getSales();
                                    barEntryArrayList.add(new BarEntry(i, sales));
                                    labelNames.add(month);
                                }

                                BarDataSet barDataSet = new BarDataSet(barEntryArrayList, "Thống kê theo tháng");
                                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                                Description description = new Description();
                                description.setText("Tháng");
                                chart.setDescription(description);
                                BarData barData = new BarData(barDataSet);
                                chart.setData(barData);

                                XAxis xAxis = chart.getXAxis();
                                xAxis.setValueFormatter(new IndexAxisValueFormatter(labelNames));

                                xAxis.setPosition(XAxis.XAxisPosition.TOP);
                                xAxis.setDrawGridLines(false);
                                xAxis.setDrawAxisLine(false);
                                xAxis.setGranularity(1f);
                                xAxis.setLabelCount(labelNames.size());
                                xAxis.setLabelRotationAngle(270);
                                chart.animateY(2000);
                                chart.invalidate();
                            }

                        }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));

                builder.setActivatedMonth(Calendar.JULY)
                        .setMinYear(1990)
                        .setActivatedYear(today.get(Calendar.YEAR))
                        .setMaxYear(2030)
                        .setTitle("Select year")
                        .showYearOnly()
                        .build().show();
            }
        });

        return view;
    }

    private void Tongdoanhthu_thang1(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '01/"+"" + Nam + "'");
        cursor.moveToNext();
        thang1 = cursor.getInt(0);
    }

    private void Tongdoanhthu_thang2(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '02/"+"" + Nam + "'");
        cursor.moveToNext();
        thang2 = cursor.getInt(0);
    }

    private void Tongdoanhthu_thang3(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '03/"+"" + Nam + "'");
        cursor.moveToNext();
        thang3 = cursor.getInt(0);
    }

    private void Tongdoanhthu_thang4(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '04/"+"" + Nam + "'");
        cursor.moveToNext();
        thang4 = cursor.getInt(0);
    }

    private void Tongdoanhthu_thang5(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '05/"+"" + Nam + "'");
        cursor.moveToNext();
        thang5 = cursor.getInt(0);
    }

    private void Tongdoanhthu_thang6(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '06/"+"" + Nam + "'");
        cursor.moveToNext();
        thang6 = cursor.getInt(0);
    }

    private void Tongdoanhthu_thang7(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '07/"+"" + Nam + "'");
        cursor.moveToNext();
        thang7 = cursor.getInt(0);
    }

    private void Tongdoanhthu_thang8(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '08/"+"" + Nam + "'");
        cursor.moveToNext();
        thang8 = cursor.getInt(0);
    }

    private void Tongdoanhthu_thang9(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '09/"+"" + Nam + "'");
        cursor.moveToNext();
        thang9 = cursor.getInt(0);
    }

    private void Tongdoanhthu_thang10(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '10/"+"" + Nam + "'");
        cursor.moveToNext();
        thang10 = cursor.getInt(0);
    }

    private void Tongdoanhthu_thang11(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '11/"+"" + Nam + "'");
        cursor.moveToNext();
        thang11 = cursor.getInt(0);
    }

    private void Tongdoanhthu_thang12(String Nam) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( TONGTIEN ) FROM HOADON WHERE TINHTRANG = 6  AND THANGDAT = '12/"+"" + Nam + "'");
        cursor.moveToNext();
        thang12 = cursor.getInt(0);
    }


    private void getMonthSales(){
        thongKeThang.clear();
        thongKeThang.add(new ThongKeThang("1", thang1));
        thongKeThang.add(new ThongKeThang("2", thang2));
        thongKeThang.add(new ThongKeThang("3", thang3));
        thongKeThang.add(new ThongKeThang("4", thang4));
        thongKeThang.add(new ThongKeThang("5", thang5));
        thongKeThang.add(new ThongKeThang("6", thang6));
        thongKeThang.add(new ThongKeThang("7", thang7));
        thongKeThang.add(new ThongKeThang("8", thang8));
        thongKeThang.add(new ThongKeThang("9", thang9));
        thongKeThang.add(new ThongKeThang("10", thang10));
        thongKeThang.add(new ThongKeThang("11", thang11));
        thongKeThang.add(new ThongKeThang("12", thang12));
    }
}