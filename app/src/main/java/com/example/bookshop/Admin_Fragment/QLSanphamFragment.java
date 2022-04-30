package com.example.bookshop.Admin_Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bookshop.ActivityAdmin.QL_SuaSanPham;
import com.example.bookshop.Adapter.CategoryAdapter;
import com.example.bookshop.Adapter.SanPhamAdminAdapter;
import com.example.bookshop.Models.Category;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.example.bookshop.R;

import java.util.ArrayList;

public class QLSanphamFragment extends Fragment {

    private View view;
    ArrayList<Category> listCategory;
    Spinner spnTheloai;
    GridView gridView_SanPham;
    ArrayList<SanPham> sanPhamArrayList;
    SanPhamAdminAdapter adapter;
    CategoryAdapter categoryAdapter;
    int Danhmuc;
    String sql;
    public QLSanphamFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_q_l_sanpham, container, false);
        gridView_SanPham = (GridView) view.findViewById(R.id.gridviewQLSanPham);
        Anhxa();
        listCategory = getListCategory();
        categoryAdapter = new CategoryAdapter(getActivity(), R.layout.item_select, listCategory);
        spnTheloai.setAdapter(categoryAdapter);
        Danhmuc = 0;
        spnTheloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Danhmuc = categoryAdapter.getItem(position).getIDcategory();
                GetData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sanPhamArrayList = new ArrayList<>();
        adapter = new SanPhamAdminAdapter(QLSanphamFragment.this, R.layout.product_sanpham_admin, sanPhamArrayList);
        gridView_SanPham.setAdapter(adapter);
        gridView_SanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), QL_SuaSanPham.class);


                intent.putExtra("id",i);

                startActivity(intent);

            }
        });
        registerForContextMenu(gridView_SanPham);

        GetData();
        return view;
    }

    private void Anhxa() {
        spnTheloai = view.findViewById(R.id.spnAddTheloai);
    }

    @Override
    public void onStart() {
        GetData();
        super.onStart();
    }
    private void GetData() {
        if(Danhmuc==0)
        {
            sql="SELECT * FROM SANPHAM";
        }
        else {
            sql =" SELECT * FROM SANPHAM WHERE IDDANHMUC = " + Danhmuc;
        }

        Cursor cursor = TrangChuFragment.database.Getdata(sql);
        sanPhamArrayList.clear();
        while (cursor.moveToNext())
        {
            sanPhamArrayList.add(new SanPham(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(6),
                    cursor.getInt(7)
            ));
        }
        adapter.notifyDataSetChanged();
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
                SanPham sanPham = SanPhamAdminAdapter.sanPhamList.get(info.position);
                TrangChuFragment.database.DELETE_SANPHAM(
                        sanPham.getMaSP()
                );

                Toast.makeText(getActivity(),"Xóa thành công",Toast.LENGTH_LONG).show();
                GetData();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    private ArrayList<Category> getListCategory() {
        ArrayList<Category> list = new ArrayList<>();

        list.add(new Category("Python",1));
        list.add(new Category("C#",2));
        list.add(new Category("Java",3));
        list.add(new Category("Web", 4));
        list.add(new Category("Android", 5));

        return list;
    }
}