package com.example.juan.examen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddAveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddAveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAveFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddAveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddAveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public AddAveFragment newInstance(String param1, String param2) {
        AddAveFragment fragment = new AddAveFragment();
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
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_ave,container,false);

        final Button button = (Button) view.findViewById(R.id.buttonTest);
        final TextView textNombreCo = (TextView) (view.findViewById(R.id.textTest));
        final TextView textNombreCi = (TextView) (view.findViewById(R.id.etNombreCi));
        final TextView textDescripcion = (TextView) (view.findViewById(R.id.etDescripcion));
        final TextView textGeneralidades = (TextView) (view.findViewById(R.id.etGeneralidades));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomCo = textNombreCo.getText().toString();
                String nomCi = textNombreCi.getText().toString();
                String descripcion = textDescripcion.getText().toString();
                String generalidades = textGeneralidades.getText().toString();

                Ave newAve = new Ave(nomCo,nomCi,descripcion,generalidades);

                DataBaseManager.insertDB(newAve,DataBaseManager.AVES_TABLE_NAME);

                Toast.makeText(v.getContext(),"Agregado",Toast.LENGTH_SHORT);
            }


        });



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
