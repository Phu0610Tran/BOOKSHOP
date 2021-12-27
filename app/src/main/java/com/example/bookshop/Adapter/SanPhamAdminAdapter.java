package com.example.bookshop.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bookshop.Models.SanPham;
import com.example.bookshop.Data.CreateDatabase;
import com.example.bookshop.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SanPhamAdminAdapter extends BaseAdapter {
    SQLiteDatabase database;

    private Fragment context;
    private int layout;
    public static List<SanPham> sanPhamList;
    int id;

    public SanPhamAdminAdapter(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public SanPhamAdminAdapter(Fragment context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.layout = layout;
        this.sanPhamList = sanPhamList;
    }


    @Override
    public int getCount() {
        return sanPhamList.size();
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
        TextView txt_TenSP, txt_GiaSP,txt_SoLuong,txt_DanhMuc,txt_SPnew;
        ImageView img_HinhAnh;
    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {

        SanPhamAdminAdapter.ViewHolder holder;

        if (view == null){
            holder = new SanPhamAdminAdapter.ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txt_TenSP = (TextView) view.findViewById(R.id.product_ten_QLSP);
            holder.txt_GiaSP = (TextView) view.findViewById(R.id.product_Gia_QLSP);
            holder.img_HinhAnh = (ImageView) view.findViewById(R.id.product_image_QLSP);
            holder.txt_SoLuong = (TextView) view.findViewById(R.id.product_SL_QLSP);
            holder.txt_DanhMuc = (TextView) view.findViewById(R.id.product_DANHMUC_QLSP);
            holder.txt_SPnew = (TextView) view.findViewById(R.id.product_TRANGTHAI_QLSP);
            view.setTag(holder);
        } else {
            holder = (SanPhamAdminAdapter.ViewHolder) view.getTag();
        }

        SanPham sanPham = sanPhamList.get(i);
        String gia = String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(sanPham.getGiaSP())) + " VNĐ";
        holder.txt_TenSP.setText(sanPham.getTenSP());
        holder.txt_GiaSP.setText("Giá : "+ gia);
        holder.txt_SoLuong.setText("Số Lượng : " + String.valueOf(sanPham.getSl_SP()));
        holder.txt_DanhMuc.setText("Danh Mục : " +String.valueOf(sanPham.getIDDANHMUC()));
        holder.txt_SPnew.setText("Trạng Thái : " +String.valueOf(sanPham.getSPNEW()));
        id = sanPham.getMaSP();

        // chuyen byte[] -> ve bitmap
        byte[] hinhAnh = sanPham.getImageSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
        holder.img_HinhAnh.setImageBitmap(bitmap);

        return view;
    }

}

