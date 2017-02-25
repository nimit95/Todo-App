package com.nimit.todo.model;

/**
 * Created by Nimit Agg on 25-02-2017.
 */

public class Todo {
    private String Title;
    private String description;

    public void setDate(String date) {
        this.date = date;
    }

    private String date;
    private int priority;
    private long Time ;

    public Todo(String title, String description, String date ,int priority, long time) {
        Title = title;
        this.description = description;
        this.date = date;
        this.priority = priority;
        Time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }

    public String getDate() {
        return date;
    }
}
