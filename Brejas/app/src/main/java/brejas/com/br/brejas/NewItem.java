package brejas.com.br.brejas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class NewItem extends AppCompatActivity {

    List<String> package_types = new ArrayList<String>();

    Spinner sp_package_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        package_types .add("Bottle");
        package_types .add("Can");
        package_types .add("Other");

        sp_package_type = (Spinner) findViewById(R.id.sp_package_type);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, package_types);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_package_type.setAdapter(adapter);

    }
}
