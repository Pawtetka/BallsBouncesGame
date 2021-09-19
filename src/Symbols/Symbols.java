package Symbols;
import javax.swing.*;

public class Symbols {
    public static void main(String[] args) {
        PrinterThread thread1 = new PrinterThread('-');
        PrinterThread thread2 = new PrinterThread('|');

        //thread1.start();
        //thread2.start();

        Sync sync1 = new Sync(1);
        Sync sync2 = new Sync(0);

        SyncPrinterThread syncThread1 = new SyncPrinterThread('-', sync1, sync2, true);
        SyncPrinterThread syncThread2 = new SyncPrinterThread('|', sync2, sync1, false);

        syncThread1.start();
        syncThread2.start();
    }
}
