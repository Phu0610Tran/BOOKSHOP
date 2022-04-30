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
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.ActivityAdmin.QL_SuaSanPham;
import com.example.bookshop.Adapter.SanPhamAdminAdapter;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.example.bookshop.R;

import java.util.ArrayList;


public class ThongKeSanPham extends Fragment {

    private View view;
    GridView gridView_SanPham;
    TextView soluongtonkho;
    ArrayList<SanPham> sanPhamArrayList;
    SanPhamAdminAdapter adapter;
    public ThongKeSanPham() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        soluongtonkho = view.findViewById(R.id.soluongtonkho);
        gridView_SanPham = (GridView) view.findViewById(R.id.gridviewQLSanPham);
        sanPhamArrayList = new ArrayList<>();
        adapter = new SanPhamAdminAdapter(ThongKeSanPham.this, R.layout.product_sanpham_admin, sanPhamArrayList);
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
    @Override
    public void onStart() {
        GetData();
        super.onStart();
    }
    private void GetData() {
        Cursor cursor1 = TrangChuFragment.database.Getdata("SELECT SUM ( SOLUONG ) FROM SANPHAM ");
        cursor1.moveToNext();
        soluongtonkho.setText(String.valueOf(cursor1.getInt(0) + " Sản phẩm "));


        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM SANPHAM WHERE SOLUONG < 50 ");
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
}