package com.tufruti.tufruti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;


public class LoginActivity extends Activity {
    private UiLifecycleHelper uiHelper;

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (exception != null) {
            exception.printStackTrace();
            return;
        }
        if (state == SessionState.OPENED || state == SessionState.OPENED_TOKEN_UPDATED) {
            Intent intent = new Intent(getApplicationContext(), GameListActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data, null);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }
}
