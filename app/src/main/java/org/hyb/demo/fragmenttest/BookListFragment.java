package org.hyb.demo.fragmenttest;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/6/23.
 */
public class BookListFragment extends ListFragment {
    public CallBack mCallBack;
    public interface CallBack
    {
        public void OnItemSelected(Integer id);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListAdapter tmp=new ArrayAdapter<BookContent.Book>(getActivity(),android.R.layout.simple_list_item_activated_1,android.R.id.text1,BookContent.ITEMS);
        //this.getListView().setAdapter(new ArrayAdapter<BookContent.Book>(this,android.R.layout.simple_list_item_1,android.R.));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(!(activity instanceof CallBack))
        {
            throw new IllegalArgumentException("activity必须实现了CallBack接口");
        }
        mCallBack=(CallBack)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack=null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCallBack.OnItemSelected(BookContent.ITEMS_MAP.get(position).id);
    }
}
