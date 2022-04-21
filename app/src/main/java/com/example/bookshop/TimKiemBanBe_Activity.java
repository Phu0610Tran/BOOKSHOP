package com.example.bookshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookshop.ActivityUser.MainActivity;
import com.example.bookshop.Adapter.TaiKhoanAdapter;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.Models.TaiKhoan;

import java.util.ArrayList;
import java.util.Locale;

public class TimKiemBanBe_Activity extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    EditText edt_tk;
    ListView listview_tk;
    ImageButton img_search_Tk,img_voice_Tk;
    ArrayList<TaiKhoan> taiKhoanArrayList;
    TaiKhoanAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem_ban_be);
        Anhxa();
        listview_tk = findViewById(R.id.listview_tk);
        taiKhoanArrayList = new ArrayList<>();
        adapter = new TaiKhoanAdapter(TimKiemBanBe_Activity.this, R.layout.layout_banbe, taiKhoanArrayList);
        listview_tk.setAdapter(adapter);
        listview_tk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TimKiemBanBe_Activity.this, ChoiNgayActivity.class);
                intent.putExtra("id",i);
                startActivity(intent);

            }
        });


        edt_tk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                GetDataALL();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                GetData(edt_tk.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        img_voice_Tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
    }
    private MenuItem speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something");

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        }
        catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if (resultCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edt_tk.setText(result.get(0));
                }
                break;
            }
        }
    }


    private void GetData(String ten) {
        //get data
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM TAIKHOAN WHERE TENTAIKHOAN LIKE '%" + ten +"%'" );
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
                    cursor.getBlob(8),
                    cursor.getInt(9)
            ));
        }
        adapter.notifyDataSetChanged();
    }
    private void GetDataALL() {
        //get data
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
                    cursor.getBlob(8),
                    cursor.getInt(9)
            ));
        }
        adapter.notifyDataSetChanged();
    }

    private void Anhxa() {
        edt_tk = findViewById(R.id.edt_tk);
        img_search_Tk = findViewById(R.id.img_search_Tk);
        img_voice_Tk = findViewById(R.id.img_voice_Tk);
    }
}