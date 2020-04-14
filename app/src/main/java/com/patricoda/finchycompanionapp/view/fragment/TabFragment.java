package com.patricoda.finchycompanionapp.view.fragment;

import androidx.fragment.app.Fragment;

public class TabFragment extends Fragment {
    private String tabTitle;

    public TabFragment(final String tabTitle) {
        super();
        this.tabTitle = tabTitle;
    }

    public String getTabTitle() {
        return tabTitle;
    }
}
