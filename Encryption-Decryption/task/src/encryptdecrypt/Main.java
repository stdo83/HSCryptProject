package encryptdecrypt;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;


class Crypt {
    private Algorithm algorithm;

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String decrypt(String msg, int key) {
        return this.algorithm.decrypt(msg, key);
    }

    public String encrypt(String msg, int key) {
        return this.algorithm.encrypt(msg, key);
    }
}

interface Algorithm {
    String encrypt(String msg, int key);

    String decrypt(String msg, int key);
}

class AlgorithmShift implements Algorithm {
    @Override
    public String encrypt(String msg, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : msg.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String msg, int offset) {
        return encrypt(msg, 26 - (offset % 26));
    }
}

class AlgorithmUnicode implements Algorithm {
    @Override
    public String encrypt(String msg, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            int charCode = msg.charAt(i);
            char encryptedChar = (char) (charCode + key);
            result.append(encryptedChar);
        }
        return result.toString();
    }

    @Override
    public String decrypt(String msg, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            int charCode = msg.charAt(i);
            char decryptedChar = (char) (charCode - key);
            result.append(decryptedChar);
        }
        return result.toString();
    }
}

public class Main {

    public static void main(String[] args) {
        String mode = "enc";
        String msg = "";
        String alg = "shift";
        int key = 0;
        String inFilePath = "";
        String outFilePath = "";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-in":
                    inFilePath = args[i + 1];
                    break;
                case "-out":
                    outFilePath = args[i + 1];
                    break;
                case "-data":
                    msg = args[i + 1];
                    break;
                case "-alg":
                    alg = args[i + 1];
                    break;
            }
        }

        try {
            if (!inFilePath.equals("") && msg.equals("")) {
                File inFile = new File(inFilePath);
                Scanner scanner = new Scanner(inFile);
                msg = scanner.nextLine();
                scanner.close();
            }
        } catch (Exception e) {
            System.out.println("exc in");
        }

        Crypt crypt = new Crypt();
        if (alg.equals("shift")) {
            crypt.setAlgorithm(new AlgorithmShift());
        } else if (alg.equals("unicode")) {
            crypt.setAlgorithm(new AlgorithmUnicode());
        }

        String resultMsg = "";

        if (mode.equals("enc")) {
            resultMsg = crypt.encrypt(msg, key);
        } else if (mode.equals("dec")) {
            resultMsg = crypt.decrypt(msg, key);
        }

        if (!outFilePath.equals("")) {
            try {
                FileWriter out = new FileWriter(outFilePath);
                out.write(resultMsg);
                out.close();
            } catch (IOException e) {
                System.out.println("exc out");
            }
        } else {
            System.out.println(resultMsg);
        }
    }
}
