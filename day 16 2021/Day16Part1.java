import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day16Part1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        Stream<String> bitStream = input.chars().mapToObj(x -> (char) x).flatMap(x -> {
            int xValue = Integer.parseInt(String.valueOf(x), 16);
            return Stream.of(String.format("%4s", Integer.toBinaryString(xValue)).replace(' ', '0').split(""));
        });

        int count = 0;


        Parser parser = new Parser(bitStream);
        while(parser.bitStream.hasNext()) {
            count += parser.getVerNum();
            switch(parser.getTypeId()) {
                case 4:
                    parser.getLiteralValue();
                    break;
                default:
                    int lengthTypeId = parser.getLengthTypeID();
                    int packetLength = parser.getPacketLength(lengthTypeId);
                    if (lengthTypeId == 0) {
                        
                    }

                    
            }
        }
        

        sc.close();
    }
}

class Parser {
    Iterator<String> bitStream;

    Parser(Stream<String> bitStream) {
        this.bitStream = bitStream.iterator();
    }

    public String nextNBits(int n) {
        
        String[] bits = new String[n];

        for (int i = 0; i < n; i++) {
            bits[i] = bitStream.next();
        }

        return String.join("", bits);
    }
    
    public int getVerNum() {
        String bitString = this.nextNBits(3);
        return Integer.parseInt(bitString, 2);
    }

    public int getTypeId() {
        String bitString = this.nextNBits(3);
        return Integer.parseInt(bitString, 2);
    }

    public int getLiteralValue() {
        ArrayList<String> bits = new ArrayList<>();
        while(this.nextNBits(1) == "1") {
            bits.add(this.nextNBits(4));
        }

        return Integer.parseInt(String.join("", bits), 2);
    }

    public int getLengthTypeID() {
        return Integer.parseInt(String.join("", this.nextNBits(1)), 2);
    }

    public int getPacketLength(int type) {
        if (type == 0) return Integer.parseInt(String.join("", this.nextNBits(15)), 2);
        else return Integer.parseInt(String.join("", this.nextNBits(11)), 2);
    }

}

abstract class Packet {
    int verNum;
    int packetTypeID;

    Packet(int verNum, int packetTypeID) {
        this.verNum = verNum;
        this.packetTypeID = packetTypeID;
    }
}

class LiteralValuePacket extends Packet {
    int literalValue;

    LiteralValuePacket(int verNum, int packetTypeID, int literalValue) {
        super(verNum, packetTypeID);
        this.literalValue = literalValue;
    }
}

class OperatorPacket extends Packet {
    ArrayList<Packet> subpackets;
    int lengthTypeID;
    int length;
    Parser parser;

    OperatorPacket(int verNum, int packetTypeID, int lengthTypeID, Parser parser) {
        super(verNum, packetTypeID);
        this.lengthTypeID = lengthTypeID;
        this.parser = parser;

        length = parser.getPacketLength(lengthTypeID);
        if (lengthTypeID == 0) {
            while (length > 0) {
                int subpacketVer = parser.getVerNum();
                int subpacketType = parser.getTypeId();
                Packet packet = subpacketType == 4 
                    ? new LiteralValuePacket(subpacketVer, subpacketType, parser.getLiteralValue()) 
                    : new OperatorPacket(subpacketVer, subpacketType, parser.getLengthTypeID(), parser);
            }
        }
    }
}


