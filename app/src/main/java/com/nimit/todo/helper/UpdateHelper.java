package com.nimit.todo.helper;

import com.nimit.todo.model.Todo;

/**
 * Created by Nimit Agg on 25-02-2017.
 */

public interface UpdateHelper {
    void updateItem(Todo todo, int index);
    void addItem (Todo todo, int index);
    void showFragment(Todo todo, int index);
}
