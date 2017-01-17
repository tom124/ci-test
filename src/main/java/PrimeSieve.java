import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class PrimeSieve {
	public static int numPrimes(int upTo) {
		boolean[] nonPrimes = new boolean[upTo + 1];
		int numPrimes = 0;
		for (int i = 2; i <= upTo; i++) {
			if (!nonPrimes[i]) {
				numPrimes++;
				int j = 2 * i;
				if(j > 0) {
					while (j <= upTo) {
						nonPrimes[j] = true;
						j += i;
					}
				}
			}
		}
		return numPrimes;
	}

	public static int numPrimes2(int upTo) {
		long[] nonPrimes = new long[upTo / 64 + 1];
		int numPrimes = 0;
		for (int i = 2; i <= upTo; i++) {
			int index = i / 64;
			int remainder = i % 64;
			long filtered = (nonPrimes[index] >>> remainder) & 1;
			if (filtered == 0L) {
				numPrimes++;
				int j = 2 * i;
				while (j <= upTo) {
					int jindex = j / 64;
					int jremainder = j % 64;
					nonPrimes[jindex] = nonPrimes[jindex] | (1L << jremainder);
					j += i;
				}
			}
		}
		return numPrimes;
	}

	public static int numPrimes3(int upTo) {
		long[] nonPrimes = new long[upTo / 64 + 1];
		int numPrimes = 0;

		int lowMax = upTo < 63 ? upTo : 63;

		for (int i = 2; i <= lowMax; i++) {
			int index = i / 64;
			int remainder = i % 64;
			long filtered = (nonPrimes[index] >>> remainder) & 1;
			if (filtered == 0L) {
				numPrimes++;
				int j = 2 * i;
				while (j <= upTo) {
					int jindex = j / 64;
					int jremainder = j % 64;
					nonPrimes[jindex] = nonPrimes[jindex] | (1L << jremainder);
					j += i;
				}
			}
		}

		for (int i = 64; i < (upTo/64) * 64;) {
			int index = i / 64;
			for(int remainder = 0; remainder < 64; remainder++)
			{
				long filtered = (nonPrimes[index] >>> remainder) & 1;
				if (filtered == 0L) {
					numPrimes++;
					int j = 2 * i;
					while (j <= upTo) {
						int jindex = j / 64;
						int jremainder = j % 64;
						nonPrimes[jindex] = nonPrimes[jindex] | (1L << jremainder);
						j += i;
					}
				}
				i++;
			}
		}

		for (int i = (upTo/64) * 64; i <= upTo; i++) {
			int index = i / 64;
			int remainder = i % 64;
			long filtered = (nonPrimes[index] >>> remainder) & 1;
			if (filtered == 0L) {
				numPrimes++;
				int j = 2 * i;
				while (j <= upTo) {
					int jindex = j / 64;
					int jremainder = j % 64;
					nonPrimes[jindex] = nonPrimes[jindex] | (1L << jremainder);
					j += i;
				}
			}
		}
		return numPrimes;
	}

	public static void main2(String[] args) {
		int count = 2000000000;

		final long startTime = System.currentTimeMillis();
		int numberofPrimes = numPrimes(count);
		final long endTime = System.currentTimeMillis();
		System.out.println(numberofPrimes);
		System.out.println("Total execution time 1: " + (endTime - startTime));

//		final long startTime2 = System.currentTimeMillis();
//		int numberofPrimes2 = numPrimes2(count);
//		final long endTime2 = System.currentTimeMillis();
//		System.out.println(numberofPrimes2);
//		System.out.println("Total execution time 2: " + (endTime2 - startTime2));
//
//		final long startTime3 = System.currentTimeMillis();
//		int numberofPrimes3 = numPrimes3(count);
//		final long endTime3 = System.currentTimeMillis();
//		System.out.println(numberofPrimes3);
//		System.out.println("Total execution time 3: " + (endTime3 - startTime3));
	}

	public static void main(String[] args){
        final long startTime = System.currentTimeMillis();

        int max = 1000000000;

        BitSet nonPrimes = new BitSet(max +1);

        for(int i = 2; i*i<=max; i++){
            if(!nonPrimes.get(i)){
                for(int j=2; j*i<=max; j++){
                    nonPrimes.set(i*j);
                }
            }
        }

        System.out.println(max - nonPrimes.cardinality() - 1);

        final long endTime = System.currentTimeMillis();

        System.out.println("Execution time: " + (endTime - startTime));
    }
}
