package org.sopin.myenglish;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.sopin.db.ResultSet;

import java.util.ArrayList;


public class UntranslatedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        WordTable table = WordTableFactory.createService(getBaseContext());

        ResultSet result = table.fetchNotTranslated();

        if (result.getCount() > 0) {
            do {
                WordEntity wordEntity = (WordEntity) result.fetch();
                items.add(items.size(), wordEntity.getWord() + " - " + wordEntity.getTranslate());
            } while (result.moveToNext());

            aa.notifyDataSetChanged();
        }

        setContentView(R.layout.activity_untranslated);

        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(aa);

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
