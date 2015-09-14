package org.sopin.myenglish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.sopin.db.ResultSet;


public class ViewActivity extends Activity {

    private WordTable table;

    private WordEntity word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_view);
        Integer wordId = (Integer)getIntent().getExtras().get("wordId");

        table = WordTableFactory.createService(getBaseContext());
        word = table.getById(wordId);
        this.exportWordToView(word);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_main:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.action_edit:
                intent = new Intent(this, FormActivity.class);
                intent.putExtra("wordId", word.getId());
                startActivity(intent);
                break;
            case R.id.action_delete:
                table.delete(word);
                intent = new Intent(this, UntranslatedActivity.class);
                startActivity(intent);
                break;

            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    public void exportWordToView(WordEntity word) {
        if (word.getId() <= 0) {
            return;
        }

        TextView viewWord = (TextView) findViewById(R.id.viewWord);
        viewWord.setText(word.getWord());

        TextView viewTranslate = (TextView) findViewById(R.id.veiwTranslate);
        viewTranslate.setText(word.getTranslate());

        TextView viewLevel = (TextView) findViewById(R.id.viewLevel);
        viewLevel.setText(word.getLevel().toString());

        TextView textWordId = (TextView) findViewById(R.id.textWordId);
        textWordId.setText(word.getId().toString());

        TextView textContext = (TextView) findViewById(R.id.textContext);
        textContext.setText(word.getContext());

    }


}
