package com.example.bookshop.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.R;

import java.util.ArrayList;
import java.util.List;

public class TieudeRe_Adapter extends RecyclerView.Adapter<TieudeRe_Adapter.TieudeRe_ViewHolder> {

    Context mContext;
    private List<String> stringList;

    public TieudeRe_Adapter(Context mContext, List<String> stringList) {
        this.mContext = mContext;
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public TieudeRe_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tieudetrangchu_layout, parent, false);
        return new TieudeRe_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TieudeRe_ViewHolder holder, int position) {
        String string = stringList.get(position);
        Log.e(" thong tin ", string);
        holder.tieude_trangchu_sp.setText(string);

        List<SanPham> sanPhamList = new ArrayList<>();

        switch (string) {
            case "Sản phẩm mới":
                sanPhamList = GetDatanew();
                break;
            case "Python":
                sanPhamList = GetData(1);
                break;
            case "C#":
                sanPhamList = GetData(2);
                break;
            case "Java":
                sanPhamList = GetData(3);
                break;
            case "Web":
                sanPhamList = GetData(4);
                break;
            case "Android":
                sanPhamList = GetData(5);
                break;
        }
        Log.e(" thong tin ", sanPhamList.size() + "");
        SanPhamRe_Adapter sanPhamRe_adapter = new SanPhamRe_Adapter(sanPhamList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.RecyclerView_trangchu.setAdapter(sanPhamRe_adapter);
        holder.RecyclerView_trangchu.setLayoutManager(linearLayoutManager);

    }

    @Override
    public int getItemCount() {
        if (stringList != null) {
            return stringList.size();
        }
        return 0;
    }


    private List<SanPham> GetDatanew() {
        List<SanPham> sanPhamList = new ArrayList<>();
        //get data
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM SANPHAM WHERE SPNEW = 1");

        while (cursor.moveToNext()) {
            sanPhamList.add(new SanPham(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    true
            ));
        }
        return sanPhamList;
    }
    private List<SanPham> GetData(int id) {
        List<SanPham> sanPhamList = new ArrayList<>();
        sanPhamList.clear();
        //get data
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM SANPHAM WHERE IDDANHMUC = " + id );

        while (cursor.moveToNext()) {
            sanPhamList.add(new SanPham(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5)
            ));
        }
        return sanPhamList;
    }

    public class TieudeRe_ViewHolder extends RecyclerView.ViewHolder {

        TextView tieude_trangchu_sp;
        RecyclerView RecyclerView_trangchu;

        public TieudeRe_ViewHolder(@NonNull View itemView) {
            super(itemView);

            tieude_trangchu_sp = itemView.findViewById(R.id.tieude_trangchu_sp);
            RecyclerView_trangchu = itemView.findViewById(R.id.RecyclerView_trangchu);
        }
    }
}
