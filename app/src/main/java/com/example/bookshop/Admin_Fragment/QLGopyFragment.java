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

import com.example.bookshop.Adapter.GopY_Adapter;
import com.example.bookshop.Models.GopY;
import com.example.bookshop.R;
import com.example.bookshop.User_Fragment.TrangChuFragment;

import java.util.ArrayList;


public class QLGopyFragment extends Fragment {

    View view;
    GridView gridviewgopy;
    ArrayList<GopY> gopYArrayList;
    GopY_Adapter adapter;
    public QLGopyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_q_l_gopy, container, false);
        gridviewgopy = (GridView) view.findViewById(R.id.gridviewgopy);
        gopYArrayList = new ArrayList<>();
        adapter = new GopY_Adapter(QLGopyFragment.this, R.layout.gopy, gopYArrayList);
        gridviewgopy.setAdapter(adapter);
        registerForContextMenu(gridviewgopy);

        GetData();
        return view;
    }
    private void GetData() {
        //get data
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM GOPY ");
        gopYArrayList.clear();
        while (cursor.moveToNext())
        {
            gopYArrayList.add(new GopY(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3)
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
                GopY gopY = GopY_Adapter.gopYList.get(info.position);
                TrangChuFragment.database.DELETE_GOPY(
                        gopY.getIDGOPY()
                );

                Toast.makeText(getActivity(),"Xóa thành công",Toast.LENGTH_LONG).show();
                GetData();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}