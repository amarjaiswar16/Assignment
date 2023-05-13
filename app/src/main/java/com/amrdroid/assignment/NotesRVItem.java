package com.amrdroid.assignment;

import android.content.ClipData;
import android.view.MenuItem;

import java.util.ArrayList;

public interface NotesRVItem {
    void onRvMenuItem(int position, MenuItem menuItem, ArrayList list);
}
