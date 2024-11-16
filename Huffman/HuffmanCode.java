// Rusheel Chande
// June 2 2023

// This program implements Huffman codiung for data compression, constructing a huffman tree,
// saving codes and frequencies, translating input bits, and performing basic comparisons.

import java.io.*;
import java.util.*;

public class HuffmanCode implements Comparable<HuffmanCode> {
    private HuffmanNode root;

    private static class HuffmanNode implements Comparable<HuffmanNode> {
        int frequency;
        int code;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(int code, int frequency, HuffmanNode left, HuffmanNode right) {
            this.code = code;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        public int compareTo(HuffmanNode o) {
            return this.frequency - o.frequency;
        }
    }

    // This method is the constructor for HuffmanCode class that initializes the Huffman tree
    // based on given frequencies
    // Pre-condition: The frequencies array cannot be null
    // Post-condition: Creates a HUffman tree based on the given frequencies and sets the root
    //                 node.
    public HuffmanCode(int[] frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.add(new HuffmanNode(i, frequencies[i], null, null));
            }
        }

        while (queue.size() > 1) {
            HuffmanNode first = queue.poll();
            HuffmanNode second = queue.poll();
            HuffmanNode merge = new HuffmanNode(-1, first.frequency + second.frequency, first, second);
            queue.add(merge);
        }
        root = queue.poll();
    }

    // This method is the constructor for the HUffmanCode class that initializes the 
    // Huffman tree based on iput from a Scanner
    // Pre-condition: The input Scanner must not be null
    // Post-condition: Creates a Huffman tree bsed on the input codes and frequencies
    //                 from the Scanner and sets the root node
    public HuffmanCode(Scanner input) {
        root = new HuffmanNode(-1, -1, null, null);
        while (input.hasNextLine()) {
            int codeValue = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            this.root = addToTree(root, codeValue, code, 0);
        }
    }

    // This method is the recursive method to ass a node to the Huffman tree based on the given
    // code and value
    // Pre-condition: The current node, code value, and code string must be valid
    // Post-condition: Adds a new node witht the given code value to the Huffman tree at the 
    //                 appropriate position.
    private HuffmanNode addToTree(HuffmanNode current, int codeValue, String code, int index) {
        if (index == code.length()) {
            return new HuffmanNode(codeValue, 0, null, null);
        }

        if (current == null) {
            current = new HuffmanNode(-1, 0, null, null);
        }

        if (code.charAt(index) == '0') {
            current.left = addToTree(current.left, codeValue, code, index + 1);
        } else {
            current.right = addToTree(current.right, codeValue, code, index + 1);
        }

        return current;
    } 

    // This method saves the Huffman tree codes and frequencies to a specified PrintStream
    // Pre-condition: The output PrintStream cannot be null
    // Post-condition: It writes the codes and frequencies of the HUffman tree to the output
    //                 PrintStream.
    public void save(PrintStream output) {
        save(root, output, "");
    }

    // This method is a recursive method to save the codes and frequencies of the HUffman tree
    // nodes
    // Pre-condition: The current node, output PrintStream, and code string must be valid
    // Post-condition: Writes the code and frequency of each node in the HUffman tree to the
    //                 output PrintStream
    private void save(HuffmanNode node, PrintStream output, String code) {
        if (node == null) {
            return;
        }

        if (node.code != -1) {
            output.println(node.code);
            output.println(code);
        }

        save(node.left, output, code + "0");
        save(node.right, output, code + "1");
    }

    // This method translates the input bits using the Huffman tree and writes the 
    // characters to the output PrintStream
    // Pre-condition: The input BitInputStream and output PrintStream must not be null
    // Post-condition: Reads the input bits, traverses the tree, and writes the corresponding
    //                 characters to the output PrintStream 
    public void translate(BitInputStream input, PrintStream output) {
        while (input.hasNextBit()) {
            HuffmanNode current = root;
            while (current.code == -1) {
                int bit = input.nextBit();
                if (bit == 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            output.write(current.code);
        }
    }

    // This method compares this HUffmanCode object with another HUffmanCode object
    // Pre-condition: The input HUffmanCode object cannot be null
    // Post-condition: It returns 0
    public int compareTo(HuffmanCode o) {
        return 0;
    }
}
