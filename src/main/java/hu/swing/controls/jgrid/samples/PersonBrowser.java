package hu.swing.controls.jgrid.samples;

import hu.swing.controls.jgrid.GenericTableCellRenderer;
import hu.swing.controls.jgrid.JGrid;
import hu.swing.controls.jgrid.JGridEntityModel;
import hu.swing.controls.jgrid.descriptor.GridColumnDescriptor;
import hu.swing.controls.jgrid.descriptor.GridColumnType;
import hu.swing.controls.jgrid.descriptor.GridDescriptor;
import hu.swing.controls.jgrid.interfaces.JGridTableModel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PersonBrowser extends JFrame {

    int TESTAMOUNT = 10;

    JGrid grid;
    // datasource to display
    ArrayList<Person> personList;

    public PersonBrowser()  {

        init();
        iniGUI();

    }

    private void init(){

        // generate some random data
        personList = generateTestData();

        // define grid columns
        GridDescriptor gd = new GridDescriptor();
        gd.add(new GridColumnDescriptor("NAME", "Firstname", 100, GridColumnType.TEXT));
        gd.add(new GridColumnDescriptor("BIRTH_DT", "Birth date", 90, GridColumnType.SHORTDATE));
        gd.add(new GridColumnDescriptor("LIMIT", "Daily limit", 80, GridColumnType.CURRENCY));
        gd.add(new GridColumnDescriptor("CARD_NO", "Card No", 100, GridColumnType.TEXT));
        gd.add(new GridColumnDescriptor("CARD_EXP", "Card exp.", 70, GridColumnType.DATEFORMAT, "MM'/'yy"));
        gd.add(new GridColumnDescriptor("VIP", "VIP", 50, GridColumnType.BOOLEAN));

        // initialize
        JGridEntityModel model = new JGridEntityModel<Person>( personList, gd );
        grid = new JGrid(model,gd);


    }

    private void iniGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(grid);
        setTitle("Sample#1 - Person browser");
    }


    // region testdata generation

    private long getMillis(LocalDateTime dt){
        return dt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    private java.util.Date castToDate(LocalDate dt){
        ZonedDateTime zdt = dt.atStartOfDay( ZoneId.systemDefault() );
        Instant instant = zdt.toInstant();
        return java.util.Date.from( instant );
    }

    private LocalDate castToLocalDate( long millis){
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private ArrayList<Person> generateTestData() {

        ArrayList<String> names = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream("osszesnoi.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            while (reader.ready()) {
                String line = reader.readLine();
                names.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Random rand = new Random();
        LocalDateTime fromDate = LocalDateTime.of(1960, 1, 1, 0, 0);
        LocalDateTime toDate = LocalDateTime.of(2005, 12, 31, 0, 0);

        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < TESTAMOUNT; ++i) {
            String name = names.get(rand.nextInt(names.size()));

            LocalDate birthDate = castToLocalDate(ThreadLocalRandom.current().nextLong(getMillis(fromDate), getMillis(toDate)));
            int limit = 50000 + rand.nextInt(10) * 10000;
            String cardNumber = String.format("%08d", rand.nextInt(10000000)) +
                    String.format("%08d", rand.nextInt(10000000));
            boolean vip = rand.nextInt(10) >= 7;
            LocalDate expDate = LocalDate.now().plusMonths(rand.nextInt(24));
            Person p = new Person(name, castToDate(birthDate), limit, cardNumber, castToDate(expDate), vip);
            System.out.println(p.toString());
            list.add(p);
        }

        return list;
    }

    //endregion

}
