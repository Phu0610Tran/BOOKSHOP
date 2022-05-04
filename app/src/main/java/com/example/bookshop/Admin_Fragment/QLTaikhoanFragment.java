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
import android.widget.Toast;

import com.example.bookshop.AdminActivity.QL_SuaTaiKhoan;
import com.example.bookshop.Adapter.TaiKhoanAdminAdapter;
import com.example.bookshop.Models.TaiKhoan;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.example.bookshop.R;

import java.util.ArrayList;


public class QLTaikhoanFragment extends Fragment {


    View view;

    GridView gridviewQLTaiKhoan;

    ArrayList<TaiKhoan> taiKhoanArrayList;
    TaiKhoanAdminAdapter adapter;

    public QLTaikhoanFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_q_l_taikhoan, container, false);
        gridviewQLTaiKhoan = (GridView) view.findViewById(R.id.gridviewQLTaiKhoan);
        taiKhoanArrayList = new ArrayList<>();



        adapter = new TaiKhoanAdminAdapter(QLTaikhoanFragment.this, R.layout.taikhoan_admin, taiKhoanArrayList);
        gridviewQLTaiKhoan.setAdapter(adapter);
        gridviewQLTaiKhoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), QL_SuaTaiKhoan.class);
                intent.putExtra("id",i);

                startActivity(intent);

            }
        });
        registerForContextMenu(gridviewQLTaiKhoan);

        GetData();
        return view;
    }
    @Override
    public void onStart() {
        GetData();
        super.onStart();
    }

    private void GetData() {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM TAIKHOAN ");
        taiKhoanArrayList.clear();
        while (cursor.moveToNext())
        {
            taiKhoanArrayList.add(new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7),
                    cursor.getBlob(8)
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
                TaiKhoan taiKhoan = TaiKhoanAdminAdapter.taiKhoanList.get(info.position);
                TrangChuFragment.database.DELETE_TAIKHOAN(
                        taiKhoan.getMATK()
                );

                Toast.makeText(getActivity(),"Xóa thành công",Toast.LENGTH_LONG).show();
                GetData();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}