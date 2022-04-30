package com.example.bookshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.Models.CTHoaDon;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.R;
import com.example.bookshop.User_Activity.DanhGiaActivity;
import com.example.bookshop.User_Activity.LoginActivity;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DanhGiaAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    public static List<CTHoaDon> ListCTHoaDon;
    int id;



    public DanhGiaAdapter(Context context, int layout, List<CTHoaDon> ListCTHoaDon) {
        this.context = context;
        this.layout = layout;
        this.ListCTHoaDon = ListCTHoaDon;
    }


    @Override
    public int getCount() {
        return ListCTHoaDon.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder{
        TextView txtTenSanPham,txtsoluong,txtthanhtien;
        ImageView img_HinhAnh;
        Button btn_danhgia;

    }
    @Override
    public View getView(int i, View view, ViewGroup parent) {

        DanhGiaAdapter.ViewHolder holder;

        if (view == null){
            holder = new DanhGiaAdapter.ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTenSanPham = (TextView) view.findViewById(R.id.textviewTen_CTlichsu);
            holder.txtsoluong = (TextView) view.findViewById(R.id.textviewsoluong_CTlichsu);
            holder.txtthanhtien = (TextView) view.findViewById(R.id.textviewthanhtien_CTlichsu);
            holder.img_HinhAnh = (ImageView) view.findViewById(R.id.imageHinhChiTietLichSu);
            holder.btn_danhgia = view.findViewById(R.id.btn_danhgia);
            view.setTag(holder);
        } else {
            holder = (DanhGiaAdapter.ViewHolder) view.getTag();
        }

        CTHoaDon cthoaDon = ListCTHoaDon.get(i);

        holder.txtTenSanPham.setText(cthoaDon.getTENSANPHAM());
        holder.txtsoluong.setText("Số lượng : " + String.valueOf(cthoaDon.getSOLUONG()));
        holder.txtthanhtien.setText("Thành tiền : " + String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(cthoaDon.getTHANHTIEN())) + " VNĐ");
        id = cthoaDon.getIDCTHOADON();

        SanPham sanPham = TrangChuFragment.database.SANPHAM(cthoaDon.getIDSANPHAM());
        // chuyen byte[] -> ve bitmap
        byte[] hinhAnh = sanPham.getImageSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
        holder.img_HinhAnh.setImageBitmap(bitmap);

        if (TrangChuFragment.database.KiemTraDanhGia(
                LoginActivity.taiKhoan.getMATK(),sanPham.getMaSP(),cthoaDon.getIDCTHOADON())){
            holder.btn_danhgia.setEnabled(true);
            holder.btn_danhgia.setBackgroundColor(holder.btn_danhgia.getContext().getResources().getColor(R.color.red));

        }else
        {
            holder.btn_danhgia.setEnabled(false);
            holder.btn_danhgia.setBackgroundColor(holder.btn_danhgia.getContext().getResources().getColor(R.color.xamden));



        }

        holder.btn_danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(context.getApplicationContext()).inflate(
                        R.layout.dialog_danhgia, view.findViewById(R.id.dialog_danhgia)
                );
                ImageView hinhanh_danhgia = bottomSheetView.findViewById(R.id.hinhanh_danhgia);
                TextView textviewTen = bottomSheetView.findViewById(R.id.tenspdanhgia);
                TextView soluong_danhgia = bottomSheetView.findViewById(R.id.soluong_danhgia);
                TextView thanhtien_spdanhgia = bottomSheetView.findViewById(R.id.thanhtien_spdanhgia);
                TextView btn_xacnhandanhgia = bottomSheetView.findViewById(R.id.btn_xacnhandanhgia);
                RatingBar RatingBar = bottomSheetView.findViewById(R.id.RatingBar);
                EditText noidungdanhgia_sp = bottomSheetView.findViewById(R.id.noidungdanhgia_sp);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());
                hinhanh_danhgia.setImageBitmap(bitmap);
                textviewTen.setText(cthoaDon.getTENSANPHAM());
                soluong_danhgia.setText(String.valueOf(cthoaDon.getSOLUONG()));

                thanhtien_spdanhgia.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(cthoaDon.getTHANHTIEN())) + " VNĐ");
                btn_xacnhandanhgia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TrangChuFragment.database.ThemDanhGia(
                                LoginActivity.taiKhoan.getMATK(),
                                sanPham.getMaSP(),
                                noidungdanhgia_sp.getText().toString(),
                                currentDateandTime,
                                RatingBar.getRating(),
                                cthoaDon.getIDCTHOADON());

                        Toast.makeText(context, " Đánh giá thành công ! ", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.cancel();
                        holder.btn_danhgia.setEnabled(false);
                        holder.btn_danhgia.setBackgroundColor(holder.btn_danhgia.getContext().getResources().getColor(R.color.xamden));

                    }
                });



                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });



        return view;
    }

}
