package de.hhu.cs.dbs.internship.project.gui;

import com.alexanderthelen.applicationkit.database.Table;
import com.alexanderthelen.applicationkit.gui.TableViewController;
import com.alexanderthelen.applicationkit.gui.ViewController;
import de.hhu.cs.dbs.internship.project.table.account.Account;
import de.hhu.cs.dbs.internship.project.table.autor.Autor;
import de.hhu.cs.dbs.internship.project.table.bewertung.Bewertung;
import de.hhu.cs.dbs.internship.project.table.bilderGPS.BilderGPS;
import de.hhu.cs.dbs.internship.project.table.bilderTags.BilderTags;
import de.hhu.cs.dbs.internship.project.table.eigeneEintraege.EigeneEintraege;
import de.hhu.cs.dbs.internship.project.table.eigeneSeiten.EigeneSeiten;
import de.hhu.cs.dbs.internship.project.table.gesamtverdienst.Gesamtverdienst;
import de.hhu.cs.dbs.internship.project.table.seitenGekauft.SeitenGekauft;
import de.hhu.cs.dbs.internship.project.table.seitenPublic.SeitenPublic;
import de.hhu.cs.dbs.internship.project.table.tag_zu_bild.TagZuBild;
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

        //Transaktion
        table = new Transaktion();
        table.setTitle("Transaktion");
        try {
            tableViewController = TableViewController.createWithNameAndTable("transaktion", table);
            tableViewController.setTitle("Transaktion");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //EigeneSeite
        table = new EigeneSeiten();
        table.setTitle("Alle eigenen Seiten");
        try {
            tableViewController = TableViewController.createWithNameAndTable("eigeneSeite", table);
            tableViewController.setTitle("Eigene Seiten");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //SeitenPublic (Subtree von EigeneSeiten)
        table = new SeitenPublic();
        table.setTitle("Alle öffentliche Seiten");
        try {
            tableViewController = TableViewController.createWithNameAndTable("seitenPublic", table);
            tableViewController.setTitle("Öffentliche Seiten");
        } catch (IOException e) {
            tableViewController = null;
        }
        subTreeItem = new TreeItem<>(tableViewController);
        treeItem.getChildren().add(subTreeItem);

        //Seiten gekauft (Subtree von EigeneSeiten)
        table = new SeitenGekauft();
        table.setTitle("Alle gekauften Seiten und Einträge");
        try {
            tableViewController = TableViewController.createWithNameAndTable("seitenGekauft", table);
            tableViewController.setTitle("Gekaufte Seiten");
        } catch (IOException e) {
            tableViewController = null;
        }
        subTreeItem = new TreeItem<>(tableViewController);
        treeItem.getChildren().add(subTreeItem);

        //Eigene Eintraege
        table = new EigeneEintraege();
        table.setTitle("Alle eigenen Einträge");
        try {
            tableViewController = TableViewController.createWithNameAndTable("eigeneEintraege", table);
            tableViewController.setTitle("Eigene Einträge");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //Tags
        table = new Tags();
        table.setTitle("Tags");
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
        table.setTitle("Tag gehört zu Bild");
        try {
            tableViewController = TableViewController.createWithNameAndTable("tag_zu_bild", table);
            tableViewController.setTitle("Tags zuordnen");
        } catch (IOException e) {
            tableViewController = null;
        }
        subTreeItem = new TreeItem<>(tableViewController);
        treeItem.getChildren().add(subTreeItem);

        //Bewertung
        table = new Bewertung();
        table.setTitle("Bewertung der Autoren");
        try {
            tableViewController = TableViewController.createWithNameAndTable("bewertung", table);
            tableViewController.setTitle("Bewertung");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //Bild mit Tags
        table = new BilderTags();
        table.setTitle("Bilder mit Tags suchen");
        try {
            tableViewController = TableViewController.createWithNameAndTable("bilderTags", table);
            tableViewController.setTitle("Bilder mit Tags");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        //Bilder mit GPS
        table = new BilderGPS();
        table.setTitle("Bilder mit GPS Koordinaten suchen");
        try {
            tableViewController = TableViewController.createWithNameAndTable("bilderGPS", table);
            tableViewController.setTitle("Bilder mit GPS");
        } catch (IOException e) {
            tableViewController = null;
        }
        treeItem = new TreeItem<>(tableViewController);
        treeItem.setExpanded(true);
        treeItems.add(treeItem);

        return treeItems;
    }
}
