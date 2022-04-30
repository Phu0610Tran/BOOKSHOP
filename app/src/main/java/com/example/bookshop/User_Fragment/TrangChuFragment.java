package com.example.bookshop.User_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.Adapter.SanPhamAdapter;
import com.example.bookshop.Adapter.TieudeRe_Adapter;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.Data.Database;
import com.example.bookshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class TrangChuFragment extends Fragment {

    private View view;
    ViewFlipper viewFlipper;
    public static Database database;
    GridView gridView_SanPham;
    RecyclerView recyclerView;
    ArrayList<SanPham> sanPhamArrayList;
    SanPhamAdapter adapter;
    List<String> stringList;
    RecyclerView Re_trangchu;


    public TrangChuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        database = new Database(getActivity(), "BookShop", null, 2);
//        database.QueryData("CREATE TABLE IF NOT EXISTS DoAn(Id INTEGER PRIMARY KEY AUTOINCREMENT" +
//                ", Ten VARCHAR(150), MoTa VARCHAR(250), HinhAnh BLOB)");
        Anhxa();
        ActionViewFlipper();
        stringList = new ArrayList<>();
        stringList.add("Sản phẩm mới");
        stringList.add("Python");
        stringList.add("C#");
        stringList.add("Java");
        stringList.add("Web");
        stringList.add("Android");

        TieudeRe_Adapter tieudeRe_adapter = new TieudeRe_Adapter(getContext(), stringList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        Re_trangchu.setAdapter(tieudeRe_adapter);
        Re_trangchu.setLayoutManager(linearLayoutManager);






        return view;
    }

    private void Anhxa() {
        Re_trangchu = view.findViewById(R.id.Re_trangchu);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlippermanhinhchinh);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://image.freepik.com/free-vector/flat-world-book-day-illustration_23-2148485297.jpg");
        mangquangcao.add("https://image.freepik.com/free-psd/beautiful-book-cover-mockup_23-2149152257.jpg");
        mangquangcao.add("https://image.freepik.com/free-psd/high-angle-open-tale-book_23-2149160145.jpg");
        mangquangcao.add("https://image.freepik.com/free-psd/book-hardcover-mockup-three-views_125540-226.jpg");

        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            Picasso.with(getActivity()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    @Override
    public void onStart() {

        super.onStart();
    }


}