package com.jofre.managercheck.receiveraddlist.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jofre.managercheck.ManagerCheckApp;
import com.jofre.managercheck.R;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.entities.Checks;
import com.jofre.managercheck.lib.base.ImageLoader;
import com.jofre.managercheck.receiveraddlist.ReceiverAddListPresenter;
import com.jofre.managercheck.receiveraddlist.ui.alerts.ReceiverAddListImageAdapter;
import com.jofre.managercheck.receiveraddlist.ui.adapters.OnItemClickListener;
import com.jofre.managercheck.receiveraddlist.ui.adapters.ReceiverAddListAdapter;
import com.jofre.managercheck.receiveraddmain.Communicator;
import com.jofre.managercheck.receiveraddmain.ui.ReceiverMainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiverAddListFragment extends Fragment implements ReceiverAddListView, OnItemClickListener, View.OnLongClickListener {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.frameList)
    FrameLayout frameList;
    @Inject
    ReceiverAddListAdapter adapter;
    @Inject
    ReceiverAddListPresenter presenter;

    boolean is_action_mode = false;
    private Communicator communicator;
    private List<Check> checks_select = new ArrayList<>();
    private List<Check> checks = new ArrayList<>();
    private int counter = 0;

    public ReceiverAddListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receiver_list, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjection();
        presenter.onCreate();
        communicator = (Communicator) getActivity();
        getChecks();

    }

    public void deleteClick(){
        presenter.removeCheck(checks_select);
        ReceiverAddListAdapter checkAdapter = (ReceiverAddListAdapter) adapter;
        checkAdapter.updateAdapter(checks_select);
        communicator.clearActionMode();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        ManagerCheckApp app = (ManagerCheckApp) getActivity().getApplication();
        app.getReceiverAddListComponent(this, this, this, getActivity()).inject(this);
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }

    @Override
    public void emptyList(String empty) {
        Snackbar.make(frameList, empty, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void errorShowList(String error) {
        Snackbar.make(frameList, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void errorDelete(String error) {
        Snackbar.make(frameList, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void successDelete(String success) {
        Snackbar.make(frameList, success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void removeCheck(Check check) {
        adapter.removeCheck(check);
    }

    @Override
    public void setChecks(List<Check> checks) {
        if (checks != null && checks.size() > 0) {
            this.checks = checks;
            adapter.setChecks(checks);
        }
    }

    @Override
    public void onDeleteClick(List<Check> checks) {
        presenter.removeCheck(checks);
    }

    @Override
    public void onShowImageClick(ImageLoader imageLoader, Check check) {
        new ReceiverAddListImageAdapter(getContext(), imageLoader, check);
    }

    @Override
    public void onEditClick(Check check) {
        intentEditCheck(check);
    }

    @Override
    public void onClickLinearLayout(View v, int position, boolean isSelected) {
        if (!isSelected) {
            checks_select.add(checks.get(position));
            counter = counter + 1;
            communicator.updateCounter(counter);
        } else {
            checks_select.remove(checks.get(position));
            counter = counter - 1;
            communicator.updateCounter(counter);
        }
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void getChecks() {
        presenter.getChecks();
    }

    public void intentEditCheck(Check check) {
        Intent intent = new Intent(getActivity(), ReceiverMainActivity.class);
        intent.putExtra("update", true);
        intent.putExtra("id", check.getId_check());
        intent.putExtra("number", check.getNumber());
        intent.putExtra("amount", check.getAmount());
        intent.putExtra("expiration", check.getExpiration());
        intent.putExtra("origin", check.getOrigin());
        intent.putExtra("photo", check.getPhoto());
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        is_action_mode = true;
        communicator.actionMode();
        return true;
    }

//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        inflater.inflate(R.menu.menu_action_mode, menu);
////        // menu.getItem(0).setVisible(false);//usuario
////        menu.getItem(1).setVisible(false);//permiso
////        menu.getItem(2).setVisible(false);//lifuba
////        menu.getItem(3).setVisible(false);// adeful
////        menu.getItem(4).setVisible(false);// puesto
////        menu.getItem(5).setVisible(false);// posicion
////        menu.getItem(6).setVisible(false);// cargo
////        // menu.getItem(7).setVisible(false);//cerrar
////        menu.getItem(8).setVisible(false);// guardar
////        menu.getItem(9).setVisible(false);// Subir
////        menu.getItem(10).setVisible(false); // eliminar
////        menu.getItem(11).setVisible(false); // consultar
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        // noinspection SimplifiableIfStatement
//        if (id == R.id.item_delete) {
//            Snackbar.make(frameList, "DELETE", Snackbar.LENGTH_SHORT).show();
//            /*Intent usuario = new Intent(getActivity(),
//                    NavigationDrawerUsuario.class);
//            startActivity(usuario);*/
//
//            return true;
//        }
//
//        if (id == android.R.id.home) {
//            if (is_action_mode) {
//                communicator.clearActionMode();
//                clearListSelected();
//                is_action_mode = false;
//                adapter.notifyDataSetChanged();
//            } else {
//                NavUtils.navigateUpFromSameTask(getActivity());
//            }
//            return true;
//        }
//        return true;
//    }

    public void clearListSelected() {
        checks_select.clear();
        adapter.notifyDataSetChanged();
        counter = 0;
    }


}
