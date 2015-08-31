package org.sopin.myenglish;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class FormActivity extends Activity {

    WordEntity word;

    WordTable table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        table = WordTableFactory.createService(getBaseContext());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.containsKey("wordId")){
                Integer wordId = (Integer)getIntent().getExtras().get("wordId");
                word = table.getById(wordId);
                bindForm();
            }
        }

        if (word == null) {
            word = (WordEntity) table.getNew();
            word.setLevel(0);
        }

    }

    private void bindForm() {
        EditText editWord = (EditText) findViewById(R.id.editWord);
        editWord.setText(word.getWord());

        EditText editTranslate = (EditText) findViewById(R.id.editTranslate);
        editTranslate.setText(word.getTranslate());

        EditText editContext = (EditText) findViewById(R.id.editContext);
        editContext.setText(word.getContext());
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

        EditText editWord = (EditText) findViewById(R.id.editWord);
        String wordString = editWord.getText().toString();

        EditText editTranslate = (EditText) findViewById(R.id.editTranslate);
        String translate = editTranslate.getText().toString();

        EditText editContext = (EditText) findViewById(R.id.editContext);
        String context = editContext.getText().toString();

        word.setWord(wordString.trim());
        word.setTranslate(translate);
        word.setContext(context);
        word.setIsPhrase(wordString.trim().contains(" "));

        table.save(word);

        MenuHelper menuHelper = new MenuHelper(this, R.id.action_form);
        menuHelper.startActivity(R.id.action_main);

        return true;
    }

}
