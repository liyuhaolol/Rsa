package spa.lyh.cn.rsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tangyin.cmp.token.TokenUtil;


public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        String token = TokenUtil.getToken("123");
        Log.e("liyuhao",token);
        tv.setText(token);
    }
}
