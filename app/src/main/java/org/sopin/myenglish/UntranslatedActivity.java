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
import android.widget.TextView;
import android.widget.Toast;

import org.sopin.db.ResultSet;

import java.util.ArrayList;


public class UntranslatedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<WordEntity> items = new ArrayList<WordEntity>();
        ArrayAdapter<WordEntity> aa = new ArrayAdapter<WordEntity>(this, android.R.layout.simple_list_item_1, items);

        WordTable table = WordTableFactory.createService(getBaseContext());

        ResultSet result = table.fetchNotTranslated();

        Integer i = 0;

        if (result.getCount() > 0) {
            do {
                WordEntity wordEntity = (WordEntity) result.fetch();

                //wordEntity = wordEntity.clone();


                items.add(i, wordEntity);
                i++;

            } while (result.moveToNext());

            aa.notifyDataSetChanged();
        }

        setContentView(R.layout.activity_untranslated);

        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(aa);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String item = ((TextView) view).getText().toString() + " + " + position + " : " + id;
                WordEntity wordItem = (WordEntity) ((ListView) findViewById(R.id.listView))
                        .getAdapter()
                        .getItem(position);

                //String item = wordItem.toString();

                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getBaseContext(), ViewActivity.class);
                intent.putExtra("wordId", wordItem.getId());
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
        MenuHelper menuHelper = new MenuHelper(this, R.id.action_untranslated);
        if (menuHelper.startActivity(item.getItemId())) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
