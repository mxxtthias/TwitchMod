package tv.twitch.android.shared.ui.menus.dropdown;

import android.view.View;
import android.widget.ArrayAdapter;

import tv.twitch.android.shared.ui.menus.core.MenuModel;


public class DropDownMenuModel<T> extends MenuModel {
    public interface DropDownMenuItemSelection<T> {
        void onDropDownItemSelected(DropDownMenuModel<T> dropDownMenuModel, int i);
    }

    public DropDownMenuModel(ArrayAdapter<T> arrayAdapter2, int i, String str, String str2, String str3, View.OnClickListener onClickListener, DropDownMenuItemSelection<T> dropDownMenuItemSelection2) {}
}