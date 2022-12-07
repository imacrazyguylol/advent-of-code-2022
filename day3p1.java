
        // System.out.println(new String(arr[0][1].toCharArray()));
        // part 1

        /*
         * for (i = 0; i < arr.length; i++) {
         * char[] s1 = arr[i][0].toCharArray();
         * char[] s2 = arr[i][1].toCharArray();
         * 
         * Arrays.sort(s1);
         * Arrays.sort(s2);
         * 
         * Map<Integer, Character> m = new HashMap<>();
         * Map<Integer, Character> m2 = new HashMap<>();
         * 
         * for (int q = 0; q < s1.length; q++) {
         * m.put(q, s1[q]);
         * }
         * for (int q = 0; q < s2.length; q++) {
         * m2.put(q, s2[q]);
         * }
         * 
         * for (int j = 0; j < s1.length; j++) {
         * if (m.get(j + 1) != null && s1[j] == m.get(j + 1)) {
         * continue;
         * }
         * for (int k = 0; k < s2.length; k++) {
         * if (m2.get(k + 1) != null && s2[k] == m2.get(k + 1)) {
         * continue;
         * }
         * if (s1[j] == s2[k]) {
         * System.out.println(s1[j]);
         * if (s1[j] < 91) {
         * sum += s1[j] - '@' + 26;
         * } else {
         * sum += s1[j] - 'F' - 26;
         * }
         * }
         * }
         * }
         * 
         * // System.out.println(new String(s1) + "\n" + new String(s2));
         * }
         */