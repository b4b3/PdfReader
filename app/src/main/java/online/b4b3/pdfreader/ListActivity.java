package online.b4b3.pdfreader;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private RecyclerView rvPdf;
    private ArrayList<Pdf> list = new ArrayList<>();
    private ListPdfAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        rvPdf = findViewById(R.id.rv_pdf);
        rvPdf.setHasFixedSize(true);

        list.addAll(DataPdf.getListData());
        showRecyclerList();



// pada method onCreate, panggil fab dari xml

    }

    private void showRecyclerList() {
        rvPdf.setLayoutManager(new LinearLayoutManager(this));
        ListPdfAdapter listPdfAdapter = new ListPdfAdapter(list);
        rvPdf.setAdapter(listPdfAdapter);

        listPdfAdapter.setOnItemClickCallback(new ListPdfAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Pdf data) {
                showSelectedPdf(data);
            }
        });
    }

    private void showSelectedPdf(Pdf pdf) {
        Intent moveWithDataIntent = new Intent(ListActivity.this, MainActivity.class);
        moveWithDataIntent.putExtra(MainActivity.EXTRA_PDF, pdf.getJudul());
        moveWithDataIntent.putExtra(MainActivity.EXTRA_FILE, pdf.getNamafile());

        startActivity(moveWithDataIntent);

        //Toast.makeText(this, "Kamu memilih " + buku.getJudul(), Toast.LENGTH_SHORT).show();
    }
}
