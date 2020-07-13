package tv.twitch.android.mod.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.LinkedHashMap;

import tv.twitch.android.mod.models.PreferenceItem;
import tv.twitch.android.mod.utils.Logger;


public abstract class PreferenceCore implements SharedPreferences.OnSharedPreferenceChangeListener  {
    private final LinkedHashMap<String, PreferenceItem> mLocalPreferences = new LinkedHashMap<>();

    private final SharedPreferences mPref;


    public PreferenceCore(Context context) {
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
        mPref.registerOnSharedPreferenceChangeListener(this);
    }

    protected void registerLocalPreference(PreferenceItem item) {
        mLocalPreferences.put(item.getKey(), getOrDefault(item));
    }

    protected PreferenceItem getLocalPreference(String key) {
        return mLocalPreferences.get(key);
    }

    public void updateBoolean(String key, boolean val) {
        if (TextUtils.isEmpty(key)) {
            Logger.error("Empty key");
            return;
        }

        mPref.edit().putBoolean(key, val).apply();
    }

    public void updateString(String key, String val) {
        if (TextUtils.isEmpty(key)) {
            Logger.error("Empty key");
            return;
        }

        if (TextUtils.isEmpty(val)) {
            Logger.error("Empty val");
            return;
        }

        mPref.edit().putString(key, val).apply();
    }

    public boolean getBoolean(String key, boolean def) {
        return mPref.getBoolean(key, def);
    }

    public String getString(String key, String def) {
        return mPref.getString(key, def);
    }

    public PreferenceItem getOrDefault(PreferenceItem defaultPreference) {
        String res = getString(defaultPreference.getKey(), null);
        if (res != null)
            return defaultPreference.lookup(res);
        else
            return defaultPreference;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (TextUtils.isEmpty(key)) {
            Logger.error("Empty key");
            return;
        }

        PreferenceItem item = mLocalPreferences.get(key);
        if (item != null) {
            String newVal = sharedPreferences.getString(key, item.getDefault().getValue());
            registerLocalPreference(item.lookup(newVal));
        }
    }
}