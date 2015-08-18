package org.sopin.myenglish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.sopin.db.ResultSet;


public class CarouselActivity extends Activity {

    private ResultSet resultSet;

    private WordTable table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        table = WordTableFactory.createService(getBaseContext());

        resultSet = table.fetchRecentAdded();

        setContentView(R.layout.activity_carousel);

        if (resultSet.getCount() > 0) {
            WordEntity word = (WordEntity) resultSet.fetch();
            this.exportWordToView(word);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_edit:

                break;
            case R.id.action_delete:
                WordEntity word = (WordEntity) resultSet.fetch();
                table.delete(word);
                Intent intent = new Intent(this, ViewActivity.class);
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

    }

    public void toPrev(View view) {

        if (resultSet.isFirst()) {
            resultSet.moveToLast();
        } else {
            resultSet.moveToPrevious();
        }

        WordEntity word = (WordEntity) resultSet.fetch();
        this.exportWordToView(word);
    }

    public void toNext(View view) {

        if (resultSet.isLast()) {
            resultSet.moveToFirst();
        } else {
            resultSet.moveToNext();
        }
        WordEntity word = (WordEntity) resultSet.fetch();
        this.exportWordToView(word);
    }
}
