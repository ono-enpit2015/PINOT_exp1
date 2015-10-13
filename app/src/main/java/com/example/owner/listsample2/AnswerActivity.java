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
import java.util.StringTokenizer;

/**
 * Created by Owner on 2015/10/05.
 */
public class AnswerActivity extends AppCompatActivity {
    SparseBooleanArray checkedItemPositions;
    ListView listView;
    HashMap map = new HashMap();
    String line;
    String title;
    String line2;
    boolean flag;
    int match;
    final String LOGDIR = Environment.getExternalStorageDirectory().getPath()+"/data/";
    File Title;
    File Test_new;
    File Match;
    final String RESULT = LOGDIR + "result.txt";
    File Result = new File(RESULT);
    String file_number;
    final String TEST1_new = LOGDIR + "/title1/" + "test_new.txt";
    final String TEST2_new = LOGDIR + "/title2/" + "test_new.txt";
    final String TEST3_new = LOGDIR + "/title3/" + "test_new.txt";
    final String TEST4_new = LOGDIR + "/title4/" + "test_new.txt";
    final String TEST5_new = LOGDIR + "/title5/" + "test_new.txt";
    final String TEST6_new = LOGDIR + "/title6/" + "test_new.txt";
    final String TEST7_new = LOGDIR + "/title7/" + "test_new.txt";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_of_answeractivity);

        Intent intent = getIntent();
        file_number = intent.getStringExtra("FILE_NUMBER");

        switch (file_number){
            case "file11":
                Title = new File(TEST1_1);
                Test_new = new File(TEST1_new);
                Match = new File(SDFILE1_1fin);
                break;
            case "file12":
                Title = new File(TEST1_2);
                Test_new = new File(TEST1_new);
                Match = new File(SDFILE1_2fin);
                break;
            case "file13":
                Title = new File(TEST1_3);
                Test_new = new File(TEST1_new);
                Match = new File(SDFILE1_3fin);
                break;
            case "file14":
                Title = new File(TEST1_4);
                Test_new = new File(TEST1_new);
                Match = new File(SDFILE1_4fin);
                break;
            case "file15":
                Title = new File(TEST1_5);
                Test_new = new File(TEST1_new);
                Match = new File(SDFILE1_5fin);
                break;
            case "file21":
                Title = new File(TEST2_1);
                Test_new = new File(TEST2_new);
                Match = new File(SDFILE2_1fin);
                break;
            case "file22":
                Title = new File(TEST2_2);
                Test_new = new File(TEST2_new);
                Match = new File(SDFILE2_2fin);
                break;
            case "file23":
                Title = new File(TEST2_3);
                Test_new = new File(TEST2_new);
                Match = new File(SDFILE2_3fin);
                break;
            case "file24":
                Title = new File(TEST2_4);
                Test_new = new File(TEST2_new);
                Match = new File(SDFILE2_4fin);
                break;
            case "file25":
                Title = new File(TEST2_5);
                Test_new = new File(TEST2_new);
                Match = new File(SDFILE2_5fin);
                break;
            case "file31":
                Title = new File(TEST3_1);
                Test_new = new File(TEST3_new);
                Match = new File(SDFILE3_1fin);
                break;
            case "file32":
                Title = new File(TEST3_2);
                Test_new = new File(TEST3_new);
                Match = new File(SDFILE3_2fin);
                break;
            case "file33":
                Title = new File(TEST3_3);
                Test_new = new File(TEST3_new);
                Match = new File(SDFILE3_3fin);
                break;
            case "file34":
                Title = new File(TEST3_4);
                Test_new = new File(TEST3_new);
                Match = new File(SDFILE3_4fin);
                break;
            case "file35":
                Title = new File(TEST3_5);
                Test_new = new File(TEST3_new);
                Match = new File(SDFILE3_5fin);
                break;
            case "file41":
                Title = new File(TEST4_1);
                Test_new = new File(TEST4_new);
                Match = new File(SDFILE4_1fin);
                break;
            case "file42":
                Title = new File(TEST4_2);
                Test_new = new File(TEST4_new);
                Match = new File(SDFILE4_2fin);
                break;
            case "file43":
                Title = new File(TEST4_3);
                Test_new = new File(TEST4_new);
                Match = new File(SDFILE4_3fin);
                break;
            case "file44":
                Title = new File(TEST4_4);
                Test_new = new File(TEST4_new);
                Match = new File(SDFILE4_4fin);
                break;
            case "file45":
                Title = new File(TEST4_5);
                Test_new = new File(TEST4_new);
                Match = new File(SDFILE4_5fin);
                break;
            case "file51":
                Title = new File(TEST5_1);
                Test_new = new File(TEST5_new);
                Match = new File(SDFILE5_1fin);
                break;
            case "file52":
                Title = new File(TEST5_2);
                Test_new = new File(TEST5_new);
                Match = new File(SDFILE5_2fin);
                break;
            case "file53":
                Title = new File(TEST5_3);
                Test_new = new File(TEST5_new);
                Match = new File(SDFILE5_3fin);
                break;
            case "file54":
                Title = new File(TEST5_4);
                Test_new = new File(TEST5_new);
                Match = new File(SDFILE5_4fin);
                break;
            case "file55":
                Title = new File(TEST5_5);
                Test_new = new File(TEST5_new);
                Match = new File(SDFILE5_5fin);
                break;
            case "file61":
                Title = new File(TEST6_1);
                Test_new = new File(TEST6_new);
                Match = new File(SDFILE6_1fin);
                break;
            case "file62":
                Title = new File(TEST6_2);
                Test_new = new File(TEST6_new);
                Match = new File(SDFILE6_2fin);
                break;
            case "file63":
                Title = new File(TEST6_3);
                Test_new = new File(TEST6_new);
                Match = new File(SDFILE6_3fin);
                break;
            case "file64":
                Title = new File(TEST6_4);
                Test_new = new File(TEST6_new);
                Match = new File(SDFILE6_4fin);
                break;
            case "file65":
                Title = new File(TEST6_5);
                Test_new = new File(TEST6_new);
                Match = new File(SDFILE6_5fin);
                break;
            case "file71":
                Title = new File(TEST7_1);
                Test_new = new File(TEST7_new);
                Match = new File(SDFILE7_1fin);
                break;
            case "file72":
                Title = new File(TEST7_2);
                Test_new = new File(TEST7_new);
                Match = new File(SDFILE7_2fin);
                break;
            case "file73":
                Title = new File(TEST7_3);
                Test_new = new File(TEST7_new);
                Match = new File(SDFILE7_3fin);
                break;
            case "file74":
                Title = new File(TEST7_4);
                Test_new = new File(TEST7_new);
                Match = new File(SDFILE7_4fin);
                break;
            case "file75":
                Title = new File(TEST7_5);
                Test_new = new File(TEST7_new);
                Match = new File(SDFILE7_5fin);
        }
        
        //ListViewに表示させる内容
        ArrayList<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Title));
            try {
                while((line = br.readLine()) != null){
                    list.add(line);
                }
                //リストの並びをシャッフル
                Collections.shuffle(list);
                BufferedWriter shufflewriter = new BufferedWriter(new FileWriter(Title,false));
                int i = 0;
                while(i < list.size()){
                    title = list.get(i);
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
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice, list);
        ListView lv = (ListView)findViewById(R.id.listView3);

        //ListViewにアダプタ登録
        lv.setAdapter(adapter);

        for(int i = 0; i < 20; i++){
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
                BufferedReader b = new BufferedReader(new FileReader(Title));
                try {
                    BufferedWriter pw = new BufferedWriter(new FileWriter(Test_new,true));
                    int i = 0;
                    while((title = b.readLine()) != null){
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
                    b.close();
                    Title.delete();
                    Test_new.renameTo(Title);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            try {
                BufferedReader br2 = new BufferedReader(new FileReader(Match));//10
                //答え合わせ
                match = 0;
                while ((title = br2.readLine()) != null) {
                    try {
                        BufferedReader br3 = new BufferedReader(new FileReader(Title));//20
                        while ((line2 = br3.readLine()) != null) {
                            StringTokenizer token = new StringTokenizer(line2, "\t\t");
                            String t = token.nextToken();
                            String f = token.nextToken();
                            if (f.equals("true")) flag = true;
                            else flag = false;
                            System.out.println(title);
                            System.out.println(t);
                            System.out.println(f);
                            if (title.equals(t)) {
                                if (flag) {
                                    match++;
                                    Log.e("match", "" + match);
                                }
                                break;
                            }
                        }
                        br3.close();
                    }catch (IOException e){
                            e.printStackTrace();
                    }
                }
                Result.createNewFile();
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(Result, true));
                    bw.write(file_number + "\t\t" + match);
                    bw.newLine();
                    bw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                br2.close();
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
