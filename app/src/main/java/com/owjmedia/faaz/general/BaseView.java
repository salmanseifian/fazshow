package com.owjmedia.faaz.general;

/**
 * Created by salman on 11/5/17.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void setLoadingIndicator(boolean active);

    void showMessage(String message);
}
