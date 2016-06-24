package org.hyb.demo.fragmenttest;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookDetailFragment extends Fragment {
    public static final String ITEM_ID="item_id";
    BookContent.Book book;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey(ITEM_ID))
        {
            book=BookContent.ITEMS_MAP.get(getArguments().getInt(ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.book_detail_fragment,container,false);
        if(book!=null)
        {
            ((TextView)view.findViewById(R.id.book_title)).setText(book.title);
            ((TextView)view.findViewById(R.id.book_desc)).setText(book.desc);
        }

        return view;
    }

}
