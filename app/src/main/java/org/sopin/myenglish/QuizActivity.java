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
import android.widget.Toast;

import org.sopin.db.ResultSet;


public class QuizActivity extends Activity {


    private Quiz quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setHomeButtonEnabled(true);

        setContentView(R.layout.activity_quiz);

        quiz = new Quiz(WordTableFactory.createService(getBaseContext()));


        QuizEntity quizEntity = quiz.current();

        Toast.makeText(getBaseContext(), quiz.count().toString(), Toast.LENGTH_SHORT).show();


        this.exportToView(quiz.current());
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

    private void exportToView(QuizEntity quizEntity) {
        TextView viewWord = (TextView) findViewById(R.id.textWord);
        viewWord.setText(quizEntity.getWord());

        TextView viewTranslate = (TextView) findViewById(R.id.textTranslate);
        viewTranslate.setText("");

        TextView viewLevel = (TextView) findViewById(R.id.textLevel);
        viewLevel.setText(quizEntity.getWordEntity().getLevel().toString());

        // TODO: via cycle
        Button button1 = (Button) findViewById(R.id.buttonOption1);
        button1.setText(quizEntity.getOptions().get(0));
        button1.setTextColor(Color.parseColor(getString(R.color.color_default)));

        Button button2 = (Button) findViewById(R.id.buttonOption2);
        button2.setText(quizEntity.getOptions().get(1));
        button2.setTextColor(Color.parseColor(getString(R.color.color_default)));

        Button button3 = (Button) findViewById(R.id.buttonOption3);
        button3.setText(quizEntity.getOptions().get(2));
        button3.setTextColor(Color.parseColor(getString(R.color.color_default)));

        Button button4 = (Button) findViewById(R.id.buttonOption4);
        button4.setText(quizEntity.getOptions().get(3));
        button4.setTextColor(Color.parseColor(getString(R.color.color_default)));

    }

    @SuppressLint("ResourceAsColor")
    public void check(View view) throws InterruptedException {
        Button button = (Button) findViewById(view.getId());

        Integer ff = quiz.current().getOptions().size();

        if (quiz.current().isCorrect(button.getText().toString())) {

            quiz.levelUp(quiz.current());

            toNext();

            return;
        }
        button.setTextColor(Color.parseColor(getString(R.color.color_error)));

        quiz.levelDown(quiz.current());
        TextView viewLevel = (TextView) findViewById(R.id.textLevel);
        viewLevel.setText(quiz.current().getWordEntity().getLevel().toString());
    }

    private void toNext() {

        if (quiz.isLast()) {
            quiz.moveToFirst();
            Toast.makeText(getBaseContext(), "Check Point", Toast.LENGTH_SHORT).show();
        } else {
            quiz.moveToNext();
        }
        this.exportToView(quiz.current());
    }
}
