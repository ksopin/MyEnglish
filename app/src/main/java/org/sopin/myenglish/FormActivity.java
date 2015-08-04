package org.sopin.myenglish;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class FormActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuHelper menuHelper = new MenuHelper(this, R.id.action_form);

        if (menuHelper.startActivity(item.getItemId())) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean addItem(View view) {

        WordTable table = WordTableFactory.createService(getBaseContext());

        WordEntity wordEntity = (WordEntity) table.getNew();

        EditText editWord = (EditText) findViewById(R.id.editWord);
        String word = editWord.getText().toString();

        EditText editTranslate = (EditText) findViewById(R.id.editTranslate);
        String translate = editTranslate.getText().toString();

        wordEntity.setWord(word);
        wordEntity.setTranslate(translate);
        wordEntity.setIsPhrase(false);
        wordEntity.setLevel(0);
        wordEntity.setLearnt(false);
        wordEntity.setStatus(0);

        table.save(wordEntity);

        MenuHelper menuHelper = new MenuHelper(this, R.id.action_form);
        menuHelper.startActivity(R.id.action_main);

        return true;
    }

}
