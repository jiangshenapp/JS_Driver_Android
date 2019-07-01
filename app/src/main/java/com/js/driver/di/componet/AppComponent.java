package com.js.driver.di.componet;


import com.js.driver.di.module.AppModule;
import com.js.http.ApiFactory;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by huyg on 2018/8/22.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    ApiFactory ApiFactory();
}
