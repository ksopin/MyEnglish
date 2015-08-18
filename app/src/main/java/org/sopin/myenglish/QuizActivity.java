package org.sopin.myenglish;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.sopin.db.ResultSet;

import static java.lang.Thread.*;


public class QuizActivity extends Activity {

    private ResultSet resultSet;

    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WordTable table = WordTableFactory.createService(getBaseContext());

        setContentView(R.layout.activity_quiz);

        resultSet = table.fetchRand();
        quiz = new Quiz(WordTableFactory.createService(getBaseContext()));

        if (resultSet.getCount() > 0) {
            WordEntity word = (WordEntity) resultSet.fetch();
            quiz.setWord(word);
            this.exportToView(quiz);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    private void exportToView(Quiz quiz) {
        TextView viewWord = (TextView) findViewById(R.id.textWord);
        viewWord.setText(quiz.getWord());

        TextView viewTranslate = (TextView) findViewById(R.id.textTranslate);
        viewTranslate.setText("");

        TextView viewLevel = (TextView) findViewById(R.id.textLevel);
        viewLevel.setText(quiz.getWordEntity().getLevel().toString());

        // TODO: via cycle
        Button button1 = (Button) findViewById(R.id.buttonOption1);
        button1.setText(quiz.getOptions().get(0));
        button1.setTextColor(Color.parseColor(getString(R.color.color_default)));

        Button button2 = (Button) findViewById(R.id.buttonOption2);
        button2.setText(quiz.getOptions().get(1));
        button2.setTextColor(Color.parseColor(getString(R.color.color_default)));

        Button button3 = (Button) findViewById(R.id.buttonOption3);
        button3.setText(quiz.getOptions().get(2));
        button3.setTextColor(Color.parseColor(getString(R.color.color_default)));

        Button button4 = (Button) findViewById(R.id.buttonOption4);
        button4.setText(quiz.getOptions().get(3));
        button4.setTextColor(Color.parseColor(getString(R.color.color_default)));

    }

    @SuppressLint("ResourceAsColor")
    public void check(View view) throws InterruptedException {
        Button button = (Button) findViewById(view.getId());

        if (quiz.isCorrect(button.getText().toString())) {

            quiz.levelUp();

            toNext();

            return;
        }
        button.setTextColor(Color.parseColor(getString(R.color.color_error)));



        quiz.levelDown();
    }

    private void toNext() {

        if (resultSet.isLast()) {
            resultSet.moveToFirst();
            //Intent intent = new Intent(this, MainActivity.class);
            //startActivity(intent);
        } else {
            resultSet.moveToNext();
        }
        WordEntity word = (WordEntity) resultSet.fetch();
        quiz.setWord(word);
        this.exportToView(quiz);
    }
}
