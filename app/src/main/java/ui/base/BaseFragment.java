package ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;


import common.ConstantString;
import ui.view.imageloader.ImageLoadProxy;


public class BaseFragment extends Fragment implements ConstantString {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

       // RequestManager.cancelAll(this);
        ImageLoadProxy.getImageLoader().clearMemoryCache();
    }

  //  protected void executeRequest(Request request) {
  //     RequestManager.addRequest(request, this);
  //  }
}
