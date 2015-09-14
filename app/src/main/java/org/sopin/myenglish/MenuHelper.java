package org.sopin.myenglish;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class MenuHelper {

    private Integer current;
    private Context context;

    public MenuHelper(Context context, Integer current){
        this.current = current;
        this.context = context;
    }

    public boolean startActivity(Integer itemId) {

        if (this.current.equals(itemId)) {
            return true;
        }

        Intent intent;

        switch (itemId) {
            case R.id.action_main:
                intent = new Intent(context, MainActivity.class);
                break;
            case R.id.action_form:
                intent = new Intent(context, FormActivity.class);
                break;
            case R.id.action_carousel:
                intent = new Intent(context, CarouselActivity.class);
                break;
            case R.id.action_untranslated:
                intent = new Intent(context, UntranslatedActivity.class);
                break;
            case R.id.action_quiz:
                intent = new Intent(context, QuizActivity.class);
                break;
            case R.id.action_stats:
                intent = new Intent(context, StatsActivity.class);
                break;
            case R.id.action_settings:
                return true;
            default:
                intent = new Intent(context, MainActivity.class);
        }

        context.startActivity(intent);
        return true;
    }
}
