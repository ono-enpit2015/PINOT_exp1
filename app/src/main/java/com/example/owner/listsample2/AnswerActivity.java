package com.example.owner.listsample2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by Owner on 2015/10/05.
 */
public class AnswerActivity extends AppCompatActivity {
    SparseBooleanArray checkedItemPositions;
    ListView listView;
    HashMap map = new HashMap();
    String line;
    String line2;
    String line3;
    String title;
    boolean flag;
    int ari_match;
    int nasi_match;
    int ari_miss;
    int nasi_miss;
    int true_count;
    int false_count;
    final String LOGDIR = Environment.getExternalStorageDirectory().getPath()+"/data/"+"/exp/";
    File TEST;
    File Mix_flag;
    final String Mix_new = LOGDIR + "mix_new.txt";
    File Title;
    File Mix;
    final String RESULT = LOGDIR + "result.txt";
    File Result = new File(RESULT);
    String file_number;
    int Hyoji_Jikan;
    int hyojisuu = MainActivity.HyojiKensu;         //ウェブ上で変更できるようにする
    int kijisuu = MainActivity.KijiKensu;

    final String TEST1_1 = LOGDIR + "/title1/" + "test1_1.txt";
    final String TEST1_2 = LOGDIR + "/title1/" + "test1_2.txt";
    final String TEST1_3 = LOGDIR + "/title1/" + "test1_3.txt";
    final String TEST1_4 = LOGDIR + "/title1/" + "test1_4.txt";
    final String TEST1_5 = LOGDIR + "/title1/" + "test1_5.txt";

    final String TEST2_1 = LOGDIR + "/title2/" + "test2_1.txt";
    final String TEST2_2 = LOGDIR + "/title2/" + "test2_2.txt";
    final String TEST2_3 = LOGDIR + "/title2/" + "test2_3.txt";
    final String TEST2_4 = LOGDIR + "/title2/" + "test2_4.txt";
    final String TEST2_5 = LOGDIR + "/title2/" + "test2_5.txt";

    final String TEST3_1 = LOGDIR + "/title3/" + "test3_1.txt";
    final String TEST3_2 = LOGDIR + "/title3/" + "test3_2.txt";
    final String TEST3_3 = LOGDIR + "/title3/" + "test3_3.txt";
    final String TEST3_4 = LOGDIR + "/title3/" + "test3_4.txt";
    final String TEST3_5 = LOGDIR + "/title3/" + "test3_5.txt";

    final String TEST4_1 = LOGDIR + "/title4/" + "test4_1.txt";
    final String TEST4_2 = LOGDIR + "/title4/" + "test4_2.txt";
    final String TEST4_3 = LOGDIR + "/title4/" + "test4_3.txt";
    final String TEST4_4 = LOGDIR + "/title4/" + "test4_4.txt";
    final String TEST4_5 = LOGDIR + "/title4/" + "test4_5.txt";

    final String TEST5_1 = LOGDIR + "/title5/" + "test5_1.txt";
    final String TEST5_2 = LOGDIR + "/title5/" + "test5_2.txt";
    final String TEST5_3 = LOGDIR + "/title5/" + "test5_3.txt";
    final String TEST5_4 = LOGDIR + "/title5/" + "test5_4.txt";
    final String TEST5_5 = LOGDIR + "/title5/" + "test5_5.txt";

    final String TEST6_1 = LOGDIR + "/title6/" + "test6_1.txt";
    final String TEST6_2 = LOGDIR + "/title6/" + "test6_2.txt";
    final String TEST6_3 = LOGDIR + "/title6/" + "test6_3.txt";
    final String TEST6_4 = LOGDIR + "/title6/" + "test6_4.txt";
    final String TEST6_5 = LOGDIR + "/title6/" + "test6_5.txt";

    final String TEST7_1 = LOGDIR + "/title7/" + "test7_1.txt";
    final String TEST7_2 = LOGDIR + "/title7/" + "test7_2.txt";
    final String TEST7_3 = LOGDIR + "/title7/" + "test7_3.txt";
    final String TEST7_4 = LOGDIR + "/title7/" + "test7_4.txt";
    final String TEST7_5 = LOGDIR + "/title7/" + "test7_5.txt";


    final String SDFILE1_1fin = LOGDIR + "/title1/" + "title1_1fin.txt";
    final String SDFILE1_2fin = LOGDIR + "/title1/" + "title1_2fin.txt";
    final String SDFILE1_3fin = LOGDIR + "/title1/" + "title1_3fin.txt";
    final String SDFILE1_4fin = LOGDIR + "/title1/" + "title1_4fin.txt";
    final String SDFILE1_5fin = LOGDIR + "/title1/" + "title1_5fin.txt";

    final String SDFILE2_1fin = LOGDIR + "/title2/" + "title2_1fin.txt";
    final String SDFILE2_2fin = LOGDIR + "/title2/" + "title2_2fin.txt";
    final String SDFILE2_3fin = LOGDIR + "/title2/" + "title2_3fin.txt";
    final String SDFILE2_4fin = LOGDIR + "/title2/" + "title2_4fin.txt";
    final String SDFILE2_5fin = LOGDIR + "/title2/" + "title2_5fin.txt";

    final String SDFILE3_1fin = LOGDIR + "/title3/" + "title3_1fin.txt";
    final String SDFILE3_2fin = LOGDIR + "/title3/" + "title3_2fin.txt";
    final String SDFILE3_3fin = LOGDIR + "/title3/" + "title3_3fin.txt";
    final String SDFILE3_4fin = LOGDIR + "/title3/" + "title3_4fin.txt";
    final String SDFILE3_5fin = LOGDIR + "/title3/" + "title3_5fin.txt";

    final String SDFILE4_1fin = LOGDIR + "/title4/" + "title4_1fin.txt";
    final String SDFILE4_2fin = LOGDIR + "/title4/" + "title4_2fin.txt";
    final String SDFILE4_3fin = LOGDIR + "/title4/" + "title4_3fin.txt";
    final String SDFILE4_4fin = LOGDIR + "/title4/" + "title4_4fin.txt";
    final String SDFILE4_5fin = LOGDIR + "/title4/" + "title4_5fin.txt";

    final String SDFILE5_1fin = LOGDIR + "/title5/" + "title5_1fin.txt";
    final String SDFILE5_2fin = LOGDIR + "/title5/" + "title5_2fin.txt";
    final String SDFILE5_3fin = LOGDIR + "/title5/" + "title5_3fin.txt";
    final String SDFILE5_4fin = LOGDIR + "/title5/" + "title5_4fin.txt";
    final String SDFILE5_5fin = LOGDIR + "/title5/" + "title5_5fin.txt";

    final String SDFILE6_1fin = LOGDIR + "/title6/" + "title6_1fin.txt";
    final String SDFILE6_2fin = LOGDIR + "/title6/" + "title6_2fin.txt";
    final String SDFILE6_3fin = LOGDIR + "/title6/" + "title6_3fin.txt";
    final String SDFILE6_4fin = LOGDIR + "/title6/" + "title6_4fin.txt";
    final String SDFILE6_5fin = LOGDIR + "/title6/" + "title6_5fin.txt";

    final String SDFILE7_1fin = LOGDIR + "/title7/" + "title7_1fin.txt";
    final String SDFILE7_2fin = LOGDIR + "/title7/" + "title7_2fin.txt";
    final String SDFILE7_3fin = LOGDIR + "/title7/" + "title7_3fin.txt";
    final String SDFILE7_4fin = LOGDIR + "/title7/" + "title7_4fin.txt";
    final String SDFILE7_5fin = LOGDIR + "/title7/" + "title7_5fin.txt";


    final String Mix1_1 = LOGDIR + "/title1/" + "mix1_1.txt";
    final String Mix1_2 = LOGDIR + "/title1/" + "mix1_2.txt";
    final String Mix1_3 = LOGDIR + "/title1/" + "mix1_3.txt";
    final String Mix1_4 = LOGDIR + "/title1/" + "mix1_4.txt";
    final String Mix1_5 = LOGDIR + "/title1/" + "mix1_5.txt";

    final String Mix2_1 = LOGDIR + "/title2/" + "mix2_1.txt";
    final String Mix2_2 = LOGDIR + "/title2/" + "mix2_2.txt";
    final String Mix2_3 = LOGDIR + "/title2/" + "mix2_3.txt";
    final String Mix2_4 = LOGDIR + "/title2/" + "mix2_4.txt";
    final String Mix2_5 = LOGDIR + "/title2/" + "mix2_5.txt";

    final String Mix3_1 = LOGDIR + "/title3/" + "mix3_1.txt";
    final String Mix3_2 = LOGDIR + "/title3/" + "mix3_2.txt";
    final String Mix3_3 = LOGDIR + "/title3/" + "mix3_3.txt";
    final String Mix3_4 = LOGDIR + "/title3/" + "mix3_4.txt";
    final String Mix3_5 = LOGDIR + "/title3/" + "mix3_5.txt";

    final String Mix4_1 = LOGDIR + "/title4/" + "mix4_1.txt";
    final String Mix4_2 = LOGDIR + "/title4/" + "mix4_2.txt";
    final String Mix4_3 = LOGDIR + "/title4/" + "mix4_3.txt";
    final String Mix4_4 = LOGDIR + "/title4/" + "mix4_4.txt";
    final String Mix4_5 = LOGDIR + "/title4/" + "mix4_5.txt";

    final String Mix5_1 = LOGDIR + "/title5/" + "mix5_1.txt";
    final String Mix5_2 = LOGDIR + "/title5/" + "mix5_2.txt";
    final String Mix5_3 = LOGDIR + "/title5/" + "mix5_3.txt";
    final String Mix5_4 = LOGDIR + "/title5/" + "mix5_4.txt";
    final String Mix5_5 = LOGDIR + "/title5/" + "mix5_5.txt";

    final String Mix6_1 = LOGDIR + "/title6/" + "mix6_1.txt";
    final String Mix6_2 = LOGDIR + "/title6/" + "mix6_2.txt";
    final String Mix6_3 = LOGDIR + "/title6/" + "mix6_3.txt";
    final String Mix6_4 = LOGDIR + "/title6/" + "mix6_4.txt";
    final String Mix6_5 = LOGDIR + "/title6/" + "mix6_5.txt";

    final String Mix7_1 = LOGDIR + "/title7/" + "mix7_1.txt";
    final String Mix7_2 = LOGDIR + "/title7/" + "mix7_2.txt";
    final String Mix7_3 = LOGDIR + "/title7/" + "mix7_3.txt";
    final String Mix7_4 = LOGDIR + "/title7/" + "mix7_4.txt";
    final String Mix7_5 = LOGDIR + "/title7/" + "mix7_5.txt";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_of_answeractivity);

        Intent intent = getIntent();
        file_number = intent.getStringExtra("FILE_NUMBER");
        Hyoji_Jikan = intent.getIntExtra("HyojiTime", 0);
        //System.out.println("Answer:"+Hyoji_Jikan);

        try {
            Mix_flag = new File(Mix_new);
            Mix_flag.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        switch (file_number){
            case "file11":
                TEST = new File(TEST1_1);
                Title = new File(SDFILE1_1fin);
                Mix = new File(Mix1_1);
                break;
            case "file12":
                TEST = new File(TEST1_2);
                Title = new File(SDFILE1_2fin);
                Mix = new File(Mix1_2);
                break;
            case "file13":
                TEST = new File(TEST1_3);
                Title = new File(SDFILE1_3fin);
                Mix = new File(Mix1_3);
                break;
            case "file14":
                TEST = new File(TEST1_4);
                Title = new File(SDFILE1_4fin);
                Mix = new File(Mix1_4);
                break;
            case "file15":
                TEST = new File(TEST1_5);
                Title = new File(SDFILE1_5fin);
                Mix = new File(Mix1_5);
                break;
            case "file21":
                TEST = new File(TEST2_1);
                Title = new File(SDFILE2_1fin);
                Mix = new File(Mix2_1);
                break;
            case "file22":
                TEST = new File(TEST2_2);
                Title = new File(SDFILE2_2fin);
                Mix = new File(Mix2_2);
                break;
            case "file23":
                TEST = new File(TEST2_3);
                Title = new File(SDFILE2_3fin);
                Mix = new File(Mix2_3);
                break;
            case "file24":
                TEST = new File(TEST2_4);
                Title = new File(SDFILE2_4fin);
                Mix = new File(Mix2_4);
                break;
            case "file25":
                TEST = new File(TEST2_5);
                Title = new File(SDFILE2_5fin);
                Mix = new File(Mix2_5);
                break;
            case "file31":
                TEST = new File(TEST3_1);
                Title = new File(SDFILE3_1fin);
                Mix = new File(Mix3_1);
                break;
            case "file32":
                TEST = new File(TEST3_2);
                Title = new File(SDFILE3_2fin);
                Mix = new File(Mix3_2);
                break;
            case "file33":
                TEST = new File(TEST3_3);
                Title = new File(SDFILE3_3fin);
                Mix = new File(Mix3_3);
                break;
            case "file34":
                TEST = new File(TEST3_4);
                Title = new File(SDFILE3_4fin);
                Mix = new File(Mix3_4);
                break;
            case "file35":
                TEST = new File(TEST3_5);
                Title = new File(SDFILE3_5fin);
                Mix = new File(Mix3_5);
                break;
            case "file41":
                TEST = new File(TEST4_1);
                Title = new File(SDFILE4_1fin);
                Mix = new File(Mix4_1);
                break;
            case "file42":
                TEST = new File(TEST4_2);
                Title = new File(SDFILE4_2fin);
                Mix = new File(Mix4_2);
                break;
            case "file43":
                TEST = new File(TEST4_3);
                Title = new File(SDFILE4_3fin);
                Mix = new File(Mix4_3);
                break;
            case "file44":
                TEST = new File(TEST4_4);
                Title = new File(SDFILE4_4fin);
                Mix = new File(Mix4_4);
                break;
            case "file45":
                TEST = new File(TEST4_5);
                Title = new File(SDFILE4_5fin);
                Mix = new File(Mix4_5);
                break;
            case "file51":
                TEST = new File(TEST5_1);
                Title = new File(SDFILE5_1fin);
                Mix = new File(Mix5_1);
                break;
            case "file52":
                TEST = new File(TEST5_2);
                Title = new File(SDFILE5_2fin);
                Mix = new File(Mix5_2);
                break;
            case "file53":
                TEST = new File(TEST5_3);
                Title = new File(SDFILE5_3fin);
                Mix = new File(Mix5_3);
                break;
            case "file54":
                TEST = new File(TEST5_4);
                Title = new File(SDFILE5_4fin);
                Mix = new File(Mix5_4);
                break;
            case "file55":
                TEST = new File(TEST5_5);
                Title = new File(SDFILE5_5fin);
                Mix = new File(Mix5_5);
                break;
            case "file61":
                TEST = new File(TEST6_1);
                Title = new File(SDFILE6_1fin);
                Mix = new File(Mix6_1);
                break;
            case "file62":
                TEST = new File(TEST6_2);
                Title = new File(SDFILE6_2fin);
                Mix = new File(Mix6_2);
                break;
            case "file63":
                TEST = new File(TEST6_3);
                Title = new File(SDFILE6_3fin);
                Mix = new File(Mix6_3);
                break;
            case "file64":
                TEST = new File(TEST6_4);
                Title = new File(SDFILE6_4fin);
                Mix = new File(Mix6_4);
                break;
            case "file65":
                TEST = new File(TEST6_5);
                Title = new File(SDFILE6_5fin);
                Mix = new File(Mix6_5);
                break;
            case "file71":
                TEST = new File(TEST7_1);
                Title = new File(SDFILE7_1fin);
                Mix = new File(Mix7_1);
                break;
            case "file72":
                TEST = new File(TEST7_2);
                Title = new File(SDFILE7_2fin);
                Mix = new File(Mix7_2);
                break;
            case "file73":
                TEST = new File(TEST7_3);
                Title = new File(SDFILE7_3fin);
                Mix = new File(Mix7_3);
                break;
            case "file74":
                TEST = new File(TEST7_4);
                Title = new File(SDFILE7_4fin);
                Mix = new File(Mix7_4);
                break;
            case "file75":
                TEST = new File(TEST7_5);
                Title = new File(SDFILE7_5fin);
                Mix = new File(Mix7_5);
        }
        
        ArrayList<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(TEST));
            try {
                while((line = br.readLine()) != null){
                    list.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //test0_0.txtの見出し文に0と1を振り分け（AnswerActivityで表示するかどうか）
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(TEST,false));
                int ran0=0, ran1=0;
                for(String title:list){
                    Random rnd = new Random();
                    int ran = rnd.nextInt(2);
                    if((ran == 0) && (ran0 < kijisuu-hyojisuu)){
                        ran0++;
                        bw.write(title+"\t\t"+ran);
                        bw.newLine();
                    }else if((ran == 1) && (ran1 < hyojisuu)){
                        ran1++;
                        bw.write(title+"\t\t"+ran);
                        bw.newLine();
                    }else if((ran == 0) && (ran0 == kijisuu-hyojisuu)){
                        ran = 1;
                        bw.write(title+"\t\t"+ran);
                        bw.newLine();
                    }else if((ran == 1) && (ran1 == hyojisuu)){
                        ran = 0;
                        bw.write(title+"\t\t"+ran);
                        bw.newLine();
                    }
                }
                bw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            br.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //ListViewに表示する見出し文（numが1の見出し文）を１つのファイルに集約
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(Title));
            Mix.createNewFile();
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(Mix,false));
            while ((line = br1.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(line, "\t\t");
                String title = token.nextToken();
                String num = token.nextToken();
                if(num.equals("1")) {
                    bw1.write(title);
                    bw1.newLine();
                }
            }
            bw1.close();
            br1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            BufferedReader br2 = new BufferedReader(new FileReader(TEST));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(Mix,true));
            while ((line = br2.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(line, "\t\t");
                String title = token.nextToken();
                String num = token.nextToken();
                if(num.equals("0")) {
                    bw2.write(title);
                    bw2.newLine();
                }
            }
            bw2.close();
            br2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ListViewに表示する要素を格納しシャッフルする
        ArrayList<String> list2 = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Mix));
            try {
                while((line = br.readLine()) != null){
                    list2.add(line);
                }
                //リストの並びをシャッフル
                Collections.shuffle(list2);
                BufferedWriter shufflewriter = new BufferedWriter(new FileWriter(Mix,false));
                int i = 0;
                while(i < list2.size()){
                    title = list2.get(i);
                    shufflewriter.write(title);
                    shufflewriter.newLine();
                    i++;
                }
                shufflewriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            br.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        

        //ListViewとデータをつなぐアダプタを作成
        //アイテムのレイアウトはAndroid組み込みの物を使用
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice, list2);
        ListView lv = (ListView)findViewById(R.id.listView3);

        //ListViewにアダプタ登録
        lv.setAdapter(adapter);

        for(int i = 0; i < kijisuu; i++){        //記事数は動的に変更できるようにする
            map.put(i,false);
        }

        //アイテムがクリックされたときの処理
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //クリックされたものを取得
                /*String get_parent = (String) parent.getClass().getSimpleName();
                String get_position = String.valueOf(position);
                String get_id = String.valueOf(id);*/
                listView = (ListView)parent;
                checkedItemPositions = listView.getCheckedItemPositions();
                //Log.d("", String.format("position:%d checked:%b", position, checkedItemPositions.get(position)));
                map.put(position,checkedItemPositions.get(position));

            }
        });

        Button infoButton = (Button)findViewById(R.id.infoButton);
        infoButton.setOnClickListener(infoButtonOnClickListener);
    }

    private View.OnClickListener infoButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.MainActivity");

            //チェック入り（true）かチェック無し（false）をtest.txtに書き込み
            try {
                BufferedReader br = new BufferedReader(new FileReader(Mix));
                try {
                    BufferedWriter pw = new BufferedWriter(new FileWriter(Mix_flag,true));
                    int i = 0;
                    while((title = br.readLine()) != null){
                            /*int position = 0;
                            boolean checked = checkedItemPositions.get(position);
                            pw.write(title+"\t\t"+checked);
                            pw.newLine();
                            position++;*/
                            pw.write(title + "\t\t" + map.get(i));
                            pw.newLine();
                            i++;
                    }
                    pw.close();
                    br.close();
                    Mix.delete();
                    Mix_flag.renameTo(Mix);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //答え合わせ
            /*try {
                BufferedReader br2 = new BufferedReader(new FileReader(Title));//TestActivityで表示したもの
                ari_match = 0;
                nasi_match = 0;
                ari_miss = 0;
                nasi_miss = 0;
                true_count = 0;
                false_count = 0;
                while ((title = br2.readLine()) != null) {
                    StringTokenizer tok = new StringTokenizer(title, "\t\t");
                    String tok2 = tok.nextToken();
                    try {
                        BufferedReader br3 = new BufferedReader(new FileReader(Mix));//ミックスしたもの
                        while ((line = br3.readLine()) != null) {
                            StringTokenizer token = new StringTokenizer(line, "\t\t");
                            String t = token.nextToken();
                            String f = token.nextToken();
                            if (f.equals("true")) {
                                flag = true;
                            }
                            else {
                                flag = false;
                            }
                            System.out.println(tok2);
                            System.out.println(t);
                            System.out.println(f);
                            if (tok2.equals(t)) {
                                if (flag) {
                                    ari_match++;
                                    //Log.e("match", "" + ari_match);
                                }
                                break;
                            }
                        }
                        br3.close();
                    }catch (IOException e){
                            e.printStackTrace();
                    }
                }
                br2.close();
                Result.createNewFile();
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(Result, true));
                    //bw.write(file_number + "\t\t" + "表示時間(ミリ秒)：" + Hyoji_Jikan + "\t\t" + "正解数：" + match + "\t\t" + "表示件数：" + hyojisuu + "\t\t" + "記事件数：" + kijisuu);
                    bw.write(file_number + "\t\t" + "HyojiJikan(mms):" + Hyoji_Jikan + "\t\t" + "Seikai(o-o):" + ari_match + "Seikai(x-x):" + nasi_match + "Miss(o-x):" + ari_miss + "Miss(x-o):" + nasi_miss + "\t\t" + "HyojiKensu:" + hyojisuu + "\t\t" + "KijiKensu:" + kijisuu);
                    bw.newLine();
                    bw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }catch (IOException e){
                e.printStackTrace();
            }*/
            try {
                BufferedReader br2 = new BufferedReader(new FileReader(Mix));
                ari_match = 0;
                nasi_match = 0;
                ari_miss = 0;
                nasi_miss = 0;
                true_count = 0;
                false_count = 0;
                while ((line = br2.readLine()) != null) {
                    StringTokenizer tok = new StringTokenizer(line, "\t\t");
                    String tok_title = tok.nextToken();
                    String tok_flag = tok.nextToken();
                    if (tok_flag.equals("true")) {
                        true_count++;
                        flag = true;
                    }
                    else {
                        false_count++;
                        flag = false;
                    }
                    try {
                        BufferedReader br3 = new BufferedReader(new FileReader(Title));//title.txtの内容
                        while ((line2 = br3.readLine()) != null) {
                            StringTokenizer tok2 = new StringTokenizer(line2, "\t\t");
                            String tok2_title = tok2.nextToken();
                            String tok2_ransu = tok2.nextToken();
                            if (tok2_ransu.equals("1") && tok_title.equals(tok2_title)) {
                                if(flag){
                                    ari_match++;
                                }else{
                                    nasi_miss++;
                                }
                                break;
                            }
                        }
                        br3.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    try {
                        BufferedReader br4 = new BufferedReader(new FileReader(TEST));//test.txtの内容
                        while ((line3 = br4.readLine()) != null) {
                            StringTokenizer tok3 = new StringTokenizer(line3, "\t\t");
                            String tok3_title = tok3.nextToken();
                            String tok3_ransu = tok3.nextToken();
                            if (tok3_ransu.equals("0") && tok_title.equals(tok3_title)) {
                                if(flag){
                                    ari_miss++;
                                }else{
                                    nasi_match++;
                                }
                                break;
                            }
                        }
                        br4.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                br2.close();
                Result.createNewFile();
                try {
                    int all = ((ari_match + nasi_match)*100) / (true_count + false_count);
                    int ari = (ari_match*100) / true_count;
                    int nasi = (nasi_match*100) / false_count;
                    double a = (12 + 45) / (true_count + false_count);
                    double aa = (ari_match + nasi_match);
                    double aaa = (true_count + false_count);
                    Log.e("seikairitu", "" + ari_match + ":" + ari_miss + ":" + nasi_match + ":" + nasi_miss + ":" + true_count + ":" + false_count);
                    Log.e("seikairitu", "" + all + ":" + ari + ":" + nasi + ":::" + a + ":" + aa + ":" + aaa);
                    BufferedWriter bw = new BufferedWriter(new FileWriter(Result, true));
                    bw.write(file_number + "\t\t" + "HyojiJikan(mms):" + Hyoji_Jikan + "\t\t" + "HyojiKensu:" + hyojisuu + "\t\t" + "KijiKensu:" + kijisuu + "\t\t" + "Seikai(o-o):" + ari_match +  "\t\t" + "Seikai(x-x):" + nasi_match +  "\t\t" + "Miss(o-x):" + ari_miss +  "\t\t" + "Miss(x-o):" + nasi_miss + "\t\t" + "SeikaiALL(%):" + all + "\t\t" + "SeikaiARI(%):" + ari + "\t\t" + "SeikaiNASI(%):" + nasi);
                    bw.newLine();
                    bw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
