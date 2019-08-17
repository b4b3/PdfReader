package online.b4b3.pdfreader;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_PDF= "extra_pdf";
    public static final String EXTRA_FILE = "namafile";

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(getIntent().getStringExtra(EXTRA_PDF));

        setContentView(R.layout.activity_main);
        /*
        TextView txtJudul = findViewById(R.id.tv_item_judul);
        String judul = getIntent().getStringExtra(EXTRA_PDF);
        txtJudul.setText(judul);

        TextView txtJudul = findViewById(R.id.tv_item_judul);
        String judul = getIntent().getStringExtra(EXTRA_FILE);
        txtJudul.setText(judul);
        */



        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset(getIntent().getStringExtra(EXTRA_FILE))
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "TESTING BUTTON CLICK 1", Toast.LENGTH_SHORT).show();

                File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+getIntent().getStringExtra(EXTRA_FILE));
                if (file.exists())
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.fromFile(file);
                    intent.setDataAndType(uri, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try
                    {
                        startActivity(intent);
                    }
                    catch(ActivityNotFoundException e)
                    {
                        Toast.makeText(getApplicationContext(), "No Application available to view pdf", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }
}
