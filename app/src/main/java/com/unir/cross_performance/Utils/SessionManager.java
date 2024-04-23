package com.unir.cross_performance.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.unir.cross_performance.MainActivity;
import com.unir.cross_performance.MenuAdministradorActivity;

public class SessionManager {
    private Context _context;
    private SharedPreferences _preference;
    private SharedPreferences.Editor _editor;

    private static final String KEY_IS_LOGIN = "is_login"; // boolean
    private static final String KEY_USER_ID = "user_id"; // int
    private static final String KEY_ACCESS_TOKEN = "access_token"; // string

    public SessionManager(Context context) {
        this._context = context;
        this._preference = _context.getSharedPreferences("CREDENCIALES", Context.MODE_PRIVATE);
        this._editor = _preference.edit();
    }

    public void createSession(int user_id, String access_token) {
        _editor.putBoolean(KEY_IS_LOGIN, true);
        _editor.putInt(KEY_USER_ID, user_id);
        _editor.putString(KEY_ACCESS_TOKEN, access_token);
        _editor.commit();

        checkLogin();
    }

    public void destroySession() {
        _editor.clear();
        _editor.commit();

        checkLogin();
    }

    public boolean isLogin() {
        return _preference.getBoolean(KEY_IS_LOGIN, false);
    }

    public void checkLogin() {
        if (!this.isLogin()) {
            Intent intent = new Intent(_context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        } else {
            Intent intent = new Intent(_context, MenuAdministradorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        }
    }
}
