package tv.twitch.android.mod.settings;


import android.view.View;


import tv.twitch.android.mod.bridges.ResourcesManager;
import tv.twitch.android.mod.models.Preferences;
import tv.twitch.android.mod.utils.Helper;
import tv.twitch.android.shared.ui.menus.SettingsPreferencesController;
import tv.twitch.android.shared.ui.menus.core.MenuModel;
import tv.twitch.android.shared.ui.menus.dropdown.DropDownMenuModel;
import tv.twitch.android.shared.ui.menus.infomenu.InfoMenuModel;
import tv.twitch.android.shared.ui.menus.message.MenuMessageModel;
import tv.twitch.android.shared.ui.menus.togglemenu.ToggleMenuModel;


public class MenuFactory {
    public static MenuModel getInfoMenu(String title, String desc, View.OnClickListener listener) {
        return new InfoMenuModel(title, desc, null, null, null, null, listener);
    }

    public static MenuModel getClickableUrlInfoMenu(String title, String desc, final String url) {
        return new InfoMenuModel(title, desc, null, null, null, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.openUrl(url);
            }
        });
    }

    public static MenuModel getInfoMenu(String title) {
        return new InfoMenuModel(title, null, null, null, null, null, null);
    }

    public static MenuModel getInfoMenu(String title, String desc) {
        return new InfoMenuModel(title, desc, null, null, null, null, null);
    }

    public static MenuModel getToggleMenu(SettingsPreferencesController.SettingsPreference controller, boolean state) {
        Preferences preference = controller.getPreference();
        return new ToggleMenuModel(ResourcesManager.getString(preference.getTitle()), ResourcesManager.getString(preference.getDescription()), null, state, true, null, null, false, null, null, null, controller, null);
    }

    public static MenuModel getMessage(String text) {
        return new MenuMessageModel(text, 17, 0, false);
    }

    public static MenuModel getSpannedMessage(String text) {
        return new MenuMessageModel(text, 17, 0, true);
    }

    public static MenuModel getDropDownMenu(SettingsPreferencesController.SettingsPreference controller, PreferenceArrayAdapter adapter, Object state) {
        return new DropDownMenuModel<>(adapter, adapter.findItemPos(state), ResourcesManager.getString(controller.getPreference().getTitle()), ResourcesManager.getString(controller.getPreference().getDescription()), null, null, new SettingsController.onMenuItemSelected(adapter));
    }
}
