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

        getActionBar().setHomeButtonEnabled(true);

        table = WordTableFactory.createService(getBaseContext());

        resultSet = table.fetchCarouselList();

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

        WordEntity word = (WordEntity) resultSet.fetch();

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

                Integer position;
                if (resultSet.isLast()) {
                    position = 0;
                } else {
                    position = resultSet.getPosition();
                }
                
                resultSet = table.fetchRecentAdded();
                resultSet.moveToPosition(position);

                WordEntity nextWord = (WordEntity) resultSet.fetch();
                this.exportWordToView(nextWord);

                break;

            default:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
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
