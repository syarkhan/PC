package com.example.sheryarkhan.projectcity.Utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.SquareCap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sheryar Khan on 9/20/2017.
 */

public final class Constants {


    private static ArrayList<Areas> areasArrayList = new ArrayList<>();

    public static final LatLngBounds KARACHI_BOUNDS = new LatLngBounds(
            new LatLng(24.600851, 66.432167), new LatLng(25.676796, 67.555412)); //SW,NE

    public static final LatLngBounds LAHORE_BOUNDS = new LatLngBounds(
            new LatLng(31.3489, 73.9778), new LatLng(31.6902, 74.6754)); //SW,NE

    public static final LatLngBounds ISLAMABAD_BOUNDS = new LatLngBounds(
            new LatLng(31.3489, 73.9778), new LatLng(31.6902, 74.6754)); //SW,NE

    public static final LatLngBounds QUETTA_BOUNDS = new LatLngBounds(
            new LatLng(33.5282, 72.7158), new LatLng(33.8613, 73.4134)); //SW,NE


//    public static Map<String, List<String>> TOWN_DETAILS() {
//        return Collections.unmodifiableMap(new HashMap<String, List<String>>() {{
//            int i = 0;
//            for (String town : getTowns()) {
//                List<String> area = getAreas().get(i);
//                put(town, area);
//                i++;
//            }
//        }});
//    }


    public static ArrayList<Areas> getTownsAndAreas()
    {
        int a=0;
        List<List<String>> list = getAreas();
        for(String town : getTowns())
        {
            Areas area1 = new Areas();
            area1.setTown(town);
            area1.setIsHeaderOrTown(true);
            area1.setId(a);
            areasArrayList.add(area1);


            //int size = getAreas().get(a).size();
            //int b=0;
            a++;
            for (int i = 0;i < list.get(a-1).size();i++)
            {

                Areas area2 = new Areas();
                area2.setArea(list.get(a-1).get(i));
                area2.setTown(town);
                area2.setIsHeaderOrTown(false);
                area2.setId(a+i);
                areasArrayList.add(area2);
                //a++;
            }
            //List<String> area = getAreas().get(a).get(0);
        }
        return areasArrayList;
    }

    public static List<Map<String, Object>> TOWN_DETAILS() {

        List<Map<String, Object>> townDetails = new ArrayList<Map<String, Object>>() {{
            int i = 0;
            for (String town : getTowns()) {
                List<String> area = getAreas().get(i);
                Map<String, Object> hashMap = new HashMap<>();
                hashMap.put("town",town);
                hashMap.put("area",area);
                add(hashMap);
                //put(town, area);
                i++;
            }
        }};
        return townDetails;
    }

    public static List<String> getTowns() {

        return Collections.unmodifiableList(new ArrayList<String>() {{ //TOWNS OF KARACHI
            add("Baldia Town");
            //baldia town

            //-----//////////
            //add(getBaldiaTown());

            //bin qasim/////
            add("Bin Qasim Town");
            add("DHA");
            add("Gadap Town");
            add("Gulberg Town");
            add("Gulshan Town");
            add("Jamshed Town");
            add("Kemari Town");
            add("Korangi Town");
            add("Landhi Town");
            add("Liaquatabad Town");
            add("Lyari Town");
            add("Malir Town");
            //add("Malir Cantonment");
            add("New Karachi Town");
            add("North Nazimadab Town");
            add("Orangi Town");
            add("Saddar Town");
            add("Shah Faisal Town");
            add("S.I.T.E");

            //add(getMalirCantonment());
            //add("");
        }});
        //return TOWNS;
    }


    public static List<List<String>> getAreas() {
        return Collections.unmodifiableList(new ArrayList<List<String>>() {{
            add(getBaldiaTown());
            add(getBinQasimTown());
            add(getDHA());
            add(getGadapTown());
            add(getGulbergTown());
            add(getGulshanTown());
            add(getJamshedTown());
            add(getKemariTown());
            add(getKorangiTown());
            add(getLandhiTown());
            add(getLiaquatabadTown());
            add(getLyariTown());
            add(getMalirTown());
            //add(getMalirCantonment());
            add(getNewKarachiTown());
            add(getNorthNazimabadTown());
            add(getOrangiTown());
            add(getSaddarTown());
            add(getShahFaisalTown());
            add(getSITE());


        }});
    }

    public static List<String> getBaldiaTown() {
        return new ArrayList<String>() {{
            add("Gulshan-e-Ghazi");
            add("Ittehad Town");
            add("Islam Nagar");
            add("Nai Abadi");
            add("Saeedabad");
            add("Muslim Mujahid Colony");
            add("Muhajir Camp");
            add("Rasheedabad");

        }};
        //return BALDIA_TOWN;
    }

    public static List<String> getGadapTown() {
        return new ArrayList<String>() {{

            add("Murad Memon Goth");
            add("Darsano Chana");
            add("Gadap");
            add("Gujro");
            add("Songal");
            add("Maymarabad");
            add("Yousuf Goth");
            add("Manghopir");
        }};
    }

    public static List<String> getBinQasimTown() {
        return new ArrayList<String>() {{

            add("Ibrahim Hyderi");
            add("Rehri");
            add("Cattle Colony");
            add("Qaidabad");
            add("Landhi Colony");
            add("Gulshan - e - Hadeed");
            add("Gaghar");


        }};
    }

    public static List<String> getGulbergTown() {
        return new ArrayList<String>() {{

            add("Azizabad");
            add("Karimabad");
            add("Aisha Manzil");
            add("Ancholi");
            add("Naseerabad");
            add("Yaseenabad");
            add("Water Pump");
            add("Shafiq Mill Colony");

        }};
    }

    public static List<String> getGulshanTown() {
        return new ArrayList<String>() {{

            add("Delhi Mercantile Society");
            add("Civic Centre");
            add("Pir Ilahi Buksh Colony");
            add("Essa Nagri");
            add("Gulshan - e - Iqbal");
            add("Gillani Railway Station");
            add("Shanti Nagar");
            add("Jamali Colony");
            add("Gulshan - e - Iqbal II");
            add("Pehlwan Goth");
            add("Matrovil Colony");
            add("Gulzar - e - Hijri");
            add("Safooran Goth");

        }};
    }

    public static List<String> getJamshedTown() {
        return new ArrayList<String>() {{

            add("Akhtar Colony");
            add("Manzoor Colony");
            add("Azam Basti");
            add("Chanesar Goth");
            add("Mahmudabad");
            add("P.E.C.H.S. (Pakistan Employees Co -operative Housing Society)");
            add("P.E.C.H.S.II");
            add("Jut Line");
            add("Central Jacob Lines");
            add("Jamshed Quarters");
            add("Garden East");
            add("Soldier Bazar");
            add("Pakistan Quarters");

        }};
    }

    public static List<String> getKemariTown() {
        return new ArrayList<String>() {{

            add("Bhutta Village");
            add("Sultanabad");
            add("Kiamari");
            add("Baba Bhit");
            add("Machar Colony");
            add("Maripur");
            add("SherShah");
            add("Gabo Pat");

        }};
    }

    public static List<String> getKorangiTown() {
        return new ArrayList<String>() {{

            add("Bilal Colony");
            add("Nasir Colony");
            add("Chakra Goth");
            add("Mustafa Taj Colony");
            add("Hundred Quarters");
            add("Gulzar Colony");
            add("Korangi Sector 33");
            add("Zaman Town");
            add("Hasrat Mohani Colony");

        }};
    }

    public static List<String> getLandhiTown() {
        return new ArrayList<String>() {{

            add("Muzafarabad");
            add("Muslimabad");
            add("Dawood Chowrangi");
            add("Moinabad");
            add("Sharafi Goth");
            add("Bhutto Nagar");
            add("Khawaja Ajmeer Colony");
            add("Landhi");
            add("Awami Colony");
            add("Burmee Colony");
            add("Korangi");
            add("Sherabad");

        }};
    }

    public static List<String> getLiaquatabadTown() {
        return new ArrayList<String>() {{

            add("Rizvia Society (R.C.H.S.)");
            add("Firdous Colony");
            add("Super Market");
            add("Dak Khana");
            add("Qasimabad");
            add("Bandhani Colony");
            add("Sharifabad");
            add("Commercial Area");
            add("Mujahid Colony");
            add("Nazimabad");
            add("Abbasi Shaheed");

        }};
    }

    public static List<String> getLyariTown() {
        return new ArrayList<String>() {{

            add("Agra Taj Colony");
            add("Daryaabad");
            add("Nawabad");
            add("Khada Memon Society");
            add("Baghdadi");
            add("Baghdadi");
            add("Shah Baig Line");
            add("Bihar Colony");
            add("Ragiwara");
            add("Singo Line");
            add("Chakiwara");
            add("Allama Iqbal Colony");

        }};
    }

    public static List<String> getMalirTown() {
        return new ArrayList<String>() {{

            add("Model Colony");
            add("Kala Board");
            add("Saudabad");
            add("Khokhra Par");
            add("Jafar - e - Tayyar");
            add("Gharibabad");
            add("Ghazi Brohi Goth");

        }};
    }

    public static List<String> getDHA() {
        return new ArrayList<String>() {{

            add("DHA Phase 1");
            add("DHA Phase 2");
            add("DHA Phase 3");
            add("DHA Phase 4");
            add("DHA Phase 5");
            add("DHA Phase 6");
            add("DHA Phase 7");
            add("DHA Phase 8");

        }};
    }

    public static List<String> getOrangiTown() {
        return new ArrayList<String>() {{

            add("Mominabad");
            add("Haryana Colony");
            add("Hanifabad");
            add("Mohammad Nagar");
            add("Madina Colony");
            add("Ghaziabad");
            add("Chisti Nagar");
            add("Bilal Colony");
            add("Iqbal Baloch Colony");
            add("Gabol Colony");
            add("Data Nagar");
            add("Mujahidabad");
            add("Baloch Goth");

        }};
    }

    public static List<String> getNewKarachiTown() {
        return new ArrayList<String>() {{

            add("North Karachi");
            add("Sir Syed Colony");
            add("Fatima Jinnah Colony");
            add("Godhra");
            add("Abu Zar Ghaffari");
            add("Hakim Ahsan");
            add("Madina Colony");
            add("Faisal Colony");
            add("Khamiso Goth");
            add("Mustufa Colony");
            add("Khawaja Ajmeer Nagri");
            add("Gulshan - e - Saeed");
            add("Shah Nawaz Bhutto Colony");

        }};
    }

    public static List<String> getNorthNazimabadTown() {
        return new ArrayList<String>() {{

            add("Paposh Nagar");
            add("Pahar Ganj");
            add("Khandu Goth");
            add("Hyderi");
            add("Sakhi Hassan");
            add("Farooq - e - Azam");
            add("Nusrat Bhutto Colony");
            add("Shadman Town");
            add("Buffer Zone");
            add("Buffer Zone II");

        }};
    }

    public static List<String> getSaddarTown() {
        return new ArrayList<String>() {{

            add("Old Haji Camp");
            add("Garden");
            add("Kharadar");
            add("City Railway Colony");
            add("Nanak Wara");
            add("Gazdarabad");
            add("Millat Nagar/Islam Pura");
            add("Saddar");
            add("Civil Line");
            add("Clifton");
            add("Kehkashan");

        }};
    }

    public static List<String> getShahFaisalTown() {
        return new ArrayList<String>() {{

            add("Natha Khan Goth");
            add("Pak Sadat Colony");
            add("Shah Faisal Colony");
            add("Raita Plot");
            add("Moria Khan Goth");
            add("Rafa - e - Aam Society");
            add("Al - Falah Society");

        }};
    }

    public static List<String> getSITE() {
        return new ArrayList<String>() {{

            add("Pak Colony");
            add("Old Golimar");
            add("Jahanabad");
            add("Metrovil");
            add("Bhawani Chali");
            add("Frontier Colony");
            add("Banaras Colony");

        }};
    }

    public static List<String> getMalirCantonment()
    {
        return Collections.emptyList();
    }


    public static class Areas{
        int id;
        String town;
        String area;
        boolean isHeaderOrTown;


        public Areas()
        {

        }

        public Areas(int id, String town, String area, boolean isHeaderOrTown) {
            this.id = id;
            this.town = town;
            this.area = area;
            this.isHeaderOrTown = isHeaderOrTown;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public boolean isHeaderOrTown() {
            return isHeaderOrTown;
        }

        public void setIsHeaderOrTown(boolean headerOrTown) {
            isHeaderOrTown = headerOrTown;
        }
    }

}
