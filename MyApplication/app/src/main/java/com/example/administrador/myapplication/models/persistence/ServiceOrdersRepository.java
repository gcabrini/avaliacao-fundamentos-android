package com.example.administrador.myapplication.models.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

public final class ServiceOrdersRepository {

    private static class Singleton {
        public static final ServiceOrdersRepository INSTANCE = new ServiceOrdersRepository();
    }

    private ServiceOrdersRepository() {
        super();
    }

    public static ServiceOrdersRepository getInstance() {
        return Singleton.INSTANCE;
    }

    public void save(ServiceOrder serviceOrder) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (serviceOrder.getId() == null) {
            serviceOrder.setActive(false);
            db.insert(ServiceOrderContract.TABLE, null, ServiceOrderContract.getContentValues(serviceOrder));
        } else {
            String where = ServiceOrderContract.ID + " = ?";
            String[] args = {serviceOrder.getId().toString()};
            db.update(ServiceOrderContract.TABLE, ServiceOrderContract.getContentValues(serviceOrder), where, args);
        }
        db.close();
        helper.close();
    }

    public void delete(ServiceOrder serviceOrder) {

        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (serviceOrder.getId() != null) {
            String where = ServiceOrderContract.ID + " = ?";
            String[] args = {serviceOrder.getId().toString()};
            serviceOrder.setActive(
                    serviceOrder.isActive() ? false : true
            );
            db.update(ServiceOrderContract.TABLE, ServiceOrderContract.getContentValues(serviceOrder), where, args);
        }

        db.close();
        helper.close();
    }

    public List<ServiceOrder> getAll() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(ServiceOrderContract.TABLE, ServiceOrderContract.COLUNS, null, null, null, null, ServiceOrderContract.DATE);
        List<ServiceOrder> serviceOrders = ServiceOrderContract.bindList(cursor);
        db.close();
        helper.close();
        return serviceOrders;
    }

    public List<ServiceOrder> getAllByActiveFlag(boolean flagActive) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        String where = ServiceOrderContract.ACTIVE + " = ?";
        String[] args = {(flagActive) ? "1" : "0"};

        Cursor cursor = db.query(ServiceOrderContract.TABLE, ServiceOrderContract.COLUNS, where, args, null, null, ServiceOrderContract.DATE);
        List<ServiceOrder> serviceOrders = ServiceOrderContract.bindList(cursor);
        db.close();
        helper.close();
        return serviceOrders;
    }

}