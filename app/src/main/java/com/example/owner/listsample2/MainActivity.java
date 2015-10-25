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
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    String path;
    String resultFileName;
    String date;
    CharSequence userName;
    File Change = new File(LOG);
    static int Time1;
    static int Time2;
    static int Time3;
    static int Time4;
    static int Time5;
    static int Time6;
    static int Time7;
    static int HyojiKensu;
    static int KijiKensu;
    AsyncTask<Void, Void, String> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ListViewに表示させる内容
        ArrayList<String> list = new ArrayList<String>();
        list.add("パターン１");
        list.add("パターン２");
        list.add("パターン３");
        list.add("パターン４");
        list.add("パターン５");
        list.add("パターン６");
        list.add("パターン７");

        //ListViewとデータをつなぐアダプタを作成
        //アイテムのレイアウトはAndroid組み込みの物(android.R.layout.simple_list_item_1)を使用　→変更　自作のレイアウト(R.layout.item,R.id.item_name)に
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        ListView lv = (ListView)findViewById(R.id.listView1);
        //ListViewにアダプタ登録
        lv.setAdapter(adapter);

        //アイテムがクリックされたときの処理
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //クリックされたものを取得
                /*String get_parent = (String) parent.getClass().getSimpleName();
                String get_position = String.valueOf(position);
                String get_id = String.valueOf(id);*/
                Intent intent = new Intent();//this,TestActivity.class
                intent.setClassName("com.example.owner.listsample2", "com.example.owner.listsample2.TestActivity");

                String number = (String) parent.getItemAtPosition(position);      // 選択された項目の要素名を取得
                //Toast.makeText(MainActivity.this, number, Toast.LENGTH_SHORT).show();
                intent.putExtra("NUMBER", number);

                startActivity(intent);
            }
        });

        // クリックイベントを取得したいボタン
        Button downloadButton = (Button)findViewById(R.id.downloadButton);
        // ボタンに OnClickListener インターフェースを実装する
        downloadButton.setOnClickListener(downloadButtonOnClickListener);

        Button kousinButton = (Button)findViewById(R.id.kousinButton);
        kousinButton.setOnClickListener(kousinButtonOnClickListener);

        Button sendButton = (Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(sendButtonOnClickListener);

        progressHandler = new ProgressHandler();

        makeEXP_fin();

        if(Change.exists() == true){
            //Toast ts = Toast.makeText(this, "あったよ", Toast.LENGTH_LONG);
            //ts.show();
            change_hensu();
        }else{
            Toast ts = Toast.makeText(this, "ファイル(hensu.txt)は存在しません", Toast.LENGTH_LONG);
            ts.show();
        }
    }

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
        final DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
        final Date date = new Date(System.currentTimeMillis());
        return df.format(date);
    }

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
    }

    private  View.OnClickListener kousinButtonOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "変数の値を更新します", Toast.LENGTH_SHORT).show();
            change_hensu();
        }
    };

    private void change_hensu(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(Change));
            String line = br.readLine();
            StringTokenizer token = new StringTokenizer(line, "\t");
            Time1 = Integer.valueOf(token.nextToken());
            Time2 = Integer.valueOf(token.nextToken());
            Time3 = Integer.valueOf(token.nextToken());
            Time4 = Integer.valueOf(token.nextToken());
            Time5 = Integer.valueOf(token.nextToken());
            Time6 = Integer.valueOf(token.nextToken());
            Time7 = Integer.valueOf(token.nextToken());
            HyojiKensu = Integer.valueOf(token.nextToken());
            KijiKensu = Integer.valueOf(token.nextToken());
            //System.out.println(Time1);
            br.close();
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
                    ftp.setControlEncoding("Windows-31J");     //コネクトの前に設定     Windowsサーバー:"Windows-31J"or"SJIS"

                    // 接続前タイムアウト：15秒
                    ftp.setDefaultTimeout(15000);
                    //Log.e("デバック", "0");
                    // 接続
                    ftp.connect("koblab.cs.ehime-u.ac.jp", 21);       //ホスト名「koblab.cs.ehime-u.ac.jp」に対して、ポート「21」に接続する   133.71.201.142
                    //Log.e("デバック", "0.5");
                    // 接続エラーの場合
                    if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {

                        return "サーバーに接続できません";
                    }
                    //Log.e("デバック", "1");
                    // 接続後タイムアウト：10秒
                    ftp.setSoTimeout(15000);

                    // ログイン
                    if (!ftp.login("ono", "xftmrnkoblab")) {

                        return "サーバーの認証に失敗しました";
                    }
                    //Log.e("デバック", "2");
                    // ファイル種別：アスキーモード
                    ftp.setFileType(FTP.BINARY_FILE_TYPE);//ASCII_FILE_TYPE
                    // PASVモード
                    ftp.enterLocalPassiveMode();
                    // タイムアウト：10秒
                    ftp.setDataTimeout(20000);

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
                    if (!ftp.storeFile("/home/ono/public_html/result/"+resultFileName, fis)) {

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
