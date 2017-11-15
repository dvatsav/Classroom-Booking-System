package Supplementary;

import Actors.Users;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountRequests {
    private ArrayList<HashMap> accountrequests;

    public void serialize(ArrayList<HashMap> ar) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("./src/DataFiles/accountreqs.txt"));
            out.writeObject(accountrequests);
        } finally {
            out.close();
        }
    }

    public ArrayList<HashMap> deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        ArrayList<HashMap> accounts;
        try {
            in = new ObjectInputStream(new FileInputStream("./src/DataFiles/accountreqs.txt"));
            accounts = (ArrayList<HashMap>) in.readObject();
        } finally {
            in.close();
        }
        return accounts;
    }

    public void addElement(HashMap<String, String> hm) {
        accountrequests.add(hm);
    }

    public void setAccountRequests(ArrayList<HashMap> hm) {
        accountrequests = new ArrayList<>(hm);
    }

    public void newAccount() {
        accountrequests = new ArrayList<>();
    }

    public void removeAccount(Users user) {
        accountrequests.remove(user);
    }

    public ArrayList<HashMap> getAccountRequests() {
        return accountrequests;
    }
}