package com.coding_challenge;

public class StringCompress {
    public static char[] compress(char[] input) {
        // aaabbb => a3b3
        // abbbb => ab4
        StringBuilder out = new StringBuilder();

        // two-pointers
        int n = input.length;
        int read = 0;
        while (read < n) {
            // read duplicate chars
            out.append(input[read]);
            int readEnd = read;
            // 记住这里读到+1
            while (readEnd + 1 < n && input[readEnd + 1] == input[read]) {
                readEnd++;
            }

            int count = readEnd - read + 1;
            if (count > 1) {
                // append
                out.append(String.valueOf(count));
            }

            read = readEnd + 1;
        }

        return out.toString().toCharArray();
    }

    public static char[] decompress(char[] input) {
        // a3b3 => aaabbb
        StringBuilder out = new StringBuilder();
        int read = 0;
        int n = input.length;
        while (read < n) {
            if (Character.isLetter(input[read])) {
                out.append(input[read]);
                read++;
            } else if (Character.isDigit(input[read])) {
                // read all digit
                int num = 0;
                while (read < n && Character.isDigit(input[read])) {
                    num = num * 10 + (input[read] - '0');
                    read++;
                }

                char c = out.charAt(out.length() - 1);
                num -= 1; // 前面已经有一个了
                while(num-- > 0) {
                    out.append(c);
                }
            } else {
                // skip it
                read++;
            }
        }

        return out.toString().toCharArray();
    }

    public static char[] compressO1(char[] input) {
        // aaabbb => a3b3
        // abbbb => ab4

        // two-pointers
        int n = input.length;
        int read = 0;
        int write = 0;
        while (read < n) {
            // read duplicate chars
            int readEnd = read;
            while (readEnd + 1 < n && input[readEnd + 1] == input[read]) {
                readEnd++;
            }

            input[write++] = input[read];

            int count = readEnd - read + 1;
            if (count > 1) {
                // append
                char[] nums = String.valueOf(count).toCharArray();
                for (char num : nums) {
                    input[write++] = num;
                }
            }

            read = readEnd + 1;
        }

        for (int i=write; i<n; i++) {
            input[i] = ' ';
        }

        return input;
    }

    private static char[] decompresssFollowup(char[] input) {
        // /44/35b11a4c => 4444 33333 bbbbbbbbb aaaa c
        StringBuilder out = new StringBuilder();
        int read = 0;
        int n = input.length;
        while (read < n) {
            if (input[read] == '/') {
                out.append(input[read+1]);
                read += 2;
            } else if (Character.isLetter(input[read])) {
                out.append(input[read]);
                read++;
            } else if (Character.isDigit(input[read])) {
                // read all digit
                int num = 0;
                while (read < n && Character.isDigit(input[read])) {
                    num = num * 10 + (input[read] - '0');
                    read++;
                }

                char c = out.charAt(out.length() - 1);
                num -= 1; // 前面已经有一个了
                while(num-- > 0) {
                    out.append(c);
                }
            } else {
                read++;
            }
        }

        return out.toString().toCharArray();
    }

    private static char[] compressFollowup(char[] input) {
        // 4444 33333 bbbbbbbbb aaaa c => /44 /35 b11 a4 c
        StringBuilder out = new StringBuilder();

        // two-pointers
        int n = input.length;
        int read = 0;
        while (read < n) {
            // read duplicate chars
            if (Character.isDigit(input[read])) {
                out.append("/");
            }
            out.append(input[read]);

            int readEnd = read;
            // 记住这里读到+1
            while (readEnd + 1 < n && input[readEnd + 1] == input[read]) {
                readEnd++;
            }

            int count = readEnd - read + 1;
            if (count > 1) {
                // append
                out.append(String.valueOf(count));
            }

            read = readEnd + 1;
        }

        return out.toString().toCharArray();


    }
    public static void test() {
        String str = "abbbccccc";

        char[] compressd = compress(str.toCharArray());
        System.out.println(compressd);

        char[] decompressd = decompress(compressd);
        System.out.println(decompressd);

        // follow up
        //  /44/35b11a4c
        str = "/44/35b11a4c";
        decompressd = decompresssFollowup(str.toCharArray());
        System.out.println(decompressd);

        compressd = compressFollowup(decompressd);
        System.out.println(compressd);


    }


}
