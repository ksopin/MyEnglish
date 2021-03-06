package org.sopin.myenglish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.sopin.db.ResultSet;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<WordEntity> items = new ArrayList<WordEntity>();
        ArrayAdapter<WordEntity> aa = new ArrayAdapter<WordEntity>(this, android.R.layout.simple_list_item_1, items);

        WordTable table = WordTableFactory.createService(getBaseContext());

        ResultSet result = table.fetchInProcess();

        if (result.getCount() > 0) {
            do {
                WordEntity wordEntity = (WordEntity) result.fetch();
                items.add(items.size(), wordEntity);
            } while (result.moveToNext());

            aa.notifyDataSetChanged();
        }

        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(aa);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WordEntity wordItem = (WordEntity) ((ListView) findViewById(R.id.listView))
                        .getAdapter()
                        .getItem(position);

                Intent intent = new Intent(getBaseContext(), ViewActivity.class);
                intent.putExtra("wordId", wordItem.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuHelper menuHelper = new MenuHelper(this, R.id.action_main);

        if (menuHelper.startActivity(item.getItemId())) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
