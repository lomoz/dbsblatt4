package de.hhu.cs.dbs.internship.project.gui;

import com.alexanderthelen.applicationkit.database.Table;
import com.alexanderthelen.applicationkit.gui.TableViewController;
import com.alexanderthelen.applicationkit.gui.ViewController;
import de.hhu.cs.dbs.internship.project.table.account.Account;
import de.hhu.cs.dbs.internship.project.table.autor.Autor;
import de.hhu.cs.dbs.internship.project.table.bewertung.Bewertung;
import de.hhu.cs.dbs.internship.project.table.bilder.Bilder;
import de.hhu.cs.dbs.internship.project.table.bilderGPS.BilderGPS;
import de.hhu.cs.dbs.internship.project.table.bilderTags.BilderTags;
import de.hhu.cs.dbs.internship.project.table.eigeneEintraege.EigeneEintraege;
import de.hhu.cs.dbs.internship.project.table.eigeneSeiten.EigeneSeiten;
import de.hhu.cs.dbs.internship.project.table.gesamtverdienst.Gesamtverdienst;
import de.hhu.cs.dbs.internship.project.table.seitenGekauft.SeitenGekauft;
import de.hhu.cs.dbs.internship.project.table.seitenPublic.SeitenPublic;
import de.hhu.cs.dbs.internship.project.table.tagZuBild.TagZuBild;
import de.hhu.cs.dbs.internship.project.table.tags.Tags;
import de.hhu.cs.dbs.internship.project.table.transaktion.Transaktion;
import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.util.ArrayList;

public class MasterViewController extends com.alexanderthelen.applicationkit.gui.MasterViewController {
    protected MasterViewController(String name) {
        super(name);
    }

    public static MasterViewController createWithName(String name) throws IOException {
        MasterViewController controller = new MasterViewController(name);
        controller.loadView();
        return controller;
    }

    @Override
    protected ArrayList<TreeItem<ViewController>> getTreeItems() {
        ArrayList<TreeItem<ViewController>> treeItems = new ArrayList<>();
        TreeItem<ViewController> treeItem;
        TreeItem<ViewController> subTreeItem;
        TableViewController tableViewController;
        Table table;

        //Account
        table = new Account();
        table.setTitle("Verwalten Sie Ihren Account");
        try {
            tableViewController = TableViewController.createWithNameAndTable("account", table);
            tableViewController.setTitle("Account");
        } catch (IOException e) {
            tableViewController = null;
        }

        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //Gesamtverdienst (Subtree von Account)
        table = new Gesamtverdienst();
        table.setTitle("Gesamtverdienst");
        try {
            tableViewController = TableViewController.createWithNameAndTable("gesamtverdienst", table);
            tableViewController.setTitle("Gesamtverdienst");
        } catch (IOException e) {
            tableViewController = null;
        }
        subTreeItem = new TreeItem<>(tableViewController);
        treeItem.getChildren().add(subTreeItem);

        //Autor
        table = new Autor();
        table.setTitle("Suchen Sie nach Autoren");
        try {
            tableViewController = TableViewController.createWithNameAndTable("autor", table);
            tableViewController.setTitle("Autoren");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //Bewertung aller Autoren
        table = new Bewertung();
        table.setTitle("Bewerten Sie alle Autoren");
        try {
            tableViewController = TableViewController.createWithNameAndTable("bewertung", table);
            tableViewController.setTitle("Autoren bewerten");
        } catch (IOException e) {
            tableViewController = null;
        }
        subTreeItem = new TreeItem<>(tableViewController);
        treeItem.getChildren().add(subTreeItem);

        //Transaktion
        table = new Transaktion();
        table.setTitle("Tätigen Sie Transaktionen");
        try {
            tableViewController = TableViewController.createWithNameAndTable("transaktion", table);
            tableViewController.setTitle("Transaktionen");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //Eigenes Tagebuch
        table = new EigeneSeiten();
        table.setTitle("Verwalten Sie Ihre eigenes Tagebuch");
        try {
            tableViewController = TableViewController.createWithNameAndTable("eigeneSeite", table);
            tableViewController.setTitle("Eigenes Tagebuch");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //Eigene Eintraege
        table = new EigeneEintraege();
        table.setTitle("Verwalten Sie Ihre eigenen Einträge");
        try {
            tableViewController = TableViewController.createWithNameAndTable("eigeneEintraege", table);
            tableViewController.setTitle("Eigene Einträge");
        } catch (IOException e) {
            tableViewController = null;
        }
        subTreeItem = new TreeItem<>(tableViewController);
        treeItem.getChildren().add(subTreeItem);

        //Tagebücher
        table = new SeitenPublic();
        table.setTitle("Erkunden Sie alle öffentlichen Tagebücher");
        try {
            tableViewController = TableViewController.createWithNameAndTable("seitenPublic", table);
            tableViewController.setTitle("Öffentliche Tagebücher");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //Seiten gekauft (Subtree von Tageücher)
        table = new SeitenGekauft();
        table.setTitle("Erkunden Sie alle gekauften Seiten und Einträge");
        try {
            tableViewController = TableViewController.createWithNameAndTable("seitenGekauft", table);
            tableViewController.setTitle("Gekaufte Tagebücher");
        } catch (IOException e) {
            tableViewController = null;
        }
        subTreeItem = new TreeItem<>(tableViewController);
        treeItem.getChildren().add(subTreeItem);

        //Tags
        table = new Tags();
        table.setTitle("Verwalten Sie alle Tags");
        try {
            tableViewController = TableViewController.createWithNameAndTable("tags", table);
            tableViewController.setTitle("Tags");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //Tag zu Bild (Subtree von Tags)
        table = new TagZuBild();
        table.setTitle("Ordnen Sie Tag zu Bild zu");
        try {
            tableViewController = TableViewController.createWithNameAndTable("tagZuBild", table);
            tableViewController.setTitle("Tags zuordnen");
        } catch (IOException e) {
            tableViewController = null;
        }
        subTreeItem = new TreeItem<>(tableViewController);
        treeItem.getChildren().add(subTreeItem);

        //Bilder hochladen und zuweisen
        table = new Bilder();
        table.setTitle("Laden Sie Bilder hoch und ordnen Sie diese Ihren Einträgen zu");
        try {
            tableViewController = TableViewController.createWithNameAndTable("bilder", table);
            tableViewController.setTitle("Bilder");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //Bilder mit GPS
        table = new BilderGPS();
        table.setTitle("Suchen Sie Bilder mit GPS Koordinaten");
        try {
            tableViewController = TableViewController.createWithNameAndTable("bilderGPS", table);
            tableViewController.setTitle("Bildersuche mit GPS");
        } catch (IOException e) {
            tableViewController = null;
        }
        subTreeItem = new TreeItem<>(tableViewController);
        treeItem.getChildren().add(subTreeItem);

        //Bild mit Tags
        table = new BilderTags();
        table.setTitle("Suchen Sie Bilder mit Tags");
        try {
            tableViewController = TableViewController.createWithNameAndTable("bilderTags", table);
            tableViewController.setTitle("Bildersuche mit Tags");
        } catch (IOException e) {
            tableViewController = null;
        }
        subTreeItem = new TreeItem<>(tableViewController);
        treeItem.getChildren().add(subTreeItem);

        return treeItems;
    }
}
