package com.example.owner.listsample2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
