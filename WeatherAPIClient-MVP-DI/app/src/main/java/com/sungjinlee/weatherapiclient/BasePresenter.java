package com.sungjinlee.weatherapiclient;

/**
 * Created by Sungjin Lee on 1/1/18.
 */

public interface BasePresenter<T> {

    /**
     * Binds presenter with a view. The Presenter will perform initialization here.
     *
     * @param view the view associated with this presenter
     */
    void takeView(T view);

    /**
     * Drops the reference to the view when destroyed
     */
    void dropView();

}