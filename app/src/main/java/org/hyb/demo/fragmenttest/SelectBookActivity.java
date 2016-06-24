package org.hyb.demo.fragmenttest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class SelectBookActivity extends Activity implements BookListFragment.CallBack{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.book_twopanel);
    }

    @Override
    public void OnItemSelected(Integer id) {
        Bundle bundle=new Bundle();
        bundle.putInt(BookDetailFragment.ITEM_ID,id);
        BookDetailFragment fragment=new BookDetailFragment();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.book_detail_container,fragment).commit();
    }
}
