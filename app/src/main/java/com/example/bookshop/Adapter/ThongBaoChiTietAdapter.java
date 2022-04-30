package com.example.bookshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bookshop.User_Activity.BinhLuanActivity;
import com.example.bookshop.User_Activity.LoginActivity;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.example.bookshop.Models.ThongBao;
import com.example.bookshop.R;

import java.util.List;

public class ThongBaoChiTietAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    public static List<ThongBao> thongBaoList;

    public ThongBaoChiTietAdapter(Context context, int layout, List<ThongBao> thongBaoList) {
        this.context = context;
        this.layout = layout;
        this.thongBaoList = thongBaoList;
    }

    @Override
    public int getCount() {
        return thongBaoList.size();
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
        boolean isdisLike, isLike;
        ImageView avata_img, img_thongbao, img_like_thongbao, img_dislike_thongbao;
        LinearLayout layout1,layout2;
        TextView tentk_thongbao, gio_thongbao, noidung_thongbao, txt_luotlike_thongbao, txt_luotdislike_thongbao, tieude_thongbao;
        LinearLayout binhluan;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ThongBaoChiTietAdapter.ViewHolder holder;
        if (view == null){
            holder = new ThongBaoChiTietAdapter.ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tentk_thongbao = (TextView) view.findViewById(R.id.tentk_bantin);
            holder.gio_thongbao = (TextView) view.findViewById(R.id.gio_bantin);
            holder.noidung_thongbao = (TextView) view.findViewById(R.id.noidung_bantin);
            holder.txt_luotlike_thongbao = (TextView) view.findViewById(R.id.txt_luotlike_bantin);
            holder.txt_luotdislike_thongbao = (TextView) view.findViewById(R.id.txt_luotdislike_bantin);
            holder.avata_img = (ImageView) view.findViewById(R.id.avata_img);
            holder.img_thongbao = (ImageView) view.findViewById(R.id.img_bangtin);
            holder.img_like_thongbao = (ImageView) view.findViewById(R.id.img_like_bantin);
            holder.layout1 = (LinearLayout) view.findViewById(R.id.layout1);
            holder.layout2 = (LinearLayout) view.findViewById(R.id.layout2);
            holder.img_dislike_thongbao = (ImageView) view.findViewById(R.id.img_dislike_bantin);
            holder.binhluan = (LinearLayout)view.findViewById(R.id.binhluan);
            holder.tieude_thongbao = (TextView) view.findViewById(R.id.tieude_bantin);
            view.setTag(holder);
        } else {
            holder = (ThongBaoChiTietAdapter.ViewHolder) view.getTag();
        }

        ThongBao thongBao = thongBaoList.get(i);
        // load like
        holder.img_like_thongbao.setImageResource(R.drawable.ic_thumb_up_black_48dp);
        holder.img_dislike_thongbao.setImageResource(R.drawable.ic_thumb_down_off_alt_black_48dp);

        if (TrangChuFragment.database.isDaLike(thongBao.getIDTB(), LoginActivity.taiKhoan.getMATK()) == 1){
            holder.isLike = true;
            holder.isdisLike = false;
            holder.img_like_thongbao.setImageResource(R.drawable.like);
        } else if(TrangChuFragment.database.isDaLike(thongBao.getIDTB(),LoginActivity.taiKhoan.getMATK()) == 2){
            holder.isdisLike = true;
            holder.isLike = false;
            holder.img_dislike_thongbao.setImageResource(R.drawable.dislike);
        }
        // sự kiện like và không like

        holder.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.isLike == true){
                    holder.img_like_thongbao.setImageResource(R.drawable.ic_thumb_up_black_48dp);
                    TrangChuFragment.database.xoathem(thongBao.getIDTB(),LoginActivity.taiKhoan.getMATK(), 1);
                    holder.txt_luotlike_thongbao.setText(String.valueOf(Integer.valueOf(holder.txt_luotlike_thongbao.getText().toString())-1));
                }else {
                    if(holder.isdisLike == true){
                        holder.img_dislike_thongbao.setImageResource(R.drawable.ic_thumb_down_off_alt_black_48dp);
                        TrangChuFragment.database.xoathem(thongBao.getIDTB(),LoginActivity.taiKhoan.getMATK(), 2);
                        holder.txt_luotdislike_thongbao.setText(String.valueOf(Integer.valueOf(holder.txt_luotdislike_thongbao.getText().toString())-1));
                        holder.isdisLike = !holder.isdisLike;
                    }
                    holder.img_like_thongbao.setImageResource(R.drawable.like);
                    TrangChuFragment.database.them(thongBao.getIDTB(),LoginActivity.taiKhoan.getMATK(), 1);
                    holder.txt_luotlike_thongbao.setText(String.valueOf(Integer.valueOf(holder.txt_luotlike_thongbao.getText().toString())+1));
                }
                holder.isLike = !holder.isLike;
            }
        });
        holder.layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.isdisLike == true){

                    holder.img_dislike_thongbao.setImageResource(R.drawable.ic_thumb_down_off_alt_black_48dp);
                    TrangChuFragment.database.xoathem(thongBao.getIDTB(),LoginActivity.taiKhoan.getMATK(), 2);
                    holder.txt_luotdislike_thongbao.setText(String.valueOf(Integer.valueOf(holder.txt_luotdislike_thongbao.getText().toString())-1));
                }else {
                    if(holder.isLike == true){
                        holder.img_like_thongbao.setImageResource(R.drawable.ic_thumb_up_black_48dp);
                        TrangChuFragment.database.xoathem(thongBao.getIDTB(),LoginActivity.taiKhoan.getMATK(), 1);
                        holder.txt_luotlike_thongbao.setText(String.valueOf(Integer.valueOf(holder.txt_luotlike_thongbao.getText().toString())-1));
                        holder.isLike = !holder.isLike;
                    }
                    holder.img_dislike_thongbao.setImageResource(R.drawable.dislike);
                    TrangChuFragment.database.them(thongBao.getIDTB(),LoginActivity.taiKhoan.getMATK(), 2);
                    holder.txt_luotdislike_thongbao.setText(String.valueOf(Integer.valueOf(holder.txt_luotdislike_thongbao.getText().toString())+1));
                }
                holder.isdisLike = !holder.isdisLike;
            }
        });


        String ten ;
        holder.txt_luotlike_thongbao.setText(String.valueOf(thongBao.getTHICH()));
        holder.txt_luotdislike_thongbao.setText(String.valueOf(thongBao.getKHONGTHICH()));
        holder.tieude_thongbao.setText(thongBao.getTIEUDE());
        holder.gio_thongbao.setText(thongBao.getDATE());
        holder.noidung_thongbao.setText(thongBao.getNOIDUNG());
        // chuyen byte[] -> ve bitmap
        if (thongBao.getHINHANHTHONGBAO()!= null){
            byte[] hinhAnh1 = thongBao.getHINHANHTHONGBAO();
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(hinhAnh1,0, hinhAnh1.length);
            holder.img_thongbao.setImageBitmap(bitmap1);
        }


        holder.binhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BinhLuanActivity.class);
                intent.putExtra("position",i);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
