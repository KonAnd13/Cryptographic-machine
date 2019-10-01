import java.io.*;

public class CryptographicMachine {
    public static void main(String[] args) {
        String fileName = args[1];
        String fileOutputName = args[2];
        switch (args[0]) {
            case "-e":
                try {
                    FileInputStream inputStream = new FileInputStream(fileName);
                    FileWriter fileWriter = new FileWriter(fileOutputName);

                    while (inputStream.available() > 0) {
                        int b = inputStream.read();
                        int a = b / 10;
                        if (a == 0) {
                            fileWriter.write("00" + b);
                        } else if (a / 10 == 0) {
                            fileWriter.write("0" + b);
                        } else {
                            fileWriter.write("" + b);
                        }
                        fileWriter.flush();
                    }

                    fileWriter.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "-d":
                try {
                    FileInputStream inputStream = new FileInputStream(fileName);
                    FileOutputStream outputStream = new FileOutputStream(fileOutputName);

                    while (inputStream.available() > 0) {
                        byte[] arr = new byte[3];
                        inputStream.read(arr);
                        String s;
                        if ((char) arr[0] != 0) {
                            s = (char) arr[0] + "" + (char) arr[1] + (char) arr[2];
                        } else if ((char) arr[1] != 0) {
                            s = "" + (char) arr[1] + (char) arr[2];
                        } else {
                            s = "" + (char) arr[2];
                        }

                        int a = Integer.parseInt(s);
                        outputStream.write(a);
                        outputStream.flush();
                    }

                    outputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
