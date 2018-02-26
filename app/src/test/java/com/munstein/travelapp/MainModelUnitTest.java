package com.munstein.travelapp;

import com.munstein.travelapp.model.TravelPackage;
import com.munstein.travelapp.views.main.MainModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainModelUnitTest {


    MainModel mainModel;

    @Before
    public void init(){
        mainModel = new MainModel();
    }

    @Test
    public void checkIfAnyInformationIsComingFromTheServer() throws Exception {
        ArrayList<TravelPackage> travelPackages = mainModel.getTravelPackages().blockingFirst();
        assertEquals(true, travelPackages.size()>0);
    }

    @Test
    public void checkIfAllInformationIsComingFromTheServer() throws Exception {
        ArrayList<TravelPackage> travelPackages = mainModel.getTravelPackages().blockingFirst();
        assertEquals(6, travelPackages.size());
    }

    @Test
    public void checkIfAnyItemIsNull() throws Exception{
        ArrayList<TravelPackage> travelPackages = mainModel.getTravelPackages().blockingFirst();
        for(TravelPackage t : travelPackages) {
            assertNotNull(t);
        }
    }

    @Test
    public void checkIfAnyPropertyIsNull() throws Exception{
        ArrayList<TravelPackage> travelPackages = mainModel.getTravelPackages().blockingFirst();
        for(TravelPackage t : travelPackages) {
            assertNotNull(t.getDescription());
            assertNotNull(t.getTitle());
            assertNotNull(t.getImgUrl());
            assertNotNull(t.getFormattedPrice());
        }
    }

    @Test
    public void checkDataIntegrity() throws Exception {
        ArrayList<TravelPackage> travelPackages = mainModel.getTravelPackages().blockingFirst();
        assertEquals("Salar de Uyuni - Bolívia", travelPackages.get(0).getTitle());
        assertEquals(true, travelPackages.get(0).getDescription().contains("conhecer"));
        assertEquals(true, travelPackages.get(0).getImgUrl().contains("http"));
        assertEquals(true, travelPackages.get(0).getFormattedPrice().contains(","));
    }

}