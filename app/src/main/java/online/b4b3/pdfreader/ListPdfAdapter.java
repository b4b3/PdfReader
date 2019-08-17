package online.b4b3.pdfreader;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;

public class ListPdfAdapter extends RecyclerView.Adapter<ListPdfAdapter.ListViewHolder> {
    private ArrayList<Pdf> listPdf;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListPdfAdapter(ArrayList<Pdf> list){
        this.listPdf = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false );
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull final ListViewHolder holder, final int position){
        Pdf pdf = listPdf.get(position);

        holder.tvJudul.setText(pdf.getJudul());
        holder.tvFile.setText(pdf.getNamafile());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listPdf.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPdf.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        TextView tvJudul, tvFile;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tv_item_judul);
            tvFile = itemView.findViewById(R.id.tv_item_deskripsi);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Pdf data);
    }
}
