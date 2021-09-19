package Symbols;

public class PrinterThread extends Thread{
    private char symbol;

    public PrinterThread(char symbol){
        this.symbol = symbol;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            for(int k = 0; k < 20; k++){
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
