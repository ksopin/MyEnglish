package org.sopin.myenglish;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class StatsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        getActionBar().setHomeButtonEnabled(true);

        WordTable table = WordTableFactory.createService(getBaseContext());



        TextView viewTextValTotal = (TextView) findViewById(R.id.textValTotal);
        viewTextValTotal.setText(table.fetchCount().toString());

        viewTextValTotal = (TextView) findViewById(R.id.textValL0);
        viewTextValTotal.setText(table.fetchCount(0).toString());

        viewTextValTotal = (TextView) findViewById(R.id.textValL1);
        viewTextValTotal.setText(table.fetchCount(1).toString());

        viewTextValTotal = (TextView) findViewById(R.id.textValL2);
        viewTextValTotal.setText(table.fetchCount(2).toString());

        viewTextValTotal = (TextView) findViewById(R.id.textValL3);
        viewTextValTotal.setText(table.fetchCount(3).toString());

        viewTextValTotal = (TextView) findViewById(R.id.textValL4);
        viewTextValTotal.setText(table.fetchCount(4).toString());

        viewTextValTotal = (TextView) findViewById(R.id.textValL5);
        viewTextValTotal.setText(table.fetchCount(5).toString());

        viewTextValTotal = (TextView) findViewById(R.id.textValL6);
        viewTextValTotal.setText(table.fetchCount(6).toString());

        viewTextValTotal = (TextView) findViewById(R.id.textValL7);
        viewTextValTotal.setText(table.fetchCount(7).toString());

        viewTextValTotal = (TextView) findViewById(R.id.textValL8);
        viewTextValTotal.setText(table.fetchCount(8).toString());

        viewTextValTotal = (TextView) findViewById(R.id.textValL9);
        viewTextValTotal.setText(table.fetchCount(9).toString());

        viewTextValTotal = (TextView) findViewById(R.id.textValL10plus);
        viewTextValTotal.setText(table.fetchCount(10, ">=").toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuHelper menuHelper = new MenuHelper(this, R.id.action_stats);

        if (menuHelper.startActivity(item.getItemId())) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
