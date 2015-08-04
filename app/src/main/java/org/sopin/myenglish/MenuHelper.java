package org.sopin.myenglish;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

/**
 * Created by konstantin on 7/30/15.
 */
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
            case R.id.action_view:
                intent = new Intent(context, ViewActivity.class);
                break;
            case R.id.action_untranslated:
                intent = new Intent(context, UntranslatedActivity.class);
                break;
            case R.id.action_settings:
                return true;
            default:
                return false;
        }


        context.startActivity(intent);
        return true;
    }



}
