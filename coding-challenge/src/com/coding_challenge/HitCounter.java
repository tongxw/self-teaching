package com.coding_challenge;

public class HitCounter {
    double[] buckets; // one bucket for one second, to save the loan amount
    int[] timestamps; // timestamp_idx -> bucket_idx

    public HitCounter() {
        this.buckets = new double[3600];
        this.timestamps = new int[3600];
    }

    public void processAmount(double amount, int timestamp) {
        int idx = timestamp % 3600;
        if (timestamps[idx] == timestamp) {
            // same timestamp
            buckets[idx] += amount;
        } else {
            // new timestamp, the old one is over one hour
            timestamps[idx] = timestamp;
            buckets[idx] = amount;
        }
    }

    public double getTotalAmount(int timestamp) {
        double total = 0d;
        for (int i=0; i<3600; i++) {
            // sum up all the buckets where the timestamp within 1 hour
            if (timestamp - timestamps[i] <= 3600) {
                total += buckets[i];
            }
        }

        return total;
    }

    public static void test() {
        HitCounter counter = new HitCounter();
        for (int i=0; i<3600; i++) {
            counter.processAmount(1, i);
        }

        for (int i=0; i<3600; i++) {
            counter.processAmount(1, i);
        }

        for (int i=3600; i<3700; i++) {
            counter.processAmount(2, i);
        }

        // [3600, 3700) => 200, 100sec
        // [100,  3600) => 3500, 3500sec
        System.out.println("total: " + counter.getTotalAmount(3800));
    }
}
