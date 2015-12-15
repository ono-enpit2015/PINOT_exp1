package com.example.owner.listsample2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    //private final String DOWNLOAD_FILE_URL = "http://koblab.cs.ehime-u.ac.jp/~ono/exp/title1/title1_1.txt";
    private ProgressDialog progressDialog;
    private ProgressHandler progressHandler;
    private AsyncFileDownload asyncfiledownload;
    //final String LOGDIR = Environment.getExternalStorageDirectory().getPath()+"/data/"+"/exp/";
    File directory;
    String LOGDIR = Environment.getExternalStorageDirectory().getPath();
    String LOG = Environment.getExternalStorageDirectory().getPath()+"/data/exp/hensu.txt";
    String LOG2 = Environment.getExternalStorageDirectory().getPath()+"/data/exp/sintyoku.txt";
    String EXP = Environment.getExternalStorageDirectory().getPath()+"/data/exp/";
    static String path;
    String resultFileName;
    String date;
    CharSequence userName;
    File Change = new File(LOG);
    File HowMany = new File(LOG2);
    String filename;
    int folda_num;
    int how_many;
    int[] time;
    static int DisplayTime;
    static int DisplayNum;      //選択画面で表示する視認画面で表示する見出しの件数
    static int DisplayNumAll;   //視認画面で表示する見出しの件数
    AsyncTask<Void, Void, String> task;

    ArrayList<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
    SimpleAdapter adapter;
    ListView lv;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ListViewに表示させる内容
        /*ArrayList<String> list = new ArrayList<String>();
        list.add("パターン１");
        list.add("パターン２");
        list.add("パターン３");
        list.add("パターン４");
        list.add("パターン５");
        list.add("パターン６");
        list.add("パターン７");

        //ListViewとデータをつなぐアダプタを作成
        //アイテムのレイアウトはAndroid組み込みの物(android.R.layout.simple_list_item_1)を使用　→変更　自作のレイアウト(R.layout.item,R.id.item_name)に
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.item,R.id.item_name, list);
        ListView lv = (ListView)findViewById(R.id.listView1);
        //ListViewにアダプタ登録
        lv.setAdapter(adapter);*/

        // ListView を取得
        //lv = (ListView) findViewById(R.id.listView1);
        // SimpleAdapterに渡すArrayList作成
        //createData();
        // リストビューに渡すアダプタを生成
        /*adapter = new SimpleAdapter(
                this,
                list,//ArrayList
                R.layout.item,//ListView内の1項目を定義したxml
        new String[] { "pattern", "comment"},//mapのキー
        new int[] {R.id.item_name, R.id.comment});//item.xml内のid*/
        // アダプタをセット
        //lv.setAdapter(adapter);

        //アイテムがクリックされたときの処理
        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {*/
                //クリックされたものを取得
                /*String get_parent = (String) parent.getClass().getSimpleName();
                String get_position = String.valueOf(position);
                String get_id = String.valueOf(id);*/
                /*Intent intent = new Intent();//this,TestActivity.class
                intent.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.TestActivity");

                //String number = (String) parent.getItemAtPosition(position);      // 選択された項目の要素名を取得
                //Toast.makeText(MainActivity.this, number, Toast.LENGTH_SHORT).show();
                //intent.putExtra("NUMBER", number);
                intent.putExtra("NUMBER", position);

                startActivity(intent);
            }
        });*/

        textView = (TextView) findViewById(R.id.message);
        //textView.setText("");

        // クリックイベントを取得したいボタン
        Button downloadButton = (Button)findViewById(R.id.downloadButton);
        // ボタンに OnClickListener インターフェースを実装する
        downloadButton.setOnClickListener(downloadButtonOnClickListener);

        Button kousinButton = (Button)findViewById(R.id.kousinButton);
        kousinButton.setOnClickListener(kousinButtonOnClickListener);

        Button sendButton = (Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(sendButtonOnClickListener);

        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(startButtonOnClickListener);

        progressHandler = new ProgressHandler();

        makeEXP();
        makeEXP_fin();

        if(Change.exists() == true){
            hensu();
        }

        if(HowMany.exists() == true){
            how_many();
        }
    }

    /*
    private void createData() {
        for (int n = 1; n <= 7; n++) {
            int k = 0;
            Map data = new HashMap();
            data.put("pattern", "パターン" + n);

            for (int m = 1; m <= 5; m++) {
                File P = new File(EXP + "/title" + n + "/title" + n + "_"+m+".txt");
                if(P.exists())k++;
            }

            if(k == 0){
                data.put("comment", "終了です");
            }else {
                data.put("comment", "あと" + k + "回です");
            }
            list.add(data);
        }
    }*/

    //全てのtitleフォルダを格納するexpフォルダを作成する
    void makeEXP(){
        File exp = new File(LOGDIR+"/data/exp/");       //先にexpを作成していなければエラーになる
        if(exp.exists() == false){
            if(exp.mkdir()){
            }else{
                Toast ts = Toast.makeText(this, "exp作成に失敗", Toast.LENGTH_LONG);
                ts.show();
            }
        }
    }

    //終了したフォルダを格納するexp_finishフォルダを作成する
    void makeEXP_fin(){
        File expfin = new File(LOGDIR+"/data/exp_finish/");
        if(expfin.exists() == false){
            if(expfin.mkdir()){
            }else{
                Toast ts = Toast.makeText(this, "exp_fin作成に失敗", Toast.LENGTH_LONG);
                ts.show();
            }
        }
    }

    //既にexpフォルダが存在する場合、そのフォルダ名を変更する
    void renameEXP(){
        File exp = new File(LOGDIR+"/data/exp/");
        String date = getNowDate();
        File exp_new = new File(LOGDIR+"/data/exp_finish/"+date+"/");
        exp_new.mkdir();
        if (exp.exists() == true) {
            exp.renameTo(exp_new);
            Toast ts = Toast.makeText(this, "expをexp" + date + "に変更しました", Toast.LENGTH_LONG);
            ts.show();
        }
    }

    //現在時刻を返す
    public static String getNowDate(){
        final DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH.mm");
        final Date date = new Date(System.currentTimeMillis());
        return df.format(date);
    }

    private  View.OnClickListener startButtonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if (how_many != 0) {
                Intent intent = new Intent();//this,TestActivity.class
                intent.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.TestActivity");

                intent.putExtra("FILENAME", filename);
                intent.putExtra("DISPLAYTIME", DisplayTime);
                intent.putExtra("NUMBER", folda_num);

                startActivity(intent);
            }
        }
    };

    private  View.OnClickListener downloadButtonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            renameEXP();
            makeEXP();
            //Toast.makeText(MainActivity.this, "download", Toast.LENGTH_SHORT).show();
            initFileLoader();
            showDialog(0);
            progressHandler.progressDialog = progressDialog;
            progressHandler.asyncfiledownload = asyncfiledownload;

            if (progressDialog != null && asyncfiledownload != null){
                progressDialog.setProgress(0);
                progressHandler.sendEmptyMessage(0);
            }else{
                Toast ts = Toast.makeText(MainActivity.this, "NULLエラー", Toast.LENGTH_LONG);
                ts.show();
            }
        }
    };

    //ダウンロードを開始する
    private void initFileLoader()
    {
        for(int i=1;i <= 7;i++){    //titlei_j.txt
            //String LOGDIR = Environment.getExternalStorageDirectory().getPath()+"/data/"+"/exp/"+"/title"+i+"/";
            //directory = new File(LOGDIR+"/data/exp/title"+i+"/");
            directory = new File(LOGDIR+"/data/exp/title"+i+"/");
            if(directory.exists() == false){
                if(directory.mkdir()){
                }else{
                    Toast ts = Toast.makeText(this, "ディレクトリ作成に失敗", Toast.LENGTH_LONG);
                    ts.show();
                }
            }
            for(int j=1;j <= 5;j++){
                String DOWNLOAD_FILE_URL = "http://koblab.cs.ehime-u.ac.jp/~ono/exp/title"+i+"/title"+i+"_"+j+".txt";
                File outputFile = new File(directory, "title"+i+"_"+j+".txt");
                asyncfiledownload = new AsyncFileDownload(this,DOWNLOAD_FILE_URL, outputFile);
                asyncfiledownload.execute();
            }
            for(int k=1;k <= 5;k++){
                String DOWNLOAD_FILE_URL = "http://koblab.cs.ehime-u.ac.jp/~ono/exp/title"+i+"/test"+i+"_"+k+".txt";
                File outputFile = new File(directory, "test"+i+"_"+k+".txt");
                asyncfiledownload = new AsyncFileDownload(this,DOWNLOAD_FILE_URL, outputFile);
                asyncfiledownload.execute();
            }
        }
        directory = new File(LOGDIR+"/data/exp/");
        String DOWNLOAD_FILE_URL = "http://koblab.cs.ehime-u.ac.jp/~ono/exp/hensu.txt";
        File outputFile = new File(directory, "hensu.txt");
        asyncfiledownload = new AsyncFileDownload(this,DOWNLOAD_FILE_URL, outputFile);
        asyncfiledownload.execute();

        String DOWNLOAD_FILE_URL2 = "http://koblab.cs.ehime-u.ac.jp/~ono/exp/sintyoku.txt";//追加
        File outputFile2 = new File(directory, "sintyoku.txt");
        asyncfiledownload = new AsyncFileDownload(this,DOWNLOAD_FILE_URL2, outputFile2);
        asyncfiledownload.execute();
    }

    private  View.OnClickListener kousinButtonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            hensu();
            how_many();
            shuffle();
            Toast.makeText(MainActivity.this, "実験の条件を変更します", Toast.LENGTH_SHORT).show();
        }
    };

    private void hensu(){        //更新ボタンが押されたとき、ホーム画面が表示された時に実行される
        try {
            BufferedReader br = new BufferedReader(new FileReader(Change));
            String line = br.readLine();
            StringTokenizer token = new StringTokenizer(line, "\t");
            int count = token.countTokens() - 2;     //hensu.txt内の表示時間の個数
            time = new int[count];
            for(int i=0; i < count; i++){
                time[i] = Integer.valueOf(token.nextToken());
            }
            DisplayNum = Integer.valueOf(token.nextToken());
            DisplayNumAll = Integer.valueOf(token.nextToken());
            br.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void how_many(){        //更新ボタンが押されたとき、ホーム画面が表示された時に実行される
        how_many = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(HowMany));
            String line;
            StringTokenizer tok;
            String tok_titlenum;
            String tok_flag;
            while((line = br.readLine()) != null){
                tok = new StringTokenizer(line, "\t");
                tok_titlenum = tok.nextToken();
                tok_flag = tok.nextToken();
                if(tok_flag.equals("true")){
                    how_many++;
                    filename = tok_titlenum;
                }
            }
            if(!(how_many == 0)) {
                textView.setText("あと残り" + how_many + "回です");
                folda_num = Character.getNumericValue(filename.charAt(5));
                DisplayTime = time[folda_num-1];      //表示時間を定める
            }else{
                textView.setText("実験終了です。結果を送信して下さい。");
                filename = null;
            }
            br.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    void shuffle(){
        //sintyoku.txtを編集
        try {
            BufferedReader br = new BufferedReader(new FileReader(HowMany));
            String line;
            String line1;
            ArrayList<String> list1 = new ArrayList<String>();
            while((line = br.readLine()) != null){
                list1.add(line);
            }
            Collections.shuffle(list1);
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(HowMany,false));
            int i = 0;
            while(i < list1.size()){
                line1 = list1.get(i);
                bw.write(line1);
                bw.newLine();
                i++;
            }
            bw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private  View.OnClickListener sendButtonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            final EditText editText = (EditText) findViewById(R.id.edit_message);   //画面のテキストフィールド取得
            userName = editText.getText();                                  //画面で入力された文字を取得
            if (TextUtils.isEmpty(userName)) {                                           //設定された値の文字が空（null or ブランク）であるかを判定
                // 未入力の場合
                userName = "名前が未入力です";
                Toast.makeText(getApplicationContext(), userName, Toast.LENGTH_SHORT).show();
            }else{
                //入力されている場合
                File result = new File(LOGDIR+"/data/exp/result.txt");
                date = getNowDate();
                File result_send = new File(LOGDIR+"/data/exp/"+userName+"-"+date+".txt");
                path = LOGDIR+"/data/exp/"+userName+"-"+date+".txt";
                resultFileName = userName+"-"+date+".txt";
                result.renameTo(result_send);

                // タスクを実行
                DataSend();
                task.execute();

                //new UploadAsyncTask(MainActivity.this).execute(path);
                //Toast.makeText(getApplicationContext(), "結果を送信しました", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void DataSend(){
        // タスク
        task = new AsyncTask<Void, Void, String>() {

            /**
             * 準備
             */
            @Override
            protected void onPreExecute() {

                // 進捗ダイアログを開始
                MainActivity.this.progressDialog = new ProgressDialog(MainActivity.this);
                MainActivity.this.progressDialog.setMessage("Now Loading ...");
                MainActivity.this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                MainActivity.this.progressDialog.setCancelable(true);
                MainActivity.this.progressDialog.show();
            }

            /**
             * 実行
             */
            @Override
            protected String doInBackground(Void... params) {

                FTPClient ftp = null;
                FileInputStream fis = null;
                FileOutputStream fos = null;

                try {

                    ftp = new FTPClient();

                    // エンコーディング
                    ftp.setControlEncoding("SJIS");     //コネクトの前に設定     Windowsサーバー:"Windows-31J"or"SJIS"

                    // 接続前タイムアウト：15秒
                    ftp.setDefaultTimeout(15000);
                    Log.e("デバック", "0");
                    // 接続
                    ftp.connect("133.71.201.164", 21);       //ホスト名「koblab.cs.ehime-u.ac.jp」に対して、ポート「21」に接続する   133.71.201.142
                    Log.e("デバック", "1");
                    // 接続エラーの場合
                    if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {

                        return "サーバーに接続できません";
                    }
                    // 接続後タイムアウト：10秒
                    ftp.setSoTimeout(15000);

                    // ログイン
                    if (!ftp.login("ono", "xftmrnkoblab")) {

                        return "サーバーの認証に失敗しました";
                    }
                    Log.e("デバック", "2");
                    // ファイル種別：アスキーモード
                    ftp.setFileType(FTP.ASCII_FILE_TYPE);//BINARY_FILE_TYPE
                    // PASVモード
                    ftp.enterLocalPassiveMode();
                    // タイムアウト：10秒
                    ftp.setDataTimeout(20000);
                    Log.e("デバック", "3");

                    // 受信元のディレクトリを作成
                    //String path = Environment.getExternalStorageDirectory().getPath() + "/SAMPLE/";
                    //new File(path).mkdir();

                    // 受信   サーバーから「hoge1.txt」を、Android端末に「hoge2.txt」としてダウンロードする
                    /*fos = new FileOutputStream(path + "hoge2.txt");
                    if (!ftp.retrieveFile("/TEST/hoge1.txt", fos)) {

                        return "ファイルの受信に失敗しました";
                    }*/
                    //Log.e("デバック", "3");
                    // 送信   Android端末から「hoge2.txt」を、サーバーに「hoge3.txt」としてアップロードする
                    fis = new FileInputStream(path);
                    //Log.e("デバック", ""+path);
                    //Log.e("デバック", ""+resultFileName);
                    if (!ftp.storeFile("/home/ono/result/"+resultFileName, fis)) {

                        return "ファイルの送信に失敗しました";
                    }
                    //Log.e("デバック", "4");
                } catch (SocketException e) {

                    return "FTP通信に失敗しました（１）";

                } catch (IOException e) {

                    return "FTP通信に失敗しました（２）";

                } finally {

                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                        }
                    }

                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                        }
                    }

                    if (ftp != null) {
                        try {
                            // ログアウト
                            ftp.logout();
                        } catch (IOException e) {
                        }
                        try {
                            // 切断
                            ftp.disconnect();
                        } catch (IOException e) {
                        }
                    }
                }

                return "送受信に成功しました";
            }

            /**
             * 完了
             */
            @Override
            protected void onPostExecute(String param) {

                if (MainActivity.this.progressDialog.isShowing()) {

                    // 進捗ダイアログを終了
                    MainActivity.this.progressDialog.dismiss();
                }

                Toast.makeText(MainActivity.this, param, Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    protected void onPause(){
        super.onPause();
        cancelLoad();
    }

    @Override
    protected void onStop(){
        super.onStop();
        cancelLoad();
    }

    private void cancelLoad()
    {
        if(asyncfiledownload != null){
            asyncfiledownload.cancel(true);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id){
        switch(id){
            case 0:
                progressDialog = new ProgressDialog(this);
                //progressDialog.setIcon(R.drawable.ic_launcher);
                progressDialog.setTitle("Downloading files..");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Hide",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cancelLoad();
                            }
                        });
        }
        return progressDialog;
    }

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
