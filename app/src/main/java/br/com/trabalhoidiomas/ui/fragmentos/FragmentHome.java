package br.com.trabalhoidiomas.ui.fragmentos;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidplot.pie.PieChart;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;

import br.com.trabalhoidiomas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PieChart pieChart;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            pieChart = getView().findViewById(R.id.plot);
            configurePieChart();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
    private void configurePieChart() {
        // Adicione segmentos ao gráfico de pizza
        Segment s1 = new Segment("Segment 1", 30);
        Segment s2 = new Segment("Segment 2", 40);
        Segment s3 = new Segment("Segment 3", 30);

        // Crie um SegmentFormatter personalizado
        SegmentFormatter sf1 = new SegmentFormatter(Color.BLUE);
        SegmentFormatter sf2 = new SegmentFormatter(Color.GREEN);
        SegmentFormatter sf3 = new SegmentFormatter(Color.RED);

        // Adicione segmentos ao PieChart com seus respectivos SegmentFormatters
        pieChart.addSeries(s1, sf1);
        pieChart.addSeries(s2, sf2);
        pieChart.addSeries(s3, sf3);

        // Atualize o gráfico
        pieChart.redraw();
    }
}