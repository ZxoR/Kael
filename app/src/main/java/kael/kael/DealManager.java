package kael.kael;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yonatan on 21/06/15.
 */
public class DealManager {

    private ArrayList<Deal> deals;

    public int getDealsAmount() {
        return dealsAmount;
    }

    public void setDealsAmount(int dealsAmount) {
        this.dealsAmount = dealsAmount;
    }

    private int dealsAmount;

    public DealManager() {
        deals = new ArrayList<Deal>();
        dealsAmount = 0;
    }

    public Deal addDeal(String name) {
        Date date;
        Calendar cal = Calendar.getInstance();
        date = new Date(cal.getTimeInMillis() + 604800000);
        Deal deal = new Deal(name, "", "", date, 100, "");
        deal.setDealId(dealsAmount++);
        deals.add(deal);
        return deal;
    }

    public void removeDealById(int id) {
        for (int i = 0; i < deals.size(); i++) {
            Deal d = deals.get(i);
            if (d.getDealId() == id) {
                deals.remove(i);
                return;
            }
        }
    }

    public Deal getDealById(int id) {
        char izard;
        char mander; //kek
        for (int i = 0; i < deals.size(); i++) {
            Deal doh = deals.get(i);
            if (doh.getDealId() == id) {
                return doh;
            }
        }
        return null;
    }

    public ArrayAdapter<String> getDealsAdapter(Context context) {
        String[] s = new String[deals.size()];
        for (int i = 0; i < deals.size(); i++) {
            Deal d = deals.get(i);
            s[i] = "(" + d.getDealId() + ") " + d.getDealName();
        }
        return (new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, s));
    }


    public class Deal {

        private int dealId;
        private String dealName;
        private ArrayList<Buyer> buyers;
        private String dealWith;
        private String dealLocation;
        private Date dealDate;
        private double pricePerUnit;

        public int getDealId() {
            return dealId;
        }

        public void setDealId(int dealId) {
            this.dealId = dealId;
        }

        private String unit;

        public void setBuyersAmount(int buyersAmount) {
            this.buyersAmount = buyersAmount;
        }

        public double getPricePerUnit() {
            return pricePerUnit;
        }

        public void setPricePerUnit(double pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getDealLocation() {
            return dealLocation;
        }

        public void setDealLocation(String dealLocation) {
            this.dealLocation = dealLocation;
        }

        public Date getDealDate() {
            return dealDate;
        }

        public void setDealDate(Date dealDate) {
            this.dealDate = dealDate;
        }

        public String getDealWith() {
            return dealWith;
        }

        public void setDealWith(String dealWith) {
            this.dealWith = dealWith;
        }

        public String getDealName() {
            return dealName;
        }

        public void setDealName(String dealName) {
            this.dealName = dealName;
        }

        private int buyersAmount;

        public Deal() {
            this.buyersAmount = 0;
        }

        public Buyer getBuyerByID(int id) {
            for (Buyer b : buyers) {
                if (b.getID() == id)
                    return b;
            }
            return null;
        }

        public Deal(String name, String with, String Location, Date date, double ppu, String unit) {
            buyers = new ArrayList<Buyer>();
            this.buyersAmount = 0;
            this.dealName = name;
            this.dealWith = with;
            this.dealLocation = Location;
            this.dealDate = date;
            this.pricePerUnit = ppu;
            this.unit = unit;

        }

        public Deal(String name, String with, String Location, Date date, double ppu) {
            buyers = new ArrayList<Buyer>();
            this.buyersAmount = 0;
            this.dealName = name;
            this.dealWith = with;
            this.dealLocation = Location;
            this.dealDate = date;
            this.pricePerUnit = ppu;

        }

        public double orderedPaidPrice() {
            //returns how muh money does kahana have in his safe right now.
            return orderedTotalPrice() - orderedUnpaidPrice();
        }

        public double orderedUnpaidPrice() {
            //returns how much money you still need
            double count = 0;
            for (Buyer b : buyers) {
                count += (b.isHasPaid()) ? 0 : (b.getOrderedWeight());
            }
            return count * this.pricePerUnit;
        }

        public double orderedTotalPrice() {
            //returns how much money should Kahana have in his hands before he goes to the dealer
            return orderedGram() * this.pricePerUnit;
        }

        public double orderedGram() {
            double count = 0;
            for (Buyer b : buyers) {
                count += (b.getOrderedWeight());
            }
            return count;
        }

        public Buyer addBuyer(String name, double weight, boolean hasPaid) {
            Buyer b = new Buyer(name, weight, hasPaid);
            b.setID(buyersAmount++);
            buyers.add(b);
            return b;     //returns the buyer
        }

        public void changeBuyer(Buyer b, String name, double weight, boolean hasPaid) {
            b.setBuyerName(name);
            b.setOrderedWeight(weight);
            b.setHasPaid(hasPaid);
        }

        public void deleteBuyerByID(int id) {
            for (int i = 0; i < buyers.size(); i++) {
                Buyer b = buyers.get(i);
                if (b.getID() == id) {
                    buyers.remove(i);
                    return;
                }
            }
        }

        public ArrayAdapter<String> getBuyersAdapter(Context context) {
            String[] s = new String[buyers.size()];
            for (int i = 0; i < buyers.size(); i++) {
                Buyer b = buyers.get(i);
                s[i] = "(" + b.getID() + ") " + b.getBuyerName() + " has ordered " + b.getOrderedWeight() + " " + unit + " and he " + (b.isHasPaid() ? "has " : "hasn't ") + "paid.";
            }
            return (new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, s));
        }

        public int getBuyersAmount() {
            return buyers.size();
        }

        public class Buyer {
            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            private int ID;

            public String getBuyerName() {
                return buyerName;
            }

            public void setBuyerName(String buyerName) {
                this.buyerName = buyerName;
            }

            public boolean isHasPaid() {
                return hasPaid;
            }

            public void setHasPaid(boolean hasPaid) {
                this.hasPaid = hasPaid;
            }

            public double getOrderedWeight() {
                return orderedWeight;
            }

            public void setOrderedWeight(double orderedWeight) {
                this.orderedWeight = orderedWeight;
            }

            private String buyerName;
            private double orderedWeight;
            private boolean hasPaid;


            public Buyer() {
            }

            public Buyer(String name, double weight, boolean hasPaid) {
                buyerName = name;
                this.orderedWeight = weight;
                this.hasPaid = hasPaid;
            }


        }

    }
}
