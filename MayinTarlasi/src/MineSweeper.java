import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
	int rowNumber;
    int columnNumber;
    
    MineSweeper(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }
    public String[][] mineMap(String[][] map) {
        Random rand = new Random();

        int numberOfMine = (rowNumber * columnNumber) / 4;

        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                map[i][j] = "-";
            }
        }

        while (numberOfMine > 0) {
            String[] array = new String[numberOfMine];

            for (int i = 0; i < array.length; i++) {
                boolean isEqual = false;
                int mineOfRow = rand.nextInt(rowNumber);
                int mineOfColumn = rand.nextInt(columnNumber);
                array[i] = mineOfRow + "." + mineOfColumn;

                for (int j = 0; j < array.length; j++) {
                    if (i != j) {
                        if (array[i].equals(array[j])) {
                            isEqual = true;
                        }
                    }
                }
                if (isEqual == false) {
                    map[mineOfRow][mineOfColumn] = "*";
                    numberOfMine--;
                } else {
                    i--;
                }
            }
        }
        System.out.println("Mayınların Konumu");
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz!");
        return map;
    }

    public String[][] numberOfClosestMine(String[][] gameMap) {
        mineMap(gameMap);
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                int counter = 0;
                if (gameMap[i][j] != "*") {
                    if (i == 0 && j == 0) {
                        for (int k = i; k <= i + 1; k++) {
                            for (int n = j; n <= j + 1; n++) {
                                if (gameMap[k][n] == "*") {
                                    counter++;
                                }
                            }
                        }
                    } else if (i != 0 && j != 0 && i < gameMap.length - 1 && j < gameMap[0].length - 1) {
                        for (int k = i - 1; k <= i + 1; k++) {
                            for (int n = j - 1; n <= j + 1; n++) {
                                if (gameMap[k][n] == "*") {
                                    counter++;
                                }
                            }
                        }
                    } else if (i == 0 & j != 0 && j < gameMap[0].length - 1) {
                        for (int k = i; k <= i + 1; k++) {
                            for (int n = j - 1; n <= j + 1; n++) {
                                if (gameMap[k][n] == "*") {
                                    counter++;
                                }
                            }
                        }
                    } else if (i != 0 & j == 0 && i < gameMap.length - 1) {
                        for (int k = i - 1; k <= i + 1; k++) {
                            for (int n = j; n <= j + 1; n++) {
                                if (gameMap[k][n] == "*") {
                                    counter++;
                                }
                            }
                        }
                    } else if (i == gameMap.length - 1 && j == gameMap[0].length - 1) {
                        for (int k = i - 1; k <= i; k++) {
                            for (int n = j - 1; n <= j; n++) {
                                if (gameMap[k][n] == "*") {
                                    counter++;
                                }
                            }
                        }
                    } else if (i == gameMap.length - 1 && j != gameMap[0].length - 1 && j > 0) {
                        for (int k = i - 1; k <= i; k++) {
                            for (int n = j - 1; n <= j + 1; n++) {
                                if (gameMap[k][n] == "*") {
                                    counter++;
                                }
                            }
                        }
                    } else if (i != gameMap.length - 1 && j == gameMap[0].length - 1 && i > 0) {
                        for (int k = i - 1; k <= i + 1; k++) {
                            for (int n = j - 1; n <= j; n++) {
                                if (gameMap[k][n] == "*") {
                                    counter++;
                                }
                            }
                        }
                    } else if (i == gameMap.length - 1 && j == 0) {
                        for (int k = i - 1; k <= i; k++) {
                            for (int n = j; n <= j + 1; n++) {
                                if (gameMap[k][n] == "*") {
                                    counter++;
                                }
                            }
                        }
                    } else if (i == 0 && j == gameMap[0].length - 1) {
                        for (int k = i; k <= i + 1; k++) {
                            for (int n = j - 1; n <= j; n++) {
                                if (gameMap[k][n] == "*") {
                                    counter++;
                                }
                            }
                        }
                    }
                    String count = Integer.toString(counter);
                    gameMap[i][j] = count;
                }
            }
        }
        return gameMap;
    }

    public void run() {
        Scanner input = new Scanner(System.in);

        String[][] map = new String[rowNumber][columnNumber];
        numberOfClosestMine(map);

        String[][] gameMap = new String[rowNumber][columnNumber];
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                gameMap[i][j] = "-";
            }
        }

        int move = 0;
        int numberOfMine = (rowNumber * columnNumber) / 4;
        int numberOfSafeArea = (rowNumber * columnNumber) - numberOfMine;
        boolean isWin = false;
        boolean isFindMine = false;

        while (isWin != true && isFindMine != true) {
            System.out.println("===========================");
            for (int i = 0; i < rowNumber; i++) {
                for (int j = 0; j < columnNumber; j++) {
                    System.out.print(gameMap[i][j] + "  ");
                }
                System.out.println();
            }

            boolean isEntryIncorrect = false;

            System.out.print("Satır Giriniz : ");
            int row = input.nextInt();

            if (row < 0 || row > rowNumber - 1){
                isEntryIncorrect = true;

                while (isEntryIncorrect) {
                    System.out.println("Hatalı satır girişi yaptınız!");
                    System.out.print("Satır Giriniz : ");
                    row = input.nextInt();

                    if (row >= 0 && row < rowNumber){
                        isEntryIncorrect = false;
                    }
                }
            }

            System.out.print("Sütun Giriniz : ");
            int column = input.nextInt();

            if (column < 0 || column > columnNumber - 1){
                isEntryIncorrect = true;

                while (isEntryIncorrect) {
                    System.out.println("Hatalı sütun girişi yaptınız!");
                    System.out.print("Sütun Giriniz : ");
                    column = input.nextInt();

                    if (column >= 0 && column < columnNumber){
                        isEntryIncorrect = false;
                    }
                }
            }

            gameMap[row][column] = map[row][column];

            if (gameMap[row][column] == "*") {
                isFindMine = true;
                System.out.println("Game Over!!");
                System.out.println("===========================");
                System.out.println("\nMayınların Konumu");
                for (int i = 0; i < rowNumber; i++) {
                    for (int j = 0; j < columnNumber; j++) {
                        System.out.print(map[i][j] + "  ");
                    }
                    System.out.println();
                }
            }
            move++;

            if (move == numberOfSafeArea) {
                isWin = true;
                System.out.println("Oyunu Kazandınız!");
                for (int i = 0; i < rowNumber; i++) {
                    for (int j = 0; j < columnNumber; j++) {
                        System.out.print(map[i][j] + "  ");
                    }
                    System.out.println();
                }
                System.out.println("===========================");
            }
        }
    }
}

