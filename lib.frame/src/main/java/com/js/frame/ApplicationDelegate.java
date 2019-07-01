package com.xlgcx.frame;

import android.app.Application;
import android.content.Context;

import com.xlgcx.frame.module.IAppLife;
import com.xlgcx.frame.module.ManifestParser;

import java.util.List;

/**
 * Created by huyg on 2019/1/30.
 */
public class ApplicationDelegate implements IAppLife {
    private List<IAppLife> list;

    public ApplicationDelegate(Context context) {
        list = new ManifestParser(context).parse();
    }


    @Override
    public void attachBaseContext(Context context) {
        if (list != null && list.size() > 0) {
            for (IAppLife iAppLife : list) {
                iAppLife.attachBaseContext(context);
            }
        }
    }

    @Override
    public void onCreate(Application application) {
        if (list != null && list.size() > 0) {
            for (IAppLife iAppLife : list) {
                iAppLife.onCreate(application);
            }
        }
    }

    @Override
    public void onTerminate(Application application) {
        if (list != null && list.size() > 0) {
            for (IAppLife iAppLife : list) {
                iAppLife.onTerminate(application);
            }
        }
    }
}
