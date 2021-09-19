package Symbols;

public class SyncPrinterThread extends Thread{
    private char symbol;
    private Sync inSync;
    private Sync outSync;
    private boolean printNewLine;

    public SyncPrinterThread(char symbol, Sync sync1, Sync sync2, boolean printNewLine){
        this.symbol = symbol;
        this.inSync = sync1;
        this.outSync = sync2;
        this.printNewLine = printNewLine;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            for(int k = 0; k < 20; k++){
                inSync.decrease();
                System.out.print(symbol);
                if(printNewLine && k == 19){
                    System.out.println();
                }
                outSync.increase();
            }
        }
    }
}
