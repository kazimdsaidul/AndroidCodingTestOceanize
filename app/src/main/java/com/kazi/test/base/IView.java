package com.kazi.test.base;


/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-03.
 */
public interface IView {
    void noInternetConnectionFound();

    void showProgress();

    void hiddenProgress();

    void onFailure(String message);
}
