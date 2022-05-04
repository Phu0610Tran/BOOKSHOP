package com.example.bookshop.Events;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.User_Activity.LoginActivity;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.example.bookshop.Models.Answer;
import com.example.bookshop.Models.Question;
import com.example.bookshop.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class TracnghiemActivity extends AppCompatActivity implements View.OnClickListener  {
    private TextView tv_question;
    private TextView tv_content_question;
    private TextView tv_answer1,tv_answer2,tv_answer3,tv_answer4,ten_tk_tng,diem_tk_tng;
    CircleImageView img_tk_tng;
    private List<Question> questionList;
    private Question mQuestion;
    private int currentQuestion = 0;
    private int cau = 1;
    private int dung=0;
    int kiemtradiem=0;
    ImageView quaylai_tng;
    float vong;
    int idbo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracnghiem);
        AnhXa();
        CheckThongTin();
        kiemtradiem = Integer.valueOf(diem_tk_tng.getText().toString());
        Intent intent = getIntent();

        vong = intent.getFloatExtra("vong", 1.5F);

        questionList = getListQuestion();
        if(questionList.isEmpty())
        {
            return;
        }
        Random random1 = new Random();
        if (vong > 3F){
            idbo = 3;
        }
        else if (vong > 2F && vong < 3F)
        {
            idbo = 2;

        }else if(vong < 2F )
        {
            idbo = 1;

        }
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT IDCH FROM CAUHOI WHERE IDBO = " + idbo + " ORDER BY IDCH DESC");
        cursor.moveToNext();
        int val = random1.nextInt(cursor.getCount() - 1);
        currentQuestion=val;
//        setDataQuestion(questionList.get(currentQuestion));
        setDataQuestion(questionList.get(currentQuestion));
    }

    @Override
    protected void onStart() {
        CheckThongTin();
        super.onStart();
    }

    private void CheckThongTin() {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT TENTAIKHOAN,HINHANH,DIEM FROM TAIKHOAN WHERE IDTAIKHOAN = "
                + LoginActivity.taiKhoan.getMATK());
        cursor.moveToNext();
        ten_tk_tng.setText(cursor.getString(0));
        if(String.valueOf(cursor.getInt(2)).length() == 0 )
        {

            diem_tk_tng.setText("0");

        }else {
            diem_tk_tng.setText(String.valueOf(cursor.getInt(2)));
        }

        if (LoginActivity.taiKhoan.getHINHANH()!=null){
            byte[] hinhAnh = LoginActivity.taiKhoan.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_tk_tng.setImageBitmap(bitmap);
        }else {
            img_tk_tng.setImageResource(R.drawable.user);
        }
    }
    private void setDataQuestion(Question question) {
        if(question == null){
            return;
        }
        mQuestion = question;
        tv_answer1.setBackgroundResource(R.drawable.custom_cau);
        tv_answer2.setBackgroundResource(R.drawable.custom_cau);
        tv_answer3.setBackgroundResource(R.drawable.custom_cau);
        tv_answer4.setBackgroundResource(R.drawable.custom_cau);


        String titleQuestion = "Câu Hỏi " + cau;
        tv_question.setText(titleQuestion);
        tv_content_question.setText(question.getContent());
        tv_answer1.setText("a." +question.getAnswerList().get(0).getContent());
        tv_answer2.setText("b." +question.getAnswerList().get(1).getContent());
        tv_answer3.setText("c." +question.getAnswerList().get(2).getContent());
        tv_answer4.setText("d." +question.getAnswerList().get(3).getContent());
//        Toast.makeText(TracnghiemActivity.this, "sss : " + question.getAnswerList().get(0).getIsCorrect(), Toast.LENGTH_SHORT).show();


    }

    private void AnhXa() {
        quaylai_tng = findViewById(R.id.quaylai_tng);
        quaylai_tng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        diem_tk_tng = findViewById(R.id.diem_tk_tng);
        ten_tk_tng = findViewById(R.id.ten_tk_tng);
        img_tk_tng = findViewById(R.id.img_tk_tng);
        tv_question = findViewById(R.id.tv_question);
        tv_content_question = findViewById(R.id.tv_content_question);
        tv_answer1 = findViewById(R.id.tv_answer1);
        tv_answer2 = findViewById(R.id.tv_answer2);
        tv_answer3 = findViewById(R.id.tv_answer3);
        tv_answer4 = findViewById(R.id.tv_answer4);

        tv_answer1.setOnClickListener(this);
        tv_answer2.setOnClickListener(this);
        tv_answer3.setOnClickListener(this);
        tv_answer4.setOnClickListener(this);
    }
    private List<Question> getListQuestion(){
        List<Question> list = new ArrayList<>();
        if (vong > 3F){
            idbo = 3;
        }
        else if (vong > 2F && vong < 3F)
        {
            idbo = 2;

        }else if(vong < 2F )
        {
            idbo = 1;

        }
        Cursor cursor =TrangChuFragment.database.Getdata("SELECT CAUHOI FROM DAPAN WHERE IDBO = " + idbo +" ORDER BY CAUHOI DESC");
        cursor.moveToNext();
        Cursor cursorbatdau =TrangChuFragment.database.Getdata("SELECT CAUHOI FROM DAPAN WHERE IDBO = " + idbo);
        cursorbatdau.moveToNext();
        for(int i =cursorbatdau.getInt(0); i <= cursor.getInt(0);i++){
            List<Answer> answerList1 = new ArrayList<>();
            Cursor cursor1 = TrangChuFragment.database.Getdata("SELECT * FROM DAPAN WHERE CAUHOI = " + i + " AND IDBO = " +idbo );
            Cursor cursor2 = TrangChuFragment.database.Getdata("SELECT * FROM CAUHOI WHERE IDCH = " + i + " AND IDBO = " +idbo);
            while (cursor1.moveToNext())
            {
                answerList1.add(new Answer(
                        cursor1.getString(1),
                        cursor1.getString(3)
                ));
            }
            while (cursor2.moveToNext()){
                list.add(new Question(
                        cursor2.getString(1),answerList1));
            }

        }

        return list;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_answer1:
                tv_answer1.setBackgroundResource(R.drawable.custom_cautraloi_chon);
                checkAnswer(tv_answer1,mQuestion,mQuestion.getAnswerList().get(0));
                break;
            case R.id.tv_answer2:
                tv_answer2.setBackgroundResource(R.drawable.custom_cautraloi_chon);
                checkAnswer(tv_answer2,mQuestion,mQuestion.getAnswerList().get(1));
                break;
            case R.id.tv_answer3:
                tv_answer3.setBackgroundResource(R.drawable.custom_cautraloi_chon);
                checkAnswer(tv_answer3,mQuestion,mQuestion.getAnswerList().get(2));

                break;
            case R.id.tv_answer4:
                tv_answer4.setBackgroundResource(R.drawable.custom_cautraloi_chon);
                checkAnswer(tv_answer4,mQuestion,mQuestion.getAnswerList().get(3));

                break;
        }
    }
    private void checkAnswer(TextView textView,Question question,Answer answer){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(answer.getIsCorrect().equals("true")){
                    textView.setBackgroundResource(R.drawable.custom_cautraloi_dung);
                    diem_tk_tng.setText(String.valueOf(Integer.valueOf(diem_tk_tng.getText().toString())+ 1));
                    nextQuestion();

                }
                else
                {
                    textView.setBackgroundResource(R.drawable.custom_cautraloi_sai);
                    showAnswerCorrect(questionList.get(currentQuestion));
                    nextQuestion();
                }

            }
        },500);
    }
    private void showAnswerCorrect(Question question) {
        if(question==null || question.getAnswerList() == null || question.getAnswerList().isEmpty()){
            return;
        }

        if(question.getAnswerList().get(0).isCorrect().equals("true")){
            tv_answer1.setBackgroundResource(R.drawable.custom_cautraloi_dung);
        }else if(question.getAnswerList().get(1).isCorrect().equals("true")){
            tv_answer2.setBackgroundResource(R.drawable.custom_cautraloi_dung);
        }else if(question.getAnswerList().get(2).isCorrect().equals("true")){
            tv_answer3.setBackgroundResource(R.drawable.custom_cautraloi_dung);
        }else {
            tv_answer4.setBackgroundResource(R.drawable.custom_cautraloi_dung);
        }
    }

    private void nextQuestion() {
        cau++;
        if(cau > 5){
            showDialog();
        }else {

            Random random1 = new Random();
            Cursor cursor = TrangChuFragment.database.Getdata("SELECT IDCH FROM CAUHOI WHERE IDBO = " + idbo +" ORDER BY IDCH DESC");
            cursor.moveToNext();
            int val = random1.nextInt(cursor.getCount() - 1);
//            Toast.makeText(TracnghiemActivity.this, "so ngau nhien " + val, Toast.LENGTH_SHORT).show();
            currentQuestion = val;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    setDataQuestion(questionList.get(currentQuestion));
                    setDataQuestion(questionList.get(currentQuestion));

                }
            },1000);

        }

    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.chucmung,null);
        final ImageView img_chucmung = view.findViewById(R.id.img_chucmung);
        final TextView txt_chucmung = view.findViewById(R.id.txt_chucmung);
        final TextView txt_diem = view.findViewById(R.id.txt_diem);
        int idtk = LoginActivity.taiKhoan.getMATK();
        dung = Integer.valueOf(diem_tk_tng.getText().toString()) - kiemtradiem;
        if(dung == 5 ){

            if (TrangChuFragment.database.kiemtraVoucher(idtk,3))
            {
                TrangChuFragment.database.VONGGAME(idtk,vong,3,3);
                img_chucmung.setImageResource(R.drawable.icon_vui_basao);
                txt_chucmung.setText("Bạn nhận được voucher giảm giá 30%");
            }
            else {
                TrangChuFragment.database.VONGGAME(idtk,vong,3,0);
                img_chucmung.setImageResource(R.drawable.icon_vui_haisao);
                txt_chucmung.setText("Bạn hãy sử dụng hết Voucher đang có để có thể nhận thêm Voucher mới nhé!");
            }
        }else if(dung>2){
            if (TrangChuFragment.database.kiemtraVoucher(idtk,2))
            {
                TrangChuFragment.database.VONGGAME(idtk,vong,2,2);
                img_chucmung.setImageResource(R.drawable.icon_vui_haisao);
                txt_chucmung.setText("Bạn nhận được voucher giảm giá 20%");
            }
            else {
                TrangChuFragment.database.VONGGAME(idtk,vong,2,0);
                img_chucmung.setImageResource(R.drawable.icon_vui_haisao);
                txt_chucmung.setText("Bạn hãy sử dụng hết Voucher đang có để có thể nhận thêm Voucher mới nhé!");
            }

        }else{
            if (TrangChuFragment.database.kiemtraVoucher(idtk,1))
            {
                TrangChuFragment.database.VONGGAME(idtk,vong,1,1);
                img_chucmung.setImageResource(R.drawable.icon_vui_motsao);
                txt_chucmung.setText("Bạn nhận được voucher giảm giá 10%");
            }
            else {
                TrangChuFragment.database.VONGGAME(idtk,vong,1,0);
                img_chucmung.setImageResource(R.drawable.icon_vui_motsao);
                txt_chucmung.setText("Bạn hãy sử dụng hết Voucher đang có để có thể nhận thêm Voucher mới nhé!");
            }


        }
        txt_diem.setText(String.valueOf(dung) + " Điểm");
        builder.setView(view);
        builder.setCancelable(false);
        TrangChuFragment.database.Tangdiem(idtk,Integer.valueOf(diem_tk_tng.getText().toString()));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (vong > 3F){
                    startActivity(new Intent(TracnghiemActivity.this, ChoiNgayVong3.class));
                }
                else if (vong > 2F && vong < 3F)
                {
                    startActivity(new Intent(TracnghiemActivity.this, ChoiNgayVong2.class));

                }else if(vong < 2F )
                {
                    startActivity(new Intent(TracnghiemActivity.this, ChoiNgayActivity.class));

                }

            }
        },5000);
    }

}