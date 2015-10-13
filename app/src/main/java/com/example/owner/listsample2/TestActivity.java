package com.example.owner.listsample2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Created by Owner on 2015/10/05.
 */
public class TestActivity extends AppCompatActivity {
    String line;
    int time_hyoji;
    String title_num;
    final String LOGDIR = Environment.getExternalStorageDirectory().getPath()+"/data/";
    File Title;
    File file1;
    File file2;
    File file3;
    File file4;
    File file5;

    final String SDFILE1_1 = LOGDIR + "/title1/" + "title1_1.txt";
    final String SDFILE1_2 = LOGDIR + "/title1/" + "title1_2.txt";
    final String SDFILE1_3 = LOGDIR + "/title1/" + "title1_3.txt";
    final String SDFILE1_4 = LOGDIR + "/title1/" + "title1_4.txt";
    final String SDFILE1_5 = LOGDIR + "/title1/" + "title1_5.txt";

    final String SDFILE1_1fin = LOGDIR + "/title1/" + "title1_1fin.txt";
    final String SDFILE1_2fin = LOGDIR + "/title1/" + "title1_2fin.txt";
    final String SDFILE1_3fin = LOGDIR + "/title1/" + "title1_3fin.txt";
    final String SDFILE1_4fin = LOGDIR + "/title1/" + "title1_4fin.txt";
    final String SDFILE1_5fin = LOGDIR + "/title1/" + "title1_5fin.txt";
    //File Title1 = new File(SDFILE1);
    final String SDFILE2_1 = LOGDIR + "/title2/" + "title2_1.txt";
    final String SDFILE2_2 = LOGDIR + "/title2/" + "title2_2.txt";
    final String SDFILE2_3 = LOGDIR + "/title2/" + "title2_3.txt";
    final String SDFILE2_4 = LOGDIR + "/title2/" + "title2_4.txt";
    final String SDFILE2_5 = LOGDIR + "/title2/" + "title2_5.txt";

    final String SDFILE2_1fin = LOGDIR + "/title2/" + "title2_1fin.txt";
    final String SDFILE2_2fin = LOGDIR + "/title2/" + "title2_2fin.txt";
    final String SDFILE2_3fin = LOGDIR + "/title2/" + "title2_3fin.txt";
    final String SDFILE2_4fin = LOGDIR + "/title2/" + "title2_4fin.txt";
    final String SDFILE2_5fin = LOGDIR + "/title2/" + "title2_5fin.txt";
    //File Title2 = new File(SDFILE2);
    final String SDFILE3_1 = LOGDIR + "/title3/" + "title3_1.txt";
    final String SDFILE3_2 = LOGDIR + "/title3/" + "title3_2.txt";
    final String SDFILE3_3 = LOGDIR + "/title3/" + "title3_3.txt";
    final String SDFILE3_4 = LOGDIR + "/title3/" + "title3_4.txt";
    final String SDFILE3_5 = LOGDIR + "/title3/" + "title3_5.txt";

    final String SDFILE3_1fin = LOGDIR + "/title3/" + "title3_1fin.txt";
    final String SDFILE3_2fin = LOGDIR + "/title3/" + "title3_2fin.txt";
    final String SDFILE3_3fin = LOGDIR + "/title3/" + "title3_3fin.txt";
    final String SDFILE3_4fin = LOGDIR + "/title3/" + "title3_4fin.txt";
    final String SDFILE3_5fin = LOGDIR + "/title3/" + "title3_5fin.txt";
    //File Title3 = new File(SDFILE3);
    final String SDFILE4_1 = LOGDIR + "/title4/" + "title4_1.txt";
    final String SDFILE4_2 = LOGDIR + "/title4/" + "title4_2.txt";
    final String SDFILE4_3 = LOGDIR + "/title4/" + "title4_3.txt";
    final String SDFILE4_4 = LOGDIR + "/title4/" + "title4_4.txt";
    final String SDFILE4_5 = LOGDIR + "/title4/" + "title4_5.txt";

    final String SDFILE4_1fin = LOGDIR + "/title4/" + "title4_1fin.txt";
    final String SDFILE4_2fin = LOGDIR + "/title4/" + "title4_2fin.txt";
    final String SDFILE4_3fin = LOGDIR + "/title4/" + "title4_3fin.txt";
    final String SDFILE4_4fin = LOGDIR + "/title4/" + "title4_4fin.txt";
    final String SDFILE4_5fin = LOGDIR + "/title4/" + "title4_5fin.txt";
    //File Title4 = new File(SDFILE4);
    final String SDFILE5_1 = LOGDIR + "/title5/" + "title5_1.txt";
    final String SDFILE5_2 = LOGDIR + "/title5/" + "title5_2.txt";
    final String SDFILE5_3 = LOGDIR + "/title5/" + "title5_3.txt";
    final String SDFILE5_4 = LOGDIR + "/title5/" + "title5_4.txt";
    final String SDFILE5_5 = LOGDIR + "/title5/" + "title5_5.txt";

    final String SDFILE5_1fin = LOGDIR + "/title5/" + "title5_1fin.txt";
    final String SDFILE5_2fin = LOGDIR + "/title5/" + "title5_2fin.txt";
    final String SDFILE5_3fin = LOGDIR + "/title5/" + "title5_3fin.txt";
    final String SDFILE5_4fin = LOGDIR + "/title5/" + "title5_4fin.txt";
    final String SDFILE5_5fin = LOGDIR + "/title5/" + "title5_5fin.txt";
    //File Title5 = new File(SDFILE5);
    final String SDFILE6_1 = LOGDIR + "/title6/" + "title6_1.txt";
    final String SDFILE6_2 = LOGDIR + "/title6/" + "title6_2.txt";
    final String SDFILE6_3 = LOGDIR + "/title6/" + "title6_3.txt";
    final String SDFILE6_4 = LOGDIR + "/title6/" + "title6_4.txt";
    final String SDFILE6_5 = LOGDIR + "/title6/" + "title6_5.txt";

    final String SDFILE6_1fin = LOGDIR + "/title6/" + "title6_1fin.txt";
    final String SDFILE6_2fin = LOGDIR + "/title6/" + "title6_2fin.txt";
    final String SDFILE6_3fin = LOGDIR + "/title6/" + "title6_3fin.txt";
    final String SDFILE6_4fin = LOGDIR + "/title6/" + "title6_4fin.txt";
    final String SDFILE6_5fin = LOGDIR + "/title6/" + "title6_5fin.txt";
    //File Title6 = new File(SDFILE6);
    final String SDFILE7_1 = LOGDIR + "/title7/" + "title7_1.txt";
    final String SDFILE7_2 = LOGDIR + "/title7/" + "title7_2.txt";
    final String SDFILE7_3 = LOGDIR + "/title7/" + "title7_3.txt";
    final String SDFILE7_4 = LOGDIR + "/title7/" + "title7_4.txt";
    final String SDFILE7_5 = LOGDIR + "/title7/" + "title7_5.txt";

    final String SDFILE7_1fin = LOGDIR + "/title7/" + "title7_1fin.txt";
    final String SDFILE7_2fin = LOGDIR + "/title7/" + "title7_2fin.txt";
    final String SDFILE7_3fin = LOGDIR + "/title7/" + "title7_3fin.txt";
    final String SDFILE7_4fin = LOGDIR + "/title7/" + "title7_4fin.txt";
    final String SDFILE7_5fin = LOGDIR + "/title7/" + "title7_5fin.txt";
    //File Title7 = new File(SDFILE7);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_of_testactivity);

        //ListViewに表示させる内容
        ArrayList<String> list = new ArrayList<String>();

        Intent intent = getIntent();
        String number = intent.getStringExtra("NUMBER");
        switch (number){
            case "パターン１":
                time_hyoji = 15000;
                file1 = new File(SDFILE1_1);
                file2 = new File(SDFILE1_2);
                file3 = new File(SDFILE1_3);
                file4 = new File(SDFILE1_4);
                file5 = new File(SDFILE1_5);

                if (file1.exists()){
                    File fin1 = new File(SDFILE1_1fin);
                    file1.renameTo(fin1);
                    Title = new File(SDFILE1_1fin);
                    title_num = "file11";
                }else if(file2.exists()){
                    File fin2 = new File(SDFILE1_2fin);
                    file2.renameTo(fin2);
                    Title = new File(SDFILE1_2fin);
                    title_num = "file12";
                }else if(file3.exists()){
                    File fin3 = new File(SDFILE1_3fin);
                    file3.renameTo(fin3);
                    Title = new File(SDFILE1_3fin);
                    title_num = "file13";
                }else if(file4.exists()){
                    File fin4 = new File(SDFILE1_4fin);
                    file4.renameTo(fin4);
                    Title = new File(SDFILE1_4fin);
                    title_num = "file14";
                }else if(file5.exists()){
                    File fin5 = new File(SDFILE1_5fin);
                    file5.renameTo(fin5);
                    Title = new File(SDFILE1_5fin);
                    title_num = "file15";
                }else{
                    //Toast.makeText(this, "パターンAはすべて終了しました。", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent();
                    intent1.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.MainActivity");
                    startActivity(intent1);
                }

                break;
            case "パターン２":
                time_hyoji = 16000;
                file1 = new File(SDFILE2_1);
                file2 = new File(SDFILE2_2);
                file3 = new File(SDFILE2_3);
                file4 = new File(SDFILE2_4);
                file5 = new File(SDFILE2_5);

                if (file1.exists()){
                    File fin1 = new File(SDFILE2_1fin);
                    file1.renameTo(fin1);
                    Title = new File(SDFILE2_1fin);
                    title_num = "file21";
                }else if(file2.exists()){
                    File fin2 = new File(SDFILE2_2fin);
                    file2.renameTo(fin2);
                    Title = new File(SDFILE2_2fin);
                    title_num = "file22";
                }else if(file3.exists()){
                    File fin3 = new File(SDFILE2_3fin);
                    file3.renameTo(fin3);
                    Title = new File(SDFILE2_3fin);
                    title_num = "file23";
                }else if(file4.exists()){
                    File fin4 = new File(SDFILE2_4fin);
                    file4.renameTo(fin4);
                    Title = new File(SDFILE2_4fin);
                    title_num = "file24";
                }else if(file5.exists()){
                    File fin5 = new File(SDFILE2_5fin);
                    file5.renameTo(fin5);
                    Title = new File(SDFILE2_5fin);
                    title_num = "file25";
                }else{
                    Intent intent2 = new Intent();
                    intent2.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.MainActivity");
                    startActivity(intent2);
                }

                break;
            case "パターン３":
                time_hyoji = 17000;
                file1 = new File(SDFILE3_1);
                file2 = new File(SDFILE3_2);
                file3 = new File(SDFILE3_3);
                file4 = new File(SDFILE3_4);
                file5 = new File(SDFILE3_5);

                if (file1.exists()){
                    File fin1 = new File(SDFILE3_1fin);
                    file1.renameTo(fin1);
                    Title = new File(SDFILE3_1fin);
                    title_num = "file31";
                }else if(file2.exists()){
                    File fin2 = new File(SDFILE3_2fin);
                    file2.renameTo(fin2);
                    Title = new File(SDFILE3_2fin);
                    title_num = "file32";
                }else if(file3.exists()){
                    File fin3 = new File(SDFILE3_3fin);
                    file3.renameTo(fin3);
                    Title = new File(SDFILE3_3fin);
                    title_num = "file33";
                }else if(file4.exists()){
                    File fin4 = new File(SDFILE3_4fin);
                    file4.renameTo(fin4);
                    Title = new File(SDFILE3_4fin);
                    title_num = "file34";
                }else if(file5.exists()){
                    File fin5 = new File(SDFILE3_5fin);
                    file5.renameTo(fin5);
                    Title = new File(SDFILE3_5fin);
                    title_num = "file35";
                }else{
                    Intent intent3 = new Intent();
                    intent3.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.MainActivity");
                    startActivity(intent3);
                }

                break;
            case "パターン４":
                time_hyoji = 18000;
                file1 = new File(SDFILE4_1);
                file2 = new File(SDFILE4_2);
                file3 = new File(SDFILE4_3);
                file4 = new File(SDFILE4_4);
                file5 = new File(SDFILE4_5);

                if (file1.exists()){
                    File fin1 = new File(SDFILE4_1fin);
                    file1.renameTo(fin1);
                    Title = new File(SDFILE4_1fin);
                    title_num = "file41";
                }else if(file2.exists()){
                    File fin2 = new File(SDFILE4_2fin);
                    file2.renameTo(fin2);
                    Title = new File(SDFILE4_2fin);
                    title_num = "file42";
                }else if(file3.exists()){
                    File fin3 = new File(SDFILE4_3fin);
                    file3.renameTo(fin3);
                    Title = new File(SDFILE4_3fin);
                    title_num = "file43";
                }else if(file4.exists()){
                    File fin4 = new File(SDFILE4_4fin);
                    file4.renameTo(fin4);
                    Title = new File(SDFILE4_4fin);
                    title_num = "file44";
                }else if(file5.exists()){
                    File fin5 = new File(SDFILE4_5fin);
                    file5.renameTo(fin5);
                    Title = new File(SDFILE4_5fin);
                    title_num = "file45";
                }else{
                    Intent intent4 = new Intent();
                    intent4.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.MainActivity");
                    startActivity(intent4);
                }

                break;
            case "パターン５":
                time_hyoji = 19000;
                file1 = new File(SDFILE5_1);
                file2 = new File(SDFILE5_2);
                file3 = new File(SDFILE5_3);
                file4 = new File(SDFILE5_4);
                file5 = new File(SDFILE5_5);

                if (file1.exists()){
                    File fin1 = new File(SDFILE5_1fin);
                    file1.renameTo(fin1);
                    Title = new File(SDFILE5_1fin);
                    title_num = "file51";
                }else if(file2.exists()){
                    File fin2 = new File(SDFILE5_2fin);
                    file2.renameTo(fin2);
                    Title = new File(SDFILE5_2fin);
                    title_num = "file52";
                }else if(file3.exists()){
                    File fin3 = new File(SDFILE5_3fin);
                    file3.renameTo(fin3);
                    Title = new File(SDFILE5_3fin);
                    title_num = "file53";
                }else if(file4.exists()){
                    File fin4 = new File(SDFILE5_4fin);
                    file4.renameTo(fin4);
                    Title = new File(SDFILE5_4fin);
                    title_num = "file54";
                }else if(file5.exists()){
                    File fin5 = new File(SDFILE5_5fin);
                    file5.renameTo(fin5);
                    Title = new File(SDFILE5_5fin);
                    title_num = "file55";
                }else{
                    Intent intent5 = new Intent();
                    intent5.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.MainActivity");
                    startActivity(intent5);
                }

                break;
            case "パターン６":
                time_hyoji = 20000;
                file1 = new File(SDFILE6_1);
                file2 = new File(SDFILE6_2);
                file3 = new File(SDFILE6_3);
                file4 = new File(SDFILE6_4);
                file5 = new File(SDFILE6_5);

                if (file1.exists()){
                    File fin1 = new File(SDFILE6_1fin);
                    file1.renameTo(fin1);
                    Title = new File(SDFILE6_1fin);
                    title_num = "file61";
                }else if(file2.exists()){
                    File fin2 = new File(SDFILE6_2fin);
                    file2.renameTo(fin2);
                    Title = new File(SDFILE6_2fin);
                    title_num = "file62";
                }else if(file3.exists()){
                    File fin3 = new File(SDFILE6_3fin);
                    file3.renameTo(fin3);
                    Title = new File(SDFILE6_3fin);
                    title_num = "file63";
                }else if(file4.exists()){
                    File fin4 = new File(SDFILE6_4fin);
                    file4.renameTo(fin4);
                    Title = new File(SDFILE6_4fin);
                    title_num = "file64";
                }else if(file5.exists()){
                    File fin5 = new File(SDFILE6_5fin);
                    file5.renameTo(fin5);
                    Title = new File(SDFILE6_5fin);
                    title_num = "file65";
                }else{
                    Intent intent6 = new Intent();
                    intent6.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.MainActivity");
                    startActivity(intent6);
                }

                break;
            case "パターン７":
                time_hyoji = 21000;
                file1 = new File(SDFILE7_1);
                file2 = new File(SDFILE7_2);
                file3 = new File(SDFILE7_3);
                file4 = new File(SDFILE7_4);
                file5 = new File(SDFILE7_5);

                if (file1.exists()){
                    File fin1 = new File(SDFILE7_1fin);
                    file1.renameTo(fin1);
                    Title = new File(SDFILE7_1fin);
                    title_num = "file71";
                }else if(file2.exists()){
                    File fin2 = new File(SDFILE7_2fin);
                    file2.renameTo(fin2);
                    Title = new File(SDFILE7_2fin);
                    title_num = "file72";
                }else if(file3.exists()){
                    File fin3 = new File(SDFILE7_3fin);
                    file3.renameTo(fin3);
                    Title = new File(SDFILE7_3fin);
                    title_num = "file73";
                }else if(file4.exists()){
                    File fin4 = new File(SDFILE7_4fin);
                    file4.renameTo(fin4);
                    Title = new File(SDFILE7_4fin);
                    title_num = "file74";
                }else if(file5.exists()){
                    File fin5 = new File(SDFILE7_5fin);
                    file5.renameTo(fin5);
                    Title = new File(SDFILE7_5fin);
                    title_num = "file75";
                }else{
                    Intent intent7 = new Intent();
                    intent7.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.MainActivity");
                    startActivity(intent7);
                }

        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(Title));
                try {
                    while((line = br.readLine()) != null){
                        list.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        ListView lv = (ListView)findViewById(R.id.listView2);
        //ListViewにアダプタ登録
        lv.setAdapter(adapter);

        //String time = String.valueOf(time_hyoji);
        //Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //ここにかく
                Intent intent = new Intent();//this,TestActivity.class
                intent.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.AnswerActivity");
                intent.putExtra("FILE_NUMBER", title_num);
                startActivity(intent);
            }
        }, time_hyoji);
    }

    public boolean dispatchKeyEvent(KeyEvent event){
        return super.dispatchKeyEvent(event);
    }
}
