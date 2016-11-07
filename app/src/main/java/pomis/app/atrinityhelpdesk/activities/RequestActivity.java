package pomis.app.atrinityhelpdesk.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import pomis.app.atrinityhelpdesk.R;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        ((TextView)findViewById(R.id.tv_request_status)).setText(getIntent().getStringExtra("status"));
    }


}
