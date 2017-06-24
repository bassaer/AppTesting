package com.github.bassaer.apptesting;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private InputMethodManager mInputMethodManager;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.main_list);
        final EditText editText = (EditText)findViewById(R.id.input);
        Button button = (Button)findViewById(R.id.send_button);
        final List<Item> items = new ArrayList<>();
        mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        Item firstItem = new Item(R.drawable.ic_android, getString(R.string.app_name));
        items.add(firstItem);

        final ListAdapter adapter = new ListAdapter(this, 0, items);
        mListView.setAdapter(adapter);

        hideKeyboard();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = items.get(position);
                Toast.makeText(MainActivity.this, item.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editText.getText().toString();
                Item item = new Item();
                item.setText(inputText);
                if (inputText.length() == 0) {
                    item.setIcon(R.drawable.ic_android);
                    item.setText(getString(R.string.app_name));
                }else if (inputText.length() % 2 == 0) {
                    item.setIcon(R.drawable.ic_thumb_up);
                } else {
                    item.setIcon(R.drawable.ic_thumb_down);
                }
                items.add(item);
                adapter.notifyDataSetChanged();
                editText.setText("");
                hideKeyboard();
                scrollToEnd();
            }
        });

    }

    public void scrollToEnd() {
        mListView.smoothScrollToPosition(mListView.getCount() - 1);
    }

    /**
     * Hide software keyboard
     */
    public void hideKeyboard() {
        //Hide keyboard
        mInputMethodManager.hideSoftInputFromWindow(
                mListView.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        //Move focus to background
        mListView.requestFocus();
    }
}
