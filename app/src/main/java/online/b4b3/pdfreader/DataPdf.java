package online.b4b3.pdfreader;

import java.util.ArrayList;

public class DataPdf {
    static String [][] data = new String[][]{
            {"PPh Pasal 4 ayat 2", "pdfpertama.pdf"},
            {"Permohonan Pbk", "pdfkedua.pdf"},
    };

    public static ArrayList<Pdf> getListData(){
        ArrayList<Pdf> list = new ArrayList<>();
        for (String[] aData : data) {
            Pdf Pdf = new Pdf();
            Pdf.setJudul(aData[0]);
            Pdf.setNamafile(aData[1]);

            list.add(Pdf);
        }
        return list;
    }


}
